1. Main screen
a. This screen consists of the main display for the grid of cells. The grid is at least 12 by 12, and you have the freedom to make it bigger.
b. Each cell is either live or dead, with live filled with a solid red circle, and dead being empty.
c. Each cell in the grid is clickable at any time. If clicked, its state flips from live to dead, or dead to live.
d. Recommended implementation for rendering the grid
i. Android 2D graphics
e. Besides the main display, there are two buttons, Next and Reset
 . Clicking on Next brings the grid to its next generation
i. Clicking on Reset clears all live cells, making the whole grid empty.
1. Before clearing everything, your app prompts the user to confirm that he/she wants to reset the game.
2. Evolution rules
 . We follow the rules as described in the referenced wiki page

i. 	Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
ii. 	Any live cell with two or three live neighbours lives on to the next generation.
iii. 	Any live cell with more than three live neighbours dies, as if by overpopulation.
iv. 	Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
b. Border condition
 . If a neighbor’s position is outside the grid, then we do not consider that neighbor as existent.

