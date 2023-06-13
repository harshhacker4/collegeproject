public class PointsTable {
    private static final String HEADER_FORMAT = "+-----------+-----------+-----------+\n";
    private static final String ROW_FORMAT = "|   Round   |   %s   |   %s   |\n";
    private static final String DATA_FORMAT = "|    %2d     |   %3d   |   %3d   |\n";
    private static final String FOOTER_FORMAT = "+-----------+-----------+-----------+\n";

    public static void main(String[] args) {
        String player1Name = "Player A";
        String player2Name = "Player B";
        int totalRounds = 5;

        // Sample scores for illustration
        int[] player1Scores = {20, 35, 50, 65, 80};
        int[] player2Scores = {15, 30, 45, 60, 75};

        String pointsTable = generatePointsTable(player1Name, player2Name, totalRounds, player1Scores, player2Scores);
        System.out.println(pointsTable);
    }

    public static String generatePointsTable(String player1Name, String player2Name, int totalRounds,
                                             int[] player1Scores, int[] player2Scores) {
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER_FORMAT);
        sb.append(String.format(ROW_FORMAT, player1Name, player2Name));
        sb.append(HEADER_FORMAT);

        for (int round = 1; round <= totalRounds; round++) {
            int player1Score = (round <= player1Scores.length) ? player1Scores[round - 1] : 0;
            int player2Score = (round <= player2Scores.length) ? player2Scores[round - 1] : 0;
            sb.append(String.format(DATA_FORMAT, round, player1Score, player2Score));
        }

        sb.append(FOOTER_FORMAT);
        return sb.toString();
    }
}
