
# myFortress
### Quentin Souvignet

This project is about making a runner in Java (little video game).

## Get started:
1-> go to Project Structure/Project and choose your Java SDK

2-> go to Project Structure/Librairies and add a Java librairie: /path/to/javafx/lib

3-> add a new configuration with [mainApp](https://github.com/NiskuT/myFortress/blob/master/src/mainApp.java) for the main and 
select add VM options and add the following line:
--module-path "/PATH/TO/JAVA-FX/lib" --add-modules javafx.controls

Now Build the project and after that run it.

## Documentation

### Scene
The application is compososed of 3 scenes:

The [homePage](https://github.com/NiskuT/myFortress/blob/master/src/homePage.java) is a menu.

The [settingScene](https://github.com/NiskuT/myFortress/blob/master/src/settingScene.java) let the user choose his keyboard configuration.

Finally the [GameScene](https://github.com/NiskuT/myFortress/blob/master/src/GameScene.java) class power the game.

### Class used

The [staticThing](https://github.com/NiskuT/myFortress/blob/master/src/staticThing.java) class offer a background for all 
of the 3 scenes. For the GameScene it's a background using parallax scrolling to make the game more realistic.

The [AnimatedThing](https://github.com/NiskuT/myFortress/blob/master/src/AnimatedThing.java) power all entities including the
shoot. This class extends the [Hero](https://github.com/NiskuT/myFortress/blob/master/src/Hero.java) class, the 
[Foe](https://github.com/NiskuT/myFortress/blob/master/src/Foe.java) class, the [flyingFoe](https://github.com/NiskuT/myFortress/blob/master/src/flyingFoe.java)
class, and the [Projectile](https://github.com/NiskuT/myFortress/blob/master/src/Projectile.java).

The [Camera](https://github.com/NiskuT/myFortress/blob/master/src/Camera.java) class let the hero not being static on the screen, 
using some differentials equations.

The [KeyConfig](https://github.com/NiskuT/myFortress/blob/master/src/KeyConfig.java) class defined the action associated 
with each key.


