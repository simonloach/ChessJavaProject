package chess.board;

import chess.pieces.Piece;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destanationCoordinate;

    public Move(Board board, Piece movedPiece, int destanationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destanationCoordinate = destanationCoordinate;
    }

    public static final class MajorMove extends Move {
        public MajorMove(final Board board,
                         final Piece movedPiece,
                         final int destanationCoordinate) {
            super(board, movedPiece, destanationCoordinate);
        }
    }


    public static final class AttackMove extends Move {
        final Piece attackedPiece;

        public AttackMove(final Board board,
                          final Piece movedPiece,
                          final int destanationCoordinate,
                          final Piece attackedPiece) {
            super(board, movedPiece, destanationCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }


}
