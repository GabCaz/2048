/* Name: Gabriel Cazaubieilh
 * Execution: none
 * Description: Board class, corresponding to a Board object, that implements
 *              the BoardInterface to simulate the board in 2048
 * Last changed: 23:20
 */

public class Board implements BoardInterface {
    // array of Tiles representing the current grid
    private Tile[][] currentTileList;
    // the game should keep track of the number of moves made by the user
    private int numOfMoves;
    // number of Tiles currently on the board
    private int numberOfTiles;
    // the x-position of each corresponding Tile in the 2D array currentTileList
    public static final int[] X_COORD = {70, 190, 310, 430};
    // the y-position of each corresponding Tile in the 2D array currentTileList
    public static final int[] Y_COORD = {70, 190, 310, 430};
    // number of Tiles per column / row
    public static final int NUM_TILE = 4;

    /* Description: CONSRUCTOR (builds a new Board) with 2 Tiles on it
     * Input: None
     * Output: None (builds a new board)
     */
    public Board() {
        currentTileList = new Tile[NUM_TILE][NUM_TILE];
        numOfMoves = 0;
        addTile();
        addTile();
    }

    /* Description: adds a Tile with value 2 or 4 at an unoccupied place on the
     *              board (During every turn, a new number tile consisting of 2
     *              or 4 should enter the board. The tile must enter at any
     *              random, unoccupied position on the board)
     * Input: None
     * Output: void
     */
    private void addTile() {
        while (true) {
            int i = StdRandom.uniform(NUM_TILE);
            int j = StdRandom.uniform(NUM_TILE);
            if (currentTileList[i][j] == null) {
                currentTileList[i][j] = new Tile(X_COORD[j], Y_COORD[i]);
                numberOfTiles++;
                currentTileList[i][j].drawApparition();
                return;
            }
        }
    }

    /* Description: sets the x and y-coordinates of each Tile in the list for
     *              their positions on the board to match their positions in the
     *              list
     * Input: void
     * Output: void
     */
    private void updateCoordinates() {
        for (int i = 0; i < NUM_TILE; i++) {
            for (int j = 0; j < NUM_TILE; j++) {
                if (currentTileList[i][j] != null) {
                    currentTileList[i][j].setX(X_COORD[j]);
                    currentTileList[i][j].setY(Y_COORD[i]);
                }
            }
        }
    }

    /* Description: draws all the Tiles of the current board
     * Input: void
     * Output: void
     */
    public void draw() {
        PennDraw.setFontSize(32);
        for (int i = 0; i < NUM_TILE; i++) {
            for (int j = 0; j < NUM_TILE; j++) {
                if (currentTileList[i][j] != null) {
                    currentTileList[i][j].draw();
                }
            }
        }
    }

    /* Description: returns true if the key typed should be processed, ie
     *              corresponds to a possible move in the given Board, ie makes
     *              at least one Tile move
     * Input: one char c that has been typed
     * Output: true if the key typed should be processed, false otherwise
     */
    private boolean keyIsLegitimate(char c) {
        if (c == 'a' || c == 'd' || c == 's' || c == 'w') {
            for (int i = 0; i < NUM_TILE; i++) {
                for (int j = 0; j < NUM_TILE; j++) {
                    if (canMove(i, j, c)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* Description: returns true if Tile t at int i, int j in currentTileList
     *              can move (is not blocked by another Tile or by an edge) in
     *              the direction described by char c, in the currentTileList
     *              array, false otherwise
     * Input: int i, int j that gives the position in currentTileList of the
     *        Tile we want to determine whether can move, char c that determines
     *        the move we want to make on this Tile
     * Output: true if can move the Tile t at int i, int j in currentTileList,
     *         false otherwise
     */
    private boolean canMove(int i, int j, char c) {
        if (currentTileList[i][j] == null) {
            return false;
        }
        if (reachesEdge(i, j, c)) {
            return false;
        }
        if (isBlockedByTile(i, j, c)) {
            return false;
        }
        // else, it is possible to move the Tile
        return true;
    }

    /* Description: returns the Tile immediatly after the Tile at int i, int j
     *              considering a move described by char c
     * Input: int i, int j that gives the position in currentTileList of the
     *        Tile we want to find the next Tile of, char c that determines the
     *        move we want to make on this Tile
     * Output: the Tile immediatly after the Tile at int i, int j considering a
     *         move described by char c
     */
    private Tile nextTile(int i, int j, char c) {
        if (c == 'w') {
            if (i + 1 < NUM_TILE) {
                return currentTileList[i + 1][j];
            }
        } else if (c == 's') {
            if (i - 1 >= 0) {
                return currentTileList[i - 1][j];
            }
        } else if (c == 'a') {
            if (j - 1 >= 0) {
                return currentTileList[i][j - 1];
            }
        } else if (c == 'd') {
            if (j + 1 < NUM_TILE) {
                return currentTileList[i][j + 1];
            }
        }
        // else, key is not legitimate, or there is no next block, so null
        return null;
    }

    /* Description: returns true if Tile t at int i, int j in currentTileList
     *              encounter something (edge, other Tile) in the
     *              direction described by char c
     * Input: int i, int j that gives the position in currentTileList of the
     *        Tile we want to determine whether encounter something, char c that
     *        determines the move we want to make on this Tile
     * Output: true if Tile t at int i, int j in currentTileList encounters
     *         something (edge, other Tile) in the direction described by char c
     */
    private boolean encountersSomething(int i, int j, char c) {
        return reachesEdge(i, j, c) || (nextTile(i, j, c) != null);
    }

    /* Description: returns true if Tile t at int i, int j in currentTileList
     *              is blocked by another Tile (with different value) in the
     *              direction described by char c
     * Input: int i, int j that gives the position in currentTileList of the
     *        Tile we want to determine whether reaches Tile with different
     *        value, char c that determines the move we want to make on this
     *        Tile
     * Output: true if Tile t at int i, int j in currentTileList is blocked by
     *         another Tile (with different value) in the direction described
     *         by char c, false otherwise
     */
    private boolean isBlockedByTile(int i, int j, char c) {
        if (nextTile(i, j, c) == null) {
            return false;
        }
        if (nextTile(i, j, c).shouldMergeWith(currentTileList[i][j])) {
            return false;
        }
        // else, reaches a block with different value, so true
        return true;
    }

    /* Description: returns true if Tile t at int i, int j in currentTileList
     *              is blocked by an edge in the direction descirbed by char c
     * Input: int i, int j that gives the position in currentTileList of the
     *        Tile we want to determine whether can move, char c that determines
     *        the move we want to make on this Tile
     * Output: true if Tile t at int i, int j in currentTileList is blocked by
     *         an edge in the direction descirbed by char c, false otherwise
     */
    private boolean reachesEdge(int i, int j, char c) {
        if (c == 'w') {
            // if tile on top of array, cannot go higher (blocked by edge)
            if (i >= NUM_TILE - 1) {
                return true;
            }
        }
        if (c == 's') {
            // if t is on bottom of board, cannot go down (blocked by edge)
            if (i <= 0) {
                return true;
            }
        }
        if (c == 'a') {
            // if tile is on left of board, cannot go to left (blocked by edge)
            if (j <= 0) {
                return true;
            }
        }

        if (c == 'd') {
            // if t is on right end of board, cannot go to right (edge blocks)
            if (j >= NUM_TILE - 1) {
                return true;
            }
        }
        return false;
    }

    /* Description: when we do the motion described by char c, all Blocks on
     *              Board will move towards this direction until they reach an
     *              obstacle in the currentTileList
     * Input: the char c that defines direction
     * Output: void
     */
    private void consolidateBoard(char c) {
        if (c == 'w') {
            for (int i = NUM_TILE - 1; i >= 0; i--) {
                for (int j = 0; j < NUM_TILE; j++) {
                    int k = i;
                    while (!encountersSomething(k, j, c)) {
                        // change position in array
                        currentTileList[k + 1][j] = currentTileList[k][j];
                        currentTileList[k][j] = null;
                        k++;
                    }
                }
            }
        } else if (c == 's') {
            for (int i = 0; i < NUM_TILE; i++) {
                for (int j = 0; j < NUM_TILE; j++) {
                    int k = i;
                    while (!encountersSomething(k, j, c)) {
                        currentTileList[k - 1][j] = currentTileList[k][j];
                        currentTileList[k][j] = null;
                        k--;
                    }
                }
            }
        } else if (c == 'a') {
            for (int j = 0; j < NUM_TILE; j++) {
                for (int i = 0; i < NUM_TILE; i++) {
                    int k = j;
                    while (!encountersSomething(i, k, c)) {
                        currentTileList[i][k - 1] = currentTileList[i][k];
                        currentTileList[i][k] = null;
                        k--;
                    }
                }
            }
        } else if (c == 'd') {
            for (int j = NUM_TILE - 1; j >= 0; j--) {
                for (int i = 0; i < NUM_TILE; i++) {
                    int k = j;
                    while (!encountersSomething(i, k, c)) {
                        currentTileList[i][k + 1] = currentTileList[i][k];
                        currentTileList[i][k] = null;
                        k++;
                    }
                }
            }
        }
    }

    /* Description: returns true if Tile t at int i, int j in currentTileList
     *              is adjacent to a Tile with same value in regard with
     *              direction described by char c
     * Input: int i, int j that gives the position in currentTileList of the
     *        Tile we want to determine whether can merge, char c that
     *        determines the move we want to make on this Tile
     * Output: true if can merge the Tile t at int i, int j in currentTileList,
     *         false otherwise
     */
    private boolean canMerge(int i, int j, char c) {
        // if there is no tile, cannot merge
        if (currentTileList[i][j] == null) {
            return false;
        }
        // if there is no previous tile, cannot merge with tile
        if (nextTile(i, j, c) == null) {
            return false;
        }
        return nextTile(i, j, c).shouldMergeWith(currentTileList[i][j]);
    }

    /* Description: if can merge with previous, do it
     * Input: int i, int j that gives the position in currentTileList of the
     *        Tile we want to merge with previous if possible, char c that
     *        determines the move we want to make on this Tile
     * Output: void
     */
    private void aggregateBoard(char c) {
        if (c == 'w') {
            for (int i = NUM_TILE - 1; i >= 0; i--) {
                for (int j = 0; j < NUM_TILE; j++) {
                    if (canMerge(i, j, c)) {
                        nextTile(i, j, c).mergeWith(currentTileList[i][j]);
                        currentTileList[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        } else if (c == 's') {
            for (int i = 0; i < NUM_TILE; i++) {
                for (int j = 0; j < NUM_TILE; j++) {
                    if (canMerge(i, j, c)) {
                        nextTile(i, j, c).mergeWith(currentTileList[i][j]);
                        currentTileList[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        } else if (c == 'a') {
            for (int j = 0; j < NUM_TILE; j++) {
                for (int i = 0; i < NUM_TILE; i++) {
                    if (canMerge(i, j, c)) {
                        nextTile(i, j, c).mergeWith(currentTileList[i][j]);
                        currentTileList[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        } else if (c == 'd') {
            for (int j = NUM_TILE - 1; j >= 0; j--) {
                for (int i = 0; i < NUM_TILE; i++) {
                    if (canMerge(i, j, c)) {
                        nextTile(i, j, c).mergeWith(currentTileList[i][j]);
                        currentTileList[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        }
    }

    /* Description: updates the currentTileList for the Tiles inside to match
     *              its new situation after the move described by char c when
     *              doing a legitimate move described by char c
     *              creates one new Tile and positions it randomly
     *              Displays nice animations for the Tiles to move smoothly
     *              on the board
     * Input: the char c that defines direction
     * Output: void
     */
    public void move(char c) {
        if (keyIsLegitimate(c)) {
            consolidateBoard(c);
            slideToNewBoard();
            aggregateBoard(c);
            slideToNewBoard();
            consolidateBoard(c);
            slideToNewBoard();
            updateCoordinates();
            addTile();
            numOfMoves++;
        }
    }

    /* Description: if the position of a Tile on the board is different than
     *              what is should be according to their positions on the
     *              currentTileList, the Tiles slide smoothly to this position
     *              on the screen
     * Input: NA
     * Output: NA
     */
    private void slideToNewBoard() {
        PennDraw.enableAnimation(TwoThousandFortyEight.NUMBER_OF_FRAMES);
        int[][] xDistanceToBePursued = new int[NUM_TILE][NUM_TILE];
        int[][] yDistanceToBePursued = new int[NUM_TILE][NUM_TILE];
        for (int i = 0; i < NUM_TILE; i++) {
            for (int j = 0; j < NUM_TILE; j++) {
                if (currentTileList[i][j] != null) {
                    xDistanceToBePursued[i][j] = X_COORD[j] -
                        currentTileList[i][j].getX();
                    yDistanceToBePursued[i][j] = Y_COORD[i] -
                        currentTileList[i][j].getY();
                }
            }
        }
        for (int k = 0; k < 20; k++) {
            for (int i = 0; i < NUM_TILE; i++) {
                for (int j = 0; j < NUM_TILE; j++) {
                    if (currentTileList[i][j] != null) {
                        currentTileList[i][j].addToX(
                                               xDistanceToBePursued[i][j] / 20);
                        currentTileList[i][j].addToY(
                                               yDistanceToBePursued[i][j] / 20);
                    }
                }
            }
            if (PennDraw.hasNextKeyTyped()) {
                return;
            }
            TwoThousandFortyEight.drawBackGround();
            draw();
            drawDashBoard();
            PennDraw.advance();
        }
    }

    /* Description: If the grid is full of number tiles and there is no move
     *              possible for the user then the game is over. returns true if
     *              no move possible, false otherwise
     * Input: None (apply it to a Board object)
     * Output: true if all adjcent Tiles have different values and the number of
     *         Tiles is already maximum, false otherwise
     */
    public boolean hasLost() {
        // if the board is not full, it is still possible to move
        if (numberOfTiles <= 15) {
            return false;
        }

        // if any two adjacent Tiles have the same score, it is possible to move
        for (int i = 0; i < NUM_TILE; i++) {
            for (int j = 0; j < NUM_TILE; j++) {
                // check if Tile on the top has same value
                if (i + 1 < NUM_TILE && currentTileList[i][j].shouldMergeWith(
                    currentTileList[i + 1][j])) {
                    return false;
                }

                // check if Tile on the right has same value
                if (j + 1 < NUM_TILE && currentTileList[i][j].shouldMergeWith(
                    currentTileList[i][j + 1])) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Description: If the user has managed to bring a 2048 tile onto the board
     *              (by merging 2 1048 tiles) then the user wins
     * Input: None (apply it to a Board object)
     * Output: true if the user has won, false otherwise
     */
    public boolean hasWon() {
        for (int i = 0; i < currentTileList.length; i++) {
            for (int j = 0; j < currentTileList[i].length; j++) {
                if (currentTileList[i][j] != null &&
                    currentTileList[i][j].getValue() >= 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public void drawDashBoard() {
        PennDraw.setFontSize(16);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(225, 560, "NumOfTiles:" + numberOfTiles);
        PennDraw.text(225, 600, "Moves:" + numOfMoves);
        PennDraw.filledRectangle(400, 575, 50, 30);
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.text(400, 575, "Replay");
    }

    /* Description: does the drawing in case we win
     * Input: void
     * Output: void
     */
    public void winDrawing() {
        PennDraw.setPenColor(255, 251, 239, 230);
        PennDraw.filledRectangle(250, 300, 275, 325);
        PennDraw.setFontSize(32);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(250, 350, "Congrations! You won!");
        PennDraw.setFontSize(16);
        PennDraw.text(250, 275, "Number of Moves:" + numOfMoves);
        PennDraw.filledRectangle(250, 175, 50, 30);
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.text(250, 175, "Replay");
    }

    /* Description: does the drawing in case we lose
     * Input: NA
     * Output: void
     */
    public void lossDrawing() {
        PennDraw.setPenColor(160, 14, 1, 230);
        PennDraw.filledRectangle(250, 300, 275, 325);
        PennDraw.setFontSize(32);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(250, 350, "You lost");
        PennDraw.setFontSize(16);
        PennDraw.text(250, 275, "Number of Moves:" + numOfMoves);
        PennDraw.filledRectangle(250, 175, 50, 30);
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.text(250, 175, "Replay");
    }
}
