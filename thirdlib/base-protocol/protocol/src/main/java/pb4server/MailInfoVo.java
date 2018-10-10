// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.MailInfoVo}
 */
public  final class MailInfoVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.MailInfoVo)
    MailInfoVoOrBuilder {
  // Use MailInfoVo.newBuilder() to construct.
  private MailInfoVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MailInfoVo() {
    readType_ = 0;
    title_ = "";
    titleParam_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    message_ = "";
    messageParam_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private MailInfoVo(
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

            readType_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            title_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              titleParam_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000004;
            }
            titleParam_.add(s);
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            message_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
              messageParam_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000010;
            }
            messageParam_.add(s);
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
        titleParam_ = titleParam_.getUnmodifiableView();
      }
      if (((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
        messageParam_ = messageParam_.getUnmodifiableView();
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalHkt.internal_static_pb4server_MailInfoVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_MailInfoVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.MailInfoVo.class, pb4server.MailInfoVo.Builder.class);
  }

  private int bitField0_;
  public static final int READTYPE_FIELD_NUMBER = 1;
  private int readType_;
  /**
   * <pre>
   *邮件结构体的读取类型 1-读lan (一般都是游戏内发的) 2-直接读取内容(一般都是后台发的)
   * </pre>
   *
   * <code>int32 readType = 1;</code>
   */
  public int getReadType() {
    return readType_;
  }

  public static final int TITLE_FIELD_NUMBER = 2;
  private volatile java.lang.Object title_;
  /**
   * <pre>
   *邮件标题lanId
   * </pre>
   *
   * <code>string title = 2;</code>
   */
  public java.lang.String getTitle() {
    java.lang.Object ref = title_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      title_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *邮件标题lanId
   * </pre>
   *
   * <code>string title = 2;</code>
   */
  public com.google.protobuf.ByteString
      getTitleBytes() {
    java.lang.Object ref = title_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      title_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TITLEPARAM_FIELD_NUMBER = 3;
  private com.google.protobuf.LazyStringList titleParam_;
  /**
   * <pre>
   *邮件标题中参数
   * </pre>
   *
   * <code>repeated string titleParam = 3;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getTitleParamList() {
    return titleParam_;
  }
  /**
   * <pre>
   *邮件标题中参数
   * </pre>
   *
   * <code>repeated string titleParam = 3;</code>
   */
  public int getTitleParamCount() {
    return titleParam_.size();
  }
  /**
   * <pre>
   *邮件标题中参数
   * </pre>
   *
   * <code>repeated string titleParam = 3;</code>
   */
  public java.lang.String getTitleParam(int index) {
    return titleParam_.get(index);
  }
  /**
   * <pre>
   *邮件标题中参数
   * </pre>
   *
   * <code>repeated string titleParam = 3;</code>
   */
  public com.google.protobuf.ByteString
      getTitleParamBytes(int index) {
    return titleParam_.getByteString(index);
  }

  public static final int MESSAGE_FIELD_NUMBER = 4;
  private volatile java.lang.Object message_;
  /**
   * <pre>
   *邮件内容lanId
   * </pre>
   *
   * <code>string message = 4;</code>
   */
  public java.lang.String getMessage() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      message_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *邮件内容lanId
   * </pre>
   *
   * <code>string message = 4;</code>
   */
  public com.google.protobuf.ByteString
      getMessageBytes() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      message_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MESSAGEPARAM_FIELD_NUMBER = 5;
  private com.google.protobuf.LazyStringList messageParam_;
  /**
   * <pre>
   *邮件内容中参数
   * </pre>
   *
   * <code>repeated string messageParam = 5;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getMessageParamList() {
    return messageParam_;
  }
  /**
   * <pre>
   *邮件内容中参数
   * </pre>
   *
   * <code>repeated string messageParam = 5;</code>
   */
  public int getMessageParamCount() {
    return messageParam_.size();
  }
  /**
   * <pre>
   *邮件内容中参数
   * </pre>
   *
   * <code>repeated string messageParam = 5;</code>
   */
  public java.lang.String getMessageParam(int index) {
    return messageParam_.get(index);
  }
  /**
   * <pre>
   *邮件内容中参数
   * </pre>
   *
   * <code>repeated string messageParam = 5;</code>
   */
  public com.google.protobuf.ByteString
      getMessageParamBytes(int index) {
    return messageParam_.getByteString(index);
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
    if (readType_ != 0) {
      output.writeInt32(1, readType_);
    }
    if (!getTitleBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, title_);
    }
    for (int i = 0; i < titleParam_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, titleParam_.getRaw(i));
    }
    if (!getMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, message_);
    }
    for (int i = 0; i < messageParam_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, messageParam_.getRaw(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (readType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, readType_);
    }
    if (!getTitleBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, title_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < titleParam_.size(); i++) {
        dataSize += computeStringSizeNoTag(titleParam_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getTitleParamList().size();
    }
    if (!getMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, message_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < messageParam_.size(); i++) {
        dataSize += computeStringSizeNoTag(messageParam_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getMessageParamList().size();
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
    if (!(obj instanceof pb4server.MailInfoVo)) {
      return super.equals(obj);
    }
    pb4server.MailInfoVo other = (pb4server.MailInfoVo) obj;

    boolean result = true;
    result = result && (getReadType()
        == other.getReadType());
    result = result && getTitle()
        .equals(other.getTitle());
    result = result && getTitleParamList()
        .equals(other.getTitleParamList());
    result = result && getMessage()
        .equals(other.getMessage());
    result = result && getMessageParamList()
        .equals(other.getMessageParamList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + READTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getReadType();
    hash = (37 * hash) + TITLE_FIELD_NUMBER;
    hash = (53 * hash) + getTitle().hashCode();
    if (getTitleParamCount() > 0) {
      hash = (37 * hash) + TITLEPARAM_FIELD_NUMBER;
      hash = (53 * hash) + getTitleParamList().hashCode();
    }
    hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getMessage().hashCode();
    if (getMessageParamCount() > 0) {
      hash = (37 * hash) + MESSAGEPARAM_FIELD_NUMBER;
      hash = (53 * hash) + getMessageParamList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.MailInfoVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.MailInfoVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.MailInfoVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.MailInfoVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.MailInfoVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.MailInfoVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.MailInfoVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.MailInfoVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.MailInfoVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.MailInfoVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.MailInfoVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.MailInfoVo parseFrom(
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
  public static Builder newBuilder(pb4server.MailInfoVo prototype) {
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
   * Protobuf type {@code pb4server.MailInfoVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.MailInfoVo)
      pb4server.MailInfoVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_MailInfoVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_MailInfoVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.MailInfoVo.class, pb4server.MailInfoVo.Builder.class);
    }

    // Construct using pb4server.MailInfoVo.newBuilder()
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
      readType_ = 0;

      title_ = "";

      titleParam_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      message_ = "";

      messageParam_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_MailInfoVo_descriptor;
    }

    public pb4server.MailInfoVo getDefaultInstanceForType() {
      return pb4server.MailInfoVo.getDefaultInstance();
    }

    public pb4server.MailInfoVo build() {
      pb4server.MailInfoVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.MailInfoVo buildPartial() {
      pb4server.MailInfoVo result = new pb4server.MailInfoVo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.readType_ = readType_;
      result.title_ = title_;
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        titleParam_ = titleParam_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000004);
      }
      result.titleParam_ = titleParam_;
      result.message_ = message_;
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        messageParam_ = messageParam_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000010);
      }
      result.messageParam_ = messageParam_;
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
      if (other instanceof pb4server.MailInfoVo) {
        return mergeFrom((pb4server.MailInfoVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.MailInfoVo other) {
      if (other == pb4server.MailInfoVo.getDefaultInstance()) return this;
      if (other.getReadType() != 0) {
        setReadType(other.getReadType());
      }
      if (!other.getTitle().isEmpty()) {
        title_ = other.title_;
        onChanged();
      }
      if (!other.titleParam_.isEmpty()) {
        if (titleParam_.isEmpty()) {
          titleParam_ = other.titleParam_;
          bitField0_ = (bitField0_ & ~0x00000004);
        } else {
          ensureTitleParamIsMutable();
          titleParam_.addAll(other.titleParam_);
        }
        onChanged();
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      if (!other.messageParam_.isEmpty()) {
        if (messageParam_.isEmpty()) {
          messageParam_ = other.messageParam_;
          bitField0_ = (bitField0_ & ~0x00000010);
        } else {
          ensureMessageParamIsMutable();
          messageParam_.addAll(other.messageParam_);
        }
        onChanged();
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
      pb4server.MailInfoVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.MailInfoVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int readType_ ;
    /**
     * <pre>
     *邮件结构体的读取类型 1-读lan (一般都是游戏内发的) 2-直接读取内容(一般都是后台发的)
     * </pre>
     *
     * <code>int32 readType = 1;</code>
     */
    public int getReadType() {
      return readType_;
    }
    /**
     * <pre>
     *邮件结构体的读取类型 1-读lan (一般都是游戏内发的) 2-直接读取内容(一般都是后台发的)
     * </pre>
     *
     * <code>int32 readType = 1;</code>
     */
    public Builder setReadType(int value) {
      
      readType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件结构体的读取类型 1-读lan (一般都是游戏内发的) 2-直接读取内容(一般都是后台发的)
     * </pre>
     *
     * <code>int32 readType = 1;</code>
     */
    public Builder clearReadType() {
      
      readType_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object title_ = "";
    /**
     * <pre>
     *邮件标题lanId
     * </pre>
     *
     * <code>string title = 2;</code>
     */
    public java.lang.String getTitle() {
      java.lang.Object ref = title_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        title_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *邮件标题lanId
     * </pre>
     *
     * <code>string title = 2;</code>
     */
    public com.google.protobuf.ByteString
        getTitleBytes() {
      java.lang.Object ref = title_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        title_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *邮件标题lanId
     * </pre>
     *
     * <code>string title = 2;</code>
     */
    public Builder setTitle(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      title_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件标题lanId
     * </pre>
     *
     * <code>string title = 2;</code>
     */
    public Builder clearTitle() {
      
      title_ = getDefaultInstance().getTitle();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件标题lanId
     * </pre>
     *
     * <code>string title = 2;</code>
     */
    public Builder setTitleBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      title_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList titleParam_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureTitleParamIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        titleParam_ = new com.google.protobuf.LazyStringArrayList(titleParam_);
        bitField0_ |= 0x00000004;
       }
    }
    /**
     * <pre>
     *邮件标题中参数
     * </pre>
     *
     * <code>repeated string titleParam = 3;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getTitleParamList() {
      return titleParam_.getUnmodifiableView();
    }
    /**
     * <pre>
     *邮件标题中参数
     * </pre>
     *
     * <code>repeated string titleParam = 3;</code>
     */
    public int getTitleParamCount() {
      return titleParam_.size();
    }
    /**
     * <pre>
     *邮件标题中参数
     * </pre>
     *
     * <code>repeated string titleParam = 3;</code>
     */
    public java.lang.String getTitleParam(int index) {
      return titleParam_.get(index);
    }
    /**
     * <pre>
     *邮件标题中参数
     * </pre>
     *
     * <code>repeated string titleParam = 3;</code>
     */
    public com.google.protobuf.ByteString
        getTitleParamBytes(int index) {
      return titleParam_.getByteString(index);
    }
    /**
     * <pre>
     *邮件标题中参数
     * </pre>
     *
     * <code>repeated string titleParam = 3;</code>
     */
    public Builder setTitleParam(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureTitleParamIsMutable();
      titleParam_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件标题中参数
     * </pre>
     *
     * <code>repeated string titleParam = 3;</code>
     */
    public Builder addTitleParam(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureTitleParamIsMutable();
      titleParam_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件标题中参数
     * </pre>
     *
     * <code>repeated string titleParam = 3;</code>
     */
    public Builder addAllTitleParam(
        java.lang.Iterable<java.lang.String> values) {
      ensureTitleParamIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, titleParam_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件标题中参数
     * </pre>
     *
     * <code>repeated string titleParam = 3;</code>
     */
    public Builder clearTitleParam() {
      titleParam_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件标题中参数
     * </pre>
     *
     * <code>repeated string titleParam = 3;</code>
     */
    public Builder addTitleParamBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureTitleParamIsMutable();
      titleParam_.add(value);
      onChanged();
      return this;
    }

    private java.lang.Object message_ = "";
    /**
     * <pre>
     *邮件内容lanId
     * </pre>
     *
     * <code>string message = 4;</code>
     */
    public java.lang.String getMessage() {
      java.lang.Object ref = message_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        message_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *邮件内容lanId
     * </pre>
     *
     * <code>string message = 4;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *邮件内容lanId
     * </pre>
     *
     * <code>string message = 4;</code>
     */
    public Builder setMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      message_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件内容lanId
     * </pre>
     *
     * <code>string message = 4;</code>
     */
    public Builder clearMessage() {
      
      message_ = getDefaultInstance().getMessage();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件内容lanId
     * </pre>
     *
     * <code>string message = 4;</code>
     */
    public Builder setMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      message_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList messageParam_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureMessageParamIsMutable() {
      if (!((bitField0_ & 0x00000010) == 0x00000010)) {
        messageParam_ = new com.google.protobuf.LazyStringArrayList(messageParam_);
        bitField0_ |= 0x00000010;
       }
    }
    /**
     * <pre>
     *邮件内容中参数
     * </pre>
     *
     * <code>repeated string messageParam = 5;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getMessageParamList() {
      return messageParam_.getUnmodifiableView();
    }
    /**
     * <pre>
     *邮件内容中参数
     * </pre>
     *
     * <code>repeated string messageParam = 5;</code>
     */
    public int getMessageParamCount() {
      return messageParam_.size();
    }
    /**
     * <pre>
     *邮件内容中参数
     * </pre>
     *
     * <code>repeated string messageParam = 5;</code>
     */
    public java.lang.String getMessageParam(int index) {
      return messageParam_.get(index);
    }
    /**
     * <pre>
     *邮件内容中参数
     * </pre>
     *
     * <code>repeated string messageParam = 5;</code>
     */
    public com.google.protobuf.ByteString
        getMessageParamBytes(int index) {
      return messageParam_.getByteString(index);
    }
    /**
     * <pre>
     *邮件内容中参数
     * </pre>
     *
     * <code>repeated string messageParam = 5;</code>
     */
    public Builder setMessageParam(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureMessageParamIsMutable();
      messageParam_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件内容中参数
     * </pre>
     *
     * <code>repeated string messageParam = 5;</code>
     */
    public Builder addMessageParam(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureMessageParamIsMutable();
      messageParam_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件内容中参数
     * </pre>
     *
     * <code>repeated string messageParam = 5;</code>
     */
    public Builder addAllMessageParam(
        java.lang.Iterable<java.lang.String> values) {
      ensureMessageParamIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, messageParam_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件内容中参数
     * </pre>
     *
     * <code>repeated string messageParam = 5;</code>
     */
    public Builder clearMessageParam() {
      messageParam_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000010);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邮件内容中参数
     * </pre>
     *
     * <code>repeated string messageParam = 5;</code>
     */
    public Builder addMessageParamBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureMessageParamIsMutable();
      messageParam_.add(value);
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


    // @@protoc_insertion_point(builder_scope:pb4server.MailInfoVo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.MailInfoVo)
  private static final pb4server.MailInfoVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.MailInfoVo();
  }

  public static pb4server.MailInfoVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MailInfoVo>
      PARSER = new com.google.protobuf.AbstractParser<MailInfoVo>() {
    public MailInfoVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new MailInfoVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MailInfoVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MailInfoVo> getParserForType() {
    return PARSER;
  }

  public pb4server.MailInfoVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
