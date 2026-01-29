package testproj;

import primitives.*;
import java.util.ArrayList;

public class Scene {
    public Color3 backgroundColor;
    public ArrayList<Object> objects;

    public Scene (Color3 backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.objects = new ArrayList<Object>();
    }

    public void init () {
        this.objects.add(new Object(
            new Vector3(0.0f, 0.0f, 200.0f),
            new Vector3(0.0f, 0.785f, 0.0f),
            new Vector3(10.0f, 50.0f, 50.0f),
            new Face[] {
                new Face(new Vector3[] {
                    new Vector3(-1.0f, -1.0f, -1.0f), // Front Face
                    new Vector3(-1.0f, 1.0f, -1.0f),
                    new Vector3(1.0f, 1.0f, -1.0f),
                    new Vector3(1.0f, -1.0f, -1.0f),
                }, new Color3(1.0f, 0.0f, 0.0f)),
                new Face(new Vector3[] {
                    new Vector3(-1.0f, -1.0f, 1.0f), // Back Face
                    new Vector3(-1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, -1.0f, 1.0f),
                }, new Color3(1.0f, 0.0f, 0.0f)),
                new Face(new Vector3[] {
                    new Vector3(-1.0f, -1.0f, -1.0f), // Left Face
                    new Vector3(-1.0f, 1.0f, -1.0f),
                    new Vector3(-1.0f, 1.0f, 1.0f),
                    new Vector3(-1.0f, -1.0f, 1.0f),
                }, new Color3(0.0f, 1.0f, 0.0f)),
                new Face(new Vector3[] {
                    new Vector3(1.0f, -1.0f, -1.0f), // Right Face
                    new Vector3(1.0f, 1.0f, -1.0f),
                    new Vector3(1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, -1.0f, 1.0f),
                }, new Color3(0.0f, 1.0f, 0.0f)),
                new Face(new Vector3[] {
                    new Vector3(-1.0f, 1.0f, -1.0f), // Top Face
                    new Vector3(-1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, 1.0f, -1.0f)
                }, new Color3(0.0f, 0.0f, 1.0f)),
                new Face(new Vector3[] {
                    new Vector3(-1.0f, -1.0f, -1.0f), // Bottom Face
                    new Vector3(-1.0f, -1.0f, 1.0f),
                    new Vector3(1.0f, -1.0f, 1.0f),
                    new Vector3(1.0f, -1.0f, -1.0f)
                }, new Color3(0.0f, 0.0f, 1.0f))
            }
        ));
    }
}