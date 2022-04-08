package pama1234.processing.util.wrapper;

import java.util.ListIterator;

import pama1234.processing.util.UITools;
import pama1234.processing.util.app.UtilApp;
import pama1234.processing.util.element.PointEntity;
import processing.core.PConstants;

public class PointCenter<T extends PointEntity<?>>extends EntityCenter<T>{
  public float minDist,minDisplayDist;
  public T select;
  public PointCenter(UtilApp p) {
    this(p,4);
  }
  public PointCenter(UtilApp p,float u) {
    super(p);
    this.minDist=u;
    this.minDisplayDist=u;
  }
  @Override
  public void update() {
    super.update();
    if(dragFlag()) select.point.set(p.cam.mouseX,p.cam.mouseY);
  }
  public boolean dragFlag() {
    return p.mousePressed&&p.mouseButton==PConstants.LEFT&&select!=null;
  }
  @Override
  public void mousePressed(int button) {
    if(p.mouseButton==PConstants.LEFT) find();
    if(select==null) super.mousePressed(button);
  }
  public void find() {
    float tmd=minDist;
    select=null;
    ListIterator<T> it=list.listIterator(list.size());
    while(it.hasPrevious()) {
      T i=it.previous();
      float td=i.point.pos.dist(p.cam.mouseX,p.cam.mouseY);
      if(td<tmd) {
        tmd=td;
        select=i;
      }
    }
  }
  @Override
  public void display() {
    super.display();
    //    UITools.cross(p.g,p.cam.mouseX,p.cam.mouseY,minDist/2,minDist/2);
    //  System.out.println(i.point.pos.dist(p.cam.mouseX,p.cam.mouseY));
    p.stroke(0xc0ffffff);
    p.noFill();
    for(T i:list) {
      p.ellipse(i.point.pos.x,i.point.pos.y,minDist*2,minDist*2);
      if(i.point.pos.dist(p.cam.mouseX,p.cam.mouseY)<minDisplayDist) {
        UITools.cross(p.g,i.point.pos.x,i.point.pos.y,minDist*2,minDist*2);
        String ts=i.getName()+"\n"+i.point.pos.toString();
        //      p.text(ts,i.point.pos.x-p.textWidth(ts)/2,i.point.pos.y-p.g.textSize);
        p.text(ts,i.point.pos.x,i.point.pos.y);
      }
    }
  }
}
