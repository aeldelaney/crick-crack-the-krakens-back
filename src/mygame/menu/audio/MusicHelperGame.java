package mygame.menu.audio;
 
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;
  
import java.util.Random;
import mygame.menu.settings.GameSettings;

 
public class MusicHelperGame {
 
    AssetManager assetManager;
     Random rand=new Random();
   long sfxPlayTime=0;
   long sfxCooloffPeriod=1000*60;
 
    public MusicHelperGame(AssetManager assetManager )
    {
        this.assetManager=assetManager;
         
        
    }
  
    
 AudioNode songGame;
 boolean  songGameFlag=false;
 
AudioNode[] sfxs=new AudioNode[10];
   
  public void initGameAudio() {
    //
     songGame = new AudioNode(assetManager, "Sounds/Menu/Music/yaketySax.ogg", DataType.Buffer);
     songGame.setPositional(false);
     songGame.setLooping(true);
     songGame.setVolume(1);
       
  }
    
 public void playGame()
  {
     if(songGame!=null && songGameFlag) 
        stopGame();
         
     //
     playAudioNode( songGame );
     songGameFlag=true;
      
  }
    
 public void stopGame()
  {
     songGame.stop();
     songGameFlag=false;
  }

 
 public boolean isGamePlaying()
  {
      return songGameFlag;
   }
 
 public void resetVolume( )
  {
      songGame.setVolume((float)GameSettings.optionMusicVolume/(float)100); 
  }
  
  
 public void playAudioNode(AudioNode audioNode)
  {
     if(audioNode==null)
          return;
  
    audioNode.setVolume((float)GameSettings.optionMusicVolume/(float)100);
    try
       {
        audioNode.play();
       }
    catch(Exception ex)
       {
         ex.printStackTrace();
       } 
  }
  
 public void playAudioNodeOnce(AudioNode audioNode)
  {
     
     if(audioNode==null)
          return;
 
      audioNode.play();
    
  }
 
}
