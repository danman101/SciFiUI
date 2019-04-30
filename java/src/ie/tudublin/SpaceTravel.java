package ie.tudublin;

import java.util.Random;

public class SpaceTravel {
    
    private Planet currentPlanet;
    private Planet selectedPlanet;
    private boolean clicked = false;
    private Ship ship;
    private int upper;
    private int lower;
    private Random random;
    private int result;

    public SpaceTravel(Ship ship)
    {
        this.ship = ship;
        random = new Random();
        upper = 4;
        lower = 0;
    }

    public void update()
    {
        if (clicked == true && selectedPlanet != null)
        {
            currentPlanet = selectedPlanet;
            result = random.nextInt(upper - lower) + lower;
            System.out.println(result);
            if(result == 3)
            {
                result = random.nextInt(upper - lower) + lower;
                ship.getParts().get(result).setDamaged(true);
            }
            clicked = false;
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