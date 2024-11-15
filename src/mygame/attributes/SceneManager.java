package mygame.attributes;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

public class SceneManager {

    private BulletAppState bulletAppState;
    private Node rootNode;
    private AssetManager assetManager;
    
    private Node sceneNode;
    private RigidBodyControl scenePhy;
    private Spatial doorSpatial;
    private Spatial keypadSpatial;
    private Spatial keycardSpatial;

    public SceneManager(BulletAppState bulletAppState, Node rootNode, AssetManager assetManager) {
        this.bulletAppState = bulletAppState;
        this.rootNode = rootNode;
        this.assetManager = assetManager;
    }

    // Set up the scene
    public void setupScene() {
        sceneNode = (Node)assetManager.loadModel("Scenes/Underwater_Base.j3o");
        sceneNode.scale(.75f);
        PhysicsHandler.addPhysics(sceneNode, false, bulletAppState);
        bulletAppState.getPhysicsSpace().add(sceneNode);
        rootNode.attachChild(sceneNode);
        
        keycardSpatial = assetManager.loadModel("Models/keycard/keycard.j3o");
        keycardSpatial.setName("key_card");
        keycardSpatial.setLocalTranslation(new Vector3f(-8f, 2f, -13));
        keycardSpatial.setLocalScale(.4f, .4f, .4f); // x, y, z
        keycardSpatial.setUserData("canBePickedUp", true);
        PhysicsHandler.addPhysics(keycardSpatial, false, bulletAppState);
        rootNode.attachChild(keycardSpatial);
        
        keypadSpatial = assetManager.loadModel("Models/keypad/keypad.j3o");
        keypadSpatial.setName("card_swiper");
        keypadSpatial.setLocalTranslation(new Vector3f(16, 4, 0));
        keypadSpatial.setLocalScale(.75f, .75f, .75f); // x, y, z
        keypadSpatial.rotate(0, FastMath.HALF_PI, 0);
        PhysicsHandler.addPhysics(keypadSpatial, false, bulletAppState);
        rootNode.attachChild(keypadSpatial);
        
        doorSpatial = assetManager.loadModel("Models/door/door.j3o");
        doorSpatial.setName("door");
        doorSpatial.setLocalTranslation(new Vector3f(16, 4.5f, 5.9f)); // z (pos back), y (pos up), x
        doorSpatial.setLocalScale(1.7f, 1.8f, 1.0f); // x, y, z
        doorSpatial.rotate(0, -FastMath.HALF_PI, 0);
        PhysicsHandler.addPhysics(doorSpatial, false, bulletAppState);
        rootNode.attachChild(doorSpatial);
    }
}
