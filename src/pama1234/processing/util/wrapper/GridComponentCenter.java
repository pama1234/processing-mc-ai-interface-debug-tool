package pama1234.processing.util.wrapper;

import pama1234.processing.util.UITools;
import pama1234.processing.util.app.UtilApp;
import pama1234.processing.util.element.component.BlankComponent;
import processing.core.PConstants;

public class GridComponentCenter<T extends BlankComponent>extends ComponentCenter<T>{
  public int gridUnit=64,crossSize=16;
  public GridComponentCenter(UtilApp p) {
    super(p);
  }
  public GridComponentCenter(UtilApp p,int u) {
    super(p,u);
  }
  public GridComponentCenter(UtilApp p,int u,int gu) {
    super(p,u);
    gridUnit=gu;
  }
  @Override
  public void display() {
    boolean flag=dragFlag();
    if(flag) {
      int tx=Math.round(select.point.des.x/gridUnit)*gridUnit,
        ty=Math.round(select.point.des.y/gridUnit)*gridUnit;
      int tw=select.w(),
        th=select.h();
      p.stroke(0x80ffffff);
      p.rect(tx,ty,tw,th);
      p.stroke(255);
      UITools.cross(p.g,tx,ty,crossSize,crossSize);
      UITools.cross(p.g,tx+tw,ty+th,crossSize,crossSize);
      UITools.cross(p.g,tx+tw,ty,crossSize,crossSize);
      UITools.cross(p.g,tx,ty+th,crossSize,crossSize);
      UITools.cross(p.g,select.point.des.x,select.point.des.y,minDist,minDist);
    }
    super.display(flag);
  }
  @Override
  public void mouseReleased(int button) {
    //    System.out.println(select!=null&&rectSelect);
    if(select!=null&&rectSelect&&button==PConstants.LEFT) select.point.des.set(f(p.cam.mouseX-dragPos.x),f(p.cam.mouseY-dragPos.y));
    super.mouseReleased(button);
  }
  public int f(float in) {
    return (int)Math.round(in/gridUnit)*gridUnit;
  }
}
