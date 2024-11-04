/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame;

import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author DESKTOPQ
 */
public class CubeChaserControl extends AbstractControl {
    private Ray ray = new Ray();
        public final Camera cam;
        private final Node rootNode;

        @Override
        protected void controlUpdate(float tpf) {

        // Make Cube jump up to face when close
    //                    if (cam.getLocation().distance(spatial.getLocalTranslation()) <
    //                    6.5) {
    //                        Vector3f camFront = cam.getLocation().add(new Vector3f (1,0,2));
    //                        Vector3f directionToCam = camFront.subtract(spatial.getLocalTranslation()).normalize();
    //                        spatial.setLocalTranslation(spatial.getLocalTranslation().addLocal(directionToCam.mult(0.5f)));
    //                    }
            // Cube chase
            if (cam.getLocation().distance(spatial.getLocalTranslation()) < 13) {
                Vector3f camDown = cam.getLocation().add(new Vector3f (0,-3.75f,0));
                Vector3f directionToCam = camDown.subtract(spatial.getLocalTranslation()).normalize();
                spatial.setLocalTranslation(spatial.getLocalTranslation().addLocal(directionToCam.mult(0.06f)));
            }

        }
        
        @Override
        protected void controlRender(RenderManager rm, ViewPort vp) {
        }

        public CubeChaserControl(Camera cam, Node rootNode) {
            this.cam = cam;
            this.rootNode = rootNode;
        }
    
}
