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
    private final Score score;
    private final TopWall topWall;
    private final BottomWall bottomWall;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.cyan);

        player = new Player();
        Wall wall = new Wall(BOARD_WIDTH / 2 - WALL_WIDTH / 2,
                BOARD_HEIGHT / 2 - WALL_WIDTH / 2);
        ball = new Ball();
        leftPaddle = new LeftPaddle();
        rightPaddle = new RightPaddle();
        score = new Score(0,0);
        topWall = new TopWall(0,0);
        bottomWall= new BottomWall(100,100);
        sprites = new ArrayList<>(List.of(ball,leftPaddle, rightPaddle, topWall, bottomWall));

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

        if (ball.getPos().x <= -BALL_WIDTH) {
            //handle right player wins a point
            score.incrementPlayerTwoScore();
        } else if (ball.getPos().x >= BOARD_WIDTH) {
            //handleleft player wins a point
            score.incrementPlayerOneScore();
        }

        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);


        graphics.setFont(new Font("Arial", Font.PLAIN, 25));
        graphics.drawString("Score: " + score.getPlayerOneScore(), 20, 50);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Score: " + score.getPlayerTwoScore(), 460, 50);

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
