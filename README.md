# SciFi UI Project

Name: Daniel Fegan

Student Number: D18124733


# Description of the assignment

The program represents the user interface of a navigation system for a space ship. The center navigation
map contains different circles traversing along seperate arcs. These circles represent planets of the solar system,
with the arcs representing their orbital paths. The program allows functionality to 'travel' to selected planets.
The program also displays an created ship icon containing the seperate parts of the ship. These parts change colour 
depending on the state of the part. The user can click on a button to repair these parts if in a damaged state.

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

### Planet

The planet class was created to hold information about each planet. This information was loaded from a csv file, which
then populated each planet with different data. Data included each planet's angle, to be able initially plot each
planet on different rings at different locations.  

### NavigationMap

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

### PlanetsDisplay

This class was responsible for displaying the image of the planet, along with displaying the selected planet and the current planet.
Mouse click was done from outside the class, using the mouseClicked method in the UI class. 
The class contains is responsible for switching the selectedPlanet into currentPlanet upon clicking the launch button (which is
a seperate class).

### ShipPart

This class represented a single ship part. It was created to hold the damaged state of each ship part, along with the title
of each part.

Each part was drawn using the  beginShape() method found within processing. Each vertex was then plotted using vertex(x,y), and finally
endShape() to close the shape. This was done as it is an ideal way to draw complex shapes. The part was then filled in using fill().


### Ship

This class was responsible for holding all ship parts, and 'forming' them together to draw a coherent single ship.
A has a relationship was employed by having an arraylist field to hold each ship part. 
The drawPart() method dealt with dealing with drawing a single part, depending on the title of the part passed to it.
(ie. part.title ="Nose" would draw a nose shape). drawShip then drew all parts by passsing each part stored in the arraylist field
into drawPart() using a for each loop. The parts were re adjusted to the correct positions sing pop/pushMatrix().
An example of such an operation is seen below:

```Java

if(shipPart.getTitle().equals("Body"))
{
	ui.pushMatrix();
	ui.translate(150, 150);
	drawPart(shipPart);
	ui.popMatrix();
}

```

Depending on the part title, the translate method will have varying arguments passed to it.

The ship class is also responsible for repairs. If the repair button in UI is clicked, the checkRepair method is called
to see if any parts are damaged. If any parts are damaged, the repair variable is set to true. If repair is true,

```Java

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

```

The startCount and repairCount variables control the length repair lasts for. Once startCount is finished incrementing to 
repairCount, it is reset, repair is set to false (end of repair) and each part is set to be undamaged, as the repair is complete.

		
This class is also responsible for changing the colour of each part depending on the part state, as seen here:

```Java

if(shipPart.getDamaged() == true)
{
	ui.fill(255, 0, 0); // this is red, for damaged
	ui.stroke(255, 0, 0);
}

if(shipPart.getDamaged() == true && repair == true)
{
	ui.fill(113, 247, 17); // this is green, for repairing as repair is set to true
	ui.stroke(113, 247, 17);
}

```

### SpaceTravel

This class is responsible for calculating the probability that a part will break upon clicking launch, along
with the probability for which part exactly will break. It is also responsible for drawing the 
diagnostics infomation box.

Probability is calculated as follows


```Java

if (launchClicked == true)
{
	result = random.nextInt(breakUpper - breakLower) + breakLower;
	if(result == 2)
	{
		result = random.nextInt(partUpper - partLower) + partLower;
		ship.getParts().get(result).setDamaged(true); //part is set to broken here
	}
	launchClicked = false;
}

```

launchClicked is reset to false once the operation is completed. breakUpper/breakLower represents the limits for the 
the breaking probability (1 in 2), and partUpper/partLower are the bounds for which part (1 in 5) will break.

Here is how the diagnostics message was calculated

```Java

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
```

### Render
This interface was created as I wanted to pass any object to the moveAndRender() method in the UI class.
The interface outlines a single method, the render() method, which each respective class must implement.
I can then pass any object that implements Render to moveAndRender, as such

```Java
public void moveAndRender(Render obj, int x, int y, float scale)
{
	pushMatrix();
	scale(scale);
	translate(x, y);
	obj.render((1/scale) * mouseX -x,(1/scale) * mouseY -y);
	popMatrix();
}
```

moveAndRender was created as it reduced the need to keep typing out the same matrix transformations. 
Here we can also see  how I dealt with how mouseX/mouseY relate to something that is transformed using a matrix.
By passing the following formula, I was able to pass a transformed variation of mouseX/mouseY,
so the each transformed object would respond correctly to mouse movement.

Formula (as seen above):
(1/scale) * mouseX -x,(1/scale) * mouseY -y

# What I am most proud of in the assignment

I am most proud of the Navigation Map, as it incorporated lots of various techniques and multiple parts to
create. I had to figure out how to move the planets along each arc using trigonometric functions. I also had to figure out a way to draw
a grid. I also think it is aesthetically pleasing, and I like how when hovering over a planet the name appears and continues to move
beside the corresponding planet. The cursor is also well designed and matches the aesthetic of the interface.

#Youtube link

[![YouTube](https://www.youtube.com/watch?v=E032gDBkyDA&feature=youtu.be)


