// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.QueryAllianceLogAskRt}
 */
public  final class QueryAllianceLogAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.QueryAllianceLogAskRt)
    QueryAllianceLogAskRtOrBuilder {
  // Use QueryAllianceLogAskRt.newBuilder() to construct.
  private QueryAllianceLogAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryAllianceLogAskRt() {
    rt_ = 0;
    l_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private QueryAllianceLogAskRt(
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
              l_ = new java.util.ArrayList<pb4server.AllianceQueryLogInfo>();
              mutable_bitField0_ |= 0x00000002;
            }
            l_.add(
                input.readMessage(pb4server.AllianceQueryLogInfo.parser(), extensionRegistry));
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
        l_ = java.util.Collections.unmodifiableList(l_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceLogAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceLogAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.QueryAllianceLogAskRt.class, pb4server.QueryAllianceLogAskRt.Builder.class);
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

  public static final int L_FIELD_NUMBER = 2;
  private java.util.List<pb4server.AllianceQueryLogInfo> l_;
  /**
   * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
   */
  public java.util.List<pb4server.AllianceQueryLogInfo> getLList() {
    return l_;
  }
  /**
   * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
   */
  public java.util.List<? extends pb4server.AllianceQueryLogInfoOrBuilder> 
      getLOrBuilderList() {
    return l_;
  }
  /**
   * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
   */
  public int getLCount() {
    return l_.size();
  }
  /**
   * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
   */
  public pb4server.AllianceQueryLogInfo getL(int index) {
    return l_.get(index);
  }
  /**
   * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
   */
  public pb4server.AllianceQueryLogInfoOrBuilder getLOrBuilder(
      int index) {
    return l_.get(index);
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
    for (int i = 0; i < l_.size(); i++) {
      output.writeMessage(2, l_.get(i));
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
    for (int i = 0; i < l_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, l_.get(i));
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
    if (!(obj instanceof pb4server.QueryAllianceLogAskRt)) {
      return super.equals(obj);
    }
    pb4server.QueryAllianceLogAskRt other = (pb4server.QueryAllianceLogAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && getLList()
        .equals(other.getLList());
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
    if (getLCount() > 0) {
      hash = (37 * hash) + L_FIELD_NUMBER;
      hash = (53 * hash) + getLList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.QueryAllianceLogAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryAllianceLogAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryAllianceLogAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryAllianceLogAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryAllianceLogAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryAllianceLogAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryAllianceLogAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryAllianceLogAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryAllianceLogAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.QueryAllianceLogAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryAllianceLogAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryAllianceLogAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.QueryAllianceLogAskRt prototype) {
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
   * Protobuf type {@code pb4server.QueryAllianceLogAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.QueryAllianceLogAskRt)
      pb4server.QueryAllianceLogAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceLogAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceLogAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.QueryAllianceLogAskRt.class, pb4server.QueryAllianceLogAskRt.Builder.class);
    }

    // Construct using pb4server.QueryAllianceLogAskRt.newBuilder()
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
        getLFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;

      if (lBuilder_ == null) {
        l_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        lBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceLogAskRt_descriptor;
    }

    public pb4server.QueryAllianceLogAskRt getDefaultInstanceForType() {
      return pb4server.QueryAllianceLogAskRt.getDefaultInstance();
    }

    public pb4server.QueryAllianceLogAskRt build() {
      pb4server.QueryAllianceLogAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.QueryAllianceLogAskRt buildPartial() {
      pb4server.QueryAllianceLogAskRt result = new pb4server.QueryAllianceLogAskRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.rt_ = rt_;
      if (lBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          l_ = java.util.Collections.unmodifiableList(l_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.l_ = l_;
      } else {
        result.l_ = lBuilder_.build();
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
      if (other instanceof pb4server.QueryAllianceLogAskRt) {
        return mergeFrom((pb4server.QueryAllianceLogAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.QueryAllianceLogAskRt other) {
      if (other == pb4server.QueryAllianceLogAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (lBuilder_ == null) {
        if (!other.l_.isEmpty()) {
          if (l_.isEmpty()) {
            l_ = other.l_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureLIsMutable();
            l_.addAll(other.l_);
          }
          onChanged();
        }
      } else {
        if (!other.l_.isEmpty()) {
          if (lBuilder_.isEmpty()) {
            lBuilder_.dispose();
            lBuilder_ = null;
            l_ = other.l_;
            bitField0_ = (bitField0_ & ~0x00000002);
            lBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getLFieldBuilder() : null;
          } else {
            lBuilder_.addAllMessages(other.l_);
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
      pb4server.QueryAllianceLogAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.QueryAllianceLogAskRt) e.getUnfinishedMessage();
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

    private java.util.List<pb4server.AllianceQueryLogInfo> l_ =
      java.util.Collections.emptyList();
    private void ensureLIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        l_ = new java.util.ArrayList<pb4server.AllianceQueryLogInfo>(l_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AllianceQueryLogInfo, pb4server.AllianceQueryLogInfo.Builder, pb4server.AllianceQueryLogInfoOrBuilder> lBuilder_;

    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public java.util.List<pb4server.AllianceQueryLogInfo> getLList() {
      if (lBuilder_ == null) {
        return java.util.Collections.unmodifiableList(l_);
      } else {
        return lBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public int getLCount() {
      if (lBuilder_ == null) {
        return l_.size();
      } else {
        return lBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public pb4server.AllianceQueryLogInfo getL(int index) {
      if (lBuilder_ == null) {
        return l_.get(index);
      } else {
        return lBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public Builder setL(
        int index, pb4server.AllianceQueryLogInfo value) {
      if (lBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLIsMutable();
        l_.set(index, value);
        onChanged();
      } else {
        lBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public Builder setL(
        int index, pb4server.AllianceQueryLogInfo.Builder builderForValue) {
      if (lBuilder_ == null) {
        ensureLIsMutable();
        l_.set(index, builderForValue.build());
        onChanged();
      } else {
        lBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public Builder addL(pb4server.AllianceQueryLogInfo value) {
      if (lBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLIsMutable();
        l_.add(value);
        onChanged();
      } else {
        lBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public Builder addL(
        int index, pb4server.AllianceQueryLogInfo value) {
      if (lBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLIsMutable();
        l_.add(index, value);
        onChanged();
      } else {
        lBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public Builder addL(
        pb4server.AllianceQueryLogInfo.Builder builderForValue) {
      if (lBuilder_ == null) {
        ensureLIsMutable();
        l_.add(builderForValue.build());
        onChanged();
      } else {
        lBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public Builder addL(
        int index, pb4server.AllianceQueryLogInfo.Builder builderForValue) {
      if (lBuilder_ == null) {
        ensureLIsMutable();
        l_.add(index, builderForValue.build());
        onChanged();
      } else {
        lBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public Builder addAllL(
        java.lang.Iterable<? extends pb4server.AllianceQueryLogInfo> values) {
      if (lBuilder_ == null) {
        ensureLIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, l_);
        onChanged();
      } else {
        lBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public Builder clearL() {
      if (lBuilder_ == null) {
        l_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        lBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public Builder removeL(int index) {
      if (lBuilder_ == null) {
        ensureLIsMutable();
        l_.remove(index);
        onChanged();
      } else {
        lBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public pb4server.AllianceQueryLogInfo.Builder getLBuilder(
        int index) {
      return getLFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public pb4server.AllianceQueryLogInfoOrBuilder getLOrBuilder(
        int index) {
      if (lBuilder_ == null) {
        return l_.get(index);  } else {
        return lBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public java.util.List<? extends pb4server.AllianceQueryLogInfoOrBuilder> 
         getLOrBuilderList() {
      if (lBuilder_ != null) {
        return lBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(l_);
      }
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public pb4server.AllianceQueryLogInfo.Builder addLBuilder() {
      return getLFieldBuilder().addBuilder(
          pb4server.AllianceQueryLogInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public pb4server.AllianceQueryLogInfo.Builder addLBuilder(
        int index) {
      return getLFieldBuilder().addBuilder(
          index, pb4server.AllianceQueryLogInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.AllianceQueryLogInfo l = 2;</code>
     */
    public java.util.List<pb4server.AllianceQueryLogInfo.Builder> 
         getLBuilderList() {
      return getLFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AllianceQueryLogInfo, pb4server.AllianceQueryLogInfo.Builder, pb4server.AllianceQueryLogInfoOrBuilder> 
        getLFieldBuilder() {
      if (lBuilder_ == null) {
        lBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.AllianceQueryLogInfo, pb4server.AllianceQueryLogInfo.Builder, pb4server.AllianceQueryLogInfoOrBuilder>(
                l_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        l_ = null;
      }
      return lBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.QueryAllianceLogAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.QueryAllianceLogAskRt)
  private static final pb4server.QueryAllianceLogAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.QueryAllianceLogAskRt();
  }

  public static pb4server.QueryAllianceLogAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueryAllianceLogAskRt>
      PARSER = new com.google.protobuf.AbstractParser<QueryAllianceLogAskRt>() {
    public QueryAllianceLogAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryAllianceLogAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryAllianceLogAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryAllianceLogAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.QueryAllianceLogAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

