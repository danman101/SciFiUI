package ie.tudublin;

public class ShipPart
{
    private String title;
    private boolean damaged;

    public ShipPart(String title)
    {
        this.title = title;
        this.damaged = false;
    }

    public void setDamaged(boolean damaged)
    {
        this.damaged = damaged;
    }

    public boolean getDamaged()
    {
        return this.damaged;
    }

    public void setTitle(boolean damaged)
    {
        this.damaged = damaged;
    }

    public String getTitle()
    {
        return this.title;
    }
}