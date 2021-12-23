import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRect;

import java.awt.*;
import java.util.GregorianCalendar;

public class Wall extends GameObject {
    private static final int WALL_WIDTH = 10;
    private static final int WALL_HEIGHT = 60;
    private static final int WALLSECTION_LENGTH = 40;
    private static final int COLLISION_OFFSET=20;

    public Wall(int x, int y, int length, String direction){
        this.x=x;
        this.y=y;
        this.direction=direction;
        this.type="WALL";
        if(direction.equals("NORTH")||direction.equals("SOUTH")){
            this.height = WALL_HEIGHT;
            this.width=length*WALLSECTION_LENGTH;
            int mod = 0;
            for(int i = 0;i<length;i++){
                mod = i%2+1;
                GImage img = new GImage("assets//wall1.png");
                img.setSize(this.WALLSECTION_LENGTH,this.WALL_HEIGHT);
                add(img,i*this.WALLSECTION_LENGTH,0);
            }
        }
        else{
            this.height=length*WALLSECTION_LENGTH;
            this.width=WALL_WIDTH;
            GRect rect = new GRect(this.width,this.height);
            rect.setFillColor(new Color(135, 89, 51));
            rect.setColor(new Color(135, 89, 51));
            rect.setFilled(true);
            add(rect,0,0);
        }



    }
    public boolean isColliding(double rx, double ry){
        if(this.direction.equals("NORTH")||this.direction.equals("SOUTH")){
            if(ry>this.y+COLLISION_OFFSET)
                return false;
        }
            return true;
    }
}
