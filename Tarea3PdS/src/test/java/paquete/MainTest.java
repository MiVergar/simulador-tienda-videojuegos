package paquete;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testBuyGameAdmin() {
    	
        Main.inventory.clear();
        Main.earnings = 0;
        Game game = new Game("Test Game", 50000, "Test Genre", "Test Platform", 100);
        Main.AddGame(game);

        boolean result = Main.BuyGameAdmin("Test Game", 50);

        assertTrue(result);
        assertEquals(150, game.amount);
        assertEquals(-1250000, Main.earnings);
    }

    @Test
    public void testBuyGameAdminNonExistentGame() {

        Main.inventory.clear();

        boolean result = Main.BuyGameAdmin("Nonexistent Game", 50);

        assertFalse(result);
    }

    @Test
    public void testSellGame() {

        Main.inventory.clear();
        Main.earnings = 0;
        Game game = new Game("Test Game", 50000, "Test Genre", "Test Platform", 100);
        Main.AddGame(game);

        boolean result = Main.SellGame("Test Game", 50);

        assertTrue(result);
        assertEquals(50, game.amount);
        assertEquals(2500000, Main.earnings);
    }
    
    @Test
    public void testSellGameNonExistentGame() {

        Main.inventory.clear();

        boolean result = Main.SellGame("Nonexistent Game", 50);

        assertFalse(result);
    }
    
    @Test
    public void testBuyGameClient() {
    	
        Main.inventory.clear();
        Main.earnings = 0;
        Game game = new Game("Test Game", 50000, "Test Genre", "Test Platform", 100);
        Main.AddGame(game);

        boolean result = Main.BuyGameClient("Test Game", 50);

        assertTrue(result);
        assertEquals(50, game.amount);
        assertEquals(2500000, Main.earnings);
    }

    @Test
    public void testBuyGameClientNonExistentGame() {

        Main.inventory.clear();

        boolean result = Main.BuyGameAdmin("Nonexistent Game", 50);

        assertFalse(result);
    }
}
