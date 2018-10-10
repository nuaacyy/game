// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * <pre>
 * PUBLIC -&gt; GAME
 * 查询联盟内部数据排行榜返回
 * </pre>
 *
 * Protobuf type {@code pb4server.QueryInAllianceRankRt}
 */
public  final class QueryInAllianceRankRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.QueryInAllianceRankRt)
    QueryInAllianceRankRtOrBuilder {
  // Use QueryInAllianceRankRt.newBuilder() to construct.
  private QueryInAllianceRankRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryInAllianceRankRt() {
    rt_ = 0;
    queryInAllianceRankVos_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private QueryInAllianceRankRt(
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
              queryInAllianceRankVos_ = new java.util.ArrayList<pb4server.QueryInAllianceRankVo>();
              mutable_bitField0_ |= 0x00000002;
            }
            queryInAllianceRankVos_.add(
                input.readMessage(pb4server.QueryInAllianceRankVo.parser(), extensionRegistry));
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
        queryInAllianceRankVos_ = java.util.Collections.unmodifiableList(queryInAllianceRankVos_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_QueryInAllianceRankRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_QueryInAllianceRankRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.QueryInAllianceRankRt.class, pb4server.QueryInAllianceRankRt.Builder.class);
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

  public static final int QUERYINALLIANCERANKVOS_FIELD_NUMBER = 2;
  private java.util.List<pb4server.QueryInAllianceRankVo> queryInAllianceRankVos_;
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
   */
  public java.util.List<pb4server.QueryInAllianceRankVo> getQueryInAllianceRankVosList() {
    return queryInAllianceRankVos_;
  }
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
   */
  public java.util.List<? extends pb4server.QueryInAllianceRankVoOrBuilder> 
      getQueryInAllianceRankVosOrBuilderList() {
    return queryInAllianceRankVos_;
  }
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
   */
  public int getQueryInAllianceRankVosCount() {
    return queryInAllianceRankVos_.size();
  }
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
   */
  public pb4server.QueryInAllianceRankVo getQueryInAllianceRankVos(int index) {
    return queryInAllianceRankVos_.get(index);
  }
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
   */
  public pb4server.QueryInAllianceRankVoOrBuilder getQueryInAllianceRankVosOrBuilder(
      int index) {
    return queryInAllianceRankVos_.get(index);
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
    for (int i = 0; i < queryInAllianceRankVos_.size(); i++) {
      output.writeMessage(2, queryInAllianceRankVos_.get(i));
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
    for (int i = 0; i < queryInAllianceRankVos_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, queryInAllianceRankVos_.get(i));
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
    if (!(obj instanceof pb4server.QueryInAllianceRankRt)) {
      return super.equals(obj);
    }
    pb4server.QueryInAllianceRankRt other = (pb4server.QueryInAllianceRankRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && getQueryInAllianceRankVosList()
        .equals(other.getQueryInAllianceRankVosList());
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
    if (getQueryInAllianceRankVosCount() > 0) {
      hash = (37 * hash) + QUERYINALLIANCERANKVOS_FIELD_NUMBER;
      hash = (53 * hash) + getQueryInAllianceRankVosList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.QueryInAllianceRankRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryInAllianceRankRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryInAllianceRankRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryInAllianceRankRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryInAllianceRankRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryInAllianceRankRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryInAllianceRankRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryInAllianceRankRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryInAllianceRankRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.QueryInAllianceRankRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryInAllianceRankRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryInAllianceRankRt parseFrom(
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
  public static Builder newBuilder(pb4server.QueryInAllianceRankRt prototype) {
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
   * PUBLIC -&gt; GAME
   * 查询联盟内部数据排行榜返回
   * </pre>
   *
   * Protobuf type {@code pb4server.QueryInAllianceRankRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.QueryInAllianceRankRt)
      pb4server.QueryInAllianceRankRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryInAllianceRankRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryInAllianceRankRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.QueryInAllianceRankRt.class, pb4server.QueryInAllianceRankRt.Builder.class);
    }

    // Construct using pb4server.QueryInAllianceRankRt.newBuilder()
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
        getQueryInAllianceRankVosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;

      if (queryInAllianceRankVosBuilder_ == null) {
        queryInAllianceRankVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        queryInAllianceRankVosBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryInAllianceRankRt_descriptor;
    }

    public pb4server.QueryInAllianceRankRt getDefaultInstanceForType() {
      return pb4server.QueryInAllianceRankRt.getDefaultInstance();
    }

    public pb4server.QueryInAllianceRankRt build() {
      pb4server.QueryInAllianceRankRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.QueryInAllianceRankRt buildPartial() {
      pb4server.QueryInAllianceRankRt result = new pb4server.QueryInAllianceRankRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.rt_ = rt_;
      if (queryInAllianceRankVosBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          queryInAllianceRankVos_ = java.util.Collections.unmodifiableList(queryInAllianceRankVos_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.queryInAllianceRankVos_ = queryInAllianceRankVos_;
      } else {
        result.queryInAllianceRankVos_ = queryInAllianceRankVosBuilder_.build();
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
      if (other instanceof pb4server.QueryInAllianceRankRt) {
        return mergeFrom((pb4server.QueryInAllianceRankRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.QueryInAllianceRankRt other) {
      if (other == pb4server.QueryInAllianceRankRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (queryInAllianceRankVosBuilder_ == null) {
        if (!other.queryInAllianceRankVos_.isEmpty()) {
          if (queryInAllianceRankVos_.isEmpty()) {
            queryInAllianceRankVos_ = other.queryInAllianceRankVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureQueryInAllianceRankVosIsMutable();
            queryInAllianceRankVos_.addAll(other.queryInAllianceRankVos_);
          }
          onChanged();
        }
      } else {
        if (!other.queryInAllianceRankVos_.isEmpty()) {
          if (queryInAllianceRankVosBuilder_.isEmpty()) {
            queryInAllianceRankVosBuilder_.dispose();
            queryInAllianceRankVosBuilder_ = null;
            queryInAllianceRankVos_ = other.queryInAllianceRankVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
            queryInAllianceRankVosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getQueryInAllianceRankVosFieldBuilder() : null;
          } else {
            queryInAllianceRankVosBuilder_.addAllMessages(other.queryInAllianceRankVos_);
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
      pb4server.QueryInAllianceRankRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.QueryInAllianceRankRt) e.getUnfinishedMessage();
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

    private java.util.List<pb4server.QueryInAllianceRankVo> queryInAllianceRankVos_ =
      java.util.Collections.emptyList();
    private void ensureQueryInAllianceRankVosIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        queryInAllianceRankVos_ = new java.util.ArrayList<pb4server.QueryInAllianceRankVo>(queryInAllianceRankVos_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.QueryInAllianceRankVo, pb4server.QueryInAllianceRankVo.Builder, pb4server.QueryInAllianceRankVoOrBuilder> queryInAllianceRankVosBuilder_;

    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public java.util.List<pb4server.QueryInAllianceRankVo> getQueryInAllianceRankVosList() {
      if (queryInAllianceRankVosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(queryInAllianceRankVos_);
      } else {
        return queryInAllianceRankVosBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public int getQueryInAllianceRankVosCount() {
      if (queryInAllianceRankVosBuilder_ == null) {
        return queryInAllianceRankVos_.size();
      } else {
        return queryInAllianceRankVosBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public pb4server.QueryInAllianceRankVo getQueryInAllianceRankVos(int index) {
      if (queryInAllianceRankVosBuilder_ == null) {
        return queryInAllianceRankVos_.get(index);
      } else {
        return queryInAllianceRankVosBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public Builder setQueryInAllianceRankVos(
        int index, pb4server.QueryInAllianceRankVo value) {
      if (queryInAllianceRankVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryInAllianceRankVosIsMutable();
        queryInAllianceRankVos_.set(index, value);
        onChanged();
      } else {
        queryInAllianceRankVosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public Builder setQueryInAllianceRankVos(
        int index, pb4server.QueryInAllianceRankVo.Builder builderForValue) {
      if (queryInAllianceRankVosBuilder_ == null) {
        ensureQueryInAllianceRankVosIsMutable();
        queryInAllianceRankVos_.set(index, builderForValue.build());
        onChanged();
      } else {
        queryInAllianceRankVosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public Builder addQueryInAllianceRankVos(pb4server.QueryInAllianceRankVo value) {
      if (queryInAllianceRankVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryInAllianceRankVosIsMutable();
        queryInAllianceRankVos_.add(value);
        onChanged();
      } else {
        queryInAllianceRankVosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public Builder addQueryInAllianceRankVos(
        int index, pb4server.QueryInAllianceRankVo value) {
      if (queryInAllianceRankVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryInAllianceRankVosIsMutable();
        queryInAllianceRankVos_.add(index, value);
        onChanged();
      } else {
        queryInAllianceRankVosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public Builder addQueryInAllianceRankVos(
        pb4server.QueryInAllianceRankVo.Builder builderForValue) {
      if (queryInAllianceRankVosBuilder_ == null) {
        ensureQueryInAllianceRankVosIsMutable();
        queryInAllianceRankVos_.add(builderForValue.build());
        onChanged();
      } else {
        queryInAllianceRankVosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public Builder addQueryInAllianceRankVos(
        int index, pb4server.QueryInAllianceRankVo.Builder builderForValue) {
      if (queryInAllianceRankVosBuilder_ == null) {
        ensureQueryInAllianceRankVosIsMutable();
        queryInAllianceRankVos_.add(index, builderForValue.build());
        onChanged();
      } else {
        queryInAllianceRankVosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public Builder addAllQueryInAllianceRankVos(
        java.lang.Iterable<? extends pb4server.QueryInAllianceRankVo> values) {
      if (queryInAllianceRankVosBuilder_ == null) {
        ensureQueryInAllianceRankVosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, queryInAllianceRankVos_);
        onChanged();
      } else {
        queryInAllianceRankVosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public Builder clearQueryInAllianceRankVos() {
      if (queryInAllianceRankVosBuilder_ == null) {
        queryInAllianceRankVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        queryInAllianceRankVosBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public Builder removeQueryInAllianceRankVos(int index) {
      if (queryInAllianceRankVosBuilder_ == null) {
        ensureQueryInAllianceRankVosIsMutable();
        queryInAllianceRankVos_.remove(index);
        onChanged();
      } else {
        queryInAllianceRankVosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public pb4server.QueryInAllianceRankVo.Builder getQueryInAllianceRankVosBuilder(
        int index) {
      return getQueryInAllianceRankVosFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public pb4server.QueryInAllianceRankVoOrBuilder getQueryInAllianceRankVosOrBuilder(
        int index) {
      if (queryInAllianceRankVosBuilder_ == null) {
        return queryInAllianceRankVos_.get(index);  } else {
        return queryInAllianceRankVosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public java.util.List<? extends pb4server.QueryInAllianceRankVoOrBuilder> 
         getQueryInAllianceRankVosOrBuilderList() {
      if (queryInAllianceRankVosBuilder_ != null) {
        return queryInAllianceRankVosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(queryInAllianceRankVos_);
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public pb4server.QueryInAllianceRankVo.Builder addQueryInAllianceRankVosBuilder() {
      return getQueryInAllianceRankVosFieldBuilder().addBuilder(
          pb4server.QueryInAllianceRankVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public pb4server.QueryInAllianceRankVo.Builder addQueryInAllianceRankVosBuilder(
        int index) {
      return getQueryInAllianceRankVosFieldBuilder().addBuilder(
          index, pb4server.QueryInAllianceRankVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
     */
    public java.util.List<pb4server.QueryInAllianceRankVo.Builder> 
         getQueryInAllianceRankVosBuilderList() {
      return getQueryInAllianceRankVosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.QueryInAllianceRankVo, pb4server.QueryInAllianceRankVo.Builder, pb4server.QueryInAllianceRankVoOrBuilder> 
        getQueryInAllianceRankVosFieldBuilder() {
      if (queryInAllianceRankVosBuilder_ == null) {
        queryInAllianceRankVosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.QueryInAllianceRankVo, pb4server.QueryInAllianceRankVo.Builder, pb4server.QueryInAllianceRankVoOrBuilder>(
                queryInAllianceRankVos_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        queryInAllianceRankVos_ = null;
      }
      return queryInAllianceRankVosBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.QueryInAllianceRankRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.QueryInAllianceRankRt)
  private static final pb4server.QueryInAllianceRankRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.QueryInAllianceRankRt();
  }

  public static pb4server.QueryInAllianceRankRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueryInAllianceRankRt>
      PARSER = new com.google.protobuf.AbstractParser<QueryInAllianceRankRt>() {
    public QueryInAllianceRankRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryInAllianceRankRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryInAllianceRankRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryInAllianceRankRt> getParserForType() {
    return PARSER;
  }

  public pb4server.QueryInAllianceRankRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

