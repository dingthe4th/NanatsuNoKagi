import acm.graphics.GImage;

import java.util.zip.GZIPInputStream;

public class Key extends GameObject {
    private static int HEIGHT = 30;
    private static int WIDTH = 30;
    public Key(int x,int y){
        this.x=x;
        this.y=y;
        this.direction="SOUTH";
        this.type="KEY";
        GImage img = new GImage("assets/key.png");
        img.setSize(WIDTH,HEIGHT);
        this.width=WIDTH;
        this.height=HEIGHT;
        add(img,0,0);
    }
}
