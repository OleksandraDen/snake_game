package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class GamePanelTest {
    private GamePanel gamePanel;

    @BeforeEach
    void setUp() {
        gamePanel = new GamePanel();
    }

    @Test
    void testGameStartsRunning() {
        assertTrue(gamePanel.running, "Гра повинна початися у стані running = true");
    }

    @Test
    void testAppleGenerationWithinBounds() {
        gamePanel.newApple();
        assertTrue(gamePanel.appleX >= 0 && gamePanel.appleX < gamePanel.screen_width,
                "Координата X яблука повинна бути в межах екрану");
        assertTrue(gamePanel.appleY >= 0 && gamePanel.appleY < gamePanel.screen_height,
                "Координата Y яблука повинна бути в межах екрану");
    }

    @Test
    void testSnakeMoves() {
        int initialX = gamePanel.x[0];
        int initialY = gamePanel.y[0];

        gamePanel.move();

        switch (gamePanel.direction) {
            case 'R' -> assertEquals(initialX + gamePanel.unit_size, gamePanel.x[0]);
            case 'L' -> assertEquals(initialX - gamePanel.unit_size, gamePanel.x[0]);
            case 'U' -> assertEquals(initialY - gamePanel.unit_size, gamePanel.y[0]);
            case 'D' -> assertEquals(initialY + gamePanel.unit_size, gamePanel.y[0]);
        }
    }

    @Test
    void testCollisionWithSelfEndsGame() {
        gamePanel.bodyParts = 3;
        gamePanel.x[0] = 100;
        gamePanel.y[0] = 100;
        gamePanel.x[1] = 100;
        gamePanel.y[1] = 100;  // Симуляція зіткнення з власним тілом

        gamePanel.checkCollisions();
        assertFalse(gamePanel.running, "Гра повинна завершитися після зіткнення змії із собою");
    }
}
