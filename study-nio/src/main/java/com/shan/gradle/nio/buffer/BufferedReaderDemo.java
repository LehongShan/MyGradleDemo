package com.shan.gradle.nio.buffer;

import java.io.*;

/**
 * Created by shanlehong on 2018/2/1.
 */
public class BufferedReaderDemo {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = null;
        try{
            FileInputStream inputStream = new FileInputStream("D://java_nio//testin.txt");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("Reading the Line of testout.txt file: \n" + reader.readLine());

        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
