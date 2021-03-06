// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 1251
 * 客户端 -&gt; 服务器
 * 查询魔物信息
 * </pre>
 *
 * Protobuf type {@code client2server.QueryMonsterInfo}
 */
public  final class QueryMonsterInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.QueryMonsterInfo)
    QueryMonsterInfoOrBuilder {
  // Use QueryMonsterInfo.newBuilder() to construct.
  private QueryMonsterInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryMonsterInfo() {
    posX_ = 0;
    posY_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QueryMonsterInfo(
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
            posX_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            posY_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_QueryMonsterInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_QueryMonsterInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.QueryMonsterInfo.class, pb4client.QueryMonsterInfo.Builder.class);
  }

  private int bitField0_;
  public static final int POSX_FIELD_NUMBER = 1;
  private int posX_;
  /**
   * <pre>
   *坐标x
   * </pre>
   *
   * <code>required int32 posX = 1;</code>
   */
  public boolean hasPosX() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *坐标x
   * </pre>
   *
   * <code>required int32 posX = 1;</code>
   */
  public int getPosX() {
    return posX_;
  }

  public static final int POSY_FIELD_NUMBER = 2;
  private int posY_;
  /**
   * <pre>
   *坐标y
   * </pre>
   *
   * <code>required int32 posY = 2;</code>
   */
  public boolean hasPosY() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *坐标y
   * </pre>
   *
   * <code>required int32 posY = 2;</code>
   */
  public int getPosY() {
    return posY_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasPosX()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPosY()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, posX_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, posY_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, posX_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, posY_);
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
    if (!(obj instanceof pb4client.QueryMonsterInfo)) {
      return super.equals(obj);
    }
    pb4client.QueryMonsterInfo other = (pb4client.QueryMonsterInfo) obj;

    boolean result = true;
    result = result && (hasPosX() == other.hasPosX());
    if (hasPosX()) {
      result = result && (getPosX()
          == other.getPosX());
    }
    result = result && (hasPosY() == other.hasPosY());
    if (hasPosY()) {
      result = result && (getPosY()
          == other.getPosY());
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
    if (hasPosX()) {
      hash = (37 * hash) + POSX_FIELD_NUMBER;
      hash = (53 * hash) + getPosX();
    }
    if (hasPosY()) {
      hash = (37 * hash) + POSY_FIELD_NUMBER;
      hash = (53 * hash) + getPosY();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.QueryMonsterInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryMonsterInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryMonsterInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryMonsterInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryMonsterInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryMonsterInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryMonsterInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryMonsterInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryMonsterInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.QueryMonsterInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryMonsterInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryMonsterInfo parseFrom(
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
  public static Builder newBuilder(pb4client.QueryMonsterInfo prototype) {
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
   * msgType = 1251
   * 客户端 -&gt; 服务器
   * 查询魔物信息
   * </pre>
   *
   * Protobuf type {@code client2server.QueryMonsterInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.QueryMonsterInfo)
      pb4client.QueryMonsterInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryMonsterInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryMonsterInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.QueryMonsterInfo.class, pb4client.QueryMonsterInfo.Builder.class);
    }

    // Construct using pb4client.QueryMonsterInfo.newBuilder()
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
      posX_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      posY_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryMonsterInfo_descriptor;
    }

    public pb4client.QueryMonsterInfo getDefaultInstanceForType() {
      return pb4client.QueryMonsterInfo.getDefaultInstance();
    }

    public pb4client.QueryMonsterInfo build() {
      pb4client.QueryMonsterInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.QueryMonsterInfo buildPartial() {
      pb4client.QueryMonsterInfo result = new pb4client.QueryMonsterInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.posX_ = posX_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.posY_ = posY_;
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
      if (other instanceof pb4client.QueryMonsterInfo) {
        return mergeFrom((pb4client.QueryMonsterInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.QueryMonsterInfo other) {
      if (other == pb4client.QueryMonsterInfo.getDefaultInstance()) return this;
      if (other.hasPosX()) {
        setPosX(other.getPosX());
      }
      if (other.hasPosY()) {
        setPosY(other.getPosY());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasPosX()) {
        return false;
      }
      if (!hasPosY()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.QueryMonsterInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.QueryMonsterInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int posX_ ;
    /**
     * <pre>
     *坐标x
     * </pre>
     *
     * <code>required int32 posX = 1;</code>
     */
    public boolean hasPosX() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *坐标x
     * </pre>
     *
     * <code>required int32 posX = 1;</code>
     */
    public int getPosX() {
      return posX_;
    }
    /**
     * <pre>
     *坐标x
     * </pre>
     *
     * <code>required int32 posX = 1;</code>
     */
    public Builder setPosX(int value) {
      bitField0_ |= 0x00000001;
      posX_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *坐标x
     * </pre>
     *
     * <code>required int32 posX = 1;</code>
     */
    public Builder clearPosX() {
      bitField0_ = (bitField0_ & ~0x00000001);
      posX_ = 0;
      onChanged();
      return this;
    }

    private int posY_ ;
    /**
     * <pre>
     *坐标y
     * </pre>
     *
     * <code>required int32 posY = 2;</code>
     */
    public boolean hasPosY() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *坐标y
     * </pre>
     *
     * <code>required int32 posY = 2;</code>
     */
    public int getPosY() {
      return posY_;
    }
    /**
     * <pre>
     *坐标y
     * </pre>
     *
     * <code>required int32 posY = 2;</code>
     */
    public Builder setPosY(int value) {
      bitField0_ |= 0x00000002;
      posY_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *坐标y
     * </pre>
     *
     * <code>required int32 posY = 2;</code>
     */
    public Builder clearPosY() {
      bitField0_ = (bitField0_ & ~0x00000002);
      posY_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.QueryMonsterInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.QueryMonsterInfo)
  private static final pb4client.QueryMonsterInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.QueryMonsterInfo();
  }

  public static pb4client.QueryMonsterInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<QueryMonsterInfo>
      PARSER = new com.google.protobuf.AbstractParser<QueryMonsterInfo>() {
    public QueryMonsterInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryMonsterInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryMonsterInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryMonsterInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.QueryMonsterInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

