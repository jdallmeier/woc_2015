package view;


import processing.core.PGraphics;

import java.util.PriorityQueue;
import java.util.Set;
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
    private boolean alternativeKeymap = true;
    Player opponent;
    Position position;
    PriorityQueue<DelayedAction> delayedActions = new PriorityQueue<>();

    public Player(Position p, Gui gui) {
        this.position = p;
        this.gui = gui;
        if (position == Position.LEFT) {
            x = 10;
            y = 380;
            alternativeKeymap = false;
        } else {
            x = 990;
            y = 380;
        }
        this.speed = gui.speed;
        player_width = 10;
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
        while (delayedActions.peek() != null
                && delayedActions.peek().getExecuteAfter() > System.currentTimeMillis()) {
            delayedActions.poll().run();
        }
        g.rect(x, y, player_width, 30);

    }

    public void interact(Set<Integer> pressedKeys) {
        if (pressedKeys.contains(alternativeKeymap ? VK_LEFT : VK_A)) {
            moveLeft();
        } else if (pressedKeys.contains((alternativeKeymap ? VK_RIGHT : VK_D))) {
            moveRight();
        }
    }

    public void moveRight() {
        float xO = opponent.getX();
        if (x < xO) {
            float player_distance = xO - (x + player_width);
            if (player_distance > speed) {
                x = x + speed;
                playerWin();
            } else {
                x = x + player_distance;
                playerWin();
            }
        } else {
            x = x + speed;
            playerWin();
        }
    }

    public void moveLeft() {
        float xO = opponent.getX();
        if (x > xO) {
            float player_distance = xO - (x + player_width);
            if (player_distance > speed) {
                x = x - speed;
                playerWin();
            } else {
                x = x - player_distance;
                playerWin();
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
        delayedActions.add(new DelayedAction(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(2)) {
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
