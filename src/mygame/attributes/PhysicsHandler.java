package mygame.attributes;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Spatial;

public class PhysicsHandler {
    public static void addPhysics(Spatial spa, boolean isDynamic, BulletAppState bulletAppState) {
        float mass = isDynamic ? 1.0f : 0.0f;

        RigidBodyControl physicsControl = new RigidBodyControl(mass);
        spa.addControl(physicsControl);

        bulletAppState.getPhysicsSpace().add(physicsControl);
    }
}
