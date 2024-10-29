import java.util.Scanner;

public class Tournament {
    public static void main(String[] args) {
        printWelcome(); //welcome user
        Scanner scanner = new Scanner(System.in); // initialize scanner
        String player1 = enterName("first"); //prompts user for players names
        String player2 = enterName("second");
        introducePlayers(player1, player2);
        String tournamentWinner = playGame(scanner, player1, player2);
        printCongratulations(tournamentWinner);
    }
    //playGame accepts Scanner object and the names of two players. It returns name of the winner of this game.
    public static String playGame(Scanner scanner, String player1, String player2)
    {
        int gameScore1 = 0;
        int gameScore2 = 0;
        int match = 1;
        while (gameScore1 < 2 && gameScore2 < 2) { //continue loop when neither player has reached a score of 2.
            System.out.printf("Playing game #%d", match);
            System.out.println();
            int[] points = playRound(scanner, player1, player2);
            if (points[0] > points[1]) {
                System.out.printf("%s won game #%d", player1, match);
                gameScore1++;
            } else {
                System.out.printf("%s won game #%d", player2, match);
                gameScore2++;
            }
            match++;
            printTournamentScores(player1, player2, gameScore1, gameScore2);
        }
        return (gameScore1 > gameScore2) ? player1 : player2;
    }
    //playRound repeatedly asks the user to input the winner of the next point until the game is over.
    //playRound detects invalid input and gives feedback.
    public static int[] playRound(Scanner scanner, String player1, String player2)
    {
        String p1 = player1.substring(0, 1).toUpperCase();
        String p2 = player2.substring(0, 1).toUpperCase();
        int points1 = 0, points2 = 0;
        while (true) {
            System.out.printf("Winner of the next point (%s for %s / %s for %s): ",
                    p1, player1, p2, player2);
            String pointWinner = scanner.next().toUpperCase();
            if (pointWinner.equals(p1)) points1++;
            else if (pointWinner.equals(p2)) points2++;
            else {
                System.out.println("Invalid input. Please enter again.");
                continue;
            }
            printGameScores(player1, player2, points1, points2);
            if (isGameOver(points1, points2)) break;
        }
        return new int[]{points1, points2};
    }
    public static boolean isGameOver(int points1, int points2) { //boolean to control to end game when 1) one player wins 7 points or 2) one player wins 3 points while the other has 0 points.
        final int WINNING_FINAL_SCORE = 7;
        final int LEADING_SCORE = 3;
        final int LOSING_SCORE = 0;
        return points1 == WINNING_FINAL_SCORE || points2 == WINNING_FINAL_SCORE ||
                (points1 == LEADING_SCORE && points2 == LOSING_SCORE) || (points2 == LEADING_SCORE
                && points1 == LOSING_SCORE);
    }
    public static void printWelcome() {
        stars();
        System.out.println("Welcome to The All NorthWest Racquet Ball Tournament");
        stars();
    }
    public static String enterName(String order) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter the name of the %s player: ", order);
        return scanner.nextLine();
    }
    public static void introducePlayers(String p1, String p2) {
        System.out.printf("Today's Players: %s and %s", p1, p2);
        System.out.println();
        stars();
    }
    // printGameScores accepts the names and points of two players and prints the scores.
    public static void printGameScores(String player1, String player2, int point1,
                                       int point2) {
        System.out.printf("Game Score %s %s", player1, player2);
        System.out.println();
        System.out.printf(" %d", point1);
        int i = 0;
        while (i < player1.length()) {
            System.out.print(" ");
            i++;
        }
        System.out.printf("%d", point2);
        System.out.println();
    }
    // printTournamentScores accepts names and game counts of the two players and print the scores
    public static void printTournamentScores(String player1, String player2, int
            score1, int score2) {
        System.out.println();
        stars();
        System.out.println("Tournament score (# of games won): "+player1+" "+score1+", "+player2+" "+score2);
                stars();
    }
    public static void printCongratulations(String tournamentWinner) {
        System.out.printf("Congratulations! %s won the tournament!",
                tournamentWinner);
        System.out.println();
        stars();
    }
    public static void stars() {

        System.out.println("******************************************************************");
    }
}