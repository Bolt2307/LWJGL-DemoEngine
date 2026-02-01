package testproj;

import java.util.ArrayList;

import primitives.untextured.Capsule;
import primitives.untextured.Cube;
import primitives.untextured.Cylinder;
import primitives.untextured.Sphere;
import primitives.untextured.SquareBipyramid;
import primitives.untextured.SquarePyramid;
import primitives.untextured.Wedge;

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

        //========== Create Scene Objects ==========
        this.objects.add(new Capsule(
            "Bean",
            new Vector3(-3.0f, 20.0f, -2.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(2.5f, 2.5f, 2.5f),
            Color3.GREEN,
            8,
            8,
            1.0f,
            false
        ));

        this.objects.add(new Cylinder(
            "Can",
            new Vector3(30.0f, 0.0f, -20.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(2.5f, 5.0f, 2.5f),
            Color3.RED,
            16,
            8,
            true,
            false
        ));

        this.objects.add(new Cube(
            "Cube1",
            new Vector3(0.0f, 0.5f, 10.0f),
            new Vector3(0.0f, 45.0f, 0.0f),
            new Vector3(5.0f, 1.0f, 5.0f),
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
            new Vector3(-30.0f, 5.0f, 20.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(10.0f, 10.0f, 10.0f),
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
            "Ground",
            new Vector3(-30.0f, -10.0f, -20.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(10.0f, 1.0f, 10.0f),
            new Color3[] {
                new Color3(0.5f, 0.5f, 0.5f),
                new Color3(0.5f, 0.5f, 0.5f),
                new Color3(0.5f, 0.5f, 0.5f),
                new Color3(0.5f, 0.5f, 0.5f),
                new Color3(0.5f, 0.5f, 0.5f),
                new Color3(0.5f, 0.5f, 0.5f)
            },
            false
        ));

        this.objects.add(new Wedge(
            "Wedge1",
            new Vector3(20.0f, 2.5f, 10.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(5.0f, 5.0f, 5.0f),
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
            new Vector3(0.0f, 2.5f, -20.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(5.0f, 5.0f, 5.0f),
            Color3.BLUE,
            8,
            false
        ));

        this.objects.add(new SquareBipyramid(
            "Plumbob",
            new Vector3(10.0f, 5.0f, 60.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(5.0f, 10.0f, 5.0f),
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
            new Vector3(-2.0f, 5.0f, 60.00f),
            new Vector3(0.0f, 45.0f, 0.0f),
            new Vector3(5.0f, 10.0f, 5.0f),
            new Color3[] {
                Color3.RED,
                Color3.GREEN,
                Color3.BLUE,
                Color3.YELLOW,
                Color3.PURPLE
            },
            false
        ));

        //========== Create Scene RigidBodies ==========

        new RigidBody(this.getObjectByName("Bean"));
        new RigidBody(this.getObjectByName("Ground")).anchored = true;
    }
}