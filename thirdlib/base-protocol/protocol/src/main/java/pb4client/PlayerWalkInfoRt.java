// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.PlayerWalkInfoRt}
 */
public  final class PlayerWalkInfoRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.PlayerWalkInfoRt)
    PlayerWalkInfoRtOrBuilder {
  // Use PlayerWalkInfoRt.newBuilder() to construct.
  private PlayerWalkInfoRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PlayerWalkInfoRt() {
    forceId_ = 0L;
    startTime_ = 0L;
    finishTime_ = 0L;
    departureX_ = 0;
    departureY_ = 0;
    targetedX_ = 0;
    targetedY_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PlayerWalkInfoRt(
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
            forceId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            startTime_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            finishTime_ = input.readInt64();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            departureX_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            departureY_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            targetedX_ = input.readInt32();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            targetedY_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_PlayerWalkInfoRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_PlayerWalkInfoRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.PlayerWalkInfoRt.class, pb4client.PlayerWalkInfoRt.Builder.class);
  }

  private int bitField0_;
  public static final int FORCEID_FIELD_NUMBER = 1;
  private long forceId_;
  /**
   * <code>optional int64 forceId = 1;</code>
   */
  public boolean hasForceId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>optional int64 forceId = 1;</code>
   */
  public long getForceId() {
    return forceId_;
  }

  public static final int STARTTIME_FIELD_NUMBER = 2;
  private long startTime_;
  /**
   * <code>optional int64 startTime = 2;</code>
   */
  public boolean hasStartTime() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>optional int64 startTime = 2;</code>
   */
  public long getStartTime() {
    return startTime_;
  }

  public static final int FINISHTIME_FIELD_NUMBER = 3;
  private long finishTime_;
  /**
   * <code>optional int64 finishTime = 3;</code>
   */
  public boolean hasFinishTime() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>optional int64 finishTime = 3;</code>
   */
  public long getFinishTime() {
    return finishTime_;
  }

  public static final int DEPARTUREX_FIELD_NUMBER = 4;
  private int departureX_;
  /**
   * <code>optional int32 departureX = 4;</code>
   */
  public boolean hasDepartureX() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>optional int32 departureX = 4;</code>
   */
  public int getDepartureX() {
    return departureX_;
  }

  public static final int DEPARTUREY_FIELD_NUMBER = 5;
  private int departureY_;
  /**
   * <code>optional int32 departureY = 5;</code>
   */
  public boolean hasDepartureY() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <code>optional int32 departureY = 5;</code>
   */
  public int getDepartureY() {
    return departureY_;
  }

  public static final int TARGETEDX_FIELD_NUMBER = 6;
  private int targetedX_;
  /**
   * <code>optional int32 targetedX = 6;</code>
   */
  public boolean hasTargetedX() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <code>optional int32 targetedX = 6;</code>
   */
  public int getTargetedX() {
    return targetedX_;
  }

  public static final int TARGETEDY_FIELD_NUMBER = 7;
  private int targetedY_;
  /**
   * <code>optional int32 targetedY = 7;</code>
   */
  public boolean hasTargetedY() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <code>optional int32 targetedY = 7;</code>
   */
  public int getTargetedY() {
    return targetedY_;
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
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, forceId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, startTime_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt64(3, finishTime_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, departureX_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, departureY_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt32(6, targetedX_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, targetedY_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, forceId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, startTime_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, finishTime_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, departureX_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, departureY_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, targetedX_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, targetedY_);
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
    if (!(obj instanceof pb4client.PlayerWalkInfoRt)) {
      return super.equals(obj);
    }
    pb4client.PlayerWalkInfoRt other = (pb4client.PlayerWalkInfoRt) obj;

    boolean result = true;
    result = result && (hasForceId() == other.hasForceId());
    if (hasForceId()) {
      result = result && (getForceId()
          == other.getForceId());
    }
    result = result && (hasStartTime() == other.hasStartTime());
    if (hasStartTime()) {
      result = result && (getStartTime()
          == other.getStartTime());
    }
    result = result && (hasFinishTime() == other.hasFinishTime());
    if (hasFinishTime()) {
      result = result && (getFinishTime()
          == other.getFinishTime());
    }
    result = result && (hasDepartureX() == other.hasDepartureX());
    if (hasDepartureX()) {
      result = result && (getDepartureX()
          == other.getDepartureX());
    }
    result = result && (hasDepartureY() == other.hasDepartureY());
    if (hasDepartureY()) {
      result = result && (getDepartureY()
          == other.getDepartureY());
    }
    result = result && (hasTargetedX() == other.hasTargetedX());
    if (hasTargetedX()) {
      result = result && (getTargetedX()
          == other.getTargetedX());
    }
    result = result && (hasTargetedY() == other.hasTargetedY());
    if (hasTargetedY()) {
      result = result && (getTargetedY()
          == other.getTargetedY());
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
    if (hasForceId()) {
      hash = (37 * hash) + FORCEID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getForceId());
    }
    if (hasStartTime()) {
      hash = (37 * hash) + STARTTIME_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getStartTime());
    }
    if (hasFinishTime()) {
      hash = (37 * hash) + FINISHTIME_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getFinishTime());
    }
    if (hasDepartureX()) {
      hash = (37 * hash) + DEPARTUREX_FIELD_NUMBER;
      hash = (53 * hash) + getDepartureX();
    }
    if (hasDepartureY()) {
      hash = (37 * hash) + DEPARTUREY_FIELD_NUMBER;
      hash = (53 * hash) + getDepartureY();
    }
    if (hasTargetedX()) {
      hash = (37 * hash) + TARGETEDX_FIELD_NUMBER;
      hash = (53 * hash) + getTargetedX();
    }
    if (hasTargetedY()) {
      hash = (37 * hash) + TARGETEDY_FIELD_NUMBER;
      hash = (53 * hash) + getTargetedY();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.PlayerWalkInfoRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlayerWalkInfoRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlayerWalkInfoRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlayerWalkInfoRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlayerWalkInfoRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlayerWalkInfoRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlayerWalkInfoRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PlayerWalkInfoRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PlayerWalkInfoRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.PlayerWalkInfoRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PlayerWalkInfoRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PlayerWalkInfoRt parseFrom(
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
  public static Builder newBuilder(pb4client.PlayerWalkInfoRt prototype) {
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
   * Protobuf type {@code client2server.PlayerWalkInfoRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.PlayerWalkInfoRt)
      pb4client.PlayerWalkInfoRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_PlayerWalkInfoRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_PlayerWalkInfoRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.PlayerWalkInfoRt.class, pb4client.PlayerWalkInfoRt.Builder.class);
    }

    // Construct using pb4client.PlayerWalkInfoRt.newBuilder()
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
      forceId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      startTime_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      finishTime_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      departureX_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      departureY_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      targetedX_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      targetedY_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_PlayerWalkInfoRt_descriptor;
    }

    public pb4client.PlayerWalkInfoRt getDefaultInstanceForType() {
      return pb4client.PlayerWalkInfoRt.getDefaultInstance();
    }

    public pb4client.PlayerWalkInfoRt build() {
      pb4client.PlayerWalkInfoRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.PlayerWalkInfoRt buildPartial() {
      pb4client.PlayerWalkInfoRt result = new pb4client.PlayerWalkInfoRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.forceId_ = forceId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.startTime_ = startTime_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.finishTime_ = finishTime_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.departureX_ = departureX_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.departureY_ = departureY_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.targetedX_ = targetedX_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.targetedY_ = targetedY_;
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
      if (other instanceof pb4client.PlayerWalkInfoRt) {
        return mergeFrom((pb4client.PlayerWalkInfoRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.PlayerWalkInfoRt other) {
      if (other == pb4client.PlayerWalkInfoRt.getDefaultInstance()) return this;
      if (other.hasForceId()) {
        setForceId(other.getForceId());
      }
      if (other.hasStartTime()) {
        setStartTime(other.getStartTime());
      }
      if (other.hasFinishTime()) {
        setFinishTime(other.getFinishTime());
      }
      if (other.hasDepartureX()) {
        setDepartureX(other.getDepartureX());
      }
      if (other.hasDepartureY()) {
        setDepartureY(other.getDepartureY());
      }
      if (other.hasTargetedX()) {
        setTargetedX(other.getTargetedX());
      }
      if (other.hasTargetedY()) {
        setTargetedY(other.getTargetedY());
      }
      this.mergeUnknownFields(other.unknownFields);
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
      pb4client.PlayerWalkInfoRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.PlayerWalkInfoRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long forceId_ ;
    /**
     * <code>optional int64 forceId = 1;</code>
     */
    public boolean hasForceId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int64 forceId = 1;</code>
     */
    public long getForceId() {
      return forceId_;
    }
    /**
     * <code>optional int64 forceId = 1;</code>
     */
    public Builder setForceId(long value) {
      bitField0_ |= 0x00000001;
      forceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 forceId = 1;</code>
     */
    public Builder clearForceId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      forceId_ = 0L;
      onChanged();
      return this;
    }

    private long startTime_ ;
    /**
     * <code>optional int64 startTime = 2;</code>
     */
    public boolean hasStartTime() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int64 startTime = 2;</code>
     */
    public long getStartTime() {
      return startTime_;
    }
    /**
     * <code>optional int64 startTime = 2;</code>
     */
    public Builder setStartTime(long value) {
      bitField0_ |= 0x00000002;
      startTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 startTime = 2;</code>
     */
    public Builder clearStartTime() {
      bitField0_ = (bitField0_ & ~0x00000002);
      startTime_ = 0L;
      onChanged();
      return this;
    }

    private long finishTime_ ;
    /**
     * <code>optional int64 finishTime = 3;</code>
     */
    public boolean hasFinishTime() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int64 finishTime = 3;</code>
     */
    public long getFinishTime() {
      return finishTime_;
    }
    /**
     * <code>optional int64 finishTime = 3;</code>
     */
    public Builder setFinishTime(long value) {
      bitField0_ |= 0x00000004;
      finishTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 finishTime = 3;</code>
     */
    public Builder clearFinishTime() {
      bitField0_ = (bitField0_ & ~0x00000004);
      finishTime_ = 0L;
      onChanged();
      return this;
    }

    private int departureX_ ;
    /**
     * <code>optional int32 departureX = 4;</code>
     */
    public boolean hasDepartureX() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 departureX = 4;</code>
     */
    public int getDepartureX() {
      return departureX_;
    }
    /**
     * <code>optional int32 departureX = 4;</code>
     */
    public Builder setDepartureX(int value) {
      bitField0_ |= 0x00000008;
      departureX_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 departureX = 4;</code>
     */
    public Builder clearDepartureX() {
      bitField0_ = (bitField0_ & ~0x00000008);
      departureX_ = 0;
      onChanged();
      return this;
    }

    private int departureY_ ;
    /**
     * <code>optional int32 departureY = 5;</code>
     */
    public boolean hasDepartureY() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional int32 departureY = 5;</code>
     */
    public int getDepartureY() {
      return departureY_;
    }
    /**
     * <code>optional int32 departureY = 5;</code>
     */
    public Builder setDepartureY(int value) {
      bitField0_ |= 0x00000010;
      departureY_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 departureY = 5;</code>
     */
    public Builder clearDepartureY() {
      bitField0_ = (bitField0_ & ~0x00000010);
      departureY_ = 0;
      onChanged();
      return this;
    }

    private int targetedX_ ;
    /**
     * <code>optional int32 targetedX = 6;</code>
     */
    public boolean hasTargetedX() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>optional int32 targetedX = 6;</code>
     */
    public int getTargetedX() {
      return targetedX_;
    }
    /**
     * <code>optional int32 targetedX = 6;</code>
     */
    public Builder setTargetedX(int value) {
      bitField0_ |= 0x00000020;
      targetedX_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 targetedX = 6;</code>
     */
    public Builder clearTargetedX() {
      bitField0_ = (bitField0_ & ~0x00000020);
      targetedX_ = 0;
      onChanged();
      return this;
    }

    private int targetedY_ ;
    /**
     * <code>optional int32 targetedY = 7;</code>
     */
    public boolean hasTargetedY() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <code>optional int32 targetedY = 7;</code>
     */
    public int getTargetedY() {
      return targetedY_;
    }
    /**
     * <code>optional int32 targetedY = 7;</code>
     */
    public Builder setTargetedY(int value) {
      bitField0_ |= 0x00000040;
      targetedY_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 targetedY = 7;</code>
     */
    public Builder clearTargetedY() {
      bitField0_ = (bitField0_ & ~0x00000040);
      targetedY_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.PlayerWalkInfoRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.PlayerWalkInfoRt)
  private static final pb4client.PlayerWalkInfoRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.PlayerWalkInfoRt();
  }

  public static pb4client.PlayerWalkInfoRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<PlayerWalkInfoRt>
      PARSER = new com.google.protobuf.AbstractParser<PlayerWalkInfoRt>() {
    public PlayerWalkInfoRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlayerWalkInfoRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PlayerWalkInfoRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PlayerWalkInfoRt> getParserForType() {
    return PARSER;
  }

  public pb4client.PlayerWalkInfoRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
