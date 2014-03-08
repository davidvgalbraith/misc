./jumble/ contains a jumble solver. Given an input string, which you have to capitalize, it will print out all substrings that are valid English words according to the Official Scrabble Player's dictionary, 4th edition. To run it, just do

python jumble.py


./switches/ contains a solution to the following problem:

"Assume that you're in a room with 10,000,000 light switches in a row, all in the "On" position. They are labelled consecutively from 1 to 10,000,000. You are asked to perform the following task and report back with the number of switches that are still in the "On" position.

For every switch, s, from 1 to 10,000,000, if s is prime, then you must walk along the wall and flip all switches that are a multiple of s. With switch 11, for example, you would flip switches 11, 22, 33, 44, etc. If you arrive at a switch that was already flipped "Off" previously, you would flip it back the other way, back to "On." How many switches are still "On" after these steps?"

To solve this problem, pick whatever Switchinator you like better, compile it with javac Switchinator.java or Switchinator2.java, and run java Switchinator(2) (# of switches)


./stack contains a simple stack machine. It can add and multiply numbers with 12-bit precision. It takes input in the form of strings of single-digit integers and operations, for instance "23+" evaluates to 5 and "24+5*" evaluates to 30. It's totally failsafe too and crashes on no input, returning -1 if there's an error.

./rice contains a solution to this problem where a pawn starts in the upper-left corner of an arbitrary mxn chessboard and can move at each step either one square to the right or one square down. Each square on the chessboard contains an integral number of grains of rice, and when the pawn reaches a square it collects the grains of rice on that square. We want to maximize the rice grains collected by our pawn. Input format is:

javac RiceChessboard.java

java RiceChessboard

m
n
n integers indicating the rice grains in each square in the first row
//repeat the above for a total of m rows

And it'll print the max sum. Pretty nifty!