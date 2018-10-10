// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3113
 * 爬塔进度到点刷新,客户端收到这个消息的时候不仅要修改怪物数据,还要把玩家当前的3个爬塔进度设置成0
 * </pre>
 *
 * Protobuf type {@code client2server.TowerRefresh}
 */
public  final class TowerRefresh extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.TowerRefresh)
    TowerRefreshOrBuilder {
  // Use TowerRefresh.newBuilder() to construct.
  private TowerRefresh(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TowerRefresh() {
    commonTowerInfo_ = "";
    eliteTowerInfo_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TowerRefresh(
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
            commonTowerInfo_ = bs;
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            eliteTowerInfo_ = bs;
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
    return pb4client.War2GamePkt.internal_static_client2server_TowerRefresh_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_TowerRefresh_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.TowerRefresh.class, pb4client.TowerRefresh.Builder.class);
  }

  private int bitField0_;
  public static final int COMMONTOWERINFO_FIELD_NUMBER = 1;
  private volatile java.lang.Object commonTowerInfo_;
  /**
   * <pre>
   *当前普通爬塔数据
   * </pre>
   *
   * <code>required string commonTowerInfo = 1;</code>
   */
  public boolean hasCommonTowerInfo() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *当前普通爬塔数据
   * </pre>
   *
   * <code>required string commonTowerInfo = 1;</code>
   */
  public java.lang.String getCommonTowerInfo() {
    java.lang.Object ref = commonTowerInfo_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        commonTowerInfo_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   *当前普通爬塔数据
   * </pre>
   *
   * <code>required string commonTowerInfo = 1;</code>
   */
  public com.google.protobuf.ByteString
      getCommonTowerInfoBytes() {
    java.lang.Object ref = commonTowerInfo_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      commonTowerInfo_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ELITETOWERINFO_FIELD_NUMBER = 2;
  private volatile java.lang.Object eliteTowerInfo_;
  /**
   * <pre>
   *当前精英爬塔数据
   * </pre>
   *
   * <code>required string eliteTowerInfo = 2;</code>
   */
  public boolean hasEliteTowerInfo() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *当前精英爬塔数据
   * </pre>
   *
   * <code>required string eliteTowerInfo = 2;</code>
   */
  public java.lang.String getEliteTowerInfo() {
    java.lang.Object ref = eliteTowerInfo_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        eliteTowerInfo_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   *当前精英爬塔数据
   * </pre>
   *
   * <code>required string eliteTowerInfo = 2;</code>
   */
  public com.google.protobuf.ByteString
      getEliteTowerInfoBytes() {
    java.lang.Object ref = eliteTowerInfo_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      eliteTowerInfo_ = b;
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

    if (!hasCommonTowerInfo()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasEliteTowerInfo()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, commonTowerInfo_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, eliteTowerInfo_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, commonTowerInfo_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, eliteTowerInfo_);
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
    if (!(obj instanceof pb4client.TowerRefresh)) {
      return super.equals(obj);
    }
    pb4client.TowerRefresh other = (pb4client.TowerRefresh) obj;

    boolean result = true;
    result = result && (hasCommonTowerInfo() == other.hasCommonTowerInfo());
    if (hasCommonTowerInfo()) {
      result = result && getCommonTowerInfo()
          .equals(other.getCommonTowerInfo());
    }
    result = result && (hasEliteTowerInfo() == other.hasEliteTowerInfo());
    if (hasEliteTowerInfo()) {
      result = result && getEliteTowerInfo()
          .equals(other.getEliteTowerInfo());
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
    if (hasCommonTowerInfo()) {
      hash = (37 * hash) + COMMONTOWERINFO_FIELD_NUMBER;
      hash = (53 * hash) + getCommonTowerInfo().hashCode();
    }
    if (hasEliteTowerInfo()) {
      hash = (37 * hash) + ELITETOWERINFO_FIELD_NUMBER;
      hash = (53 * hash) + getEliteTowerInfo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.TowerRefresh parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TowerRefresh parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TowerRefresh parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TowerRefresh parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TowerRefresh parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TowerRefresh parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TowerRefresh parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.TowerRefresh parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.TowerRefresh parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.TowerRefresh parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.TowerRefresh parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.TowerRefresh parseFrom(
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
  public static Builder newBuilder(pb4client.TowerRefresh prototype) {
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
   * msgType = 3113
   * 爬塔进度到点刷新,客户端收到这个消息的时候不仅要修改怪物数据,还要把玩家当前的3个爬塔进度设置成0
   * </pre>
   *
   * Protobuf type {@code client2server.TowerRefresh}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.TowerRefresh)
      pb4client.TowerRefreshOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_TowerRefresh_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_TowerRefresh_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.TowerRefresh.class, pb4client.TowerRefresh.Builder.class);
    }

    // Construct using pb4client.TowerRefresh.newBuilder()
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
      commonTowerInfo_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      eliteTowerInfo_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_TowerRefresh_descriptor;
    }

    public pb4client.TowerRefresh getDefaultInstanceForType() {
      return pb4client.TowerRefresh.getDefaultInstance();
    }

    public pb4client.TowerRefresh build() {
      pb4client.TowerRefresh result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.TowerRefresh buildPartial() {
      pb4client.TowerRefresh result = new pb4client.TowerRefresh(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.commonTowerInfo_ = commonTowerInfo_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.eliteTowerInfo_ = eliteTowerInfo_;
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
      if (other instanceof pb4client.TowerRefresh) {
        return mergeFrom((pb4client.TowerRefresh)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.TowerRefresh other) {
      if (other == pb4client.TowerRefresh.getDefaultInstance()) return this;
      if (other.hasCommonTowerInfo()) {
        bitField0_ |= 0x00000001;
        commonTowerInfo_ = other.commonTowerInfo_;
        onChanged();
      }
      if (other.hasEliteTowerInfo()) {
        bitField0_ |= 0x00000002;
        eliteTowerInfo_ = other.eliteTowerInfo_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasCommonTowerInfo()) {
        return false;
      }
      if (!hasEliteTowerInfo()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.TowerRefresh parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.TowerRefresh) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object commonTowerInfo_ = "";
    /**
     * <pre>
     *当前普通爬塔数据
     * </pre>
     *
     * <code>required string commonTowerInfo = 1;</code>
     */
    public boolean hasCommonTowerInfo() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *当前普通爬塔数据
     * </pre>
     *
     * <code>required string commonTowerInfo = 1;</code>
     */
    public java.lang.String getCommonTowerInfo() {
      java.lang.Object ref = commonTowerInfo_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          commonTowerInfo_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *当前普通爬塔数据
     * </pre>
     *
     * <code>required string commonTowerInfo = 1;</code>
     */
    public com.google.protobuf.ByteString
        getCommonTowerInfoBytes() {
      java.lang.Object ref = commonTowerInfo_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        commonTowerInfo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *当前普通爬塔数据
     * </pre>
     *
     * <code>required string commonTowerInfo = 1;</code>
     */
    public Builder setCommonTowerInfo(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      commonTowerInfo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前普通爬塔数据
     * </pre>
     *
     * <code>required string commonTowerInfo = 1;</code>
     */
    public Builder clearCommonTowerInfo() {
      bitField0_ = (bitField0_ & ~0x00000001);
      commonTowerInfo_ = getDefaultInstance().getCommonTowerInfo();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前普通爬塔数据
     * </pre>
     *
     * <code>required string commonTowerInfo = 1;</code>
     */
    public Builder setCommonTowerInfoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      commonTowerInfo_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object eliteTowerInfo_ = "";
    /**
     * <pre>
     *当前精英爬塔数据
     * </pre>
     *
     * <code>required string eliteTowerInfo = 2;</code>
     */
    public boolean hasEliteTowerInfo() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *当前精英爬塔数据
     * </pre>
     *
     * <code>required string eliteTowerInfo = 2;</code>
     */
    public java.lang.String getEliteTowerInfo() {
      java.lang.Object ref = eliteTowerInfo_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          eliteTowerInfo_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *当前精英爬塔数据
     * </pre>
     *
     * <code>required string eliteTowerInfo = 2;</code>
     */
    public com.google.protobuf.ByteString
        getEliteTowerInfoBytes() {
      java.lang.Object ref = eliteTowerInfo_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        eliteTowerInfo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *当前精英爬塔数据
     * </pre>
     *
     * <code>required string eliteTowerInfo = 2;</code>
     */
    public Builder setEliteTowerInfo(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      eliteTowerInfo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前精英爬塔数据
     * </pre>
     *
     * <code>required string eliteTowerInfo = 2;</code>
     */
    public Builder clearEliteTowerInfo() {
      bitField0_ = (bitField0_ & ~0x00000002);
      eliteTowerInfo_ = getDefaultInstance().getEliteTowerInfo();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前精英爬塔数据
     * </pre>
     *
     * <code>required string eliteTowerInfo = 2;</code>
     */
    public Builder setEliteTowerInfoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      eliteTowerInfo_ = value;
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


    // @@protoc_insertion_point(builder_scope:client2server.TowerRefresh)
  }

  // @@protoc_insertion_point(class_scope:client2server.TowerRefresh)
  private static final pb4client.TowerRefresh DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.TowerRefresh();
  }

  public static pb4client.TowerRefresh getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<TowerRefresh>
      PARSER = new com.google.protobuf.AbstractParser<TowerRefresh>() {
    public TowerRefresh parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TowerRefresh(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TowerRefresh> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TowerRefresh> getParserForType() {
    return PARSER;
  }

  public pb4client.TowerRefresh getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
