# Gnuplot script

set terminal png
set output "normal_avril.png"

set title "N-body implementations"

set key right center

set xlabel "Number of threads"
set ylabel "Time in s"

set xrange [1:4]
set yrange [0:70]

plot "pn120.dat" u 1:2 w linespoints title "Parallel N² 120", \
     "pn180.dat" u 1:2 w linespoints title "Parallel N² 180", \
     "pn240.dat" u 1:2 w linespoints title "Parallel N² 240", \
     "sn120.dat" u 1:2 w linespoints title "Sequential N² 120", \
     "sn180.dat" u 1:2 w linespoints title "Sequential N² 180", \
     "sn240.dat" u 1:2 w linespoints title "Sequential N² 240"
     #"phut120.dat" u 1:2 w linespoints title "Parallel NlogN 120", \
     #"shut120.dat" u 1:2 w linespoints title "Sequential NlogN 120", \
     #"phut180.dat" u 1:2 w linespoints title "Parallel NlogN 180", \
     #"shut180.dat" u 1:2 w linespoints title "Sequential NlogN 180", \
     #"phut240.dat" u 1:2 w linespoints title "Parallel NlogN 240", \
     #"shut240.dat" u 1:2 w linespoints title "Sequential NlogN 240"
