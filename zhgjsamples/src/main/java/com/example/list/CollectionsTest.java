package com.example.list;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nyzhang on 2015/12/17.
 */
public class CollectionsTest {
    public static  void main(String[]args){
        Map<String,Integer> map=new HashMap<String,Integer>(){
            //
            {
                put("tom",100);
                put("jack",88);
            }
        };
        System.out.println(map);

        List<String> list=Collections.<String>emptyList();

    }
}
