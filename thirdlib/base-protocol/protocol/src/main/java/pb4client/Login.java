// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 1
 * 客户端 -&gt; 服务器
 * 发送登录消息，包括账号和密码
 * </pre>
 *
 * Protobuf type {@code client2server.Login}
 */
public  final class Login extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.Login)
    LoginOrBuilder {
  // Use Login.newBuilder() to construct.
  private Login(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Login() {
    account_ = "";
    pwd_ = "";
    sid_ = "";
    loginType_ = 0;
    token_ = "";
    device_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Login(
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
            account_ = bs;
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            pwd_ = bs;
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            sid_ = bs;
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            loginType_ = input.readInt32();
            break;
          }
          case 42: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000010;
            token_ = bs;
            break;
          }
          case 50: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000020;
            device_ = bs;
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_Login_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_Login_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.Login.class, pb4client.Login.Builder.class);
  }

  private int bitField0_;
  public static final int ACCOUNT_FIELD_NUMBER = 1;
  private volatile java.lang.Object account_;
  /**
   * <code>required string account = 1;</code>
   */
  public boolean hasAccount() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required string account = 1;</code>
   */
  public java.lang.String getAccount() {
    java.lang.Object ref = account_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        account_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string account = 1;</code>
   */
  public com.google.protobuf.ByteString
      getAccountBytes() {
    java.lang.Object ref = account_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      account_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PWD_FIELD_NUMBER = 2;
  private volatile java.lang.Object pwd_;
  /**
   * <code>required string pwd = 2;</code>
   */
  public boolean hasPwd() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required string pwd = 2;</code>
   */
  public java.lang.String getPwd() {
    java.lang.Object ref = pwd_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        pwd_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string pwd = 2;</code>
   */
  public com.google.protobuf.ByteString
      getPwdBytes() {
    java.lang.Object ref = pwd_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      pwd_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SID_FIELD_NUMBER = 3;
  private volatile java.lang.Object sid_;
  /**
   * <pre>
   * 服务器id
   * </pre>
   *
   * <code>required string sid = 3;</code>
   */
  public boolean hasSid() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 服务器id
   * </pre>
   *
   * <code>required string sid = 3;</code>
   */
  public java.lang.String getSid() {
    java.lang.Object ref = sid_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        sid_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 服务器id
   * </pre>
   *
   * <code>required string sid = 3;</code>
   */
  public com.google.protobuf.ByteString
      getSidBytes() {
    java.lang.Object ref = sid_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      sid_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int LOGINTYPE_FIELD_NUMBER = 4;
  private int loginType_;
  /**
   * <pre>
   *登录类型 1-账号密码  2-令牌
   * </pre>
   *
   * <code>required int32 loginType = 4;</code>
   */
  public boolean hasLoginType() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *登录类型 1-账号密码  2-令牌
   * </pre>
   *
   * <code>required int32 loginType = 4;</code>
   */
  public int getLoginType() {
    return loginType_;
  }

  public static final int TOKEN_FIELD_NUMBER = 5;
  private volatile java.lang.Object token_;
  /**
   * <pre>
   *登录令牌
   * </pre>
   *
   * <code>required string token = 5;</code>
   */
  public boolean hasToken() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   *登录令牌
   * </pre>
   *
   * <code>required string token = 5;</code>
   */
  public java.lang.String getToken() {
    java.lang.Object ref = token_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        token_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   *登录令牌
   * </pre>
   *
   * <code>required string token = 5;</code>
   */
  public com.google.protobuf.ByteString
      getTokenBytes() {
    java.lang.Object ref = token_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      token_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DEVICE_FIELD_NUMBER = 6;
  private volatile java.lang.Object device_;
  /**
   * <pre>
   *设备号	设备号，PC、手游需要发送客户端设备ID，统一使用公司标准，页游可以根据需要发送相应值，比如浏览器信息
   * </pre>
   *
   * <code>required string device = 6;</code>
   */
  public boolean hasDevice() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   *设备号	设备号，PC、手游需要发送客户端设备ID，统一使用公司标准，页游可以根据需要发送相应值，比如浏览器信息
   * </pre>
   *
   * <code>required string device = 6;</code>
   */
  public java.lang.String getDevice() {
    java.lang.Object ref = device_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        device_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   *设备号	设备号，PC、手游需要发送客户端设备ID，统一使用公司标准，页游可以根据需要发送相应值，比如浏览器信息
   * </pre>
   *
   * <code>required string device = 6;</code>
   */
  public com.google.protobuf.ByteString
      getDeviceBytes() {
    java.lang.Object ref = device_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      device_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasAccount()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPwd()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasSid()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasLoginType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasToken()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasDevice()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, account_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, pwd_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, sid_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, loginType_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, token_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, device_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, account_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, pwd_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, sid_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, loginType_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, token_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, device_);
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
    if (!(obj instanceof pb4client.Login)) {
      return super.equals(obj);
    }
    pb4client.Login other = (pb4client.Login) obj;

    boolean result = true;
    result = result && (hasAccount() == other.hasAccount());
    if (hasAccount()) {
      result = result && getAccount()
          .equals(other.getAccount());
    }
    result = result && (hasPwd() == other.hasPwd());
    if (hasPwd()) {
      result = result && getPwd()
          .equals(other.getPwd());
    }
    result = result && (hasSid() == other.hasSid());
    if (hasSid()) {
      result = result && getSid()
          .equals(other.getSid());
    }
    result = result && (hasLoginType() == other.hasLoginType());
    if (hasLoginType()) {
      result = result && (getLoginType()
          == other.getLoginType());
    }
    result = result && (hasToken() == other.hasToken());
    if (hasToken()) {
      result = result && getToken()
          .equals(other.getToken());
    }
    result = result && (hasDevice() == other.hasDevice());
    if (hasDevice()) {
      result = result && getDevice()
          .equals(other.getDevice());
    }
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
    if (hasAccount()) {
      hash = (37 * hash) + ACCOUNT_FIELD_NUMBER;
      hash = (53 * hash) + getAccount().hashCode();
    }
    if (hasPwd()) {
      hash = (37 * hash) + PWD_FIELD_NUMBER;
      hash = (53 * hash) + getPwd().hashCode();
    }
    if (hasSid()) {
      hash = (37 * hash) + SID_FIELD_NUMBER;
      hash = (53 * hash) + getSid().hashCode();
    }
    if (hasLoginType()) {
      hash = (37 * hash) + LOGINTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getLoginType();
    }
    if (hasToken()) {
      hash = (37 * hash) + TOKEN_FIELD_NUMBER;
      hash = (53 * hash) + getToken().hashCode();
    }
    if (hasDevice()) {
      hash = (37 * hash) + DEVICE_FIELD_NUMBER;
      hash = (53 * hash) + getDevice().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.Login parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.Login parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.Login parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.Login parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.Login parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.Login parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.Login parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.Login parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.Login parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.Login parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.Login parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.Login parseFrom(
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
  public static Builder newBuilder(pb4client.Login prototype) {
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
   * msgType = 1
   * 客户端 -&gt; 服务器
   * 发送登录消息，包括账号和密码
   * </pre>
   *
   * Protobuf type {@code client2server.Login}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.Login)
      pb4client.LoginOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_Login_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_Login_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.Login.class, pb4client.Login.Builder.class);
    }

    // Construct using pb4client.Login.newBuilder()
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
      account_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      pwd_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      sid_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      loginType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      token_ = "";
      bitField0_ = (bitField0_ & ~0x00000010);
      device_ = "";
      bitField0_ = (bitField0_ & ~0x00000020);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_Login_descriptor;
    }

    public pb4client.Login getDefaultInstanceForType() {
      return pb4client.Login.getDefaultInstance();
    }

    public pb4client.Login build() {
      pb4client.Login result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.Login buildPartial() {
      pb4client.Login result = new pb4client.Login(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.account_ = account_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.pwd_ = pwd_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.sid_ = sid_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.loginType_ = loginType_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.token_ = token_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.device_ = device_;
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
      if (other instanceof pb4client.Login) {
        return mergeFrom((pb4client.Login)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.Login other) {
      if (other == pb4client.Login.getDefaultInstance()) return this;
      if (other.hasAccount()) {
        bitField0_ |= 0x00000001;
        account_ = other.account_;
        onChanged();
      }
      if (other.hasPwd()) {
        bitField0_ |= 0x00000002;
        pwd_ = other.pwd_;
        onChanged();
      }
      if (other.hasSid()) {
        bitField0_ |= 0x00000004;
        sid_ = other.sid_;
        onChanged();
      }
      if (other.hasLoginType()) {
        setLoginType(other.getLoginType());
      }
      if (other.hasToken()) {
        bitField0_ |= 0x00000010;
        token_ = other.token_;
        onChanged();
      }
      if (other.hasDevice()) {
        bitField0_ |= 0x00000020;
        device_ = other.device_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasAccount()) {
        return false;
      }
      if (!hasPwd()) {
        return false;
      }
      if (!hasSid()) {
        return false;
      }
      if (!hasLoginType()) {
        return false;
      }
      if (!hasToken()) {
        return false;
      }
      if (!hasDevice()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.Login parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.Login) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object account_ = "";
    /**
     * <code>required string account = 1;</code>
     */
    public boolean hasAccount() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string account = 1;</code>
     */
    public java.lang.String getAccount() {
      java.lang.Object ref = account_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          account_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string account = 1;</code>
     */
    public com.google.protobuf.ByteString
        getAccountBytes() {
      java.lang.Object ref = account_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        account_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string account = 1;</code>
     */
    public Builder setAccount(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      account_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string account = 1;</code>
     */
    public Builder clearAccount() {
      bitField0_ = (bitField0_ & ~0x00000001);
      account_ = getDefaultInstance().getAccount();
      onChanged();
      return this;
    }
    /**
     * <code>required string account = 1;</code>
     */
    public Builder setAccountBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      account_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object pwd_ = "";
    /**
     * <code>required string pwd = 2;</code>
     */
    public boolean hasPwd() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string pwd = 2;</code>
     */
    public java.lang.String getPwd() {
      java.lang.Object ref = pwd_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          pwd_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string pwd = 2;</code>
     */
    public com.google.protobuf.ByteString
        getPwdBytes() {
      java.lang.Object ref = pwd_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        pwd_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string pwd = 2;</code>
     */
    public Builder setPwd(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      pwd_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string pwd = 2;</code>
     */
    public Builder clearPwd() {
      bitField0_ = (bitField0_ & ~0x00000002);
      pwd_ = getDefaultInstance().getPwd();
      onChanged();
      return this;
    }
    /**
     * <code>required string pwd = 2;</code>
     */
    public Builder setPwdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      pwd_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object sid_ = "";
    /**
     * <pre>
     * 服务器id
     * </pre>
     *
     * <code>required string sid = 3;</code>
     */
    public boolean hasSid() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 服务器id
     * </pre>
     *
     * <code>required string sid = 3;</code>
     */
    public java.lang.String getSid() {
      java.lang.Object ref = sid_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          sid_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 服务器id
     * </pre>
     *
     * <code>required string sid = 3;</code>
     */
    public com.google.protobuf.ByteString
        getSidBytes() {
      java.lang.Object ref = sid_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        sid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 服务器id
     * </pre>
     *
     * <code>required string sid = 3;</code>
     */
    public Builder setSid(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      sid_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 服务器id
     * </pre>
     *
     * <code>required string sid = 3;</code>
     */
    public Builder clearSid() {
      bitField0_ = (bitField0_ & ~0x00000004);
      sid_ = getDefaultInstance().getSid();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 服务器id
     * </pre>
     *
     * <code>required string sid = 3;</code>
     */
    public Builder setSidBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      sid_ = value;
      onChanged();
      return this;
    }

    private int loginType_ ;
    /**
     * <pre>
     *登录类型 1-账号密码  2-令牌
     * </pre>
     *
     * <code>required int32 loginType = 4;</code>
     */
    public boolean hasLoginType() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *登录类型 1-账号密码  2-令牌
     * </pre>
     *
     * <code>required int32 loginType = 4;</code>
     */
    public int getLoginType() {
      return loginType_;
    }
    /**
     * <pre>
     *登录类型 1-账号密码  2-令牌
     * </pre>
     *
     * <code>required int32 loginType = 4;</code>
     */
    public Builder setLoginType(int value) {
      bitField0_ |= 0x00000008;
      loginType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *登录类型 1-账号密码  2-令牌
     * </pre>
     *
     * <code>required int32 loginType = 4;</code>
     */
    public Builder clearLoginType() {
      bitField0_ = (bitField0_ & ~0x00000008);
      loginType_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object token_ = "";
    /**
     * <pre>
     *登录令牌
     * </pre>
     *
     * <code>required string token = 5;</code>
     */
    public boolean hasToken() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *登录令牌
     * </pre>
     *
     * <code>required string token = 5;</code>
     */
    public java.lang.String getToken() {
      java.lang.Object ref = token_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          token_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *登录令牌
     * </pre>
     *
     * <code>required string token = 5;</code>
     */
    public com.google.protobuf.ByteString
        getTokenBytes() {
      java.lang.Object ref = token_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        token_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *登录令牌
     * </pre>
     *
     * <code>required string token = 5;</code>
     */
    public Builder setToken(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      token_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *登录令牌
     * </pre>
     *
     * <code>required string token = 5;</code>
     */
    public Builder clearToken() {
      bitField0_ = (bitField0_ & ~0x00000010);
      token_ = getDefaultInstance().getToken();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *登录令牌
     * </pre>
     *
     * <code>required string token = 5;</code>
     */
    public Builder setTokenBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      token_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object device_ = "";
    /**
     * <pre>
     *设备号	设备号，PC、手游需要发送客户端设备ID，统一使用公司标准，页游可以根据需要发送相应值，比如浏览器信息
     * </pre>
     *
     * <code>required string device = 6;</code>
     */
    public boolean hasDevice() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     *设备号	设备号，PC、手游需要发送客户端设备ID，统一使用公司标准，页游可以根据需要发送相应值，比如浏览器信息
     * </pre>
     *
     * <code>required string device = 6;</code>
     */
    public java.lang.String getDevice() {
      java.lang.Object ref = device_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          device_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *设备号	设备号，PC、手游需要发送客户端设备ID，统一使用公司标准，页游可以根据需要发送相应值，比如浏览器信息
     * </pre>
     *
     * <code>required string device = 6;</code>
     */
    public com.google.protobuf.ByteString
        getDeviceBytes() {
      java.lang.Object ref = device_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        device_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *设备号	设备号，PC、手游需要发送客户端设备ID，统一使用公司标准，页游可以根据需要发送相应值，比如浏览器信息
     * </pre>
     *
     * <code>required string device = 6;</code>
     */
    public Builder setDevice(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
      device_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *设备号	设备号，PC、手游需要发送客户端设备ID，统一使用公司标准，页游可以根据需要发送相应值，比如浏览器信息
     * </pre>
     *
     * <code>required string device = 6;</code>
     */
    public Builder clearDevice() {
      bitField0_ = (bitField0_ & ~0x00000020);
      device_ = getDefaultInstance().getDevice();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *设备号	设备号，PC、手游需要发送客户端设备ID，统一使用公司标准，页游可以根据需要发送相应值，比如浏览器信息
     * </pre>
     *
     * <code>required string device = 6;</code>
     */
    public Builder setDeviceBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
      device_ = value;
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


    // @@protoc_insertion_point(builder_scope:client2server.Login)
  }

  // @@protoc_insertion_point(class_scope:client2server.Login)
  private static final pb4client.Login DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.Login();
  }

  public static pb4client.Login getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<Login>
      PARSER = new com.google.protobuf.AbstractParser<Login>() {
    public Login parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new Login(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Login> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Login> getParserForType() {
    return PARSER;
  }

  public pb4client.Login getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

