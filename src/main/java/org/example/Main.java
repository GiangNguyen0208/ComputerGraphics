package org.example;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.*;

public class Main {
    public static void main(String[] args) {
        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Create a window
        long window = GLFW.glfwCreateWindow(800, 600, "Window", 0, 0);
        if (window == 0) {
            GLFW.glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        // Enable v-sync
        GLFW.glfwSwapInterval(1);

        // Set clear color to black
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        Texture tex = new Texture("D:\\Code\\SubjectProject\\DHMT\\ComputerGraphics\\src\\main\\resources\\img\\mushroom_sprite_pack.png");

        // Main loop
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Esc button to close window
            if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_TRUE) {
                glfwSetWindowShouldClose(window, true);
            }

            // Poll for window events
            GLFW.glfwPollEvents();

            // Clear the screen
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // Bind texture
            tex.bind();

            // Draw Quads
            glBegin(GL11.GL_QUADS);
            // Toàn bộ sprite pack (0-1 cho cả 192x32)
                glTexCoord2f(0.0f, 0.0f); glVertex2f(-0.5f, 0.5f);  // Top-left
                glTexCoord2f(1.0f, 0.0f); glVertex2f(0.5f, 0.5f);   // Top-right
                glTexCoord2f(1.0f, 1.0f); glVertex2f(0.5f, -0.5f); // Bottom-right
                glTexCoord2f(0.0f, 1.0f); glVertex2f(-0.5f, -0.5f); // Bottom-left
            glEnd();

            // Swap buffers
            GLFW.glfwSwapBuffers(window);
        }

        // Clean up
        tex.cleanup();
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}