package chess.board;


import chess.pieces.Piece;
import chess.board.Board.Builder;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    public Move(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public int getDestinationCoordinate(){
        return this.destinationCoordinate;
    }

    public abstract Board execute();

    public static final class MajorMove extends Move {
        public MajorMove(final Board board,
                         final Piece movedPiece,
                         final int destanationCoordinate) {
            super(board, movedPiece, destanationCoordinate);
        }

        @Override
        public Board execute() {
            return board;
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

        @Override
        public Board execute() {
            final Builder builder = new Builder();
            for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                //todo HASHCODE AND EQUALS FOR PIECES
                if(!this.movedPiece.equals(piece)){
                    builder.setPiece(piece); //for tych ktorych nie ruszalismy, poprostu je wstaw
                }
            }
            for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece); //for enemy pieces tez je wrzuc do boarda
            }
            //move the moved piece
            builder.setPiece(null);
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
        }
    }


}
