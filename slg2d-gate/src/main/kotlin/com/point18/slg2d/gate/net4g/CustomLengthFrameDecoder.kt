package com.point18.slg2d.gate.net4g

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import io.netty.handler.codec.CorruptedFrameException
import io.netty.handler.codec.DecoderException
import io.netty.handler.codec.TooLongFrameException
import io.netty.handler.codec.serialization.ObjectDecoder
import java.nio.ByteOrder


open class CustomLengthFrameDecoder(
    private val byteOrder: ByteOrder,
    private val maxFrameLength: Int,
    private val lengthFieldOffset: Int,
    private val lengthFieldLength: Int,
    private val lengthAdjustment: Int,
    private val initialBytesToStrip: Int,
    private val failFast: Boolean
) :
    ByteToMessageDecoder() {
    private val lengthFieldEndOffset: Int
    private var discardingTooLongFrame: Boolean = false
    private var tooLongFrameLength: Long = 0
    private var bytesToDiscard: Long = 0

    /**
     * Creates a new instance.
     *
     * @param maxFrameLength
     * the maximum length of the frame.  If the length of the frame is
     * greater than this value, [TooLongFrameException] will be
     * thrown.
     * @param lengthFieldOffset
     * the offset of the length field
     * @param lengthFieldLength
     * the length of the length field
     * @param lengthAdjustment
     * the compensation value to add to the value of the length field
     * @param initialBytesToStrip
     * the number of first bytes to strip out from the decoded frame
     * @param failFast
     * If <tt>true</tt>, a [TooLongFrameException] is thrown as
     * soon as the decoder notices the length of the frame will exceed
     * <tt>maxFrameLength</tt> regardless of whether the entire frame
     * has been read.  If <tt>false</tt>, a [TooLongFrameException]
     * is thrown after the entire frame that exceeds <tt>maxFrameLength</tt>
     * has been read.
     */
    @JvmOverloads constructor(
        maxFrameLength: Int, lengthFieldOffset: Int, lengthFieldLength: Int,
        lengthAdjustment: Int = 0, initialBytesToStrip: Int = 0, failFast: Boolean = true
    ) : this(
        ByteOrder.BIG_ENDIAN, maxFrameLength, lengthFieldOffset, lengthFieldLength,
        lengthAdjustment, initialBytesToStrip, failFast
    ) {
    }

    init {
        if (maxFrameLength <= 0) {
            throw IllegalArgumentException(
                "maxFrameLength must be a positive integer: $maxFrameLength"
            )
        }

        if (lengthFieldOffset < 0) {
            throw IllegalArgumentException(
                "lengthFieldOffset must be a non-negative integer: $lengthFieldOffset"
            )
        }

        if (initialBytesToStrip < 0) {
            throw IllegalArgumentException(
                "initialBytesToStrip must be a non-negative integer: $initialBytesToStrip"
            )
        }

        if (lengthFieldOffset > maxFrameLength - lengthFieldLength) {
            throw IllegalArgumentException(
                "maxFrameLength (" + maxFrameLength + ") " +
                        "must be equal to or greater than " +
                        "lengthFieldOffset (" + lengthFieldOffset + ") + " +
                        "lengthFieldLength (" + lengthFieldLength + ")."
            )
        }
        lengthFieldEndOffset = lengthFieldOffset + lengthFieldLength
    }

    @Throws(Exception::class)
    override fun decode(ctx: ChannelHandlerContext, inBuf: ByteBuf, out: MutableList<Any>) {
        val decoded = decode(ctx, inBuf)
        if (decoded != null) {
            out.add(decoded)
        }
    }

    private fun discardingTooLongFrame(inBuf: ByteBuf) {
        var bytesToDiscard = this.bytesToDiscard
        val localBytesToDiscard = Math.min(bytesToDiscard, inBuf.readableBytes().toLong()).toInt()
        inBuf.skipBytes(localBytesToDiscard)
        bytesToDiscard -= localBytesToDiscard.toLong()
        this.bytesToDiscard = bytesToDiscard

        failIfNecessary(false)
    }

    private fun failOnNegativeLengthField(inBuf: ByteBuf, frameLength: Long, lengthFieldEndOffset: Int) {
        inBuf.skipBytes(lengthFieldEndOffset)
        throw CorruptedFrameException(
            "negative pre-adjustment length field: $frameLength"
        )
    }

    private fun failOnFrameLengthLessThanLengthFieldEndOffset(
        inBuf: ByteBuf,
        frameLength: Long,
        lengthFieldEndOffset: Int
    ) {
        inBuf.skipBytes(lengthFieldEndOffset)
        throw CorruptedFrameException(
            ("Adjusted frame length (" + frameLength + ") is less " +
                    "than lengthFieldEndOffset: " + lengthFieldEndOffset)
        )
    }

    private fun exceededFrameLength(inBuf: ByteBuf, frameLength: Long) {
        val discard = frameLength - inBuf.readableBytes()
        tooLongFrameLength = frameLength

        if (discard < 0) {
            // buffer contains more bytes then the frameLength so we can discard all now
            inBuf.skipBytes(frameLength.toInt())
        } else {
            // Enter the discard mode and discard everything received so far.
            discardingTooLongFrame = true
            bytesToDiscard = discard
            inBuf.skipBytes(inBuf.readableBytes())
        }
        failIfNecessary(true)
    }

    private fun failOnFrameLengthLessThanInitialBytesToStrip(
        inBuf: ByteBuf,
        frameLength: Long,
        initialBytesToStrip: Int
    ) {
        inBuf.skipBytes(frameLength.toInt())
        throw CorruptedFrameException(
            ("Adjusted frame length (" + frameLength + ") is less " +
                    "than initialBytesToStrip: " + initialBytesToStrip)
        )
    }

    /**
     * Create a frame out of the [ByteBuf] and return it.
     *
     * @param   ctx             the [ChannelHandlerContext] which this [ByteToMessageDecoder] belongs to
     * @param   inBuf              the [ByteBuf] from which to read data
     * @return  frame           the [ByteBuf] which represent the frame or `null` if no frame could
     * be created.
     */
    @Throws(Exception::class)
    protected open fun decode(ctx: ChannelHandlerContext, inBuf: ByteBuf): Any? {
        if (discardingTooLongFrame) {
            discardingTooLongFrame(inBuf)
        }

        if (inBuf.readableBytes() < lengthFieldEndOffset + 2) {
            return null
        }

        val actualLengthFieldOffset = inBuf.readerIndex() + lengthFieldOffset
        var frameLength = getUnadjustedFrameLength(inBuf, actualLengthFieldOffset, lengthFieldLength, byteOrder)

        if (frameLength < 0) {
            failOnNegativeLengthField(inBuf, frameLength, lengthFieldEndOffset)
        }

        frameLength += (lengthAdjustment + lengthFieldEndOffset).toLong()

        if (frameLength < lengthFieldEndOffset) {
            failOnFrameLengthLessThanLengthFieldEndOffset(inBuf, frameLength, lengthFieldEndOffset)
        }

        val msgType = inBuf.getShort(lengthFieldEndOffset)
        if (frameLength > maxFrameLength) {
            println("收到客户端发送的错误消息，对应的消息号为：$msgType")
            exceededFrameLength(inBuf, frameLength)
            return null
        } else {
            println("收到客户端发送的消息的对应消息号为：$msgType")
        }

        // never overflows because it's less than maxFrameLength
        val frameLengthInt = frameLength.toInt()
        if (inBuf.readableBytes() < frameLengthInt) {
            return null
        }

        if (initialBytesToStrip > frameLengthInt) {
            failOnFrameLengthLessThanInitialBytesToStrip(inBuf, frameLength, initialBytesToStrip)
        }
        inBuf.skipBytes(initialBytesToStrip)

        // extract frame
        val readerIndex = inBuf.readerIndex()
        val actualFrameLength = frameLengthInt - initialBytesToStrip
        val frame = extractFrame(ctx, inBuf, readerIndex, actualFrameLength)
        inBuf.readerIndex(readerIndex + actualFrameLength)
        return frame
    }

    /**
     * Decodes the specified region of the buffer into an unadjusted frame length.  The default implementation is
     * capable of decoding the specified region into an unsigned 8/16/24/32/64 bit integer.  Override this method to
     * decode the length field encoded differently.  Note that this method must not modify the state of the specified
     * buffer (e.g. `readerIndex`, `writerIndex`, and the content of the buffer.)
     *
     * @throws DecoderException if failed to decode the specified region
     */
    protected fun getUnadjustedFrameLength(specBuf: ByteBuf, offset: Int, length: Int, order: ByteOrder): Long {
        var buf = specBuf
        buf = buf.order(order)
        val frameLength: Long
        when (length) {
            1 -> frameLength = buf.getUnsignedByte(offset).toLong()
            2 -> frameLength = buf.getUnsignedShort(offset).toLong()
            3 -> frameLength = buf.getUnsignedMedium(offset).toLong()
            4 -> frameLength = buf.getUnsignedInt(offset)
            8 -> frameLength = buf.getLong(offset)
            else -> throw DecoderException(
                "unsupported lengthFieldLength: $lengthFieldLength (expected: 1, 2, 3, 4, or 8)"
            )
        }
        return frameLength
    }

    private fun failIfNecessary(firstDetectionOfTooLongFrame: Boolean) {
        if (bytesToDiscard == 0L) {
            // Reset to the initial state and tell the handlers that
            // the frame was too large.
            val tooLongFrameLength = this.tooLongFrameLength
            this.tooLongFrameLength = 0
            discardingTooLongFrame = false
            if (!failFast || firstDetectionOfTooLongFrame) {
                fail(tooLongFrameLength)
            }
        } else {
            // Keep discarding and notify handlers if necessary.
            if (failFast && firstDetectionOfTooLongFrame) {
                fail(tooLongFrameLength)
            }
        }
    }

    /**
     * Extract the sub-region of the specified buffer.
     *
     *
     * If you are sure that the frame and its content are not accessed after
     * the current [.decode]
     * call returns, you can even avoid memory copy by returning the sliced
     * sub-region (i.e. <tt>return buffer.slice(index, length)</tt>).
     * It's often useful when you convert the extracted frame into an object.
     * Refer to the source code of [ObjectDecoder] to see how this method
     * is overridden to avoid memory copy.
     */
    protected open fun extractFrame(ctx: ChannelHandlerContext, buffer: ByteBuf, index: Int, length: Int): ByteBuf {
        return buffer.retainedSlice(index, length)
    }

    private fun fail(frameLength: Long) {
        if (frameLength > 0) {
            throw TooLongFrameException(
                ("Adjusted frame length exceeds " + maxFrameLength +
                        ": " + frameLength + " - discarded")
            )
        } else {
            throw TooLongFrameException(
                ("Adjusted frame length exceeds " + maxFrameLength +
                        " - discarding")
            )
        }
    }
}