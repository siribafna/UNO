import com.improving.*;
import com.improving.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTests {

    Deck deck;
  //  Hand hand;
    Game game;

    @BeforeEach
    public void arrange_tests() {
        game = new Game(3);
    }

    @Test
    public void game_is_playable_returns_1_for_normal_card() {
        game.setTopCard(new Card(Colors.Green, Faces.Two));
        Card card = new Card(Colors.Green, Faces.Skip);

        var result = game.isPlayable(card);
        assertEquals(1, result);
    }

    @Test
    public void game_is_playable_returns_1_for_wild_card() {
        Card card = new Card(Colors.Wild, Faces.Draw4);
        var result = game.isPlayable(card);
        assertEquals(0, result);
    }

    @Test
    public void game_is_playable_returns_1_for_color_is_1_and_wild_card() {
        Card card = new Card(Colors.Wild, Faces.Draw4);
        game.setColorIsPresent(1);
        var result = game.isPlayable(card);
        assertEquals(0, result);
    }

    @Test
    public void game_handles_weird_cards_with_draw4() {
        game.setTopCard(new Card(Colors.Wild, Faces.Draw4));
        game.handleWeirdCards();
        var result = game.getNextPlayer().getMyHand().size();
        assertEquals(11, result);
    }

    @Test
    public void game_handles_weird_cards_with_draw2() {
        game.setTopCard(new Card(Colors.Red, Faces.Draw2));
        game.handleWeirdCards();
        var result = game.getNextPlayer().getMyHand().size();
        assertEquals(9, result);
    }

    @Test
    public void game_handles_weird_cards_with_skip() {
        game.setTopCard(new Card(Colors.Red, Faces.Skip));
        game.handleWeirdCards();
        var result = game.getTurnEngine();
        assertEquals(1, result);
    }

    @Test
    public void game_handles_weird_cards_with_reverse() {
        game.setTopCard(new Card(Colors.Red, Faces.Reverse));
        game.setTurnEngine(2);
        game.handleWeirdCards();
        var turnNum = game.getTurnEngine();
        System.out.println(turnNum);
        boolean result = false;
        if (turnNum < 0) {
            result = true;
        }
        if (turnNum >= 0) {
            result = false;
        }
        assertTrue(result);
    }

}
