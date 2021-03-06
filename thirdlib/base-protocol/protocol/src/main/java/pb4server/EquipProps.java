// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.EquipProps}
 */
public  final class EquipProps extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.EquipProps)
    EquipPropsOrBuilder {
  // Use EquipProps.newBuilder() to construct.
  private EquipProps(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EquipProps() {
    propAddress_ = 0;
    propValues_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private EquipProps(
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

            propAddress_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              propValues_ = new java.util.ArrayList<pb4server.EquipProp>();
              mutable_bitField0_ |= 0x00000002;
            }
            propValues_.add(
                input.readMessage(pb4server.EquipProp.parser(), extensionRegistry));
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
        propValues_ = java.util.Collections.unmodifiableList(propValues_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalHkt.internal_static_pb4server_EquipProps_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_EquipProps_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.EquipProps.class, pb4server.EquipProps.Builder.class);
  }

  private int bitField0_;
  public static final int PROPADDRESS_FIELD_NUMBER = 1;
  private int propAddress_;
  /**
   * <pre>
   *属性位置
   * </pre>
   *
   * <code>int32 propAddress = 1;</code>
   */
  public int getPropAddress() {
    return propAddress_;
  }

  public static final int PROPVALUES_FIELD_NUMBER = 2;
  private java.util.List<pb4server.EquipProp> propValues_;
  /**
   * <pre>
   *属性
   * </pre>
   *
   * <code>repeated .pb4server.EquipProp propValues = 2;</code>
   */
  public java.util.List<pb4server.EquipProp> getPropValuesList() {
    return propValues_;
  }
  /**
   * <pre>
   *属性
   * </pre>
   *
   * <code>repeated .pb4server.EquipProp propValues = 2;</code>
   */
  public java.util.List<? extends pb4server.EquipPropOrBuilder> 
      getPropValuesOrBuilderList() {
    return propValues_;
  }
  /**
   * <pre>
   *属性
   * </pre>
   *
   * <code>repeated .pb4server.EquipProp propValues = 2;</code>
   */
  public int getPropValuesCount() {
    return propValues_.size();
  }
  /**
   * <pre>
   *属性
   * </pre>
   *
   * <code>repeated .pb4server.EquipProp propValues = 2;</code>
   */
  public pb4server.EquipProp getPropValues(int index) {
    return propValues_.get(index);
  }
  /**
   * <pre>
   *属性
   * </pre>
   *
   * <code>repeated .pb4server.EquipProp propValues = 2;</code>
   */
  public pb4server.EquipPropOrBuilder getPropValuesOrBuilder(
      int index) {
    return propValues_.get(index);
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
    if (propAddress_ != 0) {
      output.writeInt32(1, propAddress_);
    }
    for (int i = 0; i < propValues_.size(); i++) {
      output.writeMessage(2, propValues_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (propAddress_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, propAddress_);
    }
    for (int i = 0; i < propValues_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, propValues_.get(i));
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
    if (!(obj instanceof pb4server.EquipProps)) {
      return super.equals(obj);
    }
    pb4server.EquipProps other = (pb4server.EquipProps) obj;

    boolean result = true;
    result = result && (getPropAddress()
        == other.getPropAddress());
    result = result && getPropValuesList()
        .equals(other.getPropValuesList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PROPADDRESS_FIELD_NUMBER;
    hash = (53 * hash) + getPropAddress();
    if (getPropValuesCount() > 0) {
      hash = (37 * hash) + PROPVALUES_FIELD_NUMBER;
      hash = (53 * hash) + getPropValuesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.EquipProps parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.EquipProps parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.EquipProps parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.EquipProps parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.EquipProps parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.EquipProps parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.EquipProps parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.EquipProps parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.EquipProps parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.EquipProps parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.EquipProps parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.EquipProps parseFrom(
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
  public static Builder newBuilder(pb4server.EquipProps prototype) {
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
   * Protobuf type {@code pb4server.EquipProps}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.EquipProps)
      pb4server.EquipPropsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_EquipProps_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_EquipProps_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.EquipProps.class, pb4server.EquipProps.Builder.class);
    }

    // Construct using pb4server.EquipProps.newBuilder()
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
        getPropValuesFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      propAddress_ = 0;

      if (propValuesBuilder_ == null) {
        propValues_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        propValuesBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_EquipProps_descriptor;
    }

    public pb4server.EquipProps getDefaultInstanceForType() {
      return pb4server.EquipProps.getDefaultInstance();
    }

    public pb4server.EquipProps build() {
      pb4server.EquipProps result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.EquipProps buildPartial() {
      pb4server.EquipProps result = new pb4server.EquipProps(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.propAddress_ = propAddress_;
      if (propValuesBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          propValues_ = java.util.Collections.unmodifiableList(propValues_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.propValues_ = propValues_;
      } else {
        result.propValues_ = propValuesBuilder_.build();
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
      if (other instanceof pb4server.EquipProps) {
        return mergeFrom((pb4server.EquipProps)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.EquipProps other) {
      if (other == pb4server.EquipProps.getDefaultInstance()) return this;
      if (other.getPropAddress() != 0) {
        setPropAddress(other.getPropAddress());
      }
      if (propValuesBuilder_ == null) {
        if (!other.propValues_.isEmpty()) {
          if (propValues_.isEmpty()) {
            propValues_ = other.propValues_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensurePropValuesIsMutable();
            propValues_.addAll(other.propValues_);
          }
          onChanged();
        }
      } else {
        if (!other.propValues_.isEmpty()) {
          if (propValuesBuilder_.isEmpty()) {
            propValuesBuilder_.dispose();
            propValuesBuilder_ = null;
            propValues_ = other.propValues_;
            bitField0_ = (bitField0_ & ~0x00000002);
            propValuesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getPropValuesFieldBuilder() : null;
          } else {
            propValuesBuilder_.addAllMessages(other.propValues_);
          }
        }
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
      pb4server.EquipProps parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.EquipProps) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int propAddress_ ;
    /**
     * <pre>
     *属性位置
     * </pre>
     *
     * <code>int32 propAddress = 1;</code>
     */
    public int getPropAddress() {
      return propAddress_;
    }
    /**
     * <pre>
     *属性位置
     * </pre>
     *
     * <code>int32 propAddress = 1;</code>
     */
    public Builder setPropAddress(int value) {
      
      propAddress_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *属性位置
     * </pre>
     *
     * <code>int32 propAddress = 1;</code>
     */
    public Builder clearPropAddress() {
      
      propAddress_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4server.EquipProp> propValues_ =
      java.util.Collections.emptyList();
    private void ensurePropValuesIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        propValues_ = new java.util.ArrayList<pb4server.EquipProp>(propValues_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.EquipProp, pb4server.EquipProp.Builder, pb4server.EquipPropOrBuilder> propValuesBuilder_;

    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public java.util.List<pb4server.EquipProp> getPropValuesList() {
      if (propValuesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(propValues_);
      } else {
        return propValuesBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public int getPropValuesCount() {
      if (propValuesBuilder_ == null) {
        return propValues_.size();
      } else {
        return propValuesBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public pb4server.EquipProp getPropValues(int index) {
      if (propValuesBuilder_ == null) {
        return propValues_.get(index);
      } else {
        return propValuesBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public Builder setPropValues(
        int index, pb4server.EquipProp value) {
      if (propValuesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePropValuesIsMutable();
        propValues_.set(index, value);
        onChanged();
      } else {
        propValuesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public Builder setPropValues(
        int index, pb4server.EquipProp.Builder builderForValue) {
      if (propValuesBuilder_ == null) {
        ensurePropValuesIsMutable();
        propValues_.set(index, builderForValue.build());
        onChanged();
      } else {
        propValuesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public Builder addPropValues(pb4server.EquipProp value) {
      if (propValuesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePropValuesIsMutable();
        propValues_.add(value);
        onChanged();
      } else {
        propValuesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public Builder addPropValues(
        int index, pb4server.EquipProp value) {
      if (propValuesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePropValuesIsMutable();
        propValues_.add(index, value);
        onChanged();
      } else {
        propValuesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public Builder addPropValues(
        pb4server.EquipProp.Builder builderForValue) {
      if (propValuesBuilder_ == null) {
        ensurePropValuesIsMutable();
        propValues_.add(builderForValue.build());
        onChanged();
      } else {
        propValuesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public Builder addPropValues(
        int index, pb4server.EquipProp.Builder builderForValue) {
      if (propValuesBuilder_ == null) {
        ensurePropValuesIsMutable();
        propValues_.add(index, builderForValue.build());
        onChanged();
      } else {
        propValuesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public Builder addAllPropValues(
        java.lang.Iterable<? extends pb4server.EquipProp> values) {
      if (propValuesBuilder_ == null) {
        ensurePropValuesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, propValues_);
        onChanged();
      } else {
        propValuesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public Builder clearPropValues() {
      if (propValuesBuilder_ == null) {
        propValues_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        propValuesBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public Builder removePropValues(int index) {
      if (propValuesBuilder_ == null) {
        ensurePropValuesIsMutable();
        propValues_.remove(index);
        onChanged();
      } else {
        propValuesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public pb4server.EquipProp.Builder getPropValuesBuilder(
        int index) {
      return getPropValuesFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public pb4server.EquipPropOrBuilder getPropValuesOrBuilder(
        int index) {
      if (propValuesBuilder_ == null) {
        return propValues_.get(index);  } else {
        return propValuesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public java.util.List<? extends pb4server.EquipPropOrBuilder> 
         getPropValuesOrBuilderList() {
      if (propValuesBuilder_ != null) {
        return propValuesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(propValues_);
      }
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public pb4server.EquipProp.Builder addPropValuesBuilder() {
      return getPropValuesFieldBuilder().addBuilder(
          pb4server.EquipProp.getDefaultInstance());
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public pb4server.EquipProp.Builder addPropValuesBuilder(
        int index) {
      return getPropValuesFieldBuilder().addBuilder(
          index, pb4server.EquipProp.getDefaultInstance());
    }
    /**
     * <pre>
     *属性
     * </pre>
     *
     * <code>repeated .pb4server.EquipProp propValues = 2;</code>
     */
    public java.util.List<pb4server.EquipProp.Builder> 
         getPropValuesBuilderList() {
      return getPropValuesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.EquipProp, pb4server.EquipProp.Builder, pb4server.EquipPropOrBuilder> 
        getPropValuesFieldBuilder() {
      if (propValuesBuilder_ == null) {
        propValuesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.EquipProp, pb4server.EquipProp.Builder, pb4server.EquipPropOrBuilder>(
                propValues_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        propValues_ = null;
      }
      return propValuesBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.EquipProps)
  }

  // @@protoc_insertion_point(class_scope:pb4server.EquipProps)
  private static final pb4server.EquipProps DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.EquipProps();
  }

  public static pb4server.EquipProps getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EquipProps>
      PARSER = new com.google.protobuf.AbstractParser<EquipProps>() {
    public EquipProps parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new EquipProps(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EquipProps> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EquipProps> getParserForType() {
    return PARSER;
  }

  public pb4server.EquipProps getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

