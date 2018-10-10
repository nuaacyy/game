// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 * 联盟总动员任务检测
 * </pre>
 *
 * Protobuf type {@code pb4server.CheckAllianceCompetitionAskReq}
 */
public  final class CheckAllianceCompetitionAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.CheckAllianceCompetitionAskReq)
    CheckAllianceCompetitionAskReqOrBuilder {
  // Use CheckAllianceCompetitionAskReq.newBuilder() to construct.
  private CheckAllianceCompetitionAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CheckAllianceCompetitionAskReq() {
    taskType_ = 0;
    taskValue_ = java.util.Collections.emptyList();
    oldValue_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private CheckAllianceCompetitionAskReq(
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

            taskType_ = input.readInt32();
            break;
          }
          case 16: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              taskValue_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000002;
            }
            taskValue_.add(input.readInt32());
            break;
          }
          case 18: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002) && input.getBytesUntilLimit() > 0) {
              taskValue_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000002;
            }
            while (input.getBytesUntilLimit() > 0) {
              taskValue_.add(input.readInt32());
            }
            input.popLimit(limit);
            break;
          }
          case 24: {

            oldValue_ = input.readInt64();
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
        taskValue_ = java.util.Collections.unmodifiableList(taskValue_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalWkt.internal_static_pb4server_CheckAllianceCompetitionAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_CheckAllianceCompetitionAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.CheckAllianceCompetitionAskReq.class, pb4server.CheckAllianceCompetitionAskReq.Builder.class);
  }

  private int bitField0_;
  public static final int TASKTYPE_FIELD_NUMBER = 1;
  private int taskType_;
  /**
   * <pre>
   * 任务检测类型
   * </pre>
   *
   * <code>int32 taskType = 1;</code>
   */
  public int getTaskType() {
    return taskType_;
  }

  public static final int TASKVALUE_FIELD_NUMBER = 2;
  private java.util.List<java.lang.Integer> taskValue_;
  /**
   * <code>repeated int32 taskValue = 2;</code>
   */
  public java.util.List<java.lang.Integer>
      getTaskValueList() {
    return taskValue_;
  }
  /**
   * <code>repeated int32 taskValue = 2;</code>
   */
  public int getTaskValueCount() {
    return taskValue_.size();
  }
  /**
   * <code>repeated int32 taskValue = 2;</code>
   */
  public int getTaskValue(int index) {
    return taskValue_.get(index);
  }
  private int taskValueMemoizedSerializedSize = -1;

  public static final int OLDVALUE_FIELD_NUMBER = 3;
  private long oldValue_;
  /**
   * <pre>
   * 老值
   * </pre>
   *
   * <code>int64 oldValue = 3;</code>
   */
  public long getOldValue() {
    return oldValue_;
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
    if (taskType_ != 0) {
      output.writeInt32(1, taskType_);
    }
    if (getTaskValueList().size() > 0) {
      output.writeUInt32NoTag(18);
      output.writeUInt32NoTag(taskValueMemoizedSerializedSize);
    }
    for (int i = 0; i < taskValue_.size(); i++) {
      output.writeInt32NoTag(taskValue_.get(i));
    }
    if (oldValue_ != 0L) {
      output.writeInt64(3, oldValue_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (taskType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, taskType_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < taskValue_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(taskValue_.get(i));
      }
      size += dataSize;
      if (!getTaskValueList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      taskValueMemoizedSerializedSize = dataSize;
    }
    if (oldValue_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, oldValue_);
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
    if (!(obj instanceof pb4server.CheckAllianceCompetitionAskReq)) {
      return super.equals(obj);
    }
    pb4server.CheckAllianceCompetitionAskReq other = (pb4server.CheckAllianceCompetitionAskReq) obj;

    boolean result = true;
    result = result && (getTaskType()
        == other.getTaskType());
    result = result && getTaskValueList()
        .equals(other.getTaskValueList());
    result = result && (getOldValue()
        == other.getOldValue());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TASKTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getTaskType();
    if (getTaskValueCount() > 0) {
      hash = (37 * hash) + TASKVALUE_FIELD_NUMBER;
      hash = (53 * hash) + getTaskValueList().hashCode();
    }
    hash = (37 * hash) + OLDVALUE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getOldValue());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.CheckAllianceCompetitionAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.CheckAllianceCompetitionAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.CheckAllianceCompetitionAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.CheckAllianceCompetitionAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.CheckAllianceCompetitionAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.CheckAllianceCompetitionAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.CheckAllianceCompetitionAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.CheckAllianceCompetitionAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.CheckAllianceCompetitionAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.CheckAllianceCompetitionAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.CheckAllianceCompetitionAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.CheckAllianceCompetitionAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.CheckAllianceCompetitionAskReq prototype) {
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
   * 联盟总动员任务检测
   * </pre>
   *
   * Protobuf type {@code pb4server.CheckAllianceCompetitionAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.CheckAllianceCompetitionAskReq)
      pb4server.CheckAllianceCompetitionAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_CheckAllianceCompetitionAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_CheckAllianceCompetitionAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.CheckAllianceCompetitionAskReq.class, pb4server.CheckAllianceCompetitionAskReq.Builder.class);
    }

    // Construct using pb4server.CheckAllianceCompetitionAskReq.newBuilder()
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
      taskType_ = 0;

      taskValue_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      oldValue_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_CheckAllianceCompetitionAskReq_descriptor;
    }

    public pb4server.CheckAllianceCompetitionAskReq getDefaultInstanceForType() {
      return pb4server.CheckAllianceCompetitionAskReq.getDefaultInstance();
    }

    public pb4server.CheckAllianceCompetitionAskReq build() {
      pb4server.CheckAllianceCompetitionAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.CheckAllianceCompetitionAskReq buildPartial() {
      pb4server.CheckAllianceCompetitionAskReq result = new pb4server.CheckAllianceCompetitionAskReq(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.taskType_ = taskType_;
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        taskValue_ = java.util.Collections.unmodifiableList(taskValue_);
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.taskValue_ = taskValue_;
      result.oldValue_ = oldValue_;
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
      if (other instanceof pb4server.CheckAllianceCompetitionAskReq) {
        return mergeFrom((pb4server.CheckAllianceCompetitionAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.CheckAllianceCompetitionAskReq other) {
      if (other == pb4server.CheckAllianceCompetitionAskReq.getDefaultInstance()) return this;
      if (other.getTaskType() != 0) {
        setTaskType(other.getTaskType());
      }
      if (!other.taskValue_.isEmpty()) {
        if (taskValue_.isEmpty()) {
          taskValue_ = other.taskValue_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensureTaskValueIsMutable();
          taskValue_.addAll(other.taskValue_);
        }
        onChanged();
      }
      if (other.getOldValue() != 0L) {
        setOldValue(other.getOldValue());
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
      pb4server.CheckAllianceCompetitionAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.CheckAllianceCompetitionAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int taskType_ ;
    /**
     * <pre>
     * 任务检测类型
     * </pre>
     *
     * <code>int32 taskType = 1;</code>
     */
    public int getTaskType() {
      return taskType_;
    }
    /**
     * <pre>
     * 任务检测类型
     * </pre>
     *
     * <code>int32 taskType = 1;</code>
     */
    public Builder setTaskType(int value) {
      
      taskType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 任务检测类型
     * </pre>
     *
     * <code>int32 taskType = 1;</code>
     */
    public Builder clearTaskType() {
      
      taskType_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Integer> taskValue_ = java.util.Collections.emptyList();
    private void ensureTaskValueIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        taskValue_ = new java.util.ArrayList<java.lang.Integer>(taskValue_);
        bitField0_ |= 0x00000002;
       }
    }
    /**
     * <code>repeated int32 taskValue = 2;</code>
     */
    public java.util.List<java.lang.Integer>
        getTaskValueList() {
      return java.util.Collections.unmodifiableList(taskValue_);
    }
    /**
     * <code>repeated int32 taskValue = 2;</code>
     */
    public int getTaskValueCount() {
      return taskValue_.size();
    }
    /**
     * <code>repeated int32 taskValue = 2;</code>
     */
    public int getTaskValue(int index) {
      return taskValue_.get(index);
    }
    /**
     * <code>repeated int32 taskValue = 2;</code>
     */
    public Builder setTaskValue(
        int index, int value) {
      ensureTaskValueIsMutable();
      taskValue_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 taskValue = 2;</code>
     */
    public Builder addTaskValue(int value) {
      ensureTaskValueIsMutable();
      taskValue_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 taskValue = 2;</code>
     */
    public Builder addAllTaskValue(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensureTaskValueIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, taskValue_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 taskValue = 2;</code>
     */
    public Builder clearTaskValue() {
      taskValue_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }

    private long oldValue_ ;
    /**
     * <pre>
     * 老值
     * </pre>
     *
     * <code>int64 oldValue = 3;</code>
     */
    public long getOldValue() {
      return oldValue_;
    }
    /**
     * <pre>
     * 老值
     * </pre>
     *
     * <code>int64 oldValue = 3;</code>
     */
    public Builder setOldValue(long value) {
      
      oldValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 老值
     * </pre>
     *
     * <code>int64 oldValue = 3;</code>
     */
    public Builder clearOldValue() {
      
      oldValue_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:pb4server.CheckAllianceCompetitionAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.CheckAllianceCompetitionAskReq)
  private static final pb4server.CheckAllianceCompetitionAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.CheckAllianceCompetitionAskReq();
  }

  public static pb4server.CheckAllianceCompetitionAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CheckAllianceCompetitionAskReq>
      PARSER = new com.google.protobuf.AbstractParser<CheckAllianceCompetitionAskReq>() {
    public CheckAllianceCompetitionAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new CheckAllianceCompetitionAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CheckAllianceCompetitionAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CheckAllianceCompetitionAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.CheckAllianceCompetitionAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
