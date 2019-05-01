package ie.tudublin;

import java.util.ArrayList;

public class Ship implements Render{

    ArrayList<ShipPart> shipParts = new ArrayList<ShipPart>();
    UI ui;
    float mX;
    float mY;
    private boolean repair;
    int startCount = 0;
    int repairCount = 1000;

    public Ship(UI ui)
    {
        this.ui = ui;
        shipParts.add(new ShipPart("Body"));
        shipParts.add(new ShipPart("Left Wing"));
        shipParts.add(new ShipPart("Right Wing"));
        shipParts.add(new ShipPart("Nose"));
        shipParts.add(new ShipPart("Engines"));
    }

    public void render(float mX, float mY)
    {
        this.mX = this.mY;
        drawShip();
        /*
        if(repair)
        {   
            checkRepair();
        }
        */
    }

    public ArrayList<ShipPart> getParts()
    {
        return this.shipParts;
    }

    public void drawPart(ShipPart shipPart)
    {   
        ui.stroke(61, 159, 229);
        ui.fill(61, 159, 229);
        ui.beginShape();
        
        if(shipPart.getDamaged() == true)
        {
            ui.fill(255, 0, 0);
            ui.stroke(255, 0, 0);
        }
        
        if(shipPart.getDamaged() == true && repair == true)
        {
            ui.fill(113, 247, 17);
            ui.stroke(113, 247, 17);
        }

        if (shipPart.getTitle().equals("Body"))
        {
            ui.vertex(0, 0);
            ui.vertex(0, 50);
            ui.vertex(5, 50);
            ui.vertex(5, 45);
            ui.vertex(75, 45);
            ui.vertex(75, 50);
            ui.vertex(80, 50);
            ui.vertex(80, 0);
        }

        if (shipPart.getTitle().equals("Nose"))
        {
            ui.vertex(20, 0);
            ui.vertex(20, 100);
            ui.vertex(10, 100);
            ui.vertex(10, 130);
            ui.vertex(40, 130);
            ui.vertex(40, 100);
            ui.vertex(30, 100);
            ui.vertex(30, 0);
        }

        if (shipPart.getTitle().equals("Left Wing"))
        {
            ui.vertex(0, 0);
            ui.vertex(0, 70);
            ui.vertex(10, 70);
            ui.vertex(10, 65);
            ui.vertex(20, 65);
            ui.vertex(20, 45);
            ui.vertex(10, 45);
            ui.vertex(10, 0);
            ui.vertex(0, 0);
        }

        if (shipPart.getTitle().equals("Right Wing"))
        {
            ui.vertex(20, 0);
            ui.vertex(20, 70);
            ui.vertex(10, 70);
            ui.vertex(10, 65);
            ui.vertex(0, 65);
            ui.vertex(0, 45);
            ui.vertex(10, 45);
            ui.vertex(10, 0);
            ui.vertex(20, 0);
        }

        if (shipPart.getTitle().equals("Engines"))
        {
            ui.vertex(0, 0);
            ui.vertex(0, 15);
            ui.vertex(13, 15);
            ui.vertex(13, 0);
            ui.vertex(0, 0);
        }

        ui.endShape(UI.CLOSE);
        ui.noFill();
        ui.noStroke();

        if (repair == true)
        {
            startCount++;
            if(startCount == repairCount)
            {
                repair = false;
                startCount = 0;
                for (ShipPart part: shipParts)
                {
                    part.setDamaged(false);
                }
            }
        }
    }

    public void drawShip()
    {
        for(ShipPart shipPart : shipParts)
        {
            if(shipPart.getTitle().equals("Body"))
            {
                ui.pushMatrix();
                ui.translate(150, 150);
                drawPart(shipPart);
                ui.popMatrix();
            }

            if(shipPart.getTitle().equals("Nose"))
            {
                ui.pushMatrix();
                ui.translate(165, 15);
                drawPart(shipPart);
                ui.popMatrix();
            }
            
            if(shipPart.getTitle().equals("Left Wing"))
            {
                ui.pushMatrix();
                ui.translate(125, 115);
                drawPart(shipPart);
                ui.popMatrix();
            }
            
            if(shipPart.getTitle().equals("Right Wing"))
            {
                ui.pushMatrix();
                ui.translate(235, 115);
                drawPart(shipPart);
                ui.popMatrix();
            }

            if(shipPart.getTitle().equals("Engines"))
            {
                int x = 170;
                for(int i = 0; i < 2; i++)
                {
                    ui.pushMatrix();
                    ui.translate(x, 200);
                    drawPart(shipPart);
                    ui.popMatrix();
                    x = 200;
                }
            }
        }
    }
    
    public void setRepair(boolean repair)
    {
        this.repair = repair;
    }

    public boolean getRepair()
    {
        return repair;
    }

    public void checkRepair()
    {
        for (ShipPart shipPart : shipParts)
        {
            if(shipPart.getDamaged() == true)
            {
                repair = true;
            }
        }
    }
}