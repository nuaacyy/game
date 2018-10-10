// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.PlantRt}
 */
public  final class PlantRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.PlantRt)
    PlantRtOrBuilder {
  // Use PlantRt.newBuilder() to construct.
  private PlantRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PlantRt() {
    rt_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PlantRt(
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
            pb4client.PlantVo.Builder subBuilder = null;
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
              subBuilder = plantVo_.toBuilder();
            }
            plantVo_ = input.readMessage(pb4client.PlantVo.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(plantVo_);
              plantVo_ = subBuilder.buildPartial();
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
    return pb4client.War2GamePkt.internal_static_client2server_PlantRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_PlantRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.PlantRt.class, pb4client.PlantRt.Builder.class);
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

  public static final int PLANTVO_FIELD_NUMBER = 2;
  private pb4client.PlantVo plantVo_;
  /**
   * <code>optional .client2server.PlantVo plantVo = 2;</code>
   */
  public boolean hasPlantVo() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>optional .client2server.PlantVo plantVo = 2;</code>
   */
  public pb4client.PlantVo getPlantVo() {
    return plantVo_ == null ? pb4client.PlantVo.getDefaultInstance() : plantVo_;
  }
  /**
   * <code>optional .client2server.PlantVo plantVo = 2;</code>
   */
  public pb4client.PlantVoOrBuilder getPlantVoOrBuilder() {
    return plantVo_ == null ? pb4client.PlantVo.getDefaultInstance() : plantVo_;
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
    if (hasPlantVo()) {
      if (!getPlantVo().isInitialized()) {
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
      output.writeMessage(2, getPlantVo());
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
        .computeMessageSize(2, getPlantVo());
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
    if (!(obj instanceof pb4client.PlantRt)) {
      return super.equals(obj);
    }
    pb4client.PlantRt other = (pb4client.PlantRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasPlantVo() == other.hasPlantVo());
    if (hasPlantVo()) {
      result = result && getPlantVo()
          .equals(other.getPlantVo());
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
    if (hasRt()) {
      hash = (37 * hash) + RT_FIELD_NUMBER;
      hash = (53 * hash) + getRt();
    }
    if (hasPlantVo()) {
      hash = (37 * hash) + PLANTVO_FIELD_NUMBER;
      hash = (53 * hash) + getPlantVo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.PlantRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlantRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlantRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlantRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlantRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlantRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlantRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PlantRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PlantRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.PlantRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PlantRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PlantRt parseFrom(
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
  public static Builder newBuilder(pb4client.PlantRt prototype) {
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
   * Protobuf type {@code client2server.PlantRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.PlantRt)
      pb4client.PlantRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_PlantRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_PlantRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.PlantRt.class, pb4client.PlantRt.Builder.class);
    }

    // Construct using pb4client.PlantRt.newBuilder()
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
        getPlantVoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (plantVoBuilder_ == null) {
        plantVo_ = null;
      } else {
        plantVoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_PlantRt_descriptor;
    }

    public pb4client.PlantRt getDefaultInstanceForType() {
      return pb4client.PlantRt.getDefaultInstance();
    }

    public pb4client.PlantRt build() {
      pb4client.PlantRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.PlantRt buildPartial() {
      pb4client.PlantRt result = new pb4client.PlantRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      if (plantVoBuilder_ == null) {
        result.plantVo_ = plantVo_;
      } else {
        result.plantVo_ = plantVoBuilder_.build();
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
      if (other instanceof pb4client.PlantRt) {
        return mergeFrom((pb4client.PlantRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.PlantRt other) {
      if (other == pb4client.PlantRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasPlantVo()) {
        mergePlantVo(other.getPlantVo());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRt()) {
        return false;
      }
      if (hasPlantVo()) {
        if (!getPlantVo().isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.PlantRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.PlantRt) e.getUnfinishedMessage();
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

    private pb4client.PlantVo plantVo_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.PlantVo, pb4client.PlantVo.Builder, pb4client.PlantVoOrBuilder> plantVoBuilder_;
    /**
     * <code>optional .client2server.PlantVo plantVo = 2;</code>
     */
    public boolean hasPlantVo() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional .client2server.PlantVo plantVo = 2;</code>
     */
    public pb4client.PlantVo getPlantVo() {
      if (plantVoBuilder_ == null) {
        return plantVo_ == null ? pb4client.PlantVo.getDefaultInstance() : plantVo_;
      } else {
        return plantVoBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .client2server.PlantVo plantVo = 2;</code>
     */
    public Builder setPlantVo(pb4client.PlantVo value) {
      if (plantVoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        plantVo_ = value;
        onChanged();
      } else {
        plantVoBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>optional .client2server.PlantVo plantVo = 2;</code>
     */
    public Builder setPlantVo(
        pb4client.PlantVo.Builder builderForValue) {
      if (plantVoBuilder_ == null) {
        plantVo_ = builderForValue.build();
        onChanged();
      } else {
        plantVoBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>optional .client2server.PlantVo plantVo = 2;</code>
     */
    public Builder mergePlantVo(pb4client.PlantVo value) {
      if (plantVoBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002) &&
            plantVo_ != null &&
            plantVo_ != pb4client.PlantVo.getDefaultInstance()) {
          plantVo_ =
            pb4client.PlantVo.newBuilder(plantVo_).mergeFrom(value).buildPartial();
        } else {
          plantVo_ = value;
        }
        onChanged();
      } else {
        plantVoBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>optional .client2server.PlantVo plantVo = 2;</code>
     */
    public Builder clearPlantVo() {
      if (plantVoBuilder_ == null) {
        plantVo_ = null;
        onChanged();
      } else {
        plantVoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    /**
     * <code>optional .client2server.PlantVo plantVo = 2;</code>
     */
    public pb4client.PlantVo.Builder getPlantVoBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getPlantVoFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .client2server.PlantVo plantVo = 2;</code>
     */
    public pb4client.PlantVoOrBuilder getPlantVoOrBuilder() {
      if (plantVoBuilder_ != null) {
        return plantVoBuilder_.getMessageOrBuilder();
      } else {
        return plantVo_ == null ?
            pb4client.PlantVo.getDefaultInstance() : plantVo_;
      }
    }
    /**
     * <code>optional .client2server.PlantVo plantVo = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.PlantVo, pb4client.PlantVo.Builder, pb4client.PlantVoOrBuilder> 
        getPlantVoFieldBuilder() {
      if (plantVoBuilder_ == null) {
        plantVoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.PlantVo, pb4client.PlantVo.Builder, pb4client.PlantVoOrBuilder>(
                getPlantVo(),
                getParentForChildren(),
                isClean());
        plantVo_ = null;
      }
      return plantVoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.PlantRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.PlantRt)
  private static final pb4client.PlantRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.PlantRt();
  }

  public static pb4client.PlantRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<PlantRt>
      PARSER = new com.google.protobuf.AbstractParser<PlantRt>() {
    public PlantRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlantRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PlantRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PlantRt> getParserForType() {
    return PARSER;
  }

  public pb4client.PlantRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
