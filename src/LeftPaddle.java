import static utils.Constants.*;

public class LeftPaddle extends Sprite{
    private int upKey;
    private int downKey;

    public LeftPaddle() {
        super(LEFT_PADDLE_IMAGE_PATH, 0, 0, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    @Override
    public void tick() {

    }
}
