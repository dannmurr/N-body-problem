#!/bin/bash
javac BarnesHut.java BHTree.java Point.java Quad.java


java BarnesHut 120 50000 1  >  data/benchHutSeq120.dat


java BarnesHut 180 50000 1  >  data/benchHutSeq180.dat


java BarnesHut 240 50000 1  >  data/benchHutSeq240.dat
