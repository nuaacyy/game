// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.MakeCityAskRt}
 */
public  final class MakeCityAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.MakeCityAskRt)
    MakeCityAskRtOrBuilder {
  // Use MakeCityAskRt.newBuilder() to construct.
  private MakeCityAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MakeCityAskRt() {
    rt_ = 0;
    castleId_ = 0L;
    playerName_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private MakeCityAskRt(
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
          case 16: {

            castleId_ = input.readInt64();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            playerName_ = s;
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
    return pb4server.InternalWkt.internal_static_pb4server_MakeCityAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_MakeCityAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.MakeCityAskRt.class, pb4server.MakeCityAskRt.Builder.class);
  }

  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int CASTLEID_FIELD_NUMBER = 2;
  private long castleId_;
  /**
   * <code>int64 castleId = 2;</code>
   */
  public long getCastleId() {
    return castleId_;
  }

  public static final int PLAYERNAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object playerName_;
  /**
   * <code>string playerName = 3;</code>
   */
  public java.lang.String getPlayerName() {
    java.lang.Object ref = playerName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      playerName_ = s;
      return s;
    }
  }
  /**
   * <code>string playerName = 3;</code>
   */
  public com.google.protobuf.ByteString
      getPlayerNameBytes() {
    java.lang.Object ref = playerName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      playerName_ = b;
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

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (rt_ != 0) {
      output.writeInt32(1, rt_);
    }
    if (castleId_ != 0L) {
      output.writeInt64(2, castleId_);
    }
    if (!getPlayerNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, playerName_);
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
    if (castleId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, castleId_);
    }
    if (!getPlayerNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, playerName_);
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
    if (!(obj instanceof pb4server.MakeCityAskRt)) {
      return super.equals(obj);
    }
    pb4server.MakeCityAskRt other = (pb4server.MakeCityAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && (getCastleId()
        == other.getCastleId());
    result = result && getPlayerName()
        .equals(other.getPlayerName());
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
    hash = (37 * hash) + CASTLEID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getCastleId());
    hash = (37 * hash) + PLAYERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getPlayerName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.MakeCityAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.MakeCityAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.MakeCityAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.MakeCityAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.MakeCityAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.MakeCityAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.MakeCityAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.MakeCityAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.MakeCityAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.MakeCityAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.MakeCityAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.MakeCityAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.MakeCityAskRt prototype) {
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
   * Protobuf type {@code pb4server.MakeCityAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.MakeCityAskRt)
      pb4server.MakeCityAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_MakeCityAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_MakeCityAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.MakeCityAskRt.class, pb4server.MakeCityAskRt.Builder.class);
    }

    // Construct using pb4server.MakeCityAskRt.newBuilder()
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
      rt_ = 0;

      castleId_ = 0L;

      playerName_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_MakeCityAskRt_descriptor;
    }

    public pb4server.MakeCityAskRt getDefaultInstanceForType() {
      return pb4server.MakeCityAskRt.getDefaultInstance();
    }

    public pb4server.MakeCityAskRt build() {
      pb4server.MakeCityAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.MakeCityAskRt buildPartial() {
      pb4server.MakeCityAskRt result = new pb4server.MakeCityAskRt(this);
      result.rt_ = rt_;
      result.castleId_ = castleId_;
      result.playerName_ = playerName_;
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
      if (other instanceof pb4server.MakeCityAskRt) {
        return mergeFrom((pb4server.MakeCityAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.MakeCityAskRt other) {
      if (other == pb4server.MakeCityAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (other.getCastleId() != 0L) {
        setCastleId(other.getCastleId());
      }
      if (!other.getPlayerName().isEmpty()) {
        playerName_ = other.playerName_;
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
      pb4server.MakeCityAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.MakeCityAskRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

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

    private long castleId_ ;
    /**
     * <code>int64 castleId = 2;</code>
     */
    public long getCastleId() {
      return castleId_;
    }
    /**
     * <code>int64 castleId = 2;</code>
     */
    public Builder setCastleId(long value) {
      
      castleId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 castleId = 2;</code>
     */
    public Builder clearCastleId() {
      
      castleId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object playerName_ = "";
    /**
     * <code>string playerName = 3;</code>
     */
    public java.lang.String getPlayerName() {
      java.lang.Object ref = playerName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        playerName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string playerName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getPlayerNameBytes() {
      java.lang.Object ref = playerName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        playerName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string playerName = 3;</code>
     */
    public Builder setPlayerName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      playerName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string playerName = 3;</code>
     */
    public Builder clearPlayerName() {
      
      playerName_ = getDefaultInstance().getPlayerName();
      onChanged();
      return this;
    }
    /**
     * <code>string playerName = 3;</code>
     */
    public Builder setPlayerNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      playerName_ = value;
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


    // @@protoc_insertion_point(builder_scope:pb4server.MakeCityAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.MakeCityAskRt)
  private static final pb4server.MakeCityAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.MakeCityAskRt();
  }

  public static pb4server.MakeCityAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MakeCityAskRt>
      PARSER = new com.google.protobuf.AbstractParser<MakeCityAskRt>() {
    public MakeCityAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new MakeCityAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MakeCityAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MakeCityAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.MakeCityAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

