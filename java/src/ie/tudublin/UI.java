package ie.tudublin;

import processing.core.PApplet;

public class UI extends PApplet
{
    Button button;
    NavigationMap map;
    PlanetDisplay planetDisplay;
    boolean[] keys = new boolean[1024];
    Ship ship;
    SpaceTravel travel;

    public void keyPressed()
    {
        keys[keyCode] = true;
    }
    
    public void keyReleased()
    {
        keys[keyCode] = false;
    }

    public boolean checkKey(int c)
    {
        return keys[c] || keys [Character.toUpperCase(c)];
    }
    

    public void settings()
    {
        size(1300, 800);
        // Use fullscreen instead of size to make your interface fullscreen
        //fullScreen(P3D); 
    }

    public void setup()
    {
        button = new Button(this, 50, 50, 100, 50, "Launch");
        radar = new Radar(this, 1, width / 2, height / 2, 100);
        map = new NavigationMap(this);
        planetDisplay = new PlanetDisplay(this);
        ship = new Ship(this);
        travel = new SpaceTravel(ship, this);
    }

    Radar radar;

    public void draw()
    {
        background(0, 8, 17);
        
        //radar.update();
        //radar.render();
        
        moveAndRender(map, 550, 30, 0.7f);
        moveAndRender(planetDisplay, 200, 100);
        moveAndRender(button, 340, 320, 1.5f);
        moveAndRender(ship, 840, 50);
        moveAndRender(travel, 930, 100);
    }

    public void moveAndRender(Render obj, int x, int y, float scale)
    {
        pushMatrix();
        scale(scale);
        translate(x, y);
        obj.render((1/scale) * mouseX -x,(1/scale) * mouseY -y);
        popMatrix();
    }
    
    public void moveAndRender(Render obj, int x, int y)
    {
        pushMatrix();
        translate(x, y);
        obj.render(mouseX - x, mouseY - y);
        popMatrix();
    }

    public void mousePressed()
    {   
        for(Planet planet : map.getPlanets())
        {
            if(planetDisplay.getPlanet() != null && planet.isOver() == true && planetDisplay.getPlanet() != planet)
            {
                planetDisplay.getPlanet().setSelected(false);
            }
        }
        for(Planet planet : map.getPlanets())
        {
            if (planet.isOver() == true)
            {
                planetDisplay.setPlanet(planet);
                planet.setSelected(true);
                travel.setSelectedPlanet(planet);
            }
        }

        if (button.isOver())
        {
            button.setClicked(true);
            travel.setClicked(true);
        }
    }
}

