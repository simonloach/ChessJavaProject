package chess.player;

import chess.board.Board;
import chess.board.Move;
import chess.pieces.King;
import chess.pieces.Piece;

import java.util.Collection;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;

    protected Player(final Board board,
                     final Collection<Move> legalMoves,
                     final Collection<Move> opponentMoves){
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
    }

    protected King establishKing(){
        for(final Piece piece : getActivePieces()){
            if(piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("Should not reach here, not a valid boaard");
    }
    protected abstract Collection<Piece> getActivePieces();

    ;
}
