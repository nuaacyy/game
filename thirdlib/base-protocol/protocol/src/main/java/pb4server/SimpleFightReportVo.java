// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.SimpleFightReportVo}
 */
public  final class SimpleFightReportVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.SimpleFightReportVo)
    SimpleFightReportVoOrBuilder {
  // Use SimpleFightReportVo.newBuilder() to construct.
  private SimpleFightReportVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SimpleFightReportVo() {
    reportType_ = 0;
    mainPlayer_ = "";
    mainPlayerAlliance_ = "";
    atkOrDef_ = 0;
    targetName_ = "";
    allianceOrLv_ = "";
    reportId_ = 0L;
    mainIconId_ = 0;
    iconId_ = 0;
    monsterId_ = 0;
    world_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private SimpleFightReportVo(
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

            reportType_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            mainPlayer_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            mainPlayerAlliance_ = s;
            break;
          }
          case 32: {

            atkOrDef_ = input.readInt32();
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            targetName_ = s;
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            allianceOrLv_ = s;
            break;
          }
          case 56: {

            reportId_ = input.readInt64();
            break;
          }
          case 64: {

            mainIconId_ = input.readInt32();
            break;
          }
          case 72: {

            iconId_ = input.readInt32();
            break;
          }
          case 80: {

            monsterId_ = input.readInt32();
            break;
          }
          case 88: {

            world_ = input.readInt64();
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
    return pb4server.InternalPkt.internal_static_pb4server_SimpleFightReportVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_SimpleFightReportVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.SimpleFightReportVo.class, pb4server.SimpleFightReportVo.Builder.class);
  }

  public static final int REPORTTYPE_FIELD_NUMBER = 1;
  private int reportType_;
  /**
   * <pre>
   * 魔物,集结战报,侦察等,参考下面的reportType
   * </pre>
   *
   * <code>int32 reportType = 1;</code>
   */
  public int getReportType() {
    return reportType_;
  }

  public static final int MAINPLAYER_FIELD_NUMBER = 2;
  private volatile java.lang.Object mainPlayer_;
  /**
   * <pre>
   * 左边人的名字
   * </pre>
   *
   * <code>string mainPlayer = 2;</code>
   */
  public java.lang.String getMainPlayer() {
    java.lang.Object ref = mainPlayer_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      mainPlayer_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 左边人的名字
   * </pre>
   *
   * <code>string mainPlayer = 2;</code>
   */
  public com.google.protobuf.ByteString
      getMainPlayerBytes() {
    java.lang.Object ref = mainPlayer_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      mainPlayer_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MAINPLAYERALLIANCE_FIELD_NUMBER = 3;
  private volatile java.lang.Object mainPlayerAlliance_;
  /**
   * <pre>
   * 左边人的联盟
   * </pre>
   *
   * <code>string mainPlayerAlliance = 3;</code>
   */
  public java.lang.String getMainPlayerAlliance() {
    java.lang.Object ref = mainPlayerAlliance_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      mainPlayerAlliance_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 左边人的联盟
   * </pre>
   *
   * <code>string mainPlayerAlliance = 3;</code>
   */
  public com.google.protobuf.ByteString
      getMainPlayerAllianceBytes() {
    java.lang.Object ref = mainPlayerAlliance_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      mainPlayerAlliance_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ATKORDEF_FIELD_NUMBER = 4;
  private int atkOrDef_;
  /**
   * <pre>
   * 攻击或防守 0进攻 1防守
   * </pre>
   *
   * <code>int32 atkOrDef = 4;</code>
   */
  public int getAtkOrDef() {
    return atkOrDef_;
  }

  public static final int TARGETNAME_FIELD_NUMBER = 5;
  private volatile java.lang.Object targetName_;
  /**
   * <pre>
   * 右边魔物或者对手的名字
   * </pre>
   *
   * <code>string targetName = 5;</code>
   */
  public java.lang.String getTargetName() {
    java.lang.Object ref = targetName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      targetName_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 右边魔物或者对手的名字
   * </pre>
   *
   * <code>string targetName = 5;</code>
   */
  public com.google.protobuf.ByteString
      getTargetNameBytes() {
    java.lang.Object ref = targetName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      targetName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ALLIANCEORLV_FIELD_NUMBER = 6;
  private volatile java.lang.Object allianceOrLv_;
  /**
   * <pre>
   * 右边魔物等级或者对手的联盟名
   * </pre>
   *
   * <code>string allianceOrLv = 6;</code>
   */
  public java.lang.String getAllianceOrLv() {
    java.lang.Object ref = allianceOrLv_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      allianceOrLv_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 右边魔物等级或者对手的联盟名
   * </pre>
   *
   * <code>string allianceOrLv = 6;</code>
   */
  public com.google.protobuf.ByteString
      getAllianceOrLvBytes() {
    java.lang.Object ref = allianceOrLv_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      allianceOrLv_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int REPORTID_FIELD_NUMBER = 7;
  private long reportId_;
  /**
   * <pre>
   * report战报id
   * </pre>
   *
   * <code>int64 reportId = 7;</code>
   */
  public long getReportId() {
    return reportId_;
  }

  public static final int MAINICONID_FIELD_NUMBER = 8;
  private int mainIconId_;
  /**
   * <pre>
   * 左边人头像模板
   * </pre>
   *
   * <code>int32 mainIconId = 8;</code>
   */
  public int getMainIconId() {
    return mainIconId_;
  }

  public static final int ICONID_FIELD_NUMBER = 9;
  private int iconId_;
  /**
   * <pre>
   * 右边人头像模板
   * </pre>
   *
   * <code>int32 iconId = 9;</code>
   */
  public int getIconId() {
    return iconId_;
  }

  public static final int MONSTERID_FIELD_NUMBER = 10;
  private int monsterId_;
  /**
   * <pre>
   * 魔物模板Id
   * </pre>
   *
   * <code>int32 monsterId = 10;</code>
   */
  public int getMonsterId() {
    return monsterId_;
  }

  public static final int WORLD_FIELD_NUMBER = 11;
  private long world_;
  /**
   * <pre>
   * 世界服id
   * </pre>
   *
   * <code>int64 world = 11;</code>
   */
  public long getWorld() {
    return world_;
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
    if (reportType_ != 0) {
      output.writeInt32(1, reportType_);
    }
    if (!getMainPlayerBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, mainPlayer_);
    }
    if (!getMainPlayerAllianceBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, mainPlayerAlliance_);
    }
    if (atkOrDef_ != 0) {
      output.writeInt32(4, atkOrDef_);
    }
    if (!getTargetNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, targetName_);
    }
    if (!getAllianceOrLvBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, allianceOrLv_);
    }
    if (reportId_ != 0L) {
      output.writeInt64(7, reportId_);
    }
    if (mainIconId_ != 0) {
      output.writeInt32(8, mainIconId_);
    }
    if (iconId_ != 0) {
      output.writeInt32(9, iconId_);
    }
    if (monsterId_ != 0) {
      output.writeInt32(10, monsterId_);
    }
    if (world_ != 0L) {
      output.writeInt64(11, world_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (reportType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, reportType_);
    }
    if (!getMainPlayerBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, mainPlayer_);
    }
    if (!getMainPlayerAllianceBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, mainPlayerAlliance_);
    }
    if (atkOrDef_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, atkOrDef_);
    }
    if (!getTargetNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, targetName_);
    }
    if (!getAllianceOrLvBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, allianceOrLv_);
    }
    if (reportId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(7, reportId_);
    }
    if (mainIconId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, mainIconId_);
    }
    if (iconId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(9, iconId_);
    }
    if (monsterId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(10, monsterId_);
    }
    if (world_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(11, world_);
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
    if (!(obj instanceof pb4server.SimpleFightReportVo)) {
      return super.equals(obj);
    }
    pb4server.SimpleFightReportVo other = (pb4server.SimpleFightReportVo) obj;

    boolean result = true;
    result = result && (getReportType()
        == other.getReportType());
    result = result && getMainPlayer()
        .equals(other.getMainPlayer());
    result = result && getMainPlayerAlliance()
        .equals(other.getMainPlayerAlliance());
    result = result && (getAtkOrDef()
        == other.getAtkOrDef());
    result = result && getTargetName()
        .equals(other.getTargetName());
    result = result && getAllianceOrLv()
        .equals(other.getAllianceOrLv());
    result = result && (getReportId()
        == other.getReportId());
    result = result && (getMainIconId()
        == other.getMainIconId());
    result = result && (getIconId()
        == other.getIconId());
    result = result && (getMonsterId()
        == other.getMonsterId());
    result = result && (getWorld()
        == other.getWorld());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + REPORTTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getReportType();
    hash = (37 * hash) + MAINPLAYER_FIELD_NUMBER;
    hash = (53 * hash) + getMainPlayer().hashCode();
    hash = (37 * hash) + MAINPLAYERALLIANCE_FIELD_NUMBER;
    hash = (53 * hash) + getMainPlayerAlliance().hashCode();
    hash = (37 * hash) + ATKORDEF_FIELD_NUMBER;
    hash = (53 * hash) + getAtkOrDef();
    hash = (37 * hash) + TARGETNAME_FIELD_NUMBER;
    hash = (53 * hash) + getTargetName().hashCode();
    hash = (37 * hash) + ALLIANCEORLV_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceOrLv().hashCode();
    hash = (37 * hash) + REPORTID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getReportId());
    hash = (37 * hash) + MAINICONID_FIELD_NUMBER;
    hash = (53 * hash) + getMainIconId();
    hash = (37 * hash) + ICONID_FIELD_NUMBER;
    hash = (53 * hash) + getIconId();
    hash = (37 * hash) + MONSTERID_FIELD_NUMBER;
    hash = (53 * hash) + getMonsterId();
    hash = (37 * hash) + WORLD_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getWorld());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.SimpleFightReportVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.SimpleFightReportVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.SimpleFightReportVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.SimpleFightReportVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.SimpleFightReportVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.SimpleFightReportVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.SimpleFightReportVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.SimpleFightReportVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.SimpleFightReportVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.SimpleFightReportVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.SimpleFightReportVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.SimpleFightReportVo parseFrom(
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
  public static Builder newBuilder(pb4server.SimpleFightReportVo prototype) {
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
   * Protobuf type {@code pb4server.SimpleFightReportVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.SimpleFightReportVo)
      pb4server.SimpleFightReportVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_SimpleFightReportVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_SimpleFightReportVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.SimpleFightReportVo.class, pb4server.SimpleFightReportVo.Builder.class);
    }

    // Construct using pb4server.SimpleFightReportVo.newBuilder()
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
      reportType_ = 0;

      mainPlayer_ = "";

      mainPlayerAlliance_ = "";

      atkOrDef_ = 0;

      targetName_ = "";

      allianceOrLv_ = "";

      reportId_ = 0L;

      mainIconId_ = 0;

      iconId_ = 0;

      monsterId_ = 0;

      world_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_SimpleFightReportVo_descriptor;
    }

    public pb4server.SimpleFightReportVo getDefaultInstanceForType() {
      return pb4server.SimpleFightReportVo.getDefaultInstance();
    }

    public pb4server.SimpleFightReportVo build() {
      pb4server.SimpleFightReportVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.SimpleFightReportVo buildPartial() {
      pb4server.SimpleFightReportVo result = new pb4server.SimpleFightReportVo(this);
      result.reportType_ = reportType_;
      result.mainPlayer_ = mainPlayer_;
      result.mainPlayerAlliance_ = mainPlayerAlliance_;
      result.atkOrDef_ = atkOrDef_;
      result.targetName_ = targetName_;
      result.allianceOrLv_ = allianceOrLv_;
      result.reportId_ = reportId_;
      result.mainIconId_ = mainIconId_;
      result.iconId_ = iconId_;
      result.monsterId_ = monsterId_;
      result.world_ = world_;
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
      if (other instanceof pb4server.SimpleFightReportVo) {
        return mergeFrom((pb4server.SimpleFightReportVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.SimpleFightReportVo other) {
      if (other == pb4server.SimpleFightReportVo.getDefaultInstance()) return this;
      if (other.getReportType() != 0) {
        setReportType(other.getReportType());
      }
      if (!other.getMainPlayer().isEmpty()) {
        mainPlayer_ = other.mainPlayer_;
        onChanged();
      }
      if (!other.getMainPlayerAlliance().isEmpty()) {
        mainPlayerAlliance_ = other.mainPlayerAlliance_;
        onChanged();
      }
      if (other.getAtkOrDef() != 0) {
        setAtkOrDef(other.getAtkOrDef());
      }
      if (!other.getTargetName().isEmpty()) {
        targetName_ = other.targetName_;
        onChanged();
      }
      if (!other.getAllianceOrLv().isEmpty()) {
        allianceOrLv_ = other.allianceOrLv_;
        onChanged();
      }
      if (other.getReportId() != 0L) {
        setReportId(other.getReportId());
      }
      if (other.getMainIconId() != 0) {
        setMainIconId(other.getMainIconId());
      }
      if (other.getIconId() != 0) {
        setIconId(other.getIconId());
      }
      if (other.getMonsterId() != 0) {
        setMonsterId(other.getMonsterId());
      }
      if (other.getWorld() != 0L) {
        setWorld(other.getWorld());
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
      pb4server.SimpleFightReportVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.SimpleFightReportVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int reportType_ ;
    /**
     * <pre>
     * 魔物,集结战报,侦察等,参考下面的reportType
     * </pre>
     *
     * <code>int32 reportType = 1;</code>
     */
    public int getReportType() {
      return reportType_;
    }
    /**
     * <pre>
     * 魔物,集结战报,侦察等,参考下面的reportType
     * </pre>
     *
     * <code>int32 reportType = 1;</code>
     */
    public Builder setReportType(int value) {
      
      reportType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 魔物,集结战报,侦察等,参考下面的reportType
     * </pre>
     *
     * <code>int32 reportType = 1;</code>
     */
    public Builder clearReportType() {
      
      reportType_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object mainPlayer_ = "";
    /**
     * <pre>
     * 左边人的名字
     * </pre>
     *
     * <code>string mainPlayer = 2;</code>
     */
    public java.lang.String getMainPlayer() {
      java.lang.Object ref = mainPlayer_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        mainPlayer_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 左边人的名字
     * </pre>
     *
     * <code>string mainPlayer = 2;</code>
     */
    public com.google.protobuf.ByteString
        getMainPlayerBytes() {
      java.lang.Object ref = mainPlayer_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        mainPlayer_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 左边人的名字
     * </pre>
     *
     * <code>string mainPlayer = 2;</code>
     */
    public Builder setMainPlayer(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      mainPlayer_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 左边人的名字
     * </pre>
     *
     * <code>string mainPlayer = 2;</code>
     */
    public Builder clearMainPlayer() {
      
      mainPlayer_ = getDefaultInstance().getMainPlayer();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 左边人的名字
     * </pre>
     *
     * <code>string mainPlayer = 2;</code>
     */
    public Builder setMainPlayerBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      mainPlayer_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object mainPlayerAlliance_ = "";
    /**
     * <pre>
     * 左边人的联盟
     * </pre>
     *
     * <code>string mainPlayerAlliance = 3;</code>
     */
    public java.lang.String getMainPlayerAlliance() {
      java.lang.Object ref = mainPlayerAlliance_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        mainPlayerAlliance_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 左边人的联盟
     * </pre>
     *
     * <code>string mainPlayerAlliance = 3;</code>
     */
    public com.google.protobuf.ByteString
        getMainPlayerAllianceBytes() {
      java.lang.Object ref = mainPlayerAlliance_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        mainPlayerAlliance_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 左边人的联盟
     * </pre>
     *
     * <code>string mainPlayerAlliance = 3;</code>
     */
    public Builder setMainPlayerAlliance(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      mainPlayerAlliance_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 左边人的联盟
     * </pre>
     *
     * <code>string mainPlayerAlliance = 3;</code>
     */
    public Builder clearMainPlayerAlliance() {
      
      mainPlayerAlliance_ = getDefaultInstance().getMainPlayerAlliance();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 左边人的联盟
     * </pre>
     *
     * <code>string mainPlayerAlliance = 3;</code>
     */
    public Builder setMainPlayerAllianceBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      mainPlayerAlliance_ = value;
      onChanged();
      return this;
    }

    private int atkOrDef_ ;
    /**
     * <pre>
     * 攻击或防守 0进攻 1防守
     * </pre>
     *
     * <code>int32 atkOrDef = 4;</code>
     */
    public int getAtkOrDef() {
      return atkOrDef_;
    }
    /**
     * <pre>
     * 攻击或防守 0进攻 1防守
     * </pre>
     *
     * <code>int32 atkOrDef = 4;</code>
     */
    public Builder setAtkOrDef(int value) {
      
      atkOrDef_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 攻击或防守 0进攻 1防守
     * </pre>
     *
     * <code>int32 atkOrDef = 4;</code>
     */
    public Builder clearAtkOrDef() {
      
      atkOrDef_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object targetName_ = "";
    /**
     * <pre>
     * 右边魔物或者对手的名字
     * </pre>
     *
     * <code>string targetName = 5;</code>
     */
    public java.lang.String getTargetName() {
      java.lang.Object ref = targetName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        targetName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 右边魔物或者对手的名字
     * </pre>
     *
     * <code>string targetName = 5;</code>
     */
    public com.google.protobuf.ByteString
        getTargetNameBytes() {
      java.lang.Object ref = targetName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        targetName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 右边魔物或者对手的名字
     * </pre>
     *
     * <code>string targetName = 5;</code>
     */
    public Builder setTargetName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      targetName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 右边魔物或者对手的名字
     * </pre>
     *
     * <code>string targetName = 5;</code>
     */
    public Builder clearTargetName() {
      
      targetName_ = getDefaultInstance().getTargetName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 右边魔物或者对手的名字
     * </pre>
     *
     * <code>string targetName = 5;</code>
     */
    public Builder setTargetNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      targetName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object allianceOrLv_ = "";
    /**
     * <pre>
     * 右边魔物等级或者对手的联盟名
     * </pre>
     *
     * <code>string allianceOrLv = 6;</code>
     */
    public java.lang.String getAllianceOrLv() {
      java.lang.Object ref = allianceOrLv_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        allianceOrLv_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 右边魔物等级或者对手的联盟名
     * </pre>
     *
     * <code>string allianceOrLv = 6;</code>
     */
    public com.google.protobuf.ByteString
        getAllianceOrLvBytes() {
      java.lang.Object ref = allianceOrLv_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        allianceOrLv_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 右边魔物等级或者对手的联盟名
     * </pre>
     *
     * <code>string allianceOrLv = 6;</code>
     */
    public Builder setAllianceOrLv(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      allianceOrLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 右边魔物等级或者对手的联盟名
     * </pre>
     *
     * <code>string allianceOrLv = 6;</code>
     */
    public Builder clearAllianceOrLv() {
      
      allianceOrLv_ = getDefaultInstance().getAllianceOrLv();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 右边魔物等级或者对手的联盟名
     * </pre>
     *
     * <code>string allianceOrLv = 6;</code>
     */
    public Builder setAllianceOrLvBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      allianceOrLv_ = value;
      onChanged();
      return this;
    }

    private long reportId_ ;
    /**
     * <pre>
     * report战报id
     * </pre>
     *
     * <code>int64 reportId = 7;</code>
     */
    public long getReportId() {
      return reportId_;
    }
    /**
     * <pre>
     * report战报id
     * </pre>
     *
     * <code>int64 reportId = 7;</code>
     */
    public Builder setReportId(long value) {
      
      reportId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * report战报id
     * </pre>
     *
     * <code>int64 reportId = 7;</code>
     */
    public Builder clearReportId() {
      
      reportId_ = 0L;
      onChanged();
      return this;
    }

    private int mainIconId_ ;
    /**
     * <pre>
     * 左边人头像模板
     * </pre>
     *
     * <code>int32 mainIconId = 8;</code>
     */
    public int getMainIconId() {
      return mainIconId_;
    }
    /**
     * <pre>
     * 左边人头像模板
     * </pre>
     *
     * <code>int32 mainIconId = 8;</code>
     */
    public Builder setMainIconId(int value) {
      
      mainIconId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 左边人头像模板
     * </pre>
     *
     * <code>int32 mainIconId = 8;</code>
     */
    public Builder clearMainIconId() {
      
      mainIconId_ = 0;
      onChanged();
      return this;
    }

    private int iconId_ ;
    /**
     * <pre>
     * 右边人头像模板
     * </pre>
     *
     * <code>int32 iconId = 9;</code>
     */
    public int getIconId() {
      return iconId_;
    }
    /**
     * <pre>
     * 右边人头像模板
     * </pre>
     *
     * <code>int32 iconId = 9;</code>
     */
    public Builder setIconId(int value) {
      
      iconId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 右边人头像模板
     * </pre>
     *
     * <code>int32 iconId = 9;</code>
     */
    public Builder clearIconId() {
      
      iconId_ = 0;
      onChanged();
      return this;
    }

    private int monsterId_ ;
    /**
     * <pre>
     * 魔物模板Id
     * </pre>
     *
     * <code>int32 monsterId = 10;</code>
     */
    public int getMonsterId() {
      return monsterId_;
    }
    /**
     * <pre>
     * 魔物模板Id
     * </pre>
     *
     * <code>int32 monsterId = 10;</code>
     */
    public Builder setMonsterId(int value) {
      
      monsterId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 魔物模板Id
     * </pre>
     *
     * <code>int32 monsterId = 10;</code>
     */
    public Builder clearMonsterId() {
      
      monsterId_ = 0;
      onChanged();
      return this;
    }

    private long world_ ;
    /**
     * <pre>
     * 世界服id
     * </pre>
     *
     * <code>int64 world = 11;</code>
     */
    public long getWorld() {
      return world_;
    }
    /**
     * <pre>
     * 世界服id
     * </pre>
     *
     * <code>int64 world = 11;</code>
     */
    public Builder setWorld(long value) {
      
      world_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 世界服id
     * </pre>
     *
     * <code>int64 world = 11;</code>
     */
    public Builder clearWorld() {
      
      world_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:pb4server.SimpleFightReportVo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.SimpleFightReportVo)
  private static final pb4server.SimpleFightReportVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.SimpleFightReportVo();
  }

  public static pb4server.SimpleFightReportVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SimpleFightReportVo>
      PARSER = new com.google.protobuf.AbstractParser<SimpleFightReportVo>() {
    public SimpleFightReportVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SimpleFightReportVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SimpleFightReportVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SimpleFightReportVo> getParserForType() {
    return PARSER;
  }

  public pb4server.SimpleFightReportVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
