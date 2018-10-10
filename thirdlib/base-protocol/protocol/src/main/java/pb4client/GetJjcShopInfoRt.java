// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.GetJjcShopInfoRt}
 */
public  final class GetJjcShopInfoRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.GetJjcShopInfoRt)
    GetJjcShopInfoRtOrBuilder {
  // Use GetJjcShopInfoRt.newBuilder() to construct.
  private GetJjcShopInfoRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetJjcShopInfoRt() {
    rt_ = 0;
    refreshTime_ = 0L;
    times_ = 0;
    items_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetJjcShopInfoRt(
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
          case 16: {
            bitField0_ |= 0x00000002;
            refreshTime_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            times_ = input.readInt32();
            break;
          }
          case 34: {
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
              items_ = new java.util.ArrayList<pb4client.ShopItemInfo>();
              mutable_bitField0_ |= 0x00000008;
            }
            items_.add(
                input.readMessage(pb4client.ShopItemInfo.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
        items_ = java.util.Collections.unmodifiableList(items_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_GetJjcShopInfoRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_GetJjcShopInfoRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.GetJjcShopInfoRt.class, pb4client.GetJjcShopInfoRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int REFRESHTIME_FIELD_NUMBER = 2;
  private long refreshTime_;
  /**
   * <pre>
   * 上次的刷新时间 毫秒时间戳
   * </pre>
   *
   * <code>optional int64 refreshTime = 2;</code>
   */
  public boolean hasRefreshTime() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 上次的刷新时间 毫秒时间戳
   * </pre>
   *
   * <code>optional int64 refreshTime = 2;</code>
   */
  public long getRefreshTime() {
    return refreshTime_;
  }

  public static final int TIMES_FIELD_NUMBER = 3;
  private int times_;
  /**
   * <pre>
   * 刷新的次数
   * </pre>
   *
   * <code>optional int32 times = 3;</code>
   */
  public boolean hasTimes() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 刷新的次数
   * </pre>
   *
   * <code>optional int32 times = 3;</code>
   */
  public int getTimes() {
    return times_;
  }

  public static final int ITEMS_FIELD_NUMBER = 4;
  private java.util.List<pb4client.ShopItemInfo> items_;
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 4;</code>
   */
  public java.util.List<pb4client.ShopItemInfo> getItemsList() {
    return items_;
  }
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 4;</code>
   */
  public java.util.List<? extends pb4client.ShopItemInfoOrBuilder> 
      getItemsOrBuilderList() {
    return items_;
  }
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 4;</code>
   */
  public int getItemsCount() {
    return items_.size();
  }
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 4;</code>
   */
  public pb4client.ShopItemInfo getItems(int index) {
    return items_.get(index);
  }
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 4;</code>
   */
  public pb4client.ShopItemInfoOrBuilder getItemsOrBuilder(
      int index) {
    return items_.get(index);
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
    for (int i = 0; i < getItemsCount(); i++) {
      if (!getItems(i).isInitialized()) {
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
      output.writeInt64(2, refreshTime_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, times_);
    }
    for (int i = 0; i < items_.size(); i++) {
      output.writeMessage(4, items_.get(i));
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
        .computeInt64Size(2, refreshTime_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, times_);
    }
    for (int i = 0; i < items_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, items_.get(i));
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
    if (!(obj instanceof pb4client.GetJjcShopInfoRt)) {
      return super.equals(obj);
    }
    pb4client.GetJjcShopInfoRt other = (pb4client.GetJjcShopInfoRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasRefreshTime() == other.hasRefreshTime());
    if (hasRefreshTime()) {
      result = result && (getRefreshTime()
          == other.getRefreshTime());
    }
    result = result && (hasTimes() == other.hasTimes());
    if (hasTimes()) {
      result = result && (getTimes()
          == other.getTimes());
    }
    result = result && getItemsList()
        .equals(other.getItemsList());
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
    if (hasRefreshTime()) {
      hash = (37 * hash) + REFRESHTIME_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getRefreshTime());
    }
    if (hasTimes()) {
      hash = (37 * hash) + TIMES_FIELD_NUMBER;
      hash = (53 * hash) + getTimes();
    }
    if (getItemsCount() > 0) {
      hash = (37 * hash) + ITEMS_FIELD_NUMBER;
      hash = (53 * hash) + getItemsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.GetJjcShopInfoRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetJjcShopInfoRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetJjcShopInfoRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetJjcShopInfoRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetJjcShopInfoRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetJjcShopInfoRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetJjcShopInfoRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetJjcShopInfoRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetJjcShopInfoRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.GetJjcShopInfoRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetJjcShopInfoRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetJjcShopInfoRt parseFrom(
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
  public static Builder newBuilder(pb4client.GetJjcShopInfoRt prototype) {
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
   * Protobuf type {@code client2server.GetJjcShopInfoRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.GetJjcShopInfoRt)
      pb4client.GetJjcShopInfoRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_GetJjcShopInfoRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_GetJjcShopInfoRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.GetJjcShopInfoRt.class, pb4client.GetJjcShopInfoRt.Builder.class);
    }

    // Construct using pb4client.GetJjcShopInfoRt.newBuilder()
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
        getItemsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      refreshTime_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      times_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      if (itemsBuilder_ == null) {
        items_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000008);
      } else {
        itemsBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_GetJjcShopInfoRt_descriptor;
    }

    public pb4client.GetJjcShopInfoRt getDefaultInstanceForType() {
      return pb4client.GetJjcShopInfoRt.getDefaultInstance();
    }

    public pb4client.GetJjcShopInfoRt build() {
      pb4client.GetJjcShopInfoRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.GetJjcShopInfoRt buildPartial() {
      pb4client.GetJjcShopInfoRt result = new pb4client.GetJjcShopInfoRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.refreshTime_ = refreshTime_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.times_ = times_;
      if (itemsBuilder_ == null) {
        if (((bitField0_ & 0x00000008) == 0x00000008)) {
          items_ = java.util.Collections.unmodifiableList(items_);
          bitField0_ = (bitField0_ & ~0x00000008);
        }
        result.items_ = items_;
      } else {
        result.items_ = itemsBuilder_.build();
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
      if (other instanceof pb4client.GetJjcShopInfoRt) {
        return mergeFrom((pb4client.GetJjcShopInfoRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.GetJjcShopInfoRt other) {
      if (other == pb4client.GetJjcShopInfoRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasRefreshTime()) {
        setRefreshTime(other.getRefreshTime());
      }
      if (other.hasTimes()) {
        setTimes(other.getTimes());
      }
      if (itemsBuilder_ == null) {
        if (!other.items_.isEmpty()) {
          if (items_.isEmpty()) {
            items_ = other.items_;
            bitField0_ = (bitField0_ & ~0x00000008);
          } else {
            ensureItemsIsMutable();
            items_.addAll(other.items_);
          }
          onChanged();
        }
      } else {
        if (!other.items_.isEmpty()) {
          if (itemsBuilder_.isEmpty()) {
            itemsBuilder_.dispose();
            itemsBuilder_ = null;
            items_ = other.items_;
            bitField0_ = (bitField0_ & ~0x00000008);
            itemsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getItemsFieldBuilder() : null;
          } else {
            itemsBuilder_.addAllMessages(other.items_);
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
      for (int i = 0; i < getItemsCount(); i++) {
        if (!getItems(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.GetJjcShopInfoRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.GetJjcShopInfoRt) e.getUnfinishedMessage();
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
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private long refreshTime_ ;
    /**
     * <pre>
     * 上次的刷新时间 毫秒时间戳
     * </pre>
     *
     * <code>optional int64 refreshTime = 2;</code>
     */
    public boolean hasRefreshTime() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 上次的刷新时间 毫秒时间戳
     * </pre>
     *
     * <code>optional int64 refreshTime = 2;</code>
     */
    public long getRefreshTime() {
      return refreshTime_;
    }
    /**
     * <pre>
     * 上次的刷新时间 毫秒时间戳
     * </pre>
     *
     * <code>optional int64 refreshTime = 2;</code>
     */
    public Builder setRefreshTime(long value) {
      bitField0_ |= 0x00000002;
      refreshTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 上次的刷新时间 毫秒时间戳
     * </pre>
     *
     * <code>optional int64 refreshTime = 2;</code>
     */
    public Builder clearRefreshTime() {
      bitField0_ = (bitField0_ & ~0x00000002);
      refreshTime_ = 0L;
      onChanged();
      return this;
    }

    private int times_ ;
    /**
     * <pre>
     * 刷新的次数
     * </pre>
     *
     * <code>optional int32 times = 3;</code>
     */
    public boolean hasTimes() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 刷新的次数
     * </pre>
     *
     * <code>optional int32 times = 3;</code>
     */
    public int getTimes() {
      return times_;
    }
    /**
     * <pre>
     * 刷新的次数
     * </pre>
     *
     * <code>optional int32 times = 3;</code>
     */
    public Builder setTimes(int value) {
      bitField0_ |= 0x00000004;
      times_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 刷新的次数
     * </pre>
     *
     * <code>optional int32 times = 3;</code>
     */
    public Builder clearTimes() {
      bitField0_ = (bitField0_ & ~0x00000004);
      times_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.ShopItemInfo> items_ =
      java.util.Collections.emptyList();
    private void ensureItemsIsMutable() {
      if (!((bitField0_ & 0x00000008) == 0x00000008)) {
        items_ = new java.util.ArrayList<pb4client.ShopItemInfo>(items_);
        bitField0_ |= 0x00000008;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.ShopItemInfo, pb4client.ShopItemInfo.Builder, pb4client.ShopItemInfoOrBuilder> itemsBuilder_;

    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public java.util.List<pb4client.ShopItemInfo> getItemsList() {
      if (itemsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(items_);
      } else {
        return itemsBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public int getItemsCount() {
      if (itemsBuilder_ == null) {
        return items_.size();
      } else {
        return itemsBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public pb4client.ShopItemInfo getItems(int index) {
      if (itemsBuilder_ == null) {
        return items_.get(index);
      } else {
        return itemsBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public Builder setItems(
        int index, pb4client.ShopItemInfo value) {
      if (itemsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureItemsIsMutable();
        items_.set(index, value);
        onChanged();
      } else {
        itemsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public Builder setItems(
        int index, pb4client.ShopItemInfo.Builder builderForValue) {
      if (itemsBuilder_ == null) {
        ensureItemsIsMutable();
        items_.set(index, builderForValue.build());
        onChanged();
      } else {
        itemsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public Builder addItems(pb4client.ShopItemInfo value) {
      if (itemsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureItemsIsMutable();
        items_.add(value);
        onChanged();
      } else {
        itemsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public Builder addItems(
        int index, pb4client.ShopItemInfo value) {
      if (itemsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureItemsIsMutable();
        items_.add(index, value);
        onChanged();
      } else {
        itemsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public Builder addItems(
        pb4client.ShopItemInfo.Builder builderForValue) {
      if (itemsBuilder_ == null) {
        ensureItemsIsMutable();
        items_.add(builderForValue.build());
        onChanged();
      } else {
        itemsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public Builder addItems(
        int index, pb4client.ShopItemInfo.Builder builderForValue) {
      if (itemsBuilder_ == null) {
        ensureItemsIsMutable();
        items_.add(index, builderForValue.build());
        onChanged();
      } else {
        itemsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public Builder addAllItems(
        java.lang.Iterable<? extends pb4client.ShopItemInfo> values) {
      if (itemsBuilder_ == null) {
        ensureItemsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, items_);
        onChanged();
      } else {
        itemsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public Builder clearItems() {
      if (itemsBuilder_ == null) {
        items_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000008);
        onChanged();
      } else {
        itemsBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public Builder removeItems(int index) {
      if (itemsBuilder_ == null) {
        ensureItemsIsMutable();
        items_.remove(index);
        onChanged();
      } else {
        itemsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public pb4client.ShopItemInfo.Builder getItemsBuilder(
        int index) {
      return getItemsFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public pb4client.ShopItemInfoOrBuilder getItemsOrBuilder(
        int index) {
      if (itemsBuilder_ == null) {
        return items_.get(index);  } else {
        return itemsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public java.util.List<? extends pb4client.ShopItemInfoOrBuilder> 
         getItemsOrBuilderList() {
      if (itemsBuilder_ != null) {
        return itemsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(items_);
      }
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public pb4client.ShopItemInfo.Builder addItemsBuilder() {
      return getItemsFieldBuilder().addBuilder(
          pb4client.ShopItemInfo.getDefaultInstance());
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public pb4client.ShopItemInfo.Builder addItemsBuilder(
        int index) {
      return getItemsFieldBuilder().addBuilder(
          index, pb4client.ShopItemInfo.getDefaultInstance());
    }
    /**
     * <pre>
     * 商店各项商品信息
     * </pre>
     *
     * <code>repeated .client2server.ShopItemInfo items = 4;</code>
     */
    public java.util.List<pb4client.ShopItemInfo.Builder> 
         getItemsBuilderList() {
      return getItemsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.ShopItemInfo, pb4client.ShopItemInfo.Builder, pb4client.ShopItemInfoOrBuilder> 
        getItemsFieldBuilder() {
      if (itemsBuilder_ == null) {
        itemsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.ShopItemInfo, pb4client.ShopItemInfo.Builder, pb4client.ShopItemInfoOrBuilder>(
                items_,
                ((bitField0_ & 0x00000008) == 0x00000008),
                getParentForChildren(),
                isClean());
        items_ = null;
      }
      return itemsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.GetJjcShopInfoRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.GetJjcShopInfoRt)
  private static final pb4client.GetJjcShopInfoRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.GetJjcShopInfoRt();
  }

  public static pb4client.GetJjcShopInfoRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GetJjcShopInfoRt>
      PARSER = new com.google.protobuf.AbstractParser<GetJjcShopInfoRt>() {
    public GetJjcShopInfoRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetJjcShopInfoRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetJjcShopInfoRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetJjcShopInfoRt> getParserForType() {
    return PARSER;
  }

  public pb4client.GetJjcShopInfoRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
