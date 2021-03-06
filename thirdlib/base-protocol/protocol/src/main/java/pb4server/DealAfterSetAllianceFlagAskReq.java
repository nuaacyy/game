// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * <pre>
 *MsgType = 11000
 * Public-&gt;Game
 * 修改联盟旗帜之后的推送
 * </pre>
 *
 * Protobuf type {@code pb4server.DealAfterSetAllianceFlagAskReq}
 */
public  final class DealAfterSetAllianceFlagAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.DealAfterSetAllianceFlagAskReq)
    DealAfterSetAllianceFlagAskReqOrBuilder {
  // Use DealAfterSetAllianceFlagAskReq.newBuilder() to construct.
  private DealAfterSetAllianceFlagAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DealAfterSetAllianceFlagAskReq() {
    allianceId_ = 0L;
    color_ = 0;
    style_ = 0;
    effect_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private DealAfterSetAllianceFlagAskReq(
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
          case 16: {

            color_ = input.readInt32();
            break;
          }
          case 24: {

            style_ = input.readInt32();
            break;
          }
          case 32: {

            effect_ = input.readInt32();
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
    return pb4server.InternalPkt.internal_static_pb4server_DealAfterSetAllianceFlagAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_DealAfterSetAllianceFlagAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.DealAfterSetAllianceFlagAskReq.class, pb4server.DealAfterSetAllianceFlagAskReq.Builder.class);
  }

  public static final int ALLIANCEID_FIELD_NUMBER = 1;
  private long allianceId_;
  /**
   * <code>int64 allianceId = 1;</code>
   */
  public long getAllianceId() {
    return allianceId_;
  }

  public static final int COLOR_FIELD_NUMBER = 2;
  private int color_;
  /**
   * <code>int32 color = 2;</code>
   */
  public int getColor() {
    return color_;
  }

  public static final int STYLE_FIELD_NUMBER = 3;
  private int style_;
  /**
   * <code>int32 style = 3;</code>
   */
  public int getStyle() {
    return style_;
  }

  public static final int EFFECT_FIELD_NUMBER = 4;
  private int effect_;
  /**
   * <code>int32 effect = 4;</code>
   */
  public int getEffect() {
    return effect_;
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
    if (color_ != 0) {
      output.writeInt32(2, color_);
    }
    if (style_ != 0) {
      output.writeInt32(3, style_);
    }
    if (effect_ != 0) {
      output.writeInt32(4, effect_);
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
    if (color_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, color_);
    }
    if (style_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, style_);
    }
    if (effect_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, effect_);
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
    if (!(obj instanceof pb4server.DealAfterSetAllianceFlagAskReq)) {
      return super.equals(obj);
    }
    pb4server.DealAfterSetAllianceFlagAskReq other = (pb4server.DealAfterSetAllianceFlagAskReq) obj;

    boolean result = true;
    result = result && (getAllianceId()
        == other.getAllianceId());
    result = result && (getColor()
        == other.getColor());
    result = result && (getStyle()
        == other.getStyle());
    result = result && (getEffect()
        == other.getEffect());
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
    hash = (37 * hash) + COLOR_FIELD_NUMBER;
    hash = (53 * hash) + getColor();
    hash = (37 * hash) + STYLE_FIELD_NUMBER;
    hash = (53 * hash) + getStyle();
    hash = (37 * hash) + EFFECT_FIELD_NUMBER;
    hash = (53 * hash) + getEffect();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.DealAfterSetAllianceFlagAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.DealAfterSetAllianceFlagAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.DealAfterSetAllianceFlagAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.DealAfterSetAllianceFlagAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.DealAfterSetAllianceFlagAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.DealAfterSetAllianceFlagAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.DealAfterSetAllianceFlagAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.DealAfterSetAllianceFlagAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.DealAfterSetAllianceFlagAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.DealAfterSetAllianceFlagAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.DealAfterSetAllianceFlagAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.DealAfterSetAllianceFlagAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.DealAfterSetAllianceFlagAskReq prototype) {
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
   *MsgType = 11000
   * Public-&gt;Game
   * 修改联盟旗帜之后的推送
   * </pre>
   *
   * Protobuf type {@code pb4server.DealAfterSetAllianceFlagAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.DealAfterSetAllianceFlagAskReq)
      pb4server.DealAfterSetAllianceFlagAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_DealAfterSetAllianceFlagAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_DealAfterSetAllianceFlagAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.DealAfterSetAllianceFlagAskReq.class, pb4server.DealAfterSetAllianceFlagAskReq.Builder.class);
    }

    // Construct using pb4server.DealAfterSetAllianceFlagAskReq.newBuilder()
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

      color_ = 0;

      style_ = 0;

      effect_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_DealAfterSetAllianceFlagAskReq_descriptor;
    }

    public pb4server.DealAfterSetAllianceFlagAskReq getDefaultInstanceForType() {
      return pb4server.DealAfterSetAllianceFlagAskReq.getDefaultInstance();
    }

    public pb4server.DealAfterSetAllianceFlagAskReq build() {
      pb4server.DealAfterSetAllianceFlagAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.DealAfterSetAllianceFlagAskReq buildPartial() {
      pb4server.DealAfterSetAllianceFlagAskReq result = new pb4server.DealAfterSetAllianceFlagAskReq(this);
      result.allianceId_ = allianceId_;
      result.color_ = color_;
      result.style_ = style_;
      result.effect_ = effect_;
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
      if (other instanceof pb4server.DealAfterSetAllianceFlagAskReq) {
        return mergeFrom((pb4server.DealAfterSetAllianceFlagAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.DealAfterSetAllianceFlagAskReq other) {
      if (other == pb4server.DealAfterSetAllianceFlagAskReq.getDefaultInstance()) return this;
      if (other.getAllianceId() != 0L) {
        setAllianceId(other.getAllianceId());
      }
      if (other.getColor() != 0) {
        setColor(other.getColor());
      }
      if (other.getStyle() != 0) {
        setStyle(other.getStyle());
      }
      if (other.getEffect() != 0) {
        setEffect(other.getEffect());
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
      pb4server.DealAfterSetAllianceFlagAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.DealAfterSetAllianceFlagAskReq) e.getUnfinishedMessage();
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

    private int color_ ;
    /**
     * <code>int32 color = 2;</code>
     */
    public int getColor() {
      return color_;
    }
    /**
     * <code>int32 color = 2;</code>
     */
    public Builder setColor(int value) {
      
      color_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 color = 2;</code>
     */
    public Builder clearColor() {
      
      color_ = 0;
      onChanged();
      return this;
    }

    private int style_ ;
    /**
     * <code>int32 style = 3;</code>
     */
    public int getStyle() {
      return style_;
    }
    /**
     * <code>int32 style = 3;</code>
     */
    public Builder setStyle(int value) {
      
      style_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 style = 3;</code>
     */
    public Builder clearStyle() {
      
      style_ = 0;
      onChanged();
      return this;
    }

    private int effect_ ;
    /**
     * <code>int32 effect = 4;</code>
     */
    public int getEffect() {
      return effect_;
    }
    /**
     * <code>int32 effect = 4;</code>
     */
    public Builder setEffect(int value) {
      
      effect_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 effect = 4;</code>
     */
    public Builder clearEffect() {
      
      effect_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.DealAfterSetAllianceFlagAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.DealAfterSetAllianceFlagAskReq)
  private static final pb4server.DealAfterSetAllianceFlagAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.DealAfterSetAllianceFlagAskReq();
  }

  public static pb4server.DealAfterSetAllianceFlagAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DealAfterSetAllianceFlagAskReq>
      PARSER = new com.google.protobuf.AbstractParser<DealAfterSetAllianceFlagAskReq>() {
    public DealAfterSetAllianceFlagAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new DealAfterSetAllianceFlagAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DealAfterSetAllianceFlagAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DealAfterSetAllianceFlagAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.DealAfterSetAllianceFlagAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

