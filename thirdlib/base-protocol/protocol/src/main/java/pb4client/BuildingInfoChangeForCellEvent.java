// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3107
 * 地块上土地建造信息变化
 * </pre>
 *
 * Protobuf type {@code client2server.BuildingInfoChangeForCellEvent}
 */
public  final class BuildingInfoChangeForCellEvent extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.BuildingInfoChangeForCellEvent)
    BuildingInfoChangeForCellEventOrBuilder {
  // Use BuildingInfoChangeForCellEvent.newBuilder() to construct.
  private BuildingInfoChangeForCellEvent(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BuildingInfoChangeForCellEvent() {
    buildInfoForCellEvent_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private BuildingInfoChangeForCellEvent(
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
              buildInfoForCellEvent_ = new java.util.ArrayList<pb4client.BuildInfoForCellEvent>();
              mutable_bitField0_ |= 0x00000001;
            }
            buildInfoForCellEvent_.add(
                input.readMessage(pb4client.BuildInfoForCellEvent.PARSER, extensionRegistry));
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
        buildInfoForCellEvent_ = java.util.Collections.unmodifiableList(buildInfoForCellEvent_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_BuildingInfoChangeForCellEvent_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_BuildingInfoChangeForCellEvent_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.BuildingInfoChangeForCellEvent.class, pb4client.BuildingInfoChangeForCellEvent.Builder.class);
  }

  public static final int BUILDINFOFORCELLEVENT_FIELD_NUMBER = 1;
  private java.util.List<pb4client.BuildInfoForCellEvent> buildInfoForCellEvent_;
  /**
   * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
   */
  public java.util.List<pb4client.BuildInfoForCellEvent> getBuildInfoForCellEventList() {
    return buildInfoForCellEvent_;
  }
  /**
   * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
   */
  public java.util.List<? extends pb4client.BuildInfoForCellEventOrBuilder> 
      getBuildInfoForCellEventOrBuilderList() {
    return buildInfoForCellEvent_;
  }
  /**
   * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
   */
  public int getBuildInfoForCellEventCount() {
    return buildInfoForCellEvent_.size();
  }
  /**
   * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
   */
  public pb4client.BuildInfoForCellEvent getBuildInfoForCellEvent(int index) {
    return buildInfoForCellEvent_.get(index);
  }
  /**
   * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
   */
  public pb4client.BuildInfoForCellEventOrBuilder getBuildInfoForCellEventOrBuilder(
      int index) {
    return buildInfoForCellEvent_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    for (int i = 0; i < getBuildInfoForCellEventCount(); i++) {
      if (!getBuildInfoForCellEvent(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < buildInfoForCellEvent_.size(); i++) {
      output.writeMessage(1, buildInfoForCellEvent_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < buildInfoForCellEvent_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, buildInfoForCellEvent_.get(i));
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
    if (!(obj instanceof pb4client.BuildingInfoChangeForCellEvent)) {
      return super.equals(obj);
    }
    pb4client.BuildingInfoChangeForCellEvent other = (pb4client.BuildingInfoChangeForCellEvent) obj;

    boolean result = true;
    result = result && getBuildInfoForCellEventList()
        .equals(other.getBuildInfoForCellEventList());
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
    if (getBuildInfoForCellEventCount() > 0) {
      hash = (37 * hash) + BUILDINFOFORCELLEVENT_FIELD_NUMBER;
      hash = (53 * hash) + getBuildInfoForCellEventList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.BuildingInfoChangeForCellEvent parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuildingInfoChangeForCellEvent parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuildingInfoChangeForCellEvent parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuildingInfoChangeForCellEvent parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuildingInfoChangeForCellEvent parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuildingInfoChangeForCellEvent parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuildingInfoChangeForCellEvent parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuildingInfoChangeForCellEvent parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuildingInfoChangeForCellEvent parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.BuildingInfoChangeForCellEvent parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuildingInfoChangeForCellEvent parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuildingInfoChangeForCellEvent parseFrom(
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
  public static Builder newBuilder(pb4client.BuildingInfoChangeForCellEvent prototype) {
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
   * msgType = 3107
   * 地块上土地建造信息变化
   * </pre>
   *
   * Protobuf type {@code client2server.BuildingInfoChangeForCellEvent}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.BuildingInfoChangeForCellEvent)
      pb4client.BuildingInfoChangeForCellEventOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_BuildingInfoChangeForCellEvent_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_BuildingInfoChangeForCellEvent_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.BuildingInfoChangeForCellEvent.class, pb4client.BuildingInfoChangeForCellEvent.Builder.class);
    }

    // Construct using pb4client.BuildingInfoChangeForCellEvent.newBuilder()
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
        getBuildInfoForCellEventFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (buildInfoForCellEventBuilder_ == null) {
        buildInfoForCellEvent_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        buildInfoForCellEventBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_BuildingInfoChangeForCellEvent_descriptor;
    }

    public pb4client.BuildingInfoChangeForCellEvent getDefaultInstanceForType() {
      return pb4client.BuildingInfoChangeForCellEvent.getDefaultInstance();
    }

    public pb4client.BuildingInfoChangeForCellEvent build() {
      pb4client.BuildingInfoChangeForCellEvent result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.BuildingInfoChangeForCellEvent buildPartial() {
      pb4client.BuildingInfoChangeForCellEvent result = new pb4client.BuildingInfoChangeForCellEvent(this);
      int from_bitField0_ = bitField0_;
      if (buildInfoForCellEventBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          buildInfoForCellEvent_ = java.util.Collections.unmodifiableList(buildInfoForCellEvent_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.buildInfoForCellEvent_ = buildInfoForCellEvent_;
      } else {
        result.buildInfoForCellEvent_ = buildInfoForCellEventBuilder_.build();
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
      if (other instanceof pb4client.BuildingInfoChangeForCellEvent) {
        return mergeFrom((pb4client.BuildingInfoChangeForCellEvent)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.BuildingInfoChangeForCellEvent other) {
      if (other == pb4client.BuildingInfoChangeForCellEvent.getDefaultInstance()) return this;
      if (buildInfoForCellEventBuilder_ == null) {
        if (!other.buildInfoForCellEvent_.isEmpty()) {
          if (buildInfoForCellEvent_.isEmpty()) {
            buildInfoForCellEvent_ = other.buildInfoForCellEvent_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureBuildInfoForCellEventIsMutable();
            buildInfoForCellEvent_.addAll(other.buildInfoForCellEvent_);
          }
          onChanged();
        }
      } else {
        if (!other.buildInfoForCellEvent_.isEmpty()) {
          if (buildInfoForCellEventBuilder_.isEmpty()) {
            buildInfoForCellEventBuilder_.dispose();
            buildInfoForCellEventBuilder_ = null;
            buildInfoForCellEvent_ = other.buildInfoForCellEvent_;
            bitField0_ = (bitField0_ & ~0x00000001);
            buildInfoForCellEventBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getBuildInfoForCellEventFieldBuilder() : null;
          } else {
            buildInfoForCellEventBuilder_.addAllMessages(other.buildInfoForCellEvent_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      for (int i = 0; i < getBuildInfoForCellEventCount(); i++) {
        if (!getBuildInfoForCellEvent(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.BuildingInfoChangeForCellEvent parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.BuildingInfoChangeForCellEvent) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4client.BuildInfoForCellEvent> buildInfoForCellEvent_ =
      java.util.Collections.emptyList();
    private void ensureBuildInfoForCellEventIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        buildInfoForCellEvent_ = new java.util.ArrayList<pb4client.BuildInfoForCellEvent>(buildInfoForCellEvent_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.BuildInfoForCellEvent, pb4client.BuildInfoForCellEvent.Builder, pb4client.BuildInfoForCellEventOrBuilder> buildInfoForCellEventBuilder_;

    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public java.util.List<pb4client.BuildInfoForCellEvent> getBuildInfoForCellEventList() {
      if (buildInfoForCellEventBuilder_ == null) {
        return java.util.Collections.unmodifiableList(buildInfoForCellEvent_);
      } else {
        return buildInfoForCellEventBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public int getBuildInfoForCellEventCount() {
      if (buildInfoForCellEventBuilder_ == null) {
        return buildInfoForCellEvent_.size();
      } else {
        return buildInfoForCellEventBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public pb4client.BuildInfoForCellEvent getBuildInfoForCellEvent(int index) {
      if (buildInfoForCellEventBuilder_ == null) {
        return buildInfoForCellEvent_.get(index);
      } else {
        return buildInfoForCellEventBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public Builder setBuildInfoForCellEvent(
        int index, pb4client.BuildInfoForCellEvent value) {
      if (buildInfoForCellEventBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBuildInfoForCellEventIsMutable();
        buildInfoForCellEvent_.set(index, value);
        onChanged();
      } else {
        buildInfoForCellEventBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public Builder setBuildInfoForCellEvent(
        int index, pb4client.BuildInfoForCellEvent.Builder builderForValue) {
      if (buildInfoForCellEventBuilder_ == null) {
        ensureBuildInfoForCellEventIsMutable();
        buildInfoForCellEvent_.set(index, builderForValue.build());
        onChanged();
      } else {
        buildInfoForCellEventBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public Builder addBuildInfoForCellEvent(pb4client.BuildInfoForCellEvent value) {
      if (buildInfoForCellEventBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBuildInfoForCellEventIsMutable();
        buildInfoForCellEvent_.add(value);
        onChanged();
      } else {
        buildInfoForCellEventBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public Builder addBuildInfoForCellEvent(
        int index, pb4client.BuildInfoForCellEvent value) {
      if (buildInfoForCellEventBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBuildInfoForCellEventIsMutable();
        buildInfoForCellEvent_.add(index, value);
        onChanged();
      } else {
        buildInfoForCellEventBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public Builder addBuildInfoForCellEvent(
        pb4client.BuildInfoForCellEvent.Builder builderForValue) {
      if (buildInfoForCellEventBuilder_ == null) {
        ensureBuildInfoForCellEventIsMutable();
        buildInfoForCellEvent_.add(builderForValue.build());
        onChanged();
      } else {
        buildInfoForCellEventBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public Builder addBuildInfoForCellEvent(
        int index, pb4client.BuildInfoForCellEvent.Builder builderForValue) {
      if (buildInfoForCellEventBuilder_ == null) {
        ensureBuildInfoForCellEventIsMutable();
        buildInfoForCellEvent_.add(index, builderForValue.build());
        onChanged();
      } else {
        buildInfoForCellEventBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public Builder addAllBuildInfoForCellEvent(
        java.lang.Iterable<? extends pb4client.BuildInfoForCellEvent> values) {
      if (buildInfoForCellEventBuilder_ == null) {
        ensureBuildInfoForCellEventIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, buildInfoForCellEvent_);
        onChanged();
      } else {
        buildInfoForCellEventBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public Builder clearBuildInfoForCellEvent() {
      if (buildInfoForCellEventBuilder_ == null) {
        buildInfoForCellEvent_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        buildInfoForCellEventBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public Builder removeBuildInfoForCellEvent(int index) {
      if (buildInfoForCellEventBuilder_ == null) {
        ensureBuildInfoForCellEventIsMutable();
        buildInfoForCellEvent_.remove(index);
        onChanged();
      } else {
        buildInfoForCellEventBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public pb4client.BuildInfoForCellEvent.Builder getBuildInfoForCellEventBuilder(
        int index) {
      return getBuildInfoForCellEventFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public pb4client.BuildInfoForCellEventOrBuilder getBuildInfoForCellEventOrBuilder(
        int index) {
      if (buildInfoForCellEventBuilder_ == null) {
        return buildInfoForCellEvent_.get(index);  } else {
        return buildInfoForCellEventBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public java.util.List<? extends pb4client.BuildInfoForCellEventOrBuilder> 
         getBuildInfoForCellEventOrBuilderList() {
      if (buildInfoForCellEventBuilder_ != null) {
        return buildInfoForCellEventBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(buildInfoForCellEvent_);
      }
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public pb4client.BuildInfoForCellEvent.Builder addBuildInfoForCellEventBuilder() {
      return getBuildInfoForCellEventFieldBuilder().addBuilder(
          pb4client.BuildInfoForCellEvent.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public pb4client.BuildInfoForCellEvent.Builder addBuildInfoForCellEventBuilder(
        int index) {
      return getBuildInfoForCellEventFieldBuilder().addBuilder(
          index, pb4client.BuildInfoForCellEvent.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.BuildInfoForCellEvent buildInfoForCellEvent = 1;</code>
     */
    public java.util.List<pb4client.BuildInfoForCellEvent.Builder> 
         getBuildInfoForCellEventBuilderList() {
      return getBuildInfoForCellEventFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.BuildInfoForCellEvent, pb4client.BuildInfoForCellEvent.Builder, pb4client.BuildInfoForCellEventOrBuilder> 
        getBuildInfoForCellEventFieldBuilder() {
      if (buildInfoForCellEventBuilder_ == null) {
        buildInfoForCellEventBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.BuildInfoForCellEvent, pb4client.BuildInfoForCellEvent.Builder, pb4client.BuildInfoForCellEventOrBuilder>(
                buildInfoForCellEvent_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        buildInfoForCellEvent_ = null;
      }
      return buildInfoForCellEventBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.BuildingInfoChangeForCellEvent)
  }

  // @@protoc_insertion_point(class_scope:client2server.BuildingInfoChangeForCellEvent)
  private static final pb4client.BuildingInfoChangeForCellEvent DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.BuildingInfoChangeForCellEvent();
  }

  public static pb4client.BuildingInfoChangeForCellEvent getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<BuildingInfoChangeForCellEvent>
      PARSER = new com.google.protobuf.AbstractParser<BuildingInfoChangeForCellEvent>() {
    public BuildingInfoChangeForCellEvent parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BuildingInfoChangeForCellEvent(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BuildingInfoChangeForCellEvent> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BuildingInfoChangeForCellEvent> getParserForType() {
    return PARSER;
  }

  public pb4client.BuildingInfoChangeForCellEvent getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

