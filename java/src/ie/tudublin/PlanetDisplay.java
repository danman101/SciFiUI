package ie.tudublin;

import java.util.ArrayList;
import processing.core.PImage;

public class PlanetDisplay implements Render{

    private int windowHeight;
    private int windowWidth;
    private Planet planet;
    UI ui;
    PImage image;

    public PlanetDisplay(UI ui)
    {
        this.windowHeight = 100;
        this.windowWidth = 100; 
        this.ui = ui;
    }

    public void setPlanet(Planet planet)
    {
        this.planet = planet;
        this.image = ui.loadImage(planet.getImage());
    }

    public Planet getPlanet()
    {
        return this.planet;
    }

    public void render(float mX, float mY)
    {
        ui.rect(0, 0, windowWidth, windowHeight);
        if (image != null)
        {   
            ui.image(image, 0,0);
        }
    }
}