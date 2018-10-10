// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.ThumbToPlayerAskRt}
 */
public  final class ThumbToPlayerAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.ThumbToPlayerAskRt)
    ThumbToPlayerAskRtOrBuilder {
  // Use ThumbToPlayerAskRt.newBuilder() to construct.
  private ThumbToPlayerAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ThumbToPlayerAskRt() {
    rt_ = 0;
    playerName_ = "";
    lv_ = 0;
    intro_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ThumbToPlayerAskRt(
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
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            playerName_ = s;
            break;
          }
          case 24: {

            lv_ = input.readInt32();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            intro_ = s;
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
    return pb4server.InternalHkt.internal_static_pb4server_ThumbToPlayerAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_ThumbToPlayerAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.ThumbToPlayerAskRt.class, pb4server.ThumbToPlayerAskRt.Builder.class);
  }

  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int PLAYERNAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object playerName_;
  /**
   * <code>string playerName = 2;</code>
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
   * <code>string playerName = 2;</code>
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

  public static final int LV_FIELD_NUMBER = 3;
  private int lv_;
  /**
   * <code>int32 lv = 3;</code>
   */
  public int getLv() {
    return lv_;
  }

  public static final int INTRO_FIELD_NUMBER = 4;
  private volatile java.lang.Object intro_;
  /**
   * <code>string intro = 4;</code>
   */
  public java.lang.String getIntro() {
    java.lang.Object ref = intro_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      intro_ = s;
      return s;
    }
  }
  /**
   * <code>string intro = 4;</code>
   */
  public com.google.protobuf.ByteString
      getIntroBytes() {
    java.lang.Object ref = intro_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      intro_ = b;
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
    if (!getPlayerNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, playerName_);
    }
    if (lv_ != 0) {
      output.writeInt32(3, lv_);
    }
    if (!getIntroBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, intro_);
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
    if (!getPlayerNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, playerName_);
    }
    if (lv_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, lv_);
    }
    if (!getIntroBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, intro_);
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
    if (!(obj instanceof pb4server.ThumbToPlayerAskRt)) {
      return super.equals(obj);
    }
    pb4server.ThumbToPlayerAskRt other = (pb4server.ThumbToPlayerAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && getPlayerName()
        .equals(other.getPlayerName());
    result = result && (getLv()
        == other.getLv());
    result = result && getIntro()
        .equals(other.getIntro());
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
    hash = (37 * hash) + PLAYERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getPlayerName().hashCode();
    hash = (37 * hash) + LV_FIELD_NUMBER;
    hash = (53 * hash) + getLv();
    hash = (37 * hash) + INTRO_FIELD_NUMBER;
    hash = (53 * hash) + getIntro().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.ThumbToPlayerAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ThumbToPlayerAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ThumbToPlayerAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ThumbToPlayerAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ThumbToPlayerAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ThumbToPlayerAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ThumbToPlayerAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.ThumbToPlayerAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.ThumbToPlayerAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.ThumbToPlayerAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.ThumbToPlayerAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.ThumbToPlayerAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.ThumbToPlayerAskRt prototype) {
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
   * Protobuf type {@code pb4server.ThumbToPlayerAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.ThumbToPlayerAskRt)
      pb4server.ThumbToPlayerAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_ThumbToPlayerAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_ThumbToPlayerAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.ThumbToPlayerAskRt.class, pb4server.ThumbToPlayerAskRt.Builder.class);
    }

    // Construct using pb4server.ThumbToPlayerAskRt.newBuilder()
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

      playerName_ = "";

      lv_ = 0;

      intro_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_ThumbToPlayerAskRt_descriptor;
    }

    public pb4server.ThumbToPlayerAskRt getDefaultInstanceForType() {
      return pb4server.ThumbToPlayerAskRt.getDefaultInstance();
    }

    public pb4server.ThumbToPlayerAskRt build() {
      pb4server.ThumbToPlayerAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.ThumbToPlayerAskRt buildPartial() {
      pb4server.ThumbToPlayerAskRt result = new pb4server.ThumbToPlayerAskRt(this);
      result.rt_ = rt_;
      result.playerName_ = playerName_;
      result.lv_ = lv_;
      result.intro_ = intro_;
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
      if (other instanceof pb4server.ThumbToPlayerAskRt) {
        return mergeFrom((pb4server.ThumbToPlayerAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.ThumbToPlayerAskRt other) {
      if (other == pb4server.ThumbToPlayerAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (!other.getPlayerName().isEmpty()) {
        playerName_ = other.playerName_;
        onChanged();
      }
      if (other.getLv() != 0) {
        setLv(other.getLv());
      }
      if (!other.getIntro().isEmpty()) {
        intro_ = other.intro_;
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
      pb4server.ThumbToPlayerAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.ThumbToPlayerAskRt) e.getUnfinishedMessage();
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

    private java.lang.Object playerName_ = "";
    /**
     * <code>string playerName = 2;</code>
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
     * <code>string playerName = 2;</code>
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
     * <code>string playerName = 2;</code>
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
     * <code>string playerName = 2;</code>
     */
    public Builder clearPlayerName() {
      
      playerName_ = getDefaultInstance().getPlayerName();
      onChanged();
      return this;
    }
    /**
     * <code>string playerName = 2;</code>
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

    private int lv_ ;
    /**
     * <code>int32 lv = 3;</code>
     */
    public int getLv() {
      return lv_;
    }
    /**
     * <code>int32 lv = 3;</code>
     */
    public Builder setLv(int value) {
      
      lv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 lv = 3;</code>
     */
    public Builder clearLv() {
      
      lv_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object intro_ = "";
    /**
     * <code>string intro = 4;</code>
     */
    public java.lang.String getIntro() {
      java.lang.Object ref = intro_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        intro_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string intro = 4;</code>
     */
    public com.google.protobuf.ByteString
        getIntroBytes() {
      java.lang.Object ref = intro_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        intro_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string intro = 4;</code>
     */
    public Builder setIntro(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      intro_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string intro = 4;</code>
     */
    public Builder clearIntro() {
      
      intro_ = getDefaultInstance().getIntro();
      onChanged();
      return this;
    }
    /**
     * <code>string intro = 4;</code>
     */
    public Builder setIntroBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      intro_ = value;
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


    // @@protoc_insertion_point(builder_scope:pb4server.ThumbToPlayerAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.ThumbToPlayerAskRt)
  private static final pb4server.ThumbToPlayerAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.ThumbToPlayerAskRt();
  }

  public static pb4server.ThumbToPlayerAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ThumbToPlayerAskRt>
      PARSER = new com.google.protobuf.AbstractParser<ThumbToPlayerAskRt>() {
    public ThumbToPlayerAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ThumbToPlayerAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ThumbToPlayerAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ThumbToPlayerAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.ThumbToPlayerAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
