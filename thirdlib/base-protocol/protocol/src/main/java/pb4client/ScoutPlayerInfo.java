// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 *侦察玩家信息
 * </pre>
 *
 * Protobuf type {@code client2server.ScoutPlayerInfo}
 */
public  final class ScoutPlayerInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.ScoutPlayerInfo)
    ScoutPlayerInfoOrBuilder {
  // Use ScoutPlayerInfo.newBuilder() to construct.
  private ScoutPlayerInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ScoutPlayerInfo() {
    laridState_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ScoutPlayerInfo(
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
          case 10: {
            pb4client.FightPlayerInfo.Builder subBuilder = null;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
              subBuilder = playerInfo_.toBuilder();
            }
            playerInfo_ = input.readMessage(pb4client.FightPlayerInfo.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(playerInfo_);
              playerInfo_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000001;
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            laridState_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_ScoutPlayerInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_ScoutPlayerInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.ScoutPlayerInfo.class, pb4client.ScoutPlayerInfo.Builder.class);
  }

  private int bitField0_;
  public static final int PLAYERINFO_FIELD_NUMBER = 1;
  private pb4client.FightPlayerInfo playerInfo_;
  /**
   * <pre>
   * 玩家(集结手)信息
   * </pre>
   *
   * <code>required .client2server.FightPlayerInfo playerInfo = 1;</code>
   */
  public boolean hasPlayerInfo() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 玩家(集结手)信息
   * </pre>
   *
   * <code>required .client2server.FightPlayerInfo playerInfo = 1;</code>
   */
  public pb4client.FightPlayerInfo getPlayerInfo() {
    return playerInfo_ == null ? pb4client.FightPlayerInfo.getDefaultInstance() : playerInfo_;
  }
  /**
   * <pre>
   * 玩家(集结手)信息
   * </pre>
   *
   * <code>required .client2server.FightPlayerInfo playerInfo = 1;</code>
   */
  public pb4client.FightPlayerInfoOrBuilder getPlayerInfoOrBuilder() {
    return playerInfo_ == null ? pb4client.FightPlayerInfo.getDefaultInstance() : playerInfo_;
  }

  public static final int LARIDSTATE_FIELD_NUMBER = 2;
  private int laridState_;
  /**
   * <pre>
   * 领主状态 0-不明 1-行军中 2-被抓 3-藏兵 4-城内集结 5-死亡 6-城内
   * </pre>
   *
   * <code>optional int32 laridState = 2;</code>
   */
  public boolean hasLaridState() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 领主状态 0-不明 1-行军中 2-被抓 3-藏兵 4-城内集结 5-死亡 6-城内
   * </pre>
   *
   * <code>optional int32 laridState = 2;</code>
   */
  public int getLaridState() {
    return laridState_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasPlayerInfo()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeMessage(1, getPlayerInfo());
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, laridState_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getPlayerInfo());
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, laridState_);
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
    if (!(obj instanceof pb4client.ScoutPlayerInfo)) {
      return super.equals(obj);
    }
    pb4client.ScoutPlayerInfo other = (pb4client.ScoutPlayerInfo) obj;

    boolean result = true;
    result = result && (hasPlayerInfo() == other.hasPlayerInfo());
    if (hasPlayerInfo()) {
      result = result && getPlayerInfo()
          .equals(other.getPlayerInfo());
    }
    result = result && (hasLaridState() == other.hasLaridState());
    if (hasLaridState()) {
      result = result && (getLaridState()
          == other.getLaridState());
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
    if (hasPlayerInfo()) {
      hash = (37 * hash) + PLAYERINFO_FIELD_NUMBER;
      hash = (53 * hash) + getPlayerInfo().hashCode();
    }
    if (hasLaridState()) {
      hash = (37 * hash) + LARIDSTATE_FIELD_NUMBER;
      hash = (53 * hash) + getLaridState();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.ScoutPlayerInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ScoutPlayerInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ScoutPlayerInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ScoutPlayerInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ScoutPlayerInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ScoutPlayerInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ScoutPlayerInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ScoutPlayerInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ScoutPlayerInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.ScoutPlayerInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ScoutPlayerInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ScoutPlayerInfo parseFrom(
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
  public static Builder newBuilder(pb4client.ScoutPlayerInfo prototype) {
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
   *侦察玩家信息
   * </pre>
   *
   * Protobuf type {@code client2server.ScoutPlayerInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.ScoutPlayerInfo)
      pb4client.ScoutPlayerInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_ScoutPlayerInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_ScoutPlayerInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.ScoutPlayerInfo.class, pb4client.ScoutPlayerInfo.Builder.class);
    }

    // Construct using pb4client.ScoutPlayerInfo.newBuilder()
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
        getPlayerInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (playerInfoBuilder_ == null) {
        playerInfo_ = null;
      } else {
        playerInfoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      laridState_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_ScoutPlayerInfo_descriptor;
    }

    public pb4client.ScoutPlayerInfo getDefaultInstanceForType() {
      return pb4client.ScoutPlayerInfo.getDefaultInstance();
    }

    public pb4client.ScoutPlayerInfo build() {
      pb4client.ScoutPlayerInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.ScoutPlayerInfo buildPartial() {
      pb4client.ScoutPlayerInfo result = new pb4client.ScoutPlayerInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      if (playerInfoBuilder_ == null) {
        result.playerInfo_ = playerInfo_;
      } else {
        result.playerInfo_ = playerInfoBuilder_.build();
      }
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.laridState_ = laridState_;
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
      if (other instanceof pb4client.ScoutPlayerInfo) {
        return mergeFrom((pb4client.ScoutPlayerInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.ScoutPlayerInfo other) {
      if (other == pb4client.ScoutPlayerInfo.getDefaultInstance()) return this;
      if (other.hasPlayerInfo()) {
        mergePlayerInfo(other.getPlayerInfo());
      }
      if (other.hasLaridState()) {
        setLaridState(other.getLaridState());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasPlayerInfo()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.ScoutPlayerInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.ScoutPlayerInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private pb4client.FightPlayerInfo playerInfo_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.FightPlayerInfo, pb4client.FightPlayerInfo.Builder, pb4client.FightPlayerInfoOrBuilder> playerInfoBuilder_;
    /**
     * <pre>
     * 玩家(集结手)信息
     * </pre>
     *
     * <code>required .client2server.FightPlayerInfo playerInfo = 1;</code>
     */
    public boolean hasPlayerInfo() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 玩家(集结手)信息
     * </pre>
     *
     * <code>required .client2server.FightPlayerInfo playerInfo = 1;</code>
     */
    public pb4client.FightPlayerInfo getPlayerInfo() {
      if (playerInfoBuilder_ == null) {
        return playerInfo_ == null ? pb4client.FightPlayerInfo.getDefaultInstance() : playerInfo_;
      } else {
        return playerInfoBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * 玩家(集结手)信息
     * </pre>
     *
     * <code>required .client2server.FightPlayerInfo playerInfo = 1;</code>
     */
    public Builder setPlayerInfo(pb4client.FightPlayerInfo value) {
      if (playerInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        playerInfo_ = value;
        onChanged();
      } else {
        playerInfoBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     * 玩家(集结手)信息
     * </pre>
     *
     * <code>required .client2server.FightPlayerInfo playerInfo = 1;</code>
     */
    public Builder setPlayerInfo(
        pb4client.FightPlayerInfo.Builder builderForValue) {
      if (playerInfoBuilder_ == null) {
        playerInfo_ = builderForValue.build();
        onChanged();
      } else {
        playerInfoBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     * 玩家(集结手)信息
     * </pre>
     *
     * <code>required .client2server.FightPlayerInfo playerInfo = 1;</code>
     */
    public Builder mergePlayerInfo(pb4client.FightPlayerInfo value) {
      if (playerInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001) &&
            playerInfo_ != null &&
            playerInfo_ != pb4client.FightPlayerInfo.getDefaultInstance()) {
          playerInfo_ =
            pb4client.FightPlayerInfo.newBuilder(playerInfo_).mergeFrom(value).buildPartial();
        } else {
          playerInfo_ = value;
        }
        onChanged();
      } else {
        playerInfoBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     * 玩家(集结手)信息
     * </pre>
     *
     * <code>required .client2server.FightPlayerInfo playerInfo = 1;</code>
     */
    public Builder clearPlayerInfo() {
      if (playerInfoBuilder_ == null) {
        playerInfo_ = null;
        onChanged();
      } else {
        playerInfoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }
    /**
     * <pre>
     * 玩家(集结手)信息
     * </pre>
     *
     * <code>required .client2server.FightPlayerInfo playerInfo = 1;</code>
     */
    public pb4client.FightPlayerInfo.Builder getPlayerInfoBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getPlayerInfoFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * 玩家(集结手)信息
     * </pre>
     *
     * <code>required .client2server.FightPlayerInfo playerInfo = 1;</code>
     */
    public pb4client.FightPlayerInfoOrBuilder getPlayerInfoOrBuilder() {
      if (playerInfoBuilder_ != null) {
        return playerInfoBuilder_.getMessageOrBuilder();
      } else {
        return playerInfo_ == null ?
            pb4client.FightPlayerInfo.getDefaultInstance() : playerInfo_;
      }
    }
    /**
     * <pre>
     * 玩家(集结手)信息
     * </pre>
     *
     * <code>required .client2server.FightPlayerInfo playerInfo = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.FightPlayerInfo, pb4client.FightPlayerInfo.Builder, pb4client.FightPlayerInfoOrBuilder> 
        getPlayerInfoFieldBuilder() {
      if (playerInfoBuilder_ == null) {
        playerInfoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.FightPlayerInfo, pb4client.FightPlayerInfo.Builder, pb4client.FightPlayerInfoOrBuilder>(
                getPlayerInfo(),
                getParentForChildren(),
                isClean());
        playerInfo_ = null;
      }
      return playerInfoBuilder_;
    }

    private int laridState_ ;
    /**
     * <pre>
     * 领主状态 0-不明 1-行军中 2-被抓 3-藏兵 4-城内集结 5-死亡 6-城内
     * </pre>
     *
     * <code>optional int32 laridState = 2;</code>
     */
    public boolean hasLaridState() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 领主状态 0-不明 1-行军中 2-被抓 3-藏兵 4-城内集结 5-死亡 6-城内
     * </pre>
     *
     * <code>optional int32 laridState = 2;</code>
     */
    public int getLaridState() {
      return laridState_;
    }
    /**
     * <pre>
     * 领主状态 0-不明 1-行军中 2-被抓 3-藏兵 4-城内集结 5-死亡 6-城内
     * </pre>
     *
     * <code>optional int32 laridState = 2;</code>
     */
    public Builder setLaridState(int value) {
      bitField0_ |= 0x00000002;
      laridState_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 领主状态 0-不明 1-行军中 2-被抓 3-藏兵 4-城内集结 5-死亡 6-城内
     * </pre>
     *
     * <code>optional int32 laridState = 2;</code>
     */
    public Builder clearLaridState() {
      bitField0_ = (bitField0_ & ~0x00000002);
      laridState_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.ScoutPlayerInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.ScoutPlayerInfo)
  private static final pb4client.ScoutPlayerInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.ScoutPlayerInfo();
  }

  public static pb4client.ScoutPlayerInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ScoutPlayerInfo>
      PARSER = new com.google.protobuf.AbstractParser<ScoutPlayerInfo>() {
    public ScoutPlayerInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ScoutPlayerInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ScoutPlayerInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ScoutPlayerInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.ScoutPlayerInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

