// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 298
 * 客户端 -&gt; 服务器
 * 切换聊天窗口
 * </pre>
 *
 * Protobuf type {@code client2server.ChangeChatWindow}
 */
public  final class ChangeChatWindow extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.ChangeChatWindow)
    ChangeChatWindowOrBuilder {
  // Use ChangeChatWindow.newBuilder() to construct.
  private ChangeChatWindow(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ChangeChatWindow() {
    playerIdNew_ = 0L;
    roomIdNew_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ChangeChatWindow(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownField(input, unknownFields,
                                   extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {
            bitField0_ |= 0x00000001;
            playerIdNew_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            roomIdNew_ = input.readInt64();
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_ChangeChatWindow_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_ChangeChatWindow_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.ChangeChatWindow.class, pb4client.ChangeChatWindow.Builder.class);
  }

  private int bitField0_;
  public static final int PLAYERIDNEW_FIELD_NUMBER = 1;
  private long playerIdNew_;
  /**
   * <pre>
   * new私聊id
   * </pre>
   *
   * <code>required int64 playerIdNew = 1;</code>
   */
  public boolean hasPlayerIdNew() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * new私聊id
   * </pre>
   *
   * <code>required int64 playerIdNew = 1;</code>
   */
  public long getPlayerIdNew() {
    return playerIdNew_;
  }

  public static final int ROOMIDNEW_FIELD_NUMBER = 2;
  private long roomIdNew_;
  /**
   * <pre>
   * new聊天室id
   * </pre>
   *
   * <code>required int64 roomIdNew = 2;</code>
   */
  public boolean hasRoomIdNew() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * new聊天室id
   * </pre>
   *
   * <code>required int64 roomIdNew = 2;</code>
   */
  public long getRoomIdNew() {
    return roomIdNew_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasPlayerIdNew()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasRoomIdNew()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, playerIdNew_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, roomIdNew_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, playerIdNew_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, roomIdNew_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof pb4client.ChangeChatWindow)) {
      return super.equals(obj);
    }
    pb4client.ChangeChatWindow other = (pb4client.ChangeChatWindow) obj;

    boolean result = true;
    result = result && (hasPlayerIdNew() == other.hasPlayerIdNew());
    if (hasPlayerIdNew()) {
      result = result && (getPlayerIdNew()
          == other.getPlayerIdNew());
    }
    result = result && (hasRoomIdNew() == other.hasRoomIdNew());
    if (hasRoomIdNew()) {
      result = result && (getRoomIdNew()
          == other.getRoomIdNew());
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasPlayerIdNew()) {
      hash = (37 * hash) + PLAYERIDNEW_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getPlayerIdNew());
    }
    if (hasRoomIdNew()) {
      hash = (37 * hash) + ROOMIDNEW_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getRoomIdNew());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.ChangeChatWindow parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ChangeChatWindow parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ChangeChatWindow parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ChangeChatWindow parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ChangeChatWindow parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ChangeChatWindow parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ChangeChatWindow parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ChangeChatWindow parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ChangeChatWindow parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.ChangeChatWindow parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ChangeChatWindow parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ChangeChatWindow parseFrom(
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
  public static Builder newBuilder(pb4client.ChangeChatWindow prototype) {
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
   * msgType = 298
   * 客户端 -&gt; 服务器
   * 切换聊天窗口
   * </pre>
   *
   * Protobuf type {@code client2server.ChangeChatWindow}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.ChangeChatWindow)
      pb4client.ChangeChatWindowOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_ChangeChatWindow_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_ChangeChatWindow_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.ChangeChatWindow.class, pb4client.ChangeChatWindow.Builder.class);
    }

    // Construct using pb4client.ChangeChatWindow.newBuilder()
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
      playerIdNew_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      roomIdNew_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_ChangeChatWindow_descriptor;
    }

    public pb4client.ChangeChatWindow getDefaultInstanceForType() {
      return pb4client.ChangeChatWindow.getDefaultInstance();
    }

    public pb4client.ChangeChatWindow build() {
      pb4client.ChangeChatWindow result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.ChangeChatWindow buildPartial() {
      pb4client.ChangeChatWindow result = new pb4client.ChangeChatWindow(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.playerIdNew_ = playerIdNew_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.roomIdNew_ = roomIdNew_;
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof pb4client.ChangeChatWindow) {
        return mergeFrom((pb4client.ChangeChatWindow)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.ChangeChatWindow other) {
      if (other == pb4client.ChangeChatWindow.getDefaultInstance()) return this;
      if (other.hasPlayerIdNew()) {
        setPlayerIdNew(other.getPlayerIdNew());
      }
      if (other.hasRoomIdNew()) {
        setRoomIdNew(other.getRoomIdNew());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasPlayerIdNew()) {
        return false;
      }
      if (!hasRoomIdNew()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.ChangeChatWindow parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.ChangeChatWindow) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long playerIdNew_ ;
    /**
     * <pre>
     * new私聊id
     * </pre>
     *
     * <code>required int64 playerIdNew = 1;</code>
     */
    public boolean hasPlayerIdNew() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * new私聊id
     * </pre>
     *
     * <code>required int64 playerIdNew = 1;</code>
     */
    public long getPlayerIdNew() {
      return playerIdNew_;
    }
    /**
     * <pre>
     * new私聊id
     * </pre>
     *
     * <code>required int64 playerIdNew = 1;</code>
     */
    public Builder setPlayerIdNew(long value) {
      bitField0_ |= 0x00000001;
      playerIdNew_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * new私聊id
     * </pre>
     *
     * <code>required int64 playerIdNew = 1;</code>
     */
    public Builder clearPlayerIdNew() {
      bitField0_ = (bitField0_ & ~0x00000001);
      playerIdNew_ = 0L;
      onChanged();
      return this;
    }

    private long roomIdNew_ ;
    /**
     * <pre>
     * new聊天室id
     * </pre>
     *
     * <code>required int64 roomIdNew = 2;</code>
     */
    public boolean hasRoomIdNew() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * new聊天室id
     * </pre>
     *
     * <code>required int64 roomIdNew = 2;</code>
     */
    public long getRoomIdNew() {
      return roomIdNew_;
    }
    /**
     * <pre>
     * new聊天室id
     * </pre>
     *
     * <code>required int64 roomIdNew = 2;</code>
     */
    public Builder setRoomIdNew(long value) {
      bitField0_ |= 0x00000002;
      roomIdNew_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * new聊天室id
     * </pre>
     *
     * <code>required int64 roomIdNew = 2;</code>
     */
    public Builder clearRoomIdNew() {
      bitField0_ = (bitField0_ & ~0x00000002);
      roomIdNew_ = 0L;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.ChangeChatWindow)
  }

  // @@protoc_insertion_point(class_scope:client2server.ChangeChatWindow)
  private static final pb4client.ChangeChatWindow DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.ChangeChatWindow();
  }

  public static pb4client.ChangeChatWindow getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ChangeChatWindow>
      PARSER = new com.google.protobuf.AbstractParser<ChangeChatWindow>() {
    public ChangeChatWindow parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ChangeChatWindow(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ChangeChatWindow> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ChangeChatWindow> getParserForType() {
    return PARSER;
  }

  public pb4client.ChangeChatWindow getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

