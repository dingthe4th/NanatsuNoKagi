import acm.graphics.GObject;

public class Character extends GameObject {
    private static final double SPACE = 1;
    int dx;
    int dy;
    public void move(Level level) {
        switch (this.direction) {
            case "NORTH":
                if (canMove(level)) {
                    this.move(0, -dy);
                    y = y - dy;
                }
                break;
            case "SOUTH":
                if (canMove(level)) {
                    this.move(0, dy);
                    this.y = this.y + this.dy;
                }
                break;
            case "EAST":
                if (canMove(level)) {
                    this.move(dx, 0);
                    x = x + dx;
                }
                break;
            case "WEST":
                if (canMove(level)) {
                    this.move(-dx, 0);
                    x = x - dx;
                }
                break;
        }
    }

    public boolean canMove(Level level) {
        double pointx = 0, pointy = 0;
        String dir = this.direction;
        switch (dir) {
            case "NORTH":
                pointx = this.x + this.width / 2;
                pointy = this.y - SPACE;
                break;
            case "SOUTH":
                pointx = this.x + this.width / 2;
                pointy = this.y + this.height+SPACE;
                break;
            case "EAST":
                pointx = this.x + this.width + SPACE;
                pointy = this.y + this.height / 2;
                break;
            case "WEST":
                pointx = this.x - SPACE;
                pointy = this.y + this.height/ 2;
                break;
        }
        GObject ob = level.getElementAt(pointx,pointy);
        if (!(ob instanceof GameObject)) {
            return true;
        }
        GameObject object=level.getElementAt(pointx,pointy);
        if (object != null){
            if(object.type.equals("PLAYER")){
                return true;
            }
            if(object.type.equals("WALL")){
                Wall wall = level.getElementAt(pointx,pointy);
                if(wall.isColliding(pointx,pointy))
                    return false;
                else
                    return true;
            }
            if(object.type.equals("DOOR")){
                Door door = level.getElementAt(pointx,pointy);
                if(door.isColliding(pointx,pointy))
                    return false;
                else
                    return true;
            }
            if(object.type.equals("KEY")) { return true;}

            if(object.type.equals("ENEMY")){
                Enemy enemy = level.getElementAt(pointx,pointy);
                switch(enemy.getType()){
                    case "HUNTER":
                        if(enemy.isColliding(pointx,pointy))
                            return false;
                        else
                            return true;
                    case "VERTICAL":
                            return true;
                    case "HORIZONTAL":
                            return true;
                }
            }
        }
        return true;
    }
}

