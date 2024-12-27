import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class TiketyuApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TiketyuApp::createSplashScreen);
    }

    // Splash Screen
    public static void createSplashScreen() {
        JFrame splashFrame = new JFrame("Tiketyu");
        splashFrame.setSize(500, 500);
        splashFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        splashFrame.setLayout(new BorderLayout());

        try {
            ImageIcon logo = new ImageIcon("TIKETYU (2).png");
            Image logoImage = logo.getImage();
            Image scaledLogo = logoImage.getScaledInstance(500, 300, Image.SCALE_SMOOTH);
            logo = new ImageIcon(scaledLogo);

            JLabel logoLabel = new JLabel(logo);
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel splashLabel = new JLabel("Welcome to Tiketyu", SwingConstants.CENTER);
            splashLabel.setFont(new Font("Arial", Font.BOLD, 24));
            splashLabel.setForeground(Color.WHITE);

            splashFrame.add(logoLabel, BorderLayout.NORTH);
            splashFrame.add(splashLabel, BorderLayout.CENTER);
            splashFrame.getContentPane().setBackground(new Color(40, 116, 166));

            splashFrame.setLocationRelativeTo(null);
            splashFrame.setVisible(true);

            Timer timer;
            timer = new Timer(3000, e -> {
                splashFrame.dispose();
                createMainApp();
            });
            timer.setRepeats(false);
            timer.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading splash screen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main Application
    public static void createMainApp() {
        JFrame mainFrame = new JFrame("Tiketyu - Book Your Ticket");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(40, 116, 166));

        JLabel headerLabel = new JLabel("Tiketyu - Choose Your Movie", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        mainFrame.add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(236, 240, 241));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Movie Selection
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Choose a Movie:"), gbc);

        gbc.gridx = 1;
        JComboBox<String> movieDropdown = new JComboBox<>(getMovieList());
        mainPanel.add(movieDropdown, gbc);

        // Location Selection
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Choose a Location:"), gbc);

        gbc.gridx = 1;
        JComboBox<String> locationDropdown = new JComboBox<>(new String[]{"Cinema XXI Mall A", "Cinema XXI Mall B", "Cinema XXI Downtown"});
        mainPanel.add(locationDropdown, gbc);

        // Show Time Selection
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Choose Show Time:"), gbc);

        gbc.gridx = 1;
        JComboBox<String> timeDropdown = new JComboBox<>(new String[]{"08:00 AM", "10:30 AM", "01:00 PM", "03:30 PM", "06:00 PM", "08:30 PM"});
        mainPanel.add(timeDropdown, gbc);

        // Payment Method Selection
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Choose Payment Method:"), gbc);

        gbc.gridx = 1;
        JComboBox<String> paymentDropdown = new JComboBox<>(new String[]{"Cash", "Debit", "PayLater"});
        mainPanel.add(paymentDropdown, gbc);

        mainFrame.add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(236, 240, 241));

        JButton seatSelectionButton = new JButton("Select Seats");
        seatSelectionButton.setBackground(new Color(46, 204, 113));
        seatSelectionButton.setForeground(Color.WHITE);
        seatSelectionButton.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(seatSelectionButton);

        JButton cancelOrderButton = new JButton("Cancel Order");
        cancelOrderButton.setBackground(new Color(231, 76, 60));
        cancelOrderButton.setForeground(Color.WHITE);
        cancelOrderButton.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(cancelOrderButton);

        mainFrame.add(buttonPanel, BorderLayout.SOUTH);

        seatSelectionButton.addActionListener(e -> openSeatSelection(mainFrame, movieDropdown, locationDropdown, timeDropdown, paymentDropdown));

        cancelOrderButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to cancel the order?", "Cancel Order", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                resetSelections(movieDropdown, locationDropdown, timeDropdown, paymentDropdown);
                JOptionPane.showMessageDialog(mainFrame, "Order has been cancelled.", "Order Cancelled", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    // Get a list of movies manually (without API)
    public static String[] getMovieList() {
        return new String[]{"Avatar", "Titanic", "The Dark Knight", "Inception", "The Avengers", "Interstellar"};
    }

    public static void resetSelections(JComboBox<String> movieDropdown, JComboBox<String> locationDropdown, JComboBox<String> timeDropdown, JComboBox<String> paymentDropdown) {
        movieDropdown.setSelectedIndex(0);
        locationDropdown.setSelectedIndex(0);
        timeDropdown.setSelectedIndex(0);
        paymentDropdown.setSelectedIndex(0);
    }

    public static void openSeatSelection(JFrame parentFrame, JComboBox<String> movieDropdown, JComboBox<String> locationDropdown, JComboBox<String> timeDropdown, JComboBox<String> paymentDropdown) {
        JFrame seatFrame = new JFrame("Select Your Seats");
        seatFrame.setSize(800, 600);
        seatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        seatFrame.setLayout(new BorderLayout());

        JPanel seatPanel = new JPanel(new GridLayout(5, 5, 5, 5));
        seatPanel.setBackground(new Color(236, 240, 241));

        JButton[][] seats = new JButton[5][5];
        boolean[][] seatStatus = new boolean[5][5];
        ArrayList<String> selectedSeats = new ArrayList<>();

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                seats[row][col] = new JButton("R" + (row + 1) + "C" + (col + 1));
                seats[row][col].setBackground(Color.GREEN);
                final int r = row;
                final int c = col;

                seats[row][col].addActionListener(e -> {
                    if (!seatStatus[r][c]) {
                        seats[r][c].setBackground(Color.RED);
                        seatStatus[r][c] = true;
                        selectedSeats.add("R" + (r + 1) + "C" + (c + 1));
                    } else {
                        int cancelResponse = JOptionPane.showConfirmDialog(seatFrame, "Do you want to cancel this booking?", "Cancel Seat", JOptionPane.YES_NO_OPTION);
                        if (cancelResponse == JOptionPane.YES_OPTION) {
                            seats[r][c].setBackground(Color.GREEN);
                            seatStatus[r][c] = false;
                            selectedSeats.remove("R" + (r + 1) + "C" + (c + 1));
                        }
                    }
                });
                seatPanel.add(seats[row][col]);
            }
        }

        seatFrame.add(seatPanel, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.setBackground(new Color(46, 204, 113));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        seatFrame.add(confirmButton, BorderLayout.SOUTH);

        confirmButton.addActionListener(e -> {
            if (selectedSeats.isEmpty()) {
                JOptionPane.showMessageDialog(seatFrame, "Please select at least one seat.", "No Seats Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String movie = (String) movieDropdown.getSelectedItem();
            String location = (String) locationDropdown.getSelectedItem();
            String time = (String) timeDropdown.getSelectedItem();
            String paymentMethod = (String) paymentDropdown.getSelectedItem();

            displayTicketResult(movie, location, time, paymentMethod, selectedSeats);
            seatFrame.dispose();
        });

        seatFrame.setLocationRelativeTo(parentFrame);
        seatFrame.setVisible(true);
    }

    public static void displayTicketResult(String movie, String location, String time, String paymentMethod, ArrayList<String> selectedSeats) {
        String seatList = String.join(", ", selectedSeats);
        String message = String.format(
                "=== Tiket Pemesanan Tiketyu ===\n" +
                        "Film            : %s\n" +
                        "Lokasi          : %s\n" +
                        "Waktu Tayang    : %s\n" +
                        "Metode Pembayaran: %s\n" +
                        "Kursi Terpilih  : %s\n\n" +
                        "Terima kasih telah menggunakan Tiketyu!",
                movie, location, time, paymentMethod, seatList
        );

        JOptionPane.showMessageDialog(null, message, "Hasil Tiket Pemesanan", JOptionPane.INFORMATION_MESSAGE);

        try (FileWriter writer = new FileWriter("hasil_tiket.txt")) {
            writer.write(message);
            JOptionPane.showMessageDialog(null, "Tiket berhasil disimpan ke file 'hasil_tiket.txt'", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error menyimpan tiket: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}