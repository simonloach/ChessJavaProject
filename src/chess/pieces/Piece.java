package chess.pieces;

import chess.Alliance;
import chess.board.Board;
import chess.board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;
    protected final PieceType pieceType;
    private final int cachedHashCode;

    public PieceType getPieceType() {
        return pieceType;
    }

    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }

    public int getPiecePosition() {
        return piecePosition;
    }

    Piece(final PieceType pieceType,
          final int piecePosition,
          final Alliance pieceAlliance,
          final boolean isFirstMove) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.isFirstMove = isFirstMove;
        this.cachedHashCode = computeHashCode();
    }

    public int computeHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstMove ? 1 : 0);
        return result;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) { //if referentialy equal
            return true;
        }
        if (!(other instanceof Piece)) {
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return (piecePosition == otherPiece.getPiecePosition()) && (pieceType == otherPiece.getPieceType()) &&
                (pieceAlliance == otherPiece.getPieceAlliance()) && (isFirstMove == otherPiece.isFirstMove());
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board); //potem bedzie overridowane w subclassach

    public abstract Piece movePiece(Move move);

    public enum PieceType {
        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };

        private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();

        public abstract boolean isRook();

    }
}
