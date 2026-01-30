package testproj;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class App {
    private static final ArrayList<Face> renderQueue = new ArrayList<>();
    private static final float SENSITIVITY = 0.1f;
    private static Vector3 speed = new Vector3(200.0f, 200.0f, 200.0f);
    private static Window window;
    private static Camera camera;
    private static Scene mainScene;
    private static float debounce = 0.0f;
    private static float delta = 0.0f;
    private static float runTime = 0.0f;
    private static long lastTick = System.currentTimeMillis();

    public static void init () {
        mainScene = new Scene(new Color3(1.0f, 1.0f, 1.0f));
        mainScene.init();
        window = new Window();
        window.lockMouse(true);
        camera = new Camera(new Vector3(0.0f, 0.0f, -100.0f), new Vector3(0.0f, 0.0f, 0.0f), 45.0f, window);

        GLFW.glfwSetCursorPosCallback(window.getWindow(), (windowHandle, x, y) -> {
            if (window.isMouseLocked()) {
                float centerX = window.getDimensions().x / 2.0f;
                float centerY = window.getDimensions().y / 2.0f;
                float deltaX = (float)(x - centerX);
                float deltaY = (float)(y - centerY);
                camera.rotation.y += deltaX * SENSITIVITY;
                camera.rotation.x -= deltaY * SENSITIVITY;

                if (camera.rotation.x > 89.0f) {
                    camera.rotation.x = 89.0f;
                }
                if (camera.rotation.x < -89.0f) {
                    camera.rotation.x = -89.0f;
                }

                GLFW.glfwSetCursorPos(window.getWindow(), centerX, centerY);
            }
        });
    }

    public static void renderFrame () {
        for (Face face : renderQueue) {
            face.getZOrderValue();
        }

        renderQueue.sort((a, b) -> Float.compare(b.zOrder, a.zOrder));

        for (Face face : renderQueue) {
            renderFace(face.vertices, face.color);
        }

        renderQueue.clear();
    }

    public static void renderFace (Vector3[] vertices, Color3 color) {
        GL30.glBegin(GL30.GL_POLYGON);
        GL30.glColor3f(color.r, color.g, color.b);

        for (Vector3 vertex : vertices) {
            GL30.glVertex2f(vertex.x, vertex.y);
        }

        GL30.glEnd();
    }

    public static void pushFaceToQueue (Face face, Vector3 offset, Vector3 rotation, Vector3 scale) {
        Vector3[] projVertices = new Vector3[face.vertices.length];

        for (int i = 0; i < face.vertices.length; i++) {
            Vector3 scaled = face.vertices[i].multiply(scale);
            Vector3 rotated = scaled.rotate(rotation.toRadians());
            Vector3 translated = rotated.add(offset);
            Vector3 relative = translated.substract(camera.position);
            Vector3 spun = relative.rotate(camera.rotation.toRadians());
            Vector3 projected = spun.projectToCamera(camera);

            if (projected.z < 0) {
                return;
            }

            projVertices[i] = projected;
        }

        if (Helper.shoelace(projVertices) > 0.0f) {
            renderQueue.add(new Face(projVertices, face.color));
        }
    }

    public static void update (double delta) {
        Vector3 velocity = new Vector3(0, 0, 0);
        debounce -= delta;

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {
            velocity.z += speed.z*Math.cos(camera.rotation.toRadians().y) * delta;
            velocity.x += speed.z*Math.sin(camera.rotation.toRadians().y) * delta;
        }

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
            velocity.z -= speed.z*Math.cos(camera.rotation.toRadians().y) * delta;
            velocity.x -= speed.z*Math.sin(camera.rotation.toRadians().y) * delta;
        }

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
            velocity.z -= speed.x*Math.cos(camera.rotation.toRadians().y + Math.PI/2) * delta;
            velocity.x -= speed.x*Math.sin(camera.rotation.toRadians().y + Math.PI/2) * delta;
        }

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
            velocity.z += speed.x*Math.cos(camera.rotation.toRadians().y + Math.PI/2) * delta;
            velocity.x += speed.x*Math.sin(camera.rotation.toRadians().y + Math.PI/2) * delta;
        }

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS) {
            velocity.y -= speed.y * delta;
        }

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
            velocity.y += speed.y * delta;
        }

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {
            speed = new Vector3(600.0f, 600.0f, 600.0f);
            camera.focalLength = 37.0f;
        } else {
            speed = new Vector3(200.0f, 200.0f, 200.0f);
            camera.focalLength = 40.0f;
        }

        if ((GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS) && (debounce <= 0.0f)) {
            window.lockMouse();
            debounce = 0.2f;
        }

        camera.position = camera.position.add(velocity);
    }

    public static void loop () {
        delta = (System.currentTimeMillis() - lastTick)/1000.0f;
        lastTick = System.currentTimeMillis();
        runTime += delta;

        GL30.glClearColor(mainScene.backgroundColor.r, mainScene.backgroundColor.g, mainScene.backgroundColor.b, 1.0f);
        GL30.glClear(GL11.GL_COLOR_BUFFER_BIT);

        for (Object obj : mainScene.objects) {
            if (obj.visible) {
                for (Face face : obj.faces) {
                    pushFaceToQueue(face, obj.position, obj.rotation, obj.scale);
                }
            }
        }

        renderFrame();
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