package pama1234.processing.mc.ai.test;

import java.nio.ByteBuffer;

import pama1234.processing.util.app.UtilApp;

public class TestApp0001 extends UtilApp{
  TestBlankComponent c;
  ByteBuffer b;
  @Override
  public void settings() {
    super.settings();
    noSmooth();
  }
  @Override
  public void init() {
    c=new TestBlankComponent(this);
    c.refresh();
    c.doBackground=false;
    b=c.toBuffer();
    // System.out.println(toString(b.array()));
    // System.out.println(b.limit());
    // System.out.println(b.position());
    // System.out.println(c.g.width*c.g.height*4);
    center.add.add(c);
  }
  public static String toString(byte[] a) {
    if(a==null) return "null";
    int iMax=a.length-1;
    if(iMax==-1) return "[]";
    StringBuilder b=new StringBuilder();
    b.append('[');
    for(int i=0;;i++) {
      b.append(Integer.toHexString(a[i]&0xff));
      if(i==iMax) return b.append(']').toString();
      if(i%4==3) b.append(", ");
    }
  }
  @Override
  public void display() {}
  @Override
  public void update() {
    b.clear();
    c.toBuffer(b);
  }
  public static void main(String[] args) {
    System.setProperty("sun.java2d.uiScale","1");
    new TestApp0001().run();
  }
}
