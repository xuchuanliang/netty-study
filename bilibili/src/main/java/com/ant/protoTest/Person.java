//// Generated by the protocol buffer compiler.  DO NOT EDIT!
//// source: protobuf/Person.proto
//
//package com.ant.protoTest;
//
//public final class Person {
//  private Person() {}
//  public static void registerAllExtensions(
//      com.google.protobuf.ExtensionRegistryLite registry) {
//  }
//
//  public static void registerAllExtensions(
//      com.google.protobuf.ExtensionRegistry registry) {
//    registerAllExtensions(
//        (com.google.protobuf.ExtensionRegistryLite) registry);
//  }
//  public interface InfoOrBuilder extends
//      // @@protoc_insertion_point(interface_extends:com.ant.protoTest.Info)
//      com.google.protobuf.MessageOrBuilder {
//
//    /**
//     * <code>required string id = 1;</code>
//     * @return Whether the id field is set.
//     */
//    boolean hasId();
//    /**
//     * <code>required string id = 1;</code>
//     * @return The id.
//     */
//    java.lang.String getId();
//    /**
//     * <code>required string id = 1;</code>
//     * @return The bytes for id.
//     */
//    com.google.protobuf.ByteString
//        getIdBytes();
//
//    /**
//     * <code>optional string name = 2;</code>
//     * @return Whether the name field is set.
//     */
//    boolean hasName();
//    /**
//     * <code>optional string name = 2;</code>
//     * @return The name.
//     */
//    java.lang.String getName();
//    /**
//     * <code>optional string name = 2;</code>
//     * @return The bytes for name.
//     */
//    com.google.protobuf.ByteString
//        getNameBytes();
//  }
//  /**
//   * Protobuf type {@code com.ant.protoTest.Info}
//   */
//  public  static final class Info extends
//      com.google.protobuf.GeneratedMessageV3 implements
//      // @@protoc_insertion_point(message_implements:com.ant.protoTest.Info)
//      InfoOrBuilder {
//  private static final long serialVersionUID = 0L;
//    // Use Info.newBuilder() to construct.
//    private Info(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
//      super(builder);
//    }
//    private Info() {
//      id_ = "";
//      name_ = "";
//    }
//
//    @java.lang.Override
//    @SuppressWarnings({"unused"})
//    protected java.lang.Object newInstance(
//        UnusedPrivateParameter unused) {
//      return new Info();
//    }
//
//    @java.lang.Override
//    public final com.google.protobuf.UnknownFieldSet
//    getUnknownFields() {
//      return this.unknownFields;
//    }
//    private Info(
//        com.google.protobuf.CodedInputStream input,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      this();
//      if (extensionRegistry == null) {
//        throw new java.lang.NullPointerException();
//      }
//      int mutable_bitField0_ = 0;
//      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
//          com.google.protobuf.UnknownFieldSet.newBuilder();
//      try {
//        boolean done = false;
//        while (!done) {
//          int tag = input.readTag();
//          switch (tag) {
//            case 0:
//              done = true;
//              break;
//            case 10: {
//              com.google.protobuf.ByteString bs = input.readBytes();
//              bitField0_ |= 0x00000001;
//              id_ = bs;
//              break;
//            }
//            case 18: {
//              com.google.protobuf.ByteString bs = input.readBytes();
//              bitField0_ |= 0x00000002;
//              name_ = bs;
//              break;
//            }
//            default: {
//              if (!parseUnknownField(
//                  input, unknownFields, extensionRegistry, tag)) {
//                done = true;
//              }
//              break;
//            }
//          }
//        }
//      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
//        throw e.setUnfinishedMessage(this);
//      } catch (java.io.IOException e) {
//        throw new com.google.protobuf.InvalidProtocolBufferException(
//            e).setUnfinishedMessage(this);
//      } finally {
//        this.unknownFields = unknownFields.build();
//        makeExtensionsImmutable();
//      }
//    }
//    public static final com.google.protobuf.Descriptors.Descriptor
//        getDescriptor() {
//      return internal_static_com_ant_protoTest_Info_descriptor;
//    }
//
//    @java.lang.Override
//    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
//        internalGetFieldAccessorTable() {
//      return internal_static_com_ant_protoTest_Info_fieldAccessorTable
//          .ensureFieldAccessorsInitialized(
//              com.ant.protoTest.Person.Info.class, com.ant.protoTest.Person.Info.Builder.class);
//    }
//
//    private int bitField0_;
//    public static final int ID_FIELD_NUMBER = 1;
//    private volatile java.lang.Object id_;
//    /**
//     * <code>required string id = 1;</code>
//     * @return Whether the id field is set.
//     */
//    public boolean hasId() {
//      return ((bitField0_ & 0x00000001) != 0);
//    }
//    /**
//     * <code>required string id = 1;</code>
//     * @return The id.
//     */
//    public java.lang.String getId() {
//      java.lang.Object ref = id_;
//      if (ref instanceof java.lang.String) {
//        return (java.lang.String) ref;
//      } else {
//        com.google.protobuf.ByteString bs =
//            (com.google.protobuf.ByteString) ref;
//        java.lang.String s = bs.toStringUtf8();
//        if (bs.isValidUtf8()) {
//          id_ = s;
//        }
//        return s;
//      }
//    }
//    /**
//     * <code>required string id = 1;</code>
//     * @return The bytes for id.
//     */
//    public com.google.protobuf.ByteString
//        getIdBytes() {
//      java.lang.Object ref = id_;
//      if (ref instanceof java.lang.String) {
//        com.google.protobuf.ByteString b =
//            com.google.protobuf.ByteString.copyFromUtf8(
//                (java.lang.String) ref);
//        id_ = b;
//        return b;
//      } else {
//        return (com.google.protobuf.ByteString) ref;
//      }
//    }
//
//    public static final int NAME_FIELD_NUMBER = 2;
//    private volatile java.lang.Object name_;
//    /**
//     * <code>optional string name = 2;</code>
//     * @return Whether the name field is set.
//     */
//    public boolean hasName() {
//      return ((bitField0_ & 0x00000002) != 0);
//    }
//    /**
//     * <code>optional string name = 2;</code>
//     * @return The name.
//     */
//    public java.lang.String getName() {
//      java.lang.Object ref = name_;
//      if (ref instanceof java.lang.String) {
//        return (java.lang.String) ref;
//      } else {
//        com.google.protobuf.ByteString bs =
//            (com.google.protobuf.ByteString) ref;
//        java.lang.String s = bs.toStringUtf8();
//        if (bs.isValidUtf8()) {
//          name_ = s;
//        }
//        return s;
//      }
//    }
//    /**
//     * <code>optional string name = 2;</code>
//     * @return The bytes for name.
//     */
//    public com.google.protobuf.ByteString
//        getNameBytes() {
//      java.lang.Object ref = name_;
//      if (ref instanceof java.lang.String) {
//        com.google.protobuf.ByteString b =
//            com.google.protobuf.ByteString.copyFromUtf8(
//                (java.lang.String) ref);
//        name_ = b;
//        return b;
//      } else {
//        return (com.google.protobuf.ByteString) ref;
//      }
//    }
//
//    private byte memoizedIsInitialized = -1;
//    @java.lang.Override
//    public final boolean isInitialized() {
//      byte isInitialized = memoizedIsInitialized;
//      if (isInitialized == 1) return true;
//      if (isInitialized == 0) return false;
//
//      if (!hasId()) {
//        memoizedIsInitialized = 0;
//        return false;
//      }
//      memoizedIsInitialized = 1;
//      return true;
//    }
//
//    @java.lang.Override
//    public void writeTo(com.google.protobuf.CodedOutputStream output)
//                        throws java.io.IOException {
//      if (((bitField0_ & 0x00000001) != 0)) {
//        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, id_);
//      }
//      if (((bitField0_ & 0x00000002) != 0)) {
//        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
//      }
//      unknownFields.writeTo(output);
//    }
//
//    @java.lang.Override
//    public int getSerializedSize() {
//      int size = memoizedSize;
//      if (size != -1) return size;
//
//      size = 0;
//      if (((bitField0_ & 0x00000001) != 0)) {
//        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, id_);
//      }
//      if (((bitField0_ & 0x00000002) != 0)) {
//        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
//      }
//      size += unknownFields.getSerializedSize();
//      memoizedSize = size;
//      return size;
//    }
//
//    @java.lang.Override
//    public boolean equals(final java.lang.Object obj) {
//      if (obj == this) {
//       return true;
//      }
//      if (!(obj instanceof com.ant.protoTest.Person.Info)) {
//        return super.equals(obj);
//      }
//      com.ant.protoTest.Person.Info other = (com.ant.protoTest.Person.Info) obj;
//
//      if (hasId() != other.hasId()) return false;
//      if (hasId()) {
//        if (!getId()
//            .equals(other.getId())) return false;
//      }
//      if (hasName() != other.hasName()) return false;
//      if (hasName()) {
//        if (!getName()
//            .equals(other.getName())) return false;
//      }
//      if (!unknownFields.equals(other.unknownFields)) return false;
//      return true;
//    }
//
//    @java.lang.Override
//    public int hashCode() {
//      if (memoizedHashCode != 0) {
//        return memoizedHashCode;
//      }
//      int hash = 41;
//      hash = (19 * hash) + getDescriptor().hashCode();
//      if (hasId()) {
//        hash = (37 * hash) + ID_FIELD_NUMBER;
//        hash = (53 * hash) + getId().hashCode();
//      }
//      if (hasName()) {
//        hash = (37 * hash) + NAME_FIELD_NUMBER;
//        hash = (53 * hash) + getName().hashCode();
//      }
//      hash = (29 * hash) + unknownFields.hashCode();
//      memoizedHashCode = hash;
//      return hash;
//    }
//
//    public static com.ant.protoTest.Person.Info parseFrom(
//        java.nio.ByteBuffer data)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      return PARSER.parseFrom(data);
//    }
//    public static com.ant.protoTest.Person.Info parseFrom(
//        java.nio.ByteBuffer data,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      return PARSER.parseFrom(data, extensionRegistry);
//    }
//    public static com.ant.protoTest.Person.Info parseFrom(
//        com.google.protobuf.ByteString data)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      return PARSER.parseFrom(data);
//    }
//    public static com.ant.protoTest.Person.Info parseFrom(
//        com.google.protobuf.ByteString data,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      return PARSER.parseFrom(data, extensionRegistry);
//    }
//    public static com.ant.protoTest.Person.Info parseFrom(byte[] data)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      return PARSER.parseFrom(data);
//    }
//    public static com.ant.protoTest.Person.Info parseFrom(
//        byte[] data,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      return PARSER.parseFrom(data, extensionRegistry);
//    }
//    public static com.ant.protoTest.Person.Info parseFrom(java.io.InputStream input)
//        throws java.io.IOException {
//      return com.google.protobuf.GeneratedMessageV3
//          .parseWithIOException(PARSER, input);
//    }
//    public static com.ant.protoTest.Person.Info parseFrom(
//        java.io.InputStream input,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws java.io.IOException {
//      return com.google.protobuf.GeneratedMessageV3
//          .parseWithIOException(PARSER, input, extensionRegistry);
//    }
//    public static com.ant.protoTest.Person.Info parseDelimitedFrom(java.io.InputStream input)
//        throws java.io.IOException {
//      return com.google.protobuf.GeneratedMessageV3
//          .parseDelimitedWithIOException(PARSER, input);
//    }
//    public static com.ant.protoTest.Person.Info parseDelimitedFrom(
//        java.io.InputStream input,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws java.io.IOException {
//      return com.google.protobuf.GeneratedMessageV3
//          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
//    }
//    public static com.ant.protoTest.Person.Info parseFrom(
//        com.google.protobuf.CodedInputStream input)
//        throws java.io.IOException {
//      return com.google.protobuf.GeneratedMessageV3
//          .parseWithIOException(PARSER, input);
//    }
//    public static com.ant.protoTest.Person.Info parseFrom(
//        com.google.protobuf.CodedInputStream input,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws java.io.IOException {
//      return com.google.protobuf.GeneratedMessageV3
//          .parseWithIOException(PARSER, input, extensionRegistry);
//    }
//
//    @java.lang.Override
//    public Builder newBuilderForType() { return newBuilder(); }
//    public static Builder newBuilder() {
//      return DEFAULT_INSTANCE.toBuilder();
//    }
//    public static Builder newBuilder(com.ant.protoTest.Person.Info prototype) {
//      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
//    }
//    @java.lang.Override
//    public Builder toBuilder() {
//      return this == DEFAULT_INSTANCE
//          ? new Builder() : new Builder().mergeFrom(this);
//    }
//
//    @java.lang.Override
//    protected Builder newBuilderForType(
//        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
//      Builder builder = new Builder(parent);
//      return builder;
//    }
//    /**
//     * Protobuf type {@code com.ant.protoTest.Info}
//     */
//    public static final class Builder extends
//        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
//        // @@protoc_insertion_point(builder_implements:com.ant.protoTest.Info)
//        com.ant.protoTest.Person.InfoOrBuilder {
//      public static final com.google.protobuf.Descriptors.Descriptor
//          getDescriptor() {
//        return internal_static_com_ant_protoTest_Info_descriptor;
//      }
//
//      @java.lang.Override
//      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
//          internalGetFieldAccessorTable() {
//        return internal_static_com_ant_protoTest_Info_fieldAccessorTable
//            .ensureFieldAccessorsInitialized(
//                com.ant.protoTest.Person.Info.class, com.ant.protoTest.Person.Info.Builder.class);
//      }
//
//      // Construct using com.ant.protoTest.Person.Info.newBuilder()
//      private Builder() {
//        maybeForceBuilderInitialization();
//      }
//
//      private Builder(
//          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
//        super(parent);
//        maybeForceBuilderInitialization();
//      }
//      private void maybeForceBuilderInitialization() {
//        if (com.google.protobuf.GeneratedMessageV3
//                .alwaysUseFieldBuilders) {
//        }
//      }
//      @java.lang.Override
//      public Builder clear() {
//        super.clear();
//        id_ = "";
//        bitField0_ = (bitField0_ & ~0x00000001);
//        name_ = "";
//        bitField0_ = (bitField0_ & ~0x00000002);
//        return this;
//      }
//
//      @java.lang.Override
//      public com.google.protobuf.Descriptors.Descriptor
//          getDescriptorForType() {
//        return internal_static_com_ant_protoTest_Info_descriptor;
//      }
//
//      @java.lang.Override
//      public com.ant.protoTest.Person.Info getDefaultInstanceForType() {
//        return getDefaultInstance();
//      }
//
//      @java.lang.Override
//      public com.ant.protoTest.Person.Info build() {
//        com.ant.protoTest.Person.Info result = buildPartial();
//        if (!result.isInitialized()) {
//          throw newUninitializedMessageException(result);
//        }
//        return result;
//      }
//
//      @java.lang.Override
//      public com.ant.protoTest.Person.Info buildPartial() {
//        com.ant.protoTest.Person.Info result = new com.ant.protoTest.Person.Info(this);
//        int from_bitField0_ = bitField0_;
//        int to_bitField0_ = 0;
//        if (((from_bitField0_ & 0x00000001) != 0)) {
//          to_bitField0_ |= 0x00000001;
//        }
//        result.id_ = id_;
//        if (((from_bitField0_ & 0x00000002) != 0)) {
//          to_bitField0_ |= 0x00000002;
//        }
//        result.name_ = name_;
//        result.bitField0_ = to_bitField0_;
//        onBuilt();
//        return result;
//      }
//
//      @java.lang.Override
//      public Builder clone() {
//        return super.clone();
//      }
//      @java.lang.Override
//      public Builder setField(
//          com.google.protobuf.Descriptors.FieldDescriptor field,
//          java.lang.Object value) {
//        return super.setField(field, value);
//      }
//      @java.lang.Override
//      public Builder clearField(
//          com.google.protobuf.Descriptors.FieldDescriptor field) {
//        return super.clearField(field);
//      }
//      @java.lang.Override
//      public Builder clearOneof(
//          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
//        return super.clearOneof(oneof);
//      }
//      @java.lang.Override
//      public Builder setRepeatedField(
//          com.google.protobuf.Descriptors.FieldDescriptor field,
//          int index, java.lang.Object value) {
//        return super.setRepeatedField(field, index, value);
//      }
//      @java.lang.Override
//      public Builder addRepeatedField(
//          com.google.protobuf.Descriptors.FieldDescriptor field,
//          java.lang.Object value) {
//        return super.addRepeatedField(field, value);
//      }
//      @java.lang.Override
//      public Builder mergeFrom(com.google.protobuf.Message other) {
//        if (other instanceof com.ant.protoTest.Person.Info) {
//          return mergeFrom((com.ant.protoTest.Person.Info)other);
//        } else {
//          super.mergeFrom(other);
//          return this;
//        }
//      }
//
//      public Builder mergeFrom(com.ant.protoTest.Person.Info other) {
//        if (other == getDefaultInstance()) return this;
//        if (other.hasId()) {
//          bitField0_ |= 0x00000001;
//          id_ = other.id_;
//          onChanged();
//        }
//        if (other.hasName()) {
//          bitField0_ |= 0x00000002;
//          name_ = other.name_;
//          onChanged();
//        }
//        this.mergeUnknownFields(other.unknownFields);
//        onChanged();
//        return this;
//      }
//
//      @java.lang.Override
//      public final boolean isInitialized() {
//        if (!hasId()) {
//          return false;
//        }
//        return true;
//      }
//
//      @java.lang.Override
//      public Builder mergeFrom(
//          com.google.protobuf.CodedInputStream input,
//          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//          throws java.io.IOException {
//        com.ant.protoTest.Person.Info parsedMessage = null;
//        try {
//          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
//        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
//          parsedMessage = (com.ant.protoTest.Person.Info) e.getUnfinishedMessage();
//          throw e.unwrapIOException();
//        } finally {
//          if (parsedMessage != null) {
//            mergeFrom(parsedMessage);
//          }
//        }
//        return this;
//      }
//      private int bitField0_;
//
//      private java.lang.Object id_ = "";
//      /**
//       * <code>required string id = 1;</code>
//       * @return Whether the id field is set.
//       */
//      public boolean hasId() {
//        return ((bitField0_ & 0x00000001) != 0);
//      }
//      /**
//       * <code>required string id = 1;</code>
//       * @return The id.
//       */
//      public java.lang.String getId() {
//        java.lang.Object ref = id_;
//        if (!(ref instanceof java.lang.String)) {
//          com.google.protobuf.ByteString bs =
//              (com.google.protobuf.ByteString) ref;
//          java.lang.String s = bs.toStringUtf8();
//          if (bs.isValidUtf8()) {
//            id_ = s;
//          }
//          return s;
//        } else {
//          return (java.lang.String) ref;
//        }
//      }
//      /**
//       * <code>required string id = 1;</code>
//       * @return The bytes for id.
//       */
//      public com.google.protobuf.ByteString
//          getIdBytes() {
//        java.lang.Object ref = id_;
//        if (ref instanceof String) {
//          com.google.protobuf.ByteString b =
//              com.google.protobuf.ByteString.copyFromUtf8(
//                  (java.lang.String) ref);
//          id_ = b;
//          return b;
//        } else {
//          return (com.google.protobuf.ByteString) ref;
//        }
//      }
//      /**
//       * <code>required string id = 1;</code>
//       * @param value The id to set.
//       * @return This builder for chaining.
//       */
//      public Builder setId(
//          java.lang.String value) {
//        if (value == null) {
//    throw new NullPointerException();
//  }
//  bitField0_ |= 0x00000001;
//        id_ = value;
//        onChanged();
//        return this;
//      }
//      /**
//       * <code>required string id = 1;</code>
//       * @return This builder for chaining.
//       */
//      public Builder clearId() {
//        bitField0_ = (bitField0_ & ~0x00000001);
//        id_ = getDefaultInstance().getId();
//        onChanged();
//        return this;
//      }
//      /**
//       * <code>required string id = 1;</code>
//       * @param value The bytes for id to set.
//       * @return This builder for chaining.
//       */
//      public Builder setIdBytes(
//          com.google.protobuf.ByteString value) {
//        if (value == null) {
//    throw new NullPointerException();
//  }
//  bitField0_ |= 0x00000001;
//        id_ = value;
//        onChanged();
//        return this;
//      }
//
//      private java.lang.Object name_ = "";
//      /**
//       * <code>optional string name = 2;</code>
//       * @return Whether the name field is set.
//       */
//      public boolean hasName() {
//        return ((bitField0_ & 0x00000002) != 0);
//      }
//      /**
//       * <code>optional string name = 2;</code>
//       * @return The name.
//       */
//      public java.lang.String getName() {
//        java.lang.Object ref = name_;
//        if (!(ref instanceof java.lang.String)) {
//          com.google.protobuf.ByteString bs =
//              (com.google.protobuf.ByteString) ref;
//          java.lang.String s = bs.toStringUtf8();
//          if (bs.isValidUtf8()) {
//            name_ = s;
//          }
//          return s;
//        } else {
//          return (java.lang.String) ref;
//        }
//      }
//      /**
//       * <code>optional string name = 2;</code>
//       * @return The bytes for name.
//       */
//      public com.google.protobuf.ByteString
//          getNameBytes() {
//        java.lang.Object ref = name_;
//        if (ref instanceof String) {
//          com.google.protobuf.ByteString b =
//              com.google.protobuf.ByteString.copyFromUtf8(
//                  (java.lang.String) ref);
//          name_ = b;
//          return b;
//        } else {
//          return (com.google.protobuf.ByteString) ref;
//        }
//      }
//      /**
//       * <code>optional string name = 2;</code>
//       * @param value The name to set.
//       * @return This builder for chaining.
//       */
//      public Builder setName(
//          java.lang.String value) {
//        if (value == null) {
//    throw new NullPointerException();
//  }
//  bitField0_ |= 0x00000002;
//        name_ = value;
//        onChanged();
//        return this;
//      }
//      /**
//       * <code>optional string name = 2;</code>
//       * @return This builder for chaining.
//       */
//      public Builder clearName() {
//        bitField0_ = (bitField0_ & ~0x00000002);
//        name_ = getDefaultInstance().getName();
//        onChanged();
//        return this;
//      }
//      /**
//       * <code>optional string name = 2;</code>
//       * @param value The bytes for name to set.
//       * @return This builder for chaining.
//       */
//      public Builder setNameBytes(
//          com.google.protobuf.ByteString value) {
//        if (value == null) {
//    throw new NullPointerException();
//  }
//  bitField0_ |= 0x00000002;
//        name_ = value;
//        onChanged();
//        return this;
//      }
//      @java.lang.Override
//      public final Builder setUnknownFields(
//          final com.google.protobuf.UnknownFieldSet unknownFields) {
//        return super.setUnknownFields(unknownFields);
//      }
//
//      @java.lang.Override
//      public final Builder mergeUnknownFields(
//          final com.google.protobuf.UnknownFieldSet unknownFields) {
//        return super.mergeUnknownFields(unknownFields);
//      }
//
//
//      // @@protoc_insertion_point(builder_scope:com.ant.protoTest.Info)
//    }
//
//    // @@protoc_insertion_point(class_scope:com.ant.protoTest.Info)
//    private static final com.ant.protoTest.Person.Info DEFAULT_INSTANCE;
//    static {
//      DEFAULT_INSTANCE = new com.ant.protoTest.Person.Info();
//    }
//
//    public static com.ant.protoTest.Person.Info getDefaultInstance() {
//      return DEFAULT_INSTANCE;
//    }
//
//    @java.lang.Deprecated public static final com.google.protobuf.Parser<Info>
//        PARSER = new com.google.protobuf.AbstractParser<Info>() {
//      @java.lang.Override
//      public Info parsePartialFrom(
//          com.google.protobuf.CodedInputStream input,
//          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//          throws com.google.protobuf.InvalidProtocolBufferException {
//        return new Info(input, extensionRegistry);
//      }
//    };
//
//    public static com.google.protobuf.Parser<Info> parser() {
//      return PARSER;
//    }
//
//    @java.lang.Override
//    public com.google.protobuf.Parser<Info> getParserForType() {
//      return PARSER;
//    }
//
//    @java.lang.Override
//    public com.ant.protoTest.Person.Info getDefaultInstanceForType() {
//      return DEFAULT_INSTANCE;
//    }
//
//  }
//
//  private static final com.google.protobuf.Descriptors.Descriptor
//    internal_static_com_ant_protoTest_Info_descriptor;
//  private static final
//    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
//      internal_static_com_ant_protoTest_Info_fieldAccessorTable;
//
//  public static com.google.protobuf.Descriptors.FileDescriptor
//      getDescriptor() {
//    return descriptor;
//  }
//  private static  com.google.protobuf.Descriptors.FileDescriptor
//      descriptor;
//  static {
//    java.lang.String[] descriptorData = {
//      "\n\025protobuf/Person.proto\022\021com.ant.protoTe" +
//      "st\" \n\004Info\022\n\n\002id\030\001 \002(\t\022\014\n\004name\030\002 \001(\tB\033\n\021" +
//      "com.ant.protoTestB\006Person"
//    };
//    descriptor = com.google.protobuf.Descriptors.FileDescriptor
//      .internalBuildGeneratedFileFrom(descriptorData,
//        new com.google.protobuf.Descriptors.FileDescriptor[] {
//        });
//    internal_static_com_ant_protoTest_Info_descriptor =
//      getDescriptor().getMessageTypes().get(0);
//    internal_static_com_ant_protoTest_Info_fieldAccessorTable = new
//      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
//        internal_static_com_ant_protoTest_Info_descriptor,
//        new java.lang.String[] { "Id", "Name", });
//  }
//
//  // @@protoc_insertion_point(outer_class_scope)
//}
