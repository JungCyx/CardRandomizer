
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CardRandomizer extends JFrame implements KeyListener {

    private JPanel cardPanel;
    private ArrayList<String> cardPaths;
    private ArrayList<String> shuffledCards;
    private ArrayList<String> dealtCards;
    private ArrayList<JLabel> cardLabels;
    private JLabel statsLabel;
    private JLabel dealtCardLabel;
    private JButton shuffleButton;
    private JButton dealButton;
    private JButton resetButton;

    public CardRandomizer() {
        // Initialize collections
        cardPaths = new ArrayList<>();
        shuffledCards = new ArrayList<>();
        dealtCards = new ArrayList<>();
        cardLabels = new ArrayList<>();

        loadCardPaths();
        shuffledCards.addAll(cardPaths);

        // Set up the JFrame
        setTitle("Card Randomizer - Modern Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(1400, 850);
        setLocationRelativeTo(null);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().setBackground(new Color(10, 92, 10));
        addKeyListener(this);
        setFocusable(true);

        // Top section - Title and Stats
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);

        // Center section - Card Grid
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(4, 13, 5, 5));
        cardPanel.setBackground(new Color(10, 92, 10));
        cardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        displayAllCards();

        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setBackground(new Color(10, 92, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom section - Controls and Dealt Card
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(new Color(26, 26, 26));
        topPanel.setBorder(BorderFactory.createLineBorder(new Color(10, 92, 10), 2));
        topPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Card Randomizer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        statsLabel = new JLabel("Cards Remaining: 52 | Dealt: 0");
        statsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statsLabel.setForeground(new Color(144, 238, 144));
        statsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel instructionsLabel = new JLabel("Keyboard: SPACE=Shuffle | D=Deal | R=Reset");
        instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        instructionsLabel.setForeground(new Color(204, 204, 204));
        instructionsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        topPanel.add(titleLabel);
        topPanel.add(Box.createVerticalStrut(5));
        topPanel.add(statsLabel);
        topPanel.add(Box.createVerticalStrut(5));
        topPanel.add(instructionsLabel);

        return topPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout(15, 15));
        bottomPanel.setBackground(new Color(26, 26, 26));
        bottomPanel.setBorder(BorderFactory.createLineBorder(new Color(10, 92, 10), 2));
        bottomPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Dealt card section
        JPanel dealtSection = new JPanel();
        dealtSection.setLayout(new BoxLayout(dealtSection, BoxLayout.Y_AXIS));
        dealtSection.setBackground(new Color(26, 26, 26));

        JLabel dealtLabel = new JLabel("Dealt Card:");
        dealtLabel.setForeground(new Color(255, 255, 255));
        dealtLabel.setFont(new Font("Arial", Font.BOLD, 12));

        dealtCardLabel = new JLabel();
        dealtCardLabel.setPreferredSize(new Dimension(80, 120));
        dealtCardLabel.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 2));
        dealtCardLabel.setHorizontalAlignment(SwingConstants.CENTER);

        dealtSection.add(dealtLabel);
        dealtSection.add(Box.createVerticalStrut(10));
        dealtSection.add(dealtCardLabel);

        bottomPanel.add(dealtSection, BorderLayout.WEST);

        // Button section
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(26, 26, 26));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        shuffleButton = createStyledButton("Shuffle (Space)");
        shuffleButton.addActionListener(e -> shuffleCards());

        dealButton = createStyledButton("Deal Card (D)");
        dealButton.addActionListener(e -> dealCard());

        resetButton = createStyledButton("Reset (R)");
        resetButton.addActionListener(e -> resetDeck());

        buttonPanel.add(shuffleButton);
        buttonPanel.add(dealButton);
        buttonPanel.add(resetButton);

        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        return bottomPanel;
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 12));
        btn.setBackground(new Color(30, 126, 30));
        btn.setForeground(new Color(255, 255, 255));
        btn.setBorder(BorderFactory.createLineBorder(new Color(10, 92, 10), 2));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(150, 40));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(42, 154, 42));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(30, 126, 30));
            }
        });

        return btn;
    }

    private void loadCardPaths() {
        for (int i = 1; i <= 52; i++) {
            cardPaths.add("Cards/cards/" + i + ".png");
        }
    }

    private void displayAllCards() {
        cardPanel.removeAll();
        cardLabels.clear();

        for (String cardPath : shuffledCards) {
            JLabel cardLabel = createCardLabel(cardPath);
            cardLabels.add(cardLabel);
            cardPanel.add(cardLabel);
        }

        cardPanel.revalidate();
        cardPanel.repaint();
    }

    private JLabel createCardLabel(String imagePath) {
        JLabel label = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaledImage));
            label.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        } catch (Exception e) {
            label.setText("?");
            label.setFont(new Font("Arial", Font.BOLD, 24));
            label.setForeground(new Color(255, 0, 0));
            label.setPreferredSize(new Dimension(80, 120));
            label.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
        }
        return label;
    }

    private void shuffleCards() {
        Collections.shuffle(shuffledCards);
        dealtCards.clear();
        displayAllCards();
        updateStats();
        dealtCardLabel.setIcon(null);
        System.out.println("✓ Deck shuffled!");
    }

    private void dealCard() {
        if (!shuffledCards.isEmpty()) {
            String dealtCard = shuffledCards.remove(0);
            dealtCards.add(dealtCard);
            displayAllCards();
            updateStats();
            displayDealtCard(dealtCard);
            System.out.println("✓ Card dealt!");
        } else {
            JOptionPane.showMessageDialog(this, "No more cards to deal!", "Deck Empty", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void displayDealtCard(String cardPath) {
        try {
            ImageIcon icon = new ImageIcon(cardPath);
            Image scaledImage = icon.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
            dealtCardLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            dealtCardLabel.setIcon(null);
        }
    }

    private void resetDeck() {
        shuffledCards.clear();
        dealtCards.clear();
        shuffledCards.addAll(cardPaths);
        displayAllCards();
        updateStats();
        dealtCardLabel.setIcon(null);
        System.out.println("✓ Deck reset!");
    }

    private void updateStats() {
        statsLabel.setText(String.format("Cards Remaining: %d | Dealt: %d", shuffledCards.size(), dealtCards.size()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shuffleCards();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            dealCard();
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            resetDeck();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CardRandomizer());
    }
}
