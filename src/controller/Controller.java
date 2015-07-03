package controller;

import model.Layer;
import model.Player;
import view.Gui;

/**
 * Created by Jonas on 03.07.2015.
 */
public class Controller {
    Gui ui;
    Layer layer;
    Player right_player;
    Player left_player;
    public Controller(Gui g){
        ui = g;
        layer = new Layer(ui.getWidth(), ui.getHeigth());
        right_player = new Player();
        left_player = new Player();
    }

}
