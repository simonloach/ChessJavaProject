package chess.pieces;

import chess.Alliance;
import chess.board.Board;
import chess.board.BoardUtils;
import chess.board.Move;
import chess.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Queen extends Piece {
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};

    public Queen(final int piecePosition,
                 final Alliance pieceAlliance) {
        super(PieceType.QUEEN, piecePosition, pieceAlliance, true);
    }
    public Queen(final int piecePosition,
                 final Alliance pieceAlliance,
                 final boolean isFirstMove) {
        super(PieceType.QUEEN, piecePosition, pieceAlliance, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candindateDestinationCoordinate = this.piecePosition;
            while (BoardUtils.isValidCandidate(candindateDestinationCoordinate)) {
                if (isFirstColumn(candindateDestinationCoordinate, candidateCoordinateOffset) ||
                        isEighthColumn(candindateDestinationCoordinate, candidateCoordinateOffset)) {
                    break;
                }
                candindateDestinationCoordinate += candidateCoordinateOffset;
                if (BoardUtils.isValidCandidate(candindateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candindateDestinationCoordinate);
                    if (!candidateDestinationTile.isOccupied()) {
                        legalMoves.add(new Move.MajorMove(board, this, candindateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDest = candidateDestinationTile.getPiece();
                        final Alliance pieceAtDestAlliance = pieceAtDest.getPieceAlliance();

                        if (this.pieceAlliance != pieceAtDestAlliance) {
                            legalMoves.add(new Move.MajorAttackMove(board, this, candindateDestinationCoordinate, pieceAtDest));
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Queen movePiece(Move move) {
        return new Queen( move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public String toString(){
        return PieceType.QUEEN.toString();
    }

    private static boolean isFirstColumn(final int currentPossition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPossition] && (candidateOffset == -9 || candidateOffset == 7 || candidateOffset == -1);
    }

    private static boolean isEighthColumn(final int currentPossition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPossition] && (candidateOffset == -7 || candidateOffset == 9 || candidateOffset == 1);
    }

}
