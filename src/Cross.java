import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JFrame;

public class Cross extends KeyAdapter implements Runnable{
	/*
	 * TODO add train
	 * TODO When all of the above are complete, the game is complete
	 */
	
	
	private JFrame              frame;
	
	private Thread              mainGameThread;

	private Rendering           panel;
	
	static TerrainBuilder       builder = new TerrainBuilder();
	
	static Obstacles            obstacles = new Obstacles();
	
	static Point                player;
	
	static boolean              isOver;
	static boolean              isPaused;
	static boolean              isReadyToLoadGraphics;
	static boolean              isSTAT;
	static boolean              isOnWater;
	
	static int                  PLAYER_DIRECTION;
	
	static final int            UP = 0;
	static final int            DOWN = 1;
	static final int            LEFT = 2;
	static final int            RIGHT = 3;
	
	//PRIVATE NON - OBJECT FIELD
	private boolean             firstEvent;
	private boolean             keyWasReleased;
	private long                move;
	private long                thread_sleep_param;
	
	
	public static void main(String args[]){
		new Cross().initialize();
	}
	
	private void initialize(){
		isReadyToLoadGraphics = false;
		frame = new JFrame("Cross the road");
		panel = new Rendering();
		frame.setVisible(true);
		frame.setSize(707, 930);
		panel.setBounds(0,0, 700, 900);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel,BorderLayout.CENTER);
		startGame();
	}
	private void startGame(){
		isOver = false;
		mainGameThread = new Thread(this);
		isPaused = false;
		isSTAT = true;
		isOnWater = false;
		move = System.currentTimeMillis();
		firstEvent = true;
		//For testing
		thread_sleep_param = 8;
		//For testing
		keyWasReleased = false;
	    player = new Point(panel.getSize().width / 2 - 25,850);
	    PLAYER_DIRECTION = UP;
	    mainGameThread.start();
	    mainGameThread.setName("Main");
	    builder.buildTerrain();
	    obstacles.createObstacleLines();
	}
	
	
	//MAIN GAME THREAD;
	@Override
	public void run(){
		while(!isOver){
			if(!isPaused){
				if(player.y >=900 || player.x <=-50 || player.x >=700){
					isOver = true;
				}
				obstacles.moveObstacles();
				obstacles.clearObstacles();
				obstacles.obstaclePlayerCollision();
				if(player.y <0){
					builder.destroyTerrain();
					builder.buildTerrain();
					obstacles.createObstacleLines();
				}
				obstacles.generateObstacles(new Random().nextBoolean());
				if(System.currentTimeMillis() - move > 200) isSTAT = true;
				else isSTAT = false;	
			}
			try{Thread.sleep(thread_sleep_param);}catch(InterruptedException e){}
		}
		
	}
	
	
	
	
	
	//MAIN GAME THREAD : END
	
	@Override
	public void keyPressed(KeyEvent Event){
		if((Event.getKeyCode() == KeyEvent.VK_W || Event.getKeyCode() == KeyEvent.VK_UP) && !isPaused && !isOver &&(firstEvent || keyWasReleased)){
			player = new Point(player.x, player.y - 25);
			PLAYER_DIRECTION = UP;
			move = System.currentTimeMillis();
			keyWasReleased = false;
			firstEvent = false;
		}
		if((Event.getKeyCode() == KeyEvent.VK_A || Event.getKeyCode() == KeyEvent.VK_LEFT)&& !isPaused && !isOver &&(firstEvent || keyWasReleased)){
			player = new Point(player.x - 25, player.y);
			PLAYER_DIRECTION = LEFT;
			move = System.currentTimeMillis();
			keyWasReleased = false;
			firstEvent = false;
		}
		if((Event.getKeyCode() == KeyEvent.VK_S || Event.getKeyCode() == KeyEvent.VK_DOWN)&& !isPaused && !isOver &&(firstEvent || keyWasReleased)){
			player = new Point(player.x, player.y + 25);
			PLAYER_DIRECTION = DOWN;
			move = System.currentTimeMillis();
			keyWasReleased = false;
			firstEvent = false;
		}
		if((Event.getKeyCode() == KeyEvent.VK_D || Event.getKeyCode() == KeyEvent.VK_RIGHT)&& !isPaused && !isOver &&(firstEvent || keyWasReleased)){
			player = new Point(player.x + 25, player.y);
			PLAYER_DIRECTION = RIGHT;
			move = System.currentTimeMillis();
			keyWasReleased = false;
			firstEvent = false;
		}
		if(Event.getKeyCode() == KeyEvent.VK_SPACE){
			isPaused = !isPaused;
		}
		if(Event.getKeyCode() == KeyEvent.VK_R && isOver){
			builder.destroyTerrain();
			startGame();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent Event){
		keyWasReleased = true;
	}

	

}
