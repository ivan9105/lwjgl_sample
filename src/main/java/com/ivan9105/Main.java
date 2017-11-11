package com.ivan9105;


import com.ivan9105.graphics.Shader;
import com.ivan9105.input.Input;
import com.ivan9105.level.Level;
import com.ivan9105.maths.Matrix4f;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main implements Runnable {
    private static final Integer SCREEN_WIDTH = 1280;
    private static final Integer SCREEN_HEIGHT = 720;

    private Thread thread;
    private boolean running = false;
    private long window;

    private Level level;

    public void start() {
        running = true;
        thread = new Thread(this, "LWJGL Sample");
        thread.start();
    }

    private void init() {
        if (!glfwInit()) {
            //Todo handle it
        }

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        window = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "LWJGL Sample", NULL, NULL);
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window,
                vidMode.width() / 2 - SCREEN_WIDTH / 2,
                vidMode.height() / 2 - SCREEN_HEIGHT / 2);

        glfwSetKeyCallback(window, new Input());

        if (window == NULL) {
            //Todo handle it
            return;
        }

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        GL.createCapabilities();
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));
        Shader.loadAll();
        Shader.BG.enable();
        Matrix4f pr_martix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
        Shader.BG.setUniformMatrix4f("pr_matrix", pr_martix);
        Shader.BG.disable();
        level = new Level();
    }

    @Override
    public void run() {
        init();
        while (running) {
            update();
            render();

            if (glfwWindowShouldClose(window)) {
                running = false;
            }
        }
    }

    private void update() {
        glfwPollEvents();
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        level.render();
        int errorCode = glGetError();
        if (errorCode != GL_NO_ERROR) {
            System.out.println(errorCode);
        }
        glfwSwapBuffers(window);
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
