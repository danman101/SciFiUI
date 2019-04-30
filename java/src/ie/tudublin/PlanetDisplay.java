package ie.tudublin;

import processing.core.PImage;

public class PlanetDisplay implements Render{

    private int windowHeight;
    private int windowWidth;
    private Planet planet;
    UI ui;
    PImage image;

    public PlanetDisplay(UI ui)
    {
        this.windowHeight = 200;
        this.windowWidth = 200; 
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
        drawPlanetBox();
        drawInfoBox();
    }

    public void drawPlanetBox()
    {
        ui.rect(0, 0, windowWidth, windowHeight);
        if (planet != null)
        {   
            ui.image(image, 1,1, windowHeight-1, windowWidth-1);
        }
    }

    public void drawInfoBox()
    {
        ui.rect(0, windowHeight + 20, windowWidth, windowHeight);
        ui.textSize(20);
        if(planet != null)
        {
            ui.text(planet.getDisplayName(), 2, windowHeight + 40);
        }
    }
}