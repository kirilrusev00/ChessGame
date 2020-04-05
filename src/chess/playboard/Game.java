package chess.playboard;

import chess.pieces.*;

public class Game {

    private static final int STARTING_SIDE_OF_PLAYER1 = 0;
    private static final int STARTING_SIDE_OF_PLAYER2 = 7;
    private static final int BOARD_SIZE = 8;

    private TurnHandler turnHandler;
    private King kingOfPlayer1;
    private King kingOfPlayer2;

    public Game() {
        turnHandler = new TurnHandler();
        setupTeam(STARTING_SIDE_OF_PLAYER1, "player1");
        setupTeam(STARTING_SIDE_OF_PLAYER2, "player2");
    }

    private void setupTeam(int side, String player) {
        int direction = (side > 0) ? -1 : 1;
        int currentColumn = 0;

        Piece r1 = new Rook(player, new Location(side, currentColumn), this);
        Piece r2 = new Rook(player, new Location(side, BOARD_SIZE - 1 - currentColumn), this);
        currentColumn += 1;

        Piece n1 = new Knight(player, new Location(side, currentColumn), this);
        Piece n2 = new Knight(player, new Location(side, BOARD_SIZE - 1 - currentColumn), this);
        currentColumn += 1;

        Piece b1 = new Bishop(player, new Location(side, currentColumn), this);
        Piece b2 = new Bishop(player, new Location(side, BOARD_SIZE - 1 - currentColumn), this);
        currentColumn += 1;

        if (player.equalsIgnoreCase("player1")) {
            kingOfPlayer1 = new King(player, new Location(side, currentColumn), this);
        } else {
            kingOfPlayer2 = new King(player, new Location(side, currentColumn), this);
        }

        Piece q = new Queen(player, new Location(side, BOARD_SIZE - 1 - currentColumn), this);

        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece p = new Pawn(player, new Location(side + direction, i), this);
        }
    }

    public TurnHandler getTurnHandler() {
        return turnHandler;
    }

    public King getKingOfPlayer1() {
        return kingOfPlayer1;
    }

    public King getKingOfPlayer2() {
        return kingOfPlayer2;
    }
}
