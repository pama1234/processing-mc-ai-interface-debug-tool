package pama1234.nio;

public interface Data<D>{
  D toBuffer();
  void fromBuffer(D in);
}