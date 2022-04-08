package pama1234.processing.mc.ai.net;

import pama1234.processing.util.app.UtilApp;
import pama1234.processing.util.element.component.BlankComponent;

public class TestBlankComponent extends BlankComponent{
  public static final int width=640,height=360,t=1;
  public TestBlankComponent(UtilApp p) {
    super(p,0,0,width,height);
  }
  float pmouseX,pmouseY;
  @Override
  public void init() {}
  @Override
  public void initGraphics() {
    super.initGraphics();
  }
  @Override
  public void pause() {}
  @Override
  public void resume() {}
  @Override
  public void dispose() {}
  @Override
  public void mousePressed(int button) {}
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
    if(!p.mousePressed) {
      // g.stroke(0);
      // else g.stroke(255);
      g.stroke(p.frameCount%256);
      g.line(pmouseX,pmouseY,pmouseX=mouseX(),pmouseY=mouseY());
    }
  }
  @Override
  public void update() {
    super.update();
    refresh();
  }
}
