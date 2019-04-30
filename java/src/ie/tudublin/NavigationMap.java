package ie.tudublin;

import java.util.ArrayList;

import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;

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

    public NavigationMap(UI ui)
    {
        this.ui = ui;
        loadData();
    }

    public void render()
    {
        drawMap();
        drawPlanets();
        drawCursor();
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
            System.out.println(row.getString("Display Name"));          
        }
        
        for(TableRow row:table.rows())
        {
            Planet planet = new Planet(row);
            planets.add(planet);   
            System.out.println(planet.getDisplayName());  
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
        int i = 0;
        int k = 0;
        for(Planet planet:planets)
        {
            
            float cx = 400;
            float cy = 400;
             
            float x = (float) (cx +  Math.cos(planet.getAngle()) * ringWidth[i]/2);
            float y = (float) (cy +  Math.sin(planet.getAngle()) * ringWidth[i]/2);
            ui.noFill();
            ui.stroke(244, 66, 66);
            ui.ellipse(x, y, 40, 40);
            ui.stroke(244, 229, 65);
            //ui.text(planet.getDisplayName(), x+10, y+4);
            //t+=0.001;
            k++;
            if (k == 2 )
            {
                i++;
                k= 0;
            }
        }
    }

    void drawCursor()
    {
        ui.stroke(244, 66, 66);
        
        ui.arc(ui.mouseX, ui.mouseY, 50, 50, 0 - UI.HALF_PI/3, UI.HALF_PI/3);
        ui.arc(ui.mouseX, ui.mouseY, 50, 50, (3 * UI.PI/2) - UI.HALF_PI/3, (3 * UI.PI /2) + UI.HALF_PI/3);
        ui.arc(ui.mouseX, ui.mouseY, 50, 50, UI.PI - UI.HALF_PI/3, UI.PI + UI.HALF_PI/3);
        ui.arc(ui.mouseX, ui.mouseY, 50, 50, UI.PI / 2 - UI.HALF_PI/3, UI.PI / 2 + UI.HALF_PI/3);
        
        ui.line(0, ui.mouseY, ui.mouseX - 25 , ui.mouseY);
        ui.line(width, ui.mouseY, ui.mouseX + 25, ui.mouseY);
        ui.line(ui.mouseX, 0, ui.mouseX, ui.mouseY - 25);
        ui.line(ui.mouseX, height, ui.mouseX, ui.mouseY + 25);
    }
}