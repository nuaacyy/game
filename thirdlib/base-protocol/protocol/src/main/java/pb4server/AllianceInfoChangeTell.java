// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * <pre>
 * 联盟信息发生变化
 * </pre>
 *
 * Protobuf type {@code pb4server.AllianceInfoChangeTell}
 */
public  final class AllianceInfoChangeTell extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AllianceInfoChangeTell)
    AllianceInfoChangeTellOrBuilder {
  // Use AllianceInfoChangeTell.newBuilder() to construct.
  private AllianceInfoChangeTell(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceInfoChangeTell() {
    allianceName_ = "";
    allianceShortName_ = "";
    flagColor_ = 0;
    flagStyle_ = 0;
    flagEffect_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllianceInfoChangeTell(
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

            flagColor_ = input.readInt32();
            break;
          }
          case 32: {

            flagStyle_ = input.readInt32();
            break;
          }
          case 40: {

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
    return pb4server.InternalHkt.internal_static_pb4server_AllianceInfoChangeTell_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_AllianceInfoChangeTell_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AllianceInfoChangeTell.class, pb4server.AllianceInfoChangeTell.Builder.class);
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

  public static final int FLAGCOLOR_FIELD_NUMBER = 3;
  private int flagColor_;
  /**
   * <code>int32 flagColor = 3;</code>
   */
  public int getFlagColor() {
    return flagColor_;
  }

  public static final int FLAGSTYLE_FIELD_NUMBER = 4;
  private int flagStyle_;
  /**
   * <code>int32 flagStyle = 4;</code>
   */
  public int getFlagStyle() {
    return flagStyle_;
  }

  public static final int FLAGEFFECT_FIELD_NUMBER = 5;
  private int flagEffect_;
  /**
   * <code>int32 flagEffect = 5;</code>
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
    if (!getAllianceNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, allianceName_);
    }
    if (!getAllianceShortNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, allianceShortName_);
    }
    if (flagColor_ != 0) {
      output.writeInt32(3, flagColor_);
    }
    if (flagStyle_ != 0) {
      output.writeInt32(4, flagStyle_);
    }
    if (flagEffect_ != 0) {
      output.writeInt32(5, flagEffect_);
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
    if (flagColor_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, flagColor_);
    }
    if (flagStyle_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, flagStyle_);
    }
    if (flagEffect_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, flagEffect_);
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
    if (!(obj instanceof pb4server.AllianceInfoChangeTell)) {
      return super.equals(obj);
    }
    pb4server.AllianceInfoChangeTell other = (pb4server.AllianceInfoChangeTell) obj;

    boolean result = true;
    result = result && getAllianceName()
        .equals(other.getAllianceName());
    result = result && getAllianceShortName()
        .equals(other.getAllianceShortName());
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
    hash = (37 * hash) + ALLIANCENAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceName().hashCode();
    hash = (37 * hash) + ALLIANCESHORTNAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceShortName().hashCode();
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

  public static pb4server.AllianceInfoChangeTell parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceInfoChangeTell parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceInfoChangeTell parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceInfoChangeTell parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceInfoChangeTell parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceInfoChangeTell parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceInfoChangeTell parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceInfoChangeTell parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceInfoChangeTell parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AllianceInfoChangeTell parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceInfoChangeTell parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceInfoChangeTell parseFrom(
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
  public static Builder newBuilder(pb4server.AllianceInfoChangeTell prototype) {
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
   * 联盟信息发生变化
   * </pre>
   *
   * Protobuf type {@code pb4server.AllianceInfoChangeTell}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AllianceInfoChangeTell)
      pb4server.AllianceInfoChangeTellOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_AllianceInfoChangeTell_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_AllianceInfoChangeTell_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AllianceInfoChangeTell.class, pb4server.AllianceInfoChangeTell.Builder.class);
    }

    // Construct using pb4server.AllianceInfoChangeTell.newBuilder()
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

      flagColor_ = 0;

      flagStyle_ = 0;

      flagEffect_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_AllianceInfoChangeTell_descriptor;
    }

    public pb4server.AllianceInfoChangeTell getDefaultInstanceForType() {
      return pb4server.AllianceInfoChangeTell.getDefaultInstance();
    }

    public pb4server.AllianceInfoChangeTell build() {
      pb4server.AllianceInfoChangeTell result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AllianceInfoChangeTell buildPartial() {
      pb4server.AllianceInfoChangeTell result = new pb4server.AllianceInfoChangeTell(this);
      result.allianceName_ = allianceName_;
      result.allianceShortName_ = allianceShortName_;
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
      if (other instanceof pb4server.AllianceInfoChangeTell) {
        return mergeFrom((pb4server.AllianceInfoChangeTell)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AllianceInfoChangeTell other) {
      if (other == pb4server.AllianceInfoChangeTell.getDefaultInstance()) return this;
      if (!other.getAllianceName().isEmpty()) {
        allianceName_ = other.allianceName_;
        onChanged();
      }
      if (!other.getAllianceShortName().isEmpty()) {
        allianceShortName_ = other.allianceShortName_;
        onChanged();
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
      pb4server.AllianceInfoChangeTell parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AllianceInfoChangeTell) e.getUnfinishedMessage();
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

    private int flagColor_ ;
    /**
     * <code>int32 flagColor = 3;</code>
     */
    public int getFlagColor() {
      return flagColor_;
    }
    /**
     * <code>int32 flagColor = 3;</code>
     */
    public Builder setFlagColor(int value) {
      
      flagColor_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 flagColor = 3;</code>
     */
    public Builder clearFlagColor() {
      
      flagColor_ = 0;
      onChanged();
      return this;
    }

    private int flagStyle_ ;
    /**
     * <code>int32 flagStyle = 4;</code>
     */
    public int getFlagStyle() {
      return flagStyle_;
    }
    /**
     * <code>int32 flagStyle = 4;</code>
     */
    public Builder setFlagStyle(int value) {
      
      flagStyle_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 flagStyle = 4;</code>
     */
    public Builder clearFlagStyle() {
      
      flagStyle_ = 0;
      onChanged();
      return this;
    }

    private int flagEffect_ ;
    /**
     * <code>int32 flagEffect = 5;</code>
     */
    public int getFlagEffect() {
      return flagEffect_;
    }
    /**
     * <code>int32 flagEffect = 5;</code>
     */
    public Builder setFlagEffect(int value) {
      
      flagEffect_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 flagEffect = 5;</code>
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


    // @@protoc_insertion_point(builder_scope:pb4server.AllianceInfoChangeTell)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AllianceInfoChangeTell)
  private static final pb4server.AllianceInfoChangeTell DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AllianceInfoChangeTell();
  }

  public static pb4server.AllianceInfoChangeTell getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllianceInfoChangeTell>
      PARSER = new com.google.protobuf.AbstractParser<AllianceInfoChangeTell>() {
    public AllianceInfoChangeTell parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceInfoChangeTell(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceInfoChangeTell> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceInfoChangeTell> getParserForType() {
    return PARSER;
  }

  public pb4server.AllianceInfoChangeTell getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

