package cp.nbody.program3;

public class Point {

    double posX;
    double posY;
    double velX;
    double velY;
    double forceX;
    double forceY;
    double mass;

    public Point(double posX, double posY, double velX, double velY,
            double forceX, double forceY, double mass) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.forceX = forceX;
        this.forceY = forceY;
        this.mass = mass;
    }

    public boolean inQuad(Quad q) {
        return q.contains(posX, posY);
    }

    public void addForce(Point P) {

    }

    public void movePoint(){
        
    }
    
    public double distance(Point p) {
         return Math.sqrt(Math.pow((this.posX - p.posX), 2) + Math.pow((this.posY - p.posY), 2));
    }

    public Point addMasses(Point p) {
        Point a = this;

        double m = a.mass + p.mass;
        double x = (a.posX * a.mass + p.posX * p.mass) / m;
        double y = (a.posY * a.mass + p.posY *p.mass) / m;

        return new Point(x, y, a.velX, p.velY, 0, 0, m);
    }
}
