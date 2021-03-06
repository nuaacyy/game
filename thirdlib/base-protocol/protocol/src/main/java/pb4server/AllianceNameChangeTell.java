// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.AllianceNameChangeTell}
 */
public  final class AllianceNameChangeTell extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AllianceNameChangeTell)
    AllianceNameChangeTellOrBuilder {
  // Use AllianceNameChangeTell.newBuilder() to construct.
  private AllianceNameChangeTell(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceNameChangeTell() {
    allianceNameChangeVos_ = java.util.Collections.emptyList();
    nowSycnNameVersion_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllianceNameChangeTell(
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              allianceNameChangeVos_ = new java.util.ArrayList<pb4server.AllianceNameChangeVo>();
              mutable_bitField0_ |= 0x00000001;
            }
            allianceNameChangeVos_.add(
                input.readMessage(pb4server.AllianceNameChangeVo.parser(), extensionRegistry));
            break;
          }
          case 16: {

            nowSycnNameVersion_ = input.readInt32();
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        allianceNameChangeVos_ = java.util.Collections.unmodifiableList(allianceNameChangeVos_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalWkt.internal_static_pb4server_AllianceNameChangeTell_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_AllianceNameChangeTell_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AllianceNameChangeTell.class, pb4server.AllianceNameChangeTell.Builder.class);
  }

  private int bitField0_;
  public static final int ALLIANCENAMECHANGEVOS_FIELD_NUMBER = 1;
  private java.util.List<pb4server.AllianceNameChangeVo> allianceNameChangeVos_;
  /**
   * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
   */
  public java.util.List<pb4server.AllianceNameChangeVo> getAllianceNameChangeVosList() {
    return allianceNameChangeVos_;
  }
  /**
   * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
   */
  public java.util.List<? extends pb4server.AllianceNameChangeVoOrBuilder> 
      getAllianceNameChangeVosOrBuilderList() {
    return allianceNameChangeVos_;
  }
  /**
   * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
   */
  public int getAllianceNameChangeVosCount() {
    return allianceNameChangeVos_.size();
  }
  /**
   * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
   */
  public pb4server.AllianceNameChangeVo getAllianceNameChangeVos(int index) {
    return allianceNameChangeVos_.get(index);
  }
  /**
   * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
   */
  public pb4server.AllianceNameChangeVoOrBuilder getAllianceNameChangeVosOrBuilder(
      int index) {
    return allianceNameChangeVos_.get(index);
  }

  public static final int NOWSYCNNAMEVERSION_FIELD_NUMBER = 2;
  private int nowSycnNameVersion_;
  /**
   * <pre>
   * 当前版本号
   * </pre>
   *
   * <code>int32 nowSycnNameVersion = 2;</code>
   */
  public int getNowSycnNameVersion() {
    return nowSycnNameVersion_;
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
    for (int i = 0; i < allianceNameChangeVos_.size(); i++) {
      output.writeMessage(1, allianceNameChangeVos_.get(i));
    }
    if (nowSycnNameVersion_ != 0) {
      output.writeInt32(2, nowSycnNameVersion_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < allianceNameChangeVos_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, allianceNameChangeVos_.get(i));
    }
    if (nowSycnNameVersion_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, nowSycnNameVersion_);
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
    if (!(obj instanceof pb4server.AllianceNameChangeTell)) {
      return super.equals(obj);
    }
    pb4server.AllianceNameChangeTell other = (pb4server.AllianceNameChangeTell) obj;

    boolean result = true;
    result = result && getAllianceNameChangeVosList()
        .equals(other.getAllianceNameChangeVosList());
    result = result && (getNowSycnNameVersion()
        == other.getNowSycnNameVersion());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getAllianceNameChangeVosCount() > 0) {
      hash = (37 * hash) + ALLIANCENAMECHANGEVOS_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceNameChangeVosList().hashCode();
    }
    hash = (37 * hash) + NOWSYCNNAMEVERSION_FIELD_NUMBER;
    hash = (53 * hash) + getNowSycnNameVersion();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.AllianceNameChangeTell parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceNameChangeTell parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceNameChangeTell parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceNameChangeTell parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceNameChangeTell parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceNameChangeTell parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceNameChangeTell parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceNameChangeTell parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceNameChangeTell parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AllianceNameChangeTell parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceNameChangeTell parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceNameChangeTell parseFrom(
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
  public static Builder newBuilder(pb4server.AllianceNameChangeTell prototype) {
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
   * Protobuf type {@code pb4server.AllianceNameChangeTell}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AllianceNameChangeTell)
      pb4server.AllianceNameChangeTellOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_AllianceNameChangeTell_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_AllianceNameChangeTell_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AllianceNameChangeTell.class, pb4server.AllianceNameChangeTell.Builder.class);
    }

    // Construct using pb4server.AllianceNameChangeTell.newBuilder()
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
        getAllianceNameChangeVosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (allianceNameChangeVosBuilder_ == null) {
        allianceNameChangeVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        allianceNameChangeVosBuilder_.clear();
      }
      nowSycnNameVersion_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_AllianceNameChangeTell_descriptor;
    }

    public pb4server.AllianceNameChangeTell getDefaultInstanceForType() {
      return pb4server.AllianceNameChangeTell.getDefaultInstance();
    }

    public pb4server.AllianceNameChangeTell build() {
      pb4server.AllianceNameChangeTell result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AllianceNameChangeTell buildPartial() {
      pb4server.AllianceNameChangeTell result = new pb4server.AllianceNameChangeTell(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (allianceNameChangeVosBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          allianceNameChangeVos_ = java.util.Collections.unmodifiableList(allianceNameChangeVos_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.allianceNameChangeVos_ = allianceNameChangeVos_;
      } else {
        result.allianceNameChangeVos_ = allianceNameChangeVosBuilder_.build();
      }
      result.nowSycnNameVersion_ = nowSycnNameVersion_;
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
      if (other instanceof pb4server.AllianceNameChangeTell) {
        return mergeFrom((pb4server.AllianceNameChangeTell)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AllianceNameChangeTell other) {
      if (other == pb4server.AllianceNameChangeTell.getDefaultInstance()) return this;
      if (allianceNameChangeVosBuilder_ == null) {
        if (!other.allianceNameChangeVos_.isEmpty()) {
          if (allianceNameChangeVos_.isEmpty()) {
            allianceNameChangeVos_ = other.allianceNameChangeVos_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureAllianceNameChangeVosIsMutable();
            allianceNameChangeVos_.addAll(other.allianceNameChangeVos_);
          }
          onChanged();
        }
      } else {
        if (!other.allianceNameChangeVos_.isEmpty()) {
          if (allianceNameChangeVosBuilder_.isEmpty()) {
            allianceNameChangeVosBuilder_.dispose();
            allianceNameChangeVosBuilder_ = null;
            allianceNameChangeVos_ = other.allianceNameChangeVos_;
            bitField0_ = (bitField0_ & ~0x00000001);
            allianceNameChangeVosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getAllianceNameChangeVosFieldBuilder() : null;
          } else {
            allianceNameChangeVosBuilder_.addAllMessages(other.allianceNameChangeVos_);
          }
        }
      }
      if (other.getNowSycnNameVersion() != 0) {
        setNowSycnNameVersion(other.getNowSycnNameVersion());
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
      pb4server.AllianceNameChangeTell parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AllianceNameChangeTell) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4server.AllianceNameChangeVo> allianceNameChangeVos_ =
      java.util.Collections.emptyList();
    private void ensureAllianceNameChangeVosIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        allianceNameChangeVos_ = new java.util.ArrayList<pb4server.AllianceNameChangeVo>(allianceNameChangeVos_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AllianceNameChangeVo, pb4server.AllianceNameChangeVo.Builder, pb4server.AllianceNameChangeVoOrBuilder> allianceNameChangeVosBuilder_;

    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public java.util.List<pb4server.AllianceNameChangeVo> getAllianceNameChangeVosList() {
      if (allianceNameChangeVosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(allianceNameChangeVos_);
      } else {
        return allianceNameChangeVosBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public int getAllianceNameChangeVosCount() {
      if (allianceNameChangeVosBuilder_ == null) {
        return allianceNameChangeVos_.size();
      } else {
        return allianceNameChangeVosBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public pb4server.AllianceNameChangeVo getAllianceNameChangeVos(int index) {
      if (allianceNameChangeVosBuilder_ == null) {
        return allianceNameChangeVos_.get(index);
      } else {
        return allianceNameChangeVosBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public Builder setAllianceNameChangeVos(
        int index, pb4server.AllianceNameChangeVo value) {
      if (allianceNameChangeVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceNameChangeVosIsMutable();
        allianceNameChangeVos_.set(index, value);
        onChanged();
      } else {
        allianceNameChangeVosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public Builder setAllianceNameChangeVos(
        int index, pb4server.AllianceNameChangeVo.Builder builderForValue) {
      if (allianceNameChangeVosBuilder_ == null) {
        ensureAllianceNameChangeVosIsMutable();
        allianceNameChangeVos_.set(index, builderForValue.build());
        onChanged();
      } else {
        allianceNameChangeVosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public Builder addAllianceNameChangeVos(pb4server.AllianceNameChangeVo value) {
      if (allianceNameChangeVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceNameChangeVosIsMutable();
        allianceNameChangeVos_.add(value);
        onChanged();
      } else {
        allianceNameChangeVosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public Builder addAllianceNameChangeVos(
        int index, pb4server.AllianceNameChangeVo value) {
      if (allianceNameChangeVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceNameChangeVosIsMutable();
        allianceNameChangeVos_.add(index, value);
        onChanged();
      } else {
        allianceNameChangeVosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public Builder addAllianceNameChangeVos(
        pb4server.AllianceNameChangeVo.Builder builderForValue) {
      if (allianceNameChangeVosBuilder_ == null) {
        ensureAllianceNameChangeVosIsMutable();
        allianceNameChangeVos_.add(builderForValue.build());
        onChanged();
      } else {
        allianceNameChangeVosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public Builder addAllianceNameChangeVos(
        int index, pb4server.AllianceNameChangeVo.Builder builderForValue) {
      if (allianceNameChangeVosBuilder_ == null) {
        ensureAllianceNameChangeVosIsMutable();
        allianceNameChangeVos_.add(index, builderForValue.build());
        onChanged();
      } else {
        allianceNameChangeVosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public Builder addAllAllianceNameChangeVos(
        java.lang.Iterable<? extends pb4server.AllianceNameChangeVo> values) {
      if (allianceNameChangeVosBuilder_ == null) {
        ensureAllianceNameChangeVosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, allianceNameChangeVos_);
        onChanged();
      } else {
        allianceNameChangeVosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public Builder clearAllianceNameChangeVos() {
      if (allianceNameChangeVosBuilder_ == null) {
        allianceNameChangeVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        allianceNameChangeVosBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public Builder removeAllianceNameChangeVos(int index) {
      if (allianceNameChangeVosBuilder_ == null) {
        ensureAllianceNameChangeVosIsMutable();
        allianceNameChangeVos_.remove(index);
        onChanged();
      } else {
        allianceNameChangeVosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public pb4server.AllianceNameChangeVo.Builder getAllianceNameChangeVosBuilder(
        int index) {
      return getAllianceNameChangeVosFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public pb4server.AllianceNameChangeVoOrBuilder getAllianceNameChangeVosOrBuilder(
        int index) {
      if (allianceNameChangeVosBuilder_ == null) {
        return allianceNameChangeVos_.get(index);  } else {
        return allianceNameChangeVosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public java.util.List<? extends pb4server.AllianceNameChangeVoOrBuilder> 
         getAllianceNameChangeVosOrBuilderList() {
      if (allianceNameChangeVosBuilder_ != null) {
        return allianceNameChangeVosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(allianceNameChangeVos_);
      }
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public pb4server.AllianceNameChangeVo.Builder addAllianceNameChangeVosBuilder() {
      return getAllianceNameChangeVosFieldBuilder().addBuilder(
          pb4server.AllianceNameChangeVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public pb4server.AllianceNameChangeVo.Builder addAllianceNameChangeVosBuilder(
        int index) {
      return getAllianceNameChangeVosFieldBuilder().addBuilder(
          index, pb4server.AllianceNameChangeVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.AllianceNameChangeVo allianceNameChangeVos = 1;</code>
     */
    public java.util.List<pb4server.AllianceNameChangeVo.Builder> 
         getAllianceNameChangeVosBuilderList() {
      return getAllianceNameChangeVosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AllianceNameChangeVo, pb4server.AllianceNameChangeVo.Builder, pb4server.AllianceNameChangeVoOrBuilder> 
        getAllianceNameChangeVosFieldBuilder() {
      if (allianceNameChangeVosBuilder_ == null) {
        allianceNameChangeVosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.AllianceNameChangeVo, pb4server.AllianceNameChangeVo.Builder, pb4server.AllianceNameChangeVoOrBuilder>(
                allianceNameChangeVos_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        allianceNameChangeVos_ = null;
      }
      return allianceNameChangeVosBuilder_;
    }

    private int nowSycnNameVersion_ ;
    /**
     * <pre>
     * 当前版本号
     * </pre>
     *
     * <code>int32 nowSycnNameVersion = 2;</code>
     */
    public int getNowSycnNameVersion() {
      return nowSycnNameVersion_;
    }
    /**
     * <pre>
     * 当前版本号
     * </pre>
     *
     * <code>int32 nowSycnNameVersion = 2;</code>
     */
    public Builder setNowSycnNameVersion(int value) {
      
      nowSycnNameVersion_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 当前版本号
     * </pre>
     *
     * <code>int32 nowSycnNameVersion = 2;</code>
     */
    public Builder clearNowSycnNameVersion() {
      
      nowSycnNameVersion_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.AllianceNameChangeTell)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AllianceNameChangeTell)
  private static final pb4server.AllianceNameChangeTell DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AllianceNameChangeTell();
  }

  public static pb4server.AllianceNameChangeTell getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllianceNameChangeTell>
      PARSER = new com.google.protobuf.AbstractParser<AllianceNameChangeTell>() {
    public AllianceNameChangeTell parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceNameChangeTell(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceNameChangeTell> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceNameChangeTell> getParserForType() {
    return PARSER;
  }

  public pb4server.AllianceNameChangeTell getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

