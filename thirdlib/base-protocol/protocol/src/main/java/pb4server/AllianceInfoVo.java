// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.AllianceInfoVo}
 */
public  final class AllianceInfoVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AllianceInfoVo)
    AllianceInfoVoOrBuilder {
  // Use AllianceInfoVo.newBuilder() to construct.
  private AllianceInfoVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceInfoVo() {
    id_ = 0L;
    name_ = "";
    shortName_ = "";
    positions_ = java.util.Collections.emptyList();
    color_ = 0;
    style_ = 0;
    effect_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllianceInfoVo(
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

            id_ = input.readInt64();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            shortName_ = s;
            break;
          }
          case 32: {
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
              positions_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000008;
            }
            positions_.add(input.readInt32());
            break;
          }
          case 34: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008) && input.getBytesUntilLimit() > 0) {
              positions_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000008;
            }
            while (input.getBytesUntilLimit() > 0) {
              positions_.add(input.readInt32());
            }
            input.popLimit(limit);
            break;
          }
          case 40: {

            color_ = input.readInt32();
            break;
          }
          case 48: {

            style_ = input.readInt32();
            break;
          }
          case 56: {

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
      if (((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
        positions_ = java.util.Collections.unmodifiableList(positions_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalWkt.internal_static_pb4server_AllianceInfoVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_AllianceInfoVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AllianceInfoVo.class, pb4server.AllianceInfoVo.Builder.class);
  }

  private int bitField0_;
  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <pre>
   *联盟Id
   * </pre>
   *
   * <code>int64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object name_;
  /**
   * <pre>
   *联盟名称
   * </pre>
   *
   * <code>string name = 2;</code>
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *联盟名称
   * </pre>
   *
   * <code>string name = 2;</code>
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SHORTNAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object shortName_;
  /**
   * <pre>
   *联盟简称
   * </pre>
   *
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
   * <pre>
   *联盟简称
   * </pre>
   *
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

  public static final int POSITIONS_FIELD_NUMBER = 4;
  private java.util.List<java.lang.Integer> positions_;
  /**
   * <pre>
   *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 4;</code>
   */
  public java.util.List<java.lang.Integer>
      getPositionsList() {
    return positions_;
  }
  /**
   * <pre>
   *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 4;</code>
   */
  public int getPositionsCount() {
    return positions_.size();
  }
  /**
   * <pre>
   *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 4;</code>
   */
  public int getPositions(int index) {
    return positions_.get(index);
  }
  private int positionsMemoizedSerializedSize = -1;

  public static final int COLOR_FIELD_NUMBER = 5;
  private int color_;
  /**
   * <pre>
   *旗帜的颜色（没有设置过，则值为0）
   * </pre>
   *
   * <code>int32 color = 5;</code>
   */
  public int getColor() {
    return color_;
  }

  public static final int STYLE_FIELD_NUMBER = 6;
  private int style_;
  /**
   * <pre>
   *旗帜的样式（没有设置过，则值为0）
   * </pre>
   *
   * <code>int32 style = 6;</code>
   */
  public int getStyle() {
    return style_;
  }

  public static final int EFFECT_FIELD_NUMBER = 7;
  private int effect_;
  /**
   * <pre>
   *旗帜的图案（没有设置过，则值为0）
   * </pre>
   *
   * <code>int32 effect = 7;</code>
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
    getSerializedSize();
    if (id_ != 0L) {
      output.writeInt64(1, id_);
    }
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
    }
    if (!getShortNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, shortName_);
    }
    if (getPositionsList().size() > 0) {
      output.writeUInt32NoTag(34);
      output.writeUInt32NoTag(positionsMemoizedSerializedSize);
    }
    for (int i = 0; i < positions_.size(); i++) {
      output.writeInt32NoTag(positions_.get(i));
    }
    if (color_ != 0) {
      output.writeInt32(5, color_);
    }
    if (style_ != 0) {
      output.writeInt32(6, style_);
    }
    if (effect_ != 0) {
      output.writeInt32(7, effect_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, id_);
    }
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
    }
    if (!getShortNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, shortName_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < positions_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(positions_.get(i));
      }
      size += dataSize;
      if (!getPositionsList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      positionsMemoizedSerializedSize = dataSize;
    }
    if (color_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, color_);
    }
    if (style_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, style_);
    }
    if (effect_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, effect_);
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
    if (!(obj instanceof pb4server.AllianceInfoVo)) {
      return super.equals(obj);
    }
    pb4server.AllianceInfoVo other = (pb4server.AllianceInfoVo) obj;

    boolean result = true;
    result = result && (getId()
        == other.getId());
    result = result && getName()
        .equals(other.getName());
    result = result && getShortName()
        .equals(other.getShortName());
    result = result && getPositionsList()
        .equals(other.getPositionsList());
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
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getId());
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + SHORTNAME_FIELD_NUMBER;
    hash = (53 * hash) + getShortName().hashCode();
    if (getPositionsCount() > 0) {
      hash = (37 * hash) + POSITIONS_FIELD_NUMBER;
      hash = (53 * hash) + getPositionsList().hashCode();
    }
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

  public static pb4server.AllianceInfoVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceInfoVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceInfoVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceInfoVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceInfoVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceInfoVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceInfoVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceInfoVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceInfoVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AllianceInfoVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceInfoVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceInfoVo parseFrom(
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
  public static Builder newBuilder(pb4server.AllianceInfoVo prototype) {
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
   * Protobuf type {@code pb4server.AllianceInfoVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AllianceInfoVo)
      pb4server.AllianceInfoVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_AllianceInfoVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_AllianceInfoVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AllianceInfoVo.class, pb4server.AllianceInfoVo.Builder.class);
    }

    // Construct using pb4server.AllianceInfoVo.newBuilder()
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
      id_ = 0L;

      name_ = "";

      shortName_ = "";

      positions_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000008);
      color_ = 0;

      style_ = 0;

      effect_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_AllianceInfoVo_descriptor;
    }

    public pb4server.AllianceInfoVo getDefaultInstanceForType() {
      return pb4server.AllianceInfoVo.getDefaultInstance();
    }

    public pb4server.AllianceInfoVo build() {
      pb4server.AllianceInfoVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AllianceInfoVo buildPartial() {
      pb4server.AllianceInfoVo result = new pb4server.AllianceInfoVo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.id_ = id_;
      result.name_ = name_;
      result.shortName_ = shortName_;
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        positions_ = java.util.Collections.unmodifiableList(positions_);
        bitField0_ = (bitField0_ & ~0x00000008);
      }
      result.positions_ = positions_;
      result.color_ = color_;
      result.style_ = style_;
      result.effect_ = effect_;
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
      if (other instanceof pb4server.AllianceInfoVo) {
        return mergeFrom((pb4server.AllianceInfoVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AllianceInfoVo other) {
      if (other == pb4server.AllianceInfoVo.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (!other.getShortName().isEmpty()) {
        shortName_ = other.shortName_;
        onChanged();
      }
      if (!other.positions_.isEmpty()) {
        if (positions_.isEmpty()) {
          positions_ = other.positions_;
          bitField0_ = (bitField0_ & ~0x00000008);
        } else {
          ensurePositionsIsMutable();
          positions_.addAll(other.positions_);
        }
        onChanged();
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
      pb4server.AllianceInfoVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AllianceInfoVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long id_ ;
    /**
     * <pre>
     *联盟Id
     * </pre>
     *
     * <code>int64 id = 1;</code>
     */
    public long getId() {
      return id_;
    }
    /**
     * <pre>
     *联盟Id
     * </pre>
     *
     * <code>int64 id = 1;</code>
     */
    public Builder setId(long value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *联盟Id
     * </pre>
     *
     * <code>int64 id = 1;</code>
     */
    public Builder clearId() {
      
      id_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <pre>
     *联盟名称
     * </pre>
     *
     * <code>string name = 2;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *联盟名称
     * </pre>
     *
     * <code>string name = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *联盟名称
     * </pre>
     *
     * <code>string name = 2;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *联盟名称
     * </pre>
     *
     * <code>string name = 2;</code>
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *联盟名称
     * </pre>
     *
     * <code>string name = 2;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object shortName_ = "";
    /**
     * <pre>
     *联盟简称
     * </pre>
     *
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
     * <pre>
     *联盟简称
     * </pre>
     *
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
     * <pre>
     *联盟简称
     * </pre>
     *
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
     * <pre>
     *联盟简称
     * </pre>
     *
     * <code>string shortName = 3;</code>
     */
    public Builder clearShortName() {
      
      shortName_ = getDefaultInstance().getShortName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *联盟简称
     * </pre>
     *
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

    private java.util.List<java.lang.Integer> positions_ = java.util.Collections.emptyList();
    private void ensurePositionsIsMutable() {
      if (!((bitField0_ & 0x00000008) == 0x00000008)) {
        positions_ = new java.util.ArrayList<java.lang.Integer>(positions_);
        bitField0_ |= 0x00000008;
       }
    }
    /**
     * <pre>
     *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public java.util.List<java.lang.Integer>
        getPositionsList() {
      return java.util.Collections.unmodifiableList(positions_);
    }
    /**
     * <pre>
     *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public int getPositionsCount() {
      return positions_.size();
    }
    /**
     * <pre>
     *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public int getPositions(int index) {
      return positions_.get(index);
    }
    /**
     * <pre>
     *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public Builder setPositions(
        int index, int value) {
      ensurePositionsIsMutable();
      positions_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public Builder addPositions(int value) {
      ensurePositionsIsMutable();
      positions_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public Builder addAllPositions(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensurePositionsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, positions_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public Builder clearPositions() {
      positions_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000008);
      onChanged();
      return this;
    }

    private int color_ ;
    /**
     * <pre>
     *旗帜的颜色（没有设置过，则值为0）
     * </pre>
     *
     * <code>int32 color = 5;</code>
     */
    public int getColor() {
      return color_;
    }
    /**
     * <pre>
     *旗帜的颜色（没有设置过，则值为0）
     * </pre>
     *
     * <code>int32 color = 5;</code>
     */
    public Builder setColor(int value) {
      
      color_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *旗帜的颜色（没有设置过，则值为0）
     * </pre>
     *
     * <code>int32 color = 5;</code>
     */
    public Builder clearColor() {
      
      color_ = 0;
      onChanged();
      return this;
    }

    private int style_ ;
    /**
     * <pre>
     *旗帜的样式（没有设置过，则值为0）
     * </pre>
     *
     * <code>int32 style = 6;</code>
     */
    public int getStyle() {
      return style_;
    }
    /**
     * <pre>
     *旗帜的样式（没有设置过，则值为0）
     * </pre>
     *
     * <code>int32 style = 6;</code>
     */
    public Builder setStyle(int value) {
      
      style_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *旗帜的样式（没有设置过，则值为0）
     * </pre>
     *
     * <code>int32 style = 6;</code>
     */
    public Builder clearStyle() {
      
      style_ = 0;
      onChanged();
      return this;
    }

    private int effect_ ;
    /**
     * <pre>
     *旗帜的图案（没有设置过，则值为0）
     * </pre>
     *
     * <code>int32 effect = 7;</code>
     */
    public int getEffect() {
      return effect_;
    }
    /**
     * <pre>
     *旗帜的图案（没有设置过，则值为0）
     * </pre>
     *
     * <code>int32 effect = 7;</code>
     */
    public Builder setEffect(int value) {
      
      effect_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *旗帜的图案（没有设置过，则值为0）
     * </pre>
     *
     * <code>int32 effect = 7;</code>
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


    // @@protoc_insertion_point(builder_scope:pb4server.AllianceInfoVo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AllianceInfoVo)
  private static final pb4server.AllianceInfoVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AllianceInfoVo();
  }

  public static pb4server.AllianceInfoVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllianceInfoVo>
      PARSER = new com.google.protobuf.AbstractParser<AllianceInfoVo>() {
    public AllianceInfoVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceInfoVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceInfoVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceInfoVo> getParserForType() {
    return PARSER;
  }

  public pb4server.AllianceInfoVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

