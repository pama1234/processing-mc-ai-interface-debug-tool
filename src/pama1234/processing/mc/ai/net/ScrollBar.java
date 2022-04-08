package pama1234.processing.mc.ai.net;

import pama1234.math.Tools;
import pama1234.processing.util.app.UtilApp;
import pama1234.processing.util.element.component.BlankComponent;

public class ScrollBar extends BlankComponent{
  float data,hist;
  @Override
  public String getName() {
    return super.getName()+" data="+data;
  }
  public ScrollBar(UtilApp p,int x,int y,int w,int h) {
    super(p,x,y,w,h);
  }
  @Override
  public void initGraphics() {
    super.initGraphics();
    g.smooth();
    // g.stroke();
  }
  @Override
  public void init() {}
  @Override
  public void pause() {}
  @Override
  public void resume() {}
  @Override
  public void dispose() {}
  @Override
  public void mousePressed(int button) {
    // if(inbox) {
    // }
    // data=PApplet.map(mouseY(),0,w(),0,1);
  }
  @Override
  public void mouseReleased(int button) {}
  @Override
  public void mouseClicked(int button) {}
  @Override
  public void mouseMoved() {}
  @Override
  public void mouseDragged() {}
  @Override
  public void mouseWheel(int c) {}
  @Override
  public void keyPressed(char key,int keyCode) {}
  @Override
  public void keyReleased(char key,int keyCode) {}
  @Override
  public void keyTyped(char key) {}
  @Override
  public void frameMoved(int x,int y) {}
  @Override
  public void draw() {
    float tx=data*w();
    g.stroke(0);
    g.line(tx,0,tx,h());
  }
  @Override
  public void update() {
    super.update();
    if(p.mousePressed&&Tools.inBox(p.cam.mouseX,p.cam.mouseY,x1(),y1(),w(),h())) {
      // System.out.println("ScrollBar.update()");
      // data=mouseY()/w();
      data=mouseX()/w();
      refresh();
    }
    if(hist!=data) refresh();
    hist=data;
  }
}
