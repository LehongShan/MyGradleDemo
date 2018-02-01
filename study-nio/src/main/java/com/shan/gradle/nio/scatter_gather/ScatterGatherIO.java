package com.shan.gradle.nio.scatter_gather;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * 分散/聚集或向量I/O
 * Created by shanlehong on 2018/2/1.
 */
public class ScatterGatherIO {
    public static void main(String[] args) {
        String data = "Scattering and Gathering example shown in yiibai.com";
        gatherBytes(data);
        scatterBytes();

    }

    private static void gatherBytes(String data) {

        ByteBuffer byteBuffer1 = ByteBuffer.allocate(8);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(400);

        byteBuffer1.asIntBuffer().put(420);
        byteBuffer2.asCharBuffer().put(data);

        GatheringByteChannel gatherer  = createFileChannelInstance("D://java_nio//scatter_gather.txt",true);

        try {
            gatherer.write(new ByteBuffer[] { byteBuffer1, byteBuffer2 });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void scatterBytes() {
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(8);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(400);

        ScatteringByteChannel scatter  = createFileChannelInstance("D://java_nio//scatter_gather.txt",false);
        try {
            scatter.read(new ByteBuffer[]{byteBuffer1,byteBuffer2});
        } catch (IOException e) {
            e.printStackTrace();
        }

        byteBuffer1.rewind();
        byteBuffer2.rewind();

        int bufferOne = byteBuffer1.asIntBuffer().get();
        String bufferTwo = byteBuffer2.asCharBuffer().toString();

        System.out.println(bufferOne);
        System.out.println(bufferTwo);


    }

    private static FileChannel createFileChannelInstance(String file,boolean isOutput){
        FileChannel fileChannel = null;
        try {
            if (isOutput) {

                fileChannel = new FileOutputStream(file).getChannel();

            } else {
                fileChannel = new FileInputStream(file).getChannel();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileChannel;
    }


}
