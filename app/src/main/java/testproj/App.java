package testproj;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
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
        camera = new Camera(new Vector3(0.0f, 0.0f, -100.0f), 40.0f, window);

        GLFW.glfwSetCursorPosCallback(window.getWindow(), (windowHandle, x, y) -> {
            if (window.isMouseLocked()) {
                updateMouse(new Vector2((float)x, (float)y));
            }
        });
    }

    public static void updateMouse (Vector2 mousePos) {
        float cx = window.getDimensions().x / 2.0f;
        float cy = window.getDimensions().y / 2.0f;

        camera.yaw += (mousePos.x - cx) * SENSITIVITY;
        camera.pitch -= (mousePos.y - cy) * SENSITIVITY;

        Vector2 rad = new Vector2((float)(camera.yaw * Math.PI / 180.0f), (float)(camera.pitch * Math.PI / 180.0f));

        camera.forward = new Vector3((float)(Math.cos(rad.y) * Math.sin(rad.x)), (float)(Math.sin(rad.y)), (float)(Math.cos(rad.y) * Math.cos(rad.x))).normalize();
        camera.right = camera.forward.cross(Vector3.Y_VECTOR).normalize();
        camera.up = camera.right.cross(camera.forward).normalize();

        GLFW.glfwSetCursorPos(window.getWindow(), cx, cy);
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
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor3f(color.r, color.g, color.b);

        for (Vector3 vertex : vertices) {
            GL11.glVertex2f(vertex.x, vertex.y);
        }

        GL11.glEnd();
    }

    public static void pushFaceToQueue (Face face, Vector3 offset, Vector3 rotation, Vector3 scale) {
        Vector3[] projVertices = new Vector3[face.vertices.length];

        for (int i = 0; i < face.vertices.length; i++) {
            Vector3 scaled = face.vertices[i].multiply(scale);
            Vector3 rotated = scaled.rotate(rotation.toRadians());
            Vector3 translated = rotated.add(offset);
            Vector3 relative = translated.substract(camera.position);
            Vector3 spun = new Vector3(relative.dot(camera.right), relative.dot(camera.up), relative.dot(camera.forward));
            Vector3 projected = spun.projectToCamera(camera);

            if (projected.z < 0.0f) {
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
            velocity = velocity.add(camera.forward.multiply((float)(speed.z * delta)));
        }

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
            velocity = velocity.substract(camera.forward.multiply((float)(speed.z * delta)));
        }

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
            velocity = velocity.add(camera.right.multiply((float)(speed.x * delta)));
        }

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
            velocity = velocity.substract(camera.right.multiply((float)(speed.x * delta)));
        }

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS) {
            velocity.y -= speed.y * delta;
        }

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
            velocity.y += speed.y * delta;
        }

        if (GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {
            speed = new Vector3(600.0f, 600.0f, 600.0f);
            camera.fov = 37.0f;
        } else {
            speed = new Vector3(200.0f, 200.0f, 200.0f);
            camera.fov = 40.0f;
        }

        if ((GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS) && (debounce <= 0.0f)) {
            window.lockMouse();
            debounce = 0.2f;
        }

        if ((GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_E) == GLFW.GLFW_PRESS) && (debounce <= 0.0f)) { // Debug info
            System.out.println(camera);
            System.out.println("Forward: " + camera.forward);
            System.out.println("Right: " + camera.right);
            System.out.println("Up: " + camera.up);
            debounce = 0.2f;
        }

        camera.position = camera.position.add(velocity);
    }

    public static void loop () {
        delta = (System.currentTimeMillis() - lastTick)/1000.0f;
        lastTick = System.currentTimeMillis();
        runTime += delta;

        GL11.glClearColor(mainScene.backgroundColor.r, mainScene.backgroundColor.g, mainScene.backgroundColor.b, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

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