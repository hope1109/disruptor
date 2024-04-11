package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServerSocket {
    //定义一个socket入口
    private ServerSocketChannel serverSocket;
    //定义一个监听器
    Selector selector;
    public static void main(String[] args) throws IOException {
        NIOServerSocket nio =new NIOServerSocket();
        nio.initServer(8000);
        nio.listen();
    }
    public void initServer(int port) throws IOException {
        //获取一个serverSocket通道
        serverSocket = ServerSocketChannel.open();
        //设置为非阻塞状态（分为阻塞和非阻塞两种情况）
        serverSocket.configureBlocking(false);
        //将通道对应的serverSocketChannel绑定到端口上
        serverSocket.socket().bind(new InetSocketAddress(port));
        //获取一个通道管理器
        this.selector = Selector.open();
        //将通道管理器与通道进行绑定，并赋值SelectionKey.OP_ACCEPT事件
        //注册后，当事件到达后，select.select()会返回，如果没有返回，就一直阻塞。
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
    }
    public void listen() throws IOException {
        System.out.println("服务器启动");
        //轮询访问select.select()
        while(true) {
            //当事件到达时返回，否则一直阻塞
            //Channel channel = selector.select();
            //获取selector中选中项的迭代器，相中的项为注册事件。
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while(iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //删除已选的key，防止重复处理
                iterator.remove();
                handler(selectionKey);
            }
        }
    }
    public void handler(SelectionKey key) throws IOException {
        if(key.isAcceptable()) {
            handlerAccept(key);
        }else if(key.isReadable()) {
            handlerRead(key);
        }
    }
    public void handlerAccept(SelectionKey key) throws IOException {
        //获取以有的通道
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        //获取和客户端连接的通道
        SocketChannel accept = channel.accept();
        //设置为非阻塞
        accept.configureBlocking(false);
        // 在这里可以给客户端发送信息哦
        System.out.println("新的客户端连接");
        //连接成功之后，为了读取客户端传送的消息，需要设置读权限
        accept.register(selector, SelectionKey.OP_READ);
    }
    public void handlerRead(SelectionKey key) throws IOException {
        //服务器可读取消息，获取事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        //创建读取内容的缓存区buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        if(read > 0) {
            byte[] array = buffer.array();
            String msg = new String(array).trim();
            System.out.println("服务端收到信息：" + msg);

            //会写
            ByteBuffer byteBuffer = ByteBuffer.wrap("success".getBytes());
            channel.write(byteBuffer);
        }else {
            System.out.println("客户端关闭");
            key.cancel();
        }
    }
}
