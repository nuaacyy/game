// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3030
 * 服务器 -&gt; 客户端
 * 提示信息
 * </pre>
 *
 * Protobuf type {@code client2server.ShowSystemInfo}
 */
public  final class ShowSystemInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.ShowSystemInfo)
    ShowSystemInfoOrBuilder {
  // Use ShowSystemInfo.newBuilder() to construct.
  private ShowSystemInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ShowSystemInfo() {
    langId_ = "";
    param_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ShowSystemInfo(
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
          case 10: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000001;
            langId_ = bs;
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              param_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000002;
            }
            param_.add(bs);
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
        param_ = param_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_ShowSystemInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_ShowSystemInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.ShowSystemInfo.class, pb4client.ShowSystemInfo.Builder.class);
  }

  private int bitField0_;
  public static final int LANGID_FIELD_NUMBER = 1;
  private volatile java.lang.Object langId_;
  /**
   * <pre>
   * 语言包编号
   * </pre>
   *
   * <code>required string langId = 1;</code>
   */
  public boolean hasLangId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 语言包编号
   * </pre>
   *
   * <code>required string langId = 1;</code>
   */
  public java.lang.String getLangId() {
    java.lang.Object ref = langId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        langId_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 语言包编号
   * </pre>
   *
   * <code>required string langId = 1;</code>
   */
  public com.google.protobuf.ByteString
      getLangIdBytes() {
    java.lang.Object ref = langId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      langId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PARAM_FIELD_NUMBER = 2;
  private com.google.protobuf.LazyStringList param_;
  /**
   * <pre>
   * 参数
   * </pre>
   *
   * <code>repeated string param = 2;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getParamList() {
    return param_;
  }
  /**
   * <pre>
   * 参数
   * </pre>
   *
   * <code>repeated string param = 2;</code>
   */
  public int getParamCount() {
    return param_.size();
  }
  /**
   * <pre>
   * 参数
   * </pre>
   *
   * <code>repeated string param = 2;</code>
   */
  public java.lang.String getParam(int index) {
    return param_.get(index);
  }
  /**
   * <pre>
   * 参数
   * </pre>
   *
   * <code>repeated string param = 2;</code>
   */
  public com.google.protobuf.ByteString
      getParamBytes(int index) {
    return param_.getByteString(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasLangId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, langId_);
    }
    for (int i = 0; i < param_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, param_.getRaw(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, langId_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < param_.size(); i++) {
        dataSize += computeStringSizeNoTag(param_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getParamList().size();
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
    if (!(obj instanceof pb4client.ShowSystemInfo)) {
      return super.equals(obj);
    }
    pb4client.ShowSystemInfo other = (pb4client.ShowSystemInfo) obj;

    boolean result = true;
    result = result && (hasLangId() == other.hasLangId());
    if (hasLangId()) {
      result = result && getLangId()
          .equals(other.getLangId());
    }
    result = result && getParamList()
        .equals(other.getParamList());
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
    if (hasLangId()) {
      hash = (37 * hash) + LANGID_FIELD_NUMBER;
      hash = (53 * hash) + getLangId().hashCode();
    }
    if (getParamCount() > 0) {
      hash = (37 * hash) + PARAM_FIELD_NUMBER;
      hash = (53 * hash) + getParamList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.ShowSystemInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ShowSystemInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ShowSystemInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ShowSystemInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ShowSystemInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ShowSystemInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ShowSystemInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ShowSystemInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ShowSystemInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.ShowSystemInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ShowSystemInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ShowSystemInfo parseFrom(
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
  public static Builder newBuilder(pb4client.ShowSystemInfo prototype) {
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
   * msgType = 3030
   * 服务器 -&gt; 客户端
   * 提示信息
   * </pre>
   *
   * Protobuf type {@code client2server.ShowSystemInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.ShowSystemInfo)
      pb4client.ShowSystemInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_ShowSystemInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_ShowSystemInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.ShowSystemInfo.class, pb4client.ShowSystemInfo.Builder.class);
    }

    // Construct using pb4client.ShowSystemInfo.newBuilder()
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
      }
    }
    public Builder clear() {
      super.clear();
      langId_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      param_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_ShowSystemInfo_descriptor;
    }

    public pb4client.ShowSystemInfo getDefaultInstanceForType() {
      return pb4client.ShowSystemInfo.getDefaultInstance();
    }

    public pb4client.ShowSystemInfo build() {
      pb4client.ShowSystemInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.ShowSystemInfo buildPartial() {
      pb4client.ShowSystemInfo result = new pb4client.ShowSystemInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.langId_ = langId_;
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        param_ = param_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.param_ = param_;
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
      if (other instanceof pb4client.ShowSystemInfo) {
        return mergeFrom((pb4client.ShowSystemInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.ShowSystemInfo other) {
      if (other == pb4client.ShowSystemInfo.getDefaultInstance()) return this;
      if (other.hasLangId()) {
        bitField0_ |= 0x00000001;
        langId_ = other.langId_;
        onChanged();
      }
      if (!other.param_.isEmpty()) {
        if (param_.isEmpty()) {
          param_ = other.param_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensureParamIsMutable();
          param_.addAll(other.param_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasLangId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.ShowSystemInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.ShowSystemInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object langId_ = "";
    /**
     * <pre>
     * 语言包编号
     * </pre>
     *
     * <code>required string langId = 1;</code>
     */
    public boolean hasLangId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 语言包编号
     * </pre>
     *
     * <code>required string langId = 1;</code>
     */
    public java.lang.String getLangId() {
      java.lang.Object ref = langId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          langId_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 语言包编号
     * </pre>
     *
     * <code>required string langId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getLangIdBytes() {
      java.lang.Object ref = langId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        langId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 语言包编号
     * </pre>
     *
     * <code>required string langId = 1;</code>
     */
    public Builder setLangId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      langId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 语言包编号
     * </pre>
     *
     * <code>required string langId = 1;</code>
     */
    public Builder clearLangId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      langId_ = getDefaultInstance().getLangId();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 语言包编号
     * </pre>
     *
     * <code>required string langId = 1;</code>
     */
    public Builder setLangIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      langId_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList param_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureParamIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        param_ = new com.google.protobuf.LazyStringArrayList(param_);
        bitField0_ |= 0x00000002;
       }
    }
    /**
     * <pre>
     * 参数
     * </pre>
     *
     * <code>repeated string param = 2;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getParamList() {
      return param_.getUnmodifiableView();
    }
    /**
     * <pre>
     * 参数
     * </pre>
     *
     * <code>repeated string param = 2;</code>
     */
    public int getParamCount() {
      return param_.size();
    }
    /**
     * <pre>
     * 参数
     * </pre>
     *
     * <code>repeated string param = 2;</code>
     */
    public java.lang.String getParam(int index) {
      return param_.get(index);
    }
    /**
     * <pre>
     * 参数
     * </pre>
     *
     * <code>repeated string param = 2;</code>
     */
    public com.google.protobuf.ByteString
        getParamBytes(int index) {
      return param_.getByteString(index);
    }
    /**
     * <pre>
     * 参数
     * </pre>
     *
     * <code>repeated string param = 2;</code>
     */
    public Builder setParam(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureParamIsMutable();
      param_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 参数
     * </pre>
     *
     * <code>repeated string param = 2;</code>
     */
    public Builder addParam(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureParamIsMutable();
      param_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 参数
     * </pre>
     *
     * <code>repeated string param = 2;</code>
     */
    public Builder addAllParam(
        java.lang.Iterable<java.lang.String> values) {
      ensureParamIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, param_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 参数
     * </pre>
     *
     * <code>repeated string param = 2;</code>
     */
    public Builder clearParam() {
      param_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 参数
     * </pre>
     *
     * <code>repeated string param = 2;</code>
     */
    public Builder addParamBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureParamIsMutable();
      param_.add(value);
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.ShowSystemInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.ShowSystemInfo)
  private static final pb4client.ShowSystemInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.ShowSystemInfo();
  }

  public static pb4client.ShowSystemInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ShowSystemInfo>
      PARSER = new com.google.protobuf.AbstractParser<ShowSystemInfo>() {
    public ShowSystemInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ShowSystemInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ShowSystemInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ShowSystemInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.ShowSystemInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

