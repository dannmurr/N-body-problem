/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.nbody.program1;


public class Point {
    double posX;
    double posY;
    double velX;
    double velY; 
    double forceX;
    double forceY;
    double mass; 
    
    public Point(double posX, double posY, double velX, double velY, 
            double forceX, double forceY, double mass){
        this.posX = posX; 
        this.posY = posY;
        this.velX = velX; 
        this.velY = velY;
        this.forceX = forceX; 
        this.forceY  = forceY;
        this.mass = mass; 
    }

    
}
