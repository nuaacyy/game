// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3173
 * 服务器 -&gt; 客户端
 * 有新的战报
 * </pre>
 *
 * Protobuf type {@code client2server.NewBattleReport}
 */
public  final class NewBattleReport extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.NewBattleReport)
    NewBattleReportOrBuilder {
  // Use NewBattleReport.newBuilder() to construct.
  private NewBattleReport(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NewBattleReport() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private NewBattleReport(
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
          case 10: {
            pb4client.BattleReportInfo.Builder subBuilder = null;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
              subBuilder = report_.toBuilder();
            }
            report_ = input.readMessage(pb4client.BattleReportInfo.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(report_);
              report_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000001;
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
    return pb4client.War2GamePkt.internal_static_client2server_NewBattleReport_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_NewBattleReport_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.NewBattleReport.class, pb4client.NewBattleReport.Builder.class);
  }

  private int bitField0_;
  public static final int REPORT_FIELD_NUMBER = 1;
  private pb4client.BattleReportInfo report_;
  /**
   * <pre>
   *战报信息 
   * </pre>
   *
   * <code>required .client2server.BattleReportInfo report = 1;</code>
   */
  public boolean hasReport() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *战报信息 
   * </pre>
   *
   * <code>required .client2server.BattleReportInfo report = 1;</code>
   */
  public pb4client.BattleReportInfo getReport() {
    return report_ == null ? pb4client.BattleReportInfo.getDefaultInstance() : report_;
  }
  /**
   * <pre>
   *战报信息 
   * </pre>
   *
   * <code>required .client2server.BattleReportInfo report = 1;</code>
   */
  public pb4client.BattleReportInfoOrBuilder getReportOrBuilder() {
    return report_ == null ? pb4client.BattleReportInfo.getDefaultInstance() : report_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasReport()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getReport().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeMessage(1, getReport());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getReport());
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
    if (!(obj instanceof pb4client.NewBattleReport)) {
      return super.equals(obj);
    }
    pb4client.NewBattleReport other = (pb4client.NewBattleReport) obj;

    boolean result = true;
    result = result && (hasReport() == other.hasReport());
    if (hasReport()) {
      result = result && getReport()
          .equals(other.getReport());
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
    if (hasReport()) {
      hash = (37 * hash) + REPORT_FIELD_NUMBER;
      hash = (53 * hash) + getReport().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.NewBattleReport parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.NewBattleReport parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.NewBattleReport parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.NewBattleReport parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.NewBattleReport parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.NewBattleReport parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.NewBattleReport parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.NewBattleReport parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.NewBattleReport parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.NewBattleReport parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.NewBattleReport parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.NewBattleReport parseFrom(
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
  public static Builder newBuilder(pb4client.NewBattleReport prototype) {
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
   * msgType = 3173
   * 服务器 -&gt; 客户端
   * 有新的战报
   * </pre>
   *
   * Protobuf type {@code client2server.NewBattleReport}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.NewBattleReport)
      pb4client.NewBattleReportOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_NewBattleReport_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_NewBattleReport_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.NewBattleReport.class, pb4client.NewBattleReport.Builder.class);
    }

    // Construct using pb4client.NewBattleReport.newBuilder()
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
        getReportFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (reportBuilder_ == null) {
        report_ = null;
      } else {
        reportBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_NewBattleReport_descriptor;
    }

    public pb4client.NewBattleReport getDefaultInstanceForType() {
      return pb4client.NewBattleReport.getDefaultInstance();
    }

    public pb4client.NewBattleReport build() {
      pb4client.NewBattleReport result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.NewBattleReport buildPartial() {
      pb4client.NewBattleReport result = new pb4client.NewBattleReport(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      if (reportBuilder_ == null) {
        result.report_ = report_;
      } else {
        result.report_ = reportBuilder_.build();
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
      if (other instanceof pb4client.NewBattleReport) {
        return mergeFrom((pb4client.NewBattleReport)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.NewBattleReport other) {
      if (other == pb4client.NewBattleReport.getDefaultInstance()) return this;
      if (other.hasReport()) {
        mergeReport(other.getReport());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasReport()) {
        return false;
      }
      if (!getReport().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.NewBattleReport parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.NewBattleReport) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private pb4client.BattleReportInfo report_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.BattleReportInfo, pb4client.BattleReportInfo.Builder, pb4client.BattleReportInfoOrBuilder> reportBuilder_;
    /**
     * <pre>
     *战报信息 
     * </pre>
     *
     * <code>required .client2server.BattleReportInfo report = 1;</code>
     */
    public boolean hasReport() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *战报信息 
     * </pre>
     *
     * <code>required .client2server.BattleReportInfo report = 1;</code>
     */
    public pb4client.BattleReportInfo getReport() {
      if (reportBuilder_ == null) {
        return report_ == null ? pb4client.BattleReportInfo.getDefaultInstance() : report_;
      } else {
        return reportBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     *战报信息 
     * </pre>
     *
     * <code>required .client2server.BattleReportInfo report = 1;</code>
     */
    public Builder setReport(pb4client.BattleReportInfo value) {
      if (reportBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        report_ = value;
        onChanged();
      } else {
        reportBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     *战报信息 
     * </pre>
     *
     * <code>required .client2server.BattleReportInfo report = 1;</code>
     */
    public Builder setReport(
        pb4client.BattleReportInfo.Builder builderForValue) {
      if (reportBuilder_ == null) {
        report_ = builderForValue.build();
        onChanged();
      } else {
        reportBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     *战报信息 
     * </pre>
     *
     * <code>required .client2server.BattleReportInfo report = 1;</code>
     */
    public Builder mergeReport(pb4client.BattleReportInfo value) {
      if (reportBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001) &&
            report_ != null &&
            report_ != pb4client.BattleReportInfo.getDefaultInstance()) {
          report_ =
            pb4client.BattleReportInfo.newBuilder(report_).mergeFrom(value).buildPartial();
        } else {
          report_ = value;
        }
        onChanged();
      } else {
        reportBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     *战报信息 
     * </pre>
     *
     * <code>required .client2server.BattleReportInfo report = 1;</code>
     */
    public Builder clearReport() {
      if (reportBuilder_ == null) {
        report_ = null;
        onChanged();
      } else {
        reportBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }
    /**
     * <pre>
     *战报信息 
     * </pre>
     *
     * <code>required .client2server.BattleReportInfo report = 1;</code>
     */
    public pb4client.BattleReportInfo.Builder getReportBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getReportFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     *战报信息 
     * </pre>
     *
     * <code>required .client2server.BattleReportInfo report = 1;</code>
     */
    public pb4client.BattleReportInfoOrBuilder getReportOrBuilder() {
      if (reportBuilder_ != null) {
        return reportBuilder_.getMessageOrBuilder();
      } else {
        return report_ == null ?
            pb4client.BattleReportInfo.getDefaultInstance() : report_;
      }
    }
    /**
     * <pre>
     *战报信息 
     * </pre>
     *
     * <code>required .client2server.BattleReportInfo report = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.BattleReportInfo, pb4client.BattleReportInfo.Builder, pb4client.BattleReportInfoOrBuilder> 
        getReportFieldBuilder() {
      if (reportBuilder_ == null) {
        reportBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.BattleReportInfo, pb4client.BattleReportInfo.Builder, pb4client.BattleReportInfoOrBuilder>(
                getReport(),
                getParentForChildren(),
                isClean());
        report_ = null;
      }
      return reportBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.NewBattleReport)
  }

  // @@protoc_insertion_point(class_scope:client2server.NewBattleReport)
  private static final pb4client.NewBattleReport DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.NewBattleReport();
  }

  public static pb4client.NewBattleReport getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<NewBattleReport>
      PARSER = new com.google.protobuf.AbstractParser<NewBattleReport>() {
    public NewBattleReport parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new NewBattleReport(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<NewBattleReport> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NewBattleReport> getParserForType() {
    return PARSER;
  }

  public pb4client.NewBattleReport getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

