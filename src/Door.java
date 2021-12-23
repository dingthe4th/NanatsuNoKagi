import acm.graphics.GImage;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;

public class Door  extends GameObject{
    private static final int HEIGHT = 60;
    private static final int WIDTH = 40;
    private static final int COLLISION_OFFSET=20;
    public int requiredKeys = 7;
    public Door(int x, int y, int requiredKeys){
        this.x=x;
        this.y=y;
        this.type="DOOR";
        GImage img = new GImage("assets/Door.png");
        img.setSize(WIDTH,HEIGHT);
        add(img);
        this.requiredKeys=requiredKeys;
    }
    public Door(int x, int y){
        this.x=x;
        this.y=y;
        this.type="DOOR";
        GImage img = new GImage("assets/Door.png");
        img.setSize(WIDTH,HEIGHT);
        add(img);
        this.requiredKeys=7;
    }

    public boolean isColliding(double rx, double ry){
            if(ry>this.y+COLLISION_OFFSET)
                return false;
        return true;
    }

}
