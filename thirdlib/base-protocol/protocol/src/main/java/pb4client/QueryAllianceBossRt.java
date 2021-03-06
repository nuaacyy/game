// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.QueryAllianceBossRt}
 */
public  final class QueryAllianceBossRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.QueryAllianceBossRt)
    QueryAllianceBossRtOrBuilder {
  // Use QueryAllianceBossRt.newBuilder() to construct.
  private QueryAllianceBossRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryAllianceBossRt() {
    rt_ = 0;
    allianceBossVos_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QueryAllianceBossRt(
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
              allianceBossVos_ = new java.util.ArrayList<pb4client.AllianceBossVo>();
              mutable_bitField0_ |= 0x00000002;
            }
            allianceBossVos_.add(
                input.readMessage(pb4client.AllianceBossVo.PARSER, extensionRegistry));
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
        allianceBossVos_ = java.util.Collections.unmodifiableList(allianceBossVos_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_QueryAllianceBossRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_QueryAllianceBossRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.QueryAllianceBossRt.class, pb4client.QueryAllianceBossRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int ALLIANCEBOSSVOS_FIELD_NUMBER = 2;
  private java.util.List<pb4client.AllianceBossVo> allianceBossVos_;
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
   */
  public java.util.List<pb4client.AllianceBossVo> getAllianceBossVosList() {
    return allianceBossVos_;
  }
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
   */
  public java.util.List<? extends pb4client.AllianceBossVoOrBuilder> 
      getAllianceBossVosOrBuilderList() {
    return allianceBossVos_;
  }
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
   */
  public int getAllianceBossVosCount() {
    return allianceBossVos_.size();
  }
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
   */
  public pb4client.AllianceBossVo getAllianceBossVos(int index) {
    return allianceBossVos_.get(index);
  }
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
   */
  public pb4client.AllianceBossVoOrBuilder getAllianceBossVosOrBuilder(
      int index) {
    return allianceBossVos_.get(index);
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
    for (int i = 0; i < getAllianceBossVosCount(); i++) {
      if (!getAllianceBossVos(i).isInitialized()) {
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
    for (int i = 0; i < allianceBossVos_.size(); i++) {
      output.writeMessage(2, allianceBossVos_.get(i));
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
    for (int i = 0; i < allianceBossVos_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, allianceBossVos_.get(i));
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
    if (!(obj instanceof pb4client.QueryAllianceBossRt)) {
      return super.equals(obj);
    }
    pb4client.QueryAllianceBossRt other = (pb4client.QueryAllianceBossRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getAllianceBossVosList()
        .equals(other.getAllianceBossVosList());
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
    if (getAllianceBossVosCount() > 0) {
      hash = (37 * hash) + ALLIANCEBOSSVOS_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceBossVosList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.QueryAllianceBossRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryAllianceBossRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryAllianceBossRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryAllianceBossRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryAllianceBossRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryAllianceBossRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryAllianceBossRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryAllianceBossRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryAllianceBossRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.QueryAllianceBossRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryAllianceBossRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryAllianceBossRt parseFrom(
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
  public static Builder newBuilder(pb4client.QueryAllianceBossRt prototype) {
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
   * Protobuf type {@code client2server.QueryAllianceBossRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.QueryAllianceBossRt)
      pb4client.QueryAllianceBossRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryAllianceBossRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryAllianceBossRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.QueryAllianceBossRt.class, pb4client.QueryAllianceBossRt.Builder.class);
    }

    // Construct using pb4client.QueryAllianceBossRt.newBuilder()
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
        getAllianceBossVosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (allianceBossVosBuilder_ == null) {
        allianceBossVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        allianceBossVosBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryAllianceBossRt_descriptor;
    }

    public pb4client.QueryAllianceBossRt getDefaultInstanceForType() {
      return pb4client.QueryAllianceBossRt.getDefaultInstance();
    }

    public pb4client.QueryAllianceBossRt build() {
      pb4client.QueryAllianceBossRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.QueryAllianceBossRt buildPartial() {
      pb4client.QueryAllianceBossRt result = new pb4client.QueryAllianceBossRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (allianceBossVosBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          allianceBossVos_ = java.util.Collections.unmodifiableList(allianceBossVos_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.allianceBossVos_ = allianceBossVos_;
      } else {
        result.allianceBossVos_ = allianceBossVosBuilder_.build();
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
      if (other instanceof pb4client.QueryAllianceBossRt) {
        return mergeFrom((pb4client.QueryAllianceBossRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.QueryAllianceBossRt other) {
      if (other == pb4client.QueryAllianceBossRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (allianceBossVosBuilder_ == null) {
        if (!other.allianceBossVos_.isEmpty()) {
          if (allianceBossVos_.isEmpty()) {
            allianceBossVos_ = other.allianceBossVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureAllianceBossVosIsMutable();
            allianceBossVos_.addAll(other.allianceBossVos_);
          }
          onChanged();
        }
      } else {
        if (!other.allianceBossVos_.isEmpty()) {
          if (allianceBossVosBuilder_.isEmpty()) {
            allianceBossVosBuilder_.dispose();
            allianceBossVosBuilder_ = null;
            allianceBossVos_ = other.allianceBossVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
            allianceBossVosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getAllianceBossVosFieldBuilder() : null;
          } else {
            allianceBossVosBuilder_.addAllMessages(other.allianceBossVos_);
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
      for (int i = 0; i < getAllianceBossVosCount(); i++) {
        if (!getAllianceBossVos(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.QueryAllianceBossRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.QueryAllianceBossRt) e.getUnfinishedMessage();
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
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.AllianceBossVo> allianceBossVos_ =
      java.util.Collections.emptyList();
    private void ensureAllianceBossVosIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        allianceBossVos_ = new java.util.ArrayList<pb4client.AllianceBossVo>(allianceBossVos_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.AllianceBossVo, pb4client.AllianceBossVo.Builder, pb4client.AllianceBossVoOrBuilder> allianceBossVosBuilder_;

    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public java.util.List<pb4client.AllianceBossVo> getAllianceBossVosList() {
      if (allianceBossVosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(allianceBossVos_);
      } else {
        return allianceBossVosBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public int getAllianceBossVosCount() {
      if (allianceBossVosBuilder_ == null) {
        return allianceBossVos_.size();
      } else {
        return allianceBossVosBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public pb4client.AllianceBossVo getAllianceBossVos(int index) {
      if (allianceBossVosBuilder_ == null) {
        return allianceBossVos_.get(index);
      } else {
        return allianceBossVosBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public Builder setAllianceBossVos(
        int index, pb4client.AllianceBossVo value) {
      if (allianceBossVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceBossVosIsMutable();
        allianceBossVos_.set(index, value);
        onChanged();
      } else {
        allianceBossVosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public Builder setAllianceBossVos(
        int index, pb4client.AllianceBossVo.Builder builderForValue) {
      if (allianceBossVosBuilder_ == null) {
        ensureAllianceBossVosIsMutable();
        allianceBossVos_.set(index, builderForValue.build());
        onChanged();
      } else {
        allianceBossVosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public Builder addAllianceBossVos(pb4client.AllianceBossVo value) {
      if (allianceBossVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceBossVosIsMutable();
        allianceBossVos_.add(value);
        onChanged();
      } else {
        allianceBossVosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public Builder addAllianceBossVos(
        int index, pb4client.AllianceBossVo value) {
      if (allianceBossVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceBossVosIsMutable();
        allianceBossVos_.add(index, value);
        onChanged();
      } else {
        allianceBossVosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public Builder addAllianceBossVos(
        pb4client.AllianceBossVo.Builder builderForValue) {
      if (allianceBossVosBuilder_ == null) {
        ensureAllianceBossVosIsMutable();
        allianceBossVos_.add(builderForValue.build());
        onChanged();
      } else {
        allianceBossVosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public Builder addAllianceBossVos(
        int index, pb4client.AllianceBossVo.Builder builderForValue) {
      if (allianceBossVosBuilder_ == null) {
        ensureAllianceBossVosIsMutable();
        allianceBossVos_.add(index, builderForValue.build());
        onChanged();
      } else {
        allianceBossVosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public Builder addAllAllianceBossVos(
        java.lang.Iterable<? extends pb4client.AllianceBossVo> values) {
      if (allianceBossVosBuilder_ == null) {
        ensureAllianceBossVosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, allianceBossVos_);
        onChanged();
      } else {
        allianceBossVosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public Builder clearAllianceBossVos() {
      if (allianceBossVosBuilder_ == null) {
        allianceBossVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        allianceBossVosBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public Builder removeAllianceBossVos(int index) {
      if (allianceBossVosBuilder_ == null) {
        ensureAllianceBossVosIsMutable();
        allianceBossVos_.remove(index);
        onChanged();
      } else {
        allianceBossVosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public pb4client.AllianceBossVo.Builder getAllianceBossVosBuilder(
        int index) {
      return getAllianceBossVosFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public pb4client.AllianceBossVoOrBuilder getAllianceBossVosOrBuilder(
        int index) {
      if (allianceBossVosBuilder_ == null) {
        return allianceBossVos_.get(index);  } else {
        return allianceBossVosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public java.util.List<? extends pb4client.AllianceBossVoOrBuilder> 
         getAllianceBossVosOrBuilderList() {
      if (allianceBossVosBuilder_ != null) {
        return allianceBossVosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(allianceBossVos_);
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public pb4client.AllianceBossVo.Builder addAllianceBossVosBuilder() {
      return getAllianceBossVosFieldBuilder().addBuilder(
          pb4client.AllianceBossVo.getDefaultInstance());
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public pb4client.AllianceBossVo.Builder addAllianceBossVosBuilder(
        int index) {
      return getAllianceBossVosFieldBuilder().addBuilder(
          index, pb4client.AllianceBossVo.getDefaultInstance());
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.AllianceBossVo allianceBossVos = 2;</code>
     */
    public java.util.List<pb4client.AllianceBossVo.Builder> 
         getAllianceBossVosBuilderList() {
      return getAllianceBossVosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.AllianceBossVo, pb4client.AllianceBossVo.Builder, pb4client.AllianceBossVoOrBuilder> 
        getAllianceBossVosFieldBuilder() {
      if (allianceBossVosBuilder_ == null) {
        allianceBossVosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.AllianceBossVo, pb4client.AllianceBossVo.Builder, pb4client.AllianceBossVoOrBuilder>(
                allianceBossVos_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        allianceBossVos_ = null;
      }
      return allianceBossVosBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.QueryAllianceBossRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.QueryAllianceBossRt)
  private static final pb4client.QueryAllianceBossRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.QueryAllianceBossRt();
  }

  public static pb4client.QueryAllianceBossRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<QueryAllianceBossRt>
      PARSER = new com.google.protobuf.AbstractParser<QueryAllianceBossRt>() {
    public QueryAllianceBossRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryAllianceBossRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryAllianceBossRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryAllianceBossRt> getParserForType() {
    return PARSER;
  }

  public pb4client.QueryAllianceBossRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

