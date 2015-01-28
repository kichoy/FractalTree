import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class FractalTree extends PApplet {

private double fractionLength = .8f;
private int smallestBranch = 20;
private double branchAngle = 0.5f;

public void setup()
{
	size(620, 480);
	frameRate(8);
}

public double count1 = 10;
public double count2 = 0.5f;
public int branchLength = 100;
public void draw()
{
	background(0);
	stroke(mouseX, 255, mouseY);
	line(width/2, height, width/2, height-height/4);
	drawBranches(width/2, height-height/4, branchLength, 3*Math.PI/2);

	//Animation
	//branch length
	if (branchLength <= 0)
	{
		count1 = 10;
	}
	if (branchLength >= 100)
	{
		count1 = -10;
	}
	branchLength += count1;
	//branch angle
	if (branchAngle <= 0)
	{
		count2 = 0.1f*Math.random();
	}
	if (branchAngle >= 1)
	{
		count2 = -0.1f*Math.random();
	}
	branchAngle += count2;


}

public void drawBranches(int x, int y, double branchLength, double angle)
{
	double angle1, angle2;
	angle1 = angle + branchAngle;
	angle2 = angle - branchAngle;
	branchLength = branchLength * fractionLength;

	int endX1 = (int)(branchLength*Math.cos(angle1) + x);
	int endY1 = (int)(branchLength*Math.sin(angle1) + y);
	int endX2 = (int)(branchLength*Math.cos(angle2) + x);
	int endY2 = (int)(branchLength*Math.sin(angle2) + y);

	line(x, y, endX1, endY1);
	line(x, y, endX2, endY2);

	if (branchLength > smallestBranch)
	{
		drawBranches(endX1, endY1, branchLength, angle1);
		drawBranches(endX2, endY2, branchLength, angle2);
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "FractalTree" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
