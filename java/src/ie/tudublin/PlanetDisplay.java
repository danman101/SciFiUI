package ie.tudublin;

import java.util.ArrayList;
import processing.core.PImage;

public class PlanetDisplay implements Render{

    private int windowHeight;
    private int windowWidth;
    private ArrayList<Planet> planets;
    UI ui;
    PImage image;

    public PlanetDisplay(UI ui, ArrayList<Planet> planets)
    {
        this.windowHeight = 100;
        this.windowWidth = 100; 
        this.planets = planets;
        this.ui = ui;
        image = ui.loadImage(planets.get(0).getImage());
        System.out.println(planets.get(0).getImage());
    }

    public void render()
    {
        ui.rect(0, 0, windowWidth, windowHeight);
        ui.image(image, 0,0);
    }
}