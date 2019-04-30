package ie.tudublin;

import java.util.ArrayList;

public class Ship {

    ArrayList<ShipPart> shipParts;
    UI ui;

    public Ship(UI ui)
    {
        this.ui = ui;
    }

    public void render()
    {
        drawWings();
        drawBody();
        drawNose();
    }

    public void drawWings()
    {

    }

    public void drawBody()
    {

    }

    public void drawNose()
    {

    }
    
}