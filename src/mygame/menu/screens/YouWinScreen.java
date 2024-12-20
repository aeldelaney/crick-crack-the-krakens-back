package mygame.menu.screens;
 
import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.JoystickAxis;
import com.jme3.input.JoystickButton;
import com.jme3.input.KeyInput;
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
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.HAlignment;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.Panel;
import com.simsilica.lemur.VAlignment;
import com.simsilica.lemur.component.QuadBackgroundComponent;
import com.simsilica.lemur.event.MouseListener;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import mygame.Main;
import mygame.menu.audio.MenuAudioEffectsHelper;
import mygame.menu.audio.MusicHelper;
import mygame.menu.settings.Vars;
import mygame.smp.player.SimpleMediaPlayer;

 
public class YouWinScreen extends BaseAppState implements RawInputListener,  ActionListener, MouseListener   {

    private Trigger up_trigger = new KeyTrigger(KeyInput.KEY_UP);
    private Trigger down_trigger = new KeyTrigger(KeyInput.KEY_DOWN);
    private Trigger left_trigger = new KeyTrigger(KeyInput.KEY_LEFT);
    private Trigger right_trigger = new KeyTrigger(KeyInput.KEY_RIGHT);
    private Trigger space_trigger = new KeyTrigger(KeyInput.KEY_SPACE);
    private Trigger enter_trigger = new KeyTrigger(KeyInput.KEY_RETURN);
    private Trigger escape_trigger = new KeyTrigger(KeyInput.KEY_ESCAPE);
    
    private ViewPort viewPort;
    private Node rootNode;
    private Node guiNode;
    private AssetManager assetManager;
    private Main app;
    private InputManager inputManager;
    private Node localRootNode = new Node("Settings Screen RootNode");
    private Node localGuiNode = new Node("Settings Screen GuiNode");
    Panel bgImage;
    
    //Main player
//    SimpleMediaPlayer mediaPlayer1;
//    SimpleMediaPlayer mediaPlayer2;
//    SimpleMediaPlayer mediaPlayer3;
//    SimpleMediaPlayer mediaPlayer4;
    //Menu geometry
//    Geometry menuGeometry1;
//    Geometry menuGeometry2;
//    Geometry menuGeometry3;
//    Geometry menuGeometry4;
    //Map for picking
    HashMap<Rectangle, SimpleMediaPlayer> pickPlayer=new HashMap<Rectangle, SimpleMediaPlayer> ();
     //Map for selecting
    ArrayList< SimpleMediaPlayer> selectPlayer=new ArrayList<SimpleMediaPlayer> ();
    //
     Node buttonNode=new Node();
     ArrayList<Button> buttonTab=new ArrayList<Button> ();//Array containing all buttons
    MenuAudioEffectsHelper menuAudioEffectsHelper ;
    MusicHelper musicHelper;
//    int OPTION_TUT_0=0;
//    int OPTION_TUT_1=1;
//    int OPTION_TUT_2=2;
//    int OPTION_TUT_3=3;
    int OPTION_CONFIG_CANCEL=4;

//    int selectedOption=OPTION_TUT_0; //For scrolling. Current button with focus
    Label optionCancelLabel;
    //
    JoystickEventListener joystickEventListener;
    
   public void init(AppStateManager stateManager, Application app,MusicHelper musicHelper,MenuAudioEffectsHelper menuAudioEffectsHelper ) {
         
        
        this.app = (Main) app;
        this.rootNode = this.app.getRootNode();
        this.viewPort = this.app.getViewPort();
        this.guiNode = this.app.getGuiNode();
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
         this.musicHelper=musicHelper;
        this.menuAudioEffectsHelper=menuAudioEffectsHelper;
        
      
        //Background color
        viewPort.setBackgroundColor(ColorRGBA.Black);
        //Camera 
        Camera cam=app.getCamera();
       //Init player
//         mediaPlayer1=new SimpleMediaPlayer(app);
//         mediaPlayer2=new SimpleMediaPlayer(app);
//         mediaPlayer3=new SimpleMediaPlayer(app);
//         mediaPlayer4=new SimpleMediaPlayer(app);
        //Config
        //Unique name
//        String screenName="Menu1";
//         //Image to display when player is idle. Null to use screenColor
//         String idleImageAssetPath="Textures/Media/idleImageAssetPath.jpg";
//         //Image to display when player is loading. Null to use screenColor
//         String loadingImageAssetPath="Textures/Media/loadingImageAssetPath.jpg";
//       //Image to display when player is paused. Null to use screenColor
//         String pausedImageAssetPath="Textures/Media/pausedImageAssetPath.jpg";
         //Color to use if any of above pictures is not provided.
         ColorRGBA screenColor=ColorRGBA.Black;
         //Video to play
         //String videoAssetPath="Media/320_180.mjpg";
         //Audio to play
         //String audioAssetPath="Media/audio.ogg";
         //Source FPS. Should be consistent with original FPS. In most cases 25 or 30
         //int framesPerSec=30;
         //Playback mode. Play once or loop
        //int playBackMode=SimpleMediaPlayer.PB_MODE_LOOP;
        //Transparency of the screen. 1 for intro, material and menu geometries. Below 1f for HUD geometries
         //float alpha=1f;
         //Relative size 16/9
         int wid=(int)(cam.getHeight()/2.3f);
         int hei=(int)((wid/16f)*9f);
         int margin=wid/3;
         
         ///////////////////////GEOMETRIES
          //1
//          menuGeometry1=mediaPlayer1.genGeometry( screenName,wid, hei, idleImageAssetPath, loadingImageAssetPath, pausedImageAssetPath,screenColor,videoAssetPath,audioAssetPath, framesPerSec, playBackMode,alpha );
//           //Add to gui 
//          buttonNode.attachChild(menuGeometry1);
//          //Position      
//          menuGeometry1.setLocalTranslation(cam.getWidth()/4-wid/2, cam.getHeight()*0.75f-hei/2, 1.0f);
//          //Add to map for picking
//          pickPlayer.put(new Rectangle((int)menuGeometry1.getLocalTranslation().x, (int)menuGeometry1.getLocalTranslation().y ,mediaPlayer1.getWidth(),mediaPlayer1.getHeight()),mediaPlayer1);
//          selectPlayer.add(mediaPlayer1);
//            //2
//           screenName="Menu2";
//          menuGeometry2=mediaPlayer2.genGeometry( screenName,wid, hei, idleImageAssetPath, loadingImageAssetPath, pausedImageAssetPath,screenColor,videoAssetPath,audioAssetPath, framesPerSec, playBackMode,alpha );
//           //Add to gui 
//          buttonNode.attachChild(menuGeometry2);
//          //Position      
//          menuGeometry2.setLocalTranslation(cam.getWidth()*0.75f-wid/2, (int)menuGeometry1.getLocalTranslation().y, 1.0f);
//          //Add to map for picking
//          pickPlayer.put(new Rectangle((int)menuGeometry2.getLocalTranslation().x, (int)menuGeometry2.getLocalTranslation().y ,mediaPlayer2.getWidth(),mediaPlayer2.getHeight()),mediaPlayer2);
//          selectPlayer.add(mediaPlayer2);
//          //3
//          screenName="Menu3";
//          menuGeometry3=mediaPlayer3.genGeometry( screenName,wid, hei, idleImageAssetPath, loadingImageAssetPath, pausedImageAssetPath,screenColor,videoAssetPath,audioAssetPath, framesPerSec, playBackMode,alpha );
//           //Add to gui 
//          buttonNode.attachChild(menuGeometry3);
//          //Position      
//         menuGeometry3.setLocalTranslation(menuGeometry1.getLocalTranslation().x,  cam.getHeight()/4-hei/2 , 1.0f);
//         //Add to map for picking
//         pickPlayer.put(new Rectangle((int)menuGeometry3.getLocalTranslation().x, (int)menuGeometry3.getLocalTranslation().y ,mediaPlayer3.getWidth(),mediaPlayer3.getHeight()),mediaPlayer3);
//         selectPlayer.add(mediaPlayer3);
//         //4
//         screenName="Menu4";
//         menuGeometry4=mediaPlayer4.genGeometry( screenName,wid, hei, idleImageAssetPath, loadingImageAssetPath, pausedImageAssetPath,screenColor,videoAssetPath,audioAssetPath, framesPerSec, playBackMode,alpha );
//         //Add to gui 
//         buttonNode.attachChild(menuGeometry4);
//         //Position      
//         menuGeometry4.setLocalTranslation(menuGeometry2.getLocalTranslation().x,  menuGeometry3.getLocalTranslation().y , 1.0f);
//         //Add to map for picking
//         pickPlayer.put(new Rectangle((int)menuGeometry4.getLocalTranslation().x, (int)menuGeometry4.getLocalTranslation().y ,mediaPlayer1.getWidth(),mediaPlayer1.getHeight()),mediaPlayer4);
//         selectPlayer.add(mediaPlayer4); 
            
         //Calculations
        int buttonMargin=this.viewPort.getCamera().getHeight()/12;
        int buttonHeight=this.viewPort.getCamera().getHeight()/25;
        int buttonWidth=(int)(this.viewPort.getCamera().getWidth()/2-buttonMargin*3 );
        int column2PosX=this.viewPort.getCamera().getWidth()/2+buttonMargin/2;
        int buttonLeftMargin=this.viewPort.getCamera().getHeight()/10;
        int buttonTopMargin=buttonLeftMargin;
         int fontSize=this.viewPort.getCamera().getHeight()/24;
       
        //Different size for different languages and fonts
        if(Main.i18n.equals(Main.I18N_RUSSIA))
           fontSize=this.viewPort.getCamera().getHeight()/20;
        else if(Main.i18n.equals(Main.I18N_KOREA))
           fontSize=this.viewPort.getCamera().getHeight()/20;
        else if(Main.i18n.equals(Main.I18N_CHINA))
           fontSize=this.viewPort.getCamera().getHeight()/20;
         else if(Main.i18n.equals(Main.I18N_JAPAN))
           fontSize=this.viewPort.getCamera().getHeight()/20;
      
        //MENU
       //BACKGROUND
        bgImage=declareImage(this.viewPort.getCamera().getWidth(), this.viewPort.getCamera().getHeight(), 0 , this.viewPort.getCamera().getHeight(),Vars.ASSET_IMAGE_END_BG);
       
        
//        //Tut 1
//        Label hintText=declareButton(fontSize,   wid,   buttonHeight,"MainMenuButtonTutorial",(int)menuGeometry1.getLocalTranslation().x, (int)menuGeometry1.getLocalTranslation().y+ hei+buttonHeight+buttonMargin/4  );
//        //Tut 2
//        Label hintText2=declareButton(fontSize,   wid,   buttonHeight,"MainMenuButtonTutorial",(int)menuGeometry2.getLocalTranslation().x, (int)menuGeometry2.getLocalTranslation().y+ hei+buttonHeight+buttonMargin/4  );
//        //Tut 3
//        Label hintText3=declareButton(fontSize,   wid,   buttonHeight,"MainMenuButtonTutorial",(int)menuGeometry3.getLocalTranslation().x,(int) menuGeometry3.getLocalTranslation().y+ hei+buttonHeight+buttonMargin/4);
//         //Tut 4
//        Label hintText4=declareButton(fontSize,   wid,   buttonHeight,"MainMenuButtonTutorial",(int) menuGeometry4.getLocalTranslation().x, (int)menuGeometry4.getLocalTranslation().y+ hei+buttonHeight+buttonMargin/4 );
     
        //Cancel
        // Get the width and height of the screen
//        float screenWidth = this.viewPort.getCamera().getWidth();
//        float screenHeight = this.viewPort.getCamera().getHeight();

        // Calculate the position of the button to center it
//        buttonWidth = (int)(screenWidth / 2 - buttonMargin * 3);
//        buttonHeight = (int)(screenHeight / 25);
//        int xPos = (int)(screenWidth / 2 - buttonWidth / 2);  // Center horizontally
//        int yPos = (int)(screenHeight / 2 - buttonHeight / 2); // Center vertically
//
//        // Create the Cancel button
//        optionCancelLabel = declareButton(fontSize, buttonWidth, buttonHeight, "OptionCancel", xPos, yPos);


        
//        optionCancelLabel=declareButton(fontSize,   buttonWidth,   buttonHeight,"OptionCancel",buttonLeftMargin,buttonTopMargin );
//        optionCancelLabel.setTextHAlignment(HAlignment.Left);
          
        //Add buttons to gui
        localGuiNode.attachChild(buttonNode);
        
        // Get screen dimensions
        float screenWidth = this.viewPort.getCamera().getWidth();
        float screenHeight = this.viewPort.getCamera().getHeight();

        // Font size and button dimensions
        fontSize = this.viewPort.getCamera().getHeight() / 24;
        buttonHeight = (int)(screenHeight / 25);
        buttonWidth = (int)(screenWidth / 2 - buttonMargin * 3);

        // Positioning for the label and button
        buttonLeftMargin = (int)(screenHeight / 10);
        buttonTopMargin = buttonLeftMargin;

        // Create the Cancel button (top of the screen)
        int xPosButton = (int)(screenWidth / 2 - buttonWidth / 2);
        int yPosButton = (int)(screenHeight / 2 - buttonHeight / 2);  // Position it towards the top

        // Create the Cancel button
        optionCancelLabel = declareButton(fontSize, buttonWidth, buttonHeight, "MainMenuButtonQuit", xPosButton, yPosButton);

        // Create the congratulatory label (below the button)
        Label congratulatoryLabel = new Label("Congratulations! You Have Escaped...");
        congratulatoryLabel.setFontSize(fontSize);

        // Center the label horizontally by adjusting the X position
        float labelWidth = congratulatoryLabel.getPreferredSize().x;
        congratulatoryLabel.setLocalTranslation((int)(screenWidth / 2 - labelWidth / 2), (int)(yPosButton + buttonHeight + buttonMargin), 0);

        // Add the label to the GUI node
        localGuiNode.attachChild(congratulatoryLabel);

        
         //Select the first one
//         GuiGlobals.getInstance().requestFocus(buttonTab.get(selectedOption));
        
          
    }
    
     /**
     * 
     * @param sizeX
     * @param sizeY
     * @param posX
     * @param posY
     * @param assetPath
     * @return 
     */
   private  Panel declareImage(int sizeX, int sizeY, int posX, int posY, String assetPath)
    {
        Panel image = new Panel();
        image.setPreferredSize(new Vector3f(  sizeX, sizeY, 0));
        localGuiNode.attachChild(image);
        image.setLocalTranslation(posX,  posY, 0);
        QuadBackgroundComponent pBLBG = new QuadBackgroundComponent( assetManager.loadTexture(assetPath));
        image.setBackground(pBLBG);  
        
        return image;
    }
    
   /**
    * 
    * @param fontSize
    * @param buttonWidth
    * @param buttonHeight
    * @param i18nLabel
    * @param xPos
    * @param yPos
    * @return 
    */
   Button declareButton(int fontSize, int buttonWidth, int buttonHeight,String i18nLabel, int xPos, int yPos)
   {
       Button butt=new Button(ResourceBundle.getBundle(Main.i18n).getString(i18nLabel));///
        butt.setFontSize(fontSize);
        butt.setPreferredSize( new   Vector3f(buttonWidth, buttonHeight,0));
        butt.setLocalTranslation(xPos ,yPos, 0);
        //playButt.setColor(new ColorRGBA(1f,0.1f,0.1f,1f));
        butt.setShadowColor(ColorRGBA.Black);
        butt.setFocusColor(ColorRGBA.Red);
        butt.setFocusShadowColor(ColorRGBA.Red);
        butt.setHighlightColor(ColorRGBA.Red);
        butt.setHighlightShadowColor(ColorRGBA.Red); 
        butt.setTextHAlignment(HAlignment.Center);
        butt.setTextVAlignment(VAlignment.Bottom);
        butt.setEnabled(true);
        buttonNode.attachChild(butt); 
        //Put button in array
        buttonTab.add(butt);   
         //
        return butt;
   }
   
    @Override
    public void update(float tpf) {
        
        //!!!!!!!!!!IMPORTANT
//       mediaPlayer1.update(tpf);
//       mediaPlayer2.update(tpf);
//       mediaPlayer3.update(tpf);
//       mediaPlayer4.update(tpf);
    }
 

    @Override
    protected void initialize(Application app) {
     }

    @Override
    protected void cleanup(Application app) {
     }

     @Override
    protected void onEnable() {
        rootNode.attachChild(localRootNode);
        guiNode.attachChild(localGuiNode);
        //
        attachInput();
     }

    @Override
    protected void onDisable() {
         rootNode.detachChild(localRootNode);
         guiNode.detachChild(localGuiNode);
         //
         dettachInput();
    }
 
     
 
 void attachInput()
    {
     inputManager.addMapping("UP", up_trigger);
     inputManager.addListener(this, new String[]{"UP"});
     inputManager.addMapping("DOWN", down_trigger);
     inputManager.addListener(this, new String[]{"DOWN"});
     inputManager.addMapping("LEFT", left_trigger);
     inputManager.addListener(this, new String[]{"LEFT"});
     inputManager.addMapping("RIGHT",right_trigger);
     inputManager.addListener(this, new String[]{"RIGHT"});
     inputManager.addMapping("SPACE", space_trigger);
     inputManager.addListener(this, new String[]{"SPACE"});
     inputManager.addMapping("ENTER", enter_trigger);
     inputManager.addListener(this, new String[]{"ENTER"});
     inputManager.addMapping("ESC", escape_trigger);
     inputManager.addListener(this, new String[]{"ESC"});
     
     inputManager.addRawInputListener(this);
     
        for(int a=0;a<buttonTab.size();a++)
         buttonTab.get(a).addMouseListener(this);
          
     //
      joystickEventListener =new    JoystickEventListener();
      inputManager.addRawInputListener( joystickEventListener );      
    
    }
  void dettachInput()
    {
         inputManager.removeListener(this);
         inputManager.deleteMapping("UP");
         inputManager.deleteMapping("DOWN");
         inputManager.deleteMapping("LEFT");
         inputManager.deleteMapping("RIGHT");
         inputManager.deleteMapping("SPACE");
         inputManager.deleteMapping("ENTER");
         inputManager.deleteMapping("ESC");
         inputManager.removeRawInputListener( joystickEventListener );
         inputManager.removeRawInputListener(this);
         for(int a=0;a<buttonTab.size();a++)
            buttonTab.get(a).removeMouseListener(this); 
    }

    @Override
    public void beginInput() {
     }

    @Override
    public void endInput() {
    }

    @Override
    public void onJoyAxisEvent(JoyAxisEvent evt) {
     }

    @Override
    public void onJoyButtonEvent(JoyButtonEvent evt) {
     }

    @Override
    public void onMouseMotionEvent(MouseMotionEvent evt) {
     }

    @Override
  public void onMouseButtonEvent(MouseButtonEvent evt) {
             
        if(evt.isPressed()) 
           return;
      }

    @Override
    public void onKeyEvent(KeyInputEvent evt) {
     }

    @Override
    public void onTouchEvent(TouchEvent evt) {
     }

    
 /**
  * Move option up
  */ 
 private void rollUp()
    {
    }
 
 /**
  * Move option down
  */ 
 private void rollDown()
    {
    }
    
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
    if(!isPressed)
        return;
      
     //Do nothing on Escape. 
    if (name.equals("ESC"))  
         {
              cancel();
             return;
         }
    }

 
    @Override
    public void mouseButtonEvent( MouseButtonEvent mbe, Spatial sptl, Spatial sptl1) {
    
       if(mbe.isPressed())
           return;

      handleEnter();
    }


   @Override
    public void mouseEntered(MouseMotionEvent mme, Spatial sptl, Spatial sptl1) {
      //
    }


    @Override
    public void mouseExited(MouseMotionEvent mme, Spatial sptl, Spatial sptl1) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseMotionEvent mme, Spatial sptl, Spatial sptl1) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
 /**
  * Handle enter action
  */
 private void handleEnter()
     {
        cancel();
     }
 
 
   void cancel()
    {
        app.stop() ;
    }
   
   
   
  /**
     *  Easier to watch for all button and axis events with a raw input listener.
     */   
    protected class JoystickEventListener implements RawInputListener {

        public void onJoyAxisEvent(JoyAxisEvent evt) {
        
              
          if(evt.getValue()==1 && evt.getAxis().getName().equals(JoystickAxis.POV_X))
             {
                
             }
            else   if(evt.getValue()==-1 && evt.getAxis().getName().equals(JoystickAxis.POV_X))
              {
               
               }
            else  if(evt.getValue()==1 && evt.getAxis().getName().equals(JoystickAxis.POV_Y))
             { 
               rollUp(); 
             }
             else  if(evt.getValue()==-1 && evt.getAxis().getName().equals(JoystickAxis.POV_Y))
             {
               rollDown();  
             }
          else
             {
             }
           
        }

        public void onJoyButtonEvent(JoyButtonEvent evt) {
            if(evt.isPressed())
            if(evt.getButton().getLogicalId().equals(JoystickButton.BUTTON_0) ||
                evt.getButton().getLogicalId().equals(JoystickButton.BUTTON_1) ||
                    evt.getButton().getLogicalId().equals(JoystickButton.BUTTON_2) ||
                     evt.getButton().getLogicalId().equals(JoystickButton.BUTTON_3) ||
                     evt.getButton().getLogicalId().equals(JoystickButton.BUTTON_7) )
              {
                  handleEnter();
              }
           //exit
             else   if(  evt.getButton().getLogicalId().equals(JoystickButton.BUTTON_6)  || evt.getButton().getLogicalId().equals(JoystickButton.BUTTON_8) )  
                    {
                       cancel();
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
