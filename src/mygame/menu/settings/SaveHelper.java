package mygame.menu.settings;

  
import com.jme3.export.Savable;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.export.binary.BinaryImporter;
  
import java.io.File; 
import java.io.IOException;
import mygame.Main;
 
 
public class SaveHelper {
    
   
    static public void save( ) {
    String userHome = System.getProperty("user.home");
    BinaryExporter exporter = BinaryExporter.getInstance();
    File file = new File(userHome+"/MyGame/"+"save.j3o");
    try {
        System.out.println("WRITE "+ file );
         exporter.save(Main.saveObject, file);
        } 
    catch (IOException ex) {
     ex.printStackTrace();
    }
  
  }
    
   static public Savable load()  {
     String userHome = System.getProperty("user.home");
      BinaryImporter importer = BinaryImporter.getInstance();
        File file = new File(userHome+"/MyGame/"+"save.j3o");
        Savable saveObject=null;
         try { 
            Main.saveObject= (SaveObject) importer.load(file);
           System.out.println("READ "+ file );
           } 
            catch (IOException ex) {
                
                   
                  save();
                   try { Main.saveObject= (SaveObject) importer.load(file);}   catch (IOException ex2) {};
                  
                 ex.printStackTrace();
            }
         
         return saveObject;
  }
//    static public void delete( ) {
//    String userHome = System.getProperty("user.home");
//    BinaryExporter exporter = BinaryExporter.getInstance();
//    File file = new File(userHome+"/MyGame/"+"save.j3o");
//     file.delete();
//       
//  
//  }
   static public void delete() {
        String userHome = System.getProperty("user.home");
        BinaryExporter exporter = BinaryExporter.getInstance();
        // Use the same directory as save() and load()
        File file = new File(userHome + "/MyGame/" + "save.j3o");

        // Check if the file exists before trying to delete
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                System.out.println("Deleted saved file: " + file);
                //Main.saveObject = Main.startState;
            } else {
                System.out.println("Failed to delete saved file: " + file);
            }
        } else {
            System.out.println("Save file does not exist: " + file);
        }
    }

    
}
