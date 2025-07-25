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

//        float x = 0;
        float color_red = 1;
        float color_blue= 0;

        // Main loop
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Esc button to close window
            if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_TRUE) {
                glfwDestroyWindow(window);
            }

            // Poll for window events
            GLFW.glfwPollEvents();

            // Execute Action Change by Key A on Board
//            if (glfwGetKey(window, GLFW_KEY_A) == GLFW_TRUE) {
////                x += 0.001f;
//                color_red = 0.25f;
//                color_blue = 1;
//            } else {
//                color_blue = 0.25f;
//                color_red = 1;
//            }

            // Execute Action Change by Mouse Click
//            if (glfwGetMouseButton(window, 0) == GLFW_TRUE) {
//                color_red = 0.25f;
//                color_blue = 1;
//            }

            // Clear the screen
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);



            // Draw Quads
            glBegin(GL11.GL_QUADS);
                glColor4f(color_red,0,color_blue,0);
                glVertex2f(-0.5f, 0.5f);

//                glColor4f(0,1,0,0);
                glVertex2f(0.5f, 0.5f);

//                glColor4f(0,0,1,0);
                glVertex2f(0.5f, -0.5f);

//                glColor4f(0,0,0,1);
                glVertex2f(-0.5f, -0.5f);
            glEnd();

            // Swap buffers
            GLFW.glfwSwapBuffers(window);




        }

        // Clean up
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}