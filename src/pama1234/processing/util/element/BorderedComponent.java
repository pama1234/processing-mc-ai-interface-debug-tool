package pama1234.processing.util.element;

import pama1234.processing.util.UITools;
import pama1234.processing.util.app.UtilApp;
import pama1234.processing.util.element.component.BlankComponent;

public abstract class BorderedComponent extends BlankComponent{
  public int w,h;
  public int borderSize;
  public BorderedComponent(UtilApp p,int x,int y) {
    this(p,x,y,1);
  }
  public BorderedComponent(UtilApp p,int x,int y,int s) {
    this(p,x,y,0,0,1);
  }
  public BorderedComponent(UtilApp p,int x,int y,int w,int h) {
    this(p,x,y,w,h,1);
  }
  public BorderedComponent(UtilApp p,int x,int y,int w,int h,int s) {
    super(p,x,y,w+s*2,h+s*2);
    borderSize=s;
    initGraphics();
    this.w=w;
    this.h=h;
  }
  public float mouseX() {
    return super.mouseX()-borderSize;
  }
  public float mouseY() {
    return super.mouseY()-borderSize;
  }
  @Override
  public void refresh() {
    g.beginDraw();
    if(doBackground) background();
    translate();
    draw();
    g.endDraw();
  }
  @Override
  public void background() {
    super.background();
    UITools.rectBorder(g);
  }
  public void resize(int w,int h) {
    this.w=w;
    this.h=h;
    super.resize(w+borderSize*2,h+borderSize*2);
  }
  public void translate() {
    g.translate(borderSize,borderSize);
  }
}
