# Crick Crack the Kraken's Back

## Game Summary
This is an thriller escape game set in the depths of the ocean. The player awakens stranded on an underwater base without memory of what happened or why they're there. All they know is that they have to escape. To do so, the player must explore the base to learn how to leave and find the items needed to do so. However, there's more the player has to contend with. As the player moves about, they realize that they're not alone. A Kraken lurks outside the base, ready to attack at the wrong move, and some unknown entity lies in wait for the player inside the underwater base itself. Will you be able to escape?


## Genre
- escape/puzzle
- first-person
- thriller/horror

## Inspiration 

### [Subnautica](https://subnautica.fandom.com/wiki/Subnautica_Wiki)
Subnautica the player being the sole survivor of a crashed spacecraft who must explore and survive in an ocean planet. He has to travel around different underwater bases in order to gather resources and seek shelter as he moves forward in the plotline.

**1. Underwater base aesthetic**
- Subnautica has a big emphasis on the underwater base aesthetic since the player will often end up scavenging in bases located along the planet. Since our game is set in an underwater base, we drew inspiration from this game when generating assets to set the environment based on the aesthetic references. An image of the game underwater bases can be seen below.
<p alight="center">
<img width="564" alt="image" src="https://github.com/user-attachments/assets/894f74e6-d5d0-4ce6-a47a-db513d411ccd">
</p>
<br/>
<br/>

### The [Amnesia game series](https://store.steampowered.com/app/57300/Amnesia_The_Dark_Descent/)
Amnesia: The Dark Descent is a game that focuses on player progression through a castle where the player starts off with no recollection of why they are there and must figure it out as they navigate through the castle. The main tools for survival in the game involve running and hiding instead of fighting, similar to what we aim to achieve by avoiding engaging with the creatures in our game. As the game progresses, more information is uncovered about the player as well as how to escape which is an element we will focus on when working on our project.

**1. Puzzle types**
- The puzzles provide inspiration with the possibility of finding elements in other rooms/places in order to gather the necessary data to complete a specific puzzle to progress. An example can be seen below with the information on what levers need to be at what position is necessary to move on.
<p align="center">
<img width="628" alt="image" src="https://github.com/user-attachments/assets/fa739d62-5194-4acc-b3c0-732787a172bf">
</p>
<br/>
<br/>

**2. Holding a light**
- The idea of a player holding the primary source of light to navigate as well as having limited light resources to move around is also an important aspect we looked into from this game. This helps set the mood as well as the general game dynamics as seen in the example image below.
<p align="center">
<img width="540" alt="image" src="https://github.com/user-attachments/assets/c6f9b649-0581-4fc6-9608-44447e1b0f28">
</p>
<br/>
<br/>

### [colorbomb TRACE](https://colorbomb.itch.io/trace)
TRACE is an online escape room game set on a sandy moon. The player navigates by clicking on arrows on the sides of the screen to change rooms or the direction they're facing. They interact with objects via point-and-click, and they can store items they find and take pictures of things they see. There are a few aspects of this game we're taking inspiration from

**1. The point-and-click style**
- this is a pretty common style for escape room type games, so we think it'd be the most natural method to collect items in our game
<br/>
  
**2. The storage of items**
We are considering having items the user has collected stored in a side panel similar to the one shown in the game
<p align="center">
<img width="800" alt="image" src="https://github.com/user-attachments/assets/b3c508c5-e91c-4238-b9ce-547f85309dff">
</p>
<br/>
<br/>

**3. The puzzle types**
We also are taking inspiration from the puzzles in the game. For instance the one below:

The player has to notice the above pattern of on/off lights on the wall
<p align="center">
<img width="800" alt="image" src="https://github.com/user-attachments/assets/0106ceed-8d2e-4a4a-acf6-1c253ad9c74a">
</p>
<br/>
<br/>

Then they have realize it corresponds to this chest with light up buttons that they can turn on/off
<p align="center">
<img width="800" alt="image" src="https://github.com/user-attachments/assets/ffc8f5bc-913d-4433-9a0c-74da54a80eb3">
</p>
<br/>
<br/>

Putting them in the correct order gives them access to a clue for the other puzzles
<p align="center">
<img width="800" alt="image" src="https://github.com/user-attachments/assets/e4e59b40-19b5-4e61-ac6c-d2b0c50329e2">
</p>
<br/>
<br/>


### [The Quarry](https://quarrygame.2k.com/)
The Quarry is an interactive drama horror game. Players assume control of nine teenage counselors who must survive their last night at Hackett's Quarry summer camp amongst supernatural creatures and violent locals. Players make many choices throughout the game which may significantly affect character development, relationships, the story's plot, and its ending. All nine playable characters may survive or die, depending on the player's decisions.

**Quick Time Events**

  ![image](https://github.com/user-attachments/assets/4def79f1-f78f-419e-9ae0-3d7af91ef162)

One particular aspect we like about this game are the quick time events (QTEs). During cutscenes or gameplay, the player has to react quickly to a timed prompt that appears on screen. In The Quarry's case, it tends to revolve around moving the thumbstick in a particular direction. However, since our game is on the desktop, we're considering having the player click something or press a button/series of buttons instead.



## Gameplay
### Mechanics
- The game is set in an underwater base. They player starts out in a room that has no windows. This acts as their home base for the game since it is safe to use lights without alerting the Kraken
  - Here the player can manually save their progress
- The player can move using the `wasd` or `arrow keys` for movement
  - Moving around quickly generates noise
  - The player can hold the `SHIFT` key to move more slowly and quietly
- The player can hold their breath for [NUMBER OF SECONDS] seconds at a time using the `b` key
- The player can point and click to interact with objects
  - The mouse icon will change to indicate that an object is interactable
  - The player cannot just pick up items by walking over them
  - For puzzles, we intend to have animations for things like key insertion into a door, typing on keypads, etc.
  - Some interactable objects create noise
- The player has a flashlight at the start that they can use to illuminate the base
  - The player will use `right click` to turn on/off the flashlight
  - Turning the flashlight on and off creates noise
  - The flashlight has a rechargeable battery that runs out, so the player must return to base periodically to recharge
- There is a sound meter to display how loud the player is being relative to the envrionment
- There is a base status to display how structurally sound the underwater base is
- If the player dies, they reset to their last save

### Objective
- The player is trying to escape the base. They wake up and do not know what has happened to the base or to their crew. Moreover, the setting is omnious, so they just want to get out.
- To do this, the player has to solve puzzles to learn about the base and find their way to an escape pod
- While trying to escape, the player has to avoid 2 enemies
  1. Primary: the Kraken
    - The Kraken is a colossal squid lurking about outside the base
    - It is attracted to light sources since there is little light in the depths of the ocean
    - At random intervals, the Kraken swims by and the player has to be careful to keep the lights off so as not to provoke it
      - When the Kraken is near the base, all other creatures swimming around outside go away and stop making sounds
    - If the player has their light on near a window for more than [NUMBER OF SECONDS] seconds (we may add some proximity factor as well), the Kraken is attracted back to the underwater base
    - When the Kraken is aggravated, it latches onto the base and makes it shake for [NUMBER OF SECONDS] seconds. This makes the base lose [PERCENTAGE]% structural integrity. During this time, the player must remain quiet and have no lights on for it to go away. If the player fails, the Kraken destroys the base
    - Once the base reaches 0%, it collapses and the player dies
  3. Secondary: the creature
    - The creature is some unknown entity either created by the people on the base or discovered and experimented on -- who knows. Either way, it's been set loose.
    - It lurks around the base, moving slowly from area to area. If the creature is getting close to the player, they begin to notice signs of its presence: sounds, increasing anxiety levels
    - The creature is attracted by sound. If the player is over the sound threshold for more than [NUMBER OF SECONDS] seconds, then it is attracted
    - The creature moves slower than the player, so to get away, the player must run far enough away such that their normal movements no longer alert the creature of their presence
    - When the creature is too close, however, it can hear sound from any kind movement the player makes -- unless the player is holding their breath
    - The player must complete Quick Time Events to escape the creature once it gets too close
      - holding their breath
      - staying still to avoid increased heart rate etc

    

### Main Puzzles
1. Find some document that tells you where x tool is
2. See some code somewhere that let's you unlock keypad
3. Generator with a little bit of juice left needed to unlock area/object but player has to be careful in order to not alert the Kraken since it lights up when used/makes noise
4. (additional if making a secondary enemy) Find a noise maker somehow to distract the indoor creature so they can open the door to leave (opening the door makes too much noise for too long to escape otherwise)


## Development
### First Deliverable
- We added a scene graph to place a laboratory room with objects (boxes, wires, tables, chairs, etc.) and attached it to the scene node. We also created an interactable node to which we add interactable objects so that they can be identified in a more efficient manner. We added a geometric cube as our interactable object.

  ![image](https://github.com/user-attachments/assets/0e279b96-bb0d-418d-9548-897091d21f82)
  
- We handle user input such as pressing the 'wasd' keys to move or the spacebar to jump. The user can also left click on their mouse once approaching and looking at an interactive object (in this case, the block) in order to "pick it up", where it will disappear from the scene. The listener on the keys and object allows for these elements to work coherently.

  ![image](https://github.com/user-attachments/assets/73a966d5-2a63-4919-8666-368d90ff48b1)

- We have CubeChaserControl to extend AbstractControl in order to simulate a potential monster attack on the user. The cube starts off in front of the user and then creeps up to/follows the user. This provides the skeleton elements for when the user gets attacked within the game if they fail at keeping down the light/sound within the game when needed.

  ![image](https://github.com/user-attachments/assets/3c94d595-eb1a-4656-8a2c-c0bf5773627b)

  ### Second Deliverable
  - I added some texture to the floors, walls, and boxes within the scene so that the user felt more immersed with their surroundings. I also changed the lighting to flashlight in order to allow for a darker and more   mysterious feel to the game compared to before. A skeleton human as been added on the side, but there is also a similar also rigged inside the green glowing cylinder. Objects were imported through blender, as were the textures and they were created by hand instead of using read-made-assets online.
  ![image](https://github.com/user-attachments/assets/ee4ed3e9-145e-4e4c-8fef-2f22f41ac10f)
  - If running base_project.java, the changes mentioned above will be noticable. The physics is still present in the scene as the character moves around the room. Additional rooms were added but not texturized for   potential expansion.
  - In the main.java file, the gui for the main menu was added. The menu includes music, a placeholder for the trailer, sound control, tutorial placeholders, and more. The main menu runs separately from the program and when pressing "play" runs a simple cube application. If you press "esc", it brings up a miniature menu. This is due to the need to reformat project_base.java from simpleApplication to appBase classes which, considering my time constrictions and solo work on the project, was hard to fit into the alloted time space. To run main.java without error, the groovy-all.jar file from the top level of the directory needs to be manually imported into libraries as it is outdated and can barely be found online, much less directly downloaded by the system. The menu boasts a nice UI representative of the mood of the game along with some music running in the back for more atmosphere.
 ![image](https://github.com/user-attachments/assets/61731c85-5b11-4375-8aa0-622e38dea687)

  ### Final Deliverable
  - I added a trailer to the beginning of the game instead of a placeholder for increased immersion and overall presentation of the game.
  - There is now a dancing lab resident on the side that cheers on the user when they attempt to escape from the laboratory. There is a mini objective in place where the user has to find the keycard to swipe the   cardreader at the door and open it to successfully finish the game.
  ![image](https://github.com/user-attachments/assets/1d30c74b-13c5-4ae5-9af6-b2a4a856cb55)

  - The keycard has physics just like everything in the world. Thus, if the player clicks to drop the keycard before reaching the cardswiper, it will fall to the floor with a small bounce and lay on the floor until the player decides to pick it back up again. While carrying the keycard, it will appear in front of the player's view so that the player knows it is still carrying the object.
  ![image](https://github.com/user-attachments/assets/41d231b2-443c-4238-b207-82c2f69d0958)
  ![image](https://github.com/user-attachments/assets/198219ae-39f0-47bf-8a65-5d6f8c3c8455)
  ![image](https://github.com/user-attachments/assets/990cd9d7-ad54-48e5-849e-fd67f5fe8ad9)
  - There have been additional textures added, specifically on the keycard, cardreader, door, and npc. The npc is fully rigged and animated, performing one of his animations in a loop while the player investigates the laboratory.
  - The code was modularized compared to previous deliverables, where it is now broken up into many more compact classes that each have a specific purpose. This increases readability and efficiency of the code, as well as making it more compatible with large-scale changes or alterations.
  - Once the keycard has been inserted, there is a small "short-circuit" spike using particles that pops up, and then the door slides open. Afterwards, the screen transforms into a congratulatory message that tells the user that they successfully escaped.
  ![image](https://github.com/user-attachments/assets/9fdcd20b-2206-4f4e-ab5a-101d29b97af6)
  ![image](https://github.com/user-attachments/assets/c73b8311-54f0-4397-b06a-7036ed925afe)
  - The 6 effects include:
      - Particles: the particles that appear when the keycard is inserted
      - Bloom Filter: occurs when the player moves around the room
      - Skybox: can be seen outside as it represents the ocean around the base
      - Camera Shake: once the keycard is inserted, the camera shakes
      - Main Menu: there is an interactable main menu that the user can navigate through
      - Trailer: there is a trailer to connect the game together
      - Options Menu: there is an interactable options menu for the user to click around that has working sound
      - Tutorial Menu: there is a trailer menu for the user to watch the videos which can be paused and resumed at will with sound
      - Sound Adjustment and saving: sound can be adjusted and saved for the game throughout its gameplay
      - Win Screen: the win screen appears when the user has finished the game
   - For sound, there is ambient music play in the background for both the menu and the game. In the menu, every click is accompanied by a specific sound to make it more interactive. In the game, the underwater npc sings a song that gets louder as the user approaches via positional sound. Once the keycard is inserted, a sound of the door opening starts playing via interactive sound.
   - The game is fully playable with the goal being for the user to pick up the keycard and insert it into the cardreader to escape from the room.
