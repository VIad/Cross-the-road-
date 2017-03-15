import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Obstacles{
	
	static TerrainBuilder  build = Cross.builder;	
	
	static ArrayList<Image>       obstacleImage = new ArrayList<Image>();
	static ArrayList<Point>       obstacle = new ArrayList<Point>();
	static ArrayList<Point>       obstacleLines = new ArrayList<Point>();
	static ArrayList<Integer>     obstacleLineType = new ArrayList<Integer>();
	static ArrayList<Boolean>     lineDirection = new ArrayList<Boolean>();
	static ArrayList<Boolean>     direction = new ArrayList<Boolean>();
	static ArrayList<Boolean>     bigObstacle = new ArrayList<Boolean>();
	
	private static final int       LINE_ROAD = 0;
	private static final int       LINE_WATER = 1;
	

	
	
	
	/*
	 *     lineDirectionS 
	 *     LEFT = TRUE
	 *     RIGHT = FALSE;
	 *     
	 *     TYPES - 0 ROAD
	 *     TYPES - 1 WATER
	 *     TYPES - 2 TRAIN ( NOT YET IMPLEMENTED )
	 *     
	 */
	
	
	protected void createObstacleLines(){
		for(int i = 0;i<build.terrain.size();i++){
			if(build.terrain.get(i) == TerrainBuilder.ROAD_TERRAIN){
				if(build.bigTerrain.get(i)){
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 77));
					lineDirection.add(new Random().nextBoolean());
					obstacleLineType.add(0);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y  - 27));
					lineDirection.add(new Random().nextBoolean());
					obstacleLineType.add(0);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y + 23));
					lineDirection.add(new Random().nextBoolean());
					obstacleLineType.add(0);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y + 73));
					lineDirection.add(new Random().nextBoolean());
					obstacleLineType.add(0);
					
				}
				else{
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 77));
					lineDirection.add(new Random().nextBoolean());
					obstacleLineType.add(0);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 27));
					lineDirection.add(new Random().nextBoolean());
					obstacleLineType.add(0);
				}
			}
			if(build.terrain.get(i) == TerrainBuilder.WATER_TERRAIN){
				if(build.bigTerrain.get(i)){
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 77));
					lineDirection.add(new Random().nextBoolean());
					obstacleLineType.add(1);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y  - 27));
					lineDirection.add(new Random().nextBoolean());
					obstacleLineType.add(1);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y + 23));
					lineDirection.add(new Random().nextBoolean());
					obstacleLineType.add(1);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y + 73));
					lineDirection.add(new Random().nextBoolean());
					obstacleLineType.add(1);
				}
				else{
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 77));
					lineDirection.add(new Random().nextBoolean());
					obstacleLineType.add(1);
					obstacleLines.add(new Point(build.terrainAXIS.get(i).x,build.terrainAXIS.get(i).y - 27));
					lineDirection.add(new Random().nextBoolean());
					obstacleLineType.add(1);
					do{
						lineDirection.set(lineDirection.size() - 1, new Random().nextBoolean());
						break;
					}while(lineDirection.get(lineDirection.size() - 1).booleanValue() == lineDirection.get(lineDirection.size() - 1).booleanValue());
					
				}
			}

		}
	}


	protected void generateObstacles(boolean gen) {
		
		if(gen && new Random().nextBoolean() && new Random().nextBoolean() && new Random().nextInt(200) < 50){
			
			for(int i = 0;i<obstacleLines.size();i++){
			  if(obstacleLineType.get(i) == LINE_ROAD){
				
				  if(lineDirection.get(i)){
						
						if(new Random().nextBoolean()){
							
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
						
							
							if(new Random().nextBoolean() && new Random().nextBoolean() && i % 6 ==0){
								
								obstacleImage.add(new Rendering().busImage_LEFT(new Random().nextInt(3)));
							    bigObstacle.add(true);
							    
							}
							else{
								
								obstacleImage.add(new Rendering().carImage_LEFT(new Random().nextInt(8)));
							    bigObstacle.add(false);
							    
							}
							}
						}
						
					}else{
						
						if(new Random().nextBoolean()){
							
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
							
							
							if(new Random().nextBoolean() && new Random().nextBoolean() && i % 6 ==0){
								
								obstacleImage.add(new Rendering().busImage_RIGHT(new Random().nextInt(3)));
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
							
							
							if(new Random().nextBoolean() && new Random().nextBoolean() && i % 6 ==0){
								
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
							
							
							if(new Random().nextBoolean() && new Random().nextBoolean() && i % 6 ==0){
								
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
				obstacle.set(i, new Point(obstacle.get(i).x + 3,obstacle.get(i).y));
			}
			else{
				obstacle.set(i, new Point(obstacle.get(i).x - 3,obstacle.get(i).y));
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
				   if(Cross.player.x - obstacle.get(i).x <= 200 && Cross.player.x - obstacle.get(i).x >= 1){
					   Cross.isOver = true;
				   }
				  }else{
					  if(Cross.player.x - obstacle.get(i).x >= -135 && Cross.player.x - obstacle.get(i).x<= 65){
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
}
