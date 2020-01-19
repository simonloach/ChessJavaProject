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

public class King extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(final int piecePosition,
                final Alliance pieceAlliance) {
        super(PieceType.KING, piecePosition, pieceAlliance, true);
    }

    public King(final int piecePosition,
                final Alliance pieceAlliance,
                final boolean isFirstMove) {
        super(PieceType.KING, piecePosition, pieceAlliance, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int currentCandidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if (isFirstColumn(this.piecePosition, currentCandidateOffset) ||
                    isEighthColumn(this.piecePosition, currentCandidateOffset)) {
                continue;
            }

            if (BoardUtils.isValidCandidate(currentCandidateDestinationCoordinate)) {
                final Tile candidateDestinationTile = board.getTile(currentCandidateDestinationCoordinate);
                if (!candidateDestinationTile.isOccupied()) { // jesli nie occupied
                    legalMoves.add(new MajorMove(board, this, currentCandidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDest = candidateDestinationTile.getPiece();
                    final Alliance pieceAtDestAlliance = pieceAtDest.getPieceAlliance();

                    if (this.pieceAlliance != pieceAtDestAlliance) {
                        legalMoves.add(new MajorAttackMove(board, this, currentCandidateDestinationCoordinate, pieceAtDest));
                    }
                }
            }
        }


        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public King movePiece(Move move) {
        return new King(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }

    private static boolean isFirstColumn(final int currentPoss, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPoss] && ((candidateOffset == -9) || (candidateOffset == -1) || (candidateOffset == 7));
    }

    private static boolean isEighthColumn(final int currentPoss, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPoss] && ((candidateOffset == 9) || (candidateOffset == 1) || (candidateOffset == -7));
    }
}
