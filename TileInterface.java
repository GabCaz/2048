/*  Name: Gabriel Cazaubieilh
 * Execution: none (INTERFACE)
 * Description: the API for the Tile class
 * Last changed: 21:27
 */

public interface TileInterface {

    /* Description: draws a Tile at its location and of DIMENSION
     * Input: None (apply it to a Tile object)
     * Output: void
     */
    void draw();

    /* Description: draws a Tile at its location and of its DIMENSION in a
     *              progressive way (as if the Tile would appear)
     * Input: None (apply it to a Tile object)
     * Output: void
     */
    void drawApparition();

    /* Description: gets the value of a given Tile
     * Input: None (apply it to a Tile object)
     * Output: int (the value of a given Tile)
     */
    int getValue();

    /* Description: returns true if the given tile and the input Tile t have the
     *              same value
     * Input: a Tile t to know whether we should merge with
     * Output: true if has the same score as Tile t, false otherwise
     */
    boolean shouldMergeWith(Tile t);

    /* Description: Doubles the value of the current Tile (in practice, adds
     *              this.value and t.value)
     * Input: a Tile t to merge with the current Tile
     * Output: void
     */
    void mergeWith(Tile t);

    /* Description: gets the x-coordinate of a given Tile
     * Input: None (apply it to a Tile object)
     * Output: the x-coordinate of a given Tile (int)
     */
    int getX();

    /* Description: gets the y-coordinate of a given Tile
     * Input: None (apply it to a Tile object)
     * Output: the y-coordinate of a given Tile (int)
     */
     int getY();

    /* Description: sets this Tile's y to newY
     * Input: int newY, the new y we want to attribute to the Tile
     * Output: void
     */
    void setY(int newY);

    /* Description: sets this Tile's x to newX
     * Input: int newX, the new x we want to attribute to the Tile
     * Output: void
     */
    void setX(int newX);

    /* Description: adds addX to the x of the current Tile
     * Input: int addX, the number we want to add to x
     * Output: void
     */
    void addToX(int addX);

    /* Description: adds addY to the y of the current Tile
     * Input: int addY, the number we want to add to y
     * Output: void
     */
    void addToY(int addY);
}
