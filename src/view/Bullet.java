package view;

/**
 * Created by Jonas on 05.07.2015.
 */


public class Bullet {
    float x;
    float y;
    float speed;
    Position position;
    public Bullet(float x_coordinate, float y_coordinate, Position position, float speed){
        x = x_coordinate;
        y = y_coordinate;
        this.speed = speed;
        this.position = position;
    }

    public void move(){
        if(position == Position.LEFT){
            x = x+speed;
        } else {
            x = x-speed;
        }
    }

    public boolean collision(Player player){
        float playerX = player.getX();
        float playerY = player.getY();
        float playerHeight = player.getPlayer_height();
        float playerWidth = player.getPlayer_width();
        if(position == Position.LEFT) {
            if (y > (playerY - 15) && y < (playerY + playerHeight + 15)) {
                if (x > (playerX - 15) && x < (playerX + playerWidth + 15)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (y > (playerY - 15) && y < (playerY + playerHeight + 15)) {
                if (x < (playerX + playerWidth + 15) && x > (playerX - 15)){
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
