import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int CELL_SIZE = 20;

    private ArrayList<Point> snake = new ArrayList<>();
    private Point food;
    private int score = 0;
    private boolean isGameOver = false;
    private String gameOverMessage = "Game Over - Final Score: ";

    private int dx = CELL_SIZE;
    private int dy = 0;

    private Timer timer;

    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        initGame();
    }

    private void initGame() {
        snake.clear();
        snake.add(new Point(CELL_SIZE, CELL_SIZE));
        generateFood();
        score = 0;
        isGameOver = false;
        dx = CELL_SIZE;
        dy = 0;

        timer = new Timer(100, this);
        timer.start();
    }

    private void generateFood() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / CELL_SIZE) * CELL_SIZE;
        int y = rand.nextInt(HEIGHT / CELL_SIZE) * CELL_SIZE;
        food = new Point(x, y);
    }

    private void moveSnake() {
        Point newHead = new Point(snake.get(0));
        newHead.translate(dx, dy);
        snake.add(0, newHead);
        if (!newHead.equals(food)) {
            snake.remove(snake.size() - 1);
        } else {
            score++;
            generateFood();
        }
    }

    private boolean checkCollision() {
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            return true;
        }
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                return true;
            }
        }
        return false;
    }

    private void gameOver() {
        isGameOver = true;
        gameOverMessage += score;
        repaint();
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            moveSnake();
            if (checkCollision()) {
                gameOver();
            }
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isGameOver) {
            g.setColor(Color.GREEN);
            for (Point point : snake) {
                g.fillRect(point.x, point.y, CELL_SIZE, CELL_SIZE);
            }

            g.setColor(Color.RED);
            g.fillRect(food.x, food.y, CELL_SIZE, CELL_SIZE);
        } else {
            g.setColor(Color.WHITE);
            g.drawString(gameOverMessage, WIDTH / 2 - 50, HEIGHT / 2);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP && dy == 0) {
            dx = 0;
            dy = -CELL_SIZE;
        } else if (key == KeyEvent.VK_DOWN && dy == 0) {
            dx = 0;
            dy = CELL_SIZE;
        } else if (key == KeyEvent.VK_LEFT && dx == 0) {
            dx = -CELL_SIZE;
            dy = 0;
        } else if (key == KeyEvent.VK_RIGHT && dx == 0) {
            dx = CELL_SIZE;
            dy = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame snakeGame = new SnakeGame();
        frame.add(snakeGame);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
