package pama1234.nio;

import java.nio.ByteBuffer;

public interface ByteData extends Data<ByteBuffer>{
  // @Override
  // default void fromBuffer(ByteBuffer in) {
  //   // fromData(in,0,in.position());
  //   fromBuffer(in,in.position(),in.limit()-in.position());
  // }
  @Override
  default ByteBuffer toBuffer() {
    // return toBuffer(ByteBuffer.allocate(bufferSize()),0);
    return toBuffer(ByteBuffer.allocate(bufferSize()));
  }
  //---
  void fromBuffer(ByteBuffer in);
  // void fromBuffer(ByteBuffer in,int offset,int size);
  // ByteBuffer toBuffer(ByteBuffer in,int offset);
  ByteBuffer toBuffer(ByteBuffer in);
  int bufferSize();
  //---
  public static final int FLOAT_SIZE=4;
  public static final int INT_SIZE=4;
  public static final int CHAR_SIZE=2;
  public static final int BOOLEAN_SIZE=1;
  public static final byte FALSE=0,TRUE=1;
  public static boolean toBoolean(byte in) {
    if(in==TRUE) return true;
    else if(in==FALSE) return false;
    else throw new RuntimeException("Strange input "+Byte.toString(in));
  }
  public static byte toByte(boolean in) {
    if(in) return TRUE;
    else return FALSE;
  }
}