public class Player {
    private String name = "";
    private int score = 0;
    private Terrain[] collect = new Terrain[4];

    private Terrain terrain0 = new Terrain();
    private Terrain terrain1 = new Terrain();
    private Terrain terrain2 = new Terrain();
    private Terrain terrain3 = new Terrain();

    /**
     * Displays the tiles in the player's collection.
     */
    public void showTiles() {


        terrain0.generate();
        terrain1.generate();
        terrain2.generate();
        terrain3.generate();

        collect[0] = terrain0;
        collect[1] = terrain1;
        collect[2] = terrain2;
        collect[3] = terrain3;

        utils.p("   1. \n"+terrain0.print()+"\n");
        utils.p("   2. \n" + terrain1.print()+"\n");
        utils.p("   3. \n" + terrain2.print()+"\n");
        utils.p("   4. \n" + terrain3.print()+"\n");
    }

    /**
     * Plays the tile at the specified index.
     *
     * @param num The index of the tile to be played.
     * @return The tile's string representation.
     */
    public String playTile(int num) {
        return collect[num].print();
    }

    /**
     * Returns the terrain object at the specified index.
     *
     * @param num The index of the terrain object.
     * @return The terrain object.
     */
    public Terrain getTerrain(int num) {
        return collect[num];
    }

    /**
     * Constructs a Player object with the given name.
     *
     * @param nameGiven The name of the player.
     */
    public Player(String nameGiven) {
        name = nameGiven;
    }

    /**
     * Returns the name of the player.
     *
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds the specified score to the player's total score.
     *
     * @param addScore The score to be added.
     * @return The updated total score.
     */
    public int addPoints(int addScore) {
        score += addScore;
        return score;
    }

    /**
     * Displays the player's current score.
     */
    public int getPoints() {
        utils.p(score);
        return score;
    }
}
