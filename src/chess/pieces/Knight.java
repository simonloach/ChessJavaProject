package chess.pieces;

import chess.Alliance;
import chess.board.Board;
import chess.board.BoardUtils;
import chess.board.Move;
import chess.board.Move.*;
import chess.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {
    private final static int[] CANDIDATES = {-17, -15, -10, 6, -6, 10, 15, 17}; // mozliwe ruchy nie uwzgledniajac wielkosci mapy, ani czy dana kratka nie jest zajmowana przez inna figure

    public Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.KNIGHT, piecePosition, pieceAlliance);
    }


    @Override
    public Collection<Move> calculateLegalMoves(Board board) { // tutaj jest collection bo jest ogolniej

        final List<Move> legalMoves = new ArrayList<>(); //ale tutaj uzywamy podklasy collection czyli listy ktora ma kolejnosc i indexy

        for (final int currentCandidate : CANDIDATES) { //to jest taki foreach
            int candidateDestinationCoordinate = this.piecePosition + currentCandidate; // zmienna ktora sprawdza w ifie czy dane pole jest git
            if (BoardUtils.isValidCandidate(candidateDestinationCoordinate)) { //odwolanie do funkcji ktora sprawdza czy nie wywalilo nam poza boarda (0-63 accepted only)
                if (isFirstColumn(this.piecePosition, currentCandidate) ||
                        isSecondColumn(this.piecePosition, currentCandidate) ||
                        isSeventhColumn(this.piecePosition, currentCandidate) ||
                        isEighthColumn(this.piecePosition, currentCandidate)) { //tutaj jest taki wielki warunek sprawdzajacy czy kon nie znajduje sie w kolumnach 1 2 7 8 bo wtedy inaczej zasady dzialaja
                    continue;
                }

                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate); // pobieram Tile na ktory chce isc
                if (!candidateDestinationTile.isOccupied()) { // jesli nie occupied
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDest = candidateDestinationTile.getPiece();
                    final Alliance pieceAtDestAlliance = pieceAtDest.getPieceAlliance();

                    if (this.pieceAlliance != pieceAtDestAlliance) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDest));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Knight movePiece(Move move) {
        return new Knight( move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public String toString(){
        return PieceType.KNIGHT.toString();
    }

    private static boolean isFirstColumn(final int currentPoss, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPoss] && ((candidateOffset == -17) || (candidateOffset == -10) || (candidateOffset == 6) || (candidateOffset == 15));

    }

    private static boolean isSecondColumn(final int currentPoss, final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPoss] && ((candidateOffset == -10) || (candidateOffset == 6));

    }

    private static boolean isSeventhColumn(final int currentPoss, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPoss] && ((candidateOffset == -6) || (candidateOffset == 10));

    }

    private static boolean isEighthColumn(final int currentPoss, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPoss] && ((candidateOffset == -15) || (candidateOffset == -6) || (candidateOffset == 10) || (candidateOffset == 17));

    }
}
