// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 1061
 * 客户端 -&gt; 服务器
 * 秒加速
 * </pre>
 *
 * Protobuf type {@code client2server.ClearTime}
 */
public  final class ClearTime extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.ClearTime)
    ClearTimeOrBuilder {
  // Use ClearTime.newBuilder() to construct.
  private ClearTime(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ClearTime() {
    clearType_ = 0;
    clearPropsId_ = 0;
    clearPropsNum_ = 0;
    extend1_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ClearTime(
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
            clearType_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            clearPropsId_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            clearPropsNum_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            extend1_ = input.readInt64();
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
    return pb4client.War2GamePkt.internal_static_client2server_ClearTime_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_ClearTime_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.ClearTime.class, pb4client.ClearTime.Builder.class);
  }

  private int bitField0_;
  public static final int CLEARTYPE_FIELD_NUMBER = 1;
  private int clearType_;
  /**
   * <pre>
   * 要加速的功能  1-科技 2-造兵 3-秒治疗兵 4-秒建筑 5- 秒锻造  6- 秒武将升星  7-秒武将升阶  8 -秒晋升  9-秒联盟宝藏  12-秒治疗兵(活动)
   * </pre>
   *
   * <code>required int32 clearType = 1;</code>
   */
  public boolean hasClearType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 要加速的功能  1-科技 2-造兵 3-秒治疗兵 4-秒建筑 5- 秒锻造  6- 秒武将升星  7-秒武将升阶  8 -秒晋升  9-秒联盟宝藏  12-秒治疗兵(活动)
   * </pre>
   *
   * <code>required int32 clearType = 1;</code>
   */
  public int getClearType() {
    return clearType_;
  }

  public static final int CLEARPROPSID_FIELD_NUMBER = 2;
  private int clearPropsId_;
  /**
   * <pre>
   * 加速的道具ID 如果这个跟下面的字段都传0的话,就认为是元宝秒所有CD
   * </pre>
   *
   * <code>required int32 clearPropsId = 2;</code>
   */
  public boolean hasClearPropsId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 加速的道具ID 如果这个跟下面的字段都传0的话,就认为是元宝秒所有CD
   * </pre>
   *
   * <code>required int32 clearPropsId = 2;</code>
   */
  public int getClearPropsId() {
    return clearPropsId_;
  }

  public static final int CLEARPROPSNUM_FIELD_NUMBER = 3;
  private int clearPropsNum_;
  /**
   * <pre>
   * 使用的加速道具的数量
   * </pre>
   *
   * <code>required int32 clearPropsNum = 3;</code>
   */
  public boolean hasClearPropsNum() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 使用的加速道具的数量
   * </pre>
   *
   * <code>required int32 clearPropsNum = 3;</code>
   */
  public int getClearPropsNum() {
    return clearPropsNum_;
  }

  public static final int EXTEND1_FIELD_NUMBER = 4;
  private long extend1_;
  /**
   * <pre>
   *扩展字段
   * </pre>
   *
   * <code>required int64 extend1 = 4;</code>
   */
  public boolean hasExtend1() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *扩展字段
   * </pre>
   *
   * <code>required int64 extend1 = 4;</code>
   */
  public long getExtend1() {
    return extend1_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasClearType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasClearPropsId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasClearPropsNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasExtend1()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, clearType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, clearPropsId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, clearPropsNum_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt64(4, extend1_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, clearType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, clearPropsId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, clearPropsNum_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, extend1_);
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
    if (!(obj instanceof pb4client.ClearTime)) {
      return super.equals(obj);
    }
    pb4client.ClearTime other = (pb4client.ClearTime) obj;

    boolean result = true;
    result = result && (hasClearType() == other.hasClearType());
    if (hasClearType()) {
      result = result && (getClearType()
          == other.getClearType());
    }
    result = result && (hasClearPropsId() == other.hasClearPropsId());
    if (hasClearPropsId()) {
      result = result && (getClearPropsId()
          == other.getClearPropsId());
    }
    result = result && (hasClearPropsNum() == other.hasClearPropsNum());
    if (hasClearPropsNum()) {
      result = result && (getClearPropsNum()
          == other.getClearPropsNum());
    }
    result = result && (hasExtend1() == other.hasExtend1());
    if (hasExtend1()) {
      result = result && (getExtend1()
          == other.getExtend1());
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
    if (hasClearType()) {
      hash = (37 * hash) + CLEARTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getClearType();
    }
    if (hasClearPropsId()) {
      hash = (37 * hash) + CLEARPROPSID_FIELD_NUMBER;
      hash = (53 * hash) + getClearPropsId();
    }
    if (hasClearPropsNum()) {
      hash = (37 * hash) + CLEARPROPSNUM_FIELD_NUMBER;
      hash = (53 * hash) + getClearPropsNum();
    }
    if (hasExtend1()) {
      hash = (37 * hash) + EXTEND1_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getExtend1());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.ClearTime parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ClearTime parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ClearTime parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ClearTime parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ClearTime parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ClearTime parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ClearTime parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ClearTime parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ClearTime parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.ClearTime parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ClearTime parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ClearTime parseFrom(
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
  public static Builder newBuilder(pb4client.ClearTime prototype) {
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
   * msgType = 1061
   * 客户端 -&gt; 服务器
   * 秒加速
   * </pre>
   *
   * Protobuf type {@code client2server.ClearTime}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.ClearTime)
      pb4client.ClearTimeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_ClearTime_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_ClearTime_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.ClearTime.class, pb4client.ClearTime.Builder.class);
    }

    // Construct using pb4client.ClearTime.newBuilder()
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
      clearType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      clearPropsId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      clearPropsNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      extend1_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_ClearTime_descriptor;
    }

    public pb4client.ClearTime getDefaultInstanceForType() {
      return pb4client.ClearTime.getDefaultInstance();
    }

    public pb4client.ClearTime build() {
      pb4client.ClearTime result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.ClearTime buildPartial() {
      pb4client.ClearTime result = new pb4client.ClearTime(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.clearType_ = clearType_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.clearPropsId_ = clearPropsId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.clearPropsNum_ = clearPropsNum_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.extend1_ = extend1_;
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
      if (other instanceof pb4client.ClearTime) {
        return mergeFrom((pb4client.ClearTime)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.ClearTime other) {
      if (other == pb4client.ClearTime.getDefaultInstance()) return this;
      if (other.hasClearType()) {
        setClearType(other.getClearType());
      }
      if (other.hasClearPropsId()) {
        setClearPropsId(other.getClearPropsId());
      }
      if (other.hasClearPropsNum()) {
        setClearPropsNum(other.getClearPropsNum());
      }
      if (other.hasExtend1()) {
        setExtend1(other.getExtend1());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasClearType()) {
        return false;
      }
      if (!hasClearPropsId()) {
        return false;
      }
      if (!hasClearPropsNum()) {
        return false;
      }
      if (!hasExtend1()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.ClearTime parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.ClearTime) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int clearType_ ;
    /**
     * <pre>
     * 要加速的功能  1-科技 2-造兵 3-秒治疗兵 4-秒建筑 5- 秒锻造  6- 秒武将升星  7-秒武将升阶  8 -秒晋升  9-秒联盟宝藏  12-秒治疗兵(活动)
     * </pre>
     *
     * <code>required int32 clearType = 1;</code>
     */
    public boolean hasClearType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 要加速的功能  1-科技 2-造兵 3-秒治疗兵 4-秒建筑 5- 秒锻造  6- 秒武将升星  7-秒武将升阶  8 -秒晋升  9-秒联盟宝藏  12-秒治疗兵(活动)
     * </pre>
     *
     * <code>required int32 clearType = 1;</code>
     */
    public int getClearType() {
      return clearType_;
    }
    /**
     * <pre>
     * 要加速的功能  1-科技 2-造兵 3-秒治疗兵 4-秒建筑 5- 秒锻造  6- 秒武将升星  7-秒武将升阶  8 -秒晋升  9-秒联盟宝藏  12-秒治疗兵(活动)
     * </pre>
     *
     * <code>required int32 clearType = 1;</code>
     */
    public Builder setClearType(int value) {
      bitField0_ |= 0x00000001;
      clearType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 要加速的功能  1-科技 2-造兵 3-秒治疗兵 4-秒建筑 5- 秒锻造  6- 秒武将升星  7-秒武将升阶  8 -秒晋升  9-秒联盟宝藏  12-秒治疗兵(活动)
     * </pre>
     *
     * <code>required int32 clearType = 1;</code>
     */
    public Builder clearClearType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      clearType_ = 0;
      onChanged();
      return this;
    }

    private int clearPropsId_ ;
    /**
     * <pre>
     * 加速的道具ID 如果这个跟下面的字段都传0的话,就认为是元宝秒所有CD
     * </pre>
     *
     * <code>required int32 clearPropsId = 2;</code>
     */
    public boolean hasClearPropsId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 加速的道具ID 如果这个跟下面的字段都传0的话,就认为是元宝秒所有CD
     * </pre>
     *
     * <code>required int32 clearPropsId = 2;</code>
     */
    public int getClearPropsId() {
      return clearPropsId_;
    }
    /**
     * <pre>
     * 加速的道具ID 如果这个跟下面的字段都传0的话,就认为是元宝秒所有CD
     * </pre>
     *
     * <code>required int32 clearPropsId = 2;</code>
     */
    public Builder setClearPropsId(int value) {
      bitField0_ |= 0x00000002;
      clearPropsId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 加速的道具ID 如果这个跟下面的字段都传0的话,就认为是元宝秒所有CD
     * </pre>
     *
     * <code>required int32 clearPropsId = 2;</code>
     */
    public Builder clearClearPropsId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      clearPropsId_ = 0;
      onChanged();
      return this;
    }

    private int clearPropsNum_ ;
    /**
     * <pre>
     * 使用的加速道具的数量
     * </pre>
     *
     * <code>required int32 clearPropsNum = 3;</code>
     */
    public boolean hasClearPropsNum() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 使用的加速道具的数量
     * </pre>
     *
     * <code>required int32 clearPropsNum = 3;</code>
     */
    public int getClearPropsNum() {
      return clearPropsNum_;
    }
    /**
     * <pre>
     * 使用的加速道具的数量
     * </pre>
     *
     * <code>required int32 clearPropsNum = 3;</code>
     */
    public Builder setClearPropsNum(int value) {
      bitField0_ |= 0x00000004;
      clearPropsNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 使用的加速道具的数量
     * </pre>
     *
     * <code>required int32 clearPropsNum = 3;</code>
     */
    public Builder clearClearPropsNum() {
      bitField0_ = (bitField0_ & ~0x00000004);
      clearPropsNum_ = 0;
      onChanged();
      return this;
    }

    private long extend1_ ;
    /**
     * <pre>
     *扩展字段
     * </pre>
     *
     * <code>required int64 extend1 = 4;</code>
     */
    public boolean hasExtend1() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *扩展字段
     * </pre>
     *
     * <code>required int64 extend1 = 4;</code>
     */
    public long getExtend1() {
      return extend1_;
    }
    /**
     * <pre>
     *扩展字段
     * </pre>
     *
     * <code>required int64 extend1 = 4;</code>
     */
    public Builder setExtend1(long value) {
      bitField0_ |= 0x00000008;
      extend1_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *扩展字段
     * </pre>
     *
     * <code>required int64 extend1 = 4;</code>
     */
    public Builder clearExtend1() {
      bitField0_ = (bitField0_ & ~0x00000008);
      extend1_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:client2server.ClearTime)
  }

  // @@protoc_insertion_point(class_scope:client2server.ClearTime)
  private static final pb4client.ClearTime DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.ClearTime();
  }

  public static pb4client.ClearTime getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ClearTime>
      PARSER = new com.google.protobuf.AbstractParser<ClearTime>() {
    public ClearTime parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ClearTime(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ClearTime> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ClearTime> getParserForType() {
    return PARSER;
  }

  public pb4client.ClearTime getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

