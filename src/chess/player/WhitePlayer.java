package chess.player;

import chess.board.Board;
import chess.board.Move;
import chess.pieces.Piece;

import java.util.Collection;

public class WhitePlayer extends Player{
    public WhitePlayer(Board board,
                       Collection<Move> whiteStandardLegalMoves,
                       Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    protected Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }
}
