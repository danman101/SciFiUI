package ie.tudublin;

import java.util.ArrayList;

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
    }

    public void loadData()
    {
        Table table = ui.loadTable("HabHYG15ly.csv", "header");
        
        for(int i = 0 ; i < table.getRowCount() ; i ++)
        {
            TableRow row = table.getRow(i);
            System.out.println(row.getString("Display Name"));
            System.out.println(row.getString("Hab?"));            
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
        int x = 50;
        for(int i = 0; i < 5; i++)
        {
            x += 110;
            ui.ellipse(width/2, height/2, x, x);
        }
    }

    void drawPlanets()
    {
        for(Planet planet:planets)
        {
            float x = UI.map(planet.getxG(), -5, 5, width/12, width-width/12);
            float y = UI.map(planet.getyG(), -5, 5, width/12, height-width/12);
            float d = planet.getAbsMag();
            System.out.println("\n" + planet);
            ui.noFill();
            ui.stroke(244, 66, 66);
            ui.ellipse(x, y, d, d);
            ui.stroke(244, 229, 65);
            ui.line(x, y-2, x, y+2);
            ui.line(x-2, y, x+2, y);
            ui.text(planet.getDisplayName(), x+10, y+4);
        }
    }
}