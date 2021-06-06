package lld.chess;

public class StartGame {

    public static void main(String[] args) {

        Person p1 = new Person();
        Person p2 = new Person();

        Player player1 = new Player(p1, true);
        Player player2 = new Player(p2, false);

        Game game = new Game(2);
        game.initialize(player1, player2);

        while (game.isEnd()) {
            game.playerMove(player1, 1, 4, 3, 4);
            game.playerMove(player2, 6, 4, 4, 4);

        }
    }

}
