// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.AllianceCompetitionQuest}
 */
public  final class AllianceCompetitionQuest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AllianceCompetitionQuest)
    AllianceCompetitionQuestOrBuilder {
  // Use AllianceCompetitionQuest.newBuilder() to construct.
  private AllianceCompetitionQuest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceCompetitionQuest() {
    index_ = 0;
    questId_ = 0;
    refTime_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllianceCompetitionQuest(
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
            index_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            questId_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            refTime_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_AllianceCompetitionQuest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceCompetitionQuest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AllianceCompetitionQuest.class, pb4client.AllianceCompetitionQuest.Builder.class);
  }

  private int bitField0_;
  public static final int INDEX_FIELD_NUMBER = 1;
  private int index_;
  /**
   * <pre>
   * 任务排序
   * </pre>
   *
   * <code>required int32 index = 1;</code>
   */
  public boolean hasIndex() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 任务排序
   * </pre>
   *
   * <code>required int32 index = 1;</code>
   */
  public int getIndex() {
    return index_;
  }

  public static final int QUESTID_FIELD_NUMBER = 2;
  private int questId_;
  /**
   * <pre>
   * 被领取之后就是0
   * </pre>
   *
   * <code>required int32 questId = 2;</code>
   */
  public boolean hasQuestId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 被领取之后就是0
   * </pre>
   *
   * <code>required int32 questId = 2;</code>
   */
  public int getQuestId() {
    return questId_;
  }

  public static final int REFTIME_FIELD_NUMBER = 3;
  private int refTime_;
  /**
   * <pre>
   * 任务刷新倒计时,只有任务ID是0的时候这个才不为zeroTime
   * </pre>
   *
   * <code>required int32 refTime = 3;</code>
   */
  public boolean hasRefTime() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 任务刷新倒计时,只有任务ID是0的时候这个才不为zeroTime
   * </pre>
   *
   * <code>required int32 refTime = 3;</code>
   */
  public int getRefTime() {
    return refTime_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasIndex()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasQuestId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasRefTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, index_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, questId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, refTime_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, index_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, questId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, refTime_);
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
    if (!(obj instanceof pb4client.AllianceCompetitionQuest)) {
      return super.equals(obj);
    }
    pb4client.AllianceCompetitionQuest other = (pb4client.AllianceCompetitionQuest) obj;

    boolean result = true;
    result = result && (hasIndex() == other.hasIndex());
    if (hasIndex()) {
      result = result && (getIndex()
          == other.getIndex());
    }
    result = result && (hasQuestId() == other.hasQuestId());
    if (hasQuestId()) {
      result = result && (getQuestId()
          == other.getQuestId());
    }
    result = result && (hasRefTime() == other.hasRefTime());
    if (hasRefTime()) {
      result = result && (getRefTime()
          == other.getRefTime());
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
    if (hasIndex()) {
      hash = (37 * hash) + INDEX_FIELD_NUMBER;
      hash = (53 * hash) + getIndex();
    }
    if (hasQuestId()) {
      hash = (37 * hash) + QUESTID_FIELD_NUMBER;
      hash = (53 * hash) + getQuestId();
    }
    if (hasRefTime()) {
      hash = (37 * hash) + REFTIME_FIELD_NUMBER;
      hash = (53 * hash) + getRefTime();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AllianceCompetitionQuest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceCompetitionQuest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceCompetitionQuest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceCompetitionQuest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceCompetitionQuest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceCompetitionQuest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceCompetitionQuest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceCompetitionQuest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceCompetitionQuest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AllianceCompetitionQuest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceCompetitionQuest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceCompetitionQuest parseFrom(
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
  public static Builder newBuilder(pb4client.AllianceCompetitionQuest prototype) {
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
   * Protobuf type {@code client2server.AllianceCompetitionQuest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AllianceCompetitionQuest)
      pb4client.AllianceCompetitionQuestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceCompetitionQuest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceCompetitionQuest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AllianceCompetitionQuest.class, pb4client.AllianceCompetitionQuest.Builder.class);
    }

    // Construct using pb4client.AllianceCompetitionQuest.newBuilder()
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
      index_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      questId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      refTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceCompetitionQuest_descriptor;
    }

    public pb4client.AllianceCompetitionQuest getDefaultInstanceForType() {
      return pb4client.AllianceCompetitionQuest.getDefaultInstance();
    }

    public pb4client.AllianceCompetitionQuest build() {
      pb4client.AllianceCompetitionQuest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AllianceCompetitionQuest buildPartial() {
      pb4client.AllianceCompetitionQuest result = new pb4client.AllianceCompetitionQuest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.index_ = index_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.questId_ = questId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.refTime_ = refTime_;
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
      if (other instanceof pb4client.AllianceCompetitionQuest) {
        return mergeFrom((pb4client.AllianceCompetitionQuest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AllianceCompetitionQuest other) {
      if (other == pb4client.AllianceCompetitionQuest.getDefaultInstance()) return this;
      if (other.hasIndex()) {
        setIndex(other.getIndex());
      }
      if (other.hasQuestId()) {
        setQuestId(other.getQuestId());
      }
      if (other.hasRefTime()) {
        setRefTime(other.getRefTime());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasIndex()) {
        return false;
      }
      if (!hasQuestId()) {
        return false;
      }
      if (!hasRefTime()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AllianceCompetitionQuest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AllianceCompetitionQuest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int index_ ;
    /**
     * <pre>
     * 任务排序
     * </pre>
     *
     * <code>required int32 index = 1;</code>
     */
    public boolean hasIndex() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 任务排序
     * </pre>
     *
     * <code>required int32 index = 1;</code>
     */
    public int getIndex() {
      return index_;
    }
    /**
     * <pre>
     * 任务排序
     * </pre>
     *
     * <code>required int32 index = 1;</code>
     */
    public Builder setIndex(int value) {
      bitField0_ |= 0x00000001;
      index_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 任务排序
     * </pre>
     *
     * <code>required int32 index = 1;</code>
     */
    public Builder clearIndex() {
      bitField0_ = (bitField0_ & ~0x00000001);
      index_ = 0;
      onChanged();
      return this;
    }

    private int questId_ ;
    /**
     * <pre>
     * 被领取之后就是0
     * </pre>
     *
     * <code>required int32 questId = 2;</code>
     */
    public boolean hasQuestId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 被领取之后就是0
     * </pre>
     *
     * <code>required int32 questId = 2;</code>
     */
    public int getQuestId() {
      return questId_;
    }
    /**
     * <pre>
     * 被领取之后就是0
     * </pre>
     *
     * <code>required int32 questId = 2;</code>
     */
    public Builder setQuestId(int value) {
      bitField0_ |= 0x00000002;
      questId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被领取之后就是0
     * </pre>
     *
     * <code>required int32 questId = 2;</code>
     */
    public Builder clearQuestId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      questId_ = 0;
      onChanged();
      return this;
    }

    private int refTime_ ;
    /**
     * <pre>
     * 任务刷新倒计时,只有任务ID是0的时候这个才不为zeroTime
     * </pre>
     *
     * <code>required int32 refTime = 3;</code>
     */
    public boolean hasRefTime() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 任务刷新倒计时,只有任务ID是0的时候这个才不为zeroTime
     * </pre>
     *
     * <code>required int32 refTime = 3;</code>
     */
    public int getRefTime() {
      return refTime_;
    }
    /**
     * <pre>
     * 任务刷新倒计时,只有任务ID是0的时候这个才不为zeroTime
     * </pre>
     *
     * <code>required int32 refTime = 3;</code>
     */
    public Builder setRefTime(int value) {
      bitField0_ |= 0x00000004;
      refTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 任务刷新倒计时,只有任务ID是0的时候这个才不为zeroTime
     * </pre>
     *
     * <code>required int32 refTime = 3;</code>
     */
    public Builder clearRefTime() {
      bitField0_ = (bitField0_ & ~0x00000004);
      refTime_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.AllianceCompetitionQuest)
  }

  // @@protoc_insertion_point(class_scope:client2server.AllianceCompetitionQuest)
  private static final pb4client.AllianceCompetitionQuest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AllianceCompetitionQuest();
  }

  public static pb4client.AllianceCompetitionQuest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AllianceCompetitionQuest>
      PARSER = new com.google.protobuf.AbstractParser<AllianceCompetitionQuest>() {
    public AllianceCompetitionQuest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceCompetitionQuest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceCompetitionQuest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceCompetitionQuest> getParserForType() {
    return PARSER;
  }

  public pb4client.AllianceCompetitionQuest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

