package cp.nbody.program3;

public class BarnesHut {

    public static void main(String[] args) {

        Point[] points;
        double G = 6.67e-11;
        double DT = 1;

        int gnumBodies = 120;
        int numSteps = 275000;

        int maxlength = 100;

        double massOfBodies = 10;

        points = new Point[gnumBodies];

        //INITIALIZE POINTS
        //HERE PLEASE

        long start = System.nanoTime();

        for (int i = 0; i < numSteps; i++) {
            Quad q = new Quad(0, 0, maxlength);
            BHTree tree = new BHTree(q);

            for (int j = 0; i < gnumBodies; j++) {
                if (points[j].inQuad(q)) {
                    tree.insertPoint(points[j]);
                }
            }
            for (int j = 0; j < gnumBodies; j++) {
                points[j].forceX = points[j].forceY = 0;
                tree.calculateForce(points[j]);
                points[j].movePoint();
            }
        }

        long end = System.nanoTime() - start;

        System.out.println("Total execution time: " + end);

    }
}
