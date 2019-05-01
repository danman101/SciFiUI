package ie.tudublin;

import processing.core.PImage;

public class PlanetDisplay implements Render{

    private int windowHeight;
    private int windowWidth;
    private boolean launchClicked;
    private Planet selectedPlanet;
    private Planet currentPlanet;
    SpaceTravel travel;
    UI ui;
    PImage image;

    public PlanetDisplay(UI ui)
    {
        this.windowHeight = 200;
        this.windowWidth = 200; 
        this.ui = ui;
        launchClicked = false;
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

        if (launchClicked == true)
        {
            currentPlanet = selectedPlanet;
            launchClicked = false;
        }

        String selectPlanetText =  "Selected Planet: ";
        String currentPlanetText =  "Current Planet: ";

        if(selectedPlanet != null)
        {
            selectPlanetText += selectedPlanet.getDisplayName();
        }
        else 
        {
            selectPlanetText += "N/A";
        }
        
        if(currentPlanet != null)
        {
            currentPlanetText += currentPlanet.getDisplayName();
        }
        else 
        {
            currentPlanetText += "N/A";
        }
        
        ui.text(selectPlanetText, 5, windowHeight + 40);
        ui.text(currentPlanetText, 5, windowHeight + 70);
    }
    
    public void setClicked(boolean launchClicked)
    {
        this.launchClicked = launchClicked;
    }
}