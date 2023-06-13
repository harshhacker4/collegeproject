public class utils {
    private static boolean addAdjScore;
    public static void p(Object obj) {
        System.out.println(obj);
    }

    public static void np(Object obj) {
        System.out.print(obj);
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidCell(int x, int y, int SIZE) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    public static int initialScore(int[] adjArray, int row, int column, int SIZE, int ADJACENT_EMPTY_SCORE, int score, Terrain[][] mapArray){
        for(int i: adjArray){
            for(int j: adjArray){
                    if(i!=j && !(i!=0 && j!=0) && utils.isValidCell(row+i, column+j, SIZE)){
                    addAdjScore = mapArray[row+i][column+j] == null ? true : false;
                    if(addAdjScore){
                    score+=ADJACENT_EMPTY_SCORE;
                    }
                }else{
                    continue;
                }
            }
        }
        return score;
    } 

    public static boolean choiceCheck(int choice, int row, int column, Terrain[][] mapArray){
        if(choice>0 && choice<5 && mapArray[row][column]==null && row>=0 && column>=0 && row<5 && column<5){
            return true;
        }else
        return false;
    }

}

