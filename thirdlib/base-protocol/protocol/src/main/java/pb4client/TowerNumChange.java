// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3111
 * 服务器 -&gt; 客户端
 * 爬塔券变化主推
 * </pre>
 *
 * Protobuf type {@code client2server.TowerNumChange}
 */
public  final class TowerNumChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.TowerNumChange)
    TowerNumChangeOrBuilder {
  // Use TowerNumChange.newBuilder() to construct.
  private TowerNumChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TowerNumChange() {
    towerNum_ = 0;
    towerNumLastTime_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TowerNumChange(
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
            towerNum_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            towerNumLastTime_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_TowerNumChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_TowerNumChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.TowerNumChange.class, pb4client.TowerNumChange.Builder.class);
  }

  private int bitField0_;
  public static final int TOWERNUM_FIELD_NUMBER = 1;
  private int towerNum_;
  /**
   * <pre>
   *爬塔券数量
   * </pre>
   *
   * <code>required int32 towerNum = 1;</code>
   */
  public boolean hasTowerNum() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *爬塔券数量
   * </pre>
   *
   * <code>required int32 towerNum = 1;</code>
   */
  public int getTowerNum() {
    return towerNum_;
  }

  public static final int TOWERNUMLASTTIME_FIELD_NUMBER = 2;
  private int towerNumLastTime_;
  /**
   * <pre>
   *爬塔券上次变化主推
   * </pre>
   *
   * <code>required int32 towerNumLastTime = 2;</code>
   */
  public boolean hasTowerNumLastTime() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *爬塔券上次变化主推
   * </pre>
   *
   * <code>required int32 towerNumLastTime = 2;</code>
   */
  public int getTowerNumLastTime() {
    return towerNumLastTime_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasTowerNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasTowerNumLastTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, towerNum_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, towerNumLastTime_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, towerNum_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, towerNumLastTime_);
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
    if (!(obj instanceof pb4client.TowerNumChange)) {
      return super.equals(obj);
    }
    pb4client.TowerNumChange other = (pb4client.TowerNumChange) obj;

    boolean result = true;
    result = result && (hasTowerNum() == other.hasTowerNum());
    if (hasTowerNum()) {
      result = result && (getTowerNum()
          == other.getTowerNum());
    }
    result = result && (hasTowerNumLastTime() == other.hasTowerNumLastTime());
    if (hasTowerNumLastTime()) {
      result = result && (getTowerNumLastTime()
          == other.getTowerNumLastTime());
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
    if (hasTowerNum()) {
      hash = (37 * hash) + TOWERNUM_FIELD_NUMBER;
      hash = (53 * hash) + getTowerNum();
    }
    if (hasTowerNumLastTime()) {
      hash = (37 * hash) + TOWERNUMLASTTIME_FIELD_NUMBER;
      hash = (53 * hash) + getTowerNumLastTime();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.TowerNumChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TowerNumChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TowerNumChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TowerNumChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TowerNumChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TowerNumChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TowerNumChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.TowerNumChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.TowerNumChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.TowerNumChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.TowerNumChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.TowerNumChange parseFrom(
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
  public static Builder newBuilder(pb4client.TowerNumChange prototype) {
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
   * msgType = 3111
   * 服务器 -&gt; 客户端
   * 爬塔券变化主推
   * </pre>
   *
   * Protobuf type {@code client2server.TowerNumChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.TowerNumChange)
      pb4client.TowerNumChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_TowerNumChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_TowerNumChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.TowerNumChange.class, pb4client.TowerNumChange.Builder.class);
    }

    // Construct using pb4client.TowerNumChange.newBuilder()
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
      towerNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      towerNumLastTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_TowerNumChange_descriptor;
    }

    public pb4client.TowerNumChange getDefaultInstanceForType() {
      return pb4client.TowerNumChange.getDefaultInstance();
    }

    public pb4client.TowerNumChange build() {
      pb4client.TowerNumChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.TowerNumChange buildPartial() {
      pb4client.TowerNumChange result = new pb4client.TowerNumChange(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.towerNum_ = towerNum_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.towerNumLastTime_ = towerNumLastTime_;
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
      if (other instanceof pb4client.TowerNumChange) {
        return mergeFrom((pb4client.TowerNumChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.TowerNumChange other) {
      if (other == pb4client.TowerNumChange.getDefaultInstance()) return this;
      if (other.hasTowerNum()) {
        setTowerNum(other.getTowerNum());
      }
      if (other.hasTowerNumLastTime()) {
        setTowerNumLastTime(other.getTowerNumLastTime());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasTowerNum()) {
        return false;
      }
      if (!hasTowerNumLastTime()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.TowerNumChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.TowerNumChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int towerNum_ ;
    /**
     * <pre>
     *爬塔券数量
     * </pre>
     *
     * <code>required int32 towerNum = 1;</code>
     */
    public boolean hasTowerNum() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *爬塔券数量
     * </pre>
     *
     * <code>required int32 towerNum = 1;</code>
     */
    public int getTowerNum() {
      return towerNum_;
    }
    /**
     * <pre>
     *爬塔券数量
     * </pre>
     *
     * <code>required int32 towerNum = 1;</code>
     */
    public Builder setTowerNum(int value) {
      bitField0_ |= 0x00000001;
      towerNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *爬塔券数量
     * </pre>
     *
     * <code>required int32 towerNum = 1;</code>
     */
    public Builder clearTowerNum() {
      bitField0_ = (bitField0_ & ~0x00000001);
      towerNum_ = 0;
      onChanged();
      return this;
    }

    private int towerNumLastTime_ ;
    /**
     * <pre>
     *爬塔券上次变化主推
     * </pre>
     *
     * <code>required int32 towerNumLastTime = 2;</code>
     */
    public boolean hasTowerNumLastTime() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *爬塔券上次变化主推
     * </pre>
     *
     * <code>required int32 towerNumLastTime = 2;</code>
     */
    public int getTowerNumLastTime() {
      return towerNumLastTime_;
    }
    /**
     * <pre>
     *爬塔券上次变化主推
     * </pre>
     *
     * <code>required int32 towerNumLastTime = 2;</code>
     */
    public Builder setTowerNumLastTime(int value) {
      bitField0_ |= 0x00000002;
      towerNumLastTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *爬塔券上次变化主推
     * </pre>
     *
     * <code>required int32 towerNumLastTime = 2;</code>
     */
    public Builder clearTowerNumLastTime() {
      bitField0_ = (bitField0_ & ~0x00000002);
      towerNumLastTime_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.TowerNumChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.TowerNumChange)
  private static final pb4client.TowerNumChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.TowerNumChange();
  }

  public static pb4client.TowerNumChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<TowerNumChange>
      PARSER = new com.google.protobuf.AbstractParser<TowerNumChange>() {
    public TowerNumChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TowerNumChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TowerNumChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TowerNumChange> getParserForType() {
    return PARSER;
  }

  public pb4client.TowerNumChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
