//package com.ant.protoTest;
//
//import com.google.protobuf.InvalidProtocolBufferException;
//
//public class TestMain {
//    public static void main(String[] args) throws InvalidProtocolBufferException {
//        Person.Info info = Person.Info.newBuilder().setId("徐传良").setName("哈哈哈").build();
//        byte[] array = info.toByteArray();
//        Person.Info info1 = Person.Info.parseFrom(array);
//        System.out.println(info1.getId());
//        System.out.println(info1.getName());
//        for(int i = 0;i<array.length;i++){
//            System.out.print(array[i]);
//            System.out.print(" ");
//        }
//    }
//}
