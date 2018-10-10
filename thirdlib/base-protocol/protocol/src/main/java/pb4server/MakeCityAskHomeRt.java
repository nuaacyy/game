// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.MakeCityAskHomeRt}
 */
public  final class MakeCityAskHomeRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.MakeCityAskHomeRt)
    MakeCityAskHomeRtOrBuilder {
  // Use MakeCityAskHomeRt.newBuilder() to construct.
  private MakeCityAskHomeRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MakeCityAskHomeRt() {
    rt_ = 0;
    initHeroMap_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private MakeCityAskHomeRt(
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

            rt_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              initHeroMap_ = new java.util.ArrayList<pb4server.HeroForWorldMakeCity>();
              mutable_bitField0_ |= 0x00000002;
            }
            initHeroMap_.add(
                input.readMessage(pb4server.HeroForWorldMakeCity.parser(), extensionRegistry));
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
        initHeroMap_ = java.util.Collections.unmodifiableList(initHeroMap_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalHkt.internal_static_pb4server_MakeCityAskHomeRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_MakeCityAskHomeRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.MakeCityAskHomeRt.class, pb4server.MakeCityAskHomeRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int INITHEROMAP_FIELD_NUMBER = 2;
  private java.util.List<pb4server.HeroForWorldMakeCity> initHeroMap_;
  /**
   * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
   */
  public java.util.List<pb4server.HeroForWorldMakeCity> getInitHeroMapList() {
    return initHeroMap_;
  }
  /**
   * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
   */
  public java.util.List<? extends pb4server.HeroForWorldMakeCityOrBuilder> 
      getInitHeroMapOrBuilderList() {
    return initHeroMap_;
  }
  /**
   * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
   */
  public int getInitHeroMapCount() {
    return initHeroMap_.size();
  }
  /**
   * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
   */
  public pb4server.HeroForWorldMakeCity getInitHeroMap(int index) {
    return initHeroMap_.get(index);
  }
  /**
   * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
   */
  public pb4server.HeroForWorldMakeCityOrBuilder getInitHeroMapOrBuilder(
      int index) {
    return initHeroMap_.get(index);
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
    if (rt_ != 0) {
      output.writeInt32(1, rt_);
    }
    for (int i = 0; i < initHeroMap_.size(); i++) {
      output.writeMessage(2, initHeroMap_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (rt_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rt_);
    }
    for (int i = 0; i < initHeroMap_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, initHeroMap_.get(i));
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
    if (!(obj instanceof pb4server.MakeCityAskHomeRt)) {
      return super.equals(obj);
    }
    pb4server.MakeCityAskHomeRt other = (pb4server.MakeCityAskHomeRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && getInitHeroMapList()
        .equals(other.getInitHeroMapList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RT_FIELD_NUMBER;
    hash = (53 * hash) + getRt();
    if (getInitHeroMapCount() > 0) {
      hash = (37 * hash) + INITHEROMAP_FIELD_NUMBER;
      hash = (53 * hash) + getInitHeroMapList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.MakeCityAskHomeRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.MakeCityAskHomeRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.MakeCityAskHomeRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.MakeCityAskHomeRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.MakeCityAskHomeRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.MakeCityAskHomeRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.MakeCityAskHomeRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.MakeCityAskHomeRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.MakeCityAskHomeRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.MakeCityAskHomeRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.MakeCityAskHomeRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.MakeCityAskHomeRt parseFrom(
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
  public static Builder newBuilder(pb4server.MakeCityAskHomeRt prototype) {
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
   * Protobuf type {@code pb4server.MakeCityAskHomeRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.MakeCityAskHomeRt)
      pb4server.MakeCityAskHomeRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_MakeCityAskHomeRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_MakeCityAskHomeRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.MakeCityAskHomeRt.class, pb4server.MakeCityAskHomeRt.Builder.class);
    }

    // Construct using pb4server.MakeCityAskHomeRt.newBuilder()
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
        getInitHeroMapFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;

      if (initHeroMapBuilder_ == null) {
        initHeroMap_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        initHeroMapBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_MakeCityAskHomeRt_descriptor;
    }

    public pb4server.MakeCityAskHomeRt getDefaultInstanceForType() {
      return pb4server.MakeCityAskHomeRt.getDefaultInstance();
    }

    public pb4server.MakeCityAskHomeRt build() {
      pb4server.MakeCityAskHomeRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.MakeCityAskHomeRt buildPartial() {
      pb4server.MakeCityAskHomeRt result = new pb4server.MakeCityAskHomeRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.rt_ = rt_;
      if (initHeroMapBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          initHeroMap_ = java.util.Collections.unmodifiableList(initHeroMap_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.initHeroMap_ = initHeroMap_;
      } else {
        result.initHeroMap_ = initHeroMapBuilder_.build();
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
      if (other instanceof pb4server.MakeCityAskHomeRt) {
        return mergeFrom((pb4server.MakeCityAskHomeRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.MakeCityAskHomeRt other) {
      if (other == pb4server.MakeCityAskHomeRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (initHeroMapBuilder_ == null) {
        if (!other.initHeroMap_.isEmpty()) {
          if (initHeroMap_.isEmpty()) {
            initHeroMap_ = other.initHeroMap_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureInitHeroMapIsMutable();
            initHeroMap_.addAll(other.initHeroMap_);
          }
          onChanged();
        }
      } else {
        if (!other.initHeroMap_.isEmpty()) {
          if (initHeroMapBuilder_.isEmpty()) {
            initHeroMapBuilder_.dispose();
            initHeroMapBuilder_ = null;
            initHeroMap_ = other.initHeroMap_;
            bitField0_ = (bitField0_ & ~0x00000002);
            initHeroMapBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getInitHeroMapFieldBuilder() : null;
          } else {
            initHeroMapBuilder_.addAllMessages(other.initHeroMap_);
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
      pb4server.MakeCityAskHomeRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.MakeCityAskHomeRt) e.getUnfinishedMessage();
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
     * <code>int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <code>int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 rt = 1;</code>
     */
    public Builder clearRt() {
      
      rt_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4server.HeroForWorldMakeCity> initHeroMap_ =
      java.util.Collections.emptyList();
    private void ensureInitHeroMapIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        initHeroMap_ = new java.util.ArrayList<pb4server.HeroForWorldMakeCity>(initHeroMap_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.HeroForWorldMakeCity, pb4server.HeroForWorldMakeCity.Builder, pb4server.HeroForWorldMakeCityOrBuilder> initHeroMapBuilder_;

    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public java.util.List<pb4server.HeroForWorldMakeCity> getInitHeroMapList() {
      if (initHeroMapBuilder_ == null) {
        return java.util.Collections.unmodifiableList(initHeroMap_);
      } else {
        return initHeroMapBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public int getInitHeroMapCount() {
      if (initHeroMapBuilder_ == null) {
        return initHeroMap_.size();
      } else {
        return initHeroMapBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public pb4server.HeroForWorldMakeCity getInitHeroMap(int index) {
      if (initHeroMapBuilder_ == null) {
        return initHeroMap_.get(index);
      } else {
        return initHeroMapBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public Builder setInitHeroMap(
        int index, pb4server.HeroForWorldMakeCity value) {
      if (initHeroMapBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureInitHeroMapIsMutable();
        initHeroMap_.set(index, value);
        onChanged();
      } else {
        initHeroMapBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public Builder setInitHeroMap(
        int index, pb4server.HeroForWorldMakeCity.Builder builderForValue) {
      if (initHeroMapBuilder_ == null) {
        ensureInitHeroMapIsMutable();
        initHeroMap_.set(index, builderForValue.build());
        onChanged();
      } else {
        initHeroMapBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public Builder addInitHeroMap(pb4server.HeroForWorldMakeCity value) {
      if (initHeroMapBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureInitHeroMapIsMutable();
        initHeroMap_.add(value);
        onChanged();
      } else {
        initHeroMapBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public Builder addInitHeroMap(
        int index, pb4server.HeroForWorldMakeCity value) {
      if (initHeroMapBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureInitHeroMapIsMutable();
        initHeroMap_.add(index, value);
        onChanged();
      } else {
        initHeroMapBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public Builder addInitHeroMap(
        pb4server.HeroForWorldMakeCity.Builder builderForValue) {
      if (initHeroMapBuilder_ == null) {
        ensureInitHeroMapIsMutable();
        initHeroMap_.add(builderForValue.build());
        onChanged();
      } else {
        initHeroMapBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public Builder addInitHeroMap(
        int index, pb4server.HeroForWorldMakeCity.Builder builderForValue) {
      if (initHeroMapBuilder_ == null) {
        ensureInitHeroMapIsMutable();
        initHeroMap_.add(index, builderForValue.build());
        onChanged();
      } else {
        initHeroMapBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public Builder addAllInitHeroMap(
        java.lang.Iterable<? extends pb4server.HeroForWorldMakeCity> values) {
      if (initHeroMapBuilder_ == null) {
        ensureInitHeroMapIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, initHeroMap_);
        onChanged();
      } else {
        initHeroMapBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public Builder clearInitHeroMap() {
      if (initHeroMapBuilder_ == null) {
        initHeroMap_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        initHeroMapBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public Builder removeInitHeroMap(int index) {
      if (initHeroMapBuilder_ == null) {
        ensureInitHeroMapIsMutable();
        initHeroMap_.remove(index);
        onChanged();
      } else {
        initHeroMapBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public pb4server.HeroForWorldMakeCity.Builder getInitHeroMapBuilder(
        int index) {
      return getInitHeroMapFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public pb4server.HeroForWorldMakeCityOrBuilder getInitHeroMapOrBuilder(
        int index) {
      if (initHeroMapBuilder_ == null) {
        return initHeroMap_.get(index);  } else {
        return initHeroMapBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public java.util.List<? extends pb4server.HeroForWorldMakeCityOrBuilder> 
         getInitHeroMapOrBuilderList() {
      if (initHeroMapBuilder_ != null) {
        return initHeroMapBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(initHeroMap_);
      }
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public pb4server.HeroForWorldMakeCity.Builder addInitHeroMapBuilder() {
      return getInitHeroMapFieldBuilder().addBuilder(
          pb4server.HeroForWorldMakeCity.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public pb4server.HeroForWorldMakeCity.Builder addInitHeroMapBuilder(
        int index) {
      return getInitHeroMapFieldBuilder().addBuilder(
          index, pb4server.HeroForWorldMakeCity.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.HeroForWorldMakeCity initHeroMap = 2;</code>
     */
    public java.util.List<pb4server.HeroForWorldMakeCity.Builder> 
         getInitHeroMapBuilderList() {
      return getInitHeroMapFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.HeroForWorldMakeCity, pb4server.HeroForWorldMakeCity.Builder, pb4server.HeroForWorldMakeCityOrBuilder> 
        getInitHeroMapFieldBuilder() {
      if (initHeroMapBuilder_ == null) {
        initHeroMapBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.HeroForWorldMakeCity, pb4server.HeroForWorldMakeCity.Builder, pb4server.HeroForWorldMakeCityOrBuilder>(
                initHeroMap_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        initHeroMap_ = null;
      }
      return initHeroMapBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.MakeCityAskHomeRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.MakeCityAskHomeRt)
  private static final pb4server.MakeCityAskHomeRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.MakeCityAskHomeRt();
  }

  public static pb4server.MakeCityAskHomeRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MakeCityAskHomeRt>
      PARSER = new com.google.protobuf.AbstractParser<MakeCityAskHomeRt>() {
    public MakeCityAskHomeRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new MakeCityAskHomeRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MakeCityAskHomeRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MakeCityAskHomeRt> getParserForType() {
    return PARSER;
  }

  public pb4server.MakeCityAskHomeRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

