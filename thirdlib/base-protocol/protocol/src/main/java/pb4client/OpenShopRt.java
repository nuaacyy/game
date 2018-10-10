// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.OpenShopRt}
 */
public  final class OpenShopRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.OpenShopRt)
    OpenShopRtOrBuilder {
  // Use OpenShopRt.newBuilder() to construct.
  private OpenShopRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OpenShopRt() {
    rt_ = 0;
    sInfo_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private OpenShopRt(
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
              sInfo_ = new java.util.ArrayList<pb4client.ShopInfo>();
              mutable_bitField0_ |= 0x00000002;
            }
            sInfo_.add(
                input.readMessage(pb4client.ShopInfo.PARSER, extensionRegistry));
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
        sInfo_ = java.util.Collections.unmodifiableList(sInfo_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_OpenShopRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_OpenShopRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.OpenShopRt.class, pb4client.OpenShopRt.Builder.class);
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

  public static final int SINFO_FIELD_NUMBER = 2;
  private java.util.List<pb4client.ShopInfo> sInfo_;
  /**
   * <pre>
   *商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
   */
  public java.util.List<pb4client.ShopInfo> getSInfoList() {
    return sInfo_;
  }
  /**
   * <pre>
   *商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
   */
  public java.util.List<? extends pb4client.ShopInfoOrBuilder> 
      getSInfoOrBuilderList() {
    return sInfo_;
  }
  /**
   * <pre>
   *商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
   */
  public int getSInfoCount() {
    return sInfo_.size();
  }
  /**
   * <pre>
   *商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
   */
  public pb4client.ShopInfo getSInfo(int index) {
    return sInfo_.get(index);
  }
  /**
   * <pre>
   *商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
   */
  public pb4client.ShopInfoOrBuilder getSInfoOrBuilder(
      int index) {
    return sInfo_.get(index);
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
    for (int i = 0; i < getSInfoCount(); i++) {
      if (!getSInfo(i).isInitialized()) {
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
    for (int i = 0; i < sInfo_.size(); i++) {
      output.writeMessage(2, sInfo_.get(i));
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
    for (int i = 0; i < sInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, sInfo_.get(i));
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
    if (!(obj instanceof pb4client.OpenShopRt)) {
      return super.equals(obj);
    }
    pb4client.OpenShopRt other = (pb4client.OpenShopRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getSInfoList()
        .equals(other.getSInfoList());
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
    if (getSInfoCount() > 0) {
      hash = (37 * hash) + SINFO_FIELD_NUMBER;
      hash = (53 * hash) + getSInfoList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.OpenShopRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OpenShopRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OpenShopRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OpenShopRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OpenShopRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OpenShopRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OpenShopRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.OpenShopRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.OpenShopRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.OpenShopRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.OpenShopRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.OpenShopRt parseFrom(
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
  public static Builder newBuilder(pb4client.OpenShopRt prototype) {
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
   * Protobuf type {@code client2server.OpenShopRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.OpenShopRt)
      pb4client.OpenShopRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_OpenShopRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_OpenShopRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.OpenShopRt.class, pb4client.OpenShopRt.Builder.class);
    }

    // Construct using pb4client.OpenShopRt.newBuilder()
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
        getSInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (sInfoBuilder_ == null) {
        sInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        sInfoBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_OpenShopRt_descriptor;
    }

    public pb4client.OpenShopRt getDefaultInstanceForType() {
      return pb4client.OpenShopRt.getDefaultInstance();
    }

    public pb4client.OpenShopRt build() {
      pb4client.OpenShopRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.OpenShopRt buildPartial() {
      pb4client.OpenShopRt result = new pb4client.OpenShopRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (sInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          sInfo_ = java.util.Collections.unmodifiableList(sInfo_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.sInfo_ = sInfo_;
      } else {
        result.sInfo_ = sInfoBuilder_.build();
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
      if (other instanceof pb4client.OpenShopRt) {
        return mergeFrom((pb4client.OpenShopRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.OpenShopRt other) {
      if (other == pb4client.OpenShopRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (sInfoBuilder_ == null) {
        if (!other.sInfo_.isEmpty()) {
          if (sInfo_.isEmpty()) {
            sInfo_ = other.sInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureSInfoIsMutable();
            sInfo_.addAll(other.sInfo_);
          }
          onChanged();
        }
      } else {
        if (!other.sInfo_.isEmpty()) {
          if (sInfoBuilder_.isEmpty()) {
            sInfoBuilder_.dispose();
            sInfoBuilder_ = null;
            sInfo_ = other.sInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
            sInfoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getSInfoFieldBuilder() : null;
          } else {
            sInfoBuilder_.addAllMessages(other.sInfo_);
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
      for (int i = 0; i < getSInfoCount(); i++) {
        if (!getSInfo(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.OpenShopRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.OpenShopRt) e.getUnfinishedMessage();
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

    private java.util.List<pb4client.ShopInfo> sInfo_ =
      java.util.Collections.emptyList();
    private void ensureSInfoIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        sInfo_ = new java.util.ArrayList<pb4client.ShopInfo>(sInfo_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.ShopInfo, pb4client.ShopInfo.Builder, pb4client.ShopInfoOrBuilder> sInfoBuilder_;

    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public java.util.List<pb4client.ShopInfo> getSInfoList() {
      if (sInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(sInfo_);
      } else {
        return sInfoBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public int getSInfoCount() {
      if (sInfoBuilder_ == null) {
        return sInfo_.size();
      } else {
        return sInfoBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public pb4client.ShopInfo getSInfo(int index) {
      if (sInfoBuilder_ == null) {
        return sInfo_.get(index);
      } else {
        return sInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public Builder setSInfo(
        int index, pb4client.ShopInfo value) {
      if (sInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSInfoIsMutable();
        sInfo_.set(index, value);
        onChanged();
      } else {
        sInfoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public Builder setSInfo(
        int index, pb4client.ShopInfo.Builder builderForValue) {
      if (sInfoBuilder_ == null) {
        ensureSInfoIsMutable();
        sInfo_.set(index, builderForValue.build());
        onChanged();
      } else {
        sInfoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public Builder addSInfo(pb4client.ShopInfo value) {
      if (sInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSInfoIsMutable();
        sInfo_.add(value);
        onChanged();
      } else {
        sInfoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public Builder addSInfo(
        int index, pb4client.ShopInfo value) {
      if (sInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSInfoIsMutable();
        sInfo_.add(index, value);
        onChanged();
      } else {
        sInfoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public Builder addSInfo(
        pb4client.ShopInfo.Builder builderForValue) {
      if (sInfoBuilder_ == null) {
        ensureSInfoIsMutable();
        sInfo_.add(builderForValue.build());
        onChanged();
      } else {
        sInfoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public Builder addSInfo(
        int index, pb4client.ShopInfo.Builder builderForValue) {
      if (sInfoBuilder_ == null) {
        ensureSInfoIsMutable();
        sInfo_.add(index, builderForValue.build());
        onChanged();
      } else {
        sInfoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public Builder addAllSInfo(
        java.lang.Iterable<? extends pb4client.ShopInfo> values) {
      if (sInfoBuilder_ == null) {
        ensureSInfoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, sInfo_);
        onChanged();
      } else {
        sInfoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public Builder clearSInfo() {
      if (sInfoBuilder_ == null) {
        sInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        sInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public Builder removeSInfo(int index) {
      if (sInfoBuilder_ == null) {
        ensureSInfoIsMutable();
        sInfo_.remove(index);
        onChanged();
      } else {
        sInfoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public pb4client.ShopInfo.Builder getSInfoBuilder(
        int index) {
      return getSInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public pb4client.ShopInfoOrBuilder getSInfoOrBuilder(
        int index) {
      if (sInfoBuilder_ == null) {
        return sInfo_.get(index);  } else {
        return sInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public java.util.List<? extends pb4client.ShopInfoOrBuilder> 
         getSInfoOrBuilderList() {
      if (sInfoBuilder_ != null) {
        return sInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(sInfo_);
      }
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public pb4client.ShopInfo.Builder addSInfoBuilder() {
      return getSInfoFieldBuilder().addBuilder(
          pb4client.ShopInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public pb4client.ShopInfo.Builder addSInfoBuilder(
        int index) {
      return getSInfoFieldBuilder().addBuilder(
          index, pb4client.ShopInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopInfo sInfo = 2;</code>
     */
    public java.util.List<pb4client.ShopInfo.Builder> 
         getSInfoBuilderList() {
      return getSInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.ShopInfo, pb4client.ShopInfo.Builder, pb4client.ShopInfoOrBuilder> 
        getSInfoFieldBuilder() {
      if (sInfoBuilder_ == null) {
        sInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.ShopInfo, pb4client.ShopInfo.Builder, pb4client.ShopInfoOrBuilder>(
                sInfo_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        sInfo_ = null;
      }
      return sInfoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.OpenShopRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.OpenShopRt)
  private static final pb4client.OpenShopRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.OpenShopRt();
  }

  public static pb4client.OpenShopRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<OpenShopRt>
      PARSER = new com.google.protobuf.AbstractParser<OpenShopRt>() {
    public OpenShopRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OpenShopRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OpenShopRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OpenShopRt> getParserForType() {
    return PARSER;
  }

  public pb4client.OpenShopRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

