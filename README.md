# connect4AI

This is a connect4 AI which can beat its human opponent 99% of the time. The goal of the project was not to create a perfect AI but rather a non deterministic one which used less conventional approaches. When presented with two identical positions, the computer will often play different moves. This is why it can sometimes be beat as opposed to approaches such as Minimax. It uses a Monte Carlo Tree Search for the algorithm and Java Swing for its graphics. All of the code was designed by me with inspiration for the Monte Carlo Tree Search coming from Google's AlphaGo. 

# Run Code
## IDE
Put the code in an IDE such as Eclipse or IntelliJ and run the code through there. The main class is Game.java.

## Mac or Windows
The following methods only work with the Java SDK installed. 
After connect4AI has been downloaded 
``` bash
cd /connect4AI
cd /bin
java Game
```
# Usage
Whoever's turn it is is indicated on the top of the screen. Click column to place piece or click the "Click to AI move" button to have the AI move. 

There may be a pause before AI moves so do not click the button again or the AI will play two moves. 

Restart button will appear when game is over, use it to clear the board. 

# Advanced
