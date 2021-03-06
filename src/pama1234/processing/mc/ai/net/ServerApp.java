package pama1234.processing.mc.ai.net;

import java.io.IOException;
import java.net.ServerSocket;

import pama1234.processing.mc.ai.Const;
import pama1234.processing.mc.ai.net.util.ScrollBar;
import pama1234.processing.mc.ai.net.util.SocketSet;
import pama1234.processing.mc.ai.test.TestBlankComponent;
import pama1234.processing.util.app.UtilApp;

public class ServerApp extends UtilApp{
  public ServerSocket server;
  public SocketSet s;
  public int port;
  public Thread acceptThread;
  public ScrollBar[] data=new ScrollBar[18];
  public TestBlankComponent[] c;
  public ServerApp(int port) {
    this.port=port;
    try {
      server=new ServerSocket(port);
    }catch(IOException e) {
      e.printStackTrace();
    }
    s=new SocketSet(Const.controllerSize,Const.imageBufferSize);
  }
  @Override
  public void settings() {
    super.settings();
    noSmooth();
  }
  @Override
  public void init() {
    c=new TestBlankComponent[Const.imgCount];
    for(int i=0;i<c.length;i++) {
      c[i]=new TestBlankComponent(this);
      c[i].refresh();
      c[i].doBackground=false;
      gccenter.add.add(c[i]);
    }
    pcenter.minDisplayDist=pcenter.minDist=4;
    for(int i=0;i<data.length;i++) {
      data[i]=new ScrollBar(this,0,i*10,256,8);
      data[i].refresh();
      pcenter.add.add(data[i]);
    }
  }
  @Override
  public void display() {
    // int ts;
    // if(mousePressed) {
    //   switch(mouseButton) {
    //     case LEFT:
    //       stroke(0xc0ff0000);
    //       break;
    //     case CENTER:
    //       stroke(0xc000ff00);
    //       break;
    //     case RIGHT:
    //       stroke(0xc00000ff);
    //       break;
    //   }
    //   ts=28;
    // }else {
    //   stroke(0xc0ffffff);
    //   ts=32;
    // }
    // ellipse(cam.mouseX,cam.mouseY,ts,ts);
  }
  @Override
  public void update() {
    switch(s.state) {
      case SocketSet.unconnected: {
        acceptThread=new Thread() {
          @Override
          public void run() {
            s.accept(server);
            s.state=SocketSet.connected;
          }
        };
        s.state=SocketSet.connecting;
        acceptThread.start();
      }
        break;
      case SocketSet.connected: {
        s.read(18*4);
        for(int i=0;i<data.length;i++) data[i].data=s.getF();
        if(s.inLength==0) break;
        // s.putF(cam.mouseX);
        // s.putF(cam.mouseY);
        // s.putI(mousePressed?mouseButton:0);
        // s.putF(cam.x1());
        // s.putF(cam.y1());
        // s.putF(cam.w());
        // s.putF(cam.h());
        putImage();
        s.write();
      }
        break;
      case SocketSet.connecting: {}
        break;
      default:
        throw new IllegalArgumentException("Unexpected value: "+s.state);
    }
  }
  private void putImage() {
    for(int i=0;i<c.length;i++) c[i].toBuffer(s.outBuffer);
  }
  @Override
  public void exitActual() {
    System.out.println("ServerApp.exitActual()");
    super.exitActual();
  }
  public static void main(String[] args) {
    System.setProperty("sun.java2d.uiScale","1");
    new ServerApp(1234).run();
  }
}
