# Crick Crack the Kraken's Back

[game design document](https://en.wikipedia.org/wiki/Game_design_document)

## Game Summary
This game will focus on an amensiac player being stuck inside an abandoned submarine. The base that the player starts at has some lighting and charging ports so 
that the player is able to recharge their partially broken flashlight. The player then enters the rest of the submarine and tries to explore it to find clues
about what happened to the submarine and why the player was here. While exploring, if the player keeps the light on for too long in areas with windows,
it will attract the attention of the deepsea predators (Kraken) and the submarine will begin to shake/crack, causing the player to die if they don't turn off their
light for some time (and they would have to start at their last saved base position). If the player turns off their light, a creeping entity inside the
submarine will attempt to capture the player. The entity moves faster than the player, and it detects the play via sound which is also something to consider
when turning the flashlight on and off since the entity will be able to detect it. The player getting caught by the entity will result in a game over and
a respawn at the last save at the base. The player hears a specific sound when the entity moves and the sounds become clearer when the entity is closer.
If the player runs out of battery, they will have to get back to base in order to recharge. The player will also be able to find items in the submarine the 
further they go, such as more batteries and key information that will help them progress further and learn more about the way to escape.

Consider changing setting from sub to underwater base if rooms/spaces get too "cluttered"/linear.

## Genre
- escape/puzzle
- first-person
- thriller/horror

## Inspiration 

### [Subnautica](https://subnautica.fandom.com/wiki/Subnautica_Wiki)

### The [Amnesia game series](https://store.steampowered.com/app/57300/Amnesia_The_Dark_Descent/)

### [Until Dawn](https://www.playstation.com/en-us/games/until-dawn/)

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
- arrow keys or wasd for movement
- point and click to interact with objects/inventory
- ray pointing for flashlight
- animations for key insertion into a door, interacting with keypads, etc
- reset upon death -- last time player manually saved on base

- Gameplay section is too bare: Include more discussion (include or move elements that are currently in the game summary that better apply to game play.)

- Come up with a three puzzles the player will need to solve. Can then add more later if time permits.

