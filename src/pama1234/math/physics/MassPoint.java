package pama1234.math.physics;

import java.nio.ByteBuffer;

import pama1234.math.Tools;
import pama1234.math.vec.Vec2f;
import pama1234.nio.ByteData;

public class MassPoint extends Point implements ByteData{
  public static final int buffer_size=Vec2f.buffer_size*2+FLOAT_SIZE;
  public Vec2f vel;
  {
    f=0.8f;
  }
  public MassPoint(Vec2f pos,Vec2f vel) {
    this.pos=pos;
    this.vel=vel;
  }
  public MassPoint(Vec2f pos) {
    vel=new Vec2f();
    this.pos=pos;
  }
  public MassPoint(float a,float b,Vec2f vel) {
    pos=new Vec2f(a,b);
    this.vel=vel;
  }
  public MassPoint(float a,float b) {
    pos=new Vec2f(a,b);
    vel=new Vec2f();
  }
  public MassPoint(float a,float b,float c,float d) {
    pos=new Vec2f(a,b);
    vel=new Vec2f(c,d);
  }
  @Override
  public void update() {
    pos.add(vel);
    if(f!=1) vel.scale(f);
  }
  @Override
  public void add(float x,float y) {
    vel.add(x,y);
  }
  public void setInBox(int x1,int y1,int x2,int y2) {
    if(pos.x<x1) {
      pos.x=x1;
      vel.x*=-f;
    }else if(pos.x>x2) {
      pos.x=x2;
      vel.x*=-f;
    }
    if(pos.y<y1) {
      pos.y=y1;
      vel.y*=-f;
    }else if(pos.y>y2) {
      pos.y=y2;
      vel.y*=-f;
    }
  }
  public void moveInBox(int x1,int y1,int x2,int y2) {
    // x2-=x1;
    // y2-=y1;
    // pos.sub(x1,y1);
    // {
    //   float tx=(int)Math.floor(pos.x/x2)*x2;
    //   float ty=(int)Math.floor(pos.y/y2)*y2;
    //   pos.sub(tx,ty);
    // }
    // pos.add(x1,y1);
    pos.set(Tools.moveInRange(pos.x,x1,x2),Tools.moveInRange(pos.y,y1,y2));
  }
  @Override
  public void fromBuffer(ByteBuffer in) {
    f=in.getFloat();
    pos.fromBuffer(in);
    vel.fromBuffer(in);
  }
  @Override
  // public ByteBuffer toBuffer(ByteBuffer in,int offset) {
  //   in.putFloat(offset,f);
  //   pos.toBuffer(in,offset+=FLOAT_SIZE);
  //   vel.toBuffer(in,offset+=pos.bufferSize());
  public ByteBuffer toBuffer(ByteBuffer in) {
    in.putFloat(f);
    pos.toBuffer(in);
    vel.toBuffer(in);
    return in;
  }
  @Override
  public int bufferSize() {
    return buffer_size;
  }
}
