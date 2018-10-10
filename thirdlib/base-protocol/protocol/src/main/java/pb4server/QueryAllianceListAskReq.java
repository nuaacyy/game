// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * <pre>
 * 查询可申请联盟列表
 * </pre>
 *
 * Protobuf type {@code pb4server.QueryAllianceListAskReq}
 */
public  final class QueryAllianceListAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.QueryAllianceListAskReq)
    QueryAllianceListAskReqOrBuilder {
  // Use QueryAllianceListAskReq.newBuilder() to construct.
  private QueryAllianceListAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryAllianceListAskReq() {
    playerMapAreaNo_ = 0;
    allianceName_ = "";
    allianceLan_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private QueryAllianceListAskReq(
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

            playerMapAreaNo_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            allianceName_ = s;
            break;
          }
          case 24: {

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
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceListAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceListAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.QueryAllianceListAskReq.class, pb4server.QueryAllianceListAskReq.Builder.class);
  }

  public static final int PLAYERMAPAREANO_FIELD_NUMBER = 1;
  private int playerMapAreaNo_;
  /**
   * <pre>
   * 查看者所属地图服ID
   * </pre>
   *
   * <code>int32 playerMapAreaNo = 1;</code>
   */
  public int getPlayerMapAreaNo() {
    return playerMapAreaNo_;
  }

  public static final int ALLIANCENAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object allianceName_;
  /**
   * <pre>
   * 联盟名字或者简称
   * </pre>
   *
   * <code>string allianceName = 2;</code>
   */
  public java.lang.String getAllianceName() {
    java.lang.Object ref = allianceName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      allianceName_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 联盟名字或者简称
   * </pre>
   *
   * <code>string allianceName = 2;</code>
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

  public static final int ALLIANCELAN_FIELD_NUMBER = 3;
  private int allianceLan_;
  /**
   * <pre>
   * 所选语言
   * </pre>
   *
   * <code>int32 allianceLan = 3;</code>
   */
  public int getAllianceLan() {
    return allianceLan_;
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
    if (playerMapAreaNo_ != 0) {
      output.writeInt32(1, playerMapAreaNo_);
    }
    if (!getAllianceNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, allianceName_);
    }
    if (allianceLan_ != 0) {
      output.writeInt32(3, allianceLan_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (playerMapAreaNo_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, playerMapAreaNo_);
    }
    if (!getAllianceNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, allianceName_);
    }
    if (allianceLan_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, allianceLan_);
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
    if (!(obj instanceof pb4server.QueryAllianceListAskReq)) {
      return super.equals(obj);
    }
    pb4server.QueryAllianceListAskReq other = (pb4server.QueryAllianceListAskReq) obj;

    boolean result = true;
    result = result && (getPlayerMapAreaNo()
        == other.getPlayerMapAreaNo());
    result = result && getAllianceName()
        .equals(other.getAllianceName());
    result = result && (getAllianceLan()
        == other.getAllianceLan());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PLAYERMAPAREANO_FIELD_NUMBER;
    hash = (53 * hash) + getPlayerMapAreaNo();
    hash = (37 * hash) + ALLIANCENAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceName().hashCode();
    hash = (37 * hash) + ALLIANCELAN_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceLan();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.QueryAllianceListAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryAllianceListAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryAllianceListAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryAllianceListAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryAllianceListAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryAllianceListAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryAllianceListAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryAllianceListAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryAllianceListAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.QueryAllianceListAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryAllianceListAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryAllianceListAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.QueryAllianceListAskReq prototype) {
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
   * 查询可申请联盟列表
   * </pre>
   *
   * Protobuf type {@code pb4server.QueryAllianceListAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.QueryAllianceListAskReq)
      pb4server.QueryAllianceListAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceListAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceListAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.QueryAllianceListAskReq.class, pb4server.QueryAllianceListAskReq.Builder.class);
    }

    // Construct using pb4server.QueryAllianceListAskReq.newBuilder()
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
      playerMapAreaNo_ = 0;

      allianceName_ = "";

      allianceLan_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_QueryAllianceListAskReq_descriptor;
    }

    public pb4server.QueryAllianceListAskReq getDefaultInstanceForType() {
      return pb4server.QueryAllianceListAskReq.getDefaultInstance();
    }

    public pb4server.QueryAllianceListAskReq build() {
      pb4server.QueryAllianceListAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.QueryAllianceListAskReq buildPartial() {
      pb4server.QueryAllianceListAskReq result = new pb4server.QueryAllianceListAskReq(this);
      result.playerMapAreaNo_ = playerMapAreaNo_;
      result.allianceName_ = allianceName_;
      result.allianceLan_ = allianceLan_;
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
      if (other instanceof pb4server.QueryAllianceListAskReq) {
        return mergeFrom((pb4server.QueryAllianceListAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.QueryAllianceListAskReq other) {
      if (other == pb4server.QueryAllianceListAskReq.getDefaultInstance()) return this;
      if (other.getPlayerMapAreaNo() != 0) {
        setPlayerMapAreaNo(other.getPlayerMapAreaNo());
      }
      if (!other.getAllianceName().isEmpty()) {
        allianceName_ = other.allianceName_;
        onChanged();
      }
      if (other.getAllianceLan() != 0) {
        setAllianceLan(other.getAllianceLan());
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
      pb4server.QueryAllianceListAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.QueryAllianceListAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int playerMapAreaNo_ ;
    /**
     * <pre>
     * 查看者所属地图服ID
     * </pre>
     *
     * <code>int32 playerMapAreaNo = 1;</code>
     */
    public int getPlayerMapAreaNo() {
      return playerMapAreaNo_;
    }
    /**
     * <pre>
     * 查看者所属地图服ID
     * </pre>
     *
     * <code>int32 playerMapAreaNo = 1;</code>
     */
    public Builder setPlayerMapAreaNo(int value) {
      
      playerMapAreaNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 查看者所属地图服ID
     * </pre>
     *
     * <code>int32 playerMapAreaNo = 1;</code>
     */
    public Builder clearPlayerMapAreaNo() {
      
      playerMapAreaNo_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object allianceName_ = "";
    /**
     * <pre>
     * 联盟名字或者简称
     * </pre>
     *
     * <code>string allianceName = 2;</code>
     */
    public java.lang.String getAllianceName() {
      java.lang.Object ref = allianceName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        allianceName_ = s;
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
     * <code>string allianceName = 2;</code>
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
     * <code>string allianceName = 2;</code>
     */
    public Builder setAllianceName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      allianceName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟名字或者简称
     * </pre>
     *
     * <code>string allianceName = 2;</code>
     */
    public Builder clearAllianceName() {
      
      allianceName_ = getDefaultInstance().getAllianceName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟名字或者简称
     * </pre>
     *
     * <code>string allianceName = 2;</code>
     */
    public Builder setAllianceNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
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
     * <code>int32 allianceLan = 3;</code>
     */
    public int getAllianceLan() {
      return allianceLan_;
    }
    /**
     * <pre>
     * 所选语言
     * </pre>
     *
     * <code>int32 allianceLan = 3;</code>
     */
    public Builder setAllianceLan(int value) {
      
      allianceLan_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 所选语言
     * </pre>
     *
     * <code>int32 allianceLan = 3;</code>
     */
    public Builder clearAllianceLan() {
      
      allianceLan_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.QueryAllianceListAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.QueryAllianceListAskReq)
  private static final pb4server.QueryAllianceListAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.QueryAllianceListAskReq();
  }

  public static pb4server.QueryAllianceListAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueryAllianceListAskReq>
      PARSER = new com.google.protobuf.AbstractParser<QueryAllianceListAskReq>() {
    public QueryAllianceListAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryAllianceListAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryAllianceListAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryAllianceListAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.QueryAllianceListAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

