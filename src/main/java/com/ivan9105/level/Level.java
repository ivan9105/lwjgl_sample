package com.ivan9105.level;

import com.ivan9105.graphics.Shader;
import com.ivan9105.graphics.VertexArray;

public class Level {
    private VertexArray background;

    public Level() {
        float[] vertexes = new float[]{
                -10.0f, -10.0f * 9.0f, 0.0f,
                -10.0f, 10.0f * 9.0f, 0.0f,
                0.0f, 10.0f * 9.0f, 0.0f,
                0.0f, -10.0f * 9.0f, 0.0f,
        };

        byte[] indices = new byte[]{
                0, 1, 2,
                2, 3, 0
        };

        float[] tcs = new float[]{
                0, 1,
                0, 0,
                1, 0,
                1, 1
        };

        background = new VertexArray(vertexes, indices, tcs);
    }

    public void render() {
        Shader.BG.enable();
        background.render();
        Shader.BG.disable();
    }
}
