// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * <pre>
 * Public-&gt;Home
 * 通知玩家占领了奇观
 * </pre>
 *
 * Protobuf type {@code pb4server.OccupyWonder2HomeTell}
 */
public  final class OccupyWonder2HomeTell extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.OccupyWonder2HomeTell)
    OccupyWonder2HomeTellOrBuilder {
  // Use OccupyWonder2HomeTell.newBuilder() to construct.
  private OccupyWonder2HomeTell(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OccupyWonder2HomeTell() {
    playerId_ = 0L;
    occupyWonderInfo_ = java.util.Collections.emptyList();
    changeType_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private OccupyWonder2HomeTell(
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

            playerId_ = input.readInt64();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              occupyWonderInfo_ = new java.util.ArrayList<pb4client.OccupyWonder>();
              mutable_bitField0_ |= 0x00000002;
            }
            occupyWonderInfo_.add(
                input.readMessage(pb4client.OccupyWonder.PARSER, extensionRegistry));
            break;
          }
          case 32: {

            changeType_ = input.readInt32();
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
        occupyWonderInfo_ = java.util.Collections.unmodifiableList(occupyWonderInfo_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalHkt.internal_static_pb4server_OccupyWonder2HomeTell_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_OccupyWonder2HomeTell_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.OccupyWonder2HomeTell.class, pb4server.OccupyWonder2HomeTell.Builder.class);
  }

  private int bitField0_;
  public static final int PLAYERID_FIELD_NUMBER = 1;
  private long playerId_;
  /**
   * <code>int64 playerId = 1;</code>
   */
  public long getPlayerId() {
    return playerId_;
  }

  public static final int OCCUPYWONDERINFO_FIELD_NUMBER = 2;
  private java.util.List<pb4client.OccupyWonder> occupyWonderInfo_;
  /**
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  public java.util.List<pb4client.OccupyWonder> getOccupyWonderInfoList() {
    return occupyWonderInfo_;
  }
  /**
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  public java.util.List<? extends pb4client.OccupyWonderOrBuilder> 
      getOccupyWonderInfoOrBuilderList() {
    return occupyWonderInfo_;
  }
  /**
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  public int getOccupyWonderInfoCount() {
    return occupyWonderInfo_.size();
  }
  /**
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  public pb4client.OccupyWonder getOccupyWonderInfo(int index) {
    return occupyWonderInfo_.get(index);
  }
  /**
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  public pb4client.OccupyWonderOrBuilder getOccupyWonderInfoOrBuilder(
      int index) {
    return occupyWonderInfo_.get(index);
  }

  public static final int CHANGETYPE_FIELD_NUMBER = 4;
  private int changeType_;
  /**
   * <code>int32 changeType = 4;</code>
   */
  public int getChangeType() {
    return changeType_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    for (int i = 0; i < getOccupyWonderInfoCount(); i++) {
      if (!getOccupyWonderInfo(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (playerId_ != 0L) {
      output.writeInt64(1, playerId_);
    }
    for (int i = 0; i < occupyWonderInfo_.size(); i++) {
      output.writeMessage(2, occupyWonderInfo_.get(i));
    }
    if (changeType_ != 0) {
      output.writeInt32(4, changeType_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (playerId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, playerId_);
    }
    for (int i = 0; i < occupyWonderInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, occupyWonderInfo_.get(i));
    }
    if (changeType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, changeType_);
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
    if (!(obj instanceof pb4server.OccupyWonder2HomeTell)) {
      return super.equals(obj);
    }
    pb4server.OccupyWonder2HomeTell other = (pb4server.OccupyWonder2HomeTell) obj;

    boolean result = true;
    result = result && (getPlayerId()
        == other.getPlayerId());
    result = result && getOccupyWonderInfoList()
        .equals(other.getOccupyWonderInfoList());
    result = result && (getChangeType()
        == other.getChangeType());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PLAYERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPlayerId());
    if (getOccupyWonderInfoCount() > 0) {
      hash = (37 * hash) + OCCUPYWONDERINFO_FIELD_NUMBER;
      hash = (53 * hash) + getOccupyWonderInfoList().hashCode();
    }
    hash = (37 * hash) + CHANGETYPE_FIELD_NUMBER;
    hash = (53 * hash) + getChangeType();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.OccupyWonder2HomeTell parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OccupyWonder2HomeTell parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OccupyWonder2HomeTell parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OccupyWonder2HomeTell parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OccupyWonder2HomeTell parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OccupyWonder2HomeTell parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OccupyWonder2HomeTell parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.OccupyWonder2HomeTell parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.OccupyWonder2HomeTell parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.OccupyWonder2HomeTell parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.OccupyWonder2HomeTell parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.OccupyWonder2HomeTell parseFrom(
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
  public static Builder newBuilder(pb4server.OccupyWonder2HomeTell prototype) {
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
   * Public-&gt;Home
   * 通知玩家占领了奇观
   * </pre>
   *
   * Protobuf type {@code pb4server.OccupyWonder2HomeTell}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.OccupyWonder2HomeTell)
      pb4server.OccupyWonder2HomeTellOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_OccupyWonder2HomeTell_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_OccupyWonder2HomeTell_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.OccupyWonder2HomeTell.class, pb4server.OccupyWonder2HomeTell.Builder.class);
    }

    // Construct using pb4server.OccupyWonder2HomeTell.newBuilder()
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
        getOccupyWonderInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      playerId_ = 0L;

      if (occupyWonderInfoBuilder_ == null) {
        occupyWonderInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        occupyWonderInfoBuilder_.clear();
      }
      changeType_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_OccupyWonder2HomeTell_descriptor;
    }

    public pb4server.OccupyWonder2HomeTell getDefaultInstanceForType() {
      return pb4server.OccupyWonder2HomeTell.getDefaultInstance();
    }

    public pb4server.OccupyWonder2HomeTell build() {
      pb4server.OccupyWonder2HomeTell result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.OccupyWonder2HomeTell buildPartial() {
      pb4server.OccupyWonder2HomeTell result = new pb4server.OccupyWonder2HomeTell(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.playerId_ = playerId_;
      if (occupyWonderInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          occupyWonderInfo_ = java.util.Collections.unmodifiableList(occupyWonderInfo_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.occupyWonderInfo_ = occupyWonderInfo_;
      } else {
        result.occupyWonderInfo_ = occupyWonderInfoBuilder_.build();
      }
      result.changeType_ = changeType_;
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
      if (other instanceof pb4server.OccupyWonder2HomeTell) {
        return mergeFrom((pb4server.OccupyWonder2HomeTell)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.OccupyWonder2HomeTell other) {
      if (other == pb4server.OccupyWonder2HomeTell.getDefaultInstance()) return this;
      if (other.getPlayerId() != 0L) {
        setPlayerId(other.getPlayerId());
      }
      if (occupyWonderInfoBuilder_ == null) {
        if (!other.occupyWonderInfo_.isEmpty()) {
          if (occupyWonderInfo_.isEmpty()) {
            occupyWonderInfo_ = other.occupyWonderInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureOccupyWonderInfoIsMutable();
            occupyWonderInfo_.addAll(other.occupyWonderInfo_);
          }
          onChanged();
        }
      } else {
        if (!other.occupyWonderInfo_.isEmpty()) {
          if (occupyWonderInfoBuilder_.isEmpty()) {
            occupyWonderInfoBuilder_.dispose();
            occupyWonderInfoBuilder_ = null;
            occupyWonderInfo_ = other.occupyWonderInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
            occupyWonderInfoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getOccupyWonderInfoFieldBuilder() : null;
          } else {
            occupyWonderInfoBuilder_.addAllMessages(other.occupyWonderInfo_);
          }
        }
      }
      if (other.getChangeType() != 0) {
        setChangeType(other.getChangeType());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      for (int i = 0; i < getOccupyWonderInfoCount(); i++) {
        if (!getOccupyWonderInfo(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4server.OccupyWonder2HomeTell parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.OccupyWonder2HomeTell) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long playerId_ ;
    /**
     * <code>int64 playerId = 1;</code>
     */
    public long getPlayerId() {
      return playerId_;
    }
    /**
     * <code>int64 playerId = 1;</code>
     */
    public Builder setPlayerId(long value) {
      
      playerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 playerId = 1;</code>
     */
    public Builder clearPlayerId() {
      
      playerId_ = 0L;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.OccupyWonder> occupyWonderInfo_ =
      java.util.Collections.emptyList();
    private void ensureOccupyWonderInfoIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        occupyWonderInfo_ = new java.util.ArrayList<pb4client.OccupyWonder>(occupyWonderInfo_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.OccupyWonder, pb4client.OccupyWonder.Builder, pb4client.OccupyWonderOrBuilder> occupyWonderInfoBuilder_;

    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public java.util.List<pb4client.OccupyWonder> getOccupyWonderInfoList() {
      if (occupyWonderInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(occupyWonderInfo_);
      } else {
        return occupyWonderInfoBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public int getOccupyWonderInfoCount() {
      if (occupyWonderInfoBuilder_ == null) {
        return occupyWonderInfo_.size();
      } else {
        return occupyWonderInfoBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public pb4client.OccupyWonder getOccupyWonderInfo(int index) {
      if (occupyWonderInfoBuilder_ == null) {
        return occupyWonderInfo_.get(index);
      } else {
        return occupyWonderInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public Builder setOccupyWonderInfo(
        int index, pb4client.OccupyWonder value) {
      if (occupyWonderInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOccupyWonderInfoIsMutable();
        occupyWonderInfo_.set(index, value);
        onChanged();
      } else {
        occupyWonderInfoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public Builder setOccupyWonderInfo(
        int index, pb4client.OccupyWonder.Builder builderForValue) {
      if (occupyWonderInfoBuilder_ == null) {
        ensureOccupyWonderInfoIsMutable();
        occupyWonderInfo_.set(index, builderForValue.build());
        onChanged();
      } else {
        occupyWonderInfoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public Builder addOccupyWonderInfo(pb4client.OccupyWonder value) {
      if (occupyWonderInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOccupyWonderInfoIsMutable();
        occupyWonderInfo_.add(value);
        onChanged();
      } else {
        occupyWonderInfoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public Builder addOccupyWonderInfo(
        int index, pb4client.OccupyWonder value) {
      if (occupyWonderInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOccupyWonderInfoIsMutable();
        occupyWonderInfo_.add(index, value);
        onChanged();
      } else {
        occupyWonderInfoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public Builder addOccupyWonderInfo(
        pb4client.OccupyWonder.Builder builderForValue) {
      if (occupyWonderInfoBuilder_ == null) {
        ensureOccupyWonderInfoIsMutable();
        occupyWonderInfo_.add(builderForValue.build());
        onChanged();
      } else {
        occupyWonderInfoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public Builder addOccupyWonderInfo(
        int index, pb4client.OccupyWonder.Builder builderForValue) {
      if (occupyWonderInfoBuilder_ == null) {
        ensureOccupyWonderInfoIsMutable();
        occupyWonderInfo_.add(index, builderForValue.build());
        onChanged();
      } else {
        occupyWonderInfoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public Builder addAllOccupyWonderInfo(
        java.lang.Iterable<? extends pb4client.OccupyWonder> values) {
      if (occupyWonderInfoBuilder_ == null) {
        ensureOccupyWonderInfoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, occupyWonderInfo_);
        onChanged();
      } else {
        occupyWonderInfoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public Builder clearOccupyWonderInfo() {
      if (occupyWonderInfoBuilder_ == null) {
        occupyWonderInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        occupyWonderInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public Builder removeOccupyWonderInfo(int index) {
      if (occupyWonderInfoBuilder_ == null) {
        ensureOccupyWonderInfoIsMutable();
        occupyWonderInfo_.remove(index);
        onChanged();
      } else {
        occupyWonderInfoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public pb4client.OccupyWonder.Builder getOccupyWonderInfoBuilder(
        int index) {
      return getOccupyWonderInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public pb4client.OccupyWonderOrBuilder getOccupyWonderInfoOrBuilder(
        int index) {
      if (occupyWonderInfoBuilder_ == null) {
        return occupyWonderInfo_.get(index);  } else {
        return occupyWonderInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public java.util.List<? extends pb4client.OccupyWonderOrBuilder> 
         getOccupyWonderInfoOrBuilderList() {
      if (occupyWonderInfoBuilder_ != null) {
        return occupyWonderInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(occupyWonderInfo_);
      }
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public pb4client.OccupyWonder.Builder addOccupyWonderInfoBuilder() {
      return getOccupyWonderInfoFieldBuilder().addBuilder(
          pb4client.OccupyWonder.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public pb4client.OccupyWonder.Builder addOccupyWonderInfoBuilder(
        int index) {
      return getOccupyWonderInfoFieldBuilder().addBuilder(
          index, pb4client.OccupyWonder.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
     */
    public java.util.List<pb4client.OccupyWonder.Builder> 
         getOccupyWonderInfoBuilderList() {
      return getOccupyWonderInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.OccupyWonder, pb4client.OccupyWonder.Builder, pb4client.OccupyWonderOrBuilder> 
        getOccupyWonderInfoFieldBuilder() {
      if (occupyWonderInfoBuilder_ == null) {
        occupyWonderInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.OccupyWonder, pb4client.OccupyWonder.Builder, pb4client.OccupyWonderOrBuilder>(
                occupyWonderInfo_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        occupyWonderInfo_ = null;
      }
      return occupyWonderInfoBuilder_;
    }

    private int changeType_ ;
    /**
     * <code>int32 changeType = 4;</code>
     */
    public int getChangeType() {
      return changeType_;
    }
    /**
     * <code>int32 changeType = 4;</code>
     */
    public Builder setChangeType(int value) {
      
      changeType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 changeType = 4;</code>
     */
    public Builder clearChangeType() {
      
      changeType_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.OccupyWonder2HomeTell)
  }

  // @@protoc_insertion_point(class_scope:pb4server.OccupyWonder2HomeTell)
  private static final pb4server.OccupyWonder2HomeTell DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.OccupyWonder2HomeTell();
  }

  public static pb4server.OccupyWonder2HomeTell getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OccupyWonder2HomeTell>
      PARSER = new com.google.protobuf.AbstractParser<OccupyWonder2HomeTell>() {
    public OccupyWonder2HomeTell parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OccupyWonder2HomeTell(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OccupyWonder2HomeTell> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OccupyWonder2HomeTell> getParserForType() {
    return PARSER;
  }

  public pb4server.OccupyWonder2HomeTell getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

