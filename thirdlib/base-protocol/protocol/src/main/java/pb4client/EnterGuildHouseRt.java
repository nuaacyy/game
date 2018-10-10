// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.EnterGuildHouseRt}
 */
public  final class EnterGuildHouseRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.EnterGuildHouseRt)
    EnterGuildHouseRtOrBuilder {
  // Use EnterGuildHouseRt.newBuilder() to construct.
  private EnterGuildHouseRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EnterGuildHouseRt() {
    rt_ = 0;
    name_ = "";
    comfort_ = 0;
    thumbedUpNew_ = 0;
    furnitures_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private EnterGuildHouseRt(
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
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            name_ = bs;
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            comfort_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            thumbedUpNew_ = input.readInt32();
            break;
          }
          case 42: {
            if (!((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
              furnitures_ = new java.util.ArrayList<pb4client.FurnitureInfo>();
              mutable_bitField0_ |= 0x00000010;
            }
            furnitures_.add(
                input.readMessage(pb4client.FurnitureInfo.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
        furnitures_ = java.util.Collections.unmodifiableList(furnitures_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_EnterGuildHouseRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_EnterGuildHouseRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.EnterGuildHouseRt.class, pb4client.EnterGuildHouseRt.Builder.class);
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

  public static final int NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object name_;
  /**
   * <pre>
   * 后宅名称
   * </pre>
   *
   * <code>optional string name = 2;</code>
   */
  public boolean hasName() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 后宅名称
   * </pre>
   *
   * <code>optional string name = 2;</code>
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        name_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 后宅名称
   * </pre>
   *
   * <code>optional string name = 2;</code>
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int COMFORT_FIELD_NUMBER = 3;
  private int comfort_;
  /**
   * <pre>
   * 舒适度
   * </pre>
   *
   * <code>optional int32 comfort = 3;</code>
   */
  public boolean hasComfort() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 舒适度
   * </pre>
   *
   * <code>optional int32 comfort = 3;</code>
   */
  public int getComfort() {
    return comfort_;
  }

  public static final int THUMBEDUPNEW_FIELD_NUMBER = 4;
  private int thumbedUpNew_;
  /**
   * <pre>
   * 新增被赞数
   * </pre>
   *
   * <code>optional int32 thumbedUpNew = 4;</code>
   */
  public boolean hasThumbedUpNew() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 新增被赞数
   * </pre>
   *
   * <code>optional int32 thumbedUpNew = 4;</code>
   */
  public int getThumbedUpNew() {
    return thumbedUpNew_;
  }

  public static final int FURNITURES_FIELD_NUMBER = 5;
  private java.util.List<pb4client.FurnitureInfo> furnitures_;
  /**
   * <pre>
   * 当前后宅家具
   * </pre>
   *
   * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
   */
  public java.util.List<pb4client.FurnitureInfo> getFurnituresList() {
    return furnitures_;
  }
  /**
   * <pre>
   * 当前后宅家具
   * </pre>
   *
   * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
   */
  public java.util.List<? extends pb4client.FurnitureInfoOrBuilder> 
      getFurnituresOrBuilderList() {
    return furnitures_;
  }
  /**
   * <pre>
   * 当前后宅家具
   * </pre>
   *
   * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
   */
  public int getFurnituresCount() {
    return furnitures_.size();
  }
  /**
   * <pre>
   * 当前后宅家具
   * </pre>
   *
   * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
   */
  public pb4client.FurnitureInfo getFurnitures(int index) {
    return furnitures_.get(index);
  }
  /**
   * <pre>
   * 当前后宅家具
   * </pre>
   *
   * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
   */
  public pb4client.FurnitureInfoOrBuilder getFurnituresOrBuilder(
      int index) {
    return furnitures_.get(index);
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
    for (int i = 0; i < getFurnituresCount(); i++) {
      if (!getFurnitures(i).isInitialized()) {
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
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, comfort_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, thumbedUpNew_);
    }
    for (int i = 0; i < furnitures_.size(); i++) {
      output.writeMessage(5, furnitures_.get(i));
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
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, comfort_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, thumbedUpNew_);
    }
    for (int i = 0; i < furnitures_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, furnitures_.get(i));
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
    if (!(obj instanceof pb4client.EnterGuildHouseRt)) {
      return super.equals(obj);
    }
    pb4client.EnterGuildHouseRt other = (pb4client.EnterGuildHouseRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasName() == other.hasName());
    if (hasName()) {
      result = result && getName()
          .equals(other.getName());
    }
    result = result && (hasComfort() == other.hasComfort());
    if (hasComfort()) {
      result = result && (getComfort()
          == other.getComfort());
    }
    result = result && (hasThumbedUpNew() == other.hasThumbedUpNew());
    if (hasThumbedUpNew()) {
      result = result && (getThumbedUpNew()
          == other.getThumbedUpNew());
    }
    result = result && getFurnituresList()
        .equals(other.getFurnituresList());
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
    if (hasName()) {
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
    }
    if (hasComfort()) {
      hash = (37 * hash) + COMFORT_FIELD_NUMBER;
      hash = (53 * hash) + getComfort();
    }
    if (hasThumbedUpNew()) {
      hash = (37 * hash) + THUMBEDUPNEW_FIELD_NUMBER;
      hash = (53 * hash) + getThumbedUpNew();
    }
    if (getFurnituresCount() > 0) {
      hash = (37 * hash) + FURNITURES_FIELD_NUMBER;
      hash = (53 * hash) + getFurnituresList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.EnterGuildHouseRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.EnterGuildHouseRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.EnterGuildHouseRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.EnterGuildHouseRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.EnterGuildHouseRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.EnterGuildHouseRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.EnterGuildHouseRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.EnterGuildHouseRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.EnterGuildHouseRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.EnterGuildHouseRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.EnterGuildHouseRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.EnterGuildHouseRt parseFrom(
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
  public static Builder newBuilder(pb4client.EnterGuildHouseRt prototype) {
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
   * Protobuf type {@code client2server.EnterGuildHouseRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.EnterGuildHouseRt)
      pb4client.EnterGuildHouseRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_EnterGuildHouseRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_EnterGuildHouseRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.EnterGuildHouseRt.class, pb4client.EnterGuildHouseRt.Builder.class);
    }

    // Construct using pb4client.EnterGuildHouseRt.newBuilder()
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
        getFurnituresFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      name_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      comfort_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      thumbedUpNew_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      if (furnituresBuilder_ == null) {
        furnitures_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000010);
      } else {
        furnituresBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_EnterGuildHouseRt_descriptor;
    }

    public pb4client.EnterGuildHouseRt getDefaultInstanceForType() {
      return pb4client.EnterGuildHouseRt.getDefaultInstance();
    }

    public pb4client.EnterGuildHouseRt build() {
      pb4client.EnterGuildHouseRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.EnterGuildHouseRt buildPartial() {
      pb4client.EnterGuildHouseRt result = new pb4client.EnterGuildHouseRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.name_ = name_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.comfort_ = comfort_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.thumbedUpNew_ = thumbedUpNew_;
      if (furnituresBuilder_ == null) {
        if (((bitField0_ & 0x00000010) == 0x00000010)) {
          furnitures_ = java.util.Collections.unmodifiableList(furnitures_);
          bitField0_ = (bitField0_ & ~0x00000010);
        }
        result.furnitures_ = furnitures_;
      } else {
        result.furnitures_ = furnituresBuilder_.build();
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
      if (other instanceof pb4client.EnterGuildHouseRt) {
        return mergeFrom((pb4client.EnterGuildHouseRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.EnterGuildHouseRt other) {
      if (other == pb4client.EnterGuildHouseRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasName()) {
        bitField0_ |= 0x00000002;
        name_ = other.name_;
        onChanged();
      }
      if (other.hasComfort()) {
        setComfort(other.getComfort());
      }
      if (other.hasThumbedUpNew()) {
        setThumbedUpNew(other.getThumbedUpNew());
      }
      if (furnituresBuilder_ == null) {
        if (!other.furnitures_.isEmpty()) {
          if (furnitures_.isEmpty()) {
            furnitures_ = other.furnitures_;
            bitField0_ = (bitField0_ & ~0x00000010);
          } else {
            ensureFurnituresIsMutable();
            furnitures_.addAll(other.furnitures_);
          }
          onChanged();
        }
      } else {
        if (!other.furnitures_.isEmpty()) {
          if (furnituresBuilder_.isEmpty()) {
            furnituresBuilder_.dispose();
            furnituresBuilder_ = null;
            furnitures_ = other.furnitures_;
            bitField0_ = (bitField0_ & ~0x00000010);
            furnituresBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getFurnituresFieldBuilder() : null;
          } else {
            furnituresBuilder_.addAllMessages(other.furnitures_);
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
      for (int i = 0; i < getFurnituresCount(); i++) {
        if (!getFurnitures(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.EnterGuildHouseRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.EnterGuildHouseRt) e.getUnfinishedMessage();
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

    private java.lang.Object name_ = "";
    /**
     * <pre>
     * 后宅名称
     * </pre>
     *
     * <code>optional string name = 2;</code>
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 后宅名称
     * </pre>
     *
     * <code>optional string name = 2;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          name_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 后宅名称
     * </pre>
     *
     * <code>optional string name = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 后宅名称
     * </pre>
     *
     * <code>optional string name = 2;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 后宅名称
     * </pre>
     *
     * <code>optional string name = 2;</code>
     */
    public Builder clearName() {
      bitField0_ = (bitField0_ & ~0x00000002);
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 后宅名称
     * </pre>
     *
     * <code>optional string name = 2;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      name_ = value;
      onChanged();
      return this;
    }

    private int comfort_ ;
    /**
     * <pre>
     * 舒适度
     * </pre>
     *
     * <code>optional int32 comfort = 3;</code>
     */
    public boolean hasComfort() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 舒适度
     * </pre>
     *
     * <code>optional int32 comfort = 3;</code>
     */
    public int getComfort() {
      return comfort_;
    }
    /**
     * <pre>
     * 舒适度
     * </pre>
     *
     * <code>optional int32 comfort = 3;</code>
     */
    public Builder setComfort(int value) {
      bitField0_ |= 0x00000004;
      comfort_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 舒适度
     * </pre>
     *
     * <code>optional int32 comfort = 3;</code>
     */
    public Builder clearComfort() {
      bitField0_ = (bitField0_ & ~0x00000004);
      comfort_ = 0;
      onChanged();
      return this;
    }

    private int thumbedUpNew_ ;
    /**
     * <pre>
     * 新增被赞数
     * </pre>
     *
     * <code>optional int32 thumbedUpNew = 4;</code>
     */
    public boolean hasThumbedUpNew() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 新增被赞数
     * </pre>
     *
     * <code>optional int32 thumbedUpNew = 4;</code>
     */
    public int getThumbedUpNew() {
      return thumbedUpNew_;
    }
    /**
     * <pre>
     * 新增被赞数
     * </pre>
     *
     * <code>optional int32 thumbedUpNew = 4;</code>
     */
    public Builder setThumbedUpNew(int value) {
      bitField0_ |= 0x00000008;
      thumbedUpNew_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 新增被赞数
     * </pre>
     *
     * <code>optional int32 thumbedUpNew = 4;</code>
     */
    public Builder clearThumbedUpNew() {
      bitField0_ = (bitField0_ & ~0x00000008);
      thumbedUpNew_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.FurnitureInfo> furnitures_ =
      java.util.Collections.emptyList();
    private void ensureFurnituresIsMutable() {
      if (!((bitField0_ & 0x00000010) == 0x00000010)) {
        furnitures_ = new java.util.ArrayList<pb4client.FurnitureInfo>(furnitures_);
        bitField0_ |= 0x00000010;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.FurnitureInfo, pb4client.FurnitureInfo.Builder, pb4client.FurnitureInfoOrBuilder> furnituresBuilder_;

    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public java.util.List<pb4client.FurnitureInfo> getFurnituresList() {
      if (furnituresBuilder_ == null) {
        return java.util.Collections.unmodifiableList(furnitures_);
      } else {
        return furnituresBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public int getFurnituresCount() {
      if (furnituresBuilder_ == null) {
        return furnitures_.size();
      } else {
        return furnituresBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public pb4client.FurnitureInfo getFurnitures(int index) {
      if (furnituresBuilder_ == null) {
        return furnitures_.get(index);
      } else {
        return furnituresBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public Builder setFurnitures(
        int index, pb4client.FurnitureInfo value) {
      if (furnituresBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFurnituresIsMutable();
        furnitures_.set(index, value);
        onChanged();
      } else {
        furnituresBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public Builder setFurnitures(
        int index, pb4client.FurnitureInfo.Builder builderForValue) {
      if (furnituresBuilder_ == null) {
        ensureFurnituresIsMutable();
        furnitures_.set(index, builderForValue.build());
        onChanged();
      } else {
        furnituresBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public Builder addFurnitures(pb4client.FurnitureInfo value) {
      if (furnituresBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFurnituresIsMutable();
        furnitures_.add(value);
        onChanged();
      } else {
        furnituresBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public Builder addFurnitures(
        int index, pb4client.FurnitureInfo value) {
      if (furnituresBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFurnituresIsMutable();
        furnitures_.add(index, value);
        onChanged();
      } else {
        furnituresBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public Builder addFurnitures(
        pb4client.FurnitureInfo.Builder builderForValue) {
      if (furnituresBuilder_ == null) {
        ensureFurnituresIsMutable();
        furnitures_.add(builderForValue.build());
        onChanged();
      } else {
        furnituresBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public Builder addFurnitures(
        int index, pb4client.FurnitureInfo.Builder builderForValue) {
      if (furnituresBuilder_ == null) {
        ensureFurnituresIsMutable();
        furnitures_.add(index, builderForValue.build());
        onChanged();
      } else {
        furnituresBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public Builder addAllFurnitures(
        java.lang.Iterable<? extends pb4client.FurnitureInfo> values) {
      if (furnituresBuilder_ == null) {
        ensureFurnituresIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, furnitures_);
        onChanged();
      } else {
        furnituresBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public Builder clearFurnitures() {
      if (furnituresBuilder_ == null) {
        furnitures_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000010);
        onChanged();
      } else {
        furnituresBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public Builder removeFurnitures(int index) {
      if (furnituresBuilder_ == null) {
        ensureFurnituresIsMutable();
        furnitures_.remove(index);
        onChanged();
      } else {
        furnituresBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public pb4client.FurnitureInfo.Builder getFurnituresBuilder(
        int index) {
      return getFurnituresFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public pb4client.FurnitureInfoOrBuilder getFurnituresOrBuilder(
        int index) {
      if (furnituresBuilder_ == null) {
        return furnitures_.get(index);  } else {
        return furnituresBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public java.util.List<? extends pb4client.FurnitureInfoOrBuilder> 
         getFurnituresOrBuilderList() {
      if (furnituresBuilder_ != null) {
        return furnituresBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(furnitures_);
      }
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public pb4client.FurnitureInfo.Builder addFurnituresBuilder() {
      return getFurnituresFieldBuilder().addBuilder(
          pb4client.FurnitureInfo.getDefaultInstance());
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public pb4client.FurnitureInfo.Builder addFurnituresBuilder(
        int index) {
      return getFurnituresFieldBuilder().addBuilder(
          index, pb4client.FurnitureInfo.getDefaultInstance());
    }
    /**
     * <pre>
     * 当前后宅家具
     * </pre>
     *
     * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
     */
    public java.util.List<pb4client.FurnitureInfo.Builder> 
         getFurnituresBuilderList() {
      return getFurnituresFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.FurnitureInfo, pb4client.FurnitureInfo.Builder, pb4client.FurnitureInfoOrBuilder> 
        getFurnituresFieldBuilder() {
      if (furnituresBuilder_ == null) {
        furnituresBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.FurnitureInfo, pb4client.FurnitureInfo.Builder, pb4client.FurnitureInfoOrBuilder>(
                furnitures_,
                ((bitField0_ & 0x00000010) == 0x00000010),
                getParentForChildren(),
                isClean());
        furnitures_ = null;
      }
      return furnituresBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.EnterGuildHouseRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.EnterGuildHouseRt)
  private static final pb4client.EnterGuildHouseRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.EnterGuildHouseRt();
  }

  public static pb4client.EnterGuildHouseRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<EnterGuildHouseRt>
      PARSER = new com.google.protobuf.AbstractParser<EnterGuildHouseRt>() {
    public EnterGuildHouseRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new EnterGuildHouseRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EnterGuildHouseRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EnterGuildHouseRt> getParserForType() {
    return PARSER;
  }

  public pb4client.EnterGuildHouseRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

