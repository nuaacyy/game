// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3035
 * 服务器 -&gt; 客户端
 * 任务操作
 * </pre>
 *
 * Protobuf type {@code client2server.TaskOperation}
 */
public  final class TaskOperation extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.TaskOperation)
    TaskOperationOrBuilder {
  // Use TaskOperation.newBuilder() to construct.
  private TaskOperation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TaskOperation() {
    operationType_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TaskOperation(
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
            operationType_ = input.readInt32();
            break;
          }
          case 18: {
            pb4client.Task.Builder subBuilder = null;
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
              subBuilder = task_.toBuilder();
            }
            task_ = input.readMessage(pb4client.Task.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(task_);
              task_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000002;
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
    return pb4client.War2GamePkt.internal_static_client2server_TaskOperation_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_TaskOperation_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.TaskOperation.class, pb4client.TaskOperation.Builder.class);
  }

  private int bitField0_;
  public static final int OPERATIONTYPE_FIELD_NUMBER = 1;
  private int operationType_;
  /**
   * <pre>
   *1-新增了一个任务  2-完成了一个任务  3-任务信息发生改变 (进度改变)
   * </pre>
   *
   * <code>required int32 operationType = 1;</code>
   */
  public boolean hasOperationType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *1-新增了一个任务  2-完成了一个任务  3-任务信息发生改变 (进度改变)
   * </pre>
   *
   * <code>required int32 operationType = 1;</code>
   */
  public int getOperationType() {
    return operationType_;
  }

  public static final int TASK_FIELD_NUMBER = 2;
  private pb4client.Task task_;
  /**
   * <code>required .client2server.Task task = 2;</code>
   */
  public boolean hasTask() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required .client2server.Task task = 2;</code>
   */
  public pb4client.Task getTask() {
    return task_ == null ? pb4client.Task.getDefaultInstance() : task_;
  }
  /**
   * <code>required .client2server.Task task = 2;</code>
   */
  public pb4client.TaskOrBuilder getTaskOrBuilder() {
    return task_ == null ? pb4client.Task.getDefaultInstance() : task_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasOperationType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasTask()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getTask().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, operationType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeMessage(2, getTask());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, operationType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getTask());
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
    if (!(obj instanceof pb4client.TaskOperation)) {
      return super.equals(obj);
    }
    pb4client.TaskOperation other = (pb4client.TaskOperation) obj;

    boolean result = true;
    result = result && (hasOperationType() == other.hasOperationType());
    if (hasOperationType()) {
      result = result && (getOperationType()
          == other.getOperationType());
    }
    result = result && (hasTask() == other.hasTask());
    if (hasTask()) {
      result = result && getTask()
          .equals(other.getTask());
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
    if (hasOperationType()) {
      hash = (37 * hash) + OPERATIONTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getOperationType();
    }
    if (hasTask()) {
      hash = (37 * hash) + TASK_FIELD_NUMBER;
      hash = (53 * hash) + getTask().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.TaskOperation parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TaskOperation parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TaskOperation parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TaskOperation parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TaskOperation parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TaskOperation parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TaskOperation parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.TaskOperation parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.TaskOperation parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.TaskOperation parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.TaskOperation parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.TaskOperation parseFrom(
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
  public static Builder newBuilder(pb4client.TaskOperation prototype) {
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
   * msgType = 3035
   * 服务器 -&gt; 客户端
   * 任务操作
   * </pre>
   *
   * Protobuf type {@code client2server.TaskOperation}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.TaskOperation)
      pb4client.TaskOperationOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_TaskOperation_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_TaskOperation_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.TaskOperation.class, pb4client.TaskOperation.Builder.class);
    }

    // Construct using pb4client.TaskOperation.newBuilder()
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
        getTaskFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      operationType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (taskBuilder_ == null) {
        task_ = null;
      } else {
        taskBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_TaskOperation_descriptor;
    }

    public pb4client.TaskOperation getDefaultInstanceForType() {
      return pb4client.TaskOperation.getDefaultInstance();
    }

    public pb4client.TaskOperation build() {
      pb4client.TaskOperation result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.TaskOperation buildPartial() {
      pb4client.TaskOperation result = new pb4client.TaskOperation(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.operationType_ = operationType_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      if (taskBuilder_ == null) {
        result.task_ = task_;
      } else {
        result.task_ = taskBuilder_.build();
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
      if (other instanceof pb4client.TaskOperation) {
        return mergeFrom((pb4client.TaskOperation)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.TaskOperation other) {
      if (other == pb4client.TaskOperation.getDefaultInstance()) return this;
      if (other.hasOperationType()) {
        setOperationType(other.getOperationType());
      }
      if (other.hasTask()) {
        mergeTask(other.getTask());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasOperationType()) {
        return false;
      }
      if (!hasTask()) {
        return false;
      }
      if (!getTask().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.TaskOperation parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.TaskOperation) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int operationType_ ;
    /**
     * <pre>
     *1-新增了一个任务  2-完成了一个任务  3-任务信息发生改变 (进度改变)
     * </pre>
     *
     * <code>required int32 operationType = 1;</code>
     */
    public boolean hasOperationType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *1-新增了一个任务  2-完成了一个任务  3-任务信息发生改变 (进度改变)
     * </pre>
     *
     * <code>required int32 operationType = 1;</code>
     */
    public int getOperationType() {
      return operationType_;
    }
    /**
     * <pre>
     *1-新增了一个任务  2-完成了一个任务  3-任务信息发生改变 (进度改变)
     * </pre>
     *
     * <code>required int32 operationType = 1;</code>
     */
    public Builder setOperationType(int value) {
      bitField0_ |= 0x00000001;
      operationType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *1-新增了一个任务  2-完成了一个任务  3-任务信息发生改变 (进度改变)
     * </pre>
     *
     * <code>required int32 operationType = 1;</code>
     */
    public Builder clearOperationType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      operationType_ = 0;
      onChanged();
      return this;
    }

    private pb4client.Task task_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.Task, pb4client.Task.Builder, pb4client.TaskOrBuilder> taskBuilder_;
    /**
     * <code>required .client2server.Task task = 2;</code>
     */
    public boolean hasTask() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required .client2server.Task task = 2;</code>
     */
    public pb4client.Task getTask() {
      if (taskBuilder_ == null) {
        return task_ == null ? pb4client.Task.getDefaultInstance() : task_;
      } else {
        return taskBuilder_.getMessage();
      }
    }
    /**
     * <code>required .client2server.Task task = 2;</code>
     */
    public Builder setTask(pb4client.Task value) {
      if (taskBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        task_ = value;
        onChanged();
      } else {
        taskBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>required .client2server.Task task = 2;</code>
     */
    public Builder setTask(
        pb4client.Task.Builder builderForValue) {
      if (taskBuilder_ == null) {
        task_ = builderForValue.build();
        onChanged();
      } else {
        taskBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>required .client2server.Task task = 2;</code>
     */
    public Builder mergeTask(pb4client.Task value) {
      if (taskBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002) &&
            task_ != null &&
            task_ != pb4client.Task.getDefaultInstance()) {
          task_ =
            pb4client.Task.newBuilder(task_).mergeFrom(value).buildPartial();
        } else {
          task_ = value;
        }
        onChanged();
      } else {
        taskBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>required .client2server.Task task = 2;</code>
     */
    public Builder clearTask() {
      if (taskBuilder_ == null) {
        task_ = null;
        onChanged();
      } else {
        taskBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    /**
     * <code>required .client2server.Task task = 2;</code>
     */
    public pb4client.Task.Builder getTaskBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getTaskFieldBuilder().getBuilder();
    }
    /**
     * <code>required .client2server.Task task = 2;</code>
     */
    public pb4client.TaskOrBuilder getTaskOrBuilder() {
      if (taskBuilder_ != null) {
        return taskBuilder_.getMessageOrBuilder();
      } else {
        return task_ == null ?
            pb4client.Task.getDefaultInstance() : task_;
      }
    }
    /**
     * <code>required .client2server.Task task = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.Task, pb4client.Task.Builder, pb4client.TaskOrBuilder> 
        getTaskFieldBuilder() {
      if (taskBuilder_ == null) {
        taskBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.Task, pb4client.Task.Builder, pb4client.TaskOrBuilder>(
                getTask(),
                getParentForChildren(),
                isClean());
        task_ = null;
      }
      return taskBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.TaskOperation)
  }

  // @@protoc_insertion_point(class_scope:client2server.TaskOperation)
  private static final pb4client.TaskOperation DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.TaskOperation();
  }

  public static pb4client.TaskOperation getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<TaskOperation>
      PARSER = new com.google.protobuf.AbstractParser<TaskOperation>() {
    public TaskOperation parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TaskOperation(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TaskOperation> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TaskOperation> getParserForType() {
    return PARSER;
  }

  public pb4client.TaskOperation getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

