import java.awt.Image;
import java.awt.Point;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
    
public class Obstacles{
   
	static TerrainBuilder         build = Cross.builder;	
	private SecureRandom          rand = new SecureRandom();
	
	static ArrayList<Image>       obstacleImage = new ArrayList<Image>();
	static ArrayList<Point>       obstacle = new ArrayList<Point>();
	static ArrayList<Point>       obstacleLines = new ArrayList<Point>();
	static ArrayList<Integer>     obstacleLineType = new ArrayList<Integer>();
	static ArrayList<Boolean>     lineDirection = new ArrayList<Boolean>();
	static ArrayList<Boolean>     direction = new ArrayList<Boolean>();
	static ArrayList<Boolean>     bigObstacle = new ArrayList<Boolean>();
	
	//Train
	static ArrayList<Point>       trainObs = new ArrayList<Point>();
	static ArrayList<Boolean>     trainDir = new ArrayList<Boolean>();
	static ArrayList<Image>       trainImg = new ArrayList<Image>();
	
	private static final int      LINE_ROAD = 0;
	private static final int      LINE_WATER = 1;
	private static final int      LINE_TRAIN = 2;
	
	private static final int      BIG_OBS_CHANCE_PARAM = 4;
	
	
	private long                  lastTimeGenerated = 0;	
	/*
	 *     lineDirectionS 
	 *     LEFT = TRUE
	 *     RIGHT = FALSE;
	 *     
	 *     TYPES - 0 ROAD
	 *     TYPES - 1 WATER
	 *     TYPES - 2 TRAIN 
	 *     
	 */
	
	
	protected void createObstacleLines(){
		
		for(int i = 0;i<build.terrain.size();i++){
			if(build.terrain.get(i) == TerrainBuilder.ROAD_TERRAIN){
				if(build.bigTerrain.get(i)){
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 77));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_ROAD);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y  - 27));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_ROAD);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y + 23));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_ROAD);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y + 73));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_ROAD);
				}
				else{
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 77));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_ROAD);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 27));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_ROAD);
				}
			}
			if(build.terrain.get(i) == TerrainBuilder.WATER_TERRAIN){
				if(build.bigTerrain.get(i)){
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 77));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_WATER);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y  - 27));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_WATER);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y + 23));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_WATER);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y + 73));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_WATER);
				}
				else{
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 77));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_WATER);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 27));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_WATER);
					do{
						lineDirection.set(lineDirection.size() - 1, rand.nextBoolean());
						break;
					}while(lineDirection.get(lineDirection.size() - 1).booleanValue() == lineDirection.get(lineDirection.size() - 1).booleanValue());	
				}
			}
			if(build.terrain.get(i) == TerrainBuilder.TRAIN_TERRAIN){
				if(build.bigTerrain.get(i)){
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 77));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_TRAIN);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y + 73));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_TRAIN);
				}
				else{
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 77));
					lineDirection.add(rand.nextBoolean());
					obstacleLineType.add(LINE_TRAIN);
				}
			}

		}
	}


	protected void generateObstacles(boolean gen) {
		
		if(gen && rand.nextBoolean() && rand.nextBoolean() && new Random().nextInt(300) < 50){
			
			for(int i = 0;i<obstacleLines.size();i++){
			  if(obstacleLineType.get(i) == LINE_ROAD){
				
				  if(lineDirection.get(i)){
						
						if(rand.nextBoolean()){
							
							boolean hasBroken = false;
							Point p = new Point(obstacleLines.get(i).x - new Random().nextInt(2000),obstacleLines.get(i).y);
							
							for(int in = 0;in<obstacle.size();in++){
								
								if(obstacle.get(in).y == p.y && obstacle.get(in).x - p.x < 300){
									
									hasBroken = true;
									break;
									
								}
							}
							
							if(!hasBroken){
								
							obstacle.add(p);
							direction.add(lineDirection.get(i));
						
							
							if(rand.nextBoolean() && rand.nextBoolean() && i % BIG_OBS_CHANCE_PARAM ==0){
								
								obstacleImage.add(new Rendering().busImage_LEFT(new Random().nextInt(4)));
							    bigObstacle.add(true);
							    
							}
							else{
								
								obstacleImage.add(new Rendering().carImage_LEFT(new Random().nextInt(8)));
							    bigObstacle.add(false);
							    
							}
							}
						}
						
					}else{
						
						if(rand.nextBoolean()){
							
							boolean hasBroken = false;
							Point p = new Point(obstacleLines.get(i).x + 600 + new Random().nextInt(2000),obstacleLines.get(i).y);
							
							for(int in = 0;in<obstacle.size();in++){
								
								if(obstacle.get(in).y == p.y && obstacle.get(in).y - p.y > - 300){
									
									hasBroken = true;
									break;
									
								}
							}
							if(!hasBroken){
								
							direction.add(lineDirection.get(i));
							obstacle.add(p);
							
							
							if(rand.nextBoolean() && rand.nextBoolean() && i % BIG_OBS_CHANCE_PARAM ==0){
								
								obstacleImage.add(new Rendering().busImage_RIGHT(new Random().nextInt(4)));
							    bigObstacle.add(true);
							    
							}
							else{
								
								obstacleImage.add(new Rendering().carImage_RIGHT(new Random().nextInt(8)));
							    bigObstacle.add(false);
							    
							   }
							
						  }
					   }
					}				 
			 }
			  if(obstacleLineType.get(i) == LINE_WATER){
				  if(lineDirection.get(i)){
								
							boolean hasBroken = false;
							Point p = new Point(obstacleLines.get(i).x - new Random().nextInt(2000),obstacleLines.get(i).y);
							
							for(int in = 0;in<obstacle.size();in++){
								
								if(obstacle.get(in).y == p.y && obstacle.get(in).x - p.x < 300){
									
									hasBroken = true;
									break;
									
								}
							}
							
							if(!hasBroken){
								
							obstacle.add(p);
							direction.add(lineDirection.get(i));
							
							
							if(rand.nextBoolean() && rand.nextBoolean() && i % 6 ==0){
								
								obstacleImage.add(new Rendering().waterImage_BIG(new Random().nextInt(1)));
							    bigObstacle.add(true);
							    
							}
							else{
								
								obstacleImage.add(new Rendering().waterImage(new Random().nextInt(4)));
							    bigObstacle.add(false);
							    
							   }
							}
						
						
					}else{
						
						
							
							boolean hasBroken = false;
							Point p = new Point(obstacleLines.get(i).x + 600 + new Random().nextInt(2000),obstacleLines.get(i).y);
							
							for(int in = 0;in<obstacle.size();in++){
								
								if(obstacle.get(in).y == p.y && obstacle.get(in).y - p.y > - 300){
									
									hasBroken = true;
									break;
									
								}
							}
							if(!hasBroken){
								
							direction.add(lineDirection.get(i));
							obstacle.add(p);
							
							
							if(rand.nextBoolean() && rand.nextBoolean() && i % 6 ==0){
								
								obstacleImage.add(new Rendering().waterImage_BIG(new Random().nextInt(1)));
							    bigObstacle.add(true);
							    
							}
							else{
								
								obstacleImage.add(new Rendering().waterImage(new Random().nextInt(4)));
							    bigObstacle.add(false);
							    
							   }
							
						  }
					   
			        }
			  }
			  
		  }
	   }
	}
	protected void moveObstacles(){
		for(int i = 0;i<obstacle.size();i++){
			if(direction.get(i)){
				obstacle.set(i, new Point(obstacle.get(i).x + 1,obstacle.get(i).y));
			}
			else{
				obstacle.set(i, new Point(obstacle.get(i).x - 1,obstacle.get(i).y));
			}
		}
	}
    
	protected void clearObstacles(){
		for(int i = 0;i<obstacle.size();i++){
			if(obstacle.get(i).x < -2000 || obstacle.get(i).x > 3000){
				obstacle.remove(i);
				bigObstacle.remove(i);
				obstacleImage.remove(i);
				direction.remove(i);
				
			}
		}
	}
   protected void obstaclePlayerCollision(){
	   for(int i = 0;i<obstacle.size();i++){
		 
		   if(!bigObstacle.get(i)){
			   //CAR
			   if(Cross.player.y - obstacle.get(i).y <= 50 + 50 && Cross.player.y - obstacle.get(i).y >= 1 + 50){
				   if(direction.get(i)){
				   if(Cross.player.x - obstacle.get(i).x <= 100 && Cross.player.x - obstacle.get(i).x >= 1){
					   Cross.isOver = true;
				   }
				  }else{
					  if(Cross.player.x - obstacle.get(i).x >= -35 && Cross.player.x - obstacle.get(i).x<= 65){
						   Cross.isOver = true;
					   }
				  }
			   }
		   }else{
			   if(Cross.player.y - obstacle.get(i).y <= 50 + 50 && Cross.player.y - obstacle.get(i).y >= 1 + 50){
				   if(direction.get(i)){
				   if(Cross.player.x - obstacle.get(i).x <= 180 && Cross.player.x - obstacle.get(i).x >= 20){
					   Cross.isOver = true;
				   }
				  }else{
					  if(Cross.player.x - obstacle.get(i).x >= -35 && Cross.player.x - obstacle.get(i).x<= 165){
						  Cross.isOver = true;
					   }
				  }
			   }
		   } 
	  }	
	  for(int i = 0;i<build.terrain.size();i++){
		  if(build.terrain.get(i) == TerrainBuilder.WATER_TERRAIN){
			  if(!(Cross.player.y < build.obs_start_finish.get(i).y)){
			  if(build.bigTerrain.get(i)){
				  Cross.isOnWater = false;
				  if(Cross.player.y - build.obs_start_finish.get(i).y <200 && Cross.player.y - build.obs_start_finish.get(i).y >=0){
					  Cross.isOnWater = true;
					  
				  }
				  break;
			  }else{
				  Cross.isOnWater = false;
				  if(Cross.player.y - build.obs_start_finish.get(i).y <100 && Cross.player.y - build.obs_start_finish.get(i).y >=0){   
					  Cross.isOnWater = true;
					  
				  }
				  break;
				  
			  }
			}else{Cross.isOnWater = false;}
				
		  }
	  }
   }
   protected void generateTrain(boolean gen) 
   {
	   for(int i = 0;i<obstacleLineType.size();i++)
	   {
		   if(obstacleLineType.get(i) == LINE_TRAIN)
		   {
			  if(gen && System.currentTimeMillis() - lastTimeGenerated > 5000)
			  {
				  
				  lastTimeGenerated = System.currentTimeMillis();
				  
				  if(!lineDirection.get(i))
				  {
					  //left heading
					  trainDir.add(lineDirection.get(i));
					  Point p = new Point(obstacleLines.get(i).x + 700 + new Random().nextInt(2000),obstacleLines.get(i).y + 75);
					  trainObs.add(p);
				  }
				  else
				  {
					  //True - right heading
					  trainDir.add(lineDirection.get(i));
					  Point p = new Point(obstacleLines.get(i).x - 1500 - new Random().nextInt(500),obstacleLines.get(i).y + 75);
					  trainObs.add(p);
				  }
				  trainImg.add(new Rendering().trainImage(new Random().nextInt(4)));
				  trainImg.add(new Rendering().trainImage(new Random().nextInt(4)));
				  trainImg.add(new Rendering().trainImage(new Random().nextInt(4)));
				  trainImg.add(new Rendering().trainImage(new Random().nextInt(4)));

			  }
		   }
	   }
	
   }
   protected void moveTrain()
   {
	   if(!trainObs.isEmpty())
	   {
		   for(int i = 0;i<trainObs.size();i++)
		   {
			   if(trainDir.get(i))
			   {
				   trainObs.set(i, new Point(trainObs.get(i).x + 2,trainObs.get(i).y));
			   }
			   else
			   {
				   trainObs.set(i, new Point(trainObs.get(i).x - 2,trainObs.get(i).y));
			   }
			   
		   }
	   }
   }
   protected static boolean checkForCloseTrain()
   {
	   for(int i = 0;i<trainObs.size();i++)
	   {
		   if(!trainDir.get(i))
		   {
			   if(trainObs.get(i).x - 700 <=600 && trainObs.get(i).x > 0)
			   {
				   return true;
			   }
		   }
		   else
		   {
			  if(trainObs.get(i).x > - 600 && trainObs.get(i).x < 700)
			  {
				  return true;
			  }
		   }
	   }
	   return false;
   }
   protected void collidePlayerTrain()
   {
	   if(!trainObs.isEmpty())
	   {
		   for(int i = 0;i<trainObs.size();i++)
		   {
			   if(Cross.player.y - trainObs.get(i).y <=100 && Cross.player.y - trainObs.get(i).y >=0 )
			   {
				 if(trainDir.get(i))
				 {
					 //To right
					 if(Cross.player.x - trainObs.get(i).x <=225 && Cross.player.x - trainObs.get(i).x>=-775)
					 {
						 Cross.isOver = true;
					 }
				 }
				 else
				 {
					 //To left
					 if(Cross.player.x - trainObs.get(i).x >= 0 && Cross.player.x - trainObs.get(i).x <=1000)
					 {
						 Cross.isOver = true;
					 }
				 }
			   }  
		   }
	   }
	   

	   
   }
   protected void clearTrain()
   {
	   if(!trainObs.isEmpty())
	   {
		   for(int i = 0;i<trainObs.size();i++)
		   {
			   if(trainObs.get(i).x > 3000 || trainObs.get(i).x < - 2000)
			   {
				   trainObs.remove(i);
				   trainImg.remove(i);
				   trainDir.remove(i);
			   }
		   }
	   }
   }
}
