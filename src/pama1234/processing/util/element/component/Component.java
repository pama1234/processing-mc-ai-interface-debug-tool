package pama1234.processing.util.element.component;

import pama1234.processing.util.app.UtilApp;
import pama1234.processing.util.element.Part;
import pama1234.processing.util.wrapper.PartCenter;

public abstract class Component<T extends Part>extends BlankComponent{
  public PartCenter<T> c=new PartCenter<>();
  public Component(UtilApp p,int x,int y) {
    super(p,0,0,0,0);
  }
  public Component(UtilApp p,int x,int y,int w,int h) {
    super(p,x,y,w,h);
  }
  @Override
  public void update() {
    super.update();
    c.update();
  }
  @Override
  public void draw() {
    c.display();
  }
}
