// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 * 吃士兵包
 * </pre>
 *
 * Protobuf type {@code pb4server.UseSoliderAddAskReq}
 */
public  final class UseSoliderAddAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.UseSoliderAddAskReq)
    UseSoliderAddAskReqOrBuilder {
  // Use UseSoliderAddAskReq.newBuilder() to construct.
  private UseSoliderAddAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UseSoliderAddAskReq() {
    useNum_ = 0;
    solidersAdd_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private UseSoliderAddAskReq(
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

            useNum_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              solidersAdd_ = new java.util.ArrayList<pb4server.SoliderVo>();
              mutable_bitField0_ |= 0x00000002;
            }
            solidersAdd_.add(
                input.readMessage(pb4server.SoliderVo.parser(), extensionRegistry));
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
        solidersAdd_ = java.util.Collections.unmodifiableList(solidersAdd_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalWkt.internal_static_pb4server_UseSoliderAddAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_UseSoliderAddAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.UseSoliderAddAskReq.class, pb4server.UseSoliderAddAskReq.Builder.class);
  }

  private int bitField0_;
  public static final int USENUM_FIELD_NUMBER = 1;
  private int useNum_;
  /**
   * <code>int32 useNum = 1;</code>
   */
  public int getUseNum() {
    return useNum_;
  }

  public static final int SOLIDERSADD_FIELD_NUMBER = 2;
  private java.util.List<pb4server.SoliderVo> solidersAdd_;
  /**
   * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
   */
  public java.util.List<pb4server.SoliderVo> getSolidersAddList() {
    return solidersAdd_;
  }
  /**
   * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
   */
  public java.util.List<? extends pb4server.SoliderVoOrBuilder> 
      getSolidersAddOrBuilderList() {
    return solidersAdd_;
  }
  /**
   * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
   */
  public int getSolidersAddCount() {
    return solidersAdd_.size();
  }
  /**
   * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
   */
  public pb4server.SoliderVo getSolidersAdd(int index) {
    return solidersAdd_.get(index);
  }
  /**
   * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
   */
  public pb4server.SoliderVoOrBuilder getSolidersAddOrBuilder(
      int index) {
    return solidersAdd_.get(index);
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
    if (useNum_ != 0) {
      output.writeInt32(1, useNum_);
    }
    for (int i = 0; i < solidersAdd_.size(); i++) {
      output.writeMessage(2, solidersAdd_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (useNum_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, useNum_);
    }
    for (int i = 0; i < solidersAdd_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, solidersAdd_.get(i));
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
    if (!(obj instanceof pb4server.UseSoliderAddAskReq)) {
      return super.equals(obj);
    }
    pb4server.UseSoliderAddAskReq other = (pb4server.UseSoliderAddAskReq) obj;

    boolean result = true;
    result = result && (getUseNum()
        == other.getUseNum());
    result = result && getSolidersAddList()
        .equals(other.getSolidersAddList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + USENUM_FIELD_NUMBER;
    hash = (53 * hash) + getUseNum();
    if (getSolidersAddCount() > 0) {
      hash = (37 * hash) + SOLIDERSADD_FIELD_NUMBER;
      hash = (53 * hash) + getSolidersAddList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.UseSoliderAddAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.UseSoliderAddAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.UseSoliderAddAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.UseSoliderAddAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.UseSoliderAddAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.UseSoliderAddAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.UseSoliderAddAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.UseSoliderAddAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.UseSoliderAddAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.UseSoliderAddAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.UseSoliderAddAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.UseSoliderAddAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.UseSoliderAddAskReq prototype) {
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
   * 吃士兵包
   * </pre>
   *
   * Protobuf type {@code pb4server.UseSoliderAddAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.UseSoliderAddAskReq)
      pb4server.UseSoliderAddAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_UseSoliderAddAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_UseSoliderAddAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.UseSoliderAddAskReq.class, pb4server.UseSoliderAddAskReq.Builder.class);
    }

    // Construct using pb4server.UseSoliderAddAskReq.newBuilder()
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
        getSolidersAddFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      useNum_ = 0;

      if (solidersAddBuilder_ == null) {
        solidersAdd_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        solidersAddBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_UseSoliderAddAskReq_descriptor;
    }

    public pb4server.UseSoliderAddAskReq getDefaultInstanceForType() {
      return pb4server.UseSoliderAddAskReq.getDefaultInstance();
    }

    public pb4server.UseSoliderAddAskReq build() {
      pb4server.UseSoliderAddAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.UseSoliderAddAskReq buildPartial() {
      pb4server.UseSoliderAddAskReq result = new pb4server.UseSoliderAddAskReq(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.useNum_ = useNum_;
      if (solidersAddBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          solidersAdd_ = java.util.Collections.unmodifiableList(solidersAdd_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.solidersAdd_ = solidersAdd_;
      } else {
        result.solidersAdd_ = solidersAddBuilder_.build();
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
      if (other instanceof pb4server.UseSoliderAddAskReq) {
        return mergeFrom((pb4server.UseSoliderAddAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.UseSoliderAddAskReq other) {
      if (other == pb4server.UseSoliderAddAskReq.getDefaultInstance()) return this;
      if (other.getUseNum() != 0) {
        setUseNum(other.getUseNum());
      }
      if (solidersAddBuilder_ == null) {
        if (!other.solidersAdd_.isEmpty()) {
          if (solidersAdd_.isEmpty()) {
            solidersAdd_ = other.solidersAdd_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureSolidersAddIsMutable();
            solidersAdd_.addAll(other.solidersAdd_);
          }
          onChanged();
        }
      } else {
        if (!other.solidersAdd_.isEmpty()) {
          if (solidersAddBuilder_.isEmpty()) {
            solidersAddBuilder_.dispose();
            solidersAddBuilder_ = null;
            solidersAdd_ = other.solidersAdd_;
            bitField0_ = (bitField0_ & ~0x00000002);
            solidersAddBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getSolidersAddFieldBuilder() : null;
          } else {
            solidersAddBuilder_.addAllMessages(other.solidersAdd_);
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
      pb4server.UseSoliderAddAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.UseSoliderAddAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int useNum_ ;
    /**
     * <code>int32 useNum = 1;</code>
     */
    public int getUseNum() {
      return useNum_;
    }
    /**
     * <code>int32 useNum = 1;</code>
     */
    public Builder setUseNum(int value) {
      
      useNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 useNum = 1;</code>
     */
    public Builder clearUseNum() {
      
      useNum_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4server.SoliderVo> solidersAdd_ =
      java.util.Collections.emptyList();
    private void ensureSolidersAddIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        solidersAdd_ = new java.util.ArrayList<pb4server.SoliderVo>(solidersAdd_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.SoliderVo, pb4server.SoliderVo.Builder, pb4server.SoliderVoOrBuilder> solidersAddBuilder_;

    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public java.util.List<pb4server.SoliderVo> getSolidersAddList() {
      if (solidersAddBuilder_ == null) {
        return java.util.Collections.unmodifiableList(solidersAdd_);
      } else {
        return solidersAddBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public int getSolidersAddCount() {
      if (solidersAddBuilder_ == null) {
        return solidersAdd_.size();
      } else {
        return solidersAddBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public pb4server.SoliderVo getSolidersAdd(int index) {
      if (solidersAddBuilder_ == null) {
        return solidersAdd_.get(index);
      } else {
        return solidersAddBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public Builder setSolidersAdd(
        int index, pb4server.SoliderVo value) {
      if (solidersAddBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSolidersAddIsMutable();
        solidersAdd_.set(index, value);
        onChanged();
      } else {
        solidersAddBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public Builder setSolidersAdd(
        int index, pb4server.SoliderVo.Builder builderForValue) {
      if (solidersAddBuilder_ == null) {
        ensureSolidersAddIsMutable();
        solidersAdd_.set(index, builderForValue.build());
        onChanged();
      } else {
        solidersAddBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public Builder addSolidersAdd(pb4server.SoliderVo value) {
      if (solidersAddBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSolidersAddIsMutable();
        solidersAdd_.add(value);
        onChanged();
      } else {
        solidersAddBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public Builder addSolidersAdd(
        int index, pb4server.SoliderVo value) {
      if (solidersAddBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSolidersAddIsMutable();
        solidersAdd_.add(index, value);
        onChanged();
      } else {
        solidersAddBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public Builder addSolidersAdd(
        pb4server.SoliderVo.Builder builderForValue) {
      if (solidersAddBuilder_ == null) {
        ensureSolidersAddIsMutable();
        solidersAdd_.add(builderForValue.build());
        onChanged();
      } else {
        solidersAddBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public Builder addSolidersAdd(
        int index, pb4server.SoliderVo.Builder builderForValue) {
      if (solidersAddBuilder_ == null) {
        ensureSolidersAddIsMutable();
        solidersAdd_.add(index, builderForValue.build());
        onChanged();
      } else {
        solidersAddBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public Builder addAllSolidersAdd(
        java.lang.Iterable<? extends pb4server.SoliderVo> values) {
      if (solidersAddBuilder_ == null) {
        ensureSolidersAddIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, solidersAdd_);
        onChanged();
      } else {
        solidersAddBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public Builder clearSolidersAdd() {
      if (solidersAddBuilder_ == null) {
        solidersAdd_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        solidersAddBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public Builder removeSolidersAdd(int index) {
      if (solidersAddBuilder_ == null) {
        ensureSolidersAddIsMutable();
        solidersAdd_.remove(index);
        onChanged();
      } else {
        solidersAddBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public pb4server.SoliderVo.Builder getSolidersAddBuilder(
        int index) {
      return getSolidersAddFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public pb4server.SoliderVoOrBuilder getSolidersAddOrBuilder(
        int index) {
      if (solidersAddBuilder_ == null) {
        return solidersAdd_.get(index);  } else {
        return solidersAddBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public java.util.List<? extends pb4server.SoliderVoOrBuilder> 
         getSolidersAddOrBuilderList() {
      if (solidersAddBuilder_ != null) {
        return solidersAddBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(solidersAdd_);
      }
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public pb4server.SoliderVo.Builder addSolidersAddBuilder() {
      return getSolidersAddFieldBuilder().addBuilder(
          pb4server.SoliderVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public pb4server.SoliderVo.Builder addSolidersAddBuilder(
        int index) {
      return getSolidersAddFieldBuilder().addBuilder(
          index, pb4server.SoliderVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.SoliderVo solidersAdd = 2;</code>
     */
    public java.util.List<pb4server.SoliderVo.Builder> 
         getSolidersAddBuilderList() {
      return getSolidersAddFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.SoliderVo, pb4server.SoliderVo.Builder, pb4server.SoliderVoOrBuilder> 
        getSolidersAddFieldBuilder() {
      if (solidersAddBuilder_ == null) {
        solidersAddBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.SoliderVo, pb4server.SoliderVo.Builder, pb4server.SoliderVoOrBuilder>(
                solidersAdd_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        solidersAdd_ = null;
      }
      return solidersAddBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.UseSoliderAddAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.UseSoliderAddAskReq)
  private static final pb4server.UseSoliderAddAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.UseSoliderAddAskReq();
  }

  public static pb4server.UseSoliderAddAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UseSoliderAddAskReq>
      PARSER = new com.google.protobuf.AbstractParser<UseSoliderAddAskReq>() {
    public UseSoliderAddAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new UseSoliderAddAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UseSoliderAddAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UseSoliderAddAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.UseSoliderAddAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

