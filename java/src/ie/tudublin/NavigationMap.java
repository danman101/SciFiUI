package ie.tudublin;

import java.util.ArrayList;

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
    float mX;
    float mY;
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
        
        for(TableRow row:table.rows())
        {
            Planet planet = new Planet(row, ui, this);
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
        int n = 10;
        int x = 0;
        int y = 0;
        
        ui.strokeWeight(2.0f);
        ui.stroke(113, 247, 17);
        ui.fill(3, 9, 43);
        ui.rect(width/12, width/12, 670, 670);
        ui.strokeWeight(1.0f);
        ui.noFill();
        ui.stroke(40, 201, 198, 255/2);

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
        ui.stroke(61, 159, 229);
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
            
            if(planet.getSelected()==true)
            {
                ui.fill(113, 247, 17);
                ui.stroke(113, 247, 17);
            }
            else
            {
                ui.fill(61, 159, 229);
                ui.stroke(61, 159, 229);
            }
            ui.ellipse(planet.getX(), planet.getY(), planet.getWidth(), planet.getWidth());
            
            if(planet.isOver() == true)
            {
                ui.text(planet.getDisplayName(), planet.getX()+20, planet.getY()+6);
            }

            k++;
            if (k == 2 )
            {
                i++;
                k = 0;
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
        ui.stroke(113, 247, 17);
        ui.strokeWeight(2.0f);
        
        if(mX > width/12 && mX < 735 && mY > height/12 && mY < 735)
        {
            ui.arc(mX, mY, 50, 50, 0 - UI.HALF_PI/3, UI.HALF_PI/3);
            ui.arc(mX, mY, 50, 50, (3 * UI.PI/2) - UI.HALF_PI/3, (3 * UI.PI /2) + UI.HALF_PI/3);
            ui.arc(mX, mY, 50, 50, UI.PI - UI.HALF_PI/3, UI.PI + UI.HALF_PI/3);
            ui.arc(mX, mY, 50, 50, UI.PI / 2 - UI.HALF_PI/3, UI.PI / 2 + UI.HALF_PI/3);
        
            ui.line(width/12, mY, mX - 25 , mY);
            ui.line(735, mY, mX + 25, mY);
            ui.line(mX, height/12, mX, mY - 25);
            ui.line(mX, 735, mX, mY + 25);
        }
        ui.strokeWeight(1.0f);
    }
}