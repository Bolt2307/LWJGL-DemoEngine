package testproj;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
public class App {
    private static final ArrayList<Face> renderQueue = new ArrayList<>();
    private static Player player;
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
        player = new Player(Vector3.ZERO.copy(), camera, new Vector3(0.0f, 1.0f, 0.0f), mainScene);
    }

    public static void renderFrame () {
        for (Face face : renderQueue) {
            renderFace(face.vertices, face.color);
        }

        renderQueue.clear();
    }

    public static void renderFace (Vector3[] vertices, Color3 color) {
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glColor4f(color.r, color.g, color.b, color.a);
        GL11.glBegin(GL11.GL_POLYGON);

        for (Vector3 vertex : vertices) {
            GL11.glVertex3f(vertex.x, vertex.y, vertex.z/camera.renderDistance);
        }

        GL11.glEnd();
    }

    public static void pushFaceToQueue (Face face, Vector3 offset, Vector3 rotation, Vector3 scale, boolean inverted) {
        Vector3[] projVertices = new Vector3[face.vertices.length];

        for (int i = 0; i < face.vertices.length; i++) {
            Vector3 scaled = face.vertices[i].multiply(scale);
            Vector3 rotated = scaled.rotate(rotation.toRadians());
            Vector3 translated = rotated.add(offset);
            Vector3 relative = translated.subtract(camera.position);
            Vector3 spun = new Vector3(relative.dot(camera.right), relative.dot(camera.up), relative.dot(camera.forward));
            Vector3 projected = spun.projectToCamera(camera);

            if (projected.z < 0.0f) {
                return;
            }

            projVertices[i] = projected;
        }

        float area = Helper.shoelace(projVertices);

        if (area * Helper.boolToInt(!inverted) > 0.0f) {
            renderQueue.add(new Face(projVertices, face.color));
        }
    }

    public static void update (double delta) {
        debounce -= delta;

        if ((GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS) && (debounce <= 0.0f)) {
            window.lockMouse();
            debounce = 0.2f;
        }

        if ((GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_Q) == GLFW.GLFW_PRESS) && (debounce <= 0.0f)) { // Debug info
            Physics.paused = !Physics.paused;
            debounce = 0.2f;
        }

        if ((GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_E) == GLFW.GLFW_PRESS) && (debounce <= 0.0f)) { // Debug info
            System.out.println(camera);
            System.out.println("Forward: " + camera.forward);
            System.out.println("Right: " + camera.right);
            System.out.println("Up: " + camera.up);
            System.out.println("Position: " + player.getPosition());

            System.out.println("Cube3 velocity: " + mainScene.getObjectByName("Cube3").rigidBody.velocity);
            System.out.println("Cube3 acceleration: " + mainScene.getObjectByName("Cube3").rigidBody.acceleration);
            debounce = 0.2f;
        }

        mainScene.getObjectByName("Cube1").rotation = new Vector3((30.0f * runTime), (45.0f * runTime), (15.0f * runTime));
        mainScene.getObjectByName("Plumbob").rotation = new Vector3(0.0f, (float)(45.0f * runTime), 0.0f);
        mainScene.getObjectByName("Plumbob").position = new Vector3(10.0f, (float)(10.0f*Math.sin(runTime*2)), 60.0f);
    }

    public static void loop () {
        delta = (System.currentTimeMillis() - lastTick)/1000.0f;
        lastTick = System.currentTimeMillis();
        runTime += delta;

        GL11.glClearColor(mainScene.backgroundColor.r, mainScene.backgroundColor.g, mainScene.backgroundColor.b, 1.0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        for (Object3D obj : mainScene.objects) {
            if (obj.visible) {
                for (Face face : obj.faces) {
                    pushFaceToQueue(face, obj.position, obj.rotation, obj.scale, obj.invertedMesh);
                }
            }
        }

        renderFrame();
        update(delta);
        player.update(delta);
        Physics.update(delta);

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