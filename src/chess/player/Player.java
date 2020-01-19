package chess.player;

import chess.Alliance;
import chess.board.Board;
import chess.board.Move;
import chess.pieces.King;
import chess.pieces.Piece;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    private final boolean isInCheck;

    protected Player(final Board board,
                     final Collection<Move> legalMoves,
                     final Collection<Move> opponentMoves) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = ImmutableList.copyOf(Iterables.concat(legalMoves, calculateKingCastles(legalMoves, opponentMoves))); // liczenie ruchow przeciwnika
        this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty(); // przekazujedo calculateattacks coordy króla i ruchy oponenta
    }

    public King getPlayerKing() {
        return this.playerKing;
    }

    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    protected static Collection<Move> calculateAttacksOnTile(int piecePosition, Collection<Move> moves) { //sprawdzam czy dla kazdego ruchu opponenta, jesli ktorys z ruchow overlapuje z krolem to znaczy ze jest in check
        final List<Move> attackMoves = new ArrayList<>();
        for (final Move move : moves) {
            if (piecePosition == move.getDestinationCoordinate()) {
                attackMoves.add(move);
            }
        }
        return ImmutableList.copyOf(attackMoves);
    }

    protected King establishKing() {
        for (final Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Should not reach here, not a valid boaard");
    }

    public boolean isMoveLegal(final Move move) {
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck() {
        return this.isInCheck;
    }

    public boolean isInCheckMate() {
        return this.isInCheck && !hasEscapeMoves();
    }

    public boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }

    protected boolean hasEscapeMoves() { // checks if kings has room to escape, by checking all of them on the imaginary board
        for (final Move move : this.legalMoves) {
            final MoveTransition transition = makeMove(move);
            if (transition.getMoveStatus().isDone()) {
                return true;
            }
        }
        return false; // if none are possible returns false
    }

    public boolean isCastled() {
        return false;
    }

    public MoveTransition makeMove(final Move move) {
        System.out.println("MAKEMOVE INITIATED");
        if (!isMoveLegal(move)) {
            System.out.println("BAD MOVE");
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE); // jak ruch jest niemozliwy, zwroc ten sam board, i wyrzuc illegal move
        }
        System.out.println("GOOD MOVE");
        final Board transitionBoard = move.execute();
        System.out.println("EXECUTED");
        final Collection<Move> kingAttacks = Player.calculateAttacksOnTile(transitionBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(), // wez pozycje krola przeciwnika(tak naprawde swoja) i sprawdz czy dostanie
                transitionBoard.currentPlayer().getLegalMoves());
        if (!kingAttacks.isEmpty()) {
            System.out.println("KING UNDER ATTACK");
            return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK); // jesli sa ataki na krola, tez zwroc ten sam board, i powiedz graczowi ze ma checka
        }
        System.out.println("RETURNING MOVE TRANSITION");
        return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
    }

    public abstract Collection<Piece> getActivePieces();

    public abstract Alliance getAlliance();

    public abstract Player getOpponent();

    protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals);

}
