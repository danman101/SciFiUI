package ie.tudublin;

import java.util.ArrayList;

public class Ship implements Render{

    ArrayList<ShipPart> shipParts = new ArrayList<ShipPart>();
    UI ui;

    public Ship(UI ui)
    {
        this.ui = ui;
        shipParts.add(new ShipPart("Body"));
        shipParts.add(new ShipPart("Left Wing"));
        shipParts.add(new ShipPart("Right Wing"));
        shipParts.add(new ShipPart("Nose"));
    }

    public void render(float mX, float mY)
    {
         for(ShipPart shipPart : shipParts)
         { 
            /*
            ui.pushMatrix();
            ui.translate(400,0);
            drawPart(shipParts.get(0));
            ui.popMatrix();*/
           // drawPart(shipPart);
        }
        drawPart(shipParts.get(2));
    }

    public void drawPart(ShipPart shipPart)
    {
        if (shipPart.getTitle().equals("Body"))
        {
            ui.stroke(255, 255, 255);
            ui.beginShape();
            ui.vertex(0, 0);
            ui.vertex(0, 50);
            ui.vertex(5, 50);
            ui.vertex(5, 45);
            ui.vertex(75, 45);
            ui.vertex(75, 50);
            ui.vertex(80, 50);
            ui.vertex(80, 0);
            ui.endShape(UI.CLOSE);
        }

        if (shipPart.getTitle().equals("Nose"))
        {
            ui.stroke(255, 255, 255);
            ui.beginShape();
            ui.vertex(20, 0);
            ui.vertex(20, 100);
            ui.vertex(10, 100);
            ui.vertex(10, 130);
            ui.vertex(40, 130);
            ui.vertex(40, 100);
            ui.vertex(30, 100);
            ui.vertex(30, 0);
            ui.endShape(UI.CLOSE);
        }

        if (shipPart.getTitle().equals("Left Wing"))
        {
            ui.stroke(255, 255, 255);
            ui.beginShape();
            ui.vertex(0, 0);
            ui.vertex(0, 70);
            ui.vertex(10, 70);
            ui.vertex(10, 65);
            ui.vertex(20, 65);
            ui.vertex(20, 45);
            ui.vertex(10, 45);
            ui.vertex(10, 0);
            ui.vertex(0, 0);
            ui.endShape(UI.CLOSE);
        }

        if (shipPart.getTitle().equals("Right Wing"))
        {
            ui.stroke(255, 255, 255);
            ui.beginShape();
            ui.vertex(20, 0);
            ui.vertex(20, 70);
            ui.vertex(10, 70);
            ui.vertex(10, 65);
            ui.vertex(0, 65);
            ui.vertex(0, 45);
            ui.vertex(10, 45);
            ui.vertex(10, 0);
            ui.vertex(0, 0);
            ui.endShape(UI.CLOSE);
        }
    }

    public void buildShip()
    {

    }
    
}