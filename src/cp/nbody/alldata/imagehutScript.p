# Gnuplot script

set terminal png
set output "hut_avril.png"

set title "N-body implementations"

set key right center

set xlabel "Number of threads"
set ylabel "Time in s"

set xrange [1:4]
set yrange [0:10]

plot "phut120.dat" u 1:2 w linespoints title "Parallel NlogN 120", \
     "shut120.dat" u 1:2 w linespoints title "Sequential NlogN 120", \
     "phut180.dat" u 1:2 w linespoints title "Parallel NlogN 180", \
     "shut180.dat" u 1:2 w linespoints title "Sequential NlogN 180", \
     "phut240.dat" u 1:2 w linespoints title "Parallel NlogN 240", \
     "shut240.dat" u 1:2 w linespoints title "Sequential NlogN 240"
