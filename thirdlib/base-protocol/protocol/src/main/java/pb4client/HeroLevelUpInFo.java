// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.HeroLevelUpInFo}
 */
public  final class HeroLevelUpInFo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.HeroLevelUpInFo)
    HeroLevelUpInFoOrBuilder {
  // Use HeroLevelUpInFo.newBuilder() to construct.
  private HeroLevelUpInFo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HeroLevelUpInFo() {
    heroId_ = 0L;
    heroProtoId_ = 0;
    lv_ = 0;
    attack_ = 0;
    magic_ = 0;
    defence_ = 0;
    speed_ = 0;
    attCity_ = 0;
    points_ = 0;
    experience_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private HeroLevelUpInFo(
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
            heroId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            heroProtoId_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            lv_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            attack_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            magic_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            defence_ = input.readInt32();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            speed_ = input.readInt32();
            break;
          }
          case 64: {
            bitField0_ |= 0x00000080;
            attCity_ = input.readInt32();
            break;
          }
          case 72: {
            bitField0_ |= 0x00000100;
            points_ = input.readInt32();
            break;
          }
          case 80: {
            bitField0_ |= 0x00000200;
            experience_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_HeroLevelUpInFo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_HeroLevelUpInFo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.HeroLevelUpInFo.class, pb4client.HeroLevelUpInFo.Builder.class);
  }

  private int bitField0_;
  public static final int HEROID_FIELD_NUMBER = 1;
  private long heroId_;
  /**
   * <code>required int64 heroId = 1;</code>
   */
  public boolean hasHeroId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int64 heroId = 1;</code>
   */
  public long getHeroId() {
    return heroId_;
  }

  public static final int HEROPROTOID_FIELD_NUMBER = 2;
  private int heroProtoId_;
  /**
   * <code>required int32 heroProtoId = 2;</code>
   */
  public boolean hasHeroProtoId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required int32 heroProtoId = 2;</code>
   */
  public int getHeroProtoId() {
    return heroProtoId_;
  }

  public static final int LV_FIELD_NUMBER = 3;
  private int lv_;
  /**
   * <code>required int32 lv = 3;</code>
   */
  public boolean hasLv() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required int32 lv = 3;</code>
   */
  public int getLv() {
    return lv_;
  }

  public static final int ATTACK_FIELD_NUMBER = 4;
  private int attack_;
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 attack = 4;</code>
   */
  public boolean hasAttack() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 attack = 4;</code>
   */
  public int getAttack() {
    return attack_;
  }

  public static final int MAGIC_FIELD_NUMBER = 5;
  private int magic_;
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 magic = 5;</code>
   */
  public boolean hasMagic() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 magic = 5;</code>
   */
  public int getMagic() {
    return magic_;
  }

  public static final int DEFENCE_FIELD_NUMBER = 6;
  private int defence_;
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 defence = 6;</code>
   */
  public boolean hasDefence() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 defence = 6;</code>
   */
  public int getDefence() {
    return defence_;
  }

  public static final int SPEED_FIELD_NUMBER = 7;
  private int speed_;
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 speed = 7;</code>
   */
  public boolean hasSpeed() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 speed = 7;</code>
   */
  public int getSpeed() {
    return speed_;
  }

  public static final int ATTCITY_FIELD_NUMBER = 8;
  private int attCity_;
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 attCity = 8;</code>
   */
  public boolean hasAttCity() {
    return ((bitField0_ & 0x00000080) == 0x00000080);
  }
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 attCity = 8;</code>
   */
  public int getAttCity() {
    return attCity_;
  }

  public static final int POINTS_FIELD_NUMBER = 9;
  private int points_;
  /**
   * <code>required int32 points = 9;</code>
   */
  public boolean hasPoints() {
    return ((bitField0_ & 0x00000100) == 0x00000100);
  }
  /**
   * <code>required int32 points = 9;</code>
   */
  public int getPoints() {
    return points_;
  }

  public static final int EXPERIENCE_FIELD_NUMBER = 10;
  private int experience_;
  /**
   * <code>required int32 experience = 10;</code>
   */
  public boolean hasExperience() {
    return ((bitField0_ & 0x00000200) == 0x00000200);
  }
  /**
   * <code>required int32 experience = 10;</code>
   */
  public int getExperience() {
    return experience_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasHeroId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasHeroProtoId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasLv()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAttack()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasMagic()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasDefence()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasSpeed()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAttCity()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPoints()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasExperience()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, heroId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, heroProtoId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, lv_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, attack_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, magic_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt32(6, defence_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, speed_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      output.writeInt32(8, attCity_);
    }
    if (((bitField0_ & 0x00000100) == 0x00000100)) {
      output.writeInt32(9, points_);
    }
    if (((bitField0_ & 0x00000200) == 0x00000200)) {
      output.writeInt32(10, experience_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, heroId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, heroProtoId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, lv_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, attack_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, magic_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, defence_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, speed_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, attCity_);
    }
    if (((bitField0_ & 0x00000100) == 0x00000100)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(9, points_);
    }
    if (((bitField0_ & 0x00000200) == 0x00000200)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(10, experience_);
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
    if (!(obj instanceof pb4client.HeroLevelUpInFo)) {
      return super.equals(obj);
    }
    pb4client.HeroLevelUpInFo other = (pb4client.HeroLevelUpInFo) obj;

    boolean result = true;
    result = result && (hasHeroId() == other.hasHeroId());
    if (hasHeroId()) {
      result = result && (getHeroId()
          == other.getHeroId());
    }
    result = result && (hasHeroProtoId() == other.hasHeroProtoId());
    if (hasHeroProtoId()) {
      result = result && (getHeroProtoId()
          == other.getHeroProtoId());
    }
    result = result && (hasLv() == other.hasLv());
    if (hasLv()) {
      result = result && (getLv()
          == other.getLv());
    }
    result = result && (hasAttack() == other.hasAttack());
    if (hasAttack()) {
      result = result && (getAttack()
          == other.getAttack());
    }
    result = result && (hasMagic() == other.hasMagic());
    if (hasMagic()) {
      result = result && (getMagic()
          == other.getMagic());
    }
    result = result && (hasDefence() == other.hasDefence());
    if (hasDefence()) {
      result = result && (getDefence()
          == other.getDefence());
    }
    result = result && (hasSpeed() == other.hasSpeed());
    if (hasSpeed()) {
      result = result && (getSpeed()
          == other.getSpeed());
    }
    result = result && (hasAttCity() == other.hasAttCity());
    if (hasAttCity()) {
      result = result && (getAttCity()
          == other.getAttCity());
    }
    result = result && (hasPoints() == other.hasPoints());
    if (hasPoints()) {
      result = result && (getPoints()
          == other.getPoints());
    }
    result = result && (hasExperience() == other.hasExperience());
    if (hasExperience()) {
      result = result && (getExperience()
          == other.getExperience());
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
    if (hasHeroId()) {
      hash = (37 * hash) + HEROID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getHeroId());
    }
    if (hasHeroProtoId()) {
      hash = (37 * hash) + HEROPROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getHeroProtoId();
    }
    if (hasLv()) {
      hash = (37 * hash) + LV_FIELD_NUMBER;
      hash = (53 * hash) + getLv();
    }
    if (hasAttack()) {
      hash = (37 * hash) + ATTACK_FIELD_NUMBER;
      hash = (53 * hash) + getAttack();
    }
    if (hasMagic()) {
      hash = (37 * hash) + MAGIC_FIELD_NUMBER;
      hash = (53 * hash) + getMagic();
    }
    if (hasDefence()) {
      hash = (37 * hash) + DEFENCE_FIELD_NUMBER;
      hash = (53 * hash) + getDefence();
    }
    if (hasSpeed()) {
      hash = (37 * hash) + SPEED_FIELD_NUMBER;
      hash = (53 * hash) + getSpeed();
    }
    if (hasAttCity()) {
      hash = (37 * hash) + ATTCITY_FIELD_NUMBER;
      hash = (53 * hash) + getAttCity();
    }
    if (hasPoints()) {
      hash = (37 * hash) + POINTS_FIELD_NUMBER;
      hash = (53 * hash) + getPoints();
    }
    if (hasExperience()) {
      hash = (37 * hash) + EXPERIENCE_FIELD_NUMBER;
      hash = (53 * hash) + getExperience();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.HeroLevelUpInFo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroLevelUpInFo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroLevelUpInFo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroLevelUpInFo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroLevelUpInFo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroLevelUpInFo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroLevelUpInFo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.HeroLevelUpInFo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.HeroLevelUpInFo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.HeroLevelUpInFo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.HeroLevelUpInFo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.HeroLevelUpInFo parseFrom(
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
  public static Builder newBuilder(pb4client.HeroLevelUpInFo prototype) {
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
   * Protobuf type {@code client2server.HeroLevelUpInFo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.HeroLevelUpInFo)
      pb4client.HeroLevelUpInFoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroLevelUpInFo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroLevelUpInFo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.HeroLevelUpInFo.class, pb4client.HeroLevelUpInFo.Builder.class);
    }

    // Construct using pb4client.HeroLevelUpInFo.newBuilder()
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
      heroId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      heroProtoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      lv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      attack_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      magic_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      defence_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      speed_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      attCity_ = 0;
      bitField0_ = (bitField0_ & ~0x00000080);
      points_ = 0;
      bitField0_ = (bitField0_ & ~0x00000100);
      experience_ = 0;
      bitField0_ = (bitField0_ & ~0x00000200);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroLevelUpInFo_descriptor;
    }

    public pb4client.HeroLevelUpInFo getDefaultInstanceForType() {
      return pb4client.HeroLevelUpInFo.getDefaultInstance();
    }

    public pb4client.HeroLevelUpInFo build() {
      pb4client.HeroLevelUpInFo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.HeroLevelUpInFo buildPartial() {
      pb4client.HeroLevelUpInFo result = new pb4client.HeroLevelUpInFo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.heroId_ = heroId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.heroProtoId_ = heroProtoId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.lv_ = lv_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.attack_ = attack_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.magic_ = magic_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.defence_ = defence_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.speed_ = speed_;
      if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
        to_bitField0_ |= 0x00000080;
      }
      result.attCity_ = attCity_;
      if (((from_bitField0_ & 0x00000100) == 0x00000100)) {
        to_bitField0_ |= 0x00000100;
      }
      result.points_ = points_;
      if (((from_bitField0_ & 0x00000200) == 0x00000200)) {
        to_bitField0_ |= 0x00000200;
      }
      result.experience_ = experience_;
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
      if (other instanceof pb4client.HeroLevelUpInFo) {
        return mergeFrom((pb4client.HeroLevelUpInFo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.HeroLevelUpInFo other) {
      if (other == pb4client.HeroLevelUpInFo.getDefaultInstance()) return this;
      if (other.hasHeroId()) {
        setHeroId(other.getHeroId());
      }
      if (other.hasHeroProtoId()) {
        setHeroProtoId(other.getHeroProtoId());
      }
      if (other.hasLv()) {
        setLv(other.getLv());
      }
      if (other.hasAttack()) {
        setAttack(other.getAttack());
      }
      if (other.hasMagic()) {
        setMagic(other.getMagic());
      }
      if (other.hasDefence()) {
        setDefence(other.getDefence());
      }
      if (other.hasSpeed()) {
        setSpeed(other.getSpeed());
      }
      if (other.hasAttCity()) {
        setAttCity(other.getAttCity());
      }
      if (other.hasPoints()) {
        setPoints(other.getPoints());
      }
      if (other.hasExperience()) {
        setExperience(other.getExperience());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasHeroId()) {
        return false;
      }
      if (!hasHeroProtoId()) {
        return false;
      }
      if (!hasLv()) {
        return false;
      }
      if (!hasAttack()) {
        return false;
      }
      if (!hasMagic()) {
        return false;
      }
      if (!hasDefence()) {
        return false;
      }
      if (!hasSpeed()) {
        return false;
      }
      if (!hasAttCity()) {
        return false;
      }
      if (!hasPoints()) {
        return false;
      }
      if (!hasExperience()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.HeroLevelUpInFo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.HeroLevelUpInFo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long heroId_ ;
    /**
     * <code>required int64 heroId = 1;</code>
     */
    public boolean hasHeroId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 heroId = 1;</code>
     */
    public long getHeroId() {
      return heroId_;
    }
    /**
     * <code>required int64 heroId = 1;</code>
     */
    public Builder setHeroId(long value) {
      bitField0_ |= 0x00000001;
      heroId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 heroId = 1;</code>
     */
    public Builder clearHeroId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      heroId_ = 0L;
      onChanged();
      return this;
    }

    private int heroProtoId_ ;
    /**
     * <code>required int32 heroProtoId = 2;</code>
     */
    public boolean hasHeroProtoId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 heroProtoId = 2;</code>
     */
    public int getHeroProtoId() {
      return heroProtoId_;
    }
    /**
     * <code>required int32 heroProtoId = 2;</code>
     */
    public Builder setHeroProtoId(int value) {
      bitField0_ |= 0x00000002;
      heroProtoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 heroProtoId = 2;</code>
     */
    public Builder clearHeroProtoId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      heroProtoId_ = 0;
      onChanged();
      return this;
    }

    private int lv_ ;
    /**
     * <code>required int32 lv = 3;</code>
     */
    public boolean hasLv() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 lv = 3;</code>
     */
    public int getLv() {
      return lv_;
    }
    /**
     * <code>required int32 lv = 3;</code>
     */
    public Builder setLv(int value) {
      bitField0_ |= 0x00000004;
      lv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 lv = 3;</code>
     */
    public Builder clearLv() {
      bitField0_ = (bitField0_ & ~0x00000004);
      lv_ = 0;
      onChanged();
      return this;
    }

    private int attack_ ;
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 attack = 4;</code>
     */
    public boolean hasAttack() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 attack = 4;</code>
     */
    public int getAttack() {
      return attack_;
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 attack = 4;</code>
     */
    public Builder setAttack(int value) {
      bitField0_ |= 0x00000008;
      attack_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 attack = 4;</code>
     */
    public Builder clearAttack() {
      bitField0_ = (bitField0_ & ~0x00000008);
      attack_ = 0;
      onChanged();
      return this;
    }

    private int magic_ ;
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 magic = 5;</code>
     */
    public boolean hasMagic() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 magic = 5;</code>
     */
    public int getMagic() {
      return magic_;
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 magic = 5;</code>
     */
    public Builder setMagic(int value) {
      bitField0_ |= 0x00000010;
      magic_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 magic = 5;</code>
     */
    public Builder clearMagic() {
      bitField0_ = (bitField0_ & ~0x00000010);
      magic_ = 0;
      onChanged();
      return this;
    }

    private int defence_ ;
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 defence = 6;</code>
     */
    public boolean hasDefence() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 defence = 6;</code>
     */
    public int getDefence() {
      return defence_;
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 defence = 6;</code>
     */
    public Builder setDefence(int value) {
      bitField0_ |= 0x00000020;
      defence_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 defence = 6;</code>
     */
    public Builder clearDefence() {
      bitField0_ = (bitField0_ & ~0x00000020);
      defence_ = 0;
      onChanged();
      return this;
    }

    private int speed_ ;
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 speed = 7;</code>
     */
    public boolean hasSpeed() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 speed = 7;</code>
     */
    public int getSpeed() {
      return speed_;
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 speed = 7;</code>
     */
    public Builder setSpeed(int value) {
      bitField0_ |= 0x00000040;
      speed_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 speed = 7;</code>
     */
    public Builder clearSpeed() {
      bitField0_ = (bitField0_ & ~0x00000040);
      speed_ = 0;
      onChanged();
      return this;
    }

    private int attCity_ ;
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 attCity = 8;</code>
     */
    public boolean hasAttCity() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 attCity = 8;</code>
     */
    public int getAttCity() {
      return attCity_;
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 attCity = 8;</code>
     */
    public Builder setAttCity(int value) {
      bitField0_ |= 0x00000080;
      attCity_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *原为float 现*100为int
     * </pre>
     *
     * <code>required int32 attCity = 8;</code>
     */
    public Builder clearAttCity() {
      bitField0_ = (bitField0_ & ~0x00000080);
      attCity_ = 0;
      onChanged();
      return this;
    }

    private int points_ ;
    /**
     * <code>required int32 points = 9;</code>
     */
    public boolean hasPoints() {
      return ((bitField0_ & 0x00000100) == 0x00000100);
    }
    /**
     * <code>required int32 points = 9;</code>
     */
    public int getPoints() {
      return points_;
    }
    /**
     * <code>required int32 points = 9;</code>
     */
    public Builder setPoints(int value) {
      bitField0_ |= 0x00000100;
      points_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 points = 9;</code>
     */
    public Builder clearPoints() {
      bitField0_ = (bitField0_ & ~0x00000100);
      points_ = 0;
      onChanged();
      return this;
    }

    private int experience_ ;
    /**
     * <code>required int32 experience = 10;</code>
     */
    public boolean hasExperience() {
      return ((bitField0_ & 0x00000200) == 0x00000200);
    }
    /**
     * <code>required int32 experience = 10;</code>
     */
    public int getExperience() {
      return experience_;
    }
    /**
     * <code>required int32 experience = 10;</code>
     */
    public Builder setExperience(int value) {
      bitField0_ |= 0x00000200;
      experience_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 experience = 10;</code>
     */
    public Builder clearExperience() {
      bitField0_ = (bitField0_ & ~0x00000200);
      experience_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.HeroLevelUpInFo)
  }

  // @@protoc_insertion_point(class_scope:client2server.HeroLevelUpInFo)
  private static final pb4client.HeroLevelUpInFo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.HeroLevelUpInFo();
  }

  public static pb4client.HeroLevelUpInFo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<HeroLevelUpInFo>
      PARSER = new com.google.protobuf.AbstractParser<HeroLevelUpInFo>() {
    public HeroLevelUpInFo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new HeroLevelUpInFo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HeroLevelUpInFo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<HeroLevelUpInFo> getParserForType() {
    return PARSER;
  }

  public pb4client.HeroLevelUpInFo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

