#!/bin/bash
javac BarnesHut.java BHTree.java Point.java Quad.java

java BarnesHut 120 50000 1  >  data/benchHut120.dat
java BarnesHut 120 50000 2  >>  data/benchHut120.dat
java BarnesHut 120 50000 3  >>  data/benchHut120.dat
java BarnesHut 120 50000 4  >>  data/benchHut120.dat


java BarnesHut 180 50000 1  >  data/benchHut180.dat
java BarnesHut 180 50000 2  >>  data/benchHut180.dat
java BarnesHut 180 50000 3  >>  data/benchHut180.dat
java BarnesHut 180 50000 4  >>  data/benchHut180.dat



java BarnesHut 240 50000 1  >  data/benchHut240.dat
java BarnesHut 240 50000 2  >>  data/benchHut240.dat
java BarnesHut 240 50000 3  >>  data/benchHut240.dat
java BarnesHut 240 50000 4  >>  data/benchHut240.dat
