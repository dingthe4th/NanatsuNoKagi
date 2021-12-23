import acm.graphics.GImage;
public class Enemy extends Character {
    private final static int HEIGHT = 40;
    private final static int WIDTH = 20;
    private static final int COLLISION_OFFSET=20;
    public String enemyType;
    public int cycle=10;
    public Enemy(int x,int y,int dx,int dy,String enemyType){
        this.enemyType = enemyType;
        this.type="ENEMY";
        this.dx=dx;
        this.dy=dy;
        this.x=x;
        this.y=y;
        this.height=HEIGHT;
        this.width=WIDTH;
        GImage sprite = new GImage("assets/enemy.png");
        sprite.setSize(WIDTH,HEIGHT);
        add(sprite,0,0);
        this.width = WIDTH;
        this.height = HEIGHT;
        if(enemyType.equals("HUNTER")) this.direction="SOUTH";
        if(enemyType.equals("VERTICAL")) this.direction="NORTH";
        if(enemyType.equals("HORIZONTAL")) this.direction="EAST";
    }

    public String getType(){
        return enemyType;
    }

    public void updateEnemy(Player player,Level level){
        switch (enemyType)
        {
            case "HUNTER":
                if(Math.abs(this.x-player.x)>Math.abs(this.y-player.y)){
                    //check if closer in x axis
                    if(this.x>player.x)
                        this.direction="WEST";
                    else
                        this.direction="EAST";
                }
                else{
                    if(this.y>player.y)
                        this.direction="NORTH";
                    else
                        this.direction="SOUTH";
                }
                break;
            case "VERTICAL":
                if(isCollideWithType(level,"WALL")||isCollideWithType(level,"DOOR")||isCollideWithType(level,"ENEMY")
                    || isCollideWithType(level,"KEY"))
                {
                    if(this.direction.equals("NORTH")) this.direction = "SOUTH";
                    else this.direction = "NORTH";
                }
                break;
            case "HORIZONTAL":
                if(isCollideWithType(level,"WALL")||isCollideWithType(level,"DOOR")||isCollideWithType(level,"ENEMY")
                        || isCollideWithType(level,"KEY"))
                {
                    if(this.direction.equals("EAST")) this.direction = "WEST";
                    else this.direction = "EAST";
                }
                break;
        }
        this.move(level);
        cycle++;
    }

    public boolean isCollideWithType(Level level,String type){
        double pointx = 0, pointy = 0;
        int SPACE = 3;
        GameObject object;

        pointx = this.x + this.width / 2;
        pointy = this.y - SPACE;
        object=level.getElementAt(pointx,pointy);
        if (object != null){
            if(object.type.equals(type)){
                if(type.equals("DOOR")){
                    return true;
                }
                if(type.equals("WALL"))
                    return true;
                if(type.equals("ENEMY"))
                    return true;
                if(type.equals("KEY"))
                    return true;
            }
        }
        pointx = this.x + this.width / 2;
        pointy = this.y + this.height+SPACE;
        object=level.getElementAt(pointx,pointy);
        if (object != null){
            if(object.type.equals(type)){
                if(type.equals("DOOR")){
                    return true;
                }
                if(type.equals("WALL")){
                    return true;
                }
                if(type.equals("ENEMY"))
                    return true;
                if(type.equals("KEY"))
                    return true;
            }
        }

        pointx = this.x + this.width + SPACE;
        pointy = this.y + this.height / 2;
        object=level.getElementAt(pointx,pointy);
        if (object != null){
            if(object.type.equals(type)){
                if(type.equals("DOOR")){
                    return true;}
                }
                if(type.equals("WALL"))
                {return true;}
                if(type.equals("ENEMY"))
                    return true;
                if(type.equals("KEY"))
                    return true;
            }

        pointx = this.x - SPACE;
        pointy = this.y + this.height/ 2;
        object=level.getElementAt(pointx,pointy);
        if (object != null){
            if(object.type.equals(type)){
                if(type.equals("DOOR")){
                    return true;
                }
                if(type.equals("WALL")){
                    return true;
                }
                if(type.equals("ENEMY"))
                    return true;
                if(type.equals("KEY"))
                    return true;
            }
        }
        return false;
    }

    public boolean isColliding(double rx, double ry){
        if(this.direction.equals("NORTH") || this.direction.equals("SOUTH")){
            if(ry>this.y+COLLISION_OFFSET)
                return false;
        }
        if (this.direction.equals("EAST") || this.direction.equals("WEST")){
            if(rx>this.x+COLLISION_OFFSET)
                return false;
        }
        return true;
    }
}
