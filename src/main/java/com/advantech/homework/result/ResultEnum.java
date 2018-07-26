//package com.advantech.homework.result;
//
//public enum ResultEnum {
//    //枚举维护,将异常统一在一个地方进行维护,
//    //这样的好处就是在以后你要改语句的情况下,通过枚举
//    //不需要动其他地方的业务逻辑代码,只需要改动这里的语句就可以了
//    UNKONW_ERROR(-1,"未知错误"),
//    SUCCESS(0,"成功"),
//    PRIMARY_SCHOOL(100,"PRIMARY_SCHOOL"),
//    MIDDLE_SCHOOL(101,"MIDDLE_SCHOOL"),
//    ;
//    private Integer code;
//    private String msg;
//
//
//    ResultEnum(Integer code, String msg) {
//        this.code = code;
//        this.msg = msg;
//    }
//    //枚举里面只要给get方法就可以了,因为枚举的使用都是直接用构造方法来创建,不会再从新set
//    public Integer getCode() {
//        return code;
//    }
//    public String getMsg() {
//        return msg;
//    }
//}
