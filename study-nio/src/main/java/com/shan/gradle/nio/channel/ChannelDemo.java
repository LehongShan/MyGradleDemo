package com.shan.gradle.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by shanlehong on 2018/1/31.
 */
public class ChannelDemo {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("D://java_nio//testin.txt");
        ReadableByteChannel source = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("D://java_nio//testout.txt");
        WritableByteChannel destination = fileOutputStream.getChannel();
        copyData(source,destination);
        source.close();
        destination.close();
        System.out.println("Copy Data finished.");
    }

    private static void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocateDirect(20*1024);
        while (src.read(buffer)!=-1){
            // The buffer is used to drained
            buffer.flip();
            // keep sure that buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            buffer.clear(); // Now the buffer is empty, ready for the filling
        }
    }
}
