package model;

/**
 * Created by Jonas on 03.07.2015.
 */
public class Player {
    int x;
    int y;
    public Player(int x_coordinate, int y_coordinate){
        x = x_coordinate;
        y = y_coordinate;
    }

    void ButtonPress_W(){
        x--;
    }

    void ButtonPress_D(){
        x++;
    }
}
