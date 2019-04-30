package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NavigationMap implements Render
{

    private UI ui;
    int width = 800;
    int height = 800;
    float t = UI.PI;
    int[] ringWidth = {145, 265, 385, 505, 625};
    private ArrayList<Planet> planets = new ArrayList<Planet>();
    static float mX;
    static float mY;
    public NavigationMap(UI ui)
    {
        this.ui = ui;
        loadData();
    }

    public void render(float mX, float mY)
    {
        this.mX = mX;
        this.mY = mY;
        drawMap();
        drawPlanets();
        drawCursor();
        updatePlanetAngle();
    }

    public ArrayList<Planet> getPlanets()
    {
        return this.planets;
    }

    public void loadData()
    {
        Table table = ui.loadTable("planets.csv", "header");
        
        for(int i = 0 ; i < table.getRowCount() ; i ++)
        {
            TableRow row = table.getRow(i);         
        }
        
        for(TableRow row:table.rows())
        {
            Planet planet = new Planet(row, ui);
            planets.add(planet);  
        }
    }

    public void printPlanets()
    {
        for(Planet planet:planets)
        {
            System.out.println("\n" + planet);
        }
    }
    
    void drawMap()
    {
        float gap = width / 12;
        ui.stroke(255);
        int n = 10;
        int x = 0;
        int y = 0;
        ui.stroke(244, 66, 232);
        for(int i =0; i <= n; i++)
        {
            x+=gap;
            y-=gap;
            ui.line(x, gap, x, height - gap);
            ui.line(gap, height + y, width - gap, height + y);
        }
        drawMapRings();
    }
    
    void drawMapRings()
    {
        ui.noFill();
        ui.stroke(255);
        for(int i = 0; i < 5; i++)
        {
            ui.ellipse(width/2, height/2, ringWidth[i], ringWidth[i]);
        }
    }

    void drawPlanets()
    {
        float cx = width/2;
        float cy = width/2;
        int i = 1;
        int k = 0;

        for(Planet planet:planets)
        {
            planet.setX((float) (cx +  Math.cos(planet.getAngle()) * ringWidth[i]/2));
            planet.setY((float) (cy +  Math.sin(planet.getAngle()) * ringWidth[i]/2));
            
            ui.fill(244, 244, 244);
            ui.stroke(244, 244, 244);
            ui.ellipse(planet.getX(), planet.getY(), planet.getWidth(), planet.getWidth());
            ui.stroke(244, 229, 65);
            //ui.text(planet.getDisplayName(), x+10, y+4);

            k++;
            if (k == 2 )
            {
                i++;
                k= 0;
            }
        }
    }
    
    public void updatePlanetAngle()
    {
        for(Planet planet : planets)
        {
            planet.setAngle(planet.getAngle() + 0.001f);
        }
    }

    void drawCursor()
    {   
        ui.noFill();
        ui.stroke(244, 66, 66);
        
        ui.arc(mX, mY, 50, 50, 0 - UI.HALF_PI/3, UI.HALF_PI/3);
        ui.arc(mX, mY, 50, 50, (3 * UI.PI/2) - UI.HALF_PI/3, (3 * UI.PI /2) + UI.HALF_PI/3);
        ui.arc(mX, mY, 50, 50, UI.PI - UI.HALF_PI/3, UI.PI + UI.HALF_PI/3);
        ui.arc(mX, mY, 50, 50, UI.PI / 2 - UI.HALF_PI/3, UI.PI / 2 + UI.HALF_PI/3);
        
        ui.line(0, mY, mX - 25 , mY);
        ui.line(width, mY, mX + 25, mY);
        ui.line(mX, 0, mX, mY - 25);
        ui.line(mX, height, mX, mY + 25);
    }
}