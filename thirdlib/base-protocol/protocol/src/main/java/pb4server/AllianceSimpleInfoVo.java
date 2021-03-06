// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.AllianceSimpleInfoVo}
 */
public  final class AllianceSimpleInfoVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AllianceSimpleInfoVo)
    AllianceSimpleInfoVoOrBuilder {
  // Use AllianceSimpleInfoVo.newBuilder() to construct.
  private AllianceSimpleInfoVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceSimpleInfoVo() {
    allianceName_ = "";
    allianceShortName_ = "";
    allianceId_ = 0L;
    flagColor_ = 0;
    flagStyle_ = 0;
    flagEffect_ = 0;
    value_ = 0L;
    extend1_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllianceSimpleInfoVo(
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
            java.lang.String s = input.readStringRequireUtf8();

            allianceName_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            allianceShortName_ = s;
            break;
          }
          case 24: {

            allianceId_ = input.readInt64();
            break;
          }
          case 32: {

            flagColor_ = input.readInt32();
            break;
          }
          case 40: {

            flagStyle_ = input.readInt32();
            break;
          }
          case 48: {

            flagEffect_ = input.readInt32();
            break;
          }
          case 56: {

            value_ = input.readInt64();
            break;
          }
          case 66: {
            java.lang.String s = input.readStringRequireUtf8();

            extend1_ = s;
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
    return pb4server.InternalPkt.internal_static_pb4server_AllianceSimpleInfoVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_AllianceSimpleInfoVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AllianceSimpleInfoVo.class, pb4server.AllianceSimpleInfoVo.Builder.class);
  }

  public static final int ALLIANCENAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object allianceName_;
  /**
   * <code>string allianceName = 1;</code>
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
   * <code>string allianceName = 1;</code>
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

  public static final int ALLIANCESHORTNAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object allianceShortName_;
  /**
   * <code>string allianceShortName = 2;</code>
   */
  public java.lang.String getAllianceShortName() {
    java.lang.Object ref = allianceShortName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      allianceShortName_ = s;
      return s;
    }
  }
  /**
   * <code>string allianceShortName = 2;</code>
   */
  public com.google.protobuf.ByteString
      getAllianceShortNameBytes() {
    java.lang.Object ref = allianceShortName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      allianceShortName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ALLIANCEID_FIELD_NUMBER = 3;
  private long allianceId_;
  /**
   * <code>int64 allianceId = 3;</code>
   */
  public long getAllianceId() {
    return allianceId_;
  }

  public static final int FLAGCOLOR_FIELD_NUMBER = 4;
  private int flagColor_;
  /**
   * <code>int32 flagColor = 4;</code>
   */
  public int getFlagColor() {
    return flagColor_;
  }

  public static final int FLAGSTYLE_FIELD_NUMBER = 5;
  private int flagStyle_;
  /**
   * <code>int32 flagStyle = 5;</code>
   */
  public int getFlagStyle() {
    return flagStyle_;
  }

  public static final int FLAGEFFECT_FIELD_NUMBER = 6;
  private int flagEffect_;
  /**
   * <code>int32 flagEffect = 6;</code>
   */
  public int getFlagEffect() {
    return flagEffect_;
  }

  public static final int VALUE_FIELD_NUMBER = 7;
  private long value_;
  /**
   * <code>int64 value = 7;</code>
   */
  public long getValue() {
    return value_;
  }

  public static final int EXTEND1_FIELD_NUMBER = 8;
  private volatile java.lang.Object extend1_;
  /**
   * <code>string extend1 = 8;</code>
   */
  public java.lang.String getExtend1() {
    java.lang.Object ref = extend1_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      extend1_ = s;
      return s;
    }
  }
  /**
   * <code>string extend1 = 8;</code>
   */
  public com.google.protobuf.ByteString
      getExtend1Bytes() {
    java.lang.Object ref = extend1_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      extend1_ = b;
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
    if (!getAllianceNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, allianceName_);
    }
    if (!getAllianceShortNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, allianceShortName_);
    }
    if (allianceId_ != 0L) {
      output.writeInt64(3, allianceId_);
    }
    if (flagColor_ != 0) {
      output.writeInt32(4, flagColor_);
    }
    if (flagStyle_ != 0) {
      output.writeInt32(5, flagStyle_);
    }
    if (flagEffect_ != 0) {
      output.writeInt32(6, flagEffect_);
    }
    if (value_ != 0L) {
      output.writeInt64(7, value_);
    }
    if (!getExtend1Bytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 8, extend1_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getAllianceNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, allianceName_);
    }
    if (!getAllianceShortNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, allianceShortName_);
    }
    if (allianceId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, allianceId_);
    }
    if (flagColor_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, flagColor_);
    }
    if (flagStyle_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, flagStyle_);
    }
    if (flagEffect_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, flagEffect_);
    }
    if (value_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(7, value_);
    }
    if (!getExtend1Bytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(8, extend1_);
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
    if (!(obj instanceof pb4server.AllianceSimpleInfoVo)) {
      return super.equals(obj);
    }
    pb4server.AllianceSimpleInfoVo other = (pb4server.AllianceSimpleInfoVo) obj;

    boolean result = true;
    result = result && getAllianceName()
        .equals(other.getAllianceName());
    result = result && getAllianceShortName()
        .equals(other.getAllianceShortName());
    result = result && (getAllianceId()
        == other.getAllianceId());
    result = result && (getFlagColor()
        == other.getFlagColor());
    result = result && (getFlagStyle()
        == other.getFlagStyle());
    result = result && (getFlagEffect()
        == other.getFlagEffect());
    result = result && (getValue()
        == other.getValue());
    result = result && getExtend1()
        .equals(other.getExtend1());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ALLIANCENAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceName().hashCode();
    hash = (37 * hash) + ALLIANCESHORTNAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceShortName().hashCode();
    hash = (37 * hash) + ALLIANCEID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAllianceId());
    hash = (37 * hash) + FLAGCOLOR_FIELD_NUMBER;
    hash = (53 * hash) + getFlagColor();
    hash = (37 * hash) + FLAGSTYLE_FIELD_NUMBER;
    hash = (53 * hash) + getFlagStyle();
    hash = (37 * hash) + FLAGEFFECT_FIELD_NUMBER;
    hash = (53 * hash) + getFlagEffect();
    hash = (37 * hash) + VALUE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getValue());
    hash = (37 * hash) + EXTEND1_FIELD_NUMBER;
    hash = (53 * hash) + getExtend1().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.AllianceSimpleInfoVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceSimpleInfoVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceSimpleInfoVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceSimpleInfoVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceSimpleInfoVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceSimpleInfoVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceSimpleInfoVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceSimpleInfoVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceSimpleInfoVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AllianceSimpleInfoVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceSimpleInfoVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceSimpleInfoVo parseFrom(
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
  public static Builder newBuilder(pb4server.AllianceSimpleInfoVo prototype) {
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
   * Protobuf type {@code pb4server.AllianceSimpleInfoVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AllianceSimpleInfoVo)
      pb4server.AllianceSimpleInfoVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceSimpleInfoVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceSimpleInfoVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AllianceSimpleInfoVo.class, pb4server.AllianceSimpleInfoVo.Builder.class);
    }

    // Construct using pb4server.AllianceSimpleInfoVo.newBuilder()
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

      allianceShortName_ = "";

      allianceId_ = 0L;

      flagColor_ = 0;

      flagStyle_ = 0;

      flagEffect_ = 0;

      value_ = 0L;

      extend1_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceSimpleInfoVo_descriptor;
    }

    public pb4server.AllianceSimpleInfoVo getDefaultInstanceForType() {
      return pb4server.AllianceSimpleInfoVo.getDefaultInstance();
    }

    public pb4server.AllianceSimpleInfoVo build() {
      pb4server.AllianceSimpleInfoVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AllianceSimpleInfoVo buildPartial() {
      pb4server.AllianceSimpleInfoVo result = new pb4server.AllianceSimpleInfoVo(this);
      result.allianceName_ = allianceName_;
      result.allianceShortName_ = allianceShortName_;
      result.allianceId_ = allianceId_;
      result.flagColor_ = flagColor_;
      result.flagStyle_ = flagStyle_;
      result.flagEffect_ = flagEffect_;
      result.value_ = value_;
      result.extend1_ = extend1_;
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
      if (other instanceof pb4server.AllianceSimpleInfoVo) {
        return mergeFrom((pb4server.AllianceSimpleInfoVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AllianceSimpleInfoVo other) {
      if (other == pb4server.AllianceSimpleInfoVo.getDefaultInstance()) return this;
      if (!other.getAllianceName().isEmpty()) {
        allianceName_ = other.allianceName_;
        onChanged();
      }
      if (!other.getAllianceShortName().isEmpty()) {
        allianceShortName_ = other.allianceShortName_;
        onChanged();
      }
      if (other.getAllianceId() != 0L) {
        setAllianceId(other.getAllianceId());
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
      if (other.getValue() != 0L) {
        setValue(other.getValue());
      }
      if (!other.getExtend1().isEmpty()) {
        extend1_ = other.extend1_;
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
      pb4server.AllianceSimpleInfoVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AllianceSimpleInfoVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object allianceName_ = "";
    /**
     * <code>string allianceName = 1;</code>
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
     * <code>string allianceName = 1;</code>
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
     * <code>string allianceName = 1;</code>
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
     * <code>string allianceName = 1;</code>
     */
    public Builder clearAllianceName() {
      
      allianceName_ = getDefaultInstance().getAllianceName();
      onChanged();
      return this;
    }
    /**
     * <code>string allianceName = 1;</code>
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

    private java.lang.Object allianceShortName_ = "";
    /**
     * <code>string allianceShortName = 2;</code>
     */
    public java.lang.String getAllianceShortName() {
      java.lang.Object ref = allianceShortName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        allianceShortName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string allianceShortName = 2;</code>
     */
    public com.google.protobuf.ByteString
        getAllianceShortNameBytes() {
      java.lang.Object ref = allianceShortName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        allianceShortName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string allianceShortName = 2;</code>
     */
    public Builder setAllianceShortName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      allianceShortName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string allianceShortName = 2;</code>
     */
    public Builder clearAllianceShortName() {
      
      allianceShortName_ = getDefaultInstance().getAllianceShortName();
      onChanged();
      return this;
    }
    /**
     * <code>string allianceShortName = 2;</code>
     */
    public Builder setAllianceShortNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      allianceShortName_ = value;
      onChanged();
      return this;
    }

    private long allianceId_ ;
    /**
     * <code>int64 allianceId = 3;</code>
     */
    public long getAllianceId() {
      return allianceId_;
    }
    /**
     * <code>int64 allianceId = 3;</code>
     */
    public Builder setAllianceId(long value) {
      
      allianceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 allianceId = 3;</code>
     */
    public Builder clearAllianceId() {
      
      allianceId_ = 0L;
      onChanged();
      return this;
    }

    private int flagColor_ ;
    /**
     * <code>int32 flagColor = 4;</code>
     */
    public int getFlagColor() {
      return flagColor_;
    }
    /**
     * <code>int32 flagColor = 4;</code>
     */
    public Builder setFlagColor(int value) {
      
      flagColor_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 flagColor = 4;</code>
     */
    public Builder clearFlagColor() {
      
      flagColor_ = 0;
      onChanged();
      return this;
    }

    private int flagStyle_ ;
    /**
     * <code>int32 flagStyle = 5;</code>
     */
    public int getFlagStyle() {
      return flagStyle_;
    }
    /**
     * <code>int32 flagStyle = 5;</code>
     */
    public Builder setFlagStyle(int value) {
      
      flagStyle_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 flagStyle = 5;</code>
     */
    public Builder clearFlagStyle() {
      
      flagStyle_ = 0;
      onChanged();
      return this;
    }

    private int flagEffect_ ;
    /**
     * <code>int32 flagEffect = 6;</code>
     */
    public int getFlagEffect() {
      return flagEffect_;
    }
    /**
     * <code>int32 flagEffect = 6;</code>
     */
    public Builder setFlagEffect(int value) {
      
      flagEffect_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 flagEffect = 6;</code>
     */
    public Builder clearFlagEffect() {
      
      flagEffect_ = 0;
      onChanged();
      return this;
    }

    private long value_ ;
    /**
     * <code>int64 value = 7;</code>
     */
    public long getValue() {
      return value_;
    }
    /**
     * <code>int64 value = 7;</code>
     */
    public Builder setValue(long value) {
      
      value_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 value = 7;</code>
     */
    public Builder clearValue() {
      
      value_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object extend1_ = "";
    /**
     * <code>string extend1 = 8;</code>
     */
    public java.lang.String getExtend1() {
      java.lang.Object ref = extend1_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        extend1_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string extend1 = 8;</code>
     */
    public com.google.protobuf.ByteString
        getExtend1Bytes() {
      java.lang.Object ref = extend1_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        extend1_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string extend1 = 8;</code>
     */
    public Builder setExtend1(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      extend1_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string extend1 = 8;</code>
     */
    public Builder clearExtend1() {
      
      extend1_ = getDefaultInstance().getExtend1();
      onChanged();
      return this;
    }
    /**
     * <code>string extend1 = 8;</code>
     */
    public Builder setExtend1Bytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      extend1_ = value;
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


    // @@protoc_insertion_point(builder_scope:pb4server.AllianceSimpleInfoVo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AllianceSimpleInfoVo)
  private static final pb4server.AllianceSimpleInfoVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AllianceSimpleInfoVo();
  }

  public static pb4server.AllianceSimpleInfoVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllianceSimpleInfoVo>
      PARSER = new com.google.protobuf.AbstractParser<AllianceSimpleInfoVo>() {
    public AllianceSimpleInfoVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceSimpleInfoVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceSimpleInfoVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceSimpleInfoVo> getParserForType() {
    return PARSER;
  }

  public pb4server.AllianceSimpleInfoVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

