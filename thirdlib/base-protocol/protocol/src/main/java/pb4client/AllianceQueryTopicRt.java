// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.AllianceQueryTopicRt}
 */
public  final class AllianceQueryTopicRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AllianceQueryTopicRt)
    AllianceQueryTopicRtOrBuilder {
  // Use AllianceQueryTopicRt.newBuilder() to construct.
  private AllianceQueryTopicRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceQueryTopicRt() {
    rt_ = 0;
    topics_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllianceQueryTopicRt(
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
              topics_ = new java.util.ArrayList<pb4client.AllianceTopicInfo>();
              mutable_bitField0_ |= 0x00000002;
            }
            topics_.add(
                input.readMessage(pb4client.AllianceTopicInfo.PARSER, extensionRegistry));
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
        topics_ = java.util.Collections.unmodifiableList(topics_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryTopicRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryTopicRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AllianceQueryTopicRt.class, pb4client.AllianceQueryTopicRt.Builder.class);
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

  public static final int TOPICS_FIELD_NUMBER = 2;
  private java.util.List<pb4client.AllianceTopicInfo> topics_;
  /**
   * <pre>
   *联盟邮件的主题及回复记录（部分回复记录）
   * </pre>
   *
   * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
   */
  public java.util.List<pb4client.AllianceTopicInfo> getTopicsList() {
    return topics_;
  }
  /**
   * <pre>
   *联盟邮件的主题及回复记录（部分回复记录）
   * </pre>
   *
   * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
   */
  public java.util.List<? extends pb4client.AllianceTopicInfoOrBuilder> 
      getTopicsOrBuilderList() {
    return topics_;
  }
  /**
   * <pre>
   *联盟邮件的主题及回复记录（部分回复记录）
   * </pre>
   *
   * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
   */
  public int getTopicsCount() {
    return topics_.size();
  }
  /**
   * <pre>
   *联盟邮件的主题及回复记录（部分回复记录）
   * </pre>
   *
   * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
   */
  public pb4client.AllianceTopicInfo getTopics(int index) {
    return topics_.get(index);
  }
  /**
   * <pre>
   *联盟邮件的主题及回复记录（部分回复记录）
   * </pre>
   *
   * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
   */
  public pb4client.AllianceTopicInfoOrBuilder getTopicsOrBuilder(
      int index) {
    return topics_.get(index);
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
    for (int i = 0; i < getTopicsCount(); i++) {
      if (!getTopics(i).isInitialized()) {
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
    for (int i = 0; i < topics_.size(); i++) {
      output.writeMessage(2, topics_.get(i));
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
    for (int i = 0; i < topics_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, topics_.get(i));
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
    if (!(obj instanceof pb4client.AllianceQueryTopicRt)) {
      return super.equals(obj);
    }
    pb4client.AllianceQueryTopicRt other = (pb4client.AllianceQueryTopicRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getTopicsList()
        .equals(other.getTopicsList());
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
    if (getTopicsCount() > 0) {
      hash = (37 * hash) + TOPICS_FIELD_NUMBER;
      hash = (53 * hash) + getTopicsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AllianceQueryTopicRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceQueryTopicRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceQueryTopicRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceQueryTopicRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceQueryTopicRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceQueryTopicRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceQueryTopicRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceQueryTopicRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceQueryTopicRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AllianceQueryTopicRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceQueryTopicRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceQueryTopicRt parseFrom(
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
  public static Builder newBuilder(pb4client.AllianceQueryTopicRt prototype) {
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
   * Protobuf type {@code client2server.AllianceQueryTopicRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AllianceQueryTopicRt)
      pb4client.AllianceQueryTopicRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryTopicRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryTopicRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AllianceQueryTopicRt.class, pb4client.AllianceQueryTopicRt.Builder.class);
    }

    // Construct using pb4client.AllianceQueryTopicRt.newBuilder()
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
        getTopicsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (topicsBuilder_ == null) {
        topics_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        topicsBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryTopicRt_descriptor;
    }

    public pb4client.AllianceQueryTopicRt getDefaultInstanceForType() {
      return pb4client.AllianceQueryTopicRt.getDefaultInstance();
    }

    public pb4client.AllianceQueryTopicRt build() {
      pb4client.AllianceQueryTopicRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AllianceQueryTopicRt buildPartial() {
      pb4client.AllianceQueryTopicRt result = new pb4client.AllianceQueryTopicRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (topicsBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          topics_ = java.util.Collections.unmodifiableList(topics_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.topics_ = topics_;
      } else {
        result.topics_ = topicsBuilder_.build();
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
      if (other instanceof pb4client.AllianceQueryTopicRt) {
        return mergeFrom((pb4client.AllianceQueryTopicRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AllianceQueryTopicRt other) {
      if (other == pb4client.AllianceQueryTopicRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (topicsBuilder_ == null) {
        if (!other.topics_.isEmpty()) {
          if (topics_.isEmpty()) {
            topics_ = other.topics_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureTopicsIsMutable();
            topics_.addAll(other.topics_);
          }
          onChanged();
        }
      } else {
        if (!other.topics_.isEmpty()) {
          if (topicsBuilder_.isEmpty()) {
            topicsBuilder_.dispose();
            topicsBuilder_ = null;
            topics_ = other.topics_;
            bitField0_ = (bitField0_ & ~0x00000002);
            topicsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getTopicsFieldBuilder() : null;
          } else {
            topicsBuilder_.addAllMessages(other.topics_);
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
      for (int i = 0; i < getTopicsCount(); i++) {
        if (!getTopics(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AllianceQueryTopicRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AllianceQueryTopicRt) e.getUnfinishedMessage();
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

    private java.util.List<pb4client.AllianceTopicInfo> topics_ =
      java.util.Collections.emptyList();
    private void ensureTopicsIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        topics_ = new java.util.ArrayList<pb4client.AllianceTopicInfo>(topics_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.AllianceTopicInfo, pb4client.AllianceTopicInfo.Builder, pb4client.AllianceTopicInfoOrBuilder> topicsBuilder_;

    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public java.util.List<pb4client.AllianceTopicInfo> getTopicsList() {
      if (topicsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(topics_);
      } else {
        return topicsBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public int getTopicsCount() {
      if (topicsBuilder_ == null) {
        return topics_.size();
      } else {
        return topicsBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public pb4client.AllianceTopicInfo getTopics(int index) {
      if (topicsBuilder_ == null) {
        return topics_.get(index);
      } else {
        return topicsBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public Builder setTopics(
        int index, pb4client.AllianceTopicInfo value) {
      if (topicsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTopicsIsMutable();
        topics_.set(index, value);
        onChanged();
      } else {
        topicsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public Builder setTopics(
        int index, pb4client.AllianceTopicInfo.Builder builderForValue) {
      if (topicsBuilder_ == null) {
        ensureTopicsIsMutable();
        topics_.set(index, builderForValue.build());
        onChanged();
      } else {
        topicsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public Builder addTopics(pb4client.AllianceTopicInfo value) {
      if (topicsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTopicsIsMutable();
        topics_.add(value);
        onChanged();
      } else {
        topicsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public Builder addTopics(
        int index, pb4client.AllianceTopicInfo value) {
      if (topicsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTopicsIsMutable();
        topics_.add(index, value);
        onChanged();
      } else {
        topicsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public Builder addTopics(
        pb4client.AllianceTopicInfo.Builder builderForValue) {
      if (topicsBuilder_ == null) {
        ensureTopicsIsMutable();
        topics_.add(builderForValue.build());
        onChanged();
      } else {
        topicsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public Builder addTopics(
        int index, pb4client.AllianceTopicInfo.Builder builderForValue) {
      if (topicsBuilder_ == null) {
        ensureTopicsIsMutable();
        topics_.add(index, builderForValue.build());
        onChanged();
      } else {
        topicsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public Builder addAllTopics(
        java.lang.Iterable<? extends pb4client.AllianceTopicInfo> values) {
      if (topicsBuilder_ == null) {
        ensureTopicsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, topics_);
        onChanged();
      } else {
        topicsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public Builder clearTopics() {
      if (topicsBuilder_ == null) {
        topics_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        topicsBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public Builder removeTopics(int index) {
      if (topicsBuilder_ == null) {
        ensureTopicsIsMutable();
        topics_.remove(index);
        onChanged();
      } else {
        topicsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public pb4client.AllianceTopicInfo.Builder getTopicsBuilder(
        int index) {
      return getTopicsFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public pb4client.AllianceTopicInfoOrBuilder getTopicsOrBuilder(
        int index) {
      if (topicsBuilder_ == null) {
        return topics_.get(index);  } else {
        return topicsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public java.util.List<? extends pb4client.AllianceTopicInfoOrBuilder> 
         getTopicsOrBuilderList() {
      if (topicsBuilder_ != null) {
        return topicsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(topics_);
      }
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public pb4client.AllianceTopicInfo.Builder addTopicsBuilder() {
      return getTopicsFieldBuilder().addBuilder(
          pb4client.AllianceTopicInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public pb4client.AllianceTopicInfo.Builder addTopicsBuilder(
        int index) {
      return getTopicsFieldBuilder().addBuilder(
          index, pb4client.AllianceTopicInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *联盟邮件的主题及回复记录（部分回复记录）
     * </pre>
     *
     * <code>repeated .client2server.AllianceTopicInfo topics = 2;</code>
     */
    public java.util.List<pb4client.AllianceTopicInfo.Builder> 
         getTopicsBuilderList() {
      return getTopicsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.AllianceTopicInfo, pb4client.AllianceTopicInfo.Builder, pb4client.AllianceTopicInfoOrBuilder> 
        getTopicsFieldBuilder() {
      if (topicsBuilder_ == null) {
        topicsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.AllianceTopicInfo, pb4client.AllianceTopicInfo.Builder, pb4client.AllianceTopicInfoOrBuilder>(
                topics_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        topics_ = null;
      }
      return topicsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.AllianceQueryTopicRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.AllianceQueryTopicRt)
  private static final pb4client.AllianceQueryTopicRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AllianceQueryTopicRt();
  }

  public static pb4client.AllianceQueryTopicRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AllianceQueryTopicRt>
      PARSER = new com.google.protobuf.AbstractParser<AllianceQueryTopicRt>() {
    public AllianceQueryTopicRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceQueryTopicRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceQueryTopicRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceQueryTopicRt> getParserForType() {
    return PARSER;
  }

  public pb4client.AllianceQueryTopicRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

