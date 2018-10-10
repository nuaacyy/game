// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.EquipSkillInfo}
 */
public  final class EquipSkillInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.EquipSkillInfo)
    EquipSkillInfoOrBuilder {
  // Use EquipSkillInfo.newBuilder() to construct.
  private EquipSkillInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EquipSkillInfo() {
    skillId_ = 0;
    skillLv_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private EquipSkillInfo(
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
            skillId_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            skillLv_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_EquipSkillInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_EquipSkillInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.EquipSkillInfo.class, pb4client.EquipSkillInfo.Builder.class);
  }

  private int bitField0_;
  public static final int SKILLID_FIELD_NUMBER = 1;
  private int skillId_;
  /**
   * <pre>
   *技能ID
   * </pre>
   *
   * <code>required int32 skillId = 1;</code>
   */
  public boolean hasSkillId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *技能ID
   * </pre>
   *
   * <code>required int32 skillId = 1;</code>
   */
  public int getSkillId() {
    return skillId_;
  }

  public static final int SKILLLV_FIELD_NUMBER = 2;
  private int skillLv_;
  /**
   * <pre>
   *技能等级
   * </pre>
   *
   * <code>required int32 skillLv = 2;</code>
   */
  public boolean hasSkillLv() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *技能等级
   * </pre>
   *
   * <code>required int32 skillLv = 2;</code>
   */
  public int getSkillLv() {
    return skillLv_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasSkillId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasSkillLv()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, skillId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, skillLv_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, skillId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, skillLv_);
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
    if (!(obj instanceof pb4client.EquipSkillInfo)) {
      return super.equals(obj);
    }
    pb4client.EquipSkillInfo other = (pb4client.EquipSkillInfo) obj;

    boolean result = true;
    result = result && (hasSkillId() == other.hasSkillId());
    if (hasSkillId()) {
      result = result && (getSkillId()
          == other.getSkillId());
    }
    result = result && (hasSkillLv() == other.hasSkillLv());
    if (hasSkillLv()) {
      result = result && (getSkillLv()
          == other.getSkillLv());
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
    if (hasSkillId()) {
      hash = (37 * hash) + SKILLID_FIELD_NUMBER;
      hash = (53 * hash) + getSkillId();
    }
    if (hasSkillLv()) {
      hash = (37 * hash) + SKILLLV_FIELD_NUMBER;
      hash = (53 * hash) + getSkillLv();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.EquipSkillInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.EquipSkillInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.EquipSkillInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.EquipSkillInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.EquipSkillInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.EquipSkillInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.EquipSkillInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.EquipSkillInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.EquipSkillInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.EquipSkillInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.EquipSkillInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.EquipSkillInfo parseFrom(
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
  public static Builder newBuilder(pb4client.EquipSkillInfo prototype) {
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
   * Protobuf type {@code client2server.EquipSkillInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.EquipSkillInfo)
      pb4client.EquipSkillInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_EquipSkillInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_EquipSkillInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.EquipSkillInfo.class, pb4client.EquipSkillInfo.Builder.class);
    }

    // Construct using pb4client.EquipSkillInfo.newBuilder()
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
      skillId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      skillLv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_EquipSkillInfo_descriptor;
    }

    public pb4client.EquipSkillInfo getDefaultInstanceForType() {
      return pb4client.EquipSkillInfo.getDefaultInstance();
    }

    public pb4client.EquipSkillInfo build() {
      pb4client.EquipSkillInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.EquipSkillInfo buildPartial() {
      pb4client.EquipSkillInfo result = new pb4client.EquipSkillInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.skillId_ = skillId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.skillLv_ = skillLv_;
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
      if (other instanceof pb4client.EquipSkillInfo) {
        return mergeFrom((pb4client.EquipSkillInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.EquipSkillInfo other) {
      if (other == pb4client.EquipSkillInfo.getDefaultInstance()) return this;
      if (other.hasSkillId()) {
        setSkillId(other.getSkillId());
      }
      if (other.hasSkillLv()) {
        setSkillLv(other.getSkillLv());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasSkillId()) {
        return false;
      }
      if (!hasSkillLv()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.EquipSkillInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.EquipSkillInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int skillId_ ;
    /**
     * <pre>
     *技能ID
     * </pre>
     *
     * <code>required int32 skillId = 1;</code>
     */
    public boolean hasSkillId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *技能ID
     * </pre>
     *
     * <code>required int32 skillId = 1;</code>
     */
    public int getSkillId() {
      return skillId_;
    }
    /**
     * <pre>
     *技能ID
     * </pre>
     *
     * <code>required int32 skillId = 1;</code>
     */
    public Builder setSkillId(int value) {
      bitField0_ |= 0x00000001;
      skillId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *技能ID
     * </pre>
     *
     * <code>required int32 skillId = 1;</code>
     */
    public Builder clearSkillId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      skillId_ = 0;
      onChanged();
      return this;
    }

    private int skillLv_ ;
    /**
     * <pre>
     *技能等级
     * </pre>
     *
     * <code>required int32 skillLv = 2;</code>
     */
    public boolean hasSkillLv() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *技能等级
     * </pre>
     *
     * <code>required int32 skillLv = 2;</code>
     */
    public int getSkillLv() {
      return skillLv_;
    }
    /**
     * <pre>
     *技能等级
     * </pre>
     *
     * <code>required int32 skillLv = 2;</code>
     */
    public Builder setSkillLv(int value) {
      bitField0_ |= 0x00000002;
      skillLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *技能等级
     * </pre>
     *
     * <code>required int32 skillLv = 2;</code>
     */
    public Builder clearSkillLv() {
      bitField0_ = (bitField0_ & ~0x00000002);
      skillLv_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.EquipSkillInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.EquipSkillInfo)
  private static final pb4client.EquipSkillInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.EquipSkillInfo();
  }

  public static pb4client.EquipSkillInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<EquipSkillInfo>
      PARSER = new com.google.protobuf.AbstractParser<EquipSkillInfo>() {
    public EquipSkillInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new EquipSkillInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EquipSkillInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EquipSkillInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.EquipSkillInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
