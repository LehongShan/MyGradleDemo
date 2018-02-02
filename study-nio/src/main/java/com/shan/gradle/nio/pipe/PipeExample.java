package com.shan.gradle.nio.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by shanlehong on 2018/2/2.
 */
public class PipeExample {

    public static void main(String[] args) throws IOException {
        // The Pipe is created
        Pipe pipe = Pipe.open();
        // For accessing the pipe sink channel
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String td = "Data is successfully sent for checking the java NIO Channel Pipe.";
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byteBuffer.clear();
        byteBuffer.put(td.getBytes());
        byteBuffer.flip();
        while(byteBuffer.hasRemaining()){
            sinkChannel.write(byteBuffer);
        }

        // For accessing the pipe source channel
        Pipe.SourceChannel sourceChannel = pipe.source();
        byteBuffer = ByteBuffer.allocate(512);
        // The data is write to the console
        while(sourceChannel.read(byteBuffer)>0){
            byteBuffer.flip();
            while(byteBuffer.hasRemaining()){
                char TestData = (char) byteBuffer.get();
                System.out.print(TestData);
            }

            byteBuffer.clear();
        }

    }
}
