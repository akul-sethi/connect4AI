# connect4AI

This is a connect4 AI which can beat its human opponent 99% of the time. The goal of the project was not to create a perfect AI but rather a non deterministic one which used less conventional approaches. When presented with two identical positions, the computer will often play different moves. This is why it can sometimes be beat as opposed to approaches such as Minimax. It uses a Monte Carlo Tree Search for the algorithm and Java Swing for its graphics. All of the code was designed by me with inspiration for the Monte Carlo Tree Search coming from Google's AlphaGo. It is designed using principles from the Object Oriented Programming paradigm. 

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
There are 3 hyperparameters of the program which can be manipulated to change performance: ITERATIONS, NUM_ROLLOUTS, and UCB_RATE. They can be found at the beginning of the Game class.

### ITERATIONS
The number of times the Monte Carlo Tree is expanded. Increasing this number can increase accuracy but will also increase the amount of time the algorithm runs.

### NUM_ROLLOUTS
The Monte Carlo Tree Search evaluates positions by running a certain number of "rollouts." A rollout is a are simulated where moves are made randomly by the computer for each side. The number of times each side wins is used to then determine how good its position is. Increasing this hyperparameter can lead to better position evaluations but will also increase the duration of the algorithm.

### UCB_RATE
The Monte Carlo Tree Search uses an Upper Confidence Bound equation to determine how the algorithm balances exploration vs exploitation. Exploration is when the algorithm explores parts of the tree it has never seen before wherease exploitation is when is explorers parts of the tree it thinks is good. Increasing UCB_RATE increases the algorithms "preference" for exploration while decreasing it increases the likelyhood of exploitation. 

# Future improvements
## Hyperparameters
The hyperparameters were largely chosen by me arbitrarily and improved by non-rigorous experimentation. This program could vastly be improved if a system for determining the optimal hyperparameters was created. 

This could involve some sort of genetic algorithm or gradient descent.

## Recursion
When I coded this project I was not comfortable with recursion and so it was done mostly using loops. However, looking at the design now I recognize it is using a self referential data structure in the form of a Tree. Because of this I believe a more natural design choice for it would be to code the algorithm using recursion.



