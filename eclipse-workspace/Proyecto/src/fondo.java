import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class fondo extends JPanel implements ActionListener, KeyListener {
    private int ballX = 225, ballY = 175;
    private int ballXSpeed = 2, ballYSpeed = 2;  // Velocidad baja al inicio
    private int normalSpeed = 3; // Velocidad normal después del primer impacto
    private int paddle1Y = 175, paddle2Y = 175;
    private int score1 = 0, score2 = 0;
    private final int PADDLE_HEIGHT = 80;
    private final int PADDLE_WIDTH = 10;
    private final int GAME_WIDTH = 450;
    private final int GAME_HEIGHT = 350;
    private final int INFO_HEIGHT = 100;
    private final int SIDEBAR_WIDTH = 100;
    private boolean waitingAfterGoal = true;
    private boolean isPaused = false;
    private boolean firstImpact = true; // Bandera para controlar el primer impacto
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
    private final int HALF_TIME_DURATION = 60000; // 1 minuto por mitad (en milisegundos)
    private boolean isSecondHalf = false; // Para controlar si es la segunda mitad

    public fondo() {
        setPreferredSize(new Dimension(GAME_WIDTH + SIDEBAR_WIDTH, GAME_HEIGHT + INFO_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow(); // Pedir el foco inicialmente
        addKeyListener(this); // Asegurarte de que el KeyListener esté siempre activo
        setLayout(null);

        // Configurar el temporizador
        timer = new Timer(20, this);
        timer.start();

        // Configurar la barra lateral
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBounds(GAME_WIDTH, 0, SIDEBAR_WIDTH, GAME_HEIGHT);
        sidebar.setBackground(new Color(30, 30, 30));

        // Configurar el botón de pausa
        pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseButton.addActionListener(e -> togglePause());
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(pauseButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(Box.createVerticalGlue());

        this.add(sidebar);

        // Iniciar el temporizador del juego
        Timer gameTimer = new Timer(1000, e -> updateGameTime());
        gameTimer.start();
    }

    private void updateGameTime() {
        if (isPaused) return;

        gameTime += 1000;
        if (gameTime >= HALF_TIME_DURATION) {
            if (!isSecondHalf) {
                // Termina el primer tiempo, cambiar de lado
                isSecondHalf = true;
                switchSides();
                resetGame();
            } else {
                // Termina el segundo tiempo, finalizar el juego
                timer.stop();
                JOptionPane.showMessageDialog(this, "Fin del partido\nArgentina: " + score1 + " - Brasil: " + score2);
            }
        }
        repaint();
    }

    private void switchSides() {
        // Cambiar lados de las paletas
        int tempY = paddle1Y;
        paddle1Y = paddle2Y;
        paddle2Y = tempY;

        // Invertir la bandera visualmente, pero mantener la puntuación lógica
        Color[] tempColors = argentinaColors.clone();
        System.arraycopy(brazilColors, 0, argentinaColors, 0, brazilColors.length);
        System.arraycopy(tempColors, 0, brazilColors, 0, argentinaColors.length);

        // Intercambiar nombres visualmente, pero no las puntuaciones
        String tempName = "Argentina";
        if (isSecondHalf) {
            tempName = "Brasil";
        }
        goalScorerTeam = tempName;
    }
    
    private void resetGame() {
        ballX = GAME_WIDTH / 2 - 5;
        ballY = GAME_HEIGHT / 2 - 5;
        ballXSpeed = 2; // Velocidad baja al reiniciar
        ballYSpeed = 2; // Velocidad baja al reiniciar
        firstImpact = true; // Restablecer la bandera del primer impacto
        waitingAfterGoal = true;
        gameTime = 0; // Reiniciar el tiempo para la segunda mitad
    }

    private void togglePause() {
        isPaused = !isPaused;
        pauseButton.setText(isPaused ? "Reanudar" : "Pausar");
        if (!isPaused) {
            this.requestFocusInWindow(); // Asegura que el panel vuelva a tener el foco cuando se reanuda
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fondo del campo de juego
        g.setColor(Color.BLACK); // Cancha negra
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        // Línea central y círculo
        g.setColor(Color.WHITE);
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
        g.drawOval(GAME_WIDTH / 2 - 40, GAME_HEIGHT / 2 - 40, 80, 80);

        drawPaddle(g, 10, paddle1Y, argentinaColors);
        drawPaddle(g, GAME_WIDTH - 20, paddle2Y, brazilColors);

        // Pelota
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, 10, 10);

        // Fondo de la barra de información
        g.setColor(new Color(20, 20, 20)); // Fondo oscuro para la barra de información
        g.fillRect(0, GAME_HEIGHT, GAME_WIDTH + SIDEBAR_WIDTH, INFO_HEIGHT);

        drawImprovedScoreboard(g);

        // Dibujar el tiempo restante
        drawTimeRemaining(g);

        if (showingGoalMessage) {
            drawGoalMessage(g);
        }
    }

    private void drawTimeRemaining(Graphics g) {
        int remainingTime = HALF_TIME_DURATION / 1000 - gameTime / 1000;
        String timeText = String.format("Tiempo: %02d:%02d", remainingTime / 60, remainingTime % 60);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Dibujar el tiempo en la parte superior del campo de juego
        g.drawString(timeText, GAME_WIDTH / 2 - 50, 20);
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

        // Fondo del marcador
        g2d.setColor(new Color(40, 40, 40)); // Fondo del marcador más oscuro
        g2d.fillRoundRect(GAME_WIDTH / 2 - 100, GAME_HEIGHT + 10, 200, 80, 20, 20);

        // Contorno del marcador
        g2d.setColor(new Color(200, 200, 200));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(GAME_WIDTH / 2 - 100, GAME_HEIGHT + 10, 200, 80, 20, 20);

        // Línea divisoria en el marcador
        g2d.drawLine(GAME_WIDTH / 2, GAME_HEIGHT + 10, GAME_WIDTH / 2, GAME_HEIGHT + 90);

        // Puntuación
        Font scoreFont = new Font("Arial", Font.BOLD, 36);
        g2d.setFont(scoreFont);
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.valueOf(score1), GAME_WIDTH / 2 - 60, GAME_HEIGHT + 65);
        g2d.drawString(String.valueOf(score2), GAME_WIDTH / 2 + 40, GAME_HEIGHT + 65);

        // Nombres de los equipos, invertidos según la mitad del juego
        Font teamFont = new Font("Arial", Font.BOLD, 16);
        g2d.setFont(teamFont);
        g2d.setColor(new Color(200, 200, 200));

        // Ajustar la posición del texto de los equipos
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

        // Fondo del mensaje de gol
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(x - 10, y - messageHeight, messageWidth + 20, messageHeight + 20);

        // Texto del mensaje de gol
        g2d.setColor(goalScorerTeam.equals("Argentina") ? new Color(108, 174, 223) : new Color(255, 223, 0));
        g2d.drawString(message, x, y);

        // Nombre del equipo que anotó
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

        // Detectar colisiones con las paletas
        if (ballX <= 20 && ballY >= paddle1Y && ballY <= paddle1Y + PADDLE_HEIGHT) {
            ballXSpeed = -ballXSpeed;
            if (firstImpact) {
                ballXSpeed = normalSpeed * (ballXSpeed > 0 ? 1 : -1); // Restaurar velocidad normal
                ballYSpeed = normalSpeed * (ballYSpeed > 0 ? 1 : -1); // Restaurar velocidad normal
                firstImpact = false; // Desactivar la bandera del primer impacto
            }
        }
        if (ballX >= GAME_WIDTH - 30 && ballY >= paddle2Y && ballY <= paddle2Y + PADDLE_HEIGHT) {
            ballXSpeed = -ballXSpeed;
            if (firstImpact) {
                ballXSpeed = normalSpeed * (ballXSpeed > 0 ? 1 : -1); // Restaurar velocidad normal
                ballYSpeed = normalSpeed * (ballYSpeed > 0 ? 1 : -1); // Restaurar velocidad normal
                firstImpact = false; // Desactivar la bandera del primer impacto
            }
        }

        if (ballX <= 0) {
            if (isSecondHalf) {
                score2++; // El equipo de la derecha (Brasil) anota
                showGoalMessage("Brasil");
            } else {
                score1++; // El equipo de la izquierda (Argentina) anota
                showGoalMessage("Argentina");
            }
            resetBall();
        }
        
        if (ballX >= GAME_WIDTH) {
            if (isSecondHalf) {
                score1++; // El equipo de la izquierda (Argentina) anota
                showGoalMessage("Argentina");
            } else {
                score2++; // El equipo de la derecha (Brasil) anota
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

    private void showGoalMessage(String team) {
        showingGoalMessage = true;
        goalMessageCounter = 0;
        goalScorerTeam = team;
    }


    private void resetBall() {
        ballX = GAME_WIDTH / 2 - 5;
        ballY = GAME_HEIGHT / 2 - 5;
        ballXSpeed = 2; // Velocidad baja al reiniciar
        ballYSpeed = 2; // Velocidad baja al reiniciar
        firstImpact = true; // Restablecer la bandera del primer impacto
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