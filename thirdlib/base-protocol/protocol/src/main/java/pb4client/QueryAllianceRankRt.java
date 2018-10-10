// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.QueryAllianceRankRt}
 */
public  final class QueryAllianceRankRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.QueryAllianceRankRt)
    QueryAllianceRankRtOrBuilder {
  // Use QueryAllianceRankRt.newBuilder() to construct.
  private QueryAllianceRankRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryAllianceRankRt() {
    rt_ = 0;
    queryAllianceRankVos_ = java.util.Collections.emptyList();
    page_ = 0;
    myAllianceRank_ = 0;
    myAllianceScore_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QueryAllianceRankRt(
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
            rt_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              queryAllianceRankVos_ = new java.util.ArrayList<pb4client.QueryAllianceRankVo>();
              mutable_bitField0_ |= 0x00000002;
            }
            queryAllianceRankVos_.add(
                input.readMessage(pb4client.QueryAllianceRankVo.PARSER, extensionRegistry));
            break;
          }
          case 24: {
            bitField0_ |= 0x00000002;
            page_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000004;
            myAllianceRank_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000008;
            myAllianceScore_ = input.readInt64();
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
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        queryAllianceRankVos_ = java.util.Collections.unmodifiableList(queryAllianceRankVos_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_QueryAllianceRankRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_QueryAllianceRankRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.QueryAllianceRankRt.class, pb4client.QueryAllianceRankRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int QUERYALLIANCERANKVOS_FIELD_NUMBER = 2;
  private java.util.List<pb4client.QueryAllianceRankVo> queryAllianceRankVos_;
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
   */
  public java.util.List<pb4client.QueryAllianceRankVo> getQueryAllianceRankVosList() {
    return queryAllianceRankVos_;
  }
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
   */
  public java.util.List<? extends pb4client.QueryAllianceRankVoOrBuilder> 
      getQueryAllianceRankVosOrBuilderList() {
    return queryAllianceRankVos_;
  }
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
   */
  public int getQueryAllianceRankVosCount() {
    return queryAllianceRankVos_.size();
  }
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
   */
  public pb4client.QueryAllianceRankVo getQueryAllianceRankVos(int index) {
    return queryAllianceRankVos_.get(index);
  }
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
   */
  public pb4client.QueryAllianceRankVoOrBuilder getQueryAllianceRankVosOrBuilder(
      int index) {
    return queryAllianceRankVos_.get(index);
  }

  public static final int PAGE_FIELD_NUMBER = 3;
  private int page_;
  /**
   * <code>optional int32 page = 3;</code>
   */
  public boolean hasPage() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>optional int32 page = 3;</code>
   */
  public int getPage() {
    return page_;
  }

  public static final int MYALLIANCERANK_FIELD_NUMBER = 4;
  private int myAllianceRank_;
  /**
   * <pre>
   * 我帮派的排行  0表示不再前100
   * </pre>
   *
   * <code>optional int32 myAllianceRank = 4;</code>
   */
  public boolean hasMyAllianceRank() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 我帮派的排行  0表示不再前100
   * </pre>
   *
   * <code>optional int32 myAllianceRank = 4;</code>
   */
  public int getMyAllianceRank() {
    return myAllianceRank_;
  }

  public static final int MYALLIANCESCORE_FIELD_NUMBER = 5;
  private long myAllianceScore_;
  /**
   * <pre>
   * 我帮派的成绩
   * </pre>
   *
   * <code>optional int64 myAllianceScore = 5;</code>
   */
  public boolean hasMyAllianceScore() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 我帮派的成绩
   * </pre>
   *
   * <code>optional int64 myAllianceScore = 5;</code>
   */
  public long getMyAllianceScore() {
    return myAllianceScore_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasRt()) {
      memoizedIsInitialized = 0;
      return false;
    }
    for (int i = 0; i < getQueryAllianceRankVosCount(); i++) {
      if (!getQueryAllianceRankVos(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, rt_);
    }
    for (int i = 0; i < queryAllianceRankVos_.size(); i++) {
      output.writeMessage(2, queryAllianceRankVos_.get(i));
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(3, page_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(4, myAllianceRank_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt64(5, myAllianceScore_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rt_);
    }
    for (int i = 0; i < queryAllianceRankVos_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, queryAllianceRankVos_.get(i));
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, page_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, myAllianceRank_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(5, myAllianceScore_);
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
    if (!(obj instanceof pb4client.QueryAllianceRankRt)) {
      return super.equals(obj);
    }
    pb4client.QueryAllianceRankRt other = (pb4client.QueryAllianceRankRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getQueryAllianceRankVosList()
        .equals(other.getQueryAllianceRankVosList());
    result = result && (hasPage() == other.hasPage());
    if (hasPage()) {
      result = result && (getPage()
          == other.getPage());
    }
    result = result && (hasMyAllianceRank() == other.hasMyAllianceRank());
    if (hasMyAllianceRank()) {
      result = result && (getMyAllianceRank()
          == other.getMyAllianceRank());
    }
    result = result && (hasMyAllianceScore() == other.hasMyAllianceScore());
    if (hasMyAllianceScore()) {
      result = result && (getMyAllianceScore()
          == other.getMyAllianceScore());
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
    if (hasRt()) {
      hash = (37 * hash) + RT_FIELD_NUMBER;
      hash = (53 * hash) + getRt();
    }
    if (getQueryAllianceRankVosCount() > 0) {
      hash = (37 * hash) + QUERYALLIANCERANKVOS_FIELD_NUMBER;
      hash = (53 * hash) + getQueryAllianceRankVosList().hashCode();
    }
    if (hasPage()) {
      hash = (37 * hash) + PAGE_FIELD_NUMBER;
      hash = (53 * hash) + getPage();
    }
    if (hasMyAllianceRank()) {
      hash = (37 * hash) + MYALLIANCERANK_FIELD_NUMBER;
      hash = (53 * hash) + getMyAllianceRank();
    }
    if (hasMyAllianceScore()) {
      hash = (37 * hash) + MYALLIANCESCORE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getMyAllianceScore());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.QueryAllianceRankRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryAllianceRankRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryAllianceRankRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryAllianceRankRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryAllianceRankRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryAllianceRankRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryAllianceRankRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryAllianceRankRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryAllianceRankRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.QueryAllianceRankRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryAllianceRankRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryAllianceRankRt parseFrom(
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
  public static Builder newBuilder(pb4client.QueryAllianceRankRt prototype) {
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
   * Protobuf type {@code client2server.QueryAllianceRankRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.QueryAllianceRankRt)
      pb4client.QueryAllianceRankRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryAllianceRankRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryAllianceRankRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.QueryAllianceRankRt.class, pb4client.QueryAllianceRankRt.Builder.class);
    }

    // Construct using pb4client.QueryAllianceRankRt.newBuilder()
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
        getQueryAllianceRankVosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (queryAllianceRankVosBuilder_ == null) {
        queryAllianceRankVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        queryAllianceRankVosBuilder_.clear();
      }
      page_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      myAllianceRank_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      myAllianceScore_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryAllianceRankRt_descriptor;
    }

    public pb4client.QueryAllianceRankRt getDefaultInstanceForType() {
      return pb4client.QueryAllianceRankRt.getDefaultInstance();
    }

    public pb4client.QueryAllianceRankRt build() {
      pb4client.QueryAllianceRankRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.QueryAllianceRankRt buildPartial() {
      pb4client.QueryAllianceRankRt result = new pb4client.QueryAllianceRankRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (queryAllianceRankVosBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          queryAllianceRankVos_ = java.util.Collections.unmodifiableList(queryAllianceRankVos_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.queryAllianceRankVos_ = queryAllianceRankVos_;
      } else {
        result.queryAllianceRankVos_ = queryAllianceRankVosBuilder_.build();
      }
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000002;
      }
      result.page_ = page_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000004;
      }
      result.myAllianceRank_ = myAllianceRank_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000008;
      }
      result.myAllianceScore_ = myAllianceScore_;
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
      if (other instanceof pb4client.QueryAllianceRankRt) {
        return mergeFrom((pb4client.QueryAllianceRankRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.QueryAllianceRankRt other) {
      if (other == pb4client.QueryAllianceRankRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (queryAllianceRankVosBuilder_ == null) {
        if (!other.queryAllianceRankVos_.isEmpty()) {
          if (queryAllianceRankVos_.isEmpty()) {
            queryAllianceRankVos_ = other.queryAllianceRankVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureQueryAllianceRankVosIsMutable();
            queryAllianceRankVos_.addAll(other.queryAllianceRankVos_);
          }
          onChanged();
        }
      } else {
        if (!other.queryAllianceRankVos_.isEmpty()) {
          if (queryAllianceRankVosBuilder_.isEmpty()) {
            queryAllianceRankVosBuilder_.dispose();
            queryAllianceRankVosBuilder_ = null;
            queryAllianceRankVos_ = other.queryAllianceRankVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
            queryAllianceRankVosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getQueryAllianceRankVosFieldBuilder() : null;
          } else {
            queryAllianceRankVosBuilder_.addAllMessages(other.queryAllianceRankVos_);
          }
        }
      }
      if (other.hasPage()) {
        setPage(other.getPage());
      }
      if (other.hasMyAllianceRank()) {
        setMyAllianceRank(other.getMyAllianceRank());
      }
      if (other.hasMyAllianceScore()) {
        setMyAllianceScore(other.getMyAllianceScore());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRt()) {
        return false;
      }
      for (int i = 0; i < getQueryAllianceRankVosCount(); i++) {
        if (!getQueryAllianceRankVos(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.QueryAllianceRankRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.QueryAllianceRankRt) e.getUnfinishedMessage();
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
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.QueryAllianceRankVo> queryAllianceRankVos_ =
      java.util.Collections.emptyList();
    private void ensureQueryAllianceRankVosIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        queryAllianceRankVos_ = new java.util.ArrayList<pb4client.QueryAllianceRankVo>(queryAllianceRankVos_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.QueryAllianceRankVo, pb4client.QueryAllianceRankVo.Builder, pb4client.QueryAllianceRankVoOrBuilder> queryAllianceRankVosBuilder_;

    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public java.util.List<pb4client.QueryAllianceRankVo> getQueryAllianceRankVosList() {
      if (queryAllianceRankVosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(queryAllianceRankVos_);
      } else {
        return queryAllianceRankVosBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public int getQueryAllianceRankVosCount() {
      if (queryAllianceRankVosBuilder_ == null) {
        return queryAllianceRankVos_.size();
      } else {
        return queryAllianceRankVosBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public pb4client.QueryAllianceRankVo getQueryAllianceRankVos(int index) {
      if (queryAllianceRankVosBuilder_ == null) {
        return queryAllianceRankVos_.get(index);
      } else {
        return queryAllianceRankVosBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public Builder setQueryAllianceRankVos(
        int index, pb4client.QueryAllianceRankVo value) {
      if (queryAllianceRankVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.set(index, value);
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public Builder setQueryAllianceRankVos(
        int index, pb4client.QueryAllianceRankVo.Builder builderForValue) {
      if (queryAllianceRankVosBuilder_ == null) {
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.set(index, builderForValue.build());
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public Builder addQueryAllianceRankVos(pb4client.QueryAllianceRankVo value) {
      if (queryAllianceRankVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.add(value);
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public Builder addQueryAllianceRankVos(
        int index, pb4client.QueryAllianceRankVo value) {
      if (queryAllianceRankVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.add(index, value);
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public Builder addQueryAllianceRankVos(
        pb4client.QueryAllianceRankVo.Builder builderForValue) {
      if (queryAllianceRankVosBuilder_ == null) {
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.add(builderForValue.build());
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public Builder addQueryAllianceRankVos(
        int index, pb4client.QueryAllianceRankVo.Builder builderForValue) {
      if (queryAllianceRankVosBuilder_ == null) {
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.add(index, builderForValue.build());
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public Builder addAllQueryAllianceRankVos(
        java.lang.Iterable<? extends pb4client.QueryAllianceRankVo> values) {
      if (queryAllianceRankVosBuilder_ == null) {
        ensureQueryAllianceRankVosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, queryAllianceRankVos_);
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public Builder clearQueryAllianceRankVos() {
      if (queryAllianceRankVosBuilder_ == null) {
        queryAllianceRankVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public Builder removeQueryAllianceRankVos(int index) {
      if (queryAllianceRankVosBuilder_ == null) {
        ensureQueryAllianceRankVosIsMutable();
        queryAllianceRankVos_.remove(index);
        onChanged();
      } else {
        queryAllianceRankVosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public pb4client.QueryAllianceRankVo.Builder getQueryAllianceRankVosBuilder(
        int index) {
      return getQueryAllianceRankVosFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public pb4client.QueryAllianceRankVoOrBuilder getQueryAllianceRankVosOrBuilder(
        int index) {
      if (queryAllianceRankVosBuilder_ == null) {
        return queryAllianceRankVos_.get(index);  } else {
        return queryAllianceRankVosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public java.util.List<? extends pb4client.QueryAllianceRankVoOrBuilder> 
         getQueryAllianceRankVosOrBuilderList() {
      if (queryAllianceRankVosBuilder_ != null) {
        return queryAllianceRankVosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(queryAllianceRankVos_);
      }
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public pb4client.QueryAllianceRankVo.Builder addQueryAllianceRankVosBuilder() {
      return getQueryAllianceRankVosFieldBuilder().addBuilder(
          pb4client.QueryAllianceRankVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public pb4client.QueryAllianceRankVo.Builder addQueryAllianceRankVosBuilder(
        int index) {
      return getQueryAllianceRankVosFieldBuilder().addBuilder(
          index, pb4client.QueryAllianceRankVo.getDefaultInstance());
    }
    /**
     * <pre>
     * 排行内容
     * </pre>
     *
     * <code>repeated .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
     */
    public java.util.List<pb4client.QueryAllianceRankVo.Builder> 
         getQueryAllianceRankVosBuilderList() {
      return getQueryAllianceRankVosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.QueryAllianceRankVo, pb4client.QueryAllianceRankVo.Builder, pb4client.QueryAllianceRankVoOrBuilder> 
        getQueryAllianceRankVosFieldBuilder() {
      if (queryAllianceRankVosBuilder_ == null) {
        queryAllianceRankVosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.QueryAllianceRankVo, pb4client.QueryAllianceRankVo.Builder, pb4client.QueryAllianceRankVoOrBuilder>(
                queryAllianceRankVos_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        queryAllianceRankVos_ = null;
      }
      return queryAllianceRankVosBuilder_;
    }

    private int page_ ;
    /**
     * <code>optional int32 page = 3;</code>
     */
    public boolean hasPage() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 page = 3;</code>
     */
    public int getPage() {
      return page_;
    }
    /**
     * <code>optional int32 page = 3;</code>
     */
    public Builder setPage(int value) {
      bitField0_ |= 0x00000004;
      page_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 page = 3;</code>
     */
    public Builder clearPage() {
      bitField0_ = (bitField0_ & ~0x00000004);
      page_ = 0;
      onChanged();
      return this;
    }

    private int myAllianceRank_ ;
    /**
     * <pre>
     * 我帮派的排行  0表示不再前100
     * </pre>
     *
     * <code>optional int32 myAllianceRank = 4;</code>
     */
    public boolean hasMyAllianceRank() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 我帮派的排行  0表示不再前100
     * </pre>
     *
     * <code>optional int32 myAllianceRank = 4;</code>
     */
    public int getMyAllianceRank() {
      return myAllianceRank_;
    }
    /**
     * <pre>
     * 我帮派的排行  0表示不再前100
     * </pre>
     *
     * <code>optional int32 myAllianceRank = 4;</code>
     */
    public Builder setMyAllianceRank(int value) {
      bitField0_ |= 0x00000008;
      myAllianceRank_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 我帮派的排行  0表示不再前100
     * </pre>
     *
     * <code>optional int32 myAllianceRank = 4;</code>
     */
    public Builder clearMyAllianceRank() {
      bitField0_ = (bitField0_ & ~0x00000008);
      myAllianceRank_ = 0;
      onChanged();
      return this;
    }

    private long myAllianceScore_ ;
    /**
     * <pre>
     * 我帮派的成绩
     * </pre>
     *
     * <code>optional int64 myAllianceScore = 5;</code>
     */
    public boolean hasMyAllianceScore() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     * 我帮派的成绩
     * </pre>
     *
     * <code>optional int64 myAllianceScore = 5;</code>
     */
    public long getMyAllianceScore() {
      return myAllianceScore_;
    }
    /**
     * <pre>
     * 我帮派的成绩
     * </pre>
     *
     * <code>optional int64 myAllianceScore = 5;</code>
     */
    public Builder setMyAllianceScore(long value) {
      bitField0_ |= 0x00000010;
      myAllianceScore_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 我帮派的成绩
     * </pre>
     *
     * <code>optional int64 myAllianceScore = 5;</code>
     */
    public Builder clearMyAllianceScore() {
      bitField0_ = (bitField0_ & ~0x00000010);
      myAllianceScore_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:client2server.QueryAllianceRankRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.QueryAllianceRankRt)
  private static final pb4client.QueryAllianceRankRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.QueryAllianceRankRt();
  }

  public static pb4client.QueryAllianceRankRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<QueryAllianceRankRt>
      PARSER = new com.google.protobuf.AbstractParser<QueryAllianceRankRt>() {
    public QueryAllianceRankRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryAllianceRankRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryAllianceRankRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryAllianceRankRt> getParserForType() {
    return PARSER;
  }

  public pb4client.QueryAllianceRankRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

