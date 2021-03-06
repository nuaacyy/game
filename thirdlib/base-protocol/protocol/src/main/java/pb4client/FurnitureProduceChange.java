// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3180
 * 服务端 -&gt; 客户端
 * 后宅家具收集时间变化
 * </pre>
 *
 * Protobuf type {@code client2server.FurnitureProduceChange}
 */
public  final class FurnitureProduceChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.FurnitureProduceChange)
    FurnitureProduceChangeOrBuilder {
  // Use FurnitureProduceChange.newBuilder() to construct.
  private FurnitureProduceChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FurnitureProduceChange() {
    furnitures_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FurnitureProduceChange(
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              furnitures_ = new java.util.ArrayList<pb4client.FurnitureTimeInfo>();
              mutable_bitField0_ |= 0x00000001;
            }
            furnitures_.add(
                input.readMessage(pb4client.FurnitureTimeInfo.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        furnitures_ = java.util.Collections.unmodifiableList(furnitures_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_FurnitureProduceChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_FurnitureProduceChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.FurnitureProduceChange.class, pb4client.FurnitureProduceChange.Builder.class);
  }

  public static final int FURNITURES_FIELD_NUMBER = 1;
  private java.util.List<pb4client.FurnitureTimeInfo> furnitures_;
  /**
   * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
   */
  public java.util.List<pb4client.FurnitureTimeInfo> getFurnituresList() {
    return furnitures_;
  }
  /**
   * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
   */
  public java.util.List<? extends pb4client.FurnitureTimeInfoOrBuilder> 
      getFurnituresOrBuilderList() {
    return furnitures_;
  }
  /**
   * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
   */
  public int getFurnituresCount() {
    return furnitures_.size();
  }
  /**
   * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
   */
  public pb4client.FurnitureTimeInfo getFurnitures(int index) {
    return furnitures_.get(index);
  }
  /**
   * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
   */
  public pb4client.FurnitureTimeInfoOrBuilder getFurnituresOrBuilder(
      int index) {
    return furnitures_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

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
    for (int i = 0; i < furnitures_.size(); i++) {
      output.writeMessage(1, furnitures_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < furnitures_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, furnitures_.get(i));
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
    if (!(obj instanceof pb4client.FurnitureProduceChange)) {
      return super.equals(obj);
    }
    pb4client.FurnitureProduceChange other = (pb4client.FurnitureProduceChange) obj;

    boolean result = true;
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
    if (getFurnituresCount() > 0) {
      hash = (37 * hash) + FURNITURES_FIELD_NUMBER;
      hash = (53 * hash) + getFurnituresList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.FurnitureProduceChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FurnitureProduceChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FurnitureProduceChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FurnitureProduceChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FurnitureProduceChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FurnitureProduceChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FurnitureProduceChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FurnitureProduceChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FurnitureProduceChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.FurnitureProduceChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FurnitureProduceChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FurnitureProduceChange parseFrom(
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
  public static Builder newBuilder(pb4client.FurnitureProduceChange prototype) {
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
   * msgType = 3180
   * 服务端 -&gt; 客户端
   * 后宅家具收集时间变化
   * </pre>
   *
   * Protobuf type {@code client2server.FurnitureProduceChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.FurnitureProduceChange)
      pb4client.FurnitureProduceChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_FurnitureProduceChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_FurnitureProduceChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.FurnitureProduceChange.class, pb4client.FurnitureProduceChange.Builder.class);
    }

    // Construct using pb4client.FurnitureProduceChange.newBuilder()
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
      if (furnituresBuilder_ == null) {
        furnitures_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        furnituresBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_FurnitureProduceChange_descriptor;
    }

    public pb4client.FurnitureProduceChange getDefaultInstanceForType() {
      return pb4client.FurnitureProduceChange.getDefaultInstance();
    }

    public pb4client.FurnitureProduceChange build() {
      pb4client.FurnitureProduceChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.FurnitureProduceChange buildPartial() {
      pb4client.FurnitureProduceChange result = new pb4client.FurnitureProduceChange(this);
      int from_bitField0_ = bitField0_;
      if (furnituresBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          furnitures_ = java.util.Collections.unmodifiableList(furnitures_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.furnitures_ = furnitures_;
      } else {
        result.furnitures_ = furnituresBuilder_.build();
      }
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
      if (other instanceof pb4client.FurnitureProduceChange) {
        return mergeFrom((pb4client.FurnitureProduceChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.FurnitureProduceChange other) {
      if (other == pb4client.FurnitureProduceChange.getDefaultInstance()) return this;
      if (furnituresBuilder_ == null) {
        if (!other.furnitures_.isEmpty()) {
          if (furnitures_.isEmpty()) {
            furnitures_ = other.furnitures_;
            bitField0_ = (bitField0_ & ~0x00000001);
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
            bitField0_ = (bitField0_ & ~0x00000001);
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
      pb4client.FurnitureProduceChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.FurnitureProduceChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4client.FurnitureTimeInfo> furnitures_ =
      java.util.Collections.emptyList();
    private void ensureFurnituresIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        furnitures_ = new java.util.ArrayList<pb4client.FurnitureTimeInfo>(furnitures_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.FurnitureTimeInfo, pb4client.FurnitureTimeInfo.Builder, pb4client.FurnitureTimeInfoOrBuilder> furnituresBuilder_;

    /**
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public java.util.List<pb4client.FurnitureTimeInfo> getFurnituresList() {
      if (furnituresBuilder_ == null) {
        return java.util.Collections.unmodifiableList(furnitures_);
      } else {
        return furnituresBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public int getFurnituresCount() {
      if (furnituresBuilder_ == null) {
        return furnitures_.size();
      } else {
        return furnituresBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public pb4client.FurnitureTimeInfo getFurnitures(int index) {
      if (furnituresBuilder_ == null) {
        return furnitures_.get(index);
      } else {
        return furnituresBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public Builder setFurnitures(
        int index, pb4client.FurnitureTimeInfo value) {
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
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public Builder setFurnitures(
        int index, pb4client.FurnitureTimeInfo.Builder builderForValue) {
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
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public Builder addFurnitures(pb4client.FurnitureTimeInfo value) {
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
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public Builder addFurnitures(
        int index, pb4client.FurnitureTimeInfo value) {
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
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public Builder addFurnitures(
        pb4client.FurnitureTimeInfo.Builder builderForValue) {
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
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public Builder addFurnitures(
        int index, pb4client.FurnitureTimeInfo.Builder builderForValue) {
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
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public Builder addAllFurnitures(
        java.lang.Iterable<? extends pb4client.FurnitureTimeInfo> values) {
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
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public Builder clearFurnitures() {
      if (furnituresBuilder_ == null) {
        furnitures_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        furnituresBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
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
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public pb4client.FurnitureTimeInfo.Builder getFurnituresBuilder(
        int index) {
      return getFurnituresFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public pb4client.FurnitureTimeInfoOrBuilder getFurnituresOrBuilder(
        int index) {
      if (furnituresBuilder_ == null) {
        return furnitures_.get(index);  } else {
        return furnituresBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public java.util.List<? extends pb4client.FurnitureTimeInfoOrBuilder> 
         getFurnituresOrBuilderList() {
      if (furnituresBuilder_ != null) {
        return furnituresBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(furnitures_);
      }
    }
    /**
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public pb4client.FurnitureTimeInfo.Builder addFurnituresBuilder() {
      return getFurnituresFieldBuilder().addBuilder(
          pb4client.FurnitureTimeInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public pb4client.FurnitureTimeInfo.Builder addFurnituresBuilder(
        int index) {
      return getFurnituresFieldBuilder().addBuilder(
          index, pb4client.FurnitureTimeInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.FurnitureTimeInfo furnitures = 1;</code>
     */
    public java.util.List<pb4client.FurnitureTimeInfo.Builder> 
         getFurnituresBuilderList() {
      return getFurnituresFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.FurnitureTimeInfo, pb4client.FurnitureTimeInfo.Builder, pb4client.FurnitureTimeInfoOrBuilder> 
        getFurnituresFieldBuilder() {
      if (furnituresBuilder_ == null) {
        furnituresBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.FurnitureTimeInfo, pb4client.FurnitureTimeInfo.Builder, pb4client.FurnitureTimeInfoOrBuilder>(
                furnitures_,
                ((bitField0_ & 0x00000001) == 0x00000001),
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


    // @@protoc_insertion_point(builder_scope:client2server.FurnitureProduceChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.FurnitureProduceChange)
  private static final pb4client.FurnitureProduceChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.FurnitureProduceChange();
  }

  public static pb4client.FurnitureProduceChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<FurnitureProduceChange>
      PARSER = new com.google.protobuf.AbstractParser<FurnitureProduceChange>() {
    public FurnitureProduceChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new FurnitureProduceChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FurnitureProduceChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FurnitureProduceChange> getParserForType() {
    return PARSER;
  }

  public pb4client.FurnitureProduceChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

