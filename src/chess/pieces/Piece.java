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


    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.isFirstMove = true; //TODO fix that
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board); //potem bedzie overridowane w subclassach

    public boolean isFirstMove() {
        return this.isFirstMove;
    }
}
