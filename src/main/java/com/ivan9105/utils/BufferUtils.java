package com.ivan9105.utils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static java.nio.ByteOrder.nativeOrder;

public class BufferUtils {
    private BufferUtils() {
    }

    public static FloatBuffer createFloatBuffer(float[] array) {
        FloatBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(nativeOrder()).asFloatBuffer();
        result.put(array).flip();
        return result;
    }

    public static ByteBuffer createByteBuffer(byte[] array) {
        ByteBuffer result = ByteBuffer.allocateDirect(array.length).order(nativeOrder());
        result.put(array);
        return result;
    }

    public static IntBuffer createIntBuffer(int[] array) {
        IntBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(nativeOrder()).asIntBuffer();
        result.put(array).flip();
        return result;
    }
}
