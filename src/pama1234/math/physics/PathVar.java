package pama1234.math.physics;

import java.nio.ByteBuffer;

import pama1234.nio.ByteData;

public class PathVar implements ByteData{
  public float pos,des;
  public float f;
  {
    f=0.2f;
  }
  public PathVar(float in) {
    pos=des=in;
  }
  public void update() {
    pos+=(des-pos)*f;
  }
  @Override
  public void fromBuffer(ByteBuffer in) {
    f=in.getFloat();
    pos=in.getFloat();
    des=in.getFloat();
  }
  @Override
  // public ByteBuffer toBuffer(ByteBuffer in,int offset) {
  //   in.putFloat(offset,f);
  //   in.putFloat(offset+=ByteData.FLOAT_SIZE,pos);
  //   in.putFloat(offset+=ByteData.FLOAT_SIZE,des);
  public ByteBuffer toBuffer(ByteBuffer in) {
    in.putFloat(f);
    in.putFloat(pos);
    in.putFloat(des);
    return in;
  }
  @Override
  public int bufferSize() {
    return ByteData.FLOAT_SIZE*3;
  }
}
