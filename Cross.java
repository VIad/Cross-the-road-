import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.SecureRandom;
import javax.swing.JFrame;


public class Cross extends KeyAdapter implements Runnable{

	private JFrame              frame;
	
	private Rendering           panel;
	
	static Thread               mainGameThread;
	
	static TerrainBuilder       builder = new TerrainBuilder();
	
	static Obstacles            obstacles = new Obstacles();
	
	static Point                player;
	
	static boolean              isOver;
	static boolean              isPaused;
	static boolean              isReadyToLoadGraphics;
	static boolean              isSTAT;
	static boolean              isOnWater;
	static boolean              isDisplayingInfo;

	static long                 thread_sleep_param;
	static int                  PLAYER_DIRECTION;
	static int                  SCORE;
	
	static final int            UP = 0;
	static final int            DOWN = 1;
	static final int            LEFT = 2;
	static final int            RIGHT = 3;
	
	//PRIVATE NON - OBJECT FIELD
	private boolean             firstEvent;
	private boolean             keyWasReleased;
	private long                move;
	
	
	public static void main(String args[]){
		new Cross().initialize();
	}
	
	private void initialize(){
		isReadyToLoadGraphics = false;
		frame = new JFrame("Frogger");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
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
		isDisplayingInfo = false;
		isOnWater = false;
		move = System.currentTimeMillis();
		firstEvent = true;
		//For testing
		thread_sleep_param = 3;
		//For testing
		keyWasReleased = false;
		SCORE = 0;
	    player = new Point(panel.getSize().width / 2 - 25,850);
	    PLAYER_DIRECTION = UP;
	    mainGameThread.start();
	    mainGameThread.setName("Main");
	    builder.buildTerrain();
	    obstacles.createObstacleLines();
	   
	}
	
	@Override
	public void run(){
		while(!isOver){
			if(!isPaused){
				try{
				if(player.y >=900 || player.x <=-50 || player.x >=700){
					isOver = true;
				}
				obstacles.moveObstacles();
				obstacles.moveTrain();
				obstacles.clearObstacles();
				obstacles.clearTrain();
				obstacles.obstaclePlayerCollision();
				obstacles.collidePlayerTrain();
				if(player.y <0){
					builder.destroyTerrain();
					builder.buildTerrain();
					obstacles.createObstacleLines();
					SCORE++;
				}
				obstacles.generateObstacles(new SecureRandom().nextBoolean());
				obstacles.generateTrain(new SecureRandom().nextBoolean());
				
				if(System.currentTimeMillis() - move > 200) isSTAT = true;
				else isSTAT = false;
				}catch(Exception ex){}
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
		if(Event.getKeyCode() == KeyEvent.VK_SPACE && !isOver){
			isPaused = !isPaused;
		}
		if(Event.getKeyCode() == KeyEvent.VK_R && isOver){
			builder.destroyTerrain();
			startGame();
		}
		if(Event.getKeyCode() == KeyEvent.VK_1 && !isOver){
			thread_sleep_param = 2;
		}
		if(Event.getKeyCode() == KeyEvent.VK_2 && !isOver){
			thread_sleep_param = 3;
		}
		if(Event.getKeyCode() == KeyEvent.VK_I){
			isDisplayingInfo = !isDisplayingInfo;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent Event){
		keyWasReleased = true;
	}

	

}
