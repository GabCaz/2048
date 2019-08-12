/*  Name: Gabriel Cazaubieilh
 * Execution: TwoThousandFortyEight
 * Description: simulates the game 2048. The user presses one of four keys - w,
 *              s, a, d which represent the directions up, down, left, right.
 * Last changed:
 */

public class TwoThousandFortyEight {
    private static Board board = new Board();
    // says whether the game is finished or not
    private static boolean isFinished = false;
    public static final double PEN_RADIUS_FOR_BOARD = 0.005;
    public static final int NUMBER_OF_FRAMES = 100;

    /* Description: erases everything and draws the background
     * Input: None
     * Output: void
     */
    public static void drawBackGround() {
        PennDraw.clear();
        PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
        PennDraw.filledSquare(250, 250, 250);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setPenRadius(PEN_RADIUS_FOR_BOARD);
        PennDraw.square(250, 250, 250);
        PennDraw.setPenColor(PennDraw.WHITE);
        for (int i = 0; i < Board.NUM_TILE; i++) {
            for (int j = 0; j < Board.NUM_TILE; j++) {
                PennDraw.filledSquare(Board.X_COORD[i], Board.X_COORD[j],
                                      Tile.DIMENSION);
            }
        }
    }

    /* Description: once the mouse is pressed, restarts the game if the mouse
     *              was pressed on the dashboard replay button
     * Input: None
     * Output: void
     */
    public static void handleBoardReplay() {
        double x = PennDraw.mouseX();
        double y = PennDraw.mouseY();
        if (x >= 350 && x <= 450 && y >= 545 && y <= 605) {
            board = new Board();
        }
        // make sure no key is still in queue
        while (PennDraw.hasNextKeyTyped()) {
            PennDraw.nextKeyTyped();
        }
    }

    /* Description: once the mouse if pressed, restarts the game if the mouse
     *              was pressed on the outcome replay button
     * Input: None
     * Output: void
     */
    public static void handleReplay() {
        double x = PennDraw.mouseX();
        double y = PennDraw.mouseY();
        if (x >= 200 && x <= 300 && y >= 145 && y <= 205) {
            board = new Board();
            isFinished = false;
        }
        while (PennDraw.hasNextKeyTyped()) {
            PennDraw.nextKeyTyped();
        }
    }

    public static void main(String[] args) {
        PennDraw.setCanvasSize(550, 650);
        PennDraw.setXscale(-25, 525);
        PennDraw.setYscale(-25, 625);
        PennDraw.enableAnimation(NUMBER_OF_FRAMES);

        while (true) {
            drawBackGround();
            board.draw();
            board.drawDashBoard();

            if (PennDraw.mousePressed()) {
                handleBoardReplay();
            }

            if (board.hasWon()) {
                board.winDrawing();
                isFinished = true;
                // if click on Replay, load a new board and exit
                if (PennDraw.mousePressed()) {
                    handleReplay();
                }
            }

            if (board.hasLost()) {
                board.lossDrawing();
                isFinished = true;
                // if click on Replay, load a new board and exit
                if (PennDraw.mousePressed()) {
                    handleReplay();
                }
            }

            if (PennDraw.hasNextKeyTyped() && !isFinished) {
                char c = PennDraw.nextKeyTyped();
                board.move(c);
            }
            PennDraw.advance();
        }
    }
}
