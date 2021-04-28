import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player)
                player = handler.object.get(i);
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 16, 16);
    }

    public void tick(){
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt( Math.pow(x - player.getX(), 2) + Math.pow(x - player.getY(), 2));

        velX = (int) Math.round((-1.0 / distance) * diffX);
        velY = (int) Math.round((-1.0 / distance) * diffY);

        if(y <= 0 || y >= Game.HEIGHT - 32){
            velY *= -1;
        }

        if(x <= 0 || x >= Game.WIDTH - 16){
            velX *= -1;
        }

        handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.02f, handler));
    }

    public void render(Graphics g){ 
        g.setColor(Color.green);
        g.fillRect(x, y, 16, 16);
    }

}
