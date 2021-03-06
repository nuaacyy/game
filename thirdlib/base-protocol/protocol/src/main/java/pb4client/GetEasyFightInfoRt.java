// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.GetEasyFightInfoRt}
 */
public  final class GetEasyFightInfoRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.GetEasyFightInfoRt)
    GetEasyFightInfoRtOrBuilder {
  // Use GetEasyFightInfoRt.newBuilder() to construct.
  private GetEasyFightInfoRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetEasyFightInfoRt() {
    rt_ = 0;
    reports_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetEasyFightInfoRt(
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
            rt_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              reports_ = new java.util.ArrayList<pb4client.BattleReportInfo>();
              mutable_bitField0_ |= 0x00000002;
            }
            reports_.add(
                input.readMessage(pb4client.BattleReportInfo.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        reports_ = java.util.Collections.unmodifiableList(reports_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_GetEasyFightInfoRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_GetEasyFightInfoRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.GetEasyFightInfoRt.class, pb4client.GetEasyFightInfoRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int REPORTS_FIELD_NUMBER = 2;
  private java.util.List<pb4client.BattleReportInfo> reports_;
  /**
   * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
   */
  public java.util.List<pb4client.BattleReportInfo> getReportsList() {
    return reports_;
  }
  /**
   * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
   */
  public java.util.List<? extends pb4client.BattleReportInfoOrBuilder> 
      getReportsOrBuilderList() {
    return reports_;
  }
  /**
   * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
   */
  public int getReportsCount() {
    return reports_.size();
  }
  /**
   * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
   */
  public pb4client.BattleReportInfo getReports(int index) {
    return reports_.get(index);
  }
  /**
   * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
   */
  public pb4client.BattleReportInfoOrBuilder getReportsOrBuilder(
      int index) {
    return reports_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasRt()) {
      memoizedIsInitialized = 0;
      return false;
    }
    for (int i = 0; i < getReportsCount(); i++) {
      if (!getReports(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, rt_);
    }
    for (int i = 0; i < reports_.size(); i++) {
      output.writeMessage(2, reports_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rt_);
    }
    for (int i = 0; i < reports_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, reports_.get(i));
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
    if (!(obj instanceof pb4client.GetEasyFightInfoRt)) {
      return super.equals(obj);
    }
    pb4client.GetEasyFightInfoRt other = (pb4client.GetEasyFightInfoRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getReportsList()
        .equals(other.getReportsList());
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
    if (hasRt()) {
      hash = (37 * hash) + RT_FIELD_NUMBER;
      hash = (53 * hash) + getRt();
    }
    if (getReportsCount() > 0) {
      hash = (37 * hash) + REPORTS_FIELD_NUMBER;
      hash = (53 * hash) + getReportsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.GetEasyFightInfoRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetEasyFightInfoRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetEasyFightInfoRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetEasyFightInfoRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetEasyFightInfoRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetEasyFightInfoRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetEasyFightInfoRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetEasyFightInfoRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetEasyFightInfoRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.GetEasyFightInfoRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetEasyFightInfoRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetEasyFightInfoRt parseFrom(
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
  public static Builder newBuilder(pb4client.GetEasyFightInfoRt prototype) {
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
   * Protobuf type {@code client2server.GetEasyFightInfoRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.GetEasyFightInfoRt)
      pb4client.GetEasyFightInfoRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_GetEasyFightInfoRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_GetEasyFightInfoRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.GetEasyFightInfoRt.class, pb4client.GetEasyFightInfoRt.Builder.class);
    }

    // Construct using pb4client.GetEasyFightInfoRt.newBuilder()
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
        getReportsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (reportsBuilder_ == null) {
        reports_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        reportsBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_GetEasyFightInfoRt_descriptor;
    }

    public pb4client.GetEasyFightInfoRt getDefaultInstanceForType() {
      return pb4client.GetEasyFightInfoRt.getDefaultInstance();
    }

    public pb4client.GetEasyFightInfoRt build() {
      pb4client.GetEasyFightInfoRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.GetEasyFightInfoRt buildPartial() {
      pb4client.GetEasyFightInfoRt result = new pb4client.GetEasyFightInfoRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (reportsBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          reports_ = java.util.Collections.unmodifiableList(reports_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.reports_ = reports_;
      } else {
        result.reports_ = reportsBuilder_.build();
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
      if (other instanceof pb4client.GetEasyFightInfoRt) {
        return mergeFrom((pb4client.GetEasyFightInfoRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.GetEasyFightInfoRt other) {
      if (other == pb4client.GetEasyFightInfoRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (reportsBuilder_ == null) {
        if (!other.reports_.isEmpty()) {
          if (reports_.isEmpty()) {
            reports_ = other.reports_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureReportsIsMutable();
            reports_.addAll(other.reports_);
          }
          onChanged();
        }
      } else {
        if (!other.reports_.isEmpty()) {
          if (reportsBuilder_.isEmpty()) {
            reportsBuilder_.dispose();
            reportsBuilder_ = null;
            reports_ = other.reports_;
            bitField0_ = (bitField0_ & ~0x00000002);
            reportsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getReportsFieldBuilder() : null;
          } else {
            reportsBuilder_.addAllMessages(other.reports_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRt()) {
        return false;
      }
      for (int i = 0; i < getReportsCount(); i++) {
        if (!getReports(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.GetEasyFightInfoRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.GetEasyFightInfoRt) e.getUnfinishedMessage();
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
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.BattleReportInfo> reports_ =
      java.util.Collections.emptyList();
    private void ensureReportsIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        reports_ = new java.util.ArrayList<pb4client.BattleReportInfo>(reports_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.BattleReportInfo, pb4client.BattleReportInfo.Builder, pb4client.BattleReportInfoOrBuilder> reportsBuilder_;

    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public java.util.List<pb4client.BattleReportInfo> getReportsList() {
      if (reportsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(reports_);
      } else {
        return reportsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public int getReportsCount() {
      if (reportsBuilder_ == null) {
        return reports_.size();
      } else {
        return reportsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public pb4client.BattleReportInfo getReports(int index) {
      if (reportsBuilder_ == null) {
        return reports_.get(index);
      } else {
        return reportsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public Builder setReports(
        int index, pb4client.BattleReportInfo value) {
      if (reportsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureReportsIsMutable();
        reports_.set(index, value);
        onChanged();
      } else {
        reportsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public Builder setReports(
        int index, pb4client.BattleReportInfo.Builder builderForValue) {
      if (reportsBuilder_ == null) {
        ensureReportsIsMutable();
        reports_.set(index, builderForValue.build());
        onChanged();
      } else {
        reportsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public Builder addReports(pb4client.BattleReportInfo value) {
      if (reportsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureReportsIsMutable();
        reports_.add(value);
        onChanged();
      } else {
        reportsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public Builder addReports(
        int index, pb4client.BattleReportInfo value) {
      if (reportsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureReportsIsMutable();
        reports_.add(index, value);
        onChanged();
      } else {
        reportsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public Builder addReports(
        pb4client.BattleReportInfo.Builder builderForValue) {
      if (reportsBuilder_ == null) {
        ensureReportsIsMutable();
        reports_.add(builderForValue.build());
        onChanged();
      } else {
        reportsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public Builder addReports(
        int index, pb4client.BattleReportInfo.Builder builderForValue) {
      if (reportsBuilder_ == null) {
        ensureReportsIsMutable();
        reports_.add(index, builderForValue.build());
        onChanged();
      } else {
        reportsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public Builder addAllReports(
        java.lang.Iterable<? extends pb4client.BattleReportInfo> values) {
      if (reportsBuilder_ == null) {
        ensureReportsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, reports_);
        onChanged();
      } else {
        reportsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public Builder clearReports() {
      if (reportsBuilder_ == null) {
        reports_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        reportsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public Builder removeReports(int index) {
      if (reportsBuilder_ == null) {
        ensureReportsIsMutable();
        reports_.remove(index);
        onChanged();
      } else {
        reportsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public pb4client.BattleReportInfo.Builder getReportsBuilder(
        int index) {
      return getReportsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public pb4client.BattleReportInfoOrBuilder getReportsOrBuilder(
        int index) {
      if (reportsBuilder_ == null) {
        return reports_.get(index);  } else {
        return reportsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public java.util.List<? extends pb4client.BattleReportInfoOrBuilder> 
         getReportsOrBuilderList() {
      if (reportsBuilder_ != null) {
        return reportsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(reports_);
      }
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public pb4client.BattleReportInfo.Builder addReportsBuilder() {
      return getReportsFieldBuilder().addBuilder(
          pb4client.BattleReportInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public pb4client.BattleReportInfo.Builder addReportsBuilder(
        int index) {
      return getReportsFieldBuilder().addBuilder(
          index, pb4client.BattleReportInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.BattleReportInfo reports = 2;</code>
     */
    public java.util.List<pb4client.BattleReportInfo.Builder> 
         getReportsBuilderList() {
      return getReportsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.BattleReportInfo, pb4client.BattleReportInfo.Builder, pb4client.BattleReportInfoOrBuilder> 
        getReportsFieldBuilder() {
      if (reportsBuilder_ == null) {
        reportsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.BattleReportInfo, pb4client.BattleReportInfo.Builder, pb4client.BattleReportInfoOrBuilder>(
                reports_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        reports_ = null;
      }
      return reportsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.GetEasyFightInfoRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.GetEasyFightInfoRt)
  private static final pb4client.GetEasyFightInfoRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.GetEasyFightInfoRt();
  }

  public static pb4client.GetEasyFightInfoRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GetEasyFightInfoRt>
      PARSER = new com.google.protobuf.AbstractParser<GetEasyFightInfoRt>() {
    public GetEasyFightInfoRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetEasyFightInfoRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetEasyFightInfoRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetEasyFightInfoRt> getParserForType() {
    return PARSER;
  }

  public pb4client.GetEasyFightInfoRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

