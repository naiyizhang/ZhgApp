package com.example;

/**
 * Created by nyzhang on 2015/12/17.
 */
public class Test1 {
    public static void main(String[]args){
        Stu s=new Stu();
    }
}

class Stu{
    private String name="zhangsan";
    private String score;
    static {
        System.out.println("static code area");
    }
    {
        System.out.println("code area ");
        System.out.println("name="+name);
    }
    public Stu(){
        System.out.println("constructor");
        System.out.println("name="+name);
    }
    public void play(){
        System.out.println("play");
    }
}
