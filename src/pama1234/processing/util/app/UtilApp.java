package pama1234.processing.util.app;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;

import pama1234.math.vec.Vec2i;
import pama1234.processing.util.Entity;
import pama1234.processing.util.element.CamController;
import pama1234.processing.util.element.PointEntity;
import pama1234.processing.util.element.component.BlankComponent;
import pama1234.processing.util.wrapper.ComponentCenter;
import pama1234.processing.util.wrapper.EntityCenter;
import pama1234.processing.util.wrapper.GridComponentCenter;
import pama1234.processing.util.wrapper.PointCenter;
import processing.awt.PSurfaceAWT.SmoothCanvas;
import processing.core.PApplet;
import processing.core.PFont;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public abstract class UtilApp extends PApplet implements Runnable{
  @Override
  public void run() {
    super.runSketch();
  }
  public class MovedListener extends ComponentAdapter{
    @Override
    public void componentMoved(ComponentEvent e) {
      Insets tempInsets=frame.getInsets();
      Point tempLocation=frame.getLocation();
      framePos.set(
        tempInsets.left+tempLocation.x,
        tempInsets.top+tempLocation.y);
      moved++;
    }
  }
  public class PFocusListener implements FocusListener{
    @Override
    public void focusLost(FocusEvent e) {
      focusedLost();
    }
    @Override
    public void focusGained(FocusEvent e) {
      focusedGained();
    }
  }
  public class PResizedListener extends ComponentAdapter{
    @Override
    public void componentResized(ComponentEvent e) {
      resized++;
    }
  }
  public SmoothCanvas canvas;
  public JFrame frame;
  public final Vec2i framePos=new Vec2i();
  public int unitFrameSize;
  public float u;
  public boolean shift,ctrl,alt;
  public int smouseX,smouseY,deltaX,deltaY;
  public EntityCenter<Entity> center=new EntityCenter<Entity>(this);
  public PointCenter<PointEntity<?>> pcenter=new PointCenter<PointEntity<?>>(this,16);
  public ComponentCenter<BlankComponent> ccenter=new ComponentCenter<BlankComponent>(this);
  public GridComponentCenter<BlankComponent> gccenter=new GridComponentCenter<BlankComponent>(this);
  public CamController cam;
  public int background=0xff000000;
  public boolean doBackground=true;
  public PFont font;
  public String fontPath="data/font/unifont-13.0.06.ttf";
  public boolean loadFont;
  public int resized,moved;
  @Override
  public void settings() {
    unitFrameSize=Math.min(displayWidth,displayHeight)/3*2;
    size(unitFrameSize,unitFrameSize);
  }
  @Override
  public void setup() {
    surface.setResizable(true);
    canvas=(SmoothCanvas)surface.getNative();
    canvas.addComponentListener(new PResizedListener());
    canvas.addFocusListener(new PFocusListener());
    frame=(JFrame)canvas.getFrame();
    frame.addComponentListener(new MovedListener());
    Insets tempInsets=frame.getInsets();
    Point tempLocation=frame.getLocation();
    cam=new CamController(this,0,0,1);
    center.list.add(cam);
    center.list.add(pcenter);
    center.list.add(ccenter);
    center.list.add(gccenter);
    int textSize=16;
    if(loadFont) font=createFont(fontPath,textSize);
    else font=new PFont(new Font(Font.MONOSPACED,Font.PLAIN,textSize),true);
    textFont(font);
    textAlign(LEFT,BOTTOM);
    textSize(textSize);
    strokeWeight(2);
    framePos.set(
      tempInsets.left+tempLocation.x,
      tempInsets.top+tempLocation.y);
    preFrameMoved(
      tempInsets.left+tempLocation.x,
      tempInsets.top+tempLocation.y);
    preFrameResized(width,height);
    init();
    center.refresh();
    frameMoved(
      tempInsets.left+tempLocation.x,
      tempInsets.top+tempLocation.y);
    frameResized(width,height);
  }
  private void preFrameMoved(int x,int y) {
    framePos.set(x,y);
  }
  private void preFrameResized(int w,int h) {
    u=Math.max(1,Math.min(w,h))/unitFrameSize;
  }
  public abstract void init();
  @Override
  public synchronized void draw() {
    deltaX=mouseX-pmouseX;
    deltaY=mouseY-pmouseY;
    if(resized>0) {
      resized--;
      frameResized(width,height);
    }
    if(moved>0) {
      moved--;
      frameMoved(framePos.x,framePos.y);
    }
    center.refresh();
    center.update();
    update();
    if(doBackground) background(background);
    center.display();
    display();
    resetMatrix();
  }
  public abstract void display();
  public void displayLayer() {}
  public abstract void update();
  public void focusedGained() {}
  public void focusedLost() {}
  @Override
  public void frameResized(int w,int h) {
    u=Math.max(1,Math.min(w,h))/unitFrameSize;
    center.frameResized(w,h);
  }
  @Override
  public void frameMoved(int x,int y) {
    center.frameMoved(x,y);
  }
  @Override
  public void mousePressed(MouseEvent event) {
    smouseX=mouseX;
    smouseY=mouseY;
    center.mousePressed(mouseButton);
    super.mousePressed(event);
  }
  @Override
  public void mouseReleased(MouseEvent event) {
    center.mouseReleased(mouseButton);
    super.mouseReleased(event);
  }
  @Override
  public void mouseClicked(MouseEvent event) {
    super.mouseClicked(event);
    center.mouseClicked(mouseButton);
  }
  @Override
  public void mouseMoved(MouseEvent event) {
    super.mouseMoved(event);
    center.mouseMoved();
  }
  @Override
  public void mouseDragged(MouseEvent event) {
    super.mouseDragged(event);
    center.mouseDragged();
  }
  @Override
  public void mouseWheel(MouseEvent e) {
    super.mouseWheel(e);
    mouseWheel(e.getCount());
    center.mouseWheel(e.getCount());
  }
  public void mouseWheel(int count) {}
  @Override
  public void keyPressed(KeyEvent event) {
    switch(keyCode) {
      case CONTROL: {
        ctrl=true;
      }
        break;
      case SHIFT: {
        shift=true;
      }
        break;
      case ALT: {
        alt=true;
      }
        break;
    }
    center.keyPressed(key,keyCode);
    super.keyPressed(event);
  }
  @Override
  public void keyReleased(KeyEvent event) {
    switch(keyCode) {
      case CONTROL: {
        ctrl=false;
      }
        break;
      case SHIFT: {
        shift=false;
      }
        break;
      case ALT: {
        alt=false;
      }
        break;
    }
    center.keyReleased(key,keyCode);
    super.keyReleased(event);
  }
  @Override
  public void keyTyped(KeyEvent event) {
    super.keyTyped(event);
    center.keyTyped(key);
  }
  @Override
  public void textSize(float size) {
    super.textSize(size);
    textLeading(size);
  }
  @Override
  public void exitActual() {
    center.dispose();
    super.exitActual();
  }
  public static void main(String[] args) {
    System.setProperty("sun.java2d.uiScale","1");
    new UtilApp() {
      @Override
      public void init() {}
      @Override
      public void display() {
        rect(0,0,32,32);
      }
      @Override
      public void update() {}
    }.runSketch();
  }
}
