package ie.tudublin;

import processing.core.PImage;

public class PlanetDisplay implements Render{

    private int windowHeight;
    private int windowWidth;
    private Planet selectedPlanet;
    SpaceTravel travel;
    UI ui;
    PImage image;

    public PlanetDisplay(UI ui)
    {
        this.windowHeight = 200;
        this.windowWidth = 200; 
        this.ui = ui;
    }

    public void setSelectedPlanet(Planet planet)
    {
        this.selectedPlanet = planet;
        this.image = ui.loadImage(planet.getImage());
    }

    public Planet getSelectedPlanet()
    {
        return this.selectedPlanet;
    }

    public void render(float mX, float mY)
    {
        drawPlanetBox();
        drawInfoBox();
    }

    public void drawPlanetBox()
    {
        ui.rect(0, 0, windowWidth, windowHeight);
        if (selectedPlanet != null)
        {   
            ui.image(image, 1,1, windowHeight-1, windowWidth-1);
        }
    }

    public void drawInfoBox()
    {
        ui.rect(0, windowHeight + 20, windowWidth, windowHeight);
        ui.textSize(15);
        ui.fill(40, 201, 198);
        ui.text("Planet Selected:", 5, windowHeight + 40);
        if(selectedPlanet != null)
        {
            ui.text(selectedPlanet.getDisplayName(), 5, windowHeight + 70);
        }
        else 
        {
            ui.text("None", 5, windowHeight +70);
        }
    }
}