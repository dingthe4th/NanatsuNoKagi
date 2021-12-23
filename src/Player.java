import acm.graphics.GImage;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;

public class Player extends Character{
    private final static int HEIGHT = 40;
    private final static int WIDTH = 20;
    public int keys = 0;
    public Player(int x,int y){
        this.dx=5;
        this.dy=5;
        this.direction="SOUTH";
        GImage sprite = new GImage("assets/player"+this.direction+".png");
        sprite.setSize(WIDTH,HEIGHT);
        add(sprite,0,0);
        this.width = WIDTH;
        this.height = HEIGHT;
        this.type="PLAYER";
    }
    public void faceNORTH(){
        this.removeAll();
        this.direction="NORTH";
        GImage sprite = new GImage("assets/player"+this.direction+".png");
        sprite.setSize(WIDTH,HEIGHT);
        add(sprite);

    }
    public void faceEAST(){
        this.removeAll();
        this.direction="EAST";
        GImage sprite = new GImage("assets/player"+this.direction+".png");
        sprite.setSize(WIDTH,HEIGHT);
        add(sprite,0,0);

    }
    public void faceWEST(){
        this.removeAll();
        this.direction="WEST";
        GImage sprite = new GImage("assets/player"+this.direction+".png");
        sprite.setSize(WIDTH,HEIGHT);
        add(sprite,0,0);

    }
    public void faceSOUTH(){
        this.removeAll();
        this.direction="SOUTH";
        GImage sprite = new GImage("assets/player"+this.direction+".png");
        sprite.setSize(WIDTH,HEIGHT);
        add(sprite,0,0);
    }

    public void setKeyToZero(){
        this.keys=0;
    }

    public  void addKey(){
        this.keys++;
    }

    public boolean collideWithType(Level level,String type,boolean remove){
        double pointx = 0, pointy = 0;
        int SPACE = 3;
        GameObject object;

        pointx = this.x + this.width / 2;
        pointy = this.y - SPACE;
        object=level.getElementAt(pointx,pointy);
        if (object != null){
            if(object.type.equals(type)){
                if(type.equals("DOOR")){
                    Door door = level.getElementAt(pointx,pointy);
                    if(door.requiredKeys>this.keys){
                        return false;
                    }
                }
                if(type.equals("END"))
                {
                    End end = level.getElementAt(pointx,pointy);
                    if(end.requiredKeys>this.keys)
                    {
                        return false;
                    }
                }
                if(remove)
                    level.remove(object);
                return true;
            }
        }
        pointx = this.x + this.width / 2;
        pointy = this.y + this.height+SPACE;
        object=level.getElementAt(pointx,pointy);
        if (object != null){
            if(object.type.equals(type)){
                if(type.equals("DOOR")){
                    Door door = level.getElementAt(pointx,pointy);
                    if(door.requiredKeys>this.keys){
                        return false;
                    }
                }
                if(type.equals("END"))
                {
                    End end = level.getElementAt(pointx,pointy);
                    if(end.requiredKeys>this.keys)
                    {
                        return false;
                    }
                }
                if(remove)
                    level.remove(object);
                return true;
            }
        }

        pointx = this.x + this.width + SPACE;
        pointy = this.y + this.height / 2;
        object=level.getElementAt(pointx,pointy);
        if (object != null){
            if(object.type.equals(type)){
                if(type.equals("DOOR")){
                    Door door = level.getElementAt(pointx,pointy);
                    if(door.requiredKeys>this.keys){
                        return false;
                    }
                }
                if(type.equals("END"))
                {
                    End end = level.getElementAt(pointx,pointy);
                    if(end.requiredKeys>this.keys)
                    {
                        return false;
                    }
                }
                if(remove)
                    level.remove(object);
                return true;
            }
        }

        pointx = this.x - SPACE;
        pointy = this.y + this.height/ 2;
        object=level.getElementAt(pointx,pointy);
        if (object != null){
            if(object.type.equals(type)){
                if(type.equals("DOOR")){
                    Door door = level.getElementAt(pointx,pointy);
                    if(door.requiredKeys>this.keys){
                        return false;
                    }
                }
                if(type.equals("END"))
                {
                    End end = level.getElementAt(pointx,pointy);
                    if(end.requiredKeys>this.keys)
                    {
                        return false;
                    }
                }
                if(remove)
                    level.remove(object);
                return true;
            }
        }
        return false;
    }


}
