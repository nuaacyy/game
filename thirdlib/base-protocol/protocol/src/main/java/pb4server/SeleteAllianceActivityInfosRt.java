// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * <pre>
 * PUBLIC -&gt; GAME
 * 打开活动总界面只请求联盟活动的数据返回
 * </pre>
 *
 * Protobuf type {@code pb4server.SeleteAllianceActivityInfosRt}
 */
public  final class SeleteAllianceActivityInfosRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.SeleteAllianceActivityInfosRt)
    SeleteAllianceActivityInfosRtOrBuilder {
  // Use SeleteAllianceActivityInfosRt.newBuilder() to construct.
  private SeleteAllianceActivityInfosRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SeleteAllianceActivityInfosRt() {
    rt_ = 0;
    allianceActivityInfos_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private SeleteAllianceActivityInfosRt(
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
              allianceActivityInfos_ = new java.util.ArrayList<pb4server.AllianceActivityInfoVo>();
              mutable_bitField0_ |= 0x00000002;
            }
            allianceActivityInfos_.add(
                input.readMessage(pb4server.AllianceActivityInfoVo.parser(), extensionRegistry));
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
        allianceActivityInfos_ = java.util.Collections.unmodifiableList(allianceActivityInfos_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_SeleteAllianceActivityInfosRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_SeleteAllianceActivityInfosRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.SeleteAllianceActivityInfosRt.class, pb4server.SeleteAllianceActivityInfosRt.Builder.class);
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

  public static final int ALLIANCEACTIVITYINFOS_FIELD_NUMBER = 2;
  private java.util.List<pb4server.AllianceActivityInfoVo> allianceActivityInfos_;
  /**
   * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
   */
  public java.util.List<pb4server.AllianceActivityInfoVo> getAllianceActivityInfosList() {
    return allianceActivityInfos_;
  }
  /**
   * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
   */
  public java.util.List<? extends pb4server.AllianceActivityInfoVoOrBuilder> 
      getAllianceActivityInfosOrBuilderList() {
    return allianceActivityInfos_;
  }
  /**
   * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
   */
  public int getAllianceActivityInfosCount() {
    return allianceActivityInfos_.size();
  }
  /**
   * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
   */
  public pb4server.AllianceActivityInfoVo getAllianceActivityInfos(int index) {
    return allianceActivityInfos_.get(index);
  }
  /**
   * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
   */
  public pb4server.AllianceActivityInfoVoOrBuilder getAllianceActivityInfosOrBuilder(
      int index) {
    return allianceActivityInfos_.get(index);
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
    for (int i = 0; i < allianceActivityInfos_.size(); i++) {
      output.writeMessage(2, allianceActivityInfos_.get(i));
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
    for (int i = 0; i < allianceActivityInfos_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, allianceActivityInfos_.get(i));
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
    if (!(obj instanceof pb4server.SeleteAllianceActivityInfosRt)) {
      return super.equals(obj);
    }
    pb4server.SeleteAllianceActivityInfosRt other = (pb4server.SeleteAllianceActivityInfosRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && getAllianceActivityInfosList()
        .equals(other.getAllianceActivityInfosList());
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
    if (getAllianceActivityInfosCount() > 0) {
      hash = (37 * hash) + ALLIANCEACTIVITYINFOS_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceActivityInfosList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.SeleteAllianceActivityInfosRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.SeleteAllianceActivityInfosRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.SeleteAllianceActivityInfosRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.SeleteAllianceActivityInfosRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.SeleteAllianceActivityInfosRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.SeleteAllianceActivityInfosRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.SeleteAllianceActivityInfosRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.SeleteAllianceActivityInfosRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.SeleteAllianceActivityInfosRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.SeleteAllianceActivityInfosRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.SeleteAllianceActivityInfosRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.SeleteAllianceActivityInfosRt parseFrom(
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
  public static Builder newBuilder(pb4server.SeleteAllianceActivityInfosRt prototype) {
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
   * 打开活动总界面只请求联盟活动的数据返回
   * </pre>
   *
   * Protobuf type {@code pb4server.SeleteAllianceActivityInfosRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.SeleteAllianceActivityInfosRt)
      pb4server.SeleteAllianceActivityInfosRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_SeleteAllianceActivityInfosRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_SeleteAllianceActivityInfosRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.SeleteAllianceActivityInfosRt.class, pb4server.SeleteAllianceActivityInfosRt.Builder.class);
    }

    // Construct using pb4server.SeleteAllianceActivityInfosRt.newBuilder()
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
        getAllianceActivityInfosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;

      if (allianceActivityInfosBuilder_ == null) {
        allianceActivityInfos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        allianceActivityInfosBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_SeleteAllianceActivityInfosRt_descriptor;
    }

    public pb4server.SeleteAllianceActivityInfosRt getDefaultInstanceForType() {
      return pb4server.SeleteAllianceActivityInfosRt.getDefaultInstance();
    }

    public pb4server.SeleteAllianceActivityInfosRt build() {
      pb4server.SeleteAllianceActivityInfosRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.SeleteAllianceActivityInfosRt buildPartial() {
      pb4server.SeleteAllianceActivityInfosRt result = new pb4server.SeleteAllianceActivityInfosRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.rt_ = rt_;
      if (allianceActivityInfosBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          allianceActivityInfos_ = java.util.Collections.unmodifiableList(allianceActivityInfos_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.allianceActivityInfos_ = allianceActivityInfos_;
      } else {
        result.allianceActivityInfos_ = allianceActivityInfosBuilder_.build();
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
      if (other instanceof pb4server.SeleteAllianceActivityInfosRt) {
        return mergeFrom((pb4server.SeleteAllianceActivityInfosRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.SeleteAllianceActivityInfosRt other) {
      if (other == pb4server.SeleteAllianceActivityInfosRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (allianceActivityInfosBuilder_ == null) {
        if (!other.allianceActivityInfos_.isEmpty()) {
          if (allianceActivityInfos_.isEmpty()) {
            allianceActivityInfos_ = other.allianceActivityInfos_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureAllianceActivityInfosIsMutable();
            allianceActivityInfos_.addAll(other.allianceActivityInfos_);
          }
          onChanged();
        }
      } else {
        if (!other.allianceActivityInfos_.isEmpty()) {
          if (allianceActivityInfosBuilder_.isEmpty()) {
            allianceActivityInfosBuilder_.dispose();
            allianceActivityInfosBuilder_ = null;
            allianceActivityInfos_ = other.allianceActivityInfos_;
            bitField0_ = (bitField0_ & ~0x00000002);
            allianceActivityInfosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getAllianceActivityInfosFieldBuilder() : null;
          } else {
            allianceActivityInfosBuilder_.addAllMessages(other.allianceActivityInfos_);
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
      pb4server.SeleteAllianceActivityInfosRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.SeleteAllianceActivityInfosRt) e.getUnfinishedMessage();
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

    private java.util.List<pb4server.AllianceActivityInfoVo> allianceActivityInfos_ =
      java.util.Collections.emptyList();
    private void ensureAllianceActivityInfosIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        allianceActivityInfos_ = new java.util.ArrayList<pb4server.AllianceActivityInfoVo>(allianceActivityInfos_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AllianceActivityInfoVo, pb4server.AllianceActivityInfoVo.Builder, pb4server.AllianceActivityInfoVoOrBuilder> allianceActivityInfosBuilder_;

    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public java.util.List<pb4server.AllianceActivityInfoVo> getAllianceActivityInfosList() {
      if (allianceActivityInfosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(allianceActivityInfos_);
      } else {
        return allianceActivityInfosBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public int getAllianceActivityInfosCount() {
      if (allianceActivityInfosBuilder_ == null) {
        return allianceActivityInfos_.size();
      } else {
        return allianceActivityInfosBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public pb4server.AllianceActivityInfoVo getAllianceActivityInfos(int index) {
      if (allianceActivityInfosBuilder_ == null) {
        return allianceActivityInfos_.get(index);
      } else {
        return allianceActivityInfosBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public Builder setAllianceActivityInfos(
        int index, pb4server.AllianceActivityInfoVo value) {
      if (allianceActivityInfosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceActivityInfosIsMutable();
        allianceActivityInfos_.set(index, value);
        onChanged();
      } else {
        allianceActivityInfosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public Builder setAllianceActivityInfos(
        int index, pb4server.AllianceActivityInfoVo.Builder builderForValue) {
      if (allianceActivityInfosBuilder_ == null) {
        ensureAllianceActivityInfosIsMutable();
        allianceActivityInfos_.set(index, builderForValue.build());
        onChanged();
      } else {
        allianceActivityInfosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public Builder addAllianceActivityInfos(pb4server.AllianceActivityInfoVo value) {
      if (allianceActivityInfosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceActivityInfosIsMutable();
        allianceActivityInfos_.add(value);
        onChanged();
      } else {
        allianceActivityInfosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public Builder addAllianceActivityInfos(
        int index, pb4server.AllianceActivityInfoVo value) {
      if (allianceActivityInfosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceActivityInfosIsMutable();
        allianceActivityInfos_.add(index, value);
        onChanged();
      } else {
        allianceActivityInfosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public Builder addAllianceActivityInfos(
        pb4server.AllianceActivityInfoVo.Builder builderForValue) {
      if (allianceActivityInfosBuilder_ == null) {
        ensureAllianceActivityInfosIsMutable();
        allianceActivityInfos_.add(builderForValue.build());
        onChanged();
      } else {
        allianceActivityInfosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public Builder addAllianceActivityInfos(
        int index, pb4server.AllianceActivityInfoVo.Builder builderForValue) {
      if (allianceActivityInfosBuilder_ == null) {
        ensureAllianceActivityInfosIsMutable();
        allianceActivityInfos_.add(index, builderForValue.build());
        onChanged();
      } else {
        allianceActivityInfosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public Builder addAllAllianceActivityInfos(
        java.lang.Iterable<? extends pb4server.AllianceActivityInfoVo> values) {
      if (allianceActivityInfosBuilder_ == null) {
        ensureAllianceActivityInfosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, allianceActivityInfos_);
        onChanged();
      } else {
        allianceActivityInfosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public Builder clearAllianceActivityInfos() {
      if (allianceActivityInfosBuilder_ == null) {
        allianceActivityInfos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        allianceActivityInfosBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public Builder removeAllianceActivityInfos(int index) {
      if (allianceActivityInfosBuilder_ == null) {
        ensureAllianceActivityInfosIsMutable();
        allianceActivityInfos_.remove(index);
        onChanged();
      } else {
        allianceActivityInfosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public pb4server.AllianceActivityInfoVo.Builder getAllianceActivityInfosBuilder(
        int index) {
      return getAllianceActivityInfosFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public pb4server.AllianceActivityInfoVoOrBuilder getAllianceActivityInfosOrBuilder(
        int index) {
      if (allianceActivityInfosBuilder_ == null) {
        return allianceActivityInfos_.get(index);  } else {
        return allianceActivityInfosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public java.util.List<? extends pb4server.AllianceActivityInfoVoOrBuilder> 
         getAllianceActivityInfosOrBuilderList() {
      if (allianceActivityInfosBuilder_ != null) {
        return allianceActivityInfosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(allianceActivityInfos_);
      }
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public pb4server.AllianceActivityInfoVo.Builder addAllianceActivityInfosBuilder() {
      return getAllianceActivityInfosFieldBuilder().addBuilder(
          pb4server.AllianceActivityInfoVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public pb4server.AllianceActivityInfoVo.Builder addAllianceActivityInfosBuilder(
        int index) {
      return getAllianceActivityInfosFieldBuilder().addBuilder(
          index, pb4server.AllianceActivityInfoVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.AllianceActivityInfoVo allianceActivityInfos = 2;</code>
     */
    public java.util.List<pb4server.AllianceActivityInfoVo.Builder> 
         getAllianceActivityInfosBuilderList() {
      return getAllianceActivityInfosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AllianceActivityInfoVo, pb4server.AllianceActivityInfoVo.Builder, pb4server.AllianceActivityInfoVoOrBuilder> 
        getAllianceActivityInfosFieldBuilder() {
      if (allianceActivityInfosBuilder_ == null) {
        allianceActivityInfosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.AllianceActivityInfoVo, pb4server.AllianceActivityInfoVo.Builder, pb4server.AllianceActivityInfoVoOrBuilder>(
                allianceActivityInfos_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        allianceActivityInfos_ = null;
      }
      return allianceActivityInfosBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.SeleteAllianceActivityInfosRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.SeleteAllianceActivityInfosRt)
  private static final pb4server.SeleteAllianceActivityInfosRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.SeleteAllianceActivityInfosRt();
  }

  public static pb4server.SeleteAllianceActivityInfosRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SeleteAllianceActivityInfosRt>
      PARSER = new com.google.protobuf.AbstractParser<SeleteAllianceActivityInfosRt>() {
    public SeleteAllianceActivityInfosRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SeleteAllianceActivityInfosRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SeleteAllianceActivityInfosRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SeleteAllianceActivityInfosRt> getParserForType() {
    return PARSER;
  }

  public pb4server.SeleteAllianceActivityInfosRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
