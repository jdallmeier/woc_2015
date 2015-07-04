package model;


import processing.core.PGraphics;
import java.util.Set;
import static model.Position.*;
import static java.awt.event.KeyEvent.*;
/**
 * Created by Jonas on 03.07.2015.
 */
public class Player {
    float x;
    float y;
    float speed;
    float player_width;
    private boolean alternativeKeymap;
    Player opponent;
    Position position;

    public Player(Position p, float speed, boolean alternativeKeymap){
        this.position = p;
        if(position == LEFT) {
            x = 10;
            y = 380;
        } else{
            x = 990;
            y = 380;
        }
        this.speed = speed;
        this.alternativeKeymap = alternativeKeymap;
        player_width = 10;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void draw(PGraphics g) {
        g.rect(x,y,player_width,30);
    }

    public void interact(Set<Integer> pressedKeys) {
        if(pressedKeys.contains(alternativeKeymap ? VK_LEFT : VK_A)) {
            moveLeft();
        }else if(pressedKeys.contains((alternativeKeymap ? VK_RIGHT : VK_D))){
            moveRight();
        }
    }

    public void moveRight() {
        float xO = opponent.getX();
        if(x<xO) {
            float player_distance = xO - (x + player_width);
            if (player_distance > speed) {
                x = x + speed;
                playerWin();
            } else {
                x = x + player_distance;
                playerWin();
            }
        }
        else{
            x = x + speed;
            playerWin();
        }
    }

    public void moveLeft() {
        float xO = opponent.getX();
        if(x>xO) {
            float player_distance = xO - (x + player_width);
            if (player_distance > speed) {
                x = x - speed;
                playerWin();
            } else {
                x = x + player_distance;
                playerWin();
            }
        }
        else{
            x = x + speed;
            playerWin();
        }


        if((x-speed-player_width)<xO) {
            x = x - speed;
        }
    }

    public void playerWin(){

    }
}
