// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.RedPointVo}
 */
public  final class RedPointVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.RedPointVo)
    RedPointVoOrBuilder {
  // Use RedPointVo.newBuilder() to construct.
  private RedPointVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RedPointVo() {
    redPointType_ = 0;
    redPointId_ = 0L;
    redPointShowTime_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private RedPointVo(
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

            redPointType_ = input.readInt32();
            break;
          }
          case 16: {

            redPointId_ = input.readInt64();
            break;
          }
          case 24: {

            redPointShowTime_ = input.readInt32();
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
    return pb4server.InternalWkt.internal_static_pb4server_RedPointVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_RedPointVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.RedPointVo.class, pb4server.RedPointVo.Builder.class);
  }

  public static final int REDPOINTTYPE_FIELD_NUMBER = 1;
  private int redPointType_;
  /**
   * <pre>
   *红点类型 1-联盟外交 2-联盟标记 3-入帮申请 4-私聊未读消息小红点 5-群聊未读消息小红点 6-联盟帮助小红点 7-联盟科技优先小红点
   * </pre>
   *
   * <code>int32 redPointType = 1;</code>
   */
  public int getRedPointType() {
    return redPointType_;
  }

  public static final int REDPOINTID_FIELD_NUMBER = 2;
  private long redPointId_;
  /**
   * <pre>
   *红点在本类型中的子目录
   * </pre>
   *
   * <code>int64 redPointId = 2;</code>
   */
  public long getRedPointId() {
    return redPointId_;
  }

  public static final int REDPOINTSHOWTIME_FIELD_NUMBER = 3;
  private int redPointShowTime_;
  /**
   * <pre>
   *红点出现的时间戳
   * </pre>
   *
   * <code>int32 redPointShowTime = 3;</code>
   */
  public int getRedPointShowTime() {
    return redPointShowTime_;
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
    if (redPointType_ != 0) {
      output.writeInt32(1, redPointType_);
    }
    if (redPointId_ != 0L) {
      output.writeInt64(2, redPointId_);
    }
    if (redPointShowTime_ != 0) {
      output.writeInt32(3, redPointShowTime_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (redPointType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, redPointType_);
    }
    if (redPointId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, redPointId_);
    }
    if (redPointShowTime_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, redPointShowTime_);
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
    if (!(obj instanceof pb4server.RedPointVo)) {
      return super.equals(obj);
    }
    pb4server.RedPointVo other = (pb4server.RedPointVo) obj;

    boolean result = true;
    result = result && (getRedPointType()
        == other.getRedPointType());
    result = result && (getRedPointId()
        == other.getRedPointId());
    result = result && (getRedPointShowTime()
        == other.getRedPointShowTime());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + REDPOINTTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getRedPointType();
    hash = (37 * hash) + REDPOINTID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getRedPointId());
    hash = (37 * hash) + REDPOINTSHOWTIME_FIELD_NUMBER;
    hash = (53 * hash) + getRedPointShowTime();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.RedPointVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.RedPointVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.RedPointVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.RedPointVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.RedPointVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.RedPointVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.RedPointVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.RedPointVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.RedPointVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.RedPointVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.RedPointVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.RedPointVo parseFrom(
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
  public static Builder newBuilder(pb4server.RedPointVo prototype) {
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
   * Protobuf type {@code pb4server.RedPointVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.RedPointVo)
      pb4server.RedPointVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_RedPointVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_RedPointVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.RedPointVo.class, pb4server.RedPointVo.Builder.class);
    }

    // Construct using pb4server.RedPointVo.newBuilder()
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
      redPointType_ = 0;

      redPointId_ = 0L;

      redPointShowTime_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_RedPointVo_descriptor;
    }

    public pb4server.RedPointVo getDefaultInstanceForType() {
      return pb4server.RedPointVo.getDefaultInstance();
    }

    public pb4server.RedPointVo build() {
      pb4server.RedPointVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.RedPointVo buildPartial() {
      pb4server.RedPointVo result = new pb4server.RedPointVo(this);
      result.redPointType_ = redPointType_;
      result.redPointId_ = redPointId_;
      result.redPointShowTime_ = redPointShowTime_;
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
      if (other instanceof pb4server.RedPointVo) {
        return mergeFrom((pb4server.RedPointVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.RedPointVo other) {
      if (other == pb4server.RedPointVo.getDefaultInstance()) return this;
      if (other.getRedPointType() != 0) {
        setRedPointType(other.getRedPointType());
      }
      if (other.getRedPointId() != 0L) {
        setRedPointId(other.getRedPointId());
      }
      if (other.getRedPointShowTime() != 0) {
        setRedPointShowTime(other.getRedPointShowTime());
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
      pb4server.RedPointVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.RedPointVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int redPointType_ ;
    /**
     * <pre>
     *红点类型 1-联盟外交 2-联盟标记 3-入帮申请 4-私聊未读消息小红点 5-群聊未读消息小红点 6-联盟帮助小红点 7-联盟科技优先小红点
     * </pre>
     *
     * <code>int32 redPointType = 1;</code>
     */
    public int getRedPointType() {
      return redPointType_;
    }
    /**
     * <pre>
     *红点类型 1-联盟外交 2-联盟标记 3-入帮申请 4-私聊未读消息小红点 5-群聊未读消息小红点 6-联盟帮助小红点 7-联盟科技优先小红点
     * </pre>
     *
     * <code>int32 redPointType = 1;</code>
     */
    public Builder setRedPointType(int value) {
      
      redPointType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *红点类型 1-联盟外交 2-联盟标记 3-入帮申请 4-私聊未读消息小红点 5-群聊未读消息小红点 6-联盟帮助小红点 7-联盟科技优先小红点
     * </pre>
     *
     * <code>int32 redPointType = 1;</code>
     */
    public Builder clearRedPointType() {
      
      redPointType_ = 0;
      onChanged();
      return this;
    }

    private long redPointId_ ;
    /**
     * <pre>
     *红点在本类型中的子目录
     * </pre>
     *
     * <code>int64 redPointId = 2;</code>
     */
    public long getRedPointId() {
      return redPointId_;
    }
    /**
     * <pre>
     *红点在本类型中的子目录
     * </pre>
     *
     * <code>int64 redPointId = 2;</code>
     */
    public Builder setRedPointId(long value) {
      
      redPointId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *红点在本类型中的子目录
     * </pre>
     *
     * <code>int64 redPointId = 2;</code>
     */
    public Builder clearRedPointId() {
      
      redPointId_ = 0L;
      onChanged();
      return this;
    }

    private int redPointShowTime_ ;
    /**
     * <pre>
     *红点出现的时间戳
     * </pre>
     *
     * <code>int32 redPointShowTime = 3;</code>
     */
    public int getRedPointShowTime() {
      return redPointShowTime_;
    }
    /**
     * <pre>
     *红点出现的时间戳
     * </pre>
     *
     * <code>int32 redPointShowTime = 3;</code>
     */
    public Builder setRedPointShowTime(int value) {
      
      redPointShowTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *红点出现的时间戳
     * </pre>
     *
     * <code>int32 redPointShowTime = 3;</code>
     */
    public Builder clearRedPointShowTime() {
      
      redPointShowTime_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.RedPointVo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.RedPointVo)
  private static final pb4server.RedPointVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.RedPointVo();
  }

  public static pb4server.RedPointVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RedPointVo>
      PARSER = new com.google.protobuf.AbstractParser<RedPointVo>() {
    public RedPointVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RedPointVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RedPointVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RedPointVo> getParserForType() {
    return PARSER;
  }

  public pb4server.RedPointVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

