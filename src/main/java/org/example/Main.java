package org.example;

import org.joml.Matrix4f;
import org.joml.Vector3f;
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

        Window window = new Window();
        window.setSize(1024, 76 8);
        window.setFullscreen(true);
        window.createWindow("TRUONG NGUYEN HUONG GIANG - 21130338");

        GL.createCapabilities();

        // Enable v-sync
        GLFW.glfwSwapInterval(1);

        // Set clear color to black
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        Camera camera = new Camera(window.getWidth(), window.getHeight());

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


        Matrix4f scale = new Matrix4f()
                .translate(new Vector3f(100, 0, 0))
                .scale(64);

        Matrix4f target = new Matrix4f();

        camera.setPosition(new Vector3f(-100, 0, 0));

        double frame_cap = 1.0/60.0;

        double frame_time = 0;
        int frames = 0;

        double time = Timer.getTime();
        double unprocessed = 0;

        // Main loop
        while (!window.shouldClose()) {
            boolean can_render = false;

            double time_2 = Timer.getTime();
            double passed = time_2 - time;
            unprocessed += passed;
            frame_time += passed;

            time = time_2;

            while (unprocessed >= frame_cap) {
                unprocessed -= frame_cap;
                can_render = true;

                target = scale;
                // Esc button to close window
                if (glfwGetKey(window.getWindow(), GLFW_KEY_ESCAPE) == GLFW_TRUE) {
                    glfwSetWindowShouldClose(window.getWindow(), true);
                }
                // Poll for window events
                GLFW.glfwPollEvents();
                if (frame_time >= 1.0) {
                    frame_time = 0;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }
            if (can_render) {
                // Clear the screen
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

                // Bind texture
                shader.bind();
                shader.setUniform("sampler", 0);
                shader.setUniform("projection", camera.getProjection().mul(target));
                tex.bind(0);
                model.render();

                window.swapBuffers();
                frames++;
            }
        }
        // Clean up
        tex.cleanup();
        window.destroyWindow();
        GLFW.glfwTerminate();
    }
}