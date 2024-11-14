package mygame.enemy;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import mygame.*;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.shape.Box;
import java.util.Random;
import mygame.attributes.PhysicsHandler;

public class EnemyChaserControl extends AbstractControl {
    private Ray ray = new Ray();
    public final Camera cam;
    private final Node rootNode;
    private Geometry aggroCube;
    private static Box mesh = new Box(0.25f, 0.25f, 0.25f);
    private AssetManager assetManager;
    private BulletAppState bulletAppState;
     private Spatial enemy;
    
    // Declare the wander direction and timer variables
    private Vector3f wanderDirection = Vector3f.ZERO; // Default to no movement
    private float wanderTimer = 0f; // Timer for random direction change
        
    private RigidBodyControl physicsControl;  // Store the RigidBodyControl reference
    
    public EnemyChaserControl(Camera cam, Node rootNode, AssetManager assetManager, BulletAppState bulletAppState) {
        this.cam = cam;
        this.rootNode = rootNode;
        this.assetManager = assetManager;
        this.bulletAppState = bulletAppState;
    }
        
    public void setupEnemy() {
        // Aggro Cube
        //aggroCube = myBox("Scared Cube", new Vector3f(13, 0.5f, 9), ColorRGBA.Red);
        //aggroCube.addControl(new EnemyChaserControl(cam, rootNode, assetManager, bulletAppState));
        //rootNode.attachChild(aggroCube);
        //bulletAppState.getPhysicsSpace().add(aggroCube);
        //bulletAppState.setDebugEnabled(true);
        
        
        enemy = assetManager.loadModel("Models/enemy/enemy.j3o");
        enemy.setName("enemy");
        enemy.setLocalTranslation(new Vector3f(12, 0.5f, 9));
        enemy.setLocalScale(3f, 3f, 3f); // x, y, z
        //enemy.rotate(FastMath.HALF_PI, 0, 0);
        //enemy.setUserData("canBePickedUp", false);
        //PhysicsHandler.addPhysics(enemy, false, bulletAppState);
        bulletAppState.getPhysicsSpace().add(enemy);
        rootNode.attachChild(enemy);
        
        
        // Create and attach RigidBodyControl
        physicsControl = new RigidBodyControl(1.0f);  // Set mass to 1 for the cube
        //aggroCube.addControl(physicsControl);
        enemy.addControl(physicsControl);
        bulletAppState.getPhysicsSpace().add(physicsControl);
    }

    @Override
    protected void controlUpdate(float tpf) {
        
        if (physicsControl == null) {
            System.out.println("still null");
            return;  // Early return if the physics control is not yet initialized
        }
        System.out.println("no longer null");
        
        // Set the wandering speed and random movement timer
        float wanderSpeed = 10f;  // Adjust speed as needed
        float stopDistance = 13f;  // Distance at which the enemy starts chasing the player
        float wanderChangeInterval = 2f;  // Time interval (in seconds) to change the wander direction
        
        // Timer to control when to change the wander direction
        if (wanderTimer <= 0) {
            // Randomly choose a new direction
            Random random = new Random();
            float randomX = random.nextFloat() * 2 - 1;  // Random number between -1 and 1 for X
            float randomZ = random.nextFloat() * 2 - 1;  // Random number between -1 and 1 for Z

            wanderDirection = new Vector3f(randomX, 0, randomZ).normalize();  // Normalize to get a unit vector
            wanderTimer = wanderChangeInterval;  // Reset timer for next direction change
        }

        // Move the enemy in the current wandering direction if not close to the player
        if (cam.getLocation().distance(spatial.getLocalTranslation()) > stopDistance) {
            // Set the velocity to wander around
            Vector3f velocity = wanderDirection.mult(wanderSpeed);
            physicsControl.setLinearVelocity(velocity);  // Apply velocity directly through physics control

            // Decrease the wander timer
            wanderTimer -= tpf;
        } else {
            // Move toward the camera if close enough
            Vector3f directionToCam = cam.getLocation().subtract(spatial.getLocalTranslation());
            directionToCam.setY(0);  // Ignore the Y component
            directionToCam.normalize();  // Normalize to get a unit direction

            // Apply velocity to move towards the camera
            Vector3f velocity = directionToCam.mult(10f);  // Adjust speed as needed
            physicsControl.setLinearVelocity(velocity);  // Apply velocity

            // Optional: Rotate the enemy to face the camera using physics control
            if (velocity.length() > 0) {
                Quaternion lookRotation = new Quaternion();
                lookRotation.lookAt(directionToCam, Vector3f.UNIT_Y);
                physicsControl.setPhysicsRotation(lookRotation);  // Apply rotation via physics control
            }
        }
        
        // Decrease wanderTimer each frame
        wanderTimer -= tpf;
    }
        
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        // No rendering logic needed
    }
        
//    public Geometry myBox(String name, Vector3f loc, ColorRGBA color) {
//        Geometry geom = new Geometry(name, mesh);
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", color);
//        geom.setMaterial(mat);
//        geom.setLocalTranslation(loc);
//        return geom;
//    }
}
