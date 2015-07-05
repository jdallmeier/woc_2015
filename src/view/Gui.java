package view;

import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.*;

/**
 * Created by Jonas on 03.07.2015.
 */
public class Gui extends PApplet {
    float speed = 5;
    Set<Integer> pressedKeys = new HashSet<>();
    Map<Integer, Long> blockedKeys = new HashMap<>();
    PriorityQueue<DelayedAction> delayedActions = new PriorityQueue<>();
    Player LeftPlayer;
    Player RightPlayer;

    public void setup() {
        size(1000, 400);
        LeftPlayer = new Player(Position.LEFT, this);
        RightPlayer = new Player(Position.RIGHT, this);
        LeftPlayer.setOpponent(RightPlayer);
        RightPlayer.setOpponent(LeftPlayer);

    }

    public void draw() {
        background(255);
        while (delayedActions.peek() != null
                && delayedActions.peek().getExecuteAfter() < System.currentTimeMillis()) {
            delayedActions.poll().run();
        }
        LeftPlayer.interact();
        RightPlayer.interact();
        LeftPlayer.draw(g);
        RightPlayer.draw(g);
//
//        if (pressedKeys.contains(VK_W)) {
//            LeftPlayer.jump();
//            blockedKeys.put(VK_W, System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(100));
//        }
//        if (pressedKeys.contains(VK_A)) {
//            LeftPlayer.moveLeft();
//        } else if (pressedKeys.contains(VK_D)) {
//            LeftPlayer.moveRight();
//        }
//        if (pressedKeys.contains(VK_LEFT)) {
//            RightPlayer.moveLeft();
//        } else if (pressedKeys.contains(VK_RIGHT)) {
//            RightPlayer.moveRight();
//        }
//        if (pressedKeys.contains(VK_UP)) {
//            RightPlayer.jump();
//            blockedKeys.put(VK_UP, System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(100));
//        }


    }


    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        pressedKeys.add(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        super.keyReleased(event);
        pressedKeys.remove(event.getKeyCode());
    }

    public boolean isKeyPressed(int keyCode) {
        if(!pressedKeys.contains(keyCode)) {
            return false;
        }
        if (blockedKeys.containsKey(keyCode)) {
            long blockedUntil = blockedKeys.get(keyCode);
            if (blockedUntil > System.currentTimeMillis()) {
                return false;
            } else {
                blockedKeys.remove(keyCode);
            }
        }
        return true;
    }


}
