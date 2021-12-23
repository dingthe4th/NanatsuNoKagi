import acm.graphics.GCompound;
import acm.graphics.GRect;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.Inet4Address;
import java.util.Scanner;

public class Level extends GCompound {
    public Enemy[] enemies;
    public Wall[] walls;
    public Door[] doors;
    public Key[] keys;
    public End end;
    public GRect leftShadow;
    public GRect rightShadow;
    public GRect downShadow;
    public GRect upShadow;
    public int fov = 50;
    public int SHADOWSIZE = 1000;
    public int end_x, end_y;

//    public void init0(Player player){
//         Wall wall1 = new Wall(10,100,5,"SOUTH");
//         Wall wall2=new Wall(300,100,3,"EAST");
//         Key key = new Key(500,500);
//         Door door = new Door(100,100,1);
//         enemy = new Enemy(100,400,1,1,"HUNTER");
//         add(enemy,enemy.x,enemy.y);
//         add(wall1,wall1.x,wall1.y);
//         add(wall2,wall2.x,wall2.y);
//         add(key,key.x,key.y);
//         add(door,door.x,door.y);
//         add(player,player.x,player.y);
//    }
//

    public void reset(){
        removeAll();
    }
    public void loadLevel(int lev,Main main,Player player){
        double width, height;
        int numWalls = 0,numDoors= 0,numEnemies= 0,numKeys= 0;
        try {
            File file = new File("assets/level"+lev+".txt");
            Scanner sc = new Scanner(file);
            sc.useDelimiter(" |\\n");
            //get width and height
            width =Double.parseDouble(sc.next().trim());
            height = Double.parseDouble(sc.next().trim());
            numWalls= Integer.parseInt(sc.next().trim());
            walls = new Wall[numWalls];
            for(int i = 0;i<numWalls;i++){
                int wx = Integer.parseInt(sc.next().trim());
                int wy = Integer.parseInt(sc.next().trim());
                int wl = Integer.parseInt(sc.next().trim());
                String dir = sc.next().trim();
                System.out.println(dir);
                walls[i]=new Wall(wx,wy,wl,dir);
                add(walls[i],walls[i].x,walls[i].y);
            }
            numDoors = Integer.parseInt(sc.next().trim());
            doors=new Door[numDoors];
            for(int i=0;i<numDoors;i++){
                int x = Integer.parseInt(sc.next().trim());
                int y = Integer.parseInt(sc.next().trim());
                int req = Integer.parseInt(sc.next().trim());
                doors[i]=new Door(x,y,req);
                add(doors[i],doors[i].x,doors[i].y);
                end_x = x;
                end_y = y;
            }
            int req = Integer.parseInt(sc.next().trim());
            end = new End(end_x,end_y,req);
            add(end,end_x,end_y);
            numKeys = Integer.parseInt(sc.next().trim());
            keys = new Key[numKeys];
            for(int i=0;i<numKeys;i++){
                int x = Integer.parseInt(sc.next().trim());
                int y = Integer.parseInt(sc.next().trim());
                keys[i] = new Key(x,y);
                add(keys[i],x,y);
            }
            numEnemies = Integer.parseInt(sc.next().trim());
            enemies = new Enemy[numEnemies];
            for(int i=0;i<numEnemies;i++){
                int x = Integer.parseInt(sc.next().trim());
                int y = Integer.parseInt(sc.next().trim());
                int dx = Integer.parseInt(sc.next().trim());
                int dy = Integer.parseInt(sc.next().trim());
                String type = sc.next().trim();
                enemies[i]=new Enemy(x,y,dx,dy,type);
                add(enemies[i],x,y);
            }
            int xpos = Integer.parseInt(sc.next().trim());
            int ypos = Integer.parseInt(sc.next().trim());
            player.x=xpos;
            player.y=ypos;
            add(player,xpos,ypos);
            upShadow = new GRect(1000,1000);
            downShadow = new GRect(1000,1000);
            leftShadow = new GRect(1000,1000);
            rightShadow = new GRect(1000,1000);
            upShadow.setFillColor(Color.black);
            downShadow.setFillColor(Color.black);
            leftShadow.setFillColor(Color.black);
            rightShadow.setFillColor(Color.black);
            upShadow.setFilled(true);
            downShadow.setFilled(true);
            leftShadow.setFilled(true);
            rightShadow.setFilled(true);
            // Comment the next four lines to remove fog
            main.add(upShadow,0,-1000-fov);
            main.add(leftShadow,-1000-fov+player.x,0);
            main.add(rightShadow,player.x+fov+player.width,0);
            main.add(downShadow,0,ypos+fov+player.height);
            }
        catch (FileNotFoundException e) {
                e.printStackTrace();
        }
    }

    public void updateEnemy(Player player){
        for(int i=0;i<enemies.length;i++) {
            enemies[i].updateEnemy(player,this);
        }
    }
    public void updatePlayer(Player player){
        player.collideWithType(this,"DOOR",true);
        upShadow.setLocation(0,-1000-fov+player.y);
        leftShadow.setLocation(-1000-fov+player.x,0);
        rightShadow.setLocation(player.x+fov+player.width,0);
        downShadow.setLocation(0,player.y+fov+player.height);
    }
}