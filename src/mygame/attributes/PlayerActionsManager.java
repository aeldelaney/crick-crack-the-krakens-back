package mygame.attributes;

import com.jme3.app.SimpleApplication;
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
import com.jme3.scene.Spatial.CullHint;

public class PlayerActionsManager {

    private final InputManager inputManager;
    private final Camera cam;
    private final Node rootNode;
    private final PhysicsSpace physicsSpace;
    private final Node handNode;
    private final float pickupRange = 5f;
    private Spatial heldItem = null;
    private RigidBodyControl heldItemControl = null;

    // Manage what actions the player can perform
    public PlayerActionsManager(SimpleApplication app, PhysicsSpace physicsSpace) {
        this.inputManager = app.getInputManager();
        this.cam = app.getCamera();
        this.rootNode = app.getRootNode();
        this.physicsSpace = physicsSpace;

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
                System.out.println("Dropping at collision point: " + dropPosition);
            } else {
                dropPosition = origin.add(direction.mult(pickupRange));
                System.out.println("Dropping at max range: " + dropPosition);
            }

            heldItem.setLocalTranslation(dropPosition);
            heldItemControl.setKinematic(false);
            heldItemControl.setPhysicsLocation(dropPosition);
            heldItem = null;
            heldItemControl = null;
        }
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
    }
}
