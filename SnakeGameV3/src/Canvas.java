import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.Timer;

public class Canvas extends JComponent implements KeyListener{ //where everything happens
	private List<SnakeComponent> snake = new ArrayList<SnakeComponent>(); //the snake is a list of components
	private List<Turn> turns = new ArrayList<Turn>(); //list to store all previous position of snake's head
	private int speed = 10, score = 0; 
	private Timer timer1;
	private Food food = new Food();
	private String dir = "";
	private boolean gameOver = false; 
	public Canvas() {
		snake.add(new SnakeComponent(100, 100, "down")); //initialises snake
		timer1 = new Timer(50, new TimerCallback());
		addKeyListener(this);
		setFocusable(true);
	}
	@Override
	public void paintComponent(Graphics g) {
		if (gameOver) {
			displayGameOverMessage(g);
			score = 0;
		} else {
			g.drawString("Score: " + score, getWidth()/2 - 15, 20);
		}
		g.setColor(Color.BLUE);
		g.fillOval(snake.get(0).getSnakeComponentX(), snake.get(0).getSnakeComponentY(), 20, 20);
		for (int i = 1; i < snake.size(); i++) { //all components of snake follow each other
			g.fillOval(turns.get(turns.size() - (2*i)).getX(), turns.get(turns.size() - (2*i)).getY(), 20, 20);
		}
		g.setColor(Color.RED);
		g.fillOval(food.getFoodX(), food.getFoodY(), 20, 20);
	}
	private void displayGameOverMessage(Graphics g) {
		g.setFont(new Font("Calibri", 1, 25));
		g.drawString("GAME OVER!", getWidth()/2 - 80, getHeight()/2 - 50);
		g.drawString("Score: " + score, getWidth()/2 - 50, getHeight()/2);
		g.drawString("Press ENTER to start again!", getWidth()/2 - 150, getHeight()/2 + 50);
	}
	protected class TimerCallback implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { //make this better
			collide(); //collisions are checked after every timer call
			if (snake.get(0).getDirection().equals("right")) {
				snake.get(0).setSnakeComponentX(snake.get(0).getSnakeComponentX()
						+ speed);
			} else if (snake.get(0).getDirection().equals("down")) {
				snake.get(0).setSnakeComponentY(snake.get(0).getSnakeComponentY()
						+ speed);
			} else if (snake.get(0).getDirection().equals("up")) {
				snake.get(0).setSnakeComponentY(snake.get(0).getSnakeComponentY()
						- speed);
			} else if ( snake.get(0).getDirection().equals("left")) {
				snake.get(0).setSnakeComponentX(snake.get(0).getSnakeComponentX()
						- speed);
			}
			turns.add(new Turn(snake.get(0).getSnakeComponentX(), snake.get(0).getSnakeComponentY()));
			repaint();
		}		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode;
		keyCode = e.getKeyCode();		
		if (keyCode == KeyEvent.VK_RIGHT) {
			if (!dir.equals("left")) { //these conditions don't allow the snake to do directly opposite to its current direction
				setMovement("right");	
			}
		} else if (keyCode == KeyEvent.VK_DOWN) {
			if (!dir.equals("up")) {
				setMovement("down");	
			}
		} else if (keyCode == KeyEvent.VK_LEFT) {
			if (!dir.equals("right")) {
				setMovement("left");	
			}
		} else if (keyCode == KeyEvent.VK_UP) {
			if (!dir.equals("down")) {
				setMovement("up");
			}
		} else if (keyCode == KeyEvent.VK_ENTER) {
			gameOver = false;
			timer1.start();
		}		
	}
	private void setMovement(String dir) { //method to set the new direction of snake
		this.dir = dir;
		snake.get(0).setDirection(dir);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	public boolean collisionWithFood() { //method to check if snake has collided with food
		for (int i = 0; i < snake.size(); i++) {
			return (Math.abs(snake.get(0).getSnakeComponentX() - food.getFoodX()) < 17 &&
					Math.abs(snake.get(0).getSnakeComponentY() - food.getFoodY()) < 17) ;
		}
		return false;	
	}
	public boolean collisionWithWall() { //method to check if snake has collided with wall
		return (snake.get(0).getDirection().equals("right") && snake.get(0).getSnakeComponentX() + 20 >= getWidth()) || //right
				(snake.get(0).getDirection().equals("down") && snake.get(0).getSnakeComponentY() + 30 >= getHeight()) || //down
				(snake.get(0).getDirection().equals("left") && snake.get(0).getSnakeComponentX() <= 0) || //left
				(snake.get(0).getDirection().equals("up") && snake.get(0).getSnakeComponentY() <= 0) ;  //up
	}
	public void collide() { //checks for collisions
		if (collisionWithWall() || collisionWithSelf()) {
			System.out.println("Collision with wall or self!");
			initialize();
			timer1.stop();
		} else if (collisionWithFood()) {
			System.out.println("Food collision!");
			score++;
			addComponentToSnake();
			generateFood();
		} 
	}
	public boolean collisionWithSelf() { //method that checks if snake collided with any of its components
		for (int i = 2; i < snake.size(); i++) {
			if (Math.abs(snake.get(0).getSnakeComponentX() - (turns.get(turns.size() - (2*i)).getX())) < 15 &&
					Math.abs(snake.get(0).getSnakeComponentY() - (turns.get(turns.size() - (2*i))).getY()) < 15) {
				return true;
			}
		}	
		return false;
	}
	
	private void initialize() {
		gameOver = true;
		turns.clear();
		snake.clear();
		snake.add(new SnakeComponent(100, 100, "right"));
	}
	private void addComponentToSnake() { //adds new component behind last component
		switch (snake.get(snake.size()-1).getDirection()) {
		case "right": //for right
			snake.add(new SnakeComponent(snake.get(snake.size()-1).getSnakeComponentX() - 20,
					snake.get(snake.size()-1).getSnakeComponentY(), "right")); 
			break;
		case "down": //for down
			snake.add(new SnakeComponent(snake.get(snake.size()-1).getSnakeComponentX(),
					snake.get(snake.size()-1).getSnakeComponentY() - 20, "down")); 
			break;
		case "left": //for left
			snake.add(new SnakeComponent(snake.get(snake.size()-1).getSnakeComponentX() + 20,
					snake.get(snake.size()-1).getSnakeComponentY(), "left")); 
			break;
		case "up": //for up
			snake.add(new SnakeComponent(snake.get(snake.size()-1).getSnakeComponentX(),
					snake.get(snake.size()-1).getSnakeComponentY() + 20, "up")); 
			break;
		}
	}
	public void generateFood() { //method to generate food at random locations
		Random rnd = new Random();	
		int rndX = 0;
		int rndY = 0;
		rndX = rnd.nextInt(getWidth()-20);
		rndY = rnd.nextInt(getHeight()-20);
		food.setFoodX(rndX);
		food.setFoodY(rndY);
	}
	
}