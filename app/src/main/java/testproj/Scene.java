package testproj;

import java.util.ArrayList;

import primitives.Cube;
import primitives.Wedge;

public class Scene {
    public Color3 backgroundColor;
    public ArrayList<Object> objects;

    public Scene (Color3 backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.objects = new ArrayList<>();
    }

    public void init () {
        this.objects.add(new Cube(
            "Cube1",
            new Vector3(0.0f, 5.0f, 100.0f),
            new Vector3(0.0f, 45.0f, 0.0f),
            new Vector3(50.0f, 10.0f, 50.0f),
            new Color3[] {
                Color3.RED,
                Color3.RED,
                Color3.GREEN,
                Color3.GREEN,
                Color3.BLUE,
                Color3.BLUE
            }
        ));

        this.objects.add(new Cube(
            "Cube2",
            new Vector3(-300.0f, 50.0f, 200.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(100.0f, 100.0f, 100.0f),
            new Color3[] {
                Color3.RED,
                Color3.RED,
                Color3.GREEN,
                Color3.GREEN,
                Color3.BLUE,
                Color3.BLUE
            }
        ));

        this.objects.add(new Wedge(
            "Wedge1",
            new Vector3(200.0f, 25.0f, 100.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(50.0f, 50.0f, 50.0f),
            new Color3[] {
                Color3.RED,
                Color3.RED,
                Color3.GREEN,
                Color3.GREEN,
                Color3.BLUE,
            }
        ));
    }
}