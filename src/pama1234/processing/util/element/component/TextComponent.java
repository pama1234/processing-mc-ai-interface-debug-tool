package pama1234.processing.util.element.component;

import pama1234.processing.util.UITools;
import pama1234.processing.util.app.UtilApp;
import pama1234.processing.util.element.BorderedComponent;

public abstract class TextComponent extends BorderedComponent{
  public String[] s;
  public float mouseX,mouseY;
  public TextComponent(UtilApp p,int x,int y,String in) {
    super(p,x,y,4);
    s=in.split("\n");
  }
  public TextComponent(UtilApp p,int x,int y) {
    super(p,x,y);
    s=new String[0];
  }
  public void updateChange() {
    float max=1;
    for(int i=0;i<s.length;i++) {
      float tw=UITools.textWidth(g,textSize/2,s[0]);
      if(tw>max) max=tw;
    }
    int tw=(int)Math.ceil(UITools.textWidth(g,textSize/2,s[0]));
    //    int th=(int)Math.ceil(textSize*(Math.max(s.length()-s.replace("\n","").length(),1)));
    int th=(int)Math.ceil(textSize*(Math.max(s.length,1)));
    if(tw!=w()||th!=h()) resize(tw,th);
  }
  @Override
  public void draw() {
    //    g.translate(textSize/2f,textSize/4f-g.textDescent());
    for(int i=0;i<s.length;i++) UITools.textLine(g,0,textSize*i,textSize/2,textSize,s[i],(x)->0xffffffff,(x)->0,(x,cw,tw)-> {});
  }
}
