import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class fondo extends JPanel implements ActionListener, KeyListener {
    private int ballX = 250, ballY = 200;
    private int ballXSpeed = 3, ballYSpeed = 3;
    private int paddle1Y = 200, paddle2Y = 200;
    private int score1 = 0, score2 = 0;
    private final int PADDLE_HEIGHT = 80;
    private final int PADDLE_WIDTH = 10;
    private final int GAME_WIDTH = 500;
    private final int GAME_HEIGHT = 400;
    private final int INFO_HEIGHT = 100;
    private final int SIDEBAR_WIDTH = 100;
    private boolean waitingAfterGoal = true;
    private boolean isPaused = false;
    private int waitCounter = 2;
    private final int WAIT_TIME = 100;
    private final Timer timer;

    private boolean showingGoalMessage = false;
    private int goalMessageCounter = 0;
    private final int GOAL_MESSAGE_DURATION = 100;
    private String goalScorerTeam = "";

    private EquipoFutbol equipoIzquierda;
    private EquipoFutbol equipoDerecha;
    
    private final JButton pauseButton = new JButton("Pausar");
    private final JButton homeButton = new JButton("Home");

    public fondo(EquipoFutbol equipoIzquierda, EquipoFutbol equipoDerecha) {
        this.equipoIzquierda = equipoIzquierda;
        this.equipoDerecha = equipoDerecha;

        setPreferredSize(new Dimension(GAME_WIDTH + SIDEBAR_WIDTH, GAME_HEIGHT + INFO_HEIGHT));
        setBackground(Color.BLACK);
        timer = new Timer(20, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setLayout(null);

        // Configurar la barra lateral
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBounds(GAME_WIDTH, 0, SIDEBAR_WIDTH, GAME_HEIGHT);
        sidebar.setBackground(new Color(50, 50, 50));

        pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseButton.addActionListener(e -> togglePause());
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(pauseButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));

        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.addActionListener(e -> goHome());
        sidebar.add(homeButton);
        sidebar.add(Box.createVerticalGlue());

        this.add(sidebar);
    }

    private void togglePause() {
    	isPaused = !isPaused;
        pauseButton.setText(isPaused ? "Reanudar" : "Pausar");
    }

    private void goHome() {
        // Implementar la lógica para volver a la pantalla de inicio
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.add(new MenuSeleccion());
        frame.pack();
        frame.revalidate();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(34, 139, 34));
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        g.setColor(Color.WHITE);
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
        g.drawOval(GAME_WIDTH / 2 - 40, GAME_HEIGHT / 2 - 40, 80, 80);

        drawPaddle(g, 10, paddle1Y, equipoIzquierda.getColores());
        drawPaddle(g, GAME_WIDTH - 20, paddle2Y, equipoDerecha.getColores());

        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, 10, 10);

        g.setColor(new Color(50, 50, 50));
        g.fillRect(0, GAME_HEIGHT, GAME_WIDTH + SIDEBAR_WIDTH, INFO_HEIGHT);

        drawImprovedScoreboard(g);

        if (showingGoalMessage) {
            drawGoalMessage(g);
        }
    }

    private void drawPaddle(Graphics g, int x, int y, Color[] colors) {
        int stripeHeight = PADDLE_HEIGHT / colors.length;
        for (int i = 0; i < colors.length; i++) {
            g.setColor(colors[i]);
            g.fillRect(x, y + i * stripeHeight, PADDLE_WIDTH, stripeHeight);
        }
    }

    private void drawImprovedScoreboard(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(30, 30, 30));
        g2d.fillRoundRect(GAME_WIDTH / 2 - 100, GAME_HEIGHT + 10, 200, 80, 20, 20);

        g2d.setColor(new Color(200, 200, 200));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(GAME_WIDTH / 2 - 100, GAME_HEIGHT + 10, 200, 80, 20, 20);

        g2d.drawLine(GAME_WIDTH / 2, GAME_HEIGHT + 10, GAME_WIDTH / 2, GAME_HEIGHT + 90);

        Font scoreFont = new Font("Arial", Font.BOLD, 36);
        g2d.setFont(scoreFont);

        g2d.setColor(Color.WHITE);
        g2d.drawString(String.valueOf(score1), GAME_WIDTH / 2 - 60, GAME_HEIGHT + 65);
        g2d.drawString(String.valueOf(score2), GAME_WIDTH / 2 + 40, GAME_HEIGHT + 65);

        Font teamFont = new Font("Arial", Font.BOLD, 16);
        g2d.setFont(teamFont);
        g2d.setColor(new Color(200, 200, 200));
        g2d.drawString(equipoIzquierda.getNombre(), 20, GAME_HEIGHT + 30);
        g2d.drawString(equipoDerecha.getNombre(), GAME_WIDTH - 120, GAME_HEIGHT + 30);

        drawSimpleFlag(g2d, 10, GAME_HEIGHT + 40, equipoIzquierda.getColores());
        drawSimpleFlag(g2d, GAME_WIDTH - 40, GAME_HEIGHT + 40, equipoDerecha.getColores());
    }

    private void drawSimpleFlag(Graphics2D g2d, int x, int y, Color[] colors) {
        int flagWidth = 30;
        int flagHeight = 20;
        int stripeHeight = flagHeight / colors.length;
        for (int i = 0; i < colors.length; i++) {
            g2d.setColor(colors[i]);
            g2d.fillRect(x, y + i * stripeHeight, flagWidth, stripeHeight);
        }
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, flagWidth, flagHeight);
    }

    private void drawGoalMessage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        String message = "¡GOL!";
        Font goalFont = new Font("Arial", Font.BOLD, 60);
        g2d.setFont(goalFont);

        FontMetrics fm = g2d.getFontMetrics();
        int messageWidth = fm.stringWidth(message);
        int messageHeight = fm.getHeight();
        int x = (GAME_WIDTH - messageWidth) / 2;
        int y = GAME_HEIGHT / 2;

        float alpha = 1.0f - (float)goalMessageCounter / GOAL_MESSAGE_DURATION;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(x - 10, y - messageHeight, messageWidth + 20, messageHeight + 20);

        g2d.setColor(goalScorerTeam.equals(equipoIzquierda.getNombre()) ? equipoIzquierda.getColores()[0] : equipoDerecha.getColores()[0]);
        g2d.drawString(message, x, y);

        Font teamFont = new Font("Arial", Font.BOLD, 30);
        g2d.setFont(teamFont);
        fm = g2d.getFontMetrics();
        int teamNameWidth = fm.stringWidth(goalScorerTeam);
        g2d.drawString(goalScorerTeam, (GAME_WIDTH - teamNameWidth) / 2, y + 50);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isPaused) {
            return;
        }

        if (waitingAfterGoal) {
            waitCounter++;
            if (waitCounter >= WAIT_TIME) {
                waitingAfterGoal = false;
                waitCounter = 0;
            }
            repaint();
            return;
        }

        ballX += ballXSpeed;
        ballY += ballYSpeed;
        if (ballY <= 0 || ballY >= GAME_HEIGHT - 10) ballYSpeed = -ballYSpeed;
        if (ballX <= 20 && ballY >= paddle1Y && ballY <= paddle1Y + PADDLE_HEIGHT) ballXSpeed = -ballXSpeed;
        if (ballX >= GAME_WIDTH - 30 && ballY >= paddle2Y && ballY <= paddle2Y + PADDLE_HEIGHT) ballXSpeed = -ballXSpeed;

        if (ballX <= 0) {
            score2++;
            resetBall();
            showGoalMessage(equipoDerecha.getNombre());
        }
        if (ballX >= GAME_WIDTH) {
            score1++;
            resetBall();
            showGoalMessage(equipoIzquierda.getNombre());
        }

        if (showingGoalMessage) {
            goalMessageCounter++;
            if (goalMessageCounter >= GOAL_MESSAGE_DURATION) {
                showingGoalMessage = false;
                goalMessageCounter = 0;
            }
        }

        repaint();
    }

    private void showGoalMessage(String team) {
        showingGoalMessage = true;
        goalMessageCounter = 0;
        goalScorerTeam = team;
    }

    private void resetBall() {
        ballX = GAME_WIDTH / 2;
        ballY = GAME_HEIGHT / 2;
        waitingAfterGoal = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (isPaused) {
            return;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                paddle2Y = Math.max(0, paddle2Y - 20);
                break;
            case KeyEvent.VK_DOWN:
                paddle2Y = Math.min(GAME_HEIGHT - PADDLE_HEIGHT, paddle2Y + 20);
                break;
            case KeyEvent.VK_W:
                paddle1Y = Math.max(0, paddle1Y - 20);
                break;
            case KeyEvent.VK_S:
                paddle1Y = Math.min(GAME_HEIGHT - PADDLE_HEIGHT, paddle1Y + 20);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}