package testproj;

import org.lwjgl.glfw.GLFW;

import primitives.untextured.Cube;

public class Player {
    public Object3D model;
    public Camera camera;
    public Vector3 speed;
    private Vector3 position;
    private static final float SENSITIVITY = 0.1f;

    public Player (Object3D model, Camera camera) {
        this.model = model;
        this.camera = camera;
        this.speed = new Vector3(20.0f, 20.0f, 20.0f);
        this.position = model.position;
        camera.position = model.position;
        initMouse();
    }

    public Player (Vector3 position, Camera camera) {
        this.model = new Cube("Character", position, new Vector3(0.0f, 0.0f, 0.0f), new Vector3(1.0f, 1.0f, 1.0f), new Color3[]{Color3.RED, Color3.RED, Color3.RED, Color3.RED, Color3.RED, Color3.RED}, false);
        this.model.visible = false;
        this.camera = camera;
        this.position = position;
        camera.position = position;
        initMouse();
    }

    private void initMouse () {
        GLFW.glfwSetCursorPosCallback(camera.window.getWindow(), (windowHandle, x, y) -> {
            if (camera.window.isMouseLocked()) {
                float cx = camera.window.getDimensions().x / 2.0f;
                float cy = camera.window.getDimensions().y / 2.0f;

                camera.yaw += (x - cx) * SENSITIVITY;
                camera.pitch -= (y - cy) * SENSITIVITY;

                Vector2 rad = new Vector2((float)(camera.yaw * Math.PI / 180.0f), (float)(camera.pitch * Math.PI / 180.0f));

                camera.forward = new Vector3((float)(Math.cos(rad.y) * Math.sin(rad.x)), (float)(Math.sin(rad.y)), (float)(Math.cos(rad.y) * Math.cos(rad.x))).normalize();
                camera.right = camera.forward.cross(Vector3.Y_VECTOR).normalize();
                camera.up = camera.right.cross(camera.forward).normalize();

                GLFW.glfwSetCursorPos(camera.window.getWindow(), cx, cy);
            }
        });
    }

    public void update (float delta) {
        Vector3 velocity = new Vector3(0, 0, 0);

        if (GLFW.glfwGetKey(camera.window.getWindow(), GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {
            velocity = velocity.add(camera.forward.multiply((float)(speed.z * delta)));
        }

        if (GLFW.glfwGetKey(camera.window.getWindow(), GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
            velocity = velocity.subtract(camera.forward.multiply((float)(speed.z * delta)));
        }

        if (GLFW.glfwGetKey(camera.window.getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
            velocity = velocity.add(camera.right.multiply((float)(speed.x * delta)));
        }

        if (GLFW.glfwGetKey(camera.window.getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
            velocity = velocity.subtract(camera.right.multiply((float)(speed.x * delta)));
        }

        if (GLFW.glfwGetKey(camera.window.getWindow(), GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS) {
            velocity.y -= speed.y * delta;
        }

        if (GLFW.glfwGetKey(camera.window.getWindow(), GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
            velocity.y += speed.y * delta;
        }

        if (GLFW.glfwGetKey(camera.window.getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {
            speed = new Vector3(60.0f, 60.0f, 60.0f);
            camera.fov = 38.0f;
        } else {
            speed = new Vector3(20.0f, 20.0f, 20.0f);
            camera.fov = 40.0f;
        }

        position = position.add(velocity);
        camera.position = camera.position.add(velocity);
        model.position = model.position.add(velocity);
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
        this.model.position = position;
        this.camera.position = position;
    }

    public void translate(Vector3 translation) {
        this.position = this.position.add(translation);
        this.model.position = this.model.position.add(translation);
        this.camera.position = this.camera.position.add(translation);
    }
}