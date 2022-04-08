package pama1234.math.physics;

import java.nio.ByteBuffer;

import pama1234.math.vec.Vec2f;
import pama1234.nio.ByteData;

public class PathPoint extends Point implements ByteData{
  public Vec2f des;
  {
    f=0.2f;
  }
  public PathPoint(Vec2f pos,Vec2f des) {
    this.pos=pos;
    this.des=des;
  }
  public PathPoint(Vec2f pos) {
    des=new Vec2f();
    this.pos=pos;
  }
  public PathPoint(float a,float b,Vec2f des) {
    pos=new Vec2f(a,b);
    this.des=des;
  }
  public PathPoint(float a,float b) {
    pos=new Vec2f(a,b);
    des=new Vec2f(pos);
  }
  public PathPoint(float a,float b,float c,float d) {
    pos=new Vec2f(a,b);
    des=new Vec2f(c,d);
  }
  @Override
  public void update() {
    pos.add((des.x-pos.x)*f,(des.y-pos.y)*f);
  }
  @Override
  public void set(float x,float y) {
    des.set(x,y);
  }
  @Override
  public void add(float x,float y) {
    des.add(x,y);
  }
  @Override
  public void fromBuffer(ByteBuffer in) {
    // f=in.getFloat(offset);
    // pos.fromBuffer(in,offset+=ByteData.FLOAT_SIZE,offset+=pos.bufferSize());
    // des.fromBuffer(in,offset,offset+=des.bufferSize());
    f=in.getFloat();
    pos.fromBuffer(in);
    des.fromBuffer(in);
  }
  @Override
  // public ByteBuffer toBuffer(ByteBuffer in,int offset) {
  public ByteBuffer toBuffer(ByteBuffer in) {
    // in.putFloat(offset,f);
    // pos.toBuffer(in,offset+=ByteData.FLOAT_SIZE);
    // des.toBuffer(in,offset+=pos.bufferSize());
    in.putFloat(f);
    pos.toBuffer(in);
    des.toBuffer(in);
    return in;
  }
  @Override
  public int bufferSize() {
    return pos.bufferSize()+des.bufferSize()+ByteData.FLOAT_SIZE;
  }
}
