// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.AllianceBossLivenessVo}
 */
public  final class AllianceBossLivenessVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AllianceBossLivenessVo)
    AllianceBossLivenessVoOrBuilder {
  // Use AllianceBossLivenessVo.newBuilder() to construct.
  private AllianceBossLivenessVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceBossLivenessVo() {
    score_ = 0;
    allianceBossSummonInfo_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllianceBossLivenessVo(
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

            score_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              allianceBossSummonInfo_ = new java.util.ArrayList<pb4server.AllianceBossSummonInfoVo>();
              mutable_bitField0_ |= 0x00000002;
            }
            allianceBossSummonInfo_.add(
                input.readMessage(pb4server.AllianceBossSummonInfoVo.parser(), extensionRegistry));
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
        allianceBossSummonInfo_ = java.util.Collections.unmodifiableList(allianceBossSummonInfo_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_AllianceBossLivenessVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_AllianceBossLivenessVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AllianceBossLivenessVo.class, pb4server.AllianceBossLivenessVo.Builder.class);
  }

  private int bitField0_;
  public static final int SCORE_FIELD_NUMBER = 1;
  private int score_;
  /**
   * <pre>
   * 活跃度档
   * </pre>
   *
   * <code>int32 score = 1;</code>
   */
  public int getScore() {
    return score_;
  }

  public static final int ALLIANCEBOSSSUMMONINFO_FIELD_NUMBER = 2;
  private java.util.List<pb4server.AllianceBossSummonInfoVo> allianceBossSummonInfo_;
  /**
   * <pre>
   * 该挡内的召唤情况
   * </pre>
   *
   * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
   */
  public java.util.List<pb4server.AllianceBossSummonInfoVo> getAllianceBossSummonInfoList() {
    return allianceBossSummonInfo_;
  }
  /**
   * <pre>
   * 该挡内的召唤情况
   * </pre>
   *
   * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
   */
  public java.util.List<? extends pb4server.AllianceBossSummonInfoVoOrBuilder> 
      getAllianceBossSummonInfoOrBuilderList() {
    return allianceBossSummonInfo_;
  }
  /**
   * <pre>
   * 该挡内的召唤情况
   * </pre>
   *
   * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
   */
  public int getAllianceBossSummonInfoCount() {
    return allianceBossSummonInfo_.size();
  }
  /**
   * <pre>
   * 该挡内的召唤情况
   * </pre>
   *
   * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
   */
  public pb4server.AllianceBossSummonInfoVo getAllianceBossSummonInfo(int index) {
    return allianceBossSummonInfo_.get(index);
  }
  /**
   * <pre>
   * 该挡内的召唤情况
   * </pre>
   *
   * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
   */
  public pb4server.AllianceBossSummonInfoVoOrBuilder getAllianceBossSummonInfoOrBuilder(
      int index) {
    return allianceBossSummonInfo_.get(index);
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
    if (score_ != 0) {
      output.writeInt32(1, score_);
    }
    for (int i = 0; i < allianceBossSummonInfo_.size(); i++) {
      output.writeMessage(2, allianceBossSummonInfo_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (score_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, score_);
    }
    for (int i = 0; i < allianceBossSummonInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, allianceBossSummonInfo_.get(i));
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
    if (!(obj instanceof pb4server.AllianceBossLivenessVo)) {
      return super.equals(obj);
    }
    pb4server.AllianceBossLivenessVo other = (pb4server.AllianceBossLivenessVo) obj;

    boolean result = true;
    result = result && (getScore()
        == other.getScore());
    result = result && getAllianceBossSummonInfoList()
        .equals(other.getAllianceBossSummonInfoList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SCORE_FIELD_NUMBER;
    hash = (53 * hash) + getScore();
    if (getAllianceBossSummonInfoCount() > 0) {
      hash = (37 * hash) + ALLIANCEBOSSSUMMONINFO_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceBossSummonInfoList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.AllianceBossLivenessVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceBossLivenessVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceBossLivenessVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceBossLivenessVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceBossLivenessVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceBossLivenessVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceBossLivenessVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceBossLivenessVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceBossLivenessVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AllianceBossLivenessVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceBossLivenessVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceBossLivenessVo parseFrom(
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
  public static Builder newBuilder(pb4server.AllianceBossLivenessVo prototype) {
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
   * Protobuf type {@code pb4server.AllianceBossLivenessVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AllianceBossLivenessVo)
      pb4server.AllianceBossLivenessVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceBossLivenessVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceBossLivenessVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AllianceBossLivenessVo.class, pb4server.AllianceBossLivenessVo.Builder.class);
    }

    // Construct using pb4server.AllianceBossLivenessVo.newBuilder()
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
        getAllianceBossSummonInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      score_ = 0;

      if (allianceBossSummonInfoBuilder_ == null) {
        allianceBossSummonInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        allianceBossSummonInfoBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceBossLivenessVo_descriptor;
    }

    public pb4server.AllianceBossLivenessVo getDefaultInstanceForType() {
      return pb4server.AllianceBossLivenessVo.getDefaultInstance();
    }

    public pb4server.AllianceBossLivenessVo build() {
      pb4server.AllianceBossLivenessVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AllianceBossLivenessVo buildPartial() {
      pb4server.AllianceBossLivenessVo result = new pb4server.AllianceBossLivenessVo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.score_ = score_;
      if (allianceBossSummonInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          allianceBossSummonInfo_ = java.util.Collections.unmodifiableList(allianceBossSummonInfo_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.allianceBossSummonInfo_ = allianceBossSummonInfo_;
      } else {
        result.allianceBossSummonInfo_ = allianceBossSummonInfoBuilder_.build();
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
      if (other instanceof pb4server.AllianceBossLivenessVo) {
        return mergeFrom((pb4server.AllianceBossLivenessVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AllianceBossLivenessVo other) {
      if (other == pb4server.AllianceBossLivenessVo.getDefaultInstance()) return this;
      if (other.getScore() != 0) {
        setScore(other.getScore());
      }
      if (allianceBossSummonInfoBuilder_ == null) {
        if (!other.allianceBossSummonInfo_.isEmpty()) {
          if (allianceBossSummonInfo_.isEmpty()) {
            allianceBossSummonInfo_ = other.allianceBossSummonInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureAllianceBossSummonInfoIsMutable();
            allianceBossSummonInfo_.addAll(other.allianceBossSummonInfo_);
          }
          onChanged();
        }
      } else {
        if (!other.allianceBossSummonInfo_.isEmpty()) {
          if (allianceBossSummonInfoBuilder_.isEmpty()) {
            allianceBossSummonInfoBuilder_.dispose();
            allianceBossSummonInfoBuilder_ = null;
            allianceBossSummonInfo_ = other.allianceBossSummonInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
            allianceBossSummonInfoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getAllianceBossSummonInfoFieldBuilder() : null;
          } else {
            allianceBossSummonInfoBuilder_.addAllMessages(other.allianceBossSummonInfo_);
          }
        }
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
      pb4server.AllianceBossLivenessVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AllianceBossLivenessVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int score_ ;
    /**
     * <pre>
     * 活跃度档
     * </pre>
     *
     * <code>int32 score = 1;</code>
     */
    public int getScore() {
      return score_;
    }
    /**
     * <pre>
     * 活跃度档
     * </pre>
     *
     * <code>int32 score = 1;</code>
     */
    public Builder setScore(int value) {
      
      score_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 活跃度档
     * </pre>
     *
     * <code>int32 score = 1;</code>
     */
    public Builder clearScore() {
      
      score_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4server.AllianceBossSummonInfoVo> allianceBossSummonInfo_ =
      java.util.Collections.emptyList();
    private void ensureAllianceBossSummonInfoIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        allianceBossSummonInfo_ = new java.util.ArrayList<pb4server.AllianceBossSummonInfoVo>(allianceBossSummonInfo_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AllianceBossSummonInfoVo, pb4server.AllianceBossSummonInfoVo.Builder, pb4server.AllianceBossSummonInfoVoOrBuilder> allianceBossSummonInfoBuilder_;

    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public java.util.List<pb4server.AllianceBossSummonInfoVo> getAllianceBossSummonInfoList() {
      if (allianceBossSummonInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(allianceBossSummonInfo_);
      } else {
        return allianceBossSummonInfoBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public int getAllianceBossSummonInfoCount() {
      if (allianceBossSummonInfoBuilder_ == null) {
        return allianceBossSummonInfo_.size();
      } else {
        return allianceBossSummonInfoBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public pb4server.AllianceBossSummonInfoVo getAllianceBossSummonInfo(int index) {
      if (allianceBossSummonInfoBuilder_ == null) {
        return allianceBossSummonInfo_.get(index);
      } else {
        return allianceBossSummonInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public Builder setAllianceBossSummonInfo(
        int index, pb4server.AllianceBossSummonInfoVo value) {
      if (allianceBossSummonInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceBossSummonInfoIsMutable();
        allianceBossSummonInfo_.set(index, value);
        onChanged();
      } else {
        allianceBossSummonInfoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public Builder setAllianceBossSummonInfo(
        int index, pb4server.AllianceBossSummonInfoVo.Builder builderForValue) {
      if (allianceBossSummonInfoBuilder_ == null) {
        ensureAllianceBossSummonInfoIsMutable();
        allianceBossSummonInfo_.set(index, builderForValue.build());
        onChanged();
      } else {
        allianceBossSummonInfoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public Builder addAllianceBossSummonInfo(pb4server.AllianceBossSummonInfoVo value) {
      if (allianceBossSummonInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceBossSummonInfoIsMutable();
        allianceBossSummonInfo_.add(value);
        onChanged();
      } else {
        allianceBossSummonInfoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public Builder addAllianceBossSummonInfo(
        int index, pb4server.AllianceBossSummonInfoVo value) {
      if (allianceBossSummonInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllianceBossSummonInfoIsMutable();
        allianceBossSummonInfo_.add(index, value);
        onChanged();
      } else {
        allianceBossSummonInfoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public Builder addAllianceBossSummonInfo(
        pb4server.AllianceBossSummonInfoVo.Builder builderForValue) {
      if (allianceBossSummonInfoBuilder_ == null) {
        ensureAllianceBossSummonInfoIsMutable();
        allianceBossSummonInfo_.add(builderForValue.build());
        onChanged();
      } else {
        allianceBossSummonInfoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public Builder addAllianceBossSummonInfo(
        int index, pb4server.AllianceBossSummonInfoVo.Builder builderForValue) {
      if (allianceBossSummonInfoBuilder_ == null) {
        ensureAllianceBossSummonInfoIsMutable();
        allianceBossSummonInfo_.add(index, builderForValue.build());
        onChanged();
      } else {
        allianceBossSummonInfoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public Builder addAllAllianceBossSummonInfo(
        java.lang.Iterable<? extends pb4server.AllianceBossSummonInfoVo> values) {
      if (allianceBossSummonInfoBuilder_ == null) {
        ensureAllianceBossSummonInfoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, allianceBossSummonInfo_);
        onChanged();
      } else {
        allianceBossSummonInfoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public Builder clearAllianceBossSummonInfo() {
      if (allianceBossSummonInfoBuilder_ == null) {
        allianceBossSummonInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        allianceBossSummonInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public Builder removeAllianceBossSummonInfo(int index) {
      if (allianceBossSummonInfoBuilder_ == null) {
        ensureAllianceBossSummonInfoIsMutable();
        allianceBossSummonInfo_.remove(index);
        onChanged();
      } else {
        allianceBossSummonInfoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public pb4server.AllianceBossSummonInfoVo.Builder getAllianceBossSummonInfoBuilder(
        int index) {
      return getAllianceBossSummonInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public pb4server.AllianceBossSummonInfoVoOrBuilder getAllianceBossSummonInfoOrBuilder(
        int index) {
      if (allianceBossSummonInfoBuilder_ == null) {
        return allianceBossSummonInfo_.get(index);  } else {
        return allianceBossSummonInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public java.util.List<? extends pb4server.AllianceBossSummonInfoVoOrBuilder> 
         getAllianceBossSummonInfoOrBuilderList() {
      if (allianceBossSummonInfoBuilder_ != null) {
        return allianceBossSummonInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(allianceBossSummonInfo_);
      }
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public pb4server.AllianceBossSummonInfoVo.Builder addAllianceBossSummonInfoBuilder() {
      return getAllianceBossSummonInfoFieldBuilder().addBuilder(
          pb4server.AllianceBossSummonInfoVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public pb4server.AllianceBossSummonInfoVo.Builder addAllianceBossSummonInfoBuilder(
        int index) {
      return getAllianceBossSummonInfoFieldBuilder().addBuilder(
          index, pb4server.AllianceBossSummonInfoVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 该挡内的召唤情况
     * </pre>
     *
     * <code>repeated .pb4server.AllianceBossSummonInfoVo allianceBossSummonInfo = 2;</code>
     */
    public java.util.List<pb4server.AllianceBossSummonInfoVo.Builder> 
         getAllianceBossSummonInfoBuilderList() {
      return getAllianceBossSummonInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AllianceBossSummonInfoVo, pb4server.AllianceBossSummonInfoVo.Builder, pb4server.AllianceBossSummonInfoVoOrBuilder> 
        getAllianceBossSummonInfoFieldBuilder() {
      if (allianceBossSummonInfoBuilder_ == null) {
        allianceBossSummonInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.AllianceBossSummonInfoVo, pb4server.AllianceBossSummonInfoVo.Builder, pb4server.AllianceBossSummonInfoVoOrBuilder>(
                allianceBossSummonInfo_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        allianceBossSummonInfo_ = null;
      }
      return allianceBossSummonInfoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.AllianceBossLivenessVo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AllianceBossLivenessVo)
  private static final pb4server.AllianceBossLivenessVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AllianceBossLivenessVo();
  }

  public static pb4server.AllianceBossLivenessVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllianceBossLivenessVo>
      PARSER = new com.google.protobuf.AbstractParser<AllianceBossLivenessVo>() {
    public AllianceBossLivenessVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceBossLivenessVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceBossLivenessVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceBossLivenessVo> getParserForType() {
    return PARSER;
  }

  public pb4server.AllianceBossLivenessVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

