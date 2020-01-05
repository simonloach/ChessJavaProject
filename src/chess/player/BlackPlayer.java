package chess.player;

import chess.board.Board;
import chess.board.Move;
import chess.pieces.Piece;

import java.util.Collection;

public class BlackPlayer extends Player {

    public BlackPlayer(Board board,
                       Collection<Move> whiteStandardLegalMoves,
                       Collection<Move> blackStandardLegalMoves) {

        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    @Override
    protected Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }
}
