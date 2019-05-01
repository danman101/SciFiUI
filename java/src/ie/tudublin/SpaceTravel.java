package ie.tudublin;

import java.util.Random;

public class SpaceTravel implements Render {
    
    private Planet currentPlanet;
    private Planet selectedPlanet;
    private boolean clicked = false;
    private Ship ship;
    private int breakUpper;
    private int breakLower;
    private int partUpper;
    private int partLower;
    private Random random;
    private int result;
    float mX;
    float mY;
    UI ui;
    private int windowHeight;
    private int windowWidth;

    public SpaceTravel(Ship ship, UI ui)
    {
        this.ui = ui;
        this.ship = ship;
        random = new Random();
        breakUpper = 3;
        breakLower = 1;
        partUpper = 5;
        partLower = 0;
        this.windowHeight = 200;
        this.windowWidth = 200;
    }

    public void render(float mx, float my)
    {
        this.mX = mx;
        this.mY = my;
        update();
        drawDiagnostics();
    }

    public void update()
    {
        if (clicked == true && selectedPlanet != null)
        {
            currentPlanet = selectedPlanet;
            result = random.nextInt(breakUpper - breakLower) + breakLower;

            if(result == 2)
            {
                result = random.nextInt(partUpper - partLower) + partLower;
                ship.getParts().get(result).setDamaged(true);
            }
            clicked = false;
        }
    }

    public void drawDiagnostics()
    {
        int textY = windowHeight + 80;
        ui.textSize(20);
        ui.fill(40, 201, 198);
        ui.textAlign(UI.LEFT);
        ui.text("Diagnostics", 5, windowHeight + 45);

        for(ShipPart shipPart : ship.getParts())
        {
            ui.noFill();
            ui.stroke(113, 247, 17);
            ui.rect(0, windowHeight + 20, windowWidth, windowHeight);
            ui.textSize(15);
            String status;
            
            if(shipPart.getDamaged() && ship.getRepair() == true)
            {
                ui.fill(113, 247, 17);
                status = "Repairing";
            }
            else if(shipPart.getDamaged())
            {
                ui.fill(242, 25, 21);
                status = "Critical";
            }
            else 
            {
                ui.fill(40, 201, 198);
                status = "Stable";
            }

            ui.text(shipPart.getTitle() + " : " + status, 5, textY);
            textY += 30;
            ui.noFill();
        }
    }

    public Planet getCurrentPlanet()
    {
        return this.currentPlanet;
    }

    public void setClicked(boolean clicked)
    {
        this.clicked = clicked;
    }

    public void setSelectedPlanet(Planet s)
    {
        this.selectedPlanet = s;
    }
}