# Snake Game

## Recreation of Nokia's popular Snake game using JFrame. 

I made this app after taking my first Computer Science course in college. Since this is just the first working version, it does not contain different levels and only has an endless level. 

### CLASSES 

- **Canvas:** Contains all the computation.
- **Food:** Defines the attributes of a "food" for the snake.
- **SnakeComponent:** Defines the attributes of every component of the snake. This class is used to make a list of SnakeComponents that currently make up the snake.
- **Turn:** Defines the x and y coordinates of every turn that the snake makes. This class is used to make a list of where the snake is going.
- **MyApp:** Creates and makes the JFrame visible. 

### LOGIC 

- The snake is made using a list of **SnakeComponent**s. Every **SnakeComponent** has its own attributes and follows the **SnakeComponent** in front of it. 
- The **Food** is generated randomly. 
- Collisions are detected when the head of the snake overlaps with the **Food**, itself, or with the corners. 

### INSTRUCTIONS

- Press *ENTER* to start the game. 
- Avoid collision with walls and yourself and try to eat as much food as possible for a higher score.  
