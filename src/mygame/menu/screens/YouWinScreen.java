package mygame.menu.screens;

import mygame.menu.settings.Vars;
import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.HAlignment;
import com.simsilica.lemur.Panel;
import com.simsilica.lemur.VAlignment;
import com.simsilica.lemur.component.QuadBackgroundComponent;
import mygame.Main;
import mygame.menu.audio.MenuAudioEffectsHelper;
import mygame.menu.audio.MusicHelper;

public class YouWinScreen extends BaseAppState {

    private ViewPort viewPort;
    private Node rootNode;
    private Node guiNode;
    private AssetManager assetManager;
    private Main app;
    private InputManager inputManager;
    private final Node localRootNode = new Node("YouWin Screen RootNode");
    private final Node localGuiNode = new Node("YouWin Screen GuiNode");
    private final ColorRGBA backgroundColor = ColorRGBA.Black;

    Panel bgImage;
    Button backToMenuButton;

    private Trigger enter_trigger = new KeyTrigger(KeyInput.KEY_RETURN);

    public YouWinScreen() {
    }

    public void init(AppStateManager stateManager, Application app, MusicHelper musicHelper, MenuAudioEffectsHelper menuAudioEffectsHelper) {
        this.app = (Main) app;
        this.rootNode = this.app.getRootNode();
        this.viewPort = this.app.getViewPort();
        this.guiNode = this.app.getGuiNode();
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();

        // Background
        bgImage = declareImage(this.viewPort.getCamera().getWidth(), this.viewPort.getCamera().getHeight(), 0, this.viewPort.getCamera().getHeight(), Vars.ASSET_IMAGE_BG);

        // Button to return to the main menu
        backToMenuButton = declareButton("YouWinButtonBackToMenu", this.viewPort.getCamera().getWidth() / 2, this.viewPort.getCamera().getHeight() / 10);

        // Attach the button to the GUI
        localGuiNode.attachChild(backToMenuButton);

        // Set background color
        viewPort.setBackgroundColor(backgroundColor);

        // Request focus for the button
        GuiGlobals.getInstance().requestFocus(backToMenuButton);
    }

    private Panel declareImage(int sizeX, int sizeY, int posX, int posY, String assetPath) {
        Panel image = new Panel();
        image.setPreferredSize(new Vector3f(sizeX, sizeY, 0));
        localGuiNode.attachChild(image);
        image.setLocalTranslation(posX, posY, 0);
        QuadBackgroundComponent pBLBG = new QuadBackgroundComponent(assetManager.loadTexture(assetPath));
        image.setBackground(pBLBG);

        return image;
    }

    private Button declareButton(String label, int xPos, int yPos) {
        Button button = new Button(label);
        button.setFontSize(24);
        button.setPreferredSize(new Vector3f(300, 50, 0));
        button.setLocalTranslation(xPos, yPos, 0);
        button.setTextHAlignment(HAlignment.Center);
        button.setTextVAlignment(VAlignment.Center);
        button.setEnabled(true);
        return button;
    }

    @Override
    protected void initialize(Application app) {
        // Attach the YouWin screen to the root node and GUI
//        rootNode.attachChild(localRootNode);
//        guiNode.attachChild(localGuiNode);
//
//        // Add input mappings
//        inputManager.addMapping("ENTER", enter_trigger);
//        inputManager.addListener(new ActionListener() {
//            @Override
//            public void onAction(String name, boolean isPressed, float tpf) {
//                if (name.equals("ENTER") && isPressed) {
//                    // handleBackToMenu(); // Uncomment this if needed
//                    app.moveFromGameToWin();
//                }
//            }
//        }, new String[]{"ENTER"});
    }


    private void handleBackToMenu() {
        // Logic to return to the main menu
        app.moveFromWinToMenu();
    }

    @Override
    protected void cleanup(Application app) {
        // Cleanup if needed
    }

    @Override
    protected void onEnable() {
        // This is when the state is enabled, add listeners
        inputManager.addMapping("ENTER", enter_trigger);
        inputManager.addListener(new ActionListener() {
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if (isPressed && name.equals("ENTER")) {
                    app.moveFromWinToMenu();
                }
            }
        }, "ENTER");
    }

    @Override
    protected void onDisable() {
        // Remove listeners when the state is disabled
        inputManager.removeListener(new ActionListener() {
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if (isPressed && name.equals("ENTER")) {
                    app.moveFromWinToMenu();
                }
            }
        });
        inputManager.deleteMapping("ENTER");
    }

}
