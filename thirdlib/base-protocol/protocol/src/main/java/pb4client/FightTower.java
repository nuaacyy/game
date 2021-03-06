// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 953
 * 客户端 -&gt; 服务器
 * 爬塔战斗
 * </pre>
 *
 * Protobuf type {@code client2server.FightTower}
 */
public  final class FightTower extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.FightTower)
    FightTowerOrBuilder {
  // Use FightTower.newBuilder() to construct.
  private FightTower(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FightTower() {
    qianfeng_ = 0L;
    zhongjun_ = 0L;
    daying_ = 0L;
    difficulty_ = 0;
    floor_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FightTower(
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
            qianfeng_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            zhongjun_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            daying_ = input.readInt64();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            difficulty_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            floor_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_FightTower_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_FightTower_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.FightTower.class, pb4client.FightTower.Builder.class);
  }

  private int bitField0_;
  public static final int QIANFENG_FIELD_NUMBER = 1;
  private long qianfeng_;
  /**
   * <pre>
   * 配置的前锋ID
   * </pre>
   *
   * <code>required int64 qianfeng = 1;</code>
   */
  public boolean hasQianfeng() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 配置的前锋ID
   * </pre>
   *
   * <code>required int64 qianfeng = 1;</code>
   */
  public long getQianfeng() {
    return qianfeng_;
  }

  public static final int ZHONGJUN_FIELD_NUMBER = 2;
  private long zhongjun_;
  /**
   * <pre>
   * 配置的中军ID
   * </pre>
   *
   * <code>required int64 zhongjun = 2;</code>
   */
  public boolean hasZhongjun() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 配置的中军ID
   * </pre>
   *
   * <code>required int64 zhongjun = 2;</code>
   */
  public long getZhongjun() {
    return zhongjun_;
  }

  public static final int DAYING_FIELD_NUMBER = 3;
  private long daying_;
  /**
   * <pre>
   * 配置的大营ID
   * </pre>
   *
   * <code>required int64 daying = 3;</code>
   */
  public boolean hasDaying() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 配置的大营ID
   * </pre>
   *
   * <code>required int64 daying = 3;</code>
   */
  public long getDaying() {
    return daying_;
  }

  public static final int DIFFICULTY_FIELD_NUMBER = 4;
  private int difficulty_;
  /**
   * <pre>
   * 要打的难度
   * </pre>
   *
   * <code>required int32 difficulty = 4;</code>
   */
  public boolean hasDifficulty() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 要打的难度
   * </pre>
   *
   * <code>required int32 difficulty = 4;</code>
   */
  public int getDifficulty() {
    return difficulty_;
  }

  public static final int FLOOR_FIELD_NUMBER = 5;
  private int floor_;
  /**
   * <pre>
   * 要打的层数
   * </pre>
   *
   * <code>required int32 floor = 5;</code>
   */
  public boolean hasFloor() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   * 要打的层数
   * </pre>
   *
   * <code>required int32 floor = 5;</code>
   */
  public int getFloor() {
    return floor_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasQianfeng()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasZhongjun()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasDaying()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasDifficulty()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasFloor()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, qianfeng_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, zhongjun_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt64(3, daying_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, difficulty_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, floor_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, qianfeng_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, zhongjun_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, daying_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, difficulty_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, floor_);
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
    if (!(obj instanceof pb4client.FightTower)) {
      return super.equals(obj);
    }
    pb4client.FightTower other = (pb4client.FightTower) obj;

    boolean result = true;
    result = result && (hasQianfeng() == other.hasQianfeng());
    if (hasQianfeng()) {
      result = result && (getQianfeng()
          == other.getQianfeng());
    }
    result = result && (hasZhongjun() == other.hasZhongjun());
    if (hasZhongjun()) {
      result = result && (getZhongjun()
          == other.getZhongjun());
    }
    result = result && (hasDaying() == other.hasDaying());
    if (hasDaying()) {
      result = result && (getDaying()
          == other.getDaying());
    }
    result = result && (hasDifficulty() == other.hasDifficulty());
    if (hasDifficulty()) {
      result = result && (getDifficulty()
          == other.getDifficulty());
    }
    result = result && (hasFloor() == other.hasFloor());
    if (hasFloor()) {
      result = result && (getFloor()
          == other.getFloor());
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
    if (hasQianfeng()) {
      hash = (37 * hash) + QIANFENG_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getQianfeng());
    }
    if (hasZhongjun()) {
      hash = (37 * hash) + ZHONGJUN_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getZhongjun());
    }
    if (hasDaying()) {
      hash = (37 * hash) + DAYING_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getDaying());
    }
    if (hasDifficulty()) {
      hash = (37 * hash) + DIFFICULTY_FIELD_NUMBER;
      hash = (53 * hash) + getDifficulty();
    }
    if (hasFloor()) {
      hash = (37 * hash) + FLOOR_FIELD_NUMBER;
      hash = (53 * hash) + getFloor();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.FightTower parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FightTower parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FightTower parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FightTower parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FightTower parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FightTower parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FightTower parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FightTower parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FightTower parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.FightTower parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FightTower parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FightTower parseFrom(
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
  public static Builder newBuilder(pb4client.FightTower prototype) {
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
   * msgType = 953
   * 客户端 -&gt; 服务器
   * 爬塔战斗
   * </pre>
   *
   * Protobuf type {@code client2server.FightTower}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.FightTower)
      pb4client.FightTowerOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_FightTower_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_FightTower_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.FightTower.class, pb4client.FightTower.Builder.class);
    }

    // Construct using pb4client.FightTower.newBuilder()
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
      qianfeng_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      zhongjun_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      daying_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      difficulty_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      floor_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_FightTower_descriptor;
    }

    public pb4client.FightTower getDefaultInstanceForType() {
      return pb4client.FightTower.getDefaultInstance();
    }

    public pb4client.FightTower build() {
      pb4client.FightTower result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.FightTower buildPartial() {
      pb4client.FightTower result = new pb4client.FightTower(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.qianfeng_ = qianfeng_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.zhongjun_ = zhongjun_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.daying_ = daying_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.difficulty_ = difficulty_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.floor_ = floor_;
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
      if (other instanceof pb4client.FightTower) {
        return mergeFrom((pb4client.FightTower)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.FightTower other) {
      if (other == pb4client.FightTower.getDefaultInstance()) return this;
      if (other.hasQianfeng()) {
        setQianfeng(other.getQianfeng());
      }
      if (other.hasZhongjun()) {
        setZhongjun(other.getZhongjun());
      }
      if (other.hasDaying()) {
        setDaying(other.getDaying());
      }
      if (other.hasDifficulty()) {
        setDifficulty(other.getDifficulty());
      }
      if (other.hasFloor()) {
        setFloor(other.getFloor());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasQianfeng()) {
        return false;
      }
      if (!hasZhongjun()) {
        return false;
      }
      if (!hasDaying()) {
        return false;
      }
      if (!hasDifficulty()) {
        return false;
      }
      if (!hasFloor()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.FightTower parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.FightTower) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long qianfeng_ ;
    /**
     * <pre>
     * 配置的前锋ID
     * </pre>
     *
     * <code>required int64 qianfeng = 1;</code>
     */
    public boolean hasQianfeng() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 配置的前锋ID
     * </pre>
     *
     * <code>required int64 qianfeng = 1;</code>
     */
    public long getQianfeng() {
      return qianfeng_;
    }
    /**
     * <pre>
     * 配置的前锋ID
     * </pre>
     *
     * <code>required int64 qianfeng = 1;</code>
     */
    public Builder setQianfeng(long value) {
      bitField0_ |= 0x00000001;
      qianfeng_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 配置的前锋ID
     * </pre>
     *
     * <code>required int64 qianfeng = 1;</code>
     */
    public Builder clearQianfeng() {
      bitField0_ = (bitField0_ & ~0x00000001);
      qianfeng_ = 0L;
      onChanged();
      return this;
    }

    private long zhongjun_ ;
    /**
     * <pre>
     * 配置的中军ID
     * </pre>
     *
     * <code>required int64 zhongjun = 2;</code>
     */
    public boolean hasZhongjun() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 配置的中军ID
     * </pre>
     *
     * <code>required int64 zhongjun = 2;</code>
     */
    public long getZhongjun() {
      return zhongjun_;
    }
    /**
     * <pre>
     * 配置的中军ID
     * </pre>
     *
     * <code>required int64 zhongjun = 2;</code>
     */
    public Builder setZhongjun(long value) {
      bitField0_ |= 0x00000002;
      zhongjun_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 配置的中军ID
     * </pre>
     *
     * <code>required int64 zhongjun = 2;</code>
     */
    public Builder clearZhongjun() {
      bitField0_ = (bitField0_ & ~0x00000002);
      zhongjun_ = 0L;
      onChanged();
      return this;
    }

    private long daying_ ;
    /**
     * <pre>
     * 配置的大营ID
     * </pre>
     *
     * <code>required int64 daying = 3;</code>
     */
    public boolean hasDaying() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 配置的大营ID
     * </pre>
     *
     * <code>required int64 daying = 3;</code>
     */
    public long getDaying() {
      return daying_;
    }
    /**
     * <pre>
     * 配置的大营ID
     * </pre>
     *
     * <code>required int64 daying = 3;</code>
     */
    public Builder setDaying(long value) {
      bitField0_ |= 0x00000004;
      daying_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 配置的大营ID
     * </pre>
     *
     * <code>required int64 daying = 3;</code>
     */
    public Builder clearDaying() {
      bitField0_ = (bitField0_ & ~0x00000004);
      daying_ = 0L;
      onChanged();
      return this;
    }

    private int difficulty_ ;
    /**
     * <pre>
     * 要打的难度
     * </pre>
     *
     * <code>required int32 difficulty = 4;</code>
     */
    public boolean hasDifficulty() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 要打的难度
     * </pre>
     *
     * <code>required int32 difficulty = 4;</code>
     */
    public int getDifficulty() {
      return difficulty_;
    }
    /**
     * <pre>
     * 要打的难度
     * </pre>
     *
     * <code>required int32 difficulty = 4;</code>
     */
    public Builder setDifficulty(int value) {
      bitField0_ |= 0x00000008;
      difficulty_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 要打的难度
     * </pre>
     *
     * <code>required int32 difficulty = 4;</code>
     */
    public Builder clearDifficulty() {
      bitField0_ = (bitField0_ & ~0x00000008);
      difficulty_ = 0;
      onChanged();
      return this;
    }

    private int floor_ ;
    /**
     * <pre>
     * 要打的层数
     * </pre>
     *
     * <code>required int32 floor = 5;</code>
     */
    public boolean hasFloor() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     * 要打的层数
     * </pre>
     *
     * <code>required int32 floor = 5;</code>
     */
    public int getFloor() {
      return floor_;
    }
    /**
     * <pre>
     * 要打的层数
     * </pre>
     *
     * <code>required int32 floor = 5;</code>
     */
    public Builder setFloor(int value) {
      bitField0_ |= 0x00000010;
      floor_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 要打的层数
     * </pre>
     *
     * <code>required int32 floor = 5;</code>
     */
    public Builder clearFloor() {
      bitField0_ = (bitField0_ & ~0x00000010);
      floor_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.FightTower)
  }

  // @@protoc_insertion_point(class_scope:client2server.FightTower)
  private static final pb4client.FightTower DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.FightTower();
  }

  public static pb4client.FightTower getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<FightTower>
      PARSER = new com.google.protobuf.AbstractParser<FightTower>() {
    public FightTower parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new FightTower(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FightTower> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FightTower> getParserForType() {
    return PARSER;
  }

  public pb4client.FightTower getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

