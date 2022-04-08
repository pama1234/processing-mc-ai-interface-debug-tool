package pama1234.processing.mc.ai.net;

public class MainApp{
  // public static final int bufferSize=TestBlankComponent.width*TestBlankComponent.height*3*TestBlankComponent.t+4*7+4*5;
  public static final int bufferSize=TestBlankComponent.width*TestBlankComponent.height*3*TestBlankComponent.t;
  public static void main(String[] args) {
    System.setProperty("sun.java2d.uiScale","1");
    // new Thread(new ServerApp(1234)).start();
    new Thread(new ClientApp("127.0.0.1",1234)).start();
  }
}
