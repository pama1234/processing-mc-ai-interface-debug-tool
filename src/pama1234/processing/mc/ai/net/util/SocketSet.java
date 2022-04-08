package pama1234.processing.mc.ai.net.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class SocketSet{
  public static final int unconnected=0,connected=1,connecting=2;
  public Socket socket;
  public ByteBuffer inBuffer;
  public ByteBuffer outBuffer;
  public ReadableByteChannel inChannel;
  public WritableByteChannel outChannel;
  public int inLength,outLength;
  public int state;
  public SocketSet(int size) {
    inBuffer=ByteBuffer.allocate(size);
    outBuffer=ByteBuffer.allocate(size);
  }
  public void connect(String host,int port) {
    try {
      socket=new Socket(host,port);
      state=connected;
      inChannel=Channels.newChannel(socket.getInputStream());
      outChannel=Channels.newChannel(socket.getOutputStream());
    }catch(UnknownHostException e) {
      e.printStackTrace();
    }catch(IOException e) {
      e.printStackTrace();
    }
  }
  public void accept(ServerSocket server) {
    try {
      socket=server.accept();
      state=connected;
      inChannel=Channels.newChannel(socket.getInputStream());
      outChannel=Channels.newChannel(socket.getOutputStream());
    }catch(IOException e) {
      e.printStackTrace();
    }
  }
  public void close() {
    try {
      inChannel.close();
      outChannel.close();
      socket.close();
      inChannel=null;
      outChannel=null;
      socket=null;
    }catch(IOException e) {
      e.printStackTrace();
    }
    state=unconnected;
  }
  public void read(int in) {
    inBuffer.clear();
    inLength=0;
    inBuffer.limit(in);
    try {
      while(inLength<inBuffer.limit()) {
        inLength+=inChannel.read(inBuffer);
      }
    }catch(IOException e) {
      e.printStackTrace();
      close();
    }
    inBuffer.flip();
  }
  public void read() {
    inBuffer.clear();
    try {
      inLength=inChannel.read(inBuffer);
    }catch(IOException e) {
      e.printStackTrace();
      close();
    }
    inBuffer.flip();
  }
  public void write() {
    outBuffer.flip();
    try {
      outLength=outChannel.write(outBuffer);
    }catch(IOException e) {
      e.printStackTrace();
      close();
    }
    outBuffer.clear();
  }
  public void putF(float in) {
    outBuffer.putFloat(in);
  }
  public void putI(int in) {
    outBuffer.putInt(in);
  }
  public float getF() {
    return inBuffer.getFloat();
  }
  public int getI() {
    return inBuffer.getInt();
  }
  public void put(byte in) {
    outBuffer.put(in);
  }
  public byte get() {
    return inBuffer.get();
  }
}
