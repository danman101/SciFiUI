package ie.tudublin;

import processing.data.TableRow;

public class Planet
{
    private String displayName;
    private String image;
    private String colour;

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

    public String toString()
    {
        return displayName;  
    }

    public Planet(TableRow row)
    {
        displayName = row.getString("Display Name");
        image = row.getString("Image");
        colour = row.getString("Colour");
    }

}