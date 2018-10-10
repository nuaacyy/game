// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.QueryPlayerByNameRt}
 */
public  final class QueryPlayerByNameRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.QueryPlayerByNameRt)
    QueryPlayerByNameRtOrBuilder {
  // Use QueryPlayerByNameRt.newBuilder() to construct.
  private QueryPlayerByNameRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryPlayerByNameRt() {
    rt_ = 0;
    queryPlayerByNameVos_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QueryPlayerByNameRt(
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
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              queryPlayerByNameVos_ = new java.util.ArrayList<pb4client.QueryPlayerByNameVo>();
              mutable_bitField0_ |= 0x00000002;
            }
            queryPlayerByNameVos_.add(
                input.readMessage(pb4client.QueryPlayerByNameVo.PARSER, extensionRegistry));
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
        queryPlayerByNameVos_ = java.util.Collections.unmodifiableList(queryPlayerByNameVos_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_QueryPlayerByNameRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_QueryPlayerByNameRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.QueryPlayerByNameRt.class, pb4client.QueryPlayerByNameRt.Builder.class);
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

  public static final int QUERYPLAYERBYNAMEVOS_FIELD_NUMBER = 2;
  private java.util.List<pb4client.QueryPlayerByNameVo> queryPlayerByNameVos_;
  /**
   * <pre>
   * 查询的个人信息
   * </pre>
   *
   * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
   */
  public java.util.List<pb4client.QueryPlayerByNameVo> getQueryPlayerByNameVosList() {
    return queryPlayerByNameVos_;
  }
  /**
   * <pre>
   * 查询的个人信息
   * </pre>
   *
   * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
   */
  public java.util.List<? extends pb4client.QueryPlayerByNameVoOrBuilder> 
      getQueryPlayerByNameVosOrBuilderList() {
    return queryPlayerByNameVos_;
  }
  /**
   * <pre>
   * 查询的个人信息
   * </pre>
   *
   * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
   */
  public int getQueryPlayerByNameVosCount() {
    return queryPlayerByNameVos_.size();
  }
  /**
   * <pre>
   * 查询的个人信息
   * </pre>
   *
   * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
   */
  public pb4client.QueryPlayerByNameVo getQueryPlayerByNameVos(int index) {
    return queryPlayerByNameVos_.get(index);
  }
  /**
   * <pre>
   * 查询的个人信息
   * </pre>
   *
   * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
   */
  public pb4client.QueryPlayerByNameVoOrBuilder getQueryPlayerByNameVosOrBuilder(
      int index) {
    return queryPlayerByNameVos_.get(index);
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
    for (int i = 0; i < getQueryPlayerByNameVosCount(); i++) {
      if (!getQueryPlayerByNameVos(i).isInitialized()) {
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
    for (int i = 0; i < queryPlayerByNameVos_.size(); i++) {
      output.writeMessage(2, queryPlayerByNameVos_.get(i));
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
    for (int i = 0; i < queryPlayerByNameVos_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, queryPlayerByNameVos_.get(i));
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
    if (!(obj instanceof pb4client.QueryPlayerByNameRt)) {
      return super.equals(obj);
    }
    pb4client.QueryPlayerByNameRt other = (pb4client.QueryPlayerByNameRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getQueryPlayerByNameVosList()
        .equals(other.getQueryPlayerByNameVosList());
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
    if (getQueryPlayerByNameVosCount() > 0) {
      hash = (37 * hash) + QUERYPLAYERBYNAMEVOS_FIELD_NUMBER;
      hash = (53 * hash) + getQueryPlayerByNameVosList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.QueryPlayerByNameRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryPlayerByNameRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryPlayerByNameRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryPlayerByNameRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryPlayerByNameRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryPlayerByNameRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryPlayerByNameRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryPlayerByNameRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryPlayerByNameRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.QueryPlayerByNameRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryPlayerByNameRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryPlayerByNameRt parseFrom(
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
  public static Builder newBuilder(pb4client.QueryPlayerByNameRt prototype) {
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
   * Protobuf type {@code client2server.QueryPlayerByNameRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.QueryPlayerByNameRt)
      pb4client.QueryPlayerByNameRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryPlayerByNameRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryPlayerByNameRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.QueryPlayerByNameRt.class, pb4client.QueryPlayerByNameRt.Builder.class);
    }

    // Construct using pb4client.QueryPlayerByNameRt.newBuilder()
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
        getQueryPlayerByNameVosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (queryPlayerByNameVosBuilder_ == null) {
        queryPlayerByNameVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        queryPlayerByNameVosBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryPlayerByNameRt_descriptor;
    }

    public pb4client.QueryPlayerByNameRt getDefaultInstanceForType() {
      return pb4client.QueryPlayerByNameRt.getDefaultInstance();
    }

    public pb4client.QueryPlayerByNameRt build() {
      pb4client.QueryPlayerByNameRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.QueryPlayerByNameRt buildPartial() {
      pb4client.QueryPlayerByNameRt result = new pb4client.QueryPlayerByNameRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (queryPlayerByNameVosBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          queryPlayerByNameVos_ = java.util.Collections.unmodifiableList(queryPlayerByNameVos_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.queryPlayerByNameVos_ = queryPlayerByNameVos_;
      } else {
        result.queryPlayerByNameVos_ = queryPlayerByNameVosBuilder_.build();
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
      if (other instanceof pb4client.QueryPlayerByNameRt) {
        return mergeFrom((pb4client.QueryPlayerByNameRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.QueryPlayerByNameRt other) {
      if (other == pb4client.QueryPlayerByNameRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (queryPlayerByNameVosBuilder_ == null) {
        if (!other.queryPlayerByNameVos_.isEmpty()) {
          if (queryPlayerByNameVos_.isEmpty()) {
            queryPlayerByNameVos_ = other.queryPlayerByNameVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureQueryPlayerByNameVosIsMutable();
            queryPlayerByNameVos_.addAll(other.queryPlayerByNameVos_);
          }
          onChanged();
        }
      } else {
        if (!other.queryPlayerByNameVos_.isEmpty()) {
          if (queryPlayerByNameVosBuilder_.isEmpty()) {
            queryPlayerByNameVosBuilder_.dispose();
            queryPlayerByNameVosBuilder_ = null;
            queryPlayerByNameVos_ = other.queryPlayerByNameVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
            queryPlayerByNameVosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getQueryPlayerByNameVosFieldBuilder() : null;
          } else {
            queryPlayerByNameVosBuilder_.addAllMessages(other.queryPlayerByNameVos_);
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
      for (int i = 0; i < getQueryPlayerByNameVosCount(); i++) {
        if (!getQueryPlayerByNameVos(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.QueryPlayerByNameRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.QueryPlayerByNameRt) e.getUnfinishedMessage();
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

    private java.util.List<pb4client.QueryPlayerByNameVo> queryPlayerByNameVos_ =
      java.util.Collections.emptyList();
    private void ensureQueryPlayerByNameVosIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        queryPlayerByNameVos_ = new java.util.ArrayList<pb4client.QueryPlayerByNameVo>(queryPlayerByNameVos_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.QueryPlayerByNameVo, pb4client.QueryPlayerByNameVo.Builder, pb4client.QueryPlayerByNameVoOrBuilder> queryPlayerByNameVosBuilder_;

    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public java.util.List<pb4client.QueryPlayerByNameVo> getQueryPlayerByNameVosList() {
      if (queryPlayerByNameVosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(queryPlayerByNameVos_);
      } else {
        return queryPlayerByNameVosBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public int getQueryPlayerByNameVosCount() {
      if (queryPlayerByNameVosBuilder_ == null) {
        return queryPlayerByNameVos_.size();
      } else {
        return queryPlayerByNameVosBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public pb4client.QueryPlayerByNameVo getQueryPlayerByNameVos(int index) {
      if (queryPlayerByNameVosBuilder_ == null) {
        return queryPlayerByNameVos_.get(index);
      } else {
        return queryPlayerByNameVosBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public Builder setQueryPlayerByNameVos(
        int index, pb4client.QueryPlayerByNameVo value) {
      if (queryPlayerByNameVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryPlayerByNameVosIsMutable();
        queryPlayerByNameVos_.set(index, value);
        onChanged();
      } else {
        queryPlayerByNameVosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public Builder setQueryPlayerByNameVos(
        int index, pb4client.QueryPlayerByNameVo.Builder builderForValue) {
      if (queryPlayerByNameVosBuilder_ == null) {
        ensureQueryPlayerByNameVosIsMutable();
        queryPlayerByNameVos_.set(index, builderForValue.build());
        onChanged();
      } else {
        queryPlayerByNameVosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public Builder addQueryPlayerByNameVos(pb4client.QueryPlayerByNameVo value) {
      if (queryPlayerByNameVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryPlayerByNameVosIsMutable();
        queryPlayerByNameVos_.add(value);
        onChanged();
      } else {
        queryPlayerByNameVosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public Builder addQueryPlayerByNameVos(
        int index, pb4client.QueryPlayerByNameVo value) {
      if (queryPlayerByNameVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryPlayerByNameVosIsMutable();
        queryPlayerByNameVos_.add(index, value);
        onChanged();
      } else {
        queryPlayerByNameVosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public Builder addQueryPlayerByNameVos(
        pb4client.QueryPlayerByNameVo.Builder builderForValue) {
      if (queryPlayerByNameVosBuilder_ == null) {
        ensureQueryPlayerByNameVosIsMutable();
        queryPlayerByNameVos_.add(builderForValue.build());
        onChanged();
      } else {
        queryPlayerByNameVosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public Builder addQueryPlayerByNameVos(
        int index, pb4client.QueryPlayerByNameVo.Builder builderForValue) {
      if (queryPlayerByNameVosBuilder_ == null) {
        ensureQueryPlayerByNameVosIsMutable();
        queryPlayerByNameVos_.add(index, builderForValue.build());
        onChanged();
      } else {
        queryPlayerByNameVosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public Builder addAllQueryPlayerByNameVos(
        java.lang.Iterable<? extends pb4client.QueryPlayerByNameVo> values) {
      if (queryPlayerByNameVosBuilder_ == null) {
        ensureQueryPlayerByNameVosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, queryPlayerByNameVos_);
        onChanged();
      } else {
        queryPlayerByNameVosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public Builder clearQueryPlayerByNameVos() {
      if (queryPlayerByNameVosBuilder_ == null) {
        queryPlayerByNameVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        queryPlayerByNameVosBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public Builder removeQueryPlayerByNameVos(int index) {
      if (queryPlayerByNameVosBuilder_ == null) {
        ensureQueryPlayerByNameVosIsMutable();
        queryPlayerByNameVos_.remove(index);
        onChanged();
      } else {
        queryPlayerByNameVosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public pb4client.QueryPlayerByNameVo.Builder getQueryPlayerByNameVosBuilder(
        int index) {
      return getQueryPlayerByNameVosFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public pb4client.QueryPlayerByNameVoOrBuilder getQueryPlayerByNameVosOrBuilder(
        int index) {
      if (queryPlayerByNameVosBuilder_ == null) {
        return queryPlayerByNameVos_.get(index);  } else {
        return queryPlayerByNameVosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public java.util.List<? extends pb4client.QueryPlayerByNameVoOrBuilder> 
         getQueryPlayerByNameVosOrBuilderList() {
      if (queryPlayerByNameVosBuilder_ != null) {
        return queryPlayerByNameVosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(queryPlayerByNameVos_);
      }
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public pb4client.QueryPlayerByNameVo.Builder addQueryPlayerByNameVosBuilder() {
      return getQueryPlayerByNameVosFieldBuilder().addBuilder(
          pb4client.QueryPlayerByNameVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public pb4client.QueryPlayerByNameVo.Builder addQueryPlayerByNameVosBuilder(
        int index) {
      return getQueryPlayerByNameVosFieldBuilder().addBuilder(
          index, pb4client.QueryPlayerByNameVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 查询的个人信息
     * </pre>
     *
     * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
     */
    public java.util.List<pb4client.QueryPlayerByNameVo.Builder> 
         getQueryPlayerByNameVosBuilderList() {
      return getQueryPlayerByNameVosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.QueryPlayerByNameVo, pb4client.QueryPlayerByNameVo.Builder, pb4client.QueryPlayerByNameVoOrBuilder> 
        getQueryPlayerByNameVosFieldBuilder() {
      if (queryPlayerByNameVosBuilder_ == null) {
        queryPlayerByNameVosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.QueryPlayerByNameVo, pb4client.QueryPlayerByNameVo.Builder, pb4client.QueryPlayerByNameVoOrBuilder>(
                queryPlayerByNameVos_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        queryPlayerByNameVos_ = null;
      }
      return queryPlayerByNameVosBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.QueryPlayerByNameRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.QueryPlayerByNameRt)
  private static final pb4client.QueryPlayerByNameRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.QueryPlayerByNameRt();
  }

  public static pb4client.QueryPlayerByNameRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<QueryPlayerByNameRt>
      PARSER = new com.google.protobuf.AbstractParser<QueryPlayerByNameRt>() {
    public QueryPlayerByNameRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryPlayerByNameRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryPlayerByNameRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryPlayerByNameRt> getParserForType() {
    return PARSER;
  }

  public pb4client.QueryPlayerByNameRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

