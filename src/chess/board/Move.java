package chess.board;


import chess.pieces.Piece;
import chess.board.Board.Builder;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    protected final int destinationCoordinate;
    public static final Move NULL_MOVE = new NullMove();

    public Move(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    private int getCurrentCoordinate() {
        return this.getMovedPiece().getPiecePosition();
    }

    public Board execute() {
        final Builder builder = new Builder();
        for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
            //todo HASHCODE AND EQUALS FOR PIECES
            if (!this.movedPiece.equals(piece)) {
                builder.setPiece(piece); //for tych ktorych nie ruszalismy, poprostu je wstaw
            }
        }
        for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece); //for enemy pieces tez je wrzuc do boarda
        }
        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
        return builder.build();
    }

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

    public static class AttackMove extends Move {
        final Piece attackedPiece;

        public AttackMove(final Board board,
                          final Piece movedPiece,
                          final int destanationCoordinate,
                          final Piece attackedPiece) {
            super(board, movedPiece, destanationCoordinate);
            this.attackedPiece = attackedPiece;
        }

    }

    public static final class PawnMove extends Move {
        public PawnMove(final Board board,
                        final Piece movedPiece,
                        final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static class PawnAttackMove extends AttackMove {
        public PawnAttackMove(final Board board,
                              final Piece movedPiece,
                              final int destanationCoordinate,
                              final Piece attackedPiece) {
            super(board, movedPiece, destanationCoordinate, attackedPiece);
        }
    }

    public static final class PawnEnPassantAttackMove extends PawnAttackMove {
        public PawnEnPassantAttackMove(final Board board,
                                       final Piece movedPiece,
                                       final int destanationCoordinate,
                                       final Piece attackedPiece) {
            super(board, movedPiece, destanationCoordinate, attackedPiece);
        }
    }

    public static final class PawnJump extends Move {
        public PawnJump(final Board board,
                        final Piece movedPiece,
                        final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }
    static abstract class CastleMove extends Move {
        public CastleMove(final Board board,
                          final Piece movedPiece,
                          final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }
    public static final class KingSideCastleMove extends CastleMove {
        public KingSideCastleMove(final Board board,
                                  final Piece movedPiece,
                                  final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }
    public static final class QueenSideCastleMove extends CastleMove {
        public QueenSideCastleMove(final Board board,
                                   final Piece movedPiece,
                                   final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }
    public static final class NullMove extends Move {

        public NullMove() {
            super(null, null, -1);
        }

        @Override
        public Board execute(){
            throw new RuntimeException("cannot execute the NULLMOVE!");
        }
    }
    public static class MoveFactory {
        private MoveFactory(){
            throw new RuntimeException("Nieinstancjonowalna!");
        }
        public static Move createMove(final Board board,
                                      final int currentCoordinate,
                                      final int destinationCoordinate){
            for(final Move move : board.getAllLegalMoves()){
                if(move.getCurrentCoordinate() == currentCoordinate && move.getDestinationCoordinate() == destinationCoordinate){
                    return move;
                }
            }
            return NULL_MOVE;
        }
    }


}
