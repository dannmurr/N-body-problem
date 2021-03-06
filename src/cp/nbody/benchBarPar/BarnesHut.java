
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BarnesHut {

    Point[] points;
    double G = 6.67e-11;
    double DT = 0.1;

    int gnumBodies;// = 120;
    int numSteps;// = 275000;
    int numWorkers;// = 3;
    double maxlength;
    double theta = 0.8; // could be an argument

    CyclicBarrier barrier;

    public BarnesHut(int gnumBodies, int numSteps, int numWorkers) {
        this.gnumBodies = gnumBodies;
        this.numSteps = numSteps;
        this.numWorkers = numWorkers;
        double massOfBodies = 10;

        points = new Point[gnumBodies];

        barrier = new CyclicBarrier(numWorkers);

        //INITIALIZE POINTS
        Random r = new Random();
        for (int i = 0; i < gnumBodies; i++) {
            points[i] = new Point((10 * (i % (int) Math.sqrt(gnumBodies))) + r.nextDouble() * 7,
                    10 * (i / (int) Math.sqrt(gnumBodies)) + r.nextDouble() * 7, 0.0, 0.0, 0.0, 0.0, massOfBodies, numWorkers);
        }
        maxlength = points[gnumBodies - 1].posX + 7;

    }

    public void barrier(int w) {
        try {
            barrier.await();
        } catch (InterruptedException ex) {
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(BarnesHut.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static class Worker extends Thread {

        int id;
        BarnesHut work;

        public Worker(int w, BarnesHut work) {
            this.id = w;
            this.work = work;
        }

        @Override
        public void run() {

            long start = 0, end = 0;

            if (id == 0) {
                for (int i = 0; i < 5; i++) {
                    // System.out.println("body " + i + " at " + work.points[i].posX);
                }
                start = System.nanoTime();
            }

            for (int i = 0; i < work.numSteps; i++) {
                Quad q = new Quad(0, 0, work.maxlength);
                BHTree tree = new BHTree(q, work.theta);

                //create tree
                for (int j = 0; j < work.gnumBodies; j++) {
                    if (work.points[j].inQuad(q)) {
                        tree.insertPoint(work.points[j]);
                    }
                }

                //calculate forces for assigned points
                for (int j = id; j < work.gnumBodies; j += work.numWorkers) {
                    tree.calculateForce(work.points[j], id);
                }

                //wait for other workers to finish
                work.barrier(id);

                //move the points according to the forces
                for (int j = id; j < work.gnumBodies; j += work.numWorkers) {
                    work.points[j].movePoint();
                }
                work.barrier(id);

            }
            if (id == 0) {
                for (int i = 0; i < 5; i++) {
                    // System.out.println("body " + i + " at " + work.points[i].posX);
                }
                end = System.nanoTime() - start;
                System.out.println(end * Math.pow(10, -9));
            }

        }

    }

    public static void main(String[] args) {
        int bodies = Integer.parseInt(args[0]); // = 120;
        int steps = Integer.parseInt(args[1]);// = 275000;
        int nrworkers = Integer.parseInt(args[2]);// 3;
        double theta = 0.8; // this as arg?

        BarnesHut simulation = new BarnesHut(bodies, steps, nrworkers);

        for (int i = 0; i < simulation.numWorkers; i++) {
            Worker worker = new Worker(i, simulation);
            worker.start();
        }

    }

}
