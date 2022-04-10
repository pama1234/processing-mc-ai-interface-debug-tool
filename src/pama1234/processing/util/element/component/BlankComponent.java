package pama1234.processing.util.element.component;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

import pama1234.math.Tools;
import pama1234.math.physics.PathPoint;
import pama1234.nio.ByteData;
import pama1234.processing.util.app.UtilApp;
import pama1234.processing.util.element.PointEntity;
import processing.core.PConstants;
import processing.core.PGraphics;

public abstract class BlankComponent extends PointEntity<PathPoint> implements ByteData{
  public PGraphics g;
  public int textSize=32;
  public int textAlignX=PConstants.LEFT,textAlignY=PConstants.TOP;
  public int background=0xff808080;
  public boolean doBackground=true;
  public boolean inbox;
  public boolean smooth;
  public BlankComponent(UtilApp p,int x,int y) {
    this(p,x,y,0,0);
  }
  public BlankComponent(UtilApp p,int x,int y,int w,int h) {
    super(p,new PathPoint(0,0,x,y));
    g=p.createGraphics(w,h);
    initGraphics();
  }
  @Override
  public int bufferSize() {
    return g.width*g.height*4;
  }
  @Override
  public void fromBuffer(ByteBuffer in) {
    // point.fromBuffer(in);
    WritableRaster raster=((BufferedImage)g.image).getRaster();
    int[] tia=new int[4];
    tia[3]=0xff;
    try {
      for(int i=0;i<g.height;i++) for(int j=0;j<g.width;j++) {
        tia[0]=in.get()&0xff;
        tia[1]=in.get()&0xff;
        tia[2]=in.get()&0xff;
        raster.setPixel(j,i,tia);
      }
    }catch(BufferUnderflowException e) {
      System.out.println(e+" "+in);
    }
  }
  public void fromBufferVFlip(ByteBuffer in) {
    // point.fromBuffer(in);
    WritableRaster raster=((BufferedImage)g.image).getRaster();
    int[] tia=new int[4];
    tia[3]=0xff;
    try {
      for(int i=0;i<g.height;i++) for(int j=0;j<g.width;j++) {
        tia[0]=in.get()&0xff;
        tia[1]=in.get()&0xff;
        tia[2]=in.get()&0xff;
        raster.setPixel(j,g.height-1-i,tia);
      }
    }catch(BufferUnderflowException e) {
      System.out.println(e+" "+in);
    }
  }
  @Override
  // public ByteBuffer toBuffer(ByteBuffer in,int offset) {
  public ByteBuffer toBuffer(ByteBuffer in) {
    // point.toBuffer(in);
    WritableRaster raster=((BufferedImage)g.image).getRaster();
    for(int i=0;i<g.height;i++) for(int j=0;j<g.width;j++) {
      in.put((byte)raster.getSample(j,i,0));
      in.put((byte)raster.getSample(j,i,1));
      in.put((byte)raster.getSample(j,i,2));
    }
    return in;
  }
  public float mouseX() {
    return p.cam.mouseX-point.pos.x;
  }
  public float mouseY() {
    return p.cam.mouseY-point.pos.y;
  }
  public float x1() {
    return point.pos.x;
  }
  public float x2() {
    return point.pos.x+g.width;
  }
  public float y1() {
    return point.pos.y;
  }
  public float y2() {
    return point.pos.y+g.height;
  }
  public int w() {
    return g.width;
  }
  public int h() {
    return g.height;
  }
  @Override
  public void display() {
    p.image(g,point.pos.x,point.pos.y);
  }
  @Override
  public void update() {
    inbox=Tools.inBox(p.cam.mouseX,p.cam.mouseY,x1(),y1(),w(),h());
    super.update();
  }
  public void refresh() {
    g.beginDraw();
    if(doBackground) background();
    draw();
    g.endDraw();
  }
  public abstract void draw();
  public void background() {
    g.background(background);
  }
  public void resize(int w,int h) {
    g=p.createGraphics(w,h);
    initGraphics();
    refresh();
  }
  public void initGraphics() {
    if(smooth) g.smooth();
    else g.noSmooth();
    g.beginDraw();
    g.textFont(p.font);
    g.textAlign(textAlignX,textAlignY);
    g.textSize(textSize);
    g.textLeading(textSize);
    g.noStroke();
    g.endDraw();
  }
  @Override
  public void frameResized(int w,int h) {
    initGraphics();
  }
}
