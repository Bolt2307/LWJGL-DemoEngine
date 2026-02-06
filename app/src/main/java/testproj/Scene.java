package testproj;

import java.util.ArrayList;

import primitives.Cube;
import primitives.Cylinder;
import primitives.Sphere;
import primitives.SquareBipyramid;
import primitives.SquarePyramid;
import primitives.Wedge;

public class Scene {
    public Color3 backgroundColor;
    public Object3D skybox;
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

        //========== Load Textures ==========

        Texture.textureRef.add(new Texture("notexture.png"));
        Texture.textureRef.add(new Texture("coconut.png"));
        Texture.textureRef.add(new Texture("coconutHighRes.png"));
        Texture.textureRef.add(new Texture("obama.png"));

        //========== Create Skybox ========== (Not Implemented Yet)

        skybox = new Cube(
            "Skybox",
            Vector3.ZERO.copy(),
            Vector3.ZERO.copy(),
            new Vector3(1000.0f, 1000.0f, 1000.0f),
            new Texture[] {
                Texture.textureRef.get(0).copy(),
                Texture.textureRef.get(0).copy(),
                Texture.textureRef.get(0).copy(),
                Texture.textureRef.get(0).copy(),
                Texture.textureRef.get(0).copy(),
                Texture.textureRef.get(0).copy()
            },
            true
        );

        //========== Create Scene Objects ==========

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
            "Cube3",
            new Vector3(-33.0f, 50.0f, -23.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(5.0f, 5.0f, 5.0f),
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
            "TexturedCube",
            new Vector3(0.0f, 0.0f, -50.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(5.0f, 5.0f, 5.0f),
            new Texture[] {
                Texture.textureRef.get(2).copy(),
                Texture.textureRef.get(2).copy(),
                Texture.textureRef.get(2).copy(),
                Texture.textureRef.get(2).copy(),
                Texture.textureRef.get(2).copy(),
                Texture.textureRef.get(2).copy()},
            false
        ));

        this.objects.add(new SquarePyramid(
            "Obamium",
            new Vector3(-50.0f, 0.0f, -50.0f),
            new Vector3(0.0f, 0.0f, 0.0f),
            new Vector3(5.0f, 7.5f, 5.0f),
            new Texture[] {
                Texture.textureRef.get(3).copy(),
                Texture.textureRef.get(3).copy(),
                Texture.textureRef.get(3).copy(),
                Texture.textureRef.get(3).copy(),
                Texture.textureRef.get(3).copy()},
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
    }
}