// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3071
 * 服务器 -&gt; 客户端
 *  聊天室新增成员
 * </pre>
 *
 * Protobuf type {@code client2server.RoomAddMember}
 */
public  final class RoomAddMember extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.RoomAddMember)
    RoomAddMemberOrBuilder {
  // Use RoomAddMember.newBuilder() to construct.
  private RoomAddMember(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RoomAddMember() {
    roomId_ = 0L;
    member_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RoomAddMember(
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
            roomId_ = input.readInt64();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              member_ = new java.util.ArrayList<pb4client.GroupMember>();
              mutable_bitField0_ |= 0x00000002;
            }
            member_.add(
                input.readMessage(pb4client.GroupMember.PARSER, extensionRegistry));
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
        member_ = java.util.Collections.unmodifiableList(member_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_RoomAddMember_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_RoomAddMember_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.RoomAddMember.class, pb4client.RoomAddMember.Builder.class);
  }

  private int bitField0_;
  public static final int ROOMID_FIELD_NUMBER = 1;
  private long roomId_;
  /**
   * <pre>
   *聊天室id
   * </pre>
   *
   * <code>required int64 roomId = 1;</code>
   */
  public boolean hasRoomId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *聊天室id
   * </pre>
   *
   * <code>required int64 roomId = 1;</code>
   */
  public long getRoomId() {
    return roomId_;
  }

  public static final int MEMBER_FIELD_NUMBER = 2;
  private java.util.List<pb4client.GroupMember> member_;
  /**
   * <pre>
   *新成员
   * </pre>
   *
   * <code>repeated .client2server.GroupMember member = 2;</code>
   */
  public java.util.List<pb4client.GroupMember> getMemberList() {
    return member_;
  }
  /**
   * <pre>
   *新成员
   * </pre>
   *
   * <code>repeated .client2server.GroupMember member = 2;</code>
   */
  public java.util.List<? extends pb4client.GroupMemberOrBuilder> 
      getMemberOrBuilderList() {
    return member_;
  }
  /**
   * <pre>
   *新成员
   * </pre>
   *
   * <code>repeated .client2server.GroupMember member = 2;</code>
   */
  public int getMemberCount() {
    return member_.size();
  }
  /**
   * <pre>
   *新成员
   * </pre>
   *
   * <code>repeated .client2server.GroupMember member = 2;</code>
   */
  public pb4client.GroupMember getMember(int index) {
    return member_.get(index);
  }
  /**
   * <pre>
   *新成员
   * </pre>
   *
   * <code>repeated .client2server.GroupMember member = 2;</code>
   */
  public pb4client.GroupMemberOrBuilder getMemberOrBuilder(
      int index) {
    return member_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasRoomId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    for (int i = 0; i < getMemberCount(); i++) {
      if (!getMember(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, roomId_);
    }
    for (int i = 0; i < member_.size(); i++) {
      output.writeMessage(2, member_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, roomId_);
    }
    for (int i = 0; i < member_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, member_.get(i));
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
    if (!(obj instanceof pb4client.RoomAddMember)) {
      return super.equals(obj);
    }
    pb4client.RoomAddMember other = (pb4client.RoomAddMember) obj;

    boolean result = true;
    result = result && (hasRoomId() == other.hasRoomId());
    if (hasRoomId()) {
      result = result && (getRoomId()
          == other.getRoomId());
    }
    result = result && getMemberList()
        .equals(other.getMemberList());
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
    if (hasRoomId()) {
      hash = (37 * hash) + ROOMID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getRoomId());
    }
    if (getMemberCount() > 0) {
      hash = (37 * hash) + MEMBER_FIELD_NUMBER;
      hash = (53 * hash) + getMemberList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.RoomAddMember parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RoomAddMember parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RoomAddMember parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RoomAddMember parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RoomAddMember parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RoomAddMember parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RoomAddMember parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RoomAddMember parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RoomAddMember parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.RoomAddMember parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RoomAddMember parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RoomAddMember parseFrom(
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
  public static Builder newBuilder(pb4client.RoomAddMember prototype) {
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
   * msgType = 3071
   * 服务器 -&gt; 客户端
   *  聊天室新增成员
   * </pre>
   *
   * Protobuf type {@code client2server.RoomAddMember}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.RoomAddMember)
      pb4client.RoomAddMemberOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_RoomAddMember_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_RoomAddMember_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.RoomAddMember.class, pb4client.RoomAddMember.Builder.class);
    }

    // Construct using pb4client.RoomAddMember.newBuilder()
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
        getMemberFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      roomId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (memberBuilder_ == null) {
        member_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        memberBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_RoomAddMember_descriptor;
    }

    public pb4client.RoomAddMember getDefaultInstanceForType() {
      return pb4client.RoomAddMember.getDefaultInstance();
    }

    public pb4client.RoomAddMember build() {
      pb4client.RoomAddMember result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.RoomAddMember buildPartial() {
      pb4client.RoomAddMember result = new pb4client.RoomAddMember(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.roomId_ = roomId_;
      if (memberBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          member_ = java.util.Collections.unmodifiableList(member_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.member_ = member_;
      } else {
        result.member_ = memberBuilder_.build();
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
      if (other instanceof pb4client.RoomAddMember) {
        return mergeFrom((pb4client.RoomAddMember)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.RoomAddMember other) {
      if (other == pb4client.RoomAddMember.getDefaultInstance()) return this;
      if (other.hasRoomId()) {
        setRoomId(other.getRoomId());
      }
      if (memberBuilder_ == null) {
        if (!other.member_.isEmpty()) {
          if (member_.isEmpty()) {
            member_ = other.member_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureMemberIsMutable();
            member_.addAll(other.member_);
          }
          onChanged();
        }
      } else {
        if (!other.member_.isEmpty()) {
          if (memberBuilder_.isEmpty()) {
            memberBuilder_.dispose();
            memberBuilder_ = null;
            member_ = other.member_;
            bitField0_ = (bitField0_ & ~0x00000002);
            memberBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getMemberFieldBuilder() : null;
          } else {
            memberBuilder_.addAllMessages(other.member_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRoomId()) {
        return false;
      }
      for (int i = 0; i < getMemberCount(); i++) {
        if (!getMember(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.RoomAddMember parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.RoomAddMember) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long roomId_ ;
    /**
     * <pre>
     *聊天室id
     * </pre>
     *
     * <code>required int64 roomId = 1;</code>
     */
    public boolean hasRoomId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *聊天室id
     * </pre>
     *
     * <code>required int64 roomId = 1;</code>
     */
    public long getRoomId() {
      return roomId_;
    }
    /**
     * <pre>
     *聊天室id
     * </pre>
     *
     * <code>required int64 roomId = 1;</code>
     */
    public Builder setRoomId(long value) {
      bitField0_ |= 0x00000001;
      roomId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *聊天室id
     * </pre>
     *
     * <code>required int64 roomId = 1;</code>
     */
    public Builder clearRoomId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      roomId_ = 0L;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.GroupMember> member_ =
      java.util.Collections.emptyList();
    private void ensureMemberIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        member_ = new java.util.ArrayList<pb4client.GroupMember>(member_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.GroupMember, pb4client.GroupMember.Builder, pb4client.GroupMemberOrBuilder> memberBuilder_;

    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public java.util.List<pb4client.GroupMember> getMemberList() {
      if (memberBuilder_ == null) {
        return java.util.Collections.unmodifiableList(member_);
      } else {
        return memberBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public int getMemberCount() {
      if (memberBuilder_ == null) {
        return member_.size();
      } else {
        return memberBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public pb4client.GroupMember getMember(int index) {
      if (memberBuilder_ == null) {
        return member_.get(index);
      } else {
        return memberBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public Builder setMember(
        int index, pb4client.GroupMember value) {
      if (memberBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMemberIsMutable();
        member_.set(index, value);
        onChanged();
      } else {
        memberBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public Builder setMember(
        int index, pb4client.GroupMember.Builder builderForValue) {
      if (memberBuilder_ == null) {
        ensureMemberIsMutable();
        member_.set(index, builderForValue.build());
        onChanged();
      } else {
        memberBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public Builder addMember(pb4client.GroupMember value) {
      if (memberBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMemberIsMutable();
        member_.add(value);
        onChanged();
      } else {
        memberBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public Builder addMember(
        int index, pb4client.GroupMember value) {
      if (memberBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMemberIsMutable();
        member_.add(index, value);
        onChanged();
      } else {
        memberBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public Builder addMember(
        pb4client.GroupMember.Builder builderForValue) {
      if (memberBuilder_ == null) {
        ensureMemberIsMutable();
        member_.add(builderForValue.build());
        onChanged();
      } else {
        memberBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public Builder addMember(
        int index, pb4client.GroupMember.Builder builderForValue) {
      if (memberBuilder_ == null) {
        ensureMemberIsMutable();
        member_.add(index, builderForValue.build());
        onChanged();
      } else {
        memberBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public Builder addAllMember(
        java.lang.Iterable<? extends pb4client.GroupMember> values) {
      if (memberBuilder_ == null) {
        ensureMemberIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, member_);
        onChanged();
      } else {
        memberBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public Builder clearMember() {
      if (memberBuilder_ == null) {
        member_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        memberBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public Builder removeMember(int index) {
      if (memberBuilder_ == null) {
        ensureMemberIsMutable();
        member_.remove(index);
        onChanged();
      } else {
        memberBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public pb4client.GroupMember.Builder getMemberBuilder(
        int index) {
      return getMemberFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public pb4client.GroupMemberOrBuilder getMemberOrBuilder(
        int index) {
      if (memberBuilder_ == null) {
        return member_.get(index);  } else {
        return memberBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public java.util.List<? extends pb4client.GroupMemberOrBuilder> 
         getMemberOrBuilderList() {
      if (memberBuilder_ != null) {
        return memberBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(member_);
      }
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public pb4client.GroupMember.Builder addMemberBuilder() {
      return getMemberFieldBuilder().addBuilder(
          pb4client.GroupMember.getDefaultInstance());
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public pb4client.GroupMember.Builder addMemberBuilder(
        int index) {
      return getMemberFieldBuilder().addBuilder(
          index, pb4client.GroupMember.getDefaultInstance());
    }
    /**
     * <pre>
     *新成员
     * </pre>
     *
     * <code>repeated .client2server.GroupMember member = 2;</code>
     */
    public java.util.List<pb4client.GroupMember.Builder> 
         getMemberBuilderList() {
      return getMemberFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.GroupMember, pb4client.GroupMember.Builder, pb4client.GroupMemberOrBuilder> 
        getMemberFieldBuilder() {
      if (memberBuilder_ == null) {
        memberBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.GroupMember, pb4client.GroupMember.Builder, pb4client.GroupMemberOrBuilder>(
                member_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        member_ = null;
      }
      return memberBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.RoomAddMember)
  }

  // @@protoc_insertion_point(class_scope:client2server.RoomAddMember)
  private static final pb4client.RoomAddMember DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.RoomAddMember();
  }

  public static pb4client.RoomAddMember getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<RoomAddMember>
      PARSER = new com.google.protobuf.AbstractParser<RoomAddMember>() {
    public RoomAddMember parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RoomAddMember(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RoomAddMember> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RoomAddMember> getParserForType() {
    return PARSER;
  }

  public pb4client.RoomAddMember getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

