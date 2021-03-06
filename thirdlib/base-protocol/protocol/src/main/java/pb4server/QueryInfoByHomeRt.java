// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.QueryInfoByHomeRt}
 */
public  final class QueryInfoByHomeRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.QueryInfoByHomeRt)
    QueryInfoByHomeRtOrBuilder {
  // Use QueryInfoByHomeRt.newBuilder() to construct.
  private QueryInfoByHomeRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryInfoByHomeRt() {
    rt_ = 0;
    bagInfo_ = java.util.Collections.emptyList();
    mainHeroPrisonPlayerId_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private QueryInfoByHomeRt(
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
            pb4server.PlayerInFo.Builder subBuilder = null;
            if (playerInfo_ != null) {
              subBuilder = playerInfo_.toBuilder();
            }
            playerInfo_ = input.readMessage(pb4server.PlayerInFo.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(playerInfo_);
              playerInfo_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              bagInfo_ = new java.util.ArrayList<pb4server.BagInfo>();
              mutable_bitField0_ |= 0x00000004;
            }
            bagInfo_.add(
                input.readMessage(pb4server.BagInfo.parser(), extensionRegistry));
            break;
          }
          case 32: {

            mainHeroPrisonPlayerId_ = input.readInt64();
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
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        bagInfo_ = java.util.Collections.unmodifiableList(bagInfo_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalHkt.internal_static_pb4server_QueryInfoByHomeRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_QueryInfoByHomeRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.QueryInfoByHomeRt.class, pb4server.QueryInfoByHomeRt.Builder.class);
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

  public static final int PLAYERINFO_FIELD_NUMBER = 2;
  private pb4server.PlayerInFo playerInfo_;
  /**
   * <code>.pb4server.PlayerInFo playerInfo = 2;</code>
   */
  public boolean hasPlayerInfo() {
    return playerInfo_ != null;
  }
  /**
   * <code>.pb4server.PlayerInFo playerInfo = 2;</code>
   */
  public pb4server.PlayerInFo getPlayerInfo() {
    return playerInfo_ == null ? pb4server.PlayerInFo.getDefaultInstance() : playerInfo_;
  }
  /**
   * <code>.pb4server.PlayerInFo playerInfo = 2;</code>
   */
  public pb4server.PlayerInFoOrBuilder getPlayerInfoOrBuilder() {
    return getPlayerInfo();
  }

  public static final int BAGINFO_FIELD_NUMBER = 3;
  private java.util.List<pb4server.BagInfo> bagInfo_;
  /**
   * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
   */
  public java.util.List<pb4server.BagInfo> getBagInfoList() {
    return bagInfo_;
  }
  /**
   * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
   */
  public java.util.List<? extends pb4server.BagInfoOrBuilder> 
      getBagInfoOrBuilderList() {
    return bagInfo_;
  }
  /**
   * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
   */
  public int getBagInfoCount() {
    return bagInfo_.size();
  }
  /**
   * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
   */
  public pb4server.BagInfo getBagInfo(int index) {
    return bagInfo_.get(index);
  }
  /**
   * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
   */
  public pb4server.BagInfoOrBuilder getBagInfoOrBuilder(
      int index) {
    return bagInfo_.get(index);
  }

  public static final int MAINHEROPRISONPLAYERID_FIELD_NUMBER = 4;
  private long mainHeroPrisonPlayerId_;
  /**
   * <code>int64 mainHeroPrisonPlayerId = 4;</code>
   */
  public long getMainHeroPrisonPlayerId() {
    return mainHeroPrisonPlayerId_;
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
    if (playerInfo_ != null) {
      output.writeMessage(2, getPlayerInfo());
    }
    for (int i = 0; i < bagInfo_.size(); i++) {
      output.writeMessage(3, bagInfo_.get(i));
    }
    if (mainHeroPrisonPlayerId_ != 0L) {
      output.writeInt64(4, mainHeroPrisonPlayerId_);
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
    if (playerInfo_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getPlayerInfo());
    }
    for (int i = 0; i < bagInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, bagInfo_.get(i));
    }
    if (mainHeroPrisonPlayerId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, mainHeroPrisonPlayerId_);
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
    if (!(obj instanceof pb4server.QueryInfoByHomeRt)) {
      return super.equals(obj);
    }
    pb4server.QueryInfoByHomeRt other = (pb4server.QueryInfoByHomeRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && (hasPlayerInfo() == other.hasPlayerInfo());
    if (hasPlayerInfo()) {
      result = result && getPlayerInfo()
          .equals(other.getPlayerInfo());
    }
    result = result && getBagInfoList()
        .equals(other.getBagInfoList());
    result = result && (getMainHeroPrisonPlayerId()
        == other.getMainHeroPrisonPlayerId());
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
    if (hasPlayerInfo()) {
      hash = (37 * hash) + PLAYERINFO_FIELD_NUMBER;
      hash = (53 * hash) + getPlayerInfo().hashCode();
    }
    if (getBagInfoCount() > 0) {
      hash = (37 * hash) + BAGINFO_FIELD_NUMBER;
      hash = (53 * hash) + getBagInfoList().hashCode();
    }
    hash = (37 * hash) + MAINHEROPRISONPLAYERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getMainHeroPrisonPlayerId());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.QueryInfoByHomeRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryInfoByHomeRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryInfoByHomeRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryInfoByHomeRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryInfoByHomeRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.QueryInfoByHomeRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.QueryInfoByHomeRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryInfoByHomeRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryInfoByHomeRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.QueryInfoByHomeRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.QueryInfoByHomeRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.QueryInfoByHomeRt parseFrom(
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
  public static Builder newBuilder(pb4server.QueryInfoByHomeRt prototype) {
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
   * Protobuf type {@code pb4server.QueryInfoByHomeRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.QueryInfoByHomeRt)
      pb4server.QueryInfoByHomeRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_QueryInfoByHomeRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_QueryInfoByHomeRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.QueryInfoByHomeRt.class, pb4server.QueryInfoByHomeRt.Builder.class);
    }

    // Construct using pb4server.QueryInfoByHomeRt.newBuilder()
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
        getBagInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;

      if (playerInfoBuilder_ == null) {
        playerInfo_ = null;
      } else {
        playerInfo_ = null;
        playerInfoBuilder_ = null;
      }
      if (bagInfoBuilder_ == null) {
        bagInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        bagInfoBuilder_.clear();
      }
      mainHeroPrisonPlayerId_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_QueryInfoByHomeRt_descriptor;
    }

    public pb4server.QueryInfoByHomeRt getDefaultInstanceForType() {
      return pb4server.QueryInfoByHomeRt.getDefaultInstance();
    }

    public pb4server.QueryInfoByHomeRt build() {
      pb4server.QueryInfoByHomeRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.QueryInfoByHomeRt buildPartial() {
      pb4server.QueryInfoByHomeRt result = new pb4server.QueryInfoByHomeRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.rt_ = rt_;
      if (playerInfoBuilder_ == null) {
        result.playerInfo_ = playerInfo_;
      } else {
        result.playerInfo_ = playerInfoBuilder_.build();
      }
      if (bagInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          bagInfo_ = java.util.Collections.unmodifiableList(bagInfo_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.bagInfo_ = bagInfo_;
      } else {
        result.bagInfo_ = bagInfoBuilder_.build();
      }
      result.mainHeroPrisonPlayerId_ = mainHeroPrisonPlayerId_;
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
      if (other instanceof pb4server.QueryInfoByHomeRt) {
        return mergeFrom((pb4server.QueryInfoByHomeRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.QueryInfoByHomeRt other) {
      if (other == pb4server.QueryInfoByHomeRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (other.hasPlayerInfo()) {
        mergePlayerInfo(other.getPlayerInfo());
      }
      if (bagInfoBuilder_ == null) {
        if (!other.bagInfo_.isEmpty()) {
          if (bagInfo_.isEmpty()) {
            bagInfo_ = other.bagInfo_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureBagInfoIsMutable();
            bagInfo_.addAll(other.bagInfo_);
          }
          onChanged();
        }
      } else {
        if (!other.bagInfo_.isEmpty()) {
          if (bagInfoBuilder_.isEmpty()) {
            bagInfoBuilder_.dispose();
            bagInfoBuilder_ = null;
            bagInfo_ = other.bagInfo_;
            bitField0_ = (bitField0_ & ~0x00000004);
            bagInfoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getBagInfoFieldBuilder() : null;
          } else {
            bagInfoBuilder_.addAllMessages(other.bagInfo_);
          }
        }
      }
      if (other.getMainHeroPrisonPlayerId() != 0L) {
        setMainHeroPrisonPlayerId(other.getMainHeroPrisonPlayerId());
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
      pb4server.QueryInfoByHomeRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.QueryInfoByHomeRt) e.getUnfinishedMessage();
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

    private pb4server.PlayerInFo playerInfo_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.PlayerInFo, pb4server.PlayerInFo.Builder, pb4server.PlayerInFoOrBuilder> playerInfoBuilder_;
    /**
     * <code>.pb4server.PlayerInFo playerInfo = 2;</code>
     */
    public boolean hasPlayerInfo() {
      return playerInfoBuilder_ != null || playerInfo_ != null;
    }
    /**
     * <code>.pb4server.PlayerInFo playerInfo = 2;</code>
     */
    public pb4server.PlayerInFo getPlayerInfo() {
      if (playerInfoBuilder_ == null) {
        return playerInfo_ == null ? pb4server.PlayerInFo.getDefaultInstance() : playerInfo_;
      } else {
        return playerInfoBuilder_.getMessage();
      }
    }
    /**
     * <code>.pb4server.PlayerInFo playerInfo = 2;</code>
     */
    public Builder setPlayerInfo(pb4server.PlayerInFo value) {
      if (playerInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        playerInfo_ = value;
        onChanged();
      } else {
        playerInfoBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.pb4server.PlayerInFo playerInfo = 2;</code>
     */
    public Builder setPlayerInfo(
        pb4server.PlayerInFo.Builder builderForValue) {
      if (playerInfoBuilder_ == null) {
        playerInfo_ = builderForValue.build();
        onChanged();
      } else {
        playerInfoBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.pb4server.PlayerInFo playerInfo = 2;</code>
     */
    public Builder mergePlayerInfo(pb4server.PlayerInFo value) {
      if (playerInfoBuilder_ == null) {
        if (playerInfo_ != null) {
          playerInfo_ =
            pb4server.PlayerInFo.newBuilder(playerInfo_).mergeFrom(value).buildPartial();
        } else {
          playerInfo_ = value;
        }
        onChanged();
      } else {
        playerInfoBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.pb4server.PlayerInFo playerInfo = 2;</code>
     */
    public Builder clearPlayerInfo() {
      if (playerInfoBuilder_ == null) {
        playerInfo_ = null;
        onChanged();
      } else {
        playerInfo_ = null;
        playerInfoBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.pb4server.PlayerInFo playerInfo = 2;</code>
     */
    public pb4server.PlayerInFo.Builder getPlayerInfoBuilder() {
      
      onChanged();
      return getPlayerInfoFieldBuilder().getBuilder();
    }
    /**
     * <code>.pb4server.PlayerInFo playerInfo = 2;</code>
     */
    public pb4server.PlayerInFoOrBuilder getPlayerInfoOrBuilder() {
      if (playerInfoBuilder_ != null) {
        return playerInfoBuilder_.getMessageOrBuilder();
      } else {
        return playerInfo_ == null ?
            pb4server.PlayerInFo.getDefaultInstance() : playerInfo_;
      }
    }
    /**
     * <code>.pb4server.PlayerInFo playerInfo = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.PlayerInFo, pb4server.PlayerInFo.Builder, pb4server.PlayerInFoOrBuilder> 
        getPlayerInfoFieldBuilder() {
      if (playerInfoBuilder_ == null) {
        playerInfoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4server.PlayerInFo, pb4server.PlayerInFo.Builder, pb4server.PlayerInFoOrBuilder>(
                getPlayerInfo(),
                getParentForChildren(),
                isClean());
        playerInfo_ = null;
      }
      return playerInfoBuilder_;
    }

    private java.util.List<pb4server.BagInfo> bagInfo_ =
      java.util.Collections.emptyList();
    private void ensureBagInfoIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        bagInfo_ = new java.util.ArrayList<pb4server.BagInfo>(bagInfo_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.BagInfo, pb4server.BagInfo.Builder, pb4server.BagInfoOrBuilder> bagInfoBuilder_;

    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public java.util.List<pb4server.BagInfo> getBagInfoList() {
      if (bagInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(bagInfo_);
      } else {
        return bagInfoBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public int getBagInfoCount() {
      if (bagInfoBuilder_ == null) {
        return bagInfo_.size();
      } else {
        return bagInfoBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public pb4server.BagInfo getBagInfo(int index) {
      if (bagInfoBuilder_ == null) {
        return bagInfo_.get(index);
      } else {
        return bagInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public Builder setBagInfo(
        int index, pb4server.BagInfo value) {
      if (bagInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBagInfoIsMutable();
        bagInfo_.set(index, value);
        onChanged();
      } else {
        bagInfoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public Builder setBagInfo(
        int index, pb4server.BagInfo.Builder builderForValue) {
      if (bagInfoBuilder_ == null) {
        ensureBagInfoIsMutable();
        bagInfo_.set(index, builderForValue.build());
        onChanged();
      } else {
        bagInfoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public Builder addBagInfo(pb4server.BagInfo value) {
      if (bagInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBagInfoIsMutable();
        bagInfo_.add(value);
        onChanged();
      } else {
        bagInfoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public Builder addBagInfo(
        int index, pb4server.BagInfo value) {
      if (bagInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBagInfoIsMutable();
        bagInfo_.add(index, value);
        onChanged();
      } else {
        bagInfoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public Builder addBagInfo(
        pb4server.BagInfo.Builder builderForValue) {
      if (bagInfoBuilder_ == null) {
        ensureBagInfoIsMutable();
        bagInfo_.add(builderForValue.build());
        onChanged();
      } else {
        bagInfoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public Builder addBagInfo(
        int index, pb4server.BagInfo.Builder builderForValue) {
      if (bagInfoBuilder_ == null) {
        ensureBagInfoIsMutable();
        bagInfo_.add(index, builderForValue.build());
        onChanged();
      } else {
        bagInfoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public Builder addAllBagInfo(
        java.lang.Iterable<? extends pb4server.BagInfo> values) {
      if (bagInfoBuilder_ == null) {
        ensureBagInfoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, bagInfo_);
        onChanged();
      } else {
        bagInfoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public Builder clearBagInfo() {
      if (bagInfoBuilder_ == null) {
        bagInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        bagInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public Builder removeBagInfo(int index) {
      if (bagInfoBuilder_ == null) {
        ensureBagInfoIsMutable();
        bagInfo_.remove(index);
        onChanged();
      } else {
        bagInfoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public pb4server.BagInfo.Builder getBagInfoBuilder(
        int index) {
      return getBagInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public pb4server.BagInfoOrBuilder getBagInfoOrBuilder(
        int index) {
      if (bagInfoBuilder_ == null) {
        return bagInfo_.get(index);  } else {
        return bagInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public java.util.List<? extends pb4server.BagInfoOrBuilder> 
         getBagInfoOrBuilderList() {
      if (bagInfoBuilder_ != null) {
        return bagInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(bagInfo_);
      }
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public pb4server.BagInfo.Builder addBagInfoBuilder() {
      return getBagInfoFieldBuilder().addBuilder(
          pb4server.BagInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public pb4server.BagInfo.Builder addBagInfoBuilder(
        int index) {
      return getBagInfoFieldBuilder().addBuilder(
          index, pb4server.BagInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.BagInfo bagInfo = 3;</code>
     */
    public java.util.List<pb4server.BagInfo.Builder> 
         getBagInfoBuilderList() {
      return getBagInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.BagInfo, pb4server.BagInfo.Builder, pb4server.BagInfoOrBuilder> 
        getBagInfoFieldBuilder() {
      if (bagInfoBuilder_ == null) {
        bagInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.BagInfo, pb4server.BagInfo.Builder, pb4server.BagInfoOrBuilder>(
                bagInfo_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        bagInfo_ = null;
      }
      return bagInfoBuilder_;
    }

    private long mainHeroPrisonPlayerId_ ;
    /**
     * <code>int64 mainHeroPrisonPlayerId = 4;</code>
     */
    public long getMainHeroPrisonPlayerId() {
      return mainHeroPrisonPlayerId_;
    }
    /**
     * <code>int64 mainHeroPrisonPlayerId = 4;</code>
     */
    public Builder setMainHeroPrisonPlayerId(long value) {
      
      mainHeroPrisonPlayerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 mainHeroPrisonPlayerId = 4;</code>
     */
    public Builder clearMainHeroPrisonPlayerId() {
      
      mainHeroPrisonPlayerId_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:pb4server.QueryInfoByHomeRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.QueryInfoByHomeRt)
  private static final pb4server.QueryInfoByHomeRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.QueryInfoByHomeRt();
  }

  public static pb4server.QueryInfoByHomeRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueryInfoByHomeRt>
      PARSER = new com.google.protobuf.AbstractParser<QueryInfoByHomeRt>() {
    public QueryInfoByHomeRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryInfoByHomeRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryInfoByHomeRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryInfoByHomeRt> getParserForType() {
    return PARSER;
  }

  public pb4server.QueryInfoByHomeRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

