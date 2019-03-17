/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;

public class Nbody {

    double G = 6.67e-11;
    Point[] points;
    double DT = 1;

    int gnumBodies; // = 120;
    int numSteps;// = 275000;

    double massOfBodies = 10;

    // public void setParam(int gnumBodies, int numSteps) {
    //   this.gnumBodies = gnumBodies;
    //   this.numSteps = numSteps;
    // }

    public Nbody(int bodies, int steps) {
        this.gnumBodies = bodies;
        this.numSteps = steps;

        points = new Point[gnumBodies];
        Random r = new Random();

        for (int i = 0; i < gnumBodies; i++) {
            points[i] = new Point((10 * (i % (int) Math.sqrt(gnumBodies))) + r.nextDouble() * 7,
                    10 * (i / (int) Math.sqrt(gnumBodies)) + r.nextDouble() * 7, 0, 0, 0, 0, massOfBodies);
        }
    }

    public void calculateForces() {
        double distance, magnitude, directionX, directionY;

        for (int i = 0; i < gnumBodies; i++) {
            for (int j = i + 1; j < gnumBodies; j++) {
                distance = distance(points[i], points[j]);
                magnitude = (G * points[i].mass * points[j].mass) / (distance * distance);
                directionX = points[j].posX - points[i].posX;
                directionY = points[j].posY - points[i].posY;
                points[i].forceX = points[i].forceX + magnitude * directionX / distance;
                points[j].forceX = points[j].forceX - magnitude * directionX / distance;
                points[i].forceY = points[i].forceY + magnitude * directionY / distance;
                points[j].forceY = points[j].forceY - magnitude * directionY / distance;
            }
        }

    }

    public void moveBodies() {
        double deltaVelX;
        double deltaVelY;
        double deltaPosX;
        double deltaPosY;

        for (int i = 0; i < gnumBodies; i++) {
            deltaVelX = (points[i].forceX / points[i].mass) * DT;
            deltaVelY = (points[i].forceY / points[i].mass) * DT;

            deltaPosX = ((points[i].velX + deltaVelX) / 2) * DT;
            deltaPosY = ((points[i].velY + deltaVelY) / 2) * DT;

            points[i].velX = points[i].velX + deltaVelX;
            points[i].velY = points[i].velY + deltaVelY;

            points[i].posX = points[i].posX + deltaPosX;
            points[i].posY = points[i].posY + deltaPosY;

            points[i].forceX = 0;
            points[i].forceY = 0;
        }
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow((a.posX - b.posX), 2) + Math.pow((a.posY - b.posY), 2));
    }

    public static void main(String[] args) {
        int bodies = Integer.parseInt(args[0]); // = 120;
        int steps  =Integer.parseInt(args[1]);// = 275000;

        Nbody simulation = new Nbody(bodies, steps);
        // simulation.setParam(bodies, steps);

        for (int i = 0; i < 5; i++) {
            // System.out.println("Body number " + i + " at position (" + simulation.points[i].posX + ", " + simulation.points[i].posY + ")");
        }

        long start = System.nanoTime();

        for (int i = 0; i < simulation.numSteps; i++) {
            simulation.calculateForces();
            simulation.moveBodies();
        }

        long time = System.nanoTime() - start;

        for (int i = 0; i < 5; i++) {
            // System.out.println("Body number " + i + " at position (" + simulation.points[i].posX + ", " + simulation.points[i].posY + ")");
        }
        System.out.println(time*Math.pow(10, -9));

    }

}
