import javax.swing.*;

import java.awt.*;

import java.awt.event.*;



public class fondo extends JPanel implements ActionListener, KeyListener {

    private static final long serialVersionUID = 6955857637922453851L;

    private double ballX = 220, ballY = 175;

    private double ballXSpeed = 4, ballYSpeed = 4;  // Velocidad inicial de 3

    private double paddle1Y = 175, paddle2Y = 175;

    private int score1 = 0, score2 = 0;

    private final int PADDLE_HEIGHT = 80;

    private final int PADDLE_WIDTH = 10;

    private final int GAME_WIDTH = 450;

    private final int GAME_HEIGHT = 350;

    private final int INFO_HEIGHT = 100;

    private final int SIDEBAR_WIDTH = 100;

    private boolean waitingAfterGoal = true;

    private boolean isPaused = false;

    private int waitCounter = 2;

    private final int WAIT_TIME = 150;

    private final Timer timer;



    private final Color[] argentinaColors = {Color.WHITE, new Color(108, 174, 223), Color.WHITE};

    private final Color[] brazilColors = {new Color(0, 155, 58), new Color(255, 223, 0), new Color(0, 155, 58)};



    private boolean showingGoalMessage = false;

    private int goalMessageCounter = 0;

    private final int GOAL_MESSAGE_DURATION = 100;

    private String goalScorerTeam = "";



    private final CustomButton pauseButton = new CustomButton("Pausar");



    private int gameTime = 0;

    private final int HALF_TIME_DURATION = 75000; // 1 minuto por mitad (en milisegundos)

    private boolean isSecondHalf = false;



    private boolean upPressed = false;

    private boolean downPressed = false;

    private boolean wPressed = false;

    private boolean sPressed = false;

    private final double PADDLE_SPEED = 5;

    private int touchCount = 0;

    private final double SPEED_INCREASE = 0.2; // 10% de aumento

    private final int TOUCHES_FOR_SPEED_INCREASE = 3;

    private boolean lastHitLeftPaddle = false;

    private boolean lastHitRightPaddle = false;

    private boolean firstImpact = true;  // Nueva variable para controlar el primer impacto



    public fondo() {

        setPreferredSize(new Dimension(GAME_WIDTH + SIDEBAR_WIDTH, GAME_HEIGHT + INFO_HEIGHT));

        setBackground(Color.BLACK);

        setFocusable(true);

        requestFocusInWindow();

        addKeyListener(this);

        setLayout(null);



        timer = new Timer(16, this); // Aproximadamente 60 FPS

        timer.start();



        JPanel sidebar = new JPanel();

        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        sidebar.setBounds(GAME_WIDTH, 0, SIDEBAR_WIDTH, GAME_HEIGHT);

        sidebar.setBackground(new Color(30, 30, 30));



        pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        pauseButton.addActionListener(e -> togglePause());

        sidebar.add(Box.createVerticalGlue());

        sidebar.add(pauseButton);

        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        sidebar.add(Box.createVerticalGlue());



        this.add(sidebar);



        Timer gameTimer = new Timer(1000, e -> updateGameTime());

        gameTimer.start();

    }



    private void updateGameTime() {

        if (isPaused) return;



        gameTime += 1000;

        if (gameTime >= HALF_TIME_DURATION) {

            if (!isSecondHalf) {

                isSecondHalf = true;

                switchSides();

                resetGame();

            } else {

                timer.stop();

                JOptionPane.showMessageDialog(this, "Fin del partido\nArgentina: " + score1 + " - Brasil: " + score2);

            }

        }

        repaint();

    }



    private void switchSides() {

        double tempY = paddle1Y;

        paddle1Y = paddle2Y;

        paddle2Y = tempY;



        Color[] tempColors = argentinaColors.clone();

        System.arraycopy(brazilColors, 0, argentinaColors, 0, brazilColors.length);

        System.arraycopy(tempColors, 0, brazilColors, 0, argentinaColors.length);



        String tempName = "Argentina";

        if (isSecondHalf) {

            tempName = "Brasil";

        }

        goalScorerTeam = tempName;

    }

   

    private void resetGame() {

        ballX = GAME_WIDTH / 2.0 - 5;

        ballY = GAME_HEIGHT / 2.0 - 5;

        ballXSpeed = 2;

        ballYSpeed = 2;

        waitingAfterGoal = true;

        gameTime = 0;

    }



    private void togglePause() {

        isPaused = !isPaused;

        pauseButton.setText(isPaused ? "Reanudar" : "Pausar");

        if (!isPaused) {

            this.requestFocusInWindow();

        }

    }



    @Override

    public void paintComponent(Graphics g) {

        super.paintComponent(g);



        g.setColor(Color.BLACK);

        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);



        g.setColor(Color.WHITE);

        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);

        g.drawOval(GAME_WIDTH / 2 - 40, GAME_HEIGHT / 2 - 40, 80, 80);



        drawPaddle(g, 10, (int)paddle1Y, argentinaColors);

        drawPaddle(g, GAME_WIDTH - 20, (int)paddle2Y, brazilColors);



        g.setColor(Color.WHITE);

        g.fillOval((int)ballX, (int)ballY, 10, 10);



        g.setColor(new Color(20, 20, 20));

        g.fillRect(0, GAME_HEIGHT, GAME_WIDTH + SIDEBAR_WIDTH, INFO_HEIGHT);



        drawImprovedScoreboard(g);



        drawTimeRemaining(g);



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



        g2d.setColor(new Color(40, 40, 40));

        g2d.fillRoundRect(GAME_WIDTH / 2 - 100, GAME_HEIGHT + 10, 200, 80, 20, 20);



        g2d.setColor(new Color(200, 200, 200));

        g2d.setStroke(new BasicStroke(3));

        g2d.drawRoundRect(GAME_WIDTH / 2 - 100, GAME_HEIGHT + 10, 200, 80, 20, 20);



        g2d.drawLine(GAME_WIDTH / 2, GAME_HEIGHT + 10, GAME_WIDTH / 2, GAME_HEIGHT + 90);



        Font scoreFont = new Font("Arial", Font.BOLD, 36);

        g2d.setFont(scoreFont);

        g2d.setColor(Color.WHITE);



        if (isSecondHalf) {

            g2d.drawString(String.valueOf(score2), GAME_WIDTH / 2 - 60, GAME_HEIGHT + 65);

            g2d.drawString(String.valueOf(score1), GAME_WIDTH / 2 + 40, GAME_HEIGHT + 65);

        } else {

            g2d.drawString(String.valueOf(score1), GAME_WIDTH / 2 - 60, GAME_HEIGHT + 65);

            g2d.drawString(String.valueOf(score2), GAME_WIDTH / 2 + 40, GAME_HEIGHT + 65);

        }



        Font teamFont = new Font("Arial", Font.BOLD, 16);

        g2d.setFont(teamFont);

        g2d.setColor(new Color(200, 200, 200));



        if (isSecondHalf) {

            g2d.drawString("Brasil", 20, GAME_HEIGHT + 30);

            g2d.drawString("Argentina", GAME_WIDTH - 80, GAME_HEIGHT + 30);

            drawSimpleFlag(g2d, 30, GAME_HEIGHT + 40, brazilColors);

            drawSimpleFlag(g2d, GAME_WIDTH - 50, GAME_HEIGHT + 40, argentinaColors);

        } else {

            g2d.drawString("Argentina", 20, GAME_HEIGHT + 30);

            g2d.drawString("Brasil", GAME_WIDTH - 80, GAME_HEIGHT + 30);

            drawSimpleFlag(g2d, 30, GAME_HEIGHT + 40, argentinaColors);

            drawSimpleFlag(g2d, GAME_WIDTH - 50, GAME_HEIGHT + 40, brazilColors);

        }

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



    private void drawTimeRemaining(Graphics g) {

        int remainingTime = HALF_TIME_DURATION / 1000 - gameTime / 1000;

        String timeText = String.format("Tiempo: %02d:%02d", remainingTime / 60, remainingTime % 60);



        g.setColor(Color.WHITE);

        g.setFont(new Font("Arial", Font.BOLD, 18));

       

        g.drawString(timeText, GAME_WIDTH / 2 - 50, 20);

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



        g2d.setColor(goalScorerTeam.equals("Argentina") ? new Color(108, 174, 223) : new Color(255, 223, 0));

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



        // Mover las paletas

        if (wPressed && paddle1Y > 0) {

            paddle1Y -= PADDLE_SPEED;

        }

        if (sPressed && paddle1Y < GAME_HEIGHT - PADDLE_HEIGHT) {

            paddle1Y += PADDLE_SPEED;

        }

        if (upPressed && paddle2Y > 0) {

            paddle2Y -= PADDLE_SPEED;

        }

        if (downPressed && paddle2Y < GAME_HEIGHT - PADDLE_HEIGHT) {

            paddle2Y += PADDLE_SPEED;

        }



        ballX += ballXSpeed;

        ballY += ballYSpeed;



        if (ballY <= 0 || ballY >= GAME_HEIGHT - 10) ballYSpeed = -ballYSpeed;



        if (ballX <= 20 && ballY >= paddle1Y && ballY <= paddle1Y + PADDLE_HEIGHT && !lastHitLeftPaddle) {

            ballXSpeed = -ballXSpeed;

            handlePaddleHit();

        } else if (ballX >= GAME_WIDTH - 30 && ballY >= paddle2Y && ballY <= paddle2Y + PADDLE_HEIGHT && !lastHitRightPaddle) {

            ballXSpeed = -ballXSpeed;

            handlePaddleHit();

        }



        if (ballX <= 0) {

            if (!isSecondHalf) {

                score2++;

                showGoalMessage("Brasil");

            } else {

                score1++;

                showGoalMessage("Argentina");

            }

            resetBall();

        }



        if (ballX >= GAME_WIDTH) {

            if (!isSecondHalf) {

                score1++;

                showGoalMessage("Argentina");

            } else {

                score2++;

                showGoalMessage("Brasil");

            }

            resetBall();

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



    private void handlePaddleHit() {

        if (firstImpact) {

            // Cambiar la velocidad a 4.76 después del primer impacto

            double angle = Math.atan2(ballYSpeed, ballXSpeed);

            ballXSpeed = 4.76 * Math.cos(angle);

            ballYSpeed = 4.76 * Math.sin(angle);

            firstImpact = false;

        } else {

            touchCount++;

            increaseSpeed();

        }

        lastHitLeftPaddle = (ballX < GAME_WIDTH / 2);

        lastHitRightPaddle = !lastHitLeftPaddle;

    }



    private void increaseSpeed() {

        if (touchCount % TOUCHES_FOR_SPEED_INCREASE == 0) {

            double currentSpeed = Math.sqrt(ballXSpeed * ballXSpeed + ballYSpeed * ballYSpeed);

            double speedIncrease = currentSpeed * SPEED_INCREASE;

            double angle = Math.atan2(ballYSpeed, ballXSpeed);

            

            ballXSpeed += speedIncrease * Math.cos(angle);

            ballYSpeed += speedIncrease * Math.sin(angle);

            

            System.out.println("Velocidad aumentada. Nueva velocidad: " + String.format("%.2f", currentSpeed + speedIncrease));

        }

    }



    private void resetBall() {

        ballX = GAME_WIDTH / 2.0 - 5;

        ballY = GAME_HEIGHT / 2.0 - 5;

        double angle = Math.random() * Math.PI / 2 - Math.PI / 4; // Ángulo aleatorio entre -45 y 45 grados

        ballXSpeed = 3 * Math.cos(angle) * (Math.random() < 0.5 ? 1 : -1); // Velocidad inicial de 3

        ballYSpeed = 3 * Math.sin(angle);

        waitingAfterGoal = true;

        touchCount = 0;

        lastHitLeftPaddle = false;

        lastHitRightPaddle = false;

        firstImpact = true;  // Reiniciar la bandera de primer impacto

    }



    private void showGoalMessage(String team) {

        showingGoalMessage = true;

        goalMessageCounter = 0;

        goalScorerTeam = team;

    }





    @Override

    public void keyPressed(KeyEvent e) {

        if (isPaused) {

            return;

        }

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:

                upPressed = true;

                break;

            case KeyEvent.VK_DOWN:

                downPressed = true;

                break;

            case KeyEvent.VK_W:

                wPressed = true;

                break;

            case KeyEvent.VK_S:

                sPressed = true;

                break;

        }

    }



    @Override

    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:

                upPressed = false;

                break;

            case KeyEvent.VK_DOWN:

                downPressed = false;

                break;

            case KeyEvent.VK_W:

                wPressed = false;

                break;

            case KeyEvent.VK_S:

                sPressed = false;

                break;

        }

    }



    @Override

    public void keyTyped(KeyEvent e) {}



    public static void main(String[] args) {

        JFrame frame = new JFrame("Fútbol Pong");

        fondo game = new fondo();

        frame.add(game);

        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        // Centrar la ventana en la pantalla

        frame.setLocationRelativeTo(null);



        frame.setVisible(true);

    }



    private class CustomButton extends JButton {

        public CustomButton(String text) {

            super(text);

            setFont(new Font("Arial", Font.BOLD, 16));

            setForeground(Color.WHITE);

            setBackground(new Color(50, 50, 50)); // Fondo más oscuro para el botón

            setBorderPainted(false);

            setFocusPainted(false);

            setContentAreaFilled(false);

            setOpaque(true);

            setPreferredSize(new Dimension(90, 40));

            setMaximumSize(new Dimension(90, 40));



            addMouseListener(new MouseAdapter() {

                @Override

                public void mouseEntered(MouseEvent e) {

                    setBackground(new Color(70, 70, 70)); // Color de resaltado

                }



                @Override

                public void mouseExited(MouseEvent e) {

                    setBackground(new Color(50, 50, 50)); // Volver al color original

                }

            });

        }



        @Override

        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());

            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);

            super.paintComponent(g2);

            g2.dispose();

        }

    }

}