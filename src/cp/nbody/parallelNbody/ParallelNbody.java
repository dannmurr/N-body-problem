package cp.nbody.parallelNbody;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParallelNbody {

    Point[] points;
    double G = 6.67e-11;

    int gnumBodies = 120;
    int numSteps = 275000;
    int numWorkers = 3;

    double massOfBodies = 10;
    double DT = 1;

    CyclicBarrier barrier;

    public ParallelNbody() {
        points = new Point[gnumBodies];
        Random r = new Random();

        barrier = new CyclicBarrier(numWorkers);

        for (int i = 0; i < gnumBodies; i++) {
            points[i] = new Point((10 * (i % (int) Math.sqrt(gnumBodies))) + r.nextDouble() * 7,
                    10 * (i / (int) Math.sqrt(gnumBodies)) + r.nextDouble() * 7, 0, 0, 0, 0, massOfBodies, numWorkers);
        }
    }

    public void barrier(int w) {
        try {
            barrier.await();
        } catch (InterruptedException ex) {
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(ParallelNbody.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void calculateForces(int w) {
        double distance, magnitude, dirX, dirY;

        for (int i = w; i < (int) gnumBodies; i += numWorkers) {
            for (int j = i + 1; j < gnumBodies; j++) {
                distance = distance(points[i], points[j]);
                magnitude = (G * points[i].mass * points[j].mass) / (distance * distance);
                dirX = points[j].posX - points[i].posX;
                dirY = points[j].posY - points[i].posY;
                points[i].forcesX[w] = points[i].forcesX[w] + magnitude * dirX / distance;
                points[j].forcesX[w] = points[j].forcesX[w] - magnitude * dirX / distance;
                points[i].forcesY[w] = points[i].forcesY[w] + magnitude * dirY / distance;
                points[j].forcesY[w] = points[j].forcesY[w] - magnitude * dirX / distance;
            }
        }
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow((a.posX - b.posX), 2) + Math.pow((a.posY - b.posY), 2));
    }

    public void moveBodies(int w) {
        double deltaVelX, deltaVelY, deltaPosX, deltaPosY, forceX = 0, forceY = 0;
        for (int i = w; i < gnumBodies; i += numWorkers) {
            for (int k = 0; k < numWorkers; k++) {
                forceX += points[i].forcesX[k];
                points[i].forcesX[k] = 0;

                forceY += points[i].forcesY[k];
                points[i].forcesY[k] = 0;
            }
            deltaVelX = (forceX / points[i].mass) * DT;
            deltaVelY = (forceY / points[i].mass) * DT;
            deltaPosX = (points[i].velX + deltaVelX / 2) * DT;
            deltaPosY = (points[i].velY + deltaVelY / 2) * DT;

            points[i].velX = points[i].velX + deltaVelX;
            points[i].velY = points[i].velY + deltaVelY;
            points[i].posX = points[i].posX + deltaPosX;
            points[i].posY = points[i].posY + deltaPosY;

            forceX = forceY = 0;
        }
    }

    public static class Worker extends Thread {

        int id;
        ParallelNbody work;

        public Worker(int w, ParallelNbody work) {
            this.id = w;
            this.work = work;
        }

        @Override
        public void run() {

            long start = 0, end = 0;

            if (id == 0) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("body " + i + " at " + work.points[i].posX);
                }
                start = System.nanoTime();
            }

            for (int i = 0; i < work.numSteps; i++) {
                work.calculateForces(id);
                work.barrier(id);
                work.moveBodies(id);
                work.barrier(id);
            }
            if (id == 0) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("body " + i + " at " + work.points[i].posX);
                }
                end = System.nanoTime() - start;
                System.out.println("total execution time: " + end * Math.pow(10, -9) + " seconds");
            }

        }

    }

    public static void main(String[] args) {

        ParallelNbody simulation = new ParallelNbody();

        for (int i = 0; i < simulation.numWorkers; i++) {
            Worker worker = new Worker(i, simulation);
            worker.start();
        }
    }

}
