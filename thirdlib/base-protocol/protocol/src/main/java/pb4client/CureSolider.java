// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 1084
 * 客户端 -&gt; 服务器
 * 治疗兵
 * </pre>
 *
 * Protobuf type {@code client2server.CureSolider}
 */
public  final class CureSolider extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.CureSolider)
    CureSoliderOrBuilder {
  // Use CureSolider.newBuilder() to construct.
  private CureSolider(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CureSolider() {
    cureType_ = 0;
    cureSoliderInfo_ = java.util.Collections.emptyList();
    trapOrSolider_ = 0;
    eventCure_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CureSolider(
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
            cureType_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              cureSoliderInfo_ = new java.util.ArrayList<pb4client.CureSoliderInfo>();
              mutable_bitField0_ |= 0x00000002;
            }
            cureSoliderInfo_.add(
                input.readMessage(pb4client.CureSoliderInfo.PARSER, extensionRegistry));
            break;
          }
          case 24: {
            bitField0_ |= 0x00000002;
            trapOrSolider_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000004;
            eventCure_ = input.readInt32();
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
        cureSoliderInfo_ = java.util.Collections.unmodifiableList(cureSoliderInfo_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_CureSolider_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_CureSolider_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.CureSolider.class, pb4client.CureSolider.Builder.class);
  }

  private int bitField0_;
  public static final int CURETYPE_FIELD_NUMBER = 1;
  private int cureType_;
  /**
   * <pre>
   * 治疗类型  1-普通治疗  2-元宝补齐资源治疗
   * </pre>
   *
   * <code>required int32 cureType = 1;</code>
   */
  public boolean hasCureType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 治疗类型  1-普通治疗  2-元宝补齐资源治疗
   * </pre>
   *
   * <code>required int32 cureType = 1;</code>
   */
  public int getCureType() {
    return cureType_;
  }

  public static final int CURESOLIDERINFO_FIELD_NUMBER = 2;
  private java.util.List<pb4client.CureSoliderInfo> cureSoliderInfo_;
  /**
   * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
   */
  public java.util.List<pb4client.CureSoliderInfo> getCureSoliderInfoList() {
    return cureSoliderInfo_;
  }
  /**
   * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
   */
  public java.util.List<? extends pb4client.CureSoliderInfoOrBuilder> 
      getCureSoliderInfoOrBuilderList() {
    return cureSoliderInfo_;
  }
  /**
   * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
   */
  public int getCureSoliderInfoCount() {
    return cureSoliderInfo_.size();
  }
  /**
   * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
   */
  public pb4client.CureSoliderInfo getCureSoliderInfo(int index) {
    return cureSoliderInfo_.get(index);
  }
  /**
   * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
   */
  public pb4client.CureSoliderInfoOrBuilder getCureSoliderInfoOrBuilder(
      int index) {
    return cureSoliderInfo_.get(index);
  }

  public static final int TRAPORSOLIDER_FIELD_NUMBER = 3;
  private int trapOrSolider_;
  /**
   * <pre>
   * 类型  1-士兵  2-陷阱
   * </pre>
   *
   * <code>required int32 trapOrSolider = 3;</code>
   */
  public boolean hasTrapOrSolider() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 类型  1-士兵  2-陷阱
   * </pre>
   *
   * <code>required int32 trapOrSolider = 3;</code>
   */
  public int getTrapOrSolider() {
    return trapOrSolider_;
  }

  public static final int EVENTCURE_FIELD_NUMBER = 4;
  private int eventCure_;
  /**
   * <pre>
   * 活动治疗 1-活动治疗 其他-默认普通治疗
   * </pre>
   *
   * <code>optional int32 eventCure = 4;</code>
   */
  public boolean hasEventCure() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 活动治疗 1-活动治疗 其他-默认普通治疗
   * </pre>
   *
   * <code>optional int32 eventCure = 4;</code>
   */
  public int getEventCure() {
    return eventCure_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasCureType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasTrapOrSolider()) {
      memoizedIsInitialized = 0;
      return false;
    }
    for (int i = 0; i < getCureSoliderInfoCount(); i++) {
      if (!getCureSoliderInfo(i).isInitialized()) {
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
      output.writeInt32(1, cureType_);
    }
    for (int i = 0; i < cureSoliderInfo_.size(); i++) {
      output.writeMessage(2, cureSoliderInfo_.get(i));
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(3, trapOrSolider_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(4, eventCure_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, cureType_);
    }
    for (int i = 0; i < cureSoliderInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, cureSoliderInfo_.get(i));
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, trapOrSolider_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, eventCure_);
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
    if (!(obj instanceof pb4client.CureSolider)) {
      return super.equals(obj);
    }
    pb4client.CureSolider other = (pb4client.CureSolider) obj;

    boolean result = true;
    result = result && (hasCureType() == other.hasCureType());
    if (hasCureType()) {
      result = result && (getCureType()
          == other.getCureType());
    }
    result = result && getCureSoliderInfoList()
        .equals(other.getCureSoliderInfoList());
    result = result && (hasTrapOrSolider() == other.hasTrapOrSolider());
    if (hasTrapOrSolider()) {
      result = result && (getTrapOrSolider()
          == other.getTrapOrSolider());
    }
    result = result && (hasEventCure() == other.hasEventCure());
    if (hasEventCure()) {
      result = result && (getEventCure()
          == other.getEventCure());
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
    if (hasCureType()) {
      hash = (37 * hash) + CURETYPE_FIELD_NUMBER;
      hash = (53 * hash) + getCureType();
    }
    if (getCureSoliderInfoCount() > 0) {
      hash = (37 * hash) + CURESOLIDERINFO_FIELD_NUMBER;
      hash = (53 * hash) + getCureSoliderInfoList().hashCode();
    }
    if (hasTrapOrSolider()) {
      hash = (37 * hash) + TRAPORSOLIDER_FIELD_NUMBER;
      hash = (53 * hash) + getTrapOrSolider();
    }
    if (hasEventCure()) {
      hash = (37 * hash) + EVENTCURE_FIELD_NUMBER;
      hash = (53 * hash) + getEventCure();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.CureSolider parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CureSolider parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CureSolider parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CureSolider parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CureSolider parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CureSolider parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CureSolider parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.CureSolider parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.CureSolider parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.CureSolider parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.CureSolider parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.CureSolider parseFrom(
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
  public static Builder newBuilder(pb4client.CureSolider prototype) {
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
   * msgType = 1084
   * 客户端 -&gt; 服务器
   * 治疗兵
   * </pre>
   *
   * Protobuf type {@code client2server.CureSolider}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.CureSolider)
      pb4client.CureSoliderOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_CureSolider_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_CureSolider_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.CureSolider.class, pb4client.CureSolider.Builder.class);
    }

    // Construct using pb4client.CureSolider.newBuilder()
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
        getCureSoliderInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      cureType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (cureSoliderInfoBuilder_ == null) {
        cureSoliderInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        cureSoliderInfoBuilder_.clear();
      }
      trapOrSolider_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      eventCure_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_CureSolider_descriptor;
    }

    public pb4client.CureSolider getDefaultInstanceForType() {
      return pb4client.CureSolider.getDefaultInstance();
    }

    public pb4client.CureSolider build() {
      pb4client.CureSolider result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.CureSolider buildPartial() {
      pb4client.CureSolider result = new pb4client.CureSolider(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.cureType_ = cureType_;
      if (cureSoliderInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          cureSoliderInfo_ = java.util.Collections.unmodifiableList(cureSoliderInfo_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.cureSoliderInfo_ = cureSoliderInfo_;
      } else {
        result.cureSoliderInfo_ = cureSoliderInfoBuilder_.build();
      }
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000002;
      }
      result.trapOrSolider_ = trapOrSolider_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000004;
      }
      result.eventCure_ = eventCure_;
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
      if (other instanceof pb4client.CureSolider) {
        return mergeFrom((pb4client.CureSolider)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.CureSolider other) {
      if (other == pb4client.CureSolider.getDefaultInstance()) return this;
      if (other.hasCureType()) {
        setCureType(other.getCureType());
      }
      if (cureSoliderInfoBuilder_ == null) {
        if (!other.cureSoliderInfo_.isEmpty()) {
          if (cureSoliderInfo_.isEmpty()) {
            cureSoliderInfo_ = other.cureSoliderInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureCureSoliderInfoIsMutable();
            cureSoliderInfo_.addAll(other.cureSoliderInfo_);
          }
          onChanged();
        }
      } else {
        if (!other.cureSoliderInfo_.isEmpty()) {
          if (cureSoliderInfoBuilder_.isEmpty()) {
            cureSoliderInfoBuilder_.dispose();
            cureSoliderInfoBuilder_ = null;
            cureSoliderInfo_ = other.cureSoliderInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
            cureSoliderInfoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getCureSoliderInfoFieldBuilder() : null;
          } else {
            cureSoliderInfoBuilder_.addAllMessages(other.cureSoliderInfo_);
          }
        }
      }
      if (other.hasTrapOrSolider()) {
        setTrapOrSolider(other.getTrapOrSolider());
      }
      if (other.hasEventCure()) {
        setEventCure(other.getEventCure());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasCureType()) {
        return false;
      }
      if (!hasTrapOrSolider()) {
        return false;
      }
      for (int i = 0; i < getCureSoliderInfoCount(); i++) {
        if (!getCureSoliderInfo(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.CureSolider parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.CureSolider) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int cureType_ ;
    /**
     * <pre>
     * 治疗类型  1-普通治疗  2-元宝补齐资源治疗
     * </pre>
     *
     * <code>required int32 cureType = 1;</code>
     */
    public boolean hasCureType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 治疗类型  1-普通治疗  2-元宝补齐资源治疗
     * </pre>
     *
     * <code>required int32 cureType = 1;</code>
     */
    public int getCureType() {
      return cureType_;
    }
    /**
     * <pre>
     * 治疗类型  1-普通治疗  2-元宝补齐资源治疗
     * </pre>
     *
     * <code>required int32 cureType = 1;</code>
     */
    public Builder setCureType(int value) {
      bitField0_ |= 0x00000001;
      cureType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 治疗类型  1-普通治疗  2-元宝补齐资源治疗
     * </pre>
     *
     * <code>required int32 cureType = 1;</code>
     */
    public Builder clearCureType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      cureType_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.CureSoliderInfo> cureSoliderInfo_ =
      java.util.Collections.emptyList();
    private void ensureCureSoliderInfoIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        cureSoliderInfo_ = new java.util.ArrayList<pb4client.CureSoliderInfo>(cureSoliderInfo_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.CureSoliderInfo, pb4client.CureSoliderInfo.Builder, pb4client.CureSoliderInfoOrBuilder> cureSoliderInfoBuilder_;

    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public java.util.List<pb4client.CureSoliderInfo> getCureSoliderInfoList() {
      if (cureSoliderInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(cureSoliderInfo_);
      } else {
        return cureSoliderInfoBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public int getCureSoliderInfoCount() {
      if (cureSoliderInfoBuilder_ == null) {
        return cureSoliderInfo_.size();
      } else {
        return cureSoliderInfoBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public pb4client.CureSoliderInfo getCureSoliderInfo(int index) {
      if (cureSoliderInfoBuilder_ == null) {
        return cureSoliderInfo_.get(index);
      } else {
        return cureSoliderInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public Builder setCureSoliderInfo(
        int index, pb4client.CureSoliderInfo value) {
      if (cureSoliderInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCureSoliderInfoIsMutable();
        cureSoliderInfo_.set(index, value);
        onChanged();
      } else {
        cureSoliderInfoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public Builder setCureSoliderInfo(
        int index, pb4client.CureSoliderInfo.Builder builderForValue) {
      if (cureSoliderInfoBuilder_ == null) {
        ensureCureSoliderInfoIsMutable();
        cureSoliderInfo_.set(index, builderForValue.build());
        onChanged();
      } else {
        cureSoliderInfoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public Builder addCureSoliderInfo(pb4client.CureSoliderInfo value) {
      if (cureSoliderInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCureSoliderInfoIsMutable();
        cureSoliderInfo_.add(value);
        onChanged();
      } else {
        cureSoliderInfoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public Builder addCureSoliderInfo(
        int index, pb4client.CureSoliderInfo value) {
      if (cureSoliderInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCureSoliderInfoIsMutable();
        cureSoliderInfo_.add(index, value);
        onChanged();
      } else {
        cureSoliderInfoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public Builder addCureSoliderInfo(
        pb4client.CureSoliderInfo.Builder builderForValue) {
      if (cureSoliderInfoBuilder_ == null) {
        ensureCureSoliderInfoIsMutable();
        cureSoliderInfo_.add(builderForValue.build());
        onChanged();
      } else {
        cureSoliderInfoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public Builder addCureSoliderInfo(
        int index, pb4client.CureSoliderInfo.Builder builderForValue) {
      if (cureSoliderInfoBuilder_ == null) {
        ensureCureSoliderInfoIsMutable();
        cureSoliderInfo_.add(index, builderForValue.build());
        onChanged();
      } else {
        cureSoliderInfoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public Builder addAllCureSoliderInfo(
        java.lang.Iterable<? extends pb4client.CureSoliderInfo> values) {
      if (cureSoliderInfoBuilder_ == null) {
        ensureCureSoliderInfoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, cureSoliderInfo_);
        onChanged();
      } else {
        cureSoliderInfoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public Builder clearCureSoliderInfo() {
      if (cureSoliderInfoBuilder_ == null) {
        cureSoliderInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        cureSoliderInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public Builder removeCureSoliderInfo(int index) {
      if (cureSoliderInfoBuilder_ == null) {
        ensureCureSoliderInfoIsMutable();
        cureSoliderInfo_.remove(index);
        onChanged();
      } else {
        cureSoliderInfoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public pb4client.CureSoliderInfo.Builder getCureSoliderInfoBuilder(
        int index) {
      return getCureSoliderInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public pb4client.CureSoliderInfoOrBuilder getCureSoliderInfoOrBuilder(
        int index) {
      if (cureSoliderInfoBuilder_ == null) {
        return cureSoliderInfo_.get(index);  } else {
        return cureSoliderInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public java.util.List<? extends pb4client.CureSoliderInfoOrBuilder> 
         getCureSoliderInfoOrBuilderList() {
      if (cureSoliderInfoBuilder_ != null) {
        return cureSoliderInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(cureSoliderInfo_);
      }
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public pb4client.CureSoliderInfo.Builder addCureSoliderInfoBuilder() {
      return getCureSoliderInfoFieldBuilder().addBuilder(
          pb4client.CureSoliderInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public pb4client.CureSoliderInfo.Builder addCureSoliderInfoBuilder(
        int index) {
      return getCureSoliderInfoFieldBuilder().addBuilder(
          index, pb4client.CureSoliderInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
     */
    public java.util.List<pb4client.CureSoliderInfo.Builder> 
         getCureSoliderInfoBuilderList() {
      return getCureSoliderInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.CureSoliderInfo, pb4client.CureSoliderInfo.Builder, pb4client.CureSoliderInfoOrBuilder> 
        getCureSoliderInfoFieldBuilder() {
      if (cureSoliderInfoBuilder_ == null) {
        cureSoliderInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.CureSoliderInfo, pb4client.CureSoliderInfo.Builder, pb4client.CureSoliderInfoOrBuilder>(
                cureSoliderInfo_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        cureSoliderInfo_ = null;
      }
      return cureSoliderInfoBuilder_;
    }

    private int trapOrSolider_ ;
    /**
     * <pre>
     * 类型  1-士兵  2-陷阱
     * </pre>
     *
     * <code>required int32 trapOrSolider = 3;</code>
     */
    public boolean hasTrapOrSolider() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 类型  1-士兵  2-陷阱
     * </pre>
     *
     * <code>required int32 trapOrSolider = 3;</code>
     */
    public int getTrapOrSolider() {
      return trapOrSolider_;
    }
    /**
     * <pre>
     * 类型  1-士兵  2-陷阱
     * </pre>
     *
     * <code>required int32 trapOrSolider = 3;</code>
     */
    public Builder setTrapOrSolider(int value) {
      bitField0_ |= 0x00000004;
      trapOrSolider_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 类型  1-士兵  2-陷阱
     * </pre>
     *
     * <code>required int32 trapOrSolider = 3;</code>
     */
    public Builder clearTrapOrSolider() {
      bitField0_ = (bitField0_ & ~0x00000004);
      trapOrSolider_ = 0;
      onChanged();
      return this;
    }

    private int eventCure_ ;
    /**
     * <pre>
     * 活动治疗 1-活动治疗 其他-默认普通治疗
     * </pre>
     *
     * <code>optional int32 eventCure = 4;</code>
     */
    public boolean hasEventCure() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 活动治疗 1-活动治疗 其他-默认普通治疗
     * </pre>
     *
     * <code>optional int32 eventCure = 4;</code>
     */
    public int getEventCure() {
      return eventCure_;
    }
    /**
     * <pre>
     * 活动治疗 1-活动治疗 其他-默认普通治疗
     * </pre>
     *
     * <code>optional int32 eventCure = 4;</code>
     */
    public Builder setEventCure(int value) {
      bitField0_ |= 0x00000008;
      eventCure_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 活动治疗 1-活动治疗 其他-默认普通治疗
     * </pre>
     *
     * <code>optional int32 eventCure = 4;</code>
     */
    public Builder clearEventCure() {
      bitField0_ = (bitField0_ & ~0x00000008);
      eventCure_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.CureSolider)
  }

  // @@protoc_insertion_point(class_scope:client2server.CureSolider)
  private static final pb4client.CureSolider DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.CureSolider();
  }

  public static pb4client.CureSolider getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<CureSolider>
      PARSER = new com.google.protobuf.AbstractParser<CureSolider>() {
    public CureSolider parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new CureSolider(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CureSolider> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CureSolider> getParserForType() {
    return PARSER;
  }

  public pb4client.CureSolider getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
