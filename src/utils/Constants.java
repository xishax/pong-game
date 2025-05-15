package utils;

public final class Constants {
    private Constants() {
        // prevents instantiation
    }

    // Board constants
    public static final int BOARD_WIDTH = 600;
    public static final int BOARD_HEIGHT = 550;
    // A delay of 25 milliseconds results in a frame rate of 45 FPS.
    public static final int TICK_DELAY = 25;

    // Player constants
    public static final String PLAYER_IMAGE_PATH = "resources/amogus.png";
    public static final int PLAYER_WIDTH = 60;
    public static final int PLAYER_HEIGHT = 80;
    public static final int PLAYER_SPEED = 10;

    // Wall constants
    public static final String WALL_IMAGE_PATH = "resources/wall.png";
    public static final int WALL_WIDTH = 150;
    public static final int WALL_HEIGHT = 150;

    //Ball constants
    public static final String BALL_IMAGE_PATH = "resources/ball.png";
    public static final int BALL_WIDTH = 80;
    public static final int BALL_HEIGHT = 70;
    public static final int BALL_SPEED= 10;

    //Paddle constants
    public static final String LEFT_PADDLE_IMAGE_PATH = "resources/leftPaddle.png";
    public static final String RIGHT_PADDLE_IMAGE_PATH = "resources/rightPaddle.png";
    public static final int PADDLE_WIDTH = 400;
    public static final int PADDLE_HEIGHT = 450;
    public static final int PADDLE_SPEED = 7;
}
