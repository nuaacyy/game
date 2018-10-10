// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * <pre>
 * 设置联盟公告
 * </pre>
 *
 * Protobuf type {@code pb4server.SetAllianceDescptAskReq}
 */
public  final class SetAllianceDescptAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.SetAllianceDescptAskReq)
    SetAllianceDescptAskReqOrBuilder {
  // Use SetAllianceDescptAskReq.newBuilder() to construct.
  private SetAllianceDescptAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SetAllianceDescptAskReq() {
    desp_ = "";
    despType_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private SetAllianceDescptAskReq(
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            desp_ = s;
            break;
          }
          case 16: {

            despType_ = input.readInt32();
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
    return pb4server.InternalPkt.internal_static_pb4server_SetAllianceDescptAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_SetAllianceDescptAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.SetAllianceDescptAskReq.class, pb4server.SetAllianceDescptAskReq.Builder.class);
  }

  public static final int DESP_FIELD_NUMBER = 1;
  private volatile java.lang.Object desp_;
  /**
   * <pre>
   * 公告内容
   * </pre>
   *
   * <code>string desp = 1;</code>
   */
  public java.lang.String getDesp() {
    java.lang.Object ref = desp_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      desp_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 公告内容
   * </pre>
   *
   * <code>string desp = 1;</code>
   */
  public com.google.protobuf.ByteString
      getDespBytes() {
    java.lang.Object ref = desp_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      desp_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DESPTYPE_FIELD_NUMBER = 2;
  private int despType_;
  /**
   * <pre>
   * 公告类型  1-公告 2-标语
   * </pre>
   *
   * <code>int32 despType = 2;</code>
   */
  public int getDespType() {
    return despType_;
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
    if (!getDespBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, desp_);
    }
    if (despType_ != 0) {
      output.writeInt32(2, despType_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getDespBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, desp_);
    }
    if (despType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, despType_);
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
    if (!(obj instanceof pb4server.SetAllianceDescptAskReq)) {
      return super.equals(obj);
    }
    pb4server.SetAllianceDescptAskReq other = (pb4server.SetAllianceDescptAskReq) obj;

    boolean result = true;
    result = result && getDesp()
        .equals(other.getDesp());
    result = result && (getDespType()
        == other.getDespType());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + DESP_FIELD_NUMBER;
    hash = (53 * hash) + getDesp().hashCode();
    hash = (37 * hash) + DESPTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getDespType();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.SetAllianceDescptAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.SetAllianceDescptAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.SetAllianceDescptAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.SetAllianceDescptAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.SetAllianceDescptAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.SetAllianceDescptAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.SetAllianceDescptAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.SetAllianceDescptAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.SetAllianceDescptAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.SetAllianceDescptAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.SetAllianceDescptAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.SetAllianceDescptAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.SetAllianceDescptAskReq prototype) {
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
   * 设置联盟公告
   * </pre>
   *
   * Protobuf type {@code pb4server.SetAllianceDescptAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.SetAllianceDescptAskReq)
      pb4server.SetAllianceDescptAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_SetAllianceDescptAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_SetAllianceDescptAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.SetAllianceDescptAskReq.class, pb4server.SetAllianceDescptAskReq.Builder.class);
    }

    // Construct using pb4server.SetAllianceDescptAskReq.newBuilder()
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
      desp_ = "";

      despType_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_SetAllianceDescptAskReq_descriptor;
    }

    public pb4server.SetAllianceDescptAskReq getDefaultInstanceForType() {
      return pb4server.SetAllianceDescptAskReq.getDefaultInstance();
    }

    public pb4server.SetAllianceDescptAskReq build() {
      pb4server.SetAllianceDescptAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.SetAllianceDescptAskReq buildPartial() {
      pb4server.SetAllianceDescptAskReq result = new pb4server.SetAllianceDescptAskReq(this);
      result.desp_ = desp_;
      result.despType_ = despType_;
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
      if (other instanceof pb4server.SetAllianceDescptAskReq) {
        return mergeFrom((pb4server.SetAllianceDescptAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.SetAllianceDescptAskReq other) {
      if (other == pb4server.SetAllianceDescptAskReq.getDefaultInstance()) return this;
      if (!other.getDesp().isEmpty()) {
        desp_ = other.desp_;
        onChanged();
      }
      if (other.getDespType() != 0) {
        setDespType(other.getDespType());
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
      pb4server.SetAllianceDescptAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.SetAllianceDescptAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object desp_ = "";
    /**
     * <pre>
     * 公告内容
     * </pre>
     *
     * <code>string desp = 1;</code>
     */
    public java.lang.String getDesp() {
      java.lang.Object ref = desp_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        desp_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 公告内容
     * </pre>
     *
     * <code>string desp = 1;</code>
     */
    public com.google.protobuf.ByteString
        getDespBytes() {
      java.lang.Object ref = desp_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        desp_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 公告内容
     * </pre>
     *
     * <code>string desp = 1;</code>
     */
    public Builder setDesp(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      desp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 公告内容
     * </pre>
     *
     * <code>string desp = 1;</code>
     */
    public Builder clearDesp() {
      
      desp_ = getDefaultInstance().getDesp();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 公告内容
     * </pre>
     *
     * <code>string desp = 1;</code>
     */
    public Builder setDespBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      desp_ = value;
      onChanged();
      return this;
    }

    private int despType_ ;
    /**
     * <pre>
     * 公告类型  1-公告 2-标语
     * </pre>
     *
     * <code>int32 despType = 2;</code>
     */
    public int getDespType() {
      return despType_;
    }
    /**
     * <pre>
     * 公告类型  1-公告 2-标语
     * </pre>
     *
     * <code>int32 despType = 2;</code>
     */
    public Builder setDespType(int value) {
      
      despType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 公告类型  1-公告 2-标语
     * </pre>
     *
     * <code>int32 despType = 2;</code>
     */
    public Builder clearDespType() {
      
      despType_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.SetAllianceDescptAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.SetAllianceDescptAskReq)
  private static final pb4server.SetAllianceDescptAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.SetAllianceDescptAskReq();
  }

  public static pb4server.SetAllianceDescptAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SetAllianceDescptAskReq>
      PARSER = new com.google.protobuf.AbstractParser<SetAllianceDescptAskReq>() {
    public SetAllianceDescptAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SetAllianceDescptAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SetAllianceDescptAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SetAllianceDescptAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.SetAllianceDescptAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

