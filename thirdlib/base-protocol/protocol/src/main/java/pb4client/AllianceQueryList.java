// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 808
 * 客户端 -&gt; 服务器
 * 查询可加入联盟列表
 * </pre>
 *
 * Protobuf type {@code client2server.AllianceQueryList}
 */
public  final class AllianceQueryList extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AllianceQueryList)
    AllianceQueryListOrBuilder {
  // Use AllianceQueryList.newBuilder() to construct.
  private AllianceQueryList(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceQueryList() {
    allianceName_ = "";
    allianceLan_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllianceQueryList(
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
            allianceName_ = bs;
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            allianceLan_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryList_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryList_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AllianceQueryList.class, pb4client.AllianceQueryList.Builder.class);
  }

  private int bitField0_;
  public static final int ALLIANCENAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object allianceName_;
  /**
   * <pre>
   * 联盟名字或者简称
   * </pre>
   *
   * <code>required string allianceName = 1;</code>
   */
  public boolean hasAllianceName() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 联盟名字或者简称
   * </pre>
   *
   * <code>required string allianceName = 1;</code>
   */
  public java.lang.String getAllianceName() {
    java.lang.Object ref = allianceName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        allianceName_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 联盟名字或者简称
   * </pre>
   *
   * <code>required string allianceName = 1;</code>
   */
  public com.google.protobuf.ByteString
      getAllianceNameBytes() {
    java.lang.Object ref = allianceName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      allianceName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ALLIANCELAN_FIELD_NUMBER = 2;
  private int allianceLan_;
  /**
   * <pre>
   * 所选语言
   * </pre>
   *
   * <code>required int32 allianceLan = 2;</code>
   */
  public boolean hasAllianceLan() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 所选语言
   * </pre>
   *
   * <code>required int32 allianceLan = 2;</code>
   */
  public int getAllianceLan() {
    return allianceLan_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasAllianceName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAllianceLan()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, allianceName_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, allianceLan_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, allianceName_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, allianceLan_);
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
    if (!(obj instanceof pb4client.AllianceQueryList)) {
      return super.equals(obj);
    }
    pb4client.AllianceQueryList other = (pb4client.AllianceQueryList) obj;

    boolean result = true;
    result = result && (hasAllianceName() == other.hasAllianceName());
    if (hasAllianceName()) {
      result = result && getAllianceName()
          .equals(other.getAllianceName());
    }
    result = result && (hasAllianceLan() == other.hasAllianceLan());
    if (hasAllianceLan()) {
      result = result && (getAllianceLan()
          == other.getAllianceLan());
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
    if (hasAllianceName()) {
      hash = (37 * hash) + ALLIANCENAME_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceName().hashCode();
    }
    if (hasAllianceLan()) {
      hash = (37 * hash) + ALLIANCELAN_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceLan();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AllianceQueryList parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceQueryList parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceQueryList parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceQueryList parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceQueryList parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceQueryList parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceQueryList parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceQueryList parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceQueryList parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AllianceQueryList parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceQueryList parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceQueryList parseFrom(
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
  public static Builder newBuilder(pb4client.AllianceQueryList prototype) {
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
   * msgType = 808
   * 客户端 -&gt; 服务器
   * 查询可加入联盟列表
   * </pre>
   *
   * Protobuf type {@code client2server.AllianceQueryList}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AllianceQueryList)
      pb4client.AllianceQueryListOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryList_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryList_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AllianceQueryList.class, pb4client.AllianceQueryList.Builder.class);
    }

    // Construct using pb4client.AllianceQueryList.newBuilder()
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
      allianceName_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      allianceLan_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceQueryList_descriptor;
    }

    public pb4client.AllianceQueryList getDefaultInstanceForType() {
      return pb4client.AllianceQueryList.getDefaultInstance();
    }

    public pb4client.AllianceQueryList build() {
      pb4client.AllianceQueryList result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AllianceQueryList buildPartial() {
      pb4client.AllianceQueryList result = new pb4client.AllianceQueryList(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.allianceName_ = allianceName_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.allianceLan_ = allianceLan_;
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
      if (other instanceof pb4client.AllianceQueryList) {
        return mergeFrom((pb4client.AllianceQueryList)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AllianceQueryList other) {
      if (other == pb4client.AllianceQueryList.getDefaultInstance()) return this;
      if (other.hasAllianceName()) {
        bitField0_ |= 0x00000001;
        allianceName_ = other.allianceName_;
        onChanged();
      }
      if (other.hasAllianceLan()) {
        setAllianceLan(other.getAllianceLan());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasAllianceName()) {
        return false;
      }
      if (!hasAllianceLan()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AllianceQueryList parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AllianceQueryList) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object allianceName_ = "";
    /**
     * <pre>
     * 联盟名字或者简称
     * </pre>
     *
     * <code>required string allianceName = 1;</code>
     */
    public boolean hasAllianceName() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 联盟名字或者简称
     * </pre>
     *
     * <code>required string allianceName = 1;</code>
     */
    public java.lang.String getAllianceName() {
      java.lang.Object ref = allianceName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          allianceName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 联盟名字或者简称
     * </pre>
     *
     * <code>required string allianceName = 1;</code>
     */
    public com.google.protobuf.ByteString
        getAllianceNameBytes() {
      java.lang.Object ref = allianceName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        allianceName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 联盟名字或者简称
     * </pre>
     *
     * <code>required string allianceName = 1;</code>
     */
    public Builder setAllianceName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      allianceName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟名字或者简称
     * </pre>
     *
     * <code>required string allianceName = 1;</code>
     */
    public Builder clearAllianceName() {
      bitField0_ = (bitField0_ & ~0x00000001);
      allianceName_ = getDefaultInstance().getAllianceName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟名字或者简称
     * </pre>
     *
     * <code>required string allianceName = 1;</code>
     */
    public Builder setAllianceNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      allianceName_ = value;
      onChanged();
      return this;
    }

    private int allianceLan_ ;
    /**
     * <pre>
     * 所选语言
     * </pre>
     *
     * <code>required int32 allianceLan = 2;</code>
     */
    public boolean hasAllianceLan() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 所选语言
     * </pre>
     *
     * <code>required int32 allianceLan = 2;</code>
     */
    public int getAllianceLan() {
      return allianceLan_;
    }
    /**
     * <pre>
     * 所选语言
     * </pre>
     *
     * <code>required int32 allianceLan = 2;</code>
     */
    public Builder setAllianceLan(int value) {
      bitField0_ |= 0x00000002;
      allianceLan_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 所选语言
     * </pre>
     *
     * <code>required int32 allianceLan = 2;</code>
     */
    public Builder clearAllianceLan() {
      bitField0_ = (bitField0_ & ~0x00000002);
      allianceLan_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.AllianceQueryList)
  }

  // @@protoc_insertion_point(class_scope:client2server.AllianceQueryList)
  private static final pb4client.AllianceQueryList DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AllianceQueryList();
  }

  public static pb4client.AllianceQueryList getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AllianceQueryList>
      PARSER = new com.google.protobuf.AbstractParser<AllianceQueryList>() {
    public AllianceQueryList parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceQueryList(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceQueryList> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceQueryList> getParserForType() {
    return PARSER;
  }

  public pb4client.AllianceQueryList getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

