package model;

/**
 * Created by Jonas on 03.07.2015.
 */
public class Model {
    Layer layer;
    Player right_player;
    Player left_player;
    public Model(int width, int height){
        layer = new Layer(width, height);
        right_player = new Player();
        left_player = new Player();
    }
}
