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
    boolean keysBlocked = false;

    public void setup() {
        size(1000, 400);
        initPlayers();
        bg = loadImage("background.jpg");
        Container c = getParent();
        while (c.getParent() != null) {
            c = c.getParent();
        }
        if (c instanceof Window) {
            window = (Window) c;
        }
    }

    public void initPlayers() {
        pressedKeys.clear();
        blockedKeys.clear();
        delayedActions.clear();
        if (LeftPlayer != null) {
            LeftPlayer.opponent = null;
            RightPlayer.opponent = null;
        }
        LeftPlayer = new Player(Position.LEFT, this);
        RightPlayer = new Player(Position.RIGHT, this);
        LeftPlayer.setOpponent(RightPlayer);
        RightPlayer.setOpponent(LeftPlayer);
    }

    public void draw() {
        centerWindow();
        background(bg);
        image(bg, 0, 0);
        while (delayedActions.peek() != null
                && delayedActions.peek().getExecuteAfter() < System.currentTimeMillis()) {
            delayedActions.poll().run();
        }
        LeftPlayer.interact();
        RightPlayer.interact();
        LeftPlayer.draw(g);
        RightPlayer.draw(g);

    }

    public void mousePressed(){
        //Wenn Button "Neu Starten" gedrückt wird
        if(mouseX > ((width/2-180)-150) && mouseX < ((width/2-180)+150) && mouseY > ((height/2+20)-50) && mouseY < ((width/2+20)+50)){
            loop();
            keysBlocked = false;
            initPlayers();
        }

        //Wenn Button "Beenden" gedrückt wird
        if(mouseX > ((width/2+30)-150) && mouseX < ((width/2+30)+150) && mouseY > ((height/2+20)-50) && mouseY < ((width/2+20)+50)){
            System.exit(0);
        }

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
        if(!keysBlocked){
            super.keyPressed(event);
            pressedKeys.add(event.getKeyCode());
        }
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


    public void showMenu(char player) {
        noLoop();
        rect(width/2-200,height/2-100,400,200);
        //Button Neu starten
        rect(width/2-180,height/2+20,150,50);
        //Button Beenden
        rect(width/2+30,height/2+20,150,50);
        textSize(20);
        fill(0,0,0);
        text("Neu starten",width/2-180,height/2+20);
        textSize(20);
        fill(0,0,0);
        text("Beenden",width/2+30,height/2+20,150,50);
        if(player == 'l'){
            textSize(32);
            fill(255,0,0);
            text("Left player won",width/2-110,height/2-50);
        }else if(player == 'r'){
            textSize(32);
            fill(0, 0, 255);
            text("Right player won",width/2-125,height/2-50);
        }
    }

    public void blockAllKeys() {
        keysBlocked = true;

    }
}
