// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.OpenAllianceGiftAskRt}
 */
public  final class OpenAllianceGiftAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.OpenAllianceGiftAskRt)
    OpenAllianceGiftAskRtOrBuilder {
  // Use OpenAllianceGiftAskRt.newBuilder() to construct.
  private OpenAllianceGiftAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OpenAllianceGiftAskRt() {
    rt_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private OpenAllianceGiftAskRt(
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

            rt_ = input.readInt32();
            break;
          }
          case 18: {
            pb4server.AllianceBigGiftVo.Builder subBuilder = null;
            if (allianceBigGiftVo_ != null) {
              subBuilder = allianceBigGiftVo_.toBuilder();
            }
            allianceBigGiftVo_ = input.readMessage(pb4server.AllianceBigGiftVo.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(allianceBigGiftVo_);
              allianceBigGiftVo_ = subBuilder.buildPartial();
            }

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
    return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceGiftAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceGiftAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.OpenAllianceGiftAskRt.class, pb4server.OpenAllianceGiftAskRt.Builder.class);
  }

  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int ALLIANCEBIGGIFTVO_FIELD_NUMBER = 2;
  private pb4server.AllianceBigGiftVo allianceBigGiftVo_;
  /**
   * <pre>
   * 我的研发信息
   * </pre>
   *
   * <code>.pb4server.AllianceBigGiftVo allianceBigGiftVo = 2;</code>
   */
  public boolean hasAllianceBigGiftVo() {
    return allianceBigGiftVo_ != null;
  }
  /**
   * <pre>
   * 我的研发信息
   * </pre>
   *
   * <code>.pb4server.AllianceBigGiftVo allianceBigGiftVo = 2;</code>
   */
  public pb4server.AllianceBigGiftVo getAllianceBigGiftVo() {
    return allianceBigGiftVo_ == null ? pb4server.AllianceBigGiftVo.getDefaultInstance() : allianceBigGiftVo_;
  }
  /**
   * <pre>
   * 我的研发信息
   * </pre>
   *
   * <code>.pb4server.AllianceBigGiftVo allianceBigGiftVo = 2;</code>
   */
  public pb4server.AllianceBigGiftVoOrBuilder getAllianceBigGiftVoOrBuilder() {
    return getAllianceBigGiftVo();
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
    if (rt_ != 0) {
      output.writeInt32(1, rt_);
    }
    if (allianceBigGiftVo_ != null) {
      output.writeMessage(2, getAllianceBigGiftVo());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (rt_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rt_);
    }
    if (allianceBigGiftVo_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getAllianceBigGiftVo());
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
    if (!(obj instanceof pb4server.OpenAllianceGiftAskRt)) {
      return super.equals(obj);
    }
    pb4server.OpenAllianceGiftAskRt other = (pb4server.OpenAllianceGiftAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && (hasAllianceBigGiftVo() == other.hasAllianceBigGiftVo());
    if (hasAllianceBigGiftVo()) {
      result = result && getAllianceBigGiftVo()
          .equals(other.getAllianceBigGiftVo());
    }
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RT_FIELD_NUMBER;
    hash = (53 * hash) + getRt();
    if (hasAllianceBigGiftVo()) {
      hash = (37 * hash) + ALLIANCEBIGGIFTVO_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceBigGiftVo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.OpenAllianceGiftAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenAllianceGiftAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenAllianceGiftAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenAllianceGiftAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenAllianceGiftAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenAllianceGiftAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenAllianceGiftAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.OpenAllianceGiftAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.OpenAllianceGiftAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.OpenAllianceGiftAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.OpenAllianceGiftAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.OpenAllianceGiftAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.OpenAllianceGiftAskRt prototype) {
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
   * Protobuf type {@code pb4server.OpenAllianceGiftAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.OpenAllianceGiftAskRt)
      pb4server.OpenAllianceGiftAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceGiftAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceGiftAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.OpenAllianceGiftAskRt.class, pb4server.OpenAllianceGiftAskRt.Builder.class);
    }

    // Construct using pb4server.OpenAllianceGiftAskRt.newBuilder()
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
      rt_ = 0;

      if (allianceBigGiftVoBuilder_ == null) {
        allianceBigGiftVo_ = null;
      } else {
        allianceBigGiftVo_ = null;
        allianceBigGiftVoBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceGiftAskRt_descriptor;
    }

    public pb4server.OpenAllianceGiftAskRt getDefaultInstanceForType() {
      return pb4server.OpenAllianceGiftAskRt.getDefaultInstance();
    }

    public pb4server.OpenAllianceGiftAskRt build() {
      pb4server.OpenAllianceGiftAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.OpenAllianceGiftAskRt buildPartial() {
      pb4server.OpenAllianceGiftAskRt result = new pb4server.OpenAllianceGiftAskRt(this);
      result.rt_ = rt_;
      if (allianceBigGiftVoBuilder_ == null) {
        result.allianceBigGiftVo_ = allianceBigGiftVo_;
      } else {
        result.allianceBigGiftVo_ = allianceBigGiftVoBuilder_.build();
      }
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
      if (other instanceof pb4server.OpenAllianceGiftAskRt) {
        return mergeFrom((pb4server.OpenAllianceGiftAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.OpenAllianceGiftAskRt other) {
      if (other == pb4server.OpenAllianceGiftAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (other.hasAllianceBigGiftVo()) {
        mergeAllianceBigGiftVo(other.getAllianceBigGiftVo());
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
      pb4server.OpenAllianceGiftAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.OpenAllianceGiftAskRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int rt_ ;
    /**
     * <code>int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <code>int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 rt = 1;</code>
     */
    public Builder clearRt() {
      
      rt_ = 0;
      onChanged();
      return this;
    }

    private pb4server.AllianceBigGiftVo allianceBigGiftVo_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.AllianceBigGiftVo, pb4server.AllianceBigGiftVo.Builder, pb4server.AllianceBigGiftVoOrBuilder> allianceBigGiftVoBuilder_;
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>.pb4server.AllianceBigGiftVo allianceBigGiftVo = 2;</code>
     */
    public boolean hasAllianceBigGiftVo() {
      return allianceBigGiftVoBuilder_ != null || allianceBigGiftVo_ != null;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>.pb4server.AllianceBigGiftVo allianceBigGiftVo = 2;</code>
     */
    public pb4server.AllianceBigGiftVo getAllianceBigGiftVo() {
      if (allianceBigGiftVoBuilder_ == null) {
        return allianceBigGiftVo_ == null ? pb4server.AllianceBigGiftVo.getDefaultInstance() : allianceBigGiftVo_;
      } else {
        return allianceBigGiftVoBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>.pb4server.AllianceBigGiftVo allianceBigGiftVo = 2;</code>
     */
    public Builder setAllianceBigGiftVo(pb4server.AllianceBigGiftVo value) {
      if (allianceBigGiftVoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        allianceBigGiftVo_ = value;
        onChanged();
      } else {
        allianceBigGiftVoBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>.pb4server.AllianceBigGiftVo allianceBigGiftVo = 2;</code>
     */
    public Builder setAllianceBigGiftVo(
        pb4server.AllianceBigGiftVo.Builder builderForValue) {
      if (allianceBigGiftVoBuilder_ == null) {
        allianceBigGiftVo_ = builderForValue.build();
        onChanged();
      } else {
        allianceBigGiftVoBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>.pb4server.AllianceBigGiftVo allianceBigGiftVo = 2;</code>
     */
    public Builder mergeAllianceBigGiftVo(pb4server.AllianceBigGiftVo value) {
      if (allianceBigGiftVoBuilder_ == null) {
        if (allianceBigGiftVo_ != null) {
          allianceBigGiftVo_ =
            pb4server.AllianceBigGiftVo.newBuilder(allianceBigGiftVo_).mergeFrom(value).buildPartial();
        } else {
          allianceBigGiftVo_ = value;
        }
        onChanged();
      } else {
        allianceBigGiftVoBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>.pb4server.AllianceBigGiftVo allianceBigGiftVo = 2;</code>
     */
    public Builder clearAllianceBigGiftVo() {
      if (allianceBigGiftVoBuilder_ == null) {
        allianceBigGiftVo_ = null;
        onChanged();
      } else {
        allianceBigGiftVo_ = null;
        allianceBigGiftVoBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>.pb4server.AllianceBigGiftVo allianceBigGiftVo = 2;</code>
     */
    public pb4server.AllianceBigGiftVo.Builder getAllianceBigGiftVoBuilder() {
      
      onChanged();
      return getAllianceBigGiftVoFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>.pb4server.AllianceBigGiftVo allianceBigGiftVo = 2;</code>
     */
    public pb4server.AllianceBigGiftVoOrBuilder getAllianceBigGiftVoOrBuilder() {
      if (allianceBigGiftVoBuilder_ != null) {
        return allianceBigGiftVoBuilder_.getMessageOrBuilder();
      } else {
        return allianceBigGiftVo_ == null ?
            pb4server.AllianceBigGiftVo.getDefaultInstance() : allianceBigGiftVo_;
      }
    }
    /**
     * <pre>
     * 我的研发信息
     * </pre>
     *
     * <code>.pb4server.AllianceBigGiftVo allianceBigGiftVo = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.AllianceBigGiftVo, pb4server.AllianceBigGiftVo.Builder, pb4server.AllianceBigGiftVoOrBuilder> 
        getAllianceBigGiftVoFieldBuilder() {
      if (allianceBigGiftVoBuilder_ == null) {
        allianceBigGiftVoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4server.AllianceBigGiftVo, pb4server.AllianceBigGiftVo.Builder, pb4server.AllianceBigGiftVoOrBuilder>(
                getAllianceBigGiftVo(),
                getParentForChildren(),
                isClean());
        allianceBigGiftVo_ = null;
      }
      return allianceBigGiftVoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.OpenAllianceGiftAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.OpenAllianceGiftAskRt)
  private static final pb4server.OpenAllianceGiftAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.OpenAllianceGiftAskRt();
  }

  public static pb4server.OpenAllianceGiftAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OpenAllianceGiftAskRt>
      PARSER = new com.google.protobuf.AbstractParser<OpenAllianceGiftAskRt>() {
    public OpenAllianceGiftAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OpenAllianceGiftAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OpenAllianceGiftAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OpenAllianceGiftAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.OpenAllianceGiftAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

