# snake-game
This is the recreation of Nokia's popular Snake game using JFrame.
Since this is just the first working version, it does not contain different levels and only has an endless level. 

CLASSES 

Canvas class: Contains all the computation.
Food class: Defines the attributes of a "food" for the snake.
SnakeComponent: Defines the attributes of every component of the snake. This class is used to make a list of SnakeComponents that currently make up the snake.
Turn class: Defines the x and y coordinates of every turn that the snake makes. This class is used to make a list of where the snake is going.
MyApp: Creates and makes the JFrame visible. 

LOGIC 

The snake is made using a list of SnakeComponents. Every SnakeComponent has its own attributes and follows the SnakeComponent in front of it. 
The food is generated randomly. 
Collisions are detected when the head of the snake overlaps with the food, itself, or with the corners. 
