package Cards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerGame extends JFrame {
    private List<String> cardList;
    private JPanel cardPanel;
    private JTextField betTextField;


    public PokerGame() {

        //title
        setTitle("Poker Game");
        ////ExitClose screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //panel size
        setSize(600, 500);

        cardList = initializeCardList();
        //grid loyour for put the card 3 row 5 col.
        cardPanel = new JPanel(new GridLayout(3, 5));
        
        //create button 
        JButton cardButton = new JButton("Show Cards");
        //add action listener
        cardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawCards();
            }
        });
    
        // bet panel
        JPanel betPanel = new JPanel();
        JLabel betLabel = new JLabel("Enter Bet:");
        betTextField = new JTextField(5);
        betPanel.add(betLabel);
        betPanel.add(betTextField);
    

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(betPanel); 
        buttonPanel.add(cardButton);
    
        add(cardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    //add all card in arrayList
    private List<String> initializeCardList() {
        List<String> cards = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            cards.add(i + "c.jpg");
            cards.add(i + "d.jpg");
            cards.add(i + "h.jpg");
            cards.add(i + "s.jpg");
        }
        return cards;
    }

    private void drawCards() {

        //if the bet amount/bet text is empty it shows an error message
        String betText = betTextField.getText();
        if (betText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a bet before showing cards", "Invalid Bet", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //We should write integer number 
        int bet;
        try {
            bet = Integer.parseInt(betText);
            if (bet <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid bet. Please enter a positive integer", "Invalid Bet", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //for mix card
        Collections.shuffle(cardList);

        cardPanel.removeAll();

        List<String> player1List = new ArrayList<>();
        List<String> player2List = new ArrayList<>();

        //We show the images thanks to the ImageIcon
        // Add 2 cards at the top
        // cardList.get(i); take the item at index 'i' from the cardList collection and give it to the cardFileName variable.
        //provides access to images
        for (int i = 0; i < 2; i++) {
            String cardFileName = cardList.get(i);
            player1List.add(cardFileName);
            ImageIcon cardImage = new ImageIcon(getClass().getResource(cardFileName));
            JLabel cardLabel = new JLabel(cardImage);
            cardPanel.add(cardLabel);
        }

        //add 3 empty labels
        for (int i = 0; i < 3; i++) {
            cardPanel.add(new JLabel());
        }

        //add 5 cards in the middle
        for (int i = 2; i < 7; i++) {
            String cardFileName = cardList.get(i);
            player1List.add(cardFileName);
            player2List.add(cardFileName);
            ImageIcon cardImage = new ImageIcon(getClass().getResource(cardFileName));
            JLabel cardLabel = new JLabel(cardImage);
            cardPanel.add(cardLabel);
        }

        //add 2 cards at the down
        for (int i = 7; i < 9; i++) {
            String cardFileName = cardList.get(i);
            player2List.add(cardFileName);
            ImageIcon cardImage = new ImageIcon(getClass().getResource(cardFileName));
            JLabel cardLabel = new JLabel(cardImage);
            cardPanel.add(cardLabel);
        }

        // Add 3 empty labels
        for (int i = 0; i < 3; i++) {
            cardPanel.add(new JLabel());
        }

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new PokerGame().setVisible(true);;
    }
}
