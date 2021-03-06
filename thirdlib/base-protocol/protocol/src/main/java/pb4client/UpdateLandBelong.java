// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3014
 * 服务器 -&gt; 客户端
 * 土地归属发生变化主推
 * </pre>
 *
 * Protobuf type {@code client2server.UpdateLandBelong}
 */
public  final class UpdateLandBelong extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.UpdateLandBelong)
    UpdateLandBelongOrBuilder {
  // Use UpdateLandBelong.newBuilder() to construct.
  private UpdateLandBelong(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UpdateLandBelong() {
    lands_ = java.util.Collections.emptyList();
    dels_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UpdateLandBelong(
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
              lands_ = new java.util.ArrayList<pb4client.NewEveryLandInfo>();
              mutable_bitField0_ |= 0x00000001;
            }
            lands_.add(
                input.readMessage(pb4client.NewEveryLandInfo.PARSER, extensionRegistry));
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              dels_ = new java.util.ArrayList<pb4client.CellPoint>();
              mutable_bitField0_ |= 0x00000002;
            }
            dels_.add(
                input.readMessage(pb4client.CellPoint.PARSER, extensionRegistry));
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
        lands_ = java.util.Collections.unmodifiableList(lands_);
      }
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        dels_ = java.util.Collections.unmodifiableList(dels_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_UpdateLandBelong_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_UpdateLandBelong_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.UpdateLandBelong.class, pb4client.UpdateLandBelong.Builder.class);
  }

  public static final int LANDS_FIELD_NUMBER = 1;
  private java.util.List<pb4client.NewEveryLandInfo> lands_;
  /**
   * <pre>
   *格子信息
   * </pre>
   *
   * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
   */
  public java.util.List<pb4client.NewEveryLandInfo> getLandsList() {
    return lands_;
  }
  /**
   * <pre>
   *格子信息
   * </pre>
   *
   * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
   */
  public java.util.List<? extends pb4client.NewEveryLandInfoOrBuilder> 
      getLandsOrBuilderList() {
    return lands_;
  }
  /**
   * <pre>
   *格子信息
   * </pre>
   *
   * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
   */
  public int getLandsCount() {
    return lands_.size();
  }
  /**
   * <pre>
   *格子信息
   * </pre>
   *
   * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
   */
  public pb4client.NewEveryLandInfo getLands(int index) {
    return lands_.get(index);
  }
  /**
   * <pre>
   *格子信息
   * </pre>
   *
   * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
   */
  public pb4client.NewEveryLandInfoOrBuilder getLandsOrBuilder(
      int index) {
    return lands_.get(index);
  }

  public static final int DELS_FIELD_NUMBER = 2;
  private java.util.List<pb4client.CellPoint> dels_;
  /**
   * <pre>
   * 需要恢复成原始状态的地块坐标
   * </pre>
   *
   * <code>repeated .client2server.CellPoint dels = 2;</code>
   */
  public java.util.List<pb4client.CellPoint> getDelsList() {
    return dels_;
  }
  /**
   * <pre>
   * 需要恢复成原始状态的地块坐标
   * </pre>
   *
   * <code>repeated .client2server.CellPoint dels = 2;</code>
   */
  public java.util.List<? extends pb4client.CellPointOrBuilder> 
      getDelsOrBuilderList() {
    return dels_;
  }
  /**
   * <pre>
   * 需要恢复成原始状态的地块坐标
   * </pre>
   *
   * <code>repeated .client2server.CellPoint dels = 2;</code>
   */
  public int getDelsCount() {
    return dels_.size();
  }
  /**
   * <pre>
   * 需要恢复成原始状态的地块坐标
   * </pre>
   *
   * <code>repeated .client2server.CellPoint dels = 2;</code>
   */
  public pb4client.CellPoint getDels(int index) {
    return dels_.get(index);
  }
  /**
   * <pre>
   * 需要恢复成原始状态的地块坐标
   * </pre>
   *
   * <code>repeated .client2server.CellPoint dels = 2;</code>
   */
  public pb4client.CellPointOrBuilder getDelsOrBuilder(
      int index) {
    return dels_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    for (int i = 0; i < getLandsCount(); i++) {
      if (!getLands(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    for (int i = 0; i < getDelsCount(); i++) {
      if (!getDels(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < lands_.size(); i++) {
      output.writeMessage(1, lands_.get(i));
    }
    for (int i = 0; i < dels_.size(); i++) {
      output.writeMessage(2, dels_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < lands_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, lands_.get(i));
    }
    for (int i = 0; i < dels_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, dels_.get(i));
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
    if (!(obj instanceof pb4client.UpdateLandBelong)) {
      return super.equals(obj);
    }
    pb4client.UpdateLandBelong other = (pb4client.UpdateLandBelong) obj;

    boolean result = true;
    result = result && getLandsList()
        .equals(other.getLandsList());
    result = result && getDelsList()
        .equals(other.getDelsList());
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
    if (getLandsCount() > 0) {
      hash = (37 * hash) + LANDS_FIELD_NUMBER;
      hash = (53 * hash) + getLandsList().hashCode();
    }
    if (getDelsCount() > 0) {
      hash = (37 * hash) + DELS_FIELD_NUMBER;
      hash = (53 * hash) + getDelsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.UpdateLandBelong parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.UpdateLandBelong parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.UpdateLandBelong parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.UpdateLandBelong parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.UpdateLandBelong parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.UpdateLandBelong parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.UpdateLandBelong parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.UpdateLandBelong parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.UpdateLandBelong parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.UpdateLandBelong parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.UpdateLandBelong parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.UpdateLandBelong parseFrom(
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
  public static Builder newBuilder(pb4client.UpdateLandBelong prototype) {
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
   * msgType = 3014
   * 服务器 -&gt; 客户端
   * 土地归属发生变化主推
   * </pre>
   *
   * Protobuf type {@code client2server.UpdateLandBelong}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.UpdateLandBelong)
      pb4client.UpdateLandBelongOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_UpdateLandBelong_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_UpdateLandBelong_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.UpdateLandBelong.class, pb4client.UpdateLandBelong.Builder.class);
    }

    // Construct using pb4client.UpdateLandBelong.newBuilder()
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
        getLandsFieldBuilder();
        getDelsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (landsBuilder_ == null) {
        lands_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        landsBuilder_.clear();
      }
      if (delsBuilder_ == null) {
        dels_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        delsBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_UpdateLandBelong_descriptor;
    }

    public pb4client.UpdateLandBelong getDefaultInstanceForType() {
      return pb4client.UpdateLandBelong.getDefaultInstance();
    }

    public pb4client.UpdateLandBelong build() {
      pb4client.UpdateLandBelong result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.UpdateLandBelong buildPartial() {
      pb4client.UpdateLandBelong result = new pb4client.UpdateLandBelong(this);
      int from_bitField0_ = bitField0_;
      if (landsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          lands_ = java.util.Collections.unmodifiableList(lands_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.lands_ = lands_;
      } else {
        result.lands_ = landsBuilder_.build();
      }
      if (delsBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          dels_ = java.util.Collections.unmodifiableList(dels_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.dels_ = dels_;
      } else {
        result.dels_ = delsBuilder_.build();
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
      if (other instanceof pb4client.UpdateLandBelong) {
        return mergeFrom((pb4client.UpdateLandBelong)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.UpdateLandBelong other) {
      if (other == pb4client.UpdateLandBelong.getDefaultInstance()) return this;
      if (landsBuilder_ == null) {
        if (!other.lands_.isEmpty()) {
          if (lands_.isEmpty()) {
            lands_ = other.lands_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureLandsIsMutable();
            lands_.addAll(other.lands_);
          }
          onChanged();
        }
      } else {
        if (!other.lands_.isEmpty()) {
          if (landsBuilder_.isEmpty()) {
            landsBuilder_.dispose();
            landsBuilder_ = null;
            lands_ = other.lands_;
            bitField0_ = (bitField0_ & ~0x00000001);
            landsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getLandsFieldBuilder() : null;
          } else {
            landsBuilder_.addAllMessages(other.lands_);
          }
        }
      }
      if (delsBuilder_ == null) {
        if (!other.dels_.isEmpty()) {
          if (dels_.isEmpty()) {
            dels_ = other.dels_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureDelsIsMutable();
            dels_.addAll(other.dels_);
          }
          onChanged();
        }
      } else {
        if (!other.dels_.isEmpty()) {
          if (delsBuilder_.isEmpty()) {
            delsBuilder_.dispose();
            delsBuilder_ = null;
            dels_ = other.dels_;
            bitField0_ = (bitField0_ & ~0x00000002);
            delsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getDelsFieldBuilder() : null;
          } else {
            delsBuilder_.addAllMessages(other.dels_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      for (int i = 0; i < getLandsCount(); i++) {
        if (!getLands(i).isInitialized()) {
          return false;
        }
      }
      for (int i = 0; i < getDelsCount(); i++) {
        if (!getDels(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.UpdateLandBelong parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.UpdateLandBelong) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4client.NewEveryLandInfo> lands_ =
      java.util.Collections.emptyList();
    private void ensureLandsIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        lands_ = new java.util.ArrayList<pb4client.NewEveryLandInfo>(lands_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.NewEveryLandInfo, pb4client.NewEveryLandInfo.Builder, pb4client.NewEveryLandInfoOrBuilder> landsBuilder_;

    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public java.util.List<pb4client.NewEveryLandInfo> getLandsList() {
      if (landsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(lands_);
      } else {
        return landsBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public int getLandsCount() {
      if (landsBuilder_ == null) {
        return lands_.size();
      } else {
        return landsBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public pb4client.NewEveryLandInfo getLands(int index) {
      if (landsBuilder_ == null) {
        return lands_.get(index);
      } else {
        return landsBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public Builder setLands(
        int index, pb4client.NewEveryLandInfo value) {
      if (landsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLandsIsMutable();
        lands_.set(index, value);
        onChanged();
      } else {
        landsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public Builder setLands(
        int index, pb4client.NewEveryLandInfo.Builder builderForValue) {
      if (landsBuilder_ == null) {
        ensureLandsIsMutable();
        lands_.set(index, builderForValue.build());
        onChanged();
      } else {
        landsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public Builder addLands(pb4client.NewEveryLandInfo value) {
      if (landsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLandsIsMutable();
        lands_.add(value);
        onChanged();
      } else {
        landsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public Builder addLands(
        int index, pb4client.NewEveryLandInfo value) {
      if (landsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLandsIsMutable();
        lands_.add(index, value);
        onChanged();
      } else {
        landsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public Builder addLands(
        pb4client.NewEveryLandInfo.Builder builderForValue) {
      if (landsBuilder_ == null) {
        ensureLandsIsMutable();
        lands_.add(builderForValue.build());
        onChanged();
      } else {
        landsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public Builder addLands(
        int index, pb4client.NewEveryLandInfo.Builder builderForValue) {
      if (landsBuilder_ == null) {
        ensureLandsIsMutable();
        lands_.add(index, builderForValue.build());
        onChanged();
      } else {
        landsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public Builder addAllLands(
        java.lang.Iterable<? extends pb4client.NewEveryLandInfo> values) {
      if (landsBuilder_ == null) {
        ensureLandsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, lands_);
        onChanged();
      } else {
        landsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public Builder clearLands() {
      if (landsBuilder_ == null) {
        lands_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        landsBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public Builder removeLands(int index) {
      if (landsBuilder_ == null) {
        ensureLandsIsMutable();
        lands_.remove(index);
        onChanged();
      } else {
        landsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public pb4client.NewEveryLandInfo.Builder getLandsBuilder(
        int index) {
      return getLandsFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public pb4client.NewEveryLandInfoOrBuilder getLandsOrBuilder(
        int index) {
      if (landsBuilder_ == null) {
        return lands_.get(index);  } else {
        return landsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public java.util.List<? extends pb4client.NewEveryLandInfoOrBuilder> 
         getLandsOrBuilderList() {
      if (landsBuilder_ != null) {
        return landsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(lands_);
      }
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public pb4client.NewEveryLandInfo.Builder addLandsBuilder() {
      return getLandsFieldBuilder().addBuilder(
          pb4client.NewEveryLandInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public pb4client.NewEveryLandInfo.Builder addLandsBuilder(
        int index) {
      return getLandsFieldBuilder().addBuilder(
          index, pb4client.NewEveryLandInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *格子信息
     * </pre>
     *
     * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
     */
    public java.util.List<pb4client.NewEveryLandInfo.Builder> 
         getLandsBuilderList() {
      return getLandsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.NewEveryLandInfo, pb4client.NewEveryLandInfo.Builder, pb4client.NewEveryLandInfoOrBuilder> 
        getLandsFieldBuilder() {
      if (landsBuilder_ == null) {
        landsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.NewEveryLandInfo, pb4client.NewEveryLandInfo.Builder, pb4client.NewEveryLandInfoOrBuilder>(
                lands_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        lands_ = null;
      }
      return landsBuilder_;
    }

    private java.util.List<pb4client.CellPoint> dels_ =
      java.util.Collections.emptyList();
    private void ensureDelsIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        dels_ = new java.util.ArrayList<pb4client.CellPoint>(dels_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.CellPoint, pb4client.CellPoint.Builder, pb4client.CellPointOrBuilder> delsBuilder_;

    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public java.util.List<pb4client.CellPoint> getDelsList() {
      if (delsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(dels_);
      } else {
        return delsBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public int getDelsCount() {
      if (delsBuilder_ == null) {
        return dels_.size();
      } else {
        return delsBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public pb4client.CellPoint getDels(int index) {
      if (delsBuilder_ == null) {
        return dels_.get(index);
      } else {
        return delsBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public Builder setDels(
        int index, pb4client.CellPoint value) {
      if (delsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDelsIsMutable();
        dels_.set(index, value);
        onChanged();
      } else {
        delsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public Builder setDels(
        int index, pb4client.CellPoint.Builder builderForValue) {
      if (delsBuilder_ == null) {
        ensureDelsIsMutable();
        dels_.set(index, builderForValue.build());
        onChanged();
      } else {
        delsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public Builder addDels(pb4client.CellPoint value) {
      if (delsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDelsIsMutable();
        dels_.add(value);
        onChanged();
      } else {
        delsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public Builder addDels(
        int index, pb4client.CellPoint value) {
      if (delsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDelsIsMutable();
        dels_.add(index, value);
        onChanged();
      } else {
        delsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public Builder addDels(
        pb4client.CellPoint.Builder builderForValue) {
      if (delsBuilder_ == null) {
        ensureDelsIsMutable();
        dels_.add(builderForValue.build());
        onChanged();
      } else {
        delsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public Builder addDels(
        int index, pb4client.CellPoint.Builder builderForValue) {
      if (delsBuilder_ == null) {
        ensureDelsIsMutable();
        dels_.add(index, builderForValue.build());
        onChanged();
      } else {
        delsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public Builder addAllDels(
        java.lang.Iterable<? extends pb4client.CellPoint> values) {
      if (delsBuilder_ == null) {
        ensureDelsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, dels_);
        onChanged();
      } else {
        delsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public Builder clearDels() {
      if (delsBuilder_ == null) {
        dels_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        delsBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public Builder removeDels(int index) {
      if (delsBuilder_ == null) {
        ensureDelsIsMutable();
        dels_.remove(index);
        onChanged();
      } else {
        delsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public pb4client.CellPoint.Builder getDelsBuilder(
        int index) {
      return getDelsFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public pb4client.CellPointOrBuilder getDelsOrBuilder(
        int index) {
      if (delsBuilder_ == null) {
        return dels_.get(index);  } else {
        return delsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public java.util.List<? extends pb4client.CellPointOrBuilder> 
         getDelsOrBuilderList() {
      if (delsBuilder_ != null) {
        return delsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(dels_);
      }
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public pb4client.CellPoint.Builder addDelsBuilder() {
      return getDelsFieldBuilder().addBuilder(
          pb4client.CellPoint.getDefaultInstance());
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public pb4client.CellPoint.Builder addDelsBuilder(
        int index) {
      return getDelsFieldBuilder().addBuilder(
          index, pb4client.CellPoint.getDefaultInstance());
    }
    /**
     * <pre>
     * 需要恢复成原始状态的地块坐标
     * </pre>
     *
     * <code>repeated .client2server.CellPoint dels = 2;</code>
     */
    public java.util.List<pb4client.CellPoint.Builder> 
         getDelsBuilderList() {
      return getDelsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.CellPoint, pb4client.CellPoint.Builder, pb4client.CellPointOrBuilder> 
        getDelsFieldBuilder() {
      if (delsBuilder_ == null) {
        delsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.CellPoint, pb4client.CellPoint.Builder, pb4client.CellPointOrBuilder>(
                dels_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        dels_ = null;
      }
      return delsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.UpdateLandBelong)
  }

  // @@protoc_insertion_point(class_scope:client2server.UpdateLandBelong)
  private static final pb4client.UpdateLandBelong DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.UpdateLandBelong();
  }

  public static pb4client.UpdateLandBelong getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<UpdateLandBelong>
      PARSER = new com.google.protobuf.AbstractParser<UpdateLandBelong>() {
    public UpdateLandBelong parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new UpdateLandBelong(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UpdateLandBelong> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UpdateLandBelong> getParserForType() {
    return PARSER;
  }

  public pb4client.UpdateLandBelong getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

