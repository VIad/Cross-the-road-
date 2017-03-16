import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Rendering extends JPanel{
	  /**
	 * 
	 */
	private static final long serialVersionUID = -4867835391733715828L;

	  static TerrainBuilder  build = Cross.builder;
      
      private Toolkit        tk = Toolkit.getDefaultToolkit();
	  
	  private final Image    character_UP   = tk.getImage(getClass().getResource("Player/character_UP.gif"));
	  private final Image    character_DOWN   = tk.getImage(getClass().getResource("Player/character_DOWN.gif"));
	  private final Image    character_LEFT   = tk.getImage(getClass().getResource("Player/character_LEFT.gif"));
	  private final Image    character_RIGHT   = tk.getImage(getClass().getResource("Player/character_RIGHT.gif"));
	  
	  private final Image    character_UP_STAT = tk.getImage(getClass().getResource("Player/character_UP_STAT.gif"));
	  private final Image    character_DOWN_STAT = tk.getImage(getClass().getResource("Player/character_DOWN_STAT.gif"));
	  private final Image    character_LEFT_STAT = tk.getImage(getClass().getResource("Player/character_LEFT_STAT.gif"));
	  private final Image    character_RIGHT_STAT = tk.getImage(getClass().getResource("Player/character_RIGHT_STAT.gif"));
	
	  private final Image    boat_UP = tk.getImage(getClass().getResource("Player/Player_boat/Player_boat_UP.png"));
	  private final Image    boat_DOWN = tk.getImage(getClass().getResource("Player/Player_boat/Player_boat_DOWN.png"));
	  private final Image    boat_LEFT = tk.getImage(getClass().getResource("Player/Player_boat/Player_boat_LEFT.png"));
	  private final Image    boat_RIGHT = tk.getImage(getClass().getResource("Player/Player_boat/Player_boat_RIGHT.png"));
	  
	  private final Image    Train_RIGHT = tk.getImage(getClass().getResource("Obstacles/Train_obs/Train.png"));
	  private final Image    Train_LEFT = tk.getImage(getClass().getResource("Obstacles/Train_obs/Train_left.png"));
	  
      protected void paintComponent(Graphics g){
    	 super.paintComponent(g);
    	 //DRAW TERRAIN
    	 g.drawImage(build.Terrain_safe, build.Terrain_safe_start.x, build.Terrain_safe_start.y, null);
    	try{
         for(int i = 0; i < build.terrain.size();i++){
        	 if(build.terrain.get(i) == TerrainBuilder.ROAD_TERRAIN){
        		 if(build.bigTerrain.get(i)){
        			 g.drawImage(build.Terrain_road_big, build.terrainAXIS.get(i).x, build.terrainAXIS.get(i).y, null);
        			 g.drawImage(build.Terrain_road_big, build.terrainAXIS.get(i).x + 200, build.terrainAXIS.get(i).y, null);
        			 g.drawImage(build.Terrain_road_big, build.terrainAXIS.get(i).x + 400, build.terrainAXIS.get(i).y, null);
        			 g.drawImage(build.Terrain_road_big, build.terrainAXIS.get(i).x + 600, build.terrainAXIS.get(i).y, null);
        		 }
        		 else{
        			 g.drawImage(build.Terrain_road, build.terrainAXIS.get(i).x, build.terrainAXIS.get(i).y, null);
        			 g.drawImage(build.Terrain_road, build.terrainAXIS.get(i).x + 200, build.terrainAXIS.get(i).y, null);
        			 g.drawImage(build.Terrain_road, build.terrainAXIS.get(i).x + 400, build.terrainAXIS.get(i).y, null);
        			 g.drawImage(build.Terrain_road, build.terrainAXIS.get(i).x + 600, build.terrainAXIS.get(i).y, null);
        		 }
        	 }
        	 if(build.terrain.get(i) == TerrainBuilder.WATER_TERRAIN){
        		 if(build.bigTerrain.get(i)){
        			 g.drawImage(build.Terrain_water_big, build.terrainAXIS.get(i).x, build.terrainAXIS.get(i).y, null);
        		 }
        		 else{
        			 g.drawImage(build.Terrain_water, build.terrainAXIS.get(i).x, build.terrainAXIS.get(i).y, null);
        		 }
        	 }
        	 if(build.terrain.get(i) == TerrainBuilder.SAFE_TERRAIN){
        		 if(build.bigTerrain.get(i)){
        			 g.drawImage(build.Terrain_safe_big, build.terrainAXIS.get(i).x, build.terrainAXIS.get(i).y, null);
        		 }
        		 else{
        			 g.drawImage(build.Terrain_safe, build.terrainAXIS.get(i).x, build.terrainAXIS.get(i).y, null);
        		 }
        	 }
        	 if(build.terrain.get(i) == TerrainBuilder.TRAIN_TERRAIN){	 
        		 if(build.bigTerrain.get(i)){
        			 g.drawImage(build.Terrain_train, build.terrainAXIS.get(i).x, build.terrainAXIS.get(i).y, null);
        			 g.drawImage(build.Terrain_safe, build.terrainAXIS.get(i).x, build.terrainAXIS.get(i).y + 100, null);
        		 }
        		 else{
        			 g.drawImage(build.Terrain_train, build.terrainAXIS.get(i).x, build.terrainAXIS.get(i).y, null);
        		 }
        	 }
         }
    	}catch(Exception ex){}
         //DRAW CARS (OBSTACLES)
         try{
         for(int i = 0 ;i<Obstacles.obstacle.size();i++){
        	 if(Obstacles.direction.get(i)){
        		 g.drawImage(Obstacles.obstacleImage.get(i), Obstacles.obstacle.get(i).x, Obstacles.obstacle.get(i).y, null);
        	 }
        	 else{
        		 g.drawImage(Obstacles.obstacleImage.get(i), Obstacles.obstacle.get(i).x, Obstacles.obstacle.get(i).y, null);
        	 }
         }
         }catch(Exception ex){}
         //DRAW TRAIN
         try{
        	 for(int i = 0;i<Obstacles.trainObs.size();i++)
        	 {
        		 if(Obstacles.trainDir.get(i))
        		 {
        			g.drawImage(Train_RIGHT, Obstacles.trainObs.get(i).x, Obstacles.trainObs.get(i).y, null);
        			g.drawImage(Obstacles.trainImg.get(i ), Obstacles.trainObs.get(i).x - 225, Obstacles.trainObs.get(i).y, null);
        			g.drawImage(Obstacles.trainImg.get(i + 1), Obstacles.trainObs.get(i).x - 450, Obstacles.trainObs.get(i).y, null);
        			g.drawImage(Obstacles.trainImg.get(i + 2), Obstacles.trainObs.get(i).x - 675, Obstacles.trainObs.get(i).y, null);
        			g.drawImage(Obstacles.trainImg.get(i + 3), Obstacles.trainObs.get(i).x - 900, Obstacles.trainObs.get(i).y, null);
        		 }
        		 else
        		 {
        			 g.drawImage(Train_LEFT, Obstacles.trainObs.get(i).x, Obstacles.trainObs.get(i).y, null);
         			 g.drawImage(Obstacles.trainImg.get(i ), Obstacles.trainObs.get(i).x + 225, Obstacles.trainObs.get(i).y, null);
         			 g.drawImage(Obstacles.trainImg.get(i + 1), Obstacles.trainObs.get(i).x + 450, Obstacles.trainObs.get(i).y, null);
         			 g.drawImage(Obstacles.trainImg.get(i + 2), Obstacles.trainObs.get(i).x + 675, Obstacles.trainObs.get(i).y, null);
         			 g.drawImage(Obstacles.trainImg.get(i + 3), Obstacles.trainObs.get(i).x + 900, Obstacles.trainObs.get(i).y, null);
        		 }
        	 }
         }catch(Exception ex){}
                
    	 //DRAW PLAYER
    	 
    	 if(Cross.isReadyToLoadGraphics){
    		 if(Cross.isOnWater){
    			 if(Cross.PLAYER_DIRECTION == Cross.UP){
    				 g.drawImage(boat_UP, Cross.player.x, Cross.player.y - 25, null);
    	    		 g.drawImage(character_UP_STAT, Cross.player.x, Cross.player.y, null);
    			 }
    	    	 if(Cross.PLAYER_DIRECTION == Cross.DOWN){
    	    		 g.drawImage(boat_DOWN, Cross.player.x, Cross.player.y - 25, null);
    	    		 g.drawImage(character_DOWN_STAT, Cross.player.x, Cross.player.y, null);
    	    	 }
    	    	 if(Cross.PLAYER_DIRECTION == Cross.LEFT){
    	    		 g.drawImage(boat_LEFT, Cross.player.x - 25, Cross.player.y, null);
    	    		 g.drawImage(character_LEFT_STAT, Cross.player.x , Cross.player.y, null);
    	    	 }
    	    	 if(Cross.PLAYER_DIRECTION == Cross.RIGHT){
    	    		 g.drawImage(boat_RIGHT, Cross.player.x - 25, Cross.player.y, null);
    	    		 g.drawImage(character_RIGHT_STAT, Cross.player.x , Cross.player.y, null);
    	    	 }
    		 }else{
    	 if(!Cross.isSTAT && !Cross.isOver){
    	 if(Cross.PLAYER_DIRECTION == Cross.UP)
    		 g.drawImage(character_UP, Cross.player.x, Cross.player.y, null);
    	 if(Cross.PLAYER_DIRECTION == Cross.DOWN)
    		 g.drawImage(character_DOWN, Cross.player.x, Cross.player.y, null);
    	 if(Cross.PLAYER_DIRECTION == Cross.LEFT)
    		 g.drawImage(character_LEFT, Cross.player.x, Cross.player.y, null);
    	 if(Cross.PLAYER_DIRECTION == Cross.RIGHT)
    		 g.drawImage(character_RIGHT, Cross.player.x, Cross.player.y, null);
    	  }else if(Cross.isSTAT || Cross.isOver){
    		  if(Cross.PLAYER_DIRECTION == Cross.UP)
    	    		 g.drawImage(character_UP_STAT, Cross.player.x, Cross.player.y, null);
    	    	 if(Cross.PLAYER_DIRECTION == Cross.DOWN)
    	    		 g.drawImage(character_DOWN_STAT, Cross.player.x, Cross.player.y, null);
    	    	 if(Cross.PLAYER_DIRECTION == Cross.LEFT)
    	    		 g.drawImage(character_LEFT_STAT, Cross.player.x, Cross.player.y, null);
    	    	 if(Cross.PLAYER_DIRECTION == Cross.RIGHT)
    	    		 g.drawImage(character_RIGHT_STAT, Cross.player.x, Cross.player.y, null);
    	  }
    	 }
    	 }
    	 //STATUSES
    	 Graphics2D g2 = (Graphics2D) g;
    	  
    	 g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    	 g2.setColor(Color.BLACK);
    	 g2.setFont(new Font("Courier New",Font.BOLD,20));
    	 g2.drawString("Ðåçóëòàò : "+Cross.SCORE, 20, 840);
    	 g2.drawString("Òðóäíîñò : "+(Cross.thread_sleep_param == 3 ?"Ëåñíî" : "Òðóäíî"), 20, 870);
    	 g2.setColor(Color.RED);
    	 g2.setFont(new Font("Courier New",Font.BOLD,30));
    	 g2.drawString((Obstacles.checkForCloseTrain() ? "ÂËÀÊ" : ""), 400, 840);
    	 if(Cross.isOver){ 
    		 g2.setColor(Color.BLACK);
    		 g2.setFont(new Font("Courier New",Font.BOLD,50));
    		 g2.drawString("ÊÐÀÉ ÍÀ ÈÃÐÀÒÀ", 210, 450);
    		 g2.drawString("(R) Çà ðåñòàðò", 200, 500);
    	 }
    	 if(Cross.isPaused){
    		 g2.setColor(Color.BLUE);
    		 g2.setFont(new Font("Courier New",Font.BOLD,50));
    		 g2.drawString("ÏÀÓÇÀ", 210, 450);
    	 }
    	 this.repaint();
     }
     
     
     
     
      Image carImage_RIGHT(int index){
       	 Toolkit tool = Toolkit.getDefaultToolkit();
       	 Image [] images = {
       			 tool.getImage(getClass().getResource("Obstacles/Car_1.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_2.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_3.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_4.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_5.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_6.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_7.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_8.png")),
       	 };
       	 if(!(index > images.length - 1)){
       		 return images[index];
       	 }else
       	 return null;
        }
        Image carImage_LEFT(int index){
       	 Toolkit tool = Toolkit.getDefaultToolkit();
       	 Image[] images = {
       			 tool.getImage(getClass().getResource("Obstacles/Car_1_Right.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_2_Right.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_3_Right.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_4_Right.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_5_Right.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_6_Right.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_7_Right.png")),
       			 tool.getImage(getClass().getResource("Obstacles/Car_8_Right.png")),
       	 }; 
       	 if(!(index > images.length - 1)){
       		 return images[index];
       	 }else
       		 return null;
       	 
        }
        Image busImage_LEFT(int index){
          	 Toolkit tool = Toolkit.getDefaultToolkit();
          	 Image[] images = {
          			tool.getImage(getClass().getResource("Obstacles/Bus_1_Right.png")),
          			tool.getImage(getClass().getResource("Obstacles/Bus_2_Right.png")),
          			tool.getImage(getClass().getResource("Obstacles/Bus_3_Right.png")),
          	 }; 
          	 if(!(index > images.length - 1)){
          		 return images[index];
          	 }else
          		 return null;
          	 
           }
        Image busImage_RIGHT(int index){
         	 Toolkit tool = Toolkit.getDefaultToolkit();
         	 Image[] images = {
         			tool.getImage(getClass().getResource("Obstacles/Bus_1.png")),
         			tool.getImage(getClass().getResource("Obstacles/Bus_2.png")),
         			tool.getImage(getClass().getResource("Obstacles/Bus_3.png")),
         			
         	 }; 
         	 if(!(index > images.length - 1)){
         		 return images[index];
         	 }else
         		 return null;
         	 
          }
        Image waterImage(int index){
        	Toolkit tool = Toolkit.getDefaultToolkit();
        	Image[] images={
        			tool.getImage(getClass().getResource("Obstacles/Water_obs/Plat_1.png")),
        			tool.getImage(getClass().getResource("Obstacles/Water_obs/Plat_2.png")),
        			tool.getImage(getClass().getResource("Obstacles/Water_obs/Plat_3.png")),
        			tool.getImage(getClass().getResource("Obstacles/Water_obs/Plat_4.png")),
        	};
        	if(!(index > images.length - 1)){
        		 return images[index];
        	 }else
        		 return null;
        }
        Image waterImage_BIG(int index){
        	Toolkit tool = Toolkit.getDefaultToolkit();
        	Image[] images = {
        	        tool.getImage(getClass().getResource("Obstacles/Water_obs/Plat_1_Big.png")),
        	};
			if(!(index > images .length - 1)){
       		 return images[index];
       	 }else
       		 return null;
        }
        Image trainImage(int index){
        	Toolkit tool = Toolkit.getDefaultToolkit();
        	Image[] images = {
        			tool.getImage(getClass().getResource("Obstacles/Train_obs/Train_wagon_1.png")),
        			tool.getImage(getClass().getResource("Obstacles/Train_obs/Train_wagon_2.png")),
        			tool.getImage(getClass().getResource("Obstacles/Train_obs/Train_wagon_3.png")),
        			tool.getImage(getClass().getResource("Obstacles/Train_obs/Train_wagon_4.png")),
        	};
        	if(!(index > images.length - 1)){
        		return images[index];
        	}else
        		return null;
        }
        
}
