package com.ivan9105.graphics;

import com.ivan9105.maths.Matrix4f;
import com.ivan9105.maths.Vector3f;

import java.util.HashMap;
import java.util.Map;

import static com.ivan9105.utils.ShaderUtils.load;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;

public class Shader {
    public static final int VERTEX_ATTR = 0;
    public static final int TCOORDS_ATTR = 1;

    public static Shader BG;

    private int id;
    private Map<String, Integer> locationCache = new HashMap<>();

    public Shader(String vertex, String fragment) {
        id = load(vertex, fragment);
    }

    public static void loadAll() {
        BG = new Shader("shaders/bg.vert", "shaders/bg.frag");
    }

    public int getUniform(String name) {
        if (locationCache.containsKey(name)) {
            return locationCache.get(name);
        }

        int result = glGetUniformLocation(id, name);
        if (result == -1) {
            System.err.println("Could not find uniform variable '" + name + "'");
        } else {
            locationCache.put(name, result);
        }

        return result;
    }

    public void setUniform1i(String name, int value) {
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value) {
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, float x, float y) {
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniform3f(String name, Vector3f vector) {
        glUniform3f(getUniform(name), vector.getX(), vector.getY(), vector.getZ());
    }

    public void setUniformMatrix4f(String name, Matrix4f matrix) {
        glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
    }

    public void enable() {
        glUseProgram(id);
    }

    public void disable() {
        glUseProgram(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
