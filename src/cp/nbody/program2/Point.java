/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.nbody.program2;

public class Point {

    double posX;
    double posY;
    double velX;
    double velY;
    double[] forcesX;
    double[] forcesY;
    double mass;

    public Point(double posX, double posY, double velX, double velY, double forcesX, double forcesY, double mass, int numBodies) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        
        this.forcesX = new double [numBodies];
        this.forcesY = new double [numBodies];
        for(int i = 0; i < numBodies; i++){
            this.forcesX[i] = forcesX;
            this.forcesY[i] = forcesY;
        }
        this.mass = mass;
    }

}
