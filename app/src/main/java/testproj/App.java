package testproj;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL30;

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

    private static int vao;
    private static int shaderProgram;

    public static void init () {
        window = new Window();
        window.lockMouse(true);
        mainScene = new Scene(new Color3(0.0f, 0.0f, 0.0f));
        mainScene.init();
        camera = new Camera(new Vector3(0.0f, 0.0f, -100.0f), 40.0f, window);
        player = new Player(Vector3.ZERO.copy(), camera, new Vector3(0.0f, 1.0f, 0.0f), mainScene);
    }

    public static int createShaderProgram() {
        String vertexSrc =
                "#version 330 core\n" +
                "layout (location = 0) in vec3 aPos;\n" +
                "layout (location = 1) in vec3 aColor;\n" +
                "out vec3 vColor;\n" +
                "uniform mat4 mvp;\n" +
                "void main() {\n" +
                "  vColor = aColor;\n" +
                "  gl_Position = mvp * vec4(aPos, 1.0);\n" +
                "}";

        String fragmentSrc =
                "#version 330 core\n" +
                "in vec3 vColor;\n" +
                "out vec4 FragColor;\n" +
                "void main() {\n" +
                "  FragColor = vec4(vColor, 1.0);\n" +
                "}";

        int vs = GL30.glCreateShader(GL30.GL_VERTEX_SHADER);
        GL30.glShaderSource(vs, vertexSrc);
        GL30.glCompileShader(vs);

        int fs = GL30.glCreateShader(GL30.GL_FRAGMENT_SHADER);
        GL30.glShaderSource(fs, fragmentSrc);
        GL30.glCompileShader(fs);

        int program = GL30.glCreateProgram();
        GL30.glAttachShader(program, vs);
        GL30.glAttachShader(program, fs);
        GL30.glLinkProgram(program);

        GL30.glDeleteShader(vs);
        GL30.glDeleteShader(fs);

        return program;
    }

    public static void renderFrame () {
        for (Face face : renderQueue) {
            renderFace(face.vertices, face.color, face.texture, face.textured);
        }

        renderQueue.clear();
    }

    public static void renderFace (Vector3[] vertices, Color3 color, Texture texture, boolean textured) {
        GL30.glMatrixMode(GL30.GL_MODELVIEW);
        GL30.glLoadIdentity();

        if ((textured) && (vertices.length == 4)) {
            texture.pushTexture();
            GL30.glEnable(GL30.GL_TEXTURE_2D);
            GL30.glBegin(GL30.GL_QUADS);
            GL30.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

            for (int i = 0; i < vertices.length; i++) {
                GL30.glTexCoord2f((i == 0 || i == 3) ? 0.0f : 1.0f, (i < 2) ? 0.0f : 1.0f);
                GL30.glVertex3f(vertices[i].x, vertices[i].y, vertices[i].z/camera.renderDistance);
            }
        } else {
            GL30.glBegin(GL30.GL_POLYGON);
            GL30.glColor4f(color.r, color.g, color.b, color.a);

            for (Vector3 vertex : vertices) {
                GL30.glVertex3f(vertex.x, vertex.y, vertex.z/camera.renderDistance);
            }
        }

        GL30.glEnd();
    }

    public static void pushFaceToQueue (Face face, Vector3 offset, Vector3 rotation, Vector3 scale, boolean inverted) {
        Vector3[] relativeVertices = new Vector3[face.vertices.length];
        Vector3[] projVertices = new Vector3[face.vertices.length];

        for (int i = 0; i < face.vertices.length; i++) {
            Vector3 scaled = face.vertices[i].multiply(scale);
            Vector3 rotated = scaled.rotate(rotation.toRadians());
            Vector3 translated = rotated.add(offset);
            Vector3 relative = translated.subtract(camera.position);
            Vector3 spun = new Vector3(relative.dot(camera.right), relative.dot(camera.up), relative.dot(camera.forward));
            relativeVertices[i] = spun;
        }

        projVertices = camera.zClip(relativeVertices);
        
        for (int i = 0; i < projVertices.length; i++) {
            projVertices[i] = projVertices[i].projectToCamera(camera);
        }

        float area = Helper.shoelace(projVertices);

        if (area * Helper.boolToInt(!inverted) > 0.0f) {
            renderQueue.add(new Face(projVertices, face.color, face.texture, face.textured));
        }
    }

    public static void update (double delta) {
        debounce -= delta;

        if ((GLFW.glfwGetKey(window.getWindow(), GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS) && (debounce <= 0.0f)) {
            window.lockMouse();
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

        GL30.glClearColor(mainScene.backgroundColor.r, mainScene.backgroundColor.g, mainScene.backgroundColor.b, 1.0f);
        GL30.glEnable(GL30.GL_DEPTH_TEST);
        GL30.glEnable(GL30.GL_BLEND);
        GL30.glDisable(GL30.GL_LIGHTING);
        GL30.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);

        for (Object3D obj : mainScene.objects) {
            if ((obj.visible) && (obj.position.add(obj.scale.multiply(0.5f)).subtract(camera.position).magnitude() < camera.renderDistance)) {
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

        GL30.glDeleteVertexArrays(vao);
        GL30.glDeleteProgram(shaderProgram);
        window.stop();
    }
}