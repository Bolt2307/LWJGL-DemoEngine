package testproj;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.io.*;

public class App {
    private static Window window;
    private static Camera camera;

    public static void init () {
        Shape.init();
        window = new Window();
        camera = new Camera(new Vector3(0, 0, 0), new Vector3(0, 0, 0), 1.0f, window);
    }

    public static void renderFace (Vector3[] vertices, Color3 color) {
        GL30.glBegin(GL30.GL_POLYGON);
        GL30.glColor3f(color.r, color.g, color.b);

        for (Vector3 vertex : vertices) {
            Vector2 projected = vertex.projectToCamera(camera);
            GL30.glVertex2f(projected.x, projected.y);
        }

        GL30.glEnd();
    }

    public static void loop () {
        GL30.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GL30.glClear(GL11.GL_COLOR_BUFFER_BIT);

        for (Shape shape : Shape.getShapes()) {
            renderFace(shape.vertices, shape.color);
        }
    }

    public static void main (String[] args) {
        init();

        while (!GLFW.glfwWindowShouldClose(window.getWindow())) {
            loop();
            GLFW.glfwPollEvents();
            GLFW.glfwSwapBuffers(window.getWindow());
        }

        window.stop();
    }
}