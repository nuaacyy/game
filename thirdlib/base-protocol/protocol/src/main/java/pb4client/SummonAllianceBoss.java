// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * // msgType = 1316
 * 客户端 -&gt; 服务器
 * 召唤联盟BOSS
 * </pre>
 *
 * Protobuf type {@code client2server.SummonAllianceBoss}
 */
public  final class SummonAllianceBoss extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.SummonAllianceBoss)
    SummonAllianceBossOrBuilder {
  // Use SummonAllianceBoss.newBuilder() to construct.
  private SummonAllianceBoss(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SummonAllianceBoss() {
    bossId_ = 0;
    x_ = 0;
    y_ = 0;
    score_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SummonAllianceBoss(
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
            bossId_ = input.readInt32();
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
            score_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_SummonAllianceBoss_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_SummonAllianceBoss_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.SummonAllianceBoss.class, pb4client.SummonAllianceBoss.Builder.class);
  }

  private int bitField0_;
  public static final int BOSSID_FIELD_NUMBER = 1;
  private int bossId_;
  /**
   * <pre>
   * bossId
   * </pre>
   *
   * <code>required int32 bossId = 1;</code>
   */
  public boolean hasBossId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * bossId
   * </pre>
   *
   * <code>required int32 bossId = 1;</code>
   */
  public int getBossId() {
    return bossId_;
  }

  public static final int X_FIELD_NUMBER = 2;
  private int x_;
  /**
   * <pre>
   * 要召唤的坐标
   * </pre>
   *
   * <code>required int32 x = 2;</code>
   */
  public boolean hasX() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 要召唤的坐标
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
   * 要召唤的坐标
   * </pre>
   *
   * <code>required int32 y = 3;</code>
   */
  public boolean hasY() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 要召唤的坐标
   * </pre>
   *
   * <code>required int32 y = 3;</code>
   */
  public int getY() {
    return y_;
  }

  public static final int SCORE_FIELD_NUMBER = 4;
  private int score_;
  /**
   * <pre>
   * 积分段
   * </pre>
   *
   * <code>required int32 score = 4;</code>
   */
  public boolean hasScore() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 积分段
   * </pre>
   *
   * <code>required int32 score = 4;</code>
   */
  public int getScore() {
    return score_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasBossId()) {
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
    if (!hasScore()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, bossId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, x_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, y_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, score_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, bossId_);
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
        .computeInt32Size(4, score_);
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
    if (!(obj instanceof pb4client.SummonAllianceBoss)) {
      return super.equals(obj);
    }
    pb4client.SummonAllianceBoss other = (pb4client.SummonAllianceBoss) obj;

    boolean result = true;
    result = result && (hasBossId() == other.hasBossId());
    if (hasBossId()) {
      result = result && (getBossId()
          == other.getBossId());
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
    result = result && (hasScore() == other.hasScore());
    if (hasScore()) {
      result = result && (getScore()
          == other.getScore());
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
    if (hasBossId()) {
      hash = (37 * hash) + BOSSID_FIELD_NUMBER;
      hash = (53 * hash) + getBossId();
    }
    if (hasX()) {
      hash = (37 * hash) + X_FIELD_NUMBER;
      hash = (53 * hash) + getX();
    }
    if (hasY()) {
      hash = (37 * hash) + Y_FIELD_NUMBER;
      hash = (53 * hash) + getY();
    }
    if (hasScore()) {
      hash = (37 * hash) + SCORE_FIELD_NUMBER;
      hash = (53 * hash) + getScore();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.SummonAllianceBoss parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SummonAllianceBoss parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SummonAllianceBoss parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SummonAllianceBoss parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SummonAllianceBoss parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SummonAllianceBoss parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SummonAllianceBoss parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SummonAllianceBoss parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SummonAllianceBoss parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.SummonAllianceBoss parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SummonAllianceBoss parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SummonAllianceBoss parseFrom(
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
  public static Builder newBuilder(pb4client.SummonAllianceBoss prototype) {
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
   * // msgType = 1316
   * 客户端 -&gt; 服务器
   * 召唤联盟BOSS
   * </pre>
   *
   * Protobuf type {@code client2server.SummonAllianceBoss}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.SummonAllianceBoss)
      pb4client.SummonAllianceBossOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_SummonAllianceBoss_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_SummonAllianceBoss_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.SummonAllianceBoss.class, pb4client.SummonAllianceBoss.Builder.class);
    }

    // Construct using pb4client.SummonAllianceBoss.newBuilder()
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
      bossId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      x_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      y_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      score_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_SummonAllianceBoss_descriptor;
    }

    public pb4client.SummonAllianceBoss getDefaultInstanceForType() {
      return pb4client.SummonAllianceBoss.getDefaultInstance();
    }

    public pb4client.SummonAllianceBoss build() {
      pb4client.SummonAllianceBoss result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.SummonAllianceBoss buildPartial() {
      pb4client.SummonAllianceBoss result = new pb4client.SummonAllianceBoss(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.bossId_ = bossId_;
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
      result.score_ = score_;
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
      if (other instanceof pb4client.SummonAllianceBoss) {
        return mergeFrom((pb4client.SummonAllianceBoss)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.SummonAllianceBoss other) {
      if (other == pb4client.SummonAllianceBoss.getDefaultInstance()) return this;
      if (other.hasBossId()) {
        setBossId(other.getBossId());
      }
      if (other.hasX()) {
        setX(other.getX());
      }
      if (other.hasY()) {
        setY(other.getY());
      }
      if (other.hasScore()) {
        setScore(other.getScore());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasBossId()) {
        return false;
      }
      if (!hasX()) {
        return false;
      }
      if (!hasY()) {
        return false;
      }
      if (!hasScore()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.SummonAllianceBoss parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.SummonAllianceBoss) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int bossId_ ;
    /**
     * <pre>
     * bossId
     * </pre>
     *
     * <code>required int32 bossId = 1;</code>
     */
    public boolean hasBossId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * bossId
     * </pre>
     *
     * <code>required int32 bossId = 1;</code>
     */
    public int getBossId() {
      return bossId_;
    }
    /**
     * <pre>
     * bossId
     * </pre>
     *
     * <code>required int32 bossId = 1;</code>
     */
    public Builder setBossId(int value) {
      bitField0_ |= 0x00000001;
      bossId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * bossId
     * </pre>
     *
     * <code>required int32 bossId = 1;</code>
     */
    public Builder clearBossId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      bossId_ = 0;
      onChanged();
      return this;
    }

    private int x_ ;
    /**
     * <pre>
     * 要召唤的坐标
     * </pre>
     *
     * <code>required int32 x = 2;</code>
     */
    public boolean hasX() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 要召唤的坐标
     * </pre>
     *
     * <code>required int32 x = 2;</code>
     */
    public int getX() {
      return x_;
    }
    /**
     * <pre>
     * 要召唤的坐标
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
     * 要召唤的坐标
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
     * 要召唤的坐标
     * </pre>
     *
     * <code>required int32 y = 3;</code>
     */
    public boolean hasY() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 要召唤的坐标
     * </pre>
     *
     * <code>required int32 y = 3;</code>
     */
    public int getY() {
      return y_;
    }
    /**
     * <pre>
     * 要召唤的坐标
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
     * 要召唤的坐标
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

    private int score_ ;
    /**
     * <pre>
     * 积分段
     * </pre>
     *
     * <code>required int32 score = 4;</code>
     */
    public boolean hasScore() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 积分段
     * </pre>
     *
     * <code>required int32 score = 4;</code>
     */
    public int getScore() {
      return score_;
    }
    /**
     * <pre>
     * 积分段
     * </pre>
     *
     * <code>required int32 score = 4;</code>
     */
    public Builder setScore(int value) {
      bitField0_ |= 0x00000008;
      score_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 积分段
     * </pre>
     *
     * <code>required int32 score = 4;</code>
     */
    public Builder clearScore() {
      bitField0_ = (bitField0_ & ~0x00000008);
      score_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.SummonAllianceBoss)
  }

  // @@protoc_insertion_point(class_scope:client2server.SummonAllianceBoss)
  private static final pb4client.SummonAllianceBoss DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.SummonAllianceBoss();
  }

  public static pb4client.SummonAllianceBoss getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<SummonAllianceBoss>
      PARSER = new com.google.protobuf.AbstractParser<SummonAllianceBoss>() {
    public SummonAllianceBoss parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SummonAllianceBoss(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SummonAllianceBoss> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SummonAllianceBoss> getParserForType() {
    return PARSER;
  }

  public pb4client.SummonAllianceBoss getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

