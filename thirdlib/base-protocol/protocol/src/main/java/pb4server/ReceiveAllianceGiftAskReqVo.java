// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.ReceiveAllianceGiftAskReqVo}
 */
public  final class ReceiveAllianceGiftAskReqVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.ReceiveAllianceGiftAskReqVo)
    ReceiveAllianceGiftAskReqVoOrBuilder {
  // Use ReceiveAllianceGiftAskReqVo.newBuilder() to construct.
  private ReceiveAllianceGiftAskReqVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ReceiveAllianceGiftAskReqVo() {
    giftId_ = 0;
    giftNum_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ReceiveAllianceGiftAskReqVo(
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

            giftId_ = input.readInt32();
            break;
          }
          case 16: {

            giftNum_ = input.readInt32();
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
    return pb4server.InternalPkt.internal_static_pb4server_ReceiveAllianceGiftAskReqVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_ReceiveAllianceGiftAskReqVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.ReceiveAllianceGiftAskReqVo.class, pb4server.ReceiveAllianceGiftAskReqVo.Builder.class);
  }

  public static final int GIFTID_FIELD_NUMBER = 1;
  private int giftId_;
  /**
   * <code>int32 giftId = 1;</code>
   */
  public int getGiftId() {
    return giftId_;
  }

  public static final int GIFTNUM_FIELD_NUMBER = 2;
  private int giftNum_;
  /**
   * <code>int32 giftNum = 2;</code>
   */
  public int getGiftNum() {
    return giftNum_;
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
    if (giftId_ != 0) {
      output.writeInt32(1, giftId_);
    }
    if (giftNum_ != 0) {
      output.writeInt32(2, giftNum_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (giftId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, giftId_);
    }
    if (giftNum_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, giftNum_);
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
    if (!(obj instanceof pb4server.ReceiveAllianceGiftAskReqVo)) {
      return super.equals(obj);
    }
    pb4server.ReceiveAllianceGiftAskReqVo other = (pb4server.ReceiveAllianceGiftAskReqVo) obj;

    boolean result = true;
    result = result && (getGiftId()
        == other.getGiftId());
    result = result && (getGiftNum()
        == other.getGiftNum());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + GIFTID_FIELD_NUMBER;
    hash = (53 * hash) + getGiftId();
    hash = (37 * hash) + GIFTNUM_FIELD_NUMBER;
    hash = (53 * hash) + getGiftNum();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.ReceiveAllianceGiftAskReqVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ReceiveAllianceGiftAskReqVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ReceiveAllianceGiftAskReqVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ReceiveAllianceGiftAskReqVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ReceiveAllianceGiftAskReqVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ReceiveAllianceGiftAskReqVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ReceiveAllianceGiftAskReqVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.ReceiveAllianceGiftAskReqVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.ReceiveAllianceGiftAskReqVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.ReceiveAllianceGiftAskReqVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.ReceiveAllianceGiftAskReqVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.ReceiveAllianceGiftAskReqVo parseFrom(
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
  public static Builder newBuilder(pb4server.ReceiveAllianceGiftAskReqVo prototype) {
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
   * Protobuf type {@code pb4server.ReceiveAllianceGiftAskReqVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.ReceiveAllianceGiftAskReqVo)
      pb4server.ReceiveAllianceGiftAskReqVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_ReceiveAllianceGiftAskReqVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_ReceiveAllianceGiftAskReqVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.ReceiveAllianceGiftAskReqVo.class, pb4server.ReceiveAllianceGiftAskReqVo.Builder.class);
    }

    // Construct using pb4server.ReceiveAllianceGiftAskReqVo.newBuilder()
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
      giftId_ = 0;

      giftNum_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_ReceiveAllianceGiftAskReqVo_descriptor;
    }

    public pb4server.ReceiveAllianceGiftAskReqVo getDefaultInstanceForType() {
      return pb4server.ReceiveAllianceGiftAskReqVo.getDefaultInstance();
    }

    public pb4server.ReceiveAllianceGiftAskReqVo build() {
      pb4server.ReceiveAllianceGiftAskReqVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.ReceiveAllianceGiftAskReqVo buildPartial() {
      pb4server.ReceiveAllianceGiftAskReqVo result = new pb4server.ReceiveAllianceGiftAskReqVo(this);
      result.giftId_ = giftId_;
      result.giftNum_ = giftNum_;
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
      if (other instanceof pb4server.ReceiveAllianceGiftAskReqVo) {
        return mergeFrom((pb4server.ReceiveAllianceGiftAskReqVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.ReceiveAllianceGiftAskReqVo other) {
      if (other == pb4server.ReceiveAllianceGiftAskReqVo.getDefaultInstance()) return this;
      if (other.getGiftId() != 0) {
        setGiftId(other.getGiftId());
      }
      if (other.getGiftNum() != 0) {
        setGiftNum(other.getGiftNum());
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
      pb4server.ReceiveAllianceGiftAskReqVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.ReceiveAllianceGiftAskReqVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int giftId_ ;
    /**
     * <code>int32 giftId = 1;</code>
     */
    public int getGiftId() {
      return giftId_;
    }
    /**
     * <code>int32 giftId = 1;</code>
     */
    public Builder setGiftId(int value) {
      
      giftId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 giftId = 1;</code>
     */
    public Builder clearGiftId() {
      
      giftId_ = 0;
      onChanged();
      return this;
    }

    private int giftNum_ ;
    /**
     * <code>int32 giftNum = 2;</code>
     */
    public int getGiftNum() {
      return giftNum_;
    }
    /**
     * <code>int32 giftNum = 2;</code>
     */
    public Builder setGiftNum(int value) {
      
      giftNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 giftNum = 2;</code>
     */
    public Builder clearGiftNum() {
      
      giftNum_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.ReceiveAllianceGiftAskReqVo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.ReceiveAllianceGiftAskReqVo)
  private static final pb4server.ReceiveAllianceGiftAskReqVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.ReceiveAllianceGiftAskReqVo();
  }

  public static pb4server.ReceiveAllianceGiftAskReqVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ReceiveAllianceGiftAskReqVo>
      PARSER = new com.google.protobuf.AbstractParser<ReceiveAllianceGiftAskReqVo>() {
    public ReceiveAllianceGiftAskReqVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ReceiveAllianceGiftAskReqVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ReceiveAllianceGiftAskReqVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ReceiveAllianceGiftAskReqVo> getParserForType() {
    return PARSER;
  }

  public pb4server.ReceiveAllianceGiftAskReqVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
