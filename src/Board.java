import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static utils.Constants.*;

public class Board extends JPanel implements ActionListener, KeyListener {

    private final LeftPaddle leftPaddle;
    private final RightPaddle rightPaddle;
    private final Player player;
    private final List<Sprite> sprites;
    private final Set<Integer> activeKeyCodes;
    private final Ball ball;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.cyan);

        player = new Player();
        Wall wall = new Wall(BOARD_WIDTH / 2 - WALL_WIDTH / 2,
                BOARD_HEIGHT / 2 - WALL_WIDTH / 2);
        ball = new Ball();
        leftPaddle = new LeftPaddle();
        rightPaddle = new RightPaddle();
        sprites = new ArrayList<>(List.of(ball,leftPaddle, rightPaddle));

        activeKeyCodes = new HashSet<>();

        new Timer(TICK_DELAY, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        leftPaddle.handleActiveKeys(activeKeyCodes);
        rightPaddle.handleActiveKeys(activeKeyCodes);
        player.handleActiveKeys(activeKeyCodes);

        for(Sprite sprite : sprites) {
            sprite.tick();
        }


        if(ball.isColliding(leftPaddle)) {
            ball.bounceRight();
        } else if (ball.isColliding(rightPaddle)) {
            ball.bounceLeft();
        }

        if (ball.getPos().y <= 0 || ball.getPos().y >= BOARD_HEIGHT) {
            ball.flipVY();
        }

        if (ball.getPos().x <= BOARD_HEIGHT) {
            //handle right player wins a point
        } else if (ball.getPos().x >= BOARD_HEIGHT) {
            //handleleft player wins a point
        }
        //if (ball.isColliding(topWall) || ball.isColliding(bottomWall)) {}
        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);


        graphics.setFont(new Font("Arial", Font.PLAIN, 25));
        graphics.drawString("Player 1 Score: ", 20, 50);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Player 2 Score: ", 380, 50);

        for(Sprite sprite : sprites) {
            sprite.draw(graphics, this);
        }

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Unused
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        activeKeyCodes.add(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        activeKeyCodes.remove(keyEvent.getKeyCode());
    }
}
