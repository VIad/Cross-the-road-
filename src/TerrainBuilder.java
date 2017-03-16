import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class TerrainBuilder{
	
	private Toolkit        tool = Toolkit.getDefaultToolkit();
	private SecureRandom   rand = new SecureRandom();
	
	final Point            Terrain_safe_start = new Point(0,800);
	
	final Image            Terrain_safe = tool.getImage(getClass().getResource("Terrain/Safe_terrain.jpg"));
	final Image            Terrain_safe_big = tool.getImage(getClass().getResource("Terrain/Safe_terrain_big.jpg"));
	
	final Image            Terrain_road = tool.getImage(getClass().getResource("Terrain/Road_terrain.jpg"));
	final Image            Terrain_road_big = tool.getImage(getClass().getResource("Terrain/Road_terrain_big.jpg"));
	
	final Image            Terrain_water = tool.getImage(getClass().getResource("Terrain/Water_terrain.jpg"));
	final Image            Terrain_water_big = tool.getImage(getClass().getResource("Terrain/Water_terrain_big.jpg"));
	
	final Image            Terrain_train = tool.getImage(getClass().getResource("Terrain/Train.png"));
	
	static final int       SAFE_TERRAIN = 0;
	static final int       ROAD_TERRAIN = 1;
	static final int       WATER_TERRAIN = 2;
	static final int       TRAIN_TERRAIN = 3;
	
	ArrayList<Point>       obs_start_finish = new ArrayList<Point>();
	ArrayList<Integer>     terrain = new ArrayList<Integer>();
	ArrayList<Boolean>     bigTerrain = new ArrayList<Boolean>();
	ArrayList<Point>       terrainAXIS = new ArrayList<Point>();
	
	protected void buildTerrain(){
		Cross.isReadyToLoadGraphics = true;
		int remainingPixels = 700;
		terrain.clear();
		terrainAXIS.clear();
		bigTerrain.clear();
		
		while(remainingPixels >= 0){
			int toAdd = new Random().nextInt(4);
			if(toAdd == ROAD_TERRAIN){
				
				terrain.add(ROAD_TERRAIN);
				boolean isTerrainbig = rand.nextBoolean();
				bigTerrain.add(isTerrainbig);
				
				if(isTerrainbig){
					
					terrainAXIS.add(new Point(0,remainingPixels - 100));
					remainingPixels-=200;
					
				}else{
					
					terrainAXIS.add(new Point(0,remainingPixels));
					remainingPixels-=100;
					
				}	
			}
			if(toAdd == SAFE_TERRAIN){
				
				terrain.add(SAFE_TERRAIN);
				boolean isTerrainbig = new Random().nextBoolean();
				bigTerrain.add(isTerrainbig);
				
				if(isTerrainbig){
					
					terrainAXIS.add(new Point(0,remainingPixels - 100));
					remainingPixels-=200;
					
				}else{
					
					terrainAXIS.add(new Point(0,remainingPixels));
					remainingPixels-=100;
					
				}	
			}
			if(toAdd == WATER_TERRAIN){
				
				terrain.add(WATER_TERRAIN);
				boolean isTerrainbig = new Random().nextBoolean();
				bigTerrain.add(isTerrainbig);
				
				if(isTerrainbig){
					
					terrainAXIS.add(new Point(0,remainingPixels - 100));
					remainingPixels-=200;
					
				}else{
					
					terrainAXIS.add(new Point(0,remainingPixels));
					remainingPixels-=100;
					
				}	
			}
			if(toAdd == TRAIN_TERRAIN){
				terrain.add(TRAIN_TERRAIN);
				bigTerrain.add(false);
				terrainAXIS.add(new Point(0,remainingPixels));
				remainingPixels -=100;
			}
		
		}
		
		int countOfSafe = 0;
		for(int i =0;i<terrain.size();i++){
			if(terrain.get(i) == SAFE_TERRAIN && countOfSafe <1){
				countOfSafe++;
			}else{
				terrain.set(i, new Random().nextInt(3) + 1);	
			}
		}
		int countOfTrain = 0;
		for(int i = 0;i<terrain.size();i++)
		{
			if(terrain.get(i) == TRAIN_TERRAIN && countOfTrain < 1)
			{
				countOfTrain++;
			}
			else
			{
				int T_set;
				do{
					T_set = new Random().nextInt(4);
					terrain.set(i, T_set);
				}while(T_set == TRAIN_TERRAIN || T_set == SAFE_TERRAIN);
			}
		}
		
		for(int i =0;i<terrain.size();i++){
			
			if(terrain.get(i) == ROAD_TERRAIN){
				
				if(bigTerrain.get(i)){
					obs_start_finish.add(new Point(terrainAXIS.get(i).y,terrainAXIS.get(i).y));
				}else{
					obs_start_finish.add(new Point(terrainAXIS.get(i).y,terrainAXIS.get(i).y));	
				}
			}
			if(terrain.get(i) == SAFE_TERRAIN){	
				if(bigTerrain.get(i)){
					obs_start_finish.add(new Point(terrainAXIS.get(i).y,terrainAXIS.get(i).y ));
				}else{
					obs_start_finish.add(new Point(terrainAXIS.get(i).y,terrainAXIS.get(i).y));
				}
			}
			if(terrain.get(i) == WATER_TERRAIN){
				
				if(bigTerrain.get(i)){
					obs_start_finish.add(new Point(terrainAXIS.get(i).y,terrainAXIS.get(i).y ));
				}else{
					obs_start_finish.add(new Point(terrainAXIS.get(i).y,terrainAXIS.get(i).y ));
				}
			}
			if(terrain.get(i) == TRAIN_TERRAIN){
				if(bigTerrain.get(i)){
					obs_start_finish.add(new Point(terrainAXIS.get(i).y,terrainAXIS.get(i).y ));
				}else{
					obs_start_finish.add(new Point(terrainAXIS.get(i).y,terrainAXIS.get(i).y ));					
				}
			}
			
		}
		
	}
	protected void destroyTerrain()
	{
		obs_start_finish.clear();
		terrain.clear();
		bigTerrain.clear();
		terrainAXIS.clear();
		Cross.player = new Point(Cross.player.x,850);
		//Clearing static fields
		Obstacles.bigObstacle.clear();
		Obstacles.direction.clear();
		Obstacles.lineDirection.clear();
		Obstacles.obstacle.clear();
		Obstacles.obstacleImage.clear();
		Obstacles.obstacleLines.clear();
		Obstacles.obstacleLineType.clear();
		Obstacles.trainDir.clear();
		Obstacles.trainImg.clear();
		Obstacles.trainObs.clear();
		Cross.isOnWater = false;
		Cross.isReadyToLoadGraphics = false;
		Cross.PLAYER_DIRECTION = Cross.UP;
	}
}
