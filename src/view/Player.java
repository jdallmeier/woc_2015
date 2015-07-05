package view;


import processing.core.PGraphics;

import java.util.concurrent.TimeUnit;

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

    public Player(Position p, Gui gui) {
        this.position = p;
        this.gui = gui;
        if (position == Position.LEFT) {
            x = 10;
            y = 350;
        } else {
            x = 990;
            y = 350;
        }
        this.speed = gui.speed;
        player_width = 10;
        player_height = 30;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void draw(PGraphics g) {
        g.rect(x, y, player_width, player_height);

    }

    public void interact() {
        if (gui.isKeyPressed(position == Position.LEFT ? VK_A : VK_LEFT)) {
            moveLeft();
        } else if (gui.isKeyPressed((position == Position.LEFT ? VK_D : VK_RIGHT))) {
            moveRight();
        }
        if (gui.isKeyPressed((position == Position.LEFT ? VK_W : VK_UP))) {
            jump();
            gui.blockedKeys.put((position == Position.LEFT ? VK_W : VK_UP), System.currentTimeMillis() + 500);
        }
    }

    public void moveRight() {
        if(y == opponent.getY()){
            float xO = opponent.getX();
            float player_distance;
            if(x<xO) {
                player_distance = xO - (x + player_width);
            } else {
                player_distance = x - (xO + player_width);
            }
            if (player_distance > speed) {
                x = x + speed;
                playerWin();
            } else {
                if(x>xO){
                    x = x + speed;
                    playerWin();
                }
                else{
                    x = x + player_distance;
                    playerWin();
                }
            }
        } else {
            x = x + speed;
            playerWin();
        }
    }

    public void moveLeft() {
        if(y == opponent.getY()) {
            float xO = opponent.getX();
            float player_distance;
            if(x<xO) {
                player_distance = xO - (x + player_width);
            } else {
                player_distance = x - (xO + player_width);
            }
            if (player_distance > speed) {
                x = x - speed;
                playerWin();
            } else {
                if(x<xO){
                    x = x - speed;
                    playerWin();
                }
                else{
                    x = x - player_distance;
                    playerWin();
                }
            }
        } else {
            x = x - speed;
            playerWin();
        }
    }

    public void playerWin() {
        if (position == Position.LEFT) {
            if (x >= 990) {
                //PlayerLeft wins
            }
        } else {
            if (x <= 10) {
                //PlayerRight wins
            }
        }
    }

    public void jump() {
        y = y - 40;
        gui.delayedActions.add(new DelayedAction(System.currentTimeMillis() +200) {
            @Override
            public void run() {
                jumpDown();
            }
        });
    }

    public void jumpDown() {
        y = y + 40;
    }
}
