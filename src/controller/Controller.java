package controller;


import model.Model;
import view.Gui;

/**
 * Created by Jonas on 03.07.2015.
 */
public class Controller {
    Gui ui;
    Model model;
    public Controller(Gui g){
        ui = g;
        model = new Model(ui.getWidth(), ui.getHeigth());
    }

}
