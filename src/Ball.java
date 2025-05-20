import java.util.Random;

import static utils.Constants.*;

public class Ball extends Sprite {
    private double vx;
    private double vy;

    public Ball() {
        super(BALL_IMAGE_PATH, BOARD_WIDTH / 2 - WALL_WIDTH / 2,
                BOARD_HEIGHT / 2 - WALL_WIDTH / 2, BALL_WIDTH, BALL_HEIGHT);
        resetBall();
    }

    public void resetBall() {
        pos.setLocation(BOARD_WIDTH / 2 - BALL_WIDTH / 2, BOARD_HEIGHT / 2 - BALL_WIDTH / 2);

        Random random = new Random();
        int randNum = random.nextInt(4);
        if (randNum == 0) {
            vx = BALL_SPEED;
            vy = -BALL_SPEED;
        } else if (randNum == 1) {
            vx = -BALL_SPEED;
            vy = BALL_SPEED;
        } else if (randNum == 2) {
            vx = -BALL_SPEED;
            vy = BALL_SPEED;
        } else {
            vx = BALL_SPEED;
            vy = -BALL_SPEED;
        }
    }

    @Override
    public void tick() {
        pos.translate((int)vx, (int)vy);

        if (pos.y <= 0 || pos.y >= BOARD_HEIGHT - BALL_HEIGHT) {
            vy = -vy;
        }

        if (pos.x <= 0 || pos.x >= BOARD_WIDTH - BALL_WIDTH) {
            vx = -vx;
        }
    }

    public void bounceLeft() {
        vx = -Math.abs(vx) * 1.1;
    }

    public void bounceRight() {
        vx = Math.abs(vx) * 1.1;
    }

    public void flipVY() {
        vy = -vy;
    }
}
