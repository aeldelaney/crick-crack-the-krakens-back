package mygame.attributes;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Node;

public class SceneManager {

    private BulletAppState bulletAppState;
    private Node rootNode;
    private AssetManager assetManager;
    private Node geomsNode;
    
    private Node sceneNode;
    private RigidBodyControl scenePhy;

    public SceneManager(BulletAppState bulletAppState, Node rootNode, AssetManager assetManager) {
        this.bulletAppState = bulletAppState;
        this.rootNode = rootNode;
        this.assetManager = assetManager;
    }

    // Set up the scene
    public void setupScene() {
        sceneNode = (Node)assetManager.loadModel("Scenes/Underwater_Base.j3o");
        sceneNode.scale(1.5f);
        scenePhy = new RigidBodyControl(0f);
        sceneNode.addControl(scenePhy);
        bulletAppState.getPhysicsSpace().add(sceneNode);
        rootNode.attachChild(sceneNode);
    }
}
