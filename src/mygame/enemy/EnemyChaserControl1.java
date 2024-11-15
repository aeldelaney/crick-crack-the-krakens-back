package mygame.enemy;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import mygame.*;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.shape.Box;
import java.util.Random;
import mygame.attributes.PhysicsHandler;

public class EnemyChaserControl1 extends AbstractControl {
    private Ray ray = new Ray();
    public final Camera cam;
    private final Node rootNode;
    private Geometry aggroCube;
    private static Box mesh = new Box(0.25f, 0.25f, 0.25f);
    private AssetManager assetManager;
    private BulletAppState bulletAppState;
    
    // Declare the wander direction and timer variables
    private Vector3f wanderDirection = Vector3f.ZERO; // Default to no movement
    private float wanderTimer = 0f; // Timer for random direction change
        
    
    public EnemyChaserControl1(Camera cam, Node rootNode, AssetManager assetManager, BulletAppState bulletAppState) {
        this.cam = cam;
        this.rootNode = rootNode;
        this.assetManager = assetManager;
        this.bulletAppState = bulletAppState;
    }
        
    public void setupEnemy() {
        // Aggro Cube
        //aggroCube = myBox("Scared Cube", new Vector3f(13, 0.5f, 9), ColorRGBA.Red);
        aggroCube = myBox("Scared Cube", new Vector3f(13, 0.5f, 9), ColorRGBA.Red);
        //aggroCube = myBox("Scared Cube", new Vector3f(13, 4, 9), ColorRGBA.Red);
        //aggroCube.addControl(new EnemyChaserControl(cam, rootNode, assetManager, bulletAppState));
        rootNode.attachChild(aggroCube);
        bulletAppState.getPhysicsSpace().add(aggroCube);
        bulletAppState.setDebugEnabled(true);
        
        
        float mass = false ? 1.0f : 0.0f;

        RigidBodyControl physicsControl = new RigidBodyControl(mass);
        aggroCube.addControl(physicsControl);

        bulletAppState.getPhysicsSpace().add(physicsControl);
        // new rigidbody control
        // set velocity and direction through the rigidbody control; setlinearvelocity
        // return the rigid body controler from the physicshandler
    }

    @Override
    protected void controlUpdate(float tpf) {
        
        // Set the wandering speed and random movement timer
        float wanderSpeed = 0.01f;
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
            // Wander around
            spatial.setLocalTranslation(spatial.getLocalTranslation().addLocal(wanderDirection.mult(wanderSpeed)));

            // Decrease the wander timer
            wanderTimer -= tpf;
        } else {
            // Move toward the camera if close enough
            Vector3f directionToCam = cam.getLocation().subtract(spatial.getLocalTranslation());
            directionToCam.setY(0);  // Ignore the Y component
            directionToCam.normalize();  // Normalize to get a unit direction

            // Move the enemy towards the camera
            spatial.setLocalTranslation(spatial.getLocalTranslation().addLocal(directionToCam.mult(0.001f)));

            // Ensure the enemy's Y position does not sink below the ground level
            Vector3f currentPosition = spatial.getLocalTranslation();
            currentPosition.setY(Math.max(currentPosition.y, 0.5f));  // Keep the enemy above the ground
            spatial.setLocalTranslation(currentPosition);
        }
        
        

        // Make Cube jump up to face when close
    //                    if (cam.getLocation().distance(spatial.getLocalTranslation()) <
    //                    6.5) {
    //                        Vector3f camFront = cam.getLocation().add(new Vector3f (1,0,2));
    //                        Vector3f directionToCam = camFront.subtract(spatial.getLocalTranslation()).normalize();
    //                        spatial.setLocalTranslation(spatial.getLocalTranslation().addLocal(directionToCam.mult(0.5f)));
    //                    }
            // Cube chase
//        if (cam.getLocation().distance(spatial.getLocalTranslation()) < 13) {
////            Vector3f camDown = cam.getLocation().add(new Vector3f (0,-3.75f,0));
////            //Vector3f camDown = cam.getLocation().add(new Vector3f (0,0,0));
////            Vector3f directionToCam = camDown.subtract(spatial.getLocalTranslation()).normalize();
////            spatial.setLocalTranslation(spatial.getLocalTranslation().addLocal(directionToCam.mult(0.02f)));
//        // Move enemy toward the camera
//            // Get the direction vector from the enemy to the camera (ignoring Y-axis)
//            Vector3f directionToCam = cam.getLocation().subtract(spatial.getLocalTranslation());
//            directionToCam.setY(0); // Set the Y component to 0 so it doesn't move up or down
//            directionToCam.normalize(); // Normalize to get the direction vector
//
//            // Move the enemy in the x/z plane toward the camera
//            spatial.setLocalTranslation(spatial.getLocalTranslation().addLocal(directionToCam.mult(0.001f)));
//
//            // Ensure the enemy's Y position does not sink below the ground level
//            Vector3f currentPosition = spatial.getLocalTranslation();
//            currentPosition.setY(Math.max(currentPosition.y, .5f)); // groundLevel is your desired y-coordinate
//            spatial.setLocalTranslation(currentPosition);
//        }

    }
        
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
        
    public Geometry myBox(String name, Vector3f loc, ColorRGBA color) {
        Geometry geom = new Geometry(name, mesh);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        geom.setMaterial(mat);
        geom.setLocalTranslation(loc);
        //PhysicsHandler.addPhysics(geom, false, bulletAppState);
        return geom;
    }
    
}
