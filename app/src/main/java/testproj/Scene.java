package testproj;

import java.util.ArrayList;

import primitives.untextured.*;

public class Scene {
    public Color3 backgroundColor;
    public ArrayList<Object3D> objects;

    public Scene (Color3 backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.objects = new ArrayList<>();
    }


    public Object3D getObjectByName (String name) {
        for (Object3D obj : this.objects) {
            if (obj.name.equals(name)) {
                return obj;
            }
        }

        return null;
    }

    public void init () {
        this.objects.add(new Capsule(
            "Bean",
            new Vector3(-300.0f, 0.5f, -200.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(25.0f, 25.0f, 25.0f),
            Color3.GREEN,
            8,
            8,
            1.0f,
            false
        ));

        this.objects.add(new Cylinder(
            "Can",
            new Vector3(300.0f, 0.0f, -200.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(25.0f, 50.0f, 25.0f),
            Color3.RED,
            16,
            8,
            true,
            false
        ));

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
            },
            false
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
            },
            false
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
            },
            false
        ));

        this.objects.add(new Sphere(
            "Ball",
            new Vector3(0.0f, 25.0f, -200.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(50.0f, 50.0f, 50.0f),
            Color3.BLUE,
            8,
            false
        ));

        this.objects.add(new SquareBipyramid(
            "Plumbob",
            new Vector3(100.0f, 50.0f, 600.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(50.0f, 100.0f, 50.0f),
            new Color3[] {
                Color3.RED,
                Color3.GREEN,
                Color3.BLUE,
                Color3.YELLOW,
                Color3.YELLOW,
                Color3.BLUE,
                Color3.GREEN,
                Color3.RED

            },
            false
        ));

        this.objects.add(new SquarePyramid(
            "Pyramid",
            new Vector3(-200.0f, 25.0f, 600.0f),
            new Vector3(0.0f, 45.0f, 0.0f),
            new Vector3(50.0f, 100.0f, 50.0f),
            new Color3[] {
                Color3.RED,
                Color3.GREEN,
                Color3.BLUE,
                Color3.YELLOW,
                Color3.PURPLE
            },
            false
        ));
    }
}