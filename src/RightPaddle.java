import java.awt.event.KeyEvent;
import java.util.Set;

import static utils.Constants.*;

public class RightPaddle extends Sprite{
    private int upKey;
    private int downKey;
    private double dx;
    private double dy;

    public RightPaddle() {
        super(RIGHT_PADDLE_IMAGE_PATH, 350, 100, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    @Override
    public void tick() {
        dx = 0;
        pos.translate((int) dx, (int) dy);
        pos.y = Math.clamp(pos.y, 0, BOARD_HEIGHT - PLAYER_HEIGHT);
    }

    public void handleActiveKeys(Set<Integer> activeKeyCodes) {
        dy = 0;
        if (activeKeyCodes.contains(KeyEvent.VK_UP)) {
            dy -= PADDLE_SPEED;
        } else if (activeKeyCodes.contains(KeyEvent.VK_DOWN)) {
            dy += PADDLE_SPEED;
        }
    }
}
