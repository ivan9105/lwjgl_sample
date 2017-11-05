package com.ivan9105.maths;

import com.ivan9105.utils.BufferUtils;

import java.nio.FloatBuffer;

public class Matrix4f {
    private static final int SIZE = 4 * 4;
    private float[] elements = new float[SIZE];

    public Matrix4f() {

    }

    public static Matrix4f identity() {
        Matrix4f result = new Matrix4f();
        for (int i = 0; i < SIZE; i++) {
            result.getElements()[i] = 0.0f;
        }

        result.getElements()[0] = 1.0f;
        result.getElements()[1 + 4] = 1.0f;
        result.getElements()[2 + 2 * 4] = 1.0f;
        result.getElements()[3 + 3 * 4] = 1.0f;
        return result;
    }

    public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
        Matrix4f result = identity();

        result.getElements()[0] = 2.0f / (right - left);
        result.getElements()[1 + 4] = 2.0f / (top - bottom);
        result.getElements()[2 + 2 * 4] = 2.0f / (near - far);

        result.getElements()[3 * 4] = (left + right) / (left - right);
        result.getElements()[1 + 3 * 4] = (bottom + top) / (bottom - top);
        result.getElements()[2 + 3 * 4] = (far + near) / (far - near);
        return result;
    }

    public static Matrix4f translate(Vector3f vector3f) {
        Matrix4f result = identity();

        result.getElements()[3 * 4] = vector3f.getX();
        result.getElements()[1 + 3 * 4] = vector3f.getY();
        result.getElements()[2 + 3 * 4] = vector3f.getZ();
        return result;
    }

    public static Matrix4f rotate(float angle) {
        Matrix4f result = identity();

        float radians = (float) Math.toRadians(angle);
        float cos = (float) Math.cos(radians);
        float sin = (float) Math.sin(radians);

        result.getElements()[0] = cos;
        result.getElements()[1] = sin;

        result.getElements()[1 + 4] = -sin;
        result.getElements()[1 + 4] = cos;
        return result;
    }

    public Matrix4f multiply(Matrix4f matrix4f) {
        Matrix4f result = new Matrix4f();

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                float sum = 0.0f;
                for (int e = 0; e < 4; e++) {
                    sum += getElements()[x + e * 4] * matrix4f.getElements()[e + y * 4];
                }
                result.getElements()[x + y * 4] = sum;
            }
        }
        return result;
    }

    public FloatBuffer toFloatBuffer() {
        return BufferUtils.createFloatBuffer(elements);
    }

    public float[] getElements() {
        return elements;
    }

    public void setElements(float[] elements) {
        this.elements = elements;
    }
}
