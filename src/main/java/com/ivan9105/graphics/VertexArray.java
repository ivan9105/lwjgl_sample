package com.ivan9105.graphics;

import com.ivan9105.utils.BufferUtils;

import static com.ivan9105.graphics.Shader.TCOORDS_ATTR;
import static com.ivan9105.graphics.Shader.VERTEX_ATTR;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray {
    private int vao, vbo, ibo, tbo;
    private int count;

    public VertexArray(float[] vertexes, byte[] indices, float[] textureCoordinates) {
        count = indices.length;

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertexes), GL_STATIC_DRAW);
        glVertexAttribPointer(VERTEX_ATTR, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(VERTEX_ATTR);

        tbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
        glVertexAttribPointer(TCOORDS_ATTR, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(TCOORDS_ATTR);

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void bind() {
        glBindVertexArray(vao);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
    }

    public void unbind() {
        glBindVertexArray(0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
    }

    public void draw() {
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
    }

    public void render() {
        bind();
        draw();
    }
}
