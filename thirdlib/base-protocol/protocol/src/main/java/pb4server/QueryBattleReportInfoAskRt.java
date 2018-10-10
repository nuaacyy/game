// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.QueryBattleReportInfoAskRt}
 */
public  final class QueryBattleReportInfoAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.QueryBattleReportInfoAskRt)
    QueryBattleReportInfoAskRtOrBuilder {
  // Use QueryBattleReportInfoAskRt.newBuilder() to construct.
  private QueryBattleReportInfoAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryBattleReportInfoAskRt() {
    rt_ = 0;
    reportType_ = 0;
    soliderFightReport_ = "";
    heroFightReport_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private QueryBattleReportInfoAskRt(
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
          case 24: {

            reportType_ = input.readInt32();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            soliderFightReport_ = s;
            break;
          }
          case 42: {
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
              heroFightReport_ = new java.util.ArrayList<pb4client.HeroFightReport>();
              mutable_bitField0_ |= 0x00000008;
            }
            heroFightReport_.add(
                input.readMessage(pb4client.HeroFightReport.PARSER, extensionRegistry));
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
        heroFightReport_ = java.util.Collections.unmodifiableList(heroFightReport_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalHkt.internal_static_pb4server_QueryBattleReportInfoAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_QueryBattleReportInfoAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.QueryBattleReportInfoAskRt.class, pb4server.QueryBattleReportInfoAskRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int REPORTTYPE_FIELD_NUMBER = 3;
  private int reportType_;
  /**
   * <code>int32 reportType = 3;</code>
   */
  public int getReportType() {
    return reportType_;
  }

  public static final int SOLIDERFIGHTREPORT_FIELD_NUMBER = 4;
  private volatile java.lang.Object soliderFightReport_;
  /**
   * <code>string soliderFightReport = 4;</code>
   */
  public java.lang.String getSoliderFightReport() {
    java.lang.Object ref = soliderFightReport_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      soliderFightReport_ = s;
      return s;
    }
  }
  /**
   * <code>string soliderFightReport = 4;</code>
   */
  public com.google.protobuf.ByteString
      getSoliderFightReportBytes() {
    java.lang.Object ref = soliderFightReport_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      soliderFightReport_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int HEROFIGHTREPORT_FIELD_NUMBER = 5;
  private java.util.List<pb4client.HeroFightReport> heroFightReport_;
  /**
   * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
   */
  public java.util.List<pb4client.HeroFightReport> getHeroFightReportList() {
    return heroFightReport_;
  }
  /**
   * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
   */
  public java.util.List<? extends pb4client.HeroFightReportOrBuilder> 
      getHeroFightReportOrBuilderList() {
    return heroFightReport_;
  }
  /**
   * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
   */
  public int getHeroFightReportCount() {
    return heroFightReport_.size();
  }
  /**
   * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
   */
  public pb4client.HeroFightReport getHeroFightReport(int index) {
    return heroFightReport_.get(index);
  }
  /**
   * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
   */
  public pb4client.HeroFightReportOrBuilder getHeroFightReportOrBuilder(
      int index) {
    return heroFightReport_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    for (int i = 0; i < getHeroFightReportCount(); i++) {
      if (!getHeroFightReport(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (rt_ != 0) {
      output.writeInt32(1, rt_);
    }
    if (reportType_ != 0) {
      output.writeInt32(3, reportType_);
    }
    if (!getSoliderFightReportBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, soliderFightReport_);
    }
    for (int i = 0; i < heroFightReport_.size(); i++) {
      output.writeMessage(5, heroFightReport_.get(i));
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
    if (reportType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, reportType_);
    }
    if (!getSoliderFightReportBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, soliderFightReport_);
    }
    for (int i = 0; i < heroFightReport_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, heroFightReport_.get(i));
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
    if (!(obj instanceof pb4server.QueryBattleReportInfoAskRt)) {
      return super.equals(obj);
    }
    pb4server.QueryBattleReportInfoAskRt other = (pb4server.QueryBattleReportInfoAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && (getReportType()
        == other.getReportType());
    result = result && getSoliderFightReport()
        .equals(other.getSoliderFightReport());
    result = result && getHeroFightReportList()
        .equals(other.getHeroFightReportList());
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
    hash = (37 * hash) + REPORTTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getReportType();
    hash = (37 * hash) + SOLIDERFIGHTREPORT_FIELD_NUMBER;
    hash = (53 * hash) + getSoliderFightReport().hashCode();
    if (getHeroFightReportCount() > 0) {
      hash = (37 * hash) + HEROFIGHTREPORT_FIELD_NUMBER;
      hash = (53 * hash) + getHeroFightReportList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.QueryBattleReportInfoAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryBattleReportInfoAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryBattleReportInfoAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryBattleReportInfoAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryBattleReportInfoAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryBattleReportInfoAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryBattleReportInfoAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryBattleReportInfoAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryBattleReportInfoAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.QueryBattleReportInfoAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryBattleReportInfoAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryBattleReportInfoAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.QueryBattleReportInfoAskRt prototype) {
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
   * Protobuf type {@code pb4server.QueryBattleReportInfoAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.QueryBattleReportInfoAskRt)
      pb4server.QueryBattleReportInfoAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_QueryBattleReportInfoAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_QueryBattleReportInfoAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.QueryBattleReportInfoAskRt.class, pb4server.QueryBattleReportInfoAskRt.Builder.class);
    }

    // Construct using pb4server.QueryBattleReportInfoAskRt.newBuilder()
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
        getHeroFightReportFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;

      reportType_ = 0;

      soliderFightReport_ = "";

      if (heroFightReportBuilder_ == null) {
        heroFightReport_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000008);
      } else {
        heroFightReportBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_QueryBattleReportInfoAskRt_descriptor;
    }

    public pb4server.QueryBattleReportInfoAskRt getDefaultInstanceForType() {
      return pb4server.QueryBattleReportInfoAskRt.getDefaultInstance();
    }

    public pb4server.QueryBattleReportInfoAskRt build() {
      pb4server.QueryBattleReportInfoAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.QueryBattleReportInfoAskRt buildPartial() {
      pb4server.QueryBattleReportInfoAskRt result = new pb4server.QueryBattleReportInfoAskRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.rt_ = rt_;
      result.reportType_ = reportType_;
      result.soliderFightReport_ = soliderFightReport_;
      if (heroFightReportBuilder_ == null) {
        if (((bitField0_ & 0x00000008) == 0x00000008)) {
          heroFightReport_ = java.util.Collections.unmodifiableList(heroFightReport_);
          bitField0_ = (bitField0_ & ~0x00000008);
        }
        result.heroFightReport_ = heroFightReport_;
      } else {
        result.heroFightReport_ = heroFightReportBuilder_.build();
      }
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
      if (other instanceof pb4server.QueryBattleReportInfoAskRt) {
        return mergeFrom((pb4server.QueryBattleReportInfoAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.QueryBattleReportInfoAskRt other) {
      if (other == pb4server.QueryBattleReportInfoAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (other.getReportType() != 0) {
        setReportType(other.getReportType());
      }
      if (!other.getSoliderFightReport().isEmpty()) {
        soliderFightReport_ = other.soliderFightReport_;
        onChanged();
      }
      if (heroFightReportBuilder_ == null) {
        if (!other.heroFightReport_.isEmpty()) {
          if (heroFightReport_.isEmpty()) {
            heroFightReport_ = other.heroFightReport_;
            bitField0_ = (bitField0_ & ~0x00000008);
          } else {
            ensureHeroFightReportIsMutable();
            heroFightReport_.addAll(other.heroFightReport_);
          }
          onChanged();
        }
      } else {
        if (!other.heroFightReport_.isEmpty()) {
          if (heroFightReportBuilder_.isEmpty()) {
            heroFightReportBuilder_.dispose();
            heroFightReportBuilder_ = null;
            heroFightReport_ = other.heroFightReport_;
            bitField0_ = (bitField0_ & ~0x00000008);
            heroFightReportBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getHeroFightReportFieldBuilder() : null;
          } else {
            heroFightReportBuilder_.addAllMessages(other.heroFightReport_);
          }
        }
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      for (int i = 0; i < getHeroFightReportCount(); i++) {
        if (!getHeroFightReport(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4server.QueryBattleReportInfoAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.QueryBattleReportInfoAskRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

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

    private int reportType_ ;
    /**
     * <code>int32 reportType = 3;</code>
     */
    public int getReportType() {
      return reportType_;
    }
    /**
     * <code>int32 reportType = 3;</code>
     */
    public Builder setReportType(int value) {
      
      reportType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 reportType = 3;</code>
     */
    public Builder clearReportType() {
      
      reportType_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object soliderFightReport_ = "";
    /**
     * <code>string soliderFightReport = 4;</code>
     */
    public java.lang.String getSoliderFightReport() {
      java.lang.Object ref = soliderFightReport_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        soliderFightReport_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string soliderFightReport = 4;</code>
     */
    public com.google.protobuf.ByteString
        getSoliderFightReportBytes() {
      java.lang.Object ref = soliderFightReport_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        soliderFightReport_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string soliderFightReport = 4;</code>
     */
    public Builder setSoliderFightReport(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      soliderFightReport_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string soliderFightReport = 4;</code>
     */
    public Builder clearSoliderFightReport() {
      
      soliderFightReport_ = getDefaultInstance().getSoliderFightReport();
      onChanged();
      return this;
    }
    /**
     * <code>string soliderFightReport = 4;</code>
     */
    public Builder setSoliderFightReportBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      soliderFightReport_ = value;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.HeroFightReport> heroFightReport_ =
      java.util.Collections.emptyList();
    private void ensureHeroFightReportIsMutable() {
      if (!((bitField0_ & 0x00000008) == 0x00000008)) {
        heroFightReport_ = new java.util.ArrayList<pb4client.HeroFightReport>(heroFightReport_);
        bitField0_ |= 0x00000008;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.HeroFightReport, pb4client.HeroFightReport.Builder, pb4client.HeroFightReportOrBuilder> heroFightReportBuilder_;

    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public java.util.List<pb4client.HeroFightReport> getHeroFightReportList() {
      if (heroFightReportBuilder_ == null) {
        return java.util.Collections.unmodifiableList(heroFightReport_);
      } else {
        return heroFightReportBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public int getHeroFightReportCount() {
      if (heroFightReportBuilder_ == null) {
        return heroFightReport_.size();
      } else {
        return heroFightReportBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public pb4client.HeroFightReport getHeroFightReport(int index) {
      if (heroFightReportBuilder_ == null) {
        return heroFightReport_.get(index);
      } else {
        return heroFightReportBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public Builder setHeroFightReport(
        int index, pb4client.HeroFightReport value) {
      if (heroFightReportBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHeroFightReportIsMutable();
        heroFightReport_.set(index, value);
        onChanged();
      } else {
        heroFightReportBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public Builder setHeroFightReport(
        int index, pb4client.HeroFightReport.Builder builderForValue) {
      if (heroFightReportBuilder_ == null) {
        ensureHeroFightReportIsMutable();
        heroFightReport_.set(index, builderForValue.build());
        onChanged();
      } else {
        heroFightReportBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public Builder addHeroFightReport(pb4client.HeroFightReport value) {
      if (heroFightReportBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHeroFightReportIsMutable();
        heroFightReport_.add(value);
        onChanged();
      } else {
        heroFightReportBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public Builder addHeroFightReport(
        int index, pb4client.HeroFightReport value) {
      if (heroFightReportBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHeroFightReportIsMutable();
        heroFightReport_.add(index, value);
        onChanged();
      } else {
        heroFightReportBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public Builder addHeroFightReport(
        pb4client.HeroFightReport.Builder builderForValue) {
      if (heroFightReportBuilder_ == null) {
        ensureHeroFightReportIsMutable();
        heroFightReport_.add(builderForValue.build());
        onChanged();
      } else {
        heroFightReportBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public Builder addHeroFightReport(
        int index, pb4client.HeroFightReport.Builder builderForValue) {
      if (heroFightReportBuilder_ == null) {
        ensureHeroFightReportIsMutable();
        heroFightReport_.add(index, builderForValue.build());
        onChanged();
      } else {
        heroFightReportBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public Builder addAllHeroFightReport(
        java.lang.Iterable<? extends pb4client.HeroFightReport> values) {
      if (heroFightReportBuilder_ == null) {
        ensureHeroFightReportIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, heroFightReport_);
        onChanged();
      } else {
        heroFightReportBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public Builder clearHeroFightReport() {
      if (heroFightReportBuilder_ == null) {
        heroFightReport_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000008);
        onChanged();
      } else {
        heroFightReportBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public Builder removeHeroFightReport(int index) {
      if (heroFightReportBuilder_ == null) {
        ensureHeroFightReportIsMutable();
        heroFightReport_.remove(index);
        onChanged();
      } else {
        heroFightReportBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public pb4client.HeroFightReport.Builder getHeroFightReportBuilder(
        int index) {
      return getHeroFightReportFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public pb4client.HeroFightReportOrBuilder getHeroFightReportOrBuilder(
        int index) {
      if (heroFightReportBuilder_ == null) {
        return heroFightReport_.get(index);  } else {
        return heroFightReportBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public java.util.List<? extends pb4client.HeroFightReportOrBuilder> 
         getHeroFightReportOrBuilderList() {
      if (heroFightReportBuilder_ != null) {
        return heroFightReportBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(heroFightReport_);
      }
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public pb4client.HeroFightReport.Builder addHeroFightReportBuilder() {
      return getHeroFightReportFieldBuilder().addBuilder(
          pb4client.HeroFightReport.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public pb4client.HeroFightReport.Builder addHeroFightReportBuilder(
        int index) {
      return getHeroFightReportFieldBuilder().addBuilder(
          index, pb4client.HeroFightReport.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
     */
    public java.util.List<pb4client.HeroFightReport.Builder> 
         getHeroFightReportBuilderList() {
      return getHeroFightReportFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.HeroFightReport, pb4client.HeroFightReport.Builder, pb4client.HeroFightReportOrBuilder> 
        getHeroFightReportFieldBuilder() {
      if (heroFightReportBuilder_ == null) {
        heroFightReportBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.HeroFightReport, pb4client.HeroFightReport.Builder, pb4client.HeroFightReportOrBuilder>(
                heroFightReport_,
                ((bitField0_ & 0x00000008) == 0x00000008),
                getParentForChildren(),
                isClean());
        heroFightReport_ = null;
      }
      return heroFightReportBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.QueryBattleReportInfoAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.QueryBattleReportInfoAskRt)
  private static final pb4server.QueryBattleReportInfoAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.QueryBattleReportInfoAskRt();
  }

  public static pb4server.QueryBattleReportInfoAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueryBattleReportInfoAskRt>
      PARSER = new com.google.protobuf.AbstractParser<QueryBattleReportInfoAskRt>() {
    public QueryBattleReportInfoAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryBattleReportInfoAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryBattleReportInfoAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryBattleReportInfoAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.QueryBattleReportInfoAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

