// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * <pre>
 * 设置联盟申请实力限制
 * </pre>
 *
 * Protobuf type {@code pb4server.SetAlliancePowerLimitAskReq}
 */
public  final class SetAlliancePowerLimitAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.SetAlliancePowerLimitAskReq)
    SetAlliancePowerLimitAskReqOrBuilder {
  // Use SetAlliancePowerLimitAskReq.newBuilder() to construct.
  private SetAlliancePowerLimitAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SetAlliancePowerLimitAskReq() {
    power_ = 0L;
    canAddPower_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private SetAlliancePowerLimitAskReq(
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

            power_ = input.readInt64();
            break;
          }
          case 16: {

            canAddPower_ = input.readInt64();
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
    return pb4server.InternalPkt.internal_static_pb4server_SetAlliancePowerLimitAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_SetAlliancePowerLimitAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.SetAlliancePowerLimitAskReq.class, pb4server.SetAlliancePowerLimitAskReq.Builder.class);
  }

  public static final int POWER_FIELD_NUMBER = 1;
  private long power_;
  /**
   * <code>int64 power = 1;</code>
   */
  public long getPower() {
    return power_;
  }

  public static final int CANADDPOWER_FIELD_NUMBER = 2;
  private long canAddPower_;
  /**
   * <code>int64 canAddPower = 2;</code>
   */
  public long getCanAddPower() {
    return canAddPower_;
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
    if (power_ != 0L) {
      output.writeInt64(1, power_);
    }
    if (canAddPower_ != 0L) {
      output.writeInt64(2, canAddPower_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (power_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, power_);
    }
    if (canAddPower_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, canAddPower_);
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
    if (!(obj instanceof pb4server.SetAlliancePowerLimitAskReq)) {
      return super.equals(obj);
    }
    pb4server.SetAlliancePowerLimitAskReq other = (pb4server.SetAlliancePowerLimitAskReq) obj;

    boolean result = true;
    result = result && (getPower()
        == other.getPower());
    result = result && (getCanAddPower()
        == other.getCanAddPower());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + POWER_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPower());
    hash = (37 * hash) + CANADDPOWER_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getCanAddPower());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.SetAlliancePowerLimitAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.SetAlliancePowerLimitAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.SetAlliancePowerLimitAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.SetAlliancePowerLimitAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.SetAlliancePowerLimitAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.SetAlliancePowerLimitAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.SetAlliancePowerLimitAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.SetAlliancePowerLimitAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.SetAlliancePowerLimitAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.SetAlliancePowerLimitAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.SetAlliancePowerLimitAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.SetAlliancePowerLimitAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.SetAlliancePowerLimitAskReq prototype) {
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
   * 设置联盟申请实力限制
   * </pre>
   *
   * Protobuf type {@code pb4server.SetAlliancePowerLimitAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.SetAlliancePowerLimitAskReq)
      pb4server.SetAlliancePowerLimitAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_SetAlliancePowerLimitAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_SetAlliancePowerLimitAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.SetAlliancePowerLimitAskReq.class, pb4server.SetAlliancePowerLimitAskReq.Builder.class);
    }

    // Construct using pb4server.SetAlliancePowerLimitAskReq.newBuilder()
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
      power_ = 0L;

      canAddPower_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_SetAlliancePowerLimitAskReq_descriptor;
    }

    public pb4server.SetAlliancePowerLimitAskReq getDefaultInstanceForType() {
      return pb4server.SetAlliancePowerLimitAskReq.getDefaultInstance();
    }

    public pb4server.SetAlliancePowerLimitAskReq build() {
      pb4server.SetAlliancePowerLimitAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.SetAlliancePowerLimitAskReq buildPartial() {
      pb4server.SetAlliancePowerLimitAskReq result = new pb4server.SetAlliancePowerLimitAskReq(this);
      result.power_ = power_;
      result.canAddPower_ = canAddPower_;
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
      if (other instanceof pb4server.SetAlliancePowerLimitAskReq) {
        return mergeFrom((pb4server.SetAlliancePowerLimitAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.SetAlliancePowerLimitAskReq other) {
      if (other == pb4server.SetAlliancePowerLimitAskReq.getDefaultInstance()) return this;
      if (other.getPower() != 0L) {
        setPower(other.getPower());
      }
      if (other.getCanAddPower() != 0L) {
        setCanAddPower(other.getCanAddPower());
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
      pb4server.SetAlliancePowerLimitAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.SetAlliancePowerLimitAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long power_ ;
    /**
     * <code>int64 power = 1;</code>
     */
    public long getPower() {
      return power_;
    }
    /**
     * <code>int64 power = 1;</code>
     */
    public Builder setPower(long value) {
      
      power_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 power = 1;</code>
     */
    public Builder clearPower() {
      
      power_ = 0L;
      onChanged();
      return this;
    }

    private long canAddPower_ ;
    /**
     * <code>int64 canAddPower = 2;</code>
     */
    public long getCanAddPower() {
      return canAddPower_;
    }
    /**
     * <code>int64 canAddPower = 2;</code>
     */
    public Builder setCanAddPower(long value) {
      
      canAddPower_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 canAddPower = 2;</code>
     */
    public Builder clearCanAddPower() {
      
      canAddPower_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:pb4server.SetAlliancePowerLimitAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.SetAlliancePowerLimitAskReq)
  private static final pb4server.SetAlliancePowerLimitAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.SetAlliancePowerLimitAskReq();
  }

  public static pb4server.SetAlliancePowerLimitAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SetAlliancePowerLimitAskReq>
      PARSER = new com.google.protobuf.AbstractParser<SetAlliancePowerLimitAskReq>() {
    public SetAlliancePowerLimitAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SetAlliancePowerLimitAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SetAlliancePowerLimitAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SetAlliancePowerLimitAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.SetAlliancePowerLimitAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

