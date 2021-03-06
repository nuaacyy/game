// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3140
 * 服务器 -&gt; 客户端
 * 行军线的新增与删除
 * </pre>
 *
 * Protobuf type {@code client2server.WalkRobotShow}
 */
public  final class WalkRobotShow extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.WalkRobotShow)
    WalkRobotShowOrBuilder {
  // Use WalkRobotShow.newBuilder() to construct.
  private WalkRobotShow(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private WalkRobotShow() {
    showTye_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private WalkRobotShow(
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
            showTye_ = input.readInt32();
            break;
          }
          case 18: {
            pb4client.WalkRobot.Builder subBuilder = null;
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
              subBuilder = walkRobots_.toBuilder();
            }
            walkRobots_ = input.readMessage(pb4client.WalkRobot.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(walkRobots_);
              walkRobots_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000002;
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
    return pb4client.War2GamePkt.internal_static_client2server_WalkRobotShow_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_WalkRobotShow_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.WalkRobotShow.class, pb4client.WalkRobotShow.Builder.class);
  }

  private int bitField0_;
  public static final int SHOWTYE_FIELD_NUMBER = 1;
  private int showTye_;
  /**
   * <pre>
   * 1-新增  2-删除
   * </pre>
   *
   * <code>required int32 showTye = 1;</code>
   */
  public boolean hasShowTye() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 1-新增  2-删除
   * </pre>
   *
   * <code>required int32 showTye = 1;</code>
   */
  public int getShowTye() {
    return showTye_;
  }

  public static final int WALKROBOTS_FIELD_NUMBER = 2;
  private pb4client.WalkRobot walkRobots_;
  /**
   * <code>required .client2server.WalkRobot walkRobots = 2;</code>
   */
  public boolean hasWalkRobots() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required .client2server.WalkRobot walkRobots = 2;</code>
   */
  public pb4client.WalkRobot getWalkRobots() {
    return walkRobots_ == null ? pb4client.WalkRobot.getDefaultInstance() : walkRobots_;
  }
  /**
   * <code>required .client2server.WalkRobot walkRobots = 2;</code>
   */
  public pb4client.WalkRobotOrBuilder getWalkRobotsOrBuilder() {
    return walkRobots_ == null ? pb4client.WalkRobot.getDefaultInstance() : walkRobots_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasShowTye()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasWalkRobots()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getWalkRobots().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, showTye_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeMessage(2, getWalkRobots());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, showTye_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getWalkRobots());
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
    if (!(obj instanceof pb4client.WalkRobotShow)) {
      return super.equals(obj);
    }
    pb4client.WalkRobotShow other = (pb4client.WalkRobotShow) obj;

    boolean result = true;
    result = result && (hasShowTye() == other.hasShowTye());
    if (hasShowTye()) {
      result = result && (getShowTye()
          == other.getShowTye());
    }
    result = result && (hasWalkRobots() == other.hasWalkRobots());
    if (hasWalkRobots()) {
      result = result && getWalkRobots()
          .equals(other.getWalkRobots());
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
    if (hasShowTye()) {
      hash = (37 * hash) + SHOWTYE_FIELD_NUMBER;
      hash = (53 * hash) + getShowTye();
    }
    if (hasWalkRobots()) {
      hash = (37 * hash) + WALKROBOTS_FIELD_NUMBER;
      hash = (53 * hash) + getWalkRobots().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.WalkRobotShow parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WalkRobotShow parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WalkRobotShow parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WalkRobotShow parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WalkRobotShow parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WalkRobotShow parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WalkRobotShow parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.WalkRobotShow parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.WalkRobotShow parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.WalkRobotShow parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.WalkRobotShow parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.WalkRobotShow parseFrom(
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
  public static Builder newBuilder(pb4client.WalkRobotShow prototype) {
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
   * msgType = 3140
   * 服务器 -&gt; 客户端
   * 行军线的新增与删除
   * </pre>
   *
   * Protobuf type {@code client2server.WalkRobotShow}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.WalkRobotShow)
      pb4client.WalkRobotShowOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_WalkRobotShow_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_WalkRobotShow_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.WalkRobotShow.class, pb4client.WalkRobotShow.Builder.class);
    }

    // Construct using pb4client.WalkRobotShow.newBuilder()
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
        getWalkRobotsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      showTye_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (walkRobotsBuilder_ == null) {
        walkRobots_ = null;
      } else {
        walkRobotsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_WalkRobotShow_descriptor;
    }

    public pb4client.WalkRobotShow getDefaultInstanceForType() {
      return pb4client.WalkRobotShow.getDefaultInstance();
    }

    public pb4client.WalkRobotShow build() {
      pb4client.WalkRobotShow result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.WalkRobotShow buildPartial() {
      pb4client.WalkRobotShow result = new pb4client.WalkRobotShow(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.showTye_ = showTye_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      if (walkRobotsBuilder_ == null) {
        result.walkRobots_ = walkRobots_;
      } else {
        result.walkRobots_ = walkRobotsBuilder_.build();
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
      if (other instanceof pb4client.WalkRobotShow) {
        return mergeFrom((pb4client.WalkRobotShow)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.WalkRobotShow other) {
      if (other == pb4client.WalkRobotShow.getDefaultInstance()) return this;
      if (other.hasShowTye()) {
        setShowTye(other.getShowTye());
      }
      if (other.hasWalkRobots()) {
        mergeWalkRobots(other.getWalkRobots());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasShowTye()) {
        return false;
      }
      if (!hasWalkRobots()) {
        return false;
      }
      if (!getWalkRobots().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.WalkRobotShow parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.WalkRobotShow) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int showTye_ ;
    /**
     * <pre>
     * 1-新增  2-删除
     * </pre>
     *
     * <code>required int32 showTye = 1;</code>
     */
    public boolean hasShowTye() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 1-新增  2-删除
     * </pre>
     *
     * <code>required int32 showTye = 1;</code>
     */
    public int getShowTye() {
      return showTye_;
    }
    /**
     * <pre>
     * 1-新增  2-删除
     * </pre>
     *
     * <code>required int32 showTye = 1;</code>
     */
    public Builder setShowTye(int value) {
      bitField0_ |= 0x00000001;
      showTye_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 1-新增  2-删除
     * </pre>
     *
     * <code>required int32 showTye = 1;</code>
     */
    public Builder clearShowTye() {
      bitField0_ = (bitField0_ & ~0x00000001);
      showTye_ = 0;
      onChanged();
      return this;
    }

    private pb4client.WalkRobot walkRobots_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.WalkRobot, pb4client.WalkRobot.Builder, pb4client.WalkRobotOrBuilder> walkRobotsBuilder_;
    /**
     * <code>required .client2server.WalkRobot walkRobots = 2;</code>
     */
    public boolean hasWalkRobots() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required .client2server.WalkRobot walkRobots = 2;</code>
     */
    public pb4client.WalkRobot getWalkRobots() {
      if (walkRobotsBuilder_ == null) {
        return walkRobots_ == null ? pb4client.WalkRobot.getDefaultInstance() : walkRobots_;
      } else {
        return walkRobotsBuilder_.getMessage();
      }
    }
    /**
     * <code>required .client2server.WalkRobot walkRobots = 2;</code>
     */
    public Builder setWalkRobots(pb4client.WalkRobot value) {
      if (walkRobotsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        walkRobots_ = value;
        onChanged();
      } else {
        walkRobotsBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>required .client2server.WalkRobot walkRobots = 2;</code>
     */
    public Builder setWalkRobots(
        pb4client.WalkRobot.Builder builderForValue) {
      if (walkRobotsBuilder_ == null) {
        walkRobots_ = builderForValue.build();
        onChanged();
      } else {
        walkRobotsBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>required .client2server.WalkRobot walkRobots = 2;</code>
     */
    public Builder mergeWalkRobots(pb4client.WalkRobot value) {
      if (walkRobotsBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002) &&
            walkRobots_ != null &&
            walkRobots_ != pb4client.WalkRobot.getDefaultInstance()) {
          walkRobots_ =
            pb4client.WalkRobot.newBuilder(walkRobots_).mergeFrom(value).buildPartial();
        } else {
          walkRobots_ = value;
        }
        onChanged();
      } else {
        walkRobotsBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>required .client2server.WalkRobot walkRobots = 2;</code>
     */
    public Builder clearWalkRobots() {
      if (walkRobotsBuilder_ == null) {
        walkRobots_ = null;
        onChanged();
      } else {
        walkRobotsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    /**
     * <code>required .client2server.WalkRobot walkRobots = 2;</code>
     */
    public pb4client.WalkRobot.Builder getWalkRobotsBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getWalkRobotsFieldBuilder().getBuilder();
    }
    /**
     * <code>required .client2server.WalkRobot walkRobots = 2;</code>
     */
    public pb4client.WalkRobotOrBuilder getWalkRobotsOrBuilder() {
      if (walkRobotsBuilder_ != null) {
        return walkRobotsBuilder_.getMessageOrBuilder();
      } else {
        return walkRobots_ == null ?
            pb4client.WalkRobot.getDefaultInstance() : walkRobots_;
      }
    }
    /**
     * <code>required .client2server.WalkRobot walkRobots = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.WalkRobot, pb4client.WalkRobot.Builder, pb4client.WalkRobotOrBuilder> 
        getWalkRobotsFieldBuilder() {
      if (walkRobotsBuilder_ == null) {
        walkRobotsBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.WalkRobot, pb4client.WalkRobot.Builder, pb4client.WalkRobotOrBuilder>(
                getWalkRobots(),
                getParentForChildren(),
                isClean());
        walkRobots_ = null;
      }
      return walkRobotsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.WalkRobotShow)
  }

  // @@protoc_insertion_point(class_scope:client2server.WalkRobotShow)
  private static final pb4client.WalkRobotShow DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.WalkRobotShow();
  }

  public static pb4client.WalkRobotShow getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<WalkRobotShow>
      PARSER = new com.google.protobuf.AbstractParser<WalkRobotShow>() {
    public WalkRobotShow parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new WalkRobotShow(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<WalkRobotShow> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<WalkRobotShow> getParserForType() {
    return PARSER;
  }

  public pb4client.WalkRobotShow getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

