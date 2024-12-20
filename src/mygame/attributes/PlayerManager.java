package mygame.attributes;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.input.InputManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

public class PlayerManager {

    private CharacterControl player;
    private Node playerNode;
    private BulletAppState bulletAppState;
    private Camera cam;
    private InputManager inputManager;
    private float playerMoveSpeed = 0.1f;
    private CamManager camManager; // Reference to CamManager

    public PlayerManager(BulletAppState bulletAppState, Node rootNode, Camera cam, InputManager inputManager, AppSettings settings, CamManager camManager) {
        this.bulletAppState = bulletAppState;
        this.cam = cam;
        this.inputManager = inputManager;
        this.camManager = camManager;
    }

    // Set up yhe player
    public void setupPlayer() {
//        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.25f, 2f, 1);
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.25f, 4f, 1);
        player = new CharacterControl(capsuleShape, 0.05f);

        player.getCollisionShape().setMargin(0.01f);

        playerNode = new Node("Player");
        playerNode.addControl(player);

        player.setPhysicsLocation(new Vector3f(0, 4, 0));
        playerNode.setLocalTranslation(new Vector3f(7, 0, -2));
        player.setJumpSpeed(20);
        player.setFallSpeed(30);
        player.setGravity(30);

        bulletAppState.getPhysicsSpace().add(player);
    }

    public Vector3f getPlayerPosition() {
        return player.getPhysicsLocation();
    }

    public void movePlayer(boolean left, boolean right, boolean forward, boolean backward) {
        Vector3f walkDirection = new Vector3f();

        Vector3f camDir = cam.getDirection().clone().setY(0).normalizeLocal();
        Vector3f camLeft = cam.getLeft().clone().setY(0).normalizeLocal();

        if (forward) {
            walkDirection.addLocal(camDir.mult(playerMoveSpeed));
        }
        if (backward) {
            walkDirection.addLocal(camDir.negate().multLocal(playerMoveSpeed));
        }
        if (left) {
            walkDirection.addLocal(camLeft.mult(playerMoveSpeed));
        }
        if (right) {
            walkDirection.addLocal(camLeft.negate().multLocal(playerMoveSpeed));
        }

        player.setWalkDirection(walkDirection);
        player.setViewDirection(camDir);
        
        updateDepthOfField();
    }
    
    // Update the depth of field when player moves
    private void updateDepthOfField() {
        if (player.getWalkDirection().length() > 0) {
            // When moving, adjust depth of field parameters for a dynamic effect
            camManager.updateDepthOfField(10.0f, 5.0f, 1.0f);
        } else {
            // When idle, reset depth of field to default values
            camManager.updateDepthOfField(30.0f, 10.0f, 0.5f);
        }
    }
}
