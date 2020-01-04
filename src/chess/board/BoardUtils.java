package chess.board;

public class BoardUtils {
    public static final boolean FIRST_COLUMN = null;
    public static final boolean SECOND_COLUMN = null;
    public static final boolean SEVENTH_COLUMN = null;
    public static final boolean EIGHTH_COLUMN = null;

    private BoardUtils(){
        throw new RuntimeException("Nie mozesz powolac tej klasy do zycia.");
    }
    public static boolean isValidCandidate(int candidateDestinationCoord) {
        return (0 <= candidateDestinationCoord && candidateDestinationCoord < 64);
    }
}
