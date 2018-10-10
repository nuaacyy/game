// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 * World到World的ask返回
 * </pre>
 *
 * Protobuf type {@code pb4server.World2WorldAskResp}
 */
public  final class World2WorldAskResp extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.World2WorldAskResp)
    World2WorldAskRespOrBuilder {
  // Use World2WorldAskResp.newBuilder() to construct.
  private World2WorldAskResp(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private World2WorldAskResp() {
    playerId_ = 0L;
    worldId_ = 0L;
    clientMsgNo_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private World2WorldAskResp(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            playerId_ = input.readInt64();
            break;
          }
          case 16: {

            worldId_ = input.readInt64();
            break;
          }
          case 24: {

            clientMsgNo_ = input.readInt32();
            break;
          }
          case 90: {
            pb4server.CheckMoveServerXyRt.Builder subBuilder = null;
            if (msgCase_ == 11) {
              subBuilder = ((pb4server.CheckMoveServerXyRt) msg_).toBuilder();
            }
            msg_ =
                input.readMessage(pb4server.CheckMoveServerXyRt.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((pb4server.CheckMoveServerXyRt) msg_);
              msg_ = subBuilder.buildPartial();
            }
            msgCase_ = 11;
            break;
          }
          case 98: {
            pb4server.ChangeWatchAskResp.Builder subBuilder = null;
            if (msgCase_ == 12) {
              subBuilder = ((pb4server.ChangeWatchAskResp) msg_).toBuilder();
            }
            msg_ =
                input.readMessage(pb4server.ChangeWatchAskResp.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((pb4server.ChangeWatchAskResp) msg_);
              msg_ = subBuilder.buildPartial();
            }
            msgCase_ = 12;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalWkt.internal_static_pb4server_World2WorldAskResp_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_World2WorldAskResp_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.World2WorldAskResp.class, pb4server.World2WorldAskResp.Builder.class);
  }

  private int msgCase_ = 0;
  private java.lang.Object msg_;
  public enum MsgCase
      implements com.google.protobuf.Internal.EnumLite {
    CHECKMOVESERVERXYRT(11),
    CHANGEWATCHASKRESP(12),
    MSG_NOT_SET(0);
    private final int value;
    private MsgCase(int value) {
      this.value = value;
    }
    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static MsgCase valueOf(int value) {
      return forNumber(value);
    }

    public static MsgCase forNumber(int value) {
      switch (value) {
        case 11: return CHECKMOVESERVERXYRT;
        case 12: return CHANGEWATCHASKRESP;
        case 0: return MSG_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  public MsgCase
  getMsgCase() {
    return MsgCase.forNumber(
        msgCase_);
  }

  public static final int PLAYERID_FIELD_NUMBER = 1;
  private long playerId_;
  /**
   * <code>int64 playerId = 1;</code>
   */
  public long getPlayerId() {
    return playerId_;
  }

  public static final int WORLDID_FIELD_NUMBER = 2;
  private long worldId_;
  /**
   * <code>int64 worldId = 2;</code>
   */
  public long getWorldId() {
    return worldId_;
  }

  public static final int CLIENTMSGNO_FIELD_NUMBER = 3;
  private int clientMsgNo_;
  /**
   * <code>int32 clientMsgNo = 3;</code>
   */
  public int getClientMsgNo() {
    return clientMsgNo_;
  }

  public static final int CHECKMOVESERVERXYRT_FIELD_NUMBER = 11;
  /**
   * <code>.pb4server.CheckMoveServerXyRt checkMoveServerXyRt = 11;</code>
   */
  public pb4server.CheckMoveServerXyRt getCheckMoveServerXyRt() {
    if (msgCase_ == 11) {
       return (pb4server.CheckMoveServerXyRt) msg_;
    }
    return pb4server.CheckMoveServerXyRt.getDefaultInstance();
  }
  /**
   * <code>.pb4server.CheckMoveServerXyRt checkMoveServerXyRt = 11;</code>
   */
  public pb4server.CheckMoveServerXyRtOrBuilder getCheckMoveServerXyRtOrBuilder() {
    if (msgCase_ == 11) {
       return (pb4server.CheckMoveServerXyRt) msg_;
    }
    return pb4server.CheckMoveServerXyRt.getDefaultInstance();
  }

  public static final int CHANGEWATCHASKRESP_FIELD_NUMBER = 12;
  /**
   * <code>.pb4server.ChangeWatchAskResp changeWatchAskResp = 12;</code>
   */
  public pb4server.ChangeWatchAskResp getChangeWatchAskResp() {
    if (msgCase_ == 12) {
       return (pb4server.ChangeWatchAskResp) msg_;
    }
    return pb4server.ChangeWatchAskResp.getDefaultInstance();
  }
  /**
   * <code>.pb4server.ChangeWatchAskResp changeWatchAskResp = 12;</code>
   */
  public pb4server.ChangeWatchAskRespOrBuilder getChangeWatchAskRespOrBuilder() {
    if (msgCase_ == 12) {
       return (pb4server.ChangeWatchAskResp) msg_;
    }
    return pb4server.ChangeWatchAskResp.getDefaultInstance();
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (playerId_ != 0L) {
      output.writeInt64(1, playerId_);
    }
    if (worldId_ != 0L) {
      output.writeInt64(2, worldId_);
    }
    if (clientMsgNo_ != 0) {
      output.writeInt32(3, clientMsgNo_);
    }
    if (msgCase_ == 11) {
      output.writeMessage(11, (pb4server.CheckMoveServerXyRt) msg_);
    }
    if (msgCase_ == 12) {
      output.writeMessage(12, (pb4server.ChangeWatchAskResp) msg_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (playerId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, playerId_);
    }
    if (worldId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, worldId_);
    }
    if (clientMsgNo_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, clientMsgNo_);
    }
    if (msgCase_ == 11) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(11, (pb4server.CheckMoveServerXyRt) msg_);
    }
    if (msgCase_ == 12) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(12, (pb4server.ChangeWatchAskResp) msg_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof pb4server.World2WorldAskResp)) {
      return super.equals(obj);
    }
    pb4server.World2WorldAskResp other = (pb4server.World2WorldAskResp) obj;

    boolean result = true;
    result = result && (getPlayerId()
        == other.getPlayerId());
    result = result && (getWorldId()
        == other.getWorldId());
    result = result && (getClientMsgNo()
        == other.getClientMsgNo());
    result = result && getMsgCase().equals(
        other.getMsgCase());
    if (!result) return false;
    switch (msgCase_) {
      case 11:
        result = result && getCheckMoveServerXyRt()
            .equals(other.getCheckMoveServerXyRt());
        break;
      case 12:
        result = result && getChangeWatchAskResp()
            .equals(other.getChangeWatchAskResp());
        break;
      case 0:
      default:
    }
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PLAYERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPlayerId());
    hash = (37 * hash) + WORLDID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getWorldId());
    hash = (37 * hash) + CLIENTMSGNO_FIELD_NUMBER;
    hash = (53 * hash) + getClientMsgNo();
    switch (msgCase_) {
      case 11:
        hash = (37 * hash) + CHECKMOVESERVERXYRT_FIELD_NUMBER;
        hash = (53 * hash) + getCheckMoveServerXyRt().hashCode();
        break;
      case 12:
        hash = (37 * hash) + CHANGEWATCHASKRESP_FIELD_NUMBER;
        hash = (53 * hash) + getChangeWatchAskResp().hashCode();
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.World2WorldAskResp parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.World2WorldAskResp parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.World2WorldAskResp parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.World2WorldAskResp parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.World2WorldAskResp parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.World2WorldAskResp parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.World2WorldAskResp parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.World2WorldAskResp parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.World2WorldAskResp parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.World2WorldAskResp parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.World2WorldAskResp parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.World2WorldAskResp parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(pb4server.World2WorldAskResp prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * World到World的ask返回
   * </pre>
   *
   * Protobuf type {@code pb4server.World2WorldAskResp}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.World2WorldAskResp)
      pb4server.World2WorldAskRespOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_World2WorldAskResp_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_World2WorldAskResp_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.World2WorldAskResp.class, pb4server.World2WorldAskResp.Builder.class);
    }

    // Construct using pb4server.World2WorldAskResp.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      playerId_ = 0L;

      worldId_ = 0L;

      clientMsgNo_ = 0;

      msgCase_ = 0;
      msg_ = null;
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_World2WorldAskResp_descriptor;
    }

    public pb4server.World2WorldAskResp getDefaultInstanceForType() {
      return pb4server.World2WorldAskResp.getDefaultInstance();
    }

    public pb4server.World2WorldAskResp build() {
      pb4server.World2WorldAskResp result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.World2WorldAskResp buildPartial() {
      pb4server.World2WorldAskResp result = new pb4server.World2WorldAskResp(this);
      result.playerId_ = playerId_;
      result.worldId_ = worldId_;
      result.clientMsgNo_ = clientMsgNo_;
      if (msgCase_ == 11) {
        if (checkMoveServerXyRtBuilder_ == null) {
          result.msg_ = msg_;
        } else {
          result.msg_ = checkMoveServerXyRtBuilder_.build();
        }
      }
      if (msgCase_ == 12) {
        if (changeWatchAskRespBuilder_ == null) {
          result.msg_ = msg_;
        } else {
          result.msg_ = changeWatchAskRespBuilder_.build();
        }
      }
      result.msgCase_ = msgCase_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof pb4server.World2WorldAskResp) {
        return mergeFrom((pb4server.World2WorldAskResp)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.World2WorldAskResp other) {
      if (other == pb4server.World2WorldAskResp.getDefaultInstance()) return this;
      if (other.getPlayerId() != 0L) {
        setPlayerId(other.getPlayerId());
      }
      if (other.getWorldId() != 0L) {
        setWorldId(other.getWorldId());
      }
      if (other.getClientMsgNo() != 0) {
        setClientMsgNo(other.getClientMsgNo());
      }
      switch (other.getMsgCase()) {
        case CHECKMOVESERVERXYRT: {
          mergeCheckMoveServerXyRt(other.getCheckMoveServerXyRt());
          break;
        }
        case CHANGEWATCHASKRESP: {
          mergeChangeWatchAskResp(other.getChangeWatchAskResp());
          break;
        }
        case MSG_NOT_SET: {
          break;
        }
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4server.World2WorldAskResp parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.World2WorldAskResp) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int msgCase_ = 0;
    private java.lang.Object msg_;
    public MsgCase
        getMsgCase() {
      return MsgCase.forNumber(
          msgCase_);
    }

    public Builder clearMsg() {
      msgCase_ = 0;
      msg_ = null;
      onChanged();
      return this;
    }


    private long playerId_ ;
    /**
     * <code>int64 playerId = 1;</code>
     */
    public long getPlayerId() {
      return playerId_;
    }
    /**
     * <code>int64 playerId = 1;</code>
     */
    public Builder setPlayerId(long value) {
      
      playerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 playerId = 1;</code>
     */
    public Builder clearPlayerId() {
      
      playerId_ = 0L;
      onChanged();
      return this;
    }

    private long worldId_ ;
    /**
     * <code>int64 worldId = 2;</code>
     */
    public long getWorldId() {
      return worldId_;
    }
    /**
     * <code>int64 worldId = 2;</code>
     */
    public Builder setWorldId(long value) {
      
      worldId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 worldId = 2;</code>
     */
    public Builder clearWorldId() {
      
      worldId_ = 0L;
      onChanged();
      return this;
    }

    private int clientMsgNo_ ;
    /**
     * <code>int32 clientMsgNo = 3;</code>
     */
    public int getClientMsgNo() {
      return clientMsgNo_;
    }
    /**
     * <code>int32 clientMsgNo = 3;</code>
     */
    public Builder setClientMsgNo(int value) {
      
      clientMsgNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 clientMsgNo = 3;</code>
     */
    public Builder clearClientMsgNo() {
      
      clientMsgNo_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.CheckMoveServerXyRt, pb4server.CheckMoveServerXyRt.Builder, pb4server.CheckMoveServerXyRtOrBuilder> checkMoveServerXyRtBuilder_;
    /**
     * <code>.pb4server.CheckMoveServerXyRt checkMoveServerXyRt = 11;</code>
     */
    public pb4server.CheckMoveServerXyRt getCheckMoveServerXyRt() {
      if (checkMoveServerXyRtBuilder_ == null) {
        if (msgCase_ == 11) {
          return (pb4server.CheckMoveServerXyRt) msg_;
        }
        return pb4server.CheckMoveServerXyRt.getDefaultInstance();
      } else {
        if (msgCase_ == 11) {
          return checkMoveServerXyRtBuilder_.getMessage();
        }
        return pb4server.CheckMoveServerXyRt.getDefaultInstance();
      }
    }
    /**
     * <code>.pb4server.CheckMoveServerXyRt checkMoveServerXyRt = 11;</code>
     */
    public Builder setCheckMoveServerXyRt(pb4server.CheckMoveServerXyRt value) {
      if (checkMoveServerXyRtBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        msg_ = value;
        onChanged();
      } else {
        checkMoveServerXyRtBuilder_.setMessage(value);
      }
      msgCase_ = 11;
      return this;
    }
    /**
     * <code>.pb4server.CheckMoveServerXyRt checkMoveServerXyRt = 11;</code>
     */
    public Builder setCheckMoveServerXyRt(
        pb4server.CheckMoveServerXyRt.Builder builderForValue) {
      if (checkMoveServerXyRtBuilder_ == null) {
        msg_ = builderForValue.build();
        onChanged();
      } else {
        checkMoveServerXyRtBuilder_.setMessage(builderForValue.build());
      }
      msgCase_ = 11;
      return this;
    }
    /**
     * <code>.pb4server.CheckMoveServerXyRt checkMoveServerXyRt = 11;</code>
     */
    public Builder mergeCheckMoveServerXyRt(pb4server.CheckMoveServerXyRt value) {
      if (checkMoveServerXyRtBuilder_ == null) {
        if (msgCase_ == 11 &&
            msg_ != pb4server.CheckMoveServerXyRt.getDefaultInstance()) {
          msg_ = pb4server.CheckMoveServerXyRt.newBuilder((pb4server.CheckMoveServerXyRt) msg_)
              .mergeFrom(value).buildPartial();
        } else {
          msg_ = value;
        }
        onChanged();
      } else {
        if (msgCase_ == 11) {
          checkMoveServerXyRtBuilder_.mergeFrom(value);
        }
        checkMoveServerXyRtBuilder_.setMessage(value);
      }
      msgCase_ = 11;
      return this;
    }
    /**
     * <code>.pb4server.CheckMoveServerXyRt checkMoveServerXyRt = 11;</code>
     */
    public Builder clearCheckMoveServerXyRt() {
      if (checkMoveServerXyRtBuilder_ == null) {
        if (msgCase_ == 11) {
          msgCase_ = 0;
          msg_ = null;
          onChanged();
        }
      } else {
        if (msgCase_ == 11) {
          msgCase_ = 0;
          msg_ = null;
        }
        checkMoveServerXyRtBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.pb4server.CheckMoveServerXyRt checkMoveServerXyRt = 11;</code>
     */
    public pb4server.CheckMoveServerXyRt.Builder getCheckMoveServerXyRtBuilder() {
      return getCheckMoveServerXyRtFieldBuilder().getBuilder();
    }
    /**
     * <code>.pb4server.CheckMoveServerXyRt checkMoveServerXyRt = 11;</code>
     */
    public pb4server.CheckMoveServerXyRtOrBuilder getCheckMoveServerXyRtOrBuilder() {
      if ((msgCase_ == 11) && (checkMoveServerXyRtBuilder_ != null)) {
        return checkMoveServerXyRtBuilder_.getMessageOrBuilder();
      } else {
        if (msgCase_ == 11) {
          return (pb4server.CheckMoveServerXyRt) msg_;
        }
        return pb4server.CheckMoveServerXyRt.getDefaultInstance();
      }
    }
    /**
     * <code>.pb4server.CheckMoveServerXyRt checkMoveServerXyRt = 11;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.CheckMoveServerXyRt, pb4server.CheckMoveServerXyRt.Builder, pb4server.CheckMoveServerXyRtOrBuilder> 
        getCheckMoveServerXyRtFieldBuilder() {
      if (checkMoveServerXyRtBuilder_ == null) {
        if (!(msgCase_ == 11)) {
          msg_ = pb4server.CheckMoveServerXyRt.getDefaultInstance();
        }
        checkMoveServerXyRtBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4server.CheckMoveServerXyRt, pb4server.CheckMoveServerXyRt.Builder, pb4server.CheckMoveServerXyRtOrBuilder>(
                (pb4server.CheckMoveServerXyRt) msg_,
                getParentForChildren(),
                isClean());
        msg_ = null;
      }
      msgCase_ = 11;
      onChanged();;
      return checkMoveServerXyRtBuilder_;
    }

    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.ChangeWatchAskResp, pb4server.ChangeWatchAskResp.Builder, pb4server.ChangeWatchAskRespOrBuilder> changeWatchAskRespBuilder_;
    /**
     * <code>.pb4server.ChangeWatchAskResp changeWatchAskResp = 12;</code>
     */
    public pb4server.ChangeWatchAskResp getChangeWatchAskResp() {
      if (changeWatchAskRespBuilder_ == null) {
        if (msgCase_ == 12) {
          return (pb4server.ChangeWatchAskResp) msg_;
        }
        return pb4server.ChangeWatchAskResp.getDefaultInstance();
      } else {
        if (msgCase_ == 12) {
          return changeWatchAskRespBuilder_.getMessage();
        }
        return pb4server.ChangeWatchAskResp.getDefaultInstance();
      }
    }
    /**
     * <code>.pb4server.ChangeWatchAskResp changeWatchAskResp = 12;</code>
     */
    public Builder setChangeWatchAskResp(pb4server.ChangeWatchAskResp value) {
      if (changeWatchAskRespBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        msg_ = value;
        onChanged();
      } else {
        changeWatchAskRespBuilder_.setMessage(value);
      }
      msgCase_ = 12;
      return this;
    }
    /**
     * <code>.pb4server.ChangeWatchAskResp changeWatchAskResp = 12;</code>
     */
    public Builder setChangeWatchAskResp(
        pb4server.ChangeWatchAskResp.Builder builderForValue) {
      if (changeWatchAskRespBuilder_ == null) {
        msg_ = builderForValue.build();
        onChanged();
      } else {
        changeWatchAskRespBuilder_.setMessage(builderForValue.build());
      }
      msgCase_ = 12;
      return this;
    }
    /**
     * <code>.pb4server.ChangeWatchAskResp changeWatchAskResp = 12;</code>
     */
    public Builder mergeChangeWatchAskResp(pb4server.ChangeWatchAskResp value) {
      if (changeWatchAskRespBuilder_ == null) {
        if (msgCase_ == 12 &&
            msg_ != pb4server.ChangeWatchAskResp.getDefaultInstance()) {
          msg_ = pb4server.ChangeWatchAskResp.newBuilder((pb4server.ChangeWatchAskResp) msg_)
              .mergeFrom(value).buildPartial();
        } else {
          msg_ = value;
        }
        onChanged();
      } else {
        if (msgCase_ == 12) {
          changeWatchAskRespBuilder_.mergeFrom(value);
        }
        changeWatchAskRespBuilder_.setMessage(value);
      }
      msgCase_ = 12;
      return this;
    }
    /**
     * <code>.pb4server.ChangeWatchAskResp changeWatchAskResp = 12;</code>
     */
    public Builder clearChangeWatchAskResp() {
      if (changeWatchAskRespBuilder_ == null) {
        if (msgCase_ == 12) {
          msgCase_ = 0;
          msg_ = null;
          onChanged();
        }
      } else {
        if (msgCase_ == 12) {
          msgCase_ = 0;
          msg_ = null;
        }
        changeWatchAskRespBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.pb4server.ChangeWatchAskResp changeWatchAskResp = 12;</code>
     */
    public pb4server.ChangeWatchAskResp.Builder getChangeWatchAskRespBuilder() {
      return getChangeWatchAskRespFieldBuilder().getBuilder();
    }
    /**
     * <code>.pb4server.ChangeWatchAskResp changeWatchAskResp = 12;</code>
     */
    public pb4server.ChangeWatchAskRespOrBuilder getChangeWatchAskRespOrBuilder() {
      if ((msgCase_ == 12) && (changeWatchAskRespBuilder_ != null)) {
        return changeWatchAskRespBuilder_.getMessageOrBuilder();
      } else {
        if (msgCase_ == 12) {
          return (pb4server.ChangeWatchAskResp) msg_;
        }
        return pb4server.ChangeWatchAskResp.getDefaultInstance();
      }
    }
    /**
     * <code>.pb4server.ChangeWatchAskResp changeWatchAskResp = 12;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.ChangeWatchAskResp, pb4server.ChangeWatchAskResp.Builder, pb4server.ChangeWatchAskRespOrBuilder> 
        getChangeWatchAskRespFieldBuilder() {
      if (changeWatchAskRespBuilder_ == null) {
        if (!(msgCase_ == 12)) {
          msg_ = pb4server.ChangeWatchAskResp.getDefaultInstance();
        }
        changeWatchAskRespBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4server.ChangeWatchAskResp, pb4server.ChangeWatchAskResp.Builder, pb4server.ChangeWatchAskRespOrBuilder>(
                (pb4server.ChangeWatchAskResp) msg_,
                getParentForChildren(),
                isClean());
        msg_ = null;
      }
      msgCase_ = 12;
      onChanged();;
      return changeWatchAskRespBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.World2WorldAskResp)
  }

  // @@protoc_insertion_point(class_scope:pb4server.World2WorldAskResp)
  private static final pb4server.World2WorldAskResp DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.World2WorldAskResp();
  }

  public static pb4server.World2WorldAskResp getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<World2WorldAskResp>
      PARSER = new com.google.protobuf.AbstractParser<World2WorldAskResp>() {
    public World2WorldAskResp parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new World2WorldAskResp(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<World2WorldAskResp> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<World2WorldAskResp> getParserForType() {
    return PARSER;
  }

  public pb4server.World2WorldAskResp getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

