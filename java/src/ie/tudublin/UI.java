package ie.tudublin;

import processing.core.PApplet;

public class UI extends PApplet
{
    Button b;
    MovingCircle mc;
    NavigationMap map;
    PlanetDisplay planetDisplay;
    boolean[] keys = new boolean[1024];

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
        b = new Button(this, 50, 50, 100, 50, "I am a button");
        mc = new MovingCircle(this, width / 2, height * .75f, 50);
        radar = new Radar(this, 1, width / 2, height / 2, 100);
        map = new NavigationMap(this);
        planetDisplay = new PlanetDisplay(this);
    }

    Radar radar;

    public void draw()
    {
        background(0);
        
       // b.render();

       // mc.update();
        //mc.render();

        //radar.update();
        //radar.render();
        

        moveAndRender(map, 1100, 0, 0.7f);
        moveAndRender(planetDisplay, 600, 0);
        //map.render();

        if (checkKey(LEFT))
        {
            System.out.println("Left arrow key pressed");
        }
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
            if (planet.isOver() == true)
            {
                planetDisplay.setPlanet(planet);
            }
        }
    }
}

