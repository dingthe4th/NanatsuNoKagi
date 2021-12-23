import acm.graphics.GRect;
public class End extends GameObject{
    private static final int WIDTH = 40;
    private static final int HEIGHT = 20;
    public int requiredKeys;
    public End(int x, int y, int requiredKeys){
        this.x=x;
        this.y=y;
        this.type="END";
        GRect endPOint = new GRect(WIDTH,HEIGHT);
        this.width = WIDTH;
        this.height = HEIGHT;
        endPOint.setVisible(false);
        add(endPOint,0,0);
        this.requiredKeys = requiredKeys;
    }
}
