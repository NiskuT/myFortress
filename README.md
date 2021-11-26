
# myFortress
###Quentin Souvignet

This project is about making a runner in Java (little video game).

## Get started:
1-> go to Project Structure/Project and choose your Java SDK

2-> go to Project Structure/Librairies and add a Java librairie: /path/to/javafx/lib

3-> add a new configuration with mainApp for the main and select add VM options and add the following line:
--module-path "/PATH/TO/JAVA-FX/lib" --add-modules javafx.controls

Now Build the project and after that run it.

## Documentation

The static elements like the background and the hearth are handle by the [staticThing](https://github.com/NiskuT/myFortress/blob/master/src/staticThing.java) class.

The hero class extends the [AnimatedThing](https://github.com/NiskuT/myFortress/blob/master/src/AnimatedThing.java) which provide the functions require to move the Hero.

The [Camera](https://github.com/NiskuT/myFortress/blob/master/src/Camera.java) class let the hero not being static on the screen, using some differentials equations.

Finally the [GameScene](https://github.com/NiskuT/myFortress/blob/master/src/GameScene.java) class power the game.
