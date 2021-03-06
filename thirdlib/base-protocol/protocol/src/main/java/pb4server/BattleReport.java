// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.BattleReport}
 */
public  final class BattleReport extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.BattleReport)
    BattleReportOrBuilder {
  // Use BattleReport.newBuilder() to construct.
  private BattleReport(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BattleReport() {
    readState_ = 0;
    reportType_ = 0;
    fightTime_ = 0L;
    posX_ = 0;
    posY_ = 0;
    pastTime_ = 0L;
    reportContent_ = com.google.protobuf.ByteString.EMPTY;
    fightDetail_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private BattleReport(
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

            readState_ = input.readInt32();
            break;
          }
          case 16: {

            reportType_ = input.readInt32();
            break;
          }
          case 24: {

            fightTime_ = input.readInt64();
            break;
          }
          case 32: {

            posX_ = input.readInt32();
            break;
          }
          case 40: {

            posY_ = input.readInt32();
            break;
          }
          case 48: {

            pastTime_ = input.readInt64();
            break;
          }
          case 82: {

            reportContent_ = input.readBytes();
            break;
          }
          case 90: {

            fightDetail_ = input.readBytes();
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
    return pb4server.InternalHkt.internal_static_pb4server_BattleReport_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_BattleReport_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.BattleReport.class, pb4server.BattleReport.Builder.class);
  }

  public static final int READSTATE_FIELD_NUMBER = 1;
  private int readState_;
  /**
   * <code>int32 readState = 1;</code>
   */
  public int getReadState() {
    return readState_;
  }

  public static final int REPORTTYPE_FIELD_NUMBER = 2;
  private int reportType_;
  /**
   * <code>int32 reportType = 2;</code>
   */
  public int getReportType() {
    return reportType_;
  }

  public static final int FIGHTTIME_FIELD_NUMBER = 3;
  private long fightTime_;
  /**
   * <code>int64 fightTime = 3;</code>
   */
  public long getFightTime() {
    return fightTime_;
  }

  public static final int POSX_FIELD_NUMBER = 4;
  private int posX_;
  /**
   * <code>int32 posX = 4;</code>
   */
  public int getPosX() {
    return posX_;
  }

  public static final int POSY_FIELD_NUMBER = 5;
  private int posY_;
  /**
   * <code>int32 posY = 5;</code>
   */
  public int getPosY() {
    return posY_;
  }

  public static final int PASTTIME_FIELD_NUMBER = 6;
  private long pastTime_;
  /**
   * <code>int64 pastTime = 6;</code>
   */
  public long getPastTime() {
    return pastTime_;
  }

  public static final int REPORTCONTENT_FIELD_NUMBER = 10;
  private com.google.protobuf.ByteString reportContent_;
  /**
   * <code>bytes reportContent = 10;</code>
   */
  public com.google.protobuf.ByteString getReportContent() {
    return reportContent_;
  }

  public static final int FIGHTDETAIL_FIELD_NUMBER = 11;
  private com.google.protobuf.ByteString fightDetail_;
  /**
   * <code>bytes fightDetail = 11;</code>
   */
  public com.google.protobuf.ByteString getFightDetail() {
    return fightDetail_;
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
    if (readState_ != 0) {
      output.writeInt32(1, readState_);
    }
    if (reportType_ != 0) {
      output.writeInt32(2, reportType_);
    }
    if (fightTime_ != 0L) {
      output.writeInt64(3, fightTime_);
    }
    if (posX_ != 0) {
      output.writeInt32(4, posX_);
    }
    if (posY_ != 0) {
      output.writeInt32(5, posY_);
    }
    if (pastTime_ != 0L) {
      output.writeInt64(6, pastTime_);
    }
    if (!reportContent_.isEmpty()) {
      output.writeBytes(10, reportContent_);
    }
    if (!fightDetail_.isEmpty()) {
      output.writeBytes(11, fightDetail_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (readState_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, readState_);
    }
    if (reportType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, reportType_);
    }
    if (fightTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, fightTime_);
    }
    if (posX_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, posX_);
    }
    if (posY_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, posY_);
    }
    if (pastTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(6, pastTime_);
    }
    if (!reportContent_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(10, reportContent_);
    }
    if (!fightDetail_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(11, fightDetail_);
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
    if (!(obj instanceof pb4server.BattleReport)) {
      return super.equals(obj);
    }
    pb4server.BattleReport other = (pb4server.BattleReport) obj;

    boolean result = true;
    result = result && (getReadState()
        == other.getReadState());
    result = result && (getReportType()
        == other.getReportType());
    result = result && (getFightTime()
        == other.getFightTime());
    result = result && (getPosX()
        == other.getPosX());
    result = result && (getPosY()
        == other.getPosY());
    result = result && (getPastTime()
        == other.getPastTime());
    result = result && getReportContent()
        .equals(other.getReportContent());
    result = result && getFightDetail()
        .equals(other.getFightDetail());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + READSTATE_FIELD_NUMBER;
    hash = (53 * hash) + getReadState();
    hash = (37 * hash) + REPORTTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getReportType();
    hash = (37 * hash) + FIGHTTIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFightTime());
    hash = (37 * hash) + POSX_FIELD_NUMBER;
    hash = (53 * hash) + getPosX();
    hash = (37 * hash) + POSY_FIELD_NUMBER;
    hash = (53 * hash) + getPosY();
    hash = (37 * hash) + PASTTIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPastTime());
    hash = (37 * hash) + REPORTCONTENT_FIELD_NUMBER;
    hash = (53 * hash) + getReportContent().hashCode();
    hash = (37 * hash) + FIGHTDETAIL_FIELD_NUMBER;
    hash = (53 * hash) + getFightDetail().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.BattleReport parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.BattleReport parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.BattleReport parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.BattleReport parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.BattleReport parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.BattleReport parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.BattleReport parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.BattleReport parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.BattleReport parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.BattleReport parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.BattleReport parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.BattleReport parseFrom(
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
  public static Builder newBuilder(pb4server.BattleReport prototype) {
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
   * Protobuf type {@code pb4server.BattleReport}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.BattleReport)
      pb4server.BattleReportOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_BattleReport_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_BattleReport_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.BattleReport.class, pb4server.BattleReport.Builder.class);
    }

    // Construct using pb4server.BattleReport.newBuilder()
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
      readState_ = 0;

      reportType_ = 0;

      fightTime_ = 0L;

      posX_ = 0;

      posY_ = 0;

      pastTime_ = 0L;

      reportContent_ = com.google.protobuf.ByteString.EMPTY;

      fightDetail_ = com.google.protobuf.ByteString.EMPTY;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_BattleReport_descriptor;
    }

    public pb4server.BattleReport getDefaultInstanceForType() {
      return pb4server.BattleReport.getDefaultInstance();
    }

    public pb4server.BattleReport build() {
      pb4server.BattleReport result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.BattleReport buildPartial() {
      pb4server.BattleReport result = new pb4server.BattleReport(this);
      result.readState_ = readState_;
      result.reportType_ = reportType_;
      result.fightTime_ = fightTime_;
      result.posX_ = posX_;
      result.posY_ = posY_;
      result.pastTime_ = pastTime_;
      result.reportContent_ = reportContent_;
      result.fightDetail_ = fightDetail_;
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
      if (other instanceof pb4server.BattleReport) {
        return mergeFrom((pb4server.BattleReport)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.BattleReport other) {
      if (other == pb4server.BattleReport.getDefaultInstance()) return this;
      if (other.getReadState() != 0) {
        setReadState(other.getReadState());
      }
      if (other.getReportType() != 0) {
        setReportType(other.getReportType());
      }
      if (other.getFightTime() != 0L) {
        setFightTime(other.getFightTime());
      }
      if (other.getPosX() != 0) {
        setPosX(other.getPosX());
      }
      if (other.getPosY() != 0) {
        setPosY(other.getPosY());
      }
      if (other.getPastTime() != 0L) {
        setPastTime(other.getPastTime());
      }
      if (other.getReportContent() != com.google.protobuf.ByteString.EMPTY) {
        setReportContent(other.getReportContent());
      }
      if (other.getFightDetail() != com.google.protobuf.ByteString.EMPTY) {
        setFightDetail(other.getFightDetail());
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
      pb4server.BattleReport parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.BattleReport) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int readState_ ;
    /**
     * <code>int32 readState = 1;</code>
     */
    public int getReadState() {
      return readState_;
    }
    /**
     * <code>int32 readState = 1;</code>
     */
    public Builder setReadState(int value) {
      
      readState_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 readState = 1;</code>
     */
    public Builder clearReadState() {
      
      readState_ = 0;
      onChanged();
      return this;
    }

    private int reportType_ ;
    /**
     * <code>int32 reportType = 2;</code>
     */
    public int getReportType() {
      return reportType_;
    }
    /**
     * <code>int32 reportType = 2;</code>
     */
    public Builder setReportType(int value) {
      
      reportType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 reportType = 2;</code>
     */
    public Builder clearReportType() {
      
      reportType_ = 0;
      onChanged();
      return this;
    }

    private long fightTime_ ;
    /**
     * <code>int64 fightTime = 3;</code>
     */
    public long getFightTime() {
      return fightTime_;
    }
    /**
     * <code>int64 fightTime = 3;</code>
     */
    public Builder setFightTime(long value) {
      
      fightTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 fightTime = 3;</code>
     */
    public Builder clearFightTime() {
      
      fightTime_ = 0L;
      onChanged();
      return this;
    }

    private int posX_ ;
    /**
     * <code>int32 posX = 4;</code>
     */
    public int getPosX() {
      return posX_;
    }
    /**
     * <code>int32 posX = 4;</code>
     */
    public Builder setPosX(int value) {
      
      posX_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 posX = 4;</code>
     */
    public Builder clearPosX() {
      
      posX_ = 0;
      onChanged();
      return this;
    }

    private int posY_ ;
    /**
     * <code>int32 posY = 5;</code>
     */
    public int getPosY() {
      return posY_;
    }
    /**
     * <code>int32 posY = 5;</code>
     */
    public Builder setPosY(int value) {
      
      posY_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 posY = 5;</code>
     */
    public Builder clearPosY() {
      
      posY_ = 0;
      onChanged();
      return this;
    }

    private long pastTime_ ;
    /**
     * <code>int64 pastTime = 6;</code>
     */
    public long getPastTime() {
      return pastTime_;
    }
    /**
     * <code>int64 pastTime = 6;</code>
     */
    public Builder setPastTime(long value) {
      
      pastTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 pastTime = 6;</code>
     */
    public Builder clearPastTime() {
      
      pastTime_ = 0L;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString reportContent_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes reportContent = 10;</code>
     */
    public com.google.protobuf.ByteString getReportContent() {
      return reportContent_;
    }
    /**
     * <code>bytes reportContent = 10;</code>
     */
    public Builder setReportContent(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      reportContent_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes reportContent = 10;</code>
     */
    public Builder clearReportContent() {
      
      reportContent_ = getDefaultInstance().getReportContent();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString fightDetail_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes fightDetail = 11;</code>
     */
    public com.google.protobuf.ByteString getFightDetail() {
      return fightDetail_;
    }
    /**
     * <code>bytes fightDetail = 11;</code>
     */
    public Builder setFightDetail(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      fightDetail_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes fightDetail = 11;</code>
     */
    public Builder clearFightDetail() {
      
      fightDetail_ = getDefaultInstance().getFightDetail();
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


    // @@protoc_insertion_point(builder_scope:pb4server.BattleReport)
  }

  // @@protoc_insertion_point(class_scope:pb4server.BattleReport)
  private static final pb4server.BattleReport DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.BattleReport();
  }

  public static pb4server.BattleReport getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<BattleReport>
      PARSER = new com.google.protobuf.AbstractParser<BattleReport>() {
    public BattleReport parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BattleReport(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BattleReport> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BattleReport> getParserForType() {
    return PARSER;
  }

  public pb4server.BattleReport getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

