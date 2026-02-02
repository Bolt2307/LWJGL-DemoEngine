package testproj;

import java.util.ArrayList;

public class RigidBody {
    public static ArrayList<RigidBody> rigidBodies = new ArrayList<>();
    public ArrayList<RigidBody> collidingBodies = new ArrayList<>();

    public Player player = null;
    public Object3D object;
    public BoxCollider collider;
    public boolean active = true;
    public boolean collidable = true;
    public boolean anchored = false;

    public float damping = 1.0f;
    public float mass = 1.0f;
    public float gravityScale = 1.0f;

    public Vector3 position = Vector3.ZERO.copy();
    public Vector3 velocity = Vector3.ZERO.copy();
    public Vector3 acceleration = Vector3.ZERO.copy();

    public RigidBody (Object3D object, boolean anchored, boolean collidable) {
        this.object = object;
        object.rigidBody = this;
        position = object.position;
        this.anchored = anchored;
        this.collidable = collidable;
        this.collider = new BoxCollider(
            Vector3.ONE.negate(),
            Vector3.ONE.copy(),
            this.position
        ).scale(this.object.scale);

        rigidBodies.add(this);
    }

    public RigidBody (Object3D object, BoxCollider Collider) {
        this.object = object;
        object.rigidBody = this;
        position = object.position;
        this.collider = Collider;

        rigidBodies.add(this);
    }

    public ArrayList<RigidBody> checkCollisions (ArrayList<RigidBody> bodies) {
        collidingBodies.clear();

        for (RigidBody body : bodies) {
            if (body == this || !body.collidable) continue;
            if (this.collider.colliding(body.collider)) collidingBodies.add(body);
        }

        return collidingBodies;
    }
}