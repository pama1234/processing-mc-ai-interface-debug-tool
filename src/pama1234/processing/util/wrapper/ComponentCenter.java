package pama1234.processing.util.wrapper;

import java.util.ListIterator;

import pama1234.math.vec.Vec2f;
import pama1234.processing.util.app.UtilApp;
import pama1234.processing.util.element.component.BlankComponent;
import processing.core.PConstants;

public class ComponentCenter<T extends BlankComponent>extends PointCenter<T>{
  public boolean rectSelect;
  public Vec2f dragPos=new Vec2f();
  public ComponentCenter(UtilApp p) {
    super(p);
  }
  public ComponentCenter(UtilApp p,int u) {
    super(p,u);
  }
  @Override
  public void update() {
    super.update();
    if(dragFlag()&&rectSelect) select.point.set(p.cam.mouseX-dragPos.x,p.cam.mouseY-dragPos.y);
  }
  //  @Override
  //  public boolean dragFlag() {
  //    return super.dragFlag()&&rectSelect;
  //  }
  @Override
  public void display() {
    // super.display();
    display(dragFlag());
    //    if(!dragFlag()) for(T i:list) if(i.inbox) {
    //      p.stroke(0xc0ffffff);
    //      p.rect(i.x1()-minDisplayDist,i.y1()-minDisplayDist,i.w()+minDisplayDist*2,i.h()+minDisplayDist*2);
    //    }
  }
  public void display(boolean in) {
    super.display();
    if(!in) for(T i:list) if(i.inbox) {
      p.stroke(0xc0ffffff);
      p.rect(i.x1()-minDisplayDist,i.y1()-minDisplayDist,i.w()+minDisplayDist*2,i.h()+minDisplayDist*2);
    }
  }
  @Override
  public void mousePressed(int button) {
    if(p.mouseButton==PConstants.LEFT) {
      rectSelect=false;
      find();
      if(select==null) {
        select=null;
        ListIterator<T> it=list.listIterator(list.size());
        while(it.hasPrevious()) {
          T i=it.previous();
          if(i.inbox) {
            select=i;
            dragPos.set(p.cam.mouseX,p.cam.mouseY);
            dragPos.sub(select.point.pos);
            rectSelect=true;
            break;
          }
        }
      }
    }
    if(select==null) super.mousePressed(button);
  }
}
