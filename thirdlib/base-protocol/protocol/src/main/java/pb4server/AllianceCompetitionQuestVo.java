// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.AllianceCompetitionQuestVo}
 */
public  final class AllianceCompetitionQuestVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AllianceCompetitionQuestVo)
    AllianceCompetitionQuestVoOrBuilder {
  // Use AllianceCompetitionQuestVo.newBuilder() to construct.
  private AllianceCompetitionQuestVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceCompetitionQuestVo() {
    index_ = 0;
    questId_ = 0;
    refTime_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllianceCompetitionQuestVo(
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

            index_ = input.readInt32();
            break;
          }
          case 16: {

            questId_ = input.readInt32();
            break;
          }
          case 24: {

            refTime_ = input.readInt32();
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
    return pb4server.InternalPkt.internal_static_pb4server_AllianceCompetitionQuestVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_AllianceCompetitionQuestVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AllianceCompetitionQuestVo.class, pb4server.AllianceCompetitionQuestVo.Builder.class);
  }

  public static final int INDEX_FIELD_NUMBER = 1;
  private int index_;
  /**
   * <pre>
   * 任务排序
   * </pre>
   *
   * <code>int32 index = 1;</code>
   */
  public int getIndex() {
    return index_;
  }

  public static final int QUESTID_FIELD_NUMBER = 2;
  private int questId_;
  /**
   * <pre>
   * 被领取之后就是0
   * </pre>
   *
   * <code>int32 questId = 2;</code>
   */
  public int getQuestId() {
    return questId_;
  }

  public static final int REFTIME_FIELD_NUMBER = 3;
  private int refTime_;
  /**
   * <pre>
   * 任务刷新倒计时,只有任务ID是0的时候这个才不为zeroTime
   * </pre>
   *
   * <code>int32 refTime = 3;</code>
   */
  public int getRefTime() {
    return refTime_;
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
    if (index_ != 0) {
      output.writeInt32(1, index_);
    }
    if (questId_ != 0) {
      output.writeInt32(2, questId_);
    }
    if (refTime_ != 0) {
      output.writeInt32(3, refTime_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (index_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, index_);
    }
    if (questId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, questId_);
    }
    if (refTime_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, refTime_);
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
    if (!(obj instanceof pb4server.AllianceCompetitionQuestVo)) {
      return super.equals(obj);
    }
    pb4server.AllianceCompetitionQuestVo other = (pb4server.AllianceCompetitionQuestVo) obj;

    boolean result = true;
    result = result && (getIndex()
        == other.getIndex());
    result = result && (getQuestId()
        == other.getQuestId());
    result = result && (getRefTime()
        == other.getRefTime());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + INDEX_FIELD_NUMBER;
    hash = (53 * hash) + getIndex();
    hash = (37 * hash) + QUESTID_FIELD_NUMBER;
    hash = (53 * hash) + getQuestId();
    hash = (37 * hash) + REFTIME_FIELD_NUMBER;
    hash = (53 * hash) + getRefTime();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.AllianceCompetitionQuestVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceCompetitionQuestVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionQuestVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceCompetitionQuestVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionQuestVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceCompetitionQuestVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionQuestVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceCompetitionQuestVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionQuestVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AllianceCompetitionQuestVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionQuestVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceCompetitionQuestVo parseFrom(
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
  public static Builder newBuilder(pb4server.AllianceCompetitionQuestVo prototype) {
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
   * Protobuf type {@code pb4server.AllianceCompetitionQuestVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AllianceCompetitionQuestVo)
      pb4server.AllianceCompetitionQuestVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceCompetitionQuestVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceCompetitionQuestVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AllianceCompetitionQuestVo.class, pb4server.AllianceCompetitionQuestVo.Builder.class);
    }

    // Construct using pb4server.AllianceCompetitionQuestVo.newBuilder()
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
      index_ = 0;

      questId_ = 0;

      refTime_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceCompetitionQuestVo_descriptor;
    }

    public pb4server.AllianceCompetitionQuestVo getDefaultInstanceForType() {
      return pb4server.AllianceCompetitionQuestVo.getDefaultInstance();
    }

    public pb4server.AllianceCompetitionQuestVo build() {
      pb4server.AllianceCompetitionQuestVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AllianceCompetitionQuestVo buildPartial() {
      pb4server.AllianceCompetitionQuestVo result = new pb4server.AllianceCompetitionQuestVo(this);
      result.index_ = index_;
      result.questId_ = questId_;
      result.refTime_ = refTime_;
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
      if (other instanceof pb4server.AllianceCompetitionQuestVo) {
        return mergeFrom((pb4server.AllianceCompetitionQuestVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AllianceCompetitionQuestVo other) {
      if (other == pb4server.AllianceCompetitionQuestVo.getDefaultInstance()) return this;
      if (other.getIndex() != 0) {
        setIndex(other.getIndex());
      }
      if (other.getQuestId() != 0) {
        setQuestId(other.getQuestId());
      }
      if (other.getRefTime() != 0) {
        setRefTime(other.getRefTime());
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
      pb4server.AllianceCompetitionQuestVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AllianceCompetitionQuestVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int index_ ;
    /**
     * <pre>
     * 任务排序
     * </pre>
     *
     * <code>int32 index = 1;</code>
     */
    public int getIndex() {
      return index_;
    }
    /**
     * <pre>
     * 任务排序
     * </pre>
     *
     * <code>int32 index = 1;</code>
     */
    public Builder setIndex(int value) {
      
      index_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 任务排序
     * </pre>
     *
     * <code>int32 index = 1;</code>
     */
    public Builder clearIndex() {
      
      index_ = 0;
      onChanged();
      return this;
    }

    private int questId_ ;
    /**
     * <pre>
     * 被领取之后就是0
     * </pre>
     *
     * <code>int32 questId = 2;</code>
     */
    public int getQuestId() {
      return questId_;
    }
    /**
     * <pre>
     * 被领取之后就是0
     * </pre>
     *
     * <code>int32 questId = 2;</code>
     */
    public Builder setQuestId(int value) {
      
      questId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被领取之后就是0
     * </pre>
     *
     * <code>int32 questId = 2;</code>
     */
    public Builder clearQuestId() {
      
      questId_ = 0;
      onChanged();
      return this;
    }

    private int refTime_ ;
    /**
     * <pre>
     * 任务刷新倒计时,只有任务ID是0的时候这个才不为zeroTime
     * </pre>
     *
     * <code>int32 refTime = 3;</code>
     */
    public int getRefTime() {
      return refTime_;
    }
    /**
     * <pre>
     * 任务刷新倒计时,只有任务ID是0的时候这个才不为zeroTime
     * </pre>
     *
     * <code>int32 refTime = 3;</code>
     */
    public Builder setRefTime(int value) {
      
      refTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 任务刷新倒计时,只有任务ID是0的时候这个才不为zeroTime
     * </pre>
     *
     * <code>int32 refTime = 3;</code>
     */
    public Builder clearRefTime() {
      
      refTime_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.AllianceCompetitionQuestVo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AllianceCompetitionQuestVo)
  private static final pb4server.AllianceCompetitionQuestVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AllianceCompetitionQuestVo();
  }

  public static pb4server.AllianceCompetitionQuestVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllianceCompetitionQuestVo>
      PARSER = new com.google.protobuf.AbstractParser<AllianceCompetitionQuestVo>() {
    public AllianceCompetitionQuestVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceCompetitionQuestVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceCompetitionQuestVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceCompetitionQuestVo> getParserForType() {
    return PARSER;
  }

  public pb4server.AllianceCompetitionQuestVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

