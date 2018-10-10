// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.ReceiveAchievementRewardRt}
 */
public  final class ReceiveAchievementRewardRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.ReceiveAchievementRewardRt)
    ReceiveAchievementRewardRtOrBuilder {
  // Use ReceiveAchievementRewardRt.newBuilder() to construct.
  private ReceiveAchievementRewardRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ReceiveAchievementRewardRt() {
    rt_ = 0;
    achieveInfo_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ReceiveAchievementRewardRt(
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
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              achieveInfo_ = new java.util.ArrayList<pb4client.Achievement>();
              mutable_bitField0_ |= 0x00000002;
            }
            achieveInfo_.add(
                input.readMessage(pb4client.Achievement.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        achieveInfo_ = java.util.Collections.unmodifiableList(achieveInfo_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_ReceiveAchievementRewardRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_ReceiveAchievementRewardRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.ReceiveAchievementRewardRt.class, pb4client.ReceiveAchievementRewardRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int ACHIEVEINFO_FIELD_NUMBER = 2;
  private java.util.List<pb4client.Achievement> achieveInfo_;
  /**
   * <pre>
   *下一阶成就
   * </pre>
   *
   * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
   */
  public java.util.List<pb4client.Achievement> getAchieveInfoList() {
    return achieveInfo_;
  }
  /**
   * <pre>
   *下一阶成就
   * </pre>
   *
   * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
   */
  public java.util.List<? extends pb4client.AchievementOrBuilder> 
      getAchieveInfoOrBuilderList() {
    return achieveInfo_;
  }
  /**
   * <pre>
   *下一阶成就
   * </pre>
   *
   * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
   */
  public int getAchieveInfoCount() {
    return achieveInfo_.size();
  }
  /**
   * <pre>
   *下一阶成就
   * </pre>
   *
   * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
   */
  public pb4client.Achievement getAchieveInfo(int index) {
    return achieveInfo_.get(index);
  }
  /**
   * <pre>
   *下一阶成就
   * </pre>
   *
   * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
   */
  public pb4client.AchievementOrBuilder getAchieveInfoOrBuilder(
      int index) {
    return achieveInfo_.get(index);
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
    for (int i = 0; i < getAchieveInfoCount(); i++) {
      if (!getAchieveInfo(i).isInitialized()) {
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
    for (int i = 0; i < achieveInfo_.size(); i++) {
      output.writeMessage(2, achieveInfo_.get(i));
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
    for (int i = 0; i < achieveInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, achieveInfo_.get(i));
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
    if (!(obj instanceof pb4client.ReceiveAchievementRewardRt)) {
      return super.equals(obj);
    }
    pb4client.ReceiveAchievementRewardRt other = (pb4client.ReceiveAchievementRewardRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getAchieveInfoList()
        .equals(other.getAchieveInfoList());
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
    if (getAchieveInfoCount() > 0) {
      hash = (37 * hash) + ACHIEVEINFO_FIELD_NUMBER;
      hash = (53 * hash) + getAchieveInfoList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.ReceiveAchievementRewardRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ReceiveAchievementRewardRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ReceiveAchievementRewardRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ReceiveAchievementRewardRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ReceiveAchievementRewardRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ReceiveAchievementRewardRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ReceiveAchievementRewardRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ReceiveAchievementRewardRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ReceiveAchievementRewardRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.ReceiveAchievementRewardRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ReceiveAchievementRewardRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ReceiveAchievementRewardRt parseFrom(
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
  public static Builder newBuilder(pb4client.ReceiveAchievementRewardRt prototype) {
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
   * Protobuf type {@code client2server.ReceiveAchievementRewardRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.ReceiveAchievementRewardRt)
      pb4client.ReceiveAchievementRewardRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_ReceiveAchievementRewardRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_ReceiveAchievementRewardRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.ReceiveAchievementRewardRt.class, pb4client.ReceiveAchievementRewardRt.Builder.class);
    }

    // Construct using pb4client.ReceiveAchievementRewardRt.newBuilder()
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
        getAchieveInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (achieveInfoBuilder_ == null) {
        achieveInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        achieveInfoBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_ReceiveAchievementRewardRt_descriptor;
    }

    public pb4client.ReceiveAchievementRewardRt getDefaultInstanceForType() {
      return pb4client.ReceiveAchievementRewardRt.getDefaultInstance();
    }

    public pb4client.ReceiveAchievementRewardRt build() {
      pb4client.ReceiveAchievementRewardRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.ReceiveAchievementRewardRt buildPartial() {
      pb4client.ReceiveAchievementRewardRt result = new pb4client.ReceiveAchievementRewardRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (achieveInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          achieveInfo_ = java.util.Collections.unmodifiableList(achieveInfo_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.achieveInfo_ = achieveInfo_;
      } else {
        result.achieveInfo_ = achieveInfoBuilder_.build();
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
      if (other instanceof pb4client.ReceiveAchievementRewardRt) {
        return mergeFrom((pb4client.ReceiveAchievementRewardRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.ReceiveAchievementRewardRt other) {
      if (other == pb4client.ReceiveAchievementRewardRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (achieveInfoBuilder_ == null) {
        if (!other.achieveInfo_.isEmpty()) {
          if (achieveInfo_.isEmpty()) {
            achieveInfo_ = other.achieveInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureAchieveInfoIsMutable();
            achieveInfo_.addAll(other.achieveInfo_);
          }
          onChanged();
        }
      } else {
        if (!other.achieveInfo_.isEmpty()) {
          if (achieveInfoBuilder_.isEmpty()) {
            achieveInfoBuilder_.dispose();
            achieveInfoBuilder_ = null;
            achieveInfo_ = other.achieveInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
            achieveInfoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getAchieveInfoFieldBuilder() : null;
          } else {
            achieveInfoBuilder_.addAllMessages(other.achieveInfo_);
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
      for (int i = 0; i < getAchieveInfoCount(); i++) {
        if (!getAchieveInfo(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.ReceiveAchievementRewardRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.ReceiveAchievementRewardRt) e.getUnfinishedMessage();
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
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.Achievement> achieveInfo_ =
      java.util.Collections.emptyList();
    private void ensureAchieveInfoIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        achieveInfo_ = new java.util.ArrayList<pb4client.Achievement>(achieveInfo_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.Achievement, pb4client.Achievement.Builder, pb4client.AchievementOrBuilder> achieveInfoBuilder_;

    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public java.util.List<pb4client.Achievement> getAchieveInfoList() {
      if (achieveInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(achieveInfo_);
      } else {
        return achieveInfoBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public int getAchieveInfoCount() {
      if (achieveInfoBuilder_ == null) {
        return achieveInfo_.size();
      } else {
        return achieveInfoBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public pb4client.Achievement getAchieveInfo(int index) {
      if (achieveInfoBuilder_ == null) {
        return achieveInfo_.get(index);
      } else {
        return achieveInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public Builder setAchieveInfo(
        int index, pb4client.Achievement value) {
      if (achieveInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAchieveInfoIsMutable();
        achieveInfo_.set(index, value);
        onChanged();
      } else {
        achieveInfoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public Builder setAchieveInfo(
        int index, pb4client.Achievement.Builder builderForValue) {
      if (achieveInfoBuilder_ == null) {
        ensureAchieveInfoIsMutable();
        achieveInfo_.set(index, builderForValue.build());
        onChanged();
      } else {
        achieveInfoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public Builder addAchieveInfo(pb4client.Achievement value) {
      if (achieveInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAchieveInfoIsMutable();
        achieveInfo_.add(value);
        onChanged();
      } else {
        achieveInfoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public Builder addAchieveInfo(
        int index, pb4client.Achievement value) {
      if (achieveInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAchieveInfoIsMutable();
        achieveInfo_.add(index, value);
        onChanged();
      } else {
        achieveInfoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public Builder addAchieveInfo(
        pb4client.Achievement.Builder builderForValue) {
      if (achieveInfoBuilder_ == null) {
        ensureAchieveInfoIsMutable();
        achieveInfo_.add(builderForValue.build());
        onChanged();
      } else {
        achieveInfoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public Builder addAchieveInfo(
        int index, pb4client.Achievement.Builder builderForValue) {
      if (achieveInfoBuilder_ == null) {
        ensureAchieveInfoIsMutable();
        achieveInfo_.add(index, builderForValue.build());
        onChanged();
      } else {
        achieveInfoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public Builder addAllAchieveInfo(
        java.lang.Iterable<? extends pb4client.Achievement> values) {
      if (achieveInfoBuilder_ == null) {
        ensureAchieveInfoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, achieveInfo_);
        onChanged();
      } else {
        achieveInfoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public Builder clearAchieveInfo() {
      if (achieveInfoBuilder_ == null) {
        achieveInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        achieveInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public Builder removeAchieveInfo(int index) {
      if (achieveInfoBuilder_ == null) {
        ensureAchieveInfoIsMutable();
        achieveInfo_.remove(index);
        onChanged();
      } else {
        achieveInfoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public pb4client.Achievement.Builder getAchieveInfoBuilder(
        int index) {
      return getAchieveInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public pb4client.AchievementOrBuilder getAchieveInfoOrBuilder(
        int index) {
      if (achieveInfoBuilder_ == null) {
        return achieveInfo_.get(index);  } else {
        return achieveInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public java.util.List<? extends pb4client.AchievementOrBuilder> 
         getAchieveInfoOrBuilderList() {
      if (achieveInfoBuilder_ != null) {
        return achieveInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(achieveInfo_);
      }
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public pb4client.Achievement.Builder addAchieveInfoBuilder() {
      return getAchieveInfoFieldBuilder().addBuilder(
          pb4client.Achievement.getDefaultInstance());
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public pb4client.Achievement.Builder addAchieveInfoBuilder(
        int index) {
      return getAchieveInfoFieldBuilder().addBuilder(
          index, pb4client.Achievement.getDefaultInstance());
    }
    /**
     * <pre>
     *下一阶成就
     * </pre>
     *
     * <code>repeated .client2server.Achievement achieveInfo = 2;</code>
     */
    public java.util.List<pb4client.Achievement.Builder> 
         getAchieveInfoBuilderList() {
      return getAchieveInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.Achievement, pb4client.Achievement.Builder, pb4client.AchievementOrBuilder> 
        getAchieveInfoFieldBuilder() {
      if (achieveInfoBuilder_ == null) {
        achieveInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.Achievement, pb4client.Achievement.Builder, pb4client.AchievementOrBuilder>(
                achieveInfo_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        achieveInfo_ = null;
      }
      return achieveInfoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.ReceiveAchievementRewardRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.ReceiveAchievementRewardRt)
  private static final pb4client.ReceiveAchievementRewardRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.ReceiveAchievementRewardRt();
  }

  public static pb4client.ReceiveAchievementRewardRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ReceiveAchievementRewardRt>
      PARSER = new com.google.protobuf.AbstractParser<ReceiveAchievementRewardRt>() {
    public ReceiveAchievementRewardRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ReceiveAchievementRewardRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ReceiveAchievementRewardRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ReceiveAchievementRewardRt> getParserForType() {
    return PARSER;
  }

  public pb4client.ReceiveAchievementRewardRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
