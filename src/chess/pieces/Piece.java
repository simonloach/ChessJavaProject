package chess.pieces;

import chess.Alliance;
import chess.board.Board;
import chess.board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;


    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }
    public int getPiecePosition() {return piecePosition;}

    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.isFirstMove = true; //TODO fix that
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board); //potem bedzie overridowane w subclassach

    public enum PieceType{
        PAWN("P"),
        KNIGHT("N"),
        BISHOP("B"),
        ROOK("R"),
        QUEEN("Q"),
        KING("K");

        private String pieceName;
        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }

        @Override
        public String toString(){
            return this.pieceName;
        }
    }
}
