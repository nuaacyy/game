// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.OpenAllianceHelpAskRt}
 */
public  final class OpenAllianceHelpAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.OpenAllianceHelpAskRt)
    OpenAllianceHelpAskRtOrBuilder {
  // Use OpenAllianceHelpAskRt.newBuilder() to construct.
  private OpenAllianceHelpAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OpenAllianceHelpAskRt() {
    rt_ = 0;
    myHelpVo_ = java.util.Collections.emptyList();
    allianceHelpVos_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private OpenAllianceHelpAskRt(
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
              myHelpVo_ = new java.util.ArrayList<pb4server.MyHelpVo>();
              mutable_bitField0_ |= 0x00000002;
            }
            myHelpVo_.add(
                input.readMessage(pb4server.MyHelpVo.parser(), extensionRegistry));
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              allianceHelpVos_ = new java.util.ArrayList<pb4server.AllianceHelpVo>();
              mutable_bitField0_ |= 0x00000004;
            }
            allianceHelpVos_.add(
                input.readMessage(pb4server.AllianceHelpVo.parser(), extensionRegistry));
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
        myHelpVo_ = java.util.Collections.unmodifiableList(myHelpVo_);
      }
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        allianceHelpVos_ = java.util.Collections.unmodifiableList(allianceHelpVos_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceHelpAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceHelpAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.OpenAllianceHelpAskRt.class, pb4server.OpenAllianceHelpAskRt.Builder.class);
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

  public static final int MYHELPVO_FIELD_NUMBER = 2;
  private java.util.List<pb4server.MyHelpVo> myHelpVo_;
  /**
   * <pre>
   * 我的研发信息
   * </pre>
   *
   * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
   */
  public java.util.List<pb4server.MyHelpVo> getMyHelpVoList() {
    return myHelpVo_;
  }
  /**
   * <pre>
   * 我的研发信息
   * </pre>
   *
   * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
   */
  public java.util.List<? extends pb4server.MyHelpVoOrBuilder> 
      getMyHelpVoOrBuilderList() {
    return myHelpVo_;
  }
  /**
   * <pre>
   * 我的研发信息
   * </pre>
   *
   * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
   */
  public int getMyHelpVoCount() {
    return myHelpVo_.size();
  }
  /**
   * <pre>
   * 我的研发信息
   * </pre>
   *
   * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
   */
  public pb4server.MyHelpVo getMyHelpVo(int index) {
    return myHelpVo_.get(index);
  }
  /**
   * <pre>
   * 我的研发信息
   * </pre>
   *
   * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
   */
  public pb4server.MyHelpVoOrBuilder getMyHelpVoOrBuilder(
      int index) {
    return myHelpVo_.get(index);
  }

  public static final int ALLIANCEHELPVOS_FIELD_NUMBER = 3;
  private java.util.List<pb4server.AllianceHelpVo> allianceHelpVos_;
  /**
   * <pre>
   *帮助信息列表
   * </pre>
   *
   * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
   */
  public java.util.List<pb4server.AllianceHelpVo> getAllianceHelpVosList() {
    return allianceHelpVos_;
  }
  /**
   * <pre>
   *帮助信息列表
   * </pre>
   *
   * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
   */
  public java.util.List<? extends pb4server.AllianceHelpVoOrBuilder> 
      getAllianceHelpVosOrBuilderList() {
    return allianceHelpVos_;
  }
  /**
   * <pre>
   *帮助信息列表
   * </pre>
   *
   * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
   */
  public int getAllianceHelpVosCount() {
    return allianceHelpVos_.size();
  }
  /**
   * <pre>
   *帮助信息列表
   * </pre>
   *
   * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
   */
  public pb4server.AllianceHelpVo getAllianceHelpVos(int index) {
    return allianceHelpVos_.get(index);
  }
  /**
   * <pre>
   *帮助信息列表
   * </pre>
   *
   * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
   */
  public pb4server.AllianceHelpVoOrBuilder getAllianceHelpVosOrBuilder(
      int index) {
    return allianceHelpVos_.get(index);
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
    for (int i = 0; i < myHelpVo_.size(); i++) {
      output.writeMessage(2, myHelpVo_.get(i));
    }
    for (int i = 0; i < allianceHelpVos_.size(); i++) {
      output.writeMessage(3, allianceHelpVos_.get(i));
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
    for (int i = 0; i < myHelpVo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, myHelpVo_.get(i));
    }
    for (int i = 0; i < allianceHelpVos_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, allianceHelpVos_.get(i));
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
    if (!(obj instanceof pb4server.OpenAllianceHelpAskRt)) {
      return super.equals(obj);
    }
    pb4server.OpenAllianceHelpAskRt other = (pb4server.OpenAllianceHelpAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && getMyHelpVoList()
        .equals(other.getMyHelpVoList());
    result = result && getAllianceHelpVosList()
        .equals(other.getAllianceHelpVosList());
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
    if (getMyHelpVoCount() > 0) {
      hash = (37 * hash) + MYHELPVO_FIELD_NUMBER;
      hash = (53 * hash) + getMyHelpVoList().hashCode();
    }
    if (getAllianceHelpVosCount() > 0) {
      hash = (37 * hash) + ALLIANCEHELPVOS_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceHelpVosList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.OpenAllianceHelpAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenAllianceHelpAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenAllianceHelpAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenAllianceHelpAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenAllianceHelpAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenAllianceHelpAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenAllianceHelpAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.OpenAllianceHelpAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.OpenAllianceHelpAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.OpenAllianceHelpAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.OpenAllianceHelpAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.OpenAllianceHelpAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.OpenAllianceHelpAskRt prototype) {
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
   * Protobuf type {@code pb4server.OpenAllianceHelpAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.OpenAllianceHelpAskRt)
      pb4server.OpenAllianceHelpAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceHelpAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceHelpAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.OpenAllianceHelpAskRt.class, pb4server.OpenAllianceHelpAskRt.Builder.class);
    }

    // Construct using pb4server.OpenAllianceHelpAskRt.newBuilder()
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
        getMyHelpVoFieldBuilder();
        getAllianceHelpVosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;

      if (myHelpVoBuilder_ == null) {
        myHelpVo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        myHelpVoBuilder_.clear();
      }
      if (allianceHelpVosBuilder_ == null) {
        allianceHelpVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        allianceHelpVosBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceHelpAskRt_descriptor;
    }

    public pb4server.OpenAllianceHelpAskRt getDefaultInstanceForType() {
      return pb4server.OpenAllianceHelpAskRt.getDefaultInstance();
    }

    public pb4server.OpenAllianceHelpAskRt build() {
      pb4server.OpenAllianceHelpAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.OpenAllianceHelpAskRt buildPartial() {
      pb4server.OpenAllianceHelpAskRt result = new pb4server.OpenAllianceHelpAskRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.rt_ = rt_;
      if (myHelpVoBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          myHelpVo_ = java.util.Collections.unmodifiableList(myHelpVo_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.myHelpVo_ = myHelpVo_;
      } else {
        result.myHelpVo_ = myHelpVoBuilder_.build();
      }
      if (allianceHelpVosBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          allianceHelpVos_ = java.util.Collections.unmodifiableList(allianceHelpVos_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.allianceHelpVos_ = allianceHelpVos_;
      } else {
        result.allianceHelpVos_ = allianceHelpVosBuilder_.build();
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
      if (other instanceof pb4server.OpenAllianceHelpAskRt) {
        return mergeFrom((pb4server.OpenAllianceHelpAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.OpenAllianceHelpAskRt other) {
      if (other == pb4server.OpenAllianceHelpAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (myHelpVoBuilder_ == null) {
        if (!other.myHelpVo_.isEmpty()) {
          if (myHelpVo_.isEmpty()) {
            myHelpVo_ = other.myHelpVo_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureMyHelpVoIsMutable();
            myHelpVo_.addAll(other.myHelpVo_);
          }
          onChanged();
        }
      } else {
        if (!other.myHelpVo_.isEmpty()) {
          if (myHelpVoBuilder_.isEmpty()) {
            myHelpVoBuilder_.dispose();
            myHelpVoBuilder_ = null;
            myHelpVo_ = other.myHelpVo_;
            bitField0_ = (bitField0_ & ~0x00000002);
            myHelpVoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getMyHelpVoFieldBuilder() : null;
          } else {
            myHelpVoBuilder_.addAllMessages(other.myHelpVo_);
          }
        }
      }
      if (allianceHelpVosBuilder_ == null) {
        if (!other.allianceHelpVos_.isEmpty()) {
          if (allianceHelpVos_.isEmpty()) {
            allianceHelpVos_ = other.allianceHelpVos_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureAllianceHelpVosIsMutable();
            allianceHelpVos_.addAll(other.allianceHelpVos_);
          }
          onChanged();
        }
      } else {
        if (!other.allianceHelpVos_.isEmpty()) {
          if (allianceHelpVosBuilder_.isEmpty()) {
            allianceHelpVosBuilder_.dispose();
            allianceHelpVosBuilder_ = null;
            allianceHelpVos_ = other.allianceHelpVos_;
            bitField0_ = (bitField0_ & ~0x00000004);
            allianceHelpVosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getAllianceHelpVosFieldBuilder() : null;
          } else {
            allianceHelpVosBuilder_.addAllMessages(other.allianceHelpVos_);
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
      pb4server.OpenAllianceHelpAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.OpenAllianceHelpAskRt) e.getUnfinishedMessage();
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

    private java.util.List<pb4server.MyHelpVo> myHelpVo_ =
      java.util.Collections.emptyList();
    private void ensureMyHelpVoIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        myHelpVo_ = new java.util.ArrayList<pb4server.MyHelpVo>(myHelpVo_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.MyHelpVo, pb4server.MyHelpVo.Builder, pb4server.MyHelpVoOrBuilder> myHelpVoBuilder_;

    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public java.util.List<pb4server.MyHelpVo> getMyHelpVoList() {
      if (myHelpVoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(myHelpVo_);
      } else {
        return myHelpVoBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public int getMyHelpVoCount() {
      if (myHelpVoBuilder_ == null) {
        return myHelpVo_.size();
      } else {
        return myHelpVoBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public pb4server.MyHelpVo getMyHelpVo(int index) {
      if (myHelpVoBuilder_ == null) {
        return myHelpVo_.get(index);
      } else {
        return myHelpVoBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public Builder setMyHelpVo(
        int index, pb4server.MyHelpVo value) {
      if (myHelpVoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMyHelpVoIsMutable();
        myHelpVo_.set(index, value);
        onChanged();
      } else {
        myHelpVoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public Builder setMyHelpVo(
        int index, pb4server.MyHelpVo.Builder builderForValue) {
      if (myHelpVoBuilder_ == null) {
        ensureMyHelpVoIsMutable();
        myHelpVo_.set(index, builderForValue.build());
        onChanged();
      } else {
        myHelpVoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public Builder addMyHelpVo(pb4server.MyHelpVo value) {
      if (myHelpVoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMyHelpVoIsMutable();
        myHelpVo_.add(value);
        onChanged();
      } else {
        myHelpVoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public Builder addMyHelpVo(
        int index, pb4server.MyHelpVo value) {
      if (myHelpVoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMyHelpVoIsMutable();
        myHelpVo_.add(index, value);
        onChanged();
      } else {
        myHelpVoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public Builder addMyHelpVo(
        pb4server.MyHelpVo.Builder builderForValue) {
      if (myHelpVoBuilder_ == null) {
        ensureMyHelpVoIsMutable();
        myHelpVo_.add(builderForValue.build());
        onChanged();
      } else {
        myHelpVoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public Builder addMyHelpVo(
        int index, pb4server.MyHelpVo.Builder builderForValue) {
      if (myHelpVoBuilder_ == null) {
        ensureMyHelpVoIsMutable();
        myHelpVo_.add(index, builderForValue.build());
        onChanged();
      } else {
        myHelpVoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public Builder addAllMyHelpVo(
        java.lang.Iterable<? extends pb4server.MyHelpVo> values) {
      if (myHelpVoBuilder_ == null) {
        ensureMyHelpVoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, myHelpVo_);
        onChanged();
      } else {
        myHelpVoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public Builder clearMyHelpVo() {
      if (myHelpVoBuilder_ == null) {
        myHelpVo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        myHelpVoBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public Builder removeMyHelpVo(int index) {
      if (myHelpVoBuilder_ == null) {
        ensureMyHelpVoIsMutable();
        myHelpVo_.remove(index);
        onChanged();
      } else {
        myHelpVoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public pb4server.MyHelpVo.Builder getMyHelpVoBuilder(
        int index) {
      return getMyHelpVoFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public pb4server.MyHelpVoOrBuilder getMyHelpVoOrBuilder(
        int index) {
      if (myHelpVoBuilder_ == null) {
        return myHelpVo_.get(index);  } else {
        return myHelpVoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public java.util.List<? extends pb4server.MyHelpVoOrBuilder> 
         getMyHelpVoOrBuilderList() {
      if (myHelpVoBuilder_ != null) {
        return myHelpVoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(myHelpVo_);
      }
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public pb4server.MyHelpVo.Builder addMyHelpVoBuilder() {
      return getMyHelpVoFieldBuilder().addBuilder(
          pb4server.MyHelpVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public pb4server.MyHelpVo.Builder addMyHelpVoBuilder(
        int index) {
      return getMyHelpVoFieldBuilder().addBuilder(
          index, pb4server.MyHelpVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>repeated .pb4server.MyHelpVo myHelpVo = 2;</code>
     */
    public java.util.List<pb4server.MyHelpVo.Builder> 
         getMyHelpVoBuilderList() {
      return getMyHelpVoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.MyHelpVo, pb4server.MyHelpVo.Builder, pb4server.MyHelpVoOrBuilder> 
        getMyHelpVoFieldBuilder() {
      if (myHelpVoBuilder_ == null) {
        myHelpVoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.MyHelpVo, pb4server.MyHelpVo.Builder, pb4server.MyHelpVoOrBuilder>(
                myHelpVo_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        myHelpVo_ = null;
      }
      return myHelpVoBuilder_;
    }

    private java.util.List<pb4server.AllianceHelpVo> allianceHelpVos_ =
      java.util.Collections.emptyList();
    private void ensureAllianceHelpVosIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        allianceHelpVos_ = new java.util.ArrayList<pb4server.AllianceHelpVo>(allianceHelpVos_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AllianceHelpVo, pb4server.AllianceHelpVo.Builder, pb4server.AllianceHelpVoOrBuilder> allianceHelpVosBuilder_;

    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public java.util.List<pb4server.AllianceHelpVo> getAllianceHelpVosList() {
      if (allianceHelpVosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(allianceHelpVos_);
      } else {
        return allianceHelpVosBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public int getAllianceHelpVosCount() {
      if (allianceHelpVosBuilder_ == null) {
        return allianceHelpVos_.size();
      } else {
        return allianceHelpVosBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public pb4server.AllianceHelpVo getAllianceHelpVos(int index) {
      if (allianceHelpVosBuilder_ == null) {
        return allianceHelpVos_.get(index);
      } else {
        return allianceHelpVosBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public Builder setAllianceHelpVos(
        int index, pb4server.AllianceHelpVo value) {
      if (allianceHelpVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceHelpVosIsMutable();
        allianceHelpVos_.set(index, value);
        onChanged();
      } else {
        allianceHelpVosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public Builder setAllianceHelpVos(
        int index, pb4server.AllianceHelpVo.Builder builderForValue) {
      if (allianceHelpVosBuilder_ == null) {
        ensureAllianceHelpVosIsMutable();
        allianceHelpVos_.set(index, builderForValue.build());
        onChanged();
      } else {
        allianceHelpVosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public Builder addAllianceHelpVos(pb4server.AllianceHelpVo value) {
      if (allianceHelpVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceHelpVosIsMutable();
        allianceHelpVos_.add(value);
        onChanged();
      } else {
        allianceHelpVosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public Builder addAllianceHelpVos(
        int index, pb4server.AllianceHelpVo value) {
      if (allianceHelpVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceHelpVosIsMutable();
        allianceHelpVos_.add(index, value);
        onChanged();
      } else {
        allianceHelpVosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public Builder addAllianceHelpVos(
        pb4server.AllianceHelpVo.Builder builderForValue) {
      if (allianceHelpVosBuilder_ == null) {
        ensureAllianceHelpVosIsMutable();
        allianceHelpVos_.add(builderForValue.build());
        onChanged();
      } else {
        allianceHelpVosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public Builder addAllianceHelpVos(
        int index, pb4server.AllianceHelpVo.Builder builderForValue) {
      if (allianceHelpVosBuilder_ == null) {
        ensureAllianceHelpVosIsMutable();
        allianceHelpVos_.add(index, builderForValue.build());
        onChanged();
      } else {
        allianceHelpVosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public Builder addAllAllianceHelpVos(
        java.lang.Iterable<? extends pb4server.AllianceHelpVo> values) {
      if (allianceHelpVosBuilder_ == null) {
        ensureAllianceHelpVosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, allianceHelpVos_);
        onChanged();
      } else {
        allianceHelpVosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public Builder clearAllianceHelpVos() {
      if (allianceHelpVosBuilder_ == null) {
        allianceHelpVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        allianceHelpVosBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public Builder removeAllianceHelpVos(int index) {
      if (allianceHelpVosBuilder_ == null) {
        ensureAllianceHelpVosIsMutable();
        allianceHelpVos_.remove(index);
        onChanged();
      } else {
        allianceHelpVosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public pb4server.AllianceHelpVo.Builder getAllianceHelpVosBuilder(
        int index) {
      return getAllianceHelpVosFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public pb4server.AllianceHelpVoOrBuilder getAllianceHelpVosOrBuilder(
        int index) {
      if (allianceHelpVosBuilder_ == null) {
        return allianceHelpVos_.get(index);  } else {
        return allianceHelpVosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public java.util.List<? extends pb4server.AllianceHelpVoOrBuilder> 
         getAllianceHelpVosOrBuilderList() {
      if (allianceHelpVosBuilder_ != null) {
        return allianceHelpVosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(allianceHelpVos_);
      }
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public pb4server.AllianceHelpVo.Builder addAllianceHelpVosBuilder() {
      return getAllianceHelpVosFieldBuilder().addBuilder(
          pb4server.AllianceHelpVo.getDefaultInstance());
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public pb4server.AllianceHelpVo.Builder addAllianceHelpVosBuilder(
        int index) {
      return getAllianceHelpVosFieldBuilder().addBuilder(
          index, pb4server.AllianceHelpVo.getDefaultInstance());
    }
    /**
     * <pre>
     *帮助信息列表
     * </pre>
     *
     * <code>repeated .pb4server.AllianceHelpVo allianceHelpVos = 3;</code>
     */
    public java.util.List<pb4server.AllianceHelpVo.Builder> 
         getAllianceHelpVosBuilderList() {
      return getAllianceHelpVosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AllianceHelpVo, pb4server.AllianceHelpVo.Builder, pb4server.AllianceHelpVoOrBuilder> 
        getAllianceHelpVosFieldBuilder() {
      if (allianceHelpVosBuilder_ == null) {
        allianceHelpVosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.AllianceHelpVo, pb4server.AllianceHelpVo.Builder, pb4server.AllianceHelpVoOrBuilder>(
                allianceHelpVos_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        allianceHelpVos_ = null;
      }
      return allianceHelpVosBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.OpenAllianceHelpAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.OpenAllianceHelpAskRt)
  private static final pb4server.OpenAllianceHelpAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.OpenAllianceHelpAskRt();
  }

  public static pb4server.OpenAllianceHelpAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OpenAllianceHelpAskRt>
      PARSER = new com.google.protobuf.AbstractParser<OpenAllianceHelpAskRt>() {
    public OpenAllianceHelpAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OpenAllianceHelpAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OpenAllianceHelpAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OpenAllianceHelpAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.OpenAllianceHelpAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
