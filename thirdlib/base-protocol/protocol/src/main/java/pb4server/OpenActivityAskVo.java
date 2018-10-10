// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.OpenActivityAskVo}
 */
public  final class OpenActivityAskVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.OpenActivityAskVo)
    OpenActivityAskVoOrBuilder {
  // Use OpenActivityAskVo.newBuilder() to construct.
  private OpenActivityAskVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OpenActivityAskVo() {
    activityId_ = 0;
    myRank_ = 0;
    castleLv_ = 0;
    score_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private OpenActivityAskVo(
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

            activityId_ = input.readInt32();
            break;
          }
          case 16: {

            myRank_ = input.readInt32();
            break;
          }
          case 24: {

            castleLv_ = input.readInt32();
            break;
          }
          case 32: {

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
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_OpenActivityAskVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_OpenActivityAskVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.OpenActivityAskVo.class, pb4server.OpenActivityAskVo.Builder.class);
  }

  public static final int ACTIVITYID_FIELD_NUMBER = 1;
  private int activityId_;
  /**
   * <code>int32 activityId = 1;</code>
   */
  public int getActivityId() {
    return activityId_;
  }

  public static final int MYRANK_FIELD_NUMBER = 2;
  private int myRank_;
  /**
   * <code>int32 myRank = 2;</code>
   */
  public int getMyRank() {
    return myRank_;
  }

  public static final int CASTLELV_FIELD_NUMBER = 3;
  private int castleLv_;
  /**
   * <code>int32 castleLv = 3;</code>
   */
  public int getCastleLv() {
    return castleLv_;
  }

  public static final int SCORE_FIELD_NUMBER = 4;
  private int score_;
  /**
   * <code>int32 score = 4;</code>
   */
  public int getScore() {
    return score_;
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
    if (activityId_ != 0) {
      output.writeInt32(1, activityId_);
    }
    if (myRank_ != 0) {
      output.writeInt32(2, myRank_);
    }
    if (castleLv_ != 0) {
      output.writeInt32(3, castleLv_);
    }
    if (score_ != 0) {
      output.writeInt32(4, score_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (activityId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, activityId_);
    }
    if (myRank_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, myRank_);
    }
    if (castleLv_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, castleLv_);
    }
    if (score_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, score_);
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
    if (!(obj instanceof pb4server.OpenActivityAskVo)) {
      return super.equals(obj);
    }
    pb4server.OpenActivityAskVo other = (pb4server.OpenActivityAskVo) obj;

    boolean result = true;
    result = result && (getActivityId()
        == other.getActivityId());
    result = result && (getMyRank()
        == other.getMyRank());
    result = result && (getCastleLv()
        == other.getCastleLv());
    result = result && (getScore()
        == other.getScore());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ACTIVITYID_FIELD_NUMBER;
    hash = (53 * hash) + getActivityId();
    hash = (37 * hash) + MYRANK_FIELD_NUMBER;
    hash = (53 * hash) + getMyRank();
    hash = (37 * hash) + CASTLELV_FIELD_NUMBER;
    hash = (53 * hash) + getCastleLv();
    hash = (37 * hash) + SCORE_FIELD_NUMBER;
    hash = (53 * hash) + getScore();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.OpenActivityAskVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenActivityAskVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenActivityAskVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenActivityAskVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenActivityAskVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenActivityAskVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenActivityAskVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.OpenActivityAskVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.OpenActivityAskVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.OpenActivityAskVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.OpenActivityAskVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.OpenActivityAskVo parseFrom(
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
  public static Builder newBuilder(pb4server.OpenActivityAskVo prototype) {
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
   * Protobuf type {@code pb4server.OpenActivityAskVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.OpenActivityAskVo)
      pb4server.OpenActivityAskVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenActivityAskVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenActivityAskVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.OpenActivityAskVo.class, pb4server.OpenActivityAskVo.Builder.class);
    }

    // Construct using pb4server.OpenActivityAskVo.newBuilder()
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
      activityId_ = 0;

      myRank_ = 0;

      castleLv_ = 0;

      score_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenActivityAskVo_descriptor;
    }

    public pb4server.OpenActivityAskVo getDefaultInstanceForType() {
      return pb4server.OpenActivityAskVo.getDefaultInstance();
    }

    public pb4server.OpenActivityAskVo build() {
      pb4server.OpenActivityAskVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.OpenActivityAskVo buildPartial() {
      pb4server.OpenActivityAskVo result = new pb4server.OpenActivityAskVo(this);
      result.activityId_ = activityId_;
      result.myRank_ = myRank_;
      result.castleLv_ = castleLv_;
      result.score_ = score_;
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
      if (other instanceof pb4server.OpenActivityAskVo) {
        return mergeFrom((pb4server.OpenActivityAskVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.OpenActivityAskVo other) {
      if (other == pb4server.OpenActivityAskVo.getDefaultInstance()) return this;
      if (other.getActivityId() != 0) {
        setActivityId(other.getActivityId());
      }
      if (other.getMyRank() != 0) {
        setMyRank(other.getMyRank());
      }
      if (other.getCastleLv() != 0) {
        setCastleLv(other.getCastleLv());
      }
      if (other.getScore() != 0) {
        setScore(other.getScore());
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
      pb4server.OpenActivityAskVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.OpenActivityAskVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int activityId_ ;
    /**
     * <code>int32 activityId = 1;</code>
     */
    public int getActivityId() {
      return activityId_;
    }
    /**
     * <code>int32 activityId = 1;</code>
     */
    public Builder setActivityId(int value) {
      
      activityId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 activityId = 1;</code>
     */
    public Builder clearActivityId() {
      
      activityId_ = 0;
      onChanged();
      return this;
    }

    private int myRank_ ;
    /**
     * <code>int32 myRank = 2;</code>
     */
    public int getMyRank() {
      return myRank_;
    }
    /**
     * <code>int32 myRank = 2;</code>
     */
    public Builder setMyRank(int value) {
      
      myRank_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 myRank = 2;</code>
     */
    public Builder clearMyRank() {
      
      myRank_ = 0;
      onChanged();
      return this;
    }

    private int castleLv_ ;
    /**
     * <code>int32 castleLv = 3;</code>
     */
    public int getCastleLv() {
      return castleLv_;
    }
    /**
     * <code>int32 castleLv = 3;</code>
     */
    public Builder setCastleLv(int value) {
      
      castleLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 castleLv = 3;</code>
     */
    public Builder clearCastleLv() {
      
      castleLv_ = 0;
      onChanged();
      return this;
    }

    private int score_ ;
    /**
     * <code>int32 score = 4;</code>
     */
    public int getScore() {
      return score_;
    }
    /**
     * <code>int32 score = 4;</code>
     */
    public Builder setScore(int value) {
      
      score_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 score = 4;</code>
     */
    public Builder clearScore() {
      
      score_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.OpenActivityAskVo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.OpenActivityAskVo)
  private static final pb4server.OpenActivityAskVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.OpenActivityAskVo();
  }

  public static pb4server.OpenActivityAskVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OpenActivityAskVo>
      PARSER = new com.google.protobuf.AbstractParser<OpenActivityAskVo>() {
    public OpenActivityAskVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OpenActivityAskVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OpenActivityAskVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OpenActivityAskVo> getParserForType() {
    return PARSER;
  }

  public pb4server.OpenActivityAskVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
