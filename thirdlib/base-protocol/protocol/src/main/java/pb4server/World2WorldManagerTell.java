// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 *===================================================================================================
 * </pre>
 *
 * Protobuf type {@code pb4server.World2WorldManagerTell}
 */
public  final class World2WorldManagerTell extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.World2WorldManagerTell)
    World2WorldManagerTellOrBuilder {
  // Use World2WorldManagerTell.newBuilder() to construct.
  private World2WorldManagerTell(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private World2WorldManagerTell() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private World2WorldManagerTell(
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
            pb4server.AddNewAreaTell.Builder subBuilder = null;
            if (msgCase_ == 11) {
              subBuilder = ((pb4server.AddNewAreaTell) msg_).toBuilder();
            }
            msg_ =
                input.readMessage(pb4server.AddNewAreaTell.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((pb4server.AddNewAreaTell) msg_);
              msg_ = subBuilder.buildPartial();
            }
            msgCase_ = 11;
            break;
          }
          case 98: {
            pb4server.WorldSyncInfo2WorldManagerTell.Builder subBuilder = null;
            if (msgCase_ == 12) {
              subBuilder = ((pb4server.WorldSyncInfo2WorldManagerTell) msg_).toBuilder();
            }
            msg_ =
                input.readMessage(pb4server.WorldSyncInfo2WorldManagerTell.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((pb4server.WorldSyncInfo2WorldManagerTell) msg_);
              msg_ = subBuilder.buildPartial();
            }
            msgCase_ = 12;
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
    return pb4server.InternalWkt.internal_static_pb4server_World2WorldManagerTell_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_World2WorldManagerTell_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.World2WorldManagerTell.class, pb4server.World2WorldManagerTell.Builder.class);
  }

  private int msgCase_ = 0;
  private java.lang.Object msg_;
  public enum MsgCase
      implements com.google.protobuf.Internal.EnumLite {
    ADDNEWAREATELL(11),
    WORLDSYNCINFO2WORLDMANAGERTELL(12),
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
        case 11: return ADDNEWAREATELL;
        case 12: return WORLDSYNCINFO2WORLDMANAGERTELL;
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

  public static final int ADDNEWAREATELL_FIELD_NUMBER = 11;
  /**
   * <code>.pb4server.AddNewAreaTell addNewAreaTell = 11;</code>
   */
  public pb4server.AddNewAreaTell getAddNewAreaTell() {
    if (msgCase_ == 11) {
       return (pb4server.AddNewAreaTell) msg_;
    }
    return pb4server.AddNewAreaTell.getDefaultInstance();
  }
  /**
   * <code>.pb4server.AddNewAreaTell addNewAreaTell = 11;</code>
   */
  public pb4server.AddNewAreaTellOrBuilder getAddNewAreaTellOrBuilder() {
    if (msgCase_ == 11) {
       return (pb4server.AddNewAreaTell) msg_;
    }
    return pb4server.AddNewAreaTell.getDefaultInstance();
  }

  public static final int WORLDSYNCINFO2WORLDMANAGERTELL_FIELD_NUMBER = 12;
  /**
   * <code>.pb4server.WorldSyncInfo2WorldManagerTell worldSyncInfo2WorldManagerTell = 12;</code>
   */
  public pb4server.WorldSyncInfo2WorldManagerTell getWorldSyncInfo2WorldManagerTell() {
    if (msgCase_ == 12) {
       return (pb4server.WorldSyncInfo2WorldManagerTell) msg_;
    }
    return pb4server.WorldSyncInfo2WorldManagerTell.getDefaultInstance();
  }
  /**
   * <code>.pb4server.WorldSyncInfo2WorldManagerTell worldSyncInfo2WorldManagerTell = 12;</code>
   */
  public pb4server.WorldSyncInfo2WorldManagerTellOrBuilder getWorldSyncInfo2WorldManagerTellOrBuilder() {
    if (msgCase_ == 12) {
       return (pb4server.WorldSyncInfo2WorldManagerTell) msg_;
    }
    return pb4server.WorldSyncInfo2WorldManagerTell.getDefaultInstance();
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
      output.writeMessage(11, (pb4server.AddNewAreaTell) msg_);
    }
    if (msgCase_ == 12) {
      output.writeMessage(12, (pb4server.WorldSyncInfo2WorldManagerTell) msg_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (msgCase_ == 11) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(11, (pb4server.AddNewAreaTell) msg_);
    }
    if (msgCase_ == 12) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(12, (pb4server.WorldSyncInfo2WorldManagerTell) msg_);
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
    if (!(obj instanceof pb4server.World2WorldManagerTell)) {
      return super.equals(obj);
    }
    pb4server.World2WorldManagerTell other = (pb4server.World2WorldManagerTell) obj;

    boolean result = true;
    result = result && getMsgCase().equals(
        other.getMsgCase());
    if (!result) return false;
    switch (msgCase_) {
      case 11:
        result = result && getAddNewAreaTell()
            .equals(other.getAddNewAreaTell());
        break;
      case 12:
        result = result && getWorldSyncInfo2WorldManagerTell()
            .equals(other.getWorldSyncInfo2WorldManagerTell());
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
        hash = (37 * hash) + ADDNEWAREATELL_FIELD_NUMBER;
        hash = (53 * hash) + getAddNewAreaTell().hashCode();
        break;
      case 12:
        hash = (37 * hash) + WORLDSYNCINFO2WORLDMANAGERTELL_FIELD_NUMBER;
        hash = (53 * hash) + getWorldSyncInfo2WorldManagerTell().hashCode();
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.World2WorldManagerTell parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.World2WorldManagerTell parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.World2WorldManagerTell parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.World2WorldManagerTell parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.World2WorldManagerTell parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.World2WorldManagerTell parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.World2WorldManagerTell parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.World2WorldManagerTell parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.World2WorldManagerTell parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.World2WorldManagerTell parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.World2WorldManagerTell parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.World2WorldManagerTell parseFrom(
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
  public static Builder newBuilder(pb4server.World2WorldManagerTell prototype) {
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
   *===================================================================================================
   * </pre>
   *
   * Protobuf type {@code pb4server.World2WorldManagerTell}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.World2WorldManagerTell)
      pb4server.World2WorldManagerTellOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_World2WorldManagerTell_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_World2WorldManagerTell_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.World2WorldManagerTell.class, pb4server.World2WorldManagerTell.Builder.class);
    }

    // Construct using pb4server.World2WorldManagerTell.newBuilder()
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
      return pb4server.InternalWkt.internal_static_pb4server_World2WorldManagerTell_descriptor;
    }

    public pb4server.World2WorldManagerTell getDefaultInstanceForType() {
      return pb4server.World2WorldManagerTell.getDefaultInstance();
    }

    public pb4server.World2WorldManagerTell build() {
      pb4server.World2WorldManagerTell result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.World2WorldManagerTell buildPartial() {
      pb4server.World2WorldManagerTell result = new pb4server.World2WorldManagerTell(this);
      if (msgCase_ == 11) {
        if (addNewAreaTellBuilder_ == null) {
          result.msg_ = msg_;
        } else {
          result.msg_ = addNewAreaTellBuilder_.build();
        }
      }
      if (msgCase_ == 12) {
        if (worldSyncInfo2WorldManagerTellBuilder_ == null) {
          result.msg_ = msg_;
        } else {
          result.msg_ = worldSyncInfo2WorldManagerTellBuilder_.build();
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
      if (other instanceof pb4server.World2WorldManagerTell) {
        return mergeFrom((pb4server.World2WorldManagerTell)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.World2WorldManagerTell other) {
      if (other == pb4server.World2WorldManagerTell.getDefaultInstance()) return this;
      switch (other.getMsgCase()) {
        case ADDNEWAREATELL: {
          mergeAddNewAreaTell(other.getAddNewAreaTell());
          break;
        }
        case WORLDSYNCINFO2WORLDMANAGERTELL: {
          mergeWorldSyncInfo2WorldManagerTell(other.getWorldSyncInfo2WorldManagerTell());
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
      pb4server.World2WorldManagerTell parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.World2WorldManagerTell) e.getUnfinishedMessage();
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
        pb4server.AddNewAreaTell, pb4server.AddNewAreaTell.Builder, pb4server.AddNewAreaTellOrBuilder> addNewAreaTellBuilder_;
    /**
     * <code>.pb4server.AddNewAreaTell addNewAreaTell = 11;</code>
     */
    public pb4server.AddNewAreaTell getAddNewAreaTell() {
      if (addNewAreaTellBuilder_ == null) {
        if (msgCase_ == 11) {
          return (pb4server.AddNewAreaTell) msg_;
        }
        return pb4server.AddNewAreaTell.getDefaultInstance();
      } else {
        if (msgCase_ == 11) {
          return addNewAreaTellBuilder_.getMessage();
        }
        return pb4server.AddNewAreaTell.getDefaultInstance();
      }
    }
    /**
     * <code>.pb4server.AddNewAreaTell addNewAreaTell = 11;</code>
     */
    public Builder setAddNewAreaTell(pb4server.AddNewAreaTell value) {
      if (addNewAreaTellBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        msg_ = value;
        onChanged();
      } else {
        addNewAreaTellBuilder_.setMessage(value);
      }
      msgCase_ = 11;
      return this;
    }
    /**
     * <code>.pb4server.AddNewAreaTell addNewAreaTell = 11;</code>
     */
    public Builder setAddNewAreaTell(
        pb4server.AddNewAreaTell.Builder builderForValue) {
      if (addNewAreaTellBuilder_ == null) {
        msg_ = builderForValue.build();
        onChanged();
      } else {
        addNewAreaTellBuilder_.setMessage(builderForValue.build());
      }
      msgCase_ = 11;
      return this;
    }
    /**
     * <code>.pb4server.AddNewAreaTell addNewAreaTell = 11;</code>
     */
    public Builder mergeAddNewAreaTell(pb4server.AddNewAreaTell value) {
      if (addNewAreaTellBuilder_ == null) {
        if (msgCase_ == 11 &&
            msg_ != pb4server.AddNewAreaTell.getDefaultInstance()) {
          msg_ = pb4server.AddNewAreaTell.newBuilder((pb4server.AddNewAreaTell) msg_)
              .mergeFrom(value).buildPartial();
        } else {
          msg_ = value;
        }
        onChanged();
      } else {
        if (msgCase_ == 11) {
          addNewAreaTellBuilder_.mergeFrom(value);
        }
        addNewAreaTellBuilder_.setMessage(value);
      }
      msgCase_ = 11;
      return this;
    }
    /**
     * <code>.pb4server.AddNewAreaTell addNewAreaTell = 11;</code>
     */
    public Builder clearAddNewAreaTell() {
      if (addNewAreaTellBuilder_ == null) {
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
        addNewAreaTellBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.pb4server.AddNewAreaTell addNewAreaTell = 11;</code>
     */
    public pb4server.AddNewAreaTell.Builder getAddNewAreaTellBuilder() {
      return getAddNewAreaTellFieldBuilder().getBuilder();
    }
    /**
     * <code>.pb4server.AddNewAreaTell addNewAreaTell = 11;</code>
     */
    public pb4server.AddNewAreaTellOrBuilder getAddNewAreaTellOrBuilder() {
      if ((msgCase_ == 11) && (addNewAreaTellBuilder_ != null)) {
        return addNewAreaTellBuilder_.getMessageOrBuilder();
      } else {
        if (msgCase_ == 11) {
          return (pb4server.AddNewAreaTell) msg_;
        }
        return pb4server.AddNewAreaTell.getDefaultInstance();
      }
    }
    /**
     * <code>.pb4server.AddNewAreaTell addNewAreaTell = 11;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.AddNewAreaTell, pb4server.AddNewAreaTell.Builder, pb4server.AddNewAreaTellOrBuilder> 
        getAddNewAreaTellFieldBuilder() {
      if (addNewAreaTellBuilder_ == null) {
        if (!(msgCase_ == 11)) {
          msg_ = pb4server.AddNewAreaTell.getDefaultInstance();
        }
        addNewAreaTellBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4server.AddNewAreaTell, pb4server.AddNewAreaTell.Builder, pb4server.AddNewAreaTellOrBuilder>(
                (pb4server.AddNewAreaTell) msg_,
                getParentForChildren(),
                isClean());
        msg_ = null;
      }
      msgCase_ = 11;
      onChanged();;
      return addNewAreaTellBuilder_;
    }

    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.WorldSyncInfo2WorldManagerTell, pb4server.WorldSyncInfo2WorldManagerTell.Builder, pb4server.WorldSyncInfo2WorldManagerTellOrBuilder> worldSyncInfo2WorldManagerTellBuilder_;
    /**
     * <code>.pb4server.WorldSyncInfo2WorldManagerTell worldSyncInfo2WorldManagerTell = 12;</code>
     */
    public pb4server.WorldSyncInfo2WorldManagerTell getWorldSyncInfo2WorldManagerTell() {
      if (worldSyncInfo2WorldManagerTellBuilder_ == null) {
        if (msgCase_ == 12) {
          return (pb4server.WorldSyncInfo2WorldManagerTell) msg_;
        }
        return pb4server.WorldSyncInfo2WorldManagerTell.getDefaultInstance();
      } else {
        if (msgCase_ == 12) {
          return worldSyncInfo2WorldManagerTellBuilder_.getMessage();
        }
        return pb4server.WorldSyncInfo2WorldManagerTell.getDefaultInstance();
      }
    }
    /**
     * <code>.pb4server.WorldSyncInfo2WorldManagerTell worldSyncInfo2WorldManagerTell = 12;</code>
     */
    public Builder setWorldSyncInfo2WorldManagerTell(pb4server.WorldSyncInfo2WorldManagerTell value) {
      if (worldSyncInfo2WorldManagerTellBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        msg_ = value;
        onChanged();
      } else {
        worldSyncInfo2WorldManagerTellBuilder_.setMessage(value);
      }
      msgCase_ = 12;
      return this;
    }
    /**
     * <code>.pb4server.WorldSyncInfo2WorldManagerTell worldSyncInfo2WorldManagerTell = 12;</code>
     */
    public Builder setWorldSyncInfo2WorldManagerTell(
        pb4server.WorldSyncInfo2WorldManagerTell.Builder builderForValue) {
      if (worldSyncInfo2WorldManagerTellBuilder_ == null) {
        msg_ = builderForValue.build();
        onChanged();
      } else {
        worldSyncInfo2WorldManagerTellBuilder_.setMessage(builderForValue.build());
      }
      msgCase_ = 12;
      return this;
    }
    /**
     * <code>.pb4server.WorldSyncInfo2WorldManagerTell worldSyncInfo2WorldManagerTell = 12;</code>
     */
    public Builder mergeWorldSyncInfo2WorldManagerTell(pb4server.WorldSyncInfo2WorldManagerTell value) {
      if (worldSyncInfo2WorldManagerTellBuilder_ == null) {
        if (msgCase_ == 12 &&
            msg_ != pb4server.WorldSyncInfo2WorldManagerTell.getDefaultInstance()) {
          msg_ = pb4server.WorldSyncInfo2WorldManagerTell.newBuilder((pb4server.WorldSyncInfo2WorldManagerTell) msg_)
              .mergeFrom(value).buildPartial();
        } else {
          msg_ = value;
        }
        onChanged();
      } else {
        if (msgCase_ == 12) {
          worldSyncInfo2WorldManagerTellBuilder_.mergeFrom(value);
        }
        worldSyncInfo2WorldManagerTellBuilder_.setMessage(value);
      }
      msgCase_ = 12;
      return this;
    }
    /**
     * <code>.pb4server.WorldSyncInfo2WorldManagerTell worldSyncInfo2WorldManagerTell = 12;</code>
     */
    public Builder clearWorldSyncInfo2WorldManagerTell() {
      if (worldSyncInfo2WorldManagerTellBuilder_ == null) {
        if (msgCase_ == 12) {
          msgCase_ = 0;
          msg_ = null;
          onChanged();
        }
      } else {
        if (msgCase_ == 12) {
          msgCase_ = 0;
          msg_ = null;
        }
        worldSyncInfo2WorldManagerTellBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.pb4server.WorldSyncInfo2WorldManagerTell worldSyncInfo2WorldManagerTell = 12;</code>
     */
    public pb4server.WorldSyncInfo2WorldManagerTell.Builder getWorldSyncInfo2WorldManagerTellBuilder() {
      return getWorldSyncInfo2WorldManagerTellFieldBuilder().getBuilder();
    }
    /**
     * <code>.pb4server.WorldSyncInfo2WorldManagerTell worldSyncInfo2WorldManagerTell = 12;</code>
     */
    public pb4server.WorldSyncInfo2WorldManagerTellOrBuilder getWorldSyncInfo2WorldManagerTellOrBuilder() {
      if ((msgCase_ == 12) && (worldSyncInfo2WorldManagerTellBuilder_ != null)) {
        return worldSyncInfo2WorldManagerTellBuilder_.getMessageOrBuilder();
      } else {
        if (msgCase_ == 12) {
          return (pb4server.WorldSyncInfo2WorldManagerTell) msg_;
        }
        return pb4server.WorldSyncInfo2WorldManagerTell.getDefaultInstance();
      }
    }
    /**
     * <code>.pb4server.WorldSyncInfo2WorldManagerTell worldSyncInfo2WorldManagerTell = 12;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.WorldSyncInfo2WorldManagerTell, pb4server.WorldSyncInfo2WorldManagerTell.Builder, pb4server.WorldSyncInfo2WorldManagerTellOrBuilder> 
        getWorldSyncInfo2WorldManagerTellFieldBuilder() {
      if (worldSyncInfo2WorldManagerTellBuilder_ == null) {
        if (!(msgCase_ == 12)) {
          msg_ = pb4server.WorldSyncInfo2WorldManagerTell.getDefaultInstance();
        }
        worldSyncInfo2WorldManagerTellBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4server.WorldSyncInfo2WorldManagerTell, pb4server.WorldSyncInfo2WorldManagerTell.Builder, pb4server.WorldSyncInfo2WorldManagerTellOrBuilder>(
                (pb4server.WorldSyncInfo2WorldManagerTell) msg_,
                getParentForChildren(),
                isClean());
        msg_ = null;
      }
      msgCase_ = 12;
      onChanged();;
      return worldSyncInfo2WorldManagerTellBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.World2WorldManagerTell)
  }

  // @@protoc_insertion_point(class_scope:pb4server.World2WorldManagerTell)
  private static final pb4server.World2WorldManagerTell DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.World2WorldManagerTell();
  }

  public static pb4server.World2WorldManagerTell getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<World2WorldManagerTell>
      PARSER = new com.google.protobuf.AbstractParser<World2WorldManagerTell>() {
    public World2WorldManagerTell parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new World2WorldManagerTell(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<World2WorldManagerTell> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<World2WorldManagerTell> getParserForType() {
    return PARSER;
  }

  public pb4server.World2WorldManagerTell getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
