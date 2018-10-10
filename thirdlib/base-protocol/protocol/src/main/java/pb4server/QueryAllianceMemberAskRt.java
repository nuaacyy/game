// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.QueryAllianceMemberAskRt}
 */
public  final class QueryAllianceMemberAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.QueryAllianceMemberAskRt)
    QueryAllianceMemberAskRtOrBuilder {
  // Use QueryAllianceMemberAskRt.newBuilder() to construct.
  private QueryAllianceMemberAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryAllianceMemberAskRt() {
    rt_ = 0;
    onlineQty_ = 0;
    queryAllianceMemberVos_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private QueryAllianceMemberAskRt(
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
          case 16: {

            onlineQty_ = input.readInt32();
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              queryAllianceMemberVos_ = new java.util.ArrayList<pb4server.QueryAllianceMemberVo>();
              mutable_bitField0_ |= 0x00000004;
            }
            queryAllianceMemberVos_.add(
                input.readMessage(pb4server.QueryAllianceMemberVo.parser(), extensionRegistry));
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
        queryAllianceMemberVos_ = java.util.Collections.unmodifiableList(queryAllianceMemberVos_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceMemberAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceMemberAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.QueryAllianceMemberAskRt.class, pb4server.QueryAllianceMemberAskRt.Builder.class);
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

  public static final int ONLINEQTY_FIELD_NUMBER = 2;
  private int onlineQty_;
  /**
   * <pre>
   *在线玩家数
   * </pre>
   *
   * <code>int32 onlineQty = 2;</code>
   */
  public int getOnlineQty() {
    return onlineQty_;
  }

  public static final int QUERYALLIANCEMEMBERVOS_FIELD_NUMBER = 3;
  private java.util.List<pb4server.QueryAllianceMemberVo> queryAllianceMemberVos_;
  /**
   * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
   */
  public java.util.List<pb4server.QueryAllianceMemberVo> getQueryAllianceMemberVosList() {
    return queryAllianceMemberVos_;
  }
  /**
   * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
   */
  public java.util.List<? extends pb4server.QueryAllianceMemberVoOrBuilder> 
      getQueryAllianceMemberVosOrBuilderList() {
    return queryAllianceMemberVos_;
  }
  /**
   * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
   */
  public int getQueryAllianceMemberVosCount() {
    return queryAllianceMemberVos_.size();
  }
  /**
   * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
   */
  public pb4server.QueryAllianceMemberVo getQueryAllianceMemberVos(int index) {
    return queryAllianceMemberVos_.get(index);
  }
  /**
   * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
   */
  public pb4server.QueryAllianceMemberVoOrBuilder getQueryAllianceMemberVosOrBuilder(
      int index) {
    return queryAllianceMemberVos_.get(index);
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
    if (onlineQty_ != 0) {
      output.writeInt32(2, onlineQty_);
    }
    for (int i = 0; i < queryAllianceMemberVos_.size(); i++) {
      output.writeMessage(3, queryAllianceMemberVos_.get(i));
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
    if (onlineQty_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, onlineQty_);
    }
    for (int i = 0; i < queryAllianceMemberVos_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, queryAllianceMemberVos_.get(i));
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
    if (!(obj instanceof pb4server.QueryAllianceMemberAskRt)) {
      return super.equals(obj);
    }
    pb4server.QueryAllianceMemberAskRt other = (pb4server.QueryAllianceMemberAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && (getOnlineQty()
        == other.getOnlineQty());
    result = result && getQueryAllianceMemberVosList()
        .equals(other.getQueryAllianceMemberVosList());
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
    hash = (37 * hash) + ONLINEQTY_FIELD_NUMBER;
    hash = (53 * hash) + getOnlineQty();
    if (getQueryAllianceMemberVosCount() > 0) {
      hash = (37 * hash) + QUERYALLIANCEMEMBERVOS_FIELD_NUMBER;
      hash = (53 * hash) + getQueryAllianceMemberVosList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.QueryAllianceMemberAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryAllianceMemberAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryAllianceMemberAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryAllianceMemberAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryAllianceMemberAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryAllianceMemberAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryAllianceMemberAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryAllianceMemberAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryAllianceMemberAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.QueryAllianceMemberAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryAllianceMemberAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryAllianceMemberAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.QueryAllianceMemberAskRt prototype) {
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
   * Protobuf type {@code pb4server.QueryAllianceMemberAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.QueryAllianceMemberAskRt)
      pb4server.QueryAllianceMemberAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceMemberAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceMemberAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.QueryAllianceMemberAskRt.class, pb4server.QueryAllianceMemberAskRt.Builder.class);
    }

    // Construct using pb4server.QueryAllianceMemberAskRt.newBuilder()
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
        getQueryAllianceMemberVosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;

      onlineQty_ = 0;

      if (queryAllianceMemberVosBuilder_ == null) {
        queryAllianceMemberVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        queryAllianceMemberVosBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceMemberAskRt_descriptor;
    }

    public pb4server.QueryAllianceMemberAskRt getDefaultInstanceForType() {
      return pb4server.QueryAllianceMemberAskRt.getDefaultInstance();
    }

    public pb4server.QueryAllianceMemberAskRt build() {
      pb4server.QueryAllianceMemberAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.QueryAllianceMemberAskRt buildPartial() {
      pb4server.QueryAllianceMemberAskRt result = new pb4server.QueryAllianceMemberAskRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.rt_ = rt_;
      result.onlineQty_ = onlineQty_;
      if (queryAllianceMemberVosBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          queryAllianceMemberVos_ = java.util.Collections.unmodifiableList(queryAllianceMemberVos_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.queryAllianceMemberVos_ = queryAllianceMemberVos_;
      } else {
        result.queryAllianceMemberVos_ = queryAllianceMemberVosBuilder_.build();
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
      if (other instanceof pb4server.QueryAllianceMemberAskRt) {
        return mergeFrom((pb4server.QueryAllianceMemberAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.QueryAllianceMemberAskRt other) {
      if (other == pb4server.QueryAllianceMemberAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (other.getOnlineQty() != 0) {
        setOnlineQty(other.getOnlineQty());
      }
      if (queryAllianceMemberVosBuilder_ == null) {
        if (!other.queryAllianceMemberVos_.isEmpty()) {
          if (queryAllianceMemberVos_.isEmpty()) {
            queryAllianceMemberVos_ = other.queryAllianceMemberVos_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureQueryAllianceMemberVosIsMutable();
            queryAllianceMemberVos_.addAll(other.queryAllianceMemberVos_);
          }
          onChanged();
        }
      } else {
        if (!other.queryAllianceMemberVos_.isEmpty()) {
          if (queryAllianceMemberVosBuilder_.isEmpty()) {
            queryAllianceMemberVosBuilder_.dispose();
            queryAllianceMemberVosBuilder_ = null;
            queryAllianceMemberVos_ = other.queryAllianceMemberVos_;
            bitField0_ = (bitField0_ & ~0x00000004);
            queryAllianceMemberVosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getQueryAllianceMemberVosFieldBuilder() : null;
          } else {
            queryAllianceMemberVosBuilder_.addAllMessages(other.queryAllianceMemberVos_);
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
      pb4server.QueryAllianceMemberAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.QueryAllianceMemberAskRt) e.getUnfinishedMessage();
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

    private int onlineQty_ ;
    /**
     * <pre>
     *在线玩家数
     * </pre>
     *
     * <code>int32 onlineQty = 2;</code>
     */
    public int getOnlineQty() {
      return onlineQty_;
    }
    /**
     * <pre>
     *在线玩家数
     * </pre>
     *
     * <code>int32 onlineQty = 2;</code>
     */
    public Builder setOnlineQty(int value) {
      
      onlineQty_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *在线玩家数
     * </pre>
     *
     * <code>int32 onlineQty = 2;</code>
     */
    public Builder clearOnlineQty() {
      
      onlineQty_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4server.QueryAllianceMemberVo> queryAllianceMemberVos_ =
      java.util.Collections.emptyList();
    private void ensureQueryAllianceMemberVosIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        queryAllianceMemberVos_ = new java.util.ArrayList<pb4server.QueryAllianceMemberVo>(queryAllianceMemberVos_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.QueryAllianceMemberVo, pb4server.QueryAllianceMemberVo.Builder, pb4server.QueryAllianceMemberVoOrBuilder> queryAllianceMemberVosBuilder_;

    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public java.util.List<pb4server.QueryAllianceMemberVo> getQueryAllianceMemberVosList() {
      if (queryAllianceMemberVosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(queryAllianceMemberVos_);
      } else {
        return queryAllianceMemberVosBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public int getQueryAllianceMemberVosCount() {
      if (queryAllianceMemberVosBuilder_ == null) {
        return queryAllianceMemberVos_.size();
      } else {
        return queryAllianceMemberVosBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public pb4server.QueryAllianceMemberVo getQueryAllianceMemberVos(int index) {
      if (queryAllianceMemberVosBuilder_ == null) {
        return queryAllianceMemberVos_.get(index);
      } else {
        return queryAllianceMemberVosBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public Builder setQueryAllianceMemberVos(
        int index, pb4server.QueryAllianceMemberVo value) {
      if (queryAllianceMemberVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryAllianceMemberVosIsMutable();
        queryAllianceMemberVos_.set(index, value);
        onChanged();
      } else {
        queryAllianceMemberVosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public Builder setQueryAllianceMemberVos(
        int index, pb4server.QueryAllianceMemberVo.Builder builderForValue) {
      if (queryAllianceMemberVosBuilder_ == null) {
        ensureQueryAllianceMemberVosIsMutable();
        queryAllianceMemberVos_.set(index, builderForValue.build());
        onChanged();
      } else {
        queryAllianceMemberVosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public Builder addQueryAllianceMemberVos(pb4server.QueryAllianceMemberVo value) {
      if (queryAllianceMemberVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryAllianceMemberVosIsMutable();
        queryAllianceMemberVos_.add(value);
        onChanged();
      } else {
        queryAllianceMemberVosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public Builder addQueryAllianceMemberVos(
        int index, pb4server.QueryAllianceMemberVo value) {
      if (queryAllianceMemberVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryAllianceMemberVosIsMutable();
        queryAllianceMemberVos_.add(index, value);
        onChanged();
      } else {
        queryAllianceMemberVosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public Builder addQueryAllianceMemberVos(
        pb4server.QueryAllianceMemberVo.Builder builderForValue) {
      if (queryAllianceMemberVosBuilder_ == null) {
        ensureQueryAllianceMemberVosIsMutable();
        queryAllianceMemberVos_.add(builderForValue.build());
        onChanged();
      } else {
        queryAllianceMemberVosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public Builder addQueryAllianceMemberVos(
        int index, pb4server.QueryAllianceMemberVo.Builder builderForValue) {
      if (queryAllianceMemberVosBuilder_ == null) {
        ensureQueryAllianceMemberVosIsMutable();
        queryAllianceMemberVos_.add(index, builderForValue.build());
        onChanged();
      } else {
        queryAllianceMemberVosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public Builder addAllQueryAllianceMemberVos(
        java.lang.Iterable<? extends pb4server.QueryAllianceMemberVo> values) {
      if (queryAllianceMemberVosBuilder_ == null) {
        ensureQueryAllianceMemberVosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, queryAllianceMemberVos_);
        onChanged();
      } else {
        queryAllianceMemberVosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public Builder clearQueryAllianceMemberVos() {
      if (queryAllianceMemberVosBuilder_ == null) {
        queryAllianceMemberVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        queryAllianceMemberVosBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public Builder removeQueryAllianceMemberVos(int index) {
      if (queryAllianceMemberVosBuilder_ == null) {
        ensureQueryAllianceMemberVosIsMutable();
        queryAllianceMemberVos_.remove(index);
        onChanged();
      } else {
        queryAllianceMemberVosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public pb4server.QueryAllianceMemberVo.Builder getQueryAllianceMemberVosBuilder(
        int index) {
      return getQueryAllianceMemberVosFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public pb4server.QueryAllianceMemberVoOrBuilder getQueryAllianceMemberVosOrBuilder(
        int index) {
      if (queryAllianceMemberVosBuilder_ == null) {
        return queryAllianceMemberVos_.get(index);  } else {
        return queryAllianceMemberVosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public java.util.List<? extends pb4server.QueryAllianceMemberVoOrBuilder> 
         getQueryAllianceMemberVosOrBuilderList() {
      if (queryAllianceMemberVosBuilder_ != null) {
        return queryAllianceMemberVosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(queryAllianceMemberVos_);
      }
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public pb4server.QueryAllianceMemberVo.Builder addQueryAllianceMemberVosBuilder() {
      return getQueryAllianceMemberVosFieldBuilder().addBuilder(
          pb4server.QueryAllianceMemberVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public pb4server.QueryAllianceMemberVo.Builder addQueryAllianceMemberVosBuilder(
        int index) {
      return getQueryAllianceMemberVosFieldBuilder().addBuilder(
          index, pb4server.QueryAllianceMemberVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.QueryAllianceMemberVo queryAllianceMemberVos = 3;</code>
     */
    public java.util.List<pb4server.QueryAllianceMemberVo.Builder> 
         getQueryAllianceMemberVosBuilderList() {
      return getQueryAllianceMemberVosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.QueryAllianceMemberVo, pb4server.QueryAllianceMemberVo.Builder, pb4server.QueryAllianceMemberVoOrBuilder> 
        getQueryAllianceMemberVosFieldBuilder() {
      if (queryAllianceMemberVosBuilder_ == null) {
        queryAllianceMemberVosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.QueryAllianceMemberVo, pb4server.QueryAllianceMemberVo.Builder, pb4server.QueryAllianceMemberVoOrBuilder>(
                queryAllianceMemberVos_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        queryAllianceMemberVos_ = null;
      }
      return queryAllianceMemberVosBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.QueryAllianceMemberAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.QueryAllianceMemberAskRt)
  private static final pb4server.QueryAllianceMemberAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.QueryAllianceMemberAskRt();
  }

  public static pb4server.QueryAllianceMemberAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueryAllianceMemberAskRt>
      PARSER = new com.google.protobuf.AbstractParser<QueryAllianceMemberAskRt>() {
    public QueryAllianceMemberAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryAllianceMemberAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryAllianceMemberAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryAllianceMemberAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.QueryAllianceMemberAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
