import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.ArrayList;

public class CardRandomizer extends JFrame {

    //Practicing Encapsulation
    private JPanel cardPanel;
    private ArrayList<ImageIcon> cards;
    private ArrayList<JLabel> cardLabels;
    private JButton shuffleButton;

    public CardRandomizer() {
       
        // Set up the JFrame
        JFrame frame= new JFrame();
        frame.setTitle("Card Randomizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
       

        // Make List
        cards = new ArrayList<>();
        cardLabels = new ArrayList<>();
        loadCardImages();

        // Set up JPanel
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(4, 13)); // 4 rows, 13 columns for all 52 cards
        cardPanel.setBackground(new Color(0,100,0));  //Darker Green Color
       
        // Add card labels to the panel
        for (ImageIcon card : cards) {
            JLabel cardLabel = new JLabel(card);
            cardLabels.add(cardLabel);
            cardPanel.add(cardLabel);
        }

        // Add the card panel to the center
        add(cardPanel, BorderLayout.CENTER);

        // Set up the shuffle button
        shuffleButton = new JButton("Shuffle Cards");
        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffleAndDisplayCards();
            }
        });

        // Add the shuffle button to the bottom
        add(shuffleButton, BorderLayout.SOUTH);


        setVisible(true);
    }

    // load card images from a folder
    private void loadCardImages() {
        for (int i = 1; i <= 52; i++) {
            String imagePath = "cards/" + i + ".png"; //load from cards folder
            ImageIcon cardIcon = new ImageIcon(imagePath);
    
            // Resized the image, It was too big
            Image ResizedImage = cardIcon.getImage().getScaledInstance(80, 120,Image.SCALE_SMOOTH); 
            ImageIcon resizedIcon = new ImageIcon(ResizedImage);
    
            cards.add(resizedIcon); // Add the resized image to the list
        }
    }
    

    // shuffling cards
    private void shuffleAndDisplayCards() {
        Collections.shuffle(cards); // Shuffle the card list
       
        cardPanel.removeAll(); // Clear the current cards
         
        for (int i = 0; i < cardLabels.size(); i++) {
            cardLabels.get(i).setIcon(cards.get(i)); // Assign new shuffled cards
            cardPanel.add(cardLabels.get(i));
        }
     
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public static void main(String[] args) {
        new CardRandomizer();
    }
}
