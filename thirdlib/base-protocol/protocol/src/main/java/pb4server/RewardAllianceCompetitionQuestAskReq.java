// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * <pre>
 * 领取联盟总动员任务奖励
 * </pre>
 *
 * Protobuf type {@code pb4server.RewardAllianceCompetitionQuestAskReq}
 */
public  final class RewardAllianceCompetitionQuestAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.RewardAllianceCompetitionQuestAskReq)
    RewardAllianceCompetitionQuestAskReqOrBuilder {
  // Use RewardAllianceCompetitionQuestAskReq.newBuilder() to construct.
  private RewardAllianceCompetitionQuestAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RewardAllianceCompetitionQuestAskReq() {
    addScore_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private RewardAllianceCompetitionQuestAskReq(
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

            addScore_ = input.readInt32();
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
    return pb4server.InternalPkt.internal_static_pb4server_RewardAllianceCompetitionQuestAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_RewardAllianceCompetitionQuestAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.RewardAllianceCompetitionQuestAskReq.class, pb4server.RewardAllianceCompetitionQuestAskReq.Builder.class);
  }

  public static final int ADDSCORE_FIELD_NUMBER = 1;
  private int addScore_;
  /**
   * <pre>
   * 加到的积分
   * </pre>
   *
   * <code>int32 addScore = 1;</code>
   */
  public int getAddScore() {
    return addScore_;
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
    if (addScore_ != 0) {
      output.writeInt32(1, addScore_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (addScore_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, addScore_);
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
    if (!(obj instanceof pb4server.RewardAllianceCompetitionQuestAskReq)) {
      return super.equals(obj);
    }
    pb4server.RewardAllianceCompetitionQuestAskReq other = (pb4server.RewardAllianceCompetitionQuestAskReq) obj;

    boolean result = true;
    result = result && (getAddScore()
        == other.getAddScore());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ADDSCORE_FIELD_NUMBER;
    hash = (53 * hash) + getAddScore();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.RewardAllianceCompetitionQuestAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.RewardAllianceCompetitionQuestAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.RewardAllianceCompetitionQuestAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.RewardAllianceCompetitionQuestAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.RewardAllianceCompetitionQuestAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.RewardAllianceCompetitionQuestAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.RewardAllianceCompetitionQuestAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.RewardAllianceCompetitionQuestAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.RewardAllianceCompetitionQuestAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.RewardAllianceCompetitionQuestAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.RewardAllianceCompetitionQuestAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.RewardAllianceCompetitionQuestAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.RewardAllianceCompetitionQuestAskReq prototype) {
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
   * 领取联盟总动员任务奖励
   * </pre>
   *
   * Protobuf type {@code pb4server.RewardAllianceCompetitionQuestAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.RewardAllianceCompetitionQuestAskReq)
      pb4server.RewardAllianceCompetitionQuestAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_RewardAllianceCompetitionQuestAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_RewardAllianceCompetitionQuestAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.RewardAllianceCompetitionQuestAskReq.class, pb4server.RewardAllianceCompetitionQuestAskReq.Builder.class);
    }

    // Construct using pb4server.RewardAllianceCompetitionQuestAskReq.newBuilder()
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
      addScore_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_RewardAllianceCompetitionQuestAskReq_descriptor;
    }

    public pb4server.RewardAllianceCompetitionQuestAskReq getDefaultInstanceForType() {
      return pb4server.RewardAllianceCompetitionQuestAskReq.getDefaultInstance();
    }

    public pb4server.RewardAllianceCompetitionQuestAskReq build() {
      pb4server.RewardAllianceCompetitionQuestAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.RewardAllianceCompetitionQuestAskReq buildPartial() {
      pb4server.RewardAllianceCompetitionQuestAskReq result = new pb4server.RewardAllianceCompetitionQuestAskReq(this);
      result.addScore_ = addScore_;
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
      if (other instanceof pb4server.RewardAllianceCompetitionQuestAskReq) {
        return mergeFrom((pb4server.RewardAllianceCompetitionQuestAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.RewardAllianceCompetitionQuestAskReq other) {
      if (other == pb4server.RewardAllianceCompetitionQuestAskReq.getDefaultInstance()) return this;
      if (other.getAddScore() != 0) {
        setAddScore(other.getAddScore());
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
      pb4server.RewardAllianceCompetitionQuestAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.RewardAllianceCompetitionQuestAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int addScore_ ;
    /**
     * <pre>
     * 加到的积分
     * </pre>
     *
     * <code>int32 addScore = 1;</code>
     */
    public int getAddScore() {
      return addScore_;
    }
    /**
     * <pre>
     * 加到的积分
     * </pre>
     *
     * <code>int32 addScore = 1;</code>
     */
    public Builder setAddScore(int value) {
      
      addScore_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 加到的积分
     * </pre>
     *
     * <code>int32 addScore = 1;</code>
     */
    public Builder clearAddScore() {
      
      addScore_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.RewardAllianceCompetitionQuestAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.RewardAllianceCompetitionQuestAskReq)
  private static final pb4server.RewardAllianceCompetitionQuestAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.RewardAllianceCompetitionQuestAskReq();
  }

  public static pb4server.RewardAllianceCompetitionQuestAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RewardAllianceCompetitionQuestAskReq>
      PARSER = new com.google.protobuf.AbstractParser<RewardAllianceCompetitionQuestAskReq>() {
    public RewardAllianceCompetitionQuestAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RewardAllianceCompetitionQuestAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RewardAllianceCompetitionQuestAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RewardAllianceCompetitionQuestAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.RewardAllianceCompetitionQuestAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

