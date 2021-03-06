// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.WtInfo}
 */
public  final class WtInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.WtInfo)
    WtInfoOrBuilder {
  // Use WtInfo.newBuilder() to construct.
  private WtInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private WtInfo() {
    taskProtoId_ = 0;
    taskNowState_ = 0;
    taskFinish_ = 0;
    endTime_ = 0;
    finishTime_ = 0;
    nowFinishInfo_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private WtInfo(
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
            taskProtoId_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            taskNowState_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            taskFinish_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            endTime_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            finishTime_ = input.readInt32();
            break;
          }
          case 50: {
            com.google.protobuf.ByteString bs = input.readBytes();
            if (!((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
              nowFinishInfo_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000020;
            }
            nowFinishInfo_.add(bs);
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
      if (((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
        nowFinishInfo_ = nowFinishInfo_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_WtInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_WtInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.WtInfo.class, pb4client.WtInfo.Builder.class);
  }

  private int bitField0_;
  public static final int TASKPROTO_ID_FIELD_NUMBER = 1;
  private int taskProtoId_;
  /**
   * <pre>
   *模板ID
   * </pre>
   *
   * <code>required int32 taskProto_id = 1;</code>
   */
  public boolean hasTaskProtoId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *模板ID
   * </pre>
   *
   * <code>required int32 taskProto_id = 1;</code>
   */
  public int getTaskProtoId() {
    return taskProtoId_;
  }

  public static final int TASKNOWSTATE_FIELD_NUMBER = 2;
  private int taskNowState_;
  /**
   * <pre>
   *当前状态 0-进行中 1-结束并且成功完成 2-结束但是并没有成功完成
   * </pre>
   *
   * <code>required int32 taskNowState = 2;</code>
   */
  public boolean hasTaskNowState() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *当前状态 0-进行中 1-结束并且成功完成 2-结束但是并没有成功完成
   * </pre>
   *
   * <code>required int32 taskNowState = 2;</code>
   */
  public int getTaskNowState() {
    return taskNowState_;
  }

  public static final int TASKFINISH_FIELD_NUMBER = 3;
  private int taskFinish_;
  /**
   * <pre>
   *当前完成度
   * </pre>
   *
   * <code>required int32 taskFinish = 3;</code>
   */
  public boolean hasTaskFinish() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *当前完成度
   * </pre>
   *
   * <code>required int32 taskFinish = 3;</code>
   */
  public int getTaskFinish() {
    return taskFinish_;
  }

  public static final int ENDTIME_FIELD_NUMBER = 4;
  private int endTime_;
  /**
   * <pre>
   *结束时间
   * </pre>
   *
   * <code>required int32 endTime = 4;</code>
   */
  public boolean hasEndTime() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *结束时间
   * </pre>
   *
   * <code>required int32 endTime = 4;</code>
   */
  public int getEndTime() {
    return endTime_;
  }

  public static final int FINISHTIME_FIELD_NUMBER = 5;
  private int finishTime_;
  /**
   * <pre>
   *完成时间
   * </pre>
   *
   * <code>required int32 finishTime = 5;</code>
   */
  public boolean hasFinishTime() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   *完成时间
   * </pre>
   *
   * <code>required int32 finishTime = 5;</code>
   */
  public int getFinishTime() {
    return finishTime_;
  }

  public static final int NOWFINISHINFO_FIELD_NUMBER = 6;
  private com.google.protobuf.LazyStringList nowFinishInfo_;
  /**
   * <pre>
   *当前完成者帮派名字集合
   * </pre>
   *
   * <code>repeated string nowFinishInfo = 6;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getNowFinishInfoList() {
    return nowFinishInfo_;
  }
  /**
   * <pre>
   *当前完成者帮派名字集合
   * </pre>
   *
   * <code>repeated string nowFinishInfo = 6;</code>
   */
  public int getNowFinishInfoCount() {
    return nowFinishInfo_.size();
  }
  /**
   * <pre>
   *当前完成者帮派名字集合
   * </pre>
   *
   * <code>repeated string nowFinishInfo = 6;</code>
   */
  public java.lang.String getNowFinishInfo(int index) {
    return nowFinishInfo_.get(index);
  }
  /**
   * <pre>
   *当前完成者帮派名字集合
   * </pre>
   *
   * <code>repeated string nowFinishInfo = 6;</code>
   */
  public com.google.protobuf.ByteString
      getNowFinishInfoBytes(int index) {
    return nowFinishInfo_.getByteString(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasTaskProtoId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasTaskNowState()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasTaskFinish()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasEndTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasFinishTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, taskProtoId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, taskNowState_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, taskFinish_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, endTime_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, finishTime_);
    }
    for (int i = 0; i < nowFinishInfo_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, nowFinishInfo_.getRaw(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, taskProtoId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, taskNowState_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, taskFinish_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, endTime_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, finishTime_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < nowFinishInfo_.size(); i++) {
        dataSize += computeStringSizeNoTag(nowFinishInfo_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getNowFinishInfoList().size();
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
    if (!(obj instanceof pb4client.WtInfo)) {
      return super.equals(obj);
    }
    pb4client.WtInfo other = (pb4client.WtInfo) obj;

    boolean result = true;
    result = result && (hasTaskProtoId() == other.hasTaskProtoId());
    if (hasTaskProtoId()) {
      result = result && (getTaskProtoId()
          == other.getTaskProtoId());
    }
    result = result && (hasTaskNowState() == other.hasTaskNowState());
    if (hasTaskNowState()) {
      result = result && (getTaskNowState()
          == other.getTaskNowState());
    }
    result = result && (hasTaskFinish() == other.hasTaskFinish());
    if (hasTaskFinish()) {
      result = result && (getTaskFinish()
          == other.getTaskFinish());
    }
    result = result && (hasEndTime() == other.hasEndTime());
    if (hasEndTime()) {
      result = result && (getEndTime()
          == other.getEndTime());
    }
    result = result && (hasFinishTime() == other.hasFinishTime());
    if (hasFinishTime()) {
      result = result && (getFinishTime()
          == other.getFinishTime());
    }
    result = result && getNowFinishInfoList()
        .equals(other.getNowFinishInfoList());
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
    if (hasTaskProtoId()) {
      hash = (37 * hash) + TASKPROTO_ID_FIELD_NUMBER;
      hash = (53 * hash) + getTaskProtoId();
    }
    if (hasTaskNowState()) {
      hash = (37 * hash) + TASKNOWSTATE_FIELD_NUMBER;
      hash = (53 * hash) + getTaskNowState();
    }
    if (hasTaskFinish()) {
      hash = (37 * hash) + TASKFINISH_FIELD_NUMBER;
      hash = (53 * hash) + getTaskFinish();
    }
    if (hasEndTime()) {
      hash = (37 * hash) + ENDTIME_FIELD_NUMBER;
      hash = (53 * hash) + getEndTime();
    }
    if (hasFinishTime()) {
      hash = (37 * hash) + FINISHTIME_FIELD_NUMBER;
      hash = (53 * hash) + getFinishTime();
    }
    if (getNowFinishInfoCount() > 0) {
      hash = (37 * hash) + NOWFINISHINFO_FIELD_NUMBER;
      hash = (53 * hash) + getNowFinishInfoList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.WtInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WtInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WtInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WtInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WtInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WtInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WtInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.WtInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.WtInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.WtInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.WtInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.WtInfo parseFrom(
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
  public static Builder newBuilder(pb4client.WtInfo prototype) {
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
   * Protobuf type {@code client2server.WtInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.WtInfo)
      pb4client.WtInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_WtInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_WtInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.WtInfo.class, pb4client.WtInfo.Builder.class);
    }

    // Construct using pb4client.WtInfo.newBuilder()
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
      taskProtoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      taskNowState_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      taskFinish_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      endTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      finishTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      nowFinishInfo_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000020);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_WtInfo_descriptor;
    }

    public pb4client.WtInfo getDefaultInstanceForType() {
      return pb4client.WtInfo.getDefaultInstance();
    }

    public pb4client.WtInfo build() {
      pb4client.WtInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.WtInfo buildPartial() {
      pb4client.WtInfo result = new pb4client.WtInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.taskProtoId_ = taskProtoId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.taskNowState_ = taskNowState_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.taskFinish_ = taskFinish_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.endTime_ = endTime_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.finishTime_ = finishTime_;
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        nowFinishInfo_ = nowFinishInfo_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000020);
      }
      result.nowFinishInfo_ = nowFinishInfo_;
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
      if (other instanceof pb4client.WtInfo) {
        return mergeFrom((pb4client.WtInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.WtInfo other) {
      if (other == pb4client.WtInfo.getDefaultInstance()) return this;
      if (other.hasTaskProtoId()) {
        setTaskProtoId(other.getTaskProtoId());
      }
      if (other.hasTaskNowState()) {
        setTaskNowState(other.getTaskNowState());
      }
      if (other.hasTaskFinish()) {
        setTaskFinish(other.getTaskFinish());
      }
      if (other.hasEndTime()) {
        setEndTime(other.getEndTime());
      }
      if (other.hasFinishTime()) {
        setFinishTime(other.getFinishTime());
      }
      if (!other.nowFinishInfo_.isEmpty()) {
        if (nowFinishInfo_.isEmpty()) {
          nowFinishInfo_ = other.nowFinishInfo_;
          bitField0_ = (bitField0_ & ~0x00000020);
        } else {
          ensureNowFinishInfoIsMutable();
          nowFinishInfo_.addAll(other.nowFinishInfo_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasTaskProtoId()) {
        return false;
      }
      if (!hasTaskNowState()) {
        return false;
      }
      if (!hasTaskFinish()) {
        return false;
      }
      if (!hasEndTime()) {
        return false;
      }
      if (!hasFinishTime()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.WtInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.WtInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int taskProtoId_ ;
    /**
     * <pre>
     *模板ID
     * </pre>
     *
     * <code>required int32 taskProto_id = 1;</code>
     */
    public boolean hasTaskProtoId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *模板ID
     * </pre>
     *
     * <code>required int32 taskProto_id = 1;</code>
     */
    public int getTaskProtoId() {
      return taskProtoId_;
    }
    /**
     * <pre>
     *模板ID
     * </pre>
     *
     * <code>required int32 taskProto_id = 1;</code>
     */
    public Builder setTaskProtoId(int value) {
      bitField0_ |= 0x00000001;
      taskProtoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *模板ID
     * </pre>
     *
     * <code>required int32 taskProto_id = 1;</code>
     */
    public Builder clearTaskProtoId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      taskProtoId_ = 0;
      onChanged();
      return this;
    }

    private int taskNowState_ ;
    /**
     * <pre>
     *当前状态 0-进行中 1-结束并且成功完成 2-结束但是并没有成功完成
     * </pre>
     *
     * <code>required int32 taskNowState = 2;</code>
     */
    public boolean hasTaskNowState() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *当前状态 0-进行中 1-结束并且成功完成 2-结束但是并没有成功完成
     * </pre>
     *
     * <code>required int32 taskNowState = 2;</code>
     */
    public int getTaskNowState() {
      return taskNowState_;
    }
    /**
     * <pre>
     *当前状态 0-进行中 1-结束并且成功完成 2-结束但是并没有成功完成
     * </pre>
     *
     * <code>required int32 taskNowState = 2;</code>
     */
    public Builder setTaskNowState(int value) {
      bitField0_ |= 0x00000002;
      taskNowState_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前状态 0-进行中 1-结束并且成功完成 2-结束但是并没有成功完成
     * </pre>
     *
     * <code>required int32 taskNowState = 2;</code>
     */
    public Builder clearTaskNowState() {
      bitField0_ = (bitField0_ & ~0x00000002);
      taskNowState_ = 0;
      onChanged();
      return this;
    }

    private int taskFinish_ ;
    /**
     * <pre>
     *当前完成度
     * </pre>
     *
     * <code>required int32 taskFinish = 3;</code>
     */
    public boolean hasTaskFinish() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *当前完成度
     * </pre>
     *
     * <code>required int32 taskFinish = 3;</code>
     */
    public int getTaskFinish() {
      return taskFinish_;
    }
    /**
     * <pre>
     *当前完成度
     * </pre>
     *
     * <code>required int32 taskFinish = 3;</code>
     */
    public Builder setTaskFinish(int value) {
      bitField0_ |= 0x00000004;
      taskFinish_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前完成度
     * </pre>
     *
     * <code>required int32 taskFinish = 3;</code>
     */
    public Builder clearTaskFinish() {
      bitField0_ = (bitField0_ & ~0x00000004);
      taskFinish_ = 0;
      onChanged();
      return this;
    }

    private int endTime_ ;
    /**
     * <pre>
     *结束时间
     * </pre>
     *
     * <code>required int32 endTime = 4;</code>
     */
    public boolean hasEndTime() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *结束时间
     * </pre>
     *
     * <code>required int32 endTime = 4;</code>
     */
    public int getEndTime() {
      return endTime_;
    }
    /**
     * <pre>
     *结束时间
     * </pre>
     *
     * <code>required int32 endTime = 4;</code>
     */
    public Builder setEndTime(int value) {
      bitField0_ |= 0x00000008;
      endTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *结束时间
     * </pre>
     *
     * <code>required int32 endTime = 4;</code>
     */
    public Builder clearEndTime() {
      bitField0_ = (bitField0_ & ~0x00000008);
      endTime_ = 0;
      onChanged();
      return this;
    }

    private int finishTime_ ;
    /**
     * <pre>
     *完成时间
     * </pre>
     *
     * <code>required int32 finishTime = 5;</code>
     */
    public boolean hasFinishTime() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *完成时间
     * </pre>
     *
     * <code>required int32 finishTime = 5;</code>
     */
    public int getFinishTime() {
      return finishTime_;
    }
    /**
     * <pre>
     *完成时间
     * </pre>
     *
     * <code>required int32 finishTime = 5;</code>
     */
    public Builder setFinishTime(int value) {
      bitField0_ |= 0x00000010;
      finishTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *完成时间
     * </pre>
     *
     * <code>required int32 finishTime = 5;</code>
     */
    public Builder clearFinishTime() {
      bitField0_ = (bitField0_ & ~0x00000010);
      finishTime_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList nowFinishInfo_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureNowFinishInfoIsMutable() {
      if (!((bitField0_ & 0x00000020) == 0x00000020)) {
        nowFinishInfo_ = new com.google.protobuf.LazyStringArrayList(nowFinishInfo_);
        bitField0_ |= 0x00000020;
       }
    }
    /**
     * <pre>
     *当前完成者帮派名字集合
     * </pre>
     *
     * <code>repeated string nowFinishInfo = 6;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getNowFinishInfoList() {
      return nowFinishInfo_.getUnmodifiableView();
    }
    /**
     * <pre>
     *当前完成者帮派名字集合
     * </pre>
     *
     * <code>repeated string nowFinishInfo = 6;</code>
     */
    public int getNowFinishInfoCount() {
      return nowFinishInfo_.size();
    }
    /**
     * <pre>
     *当前完成者帮派名字集合
     * </pre>
     *
     * <code>repeated string nowFinishInfo = 6;</code>
     */
    public java.lang.String getNowFinishInfo(int index) {
      return nowFinishInfo_.get(index);
    }
    /**
     * <pre>
     *当前完成者帮派名字集合
     * </pre>
     *
     * <code>repeated string nowFinishInfo = 6;</code>
     */
    public com.google.protobuf.ByteString
        getNowFinishInfoBytes(int index) {
      return nowFinishInfo_.getByteString(index);
    }
    /**
     * <pre>
     *当前完成者帮派名字集合
     * </pre>
     *
     * <code>repeated string nowFinishInfo = 6;</code>
     */
    public Builder setNowFinishInfo(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureNowFinishInfoIsMutable();
      nowFinishInfo_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前完成者帮派名字集合
     * </pre>
     *
     * <code>repeated string nowFinishInfo = 6;</code>
     */
    public Builder addNowFinishInfo(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureNowFinishInfoIsMutable();
      nowFinishInfo_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前完成者帮派名字集合
     * </pre>
     *
     * <code>repeated string nowFinishInfo = 6;</code>
     */
    public Builder addAllNowFinishInfo(
        java.lang.Iterable<java.lang.String> values) {
      ensureNowFinishInfoIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, nowFinishInfo_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前完成者帮派名字集合
     * </pre>
     *
     * <code>repeated string nowFinishInfo = 6;</code>
     */
    public Builder clearNowFinishInfo() {
      nowFinishInfo_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000020);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前完成者帮派名字集合
     * </pre>
     *
     * <code>repeated string nowFinishInfo = 6;</code>
     */
    public Builder addNowFinishInfoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureNowFinishInfoIsMutable();
      nowFinishInfo_.add(value);
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


    // @@protoc_insertion_point(builder_scope:client2server.WtInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.WtInfo)
  private static final pb4client.WtInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.WtInfo();
  }

  public static pb4client.WtInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<WtInfo>
      PARSER = new com.google.protobuf.AbstractParser<WtInfo>() {
    public WtInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new WtInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<WtInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<WtInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.WtInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

