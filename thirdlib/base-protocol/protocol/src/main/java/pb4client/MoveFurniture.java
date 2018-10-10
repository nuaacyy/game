// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 1524
 * 客户端 -&gt; 服务器
 * 移动家具
 * </pre>
 *
 * Protobuf type {@code client2server.MoveFurniture}
 */
public  final class MoveFurniture extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.MoveFurniture)
    MoveFurnitureOrBuilder {
  // Use MoveFurniture.newBuilder() to construct.
  private MoveFurniture(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MoveFurniture() {
    furnitureId_ = 0L;
    x_ = 0;
    y_ = 0;
    direction_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MoveFurniture(
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
            furnitureId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            x_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            y_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            direction_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_MoveFurniture_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_MoveFurniture_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.MoveFurniture.class, pb4client.MoveFurniture.Builder.class);
  }

  private int bitField0_;
  public static final int FURNITUREID_FIELD_NUMBER = 1;
  private long furnitureId_;
  /**
   * <pre>
   *家具唯一Id
   * </pre>
   *
   * <code>required int64 furnitureId = 1;</code>
   */
  public boolean hasFurnitureId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *家具唯一Id
   * </pre>
   *
   * <code>required int64 furnitureId = 1;</code>
   */
  public long getFurnitureId() {
    return furnitureId_;
  }

  public static final int X_FIELD_NUMBER = 2;
  private int x_;
  /**
   * <pre>
   * 家具x坐标
   * </pre>
   *
   * <code>required int32 x = 2;</code>
   */
  public boolean hasX() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 家具x坐标
   * </pre>
   *
   * <code>required int32 x = 2;</code>
   */
  public int getX() {
    return x_;
  }

  public static final int Y_FIELD_NUMBER = 3;
  private int y_;
  /**
   * <pre>
   * 家具y坐标
   * </pre>
   *
   * <code>required int32 y = 3;</code>
   */
  public boolean hasY() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 家具y坐标
   * </pre>
   *
   * <code>required int32 y = 3;</code>
   */
  public int getY() {
    return y_;
  }

  public static final int DIRECTION_FIELD_NUMBER = 4;
  private int direction_;
  /**
   * <pre>
   * 家具朝向
   * </pre>
   *
   * <code>required int32 direction = 4;</code>
   */
  public boolean hasDirection() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 家具朝向
   * </pre>
   *
   * <code>required int32 direction = 4;</code>
   */
  public int getDirection() {
    return direction_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasFurnitureId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasX()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasY()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasDirection()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, furnitureId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, x_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, y_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, direction_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, furnitureId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, x_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, y_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, direction_);
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
    if (!(obj instanceof pb4client.MoveFurniture)) {
      return super.equals(obj);
    }
    pb4client.MoveFurniture other = (pb4client.MoveFurniture) obj;

    boolean result = true;
    result = result && (hasFurnitureId() == other.hasFurnitureId());
    if (hasFurnitureId()) {
      result = result && (getFurnitureId()
          == other.getFurnitureId());
    }
    result = result && (hasX() == other.hasX());
    if (hasX()) {
      result = result && (getX()
          == other.getX());
    }
    result = result && (hasY() == other.hasY());
    if (hasY()) {
      result = result && (getY()
          == other.getY());
    }
    result = result && (hasDirection() == other.hasDirection());
    if (hasDirection()) {
      result = result && (getDirection()
          == other.getDirection());
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
    if (hasFurnitureId()) {
      hash = (37 * hash) + FURNITUREID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getFurnitureId());
    }
    if (hasX()) {
      hash = (37 * hash) + X_FIELD_NUMBER;
      hash = (53 * hash) + getX();
    }
    if (hasY()) {
      hash = (37 * hash) + Y_FIELD_NUMBER;
      hash = (53 * hash) + getY();
    }
    if (hasDirection()) {
      hash = (37 * hash) + DIRECTION_FIELD_NUMBER;
      hash = (53 * hash) + getDirection();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.MoveFurniture parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MoveFurniture parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MoveFurniture parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MoveFurniture parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MoveFurniture parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MoveFurniture parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MoveFurniture parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MoveFurniture parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MoveFurniture parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.MoveFurniture parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MoveFurniture parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MoveFurniture parseFrom(
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
  public static Builder newBuilder(pb4client.MoveFurniture prototype) {
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
   * msgType = 1524
   * 客户端 -&gt; 服务器
   * 移动家具
   * </pre>
   *
   * Protobuf type {@code client2server.MoveFurniture}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.MoveFurniture)
      pb4client.MoveFurnitureOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_MoveFurniture_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_MoveFurniture_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.MoveFurniture.class, pb4client.MoveFurniture.Builder.class);
    }

    // Construct using pb4client.MoveFurniture.newBuilder()
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
      furnitureId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      x_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      y_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      direction_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_MoveFurniture_descriptor;
    }

    public pb4client.MoveFurniture getDefaultInstanceForType() {
      return pb4client.MoveFurniture.getDefaultInstance();
    }

    public pb4client.MoveFurniture build() {
      pb4client.MoveFurniture result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.MoveFurniture buildPartial() {
      pb4client.MoveFurniture result = new pb4client.MoveFurniture(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.furnitureId_ = furnitureId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.x_ = x_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.y_ = y_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.direction_ = direction_;
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
      if (other instanceof pb4client.MoveFurniture) {
        return mergeFrom((pb4client.MoveFurniture)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.MoveFurniture other) {
      if (other == pb4client.MoveFurniture.getDefaultInstance()) return this;
      if (other.hasFurnitureId()) {
        setFurnitureId(other.getFurnitureId());
      }
      if (other.hasX()) {
        setX(other.getX());
      }
      if (other.hasY()) {
        setY(other.getY());
      }
      if (other.hasDirection()) {
        setDirection(other.getDirection());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasFurnitureId()) {
        return false;
      }
      if (!hasX()) {
        return false;
      }
      if (!hasY()) {
        return false;
      }
      if (!hasDirection()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.MoveFurniture parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.MoveFurniture) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long furnitureId_ ;
    /**
     * <pre>
     *家具唯一Id
     * </pre>
     *
     * <code>required int64 furnitureId = 1;</code>
     */
    public boolean hasFurnitureId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *家具唯一Id
     * </pre>
     *
     * <code>required int64 furnitureId = 1;</code>
     */
    public long getFurnitureId() {
      return furnitureId_;
    }
    /**
     * <pre>
     *家具唯一Id
     * </pre>
     *
     * <code>required int64 furnitureId = 1;</code>
     */
    public Builder setFurnitureId(long value) {
      bitField0_ |= 0x00000001;
      furnitureId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *家具唯一Id
     * </pre>
     *
     * <code>required int64 furnitureId = 1;</code>
     */
    public Builder clearFurnitureId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      furnitureId_ = 0L;
      onChanged();
      return this;
    }

    private int x_ ;
    /**
     * <pre>
     * 家具x坐标
     * </pre>
     *
     * <code>required int32 x = 2;</code>
     */
    public boolean hasX() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 家具x坐标
     * </pre>
     *
     * <code>required int32 x = 2;</code>
     */
    public int getX() {
      return x_;
    }
    /**
     * <pre>
     * 家具x坐标
     * </pre>
     *
     * <code>required int32 x = 2;</code>
     */
    public Builder setX(int value) {
      bitField0_ |= 0x00000002;
      x_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 家具x坐标
     * </pre>
     *
     * <code>required int32 x = 2;</code>
     */
    public Builder clearX() {
      bitField0_ = (bitField0_ & ~0x00000002);
      x_ = 0;
      onChanged();
      return this;
    }

    private int y_ ;
    /**
     * <pre>
     * 家具y坐标
     * </pre>
     *
     * <code>required int32 y = 3;</code>
     */
    public boolean hasY() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 家具y坐标
     * </pre>
     *
     * <code>required int32 y = 3;</code>
     */
    public int getY() {
      return y_;
    }
    /**
     * <pre>
     * 家具y坐标
     * </pre>
     *
     * <code>required int32 y = 3;</code>
     */
    public Builder setY(int value) {
      bitField0_ |= 0x00000004;
      y_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 家具y坐标
     * </pre>
     *
     * <code>required int32 y = 3;</code>
     */
    public Builder clearY() {
      bitField0_ = (bitField0_ & ~0x00000004);
      y_ = 0;
      onChanged();
      return this;
    }

    private int direction_ ;
    /**
     * <pre>
     * 家具朝向
     * </pre>
     *
     * <code>required int32 direction = 4;</code>
     */
    public boolean hasDirection() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 家具朝向
     * </pre>
     *
     * <code>required int32 direction = 4;</code>
     */
    public int getDirection() {
      return direction_;
    }
    /**
     * <pre>
     * 家具朝向
     * </pre>
     *
     * <code>required int32 direction = 4;</code>
     */
    public Builder setDirection(int value) {
      bitField0_ |= 0x00000008;
      direction_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 家具朝向
     * </pre>
     *
     * <code>required int32 direction = 4;</code>
     */
    public Builder clearDirection() {
      bitField0_ = (bitField0_ & ~0x00000008);
      direction_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.MoveFurniture)
  }

  // @@protoc_insertion_point(class_scope:client2server.MoveFurniture)
  private static final pb4client.MoveFurniture DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.MoveFurniture();
  }

  public static pb4client.MoveFurniture getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<MoveFurniture>
      PARSER = new com.google.protobuf.AbstractParser<MoveFurniture>() {
    public MoveFurniture parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new MoveFurniture(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MoveFurniture> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MoveFurniture> getParserForType() {
    return PARSER;
  }

  public pb4client.MoveFurniture getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
