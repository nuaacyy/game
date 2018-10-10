// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 * 玩家主动离开联盟,发起是在HOME服
 * </pre>
 *
 * Protobuf type {@code pb4server.LeaveAllianceBySelfTell}
 */
public  final class LeaveAllianceBySelfTell extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.LeaveAllianceBySelfTell)
    LeaveAllianceBySelfTellOrBuilder {
  // Use LeaveAllianceBySelfTell.newBuilder() to construct.
  private LeaveAllianceBySelfTell(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private LeaveAllianceBySelfTell() {
    oldPos_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private LeaveAllianceBySelfTell(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              oldPos_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000001;
            }
            oldPos_.add(input.readInt32());
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001) && input.getBytesUntilLimit() > 0) {
              oldPos_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              oldPos_.add(input.readInt32());
            }
            input.popLimit(limit);
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
        oldPos_ = java.util.Collections.unmodifiableList(oldPos_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalWkt.internal_static_pb4server_LeaveAllianceBySelfTell_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_LeaveAllianceBySelfTell_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.LeaveAllianceBySelfTell.class, pb4server.LeaveAllianceBySelfTell.Builder.class);
  }

  public static final int OLDPOS_FIELD_NUMBER = 1;
  private java.util.List<java.lang.Integer> oldPos_;
  /**
   * <code>repeated int32 oldPos = 1;</code>
   */
  public java.util.List<java.lang.Integer>
      getOldPosList() {
    return oldPos_;
  }
  /**
   * <code>repeated int32 oldPos = 1;</code>
   */
  public int getOldPosCount() {
    return oldPos_.size();
  }
  /**
   * <code>repeated int32 oldPos = 1;</code>
   */
  public int getOldPos(int index) {
    return oldPos_.get(index);
  }
  private int oldPosMemoizedSerializedSize = -1;

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
    if (getOldPosList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(oldPosMemoizedSerializedSize);
    }
    for (int i = 0; i < oldPos_.size(); i++) {
      output.writeInt32NoTag(oldPos_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < oldPos_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(oldPos_.get(i));
      }
      size += dataSize;
      if (!getOldPosList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      oldPosMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof pb4server.LeaveAllianceBySelfTell)) {
      return super.equals(obj);
    }
    pb4server.LeaveAllianceBySelfTell other = (pb4server.LeaveAllianceBySelfTell) obj;

    boolean result = true;
    result = result && getOldPosList()
        .equals(other.getOldPosList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getOldPosCount() > 0) {
      hash = (37 * hash) + OLDPOS_FIELD_NUMBER;
      hash = (53 * hash) + getOldPosList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.LeaveAllianceBySelfTell parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.LeaveAllianceBySelfTell parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.LeaveAllianceBySelfTell parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.LeaveAllianceBySelfTell parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.LeaveAllianceBySelfTell parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.LeaveAllianceBySelfTell parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.LeaveAllianceBySelfTell parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.LeaveAllianceBySelfTell parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.LeaveAllianceBySelfTell parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.LeaveAllianceBySelfTell parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.LeaveAllianceBySelfTell parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.LeaveAllianceBySelfTell parseFrom(
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
  public static Builder newBuilder(pb4server.LeaveAllianceBySelfTell prototype) {
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
   * 玩家主动离开联盟,发起是在HOME服
   * </pre>
   *
   * Protobuf type {@code pb4server.LeaveAllianceBySelfTell}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.LeaveAllianceBySelfTell)
      pb4server.LeaveAllianceBySelfTellOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_LeaveAllianceBySelfTell_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_LeaveAllianceBySelfTell_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.LeaveAllianceBySelfTell.class, pb4server.LeaveAllianceBySelfTell.Builder.class);
    }

    // Construct using pb4server.LeaveAllianceBySelfTell.newBuilder()
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
      oldPos_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_LeaveAllianceBySelfTell_descriptor;
    }

    public pb4server.LeaveAllianceBySelfTell getDefaultInstanceForType() {
      return pb4server.LeaveAllianceBySelfTell.getDefaultInstance();
    }

    public pb4server.LeaveAllianceBySelfTell build() {
      pb4server.LeaveAllianceBySelfTell result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.LeaveAllianceBySelfTell buildPartial() {
      pb4server.LeaveAllianceBySelfTell result = new pb4server.LeaveAllianceBySelfTell(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        oldPos_ = java.util.Collections.unmodifiableList(oldPos_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.oldPos_ = oldPos_;
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
      if (other instanceof pb4server.LeaveAllianceBySelfTell) {
        return mergeFrom((pb4server.LeaveAllianceBySelfTell)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.LeaveAllianceBySelfTell other) {
      if (other == pb4server.LeaveAllianceBySelfTell.getDefaultInstance()) return this;
      if (!other.oldPos_.isEmpty()) {
        if (oldPos_.isEmpty()) {
          oldPos_ = other.oldPos_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureOldPosIsMutable();
          oldPos_.addAll(other.oldPos_);
        }
        onChanged();
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
      pb4server.LeaveAllianceBySelfTell parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.LeaveAllianceBySelfTell) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<java.lang.Integer> oldPos_ = java.util.Collections.emptyList();
    private void ensureOldPosIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        oldPos_ = new java.util.ArrayList<java.lang.Integer>(oldPos_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated int32 oldPos = 1;</code>
     */
    public java.util.List<java.lang.Integer>
        getOldPosList() {
      return java.util.Collections.unmodifiableList(oldPos_);
    }
    /**
     * <code>repeated int32 oldPos = 1;</code>
     */
    public int getOldPosCount() {
      return oldPos_.size();
    }
    /**
     * <code>repeated int32 oldPos = 1;</code>
     */
    public int getOldPos(int index) {
      return oldPos_.get(index);
    }
    /**
     * <code>repeated int32 oldPos = 1;</code>
     */
    public Builder setOldPos(
        int index, int value) {
      ensureOldPosIsMutable();
      oldPos_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 oldPos = 1;</code>
     */
    public Builder addOldPos(int value) {
      ensureOldPosIsMutable();
      oldPos_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 oldPos = 1;</code>
     */
    public Builder addAllOldPos(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensureOldPosIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, oldPos_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 oldPos = 1;</code>
     */
    public Builder clearOldPos() {
      oldPos_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
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


    // @@protoc_insertion_point(builder_scope:pb4server.LeaveAllianceBySelfTell)
  }

  // @@protoc_insertion_point(class_scope:pb4server.LeaveAllianceBySelfTell)
  private static final pb4server.LeaveAllianceBySelfTell DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.LeaveAllianceBySelfTell();
  }

  public static pb4server.LeaveAllianceBySelfTell getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<LeaveAllianceBySelfTell>
      PARSER = new com.google.protobuf.AbstractParser<LeaveAllianceBySelfTell>() {
    public LeaveAllianceBySelfTell parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new LeaveAllianceBySelfTell(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<LeaveAllianceBySelfTell> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<LeaveAllianceBySelfTell> getParserForType() {
    return PARSER;
  }

  public pb4server.LeaveAllianceBySelfTell getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

