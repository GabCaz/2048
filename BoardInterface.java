/* Name: Gabriel Cazaubieilh
 * Execution: none (INTERFACE)
 * Description: the API for the Board class
 * Last changed: 22:23
 */

public interface BoardInterface {

    /* Description: returns true if the user has won, false otherwise
     *              ("If the user has managed to bring a 2048 tile onto board
     *              (by merging 2 1048 tiles) then the user wins")
     * Input: None (apply it to a Board object)
     * Output: true if the user has won, false otherwise
     */
    boolean hasWon();

    /* Description: If the grid is full of number tiles and there is no move
     *              possible for the user then the game is over. returns true if
     *              no move possible, false otherwise
     * Input: None (apply it to a Board object)
     * Output: true if all adjcent Tiles have different values and the number of
     *         Tiles is already maximum, false otherwise
     */
    boolean hasLost();

    /* Description: draws the current board
     * Input: void
     * Output: void
     */
    void draw();

    /* Description: updates the currentTileList for the Tiles inside to match
     *              its new situation after the move described by char c when
     *              doing a legitimate move described by char c
     *              creates one new Tile and positions it randomly
     *              Displays nice animations for the Tiles to move smoothly
     *              on the board
     * Input: the char c that defines direction
     * Output: void
     */
    void move(char c);

    /* Description: does the drawing in case we win
     * Input: NA
     * Output: void
     */
    void winDrawing();

    /* Description: does the drawing in case we lose
     * Input: NA
     * Output: void
     */
    void lossDrawing();
}
