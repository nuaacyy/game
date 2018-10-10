// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * <pre>
 * 触发心跳
 * </pre>
 *
 * Protobuf type {@code pb4server.TriggerHeartAskReq}
 */
public  final class TriggerHeartAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.TriggerHeartAskReq)
    TriggerHeartAskReqOrBuilder {
  // Use TriggerHeartAskReq.newBuilder() to construct.
  private TriggerHeartAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TriggerHeartAskReq() {
    action_ = 0;
    actionId_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private TriggerHeartAskReq(
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

            action_ = input.readInt32();
            break;
          }
          case 16: {

            actionId_ = input.readInt64();
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
    return pb4server.InternalHkt.internal_static_pb4server_TriggerHeartAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_TriggerHeartAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.TriggerHeartAskReq.class, pb4server.TriggerHeartAskReq.Builder.class);
  }

  public static final int ACTION_FIELD_NUMBER = 1;
  private int action_;
  /**
   * <code>int32 action = 1;</code>
   */
  public int getAction() {
    return action_;
  }

  public static final int ACTIONID_FIELD_NUMBER = 2;
  private long actionId_;
  /**
   * <code>int64 actionId = 2;</code>
   */
  public long getActionId() {
    return actionId_;
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
    if (action_ != 0) {
      output.writeInt32(1, action_);
    }
    if (actionId_ != 0L) {
      output.writeInt64(2, actionId_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (action_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, action_);
    }
    if (actionId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, actionId_);
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
    if (!(obj instanceof pb4server.TriggerHeartAskReq)) {
      return super.equals(obj);
    }
    pb4server.TriggerHeartAskReq other = (pb4server.TriggerHeartAskReq) obj;

    boolean result = true;
    result = result && (getAction()
        == other.getAction());
    result = result && (getActionId()
        == other.getActionId());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ACTION_FIELD_NUMBER;
    hash = (53 * hash) + getAction();
    hash = (37 * hash) + ACTIONID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getActionId());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.TriggerHeartAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.TriggerHeartAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.TriggerHeartAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.TriggerHeartAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.TriggerHeartAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.TriggerHeartAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.TriggerHeartAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.TriggerHeartAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.TriggerHeartAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.TriggerHeartAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.TriggerHeartAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.TriggerHeartAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.TriggerHeartAskReq prototype) {
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
   * 触发心跳
   * </pre>
   *
   * Protobuf type {@code pb4server.TriggerHeartAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.TriggerHeartAskReq)
      pb4server.TriggerHeartAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_TriggerHeartAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_TriggerHeartAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.TriggerHeartAskReq.class, pb4server.TriggerHeartAskReq.Builder.class);
    }

    // Construct using pb4server.TriggerHeartAskReq.newBuilder()
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
      action_ = 0;

      actionId_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_TriggerHeartAskReq_descriptor;
    }

    public pb4server.TriggerHeartAskReq getDefaultInstanceForType() {
      return pb4server.TriggerHeartAskReq.getDefaultInstance();
    }

    public pb4server.TriggerHeartAskReq build() {
      pb4server.TriggerHeartAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.TriggerHeartAskReq buildPartial() {
      pb4server.TriggerHeartAskReq result = new pb4server.TriggerHeartAskReq(this);
      result.action_ = action_;
      result.actionId_ = actionId_;
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
      if (other instanceof pb4server.TriggerHeartAskReq) {
        return mergeFrom((pb4server.TriggerHeartAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.TriggerHeartAskReq other) {
      if (other == pb4server.TriggerHeartAskReq.getDefaultInstance()) return this;
      if (other.getAction() != 0) {
        setAction(other.getAction());
      }
      if (other.getActionId() != 0L) {
        setActionId(other.getActionId());
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
      pb4server.TriggerHeartAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.TriggerHeartAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int action_ ;
    /**
     * <code>int32 action = 1;</code>
     */
    public int getAction() {
      return action_;
    }
    /**
     * <code>int32 action = 1;</code>
     */
    public Builder setAction(int value) {
      
      action_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 action = 1;</code>
     */
    public Builder clearAction() {
      
      action_ = 0;
      onChanged();
      return this;
    }

    private long actionId_ ;
    /**
     * <code>int64 actionId = 2;</code>
     */
    public long getActionId() {
      return actionId_;
    }
    /**
     * <code>int64 actionId = 2;</code>
     */
    public Builder setActionId(long value) {
      
      actionId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 actionId = 2;</code>
     */
    public Builder clearActionId() {
      
      actionId_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:pb4server.TriggerHeartAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.TriggerHeartAskReq)
  private static final pb4server.TriggerHeartAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.TriggerHeartAskReq();
  }

  public static pb4server.TriggerHeartAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TriggerHeartAskReq>
      PARSER = new com.google.protobuf.AbstractParser<TriggerHeartAskReq>() {
    public TriggerHeartAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TriggerHeartAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TriggerHeartAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TriggerHeartAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.TriggerHeartAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
