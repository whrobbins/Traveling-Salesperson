import java.awt.geom.Point2D;

/**
 * A simple facade for the class Point2D.Double - a class that represents
 * a point in 2D space with Double precision.
 * 
 */ 

public class Point extends Point2D.Double
{

    public Point(double x, double y)
    {
        super(x,y);
    }
    
    public String toString()
    {
        return "("+this.x+","+this.y+")";
    }

}