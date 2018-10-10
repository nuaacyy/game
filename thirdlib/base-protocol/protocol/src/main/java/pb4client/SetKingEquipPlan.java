// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 1227
 * 客户端 -&gt; 服务器
 * 设置一个君主装备预设
 * </pre>
 *
 * Protobuf type {@code client2server.SetKingEquipPlan}
 */
public  final class SetKingEquipPlan extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.SetKingEquipPlan)
    SetKingEquipPlanOrBuilder {
  // Use SetKingEquipPlan.newBuilder() to construct.
  private SetKingEquipPlan(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SetKingEquipPlan() {
    planId_ = 0;
    planName_ = "";
    plan_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SetKingEquipPlan(
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
            planId_ = input.readInt32();
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            planName_ = bs;
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              plan_ = new java.util.ArrayList<pb4client.PlanVo>();
              mutable_bitField0_ |= 0x00000004;
            }
            plan_.add(
                input.readMessage(pb4client.PlanVo.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        plan_ = java.util.Collections.unmodifiableList(plan_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_SetKingEquipPlan_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_SetKingEquipPlan_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.SetKingEquipPlan.class, pb4client.SetKingEquipPlan.Builder.class);
  }

  private int bitField0_;
  public static final int PLANID_FIELD_NUMBER = 1;
  private int planId_;
  /**
   * <pre>
   * 排序ID
   * </pre>
   *
   * <code>required int32 planId = 1;</code>
   */
  public boolean hasPlanId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 排序ID
   * </pre>
   *
   * <code>required int32 planId = 1;</code>
   */
  public int getPlanId() {
    return planId_;
  }

  public static final int PLANNAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object planName_;
  /**
   * <pre>
   * 名字
   * </pre>
   *
   * <code>required string planName = 2;</code>
   */
  public boolean hasPlanName() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 名字
   * </pre>
   *
   * <code>required string planName = 2;</code>
   */
  public java.lang.String getPlanName() {
    java.lang.Object ref = planName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        planName_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 名字
   * </pre>
   *
   * <code>required string planName = 2;</code>
   */
  public com.google.protobuf.ByteString
      getPlanNameBytes() {
    java.lang.Object ref = planName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      planName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PLAN_FIELD_NUMBER = 3;
  private java.util.List<pb4client.PlanVo> plan_;
  /**
   * <pre>
   * 预设内容
   * </pre>
   *
   * <code>repeated .client2server.PlanVo plan = 3;</code>
   */
  public java.util.List<pb4client.PlanVo> getPlanList() {
    return plan_;
  }
  /**
   * <pre>
   * 预设内容
   * </pre>
   *
   * <code>repeated .client2server.PlanVo plan = 3;</code>
   */
  public java.util.List<? extends pb4client.PlanVoOrBuilder> 
      getPlanOrBuilderList() {
    return plan_;
  }
  /**
   * <pre>
   * 预设内容
   * </pre>
   *
   * <code>repeated .client2server.PlanVo plan = 3;</code>
   */
  public int getPlanCount() {
    return plan_.size();
  }
  /**
   * <pre>
   * 预设内容
   * </pre>
   *
   * <code>repeated .client2server.PlanVo plan = 3;</code>
   */
  public pb4client.PlanVo getPlan(int index) {
    return plan_.get(index);
  }
  /**
   * <pre>
   * 预设内容
   * </pre>
   *
   * <code>repeated .client2server.PlanVo plan = 3;</code>
   */
  public pb4client.PlanVoOrBuilder getPlanOrBuilder(
      int index) {
    return plan_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasPlanId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPlanName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    for (int i = 0; i < getPlanCount(); i++) {
      if (!getPlan(i).isInitialized()) {
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
      output.writeInt32(1, planId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, planName_);
    }
    for (int i = 0; i < plan_.size(); i++) {
      output.writeMessage(3, plan_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, planId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, planName_);
    }
    for (int i = 0; i < plan_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, plan_.get(i));
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
    if (!(obj instanceof pb4client.SetKingEquipPlan)) {
      return super.equals(obj);
    }
    pb4client.SetKingEquipPlan other = (pb4client.SetKingEquipPlan) obj;

    boolean result = true;
    result = result && (hasPlanId() == other.hasPlanId());
    if (hasPlanId()) {
      result = result && (getPlanId()
          == other.getPlanId());
    }
    result = result && (hasPlanName() == other.hasPlanName());
    if (hasPlanName()) {
      result = result && getPlanName()
          .equals(other.getPlanName());
    }
    result = result && getPlanList()
        .equals(other.getPlanList());
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
    if (hasPlanId()) {
      hash = (37 * hash) + PLANID_FIELD_NUMBER;
      hash = (53 * hash) + getPlanId();
    }
    if (hasPlanName()) {
      hash = (37 * hash) + PLANNAME_FIELD_NUMBER;
      hash = (53 * hash) + getPlanName().hashCode();
    }
    if (getPlanCount() > 0) {
      hash = (37 * hash) + PLAN_FIELD_NUMBER;
      hash = (53 * hash) + getPlanList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.SetKingEquipPlan parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SetKingEquipPlan parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SetKingEquipPlan parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SetKingEquipPlan parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SetKingEquipPlan parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SetKingEquipPlan parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SetKingEquipPlan parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SetKingEquipPlan parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SetKingEquipPlan parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.SetKingEquipPlan parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SetKingEquipPlan parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SetKingEquipPlan parseFrom(
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
  public static Builder newBuilder(pb4client.SetKingEquipPlan prototype) {
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
   * msgType = 1227
   * 客户端 -&gt; 服务器
   * 设置一个君主装备预设
   * </pre>
   *
   * Protobuf type {@code client2server.SetKingEquipPlan}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.SetKingEquipPlan)
      pb4client.SetKingEquipPlanOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_SetKingEquipPlan_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_SetKingEquipPlan_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.SetKingEquipPlan.class, pb4client.SetKingEquipPlan.Builder.class);
    }

    // Construct using pb4client.SetKingEquipPlan.newBuilder()
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
        getPlanFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      planId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      planName_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      if (planBuilder_ == null) {
        plan_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        planBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_SetKingEquipPlan_descriptor;
    }

    public pb4client.SetKingEquipPlan getDefaultInstanceForType() {
      return pb4client.SetKingEquipPlan.getDefaultInstance();
    }

    public pb4client.SetKingEquipPlan build() {
      pb4client.SetKingEquipPlan result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.SetKingEquipPlan buildPartial() {
      pb4client.SetKingEquipPlan result = new pb4client.SetKingEquipPlan(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.planId_ = planId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.planName_ = planName_;
      if (planBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          plan_ = java.util.Collections.unmodifiableList(plan_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.plan_ = plan_;
      } else {
        result.plan_ = planBuilder_.build();
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
      if (other instanceof pb4client.SetKingEquipPlan) {
        return mergeFrom((pb4client.SetKingEquipPlan)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.SetKingEquipPlan other) {
      if (other == pb4client.SetKingEquipPlan.getDefaultInstance()) return this;
      if (other.hasPlanId()) {
        setPlanId(other.getPlanId());
      }
      if (other.hasPlanName()) {
        bitField0_ |= 0x00000002;
        planName_ = other.planName_;
        onChanged();
      }
      if (planBuilder_ == null) {
        if (!other.plan_.isEmpty()) {
          if (plan_.isEmpty()) {
            plan_ = other.plan_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensurePlanIsMutable();
            plan_.addAll(other.plan_);
          }
          onChanged();
        }
      } else {
        if (!other.plan_.isEmpty()) {
          if (planBuilder_.isEmpty()) {
            planBuilder_.dispose();
            planBuilder_ = null;
            plan_ = other.plan_;
            bitField0_ = (bitField0_ & ~0x00000004);
            planBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getPlanFieldBuilder() : null;
          } else {
            planBuilder_.addAllMessages(other.plan_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasPlanId()) {
        return false;
      }
      if (!hasPlanName()) {
        return false;
      }
      for (int i = 0; i < getPlanCount(); i++) {
        if (!getPlan(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.SetKingEquipPlan parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.SetKingEquipPlan) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int planId_ ;
    /**
     * <pre>
     * 排序ID
     * </pre>
     *
     * <code>required int32 planId = 1;</code>
     */
    public boolean hasPlanId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 排序ID
     * </pre>
     *
     * <code>required int32 planId = 1;</code>
     */
    public int getPlanId() {
      return planId_;
    }
    /**
     * <pre>
     * 排序ID
     * </pre>
     *
     * <code>required int32 planId = 1;</code>
     */
    public Builder setPlanId(int value) {
      bitField0_ |= 0x00000001;
      planId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 排序ID
     * </pre>
     *
     * <code>required int32 planId = 1;</code>
     */
    public Builder clearPlanId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      planId_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object planName_ = "";
    /**
     * <pre>
     * 名字
     * </pre>
     *
     * <code>required string planName = 2;</code>
     */
    public boolean hasPlanName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 名字
     * </pre>
     *
     * <code>required string planName = 2;</code>
     */
    public java.lang.String getPlanName() {
      java.lang.Object ref = planName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          planName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 名字
     * </pre>
     *
     * <code>required string planName = 2;</code>
     */
    public com.google.protobuf.ByteString
        getPlanNameBytes() {
      java.lang.Object ref = planName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        planName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 名字
     * </pre>
     *
     * <code>required string planName = 2;</code>
     */
    public Builder setPlanName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      planName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 名字
     * </pre>
     *
     * <code>required string planName = 2;</code>
     */
    public Builder clearPlanName() {
      bitField0_ = (bitField0_ & ~0x00000002);
      planName_ = getDefaultInstance().getPlanName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 名字
     * </pre>
     *
     * <code>required string planName = 2;</code>
     */
    public Builder setPlanNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      planName_ = value;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.PlanVo> plan_ =
      java.util.Collections.emptyList();
    private void ensurePlanIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        plan_ = new java.util.ArrayList<pb4client.PlanVo>(plan_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.PlanVo, pb4client.PlanVo.Builder, pb4client.PlanVoOrBuilder> planBuilder_;

    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public java.util.List<pb4client.PlanVo> getPlanList() {
      if (planBuilder_ == null) {
        return java.util.Collections.unmodifiableList(plan_);
      } else {
        return planBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public int getPlanCount() {
      if (planBuilder_ == null) {
        return plan_.size();
      } else {
        return planBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public pb4client.PlanVo getPlan(int index) {
      if (planBuilder_ == null) {
        return plan_.get(index);
      } else {
        return planBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public Builder setPlan(
        int index, pb4client.PlanVo value) {
      if (planBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePlanIsMutable();
        plan_.set(index, value);
        onChanged();
      } else {
        planBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public Builder setPlan(
        int index, pb4client.PlanVo.Builder builderForValue) {
      if (planBuilder_ == null) {
        ensurePlanIsMutable();
        plan_.set(index, builderForValue.build());
        onChanged();
      } else {
        planBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public Builder addPlan(pb4client.PlanVo value) {
      if (planBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePlanIsMutable();
        plan_.add(value);
        onChanged();
      } else {
        planBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public Builder addPlan(
        int index, pb4client.PlanVo value) {
      if (planBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePlanIsMutable();
        plan_.add(index, value);
        onChanged();
      } else {
        planBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public Builder addPlan(
        pb4client.PlanVo.Builder builderForValue) {
      if (planBuilder_ == null) {
        ensurePlanIsMutable();
        plan_.add(builderForValue.build());
        onChanged();
      } else {
        planBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public Builder addPlan(
        int index, pb4client.PlanVo.Builder builderForValue) {
      if (planBuilder_ == null) {
        ensurePlanIsMutable();
        plan_.add(index, builderForValue.build());
        onChanged();
      } else {
        planBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public Builder addAllPlan(
        java.lang.Iterable<? extends pb4client.PlanVo> values) {
      if (planBuilder_ == null) {
        ensurePlanIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, plan_);
        onChanged();
      } else {
        planBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public Builder clearPlan() {
      if (planBuilder_ == null) {
        plan_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        planBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public Builder removePlan(int index) {
      if (planBuilder_ == null) {
        ensurePlanIsMutable();
        plan_.remove(index);
        onChanged();
      } else {
        planBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public pb4client.PlanVo.Builder getPlanBuilder(
        int index) {
      return getPlanFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public pb4client.PlanVoOrBuilder getPlanOrBuilder(
        int index) {
      if (planBuilder_ == null) {
        return plan_.get(index);  } else {
        return planBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public java.util.List<? extends pb4client.PlanVoOrBuilder> 
         getPlanOrBuilderList() {
      if (planBuilder_ != null) {
        return planBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(plan_);
      }
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public pb4client.PlanVo.Builder addPlanBuilder() {
      return getPlanFieldBuilder().addBuilder(
          pb4client.PlanVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public pb4client.PlanVo.Builder addPlanBuilder(
        int index) {
      return getPlanFieldBuilder().addBuilder(
          index, pb4client.PlanVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 预设内容
     * </pre>
     *
     * <code>repeated .client2server.PlanVo plan = 3;</code>
     */
    public java.util.List<pb4client.PlanVo.Builder> 
         getPlanBuilderList() {
      return getPlanFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.PlanVo, pb4client.PlanVo.Builder, pb4client.PlanVoOrBuilder> 
        getPlanFieldBuilder() {
      if (planBuilder_ == null) {
        planBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.PlanVo, pb4client.PlanVo.Builder, pb4client.PlanVoOrBuilder>(
                plan_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        plan_ = null;
      }
      return planBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.SetKingEquipPlan)
  }

  // @@protoc_insertion_point(class_scope:client2server.SetKingEquipPlan)
  private static final pb4client.SetKingEquipPlan DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.SetKingEquipPlan();
  }

  public static pb4client.SetKingEquipPlan getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<SetKingEquipPlan>
      PARSER = new com.google.protobuf.AbstractParser<SetKingEquipPlan>() {
    public SetKingEquipPlan parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SetKingEquipPlan(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SetKingEquipPlan> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SetKingEquipPlan> getParserForType() {
    return PARSER;
  }

  public pb4client.SetKingEquipPlan getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

