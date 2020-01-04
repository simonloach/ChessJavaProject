package chess.pieces;

import chess.Alliance;
import chess.board.Board;
import chess.board.Move;

import java.util.Collection;

public class Rook extends Piece{

    Rook(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return null;
    }
}
