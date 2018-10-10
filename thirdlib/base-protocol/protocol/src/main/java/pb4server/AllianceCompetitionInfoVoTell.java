// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.AllianceCompetitionInfoVoTell}
 */
public  final class AllianceCompetitionInfoVoTell extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AllianceCompetitionInfoVoTell)
    AllianceCompetitionInfoVoTellOrBuilder {
  // Use AllianceCompetitionInfoVoTell.newBuilder() to construct.
  private AllianceCompetitionInfoVoTell(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceCompetitionInfoVoTell() {
    allianceCompetitionId_ = 0L;
    allianceCompetitionTicket_ = 0;
    allianceCompetitionNowTaskId_ = 0;
    allianceCompetitionNowTaskState_ = 0;
    allianceCompetitionNowTaskOverTime_ = 0;
    allianceCompetitionGetTaskNum_ = 0;
    allianceCompetitionBuyTaskNum_ = 0;
    allianceCompetitionRankLv_ = 0;
    allianceCompetitionNowTaskValue_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllianceCompetitionInfoVoTell(
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

            allianceCompetitionId_ = input.readInt64();
            break;
          }
          case 16: {

            allianceCompetitionTicket_ = input.readInt32();
            break;
          }
          case 24: {

            allianceCompetitionNowTaskId_ = input.readInt32();
            break;
          }
          case 32: {

            allianceCompetitionNowTaskState_ = input.readInt32();
            break;
          }
          case 40: {

            allianceCompetitionNowTaskOverTime_ = input.readInt32();
            break;
          }
          case 48: {

            allianceCompetitionGetTaskNum_ = input.readInt32();
            break;
          }
          case 56: {

            allianceCompetitionBuyTaskNum_ = input.readInt32();
            break;
          }
          case 64: {

            allianceCompetitionRankLv_ = input.readInt32();
            break;
          }
          case 72: {

            allianceCompetitionNowTaskValue_ = input.readInt32();
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
    return pb4server.InternalHkt.internal_static_pb4server_AllianceCompetitionInfoVoTell_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_AllianceCompetitionInfoVoTell_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AllianceCompetitionInfoVoTell.class, pb4server.AllianceCompetitionInfoVoTell.Builder.class);
  }

  public static final int ALLIANCECOMPETITIONID_FIELD_NUMBER = 1;
  private long allianceCompetitionId_;
  /**
   * <code>int64 allianceCompetitionId = 1;</code>
   */
  public long getAllianceCompetitionId() {
    return allianceCompetitionId_;
  }

  public static final int ALLIANCECOMPETITIONTICKET_FIELD_NUMBER = 2;
  private int allianceCompetitionTicket_;
  /**
   * <code>int32 allianceCompetitionTicket = 2;</code>
   */
  public int getAllianceCompetitionTicket() {
    return allianceCompetitionTicket_;
  }

  public static final int ALLIANCECOMPETITIONNOWTASKID_FIELD_NUMBER = 3;
  private int allianceCompetitionNowTaskId_;
  /**
   * <code>int32 allianceCompetitionNowTaskId = 3;</code>
   */
  public int getAllianceCompetitionNowTaskId() {
    return allianceCompetitionNowTaskId_;
  }

  public static final int ALLIANCECOMPETITIONNOWTASKSTATE_FIELD_NUMBER = 4;
  private int allianceCompetitionNowTaskState_;
  /**
   * <code>int32 allianceCompetitionNowTaskState = 4;</code>
   */
  public int getAllianceCompetitionNowTaskState() {
    return allianceCompetitionNowTaskState_;
  }

  public static final int ALLIANCECOMPETITIONNOWTASKOVERTIME_FIELD_NUMBER = 5;
  private int allianceCompetitionNowTaskOverTime_;
  /**
   * <code>int32 allianceCompetitionNowTaskOverTime = 5;</code>
   */
  public int getAllianceCompetitionNowTaskOverTime() {
    return allianceCompetitionNowTaskOverTime_;
  }

  public static final int ALLIANCECOMPETITIONGETTASKNUM_FIELD_NUMBER = 6;
  private int allianceCompetitionGetTaskNum_;
  /**
   * <code>int32 allianceCompetitionGetTaskNum = 6;</code>
   */
  public int getAllianceCompetitionGetTaskNum() {
    return allianceCompetitionGetTaskNum_;
  }

  public static final int ALLIANCECOMPETITIONBUYTASKNUM_FIELD_NUMBER = 7;
  private int allianceCompetitionBuyTaskNum_;
  /**
   * <code>int32 allianceCompetitionBuyTaskNum = 7;</code>
   */
  public int getAllianceCompetitionBuyTaskNum() {
    return allianceCompetitionBuyTaskNum_;
  }

  public static final int ALLIANCECOMPETITIONRANKLV_FIELD_NUMBER = 8;
  private int allianceCompetitionRankLv_;
  /**
   * <code>int32 allianceCompetitionRankLv = 8;</code>
   */
  public int getAllianceCompetitionRankLv() {
    return allianceCompetitionRankLv_;
  }

  public static final int ALLIANCECOMPETITIONNOWTASKVALUE_FIELD_NUMBER = 9;
  private int allianceCompetitionNowTaskValue_;
  /**
   * <code>int32 allianceCompetitionNowTaskValue = 9;</code>
   */
  public int getAllianceCompetitionNowTaskValue() {
    return allianceCompetitionNowTaskValue_;
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
    if (allianceCompetitionId_ != 0L) {
      output.writeInt64(1, allianceCompetitionId_);
    }
    if (allianceCompetitionTicket_ != 0) {
      output.writeInt32(2, allianceCompetitionTicket_);
    }
    if (allianceCompetitionNowTaskId_ != 0) {
      output.writeInt32(3, allianceCompetitionNowTaskId_);
    }
    if (allianceCompetitionNowTaskState_ != 0) {
      output.writeInt32(4, allianceCompetitionNowTaskState_);
    }
    if (allianceCompetitionNowTaskOverTime_ != 0) {
      output.writeInt32(5, allianceCompetitionNowTaskOverTime_);
    }
    if (allianceCompetitionGetTaskNum_ != 0) {
      output.writeInt32(6, allianceCompetitionGetTaskNum_);
    }
    if (allianceCompetitionBuyTaskNum_ != 0) {
      output.writeInt32(7, allianceCompetitionBuyTaskNum_);
    }
    if (allianceCompetitionRankLv_ != 0) {
      output.writeInt32(8, allianceCompetitionRankLv_);
    }
    if (allianceCompetitionNowTaskValue_ != 0) {
      output.writeInt32(9, allianceCompetitionNowTaskValue_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (allianceCompetitionId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, allianceCompetitionId_);
    }
    if (allianceCompetitionTicket_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, allianceCompetitionTicket_);
    }
    if (allianceCompetitionNowTaskId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, allianceCompetitionNowTaskId_);
    }
    if (allianceCompetitionNowTaskState_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, allianceCompetitionNowTaskState_);
    }
    if (allianceCompetitionNowTaskOverTime_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, allianceCompetitionNowTaskOverTime_);
    }
    if (allianceCompetitionGetTaskNum_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, allianceCompetitionGetTaskNum_);
    }
    if (allianceCompetitionBuyTaskNum_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, allianceCompetitionBuyTaskNum_);
    }
    if (allianceCompetitionRankLv_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, allianceCompetitionRankLv_);
    }
    if (allianceCompetitionNowTaskValue_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(9, allianceCompetitionNowTaskValue_);
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
    if (!(obj instanceof pb4server.AllianceCompetitionInfoVoTell)) {
      return super.equals(obj);
    }
    pb4server.AllianceCompetitionInfoVoTell other = (pb4server.AllianceCompetitionInfoVoTell) obj;

    boolean result = true;
    result = result && (getAllianceCompetitionId()
        == other.getAllianceCompetitionId());
    result = result && (getAllianceCompetitionTicket()
        == other.getAllianceCompetitionTicket());
    result = result && (getAllianceCompetitionNowTaskId()
        == other.getAllianceCompetitionNowTaskId());
    result = result && (getAllianceCompetitionNowTaskState()
        == other.getAllianceCompetitionNowTaskState());
    result = result && (getAllianceCompetitionNowTaskOverTime()
        == other.getAllianceCompetitionNowTaskOverTime());
    result = result && (getAllianceCompetitionGetTaskNum()
        == other.getAllianceCompetitionGetTaskNum());
    result = result && (getAllianceCompetitionBuyTaskNum()
        == other.getAllianceCompetitionBuyTaskNum());
    result = result && (getAllianceCompetitionRankLv()
        == other.getAllianceCompetitionRankLv());
    result = result && (getAllianceCompetitionNowTaskValue()
        == other.getAllianceCompetitionNowTaskValue());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ALLIANCECOMPETITIONID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAllianceCompetitionId());
    hash = (37 * hash) + ALLIANCECOMPETITIONTICKET_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceCompetitionTicket();
    hash = (37 * hash) + ALLIANCECOMPETITIONNOWTASKID_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceCompetitionNowTaskId();
    hash = (37 * hash) + ALLIANCECOMPETITIONNOWTASKSTATE_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceCompetitionNowTaskState();
    hash = (37 * hash) + ALLIANCECOMPETITIONNOWTASKOVERTIME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceCompetitionNowTaskOverTime();
    hash = (37 * hash) + ALLIANCECOMPETITIONGETTASKNUM_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceCompetitionGetTaskNum();
    hash = (37 * hash) + ALLIANCECOMPETITIONBUYTASKNUM_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceCompetitionBuyTaskNum();
    hash = (37 * hash) + ALLIANCECOMPETITIONRANKLV_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceCompetitionRankLv();
    hash = (37 * hash) + ALLIANCECOMPETITIONNOWTASKVALUE_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceCompetitionNowTaskValue();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.AllianceCompetitionInfoVoTell parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceCompetitionInfoVoTell parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionInfoVoTell parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceCompetitionInfoVoTell parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionInfoVoTell parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceCompetitionInfoVoTell parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionInfoVoTell parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceCompetitionInfoVoTell parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionInfoVoTell parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AllianceCompetitionInfoVoTell parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionInfoVoTell parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceCompetitionInfoVoTell parseFrom(
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
  public static Builder newBuilder(pb4server.AllianceCompetitionInfoVoTell prototype) {
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
   * Protobuf type {@code pb4server.AllianceCompetitionInfoVoTell}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AllianceCompetitionInfoVoTell)
      pb4server.AllianceCompetitionInfoVoTellOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_AllianceCompetitionInfoVoTell_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_AllianceCompetitionInfoVoTell_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AllianceCompetitionInfoVoTell.class, pb4server.AllianceCompetitionInfoVoTell.Builder.class);
    }

    // Construct using pb4server.AllianceCompetitionInfoVoTell.newBuilder()
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
      allianceCompetitionId_ = 0L;

      allianceCompetitionTicket_ = 0;

      allianceCompetitionNowTaskId_ = 0;

      allianceCompetitionNowTaskState_ = 0;

      allianceCompetitionNowTaskOverTime_ = 0;

      allianceCompetitionGetTaskNum_ = 0;

      allianceCompetitionBuyTaskNum_ = 0;

      allianceCompetitionRankLv_ = 0;

      allianceCompetitionNowTaskValue_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_AllianceCompetitionInfoVoTell_descriptor;
    }

    public pb4server.AllianceCompetitionInfoVoTell getDefaultInstanceForType() {
      return pb4server.AllianceCompetitionInfoVoTell.getDefaultInstance();
    }

    public pb4server.AllianceCompetitionInfoVoTell build() {
      pb4server.AllianceCompetitionInfoVoTell result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AllianceCompetitionInfoVoTell buildPartial() {
      pb4server.AllianceCompetitionInfoVoTell result = new pb4server.AllianceCompetitionInfoVoTell(this);
      result.allianceCompetitionId_ = allianceCompetitionId_;
      result.allianceCompetitionTicket_ = allianceCompetitionTicket_;
      result.allianceCompetitionNowTaskId_ = allianceCompetitionNowTaskId_;
      result.allianceCompetitionNowTaskState_ = allianceCompetitionNowTaskState_;
      result.allianceCompetitionNowTaskOverTime_ = allianceCompetitionNowTaskOverTime_;
      result.allianceCompetitionGetTaskNum_ = allianceCompetitionGetTaskNum_;
      result.allianceCompetitionBuyTaskNum_ = allianceCompetitionBuyTaskNum_;
      result.allianceCompetitionRankLv_ = allianceCompetitionRankLv_;
      result.allianceCompetitionNowTaskValue_ = allianceCompetitionNowTaskValue_;
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
      if (other instanceof pb4server.AllianceCompetitionInfoVoTell) {
        return mergeFrom((pb4server.AllianceCompetitionInfoVoTell)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AllianceCompetitionInfoVoTell other) {
      if (other == pb4server.AllianceCompetitionInfoVoTell.getDefaultInstance()) return this;
      if (other.getAllianceCompetitionId() != 0L) {
        setAllianceCompetitionId(other.getAllianceCompetitionId());
      }
      if (other.getAllianceCompetitionTicket() != 0) {
        setAllianceCompetitionTicket(other.getAllianceCompetitionTicket());
      }
      if (other.getAllianceCompetitionNowTaskId() != 0) {
        setAllianceCompetitionNowTaskId(other.getAllianceCompetitionNowTaskId());
      }
      if (other.getAllianceCompetitionNowTaskState() != 0) {
        setAllianceCompetitionNowTaskState(other.getAllianceCompetitionNowTaskState());
      }
      if (other.getAllianceCompetitionNowTaskOverTime() != 0) {
        setAllianceCompetitionNowTaskOverTime(other.getAllianceCompetitionNowTaskOverTime());
      }
      if (other.getAllianceCompetitionGetTaskNum() != 0) {
        setAllianceCompetitionGetTaskNum(other.getAllianceCompetitionGetTaskNum());
      }
      if (other.getAllianceCompetitionBuyTaskNum() != 0) {
        setAllianceCompetitionBuyTaskNum(other.getAllianceCompetitionBuyTaskNum());
      }
      if (other.getAllianceCompetitionRankLv() != 0) {
        setAllianceCompetitionRankLv(other.getAllianceCompetitionRankLv());
      }
      if (other.getAllianceCompetitionNowTaskValue() != 0) {
        setAllianceCompetitionNowTaskValue(other.getAllianceCompetitionNowTaskValue());
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
      pb4server.AllianceCompetitionInfoVoTell parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AllianceCompetitionInfoVoTell) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long allianceCompetitionId_ ;
    /**
     * <code>int64 allianceCompetitionId = 1;</code>
     */
    public long getAllianceCompetitionId() {
      return allianceCompetitionId_;
    }
    /**
     * <code>int64 allianceCompetitionId = 1;</code>
     */
    public Builder setAllianceCompetitionId(long value) {
      
      allianceCompetitionId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 allianceCompetitionId = 1;</code>
     */
    public Builder clearAllianceCompetitionId() {
      
      allianceCompetitionId_ = 0L;
      onChanged();
      return this;
    }

    private int allianceCompetitionTicket_ ;
    /**
     * <code>int32 allianceCompetitionTicket = 2;</code>
     */
    public int getAllianceCompetitionTicket() {
      return allianceCompetitionTicket_;
    }
    /**
     * <code>int32 allianceCompetitionTicket = 2;</code>
     */
    public Builder setAllianceCompetitionTicket(int value) {
      
      allianceCompetitionTicket_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 allianceCompetitionTicket = 2;</code>
     */
    public Builder clearAllianceCompetitionTicket() {
      
      allianceCompetitionTicket_ = 0;
      onChanged();
      return this;
    }

    private int allianceCompetitionNowTaskId_ ;
    /**
     * <code>int32 allianceCompetitionNowTaskId = 3;</code>
     */
    public int getAllianceCompetitionNowTaskId() {
      return allianceCompetitionNowTaskId_;
    }
    /**
     * <code>int32 allianceCompetitionNowTaskId = 3;</code>
     */
    public Builder setAllianceCompetitionNowTaskId(int value) {
      
      allianceCompetitionNowTaskId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 allianceCompetitionNowTaskId = 3;</code>
     */
    public Builder clearAllianceCompetitionNowTaskId() {
      
      allianceCompetitionNowTaskId_ = 0;
      onChanged();
      return this;
    }

    private int allianceCompetitionNowTaskState_ ;
    /**
     * <code>int32 allianceCompetitionNowTaskState = 4;</code>
     */
    public int getAllianceCompetitionNowTaskState() {
      return allianceCompetitionNowTaskState_;
    }
    /**
     * <code>int32 allianceCompetitionNowTaskState = 4;</code>
     */
    public Builder setAllianceCompetitionNowTaskState(int value) {
      
      allianceCompetitionNowTaskState_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 allianceCompetitionNowTaskState = 4;</code>
     */
    public Builder clearAllianceCompetitionNowTaskState() {
      
      allianceCompetitionNowTaskState_ = 0;
      onChanged();
      return this;
    }

    private int allianceCompetitionNowTaskOverTime_ ;
    /**
     * <code>int32 allianceCompetitionNowTaskOverTime = 5;</code>
     */
    public int getAllianceCompetitionNowTaskOverTime() {
      return allianceCompetitionNowTaskOverTime_;
    }
    /**
     * <code>int32 allianceCompetitionNowTaskOverTime = 5;</code>
     */
    public Builder setAllianceCompetitionNowTaskOverTime(int value) {
      
      allianceCompetitionNowTaskOverTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 allianceCompetitionNowTaskOverTime = 5;</code>
     */
    public Builder clearAllianceCompetitionNowTaskOverTime() {
      
      allianceCompetitionNowTaskOverTime_ = 0;
      onChanged();
      return this;
    }

    private int allianceCompetitionGetTaskNum_ ;
    /**
     * <code>int32 allianceCompetitionGetTaskNum = 6;</code>
     */
    public int getAllianceCompetitionGetTaskNum() {
      return allianceCompetitionGetTaskNum_;
    }
    /**
     * <code>int32 allianceCompetitionGetTaskNum = 6;</code>
     */
    public Builder setAllianceCompetitionGetTaskNum(int value) {
      
      allianceCompetitionGetTaskNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 allianceCompetitionGetTaskNum = 6;</code>
     */
    public Builder clearAllianceCompetitionGetTaskNum() {
      
      allianceCompetitionGetTaskNum_ = 0;
      onChanged();
      return this;
    }

    private int allianceCompetitionBuyTaskNum_ ;
    /**
     * <code>int32 allianceCompetitionBuyTaskNum = 7;</code>
     */
    public int getAllianceCompetitionBuyTaskNum() {
      return allianceCompetitionBuyTaskNum_;
    }
    /**
     * <code>int32 allianceCompetitionBuyTaskNum = 7;</code>
     */
    public Builder setAllianceCompetitionBuyTaskNum(int value) {
      
      allianceCompetitionBuyTaskNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 allianceCompetitionBuyTaskNum = 7;</code>
     */
    public Builder clearAllianceCompetitionBuyTaskNum() {
      
      allianceCompetitionBuyTaskNum_ = 0;
      onChanged();
      return this;
    }

    private int allianceCompetitionRankLv_ ;
    /**
     * <code>int32 allianceCompetitionRankLv = 8;</code>
     */
    public int getAllianceCompetitionRankLv() {
      return allianceCompetitionRankLv_;
    }
    /**
     * <code>int32 allianceCompetitionRankLv = 8;</code>
     */
    public Builder setAllianceCompetitionRankLv(int value) {
      
      allianceCompetitionRankLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 allianceCompetitionRankLv = 8;</code>
     */
    public Builder clearAllianceCompetitionRankLv() {
      
      allianceCompetitionRankLv_ = 0;
      onChanged();
      return this;
    }

    private int allianceCompetitionNowTaskValue_ ;
    /**
     * <code>int32 allianceCompetitionNowTaskValue = 9;</code>
     */
    public int getAllianceCompetitionNowTaskValue() {
      return allianceCompetitionNowTaskValue_;
    }
    /**
     * <code>int32 allianceCompetitionNowTaskValue = 9;</code>
     */
    public Builder setAllianceCompetitionNowTaskValue(int value) {
      
      allianceCompetitionNowTaskValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 allianceCompetitionNowTaskValue = 9;</code>
     */
    public Builder clearAllianceCompetitionNowTaskValue() {
      
      allianceCompetitionNowTaskValue_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.AllianceCompetitionInfoVoTell)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AllianceCompetitionInfoVoTell)
  private static final pb4server.AllianceCompetitionInfoVoTell DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AllianceCompetitionInfoVoTell();
  }

  public static pb4server.AllianceCompetitionInfoVoTell getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllianceCompetitionInfoVoTell>
      PARSER = new com.google.protobuf.AbstractParser<AllianceCompetitionInfoVoTell>() {
    public AllianceCompetitionInfoVoTell parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceCompetitionInfoVoTell(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceCompetitionInfoVoTell> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceCompetitionInfoVoTell> getParserForType() {
    return PARSER;
  }

  public pb4server.AllianceCompetitionInfoVoTell getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
