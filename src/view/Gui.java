package view;

import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.awt.event.KeyEvent.*;

/**
 * Created by Jonas on 03.07.2015.
 */
public class Gui extends PApplet {
    //color c = color(0);
    float x = 10;
    float y = 360;
    float x2 = 990;
    float y2 = 360;
    float speed = 5;
    Set<Integer> keys = new HashSet<>();
    Map<Integer, Long> blockedKeys = new HashMap<>();

    PriorityQueue<DelayedAction> delayedActions = new PriorityQueue<>();

    public void setup() {
        size(1000, 400);
    }

    public void draw() {
        background(255);
        display();
        while (delayedActions.peek() != null
                && delayedActions.peek().getExecuteAfter() > System.currentTimeMillis()) {
            delayedActions.poll().run();
        }
        if (keys.contains(VK_W)) {
            jump();
            blockedKeys.put(VK_W, System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(100));
        }
        if (keys.contains(VK_A)) {
            moveLeft();
        } else if (keys.contains(VK_D)) {
            moveRight();
        }
        if (keys.contains(VK_LEFT)) {
            movePlayerTwoLeft();
        } else if (keys.contains(VK_RIGHT)) {
            movePlayerTwoRight();
        }
        if (keys.contains(VK_UP)) {
            jumpPlayerTwo();
            blockedKeys.put(VK_UP, System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(100));
        }


    }


    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (blockedKeys.containsKey(event.getKeyCode())) {
            long blockedUntil = blockedKeys.get(event.getKeyCode());
            if (blockedUntil > System.currentTimeMillis()) {
                return;
            } else {
                blockedKeys.remove(event.getKeyCode());
            }
        }
        keys.add(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        super.keyReleased(event);
        keys.remove(event.getKeyCode());
    }

    public void moveRight() {
        x = x + speed;
        if (x > width - 10) {
            //Spieler hat gewonnen
            x = 0;

        }
    }

    public void moveLeft() {
        x = x - speed;
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

    public void movePlayerTwoLeft() {
        x2 = x2 - speed;
        if (x2 < 10) {
            //Spieler hat gewonnen
            x2 = 390;

        }
    }

    public void movePlayerTwoRight() {
        x2 = x2 + speed;
    }

    public void jumpPlayerTwo() {
        y2 = y2 - 40;
        jumpDownPlayerTwo();
    }

    public void jumpDownPlayerTwo() {
        y2 = y2 + 40;
    }

    public void display() {
        //fill(c);
        rect(x, y, 10, 30);
        rect(x2, y2, 10, 30);
    }
}
