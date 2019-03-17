#!/bin/bash
javac ParallelNbody.java Point.java

java ParallelNbody 120 50000 1  >  data/bench120.dat
java ParallelNbody 120 50000 2  >>  data/bench120.dat
java ParallelNbody 120 50000 3  >>  data/bench120.dat
java ParallelNbody 120 50000 4  >>  data/bench120.dat


java ParallelNbody 180 50000 1  >  data/bench180.dat
java ParallelNbody 180 50000 2  >>  data/bench180.dat
java ParallelNbody 180 50000 3  >>  data/bench180.dat
java ParallelNbody 180 50000 4  >>  data/bench180.dat



java ParallelNbody 240 50000 1  >  data/bench240.dat
java ParallelNbody 240 50000 2  >>  data/bench240.dat
java ParallelNbody 240 50000 3  >>  data/bench240.dat
java ParallelNbody 240 50000 4  >>  data/bench240.dat
