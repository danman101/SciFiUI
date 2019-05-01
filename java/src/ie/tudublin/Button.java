package ie.tudublin;

import processing.core.PApplet;

public class Button implements Render
{
    UI ui;
    private float x;
    private float y;
    private float width;
    private float height;
    private String text;
    private float mX;
    private float mY;
    private boolean clicked;

    public Button(UI ui, float x, float y, float width, float height, String text)
    {
        this.ui = ui;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        clicked = false;
    }

    public void render(float mX, float mY)
    {
        this.mX = mX;
        this.mY = mY;
        ui.noFill();
        ui.stroke(113, 247, 17);
        ui.rect(x, y, width, height);
        ui.textAlign(PApplet.CENTER, PApplet.CENTER);
        ui.text(text, x + width * 0.5f, y + height * 0.45f);
    }

    public boolean isOver()
    {
        if (mX> x && mX < x + width && mY > y && mY < y + height)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setClicked(boolean clicked)
    {
        this.clicked = clicked;
    }

    public boolean getClicked()
    {
        return this.clicked;
    }
}