package chess.board;

import chess.pieces.Piece;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;


public abstract class Tile {
    protected final int tileCoordinate;

    private static final Map<Integer, Tile> EMPTY_TILE_CACHE = createEmptyTilesMap();

    private static Map<Integer, Tile> createEmptyTilesMap() {

        final Map<Integer, Tile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap); //using guava library
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILE_CACHE.get(tileCoordinate); // if
    }

    private Tile(final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isOccupied();

    public abstract Piece getPiece();


    public static final class EmptyTile extends Tile {
        private EmptyTile(int coordinate) {
            super(coordinate);
        }

        @Override
        public boolean isOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile {
        private final Piece pieceOnTile;

        private OccupiedTile(final int tileCoordinate, final Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
