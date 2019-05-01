# SciFi UI Project

Name: Daniel Fegan

Student Number: D18124733


# Description of the assignment

The program represents the user interface of a navigation system for a space ship. The center navigation
map contains different circles traversing along seperate arcs. These circles represent planets of the solar system,
with the arcs representing their orbital paths. The program allows functionality to 'travel' to selected planets.

# Instructions

Hover your mouse over any of the rotating circles to view the name of the planet that
the circle represents. Clickling on this circle will display an image of the corresponding planet,
along with other info about the planet.

Clicking on launch will then 'transport' your ship to the planet you selected, turning your current
planet into the selected planet.

Clicking launch has a chance to cause damage to your ship. If this happens, you'll see which area of
the ship was damaged in the ship icon located inside the radar on the righthand side of the screen. The icon
is seperated into parts, representing each part of the ship. You will also be notified which part of the ship was
damaged in the diagnostics box beneath this ship icon. This box details which parts are in a critical, stable, or repairing
state. 

Clickling on the repair button beneath the diagnostics box will repair all currently damaged ship parts (it takes 
a moment to complete repairing). You can see which parts of the ship are repairing as the corresponding part icons 
will turn green.



# How it works

## Main Classes

###Planet

The planet class was created to hold information about each planet. This information was loaded from a csv file, which
then populated each planet with different data. Data included each planet's angle, to be able initially plot each
planet on different rings at different locations.  

###NavigationMap

This class was created to draw the main center navigation map. It was drawn in layers. First the main square and grid 
was drawn, which was then followed by the rings. The planets were then drawn onto each ring at seperate locations.
This was done using trigonometric functions, seen here

```Java

planet.setX((float) (cx +  Math.cos(planet.getAngle()) * ringWidth[i]/2));
planet.setY((float) (cy +  Math.sin(planet.getAngle()) * ringWidth[i]/2));

```

The cursor was then drawn. The cursor was designed to only appear when the mouse was hovering over the nav map. This was done
by an if statement checking the boundaries of the map.

The planet angle was then updated. This was done as such

```Java

public void updatePlanetAngle()
{
	for(Planet planet : planets)
	{
		planet.setAngle(planet.getAngle() + 0.001f);
	}
}

```

The drawPlanets method would then have continually increasing values for planet.getAngle(), as the above function
would continually readjust each planets angle. This results in each planet moving along each arc.

# What I am most proud of in the assignment

# Markdown Tutorial

This is *emphasis*

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

This is an image using a relative URL:

![An image](images/p8.png)

This is an image using an absolute URL:

![A different image](https://bryanduggandotorg.files.wordpress.com/2019/02/infinite-forms-00045.png?w=595&h=&zoom=2)

This is a youtube video:

[![YouTube](http://img.youtube.com/vi/J2kHSSFA4NU/0.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)

This is a table:

| Heading 1 | Heading 2 |
|-----------|-----------|
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |

