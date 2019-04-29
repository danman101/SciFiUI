package ie.tudublin;

import java.util.ArrayList;

import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NavigationMap
{

    private UI ui;
    int width = 800;
    int height = 800;
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
        int x = 25;
        for(int i = 0; i < 5; i++)
        {
            x += 120;
            ui.ellipse(width/2, height/2, x, x);
        }
    }

    void drawPlanets()
    {
        for(Planet planet:planets)
        {
            float x = 400;
            float y = 400;
            float d = 40;
            System.out.println("\n" + planet);
            int a = 413;
            
            for (int i = 0; i < 5; i++)
            {   
                x = (float) (25 * Math.cos(400 * Math.PI));
                y = 400;
                a += 60; //actual x
                ui.noFill();
                ui.stroke(244, 66, 66);
                ui.ellipse(a, y, d, d); //swap x for y (testing)
                ui.stroke(244, 229, 65);
                ui.text(planet.getDisplayName(), x+10, y+4);
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