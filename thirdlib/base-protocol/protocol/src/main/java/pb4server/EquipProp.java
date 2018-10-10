// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.EquipProp}
 */
public  final class EquipProp extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.EquipProp)
    EquipPropOrBuilder {
  // Use EquipProp.newBuilder() to construct.
  private EquipProp(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EquipProp() {
    propType_ = 0;
    propValue_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private EquipProp(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            propType_ = input.readInt32();
            break;
          }
          case 16: {

            propValue_ = input.readInt32();
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
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalHkt.internal_static_pb4server_EquipProp_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_EquipProp_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.EquipProp.class, pb4server.EquipProp.Builder.class);
  }

  public static final int PROPTYPE_FIELD_NUMBER = 1;
  private int propType_;
  /**
   * <pre>
   *属性代号
   * </pre>
   *
   * <code>int32 propType = 1;</code>
   */
  public int getPropType() {
    return propType_;
  }

  public static final int PROPVALUE_FIELD_NUMBER = 2;
  private int propValue_;
  /**
   * <pre>
   *属性值
   * </pre>
   *
   * <code>int32 propValue = 2;</code>
   */
  public int getPropValue() {
    return propValue_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (propType_ != 0) {
      output.writeInt32(1, propType_);
    }
    if (propValue_ != 0) {
      output.writeInt32(2, propValue_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (propType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, propType_);
    }
    if (propValue_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, propValue_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof pb4server.EquipProp)) {
      return super.equals(obj);
    }
    pb4server.EquipProp other = (pb4server.EquipProp) obj;

    boolean result = true;
    result = result && (getPropType()
        == other.getPropType());
    result = result && (getPropValue()
        == other.getPropValue());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PROPTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getPropType();
    hash = (37 * hash) + PROPVALUE_FIELD_NUMBER;
    hash = (53 * hash) + getPropValue();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.EquipProp parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.EquipProp parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.EquipProp parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.EquipProp parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.EquipProp parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.EquipProp parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.EquipProp parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.EquipProp parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.EquipProp parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.EquipProp parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.EquipProp parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.EquipProp parseFrom(
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
  public static Builder newBuilder(pb4server.EquipProp prototype) {
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
   * Protobuf type {@code pb4server.EquipProp}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.EquipProp)
      pb4server.EquipPropOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_EquipProp_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_EquipProp_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.EquipProp.class, pb4server.EquipProp.Builder.class);
    }

    // Construct using pb4server.EquipProp.newBuilder()
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
      propType_ = 0;

      propValue_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_EquipProp_descriptor;
    }

    public pb4server.EquipProp getDefaultInstanceForType() {
      return pb4server.EquipProp.getDefaultInstance();
    }

    public pb4server.EquipProp build() {
      pb4server.EquipProp result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.EquipProp buildPartial() {
      pb4server.EquipProp result = new pb4server.EquipProp(this);
      result.propType_ = propType_;
      result.propValue_ = propValue_;
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
      if (other instanceof pb4server.EquipProp) {
        return mergeFrom((pb4server.EquipProp)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.EquipProp other) {
      if (other == pb4server.EquipProp.getDefaultInstance()) return this;
      if (other.getPropType() != 0) {
        setPropType(other.getPropType());
      }
      if (other.getPropValue() != 0) {
        setPropValue(other.getPropValue());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4server.EquipProp parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.EquipProp) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int propType_ ;
    /**
     * <pre>
     *属性代号
     * </pre>
     *
     * <code>int32 propType = 1;</code>
     */
    public int getPropType() {
      return propType_;
    }
    /**
     * <pre>
     *属性代号
     * </pre>
     *
     * <code>int32 propType = 1;</code>
     */
    public Builder setPropType(int value) {
      
      propType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *属性代号
     * </pre>
     *
     * <code>int32 propType = 1;</code>
     */
    public Builder clearPropType() {
      
      propType_ = 0;
      onChanged();
      return this;
    }

    private int propValue_ ;
    /**
     * <pre>
     *属性值
     * </pre>
     *
     * <code>int32 propValue = 2;</code>
     */
    public int getPropValue() {
      return propValue_;
    }
    /**
     * <pre>
     *属性值
     * </pre>
     *
     * <code>int32 propValue = 2;</code>
     */
    public Builder setPropValue(int value) {
      
      propValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *属性值
     * </pre>
     *
     * <code>int32 propValue = 2;</code>
     */
    public Builder clearPropValue() {
      
      propValue_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.EquipProp)
  }

  // @@protoc_insertion_point(class_scope:pb4server.EquipProp)
  private static final pb4server.EquipProp DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.EquipProp();
  }

  public static pb4server.EquipProp getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EquipProp>
      PARSER = new com.google.protobuf.AbstractParser<EquipProp>() {
    public EquipProp parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new EquipProp(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EquipProp> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EquipProp> getParserForType() {
    return PARSER;
  }

  public pb4server.EquipProp getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

