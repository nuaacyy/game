// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.ActivityBossInfo}
 */
public  final class ActivityBossInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.ActivityBossInfo)
    ActivityBossInfoOrBuilder {
  // Use ActivityBossInfo.newBuilder() to construct.
  private ActivityBossInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ActivityBossInfo() {
    monsterActivityId_ = 0;
    bossHp_ = 0;
    unlockTime_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ActivityBossInfo(
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
            monsterActivityId_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            bossHp_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            unlockTime_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_ActivityBossInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_ActivityBossInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.ActivityBossInfo.class, pb4client.ActivityBossInfo.Builder.class);
  }

  private int bitField0_;
  public static final int MONSTERACTIVITYID_FIELD_NUMBER = 1;
  private int monsterActivityId_;
  /**
   * <pre>
   *这个是monsterActivity表的id字段
   * </pre>
   *
   * <code>required int32 monsterActivityId = 1;</code>
   */
  public boolean hasMonsterActivityId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *这个是monsterActivity表的id字段
   * </pre>
   *
   * <code>required int32 monsterActivityId = 1;</code>
   */
  public int getMonsterActivityId() {
    return monsterActivityId_;
  }

  public static final int BOSSHP_FIELD_NUMBER = 2;
  private int bossHp_;
  /**
   * <pre>
   * 血量	(合并allianceBossHp字段,根据BossId计算血量，再计算比例)
   * </pre>
   *
   * <code>required int32 bossHp = 2;</code>
   */
  public boolean hasBossHp() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 血量	(合并allianceBossHp字段,根据BossId计算血量，再计算比例)
   * </pre>
   *
   * <code>required int32 bossHp = 2;</code>
   */
  public int getBossHp() {
    return bossHp_;
  }

  public static final int UNLOCKTIME_FIELD_NUMBER = 3;
  private int unlockTime_;
  /**
   * <pre>
   * 魔物解锁时间
   * </pre>
   *
   * <code>required int32 unlockTime = 3;</code>
   */
  public boolean hasUnlockTime() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 魔物解锁时间
   * </pre>
   *
   * <code>required int32 unlockTime = 3;</code>
   */
  public int getUnlockTime() {
    return unlockTime_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasMonsterActivityId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasBossHp()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasUnlockTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, monsterActivityId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, bossHp_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, unlockTime_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, monsterActivityId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, bossHp_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, unlockTime_);
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
    if (!(obj instanceof pb4client.ActivityBossInfo)) {
      return super.equals(obj);
    }
    pb4client.ActivityBossInfo other = (pb4client.ActivityBossInfo) obj;

    boolean result = true;
    result = result && (hasMonsterActivityId() == other.hasMonsterActivityId());
    if (hasMonsterActivityId()) {
      result = result && (getMonsterActivityId()
          == other.getMonsterActivityId());
    }
    result = result && (hasBossHp() == other.hasBossHp());
    if (hasBossHp()) {
      result = result && (getBossHp()
          == other.getBossHp());
    }
    result = result && (hasUnlockTime() == other.hasUnlockTime());
    if (hasUnlockTime()) {
      result = result && (getUnlockTime()
          == other.getUnlockTime());
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
    if (hasMonsterActivityId()) {
      hash = (37 * hash) + MONSTERACTIVITYID_FIELD_NUMBER;
      hash = (53 * hash) + getMonsterActivityId();
    }
    if (hasBossHp()) {
      hash = (37 * hash) + BOSSHP_FIELD_NUMBER;
      hash = (53 * hash) + getBossHp();
    }
    if (hasUnlockTime()) {
      hash = (37 * hash) + UNLOCKTIME_FIELD_NUMBER;
      hash = (53 * hash) + getUnlockTime();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.ActivityBossInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ActivityBossInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ActivityBossInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ActivityBossInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ActivityBossInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ActivityBossInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ActivityBossInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ActivityBossInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ActivityBossInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.ActivityBossInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ActivityBossInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ActivityBossInfo parseFrom(
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
  public static Builder newBuilder(pb4client.ActivityBossInfo prototype) {
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
   * Protobuf type {@code client2server.ActivityBossInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.ActivityBossInfo)
      pb4client.ActivityBossInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_ActivityBossInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_ActivityBossInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.ActivityBossInfo.class, pb4client.ActivityBossInfo.Builder.class);
    }

    // Construct using pb4client.ActivityBossInfo.newBuilder()
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
      monsterActivityId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      bossHp_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      unlockTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_ActivityBossInfo_descriptor;
    }

    public pb4client.ActivityBossInfo getDefaultInstanceForType() {
      return pb4client.ActivityBossInfo.getDefaultInstance();
    }

    public pb4client.ActivityBossInfo build() {
      pb4client.ActivityBossInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.ActivityBossInfo buildPartial() {
      pb4client.ActivityBossInfo result = new pb4client.ActivityBossInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.monsterActivityId_ = monsterActivityId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.bossHp_ = bossHp_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.unlockTime_ = unlockTime_;
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
      if (other instanceof pb4client.ActivityBossInfo) {
        return mergeFrom((pb4client.ActivityBossInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.ActivityBossInfo other) {
      if (other == pb4client.ActivityBossInfo.getDefaultInstance()) return this;
      if (other.hasMonsterActivityId()) {
        setMonsterActivityId(other.getMonsterActivityId());
      }
      if (other.hasBossHp()) {
        setBossHp(other.getBossHp());
      }
      if (other.hasUnlockTime()) {
        setUnlockTime(other.getUnlockTime());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasMonsterActivityId()) {
        return false;
      }
      if (!hasBossHp()) {
        return false;
      }
      if (!hasUnlockTime()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.ActivityBossInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.ActivityBossInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int monsterActivityId_ ;
    /**
     * <pre>
     *这个是monsterActivity表的id字段
     * </pre>
     *
     * <code>required int32 monsterActivityId = 1;</code>
     */
    public boolean hasMonsterActivityId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *这个是monsterActivity表的id字段
     * </pre>
     *
     * <code>required int32 monsterActivityId = 1;</code>
     */
    public int getMonsterActivityId() {
      return monsterActivityId_;
    }
    /**
     * <pre>
     *这个是monsterActivity表的id字段
     * </pre>
     *
     * <code>required int32 monsterActivityId = 1;</code>
     */
    public Builder setMonsterActivityId(int value) {
      bitField0_ |= 0x00000001;
      monsterActivityId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *这个是monsterActivity表的id字段
     * </pre>
     *
     * <code>required int32 monsterActivityId = 1;</code>
     */
    public Builder clearMonsterActivityId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      monsterActivityId_ = 0;
      onChanged();
      return this;
    }

    private int bossHp_ ;
    /**
     * <pre>
     * 血量	(合并allianceBossHp字段,根据BossId计算血量，再计算比例)
     * </pre>
     *
     * <code>required int32 bossHp = 2;</code>
     */
    public boolean hasBossHp() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 血量	(合并allianceBossHp字段,根据BossId计算血量，再计算比例)
     * </pre>
     *
     * <code>required int32 bossHp = 2;</code>
     */
    public int getBossHp() {
      return bossHp_;
    }
    /**
     * <pre>
     * 血量	(合并allianceBossHp字段,根据BossId计算血量，再计算比例)
     * </pre>
     *
     * <code>required int32 bossHp = 2;</code>
     */
    public Builder setBossHp(int value) {
      bitField0_ |= 0x00000002;
      bossHp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 血量	(合并allianceBossHp字段,根据BossId计算血量，再计算比例)
     * </pre>
     *
     * <code>required int32 bossHp = 2;</code>
     */
    public Builder clearBossHp() {
      bitField0_ = (bitField0_ & ~0x00000002);
      bossHp_ = 0;
      onChanged();
      return this;
    }

    private int unlockTime_ ;
    /**
     * <pre>
     * 魔物解锁时间
     * </pre>
     *
     * <code>required int32 unlockTime = 3;</code>
     */
    public boolean hasUnlockTime() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 魔物解锁时间
     * </pre>
     *
     * <code>required int32 unlockTime = 3;</code>
     */
    public int getUnlockTime() {
      return unlockTime_;
    }
    /**
     * <pre>
     * 魔物解锁时间
     * </pre>
     *
     * <code>required int32 unlockTime = 3;</code>
     */
    public Builder setUnlockTime(int value) {
      bitField0_ |= 0x00000004;
      unlockTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 魔物解锁时间
     * </pre>
     *
     * <code>required int32 unlockTime = 3;</code>
     */
    public Builder clearUnlockTime() {
      bitField0_ = (bitField0_ & ~0x00000004);
      unlockTime_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.ActivityBossInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.ActivityBossInfo)
  private static final pb4client.ActivityBossInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.ActivityBossInfo();
  }

  public static pb4client.ActivityBossInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ActivityBossInfo>
      PARSER = new com.google.protobuf.AbstractParser<ActivityBossInfo>() {
    public ActivityBossInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ActivityBossInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ActivityBossInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ActivityBossInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.ActivityBossInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

