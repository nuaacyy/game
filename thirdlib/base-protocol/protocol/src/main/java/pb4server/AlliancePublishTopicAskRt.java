// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.AlliancePublishTopicAskRt}
 */
public  final class AlliancePublishTopicAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AlliancePublishTopicAskRt)
    AlliancePublishTopicAskRtOrBuilder {
  // Use AlliancePublishTopicAskRt.newBuilder() to construct.
  private AlliancePublishTopicAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AlliancePublishTopicAskRt() {
    rt_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AlliancePublishTopicAskRt(
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
          case 18: {
            pb4server.AllianceTopicInfoVo.Builder subBuilder = null;
            if (topic_ != null) {
              subBuilder = topic_.toBuilder();
            }
            topic_ = input.readMessage(pb4server.AllianceTopicInfoVo.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(topic_);
              topic_ = subBuilder.buildPartial();
            }

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
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_AlliancePublishTopicAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_AlliancePublishTopicAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AlliancePublishTopicAskRt.class, pb4server.AlliancePublishTopicAskRt.Builder.class);
  }

  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int TOPIC_FIELD_NUMBER = 2;
  private pb4server.AllianceTopicInfoVo topic_;
  /**
   * <pre>
   *联盟邮件的主题及回复
   * </pre>
   *
   * <code>.pb4server.AllianceTopicInfoVo topic = 2;</code>
   */
  public boolean hasTopic() {
    return topic_ != null;
  }
  /**
   * <pre>
   *联盟邮件的主题及回复
   * </pre>
   *
   * <code>.pb4server.AllianceTopicInfoVo topic = 2;</code>
   */
  public pb4server.AllianceTopicInfoVo getTopic() {
    return topic_ == null ? pb4server.AllianceTopicInfoVo.getDefaultInstance() : topic_;
  }
  /**
   * <pre>
   *联盟邮件的主题及回复
   * </pre>
   *
   * <code>.pb4server.AllianceTopicInfoVo topic = 2;</code>
   */
  public pb4server.AllianceTopicInfoVoOrBuilder getTopicOrBuilder() {
    return getTopic();
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
    if (topic_ != null) {
      output.writeMessage(2, getTopic());
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
    if (topic_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getTopic());
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
    if (!(obj instanceof pb4server.AlliancePublishTopicAskRt)) {
      return super.equals(obj);
    }
    pb4server.AlliancePublishTopicAskRt other = (pb4server.AlliancePublishTopicAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && (hasTopic() == other.hasTopic());
    if (hasTopic()) {
      result = result && getTopic()
          .equals(other.getTopic());
    }
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
    if (hasTopic()) {
      hash = (37 * hash) + TOPIC_FIELD_NUMBER;
      hash = (53 * hash) + getTopic().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.AlliancePublishTopicAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AlliancePublishTopicAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AlliancePublishTopicAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AlliancePublishTopicAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AlliancePublishTopicAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AlliancePublishTopicAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AlliancePublishTopicAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AlliancePublishTopicAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AlliancePublishTopicAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AlliancePublishTopicAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AlliancePublishTopicAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AlliancePublishTopicAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.AlliancePublishTopicAskRt prototype) {
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
   * Protobuf type {@code pb4server.AlliancePublishTopicAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AlliancePublishTopicAskRt)
      pb4server.AlliancePublishTopicAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_AlliancePublishTopicAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_AlliancePublishTopicAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AlliancePublishTopicAskRt.class, pb4server.AlliancePublishTopicAskRt.Builder.class);
    }

    // Construct using pb4server.AlliancePublishTopicAskRt.newBuilder()
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
      rt_ = 0;

      if (topicBuilder_ == null) {
        topic_ = null;
      } else {
        topic_ = null;
        topicBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_AlliancePublishTopicAskRt_descriptor;
    }

    public pb4server.AlliancePublishTopicAskRt getDefaultInstanceForType() {
      return pb4server.AlliancePublishTopicAskRt.getDefaultInstance();
    }

    public pb4server.AlliancePublishTopicAskRt build() {
      pb4server.AlliancePublishTopicAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AlliancePublishTopicAskRt buildPartial() {
      pb4server.AlliancePublishTopicAskRt result = new pb4server.AlliancePublishTopicAskRt(this);
      result.rt_ = rt_;
      if (topicBuilder_ == null) {
        result.topic_ = topic_;
      } else {
        result.topic_ = topicBuilder_.build();
      }
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
      if (other instanceof pb4server.AlliancePublishTopicAskRt) {
        return mergeFrom((pb4server.AlliancePublishTopicAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AlliancePublishTopicAskRt other) {
      if (other == pb4server.AlliancePublishTopicAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (other.hasTopic()) {
        mergeTopic(other.getTopic());
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
      pb4server.AlliancePublishTopicAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AlliancePublishTopicAskRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

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

    private pb4server.AllianceTopicInfoVo topic_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.AllianceTopicInfoVo, pb4server.AllianceTopicInfoVo.Builder, pb4server.AllianceTopicInfoVoOrBuilder> topicBuilder_;
    /**
     * <pre>
     *联盟邮件的主题及回复
     * </pre>
     *
     * <code>.pb4server.AllianceTopicInfoVo topic = 2;</code>
     */
    public boolean hasTopic() {
      return topicBuilder_ != null || topic_ != null;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复
     * </pre>
     *
     * <code>.pb4server.AllianceTopicInfoVo topic = 2;</code>
     */
    public pb4server.AllianceTopicInfoVo getTopic() {
      if (topicBuilder_ == null) {
        return topic_ == null ? pb4server.AllianceTopicInfoVo.getDefaultInstance() : topic_;
      } else {
        return topicBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     *联盟邮件的主题及回复
     * </pre>
     *
     * <code>.pb4server.AllianceTopicInfoVo topic = 2;</code>
     */
    public Builder setTopic(pb4server.AllianceTopicInfoVo value) {
      if (topicBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        topic_ = value;
        onChanged();
      } else {
        topicBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复
     * </pre>
     *
     * <code>.pb4server.AllianceTopicInfoVo topic = 2;</code>
     */
    public Builder setTopic(
        pb4server.AllianceTopicInfoVo.Builder builderForValue) {
      if (topicBuilder_ == null) {
        topic_ = builderForValue.build();
        onChanged();
      } else {
        topicBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复
     * </pre>
     *
     * <code>.pb4server.AllianceTopicInfoVo topic = 2;</code>
     */
    public Builder mergeTopic(pb4server.AllianceTopicInfoVo value) {
      if (topicBuilder_ == null) {
        if (topic_ != null) {
          topic_ =
            pb4server.AllianceTopicInfoVo.newBuilder(topic_).mergeFrom(value).buildPartial();
        } else {
          topic_ = value;
        }
        onChanged();
      } else {
        topicBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复
     * </pre>
     *
     * <code>.pb4server.AllianceTopicInfoVo topic = 2;</code>
     */
    public Builder clearTopic() {
      if (topicBuilder_ == null) {
        topic_ = null;
        onChanged();
      } else {
        topic_ = null;
        topicBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     *联盟邮件的主题及回复
     * </pre>
     *
     * <code>.pb4server.AllianceTopicInfoVo topic = 2;</code>
     */
    public pb4server.AllianceTopicInfoVo.Builder getTopicBuilder() {
      
      onChanged();
      return getTopicFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     *联盟邮件的主题及回复
     * </pre>
     *
     * <code>.pb4server.AllianceTopicInfoVo topic = 2;</code>
     */
    public pb4server.AllianceTopicInfoVoOrBuilder getTopicOrBuilder() {
      if (topicBuilder_ != null) {
        return topicBuilder_.getMessageOrBuilder();
      } else {
        return topic_ == null ?
            pb4server.AllianceTopicInfoVo.getDefaultInstance() : topic_;
      }
    }
    /**
     * <pre>
     *联盟邮件的主题及回复
     * </pre>
     *
     * <code>.pb4server.AllianceTopicInfoVo topic = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.AllianceTopicInfoVo, pb4server.AllianceTopicInfoVo.Builder, pb4server.AllianceTopicInfoVoOrBuilder> 
        getTopicFieldBuilder() {
      if (topicBuilder_ == null) {
        topicBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4server.AllianceTopicInfoVo, pb4server.AllianceTopicInfoVo.Builder, pb4server.AllianceTopicInfoVoOrBuilder>(
                getTopic(),
                getParentForChildren(),
                isClean());
        topic_ = null;
      }
      return topicBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.AlliancePublishTopicAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AlliancePublishTopicAskRt)
  private static final pb4server.AlliancePublishTopicAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AlliancePublishTopicAskRt();
  }

  public static pb4server.AlliancePublishTopicAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AlliancePublishTopicAskRt>
      PARSER = new com.google.protobuf.AbstractParser<AlliancePublishTopicAskRt>() {
    public AlliancePublishTopicAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AlliancePublishTopicAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AlliancePublishTopicAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AlliancePublishTopicAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.AlliancePublishTopicAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
