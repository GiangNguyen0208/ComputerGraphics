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

        // Define vertices for a square (2 triangles)
        float[] vertices = new float[] {
                -0.5f, 0.5f, 0,   // TOP LEFT       0
                0.5f, 0.5f, 0,    // TOP RIGHT      1
                0.5f, -0.5f, 0,   // BOTTOM RIGHT   2
                -0.5f, -0.5f, 0,  // BOTTOM LEFT    3
        };

        // Define texture coordinates for 2 triangles
        float[] texture = new float[] {
                0.0f, 0.0f,  // TOP LEFT        0
                1.0f, 0.0f,  // TOP RIGHT       1
                1.0f, 1.0f,  // BOTTOM RIGHT    2
                0.0f, 1.0f,  // BOTTOM LEFT     3
        };

        int[] indices = new int[] {
                0, 1, 2,
                2, 3, 0,
        };

        Model model = new Model(vertices, texture, indices);
        Shader shader = new Shader("shader");

        Texture tex = new Texture("D:\\Code\\SubjectProject\\DHMT\\ComputerGraphics\\src\\main\\resources\\img\\screenshot_67.png");

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
            shader.bind();
            shader.setUniform("sampler", 0);
            tex.bind(0);
            model.render();

            // Swap buffers
            GLFW.glfwSwapBuffers(window);
        }

        // Clean up
        tex.cleanup();
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}