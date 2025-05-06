import java.util.Random;

import static utils.Constants.*;

public class Ball extends Sprite {
    private int vx;
    private int vy;

    public Ball(String imagePath, int x, int y, int width, int height, int vx, int vy) {
        super(BALL_IMAGE_PATH, BOARD_WIDTH / 2 - WALL_WIDTH / 2,
                BOARD_HEIGHT / 2 - WALL_WIDTH / 2, BALL_WIDTH, BALL_HEIGHT);
        resetBall();
        this.vx = vx;
        this.vy = vy;
    }

    public void resetBall() {
        pos.setLocation(BOARD_WIDTH / 2 - BALL_WIDTH / 2, BOARD_HEIGHT / 2 - BALL_WIDTH / 2);
        pos.translate(vx,vy);

        Random random = new Random();
        vx = random.nextInt(3);
        vy = random.nextInt(3);


    }

    @Override
    public void tick() {
        pos.translate(vx, vy);
        pos.x = Math.clamp(pos.x, 0, BOARD_WIDTH - BALL_WIDTH);
        pos.y = Math.clamp(pos.x, 0, BOARD_HEIGHT - BALL_HEIGHT);

    }
}
