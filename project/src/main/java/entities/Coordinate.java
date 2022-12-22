/*  
 *  Coordinate.java
 *  
 *  Description: Stores X and Y coordinates.
 * 
 *  Author: Lionel
 * 
 *  Last changed: Nov 15th, 2022
 *
*/

package entities;

public class Coordinate 
{
    // Attributes
    private int X;
    private int Y;

    // Default constructor (must pass X & Y coordinates)
    public Coordinate(int setX, int setY)
    {
        this.X = setX;
        this.Y = setY;
    }

    // Getters
    public int get_X() { return this.X; }
    public int get_Y() { return this.Y; }

    // Setters
    public void set_X(int val) { this.X = val; }
    public void set_Y(int val) { this.Y = val; }

    // Checks if this coordinate is equal to another coordinate
    public boolean equals(Coordinate other)
    {
        if(this.X == other.get_X() && this.Y == other.get_Y())
            return true;
        return false;
    }
}
