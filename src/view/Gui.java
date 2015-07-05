package view;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.*;

/**
 * Created by Jonas on 03.07.2015.
 */
public class Gui extends PApplet {
    PImage bg;
    float speed = 5;
    Set<Integer> pressedKeys = new HashSet<>();
    Map<Integer, Long> blockedKeys = new HashMap<>();
    PriorityQueue<DelayedAction> delayedActions = new PriorityQueue<>();
    Player LeftPlayer;
    Player RightPlayer;
    boolean centered = false;
    Window window;

    public void setup() {
        size(1000, 400);
        LeftPlayer = new Player(Position.LEFT, this);
        RightPlayer = new Player(Position.RIGHT, this);
        LeftPlayer.setOpponent(RightPlayer);
        RightPlayer.setOpponent(LeftPlayer);
        bg = loadImage("background.jpg");
        Container c = getParent();
        while (c.getParent() != null) {
            c = c.getParent();
        }
        if (c instanceof Window) {
            window = (Window) c;
        }
    }

    public void draw() {
        centerWindow();
        background(bg);
        image(bg,0,0);
        while (delayedActions.peek() != null
                && delayedActions.peek().getExecuteAfter() < System.currentTimeMillis()) {
            delayedActions.poll().run();
        }
        LeftPlayer.interact();
        RightPlayer.interact();
        LeftPlayer.draw(g);
        RightPlayer.draw(g);

    }

    private void centerWindow() {
        if (window != null && !centered) {
            window.setLocation(displayWidth / 2 - window.getWidth() / 2, displayHeight / 2 - window.getHeight() / 2);
            window.setMaximumSize(new Dimension(window.getWidth(), window.getHeight()));
            window.setMinimumSize(new Dimension(window.getWidth(), window.getHeight()));
            centered = true;
        }
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
