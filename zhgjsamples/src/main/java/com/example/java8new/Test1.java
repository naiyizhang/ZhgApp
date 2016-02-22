package com.example.java8new;

/**
 * Created by nyzhang on 2016/1/15.
 */
public class Test1 {

    public static void main(String[]args){
//        Converter<String,Integer> converter=f->Integer.valueOf(f);
//        Converter<String,Integer> converter=Integer::valueOf;
        Test1 test1=new Test1();
        A a=test1.new A();
        Converter<String,Integer> converter=a::startsWith;
        System.out.println(converter.convert("123"));



    }
    class A{
        public int startsWith(String s){
            return s.length();
        }
    }
    public interface Converter<F,T>{
        T convert(F f);
    }
}
