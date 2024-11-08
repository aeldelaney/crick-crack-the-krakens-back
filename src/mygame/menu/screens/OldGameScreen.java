 
package mygame.menu.screens;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
 import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.simsilica.lemur.GuiGlobals;
import mygame.Main;
import mygame.menu.audio.MenuAudioEffectsHelper;
import mygame.menu.pause.PauseOverlay;
import com.jme3.input.RawInputListener;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import com.jme3.input.JoystickButton;
import mygame.attributes.CamManager;
import mygame.attributes.InputHandler;
import mygame.attributes.PhysicsHandler;
import mygame.attributes.PlayerActionsManager;
import mygame.attributes.PlayerManager;
import mygame.attributes.SceneManager;

public class OldGameScreen extends BaseAppState implements ActionListener, PauseOverlay.PauseListener {
    
    private BulletAppState bulletAppState;  // Physics system
    private PlayerManager playerManager;
    private SceneManager sceneManager;
    private PlayerActionsManager interactionManager; // Interaction manager
    private CamManager cameraManager; // Camera manager
    private InputHandler inputHandler; // Input handler
    private boolean nextScene = false;
    private final static Trigger TRIGGER_P= new KeyTrigger(KeyInput.KEY_P);
    private final static String MAPPING_SCENE = "Next Scene";
    
    private ViewPort viewPort;
    private InputManager inputManager;
    private Node rootNode;
    private Node guiNode;
    private AssetManager assetManager;
    private Main app;
    private final Node localRootNode = new Node("Settings Screen RootNode");
    private final Node localGuiNode = new Node("Settings Screen GuiNode");
    private Trigger escape_trigger = new KeyTrigger(KeyInput.KEY_ESCAPE);
    FilterPostProcessor fpp;
    PauseOverlay pauseOverlay;
    //Audio
    MenuAudioEffectsHelper menuAudioEffectsHelper ;
    JoystickEventListener joystickEventListener;
    public void init(AppStateManager stateManager, Application app,MenuAudioEffectsHelper menuAudioEffectsHelper ) {
        super.initialize(stateManager, app);  
        
        this.app = (Main) app;
        this.rootNode = this.app.getRootNode();
        this.viewPort = this.app.getViewPort();
        this.guiNode = this.app.getGuiNode();
        this.assetManager = this.app.getAssetManager();
        this.inputManager=this.app.getInputManager();
        this.menuAudioEffectsHelper=menuAudioEffectsHelper;
        
        
        this.rootNode = this.app.getRootNode();
        
        inputManager = this.app.getInputManager();
        inputManager.addMapping(MAPPING_SCENE, TRIGGER_P);
        inputManager.addListener(actionListener, MAPPING_SCENE);

        // Initialize Bullet Physics System
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);

        // Initialize the camera manager
        cameraManager = new CamManager(this.app);

        // Initialize player and scene managers
        playerManager = new PlayerManager(
            bulletAppState,
            this.app.getRootNode(),
            this.app.getCamera(),
            this.app.getInputManager(),
            this.app.getContext().getSettings()
        );

        sceneManager = new SceneManager(
            bulletAppState,
            this.app.getRootNode(),
            this.app.getAssetManager()
        );

        // Set up the player and the scene
        playerManager.setupPlayer();
        sceneManager.setupScene();

        // Initialize the interaction manager
        interactionManager = new PlayerActionsManager(
            this.app,
            bulletAppState.getPhysicsSpace()
        );

        // Initialize the input handler
        inputHandler = new InputHandler(
            this.app,
            interactionManager
        );
        //
//        Box b = new Box(1, 1, 1);
//        Geometry geom = new Geometry("Box", b);
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", ColorRGBA.Blue);
//        geom.setMaterial(mat);
//         
//        localRootNode.attachChild(geom);
        //
        
        
        fpp = new FilterPostProcessor(assetManager);
        //Pause overlay
        pauseOverlay=new PauseOverlay();
        pauseOverlay.init(stateManager, app, localGuiNode, fpp, this, menuAudioEffectsHelper);
        //
//        viewPort.setBackgroundColor(ColorRGBA.Black);
     
    }

    @Override
    protected void initialize(Application app) {
     }

    @Override
    protected void cleanup(Application app) {
     }
  /**
    * Service keys and muouse only in this state. Disable once the state is switched
    */
    void attachInput()
    {
    
     inputManager.addMapping("ESC", escape_trigger);
     inputManager.addListener(this, new String[]{"ESC"});
     //
     joystickEventListener =new  JoystickEventListener();
     inputManager.addRawInputListener( joystickEventListener );   
    }
 
  /**
   * Disable inputs when the state is disabled
    
   */  
  void dettachInput()
    {
         inputManager.removeListener(this);
          inputManager.deleteMapping("ESC");
          inputManager.removeRawInputListener( joystickEventListener );  
    }
  
    @Override
    protected void onEnable() {
        rootNode.attachChild(localRootNode);
        guiNode.attachChild(localGuiNode);
        //
        attachInput();
        //
        app.getViewPort().addProcessor(fpp);
        //
        GuiGlobals.getInstance().setCursorEventsEnabled(false);
        app.getInputManager().setCursorVisible(false); 
        
      
     }

    @Override
    protected void onDisable() {
         rootNode.detachChild(localRootNode);
         guiNode.detachChild(localGuiNode);
         //
         dettachInput();
         //
         app.getViewPort().removeProcessor(fpp); 
        //
        GuiGlobals.getInstance().setCursorEventsEnabled(true);
        app.getInputManager().setCursorVisible(true); 
    }

 @Override
 public void onAction(String name, boolean isPressed, float tpf) {
      
    
     if(!isPressed)
      return;
      
       if(name.equals("ESC") )
           {
            //Pause or unpause
          if(!pauseOverlay.isEnabled())
               pauseOverlay.enableMenu();
           else
               pauseOverlay.disableMenu();   
           }
    }

    @Override
    public void onReturn() {
          pauseOverlay.disableMenu();
          
     }

    @Override
    public void onQuit() {
         pauseOverlay.disableMenu();
         app.moveFromGameToMenu();
     }

    @Override
    public void onPause() {
          app.getFlyByCamera().setEnabled(false);
          GuiGlobals.getInstance().setCursorEventsEnabled(true);
          app.getInputManager().setCursorVisible(true); 
     
     }
    @Override
    public void onUnpause() {
         app.getFlyByCamera().setEnabled(true);
         GuiGlobals.getInstance().setCursorEventsEnabled(false);
         app.getInputManager().setCursorVisible(false); 
       
     
     }
    
        
    private ActionListener actionListener = new ActionListener() {
            @Override
            public void onAction(String name, boolean isPressed, float tpf)
            {
                if (!isPressed) {
                    if (name.equals(MAPPING_SCENE)) {   
                     nextScene = true;
                    }
            }
    }
    };
    
     /**
     *  Easier to watch for all button and axis events with a raw input listener.
     */   
    protected class JoystickEventListener implements RawInputListener {

        public void onJoyAxisEvent(JoyAxisEvent evt) {
        }
        
 public void onJoyButtonEvent(JoyButtonEvent evt) {
         if(  evt.getButton().getLogicalId().equals(JoystickButton.BUTTON_6)  || evt.getButton().getLogicalId().equals(JoystickButton.BUTTON_8) )  
                    {
                      if(evt.isPressed())
                          {
                            //Pause or unpause
                          if(!pauseOverlay.isEnabled())
                               pauseOverlay.enableMenu();
                           else
                               pauseOverlay.disableMenu();  
                          }
                     }
        }

        public void beginInput() {}
        public void endInput() {}
        public void onMouseMotionEvent(MouseMotionEvent evt) {}
        public void onMouseButtonEvent(MouseButtonEvent evt) {}
        public void onKeyEvent(KeyInputEvent evt) {}
        public void onTouchEvent(TouchEvent evt) {}        
    }
}
