package com.ivan9105;


import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main implements Runnable {
    private static final Integer SCREEN_WIDTH = 1280;
    private static final Integer SCREEN_HEIGHT = 720;

    private Thread thread;
    private boolean running = false;
    private long window;

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
        if (window == NULL) {
            //Todo handle it
            return;
        }

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
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
        glfwSwapBuffers(window);
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
