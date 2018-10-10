// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * <pre>
 * Public-&gt;Game
 * 通知玩家联盟总动员结束
 * </pre>
 *
 * Protobuf type {@code pb4server.AllianceCompetitionOverNotic2GTell}
 */
public  final class AllianceCompetitionOverNotic2GTell extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AllianceCompetitionOverNotic2GTell)
    AllianceCompetitionOverNotic2GTellOrBuilder {
  // Use AllianceCompetitionOverNotic2GTell.newBuilder() to construct.
  private AllianceCompetitionOverNotic2GTell(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceCompetitionOverNotic2GTell() {
    rankLv_ = 0;
    rank_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllianceCompetitionOverNotic2GTell(
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

            rankLv_ = input.readInt32();
            break;
          }
          case 16: {

            rank_ = input.readInt32();
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
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalHkt.internal_static_pb4server_AllianceCompetitionOverNotic2GTell_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_AllianceCompetitionOverNotic2GTell_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AllianceCompetitionOverNotic2GTell.class, pb4server.AllianceCompetitionOverNotic2GTell.Builder.class);
  }

  public static final int RANKLV_FIELD_NUMBER = 1;
  private int rankLv_;
  /**
   * <code>int32 rankLv = 1;</code>
   */
  public int getRankLv() {
    return rankLv_;
  }

  public static final int RANK_FIELD_NUMBER = 2;
  private int rank_;
  /**
   * <code>int32 rank = 2;</code>
   */
  public int getRank() {
    return rank_;
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
    if (rankLv_ != 0) {
      output.writeInt32(1, rankLv_);
    }
    if (rank_ != 0) {
      output.writeInt32(2, rank_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (rankLv_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rankLv_);
    }
    if (rank_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, rank_);
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
    if (!(obj instanceof pb4server.AllianceCompetitionOverNotic2GTell)) {
      return super.equals(obj);
    }
    pb4server.AllianceCompetitionOverNotic2GTell other = (pb4server.AllianceCompetitionOverNotic2GTell) obj;

    boolean result = true;
    result = result && (getRankLv()
        == other.getRankLv());
    result = result && (getRank()
        == other.getRank());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RANKLV_FIELD_NUMBER;
    hash = (53 * hash) + getRankLv();
    hash = (37 * hash) + RANK_FIELD_NUMBER;
    hash = (53 * hash) + getRank();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.AllianceCompetitionOverNotic2GTell parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceCompetitionOverNotic2GTell parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionOverNotic2GTell parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceCompetitionOverNotic2GTell parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionOverNotic2GTell parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceCompetitionOverNotic2GTell parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionOverNotic2GTell parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceCompetitionOverNotic2GTell parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionOverNotic2GTell parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AllianceCompetitionOverNotic2GTell parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceCompetitionOverNotic2GTell parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceCompetitionOverNotic2GTell parseFrom(
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
  public static Builder newBuilder(pb4server.AllianceCompetitionOverNotic2GTell prototype) {
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
   * Public-&gt;Game
   * 通知玩家联盟总动员结束
   * </pre>
   *
   * Protobuf type {@code pb4server.AllianceCompetitionOverNotic2GTell}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AllianceCompetitionOverNotic2GTell)
      pb4server.AllianceCompetitionOverNotic2GTellOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_AllianceCompetitionOverNotic2GTell_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_AllianceCompetitionOverNotic2GTell_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AllianceCompetitionOverNotic2GTell.class, pb4server.AllianceCompetitionOverNotic2GTell.Builder.class);
    }

    // Construct using pb4server.AllianceCompetitionOverNotic2GTell.newBuilder()
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
      rankLv_ = 0;

      rank_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_AllianceCompetitionOverNotic2GTell_descriptor;
    }

    public pb4server.AllianceCompetitionOverNotic2GTell getDefaultInstanceForType() {
      return pb4server.AllianceCompetitionOverNotic2GTell.getDefaultInstance();
    }

    public pb4server.AllianceCompetitionOverNotic2GTell build() {
      pb4server.AllianceCompetitionOverNotic2GTell result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AllianceCompetitionOverNotic2GTell buildPartial() {
      pb4server.AllianceCompetitionOverNotic2GTell result = new pb4server.AllianceCompetitionOverNotic2GTell(this);
      result.rankLv_ = rankLv_;
      result.rank_ = rank_;
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
      if (other instanceof pb4server.AllianceCompetitionOverNotic2GTell) {
        return mergeFrom((pb4server.AllianceCompetitionOverNotic2GTell)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AllianceCompetitionOverNotic2GTell other) {
      if (other == pb4server.AllianceCompetitionOverNotic2GTell.getDefaultInstance()) return this;
      if (other.getRankLv() != 0) {
        setRankLv(other.getRankLv());
      }
      if (other.getRank() != 0) {
        setRank(other.getRank());
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
      pb4server.AllianceCompetitionOverNotic2GTell parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AllianceCompetitionOverNotic2GTell) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int rankLv_ ;
    /**
     * <code>int32 rankLv = 1;</code>
     */
    public int getRankLv() {
      return rankLv_;
    }
    /**
     * <code>int32 rankLv = 1;</code>
     */
    public Builder setRankLv(int value) {
      
      rankLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 rankLv = 1;</code>
     */
    public Builder clearRankLv() {
      
      rankLv_ = 0;
      onChanged();
      return this;
    }

    private int rank_ ;
    /**
     * <code>int32 rank = 2;</code>
     */
    public int getRank() {
      return rank_;
    }
    /**
     * <code>int32 rank = 2;</code>
     */
    public Builder setRank(int value) {
      
      rank_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 rank = 2;</code>
     */
    public Builder clearRank() {
      
      rank_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.AllianceCompetitionOverNotic2GTell)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AllianceCompetitionOverNotic2GTell)
  private static final pb4server.AllianceCompetitionOverNotic2GTell DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AllianceCompetitionOverNotic2GTell();
  }

  public static pb4server.AllianceCompetitionOverNotic2GTell getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllianceCompetitionOverNotic2GTell>
      PARSER = new com.google.protobuf.AbstractParser<AllianceCompetitionOverNotic2GTell>() {
    public AllianceCompetitionOverNotic2GTell parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceCompetitionOverNotic2GTell(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceCompetitionOverNotic2GTell> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceCompetitionOverNotic2GTell> getParserForType() {
    return PARSER;
  }

  public pb4server.AllianceCompetitionOverNotic2GTell getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
