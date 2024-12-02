package mygame.attributes;

import com.jme3.app.SimpleApplication;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.DepthOfFieldFilter;

public class CamManager {

    private FlyByCamera flyCam;
    private InputManager inputManager;
    private FilterPostProcessor fpp;
    private DepthOfFieldFilter dofFilter;

    public CamManager(SimpleApplication app) {
        this.flyCam = app.getFlyByCamera();
        this.inputManager = app.getInputManager();

        setupCamera();
        setupPostProcessing(app);
    }

    private void setupCamera() {
        flyCam.setEnabled(true);
        flyCam.setMoveSpeed(0);
        flyCam.setDragToRotate(false);
        flyCam.setRotationSpeed(2.0f); 
        flyCam.setZoomSpeed(0);

        inputManager.setCursorVisible(false);
    }
    
    // Set up post-processing effects (Depth of Field)
    private void setupPostProcessing(SimpleApplication app) {
        fpp = new FilterPostProcessor(app.getAssetManager());

        // Set up Depth of Field filter
        dofFilter = new DepthOfFieldFilter();
        fpp.addFilter(dofFilter);

        // Add the FilterPostProcessor to the ViewPort
        app.getViewPort().addProcessor(fpp);
    }

    // Update Depth of Field properties dynamically
    public void updateDepthOfField(float focusDistance, float focusRange, float blurScale) {
        dofFilter.setFocusDistance(focusDistance);
        dofFilter.setFocusRange(focusRange);
        dofFilter.setBlurScale(blurScale);
    }
}
