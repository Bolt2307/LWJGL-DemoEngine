package testproj;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

public class Window {
    private long window;

    public Window() {
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("GLFW initialization failed");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);

        window = GLFW.glfwCreateWindow(800, 600, "Test", 0, 0);
        if (window == 0) {
            throw new IllegalStateException("Failed to create window");
        }

        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwShowWindow(window);
        GL.createCapabilities();
    }

    public long getWindow () {
        return window;
    }

    public void stop () {
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public Vector2 getDimensions () {
        int[] width = new int[1];
        int[] height = new int[1];
        GLFW.glfwGetWindowSize(window, width, height);
        return new Vector2(width[0], height[0]);
    }
}