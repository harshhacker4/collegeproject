import java.util.Arrays;

public class Map {
    
    private static int boardSize = 10;
    private static final String MAP_START = "+----------";
    private static final String MAP_MID = "|%d%d%d       ";
    private static String finalMap = "";
    private static String presentMap = "";
    private static Terrain[][] mapArray;
    private static final int BASE_SCORE = 10;
    private static final int[] adjacentOffsets = {-1, 0, 1};

    /**
     * Constructor to initialize the board size and map array.
     * 
     * @param size The size of the board.
     */
    public Map(int size) {
        boardSize = size;
        mapArray = new Terrain[boardSize][boardSize];
    }
    
    /**
     * Generates the map of the game board.
     * 
     * @return The generated map as a string.
     */
    public String generateMap() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < boardSize; i++) {
            sb.append(MAP_START.repeat(boardSize)).append("+\n");
            
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < boardSize; k++) {
                    if (k != boardSize - 1)
                        sb.append(String.format(MAP_MID, i, j, k));
                    else
                        sb.append(String.format(MAP_MID, i, j, k)).append("|");
                }
                
                sb.append("\n");
            }
            
            if (i == boardSize - 1) {
                sb.append(MAP_START.repeat(boardSize)).append("+");
            }
        }
        
        finalMap = sb.toString();
        presentMap = finalMap;
        return presentMap;
    }

    /**
     * Updates the map with the given terrain at the specified position.
     * 
     * @param terrain The terrain object to be placed on the map.
     * @param row     The row index of the position.
     * @param column  The column index of the position.
     * @param player  The player who is updating the map.
     */
    public void place(Terrain terrain, int row, int column, Player player) {
        String toBeReplaced = Integer.toString(row) + Integer.toString(column) + "   ";
        String nameReplace = "";
        String adjust = " ";
        String playerName = player.getName();
        String nameAdjust = playerName.length() >= 5 ? playerName.substring(0, 5) : playerName + adjust.repeat(5 - playerName.length());
        
        char[] terrainCopy = Arrays.copyOf(terrain.getTerrain(), terrain.getTerrain().length);
        Terrain terrainObject = new Terrain(terrainCopy);
        mapArray[row][column] = terrainObject;
    

        for (int i = 0; i < 6; i++) {
            if (i < 5) {
                toBeReplaced = Integer.toString(row) + Integer.toString(i) + Integer.toString(column) + "       ";
                finalMap = finalMap.replace(toBeReplaced, terrain.getRow(i + 1));
            } else {
                nameReplace = Integer.toString(row) + Integer.toString(i) + Integer.toString(column) + "       ";
                finalMap = finalMap.replace(nameReplace, "  " + nameAdjust + "   ");
            }
            presentMap = finalMap.replaceAll("\\d", " ");
        }

        int score = calculateScore(row, column);
        player.addPoints(score);
    }

    /**
     * Calculates the score for placing terrain at the specified position.
     * 
     * @param row    The row index of the position.
     * @param column The column index of the position.
     * @return The calculated score.
     */
    private int calculateScore(int row, int column) {
        int score = BASE_SCORE;
        boolean[] check = new boolean[4];
        int addScore = utils.initialScore(adjacentOffsets, row, column, boardSize, 2, score, mapArray);
        
        for (int i : adjacentOffsets) {
            for (int j : adjacentOffsets) {
                if (i != j && !(i != 0 && j != 0) && isValidCell(row + i, column + j, boardSize)) {
                    if (!check[0] && row - 1 >= 0 && (mapArray[row - 1][column] != null)) {
                        score += mapArray[row][column].getNorth() == mapArray[row - 1][column].getSouth() ? 5 : -2;
                        check[0] = true;
                    }
                    if (!check[1] && row + 1 < boardSize && (mapArray[row + 1][column] != null)) {
                        score += mapArray[row][column].getSouth() == mapArray[row + 1][column].getNorth() ? 5 : -2;
                        check[1] = true;
                    }
                    if (!check[2] && column + 1 < boardSize && (mapArray[row][column + 1] != null)) {
                        score += mapArray[row][column].getEast() == mapArray[row][column + 1].getWest() ? 5 : -2;
                        System.out.println("forEast "+mapArray[row][column].getEast() + mapArray[row][column+1].getWest());
                        check[2] = true;
                    }
                    if (!check[3] && column - 1 >= 0 && (mapArray[row][column - 1] != null)) {
                        score += mapArray[row][column].getWest() == mapArray[row][column - 1].getEast() ? 5 : -2;
                        System.out.println("forWest "+mapArray[row][column].getWest() + mapArray[row][column-1].getEast());
                        check[3] = true;
                    }
                }
            }
        }
        int a =0, b=0;
        for(Terrain i[]: mapArray){
            for(Terrain j: i){
                utils.p(" a: "+a+" b: "+b+" "+j);
                b++;
            }
            a++;
        }
        
        return score - BASE_SCORE + addScore;
    }

    /**
     * Checks if the given row and column values represent a valid cell in the map.
     * 
     * @param row    The row index to be checked.
     * @param column The column index to be checked.
     * @param size   The size of the map.
     * @return True if the cell is valid, false otherwise.
     */
    private boolean isValidCell(int row, int column, int size) {
        return row >= 0 && row < size && column >= 0 && column < size;
    }

    /**
     * Displays the current map on the console.
     */
    public void draw() {
        System.out.println(presentMap);
    }

    public boolean isEmpty(int Xpos, int Ypos){
        return mapArray[Xpos][Ypos] == null;
    }

    public static Terrain[][] mapArray(){
        return mapArray;
    }
}
