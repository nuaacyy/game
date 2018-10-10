// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.AllianceActivityRankAskVo}
 */
public  final class AllianceActivityRankAskVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AllianceActivityRankAskVo)
    AllianceActivityRankAskVoOrBuilder {
  // Use AllianceActivityRankAskVo.newBuilder() to construct.
  private AllianceActivityRankAskVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceActivityRankAskVo() {
    allianceId_ = 0L;
    allianceName_ = "";
    shortName_ = "";
    myScore_ = 0;
    flagColor_ = 0;
    flagStyle_ = 0;
    flagEffect_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllianceActivityRankAskVo(
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

            allianceId_ = input.readInt64();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            allianceName_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            shortName_ = s;
            break;
          }
          case 32: {

            myScore_ = input.readInt32();
            break;
          }
          case 40: {

            flagColor_ = input.readInt32();
            break;
          }
          case 48: {

            flagStyle_ = input.readInt32();
            break;
          }
          case 56: {

            flagEffect_ = input.readInt32();
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
    return pb4server.InternalPkt.internal_static_pb4server_AllianceActivityRankAskVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_AllianceActivityRankAskVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AllianceActivityRankAskVo.class, pb4server.AllianceActivityRankAskVo.Builder.class);
  }

  public static final int ALLIANCEID_FIELD_NUMBER = 1;
  private long allianceId_;
  /**
   * <code>int64 allianceId = 1;</code>
   */
  public long getAllianceId() {
    return allianceId_;
  }

  public static final int ALLIANCENAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object allianceName_;
  /**
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

  public static final int SHORTNAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object shortName_;
  /**
   * <code>string shortName = 3;</code>
   */
  public java.lang.String getShortName() {
    java.lang.Object ref = shortName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      shortName_ = s;
      return s;
    }
  }
  /**
   * <code>string shortName = 3;</code>
   */
  public com.google.protobuf.ByteString
      getShortNameBytes() {
    java.lang.Object ref = shortName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      shortName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MYSCORE_FIELD_NUMBER = 4;
  private int myScore_;
  /**
   * <code>int32 myScore = 4;</code>
   */
  public int getMyScore() {
    return myScore_;
  }

  public static final int FLAGCOLOR_FIELD_NUMBER = 5;
  private int flagColor_;
  /**
   * <code>int32 flagColor = 5;</code>
   */
  public int getFlagColor() {
    return flagColor_;
  }

  public static final int FLAGSTYLE_FIELD_NUMBER = 6;
  private int flagStyle_;
  /**
   * <code>int32 flagStyle = 6;</code>
   */
  public int getFlagStyle() {
    return flagStyle_;
  }

  public static final int FLAGEFFECT_FIELD_NUMBER = 7;
  private int flagEffect_;
  /**
   * <code>int32 flagEffect = 7;</code>
   */
  public int getFlagEffect() {
    return flagEffect_;
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
    if (allianceId_ != 0L) {
      output.writeInt64(1, allianceId_);
    }
    if (!getAllianceNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, allianceName_);
    }
    if (!getShortNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, shortName_);
    }
    if (myScore_ != 0) {
      output.writeInt32(4, myScore_);
    }
    if (flagColor_ != 0) {
      output.writeInt32(5, flagColor_);
    }
    if (flagStyle_ != 0) {
      output.writeInt32(6, flagStyle_);
    }
    if (flagEffect_ != 0) {
      output.writeInt32(7, flagEffect_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (allianceId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, allianceId_);
    }
    if (!getAllianceNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, allianceName_);
    }
    if (!getShortNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, shortName_);
    }
    if (myScore_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, myScore_);
    }
    if (flagColor_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, flagColor_);
    }
    if (flagStyle_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, flagStyle_);
    }
    if (flagEffect_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, flagEffect_);
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
    if (!(obj instanceof pb4server.AllianceActivityRankAskVo)) {
      return super.equals(obj);
    }
    pb4server.AllianceActivityRankAskVo other = (pb4server.AllianceActivityRankAskVo) obj;

    boolean result = true;
    result = result && (getAllianceId()
        == other.getAllianceId());
    result = result && getAllianceName()
        .equals(other.getAllianceName());
    result = result && getShortName()
        .equals(other.getShortName());
    result = result && (getMyScore()
        == other.getMyScore());
    result = result && (getFlagColor()
        == other.getFlagColor());
    result = result && (getFlagStyle()
        == other.getFlagStyle());
    result = result && (getFlagEffect()
        == other.getFlagEffect());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ALLIANCEID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAllianceId());
    hash = (37 * hash) + ALLIANCENAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceName().hashCode();
    hash = (37 * hash) + SHORTNAME_FIELD_NUMBER;
    hash = (53 * hash) + getShortName().hashCode();
    hash = (37 * hash) + MYSCORE_FIELD_NUMBER;
    hash = (53 * hash) + getMyScore();
    hash = (37 * hash) + FLAGCOLOR_FIELD_NUMBER;
    hash = (53 * hash) + getFlagColor();
    hash = (37 * hash) + FLAGSTYLE_FIELD_NUMBER;
    hash = (53 * hash) + getFlagStyle();
    hash = (37 * hash) + FLAGEFFECT_FIELD_NUMBER;
    hash = (53 * hash) + getFlagEffect();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.AllianceActivityRankAskVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceActivityRankAskVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceActivityRankAskVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceActivityRankAskVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceActivityRankAskVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceActivityRankAskVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceActivityRankAskVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceActivityRankAskVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceActivityRankAskVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AllianceActivityRankAskVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceActivityRankAskVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceActivityRankAskVo parseFrom(
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
  public static Builder newBuilder(pb4server.AllianceActivityRankAskVo prototype) {
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
   * Protobuf type {@code pb4server.AllianceActivityRankAskVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AllianceActivityRankAskVo)
      pb4server.AllianceActivityRankAskVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceActivityRankAskVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceActivityRankAskVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AllianceActivityRankAskVo.class, pb4server.AllianceActivityRankAskVo.Builder.class);
    }

    // Construct using pb4server.AllianceActivityRankAskVo.newBuilder()
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
      allianceId_ = 0L;

      allianceName_ = "";

      shortName_ = "";

      myScore_ = 0;

      flagColor_ = 0;

      flagStyle_ = 0;

      flagEffect_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceActivityRankAskVo_descriptor;
    }

    public pb4server.AllianceActivityRankAskVo getDefaultInstanceForType() {
      return pb4server.AllianceActivityRankAskVo.getDefaultInstance();
    }

    public pb4server.AllianceActivityRankAskVo build() {
      pb4server.AllianceActivityRankAskVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AllianceActivityRankAskVo buildPartial() {
      pb4server.AllianceActivityRankAskVo result = new pb4server.AllianceActivityRankAskVo(this);
      result.allianceId_ = allianceId_;
      result.allianceName_ = allianceName_;
      result.shortName_ = shortName_;
      result.myScore_ = myScore_;
      result.flagColor_ = flagColor_;
      result.flagStyle_ = flagStyle_;
      result.flagEffect_ = flagEffect_;
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
      if (other instanceof pb4server.AllianceActivityRankAskVo) {
        return mergeFrom((pb4server.AllianceActivityRankAskVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AllianceActivityRankAskVo other) {
      if (other == pb4server.AllianceActivityRankAskVo.getDefaultInstance()) return this;
      if (other.getAllianceId() != 0L) {
        setAllianceId(other.getAllianceId());
      }
      if (!other.getAllianceName().isEmpty()) {
        allianceName_ = other.allianceName_;
        onChanged();
      }
      if (!other.getShortName().isEmpty()) {
        shortName_ = other.shortName_;
        onChanged();
      }
      if (other.getMyScore() != 0) {
        setMyScore(other.getMyScore());
      }
      if (other.getFlagColor() != 0) {
        setFlagColor(other.getFlagColor());
      }
      if (other.getFlagStyle() != 0) {
        setFlagStyle(other.getFlagStyle());
      }
      if (other.getFlagEffect() != 0) {
        setFlagEffect(other.getFlagEffect());
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
      pb4server.AllianceActivityRankAskVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AllianceActivityRankAskVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long allianceId_ ;
    /**
     * <code>int64 allianceId = 1;</code>
     */
    public long getAllianceId() {
      return allianceId_;
    }
    /**
     * <code>int64 allianceId = 1;</code>
     */
    public Builder setAllianceId(long value) {
      
      allianceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 allianceId = 1;</code>
     */
    public Builder clearAllianceId() {
      
      allianceId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object allianceName_ = "";
    /**
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
     * <code>string allianceName = 2;</code>
     */
    public Builder clearAllianceName() {
      
      allianceName_ = getDefaultInstance().getAllianceName();
      onChanged();
      return this;
    }
    /**
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

    private java.lang.Object shortName_ = "";
    /**
     * <code>string shortName = 3;</code>
     */
    public java.lang.String getShortName() {
      java.lang.Object ref = shortName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        shortName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string shortName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getShortNameBytes() {
      java.lang.Object ref = shortName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        shortName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string shortName = 3;</code>
     */
    public Builder setShortName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      shortName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string shortName = 3;</code>
     */
    public Builder clearShortName() {
      
      shortName_ = getDefaultInstance().getShortName();
      onChanged();
      return this;
    }
    /**
     * <code>string shortName = 3;</code>
     */
    public Builder setShortNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      shortName_ = value;
      onChanged();
      return this;
    }

    private int myScore_ ;
    /**
     * <code>int32 myScore = 4;</code>
     */
    public int getMyScore() {
      return myScore_;
    }
    /**
     * <code>int32 myScore = 4;</code>
     */
    public Builder setMyScore(int value) {
      
      myScore_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 myScore = 4;</code>
     */
    public Builder clearMyScore() {
      
      myScore_ = 0;
      onChanged();
      return this;
    }

    private int flagColor_ ;
    /**
     * <code>int32 flagColor = 5;</code>
     */
    public int getFlagColor() {
      return flagColor_;
    }
    /**
     * <code>int32 flagColor = 5;</code>
     */
    public Builder setFlagColor(int value) {
      
      flagColor_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 flagColor = 5;</code>
     */
    public Builder clearFlagColor() {
      
      flagColor_ = 0;
      onChanged();
      return this;
    }

    private int flagStyle_ ;
    /**
     * <code>int32 flagStyle = 6;</code>
     */
    public int getFlagStyle() {
      return flagStyle_;
    }
    /**
     * <code>int32 flagStyle = 6;</code>
     */
    public Builder setFlagStyle(int value) {
      
      flagStyle_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 flagStyle = 6;</code>
     */
    public Builder clearFlagStyle() {
      
      flagStyle_ = 0;
      onChanged();
      return this;
    }

    private int flagEffect_ ;
    /**
     * <code>int32 flagEffect = 7;</code>
     */
    public int getFlagEffect() {
      return flagEffect_;
    }
    /**
     * <code>int32 flagEffect = 7;</code>
     */
    public Builder setFlagEffect(int value) {
      
      flagEffect_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 flagEffect = 7;</code>
     */
    public Builder clearFlagEffect() {
      
      flagEffect_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.AllianceActivityRankAskVo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AllianceActivityRankAskVo)
  private static final pb4server.AllianceActivityRankAskVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AllianceActivityRankAskVo();
  }

  public static pb4server.AllianceActivityRankAskVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllianceActivityRankAskVo>
      PARSER = new com.google.protobuf.AbstractParser<AllianceActivityRankAskVo>() {
    public AllianceActivityRankAskVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceActivityRankAskVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceActivityRankAskVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceActivityRankAskVo> getParserForType() {
    return PARSER;
  }

  public pb4server.AllianceActivityRankAskVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

