package ie.tudublin;

import processing.data.TableRow;

public class Planet
{
    private String displayName;
    private String image;
    private String colour;
    private float angle;
    private float width;
    public float x;
    public float y;
    UI ui;

    public Planet(TableRow row, UI ui)
    {
        displayName = row.getString("Display Name");
        image = row.getString("Image");
        colour = row.getString("Colour");
        angle = row.getFloat("Angle");
        width = 30.0f;
        this.ui = ui;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public String getImage()
    {
        return image;
    }

    public void setColour(String colour)
    {
        this.colour = colour;
    }

    public String getColour()
    {
        return colour;
    }

    public void setAngle(float angle)
    {
        this.angle = angle;
    }

    public float getAngle()
    {
        return angle;
    }

    public String toString()
    {
        return displayName;  
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getX()
    {
        return this.x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getY()
    {
        return this.y;
    }

    public void setWidth(float width)
    {
        this.width = width;
    }

    public float getWidth()
    {
        return this.width;
    }
    
    boolean isOver() {
        if (UI.dist(x, y, NavigationMap.mX, NavigationMap.mY) < this.width)
          return true;
        else
          return false;
      }
    
}