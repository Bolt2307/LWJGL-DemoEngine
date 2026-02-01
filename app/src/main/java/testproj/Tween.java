package testproj;

public class Tween {
    public float duration;
    public float time;

    public Vector3 reference;
    public Vector3 target;

    public Tween (Vector3 reference, Vector3 target, float duration) {
        this.reference = reference;
        this.target = target;
        this.duration = duration;
        this.time = 0.0f;
    }
}