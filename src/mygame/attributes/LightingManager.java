package mygame.attributes;

import com.jme3.bullet.BulletAppState;
import com.jme3.input.InputManager;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.SpotLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

public class LightingManager {
    
    private Node rootNode;
    private SpotLight spot;
    
    private BulletAppState bulletAppState;
    private Camera cam;
    private InputManager inputManager;
    
    public LightingManager(BulletAppState bulletAppState, Node rootNode, Camera cam, InputManager inputManager, AppSettings settings) {
        this.bulletAppState = bulletAppState;
        this.cam = cam;
        this.inputManager = inputManager;
        this.rootNode = rootNode;
    }
    
    public void setupLighting() {
	// Light
//        AmbientLight ambient = new AmbientLight();
//        ambient.setColor(ColorRGBA.Red);
//        rootNode.addLight(ambient);
//        DirectionalLight sun = new DirectionalLight();
//        sun.setDirection(new Vector3f(-1.4f, 1.4f, -1.4f));
//        rootNode.addLight(sun);
        
        // Spotlight
        spot = new SpotLight();
        spot.setSpotRange(500);
        spot.setSpotOuterAngle(20 * FastMath.DEG_TO_RAD);
        spot.setSpotInnerAngle(15 * FastMath.DEG_TO_RAD);
        rootNode.addLight(spot);
    }
    
    public void updateSpotlight() {
        spot.setDirection(cam.getDirection());
        spot.setPosition(cam.getLocation());
    }
}
