// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user_entities.proto

package com.nghia.grpc.entities.user;

/**
 * Protobuf type {@code FindUserByUsernameRequest}
 */
public final class FindUserByUsernameRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:FindUserByUsernameRequest)
    FindUserByUsernameRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FindUserByUsernameRequest.newBuilder() to construct.
  private FindUserByUsernameRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FindUserByUsernameRequest() {
    username_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new FindUserByUsernameRequest();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.nghia.grpc.entities.user.UserEntities.internal_static_FindUserByUsernameRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.nghia.grpc.entities.user.UserEntities.internal_static_FindUserByUsernameRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.nghia.grpc.entities.user.FindUserByUsernameRequest.class, com.nghia.grpc.entities.user.FindUserByUsernameRequest.Builder.class);
  }

  public static final int USERNAME_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object username_ = "";
  /**
   * <code>string username = 1;</code>
   * @return The username.
   */
  @java.lang.Override
  public java.lang.String getUsername() {
    java.lang.Object ref = username_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      username_ = s;
      return s;
    }
  }
  /**
   * <code>string username = 1;</code>
   * @return The bytes for username.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getUsernameBytes() {
    java.lang.Object ref = username_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      username_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(username_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, username_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(username_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, username_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.nghia.grpc.entities.user.FindUserByUsernameRequest)) {
      return super.equals(obj);
    }
    com.nghia.grpc.entities.user.FindUserByUsernameRequest other = (com.nghia.grpc.entities.user.FindUserByUsernameRequest) obj;

    if (!getUsername()
        .equals(other.getUsername())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + USERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getUsername().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.nghia.grpc.entities.user.FindUserByUsernameRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
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
   * Protobuf type {@code FindUserByUsernameRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:FindUserByUsernameRequest)
      com.nghia.grpc.entities.user.FindUserByUsernameRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.nghia.grpc.entities.user.UserEntities.internal_static_FindUserByUsernameRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.nghia.grpc.entities.user.UserEntities.internal_static_FindUserByUsernameRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.nghia.grpc.entities.user.FindUserByUsernameRequest.class, com.nghia.grpc.entities.user.FindUserByUsernameRequest.Builder.class);
    }

    // Construct using com.nghia.grpc.entities.user.FindUserByUsernameRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      username_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.nghia.grpc.entities.user.UserEntities.internal_static_FindUserByUsernameRequest_descriptor;
    }

    @java.lang.Override
    public com.nghia.grpc.entities.user.FindUserByUsernameRequest getDefaultInstanceForType() {
      return com.nghia.grpc.entities.user.FindUserByUsernameRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.nghia.grpc.entities.user.FindUserByUsernameRequest build() {
      com.nghia.grpc.entities.user.FindUserByUsernameRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.nghia.grpc.entities.user.FindUserByUsernameRequest buildPartial() {
      com.nghia.grpc.entities.user.FindUserByUsernameRequest result = new com.nghia.grpc.entities.user.FindUserByUsernameRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.nghia.grpc.entities.user.FindUserByUsernameRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.username_ = username_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.nghia.grpc.entities.user.FindUserByUsernameRequest) {
        return mergeFrom((com.nghia.grpc.entities.user.FindUserByUsernameRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.nghia.grpc.entities.user.FindUserByUsernameRequest other) {
      if (other == com.nghia.grpc.entities.user.FindUserByUsernameRequest.getDefaultInstance()) return this;
      if (!other.getUsername().isEmpty()) {
        username_ = other.username_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              username_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.lang.Object username_ = "";
    /**
     * <code>string username = 1;</code>
     * @return The username.
     */
    public java.lang.String getUsername() {
      java.lang.Object ref = username_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        username_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string username = 1;</code>
     * @return The bytes for username.
     */
    public com.google.protobuf.ByteString
        getUsernameBytes() {
      java.lang.Object ref = username_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        username_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string username = 1;</code>
     * @param value The username to set.
     * @return This builder for chaining.
     */
    public Builder setUsername(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      username_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string username = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearUsername() {
      username_ = getDefaultInstance().getUsername();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string username = 1;</code>
     * @param value The bytes for username to set.
     * @return This builder for chaining.
     */
    public Builder setUsernameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      username_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:FindUserByUsernameRequest)
  }

  // @@protoc_insertion_point(class_scope:FindUserByUsernameRequest)
  private static final com.nghia.grpc.entities.user.FindUserByUsernameRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.nghia.grpc.entities.user.FindUserByUsernameRequest();
  }

  public static com.nghia.grpc.entities.user.FindUserByUsernameRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FindUserByUsernameRequest>
      PARSER = new com.google.protobuf.AbstractParser<FindUserByUsernameRequest>() {
    @java.lang.Override
    public FindUserByUsernameRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<FindUserByUsernameRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FindUserByUsernameRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.nghia.grpc.entities.user.FindUserByUsernameRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

