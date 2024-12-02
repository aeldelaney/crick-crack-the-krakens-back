package mygame.attributes;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
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
    private boolean isPositionalMusicPlaying = false;
    
    // Add the AudioNode for positional sound
    private AudioNode soundNode;

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
        
        
        
        // Add the sound to the specific spatial (e.g., cube.015 or any spatial)
        Spatial cube015 = ((Node)((Node)sceneNode.getChild("background")).getChild("Scene")).getChild("Cube.015");
        if (cube015 != null) {
            // Create the AudioNode
            soundNode = new AudioNode(assetManager, "Sounds/Menu/Music/menSinging.ogg", true);  // Load your sound file
            soundNode.setPositional(true);  // Enable positional sound
            soundNode.setLocalTranslation(cube015.getLocalTranslation());  // Position the sound on the object
            soundNode.setVolume(1.0f);  // Set the initial volume level (can be adjusted dynamically)

            // Play the sound
            //soundNode.play();

            // Attach the sound node to the scene
            rootNode.attachChild(soundNode);
        } else {
            System.out.println("cube.015 not found in scene!");
        }
    }
    
// Update the sound's volume based on the distance to the camera (player)
    public void updateSoundVolume(Camera cam) {
        if (soundNode != null) {
            //Spatial cube015 = ((Node)((Node)sceneNode.getChild("background")).getChild("Scene")).getChild("Cube.015");
            Spatial cube015 = ((Node)((Node)sceneNode.getChild("background")).getChild("Scene")).getChild("Cube.015");
            if (cube015 != null) {
                // Calculate the distance between the camera (player) and the cube
                float distance = cam.getLocation().distance(cube015.getWorldTranslation());

                // Map the distance to volume (you can adjust the min/max values)
                float maxDistance = 25f;  // Max distance at which the sound will be silent
                float minDistance = 5f;   // Minimum distance for max volume
                
                // Calculate the volume based on the distance
                float volume = 1 - Math.min(1, (distance - minDistance) / (maxDistance - minDistance));

                // Ensure the volume is between 0 and 1
                soundNode.setVolume(Math.max(0, Math.min(1, volume)));
            }
        }
    }
    
    // Method to start positional music
    public void playPositionalMusic() {
        if (!isPositionalMusicPlaying) {
            soundNode.play();
            isPositionalMusicPlaying = true;
        }
    }
    
    // Method to stop positional music
    public void stopPositionalMusic() {
        if (isPositionalMusicPlaying) {
            soundNode.stop(); 
            isPositionalMusicPlaying = false;
        }
    }

}
