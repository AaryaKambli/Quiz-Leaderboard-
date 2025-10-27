package gui;

import service.Leaderboard;

import javax.swing.*;
import java.awt.*;

/**
 * QuizLeaderboardApp (Main View/Controller): The entry point for the GUI.
 * This class builds the visual components and handles all user interactions.
 */
public class QuizLeaderboardApp {
    private final Leaderboard leaderboard = new Leaderboard();
    private final LeaderboardTableModel tableModel = new LeaderboardTableModel(leaderboard);

    private JFrame frame;
    private JTable leaderboardTable;
    private JTextField nameField;
    private JSpinner scoreSpinner;

    /**
     * Initializes all UI components and displays the window.
     */
    public void createAndShowGUI() {
        // --- Frame Setup ---
        frame = new JFrame("Official Quiz Score Ranking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 480); // Slight size change
        frame.setLayout(new BorderLayout(15, 15)); // Increased border spacing

        // --- Leaderboard Table (Center) ---
        leaderboardTable = new JTable(tableModel);
        leaderboardTable.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Slightly different font choice
        leaderboardTable.setRowHeight(28);
        leaderboardTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 17));

        // Aesthetic: Prevent column reordering
        leaderboardTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // --- Input Panel (Bottom) ---
        JPanel inputPanel = createInputPanel();
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Add a small title header (a common human touch)
        JLabel headerLabel = new JLabel("High Score Entry", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        frame.add(headerLabel, BorderLayout.NORTH);

        // --- Display ---
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Creates the panel containing input fields and the submit button.
     */
    private JPanel createInputPanel() {
        // Changed layout slightly for a more custom look
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components

        // 1. Name Input
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Player Name:"), gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameField, gbc);

        // 2. Score Input
        gbc.gridx = 2; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Score:"), gbc);
        SpinnerNumberModel scoreModel = new SpinnerNumberModel(1000, 0, 10000, 10);
        scoreSpinner = new JSpinner(scoreModel);
        gbc.gridx = 3; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(scoreSpinner, gbc);

        // 3. Submit Button
        JButton submitButton = new JButton("Submit Score");
        submitButton.setBackground(new Color(30, 144, 255)); // Dodger Blue
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> handleSubmitScore());

        gbc.gridx = 4; gbc.gridy = 0; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.EAST;
        panel.add(submitButton, gbc);

        return panel;
    }

    /**
     * Handles the submission of a new score from the input fields.
     */
    private void handleSubmitScore() {
        String name = nameField.getText(); // Name is trimmed in the Leaderboard service
        Integer score = (Integer) scoreSpinner.getValue();

        // Simple check to show a message if the name field is empty, instead of just using "Anonymous"
        if (name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a name for the entry.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return; // Stop processing if input is bad
        }

        // 1. Update the Service/Model
        leaderboard.addEntry(name, score);

        // 2. Clear input fields
        nameField.setText("");
        scoreSpinner.setValue(1000);

        // 3. Update the View (and scroll to the top to see the new entry)
        tableModel.refresh();
        leaderboardTable.scrollRectToVisible(leaderboardTable.getCellRect(0, 0, true));
    }

    /**
     * Main method to launch the application on the Event Dispatch Thread (EDT).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuizLeaderboardApp app = new QuizLeaderboardApp();
            app.createAndShowGUI();
        });
    }
}
