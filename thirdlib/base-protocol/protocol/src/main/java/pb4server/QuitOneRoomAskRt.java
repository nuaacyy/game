// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.QuitOneRoomAskRt}
 */
public  final class QuitOneRoomAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.QuitOneRoomAskRt)
    QuitOneRoomAskRtOrBuilder {
  // Use QuitOneRoomAskRt.newBuilder() to construct.
  private QuitOneRoomAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QuitOneRoomAskRt() {
    rt_ = 0;
    chatRoomId_ = 0L;
    roomName_ = "";
    unreadNum_ = 0;
    iconProtoIds_ = java.util.Collections.emptyList();
    memberNum_ = 0;
    roomPlayerId_ = 0L;
    memberIds_ = java.util.Collections.emptyList();
    lastSendTime_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private QuitOneRoomAskRt(
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
          case 16: {

            chatRoomId_ = input.readInt64();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            roomName_ = s;
            break;
          }
          case 32: {

            unreadNum_ = input.readInt32();
            break;
          }
          case 40: {
            if (!((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
              iconProtoIds_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000010;
            }
            iconProtoIds_.add(input.readInt32());
            break;
          }
          case 42: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000010) == 0x00000010) && input.getBytesUntilLimit() > 0) {
              iconProtoIds_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000010;
            }
            while (input.getBytesUntilLimit() > 0) {
              iconProtoIds_.add(input.readInt32());
            }
            input.popLimit(limit);
            break;
          }
          case 48: {

            memberNum_ = input.readInt32();
            break;
          }
          case 56: {

            roomPlayerId_ = input.readInt64();
            break;
          }
          case 64: {
            if (!((mutable_bitField0_ & 0x00000080) == 0x00000080)) {
              memberIds_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000080;
            }
            memberIds_.add(input.readInt64());
            break;
          }
          case 66: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000080) == 0x00000080) && input.getBytesUntilLimit() > 0) {
              memberIds_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000080;
            }
            while (input.getBytesUntilLimit() > 0) {
              memberIds_.add(input.readInt64());
            }
            input.popLimit(limit);
            break;
          }
          case 72: {

            lastSendTime_ = input.readInt64();
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
      if (((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
        iconProtoIds_ = java.util.Collections.unmodifiableList(iconProtoIds_);
      }
      if (((mutable_bitField0_ & 0x00000080) == 0x00000080)) {
        memberIds_ = java.util.Collections.unmodifiableList(memberIds_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_QuitOneRoomAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_QuitOneRoomAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.QuitOneRoomAskRt.class, pb4server.QuitOneRoomAskRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int CHATROOMID_FIELD_NUMBER = 2;
  private long chatRoomId_;
  /**
   * <code>int64 chatRoomId = 2;</code>
   */
  public long getChatRoomId() {
    return chatRoomId_;
  }

  public static final int ROOMNAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object roomName_;
  /**
   * <code>string roomName = 3;</code>
   */
  public java.lang.String getRoomName() {
    java.lang.Object ref = roomName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      roomName_ = s;
      return s;
    }
  }
  /**
   * <code>string roomName = 3;</code>
   */
  public com.google.protobuf.ByteString
      getRoomNameBytes() {
    java.lang.Object ref = roomName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      roomName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int UNREADNUM_FIELD_NUMBER = 4;
  private int unreadNum_;
  /**
   * <code>int32 unreadNum = 4;</code>
   */
  public int getUnreadNum() {
    return unreadNum_;
  }

  public static final int ICONPROTOIDS_FIELD_NUMBER = 5;
  private java.util.List<java.lang.Integer> iconProtoIds_;
  /**
   * <code>repeated int32 iconProtoIds = 5;</code>
   */
  public java.util.List<java.lang.Integer>
      getIconProtoIdsList() {
    return iconProtoIds_;
  }
  /**
   * <code>repeated int32 iconProtoIds = 5;</code>
   */
  public int getIconProtoIdsCount() {
    return iconProtoIds_.size();
  }
  /**
   * <code>repeated int32 iconProtoIds = 5;</code>
   */
  public int getIconProtoIds(int index) {
    return iconProtoIds_.get(index);
  }
  private int iconProtoIdsMemoizedSerializedSize = -1;

  public static final int MEMBERNUM_FIELD_NUMBER = 6;
  private int memberNum_;
  /**
   * <code>int32 memberNum = 6;</code>
   */
  public int getMemberNum() {
    return memberNum_;
  }

  public static final int ROOMPLAYERID_FIELD_NUMBER = 7;
  private long roomPlayerId_;
  /**
   * <code>int64 roomPlayerId = 7;</code>
   */
  public long getRoomPlayerId() {
    return roomPlayerId_;
  }

  public static final int MEMBERIDS_FIELD_NUMBER = 8;
  private java.util.List<java.lang.Long> memberIds_;
  /**
   * <code>repeated int64 memberIds = 8;</code>
   */
  public java.util.List<java.lang.Long>
      getMemberIdsList() {
    return memberIds_;
  }
  /**
   * <code>repeated int64 memberIds = 8;</code>
   */
  public int getMemberIdsCount() {
    return memberIds_.size();
  }
  /**
   * <code>repeated int64 memberIds = 8;</code>
   */
  public long getMemberIds(int index) {
    return memberIds_.get(index);
  }
  private int memberIdsMemoizedSerializedSize = -1;

  public static final int LASTSENDTIME_FIELD_NUMBER = 9;
  private long lastSendTime_;
  /**
   * <code>int64 lastSendTime = 9;</code>
   */
  public long getLastSendTime() {
    return lastSendTime_;
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
    getSerializedSize();
    if (rt_ != 0) {
      output.writeInt32(1, rt_);
    }
    if (chatRoomId_ != 0L) {
      output.writeInt64(2, chatRoomId_);
    }
    if (!getRoomNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, roomName_);
    }
    if (unreadNum_ != 0) {
      output.writeInt32(4, unreadNum_);
    }
    if (getIconProtoIdsList().size() > 0) {
      output.writeUInt32NoTag(42);
      output.writeUInt32NoTag(iconProtoIdsMemoizedSerializedSize);
    }
    for (int i = 0; i < iconProtoIds_.size(); i++) {
      output.writeInt32NoTag(iconProtoIds_.get(i));
    }
    if (memberNum_ != 0) {
      output.writeInt32(6, memberNum_);
    }
    if (roomPlayerId_ != 0L) {
      output.writeInt64(7, roomPlayerId_);
    }
    if (getMemberIdsList().size() > 0) {
      output.writeUInt32NoTag(66);
      output.writeUInt32NoTag(memberIdsMemoizedSerializedSize);
    }
    for (int i = 0; i < memberIds_.size(); i++) {
      output.writeInt64NoTag(memberIds_.get(i));
    }
    if (lastSendTime_ != 0L) {
      output.writeInt64(9, lastSendTime_);
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
    if (chatRoomId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, chatRoomId_);
    }
    if (!getRoomNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, roomName_);
    }
    if (unreadNum_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, unreadNum_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < iconProtoIds_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(iconProtoIds_.get(i));
      }
      size += dataSize;
      if (!getIconProtoIdsList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      iconProtoIdsMemoizedSerializedSize = dataSize;
    }
    if (memberNum_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, memberNum_);
    }
    if (roomPlayerId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(7, roomPlayerId_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < memberIds_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt64SizeNoTag(memberIds_.get(i));
      }
      size += dataSize;
      if (!getMemberIdsList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      memberIdsMemoizedSerializedSize = dataSize;
    }
    if (lastSendTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(9, lastSendTime_);
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
    if (!(obj instanceof pb4server.QuitOneRoomAskRt)) {
      return super.equals(obj);
    }
    pb4server.QuitOneRoomAskRt other = (pb4server.QuitOneRoomAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && (getChatRoomId()
        == other.getChatRoomId());
    result = result && getRoomName()
        .equals(other.getRoomName());
    result = result && (getUnreadNum()
        == other.getUnreadNum());
    result = result && getIconProtoIdsList()
        .equals(other.getIconProtoIdsList());
    result = result && (getMemberNum()
        == other.getMemberNum());
    result = result && (getRoomPlayerId()
        == other.getRoomPlayerId());
    result = result && getMemberIdsList()
        .equals(other.getMemberIdsList());
    result = result && (getLastSendTime()
        == other.getLastSendTime());
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
    hash = (37 * hash) + CHATROOMID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getChatRoomId());
    hash = (37 * hash) + ROOMNAME_FIELD_NUMBER;
    hash = (53 * hash) + getRoomName().hashCode();
    hash = (37 * hash) + UNREADNUM_FIELD_NUMBER;
    hash = (53 * hash) + getUnreadNum();
    if (getIconProtoIdsCount() > 0) {
      hash = (37 * hash) + ICONPROTOIDS_FIELD_NUMBER;
      hash = (53 * hash) + getIconProtoIdsList().hashCode();
    }
    hash = (37 * hash) + MEMBERNUM_FIELD_NUMBER;
    hash = (53 * hash) + getMemberNum();
    hash = (37 * hash) + ROOMPLAYERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getRoomPlayerId());
    if (getMemberIdsCount() > 0) {
      hash = (37 * hash) + MEMBERIDS_FIELD_NUMBER;
      hash = (53 * hash) + getMemberIdsList().hashCode();
    }
    hash = (37 * hash) + LASTSENDTIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getLastSendTime());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.QuitOneRoomAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QuitOneRoomAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QuitOneRoomAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QuitOneRoomAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QuitOneRoomAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QuitOneRoomAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QuitOneRoomAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QuitOneRoomAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QuitOneRoomAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.QuitOneRoomAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QuitOneRoomAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QuitOneRoomAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.QuitOneRoomAskRt prototype) {
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
   * Protobuf type {@code pb4server.QuitOneRoomAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.QuitOneRoomAskRt)
      pb4server.QuitOneRoomAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_QuitOneRoomAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_QuitOneRoomAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.QuitOneRoomAskRt.class, pb4server.QuitOneRoomAskRt.Builder.class);
    }

    // Construct using pb4server.QuitOneRoomAskRt.newBuilder()
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

      chatRoomId_ = 0L;

      roomName_ = "";

      unreadNum_ = 0;

      iconProtoIds_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000010);
      memberNum_ = 0;

      roomPlayerId_ = 0L;

      memberIds_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000080);
      lastSendTime_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_QuitOneRoomAskRt_descriptor;
    }

    public pb4server.QuitOneRoomAskRt getDefaultInstanceForType() {
      return pb4server.QuitOneRoomAskRt.getDefaultInstance();
    }

    public pb4server.QuitOneRoomAskRt build() {
      pb4server.QuitOneRoomAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.QuitOneRoomAskRt buildPartial() {
      pb4server.QuitOneRoomAskRt result = new pb4server.QuitOneRoomAskRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.rt_ = rt_;
      result.chatRoomId_ = chatRoomId_;
      result.roomName_ = roomName_;
      result.unreadNum_ = unreadNum_;
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        iconProtoIds_ = java.util.Collections.unmodifiableList(iconProtoIds_);
        bitField0_ = (bitField0_ & ~0x00000010);
      }
      result.iconProtoIds_ = iconProtoIds_;
      result.memberNum_ = memberNum_;
      result.roomPlayerId_ = roomPlayerId_;
      if (((bitField0_ & 0x00000080) == 0x00000080)) {
        memberIds_ = java.util.Collections.unmodifiableList(memberIds_);
        bitField0_ = (bitField0_ & ~0x00000080);
      }
      result.memberIds_ = memberIds_;
      result.lastSendTime_ = lastSendTime_;
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
      if (other instanceof pb4server.QuitOneRoomAskRt) {
        return mergeFrom((pb4server.QuitOneRoomAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.QuitOneRoomAskRt other) {
      if (other == pb4server.QuitOneRoomAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (other.getChatRoomId() != 0L) {
        setChatRoomId(other.getChatRoomId());
      }
      if (!other.getRoomName().isEmpty()) {
        roomName_ = other.roomName_;
        onChanged();
      }
      if (other.getUnreadNum() != 0) {
        setUnreadNum(other.getUnreadNum());
      }
      if (!other.iconProtoIds_.isEmpty()) {
        if (iconProtoIds_.isEmpty()) {
          iconProtoIds_ = other.iconProtoIds_;
          bitField0_ = (bitField0_ & ~0x00000010);
        } else {
          ensureIconProtoIdsIsMutable();
          iconProtoIds_.addAll(other.iconProtoIds_);
        }
        onChanged();
      }
      if (other.getMemberNum() != 0) {
        setMemberNum(other.getMemberNum());
      }
      if (other.getRoomPlayerId() != 0L) {
        setRoomPlayerId(other.getRoomPlayerId());
      }
      if (!other.memberIds_.isEmpty()) {
        if (memberIds_.isEmpty()) {
          memberIds_ = other.memberIds_;
          bitField0_ = (bitField0_ & ~0x00000080);
        } else {
          ensureMemberIdsIsMutable();
          memberIds_.addAll(other.memberIds_);
        }
        onChanged();
      }
      if (other.getLastSendTime() != 0L) {
        setLastSendTime(other.getLastSendTime());
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
      pb4server.QuitOneRoomAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.QuitOneRoomAskRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

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

    private long chatRoomId_ ;
    /**
     * <code>int64 chatRoomId = 2;</code>
     */
    public long getChatRoomId() {
      return chatRoomId_;
    }
    /**
     * <code>int64 chatRoomId = 2;</code>
     */
    public Builder setChatRoomId(long value) {
      
      chatRoomId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 chatRoomId = 2;</code>
     */
    public Builder clearChatRoomId() {
      
      chatRoomId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object roomName_ = "";
    /**
     * <code>string roomName = 3;</code>
     */
    public java.lang.String getRoomName() {
      java.lang.Object ref = roomName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        roomName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string roomName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getRoomNameBytes() {
      java.lang.Object ref = roomName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        roomName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string roomName = 3;</code>
     */
    public Builder setRoomName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      roomName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string roomName = 3;</code>
     */
    public Builder clearRoomName() {
      
      roomName_ = getDefaultInstance().getRoomName();
      onChanged();
      return this;
    }
    /**
     * <code>string roomName = 3;</code>
     */
    public Builder setRoomNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      roomName_ = value;
      onChanged();
      return this;
    }

    private int unreadNum_ ;
    /**
     * <code>int32 unreadNum = 4;</code>
     */
    public int getUnreadNum() {
      return unreadNum_;
    }
    /**
     * <code>int32 unreadNum = 4;</code>
     */
    public Builder setUnreadNum(int value) {
      
      unreadNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 unreadNum = 4;</code>
     */
    public Builder clearUnreadNum() {
      
      unreadNum_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Integer> iconProtoIds_ = java.util.Collections.emptyList();
    private void ensureIconProtoIdsIsMutable() {
      if (!((bitField0_ & 0x00000010) == 0x00000010)) {
        iconProtoIds_ = new java.util.ArrayList<java.lang.Integer>(iconProtoIds_);
        bitField0_ |= 0x00000010;
       }
    }
    /**
     * <code>repeated int32 iconProtoIds = 5;</code>
     */
    public java.util.List<java.lang.Integer>
        getIconProtoIdsList() {
      return java.util.Collections.unmodifiableList(iconProtoIds_);
    }
    /**
     * <code>repeated int32 iconProtoIds = 5;</code>
     */
    public int getIconProtoIdsCount() {
      return iconProtoIds_.size();
    }
    /**
     * <code>repeated int32 iconProtoIds = 5;</code>
     */
    public int getIconProtoIds(int index) {
      return iconProtoIds_.get(index);
    }
    /**
     * <code>repeated int32 iconProtoIds = 5;</code>
     */
    public Builder setIconProtoIds(
        int index, int value) {
      ensureIconProtoIdsIsMutable();
      iconProtoIds_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 iconProtoIds = 5;</code>
     */
    public Builder addIconProtoIds(int value) {
      ensureIconProtoIdsIsMutable();
      iconProtoIds_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 iconProtoIds = 5;</code>
     */
    public Builder addAllIconProtoIds(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensureIconProtoIdsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, iconProtoIds_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 iconProtoIds = 5;</code>
     */
    public Builder clearIconProtoIds() {
      iconProtoIds_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000010);
      onChanged();
      return this;
    }

    private int memberNum_ ;
    /**
     * <code>int32 memberNum = 6;</code>
     */
    public int getMemberNum() {
      return memberNum_;
    }
    /**
     * <code>int32 memberNum = 6;</code>
     */
    public Builder setMemberNum(int value) {
      
      memberNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 memberNum = 6;</code>
     */
    public Builder clearMemberNum() {
      
      memberNum_ = 0;
      onChanged();
      return this;
    }

    private long roomPlayerId_ ;
    /**
     * <code>int64 roomPlayerId = 7;</code>
     */
    public long getRoomPlayerId() {
      return roomPlayerId_;
    }
    /**
     * <code>int64 roomPlayerId = 7;</code>
     */
    public Builder setRoomPlayerId(long value) {
      
      roomPlayerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 roomPlayerId = 7;</code>
     */
    public Builder clearRoomPlayerId() {
      
      roomPlayerId_ = 0L;
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Long> memberIds_ = java.util.Collections.emptyList();
    private void ensureMemberIdsIsMutable() {
      if (!((bitField0_ & 0x00000080) == 0x00000080)) {
        memberIds_ = new java.util.ArrayList<java.lang.Long>(memberIds_);
        bitField0_ |= 0x00000080;
       }
    }
    /**
     * <code>repeated int64 memberIds = 8;</code>
     */
    public java.util.List<java.lang.Long>
        getMemberIdsList() {
      return java.util.Collections.unmodifiableList(memberIds_);
    }
    /**
     * <code>repeated int64 memberIds = 8;</code>
     */
    public int getMemberIdsCount() {
      return memberIds_.size();
    }
    /**
     * <code>repeated int64 memberIds = 8;</code>
     */
    public long getMemberIds(int index) {
      return memberIds_.get(index);
    }
    /**
     * <code>repeated int64 memberIds = 8;</code>
     */
    public Builder setMemberIds(
        int index, long value) {
      ensureMemberIdsIsMutable();
      memberIds_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 memberIds = 8;</code>
     */
    public Builder addMemberIds(long value) {
      ensureMemberIdsIsMutable();
      memberIds_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 memberIds = 8;</code>
     */
    public Builder addAllMemberIds(
        java.lang.Iterable<? extends java.lang.Long> values) {
      ensureMemberIdsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, memberIds_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 memberIds = 8;</code>
     */
    public Builder clearMemberIds() {
      memberIds_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000080);
      onChanged();
      return this;
    }

    private long lastSendTime_ ;
    /**
     * <code>int64 lastSendTime = 9;</code>
     */
    public long getLastSendTime() {
      return lastSendTime_;
    }
    /**
     * <code>int64 lastSendTime = 9;</code>
     */
    public Builder setLastSendTime(long value) {
      
      lastSendTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 lastSendTime = 9;</code>
     */
    public Builder clearLastSendTime() {
      
      lastSendTime_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:pb4server.QuitOneRoomAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.QuitOneRoomAskRt)
  private static final pb4server.QuitOneRoomAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.QuitOneRoomAskRt();
  }

  public static pb4server.QuitOneRoomAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QuitOneRoomAskRt>
      PARSER = new com.google.protobuf.AbstractParser<QuitOneRoomAskRt>() {
    public QuitOneRoomAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QuitOneRoomAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QuitOneRoomAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QuitOneRoomAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.QuitOneRoomAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
