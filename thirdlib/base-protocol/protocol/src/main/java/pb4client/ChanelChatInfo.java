// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3077
 * 服务器 -&gt; 客户端
 * 历史聊天消息主推
 * </pre>
 *
 * Protobuf type {@code client2server.ChanelChatInfo}
 */
public  final class ChanelChatInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.ChanelChatInfo)
    ChanelChatInfoOrBuilder {
  // Use ChanelChatInfo.newBuilder() to construct.
  private ChanelChatInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ChanelChatInfo() {
    chatInfos_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ChanelChatInfo(
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              chatInfos_ = new java.util.ArrayList<pb4client.ChatInfo>();
              mutable_bitField0_ |= 0x00000001;
            }
            chatInfos_.add(
                input.readMessage(pb4client.ChatInfo.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        chatInfos_ = java.util.Collections.unmodifiableList(chatInfos_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_ChanelChatInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_ChanelChatInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.ChanelChatInfo.class, pb4client.ChanelChatInfo.Builder.class);
  }

  public static final int CHATINFOS_FIELD_NUMBER = 1;
  private java.util.List<pb4client.ChatInfo> chatInfos_;
  /**
   * <pre>
   *频道历史消息
   * </pre>
   *
   * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
   */
  public java.util.List<pb4client.ChatInfo> getChatInfosList() {
    return chatInfos_;
  }
  /**
   * <pre>
   *频道历史消息
   * </pre>
   *
   * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
   */
  public java.util.List<? extends pb4client.ChatInfoOrBuilder> 
      getChatInfosOrBuilderList() {
    return chatInfos_;
  }
  /**
   * <pre>
   *频道历史消息
   * </pre>
   *
   * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
   */
  public int getChatInfosCount() {
    return chatInfos_.size();
  }
  /**
   * <pre>
   *频道历史消息
   * </pre>
   *
   * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
   */
  public pb4client.ChatInfo getChatInfos(int index) {
    return chatInfos_.get(index);
  }
  /**
   * <pre>
   *频道历史消息
   * </pre>
   *
   * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
   */
  public pb4client.ChatInfoOrBuilder getChatInfosOrBuilder(
      int index) {
    return chatInfos_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    for (int i = 0; i < getChatInfosCount(); i++) {
      if (!getChatInfos(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < chatInfos_.size(); i++) {
      output.writeMessage(1, chatInfos_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < chatInfos_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, chatInfos_.get(i));
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
    if (!(obj instanceof pb4client.ChanelChatInfo)) {
      return super.equals(obj);
    }
    pb4client.ChanelChatInfo other = (pb4client.ChanelChatInfo) obj;

    boolean result = true;
    result = result && getChatInfosList()
        .equals(other.getChatInfosList());
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
    if (getChatInfosCount() > 0) {
      hash = (37 * hash) + CHATINFOS_FIELD_NUMBER;
      hash = (53 * hash) + getChatInfosList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.ChanelChatInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ChanelChatInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ChanelChatInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ChanelChatInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ChanelChatInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ChanelChatInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ChanelChatInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ChanelChatInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ChanelChatInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.ChanelChatInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ChanelChatInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ChanelChatInfo parseFrom(
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
  public static Builder newBuilder(pb4client.ChanelChatInfo prototype) {
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
   * msgType = 3077
   * 服务器 -&gt; 客户端
   * 历史聊天消息主推
   * </pre>
   *
   * Protobuf type {@code client2server.ChanelChatInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.ChanelChatInfo)
      pb4client.ChanelChatInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_ChanelChatInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_ChanelChatInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.ChanelChatInfo.class, pb4client.ChanelChatInfo.Builder.class);
    }

    // Construct using pb4client.ChanelChatInfo.newBuilder()
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
        getChatInfosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (chatInfosBuilder_ == null) {
        chatInfos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        chatInfosBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_ChanelChatInfo_descriptor;
    }

    public pb4client.ChanelChatInfo getDefaultInstanceForType() {
      return pb4client.ChanelChatInfo.getDefaultInstance();
    }

    public pb4client.ChanelChatInfo build() {
      pb4client.ChanelChatInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.ChanelChatInfo buildPartial() {
      pb4client.ChanelChatInfo result = new pb4client.ChanelChatInfo(this);
      int from_bitField0_ = bitField0_;
      if (chatInfosBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          chatInfos_ = java.util.Collections.unmodifiableList(chatInfos_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.chatInfos_ = chatInfos_;
      } else {
        result.chatInfos_ = chatInfosBuilder_.build();
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
      if (other instanceof pb4client.ChanelChatInfo) {
        return mergeFrom((pb4client.ChanelChatInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.ChanelChatInfo other) {
      if (other == pb4client.ChanelChatInfo.getDefaultInstance()) return this;
      if (chatInfosBuilder_ == null) {
        if (!other.chatInfos_.isEmpty()) {
          if (chatInfos_.isEmpty()) {
            chatInfos_ = other.chatInfos_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureChatInfosIsMutable();
            chatInfos_.addAll(other.chatInfos_);
          }
          onChanged();
        }
      } else {
        if (!other.chatInfos_.isEmpty()) {
          if (chatInfosBuilder_.isEmpty()) {
            chatInfosBuilder_.dispose();
            chatInfosBuilder_ = null;
            chatInfos_ = other.chatInfos_;
            bitField0_ = (bitField0_ & ~0x00000001);
            chatInfosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getChatInfosFieldBuilder() : null;
          } else {
            chatInfosBuilder_.addAllMessages(other.chatInfos_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      for (int i = 0; i < getChatInfosCount(); i++) {
        if (!getChatInfos(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.ChanelChatInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.ChanelChatInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4client.ChatInfo> chatInfos_ =
      java.util.Collections.emptyList();
    private void ensureChatInfosIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        chatInfos_ = new java.util.ArrayList<pb4client.ChatInfo>(chatInfos_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.ChatInfo, pb4client.ChatInfo.Builder, pb4client.ChatInfoOrBuilder> chatInfosBuilder_;

    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public java.util.List<pb4client.ChatInfo> getChatInfosList() {
      if (chatInfosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(chatInfos_);
      } else {
        return chatInfosBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public int getChatInfosCount() {
      if (chatInfosBuilder_ == null) {
        return chatInfos_.size();
      } else {
        return chatInfosBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public pb4client.ChatInfo getChatInfos(int index) {
      if (chatInfosBuilder_ == null) {
        return chatInfos_.get(index);
      } else {
        return chatInfosBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public Builder setChatInfos(
        int index, pb4client.ChatInfo value) {
      if (chatInfosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureChatInfosIsMutable();
        chatInfos_.set(index, value);
        onChanged();
      } else {
        chatInfosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public Builder setChatInfos(
        int index, pb4client.ChatInfo.Builder builderForValue) {
      if (chatInfosBuilder_ == null) {
        ensureChatInfosIsMutable();
        chatInfos_.set(index, builderForValue.build());
        onChanged();
      } else {
        chatInfosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public Builder addChatInfos(pb4client.ChatInfo value) {
      if (chatInfosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureChatInfosIsMutable();
        chatInfos_.add(value);
        onChanged();
      } else {
        chatInfosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public Builder addChatInfos(
        int index, pb4client.ChatInfo value) {
      if (chatInfosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureChatInfosIsMutable();
        chatInfos_.add(index, value);
        onChanged();
      } else {
        chatInfosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public Builder addChatInfos(
        pb4client.ChatInfo.Builder builderForValue) {
      if (chatInfosBuilder_ == null) {
        ensureChatInfosIsMutable();
        chatInfos_.add(builderForValue.build());
        onChanged();
      } else {
        chatInfosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public Builder addChatInfos(
        int index, pb4client.ChatInfo.Builder builderForValue) {
      if (chatInfosBuilder_ == null) {
        ensureChatInfosIsMutable();
        chatInfos_.add(index, builderForValue.build());
        onChanged();
      } else {
        chatInfosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public Builder addAllChatInfos(
        java.lang.Iterable<? extends pb4client.ChatInfo> values) {
      if (chatInfosBuilder_ == null) {
        ensureChatInfosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, chatInfos_);
        onChanged();
      } else {
        chatInfosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public Builder clearChatInfos() {
      if (chatInfosBuilder_ == null) {
        chatInfos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        chatInfosBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public Builder removeChatInfos(int index) {
      if (chatInfosBuilder_ == null) {
        ensureChatInfosIsMutable();
        chatInfos_.remove(index);
        onChanged();
      } else {
        chatInfosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public pb4client.ChatInfo.Builder getChatInfosBuilder(
        int index) {
      return getChatInfosFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public pb4client.ChatInfoOrBuilder getChatInfosOrBuilder(
        int index) {
      if (chatInfosBuilder_ == null) {
        return chatInfos_.get(index);  } else {
        return chatInfosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public java.util.List<? extends pb4client.ChatInfoOrBuilder> 
         getChatInfosOrBuilderList() {
      if (chatInfosBuilder_ != null) {
        return chatInfosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(chatInfos_);
      }
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public pb4client.ChatInfo.Builder addChatInfosBuilder() {
      return getChatInfosFieldBuilder().addBuilder(
          pb4client.ChatInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public pb4client.ChatInfo.Builder addChatInfosBuilder(
        int index) {
      return getChatInfosFieldBuilder().addBuilder(
          index, pb4client.ChatInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *频道历史消息
     * </pre>
     *
     * <code>repeated .client2server.ChatInfo chatInfos = 1;</code>
     */
    public java.util.List<pb4client.ChatInfo.Builder> 
         getChatInfosBuilderList() {
      return getChatInfosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.ChatInfo, pb4client.ChatInfo.Builder, pb4client.ChatInfoOrBuilder> 
        getChatInfosFieldBuilder() {
      if (chatInfosBuilder_ == null) {
        chatInfosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.ChatInfo, pb4client.ChatInfo.Builder, pb4client.ChatInfoOrBuilder>(
                chatInfos_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        chatInfos_ = null;
      }
      return chatInfosBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.ChanelChatInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.ChanelChatInfo)
  private static final pb4client.ChanelChatInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.ChanelChatInfo();
  }

  public static pb4client.ChanelChatInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ChanelChatInfo>
      PARSER = new com.google.protobuf.AbstractParser<ChanelChatInfo>() {
    public ChanelChatInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ChanelChatInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ChanelChatInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ChanelChatInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.ChanelChatInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
