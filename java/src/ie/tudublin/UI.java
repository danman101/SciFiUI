package ie.tudublin;

import processing.core.PApplet;

public class UI extends PApplet
{
    Button launch;
    Button repair;
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
        launch = new Button(this, 50, 50, 100, 50, "Launch");
        repair = new Button(this, 50, 50, 100, 50, "Repair");
        radar = new Radar(this, 0.2f, 30, 30, 100);
        planetDisplay = new PlanetDisplay(this);
        map = new NavigationMap(this);
        ship = new Ship(this);
        travel = new SpaceTravel(ship, this);
    }

    Radar radar;

    public void draw()
    {
        background(0, 8, 17);
        
        moveAndRender(map, 550, 30, 0.7f);
        moveAndRender(planetDisplay, 200, 100);
        moveAndRender(launch, 340, 320, 1.5f);
        moveAndRender(repair, 587, 320, 1.5f);
        moveAndRender(radar, 1000, 160);
        moveAndRender(ship, 1100, 120, 0.8f);
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
            if(planetDisplay.getSelectedPlanet() != null && planet.isOver() == true && planetDisplay.getSelectedPlanet() != planet)
            {
                planetDisplay.getSelectedPlanet().setSelected(false);
            }
        }
        for(Planet planet : map.getPlanets())
        {
            if (planet.isOver() == true)
            {
                planetDisplay.setSelectedPlanet(planet);
                planet.setSelected(true);
            }
        }

        if (launch.isOver())
        {
            travel.setClicked(true);
        }

        if (repair.isOver())
        {
            ship.checkRepair();
        }
    }
}

