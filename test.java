import java.util.Scanner;

public class test {
    private static Scanner scanner = new Scanner(System.in);
    private static final int SIZE = 5;
    public static void main(String[] args) {
        Map board = new Map(SIZE);
        Player playerA = new Player(args[0]);
        Player playerB = new Player(args[1]);
        int rounds = Integer.parseInt(args[2]);

        playGame(playerA, playerB, rounds, board);
    }

    public static void playGame(Player player1, Player player2, int rounds, Map board) {
        int count = 0, countMain = 0;
        
        
        while(countMain <rounds){
            count=0;
            board.generateMap();
            boolean isStart = false;
            utils.p("Round "+countMain+"\n");
            while (count < 25) {
                Player currentPlayer;
                if (countMain == 0) {
                    currentPlayer = (count % 2 == 0) ? player1 : player2;
                } else {
                    if(!isStart){
                    currentPlayer = (player1.getPoints() > player2.getPoints()) ? player1 : player2;
                    isStart = true;
                }else
                currentPlayer = (count % 2 == 0) ? player1 : player2;
                }
                
                
                utils.p(String.format("Tiles for %s below. Please choose (1 - 4) \n", currentPlayer.getName()));
                currentPlayer.showTiles();

                utils.p("Choose a tile: ");
                int choice = scanner.nextInt();

                utils.p("Enter the row: ");
                int row = scanner.nextInt();
                utils.p("Enter the column: ");
                int column = scanner.nextInt();
                Terrain selectedTerrain = currentPlayer.getTerrain(choice-1);
                board.place(selectedTerrain, row, column, currentPlayer);
                utils.clearConsole();
                utils.p(String.format("%s has scored %d points by now. %s has scored %d points by now.", player1.getName(), player1.getPoints(), player2.getName(), player2.getPoints())
                );
                board.draw();

                count++;
            }
            countMain++;
        }
    }

}
