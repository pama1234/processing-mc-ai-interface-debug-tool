package pama1234.processing.util.wrapper;

import pama1234.processing.util.Entity;
import pama1234.processing.util.app.UtilApp;

public class LayeredEntityCenter extends Entity{
  public EntityCenter<?>[] list;
  public LayeredEntityCenter(UtilApp p,int size) {
    super(p);
    list=new EntityCenter<?>[size];
  }
  @Override
  public void init() {
    for(EntityCenter<?> i:list) i.init();
  }
  @Override
  public void update() {
    for(EntityCenter<?> i:list) i.update();
  }
  @Override
  public void display() {
    for(EntityCenter<?> i:list) i.display();
  }
  @Override
  public void pause() {
    for(EntityCenter<?> i:list) i.pause();
  }
  @Override
  public void resume() {
    for(EntityCenter<?> i:list) i.resume();
  }
  @Override
  public void dispose() {
    for(EntityCenter<?> i:list) i.dispose();
  }
  @Override
  public void mousePressed(int button) {
    for(EntityCenter<?> i:list) i.mousePressed(button);
  }
  @Override
  public void mouseReleased(int button) {
    for(EntityCenter<?> i:list) i.mouseReleased(button);
  }
  @Override
  public void mouseClicked(int button) {
    for(EntityCenter<?> i:list) i.mouseClicked(button);
  }
  @Override
  public void mouseMoved() {
    for(EntityCenter<?> i:list) i.mouseMoved();
  }
  @Override
  public void mouseDragged() {
    for(EntityCenter<?> i:list) i.mouseDragged();
  }
  @Override
  public void mouseWheel(int c) {
    for(EntityCenter<?> i:list) i.mouseWheel(c);
  }
  @Override
  public void keyPressed(char key,int keyCode) {
    for(EntityCenter<?> i:list) i.keyPressed(key,keyCode);
  }
  @Override
  public void keyReleased(char key,int keyCode) {
    for(EntityCenter<?> i:list) i.keyReleased(key,keyCode);
  }
  @Override
  public void keyTyped(char key) {
    for(EntityCenter<?> i:list) i.keyTyped(key);
  }
  @Override
  public void frameResized(int w,int h) {
    for(EntityCenter<?> i:list) i.frameResized(w,h);
  }
  @Override
  public void frameMoved(int x,int y) {
    for(EntityCenter<?> i:list) i.frameMoved(x,y);
  }
}
