// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * <pre>
 * 发送狩猎邀请
 * </pre>
 *
 * Protobuf type {@code pb4server.HunterInviteAskReq}
 */
public  final class HunterInviteAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.HunterInviteAskReq)
    HunterInviteAskReqOrBuilder {
  // Use HunterInviteAskReq.newBuilder() to construct.
  private HunterInviteAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HunterInviteAskReq() {
    posX_ = 0;
    posY_ = 0;
    bossId_ = 0;
    nowHp_ = 0;
    atkRecord_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private HunterInviteAskReq(
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

            posX_ = input.readInt32();
            break;
          }
          case 16: {

            posY_ = input.readInt32();
            break;
          }
          case 24: {

            bossId_ = input.readInt32();
            break;
          }
          case 32: {

            nowHp_ = input.readInt32();
            break;
          }
          case 42: {
            if (!((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
              atkRecord_ = new java.util.ArrayList<pb4server.AtkRecordVo>();
              mutable_bitField0_ |= 0x00000010;
            }
            atkRecord_.add(
                input.readMessage(pb4server.AtkRecordVo.parser(), extensionRegistry));
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
        atkRecord_ = java.util.Collections.unmodifiableList(atkRecord_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_HunterInviteAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_HunterInviteAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.HunterInviteAskReq.class, pb4server.HunterInviteAskReq.Builder.class);
  }

  private int bitField0_;
  public static final int POSX_FIELD_NUMBER = 1;
  private int posX_;
  /**
   * <code>int32 posX = 1;</code>
   */
  public int getPosX() {
    return posX_;
  }

  public static final int POSY_FIELD_NUMBER = 2;
  private int posY_;
  /**
   * <code>int32 posY = 2;</code>
   */
  public int getPosY() {
    return posY_;
  }

  public static final int BOSSID_FIELD_NUMBER = 3;
  private int bossId_;
  /**
   * <code>int32 bossId = 3;</code>
   */
  public int getBossId() {
    return bossId_;
  }

  public static final int NOWHP_FIELD_NUMBER = 4;
  private int nowHp_;
  /**
   * <code>int32 nowHp = 4;</code>
   */
  public int getNowHp() {
    return nowHp_;
  }

  public static final int ATKRECORD_FIELD_NUMBER = 5;
  private java.util.List<pb4server.AtkRecordVo> atkRecord_;
  /**
   * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
   */
  public java.util.List<pb4server.AtkRecordVo> getAtkRecordList() {
    return atkRecord_;
  }
  /**
   * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
   */
  public java.util.List<? extends pb4server.AtkRecordVoOrBuilder> 
      getAtkRecordOrBuilderList() {
    return atkRecord_;
  }
  /**
   * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
   */
  public int getAtkRecordCount() {
    return atkRecord_.size();
  }
  /**
   * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
   */
  public pb4server.AtkRecordVo getAtkRecord(int index) {
    return atkRecord_.get(index);
  }
  /**
   * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
   */
  public pb4server.AtkRecordVoOrBuilder getAtkRecordOrBuilder(
      int index) {
    return atkRecord_.get(index);
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
    if (posX_ != 0) {
      output.writeInt32(1, posX_);
    }
    if (posY_ != 0) {
      output.writeInt32(2, posY_);
    }
    if (bossId_ != 0) {
      output.writeInt32(3, bossId_);
    }
    if (nowHp_ != 0) {
      output.writeInt32(4, nowHp_);
    }
    for (int i = 0; i < atkRecord_.size(); i++) {
      output.writeMessage(5, atkRecord_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (posX_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, posX_);
    }
    if (posY_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, posY_);
    }
    if (bossId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, bossId_);
    }
    if (nowHp_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, nowHp_);
    }
    for (int i = 0; i < atkRecord_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, atkRecord_.get(i));
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
    if (!(obj instanceof pb4server.HunterInviteAskReq)) {
      return super.equals(obj);
    }
    pb4server.HunterInviteAskReq other = (pb4server.HunterInviteAskReq) obj;

    boolean result = true;
    result = result && (getPosX()
        == other.getPosX());
    result = result && (getPosY()
        == other.getPosY());
    result = result && (getBossId()
        == other.getBossId());
    result = result && (getNowHp()
        == other.getNowHp());
    result = result && getAtkRecordList()
        .equals(other.getAtkRecordList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + POSX_FIELD_NUMBER;
    hash = (53 * hash) + getPosX();
    hash = (37 * hash) + POSY_FIELD_NUMBER;
    hash = (53 * hash) + getPosY();
    hash = (37 * hash) + BOSSID_FIELD_NUMBER;
    hash = (53 * hash) + getBossId();
    hash = (37 * hash) + NOWHP_FIELD_NUMBER;
    hash = (53 * hash) + getNowHp();
    if (getAtkRecordCount() > 0) {
      hash = (37 * hash) + ATKRECORD_FIELD_NUMBER;
      hash = (53 * hash) + getAtkRecordList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.HunterInviteAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.HunterInviteAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.HunterInviteAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.HunterInviteAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.HunterInviteAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.HunterInviteAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.HunterInviteAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.HunterInviteAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.HunterInviteAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.HunterInviteAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.HunterInviteAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.HunterInviteAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.HunterInviteAskReq prototype) {
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
   * 发送狩猎邀请
   * </pre>
   *
   * Protobuf type {@code pb4server.HunterInviteAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.HunterInviteAskReq)
      pb4server.HunterInviteAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_HunterInviteAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_HunterInviteAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.HunterInviteAskReq.class, pb4server.HunterInviteAskReq.Builder.class);
    }

    // Construct using pb4server.HunterInviteAskReq.newBuilder()
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
        getAtkRecordFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      posX_ = 0;

      posY_ = 0;

      bossId_ = 0;

      nowHp_ = 0;

      if (atkRecordBuilder_ == null) {
        atkRecord_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000010);
      } else {
        atkRecordBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_HunterInviteAskReq_descriptor;
    }

    public pb4server.HunterInviteAskReq getDefaultInstanceForType() {
      return pb4server.HunterInviteAskReq.getDefaultInstance();
    }

    public pb4server.HunterInviteAskReq build() {
      pb4server.HunterInviteAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.HunterInviteAskReq buildPartial() {
      pb4server.HunterInviteAskReq result = new pb4server.HunterInviteAskReq(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.posX_ = posX_;
      result.posY_ = posY_;
      result.bossId_ = bossId_;
      result.nowHp_ = nowHp_;
      if (atkRecordBuilder_ == null) {
        if (((bitField0_ & 0x00000010) == 0x00000010)) {
          atkRecord_ = java.util.Collections.unmodifiableList(atkRecord_);
          bitField0_ = (bitField0_ & ~0x00000010);
        }
        result.atkRecord_ = atkRecord_;
      } else {
        result.atkRecord_ = atkRecordBuilder_.build();
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
      if (other instanceof pb4server.HunterInviteAskReq) {
        return mergeFrom((pb4server.HunterInviteAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.HunterInviteAskReq other) {
      if (other == pb4server.HunterInviteAskReq.getDefaultInstance()) return this;
      if (other.getPosX() != 0) {
        setPosX(other.getPosX());
      }
      if (other.getPosY() != 0) {
        setPosY(other.getPosY());
      }
      if (other.getBossId() != 0) {
        setBossId(other.getBossId());
      }
      if (other.getNowHp() != 0) {
        setNowHp(other.getNowHp());
      }
      if (atkRecordBuilder_ == null) {
        if (!other.atkRecord_.isEmpty()) {
          if (atkRecord_.isEmpty()) {
            atkRecord_ = other.atkRecord_;
            bitField0_ = (bitField0_ & ~0x00000010);
          } else {
            ensureAtkRecordIsMutable();
            atkRecord_.addAll(other.atkRecord_);
          }
          onChanged();
        }
      } else {
        if (!other.atkRecord_.isEmpty()) {
          if (atkRecordBuilder_.isEmpty()) {
            atkRecordBuilder_.dispose();
            atkRecordBuilder_ = null;
            atkRecord_ = other.atkRecord_;
            bitField0_ = (bitField0_ & ~0x00000010);
            atkRecordBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getAtkRecordFieldBuilder() : null;
          } else {
            atkRecordBuilder_.addAllMessages(other.atkRecord_);
          }
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
      pb4server.HunterInviteAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.HunterInviteAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int posX_ ;
    /**
     * <code>int32 posX = 1;</code>
     */
    public int getPosX() {
      return posX_;
    }
    /**
     * <code>int32 posX = 1;</code>
     */
    public Builder setPosX(int value) {
      
      posX_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 posX = 1;</code>
     */
    public Builder clearPosX() {
      
      posX_ = 0;
      onChanged();
      return this;
    }

    private int posY_ ;
    /**
     * <code>int32 posY = 2;</code>
     */
    public int getPosY() {
      return posY_;
    }
    /**
     * <code>int32 posY = 2;</code>
     */
    public Builder setPosY(int value) {
      
      posY_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 posY = 2;</code>
     */
    public Builder clearPosY() {
      
      posY_ = 0;
      onChanged();
      return this;
    }

    private int bossId_ ;
    /**
     * <code>int32 bossId = 3;</code>
     */
    public int getBossId() {
      return bossId_;
    }
    /**
     * <code>int32 bossId = 3;</code>
     */
    public Builder setBossId(int value) {
      
      bossId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 bossId = 3;</code>
     */
    public Builder clearBossId() {
      
      bossId_ = 0;
      onChanged();
      return this;
    }

    private int nowHp_ ;
    /**
     * <code>int32 nowHp = 4;</code>
     */
    public int getNowHp() {
      return nowHp_;
    }
    /**
     * <code>int32 nowHp = 4;</code>
     */
    public Builder setNowHp(int value) {
      
      nowHp_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 nowHp = 4;</code>
     */
    public Builder clearNowHp() {
      
      nowHp_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4server.AtkRecordVo> atkRecord_ =
      java.util.Collections.emptyList();
    private void ensureAtkRecordIsMutable() {
      if (!((bitField0_ & 0x00000010) == 0x00000010)) {
        atkRecord_ = new java.util.ArrayList<pb4server.AtkRecordVo>(atkRecord_);
        bitField0_ |= 0x00000010;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AtkRecordVo, pb4server.AtkRecordVo.Builder, pb4server.AtkRecordVoOrBuilder> atkRecordBuilder_;

    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public java.util.List<pb4server.AtkRecordVo> getAtkRecordList() {
      if (atkRecordBuilder_ == null) {
        return java.util.Collections.unmodifiableList(atkRecord_);
      } else {
        return atkRecordBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public int getAtkRecordCount() {
      if (atkRecordBuilder_ == null) {
        return atkRecord_.size();
      } else {
        return atkRecordBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public pb4server.AtkRecordVo getAtkRecord(int index) {
      if (atkRecordBuilder_ == null) {
        return atkRecord_.get(index);
      } else {
        return atkRecordBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public Builder setAtkRecord(
        int index, pb4server.AtkRecordVo value) {
      if (atkRecordBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAtkRecordIsMutable();
        atkRecord_.set(index, value);
        onChanged();
      } else {
        atkRecordBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public Builder setAtkRecord(
        int index, pb4server.AtkRecordVo.Builder builderForValue) {
      if (atkRecordBuilder_ == null) {
        ensureAtkRecordIsMutable();
        atkRecord_.set(index, builderForValue.build());
        onChanged();
      } else {
        atkRecordBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public Builder addAtkRecord(pb4server.AtkRecordVo value) {
      if (atkRecordBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAtkRecordIsMutable();
        atkRecord_.add(value);
        onChanged();
      } else {
        atkRecordBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public Builder addAtkRecord(
        int index, pb4server.AtkRecordVo value) {
      if (atkRecordBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAtkRecordIsMutable();
        atkRecord_.add(index, value);
        onChanged();
      } else {
        atkRecordBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public Builder addAtkRecord(
        pb4server.AtkRecordVo.Builder builderForValue) {
      if (atkRecordBuilder_ == null) {
        ensureAtkRecordIsMutable();
        atkRecord_.add(builderForValue.build());
        onChanged();
      } else {
        atkRecordBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public Builder addAtkRecord(
        int index, pb4server.AtkRecordVo.Builder builderForValue) {
      if (atkRecordBuilder_ == null) {
        ensureAtkRecordIsMutable();
        atkRecord_.add(index, builderForValue.build());
        onChanged();
      } else {
        atkRecordBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public Builder addAllAtkRecord(
        java.lang.Iterable<? extends pb4server.AtkRecordVo> values) {
      if (atkRecordBuilder_ == null) {
        ensureAtkRecordIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, atkRecord_);
        onChanged();
      } else {
        atkRecordBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public Builder clearAtkRecord() {
      if (atkRecordBuilder_ == null) {
        atkRecord_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000010);
        onChanged();
      } else {
        atkRecordBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public Builder removeAtkRecord(int index) {
      if (atkRecordBuilder_ == null) {
        ensureAtkRecordIsMutable();
        atkRecord_.remove(index);
        onChanged();
      } else {
        atkRecordBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public pb4server.AtkRecordVo.Builder getAtkRecordBuilder(
        int index) {
      return getAtkRecordFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public pb4server.AtkRecordVoOrBuilder getAtkRecordOrBuilder(
        int index) {
      if (atkRecordBuilder_ == null) {
        return atkRecord_.get(index);  } else {
        return atkRecordBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public java.util.List<? extends pb4server.AtkRecordVoOrBuilder> 
         getAtkRecordOrBuilderList() {
      if (atkRecordBuilder_ != null) {
        return atkRecordBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(atkRecord_);
      }
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public pb4server.AtkRecordVo.Builder addAtkRecordBuilder() {
      return getAtkRecordFieldBuilder().addBuilder(
          pb4server.AtkRecordVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public pb4server.AtkRecordVo.Builder addAtkRecordBuilder(
        int index) {
      return getAtkRecordFieldBuilder().addBuilder(
          index, pb4server.AtkRecordVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.AtkRecordVo atkRecord = 5;</code>
     */
    public java.util.List<pb4server.AtkRecordVo.Builder> 
         getAtkRecordBuilderList() {
      return getAtkRecordFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.AtkRecordVo, pb4server.AtkRecordVo.Builder, pb4server.AtkRecordVoOrBuilder> 
        getAtkRecordFieldBuilder() {
      if (atkRecordBuilder_ == null) {
        atkRecordBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.AtkRecordVo, pb4server.AtkRecordVo.Builder, pb4server.AtkRecordVoOrBuilder>(
                atkRecord_,
                ((bitField0_ & 0x00000010) == 0x00000010),
                getParentForChildren(),
                isClean());
        atkRecord_ = null;
      }
      return atkRecordBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.HunterInviteAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.HunterInviteAskReq)
  private static final pb4server.HunterInviteAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.HunterInviteAskReq();
  }

  public static pb4server.HunterInviteAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<HunterInviteAskReq>
      PARSER = new com.google.protobuf.AbstractParser<HunterInviteAskReq>() {
    public HunterInviteAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new HunterInviteAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HunterInviteAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<HunterInviteAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.HunterInviteAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

