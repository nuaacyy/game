// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.World2WorldManagerAskResp}
 */
public  final class World2WorldManagerAskResp extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.World2WorldManagerAskResp)
    World2WorldManagerAskRespOrBuilder {
  // Use World2WorldManagerAskResp.newBuilder() to construct.
  private World2WorldManagerAskResp(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private World2WorldManagerAskResp() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private World2WorldManagerAskResp(
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
          case 90: {
            pb4server.AllianceNameChangeAskRt.Builder subBuilder = null;
            if (msgCase_ == 11) {
              subBuilder = ((pb4server.AllianceNameChangeAskRt) msg_).toBuilder();
            }
            msg_ =
                input.readMessage(pb4server.AllianceNameChangeAskRt.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((pb4server.AllianceNameChangeAskRt) msg_);
              msg_ = subBuilder.buildPartial();
            }
            msgCase_ = 11;
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
    return pb4server.InternalWkt.internal_static_pb4server_World2WorldManagerAskResp_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_World2WorldManagerAskResp_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.World2WorldManagerAskResp.class, pb4server.World2WorldManagerAskResp.Builder.class);
  }

  private int msgCase_ = 0;
  private java.lang.Object msg_;
  public enum MsgCase
      implements com.google.protobuf.Internal.EnumLite {
    ALLIANCENAMECHANGEASKRT(11),
    MSG_NOT_SET(0);
    private final int value;
    private MsgCase(int value) {
      this.value = value;
    }
    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static MsgCase valueOf(int value) {
      return forNumber(value);
    }

    public static MsgCase forNumber(int value) {
      switch (value) {
        case 11: return ALLIANCENAMECHANGEASKRT;
        case 0: return MSG_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  public MsgCase
  getMsgCase() {
    return MsgCase.forNumber(
        msgCase_);
  }

  public static final int ALLIANCENAMECHANGEASKRT_FIELD_NUMBER = 11;
  /**
   * <code>.pb4server.AllianceNameChangeAskRt allianceNameChangeAskRt = 11;</code>
   */
  public pb4server.AllianceNameChangeAskRt getAllianceNameChangeAskRt() {
    if (msgCase_ == 11) {
       return (pb4server.AllianceNameChangeAskRt) msg_;
    }
    return pb4server.AllianceNameChangeAskRt.getDefaultInstance();
  }
  /**
   * <code>.pb4server.AllianceNameChangeAskRt allianceNameChangeAskRt = 11;</code>
   */
  public pb4server.AllianceNameChangeAskRtOrBuilder getAllianceNameChangeAskRtOrBuilder() {
    if (msgCase_ == 11) {
       return (pb4server.AllianceNameChangeAskRt) msg_;
    }
    return pb4server.AllianceNameChangeAskRt.getDefaultInstance();
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
    if (msgCase_ == 11) {
      output.writeMessage(11, (pb4server.AllianceNameChangeAskRt) msg_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (msgCase_ == 11) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(11, (pb4server.AllianceNameChangeAskRt) msg_);
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
    if (!(obj instanceof pb4server.World2WorldManagerAskResp)) {
      return super.equals(obj);
    }
    pb4server.World2WorldManagerAskResp other = (pb4server.World2WorldManagerAskResp) obj;

    boolean result = true;
    result = result && getMsgCase().equals(
        other.getMsgCase());
    if (!result) return false;
    switch (msgCase_) {
      case 11:
        result = result && getAllianceNameChangeAskRt()
            .equals(other.getAllianceNameChangeAskRt());
        break;
      case 0:
      default:
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
    switch (msgCase_) {
      case 11:
        hash = (37 * hash) + ALLIANCENAMECHANGEASKRT_FIELD_NUMBER;
        hash = (53 * hash) + getAllianceNameChangeAskRt().hashCode();
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.World2WorldManagerAskResp parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.World2WorldManagerAskResp parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.World2WorldManagerAskResp parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.World2WorldManagerAskResp parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.World2WorldManagerAskResp parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.World2WorldManagerAskResp parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.World2WorldManagerAskResp parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.World2WorldManagerAskResp parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.World2WorldManagerAskResp parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.World2WorldManagerAskResp parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.World2WorldManagerAskResp parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.World2WorldManagerAskResp parseFrom(
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
  public static Builder newBuilder(pb4server.World2WorldManagerAskResp prototype) {
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
   * Protobuf type {@code pb4server.World2WorldManagerAskResp}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.World2WorldManagerAskResp)
      pb4server.World2WorldManagerAskRespOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_World2WorldManagerAskResp_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_World2WorldManagerAskResp_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.World2WorldManagerAskResp.class, pb4server.World2WorldManagerAskResp.Builder.class);
    }

    // Construct using pb4server.World2WorldManagerAskResp.newBuilder()
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
      msgCase_ = 0;
      msg_ = null;
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_World2WorldManagerAskResp_descriptor;
    }

    public pb4server.World2WorldManagerAskResp getDefaultInstanceForType() {
      return pb4server.World2WorldManagerAskResp.getDefaultInstance();
    }

    public pb4server.World2WorldManagerAskResp build() {
      pb4server.World2WorldManagerAskResp result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.World2WorldManagerAskResp buildPartial() {
      pb4server.World2WorldManagerAskResp result = new pb4server.World2WorldManagerAskResp(this);
      if (msgCase_ == 11) {
        if (allianceNameChangeAskRtBuilder_ == null) {
          result.msg_ = msg_;
        } else {
          result.msg_ = allianceNameChangeAskRtBuilder_.build();
        }
      }
      result.msgCase_ = msgCase_;
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
      if (other instanceof pb4server.World2WorldManagerAskResp) {
        return mergeFrom((pb4server.World2WorldManagerAskResp)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.World2WorldManagerAskResp other) {
      if (other == pb4server.World2WorldManagerAskResp.getDefaultInstance()) return this;
      switch (other.getMsgCase()) {
        case ALLIANCENAMECHANGEASKRT: {
          mergeAllianceNameChangeAskRt(other.getAllianceNameChangeAskRt());
          break;
        }
        case MSG_NOT_SET: {
          break;
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
      pb4server.World2WorldManagerAskResp parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.World2WorldManagerAskResp) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int msgCase_ = 0;
    private java.lang.Object msg_;
    public MsgCase
        getMsgCase() {
      return MsgCase.forNumber(
          msgCase_);
    }

    public Builder clearMsg() {
      msgCase_ = 0;
      msg_ = null;
      onChanged();
      return this;
    }


    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.AllianceNameChangeAskRt, pb4server.AllianceNameChangeAskRt.Builder, pb4server.AllianceNameChangeAskRtOrBuilder> allianceNameChangeAskRtBuilder_;
    /**
     * <code>.pb4server.AllianceNameChangeAskRt allianceNameChangeAskRt = 11;</code>
     */
    public pb4server.AllianceNameChangeAskRt getAllianceNameChangeAskRt() {
      if (allianceNameChangeAskRtBuilder_ == null) {
        if (msgCase_ == 11) {
          return (pb4server.AllianceNameChangeAskRt) msg_;
        }
        return pb4server.AllianceNameChangeAskRt.getDefaultInstance();
      } else {
        if (msgCase_ == 11) {
          return allianceNameChangeAskRtBuilder_.getMessage();
        }
        return pb4server.AllianceNameChangeAskRt.getDefaultInstance();
      }
    }
    /**
     * <code>.pb4server.AllianceNameChangeAskRt allianceNameChangeAskRt = 11;</code>
     */
    public Builder setAllianceNameChangeAskRt(pb4server.AllianceNameChangeAskRt value) {
      if (allianceNameChangeAskRtBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        msg_ = value;
        onChanged();
      } else {
        allianceNameChangeAskRtBuilder_.setMessage(value);
      }
      msgCase_ = 11;
      return this;
    }
    /**
     * <code>.pb4server.AllianceNameChangeAskRt allianceNameChangeAskRt = 11;</code>
     */
    public Builder setAllianceNameChangeAskRt(
        pb4server.AllianceNameChangeAskRt.Builder builderForValue) {
      if (allianceNameChangeAskRtBuilder_ == null) {
        msg_ = builderForValue.build();
        onChanged();
      } else {
        allianceNameChangeAskRtBuilder_.setMessage(builderForValue.build());
      }
      msgCase_ = 11;
      return this;
    }
    /**
     * <code>.pb4server.AllianceNameChangeAskRt allianceNameChangeAskRt = 11;</code>
     */
    public Builder mergeAllianceNameChangeAskRt(pb4server.AllianceNameChangeAskRt value) {
      if (allianceNameChangeAskRtBuilder_ == null) {
        if (msgCase_ == 11 &&
            msg_ != pb4server.AllianceNameChangeAskRt.getDefaultInstance()) {
          msg_ = pb4server.AllianceNameChangeAskRt.newBuilder((pb4server.AllianceNameChangeAskRt) msg_)
              .mergeFrom(value).buildPartial();
        } else {
          msg_ = value;
        }
        onChanged();
      } else {
        if (msgCase_ == 11) {
          allianceNameChangeAskRtBuilder_.mergeFrom(value);
        }
        allianceNameChangeAskRtBuilder_.setMessage(value);
      }
      msgCase_ = 11;
      return this;
    }
    /**
     * <code>.pb4server.AllianceNameChangeAskRt allianceNameChangeAskRt = 11;</code>
     */
    public Builder clearAllianceNameChangeAskRt() {
      if (allianceNameChangeAskRtBuilder_ == null) {
        if (msgCase_ == 11) {
          msgCase_ = 0;
          msg_ = null;
          onChanged();
        }
      } else {
        if (msgCase_ == 11) {
          msgCase_ = 0;
          msg_ = null;
        }
        allianceNameChangeAskRtBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.pb4server.AllianceNameChangeAskRt allianceNameChangeAskRt = 11;</code>
     */
    public pb4server.AllianceNameChangeAskRt.Builder getAllianceNameChangeAskRtBuilder() {
      return getAllianceNameChangeAskRtFieldBuilder().getBuilder();
    }
    /**
     * <code>.pb4server.AllianceNameChangeAskRt allianceNameChangeAskRt = 11;</code>
     */
    public pb4server.AllianceNameChangeAskRtOrBuilder getAllianceNameChangeAskRtOrBuilder() {
      if ((msgCase_ == 11) && (allianceNameChangeAskRtBuilder_ != null)) {
        return allianceNameChangeAskRtBuilder_.getMessageOrBuilder();
      } else {
        if (msgCase_ == 11) {
          return (pb4server.AllianceNameChangeAskRt) msg_;
        }
        return pb4server.AllianceNameChangeAskRt.getDefaultInstance();
      }
    }
    /**
     * <code>.pb4server.AllianceNameChangeAskRt allianceNameChangeAskRt = 11;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.AllianceNameChangeAskRt, pb4server.AllianceNameChangeAskRt.Builder, pb4server.AllianceNameChangeAskRtOrBuilder> 
        getAllianceNameChangeAskRtFieldBuilder() {
      if (allianceNameChangeAskRtBuilder_ == null) {
        if (!(msgCase_ == 11)) {
          msg_ = pb4server.AllianceNameChangeAskRt.getDefaultInstance();
        }
        allianceNameChangeAskRtBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4server.AllianceNameChangeAskRt, pb4server.AllianceNameChangeAskRt.Builder, pb4server.AllianceNameChangeAskRtOrBuilder>(
                (pb4server.AllianceNameChangeAskRt) msg_,
                getParentForChildren(),
                isClean());
        msg_ = null;
      }
      msgCase_ = 11;
      onChanged();;
      return allianceNameChangeAskRtBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.World2WorldManagerAskResp)
  }

  // @@protoc_insertion_point(class_scope:pb4server.World2WorldManagerAskResp)
  private static final pb4server.World2WorldManagerAskResp DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.World2WorldManagerAskResp();
  }

  public static pb4server.World2WorldManagerAskResp getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<World2WorldManagerAskResp>
      PARSER = new com.google.protobuf.AbstractParser<World2WorldManagerAskResp>() {
    public World2WorldManagerAskResp parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new World2WorldManagerAskResp(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<World2WorldManagerAskResp> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<World2WorldManagerAskResp> getParserForType() {
    return PARSER;
  }

  public pb4server.World2WorldManagerAskResp getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
