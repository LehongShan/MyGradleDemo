package com.shan.gradle.nio.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Java NIO通道之间的数据传输
 * Created by shanlehong on 2018/2/1.
 */
public class TransferDemo {

    public static void main(String[] args) throws Exception {
        String relativelyPath = "D://java_nio";
        String[] inFileArr = new String[]{relativelyPath + "//transfer_1.txt", relativelyPath + "//transfer_2.txt",
                relativelyPath + "//transfer_3.txt", relativelyPath + "//transfer_4.txt"};
        String outFile = relativelyPath + "//transfer_out.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outFile));
        WritableByteChannel targetChannel = fileOutputStream.getChannel();
        for (int i = 0; i < inFileArr.length; i++) {
            String inFile = inFileArr[i];
            FileInputStream fileInputStream = new FileInputStream(inFile);
            FileChannel fileChannel = fileInputStream.getChannel();
            fileChannel.transferTo(0, fileChannel.size(), targetChannel);
            fileInputStream.close();
            fileChannel.close();
        }
        fileOutputStream.close();
        targetChannel.close();
        System.out.println("All jobs done...");
    }
}
