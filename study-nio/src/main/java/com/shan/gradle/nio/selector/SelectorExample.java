package com.shan.gradle.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 选择器(Selector)
 * Created by shanlehong on 2018/2/2.
 */
public class SelectorExample {

    public static void main(String[] args) throws IOException {
        // Get the selector
        Selector selector = Selector.open();

        System.out.println("Selector is open for making connection: "+selector.isOpen());
        // Get the server socket channel and register using selector
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8081);
        serverSocketChannel.bind(hostAddress);
        serverSocketChannel.configureBlocking(false);

        int ops =serverSocketChannel.validOps();
        SelectionKey selectKey = serverSocketChannel.register(selector,ops);
        for (;;){
            System.out.println("Waiting for the select operation...");
            int noOfKeys = selector.select();
            System.out.println("The Number of selected keys are: " + noOfKeys);
            Set selectedKeys = selector.selectedKeys();
            Iterator itr = selectedKeys.iterator();

            while(itr.hasNext()){
                SelectionKey ky = (SelectionKey) itr.next();
                if (ky.isAcceptable()){
                    // The new client connection is accepted
                    SocketChannel client = serverSocketChannel.accept();
                    client.configureBlocking(false);
                    // The new connection is added to a selector
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("The new connection is accepted from the client: " + client);

                }else if (ky.isReadable()){
                    SocketChannel client = (SocketChannel) ky.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    client.read(buffer);
                    String output = new String(buffer.array()).trim();
                    System.out.println("Message read from client: " + output);
                    if (output.equals("Bye Bye")) {
                        client.close();
                        System.out.println("The Client messages are complete; close the session.");
                    }
                }

            }

        }

    }
}
