// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 *运输资源
 * </pre>
 *
 * Protobuf type {@code pb4server.TransportResAskReq}
 */
public  final class TransportResAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.TransportResAskReq)
    TransportResAskReqOrBuilder {
  // Use TransportResAskReq.newBuilder() to construct.
  private TransportResAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TransportResAskReq() {
    targetPlayerId_ = 0L;
    res_ = "";
    effectMap_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private TransportResAskReq(
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

            targetPlayerId_ = input.readInt64();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            res_ = s;
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              effectMap_ = new java.util.ArrayList<pb4server.EffectVo>();
              mutable_bitField0_ |= 0x00000004;
            }
            effectMap_.add(
                input.readMessage(pb4server.EffectVo.parser(), extensionRegistry));
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
        effectMap_ = java.util.Collections.unmodifiableList(effectMap_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalWkt.internal_static_pb4server_TransportResAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_TransportResAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.TransportResAskReq.class, pb4server.TransportResAskReq.Builder.class);
  }

  private int bitField0_;
  public static final int TARGETPLAYERID_FIELD_NUMBER = 1;
  private long targetPlayerId_;
  /**
   * <code>int64 targetPlayerId = 1;</code>
   */
  public long getTargetPlayerId() {
    return targetPlayerId_;
  }

  public static final int RES_FIELD_NUMBER = 2;
  private volatile java.lang.Object res_;
  /**
   * <code>string res = 2;</code>
   */
  public java.lang.String getRes() {
    java.lang.Object ref = res_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      res_ = s;
      return s;
    }
  }
  /**
   * <code>string res = 2;</code>
   */
  public com.google.protobuf.ByteString
      getResBytes() {
    java.lang.Object ref = res_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      res_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EFFECTMAP_FIELD_NUMBER = 3;
  private java.util.List<pb4server.EffectVo> effectMap_;
  /**
   * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
   */
  public java.util.List<pb4server.EffectVo> getEffectMapList() {
    return effectMap_;
  }
  /**
   * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
   */
  public java.util.List<? extends pb4server.EffectVoOrBuilder> 
      getEffectMapOrBuilderList() {
    return effectMap_;
  }
  /**
   * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
   */
  public int getEffectMapCount() {
    return effectMap_.size();
  }
  /**
   * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
   */
  public pb4server.EffectVo getEffectMap(int index) {
    return effectMap_.get(index);
  }
  /**
   * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
   */
  public pb4server.EffectVoOrBuilder getEffectMapOrBuilder(
      int index) {
    return effectMap_.get(index);
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
    if (targetPlayerId_ != 0L) {
      output.writeInt64(1, targetPlayerId_);
    }
    if (!getResBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, res_);
    }
    for (int i = 0; i < effectMap_.size(); i++) {
      output.writeMessage(3, effectMap_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (targetPlayerId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, targetPlayerId_);
    }
    if (!getResBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, res_);
    }
    for (int i = 0; i < effectMap_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, effectMap_.get(i));
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
    if (!(obj instanceof pb4server.TransportResAskReq)) {
      return super.equals(obj);
    }
    pb4server.TransportResAskReq other = (pb4server.TransportResAskReq) obj;

    boolean result = true;
    result = result && (getTargetPlayerId()
        == other.getTargetPlayerId());
    result = result && getRes()
        .equals(other.getRes());
    result = result && getEffectMapList()
        .equals(other.getEffectMapList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TARGETPLAYERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTargetPlayerId());
    hash = (37 * hash) + RES_FIELD_NUMBER;
    hash = (53 * hash) + getRes().hashCode();
    if (getEffectMapCount() > 0) {
      hash = (37 * hash) + EFFECTMAP_FIELD_NUMBER;
      hash = (53 * hash) + getEffectMapList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.TransportResAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.TransportResAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.TransportResAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.TransportResAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.TransportResAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.TransportResAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.TransportResAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.TransportResAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.TransportResAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.TransportResAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.TransportResAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.TransportResAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.TransportResAskReq prototype) {
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
   *运输资源
   * </pre>
   *
   * Protobuf type {@code pb4server.TransportResAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.TransportResAskReq)
      pb4server.TransportResAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_TransportResAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_TransportResAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.TransportResAskReq.class, pb4server.TransportResAskReq.Builder.class);
    }

    // Construct using pb4server.TransportResAskReq.newBuilder()
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
        getEffectMapFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      targetPlayerId_ = 0L;

      res_ = "";

      if (effectMapBuilder_ == null) {
        effectMap_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        effectMapBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_TransportResAskReq_descriptor;
    }

    public pb4server.TransportResAskReq getDefaultInstanceForType() {
      return pb4server.TransportResAskReq.getDefaultInstance();
    }

    public pb4server.TransportResAskReq build() {
      pb4server.TransportResAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.TransportResAskReq buildPartial() {
      pb4server.TransportResAskReq result = new pb4server.TransportResAskReq(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.targetPlayerId_ = targetPlayerId_;
      result.res_ = res_;
      if (effectMapBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          effectMap_ = java.util.Collections.unmodifiableList(effectMap_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.effectMap_ = effectMap_;
      } else {
        result.effectMap_ = effectMapBuilder_.build();
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
      if (other instanceof pb4server.TransportResAskReq) {
        return mergeFrom((pb4server.TransportResAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.TransportResAskReq other) {
      if (other == pb4server.TransportResAskReq.getDefaultInstance()) return this;
      if (other.getTargetPlayerId() != 0L) {
        setTargetPlayerId(other.getTargetPlayerId());
      }
      if (!other.getRes().isEmpty()) {
        res_ = other.res_;
        onChanged();
      }
      if (effectMapBuilder_ == null) {
        if (!other.effectMap_.isEmpty()) {
          if (effectMap_.isEmpty()) {
            effectMap_ = other.effectMap_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureEffectMapIsMutable();
            effectMap_.addAll(other.effectMap_);
          }
          onChanged();
        }
      } else {
        if (!other.effectMap_.isEmpty()) {
          if (effectMapBuilder_.isEmpty()) {
            effectMapBuilder_.dispose();
            effectMapBuilder_ = null;
            effectMap_ = other.effectMap_;
            bitField0_ = (bitField0_ & ~0x00000004);
            effectMapBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getEffectMapFieldBuilder() : null;
          } else {
            effectMapBuilder_.addAllMessages(other.effectMap_);
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
      pb4server.TransportResAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.TransportResAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long targetPlayerId_ ;
    /**
     * <code>int64 targetPlayerId = 1;</code>
     */
    public long getTargetPlayerId() {
      return targetPlayerId_;
    }
    /**
     * <code>int64 targetPlayerId = 1;</code>
     */
    public Builder setTargetPlayerId(long value) {
      
      targetPlayerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 targetPlayerId = 1;</code>
     */
    public Builder clearTargetPlayerId() {
      
      targetPlayerId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object res_ = "";
    /**
     * <code>string res = 2;</code>
     */
    public java.lang.String getRes() {
      java.lang.Object ref = res_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        res_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string res = 2;</code>
     */
    public com.google.protobuf.ByteString
        getResBytes() {
      java.lang.Object ref = res_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        res_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string res = 2;</code>
     */
    public Builder setRes(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      res_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string res = 2;</code>
     */
    public Builder clearRes() {
      
      res_ = getDefaultInstance().getRes();
      onChanged();
      return this;
    }
    /**
     * <code>string res = 2;</code>
     */
    public Builder setResBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      res_ = value;
      onChanged();
      return this;
    }

    private java.util.List<pb4server.EffectVo> effectMap_ =
      java.util.Collections.emptyList();
    private void ensureEffectMapIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        effectMap_ = new java.util.ArrayList<pb4server.EffectVo>(effectMap_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.EffectVo, pb4server.EffectVo.Builder, pb4server.EffectVoOrBuilder> effectMapBuilder_;

    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public java.util.List<pb4server.EffectVo> getEffectMapList() {
      if (effectMapBuilder_ == null) {
        return java.util.Collections.unmodifiableList(effectMap_);
      } else {
        return effectMapBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public int getEffectMapCount() {
      if (effectMapBuilder_ == null) {
        return effectMap_.size();
      } else {
        return effectMapBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public pb4server.EffectVo getEffectMap(int index) {
      if (effectMapBuilder_ == null) {
        return effectMap_.get(index);
      } else {
        return effectMapBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public Builder setEffectMap(
        int index, pb4server.EffectVo value) {
      if (effectMapBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEffectMapIsMutable();
        effectMap_.set(index, value);
        onChanged();
      } else {
        effectMapBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public Builder setEffectMap(
        int index, pb4server.EffectVo.Builder builderForValue) {
      if (effectMapBuilder_ == null) {
        ensureEffectMapIsMutable();
        effectMap_.set(index, builderForValue.build());
        onChanged();
      } else {
        effectMapBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public Builder addEffectMap(pb4server.EffectVo value) {
      if (effectMapBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEffectMapIsMutable();
        effectMap_.add(value);
        onChanged();
      } else {
        effectMapBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public Builder addEffectMap(
        int index, pb4server.EffectVo value) {
      if (effectMapBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEffectMapIsMutable();
        effectMap_.add(index, value);
        onChanged();
      } else {
        effectMapBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public Builder addEffectMap(
        pb4server.EffectVo.Builder builderForValue) {
      if (effectMapBuilder_ == null) {
        ensureEffectMapIsMutable();
        effectMap_.add(builderForValue.build());
        onChanged();
      } else {
        effectMapBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public Builder addEffectMap(
        int index, pb4server.EffectVo.Builder builderForValue) {
      if (effectMapBuilder_ == null) {
        ensureEffectMapIsMutable();
        effectMap_.add(index, builderForValue.build());
        onChanged();
      } else {
        effectMapBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public Builder addAllEffectMap(
        java.lang.Iterable<? extends pb4server.EffectVo> values) {
      if (effectMapBuilder_ == null) {
        ensureEffectMapIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, effectMap_);
        onChanged();
      } else {
        effectMapBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public Builder clearEffectMap() {
      if (effectMapBuilder_ == null) {
        effectMap_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        effectMapBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public Builder removeEffectMap(int index) {
      if (effectMapBuilder_ == null) {
        ensureEffectMapIsMutable();
        effectMap_.remove(index);
        onChanged();
      } else {
        effectMapBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public pb4server.EffectVo.Builder getEffectMapBuilder(
        int index) {
      return getEffectMapFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public pb4server.EffectVoOrBuilder getEffectMapOrBuilder(
        int index) {
      if (effectMapBuilder_ == null) {
        return effectMap_.get(index);  } else {
        return effectMapBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public java.util.List<? extends pb4server.EffectVoOrBuilder> 
         getEffectMapOrBuilderList() {
      if (effectMapBuilder_ != null) {
        return effectMapBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(effectMap_);
      }
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public pb4server.EffectVo.Builder addEffectMapBuilder() {
      return getEffectMapFieldBuilder().addBuilder(
          pb4server.EffectVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public pb4server.EffectVo.Builder addEffectMapBuilder(
        int index) {
      return getEffectMapFieldBuilder().addBuilder(
          index, pb4server.EffectVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.EffectVo effectMap = 3;</code>
     */
    public java.util.List<pb4server.EffectVo.Builder> 
         getEffectMapBuilderList() {
      return getEffectMapFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.EffectVo, pb4server.EffectVo.Builder, pb4server.EffectVoOrBuilder> 
        getEffectMapFieldBuilder() {
      if (effectMapBuilder_ == null) {
        effectMapBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.EffectVo, pb4server.EffectVo.Builder, pb4server.EffectVoOrBuilder>(
                effectMap_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        effectMap_ = null;
      }
      return effectMapBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.TransportResAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.TransportResAskReq)
  private static final pb4server.TransportResAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.TransportResAskReq();
  }

  public static pb4server.TransportResAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TransportResAskReq>
      PARSER = new com.google.protobuf.AbstractParser<TransportResAskReq>() {
    public TransportResAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TransportResAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TransportResAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TransportResAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.TransportResAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

