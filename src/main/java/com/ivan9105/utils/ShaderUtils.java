package com.ivan9105.utils;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class ShaderUtils {
    private static final int ERROR_STATUS = -1;

    private ShaderUtils() {
    }

    public static int load(String vertexPath, String fragmentPath) {
        String vertex = FileUtils.loadAsString(vertexPath);
        String frag = FileUtils.loadAsString(fragmentPath);
        return create(vertex, frag);
    }

    private static int create(String vertex, String fragment) {
        int program = glCreateProgram();
        int vertexId = glCreateShader(GL_VERTEX_SHADER);
        int fragmentId = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(vertexId, vertex);
        glShaderSource(fragmentId, fragment);

        glCompileShader(vertexId);
        if (glGetShaderi(vertexId, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile vertex shader");
            System.err.println(glGetShaderInfoLog(vertexId));
            return ERROR_STATUS;
        }

        glCompileShader(fragmentId);
        if (glGetShaderi(fragmentId, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile frag shader");
            System.err.println(glGetShaderInfoLog(fragmentId));
            return ERROR_STATUS;
        }

        glAttachShader(program, vertexId);
        glAttachShader(program, fragmentId);
        glLinkProgram(program);
        glValidateProgram(program);

        glDeleteShader(vertexId);
        glDeleteShader(fragmentId);

        return program;
    }

}
