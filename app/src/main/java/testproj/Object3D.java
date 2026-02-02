package testproj;

public class Object3D {
    public RigidBody rigidBody;

    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;
    public Face[] faces;
    public String name = "Object3D";
    public boolean invertedMesh = false;
    public boolean visible = true;
    
    public Object3D (Vector3 position, Vector3 rotation, Vector3 scale, Face[] faces) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.faces = faces;
    }

    public Object3D (Vector3 position, Vector3 rotation, Vector3 scale, Face[] faces, boolean invertedMesh) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.faces = faces;
        this.invertedMesh = invertedMesh;
    }

    public Object3D (String name, Vector3 position, Vector3 rotation, Vector3 scale, Face[] faces) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.faces = faces;
        this.name = name;
    }

    public Object3D (String name, Vector3 position, Vector3 rotation, Vector3 scale, Face[] faces, boolean invertedMesh) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.faces = faces;
        this.name = name;
        this.invertedMesh = invertedMesh;
    }

    public void setColor (Color3 color) {
        for (Face face : this.faces) {
            face.color = color;
        }
    }

    public Object3D copy () {
        Face[] newFaces = new Face[this.faces.length];
        for (int i = 0; i < this.faces.length; i++) {
            newFaces[i] = this.faces[i].copy();
        }

        Object3D newObj = new Object3D(
            this.name,
            new Vector3(this.position.x, this.position.y, this.position.z),
            new Vector3(this.rotation.x, this.rotation.y, this.rotation.z),
            new Vector3(this.scale.x, this.scale.y, this.scale.z),
            newFaces,
            this.invertedMesh
        );

        return newObj;
    }
}