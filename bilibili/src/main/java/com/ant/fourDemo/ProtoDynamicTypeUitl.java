package com.ant.fourDemo;

/**
 * 针对多种需要在网络总传递的Protobuf实体类型，使用枚举的方式在.proto文件中定义
 */
public class ProtoDynamicTypeUitl {
    public static PersonInfo.MyMessage build(int i){
        PersonInfo.MyMessage myMessage;
        if(i == 0){
            myMessage = PersonInfo
                    .MyMessage
                    .newBuilder()
                    .setDataType(PersonInfo.MyMessage.DataType.PersonType)
                    .setPerson(
                            PersonInfo
                                    .Person
                                    .newBuilder()
                                    .setId("uuid")
                                    .setName("徐传良")
                                    .build()
                    ).build();
        }else if(i == 1){
            myMessage = PersonInfo.MyMessage.newBuilder()
                    .setDataType(PersonInfo.MyMessage.DataType.Dog)
                    .setDog(PersonInfo.Dog.newBuilder().setAge(222).setName("i am dog").build())
                    .build();
        }else{
            myMessage = PersonInfo.MyMessage.newBuilder()
                    .setDataType(PersonInfo.MyMessage.DataType.Cat)
                    .setCat(PersonInfo.Cat.newBuilder().setAge(0).setName("i am cat").build())
                    .build();
        }
        return myMessage;
    }

    public static void printProtoDynamicType(PersonInfo.MyMessage myMessage){
        if(myMessage.getDataType() == PersonInfo.MyMessage.DataType.PersonType){
            System.out.println(myMessage.getPerson().getId());
            System.out.println(myMessage.getPerson().getName());
        }else if(myMessage.getDataType() == PersonInfo.MyMessage.DataType.Dog){
            System.out.println(myMessage.getDog().getName());
            System.out.println(myMessage.getDog().getAge());
        }else {
            System.out.println(myMessage.getCat().getAge());
            System.out.println(myMessage.getCat().getName());
        }
    }
}
