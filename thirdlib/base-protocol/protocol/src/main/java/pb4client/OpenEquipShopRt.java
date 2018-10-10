// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.OpenEquipShopRt}
 */
public  final class OpenEquipShopRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.OpenEquipShopRt)
    OpenEquipShopRtOrBuilder {
  // Use OpenEquipShopRt.newBuilder() to construct.
  private OpenEquipShopRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OpenEquipShopRt() {
    rt_ = 0;
    cdOverSec_ = 0;
    equipInfo_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private OpenEquipShopRt(
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
          case 32: {
            bitField0_ |= 0x00000002;
            cdOverSec_ = input.readInt32();
            break;
          }
          case 42: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              equipInfo_ = new java.util.ArrayList<pb4client.EquipShopInfo>();
              mutable_bitField0_ |= 0x00000004;
            }
            equipInfo_.add(
                input.readMessage(pb4client.EquipShopInfo.PARSER, extensionRegistry));
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
        equipInfo_ = java.util.Collections.unmodifiableList(equipInfo_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_OpenEquipShopRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_OpenEquipShopRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.OpenEquipShopRt.class, pb4client.OpenEquipShopRt.Builder.class);
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

  public static final int CDOVERSEC_FIELD_NUMBER = 4;
  private int cdOverSec_;
  /**
   * <pre>
   *下次自动刷新时间
   * </pre>
   *
   * <code>optional int32 cdOverSec = 4;</code>
   */
  public boolean hasCdOverSec() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *下次自动刷新时间
   * </pre>
   *
   * <code>optional int32 cdOverSec = 4;</code>
   */
  public int getCdOverSec() {
    return cdOverSec_;
  }

  public static final int EQUIPINFO_FIELD_NUMBER = 5;
  private java.util.List<pb4client.EquipShopInfo> equipInfo_;
  /**
   * <pre>
   *装备信息
   * </pre>
   *
   * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
   */
  public java.util.List<pb4client.EquipShopInfo> getEquipInfoList() {
    return equipInfo_;
  }
  /**
   * <pre>
   *装备信息
   * </pre>
   *
   * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
   */
  public java.util.List<? extends pb4client.EquipShopInfoOrBuilder> 
      getEquipInfoOrBuilderList() {
    return equipInfo_;
  }
  /**
   * <pre>
   *装备信息
   * </pre>
   *
   * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
   */
  public int getEquipInfoCount() {
    return equipInfo_.size();
  }
  /**
   * <pre>
   *装备信息
   * </pre>
   *
   * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
   */
  public pb4client.EquipShopInfo getEquipInfo(int index) {
    return equipInfo_.get(index);
  }
  /**
   * <pre>
   *装备信息
   * </pre>
   *
   * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
   */
  public pb4client.EquipShopInfoOrBuilder getEquipInfoOrBuilder(
      int index) {
    return equipInfo_.get(index);
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
    for (int i = 0; i < getEquipInfoCount(); i++) {
      if (!getEquipInfo(i).isInitialized()) {
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
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(4, cdOverSec_);
    }
    for (int i = 0; i < equipInfo_.size(); i++) {
      output.writeMessage(5, equipInfo_.get(i));
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
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, cdOverSec_);
    }
    for (int i = 0; i < equipInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, equipInfo_.get(i));
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
    if (!(obj instanceof pb4client.OpenEquipShopRt)) {
      return super.equals(obj);
    }
    pb4client.OpenEquipShopRt other = (pb4client.OpenEquipShopRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasCdOverSec() == other.hasCdOverSec());
    if (hasCdOverSec()) {
      result = result && (getCdOverSec()
          == other.getCdOverSec());
    }
    result = result && getEquipInfoList()
        .equals(other.getEquipInfoList());
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
    if (hasCdOverSec()) {
      hash = (37 * hash) + CDOVERSEC_FIELD_NUMBER;
      hash = (53 * hash) + getCdOverSec();
    }
    if (getEquipInfoCount() > 0) {
      hash = (37 * hash) + EQUIPINFO_FIELD_NUMBER;
      hash = (53 * hash) + getEquipInfoList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.OpenEquipShopRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OpenEquipShopRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OpenEquipShopRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OpenEquipShopRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OpenEquipShopRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OpenEquipShopRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OpenEquipShopRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.OpenEquipShopRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.OpenEquipShopRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.OpenEquipShopRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.OpenEquipShopRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.OpenEquipShopRt parseFrom(
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
  public static Builder newBuilder(pb4client.OpenEquipShopRt prototype) {
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
   * Protobuf type {@code client2server.OpenEquipShopRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.OpenEquipShopRt)
      pb4client.OpenEquipShopRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_OpenEquipShopRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_OpenEquipShopRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.OpenEquipShopRt.class, pb4client.OpenEquipShopRt.Builder.class);
    }

    // Construct using pb4client.OpenEquipShopRt.newBuilder()
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
        getEquipInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      cdOverSec_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      if (equipInfoBuilder_ == null) {
        equipInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        equipInfoBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_OpenEquipShopRt_descriptor;
    }

    public pb4client.OpenEquipShopRt getDefaultInstanceForType() {
      return pb4client.OpenEquipShopRt.getDefaultInstance();
    }

    public pb4client.OpenEquipShopRt build() {
      pb4client.OpenEquipShopRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.OpenEquipShopRt buildPartial() {
      pb4client.OpenEquipShopRt result = new pb4client.OpenEquipShopRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.cdOverSec_ = cdOverSec_;
      if (equipInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          equipInfo_ = java.util.Collections.unmodifiableList(equipInfo_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.equipInfo_ = equipInfo_;
      } else {
        result.equipInfo_ = equipInfoBuilder_.build();
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
      if (other instanceof pb4client.OpenEquipShopRt) {
        return mergeFrom((pb4client.OpenEquipShopRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.OpenEquipShopRt other) {
      if (other == pb4client.OpenEquipShopRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasCdOverSec()) {
        setCdOverSec(other.getCdOverSec());
      }
      if (equipInfoBuilder_ == null) {
        if (!other.equipInfo_.isEmpty()) {
          if (equipInfo_.isEmpty()) {
            equipInfo_ = other.equipInfo_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureEquipInfoIsMutable();
            equipInfo_.addAll(other.equipInfo_);
          }
          onChanged();
        }
      } else {
        if (!other.equipInfo_.isEmpty()) {
          if (equipInfoBuilder_.isEmpty()) {
            equipInfoBuilder_.dispose();
            equipInfoBuilder_ = null;
            equipInfo_ = other.equipInfo_;
            bitField0_ = (bitField0_ & ~0x00000004);
            equipInfoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getEquipInfoFieldBuilder() : null;
          } else {
            equipInfoBuilder_.addAllMessages(other.equipInfo_);
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
      for (int i = 0; i < getEquipInfoCount(); i++) {
        if (!getEquipInfo(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.OpenEquipShopRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.OpenEquipShopRt) e.getUnfinishedMessage();
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

    private int cdOverSec_ ;
    /**
     * <pre>
     *下次自动刷新时间
     * </pre>
     *
     * <code>optional int32 cdOverSec = 4;</code>
     */
    public boolean hasCdOverSec() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *下次自动刷新时间
     * </pre>
     *
     * <code>optional int32 cdOverSec = 4;</code>
     */
    public int getCdOverSec() {
      return cdOverSec_;
    }
    /**
     * <pre>
     *下次自动刷新时间
     * </pre>
     *
     * <code>optional int32 cdOverSec = 4;</code>
     */
    public Builder setCdOverSec(int value) {
      bitField0_ |= 0x00000002;
      cdOverSec_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *下次自动刷新时间
     * </pre>
     *
     * <code>optional int32 cdOverSec = 4;</code>
     */
    public Builder clearCdOverSec() {
      bitField0_ = (bitField0_ & ~0x00000002);
      cdOverSec_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.EquipShopInfo> equipInfo_ =
      java.util.Collections.emptyList();
    private void ensureEquipInfoIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        equipInfo_ = new java.util.ArrayList<pb4client.EquipShopInfo>(equipInfo_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.EquipShopInfo, pb4client.EquipShopInfo.Builder, pb4client.EquipShopInfoOrBuilder> equipInfoBuilder_;

    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public java.util.List<pb4client.EquipShopInfo> getEquipInfoList() {
      if (equipInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(equipInfo_);
      } else {
        return equipInfoBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public int getEquipInfoCount() {
      if (equipInfoBuilder_ == null) {
        return equipInfo_.size();
      } else {
        return equipInfoBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public pb4client.EquipShopInfo getEquipInfo(int index) {
      if (equipInfoBuilder_ == null) {
        return equipInfo_.get(index);
      } else {
        return equipInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public Builder setEquipInfo(
        int index, pb4client.EquipShopInfo value) {
      if (equipInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEquipInfoIsMutable();
        equipInfo_.set(index, value);
        onChanged();
      } else {
        equipInfoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public Builder setEquipInfo(
        int index, pb4client.EquipShopInfo.Builder builderForValue) {
      if (equipInfoBuilder_ == null) {
        ensureEquipInfoIsMutable();
        equipInfo_.set(index, builderForValue.build());
        onChanged();
      } else {
        equipInfoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public Builder addEquipInfo(pb4client.EquipShopInfo value) {
      if (equipInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEquipInfoIsMutable();
        equipInfo_.add(value);
        onChanged();
      } else {
        equipInfoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public Builder addEquipInfo(
        int index, pb4client.EquipShopInfo value) {
      if (equipInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEquipInfoIsMutable();
        equipInfo_.add(index, value);
        onChanged();
      } else {
        equipInfoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public Builder addEquipInfo(
        pb4client.EquipShopInfo.Builder builderForValue) {
      if (equipInfoBuilder_ == null) {
        ensureEquipInfoIsMutable();
        equipInfo_.add(builderForValue.build());
        onChanged();
      } else {
        equipInfoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public Builder addEquipInfo(
        int index, pb4client.EquipShopInfo.Builder builderForValue) {
      if (equipInfoBuilder_ == null) {
        ensureEquipInfoIsMutable();
        equipInfo_.add(index, builderForValue.build());
        onChanged();
      } else {
        equipInfoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public Builder addAllEquipInfo(
        java.lang.Iterable<? extends pb4client.EquipShopInfo> values) {
      if (equipInfoBuilder_ == null) {
        ensureEquipInfoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, equipInfo_);
        onChanged();
      } else {
        equipInfoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public Builder clearEquipInfo() {
      if (equipInfoBuilder_ == null) {
        equipInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        equipInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public Builder removeEquipInfo(int index) {
      if (equipInfoBuilder_ == null) {
        ensureEquipInfoIsMutable();
        equipInfo_.remove(index);
        onChanged();
      } else {
        equipInfoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public pb4client.EquipShopInfo.Builder getEquipInfoBuilder(
        int index) {
      return getEquipInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public pb4client.EquipShopInfoOrBuilder getEquipInfoOrBuilder(
        int index) {
      if (equipInfoBuilder_ == null) {
        return equipInfo_.get(index);  } else {
        return equipInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public java.util.List<? extends pb4client.EquipShopInfoOrBuilder> 
         getEquipInfoOrBuilderList() {
      if (equipInfoBuilder_ != null) {
        return equipInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(equipInfo_);
      }
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public pb4client.EquipShopInfo.Builder addEquipInfoBuilder() {
      return getEquipInfoFieldBuilder().addBuilder(
          pb4client.EquipShopInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public pb4client.EquipShopInfo.Builder addEquipInfoBuilder(
        int index) {
      return getEquipInfoFieldBuilder().addBuilder(
          index, pb4client.EquipShopInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *装备信息
     * </pre>
     *
     * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
     */
    public java.util.List<pb4client.EquipShopInfo.Builder> 
         getEquipInfoBuilderList() {
      return getEquipInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.EquipShopInfo, pb4client.EquipShopInfo.Builder, pb4client.EquipShopInfoOrBuilder> 
        getEquipInfoFieldBuilder() {
      if (equipInfoBuilder_ == null) {
        equipInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.EquipShopInfo, pb4client.EquipShopInfo.Builder, pb4client.EquipShopInfoOrBuilder>(
                equipInfo_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        equipInfo_ = null;
      }
      return equipInfoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.OpenEquipShopRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.OpenEquipShopRt)
  private static final pb4client.OpenEquipShopRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.OpenEquipShopRt();
  }

  public static pb4client.OpenEquipShopRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<OpenEquipShopRt>
      PARSER = new com.google.protobuf.AbstractParser<OpenEquipShopRt>() {
    public OpenEquipShopRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OpenEquipShopRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OpenEquipShopRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OpenEquipShopRt> getParserForType() {
    return PARSER;
  }

  public pb4client.OpenEquipShopRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

