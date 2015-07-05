package view;

import processing.core.PGraphics;
import static java.awt.event.KeyEvent.*;

/**
 * Created by Jonas on 03.07.2015.
 */
public class Player {
    final Gui gui;

    float x;
    float y;
    float speed;
    float player_width;
    float player_height;
    Player opponent;
    Position position;
    boolean onTop;
    boolean bulletActive;
    Bullet bullet;



    int WinCount=0;

    public Player(Position p, Gui gui) {
        this.position = p;
        this.gui = gui;
        init();
        this.speed = gui.speed;
        player_width = 10;
        player_height = 30;
        bulletActive = false;
    }

    public void init() {
        if (position == Position.LEFT) {
            x = 30;
            y = gui.height-60;
        } else {
            x = gui.width - (30+player_width);
            y = gui.height-60;
        }
        this.speed = gui.speed;
        player_width = 10;
        player_height = 30;
        onTop = false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWinCount() {
        return WinCount;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void draw(PGraphics g) {
        if(playerDistance() > 0 && onTop) {
            y = y + player_height;
            if (position == Position.LEFT){
                g.fill(255,0,0);
            }else{
                g.fill(0,0,255);
            }
            g.rect(x, y, player_width, player_height);
            onTop = false;
        }  else {
            if (position == Position.LEFT){
                g.fill(255, 0, 0);
            }else{
                g.fill(0,0,255);
            }
            g.rect(x, y, player_width, player_height);
        }

        if(bulletActive){
            if(!bullet.collision(opponent)) {
                bullet.move();
                float bulletX = bullet.getX();
                float bulletY = bullet.getY();
                g.ellipse(bulletX, bulletY, 15, 15);
            } else {
                opponent.block(2500);
                bulletActive = false;
            }
        }

    }

    public float getPlayer_width() {
        return player_width;
    }

    public void block(int blockTime){
        gui.blockedKeys.put((position == Position.LEFT ? VK_W : VK_UP), System.currentTimeMillis() + blockTime);
        gui.blockedKeys.put((position == Position.LEFT ? VK_D : VK_RIGHT), System.currentTimeMillis() + blockTime);
        gui.blockedKeys.put((position == Position.LEFT ? VK_S : VK_DOWN), System.currentTimeMillis() + blockTime);
        gui.blockedKeys.put((position == Position.LEFT ? VK_A : VK_LEFT), System.currentTimeMillis() + blockTime);

    }

    public float getPlayer_height() {
        return player_height;
    }

    public void interact() {
        //Directional keys
        if (gui.isKeyPressed(position == Position.LEFT ? VK_A : VK_LEFT)) {
            moveLeft();
        } else if (gui.isKeyPressed((position == Position.LEFT ? VK_D : VK_RIGHT))) {
            moveRight();
        }

        //Jump key
        if (gui.isKeyPressed((position == Position.LEFT ? VK_W : VK_UP))) {
            jump();
            gui.blockedKeys.put((position == Position.LEFT ? VK_W : VK_UP), System.currentTimeMillis() + 500);
        }
        //Shooting key
        if (gui.isKeyPressed((position == Position.LEFT ? VK_S : VK_DOWN))){
            shoot();
            gui.blockedKeys.put((position == Position.LEFT ? VK_S : VK_DOWN), System.currentTimeMillis() + 3500);
        }
    }

    public void shoot(){
        bullet = new Bullet(x,y+(player_height/2),position,10);
        bulletActive = true;
    }

    public float playerDistance(){
        float xO = opponent.getX();
        if((x+player_width)<=xO) {
            return xO - (x + player_width);
        } else {
            return x - (xO + player_width);
        }
    }

    public void moveRight() {
        if(position == Position.RIGHT && (x+speed)>(gui.width-player_width*2)) {

        } else{
            if (y == opponent.getY()) {
                float xO = opponent.getX();
                float player_distance = playerDistance();

                if (player_distance > speed) {
                    x = x + speed;
                    playerWin();
                } else {
                    if (x > xO) {
                        x = x + speed;
                        playerWin();
                    } else {
                        x = x + player_distance;
                        playerWin();
                    }
                }
            } else {
                x = x + speed;
                playerWin();
            }
        }
    }

    public void moveLeft() {
        if(position == Position.LEFT && (x-speed)<player_width) {

        } else {
            if (y == opponent.getY()) {
                float xO = opponent.getX();
                float player_distance;
                if (x < xO) {
                    player_distance = xO - (x + player_width);
                } else {
                    player_distance = x - (xO + player_width);
                }
                if (player_distance > speed) {
                    x = x - speed;
                    playerWin();
                } else {
                    if (x < xO) {
                        x = x - speed;
                        playerWin();
                    } else {
                        x = x - player_distance;
                        playerWin();
                    }
                }
            } else {
                x = x - speed;
                playerWin();
            }
        }
    }

    public void playerWin() {
        if (position == Position.LEFT) {
            if (x >= 990) {
                gui.blockAllKeys();
                gui.showMenu('l');
            }
        } else {
            if (x <= 10) {
                gui.blockAllKeys();
                gui.showMenu('r');
            }
        }
    }

    public void jump() {
        if(!onTop) {
            y = y - 40;
            gui.delayedActions.add(new DelayedAction(System.currentTimeMillis() + 200) {
                @Override
                public void run() {
                    jumpDown();
                }
            });
        }
    }

    public void jumpDown() {
        if(!onTop){
            if((playerDistance()+player_width) < player_width){
                y = y + 10;
                onTop = true;
            } else {
                y = y +40;
            }
        }
    }
}
