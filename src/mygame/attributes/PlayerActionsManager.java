package mygame.attributes;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.scene.Node;
import com.jme3.collision.CollisionResults;
import com.jme3.renderer.Camera;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.collision.CollisionResult;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.scene.control.AbstractControl;
import mygame.menu.screens.GameScreen;

public class PlayerActionsManager {

    private final InputManager inputManager;
    private final Camera cam;
    private final Node rootNode;
    private final PhysicsSpace physicsSpace;
    private final Node handNode;
    private final float pickupRange = 5f;
    private Spatial heldItem = null;
    private RigidBodyControl heldItemControl = null;
    private AssetManager assetManager;
    private boolean gameWon = false;
    private boolean gameFailed = false;
    private SceneManager sceneManager;

    // Manage what actions the player can perform
    public PlayerActionsManager(SimpleApplication app, PhysicsSpace physicsSpace, AssetManager assetManager, SceneManager sceneManager) {
        this.inputManager = app.getInputManager();
        this.cam = app.getCamera();
        this.rootNode = app.getRootNode();
        this.physicsSpace = physicsSpace;
        this.assetManager = assetManager;
        this.sceneManager = sceneManager;

        handNode = new Node("HandNode");
        app.getRootNode().attachChild(handNode);
    }

    private void pickUpItem() {
        CollisionResults results = new CollisionResults();
        Vector3f origin = cam.getLocation();
        Vector3f direction = cam.getDirection();

        Ray ray = new Ray(origin, direction);
        rootNode.collideWith(ray, results);

        if (results.size() > 0) {
            for (int i = 0; i < results.size(); i++) {
                Spatial target = results.getCollision(i).getGeometry();
                float distance = results.getCollision(i).getDistance();

                if (distance <= pickupRange) {
                    Boolean canBePickedUp = (Boolean) target.getUserData("canBePickedUp");
                    if (Boolean.TRUE.equals(canBePickedUp)) {
                        heldItem = target;
                        heldItemControl = heldItem.getControl(RigidBodyControl.class);

                        if (heldItemControl != null) {
                            heldItemControl.setKinematic(true);
                        }

                        if (heldItem.getParent() != null) {
                            heldItem.getParent().detachChild(heldItem);
                        }
                        heldItem.setLocalTranslation(Vector3f.ZERO);
                        handNode.attachChild(heldItem);

                        break;
                    }
                }
            }
        }
    }

    private void dropItem() {
        if (heldItem != null) {
            handNode.detachChild(heldItem);
            rootNode.attachChild(heldItem);
            Vector3f dropPosition;

            CollisionResults results = new CollisionResults();
            Vector3f origin = cam.getLocation();
            Vector3f direction = cam.getDirection().clone();

            Ray ray = new Ray(origin, direction);
            ray.setLimit(pickupRange);

            heldItemControl.setKinematic(true);

            rootNode.collideWith(ray, results);

            if (results.size() > 0) {
                CollisionResult closest = results.getClosestCollision();
                dropPosition = closest.getContactPoint().subtract(direction.mult(0.1f));
            } else {
                dropPosition = origin.add(direction.mult(pickupRange));
            }

            heldItem.setLocalTranslation(dropPosition);
            heldItemControl.setKinematic(false);
            heldItemControl.setPhysicsLocation(dropPosition);
            
            
            // Check if the dropped item is within a certain range of another item
            Spatial targetItem = rootNode.getChild("card_swiper"); // Replace with the actual name or reference
            if (targetItem != null) {
                float distance = dropPosition.distance(targetItem.getLocalTranslation());
                float interactionRange = 2.0f; // Define your desired range here

                if (distance <= interactionRange) {
                    // Make the item disappear and add particles similar to a shortcircuit
                    rootNode.detachChild(heldItem);
                    openDoor(targetItem); // Replace with funciton for keypad
                }
            }
            
            
            heldItem = null;
            heldItemControl = null;
        }
    }
    
    public Boolean isWonGame() {
        return gameWon;
    }
    
    public void restartWonGame() {
        gameWon = false;
    }
    
    public Boolean isFailedGame() {
        return gameFailed;
    }
    
    public void restartFailedGame() {
        gameFailed = false;
    }
    
    private void openDoor(Spatial targetItem) {
        // Step 1: Create a particle effect for the spark
        ParticleEmitter spark = new ParticleEmitter("SparkEmitter", ParticleMesh.Type.Triangle, 30);
        Material sparkMat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        
        sparkMat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/spark.png"));
        spark.setMaterial(sparkMat);
        spark.setImagesX(1); 
        spark.setImagesY(1);
        spark.setEndColor(ColorRGBA.Yellow); 
        spark.setStartColor(ColorRGBA.White);
        spark.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 1, 0));
        spark.setStartSize(0.2f);
        spark.setEndSize(0.1f);
        spark.setGravity(0, 1, 0);
        spark.setLowLife(0.5f);
        spark.setHighLife(1.5f);
        spark.getParticleInfluencer().setVelocityVariation(1.0f);
        spark.setLocalTranslation(targetItem.getLocalTranslation()); // Set location to targetItem
        spark.setFacingVelocity(true);

        rootNode.attachChild(spark); // Add the spark effect to the scene
        
        // Step 2: Create an animation to "open" the door by moving it to the left
        Spatial door = rootNode.getChild("door");
        Vector3f doorOpenPosition = targetItem.getLocalTranslation().add(new Vector3f(2, 0, -5)); // Adjust distance as needed
        
        // Create an interpolation control to move the door
        door.addControl(new AbstractControl() {
            private float elapsedTime = 0;
            private float duration = 500.0f; // Time to complete the movement
            private Vector3f startPosition = door.getLocalTranslation();
            private Vector3f endPosition = doorOpenPosition;

            @Override
            protected void controlUpdate(float tpf) {
                elapsedTime += tpf;
                float progress = Math.min(elapsedTime / duration, 1.0f);
                Vector3f newPosition = FastMath.interpolateLinear(progress, startPosition, endPosition);
                door.setLocalTranslation(newPosition);

                // Remove control when the animation is complete
                if (progress >= 1.0f) {
                    spatial.removeControl(this);
                    rootNode.detachChild(door);
                }
                if (elapsedTime >= 3.0f) {
                    gameWon = true;
                    door.removeControl(this);
                }
            }

            @Override
            protected void controlRender(RenderManager rm, ViewPort vp) {
                // No rendering-related code needed here
            }
        });
    }
    

    public void handleInteraction() {
        if (heldItem == null) {
            pickUpItem();
        } else {
            dropItem();
        }
    }

    public void update(float tpf) {
        handNode.setLocalTranslation(cam.getLocation().add(cam.getDirection().mult(2f)));
        handNode.setLocalRotation(cam.getRotation());
        
        if (sceneManager != null) {
        sceneManager.updateSoundVolume(cam);  // Adjust the sound volume based on distance
    }
    }
}
