// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.AllianceQueryReqListRt}
 */
public  final class AllianceQueryReqListRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AllianceQueryReqListRt)
    AllianceQueryReqListRtOrBuilder {
  // Use AllianceQueryReqListRt.newBuilder() to construct.
  private AllianceQueryReqListRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceQueryReqListRt() {
    rt_ = 0;
    canAddPower_ = 0L;
    powerLimit_ = 0L;
    players_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllianceQueryReqListRt(
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
            rt_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            canAddPower_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            powerLimit_ = input.readInt64();
            break;
          }
          case 34: {
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
              players_ = new java.util.ArrayList<pb4client.AllianceQueryReqListInfo>();
              mutable_bitField0_ |= 0x00000008;
            }
            players_.add(
                input.readMessage(pb4client.AllianceQueryReqListInfo.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
        players_ = java.util.Collections.unmodifiableList(players_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryReqListRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryReqListRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AllianceQueryReqListRt.class, pb4client.AllianceQueryReqListRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <pre>
   *返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int CANADDPOWER_FIELD_NUMBER = 2;
  private long canAddPower_;
  /**
   * <pre>
   * 可以直接加入的战斗力
   * </pre>
   *
   * <code>optional int64 canAddPower = 2;</code>
   */
  public boolean hasCanAddPower() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 可以直接加入的战斗力
   * </pre>
   *
   * <code>optional int64 canAddPower = 2;</code>
   */
  public long getCanAddPower() {
    return canAddPower_;
  }

  public static final int POWERLIMIT_FIELD_NUMBER = 3;
  private long powerLimit_;
  /**
   * <pre>
   * 可申请的最低战斗力
   * </pre>
   *
   * <code>optional int64 powerLimit = 3;</code>
   */
  public boolean hasPowerLimit() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 可申请的最低战斗力
   * </pre>
   *
   * <code>optional int64 powerLimit = 3;</code>
   */
  public long getPowerLimit() {
    return powerLimit_;
  }

  public static final int PLAYERS_FIELD_NUMBER = 4;
  private java.util.List<pb4client.AllianceQueryReqListInfo> players_;
  /**
   * <pre>
   *申请加入联盟的玩家列表
   * </pre>
   *
   * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
   */
  public java.util.List<pb4client.AllianceQueryReqListInfo> getPlayersList() {
    return players_;
  }
  /**
   * <pre>
   *申请加入联盟的玩家列表
   * </pre>
   *
   * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
   */
  public java.util.List<? extends pb4client.AllianceQueryReqListInfoOrBuilder> 
      getPlayersOrBuilderList() {
    return players_;
  }
  /**
   * <pre>
   *申请加入联盟的玩家列表
   * </pre>
   *
   * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
   */
  public int getPlayersCount() {
    return players_.size();
  }
  /**
   * <pre>
   *申请加入联盟的玩家列表
   * </pre>
   *
   * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
   */
  public pb4client.AllianceQueryReqListInfo getPlayers(int index) {
    return players_.get(index);
  }
  /**
   * <pre>
   *申请加入联盟的玩家列表
   * </pre>
   *
   * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
   */
  public pb4client.AllianceQueryReqListInfoOrBuilder getPlayersOrBuilder(
      int index) {
    return players_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasRt()) {
      memoizedIsInitialized = 0;
      return false;
    }
    for (int i = 0; i < getPlayersCount(); i++) {
      if (!getPlayers(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, rt_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, canAddPower_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt64(3, powerLimit_);
    }
    for (int i = 0; i < players_.size(); i++) {
      output.writeMessage(4, players_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rt_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, canAddPower_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, powerLimit_);
    }
    for (int i = 0; i < players_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, players_.get(i));
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
    if (!(obj instanceof pb4client.AllianceQueryReqListRt)) {
      return super.equals(obj);
    }
    pb4client.AllianceQueryReqListRt other = (pb4client.AllianceQueryReqListRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasCanAddPower() == other.hasCanAddPower());
    if (hasCanAddPower()) {
      result = result && (getCanAddPower()
          == other.getCanAddPower());
    }
    result = result && (hasPowerLimit() == other.hasPowerLimit());
    if (hasPowerLimit()) {
      result = result && (getPowerLimit()
          == other.getPowerLimit());
    }
    result = result && getPlayersList()
        .equals(other.getPlayersList());
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
    if (hasRt()) {
      hash = (37 * hash) + RT_FIELD_NUMBER;
      hash = (53 * hash) + getRt();
    }
    if (hasCanAddPower()) {
      hash = (37 * hash) + CANADDPOWER_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getCanAddPower());
    }
    if (hasPowerLimit()) {
      hash = (37 * hash) + POWERLIMIT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getPowerLimit());
    }
    if (getPlayersCount() > 0) {
      hash = (37 * hash) + PLAYERS_FIELD_NUMBER;
      hash = (53 * hash) + getPlayersList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AllianceQueryReqListRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceQueryReqListRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceQueryReqListRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceQueryReqListRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceQueryReqListRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceQueryReqListRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceQueryReqListRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceQueryReqListRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceQueryReqListRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AllianceQueryReqListRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceQueryReqListRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceQueryReqListRt parseFrom(
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
  public static Builder newBuilder(pb4client.AllianceQueryReqListRt prototype) {
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
   * Protobuf type {@code client2server.AllianceQueryReqListRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AllianceQueryReqListRt)
      pb4client.AllianceQueryReqListRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryReqListRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryReqListRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AllianceQueryReqListRt.class, pb4client.AllianceQueryReqListRt.Builder.class);
    }

    // Construct using pb4client.AllianceQueryReqListRt.newBuilder()
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
        getPlayersFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      canAddPower_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      powerLimit_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      if (playersBuilder_ == null) {
        players_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000008);
      } else {
        playersBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryReqListRt_descriptor;
    }

    public pb4client.AllianceQueryReqListRt getDefaultInstanceForType() {
      return pb4client.AllianceQueryReqListRt.getDefaultInstance();
    }

    public pb4client.AllianceQueryReqListRt build() {
      pb4client.AllianceQueryReqListRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AllianceQueryReqListRt buildPartial() {
      pb4client.AllianceQueryReqListRt result = new pb4client.AllianceQueryReqListRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.canAddPower_ = canAddPower_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.powerLimit_ = powerLimit_;
      if (playersBuilder_ == null) {
        if (((bitField0_ & 0x00000008) == 0x00000008)) {
          players_ = java.util.Collections.unmodifiableList(players_);
          bitField0_ = (bitField0_ & ~0x00000008);
        }
        result.players_ = players_;
      } else {
        result.players_ = playersBuilder_.build();
      }
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
      if (other instanceof pb4client.AllianceQueryReqListRt) {
        return mergeFrom((pb4client.AllianceQueryReqListRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AllianceQueryReqListRt other) {
      if (other == pb4client.AllianceQueryReqListRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasCanAddPower()) {
        setCanAddPower(other.getCanAddPower());
      }
      if (other.hasPowerLimit()) {
        setPowerLimit(other.getPowerLimit());
      }
      if (playersBuilder_ == null) {
        if (!other.players_.isEmpty()) {
          if (players_.isEmpty()) {
            players_ = other.players_;
            bitField0_ = (bitField0_ & ~0x00000008);
          } else {
            ensurePlayersIsMutable();
            players_.addAll(other.players_);
          }
          onChanged();
        }
      } else {
        if (!other.players_.isEmpty()) {
          if (playersBuilder_.isEmpty()) {
            playersBuilder_.dispose();
            playersBuilder_ = null;
            players_ = other.players_;
            bitField0_ = (bitField0_ & ~0x00000008);
            playersBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getPlayersFieldBuilder() : null;
          } else {
            playersBuilder_.addAllMessages(other.players_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRt()) {
        return false;
      }
      for (int i = 0; i < getPlayersCount(); i++) {
        if (!getPlayers(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AllianceQueryReqListRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AllianceQueryReqListRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int rt_ ;
    /**
     * <pre>
     *返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <pre>
     *返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private long canAddPower_ ;
    /**
     * <pre>
     * 可以直接加入的战斗力
     * </pre>
     *
     * <code>optional int64 canAddPower = 2;</code>
     */
    public boolean hasCanAddPower() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 可以直接加入的战斗力
     * </pre>
     *
     * <code>optional int64 canAddPower = 2;</code>
     */
    public long getCanAddPower() {
      return canAddPower_;
    }
    /**
     * <pre>
     * 可以直接加入的战斗力
     * </pre>
     *
     * <code>optional int64 canAddPower = 2;</code>
     */
    public Builder setCanAddPower(long value) {
      bitField0_ |= 0x00000002;
      canAddPower_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 可以直接加入的战斗力
     * </pre>
     *
     * <code>optional int64 canAddPower = 2;</code>
     */
    public Builder clearCanAddPower() {
      bitField0_ = (bitField0_ & ~0x00000002);
      canAddPower_ = 0L;
      onChanged();
      return this;
    }

    private long powerLimit_ ;
    /**
     * <pre>
     * 可申请的最低战斗力
     * </pre>
     *
     * <code>optional int64 powerLimit = 3;</code>
     */
    public boolean hasPowerLimit() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 可申请的最低战斗力
     * </pre>
     *
     * <code>optional int64 powerLimit = 3;</code>
     */
    public long getPowerLimit() {
      return powerLimit_;
    }
    /**
     * <pre>
     * 可申请的最低战斗力
     * </pre>
     *
     * <code>optional int64 powerLimit = 3;</code>
     */
    public Builder setPowerLimit(long value) {
      bitField0_ |= 0x00000004;
      powerLimit_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 可申请的最低战斗力
     * </pre>
     *
     * <code>optional int64 powerLimit = 3;</code>
     */
    public Builder clearPowerLimit() {
      bitField0_ = (bitField0_ & ~0x00000004);
      powerLimit_ = 0L;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.AllianceQueryReqListInfo> players_ =
      java.util.Collections.emptyList();
    private void ensurePlayersIsMutable() {
      if (!((bitField0_ & 0x00000008) == 0x00000008)) {
        players_ = new java.util.ArrayList<pb4client.AllianceQueryReqListInfo>(players_);
        bitField0_ |= 0x00000008;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.AllianceQueryReqListInfo, pb4client.AllianceQueryReqListInfo.Builder, pb4client.AllianceQueryReqListInfoOrBuilder> playersBuilder_;

    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public java.util.List<pb4client.AllianceQueryReqListInfo> getPlayersList() {
      if (playersBuilder_ == null) {
        return java.util.Collections.unmodifiableList(players_);
      } else {
        return playersBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public int getPlayersCount() {
      if (playersBuilder_ == null) {
        return players_.size();
      } else {
        return playersBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public pb4client.AllianceQueryReqListInfo getPlayers(int index) {
      if (playersBuilder_ == null) {
        return players_.get(index);
      } else {
        return playersBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public Builder setPlayers(
        int index, pb4client.AllianceQueryReqListInfo value) {
      if (playersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePlayersIsMutable();
        players_.set(index, value);
        onChanged();
      } else {
        playersBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public Builder setPlayers(
        int index, pb4client.AllianceQueryReqListInfo.Builder builderForValue) {
      if (playersBuilder_ == null) {
        ensurePlayersIsMutable();
        players_.set(index, builderForValue.build());
        onChanged();
      } else {
        playersBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public Builder addPlayers(pb4client.AllianceQueryReqListInfo value) {
      if (playersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePlayersIsMutable();
        players_.add(value);
        onChanged();
      } else {
        playersBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public Builder addPlayers(
        int index, pb4client.AllianceQueryReqListInfo value) {
      if (playersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePlayersIsMutable();
        players_.add(index, value);
        onChanged();
      } else {
        playersBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public Builder addPlayers(
        pb4client.AllianceQueryReqListInfo.Builder builderForValue) {
      if (playersBuilder_ == null) {
        ensurePlayersIsMutable();
        players_.add(builderForValue.build());
        onChanged();
      } else {
        playersBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public Builder addPlayers(
        int index, pb4client.AllianceQueryReqListInfo.Builder builderForValue) {
      if (playersBuilder_ == null) {
        ensurePlayersIsMutable();
        players_.add(index, builderForValue.build());
        onChanged();
      } else {
        playersBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public Builder addAllPlayers(
        java.lang.Iterable<? extends pb4client.AllianceQueryReqListInfo> values) {
      if (playersBuilder_ == null) {
        ensurePlayersIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, players_);
        onChanged();
      } else {
        playersBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public Builder clearPlayers() {
      if (playersBuilder_ == null) {
        players_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000008);
        onChanged();
      } else {
        playersBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public Builder removePlayers(int index) {
      if (playersBuilder_ == null) {
        ensurePlayersIsMutable();
        players_.remove(index);
        onChanged();
      } else {
        playersBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public pb4client.AllianceQueryReqListInfo.Builder getPlayersBuilder(
        int index) {
      return getPlayersFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public pb4client.AllianceQueryReqListInfoOrBuilder getPlayersOrBuilder(
        int index) {
      if (playersBuilder_ == null) {
        return players_.get(index);  } else {
        return playersBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public java.util.List<? extends pb4client.AllianceQueryReqListInfoOrBuilder> 
         getPlayersOrBuilderList() {
      if (playersBuilder_ != null) {
        return playersBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(players_);
      }
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public pb4client.AllianceQueryReqListInfo.Builder addPlayersBuilder() {
      return getPlayersFieldBuilder().addBuilder(
          pb4client.AllianceQueryReqListInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public pb4client.AllianceQueryReqListInfo.Builder addPlayersBuilder(
        int index) {
      return getPlayersFieldBuilder().addBuilder(
          index, pb4client.AllianceQueryReqListInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *申请加入联盟的玩家列表
     * </pre>
     *
     * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
     */
    public java.util.List<pb4client.AllianceQueryReqListInfo.Builder> 
         getPlayersBuilderList() {
      return getPlayersFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.AllianceQueryReqListInfo, pb4client.AllianceQueryReqListInfo.Builder, pb4client.AllianceQueryReqListInfoOrBuilder> 
        getPlayersFieldBuilder() {
      if (playersBuilder_ == null) {
        playersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.AllianceQueryReqListInfo, pb4client.AllianceQueryReqListInfo.Builder, pb4client.AllianceQueryReqListInfoOrBuilder>(
                players_,
                ((bitField0_ & 0x00000008) == 0x00000008),
                getParentForChildren(),
                isClean());
        players_ = null;
      }
      return playersBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.AllianceQueryReqListRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.AllianceQueryReqListRt)
  private static final pb4client.AllianceQueryReqListRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AllianceQueryReqListRt();
  }

  public static pb4client.AllianceQueryReqListRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AllianceQueryReqListRt>
      PARSER = new com.google.protobuf.AbstractParser<AllianceQueryReqListRt>() {
    public AllianceQueryReqListRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceQueryReqListRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceQueryReqListRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceQueryReqListRt> getParserForType() {
    return PARSER;
  }

  public pb4client.AllianceQueryReqListRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

