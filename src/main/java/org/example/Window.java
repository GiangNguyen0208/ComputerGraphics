package org.example;

import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;

public class Window {
    private long window;
    private int width, height;
    public Window() {
        setSize(640, 480);
    }

    public void createWindow(String title) {
        window = glfwCreateWindow(width, height, title, 0, 0);

        if (window == 0) {
            throw new IllegalStateException("Fail to create window!");
        }
        GLFWVidMode vid = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window,
                (vid.width() - width)/2,
                (vid.height() - height)/2);

        glfwShowWindow(window);

        glfwMakeContextCurrent(window);

    }
    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }
    public void swapBuffers() {
        glfwSwapBuffers(window);
    }
    public void destroyWindow() {
        glfwDestroyWindow(window);
    }
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
