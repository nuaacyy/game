// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.OpenAfterAllianceCompetitionRt}
 */
public  final class OpenAfterAllianceCompetitionRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.OpenAfterAllianceCompetitionRt)
    OpenAfterAllianceCompetitionRtOrBuilder {
  // Use OpenAfterAllianceCompetitionRt.newBuilder() to construct.
  private OpenAfterAllianceCompetitionRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OpenAfterAllianceCompetitionRt() {
    rt_ = 0;
    beforeRankLv_ = 0;
    afterRankLv_ = 0;
    score_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private OpenAfterAllianceCompetitionRt(
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
          case 16: {
            bitField0_ |= 0x00000002;
            beforeRankLv_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            afterRankLv_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            score_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_OpenAfterAllianceCompetitionRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_OpenAfterAllianceCompetitionRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.OpenAfterAllianceCompetitionRt.class, pb4client.OpenAfterAllianceCompetitionRt.Builder.class);
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

  public static final int BEFORERANKLV_FIELD_NUMBER = 2;
  private int beforeRankLv_;
  /**
   * <pre>
   * 联盟参赛之前的段位
   * </pre>
   *
   * <code>optional int32 beforeRankLv = 2;</code>
   */
  public boolean hasBeforeRankLv() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 联盟参赛之前的段位
   * </pre>
   *
   * <code>optional int32 beforeRankLv = 2;</code>
   */
  public int getBeforeRankLv() {
    return beforeRankLv_;
  }

  public static final int AFTERRANKLV_FIELD_NUMBER = 3;
  private int afterRankLv_;
  /**
   * <pre>
   * 联盟参赛之后的段位
   * </pre>
   *
   * <code>optional int32 afterRankLv = 3;</code>
   */
  public boolean hasAfterRankLv() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 联盟参赛之后的段位
   * </pre>
   *
   * <code>optional int32 afterRankLv = 3;</code>
   */
  public int getAfterRankLv() {
    return afterRankLv_;
  }

  public static final int SCORE_FIELD_NUMBER = 4;
  private int score_;
  /**
   * <pre>
   * 本次参赛获取到的积分
   * </pre>
   *
   * <code>optional int32 score = 4;</code>
   */
  public boolean hasScore() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 本次参赛获取到的积分
   * </pre>
   *
   * <code>optional int32 score = 4;</code>
   */
  public int getScore() {
    return score_;
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
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, rt_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, beforeRankLv_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, afterRankLv_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, score_);
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
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, beforeRankLv_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, afterRankLv_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, score_);
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
    if (!(obj instanceof pb4client.OpenAfterAllianceCompetitionRt)) {
      return super.equals(obj);
    }
    pb4client.OpenAfterAllianceCompetitionRt other = (pb4client.OpenAfterAllianceCompetitionRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasBeforeRankLv() == other.hasBeforeRankLv());
    if (hasBeforeRankLv()) {
      result = result && (getBeforeRankLv()
          == other.getBeforeRankLv());
    }
    result = result && (hasAfterRankLv() == other.hasAfterRankLv());
    if (hasAfterRankLv()) {
      result = result && (getAfterRankLv()
          == other.getAfterRankLv());
    }
    result = result && (hasScore() == other.hasScore());
    if (hasScore()) {
      result = result && (getScore()
          == other.getScore());
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
    if (hasRt()) {
      hash = (37 * hash) + RT_FIELD_NUMBER;
      hash = (53 * hash) + getRt();
    }
    if (hasBeforeRankLv()) {
      hash = (37 * hash) + BEFORERANKLV_FIELD_NUMBER;
      hash = (53 * hash) + getBeforeRankLv();
    }
    if (hasAfterRankLv()) {
      hash = (37 * hash) + AFTERRANKLV_FIELD_NUMBER;
      hash = (53 * hash) + getAfterRankLv();
    }
    if (hasScore()) {
      hash = (37 * hash) + SCORE_FIELD_NUMBER;
      hash = (53 * hash) + getScore();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.OpenAfterAllianceCompetitionRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OpenAfterAllianceCompetitionRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OpenAfterAllianceCompetitionRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OpenAfterAllianceCompetitionRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OpenAfterAllianceCompetitionRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OpenAfterAllianceCompetitionRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OpenAfterAllianceCompetitionRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.OpenAfterAllianceCompetitionRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.OpenAfterAllianceCompetitionRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.OpenAfterAllianceCompetitionRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.OpenAfterAllianceCompetitionRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.OpenAfterAllianceCompetitionRt parseFrom(
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
  public static Builder newBuilder(pb4client.OpenAfterAllianceCompetitionRt prototype) {
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
   * Protobuf type {@code client2server.OpenAfterAllianceCompetitionRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.OpenAfterAllianceCompetitionRt)
      pb4client.OpenAfterAllianceCompetitionRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_OpenAfterAllianceCompetitionRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_OpenAfterAllianceCompetitionRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.OpenAfterAllianceCompetitionRt.class, pb4client.OpenAfterAllianceCompetitionRt.Builder.class);
    }

    // Construct using pb4client.OpenAfterAllianceCompetitionRt.newBuilder()
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
      bitField0_ = (bitField0_ & ~0x00000001);
      beforeRankLv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      afterRankLv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      score_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_OpenAfterAllianceCompetitionRt_descriptor;
    }

    public pb4client.OpenAfterAllianceCompetitionRt getDefaultInstanceForType() {
      return pb4client.OpenAfterAllianceCompetitionRt.getDefaultInstance();
    }

    public pb4client.OpenAfterAllianceCompetitionRt build() {
      pb4client.OpenAfterAllianceCompetitionRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.OpenAfterAllianceCompetitionRt buildPartial() {
      pb4client.OpenAfterAllianceCompetitionRt result = new pb4client.OpenAfterAllianceCompetitionRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.beforeRankLv_ = beforeRankLv_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.afterRankLv_ = afterRankLv_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.score_ = score_;
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
      if (other instanceof pb4client.OpenAfterAllianceCompetitionRt) {
        return mergeFrom((pb4client.OpenAfterAllianceCompetitionRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.OpenAfterAllianceCompetitionRt other) {
      if (other == pb4client.OpenAfterAllianceCompetitionRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasBeforeRankLv()) {
        setBeforeRankLv(other.getBeforeRankLv());
      }
      if (other.hasAfterRankLv()) {
        setAfterRankLv(other.getAfterRankLv());
      }
      if (other.hasScore()) {
        setScore(other.getScore());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRt()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.OpenAfterAllianceCompetitionRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.OpenAfterAllianceCompetitionRt) e.getUnfinishedMessage();
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

    private int beforeRankLv_ ;
    /**
     * <pre>
     * 联盟参赛之前的段位
     * </pre>
     *
     * <code>optional int32 beforeRankLv = 2;</code>
     */
    public boolean hasBeforeRankLv() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 联盟参赛之前的段位
     * </pre>
     *
     * <code>optional int32 beforeRankLv = 2;</code>
     */
    public int getBeforeRankLv() {
      return beforeRankLv_;
    }
    /**
     * <pre>
     * 联盟参赛之前的段位
     * </pre>
     *
     * <code>optional int32 beforeRankLv = 2;</code>
     */
    public Builder setBeforeRankLv(int value) {
      bitField0_ |= 0x00000002;
      beforeRankLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟参赛之前的段位
     * </pre>
     *
     * <code>optional int32 beforeRankLv = 2;</code>
     */
    public Builder clearBeforeRankLv() {
      bitField0_ = (bitField0_ & ~0x00000002);
      beforeRankLv_ = 0;
      onChanged();
      return this;
    }

    private int afterRankLv_ ;
    /**
     * <pre>
     * 联盟参赛之后的段位
     * </pre>
     *
     * <code>optional int32 afterRankLv = 3;</code>
     */
    public boolean hasAfterRankLv() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 联盟参赛之后的段位
     * </pre>
     *
     * <code>optional int32 afterRankLv = 3;</code>
     */
    public int getAfterRankLv() {
      return afterRankLv_;
    }
    /**
     * <pre>
     * 联盟参赛之后的段位
     * </pre>
     *
     * <code>optional int32 afterRankLv = 3;</code>
     */
    public Builder setAfterRankLv(int value) {
      bitField0_ |= 0x00000004;
      afterRankLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟参赛之后的段位
     * </pre>
     *
     * <code>optional int32 afterRankLv = 3;</code>
     */
    public Builder clearAfterRankLv() {
      bitField0_ = (bitField0_ & ~0x00000004);
      afterRankLv_ = 0;
      onChanged();
      return this;
    }

    private int score_ ;
    /**
     * <pre>
     * 本次参赛获取到的积分
     * </pre>
     *
     * <code>optional int32 score = 4;</code>
     */
    public boolean hasScore() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 本次参赛获取到的积分
     * </pre>
     *
     * <code>optional int32 score = 4;</code>
     */
    public int getScore() {
      return score_;
    }
    /**
     * <pre>
     * 本次参赛获取到的积分
     * </pre>
     *
     * <code>optional int32 score = 4;</code>
     */
    public Builder setScore(int value) {
      bitField0_ |= 0x00000008;
      score_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 本次参赛获取到的积分
     * </pre>
     *
     * <code>optional int32 score = 4;</code>
     */
    public Builder clearScore() {
      bitField0_ = (bitField0_ & ~0x00000008);
      score_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.OpenAfterAllianceCompetitionRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.OpenAfterAllianceCompetitionRt)
  private static final pb4client.OpenAfterAllianceCompetitionRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.OpenAfterAllianceCompetitionRt();
  }

  public static pb4client.OpenAfterAllianceCompetitionRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<OpenAfterAllianceCompetitionRt>
      PARSER = new com.google.protobuf.AbstractParser<OpenAfterAllianceCompetitionRt>() {
    public OpenAfterAllianceCompetitionRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OpenAfterAllianceCompetitionRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OpenAfterAllianceCompetitionRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OpenAfterAllianceCompetitionRt> getParserForType() {
    return PARSER;
  }

  public pb4client.OpenAfterAllianceCompetitionRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

