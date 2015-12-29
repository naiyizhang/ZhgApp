package com.example.urlconnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nyzhang on 2015/12/22.
 */
public class TestConn {
    public static void main(String[]args){

        try {
            URL url=new URL("");
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
