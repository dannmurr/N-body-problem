#!/bin/bash
javac Nbody.java Point.java

java Nbody 120 50000 > data/sbench120.dat
java Nbody 180 50000 > data/sbench180.dat
java Nbody 240 50000 > data/sbench240.dat
