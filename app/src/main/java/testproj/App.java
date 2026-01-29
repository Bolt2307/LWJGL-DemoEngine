package testproj;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

public class App {
    private static Window window;
    private static Camera camera;
    private static Scene mainScene;
    private static Vector3 speed = new Vector3(200.0f, 200.0f, 200.0f);
    private static float delta = 0.0f;
    private static long lastTick = System.currentTimeMillis();

    public static void init () {
        mainScene = new Scene(new Color3(1.0f, 1.0f, 1.0f));
        mainScene.init();
        window = new Window();
        camera = new Camera(new Vector3(0.0f, 0.0f, -100.0f), new Vector3(0.0f, 0.0f, 0.0f), 90.0f, window);
    }

    public static void renderFace (Vector3[] vertices, Vector3 offset, Vector3 rotation, Vector3 scale, Color3 color) {
        GL30.glBegin(GL30.GL_POLYGON);
        GL30.glColor3f(color.r, color.g, color.b);

        for (Vector3 vertex : vertices) {
            Vector3 scaled = vertex.multiply(scale);
            Vector3 rotated = scaled.rotate(rotation);
            Vector3 translated = rotated.add(offset);
            Vector3 relative = translated.substract(camera.position);
            Vector3 projected = relative.projectToCamera(camera);

            if (projected.z < 0) {
                GL30.glFlush();
                break;
            }

            GL30.glVertex2f(projected.x, projected.y);
        }

        GL30.glEnd();
    }

    public static void update (double delta) {
        Vector3 motion = new Vector3(0, 0, 0);

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {
            motion.z += speed.z * delta;
        }
        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
            motion.z -= speed.z * delta;
        }
        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
            motion.x -= speed.x * delta;
        }
        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
            motion.x += speed.x * delta;
        }
        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS) {
            motion.y -= speed.y * delta;
        }
        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
            motion.y += speed.y * delta;
        }

        camera.position = camera.position.add(motion);
    }

    public static void loop () {
        delta = (System.currentTimeMillis() - lastTick)/1000.0f;
        lastTick = System.currentTimeMillis();

        GL30.glClearColor(mainScene.backgroundColor.r, mainScene.backgroundColor.g, mainScene.backgroundColor.b, 1.0f);
        GL30.glClear(GL11.GL_COLOR_BUFFER_BIT);

        for (Object obj : mainScene.objects) {
            if (obj.visible) {
                for (Face face : obj.faces) {
                    renderFace(face.vertices, obj.position, obj.rotation, obj.scale, face.color);
                }
            }
        }

        update(delta);

        GLFW.glfwPollEvents();
        GLFW.glfwSwapBuffers(window.getWindow());
    }

    public static void main (String[] args) {
        init();

        while (!GLFW.glfwWindowShouldClose(window.getWindow())) {
            loop();
        }

        window.stop();
    }
}