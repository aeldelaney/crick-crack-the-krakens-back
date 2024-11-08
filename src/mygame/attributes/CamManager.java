package mygame.attributes;

import com.jme3.app.SimpleApplication;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;

public class CamManager {

    private FlyByCamera flyCam;
    private InputManager inputManager;

    public CamManager(SimpleApplication app) {
        this.flyCam = app.getFlyByCamera();
        this.inputManager = app.getInputManager();

        setupCamera();
    }

    private void setupCamera() {
        flyCam.setEnabled(true);
        flyCam.setMoveSpeed(0);
        flyCam.setDragToRotate(false);
        flyCam.setRotationSpeed(2.0f); 
        flyCam.setZoomSpeed(0);

        inputManager.setCursorVisible(false);
    }
}
