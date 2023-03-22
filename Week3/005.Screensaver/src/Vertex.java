import java.awt.geom.Point2D;

public class Vertex
{
    private Point2D location;
    private int direction;
    private double rc;

    public Vertex(int x, int y, int direction, double rc)
    {
        this.location = new Point2D.Double(x, y);
        this.direction = direction;
        this.rc = rc;
    }

    public double getX()
    {
        return location.getX();
    }

    public double getY()
    {
        return location.getY();
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getRc() {
        return rc;
    }

    public void setRc(double rc) {
        this.rc = rc;
    }
}
