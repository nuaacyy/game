// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 * Public-&gt;Game
 * 推送给盟友联盟挑战信息发生变化
 * </pre>
 *
 * Protobuf type {@code pb4server.AllianceActivityChangeNotic2GTell}
 */
public  final class AllianceActivityChangeNotic2GTell extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AllianceActivityChangeNotic2GTell)
    AllianceActivityChangeNotic2GTellOrBuilder {
  // Use AllianceActivityChangeNotic2GTell.newBuilder() to construct.
  private AllianceActivityChangeNotic2GTell(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceActivityChangeNotic2GTell() {
    playerIds_ = java.util.Collections.emptyList();
    activityId_ = 0;
    score_ = 0;
    rank_ = 0;
    isActivityOver_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllianceActivityChangeNotic2GTell(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              playerIds_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000001;
            }
            playerIds_.add(input.readInt64());
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001) && input.getBytesUntilLimit() > 0) {
              playerIds_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              playerIds_.add(input.readInt64());
            }
            input.popLimit(limit);
            break;
          }
          case 16: {

            activityId_ = input.readInt32();
            break;
          }
          case 24: {

            score_ = input.readInt32();
            break;
          }
          case 32: {

            rank_ = input.readInt32();
            break;
          }
          case 40: {

            isActivityOver_ = input.readInt32();
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        playerIds_ = java.util.Collections.unmodifiableList(playerIds_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalWkt.internal_static_pb4server_AllianceActivityChangeNotic2GTell_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_AllianceActivityChangeNotic2GTell_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AllianceActivityChangeNotic2GTell.class, pb4server.AllianceActivityChangeNotic2GTell.Builder.class);
  }

  private int bitField0_;
  public static final int PLAYERIDS_FIELD_NUMBER = 1;
  private java.util.List<java.lang.Long> playerIds_;
  /**
   * <code>repeated int64 playerIds = 1;</code>
   */
  public java.util.List<java.lang.Long>
      getPlayerIdsList() {
    return playerIds_;
  }
  /**
   * <code>repeated int64 playerIds = 1;</code>
   */
  public int getPlayerIdsCount() {
    return playerIds_.size();
  }
  /**
   * <code>repeated int64 playerIds = 1;</code>
   */
  public long getPlayerIds(int index) {
    return playerIds_.get(index);
  }
  private int playerIdsMemoizedSerializedSize = -1;

  public static final int ACTIVITYID_FIELD_NUMBER = 2;
  private int activityId_;
  /**
   * <code>int32 activityId = 2;</code>
   */
  public int getActivityId() {
    return activityId_;
  }

  public static final int SCORE_FIELD_NUMBER = 3;
  private int score_;
  /**
   * <code>int32 score = 3;</code>
   */
  public int getScore() {
    return score_;
  }

  public static final int RANK_FIELD_NUMBER = 4;
  private int rank_;
  /**
   * <code>int32 rank = 4;</code>
   */
  public int getRank() {
    return rank_;
  }

  public static final int ISACTIVITYOVER_FIELD_NUMBER = 5;
  private int isActivityOver_;
  /**
   * <code>int32 isActivityOver = 5;</code>
   */
  public int getIsActivityOver() {
    return isActivityOver_;
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
    getSerializedSize();
    if (getPlayerIdsList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(playerIdsMemoizedSerializedSize);
    }
    for (int i = 0; i < playerIds_.size(); i++) {
      output.writeInt64NoTag(playerIds_.get(i));
    }
    if (activityId_ != 0) {
      output.writeInt32(2, activityId_);
    }
    if (score_ != 0) {
      output.writeInt32(3, score_);
    }
    if (rank_ != 0) {
      output.writeInt32(4, rank_);
    }
    if (isActivityOver_ != 0) {
      output.writeInt32(5, isActivityOver_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < playerIds_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt64SizeNoTag(playerIds_.get(i));
      }
      size += dataSize;
      if (!getPlayerIdsList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      playerIdsMemoizedSerializedSize = dataSize;
    }
    if (activityId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, activityId_);
    }
    if (score_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, score_);
    }
    if (rank_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, rank_);
    }
    if (isActivityOver_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, isActivityOver_);
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
    if (!(obj instanceof pb4server.AllianceActivityChangeNotic2GTell)) {
      return super.equals(obj);
    }
    pb4server.AllianceActivityChangeNotic2GTell other = (pb4server.AllianceActivityChangeNotic2GTell) obj;

    boolean result = true;
    result = result && getPlayerIdsList()
        .equals(other.getPlayerIdsList());
    result = result && (getActivityId()
        == other.getActivityId());
    result = result && (getScore()
        == other.getScore());
    result = result && (getRank()
        == other.getRank());
    result = result && (getIsActivityOver()
        == other.getIsActivityOver());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getPlayerIdsCount() > 0) {
      hash = (37 * hash) + PLAYERIDS_FIELD_NUMBER;
      hash = (53 * hash) + getPlayerIdsList().hashCode();
    }
    hash = (37 * hash) + ACTIVITYID_FIELD_NUMBER;
    hash = (53 * hash) + getActivityId();
    hash = (37 * hash) + SCORE_FIELD_NUMBER;
    hash = (53 * hash) + getScore();
    hash = (37 * hash) + RANK_FIELD_NUMBER;
    hash = (53 * hash) + getRank();
    hash = (37 * hash) + ISACTIVITYOVER_FIELD_NUMBER;
    hash = (53 * hash) + getIsActivityOver();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.AllianceActivityChangeNotic2GTell parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceActivityChangeNotic2GTell parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceActivityChangeNotic2GTell parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceActivityChangeNotic2GTell parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceActivityChangeNotic2GTell parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceActivityChangeNotic2GTell parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceActivityChangeNotic2GTell parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceActivityChangeNotic2GTell parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceActivityChangeNotic2GTell parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AllianceActivityChangeNotic2GTell parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceActivityChangeNotic2GTell parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceActivityChangeNotic2GTell parseFrom(
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
  public static Builder newBuilder(pb4server.AllianceActivityChangeNotic2GTell prototype) {
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
   * Public-&gt;Game
   * 推送给盟友联盟挑战信息发生变化
   * </pre>
   *
   * Protobuf type {@code pb4server.AllianceActivityChangeNotic2GTell}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AllianceActivityChangeNotic2GTell)
      pb4server.AllianceActivityChangeNotic2GTellOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_AllianceActivityChangeNotic2GTell_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_AllianceActivityChangeNotic2GTell_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AllianceActivityChangeNotic2GTell.class, pb4server.AllianceActivityChangeNotic2GTell.Builder.class);
    }

    // Construct using pb4server.AllianceActivityChangeNotic2GTell.newBuilder()
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
      playerIds_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      activityId_ = 0;

      score_ = 0;

      rank_ = 0;

      isActivityOver_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_AllianceActivityChangeNotic2GTell_descriptor;
    }

    public pb4server.AllianceActivityChangeNotic2GTell getDefaultInstanceForType() {
      return pb4server.AllianceActivityChangeNotic2GTell.getDefaultInstance();
    }

    public pb4server.AllianceActivityChangeNotic2GTell build() {
      pb4server.AllianceActivityChangeNotic2GTell result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AllianceActivityChangeNotic2GTell buildPartial() {
      pb4server.AllianceActivityChangeNotic2GTell result = new pb4server.AllianceActivityChangeNotic2GTell(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        playerIds_ = java.util.Collections.unmodifiableList(playerIds_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.playerIds_ = playerIds_;
      result.activityId_ = activityId_;
      result.score_ = score_;
      result.rank_ = rank_;
      result.isActivityOver_ = isActivityOver_;
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
      if (other instanceof pb4server.AllianceActivityChangeNotic2GTell) {
        return mergeFrom((pb4server.AllianceActivityChangeNotic2GTell)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AllianceActivityChangeNotic2GTell other) {
      if (other == pb4server.AllianceActivityChangeNotic2GTell.getDefaultInstance()) return this;
      if (!other.playerIds_.isEmpty()) {
        if (playerIds_.isEmpty()) {
          playerIds_ = other.playerIds_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensurePlayerIdsIsMutable();
          playerIds_.addAll(other.playerIds_);
        }
        onChanged();
      }
      if (other.getActivityId() != 0) {
        setActivityId(other.getActivityId());
      }
      if (other.getScore() != 0) {
        setScore(other.getScore());
      }
      if (other.getRank() != 0) {
        setRank(other.getRank());
      }
      if (other.getIsActivityOver() != 0) {
        setIsActivityOver(other.getIsActivityOver());
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
      pb4server.AllianceActivityChangeNotic2GTell parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AllianceActivityChangeNotic2GTell) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<java.lang.Long> playerIds_ = java.util.Collections.emptyList();
    private void ensurePlayerIdsIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        playerIds_ = new java.util.ArrayList<java.lang.Long>(playerIds_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated int64 playerIds = 1;</code>
     */
    public java.util.List<java.lang.Long>
        getPlayerIdsList() {
      return java.util.Collections.unmodifiableList(playerIds_);
    }
    /**
     * <code>repeated int64 playerIds = 1;</code>
     */
    public int getPlayerIdsCount() {
      return playerIds_.size();
    }
    /**
     * <code>repeated int64 playerIds = 1;</code>
     */
    public long getPlayerIds(int index) {
      return playerIds_.get(index);
    }
    /**
     * <code>repeated int64 playerIds = 1;</code>
     */
    public Builder setPlayerIds(
        int index, long value) {
      ensurePlayerIdsIsMutable();
      playerIds_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 playerIds = 1;</code>
     */
    public Builder addPlayerIds(long value) {
      ensurePlayerIdsIsMutable();
      playerIds_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 playerIds = 1;</code>
     */
    public Builder addAllPlayerIds(
        java.lang.Iterable<? extends java.lang.Long> values) {
      ensurePlayerIdsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, playerIds_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 playerIds = 1;</code>
     */
    public Builder clearPlayerIds() {
      playerIds_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }

    private int activityId_ ;
    /**
     * <code>int32 activityId = 2;</code>
     */
    public int getActivityId() {
      return activityId_;
    }
    /**
     * <code>int32 activityId = 2;</code>
     */
    public Builder setActivityId(int value) {
      
      activityId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 activityId = 2;</code>
     */
    public Builder clearActivityId() {
      
      activityId_ = 0;
      onChanged();
      return this;
    }

    private int score_ ;
    /**
     * <code>int32 score = 3;</code>
     */
    public int getScore() {
      return score_;
    }
    /**
     * <code>int32 score = 3;</code>
     */
    public Builder setScore(int value) {
      
      score_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 score = 3;</code>
     */
    public Builder clearScore() {
      
      score_ = 0;
      onChanged();
      return this;
    }

    private int rank_ ;
    /**
     * <code>int32 rank = 4;</code>
     */
    public int getRank() {
      return rank_;
    }
    /**
     * <code>int32 rank = 4;</code>
     */
    public Builder setRank(int value) {
      
      rank_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 rank = 4;</code>
     */
    public Builder clearRank() {
      
      rank_ = 0;
      onChanged();
      return this;
    }

    private int isActivityOver_ ;
    /**
     * <code>int32 isActivityOver = 5;</code>
     */
    public int getIsActivityOver() {
      return isActivityOver_;
    }
    /**
     * <code>int32 isActivityOver = 5;</code>
     */
    public Builder setIsActivityOver(int value) {
      
      isActivityOver_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 isActivityOver = 5;</code>
     */
    public Builder clearIsActivityOver() {
      
      isActivityOver_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.AllianceActivityChangeNotic2GTell)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AllianceActivityChangeNotic2GTell)
  private static final pb4server.AllianceActivityChangeNotic2GTell DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AllianceActivityChangeNotic2GTell();
  }

  public static pb4server.AllianceActivityChangeNotic2GTell getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllianceActivityChangeNotic2GTell>
      PARSER = new com.google.protobuf.AbstractParser<AllianceActivityChangeNotic2GTell>() {
    public AllianceActivityChangeNotic2GTell parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceActivityChangeNotic2GTell(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceActivityChangeNotic2GTell> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceActivityChangeNotic2GTell> getParserForType() {
    return PARSER;
  }

  public pb4server.AllianceActivityChangeNotic2GTell getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

