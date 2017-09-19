package my201706.Epoll;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class EpollServer {
    public static void main(String[] args) throws InterruptedException {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
            ssc.configureBlocking(false);

            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            ByteBuffer writeBuff = ByteBuffer.allocate(128);
            writeBuff.put("received".getBytes());
            writeBuff.flip();

            while (true) {
//            	TimeUnit.MILLISECONDS.sleep(10000);
            	System.out.println("`````````````````````````````````````````````");
                int nReady = selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                System.out.println(nReady+"nReadynReadynReadynReady");
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        SelectionKey connectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
                        connectionKey.attach(new EpollTask(socketChannel, connectionKey));
                    }
                    else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);
                        readBuff.flip();


                        EpollTask conn = (EpollTask)key.attachment();
                        conn.onRead(getInt(readBuff));

                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                    else if (key.isWritable()) {
                        writeBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();

                        EpollTask conn = (EpollTask)key.attachment();
                        key.interestOps(SelectionKey.OP_READ);
                        conn.onWrite();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getInt(ByteBuffer buf) {
        int r = 0;
        while (buf.hasRemaining()) {
            r *= 10;
            r += buf.get() - '0';
        }

        return r;
    }
}