import com.improving.uno.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerTests {

    Deck deck;
    Game game;

    @BeforeEach
    public void arrange_tests() {
        game = new Game(3);
        deck = new Deck();
    }

    @Test
    public void take_turns_should_return_same_color_different_number() throws Exception {
        game.setTopCard(new Card(Colors.Green, Faces.Five));
        List<Card> createHand = new ArrayList<>();
        createHand.add(new Card(Colors.Green, Faces.Two));
        createHand.add(new Card(Colors.Blue, Faces.Two));
        createHand.add(new Card(Colors.Red, Faces.Six));
        createHand.add(new Card(Colors.Red, Faces.Skip));
        createHand.add(new Card(Colors.Yellow, Faces.Reverse));
        createHand.add(new Card(Colors.Yellow, Faces.Three));
        createHand.add(new Card(Colors.Blue, Faces.One));

        var result = createHand.get(0);
        game.setCurrentPlayer(new Player(createHand, 1));
        game.getCurrentPlayer().takeTurn(game);

        assertEquals(result,game.getTopCard());
    }

    @Test
    public void take_turns_should_return_different_color_same_number() throws Exception {
        game.setTopCard(new Card(Colors.Green, Faces.Five));
        List<Card> createHand = new ArrayList<>();
        createHand.add(new Card(Colors.Blue, Faces.Five));
        createHand.add(new Card(Colors.Yellow, Faces.Six));
        createHand.add(new Card(Colors.Red, Faces.Six));
        createHand.add(new Card(Colors.Red, Faces.Skip));
        createHand.add(new Card(Colors.Yellow, Faces.Reverse));
        createHand.add(new Card(Colors.Yellow, Faces.Three));
        createHand.add(new Card(Colors.Blue, Faces.One));

        var result = createHand.get(0);
        game.setCurrentPlayer(new Player(createHand, 1));
        game.getCurrentPlayer().takeTurn(game);

        assertEquals(result,game.getTopCard());
    }

    @Test
    public void take_turns_should_return_wild() throws Exception {
        game.setTopCard(new Card(Colors.Green, Faces.Five));
        List<Card> createHand = new ArrayList<>();
        createHand.add(new Card(Colors.Wild, Faces.Wild));
        createHand.add(new Card(Colors.Blue, Faces.Two));
        createHand.add(new Card(Colors.Red, Faces.Six));
        createHand.add(new Card(Colors.Red, Faces.Skip));
        createHand.add(new Card(Colors.Yellow, Faces.Reverse));
        createHand.add(new Card(Colors.Yellow, Faces.Three));
        createHand.add(new Card(Colors.Blue, Faces.One));

        var result = createHand.get(0);
        game.setCurrentPlayer(new Player(createHand, 1));
        game.getCurrentPlayer().takeTurn(game);

        assertEquals(result,game.getTopCard());
    }

    @Test
    public void take_turns_should_return_with_red_color() throws Exception {
        game.setTopCard(new Card(Colors.Green, Faces.Five));
        List<Card> createHand = new ArrayList<>();
        createHand.add(new Card(Colors.Wild, Faces.Wild));
        createHand.add(new Card(Colors.Red, Faces.Six));
        createHand.add(new Card(Colors.Red, Faces.Six));
        createHand.add(new Card(Colors.Red, Faces.Skip));
        createHand.add(new Card(Colors.Yellow, Faces.Reverse));
        createHand.add(new Card(Colors.Yellow, Faces.Three));
        createHand.add(new Card(Colors.Blue, Faces.One));
        game.setColorIsPresent(1);
        var result = createHand.get(1);
        game.setCurrentPlayer(new Player(createHand,  1));
        game.getCurrentPlayer().takeTurn(game);
        game.getCurrentPlayer().takeTurn(game);

        assertEquals(result,game.getTopCard());
    }

    @Test
    public void take_turns_should_return_with_red_draw4() throws Exception {
        game.setTopCard(new Card(Colors.Green, Faces.Five));
        List<Card> createHand = new ArrayList<>();
        createHand.add(new Card(Colors.Wild, Faces.Draw4));
        createHand.add(new Card(Colors.Red, Faces.Seven));
        createHand.add(new Card(Colors.Red, Faces.Six));
        createHand.add(new Card(Colors.Red, Faces.Skip));
        createHand.add(new Card(Colors.Yellow, Faces.Reverse));
        createHand.add(new Card(Colors.Yellow, Faces.Three));
        createHand.add(new Card(Colors.Blue, Faces.One));
        game.setColorIsPresent(1);
        var result = createHand.get(1);
        game.setCurrentPlayer(new Player(createHand,  1));
        game.getCurrentPlayer().takeTurn(game);
        game.getCurrentPlayer().takeTurn(game);

        assertEquals(result,game.getTopCard());
    }

    @Test
    public void take_turns_should_draw_if_no_playable_card() throws Exception {
        game.setTopCard(new Card(Colors.Green, Faces.Five));
        List<Card> createHand = new ArrayList<>();
        createHand.add(new Card(Colors.Blue, Faces.Six));
        createHand.add(new Card(Colors.Yellow, Faces.Six));
        createHand.add(new Card(Colors.Red, Faces.Six));
        createHand.add(new Card(Colors.Red, Faces.Skip));
        createHand.add(new Card(Colors.Yellow, Faces.Reverse));
        createHand.add(new Card(Colors.Yellow, Faces.Three));
        createHand.add(new Card(Colors.Blue, Faces.One));

        game.setCurrentPlayer(new Player(createHand, 1));
        game.getCurrentPlayer().takeTurn(game);
        var result = game.getCurrentPlayer().getMyHand().size();

        assertEquals(8,result);
    }
    @Test
    public void play_should_remove_from_hand() throws Exception {
        game.setTopCard(new Card(Colors.Green, Faces.Five));

        game.setCurrentPlayer(new Player(game.createHand(),  1));
        var card = game.getCurrentPlayer().getMyHand().get(0);
        game.playCard(card, null);
        var result = game.getCurrentPlayer().getMyHand().size();

        assertEquals(6, result);
    }

    @Test
    public void take_turns_should_return_with_color_diff_set() throws Exception {
        game.setTopCard(new Card(Colors.Green, Faces.Five));
        List<Card> createHand = new ArrayList<>();
        createHand.add(new Card(Colors.Wild, Faces.Wild));
        createHand.add(new Card(Colors.Blue, Faces.Six));
        createHand.add(new Card(Colors.Red, Faces.Six));
        createHand.add(new Card(Colors.Red, Faces.Skip));
        createHand.add(new Card(Colors.Yellow, Faces.Reverse));
        createHand.add(new Card(Colors.Yellow, Faces.Three));
        createHand.add(new Card(Colors.Blue, Faces.One));
        game.setColorIsPresent(1);
        var result = createHand.get(1);
        game.setCurrentPlayer(new Player(createHand,  1));
        game.getCurrentPlayer().takeTurn(game);
        game.getCurrentPlayer().takeTurn(game);
        game.getCurrentPlayer().takeTurn(game);

        assertEquals(result,game.getTopCard());
    }


}
