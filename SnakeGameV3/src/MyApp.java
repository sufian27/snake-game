import javax.swing.JFrame;

public class MyApp extends JFrame {
	public MyApp() {
		Canvas canvas = new Canvas();
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snake Game");
		setResizable(false);
		add(canvas);
	}
	public static void main(String[] args) {
		new MyApp().setVisible(true);
	}
}
