// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * 通知开关
 * </pre>
 *
 * Protobuf type {@code client2server.NoticeSwitch}
 */
public  final class NoticeSwitch extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.NoticeSwitch)
    NoticeSwitchOrBuilder {
  // Use NoticeSwitch.newBuilder() to construct.
  private NoticeSwitch(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NoticeSwitch() {
    typeProtoId_ = 0;
    refuseDisturb_ = 0;
    switch_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private NoticeSwitch(
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
            typeProtoId_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            refuseDisturb_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            switch_ = input.readInt32();
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_NoticeSwitch_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_NoticeSwitch_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.NoticeSwitch.class, pb4client.NoticeSwitch.Builder.class);
  }

  private int bitField0_;
  public static final int TYPEPROTOID_FIELD_NUMBER = 1;
  private int typeProtoId_;
  /**
   * <pre>
   * 开关类型 1~内城 2~部队 3~社交
   * </pre>
   *
   * <code>required int32 typeProtoId = 1;</code>
   */
  public boolean hasTypeProtoId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 开关类型 1~内城 2~部队 3~社交
   * </pre>
   *
   * <code>required int32 typeProtoId = 1;</code>
   */
  public int getTypeProtoId() {
    return typeProtoId_;
  }

  public static final int REFUSEDISTURB_FIELD_NUMBER = 2;
  private int refuseDisturb_;
  /**
   * <pre>
   * 勿扰 0~关 1~开
   * </pre>
   *
   * <code>required int32 refuseDisturb = 2;</code>
   */
  public boolean hasRefuseDisturb() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 勿扰 0~关 1~开
   * </pre>
   *
   * <code>required int32 refuseDisturb = 2;</code>
   */
  public int getRefuseDisturb() {
    return refuseDisturb_;
  }

  public static final int SWITCH_FIELD_NUMBER = 3;
  private int switch_;
  /**
   * <pre>
   * 通知开关 0~关 1~开
   * </pre>
   *
   * <code>required int32 switch = 3;</code>
   */
  public boolean hasSwitch() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 通知开关 0~关 1~开
   * </pre>
   *
   * <code>required int32 switch = 3;</code>
   */
  public int getSwitch() {
    return switch_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasTypeProtoId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasRefuseDisturb()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasSwitch()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, typeProtoId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, refuseDisturb_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, switch_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, typeProtoId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, refuseDisturb_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, switch_);
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
    if (!(obj instanceof pb4client.NoticeSwitch)) {
      return super.equals(obj);
    }
    pb4client.NoticeSwitch other = (pb4client.NoticeSwitch) obj;

    boolean result = true;
    result = result && (hasTypeProtoId() == other.hasTypeProtoId());
    if (hasTypeProtoId()) {
      result = result && (getTypeProtoId()
          == other.getTypeProtoId());
    }
    result = result && (hasRefuseDisturb() == other.hasRefuseDisturb());
    if (hasRefuseDisturb()) {
      result = result && (getRefuseDisturb()
          == other.getRefuseDisturb());
    }
    result = result && (hasSwitch() == other.hasSwitch());
    if (hasSwitch()) {
      result = result && (getSwitch()
          == other.getSwitch());
    }
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
    if (hasTypeProtoId()) {
      hash = (37 * hash) + TYPEPROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getTypeProtoId();
    }
    if (hasRefuseDisturb()) {
      hash = (37 * hash) + REFUSEDISTURB_FIELD_NUMBER;
      hash = (53 * hash) + getRefuseDisturb();
    }
    if (hasSwitch()) {
      hash = (37 * hash) + SWITCH_FIELD_NUMBER;
      hash = (53 * hash) + getSwitch();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.NoticeSwitch parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.NoticeSwitch parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.NoticeSwitch parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.NoticeSwitch parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.NoticeSwitch parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.NoticeSwitch parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.NoticeSwitch parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.NoticeSwitch parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.NoticeSwitch parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.NoticeSwitch parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.NoticeSwitch parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.NoticeSwitch parseFrom(
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
  public static Builder newBuilder(pb4client.NoticeSwitch prototype) {
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
   * 通知开关
   * </pre>
   *
   * Protobuf type {@code client2server.NoticeSwitch}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.NoticeSwitch)
      pb4client.NoticeSwitchOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_NoticeSwitch_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_NoticeSwitch_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.NoticeSwitch.class, pb4client.NoticeSwitch.Builder.class);
    }

    // Construct using pb4client.NoticeSwitch.newBuilder()
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
      typeProtoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      refuseDisturb_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      switch_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_NoticeSwitch_descriptor;
    }

    public pb4client.NoticeSwitch getDefaultInstanceForType() {
      return pb4client.NoticeSwitch.getDefaultInstance();
    }

    public pb4client.NoticeSwitch build() {
      pb4client.NoticeSwitch result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.NoticeSwitch buildPartial() {
      pb4client.NoticeSwitch result = new pb4client.NoticeSwitch(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.typeProtoId_ = typeProtoId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.refuseDisturb_ = refuseDisturb_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.switch_ = switch_;
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
      if (other instanceof pb4client.NoticeSwitch) {
        return mergeFrom((pb4client.NoticeSwitch)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.NoticeSwitch other) {
      if (other == pb4client.NoticeSwitch.getDefaultInstance()) return this;
      if (other.hasTypeProtoId()) {
        setTypeProtoId(other.getTypeProtoId());
      }
      if (other.hasRefuseDisturb()) {
        setRefuseDisturb(other.getRefuseDisturb());
      }
      if (other.hasSwitch()) {
        setSwitch(other.getSwitch());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasTypeProtoId()) {
        return false;
      }
      if (!hasRefuseDisturb()) {
        return false;
      }
      if (!hasSwitch()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.NoticeSwitch parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.NoticeSwitch) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int typeProtoId_ ;
    /**
     * <pre>
     * 开关类型 1~内城 2~部队 3~社交
     * </pre>
     *
     * <code>required int32 typeProtoId = 1;</code>
     */
    public boolean hasTypeProtoId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 开关类型 1~内城 2~部队 3~社交
     * </pre>
     *
     * <code>required int32 typeProtoId = 1;</code>
     */
    public int getTypeProtoId() {
      return typeProtoId_;
    }
    /**
     * <pre>
     * 开关类型 1~内城 2~部队 3~社交
     * </pre>
     *
     * <code>required int32 typeProtoId = 1;</code>
     */
    public Builder setTypeProtoId(int value) {
      bitField0_ |= 0x00000001;
      typeProtoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 开关类型 1~内城 2~部队 3~社交
     * </pre>
     *
     * <code>required int32 typeProtoId = 1;</code>
     */
    public Builder clearTypeProtoId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      typeProtoId_ = 0;
      onChanged();
      return this;
    }

    private int refuseDisturb_ ;
    /**
     * <pre>
     * 勿扰 0~关 1~开
     * </pre>
     *
     * <code>required int32 refuseDisturb = 2;</code>
     */
    public boolean hasRefuseDisturb() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 勿扰 0~关 1~开
     * </pre>
     *
     * <code>required int32 refuseDisturb = 2;</code>
     */
    public int getRefuseDisturb() {
      return refuseDisturb_;
    }
    /**
     * <pre>
     * 勿扰 0~关 1~开
     * </pre>
     *
     * <code>required int32 refuseDisturb = 2;</code>
     */
    public Builder setRefuseDisturb(int value) {
      bitField0_ |= 0x00000002;
      refuseDisturb_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 勿扰 0~关 1~开
     * </pre>
     *
     * <code>required int32 refuseDisturb = 2;</code>
     */
    public Builder clearRefuseDisturb() {
      bitField0_ = (bitField0_ & ~0x00000002);
      refuseDisturb_ = 0;
      onChanged();
      return this;
    }

    private int switch_ ;
    /**
     * <pre>
     * 通知开关 0~关 1~开
     * </pre>
     *
     * <code>required int32 switch = 3;</code>
     */
    public boolean hasSwitch() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 通知开关 0~关 1~开
     * </pre>
     *
     * <code>required int32 switch = 3;</code>
     */
    public int getSwitch() {
      return switch_;
    }
    /**
     * <pre>
     * 通知开关 0~关 1~开
     * </pre>
     *
     * <code>required int32 switch = 3;</code>
     */
    public Builder setSwitch(int value) {
      bitField0_ |= 0x00000004;
      switch_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 通知开关 0~关 1~开
     * </pre>
     *
     * <code>required int32 switch = 3;</code>
     */
    public Builder clearSwitch() {
      bitField0_ = (bitField0_ & ~0x00000004);
      switch_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.NoticeSwitch)
  }

  // @@protoc_insertion_point(class_scope:client2server.NoticeSwitch)
  private static final pb4client.NoticeSwitch DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.NoticeSwitch();
  }

  public static pb4client.NoticeSwitch getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<NoticeSwitch>
      PARSER = new com.google.protobuf.AbstractParser<NoticeSwitch>() {
    public NoticeSwitch parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new NoticeSwitch(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<NoticeSwitch> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NoticeSwitch> getParserForType() {
    return PARSER;
  }

  public pb4client.NoticeSwitch getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
