package testproj;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.io.*;

public class App {
    private static Window window;
    private static int vao;
    private static String vertexPath = "app\\\\src\\\\main\\\\resources\\\\shaders\\\\Vertex.glsl";
    private static String fragmentPath = "app\\\\src\\\\main\\\\resources\\\\shaders\\\\Fragment.glsl";

    public static void init () {
        Shape.init();

        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        int coordVBO = GL30.glGenVertexArrays();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, coordVBO);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, Shape.getShapes().get(0).vertices.capacity(), GL30.GL_STATIC_DRAW);
        GL30.glVertexAttribPointer(0, 2, GL30.GL_DOUBLE, false, 0, 0);
        GL30.glEnableVertexAttribArray(0);

        int colorVBO = GL30.glGenVertexArrays();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, colorVBO);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, Shape.getShapes().get(0).colors.capacity(), GL30.GL_STATIC_DRAW);
        GL30.glVertexAttribPointer(1, 3, GL30.GL_DOUBLE, false, 0, 0);
        GL30.glEnableVertexAttribArray(1);

        int indicesVBO = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, indicesVBO);
        GL30.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, Shape.getShapes().get(0).indices.capacity(), GL30.GL_STATIC_DRAW);
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);

        int vertexShader = Helper.loadShader(new File (vertexPath), GL30.GL_VERTEX_SHADER);
        int fragmentShader = Helper.loadShader(new File (fragmentPath), GL30.GL_FRAGMENT_SHADER);
        int program = GL30.glCreateProgram();

        GL30.glAttachShader(program, vertexShader);
        GL30.glAttachShader(program, fragmentShader);
        GL30.glLinkProgram(program);
        GL30.glValidateProgram(program);

        int error = GL30.glGetError();
		while(error != 0) {
			System.out.println("OpenGL Error: " + error);
			error = GL30.glGetError();
		}

        GL30.glUseProgram(program);
    }

    public static void loop () {
        GL30.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GL30.glBindVertexArray(vao);
        GL30.glDrawElements(GL30.GL_TRIANGLES, Shape.getShapes().get(0).vertexCount, GL30.GL_UNSIGNED_INT, 0);
        GL30.glBindVertexArray(0);
        GL30.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public static void main (String[] args) {
        window = new Window();
        init();

        while (!GLFW.glfwWindowShouldClose(window.getWindow())) {
            loop();
            GLFW.glfwPollEvents();
            GLFW.glfwSwapBuffers(window.getWindow());
        }

        window.stop();
    }
}