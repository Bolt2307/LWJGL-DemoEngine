package testproj;

public class Object {
    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;
    public Face[] faces;
    public String name = "Object";
    public boolean invertedMesh = false;
    public boolean visible = true;
    
    public Object (Vector3 position, Vector3 rotation, Vector3 scale, Face[] faces) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.faces = faces;
    }

    public Object (Vector3 position, Vector3 rotation, Vector3 scale, Face[] faces, boolean invertedMesh) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.faces = faces;
        this.invertedMesh = invertedMesh;
    }

    public Object (String name, Vector3 position, Vector3 rotation, Vector3 scale, Face[] faces) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.faces = faces;
        this.name = name;
    }

    public Object (String name, Vector3 position, Vector3 rotation, Vector3 scale, Face[] faces, boolean invertedMesh) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.faces = faces;
        this.name = name;
        this.invertedMesh = invertedMesh;
    }
}