// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 *英雄数据
 * </pre>
 *
 * Protobuf type {@code client2server.HeroInfoForReport}
 */
public  final class HeroInfoForReport extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.HeroInfoForReport)
    HeroInfoForReportOrBuilder {
  // Use HeroInfoForReport.newBuilder() to construct.
  private HeroInfoForReport(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HeroInfoForReport() {
    protoId_ = 0;
    lv_ = 0;
    starLv_ = 0;
    grade_ = 0;
    isLarid_ = 0;
    curExp_ = 0;
    addExp_ = 0;
    expOverFlow_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private HeroInfoForReport(
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
          case 8: {
            bitField0_ |= 0x00000001;
            protoId_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            lv_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            starLv_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            grade_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            isLarid_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            curExp_ = input.readInt32();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            addExp_ = input.readInt32();
            break;
          }
          case 64: {
            bitField0_ |= 0x00000080;
            expOverFlow_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_HeroInfoForReport_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_HeroInfoForReport_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.HeroInfoForReport.class, pb4client.HeroInfoForReport.Builder.class);
  }

  private int bitField0_;
  public static final int PROTOID_FIELD_NUMBER = 1;
  private int protoId_;
  /**
   * <pre>
   *配置ID
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  public boolean hasProtoId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *配置ID
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  public int getProtoId() {
    return protoId_;
  }

  public static final int LV_FIELD_NUMBER = 2;
  private int lv_;
  /**
   * <pre>
   *等级
   * </pre>
   *
   * <code>optional int32 lv = 2;</code>
   */
  public boolean hasLv() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *等级
   * </pre>
   *
   * <code>optional int32 lv = 2;</code>
   */
  public int getLv() {
    return lv_;
  }

  public static final int STARLV_FIELD_NUMBER = 3;
  private int starLv_;
  /**
   * <pre>
   *星级
   * </pre>
   *
   * <code>optional int32 starLv = 3;</code>
   */
  public boolean hasStarLv() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *星级
   * </pre>
   *
   * <code>optional int32 starLv = 3;</code>
   */
  public int getStarLv() {
    return starLv_;
  }

  public static final int GRADE_FIELD_NUMBER = 4;
  private int grade_;
  /**
   * <pre>
   *军阶
   * </pre>
   *
   * <code>optional int32 grade = 4;</code>
   */
  public boolean hasGrade() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *军阶
   * </pre>
   *
   * <code>optional int32 grade = 4;</code>
   */
  public int getGrade() {
    return grade_;
  }

  public static final int ISLARID_FIELD_NUMBER = 5;
  private int isLarid_;
  /**
   * <pre>
   *是否是领主
   * </pre>
   *
   * <code>optional int32 isLarid = 5;</code>
   */
  public boolean hasIsLarid() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   *是否是领主
   * </pre>
   *
   * <code>optional int32 isLarid = 5;</code>
   */
  public int getIsLarid() {
    return isLarid_;
  }

  public static final int CUREXP_FIELD_NUMBER = 6;
  private int curExp_;
  /**
   * <pre>
   *当前经验
   * </pre>
   *
   * <code>optional int32 curExp = 6;</code>
   */
  public boolean hasCurExp() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   *当前经验
   * </pre>
   *
   * <code>optional int32 curExp = 6;</code>
   */
  public int getCurExp() {
    return curExp_;
  }

  public static final int ADDEXP_FIELD_NUMBER = 7;
  private int addExp_;
  /**
   * <pre>
   *增加的经验
   * </pre>
   *
   * <code>optional int32 addExp = 7;</code>
   */
  public boolean hasAddExp() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <pre>
   *增加的经验
   * </pre>
   *
   * <code>optional int32 addExp = 7;</code>
   */
  public int getAddExp() {
    return addExp_;
  }

  public static final int EXPOVERFLOW_FIELD_NUMBER = 8;
  private int expOverFlow_;
  /**
   * <pre>
   *经验是否溢出
   * </pre>
   *
   * <code>optional int32 expOverFlow = 8;</code>
   */
  public boolean hasExpOverFlow() {
    return ((bitField0_ & 0x00000080) == 0x00000080);
  }
  /**
   * <pre>
   *经验是否溢出
   * </pre>
   *
   * <code>optional int32 expOverFlow = 8;</code>
   */
  public int getExpOverFlow() {
    return expOverFlow_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasProtoId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, protoId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, lv_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, starLv_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, grade_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, isLarid_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt32(6, curExp_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, addExp_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      output.writeInt32(8, expOverFlow_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, protoId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, lv_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, starLv_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, grade_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, isLarid_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, curExp_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, addExp_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, expOverFlow_);
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
    if (!(obj instanceof pb4client.HeroInfoForReport)) {
      return super.equals(obj);
    }
    pb4client.HeroInfoForReport other = (pb4client.HeroInfoForReport) obj;

    boolean result = true;
    result = result && (hasProtoId() == other.hasProtoId());
    if (hasProtoId()) {
      result = result && (getProtoId()
          == other.getProtoId());
    }
    result = result && (hasLv() == other.hasLv());
    if (hasLv()) {
      result = result && (getLv()
          == other.getLv());
    }
    result = result && (hasStarLv() == other.hasStarLv());
    if (hasStarLv()) {
      result = result && (getStarLv()
          == other.getStarLv());
    }
    result = result && (hasGrade() == other.hasGrade());
    if (hasGrade()) {
      result = result && (getGrade()
          == other.getGrade());
    }
    result = result && (hasIsLarid() == other.hasIsLarid());
    if (hasIsLarid()) {
      result = result && (getIsLarid()
          == other.getIsLarid());
    }
    result = result && (hasCurExp() == other.hasCurExp());
    if (hasCurExp()) {
      result = result && (getCurExp()
          == other.getCurExp());
    }
    result = result && (hasAddExp() == other.hasAddExp());
    if (hasAddExp()) {
      result = result && (getAddExp()
          == other.getAddExp());
    }
    result = result && (hasExpOverFlow() == other.hasExpOverFlow());
    if (hasExpOverFlow()) {
      result = result && (getExpOverFlow()
          == other.getExpOverFlow());
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
    if (hasProtoId()) {
      hash = (37 * hash) + PROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getProtoId();
    }
    if (hasLv()) {
      hash = (37 * hash) + LV_FIELD_NUMBER;
      hash = (53 * hash) + getLv();
    }
    if (hasStarLv()) {
      hash = (37 * hash) + STARLV_FIELD_NUMBER;
      hash = (53 * hash) + getStarLv();
    }
    if (hasGrade()) {
      hash = (37 * hash) + GRADE_FIELD_NUMBER;
      hash = (53 * hash) + getGrade();
    }
    if (hasIsLarid()) {
      hash = (37 * hash) + ISLARID_FIELD_NUMBER;
      hash = (53 * hash) + getIsLarid();
    }
    if (hasCurExp()) {
      hash = (37 * hash) + CUREXP_FIELD_NUMBER;
      hash = (53 * hash) + getCurExp();
    }
    if (hasAddExp()) {
      hash = (37 * hash) + ADDEXP_FIELD_NUMBER;
      hash = (53 * hash) + getAddExp();
    }
    if (hasExpOverFlow()) {
      hash = (37 * hash) + EXPOVERFLOW_FIELD_NUMBER;
      hash = (53 * hash) + getExpOverFlow();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.HeroInfoForReport parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroInfoForReport parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroInfoForReport parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroInfoForReport parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroInfoForReport parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroInfoForReport parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroInfoForReport parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.HeroInfoForReport parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.HeroInfoForReport parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.HeroInfoForReport parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.HeroInfoForReport parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.HeroInfoForReport parseFrom(
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
  public static Builder newBuilder(pb4client.HeroInfoForReport prototype) {
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
   *英雄数据
   * </pre>
   *
   * Protobuf type {@code client2server.HeroInfoForReport}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.HeroInfoForReport)
      pb4client.HeroInfoForReportOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroInfoForReport_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroInfoForReport_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.HeroInfoForReport.class, pb4client.HeroInfoForReport.Builder.class);
    }

    // Construct using pb4client.HeroInfoForReport.newBuilder()
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
      protoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      lv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      starLv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      grade_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      isLarid_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      curExp_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      addExp_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      expOverFlow_ = 0;
      bitField0_ = (bitField0_ & ~0x00000080);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroInfoForReport_descriptor;
    }

    public pb4client.HeroInfoForReport getDefaultInstanceForType() {
      return pb4client.HeroInfoForReport.getDefaultInstance();
    }

    public pb4client.HeroInfoForReport build() {
      pb4client.HeroInfoForReport result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.HeroInfoForReport buildPartial() {
      pb4client.HeroInfoForReport result = new pb4client.HeroInfoForReport(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.protoId_ = protoId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.lv_ = lv_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.starLv_ = starLv_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.grade_ = grade_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.isLarid_ = isLarid_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.curExp_ = curExp_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.addExp_ = addExp_;
      if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
        to_bitField0_ |= 0x00000080;
      }
      result.expOverFlow_ = expOverFlow_;
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
      if (other instanceof pb4client.HeroInfoForReport) {
        return mergeFrom((pb4client.HeroInfoForReport)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.HeroInfoForReport other) {
      if (other == pb4client.HeroInfoForReport.getDefaultInstance()) return this;
      if (other.hasProtoId()) {
        setProtoId(other.getProtoId());
      }
      if (other.hasLv()) {
        setLv(other.getLv());
      }
      if (other.hasStarLv()) {
        setStarLv(other.getStarLv());
      }
      if (other.hasGrade()) {
        setGrade(other.getGrade());
      }
      if (other.hasIsLarid()) {
        setIsLarid(other.getIsLarid());
      }
      if (other.hasCurExp()) {
        setCurExp(other.getCurExp());
      }
      if (other.hasAddExp()) {
        setAddExp(other.getAddExp());
      }
      if (other.hasExpOverFlow()) {
        setExpOverFlow(other.getExpOverFlow());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasProtoId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.HeroInfoForReport parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.HeroInfoForReport) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int protoId_ ;
    /**
     * <pre>
     *配置ID
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public boolean hasProtoId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *配置ID
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public int getProtoId() {
      return protoId_;
    }
    /**
     * <pre>
     *配置ID
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public Builder setProtoId(int value) {
      bitField0_ |= 0x00000001;
      protoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *配置ID
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public Builder clearProtoId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      protoId_ = 0;
      onChanged();
      return this;
    }

    private int lv_ ;
    /**
     * <pre>
     *等级
     * </pre>
     *
     * <code>optional int32 lv = 2;</code>
     */
    public boolean hasLv() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *等级
     * </pre>
     *
     * <code>optional int32 lv = 2;</code>
     */
    public int getLv() {
      return lv_;
    }
    /**
     * <pre>
     *等级
     * </pre>
     *
     * <code>optional int32 lv = 2;</code>
     */
    public Builder setLv(int value) {
      bitField0_ |= 0x00000002;
      lv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *等级
     * </pre>
     *
     * <code>optional int32 lv = 2;</code>
     */
    public Builder clearLv() {
      bitField0_ = (bitField0_ & ~0x00000002);
      lv_ = 0;
      onChanged();
      return this;
    }

    private int starLv_ ;
    /**
     * <pre>
     *星级
     * </pre>
     *
     * <code>optional int32 starLv = 3;</code>
     */
    public boolean hasStarLv() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *星级
     * </pre>
     *
     * <code>optional int32 starLv = 3;</code>
     */
    public int getStarLv() {
      return starLv_;
    }
    /**
     * <pre>
     *星级
     * </pre>
     *
     * <code>optional int32 starLv = 3;</code>
     */
    public Builder setStarLv(int value) {
      bitField0_ |= 0x00000004;
      starLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *星级
     * </pre>
     *
     * <code>optional int32 starLv = 3;</code>
     */
    public Builder clearStarLv() {
      bitField0_ = (bitField0_ & ~0x00000004);
      starLv_ = 0;
      onChanged();
      return this;
    }

    private int grade_ ;
    /**
     * <pre>
     *军阶
     * </pre>
     *
     * <code>optional int32 grade = 4;</code>
     */
    public boolean hasGrade() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *军阶
     * </pre>
     *
     * <code>optional int32 grade = 4;</code>
     */
    public int getGrade() {
      return grade_;
    }
    /**
     * <pre>
     *军阶
     * </pre>
     *
     * <code>optional int32 grade = 4;</code>
     */
    public Builder setGrade(int value) {
      bitField0_ |= 0x00000008;
      grade_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *军阶
     * </pre>
     *
     * <code>optional int32 grade = 4;</code>
     */
    public Builder clearGrade() {
      bitField0_ = (bitField0_ & ~0x00000008);
      grade_ = 0;
      onChanged();
      return this;
    }

    private int isLarid_ ;
    /**
     * <pre>
     *是否是领主
     * </pre>
     *
     * <code>optional int32 isLarid = 5;</code>
     */
    public boolean hasIsLarid() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *是否是领主
     * </pre>
     *
     * <code>optional int32 isLarid = 5;</code>
     */
    public int getIsLarid() {
      return isLarid_;
    }
    /**
     * <pre>
     *是否是领主
     * </pre>
     *
     * <code>optional int32 isLarid = 5;</code>
     */
    public Builder setIsLarid(int value) {
      bitField0_ |= 0x00000010;
      isLarid_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *是否是领主
     * </pre>
     *
     * <code>optional int32 isLarid = 5;</code>
     */
    public Builder clearIsLarid() {
      bitField0_ = (bitField0_ & ~0x00000010);
      isLarid_ = 0;
      onChanged();
      return this;
    }

    private int curExp_ ;
    /**
     * <pre>
     *当前经验
     * </pre>
     *
     * <code>optional int32 curExp = 6;</code>
     */
    public boolean hasCurExp() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     *当前经验
     * </pre>
     *
     * <code>optional int32 curExp = 6;</code>
     */
    public int getCurExp() {
      return curExp_;
    }
    /**
     * <pre>
     *当前经验
     * </pre>
     *
     * <code>optional int32 curExp = 6;</code>
     */
    public Builder setCurExp(int value) {
      bitField0_ |= 0x00000020;
      curExp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前经验
     * </pre>
     *
     * <code>optional int32 curExp = 6;</code>
     */
    public Builder clearCurExp() {
      bitField0_ = (bitField0_ & ~0x00000020);
      curExp_ = 0;
      onChanged();
      return this;
    }

    private int addExp_ ;
    /**
     * <pre>
     *增加的经验
     * </pre>
     *
     * <code>optional int32 addExp = 7;</code>
     */
    public boolean hasAddExp() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <pre>
     *增加的经验
     * </pre>
     *
     * <code>optional int32 addExp = 7;</code>
     */
    public int getAddExp() {
      return addExp_;
    }
    /**
     * <pre>
     *增加的经验
     * </pre>
     *
     * <code>optional int32 addExp = 7;</code>
     */
    public Builder setAddExp(int value) {
      bitField0_ |= 0x00000040;
      addExp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *增加的经验
     * </pre>
     *
     * <code>optional int32 addExp = 7;</code>
     */
    public Builder clearAddExp() {
      bitField0_ = (bitField0_ & ~0x00000040);
      addExp_ = 0;
      onChanged();
      return this;
    }

    private int expOverFlow_ ;
    /**
     * <pre>
     *经验是否溢出
     * </pre>
     *
     * <code>optional int32 expOverFlow = 8;</code>
     */
    public boolean hasExpOverFlow() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    /**
     * <pre>
     *经验是否溢出
     * </pre>
     *
     * <code>optional int32 expOverFlow = 8;</code>
     */
    public int getExpOverFlow() {
      return expOverFlow_;
    }
    /**
     * <pre>
     *经验是否溢出
     * </pre>
     *
     * <code>optional int32 expOverFlow = 8;</code>
     */
    public Builder setExpOverFlow(int value) {
      bitField0_ |= 0x00000080;
      expOverFlow_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *经验是否溢出
     * </pre>
     *
     * <code>optional int32 expOverFlow = 8;</code>
     */
    public Builder clearExpOverFlow() {
      bitField0_ = (bitField0_ & ~0x00000080);
      expOverFlow_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.HeroInfoForReport)
  }

  // @@protoc_insertion_point(class_scope:client2server.HeroInfoForReport)
  private static final pb4client.HeroInfoForReport DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.HeroInfoForReport();
  }

  public static pb4client.HeroInfoForReport getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<HeroInfoForReport>
      PARSER = new com.google.protobuf.AbstractParser<HeroInfoForReport>() {
    public HeroInfoForReport parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new HeroInfoForReport(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HeroInfoForReport> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<HeroInfoForReport> getParserForType() {
    return PARSER;
  }

  public pb4client.HeroInfoForReport getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

