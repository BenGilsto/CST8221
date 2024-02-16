package basic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Connect4UI2 extends JFrame{
	private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    // Images
    private ImageIcon emptyIcon;
    private ImageIcon whiteIcon;
    private ImageIcon blackIcon;

    // UI components
    private JLabel[][] slots;
    private JLabel lblTitle, lblRoundInfo, lblCurrentScore, lblPlayerTurn, lblTimer, lblScores;
    private JTextArea txtChatArea, txtMoveHistory;
    private JTextField txtMessageInput;
    private JButton btnAbortGame, btnSendMessage;

    public Connect4UI2() {
        loadImages(); // Method to load images
        initializeUI();
        
    }

    private void loadImages() {
        // Adjust paths as necessary
        emptyIcon = new ImageIcon(getClass().getResource("A12empty.png"));
        whiteIcon = new ImageIcon(getClass().getResource("A12white.png"));
        blackIcon = new ImageIcon(getClass().getResource("A12black.png"));
    }

    private void initializeUI() {
        setTitle("Connect 4 Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
	

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        createMenus(menuBar);
        setJMenuBar(menuBar);

        // Title Label
        lblTitle = new JLabel("CONNECT 4", SwingConstants.CENTER);

        // Abort Game Button
        btnAbortGame = new JButton("Abort Game");

        // Game Board Panel
        JPanel boardPanel = new JPanel(new GridLayout(ROWS, COLUMNS, 0, 0));
        boardPanel.setBackground(new Color(255, 242, 0));
        slots = new JLabel[ROWS][COLUMNS];
        Dimension slotSize = new Dimension(emptyIcon.getIconWidth(), emptyIcon.getIconHeight());

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                slots[i][j] = new JLabel(emptyIcon, SwingConstants.CENTER);
                slots[i][j].setOpaque(true);
                slots[i][j].setPreferredSize(slotSize);
                boardPanel.add(slots[i][j]);
            }
        }

        // Simulate a few "played" chips in the UI
        simulateGameProgress();

        // Right Section Components
        lblRoundInfo = new JLabel("Round 1", SwingConstants.CENTER);
        lblCurrentScore = new JLabel("Score: 0 - 0", SwingConstants.CENTER);
        lblPlayerTurn = new JLabel("Player 1's Turn", SwingConstants.CENTER);
        lblTimer = new JLabel("30 s", SwingConstants.CENTER);
        lblScores = new JLabel("$1000", SwingConstants.CENTER);
        JPanel rightPanel = new JPanel(new GridLayout(5, 1));
        rightPanel.add(lblRoundInfo);
        rightPanel.add(lblCurrentScore);
        rightPanel.add(lblPlayerTurn);
        rightPanel.add(lblTimer);
        rightPanel.add(lblScores);

        // Chat Area and Move History
        setupTextAreas();

        // Bottom Panel for message input and send button
        setupBottomPanel();

        // Adding Components to Frame
        addComponentsToFrame(boardPanel, rightPanel);

        // Display the window
        finalizeWindow();
    }

    private void createMenus(JMenuBar menuBar) {
        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newgameItem = new JMenuItem("New Game");
        JMenuItem newgameItem2 = new JMenuItem("Quit Game");
        fileMenu.add(newgameItem);
        fileMenu.add(newgameItem2);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem aboutItem2 = new JMenuItem("Settings");
        helpMenu.add(aboutItem);
        helpMenu.add(aboutItem2);
        
        
        JMenu languageMenu = new JMenu("Language");
        JMenuItem englishItem = new JMenuItem("English");
        JMenuItem frenchItem = new JMenuItem("French");
        languageMenu.add(englishItem);
        languageMenu.add(frenchItem);
        
        JMenu networkMenu = new JMenu("Network");
        JMenuItem settingsItem = new JMenuItem("Chat Settings");
        networkMenu.add(settingsItem);

        // Adding Menus to MenuBar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(languageMenu);
        menuBar.add(networkMenu);
    }
    
    private void simulateGameProgress() {
        slots[4][2].setIcon(blackIcon); // Simulate a black chip
        slots[4][3].setIcon(whiteIcon); // Simulate a white chip
    }

    private void setupTextAreas() {
        txtChatArea = new JTextArea(5, 20);
        txtChatArea.setEditable(false);
        txtMoveHistory = new JTextArea(5, 20);
        txtMoveHistory.setEditable(false);
    }

    private void setupBottomPanel() {
        txtMessageInput = new JTextField(30);
        btnSendMessage = new JButton("Send");
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(txtMessageInput, BorderLayout.CENTER);
        bottomPanel.add(btnSendMessage, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addComponentsToFrame(JPanel boardPanel, JPanel rightPanel) {
        add(lblTitle, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(new JScrollPane(txtChatArea), BorderLayout.WEST); // Wrapping chat area in a scroll pane
    }

    private void finalizeWindow() {
        pack();
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);
        setVisible(true);
        

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Connect4UI2());
    }
}
