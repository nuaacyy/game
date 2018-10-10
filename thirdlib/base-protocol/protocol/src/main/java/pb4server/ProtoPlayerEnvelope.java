// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * <pre>
 * gate -&gt; home
 * 信封消息
 * </pre>
 *
 * Protobuf type {@code pb4server.ProtoPlayerEnvelope}
 */
public  final class ProtoPlayerEnvelope extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.ProtoPlayerEnvelope)
    ProtoPlayerEnvelopeOrBuilder {
  // Use ProtoPlayerEnvelope.newBuilder() to construct.
  private ProtoPlayerEnvelope(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ProtoPlayerEnvelope() {
    msgType_ = 0;
    clientMsgNo_ = 0;
    playerId_ = 0L;
    msgBin_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ProtoPlayerEnvelope(
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

            msgType_ = input.readInt32();
            break;
          }
          case 16: {

            clientMsgNo_ = input.readInt32();
            break;
          }
          case 24: {

            playerId_ = input.readInt64();
            break;
          }
          case 34: {

            msgBin_ = input.readBytes();
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
    return pb4server.InternalPkt.internal_static_pb4server_ProtoPlayerEnvelope_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_ProtoPlayerEnvelope_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.ProtoPlayerEnvelope.class, pb4server.ProtoPlayerEnvelope.Builder.class);
  }

  public static final int MSGTYPE_FIELD_NUMBER = 1;
  private int msgType_;
  /**
   * <code>int32 msgType = 1;</code>
   */
  public int getMsgType() {
    return msgType_;
  }

  public static final int CLIENTMSGNO_FIELD_NUMBER = 2;
  private int clientMsgNo_;
  /**
   * <code>int32 clientMsgNo = 2;</code>
   */
  public int getClientMsgNo() {
    return clientMsgNo_;
  }

  public static final int PLAYERID_FIELD_NUMBER = 3;
  private long playerId_;
  /**
   * <code>int64 playerId = 3;</code>
   */
  public long getPlayerId() {
    return playerId_;
  }

  public static final int MSGBIN_FIELD_NUMBER = 4;
  private com.google.protobuf.ByteString msgBin_;
  /**
   * <code>bytes msgBin = 4;</code>
   */
  public com.google.protobuf.ByteString getMsgBin() {
    return msgBin_;
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
    if (msgType_ != 0) {
      output.writeInt32(1, msgType_);
    }
    if (clientMsgNo_ != 0) {
      output.writeInt32(2, clientMsgNo_);
    }
    if (playerId_ != 0L) {
      output.writeInt64(3, playerId_);
    }
    if (!msgBin_.isEmpty()) {
      output.writeBytes(4, msgBin_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (msgType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, msgType_);
    }
    if (clientMsgNo_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, clientMsgNo_);
    }
    if (playerId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, playerId_);
    }
    if (!msgBin_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(4, msgBin_);
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
    if (!(obj instanceof pb4server.ProtoPlayerEnvelope)) {
      return super.equals(obj);
    }
    pb4server.ProtoPlayerEnvelope other = (pb4server.ProtoPlayerEnvelope) obj;

    boolean result = true;
    result = result && (getMsgType()
        == other.getMsgType());
    result = result && (getClientMsgNo()
        == other.getClientMsgNo());
    result = result && (getPlayerId()
        == other.getPlayerId());
    result = result && getMsgBin()
        .equals(other.getMsgBin());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + MSGTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getMsgType();
    hash = (37 * hash) + CLIENTMSGNO_FIELD_NUMBER;
    hash = (53 * hash) + getClientMsgNo();
    hash = (37 * hash) + PLAYERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPlayerId());
    hash = (37 * hash) + MSGBIN_FIELD_NUMBER;
    hash = (53 * hash) + getMsgBin().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.ProtoPlayerEnvelope parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ProtoPlayerEnvelope parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ProtoPlayerEnvelope parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ProtoPlayerEnvelope parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ProtoPlayerEnvelope parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ProtoPlayerEnvelope parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ProtoPlayerEnvelope parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.ProtoPlayerEnvelope parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.ProtoPlayerEnvelope parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.ProtoPlayerEnvelope parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.ProtoPlayerEnvelope parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.ProtoPlayerEnvelope parseFrom(
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
  public static Builder newBuilder(pb4server.ProtoPlayerEnvelope prototype) {
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
   * gate -&gt; home
   * 信封消息
   * </pre>
   *
   * Protobuf type {@code pb4server.ProtoPlayerEnvelope}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.ProtoPlayerEnvelope)
      pb4server.ProtoPlayerEnvelopeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_ProtoPlayerEnvelope_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_ProtoPlayerEnvelope_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.ProtoPlayerEnvelope.class, pb4server.ProtoPlayerEnvelope.Builder.class);
    }

    // Construct using pb4server.ProtoPlayerEnvelope.newBuilder()
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
      msgType_ = 0;

      clientMsgNo_ = 0;

      playerId_ = 0L;

      msgBin_ = com.google.protobuf.ByteString.EMPTY;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_ProtoPlayerEnvelope_descriptor;
    }

    public pb4server.ProtoPlayerEnvelope getDefaultInstanceForType() {
      return pb4server.ProtoPlayerEnvelope.getDefaultInstance();
    }

    public pb4server.ProtoPlayerEnvelope build() {
      pb4server.ProtoPlayerEnvelope result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.ProtoPlayerEnvelope buildPartial() {
      pb4server.ProtoPlayerEnvelope result = new pb4server.ProtoPlayerEnvelope(this);
      result.msgType_ = msgType_;
      result.clientMsgNo_ = clientMsgNo_;
      result.playerId_ = playerId_;
      result.msgBin_ = msgBin_;
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
      if (other instanceof pb4server.ProtoPlayerEnvelope) {
        return mergeFrom((pb4server.ProtoPlayerEnvelope)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.ProtoPlayerEnvelope other) {
      if (other == pb4server.ProtoPlayerEnvelope.getDefaultInstance()) return this;
      if (other.getMsgType() != 0) {
        setMsgType(other.getMsgType());
      }
      if (other.getClientMsgNo() != 0) {
        setClientMsgNo(other.getClientMsgNo());
      }
      if (other.getPlayerId() != 0L) {
        setPlayerId(other.getPlayerId());
      }
      if (other.getMsgBin() != com.google.protobuf.ByteString.EMPTY) {
        setMsgBin(other.getMsgBin());
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
      pb4server.ProtoPlayerEnvelope parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.ProtoPlayerEnvelope) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int msgType_ ;
    /**
     * <code>int32 msgType = 1;</code>
     */
    public int getMsgType() {
      return msgType_;
    }
    /**
     * <code>int32 msgType = 1;</code>
     */
    public Builder setMsgType(int value) {
      
      msgType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 msgType = 1;</code>
     */
    public Builder clearMsgType() {
      
      msgType_ = 0;
      onChanged();
      return this;
    }

    private int clientMsgNo_ ;
    /**
     * <code>int32 clientMsgNo = 2;</code>
     */
    public int getClientMsgNo() {
      return clientMsgNo_;
    }
    /**
     * <code>int32 clientMsgNo = 2;</code>
     */
    public Builder setClientMsgNo(int value) {
      
      clientMsgNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 clientMsgNo = 2;</code>
     */
    public Builder clearClientMsgNo() {
      
      clientMsgNo_ = 0;
      onChanged();
      return this;
    }

    private long playerId_ ;
    /**
     * <code>int64 playerId = 3;</code>
     */
    public long getPlayerId() {
      return playerId_;
    }
    /**
     * <code>int64 playerId = 3;</code>
     */
    public Builder setPlayerId(long value) {
      
      playerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 playerId = 3;</code>
     */
    public Builder clearPlayerId() {
      
      playerId_ = 0L;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString msgBin_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes msgBin = 4;</code>
     */
    public com.google.protobuf.ByteString getMsgBin() {
      return msgBin_;
    }
    /**
     * <code>bytes msgBin = 4;</code>
     */
    public Builder setMsgBin(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      msgBin_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes msgBin = 4;</code>
     */
    public Builder clearMsgBin() {
      
      msgBin_ = getDefaultInstance().getMsgBin();
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.ProtoPlayerEnvelope)
  }

  // @@protoc_insertion_point(class_scope:pb4server.ProtoPlayerEnvelope)
  private static final pb4server.ProtoPlayerEnvelope DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.ProtoPlayerEnvelope();
  }

  public static pb4server.ProtoPlayerEnvelope getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ProtoPlayerEnvelope>
      PARSER = new com.google.protobuf.AbstractParser<ProtoPlayerEnvelope>() {
    public ProtoPlayerEnvelope parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ProtoPlayerEnvelope(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ProtoPlayerEnvelope> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ProtoPlayerEnvelope> getParserForType() {
    return PARSER;
  }

  public pb4server.ProtoPlayerEnvelope getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
