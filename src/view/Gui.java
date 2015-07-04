package view;

import model.Player;
import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jonas on 03.07.2015.
 */
public class Gui extends PApplet {
    //color c = color(0);
    float x = 10;
    float y = 380;
    float x2 = 990;
    float y2 = 380;
    float speed = 5;
    float player_width = 10;
    Set<Integer> keys= new HashSet<>();

    public void setup() {
        size(1000, 400);
    }

    public void draw() {


        background(255);
        display();

        if(keys.contains((java.awt.event.KeyEvent.VK_LEFT))){
            movePlayerTwoLeft();
        }else if(keys.contains((java.awt.event.KeyEvent.VK_RIGHT))){
            movePlayerTwoRight();
        }
        new Player().draw();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        keys.add(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        super.keyReleased(event);
        keys.remove(event.getKeyCode());
    }


    public void movePlayerTwoLeft() {
        if((x2-speed-player_width)>x) {
            x2 = x2 - speed;
            if (x2 < 10) {
                //Spieler hat gewonnen
                x2 = 390;
            }
        }
    }

    public void movePlayerTwoRight() {
        if((x2+player_width+speed)>x) {
            x2 = x2 + speed;
        }
    }


    public void display() {
//fill(c);
        rect(x,y,player_width,30);
        rect(x2,y2,player_width,30);
    }
}