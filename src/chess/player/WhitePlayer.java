package chess.player;

import chess.Alliance;
import chess.board.Board;
import chess.board.Move;
import chess.board.Tile;
import chess.pieces.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WhitePlayer extends Player{
    public WhitePlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals) {
        final List<Move> kingCastles = new ArrayList<>();
        if( this.playerKing.isFirstMove() && !this.isInCheck() ){
            //white king side castle
            if(  !this.board.getTile(61).isOccupied()  &&  !this.board.getTile(62).isOccupied() ){
                final Tile rookTile = this.board.getTile(63);
                if(rookTile.isOccupied() && rookTile.getPiece().isFirstMove()){
                    if( Player.calculateAttacksOnTile(61, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnTile(62, opponentLegals).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()){
                        //TODO ADD A CASTLEMOVE!
                        kingCastles.add(null);
                    }
                }
            }
            //white queen side castl
            if(  !this.board.getTile(59).isOccupied()  &&
                    !this.board.getTile(58).isOccupied() &&
                    !this.board.getTile(57).isOccupied() ){
                final Tile rookTile = this.board.getTile(56);
                if(rookTile.isOccupied() && rookTile.getPiece().isFirstMove()){
                    //TODO ADD A CASTLEMOVE
                    kingCastles.add(null);
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }
}
