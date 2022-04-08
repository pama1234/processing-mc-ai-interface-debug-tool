package pama1234.processing.mc.ai.net;

import pama1234.processing.mc.ai.Const;
import pama1234.processing.mc.ai.net.util.ScrollBar;
import pama1234.processing.mc.ai.net.util.SocketSet;
import pama1234.processing.mc.ai.test.TestBlankComponent;
import pama1234.processing.util.app.UtilApp;

public class ClientApp extends UtilApp{
  public SocketSet s;
  public String host;
  public int port;
  // public float x,y,camX,camY,camW,camH;
  // public int button;
  public ScrollBar[] data=new ScrollBar[18];
  public TestBlankComponent[] c;
  public ClientApp(String host,int port) {
    this.host=host;
    this.port=port;
    s=new SocketSet(Const.bufferSize);
    s.connect(host,port);
  }
  @Override
  public void settings() {
    super.settings();
    // noSmooth();
  }
  @Override
  public void init() {
    c=new TestBlankComponent[Const.t];
    for(int i=0;i<c.length;i++) {
      c[i]=new TestBlankComponent(this);
      c[i].refresh();
      c[i].doBackground=false;
      gccenter.add.add(c[i]);
    }
    // data[3]=1;
    pcenter.minDisplayDist=pcenter.minDist=4;
    for(int i=0;i<data.length;i++) {
      data[i]=new ScrollBar(this,0,i*10,256,8);
      data[i].data=0.45f;
      data[i].refresh();
      pcenter.add.add(data[i]);
    }
    data[3].data=0.5f;
    data[4].data=0.5f;
  }
  @Override
  public void display() {
    // rect(camX,camY,camW,camH);
    // switch(button) {
    //   case LEFT:
    //     stroke(0xc0ff0000);
    //     break;
    //   case CENTER:
    //     stroke(0xc000ff00);
    //     break;
    //   case RIGHT:
    //     stroke(0xc00000ff);
    //     break;
    //   default:
    //     stroke(0xc0ffffff);
    //     break;
    // }
    // // System.out.println(Integer.toHexString(g.strokeColor));
    // ellipse(x,y,32,32);
  }
  @Override
  public void update() {
    switch(s.state) {
      case SocketSet.unconnected: {}
        break;
      case SocketSet.connected: {
        for(int i=0;i<18;i++) s.putF(0);
        // if((frameCount/120)%2==0) data[3].data=0;
        // else data[3].data=0.5f;
        for(int i=0;i<data.length;i++) s.putF(data[i].data);
        s.write();
        s.read(Const.bufferSize);
        // x=s.getF();
        // y=s.getF();
        // button=s.getI();
        // camX=s.getF();
        // camY=s.getF();
        // camW=s.getF();
        // camH=s.getF();
        for(int i=0;i<c.length;i++) c[i].fromBuffer(s.inBuffer);
      }
        break;
      default:
        throw new IllegalArgumentException("Unexpected value: "+s.state);
    }
  }
  @Override
  public void mousePressed() {
    if(mouseButton==CENTER&&s.state==0) s.connect(host,port);
  }
  @Override
  public void exitActual() {
    System.out.println("ClientApp.exitActual()");
    super.exitActual();
  }
  public static void main(String[] args) {
    System.setProperty("sun.java2d.uiScale","1");
    new ClientApp("127.0.0.1",1234).run();
  }
}
