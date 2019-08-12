/*  Name: Gabriel Cazaubieilh
 * Execution: none
 * Description: Tile class, corresponding to a Tile object, that implements
 *              the TileInterface to simulate a tile in 2048
 * Last changed: 21:56
 */

public class Tile implements TileInterface {
    // the x-location of the Tile
    private int x;
    // the y-location of the Tile
    private int y;
    // the value contained by the Tile
    private int value;
    // the dimension of a Tile (to draw)
    public static final int DIMENSION = 50;

    /* Description: constructor - creates a new Tile with a value of 2 (with
     *              probability of 0.7) or 4 (with probability 0.3)
     * Input: None
     * Output: None (but see description)
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        if (Math.random() < 0.7) {
            value = 2;
        } else {
            value = 4;
        }
    }

    /* Description: sets the color to draw Tiles depending on the value on Tile
     * Input: NA
     * Output: void
     */
    private void setTileColor() {
        if (value == 2) {
            PennDraw.setPenColor(255, 244, 201);
        } else if (value == 4) {
            PennDraw.setPenColor(255, 227, 130);
        } else if (value == 8) {
            PennDraw.setPenColor(255, 208, 56);
        } else if (value == 16) {
            PennDraw.setPenColor(255, 192, 5);
        } else if (value == 32) {
            PennDraw.setPenColor(252, 155, 85);
        } else if (value == 64) {
            PennDraw.setPenColor(249, 120, 27);
        } else if (value == 128) {
            PennDraw.setPenColor(247, 93, 79);
        } else if (value == 256) {
            PennDraw.setPenColor(244, 34, 19);
        } else if (value == 512) {
            PennDraw.setPenColor(242, 16, 0);
        } else if (value == 1024) {
            PennDraw.setPenColor(175, 61, 43);
        } else if (value == 2048) {
            PennDraw.setPenColor(114, 16, 1);
        }
    }

    /* Description: draws a Tile at its location and of size DIMENSION
     * Input: None (apply it to a Tile object)
     * Output: void
     */
    public void draw() {
        PennDraw.setPenRadius(TwoThousandFortyEight.PEN_RADIUS_FOR_BOARD / 3);
        setTileColor();
        PennDraw.filledSquare(x, y, DIMENSION);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.square(x, y, DIMENSION);
        PennDraw.text(x, y, "" + value);
    }

    /* Description: draws a Tile background at its location and of its DIMENSION
     *              in progressive way (as if the Tile would appear)
     * Input: None (apply it to a Tile object)
     * Output: void
     */
    public void drawApparition() {
        PennDraw.enableAnimation(TwoThousandFortyEight.NUMBER_OF_FRAMES);
        for (int k = 2; k <= 15; k++) {
            if (PennDraw.hasNextKeyTyped()) {
                return;
            }
            setTileColor();
            PennDraw.filledSquare(x, y, k * DIMENSION / 15.0);
            PennDraw.advance();
        }
    }

    /* Description: gets the value of a given Tile
     * Input: None (apply it to a Tile object)
     * Output: int (the value of a given Tile)
     */
    public int getValue() {
        return value;
    }

    /* Description: gets the x-coordinate of a given Tile
     * Input: None (apply it to a Tile object)
     * Output: the x-coordinate of a given Tile (int)
     */
    public int getX() {
        return x;
    }

    /* Description: gets the y-coordinate of a given Tile
     * Input: None (apply it to a Tile object)
     * Output: the y-coordinate of a given Tile (int)
     */
    public int getY() {
        return y;
    }

    /* Description: returns true if the given tile and the input Tile t have the
     *              same value
     * Input: a Tile t to know whether we should merge with
     * Output: true if has the same score as Tile t, false otherwise
     */
    public boolean shouldMergeWith(Tile t) {
        return this.value == t.value;
    }

    /* Description: Doubles the value of the current Tile (in practice, adds
     *              this.value and t.value)
     * Input: a Tile t to merge with the current Tile
     * Output: void
     */
    public void mergeWith(Tile t) {
        this.value = this.value + t.value;
    }

    /* Description: sets this Tile's y to newY
     * Input: int newY, the new Y we want to attribute to the Tile
     * Output: void
     */
    public void setY(int newY) {
        y = newY;
    }

    /* Description: sets this Tile's x to newX
     * Input: int newX, the new x we want to attribute to the Tile
     * Output: void
     */
    public void setX(int newX) {
        x = newX;
    }

    /* Description: adds addX to the x of the current Tile
     * Input: int addX, the number we want to add to x
     * Output: void
     */
    public void addToX(int addX) {
        x += addX;
    }

    /* Description: adds addY to the y of the current Tile
     * Input: int addY, the number we want to add to y
     * Output: void
     */
    public void addToY(int addY) {
        y += addY;
    }
}
