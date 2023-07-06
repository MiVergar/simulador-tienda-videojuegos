package paquete;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testBuyGameAdmin() {
        // Arrange
        Main.inventory.clear();
        Game game = new Game("Test Game", 50000, "Test Genre", "Test Platform", 100);
        Main.AddGame(game);

        // Act
        boolean result = Main.BuyGameAdmin("Test Game", 50);

        // Assert
        assertTrue(result);
        assertEquals(150, game.amount);
        assertEquals(-1250000, Main.earnings);
    }

    @Test
    public void testBuyGameAdmin_NonExistentGame() {
        // Arrange
        Main.inventory.clear();

        // Act
        boolean result = Main.BuyGameAdmin("Nonexistent Game", 50);

        // Assert
        assertFalse(result);
    }

    // Agrega más métodos de prueba para cubrir otros escenarios

    @Test
    public void testSellGame() {
        // Arrange
        Main.inventory.clear();
        Game game = new Game("Test Game", 50000, "Test Genre", "Test Platform", 100);
        Main.AddGame(game);

        // Act
        boolean result = Main.SellGame("Test Game", 50);

        // Assert
        assertTrue(result);
        assertEquals(50, game.amount);
        assertEquals(2500000, Main.earnings);
    }

    // Agrega más métodos de prueba para cubrir otros escenarios
}
