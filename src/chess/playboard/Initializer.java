package chess.playboard;

import chess.pieces.*;

/**
 * The ChessGame class is used for holding chess objects
 * which contains the board, pieces and handles alternating turns.
 */
public class Initializer {

    private TurnHandler chessBoard;
    private King player1King;
    private King player2King;

    /**
     * Create new instances of necessary properties.
     */
    public Initializer() {
        chessBoard = new TurnHandler();
        setupTeam(0, "player1");
        setupTeam(7, "player2");
    }

    /**
     * Sets up pieces for each player.
     * @param side Starting side of the player
     * @param player String of the player
     */
    private void setupTeam(int side, String player) {
        int one = (side > 0) ? -1: 1;
        int colIncerment = 0;

        // Rook
        Piece r1 = new Rook(player, new Location(side, colIncerment), this);
        Piece r2 = new Rook(player, new Location(side, 7-colIncerment), this);
        colIncerment += 1;

        // Knight
        Piece n1 = new Knight(player, new Location(side, colIncerment), this);
        Piece n2 = new Knight(player, new Location(side, 7-colIncerment), this);
        colIncerment += 1;

        // Bishop
        Piece b1 = new Bishop(player, new Location(side, colIncerment), this);
        Piece b2 = new Bishop(player, new Location(side, 7-colIncerment), this);
        colIncerment += 1;

        // King & Queen
        if (player.equalsIgnoreCase("player1")) {
            player1King = new King(player, new Location(side, colIncerment), this);
        } else {
            player2King = new King(player, new Location(side, colIncerment), this);
        }

        Piece q = new Queen(player, new Location(side, 7-colIncerment), this);

        // Pawns
        for (int i = 0; i < 8; i++) {
            Piece p = new Pawn(player, new Location(side + one, i), this);
        }
    }

    /**
     * Returns the ChessBoard.
     * @return The board object of the chess game.
     */
    public TurnHandler getChessBoard() {
        return chessBoard;
    }

    public King getPlayer1King() {
        return player1King;
    }

    public King getPlayer2King() {
        return player2King;
    }
}
