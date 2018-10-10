// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 * GAME -&gt; HOME
 * </pre>
 *
 * Protobuf type {@code pb4server.CheckAllianceCompetitionAskRt}
 */
public  final class CheckAllianceCompetitionAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.CheckAllianceCompetitionAskRt)
    CheckAllianceCompetitionAskRtOrBuilder {
  // Use CheckAllianceCompetitionAskRt.newBuilder() to construct.
  private CheckAllianceCompetitionAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CheckAllianceCompetitionAskRt() {
    rt_ = 0;
    isAllFinish_ = 0;
    nowValue_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private CheckAllianceCompetitionAskRt(
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
          case 16: {

            isAllFinish_ = input.readInt32();
            break;
          }
          case 24: {

            nowValue_ = input.readInt64();
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
    return pb4server.InternalWkt.internal_static_pb4server_CheckAllianceCompetitionAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_CheckAllianceCompetitionAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.CheckAllianceCompetitionAskRt.class, pb4server.CheckAllianceCompetitionAskRt.Builder.class);
  }

  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int ISALLFINISH_FIELD_NUMBER = 2;
  private int isAllFinish_;
  /**
   * <pre>
   * 是否完成
   * </pre>
   *
   * <code>int32 isAllFinish = 2;</code>
   */
  public int getIsAllFinish() {
    return isAllFinish_;
  }

  public static final int NOWVALUE_FIELD_NUMBER = 3;
  private long nowValue_;
  /**
   * <pre>
   * 当前进度
   * </pre>
   *
   * <code>int64 nowValue = 3;</code>
   */
  public long getNowValue() {
    return nowValue_;
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
    if (isAllFinish_ != 0) {
      output.writeInt32(2, isAllFinish_);
    }
    if (nowValue_ != 0L) {
      output.writeInt64(3, nowValue_);
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
    if (isAllFinish_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, isAllFinish_);
    }
    if (nowValue_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, nowValue_);
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
    if (!(obj instanceof pb4server.CheckAllianceCompetitionAskRt)) {
      return super.equals(obj);
    }
    pb4server.CheckAllianceCompetitionAskRt other = (pb4server.CheckAllianceCompetitionAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && (getIsAllFinish()
        == other.getIsAllFinish());
    result = result && (getNowValue()
        == other.getNowValue());
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
    hash = (37 * hash) + ISALLFINISH_FIELD_NUMBER;
    hash = (53 * hash) + getIsAllFinish();
    hash = (37 * hash) + NOWVALUE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getNowValue());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.CheckAllianceCompetitionAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.CheckAllianceCompetitionAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.CheckAllianceCompetitionAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.CheckAllianceCompetitionAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.CheckAllianceCompetitionAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.CheckAllianceCompetitionAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.CheckAllianceCompetitionAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.CheckAllianceCompetitionAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.CheckAllianceCompetitionAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.CheckAllianceCompetitionAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.CheckAllianceCompetitionAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.CheckAllianceCompetitionAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.CheckAllianceCompetitionAskRt prototype) {
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
   * GAME -&gt; HOME
   * </pre>
   *
   * Protobuf type {@code pb4server.CheckAllianceCompetitionAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.CheckAllianceCompetitionAskRt)
      pb4server.CheckAllianceCompetitionAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_CheckAllianceCompetitionAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_CheckAllianceCompetitionAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.CheckAllianceCompetitionAskRt.class, pb4server.CheckAllianceCompetitionAskRt.Builder.class);
    }

    // Construct using pb4server.CheckAllianceCompetitionAskRt.newBuilder()
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

      isAllFinish_ = 0;

      nowValue_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_CheckAllianceCompetitionAskRt_descriptor;
    }

    public pb4server.CheckAllianceCompetitionAskRt getDefaultInstanceForType() {
      return pb4server.CheckAllianceCompetitionAskRt.getDefaultInstance();
    }

    public pb4server.CheckAllianceCompetitionAskRt build() {
      pb4server.CheckAllianceCompetitionAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.CheckAllianceCompetitionAskRt buildPartial() {
      pb4server.CheckAllianceCompetitionAskRt result = new pb4server.CheckAllianceCompetitionAskRt(this);
      result.rt_ = rt_;
      result.isAllFinish_ = isAllFinish_;
      result.nowValue_ = nowValue_;
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
      if (other instanceof pb4server.CheckAllianceCompetitionAskRt) {
        return mergeFrom((pb4server.CheckAllianceCompetitionAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.CheckAllianceCompetitionAskRt other) {
      if (other == pb4server.CheckAllianceCompetitionAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (other.getIsAllFinish() != 0) {
        setIsAllFinish(other.getIsAllFinish());
      }
      if (other.getNowValue() != 0L) {
        setNowValue(other.getNowValue());
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
      pb4server.CheckAllianceCompetitionAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.CheckAllianceCompetitionAskRt) e.getUnfinishedMessage();
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

    private int isAllFinish_ ;
    /**
     * <pre>
     * 是否完成
     * </pre>
     *
     * <code>int32 isAllFinish = 2;</code>
     */
    public int getIsAllFinish() {
      return isAllFinish_;
    }
    /**
     * <pre>
     * 是否完成
     * </pre>
     *
     * <code>int32 isAllFinish = 2;</code>
     */
    public Builder setIsAllFinish(int value) {
      
      isAllFinish_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 是否完成
     * </pre>
     *
     * <code>int32 isAllFinish = 2;</code>
     */
    public Builder clearIsAllFinish() {
      
      isAllFinish_ = 0;
      onChanged();
      return this;
    }

    private long nowValue_ ;
    /**
     * <pre>
     * 当前进度
     * </pre>
     *
     * <code>int64 nowValue = 3;</code>
     */
    public long getNowValue() {
      return nowValue_;
    }
    /**
     * <pre>
     * 当前进度
     * </pre>
     *
     * <code>int64 nowValue = 3;</code>
     */
    public Builder setNowValue(long value) {
      
      nowValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 当前进度
     * </pre>
     *
     * <code>int64 nowValue = 3;</code>
     */
    public Builder clearNowValue() {
      
      nowValue_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:pb4server.CheckAllianceCompetitionAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.CheckAllianceCompetitionAskRt)
  private static final pb4server.CheckAllianceCompetitionAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.CheckAllianceCompetitionAskRt();
  }

  public static pb4server.CheckAllianceCompetitionAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CheckAllianceCompetitionAskRt>
      PARSER = new com.google.protobuf.AbstractParser<CheckAllianceCompetitionAskRt>() {
    public CheckAllianceCompetitionAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new CheckAllianceCompetitionAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CheckAllianceCompetitionAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CheckAllianceCompetitionAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.CheckAllianceCompetitionAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
