public class SnakeComponent { //every component has a direction, X coordinate, and Y coordinate
	private int snakeComponentX;
	private int snakeComponentY;
	private String direction;
	public SnakeComponent(int snakeComponentX, int snakeComponentY, 
			String direction) {
		this.snakeComponentX = snakeComponentX;
		this.snakeComponentY = snakeComponentY;
		
		this.direction = direction;
	}
	public int getSnakeComponentX() {
		return snakeComponentX;
	}
	public void setSnakeComponentX(int snakeComponentX) {
		this.snakeComponentX = snakeComponentX;
	}
	public int getSnakeComponentY() {
		return snakeComponentY;
	}
	public void setSnakeComponentY(int snakeComponentY) {
		this.snakeComponentY = snakeComponentY;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
