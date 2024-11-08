package mygame.attributes;

import com.jme3.app.Application;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

public class InputHandler {

    private InputManager inputManager;
    private PlayerActionsManager interactionManager;

    private boolean left = false, right = false, forward = false, backward = false;

    public InputHandler(Application app, PlayerActionsManager interactionManager) {
        this.inputManager = app.getInputManager();
        this.interactionManager = interactionManager;

        setupKeys();
    }

    private void setupKeys() {
        inputManager.addMapping("Interact", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addListener(actionListener, "Left", "Right", "Forward", "Backward", "ToggleCrosshair", "Interact");
    }

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            switch (name) {
                case "Left":
                    left = isPressed;
                    break;
                case "Right":
                    right = isPressed;
                    break;
                case "Forward":
                    forward = isPressed;
                    break;
                case "Backward":
                    backward = isPressed;
                    break;
                case "Interact":
                    if (isPressed) {
                        interactionManager.handleInteraction();
                    }
                    break;
            }
        }
    };

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isForward() {
        return forward;
    }

    public boolean isBackward() {
        return backward;
    }
}
