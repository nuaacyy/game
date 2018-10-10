// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.OpenAllianceCompetitionMainAskRt}
 */
public  final class OpenAllianceCompetitionMainAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.OpenAllianceCompetitionMainAskRt)
    OpenAllianceCompetitionMainAskRtOrBuilder {
  // Use OpenAllianceCompetitionMainAskRt.newBuilder() to construct.
  private OpenAllianceCompetitionMainAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OpenAllianceCompetitionMainAskRt() {
    rt_ = 0;
    nowScore_ = 0;
    joinPlayerNum_ = 0;
    rewardTime_ = 0;
    quests_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private OpenAllianceCompetitionMainAskRt(
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

            rt_ = input.readInt32();
            break;
          }
          case 16: {

            nowScore_ = input.readInt32();
            break;
          }
          case 24: {

            joinPlayerNum_ = input.readInt32();
            break;
          }
          case 32: {

            rewardTime_ = input.readInt32();
            break;
          }
          case 42: {
            if (!((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
              quests_ = new java.util.ArrayList<pb4server.AllianceCompetitionQuestVo>();
              mutable_bitField0_ |= 0x00000010;
            }
            quests_.add(
                input.readMessage(pb4server.AllianceCompetitionQuestVo.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
        quests_ = java.util.Collections.unmodifiableList(quests_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceCompetitionMainAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceCompetitionMainAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.OpenAllianceCompetitionMainAskRt.class, pb4server.OpenAllianceCompetitionMainAskRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int NOWSCORE_FIELD_NUMBER = 2;
  private int nowScore_;
  /**
   * <pre>
   * 当前积分
   * </pre>
   *
   * <code>int32 nowScore = 2;</code>
   */
  public int getNowScore() {
    return nowScore_;
  }

  public static final int JOINPLAYERNUM_FIELD_NUMBER = 3;
  private int joinPlayerNum_;
  /**
   * <pre>
   * 参与人数
   * </pre>
   *
   * <code>int32 joinPlayerNum = 3;</code>
   */
  public int getJoinPlayerNum() {
    return joinPlayerNum_;
  }

  public static final int REWARDTIME_FIELD_NUMBER = 4;
  private int rewardTime_;
  /**
   * <pre>
   * 领奖时间
   * </pre>
   *
   * <code>int32 rewardTime = 4;</code>
   */
  public int getRewardTime() {
    return rewardTime_;
  }

  public static final int QUESTS_FIELD_NUMBER = 5;
  private java.util.List<pb4server.AllianceCompetitionQuestVo> quests_;
  /**
   * <pre>
   * 联盟总动员任务信息
   * </pre>
   *
   * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
   */
  public java.util.List<pb4server.AllianceCompetitionQuestVo> getQuestsList() {
    return quests_;
  }
  /**
   * <pre>
   * 联盟总动员任务信息
   * </pre>
   *
   * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
   */
  public java.util.List<? extends pb4server.AllianceCompetitionQuestVoOrBuilder> 
      getQuestsOrBuilderList() {
    return quests_;
  }
  /**
   * <pre>
   * 联盟总动员任务信息
   * </pre>
   *
   * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
   */
  public int getQuestsCount() {
    return quests_.size();
  }
  /**
   * <pre>
   * 联盟总动员任务信息
   * </pre>
   *
   * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
   */
  public pb4server.AllianceCompetitionQuestVo getQuests(int index) {
    return quests_.get(index);
  }
  /**
   * <pre>
   * 联盟总动员任务信息
   * </pre>
   *
   * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
   */
  public pb4server.AllianceCompetitionQuestVoOrBuilder getQuestsOrBuilder(
      int index) {
    return quests_.get(index);
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
    if (rt_ != 0) {
      output.writeInt32(1, rt_);
    }
    if (nowScore_ != 0) {
      output.writeInt32(2, nowScore_);
    }
    if (joinPlayerNum_ != 0) {
      output.writeInt32(3, joinPlayerNum_);
    }
    if (rewardTime_ != 0) {
      output.writeInt32(4, rewardTime_);
    }
    for (int i = 0; i < quests_.size(); i++) {
      output.writeMessage(5, quests_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (rt_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rt_);
    }
    if (nowScore_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, nowScore_);
    }
    if (joinPlayerNum_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, joinPlayerNum_);
    }
    if (rewardTime_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, rewardTime_);
    }
    for (int i = 0; i < quests_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, quests_.get(i));
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
    if (!(obj instanceof pb4server.OpenAllianceCompetitionMainAskRt)) {
      return super.equals(obj);
    }
    pb4server.OpenAllianceCompetitionMainAskRt other = (pb4server.OpenAllianceCompetitionMainAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && (getNowScore()
        == other.getNowScore());
    result = result && (getJoinPlayerNum()
        == other.getJoinPlayerNum());
    result = result && (getRewardTime()
        == other.getRewardTime());
    result = result && getQuestsList()
        .equals(other.getQuestsList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RT_FIELD_NUMBER;
    hash = (53 * hash) + getRt();
    hash = (37 * hash) + NOWSCORE_FIELD_NUMBER;
    hash = (53 * hash) + getNowScore();
    hash = (37 * hash) + JOINPLAYERNUM_FIELD_NUMBER;
    hash = (53 * hash) + getJoinPlayerNum();
    hash = (37 * hash) + REWARDTIME_FIELD_NUMBER;
    hash = (53 * hash) + getRewardTime();
    if (getQuestsCount() > 0) {
      hash = (37 * hash) + QUESTS_FIELD_NUMBER;
      hash = (53 * hash) + getQuestsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.OpenAllianceCompetitionMainAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenAllianceCompetitionMainAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenAllianceCompetitionMainAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenAllianceCompetitionMainAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenAllianceCompetitionMainAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenAllianceCompetitionMainAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenAllianceCompetitionMainAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.OpenAllianceCompetitionMainAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.OpenAllianceCompetitionMainAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.OpenAllianceCompetitionMainAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.OpenAllianceCompetitionMainAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.OpenAllianceCompetitionMainAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.OpenAllianceCompetitionMainAskRt prototype) {
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
   * Protobuf type {@code pb4server.OpenAllianceCompetitionMainAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.OpenAllianceCompetitionMainAskRt)
      pb4server.OpenAllianceCompetitionMainAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceCompetitionMainAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceCompetitionMainAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.OpenAllianceCompetitionMainAskRt.class, pb4server.OpenAllianceCompetitionMainAskRt.Builder.class);
    }

    // Construct using pb4server.OpenAllianceCompetitionMainAskRt.newBuilder()
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
        getQuestsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;

      nowScore_ = 0;

      joinPlayerNum_ = 0;

      rewardTime_ = 0;

      if (questsBuilder_ == null) {
        quests_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000010);
      } else {
        questsBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceCompetitionMainAskRt_descriptor;
    }

    public pb4server.OpenAllianceCompetitionMainAskRt getDefaultInstanceForType() {
      return pb4server.OpenAllianceCompetitionMainAskRt.getDefaultInstance();
    }

    public pb4server.OpenAllianceCompetitionMainAskRt build() {
      pb4server.OpenAllianceCompetitionMainAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.OpenAllianceCompetitionMainAskRt buildPartial() {
      pb4server.OpenAllianceCompetitionMainAskRt result = new pb4server.OpenAllianceCompetitionMainAskRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.rt_ = rt_;
      result.nowScore_ = nowScore_;
      result.joinPlayerNum_ = joinPlayerNum_;
      result.rewardTime_ = rewardTime_;
      if (questsBuilder_ == null) {
        if (((bitField0_ & 0x00000010) == 0x00000010)) {
          quests_ = java.util.Collections.unmodifiableList(quests_);
          bitField0_ = (bitField0_ & ~0x00000010);
        }
        result.quests_ = quests_;
      } else {
        result.quests_ = questsBuilder_.build();
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
      if (other instanceof pb4server.OpenAllianceCompetitionMainAskRt) {
        return mergeFrom((pb4server.OpenAllianceCompetitionMainAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.OpenAllianceCompetitionMainAskRt other) {
      if (other == pb4server.OpenAllianceCompetitionMainAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (other.getNowScore() != 0) {
        setNowScore(other.getNowScore());
      }
      if (other.getJoinPlayerNum() != 0) {
        setJoinPlayerNum(other.getJoinPlayerNum());
      }
      if (other.getRewardTime() != 0) {
        setRewardTime(other.getRewardTime());
      }
      if (questsBuilder_ == null) {
        if (!other.quests_.isEmpty()) {
          if (quests_.isEmpty()) {
            quests_ = other.quests_;
            bitField0_ = (bitField0_ & ~0x00000010);
          } else {
            ensureQuestsIsMutable();
            quests_.addAll(other.quests_);
          }
          onChanged();
        }
      } else {
        if (!other.quests_.isEmpty()) {
          if (questsBuilder_.isEmpty()) {
            questsBuilder_.dispose();
            questsBuilder_ = null;
            quests_ = other.quests_;
            bitField0_ = (bitField0_ & ~0x00000010);
            questsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getQuestsFieldBuilder() : null;
          } else {
            questsBuilder_.addAllMessages(other.quests_);
          }
        }
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
      pb4server.OpenAllianceCompetitionMainAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.OpenAllianceCompetitionMainAskRt) e.getUnfinishedMessage();
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
     * <code>int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <code>int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 rt = 1;</code>
     */
    public Builder clearRt() {
      
      rt_ = 0;
      onChanged();
      return this;
    }

    private int nowScore_ ;
    /**
     * <pre>
     * 当前积分
     * </pre>
     *
     * <code>int32 nowScore = 2;</code>
     */
    public int getNowScore() {
      return nowScore_;
    }
    /**
     * <pre>
     * 当前积分
     * </pre>
     *
     * <code>int32 nowScore = 2;</code>
     */
    public Builder setNowScore(int value) {
      
      nowScore_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 当前积分
     * </pre>
     *
     * <code>int32 nowScore = 2;</code>
     */
    public Builder clearNowScore() {
      
      nowScore_ = 0;
      onChanged();
      return this;
    }

    private int joinPlayerNum_ ;
    /**
     * <pre>
     * 参与人数
     * </pre>
     *
     * <code>int32 joinPlayerNum = 3;</code>
     */
    public int getJoinPlayerNum() {
      return joinPlayerNum_;
    }
    /**
     * <pre>
     * 参与人数
     * </pre>
     *
     * <code>int32 joinPlayerNum = 3;</code>
     */
    public Builder setJoinPlayerNum(int value) {
      
      joinPlayerNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 参与人数
     * </pre>
     *
     * <code>int32 joinPlayerNum = 3;</code>
     */
    public Builder clearJoinPlayerNum() {
      
      joinPlayerNum_ = 0;
      onChanged();
      return this;
    }

    private int rewardTime_ ;
    /**
     * <pre>
     * 领奖时间
     * </pre>
     *
     * <code>int32 rewardTime = 4;</code>
     */
    public int getRewardTime() {
      return rewardTime_;
    }
    /**
     * <pre>
     * 领奖时间
     * </pre>
     *
     * <code>int32 rewardTime = 4;</code>
     */
    public Builder setRewardTime(int value) {
      
      rewardTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 领奖时间
     * </pre>
     *
     * <code>int32 rewardTime = 4;</code>
     */
    public Builder clearRewardTime() {
      
      rewardTime_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4server.AllianceCompetitionQuestVo> quests_ =
      java.util.Collections.emptyList();
    private void ensureQuestsIsMutable() {
      if (!((bitField0_ & 0x00000010) == 0x00000010)) {
        quests_ = new java.util.ArrayList<pb4server.AllianceCompetitionQuestVo>(quests_);
        bitField0_ |= 0x00000010;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AllianceCompetitionQuestVo, pb4server.AllianceCompetitionQuestVo.Builder, pb4server.AllianceCompetitionQuestVoOrBuilder> questsBuilder_;

    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public java.util.List<pb4server.AllianceCompetitionQuestVo> getQuestsList() {
      if (questsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(quests_);
      } else {
        return questsBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public int getQuestsCount() {
      if (questsBuilder_ == null) {
        return quests_.size();
      } else {
        return questsBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public pb4server.AllianceCompetitionQuestVo getQuests(int index) {
      if (questsBuilder_ == null) {
        return quests_.get(index);
      } else {
        return questsBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public Builder setQuests(
        int index, pb4server.AllianceCompetitionQuestVo value) {
      if (questsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQuestsIsMutable();
        quests_.set(index, value);
        onChanged();
      } else {
        questsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public Builder setQuests(
        int index, pb4server.AllianceCompetitionQuestVo.Builder builderForValue) {
      if (questsBuilder_ == null) {
        ensureQuestsIsMutable();
        quests_.set(index, builderForValue.build());
        onChanged();
      } else {
        questsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public Builder addQuests(pb4server.AllianceCompetitionQuestVo value) {
      if (questsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQuestsIsMutable();
        quests_.add(value);
        onChanged();
      } else {
        questsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public Builder addQuests(
        int index, pb4server.AllianceCompetitionQuestVo value) {
      if (questsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQuestsIsMutable();
        quests_.add(index, value);
        onChanged();
      } else {
        questsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public Builder addQuests(
        pb4server.AllianceCompetitionQuestVo.Builder builderForValue) {
      if (questsBuilder_ == null) {
        ensureQuestsIsMutable();
        quests_.add(builderForValue.build());
        onChanged();
      } else {
        questsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public Builder addQuests(
        int index, pb4server.AllianceCompetitionQuestVo.Builder builderForValue) {
      if (questsBuilder_ == null) {
        ensureQuestsIsMutable();
        quests_.add(index, builderForValue.build());
        onChanged();
      } else {
        questsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public Builder addAllQuests(
        java.lang.Iterable<? extends pb4server.AllianceCompetitionQuestVo> values) {
      if (questsBuilder_ == null) {
        ensureQuestsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, quests_);
        onChanged();
      } else {
        questsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public Builder clearQuests() {
      if (questsBuilder_ == null) {
        quests_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000010);
        onChanged();
      } else {
        questsBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public Builder removeQuests(int index) {
      if (questsBuilder_ == null) {
        ensureQuestsIsMutable();
        quests_.remove(index);
        onChanged();
      } else {
        questsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public pb4server.AllianceCompetitionQuestVo.Builder getQuestsBuilder(
        int index) {
      return getQuestsFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public pb4server.AllianceCompetitionQuestVoOrBuilder getQuestsOrBuilder(
        int index) {
      if (questsBuilder_ == null) {
        return quests_.get(index);  } else {
        return questsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public java.util.List<? extends pb4server.AllianceCompetitionQuestVoOrBuilder> 
         getQuestsOrBuilderList() {
      if (questsBuilder_ != null) {
        return questsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(quests_);
      }
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public pb4server.AllianceCompetitionQuestVo.Builder addQuestsBuilder() {
      return getQuestsFieldBuilder().addBuilder(
          pb4server.AllianceCompetitionQuestVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public pb4server.AllianceCompetitionQuestVo.Builder addQuestsBuilder(
        int index) {
      return getQuestsFieldBuilder().addBuilder(
          index, pb4server.AllianceCompetitionQuestVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 联盟总动员任务信息
     * </pre>
     *
     * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
     */
    public java.util.List<pb4server.AllianceCompetitionQuestVo.Builder> 
         getQuestsBuilderList() {
      return getQuestsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AllianceCompetitionQuestVo, pb4server.AllianceCompetitionQuestVo.Builder, pb4server.AllianceCompetitionQuestVoOrBuilder> 
        getQuestsFieldBuilder() {
      if (questsBuilder_ == null) {
        questsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.AllianceCompetitionQuestVo, pb4server.AllianceCompetitionQuestVo.Builder, pb4server.AllianceCompetitionQuestVoOrBuilder>(
                quests_,
                ((bitField0_ & 0x00000010) == 0x00000010),
                getParentForChildren(),
                isClean());
        quests_ = null;
      }
      return questsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.OpenAllianceCompetitionMainAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.OpenAllianceCompetitionMainAskRt)
  private static final pb4server.OpenAllianceCompetitionMainAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.OpenAllianceCompetitionMainAskRt();
  }

  public static pb4server.OpenAllianceCompetitionMainAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OpenAllianceCompetitionMainAskRt>
      PARSER = new com.google.protobuf.AbstractParser<OpenAllianceCompetitionMainAskRt>() {
    public OpenAllianceCompetitionMainAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OpenAllianceCompetitionMainAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OpenAllianceCompetitionMainAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OpenAllianceCompetitionMainAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.OpenAllianceCompetitionMainAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

