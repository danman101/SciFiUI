package ie.tudublin;

public class ShipPart
{
    private String title;
    private boolean damaged;

    public ShipPart(String t)
    {
        this.title = t;
        this.damaged = false;
    }

    public void setDamaged(boolean damaged)
    {
        this.damaged = damaged;
    }

    public boolean getDamaged()
    {
        return damaged;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
}