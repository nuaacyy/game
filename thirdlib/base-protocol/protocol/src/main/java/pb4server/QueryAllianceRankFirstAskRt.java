// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * <pre>
 * PUBLIC -&gt; GAME
 * 查询联盟数据排行榜首页返回
 * </pre>
 *
 * Protobuf type {@code pb4server.QueryAllianceRankFirstAskRt}
 */
public  final class QueryAllianceRankFirstAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.QueryAllianceRankFirstAskRt)
    QueryAllianceRankFirstAskRtOrBuilder {
  // Use QueryAllianceRankFirstAskRt.newBuilder() to construct.
  private QueryAllianceRankFirstAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryAllianceRankFirstAskRt() {
    rt_ = 0;
    queryAllianceRankVos_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private QueryAllianceRankFirstAskRt(
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
              queryAllianceRankVos_ = new java.util.ArrayList<pb4server.QueryAllianceRankAskVos>();
              mutable_bitField0_ |= 0x00000002;
            }
            queryAllianceRankVos_.add(
                input.readMessage(pb4server.QueryAllianceRankAskVos.parser(), extensionRegistry));
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
        queryAllianceRankVos_ = java.util.Collections.unmodifiableList(queryAllianceRankVos_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceRankFirstAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceRankFirstAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.QueryAllianceRankFirstAskRt.class, pb4server.QueryAllianceRankFirstAskRt.Builder.class);
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

  public static final int QUERYALLIANCERANKVOS_FIELD_NUMBER = 2;
  private java.util.List<pb4server.QueryAllianceRankAskVos> queryAllianceRankVos_;
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
   */
  public java.util.List<pb4server.QueryAllianceRankAskVos> getQueryAllianceRankVosList() {
    return queryAllianceRankVos_;
  }
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
   */
  public java.util.List<? extends pb4server.QueryAllianceRankAskVosOrBuilder> 
      getQueryAllianceRankVosOrBuilderList() {
    return queryAllianceRankVos_;
  }
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
   */
  public int getQueryAllianceRankVosCount() {
    return queryAllianceRankVos_.size();
  }
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
   */
  public pb4server.QueryAllianceRankAskVos getQueryAllianceRankVos(int index) {
    return queryAllianceRankVos_.get(index);
  }
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
   */
  public pb4server.QueryAllianceRankAskVosOrBuilder getQueryAllianceRankVosOrBuilder(
      int index) {
    return queryAllianceRankVos_.get(index);
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
    for (int i = 0; i < queryAllianceRankVos_.size(); i++) {
      output.writeMessage(2, queryAllianceRankVos_.get(i));
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
    for (int i = 0; i < queryAllianceRankVos_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, queryAllianceRankVos_.get(i));
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
    if (!(obj instanceof pb4server.QueryAllianceRankFirstAskRt)) {
      return super.equals(obj);
    }
    pb4server.QueryAllianceRankFirstAskRt other = (pb4server.QueryAllianceRankFirstAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && getQueryAllianceRankVosList()
        .equals(other.getQueryAllianceRankVosList());
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
    if (getQueryAllianceRankVosCount() > 0) {
      hash = (37 * hash) + QUERYALLIANCERANKVOS_FIELD_NUMBER;
      hash = (53 * hash) + getQueryAllianceRankVosList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.QueryAllianceRankFirstAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryAllianceRankFirstAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryAllianceRankFirstAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryAllianceRankFirstAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryAllianceRankFirstAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryAllianceRankFirstAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryAllianceRankFirstAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryAllianceRankFirstAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryAllianceRankFirstAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.QueryAllianceRankFirstAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryAllianceRankFirstAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryAllianceRankFirstAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.QueryAllianceRankFirstAskRt prototype) {
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
   * 查询联盟数据排行榜首页返回
   * </pre>
   *
   * Protobuf type {@code pb4server.QueryAllianceRankFirstAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.QueryAllianceRankFirstAskRt)
      pb4server.QueryAllianceRankFirstAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceRankFirstAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceRankFirstAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.QueryAllianceRankFirstAskRt.class, pb4server.QueryAllianceRankFirstAskRt.Builder.class);
    }

    // Construct using pb4server.QueryAllianceRankFirstAskRt.newBuilder()
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
        getQueryAllianceRankVosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;

      if (queryAllianceRankVosBuilder_ == null) {
        queryAllianceRankVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        queryAllianceRankVosBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceRankFirstAskRt_descriptor;
    }

    public pb4server.QueryAllianceRankFirstAskRt getDefaultInstanceForType() {
      return pb4server.QueryAllianceRankFirstAskRt.getDefaultInstance();
    }

    public pb4server.QueryAllianceRankFirstAskRt build() {
      pb4server.QueryAllianceRankFirstAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.QueryAllianceRankFirstAskRt buildPartial() {
      pb4server.QueryAllianceRankFirstAskRt result = new pb4server.QueryAllianceRankFirstAskRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.rt_ = rt_;
      if (queryAllianceRankVosBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          queryAllianceRankVos_ = java.util.Collections.unmodifiableList(queryAllianceRankVos_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.queryAllianceRankVos_ = queryAllianceRankVos_;
      } else {
        result.queryAllianceRankVos_ = queryAllianceRankVosBuilder_.build();
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
      if (other instanceof pb4server.QueryAllianceRankFirstAskRt) {
        return mergeFrom((pb4server.QueryAllianceRankFirstAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.QueryAllianceRankFirstAskRt other) {
      if (other == pb4server.QueryAllianceRankFirstAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (queryAllianceRankVosBuilder_ == null) {
        if (!other.queryAllianceRankVos_.isEmpty()) {
          if (queryAllianceRankVos_.isEmpty()) {
            queryAllianceRankVos_ = other.queryAllianceRankVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureQueryAllianceRankVosIsMutable();
            queryAllianceRankVos_.addAll(other.queryAllianceRankVos_);
          }
          onChanged();
        }
      } else {
        if (!other.queryAllianceRankVos_.isEmpty()) {
          if (queryAllianceRankVosBuilder_.isEmpty()) {
            queryAllianceRankVosBuilder_.dispose();
            queryAllianceRankVosBuilder_ = null;
            queryAllianceRankVos_ = other.queryAllianceRankVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
            queryAllianceRankVosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getQueryAllianceRankVosFieldBuilder() : null;
          } else {
            queryAllianceRankVosBuilder_.addAllMessages(other.queryAllianceRankVos_);
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
      pb4server.QueryAllianceRankFirstAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.QueryAllianceRankFirstAskRt) e.getUnfinishedMessage();
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

    private java.util.List<pb4server.QueryAllianceRankAskVos> queryAllianceRankVos_ =
      java.util.Collections.emptyList();
    private void ensureQueryAllianceRankVosIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        queryAllianceRankVos_ = new java.util.ArrayList<pb4server.QueryAllianceRankAskVos>(queryAllianceRankVos_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.QueryAllianceRankAskVos, pb4server.QueryAllianceRankAskVos.Builder, pb4server.QueryAllianceRankAskVosOrBuilder> queryAllianceRankVosBuilder_;

    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public java.util.List<pb4server.QueryAllianceRankAskVos> getQueryAllianceRankVosList() {
      if (queryAllianceRankVosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(queryAllianceRankVos_);
      } else {
        return queryAllianceRankVosBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public int getQueryAllianceRankVosCount() {
      if (queryAllianceRankVosBuilder_ == null) {
        return queryAllianceRankVos_.size();
      } else {
        return queryAllianceRankVosBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public pb4server.QueryAllianceRankAskVos getQueryAllianceRankVos(int index) {
      if (queryAllianceRankVosBuilder_ == null) {
        return queryAllianceRankVos_.get(index);
      } else {
        return queryAllianceRankVosBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public Builder setQueryAllianceRankVos(
        int index, pb4server.QueryAllianceRankAskVos value) {
      if (queryAllianceRankVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.set(index, value);
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public Builder setQueryAllianceRankVos(
        int index, pb4server.QueryAllianceRankAskVos.Builder builderForValue) {
      if (queryAllianceRankVosBuilder_ == null) {
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.set(index, builderForValue.build());
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public Builder addQueryAllianceRankVos(pb4server.QueryAllianceRankAskVos value) {
      if (queryAllianceRankVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.add(value);
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public Builder addQueryAllianceRankVos(
        int index, pb4server.QueryAllianceRankAskVos value) {
      if (queryAllianceRankVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.add(index, value);
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public Builder addQueryAllianceRankVos(
        pb4server.QueryAllianceRankAskVos.Builder builderForValue) {
      if (queryAllianceRankVosBuilder_ == null) {
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.add(builderForValue.build());
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public Builder addQueryAllianceRankVos(
        int index, pb4server.QueryAllianceRankAskVos.Builder builderForValue) {
      if (queryAllianceRankVosBuilder_ == null) {
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.add(index, builderForValue.build());
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public Builder addAllQueryAllianceRankVos(
        java.lang.Iterable<? extends pb4server.QueryAllianceRankAskVos> values) {
      if (queryAllianceRankVosBuilder_ == null) {
        ensureQueryAllianceRankVosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, queryAllianceRankVos_);
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public Builder clearQueryAllianceRankVos() {
      if (queryAllianceRankVosBuilder_ == null) {
        queryAllianceRankVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public Builder removeQueryAllianceRankVos(int index) {
      if (queryAllianceRankVosBuilder_ == null) {
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.remove(index);
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public pb4server.QueryAllianceRankAskVos.Builder getQueryAllianceRankVosBuilder(
        int index) {
      return getQueryAllianceRankVosFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public pb4server.QueryAllianceRankAskVosOrBuilder getQueryAllianceRankVosOrBuilder(
        int index) {
      if (queryAllianceRankVosBuilder_ == null) {
        return queryAllianceRankVos_.get(index);  } else {
        return queryAllianceRankVosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public java.util.List<? extends pb4server.QueryAllianceRankAskVosOrBuilder> 
         getQueryAllianceRankVosOrBuilderList() {
      if (queryAllianceRankVosBuilder_ != null) {
        return queryAllianceRankVosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(queryAllianceRankVos_);
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public pb4server.QueryAllianceRankAskVos.Builder addQueryAllianceRankVosBuilder() {
      return getQueryAllianceRankVosFieldBuilder().addBuilder(
          pb4server.QueryAllianceRankAskVos.getDefaultInstance());
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public pb4server.QueryAllianceRankAskVos.Builder addQueryAllianceRankVosBuilder(
        int index) {
      return getQueryAllianceRankVosFieldBuilder().addBuilder(
          index, pb4server.QueryAllianceRankAskVos.getDefaultInstance());
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .pb4server.QueryAllianceRankAskVos queryAllianceRankVos = 2;</code>
     */
    public java.util.List<pb4server.QueryAllianceRankAskVos.Builder> 
         getQueryAllianceRankVosBuilderList() {
      return getQueryAllianceRankVosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.QueryAllianceRankAskVos, pb4server.QueryAllianceRankAskVos.Builder, pb4server.QueryAllianceRankAskVosOrBuilder> 
        getQueryAllianceRankVosFieldBuilder() {
      if (queryAllianceRankVosBuilder_ == null) {
        queryAllianceRankVosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.QueryAllianceRankAskVos, pb4server.QueryAllianceRankAskVos.Builder, pb4server.QueryAllianceRankAskVosOrBuilder>(
                queryAllianceRankVos_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        queryAllianceRankVos_ = null;
      }
      return queryAllianceRankVosBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.QueryAllianceRankFirstAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.QueryAllianceRankFirstAskRt)
  private static final pb4server.QueryAllianceRankFirstAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.QueryAllianceRankFirstAskRt();
  }

  public static pb4server.QueryAllianceRankFirstAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueryAllianceRankFirstAskRt>
      PARSER = new com.google.protobuf.AbstractParser<QueryAllianceRankFirstAskRt>() {
    public QueryAllianceRankFirstAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryAllianceRankFirstAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryAllianceRankFirstAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryAllianceRankFirstAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.QueryAllianceRankFirstAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

