package pama1234.processing.util.element.component;

import java.util.ArrayList;

import pama1234.processing.util.UITools;
import pama1234.processing.util.app.UtilApp;
import pama1234.processing.util.element.BorderedComponent;

public class TextArea extends BorderedComponent{
  public ArrayList<String> s=new ArrayList<>();
  public Caret caret;
  public TextArea(UtilApp p,int x,int y,String... in) {
    super(p,x,y,3);
    //    smooth=true;
    caret=new Caret();
    for(String i:in) s.add(i);
    //    updateChange();
  }
  public void getBackground(int in) {}
  public void insert(int x,int y,String in) {}
  public void remove(int sx,int sy,int ex,int ey) {}
  public void updateChange() {
    //    s=sb.toString();
    float maxSize=1;
    for(String i:s) {
      float ts=g.textWidth(i);
      //      System.out.println(ts);
      if(maxSize<ts) maxSize=ts;
    }
    //    resize((int)Math.ceil(maxSize)+textSize,(int)Math.ceil(textSize*(Math.max(s.length()-s.replace("\n","").length(),1)+0.5f)));
    //    resize((int)Math.ceil(maxSize)+textSize,(int)Math.ceil(textSize*(Math.max(s.size(),1)+0.5f)));
    resize((int)Math.ceil(maxSize),(int)Math.ceil(textSize*(Math.max(s.size(),1))));
    //    PApplet.println(maxSize);
    //    refresh();
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
  public void update() {
    super.update();
    caret.update();
    //    refresh();
  }
  //  @Override
  //  public float mouseX() {
  //    return super.mouseX()-textSize/2f;
  //  }
  //  @Override
  //  public float mouseY() {
  //    return super.mouseY()-textSize/4f+g.textDescent();
  //  }
  @Override
  public void draw() {
    //    g.translate(textSize/2f,textSize/4f-g.textDescent());
    //    for(int i=0;i<s.size();i++) g.text(s.get(i),0,i*(textSize-g.textDescent()));
    for(int i=0;i<s.size();i++) {
      caret.iy=i;
      UITools.textWidth(g,textSize/2,s.get(i),caret::textWidthUpdate);
      UITools.textLine(g,
        0,i*textSize,textSize/2,textSize,s.get(i),
        caret::getForeground,
        caret::getBackground,caret::textWidthDisplay);
    }
    caret.display();
    //    refresh();
  }
  @Override
  public void mousePressed(int button) {
    caret.mousePressed(button);
  }
  @Override
  public void mouseReleased(int button) {
    caret.mouseReleased(button);
  }
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
  public class Caret{
    StringBuilder sb=new StringBuilder();
    boolean select;
    float x,y;
    float startX,startY,endX,endY;
    int sx,sy,ex,ey;
    int iy;
    public int getBackground(int ix) {
      final int tc=0xffFFCC00;
      if(ix==ex&&iy==ey) return tc;
      return 0;
    }
    public int getForeground(int a) {
      return 0xffffffff;
    }
    public void textWidthDisplay(int a,float cw,float tw) {
      if(iy==ey) {
        if(a==ex) {
          //        System.out.println("TextArea.Caret.putTextWidth()");
          g.fill(0xff7F0055);
          g.rect(tw,ey*textSize,2,textSize);
        }
      }
    }
    public void textWidthUpdate(int a,float cw,float tw) {
      if(iy==ey) {
        float tx=endX-tw;
        if(tx>0) {
          if(tx<=cw/2) ex=a;
          else if(tx<=cw) ex=a+1;
        }
      }
    }
    public void update() {
      if(p.mousePressed&&inbox) {
        //        endX=Math.round(mouseX()/(textSize/2));
        //        endY=(int)Math.floor(mouseY()/textSize);
        endX=mouseX();
        endY=mouseY();
        ey=(int)Math.floor(endY/textSize);
        ex=-1;
        //        PApplet.println(ex,ey);
        //        System.out.println(ey);
        refresh();
      }
    }
    public void display() {}
    public void mousePressed(int button) {
      //      startX=Math.round(mouseX()/(textSize/2));
      //      startY=(int)Math.floor(mouseY()/textSize);
      startX=mouseX();
      startY=mouseY();
      sy=(int)Math.floor(startY/textSize);
    }
    public void mouseReleased(int button) {}
    public void keyPressed(char key,int keyCode) {}
    public void keyReleased(char key,int keyCode) {}
    public void keyTyped(char key) {}
  }
  @Override
  public void frameMoved(int x,int y) {}
  public static void main(String[] args) {
    new UtilApp() {
      TextArea text;
      public void settings() {
        super.settings();
        noSmooth();
      }
      @Override
      public void init() {
        background=color(0);
        text=new TextArea(this,0,0,"Test","pama1234","abc李则润123","   ");
        text.updateChange();
        //        text.refresh();
        center.add.add(text);
      }
      @Override
      public void update() {}
      @Override
      public void display() {}
      @Override
      public void dispose() {}
    }.run();
  }
}
