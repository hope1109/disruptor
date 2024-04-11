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
    //����һ��socket���
    private ServerSocketChannel serverSocket;
    //����һ��������
    Selector selector;
    public static void main(String[] args) throws IOException {
        NIOServerSocket nio =new NIOServerSocket();
        nio.initServer(8000);
        nio.listen();
    }
    public void initServer(int port) throws IOException {
        //��ȡһ��serverSocketͨ��
        serverSocket = ServerSocketChannel.open();
        //����Ϊ������״̬����Ϊ�����ͷ��������������
        serverSocket.configureBlocking(false);
        //��ͨ����Ӧ��serverSocketChannel�󶨵��˿���
        serverSocket.socket().bind(new InetSocketAddress(port));
        //��ȡһ��ͨ��������
        this.selector = Selector.open();
        //��ͨ����������ͨ�����а󶨣�����ֵSelectionKey.OP_ACCEPT�¼�
        //ע��󣬵��¼������select.select()�᷵�أ����û�з��أ���һֱ������
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
    }
    public void listen() throws IOException {
        System.out.println("����������");
        //��ѯ����select.select()
        while(true) {
            //���¼�����ʱ���أ�����һֱ����
            //Channel channel = selector.select();
            //��ȡselector��ѡ����ĵ����������е���Ϊע���¼���
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while(iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //ɾ����ѡ��key����ֹ�ظ�����
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
        //��ȡ���е�ͨ��
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        //��ȡ�Ϳͻ������ӵ�ͨ��
        SocketChannel accept = channel.accept();
        //����Ϊ������
        accept.configureBlocking(false);
        // ��������Ը��ͻ��˷�����ϢŶ
        System.out.println("�µĿͻ�������");
        //���ӳɹ�֮��Ϊ�˶�ȡ�ͻ��˴��͵���Ϣ����Ҫ���ö�Ȩ��
        accept.register(selector, SelectionKey.OP_READ);
    }
    public void handlerRead(SelectionKey key) throws IOException {
        //�������ɶ�ȡ��Ϣ����ȡ�¼�������Socketͨ��
        SocketChannel channel = (SocketChannel) key.channel();
        //������ȡ���ݵĻ�����buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        if(read > 0) {
            byte[] array = buffer.array();
            String msg = new String(array).trim();
            System.out.println("������յ���Ϣ��" + msg);

            //��д
            ByteBuffer byteBuffer = ByteBuffer.wrap("success".getBytes());
            channel.write(byteBuffer);
        }else {
            System.out.println("�ͻ��˹ر�");
            key.cancel();
        }
    }
}
