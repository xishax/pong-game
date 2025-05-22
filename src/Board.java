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

    private final Paddle leftPaddle;
    private final Paddle rightPaddle;
    private final Player player;
    private final List<Sprite> sprites;
    private final Set<Integer> activeKeyCodes;
    private final Ball ball;
    private final Score score;
    private final Wall topWall;
    private final Wall bottomWall;
    private int rallyCounter;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.cyan);

        rallyCounter = 0;
        player = new Player();
        ball = new Ball();
        leftPaddle = new Paddle(30,70);
        rightPaddle = new Paddle(500,70);
        score = new Score(0,0);
        topWall = new Wall(0,0);
        bottomWall= new Wall(0,520);
        sprites = new ArrayList<>(List.of(ball, leftPaddle, rightPaddle, topWall, bottomWall));

        activeKeyCodes = new HashSet<>();

        new Timer(TICK_DELAY, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        leftPaddle.leftHandleActiveKeys(activeKeyCodes);
        rightPaddle.rightHandleActiveKeys(activeKeyCodes);
        player.handleActiveKeys(activeKeyCodes);

        for(Sprite sprite : sprites) {
            sprite.tick();
        }

        if(ball.isColliding(leftPaddle)) {
            ball.bounceRight();
        } else if (ball.isColliding(rightPaddle)) {
            ball.bounceLeft();
        }

        if (ball.isColliding(topWall)) {
            ball.flipVY();
        } else if (ball.isColliding(bottomWall)) {
            ball.flipVY();
        }

        if (ball.getPos().x <= -BALL_WIDTH) {
            //handle right player wins a point
            score.incrementPlayerTwoScore();
            ball.resetBall();
        } else if (ball.getPos().x >= BOARD_WIDTH) {
            //handle left player wins a point
            score.incrementPlayerOneScore();
            ball.resetBall();
        }
        if (ball.isColliding(leftPaddle) || ball.isColliding(rightPaddle) || ball.isColliding(topWall) || ball.isColliding(bottomWall)) {
            rallyCounter = rallyCounter + 1;
        }

        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.setFont(new Font("Arial", Font.PLAIN, 25));
        graphics.drawString("Score: " + score.getPlayerOneScore(), 20, 50);
        graphics.drawString("Score: " + score.getPlayerTwoScore(), 460, 50);
        graphics.setColor(Color.BLACK);

        for(Sprite sprite : sprites) {
            sprite.draw(graphics, this);
        }

        if (score.getPlayerOneScore() == 11) {
            ball.ballStop();
            graphics.drawString("Player One Wins!",200,350);
            graphics.drawString("Rally Counter: " + rallyCounter, 200,400 );
        }else if (score.getPlayerTwoScore() == 11) {
            ball.ballStop();
            graphics.drawString("Player Two Wins!",200,350);
            graphics.drawString("RallyCounter: "  + rallyCounter, 200, 400);
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
