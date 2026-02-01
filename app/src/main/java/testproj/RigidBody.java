package testproj;

import java.util.ArrayList;

public class RigidBody {
    public static ArrayList<RigidBody> rigidBodies = new ArrayList<>();
    public ArrayList<RigidBody> collidingBodies = new ArrayList<>();

    public Object3D object;
    public Object3D collider;
    public boolean active = true;
    public boolean collidable = true;
    public boolean anchored = false;
    public float mass = 1.0f;
    public float gravityScale = 1.0f;

    public Vector3 p1 = Vector3.ZERO;
    public Vector3 p2 = Vector3.ZERO;

    public Vector3 position = Vector3.ZERO;
    public Vector3 velocity = Vector3.ZERO;
    public Vector3 acceleration = Vector3.ZERO;

    public RigidBody (Object3D object) {
        this.object = object;
        object.rigidBody = this;
        position = object.position;
        this.collider = object;

        rigidBodies.add(this);
        findProjected();
    }

    public RigidBody (Object3D object, Object3D Collider, boolean resize) {
        this.object = object;
        object.rigidBody = this;
        position = object.position;
        this.collider = Collider;
        this.collider.visible = false;

        if (resize) this.collider.scale = this.object.scale;

        rigidBodies.add(this);
        findProjected();
    }

    public void findProjected () {
        for (Face face : collider.faces) {
            for (Vector3 vertex : face.vertices) {
                Vector3 vert = vertex.add(position);
                vert = vert.rotate(collider.rotation.toRadians());

                if (vert.x < p1.x) p1.x = vert.x;
                if (vert.y < p1.y) p1.y = vert.y;
                if (vert.z < p1.z) p1.z = vert.z;
                if (vert.x > p2.x) p2.x = vert.x;
                if (vert.y > p2.y) p2.y = vert.y;
                if (vert.z > p2.z) p2.z = vert.z;
            }
        }
    }

    public void checkCollisions (ArrayList<RigidBody> bodies) {
        boolean updated = false;
        collidingBodies.clear();

        for (RigidBody body : bodies) {
            if (body == this || !body.collidable) continue;
            if (this.position.subtract(body.position).magnitude() <= this.collider.scale.subtract(body.collider.scale).magnitude()) continue;

            if (!updated) {
                findProjected();
                updated = true;
            }

            if (Helper.overlap(this.p1, this.p2, body.p1, body.p2)) {
                collidingBodies.add(body);
            }
        }
    }
}