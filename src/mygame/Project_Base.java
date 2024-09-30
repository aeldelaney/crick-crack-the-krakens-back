package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.shape.Box;
import com.jme3.input.controls.*;
import com.jme3.input.*;
import com.jme3.math.Ray;


/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Project_Base extends SimpleApplication implements ActionListener {
    private Node sceneNode;
    private Node interactiveNode;
    private BulletAppState bulletAppState;
    private RigidBodyControl scenePhy;
    private Node playerNode;
    private BetterCharacterControl playerControl;
    private CameraNode camNode;
    private final Vector3f walkDirection = new Vector3f(0,0,0);
    private final Vector3f viewDirection = new Vector3f(0,0,1);
    private boolean rotateLeft = false, rotateRight = false,
    rotateUp = false, rotateDown = false,
    forward = false, backward = false;
    private final float speed=8;
    
    private final static String MAPPING_PICKUP  = "Pickup Item";
    private final static String MAPPING_ROTATE = "Rotate";
    private boolean rotate = true;
    private final static String MAPPING_ROTATE_RIGHT  = "Rotate Right";
    private final static String MAPPING_ROTATE_LEFT = "Rotate Left";
    private final static String MAPPING_ROTATE_UP = "Rotate Up";
    private final static String MAPPING_ROTATE_DOWN = "Rotate Down";
    private Geometry item1;
    
    private static Box mesh = new Box(Vector3f.ZERO, 1, 1, 1);
    
    private final static Trigger TRIGGER_PICKUP =
        new MouseButtonTrigger(MouseInput.BUTTON_LEFT);
//            
    
     private final static Trigger TRIGGER_ROTATE =
//           new KeyTrigger(KeyInput.KEY_LSHIFT);
        new MouseButtonTrigger(MouseInput.BUTTON_RIGHT);
     
    public static void main(String[] args) {
        Project_Base app = new Project_Base();
        app.start();
    }
    
    public Geometry myBox(String name, Vector3f loc, ColorRGBA color)
    {
        Geometry geom = new Geometry(name, mesh);
        Material mat = new Material(assetManager,
            "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        geom.setMaterial(mat);
        geom.setLocalTranslation(loc);
        return geom;
}
    
    private void attachCenterMark() {
           Geometry c = myBox("center mark",
             Vector3f.ZERO, ColorRGBA.Red);
           c.scale(4);
           c.setLocalTranslation( settings.getWidth()/2,
             settings.getHeight()/2, 0 );
           guiNode.attachChild(c); // attach to 2D user interface
    }

    @Override
    public void simpleInitApp() {
        inputManager.addMapping("Forward",
        new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Back",
        new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Rotate Left",
        new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Rotate Right",
        new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Jump",
        new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this, "Rotate Left",
        "Rotate Right");
        inputManager.addListener(this, "Forward", "Back", "Jump");
        
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        // Model
        sceneNode = (Node)assetManager.loadModel("Scenes/Underwater_Base.j3o");
        sceneNode.scale(1.5f);
        scenePhy = new RigidBodyControl(0f);
        sceneNode.addControl(scenePhy);
        bulletAppState.getPhysicsSpace().add(sceneNode);

        rootNode.attachChild(sceneNode);
        
        // Light
        AmbientLight ambient = new AmbientLight();
        rootNode.addLight(ambient);
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1.4f, -1.4f, -1.4f));
        rootNode.addLight(sun);
        viewPort.setBackgroundColor(ColorRGBA.Cyan);
        
        // Player
        playerNode = new Node("the player");
        playerNode.setLocalTranslation(new Vector3f(7, 0, -2));
        rootNode.attachChild(playerNode);
        
        playerControl = new BetterCharacterControl(1.5f, 4, 30f);
        playerControl.setJumpForce(new Vector3f(0, 200, 0));
        playerControl.setGravity(new Vector3f(0, -10, 0));
        playerNode.addControl(playerControl);
        bulletAppState.getPhysicsSpace().add(playerControl);
    
        // Item
        Box b = new Box(0.25f, 0.75f, 0.25f);
        item1 = new Geometry("item", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.White);
        item1.setMaterial(mat);
        item1.setLocalTranslation(new Vector3f(1, 2, -5));
                
        interactiveNode = (Node)sceneNode.getChild("interactive objects");
        interactiveNode.attachChild(item1);
        
        // Mappings
        inputManager.addMapping(MAPPING_PICKUP, TRIGGER_PICKUP);
        inputManager.addMapping(MAPPING_ROTATE, TRIGGER_ROTATE);
        
        inputManager.addListener(analogListener, new String[]{MAPPING_ROTATE});
        inputManager.addListener(analogListener, new String[]{MAPPING_PICKUP});
  
        // center mark
        attachCenterMark();
    }
   

    @Override
    public void simpleUpdate(float tpf) {
        camNode = new CameraNode("CamNode", cam);
        // Set the direction to SpatialToCamera, which means the camera will copy the movements of the Node
        camNode.setControlDir(CameraControl.
        ControlDirection.SpatialToCamera);
        camNode.setLocalTranslation(new Vector3f(0, 4, -6));
        Quaternion quat = new Quaternion();
        quat.lookAt(Vector3f.UNIT_Z, Vector3f.UNIT_Y);
        camNode.setLocalRotation(quat);
        //attaching the camNode to the playerNode
        playerNode.attachChild(camNode);
        camNode.setEnabled(true);
        //disable the default 1st-person flyCam
        flyCam.setEnabled(false);
        inputManager.setCursorVisible(true);
        // Get current forward and left vectors of the playerNode:
        Vector3f modelForwardDir =
        playerNode.getWorldRotation().mult(Vector3f.UNIT_Z);
        Vector3f modelLeftDir =
        playerNode.getWorldRotation().mult(Vector3f.UNIT_X);
        // Determine the change in direction
        walkDirection.set(0, 0, 0);
        if (forward) {
            walkDirection.addLocal(modelForwardDir.mult(speed));
        } else if (backward) {
            walkDirection.addLocal(modelForwardDir.mult(speed).
            negate());
        }
        playerControl.setWalkDirection(walkDirection); // walk!
        // Determine the change in rotation
        if (rotateLeft) {
            Quaternion rotateL = new Quaternion().
            fromAngleAxis(FastMath.PI * tpf, Vector3f.UNIT_Y);
            rotateL.multLocal(viewDirection);
        } else if (rotateRight) {
            Quaternion rotateR = new Quaternion().
            fromAngleAxis(-FastMath.PI * tpf, Vector3f.UNIT_Y);
            rotateR.multLocal(viewDirection);
        }
        playerControl.setViewDirection(viewDirection); // turn!
    }
      
    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    @Override
    public void onAction(String binding, boolean isPressed, float tpf) {
        switch (binding) {
            case "Rotate Left" -> {
                rotateLeft = isPressed;
            }
            case "Rotate Right" -> {
                rotateRight = isPressed;
            }
            case "Forward" -> forward = isPressed;
            case "Back" -> backward = isPressed;
            case "Jump" -> playerControl.jump();
            default -> {
            }
        }
    }
    
    private AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float intensity, float tpf) {
            CollisionResults results = new CollisionResults();
            Ray ray = new Ray(cam.getLocation(), cam.getDirection());
            interactiveNode.collideWith(ray, results);
            if (results.size() > 0) {
                Geometry target = results.getClosestCollision().getGeometry();
                if (name.equals(MAPPING_PICKUP)) {
                    target.removeFromParent();
                }
                if (name.equals(MAPPING_ROTATE)) {
                    target.rotate(0, intensity, 0); // rotate around Y axis
                }
            } else {
                System.out.println("Selection: Nothing" );
            }
 
                      
        };
    };
    

}
