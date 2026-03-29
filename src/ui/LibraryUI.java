package ui;

import services.LibraryService;
import models.Book;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class LibraryUI {

    private LibraryService service = new LibraryService();

    // Brown palette
    private static final Color DARK_BROWN   = new Color(62, 39, 22);
    private static final Color MID_BROWN    = new Color(101, 67, 40);
    private static final Color WARM_BROWN   = new Color(139, 90, 43);
    private static final Color LIGHT_TAN    = new Color(210, 180, 140);
    private static final Color CREAM        = new Color(250, 243, 230);
    private static final Color ACCENT       = new Color(188, 120, 56);
    private static final Color TEXT_DARK    = new Color(40, 22, 8);
    private static final Color TEXT_LIGHT   = new Color(250, 243, 230);

    public LibraryUI() {
        // Custom look and feel tweaks
        UIManager.put("ScrollBar.thumb", WARM_BROWN);
        UIManager.put("ScrollBar.track", LIGHT_TAN);

        JFrame frame = new JFrame("Library Management System");
        frame.setSize(540, 560);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Root panel with dark brown background
        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(DARK_BROWN);
        frame.setContentPane(root);

        // ── Header ──────────────────────────────────────────────
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BROWN);
        header.setBorder(new EmptyBorder(22, 28, 14, 28));

        JLabel titleLabel = new JLabel("📚  Library Management");
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 22));
        titleLabel.setForeground(LIGHT_TAN);

        JLabel subtitleLabel = new JLabel("Manage your collection with ease");
        subtitleLabel.setFont(new Font("Georgia", Font.ITALIC, 12));
        subtitleLabel.setForeground(new Color(180, 145, 100));

        JPanel titleStack = new JPanel();
        titleStack.setLayout(new BoxLayout(titleStack, BoxLayout.Y_AXIS));
        titleStack.setOpaque(false);
        titleStack.add(titleLabel);
        titleStack.add(Box.createVerticalStrut(2));
        titleStack.add(subtitleLabel);

        header.add(titleStack, BorderLayout.CENTER);

        // Thin gold separator line
        JPanel separator = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(ACCENT);
                g.fillRect(0, 0, getWidth(), 1);
            }
        };
        separator.setPreferredSize(new Dimension(0, 1));
        separator.setOpaque(false);

        // ── Form Card ────────────────────────────────────────────
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(CREAM);
        card.setBorder(new CompoundBorder(
            new EmptyBorder(18, 20, 12, 20),
            new CompoundBorder(
                new LineBorder(LIGHT_TAN, 1, true),
                new EmptyBorder(20, 24, 20, 24)
            )
        ));

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6, 8, 6, 8);
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.WEST;

        // Fields
        JTextField idField     = styledField();
        JTextField titleField  = styledField();
        JTextField authorField = styledField();

        // Row 0 – ID
        gc.gridx = 0; gc.gridy = 0; gc.weightx = 0;
        card.add(styledLabel("Book ID"), gc);
        gc.gridx = 1; gc.weightx = 1;
        card.add(idField, gc);

        // Row 1 – Title
        gc.gridx = 0; gc.gridy = 1; gc.weightx = 0;
        card.add(styledLabel("Title"), gc);
        gc.gridx = 1; gc.weightx = 1;
        card.add(titleField, gc);

        // Row 2 – Author
        gc.gridx = 0; gc.gridy = 2; gc.weightx = 0;
        card.add(styledLabel("Author"), gc);
        gc.gridx = 1; gc.weightx = 1;
        card.add(authorField, gc);

        // ── Button Row ───────────────────────────────────────────
        JButton addBtn    = styledButton("Add Book",    "➕");
        JButton showBtn   = styledButton("Show Books",  "📋");
        JButton issueBtn  = styledButton("Issue Book",  "📤");
        JButton returnBtn = styledButton("Return Book", "📥");

        JPanel btnPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        btnPanel.setOpaque(false);
        btnPanel.setBorder(new EmptyBorder(14, 0, 0, 0));
        btnPanel.add(addBtn);
        btnPanel.add(showBtn);
        btnPanel.add(issueBtn);
        btnPanel.add(returnBtn);

        gc.gridx = 0; gc.gridy = 3; gc.gridwidth = 2; gc.weightx = 1;
        gc.insets = new Insets(4, 8, 0, 8);
        card.add(btnPanel, gc);

        // ── Output Area ──────────────────────────────────────────
        JTextArea output = new JTextArea(7, 40);
        output.setEditable(false);
        output.setFont(new Font("Courier New", Font.PLAIN, 13));
        output.setBackground(new Color(38, 24, 10));
        output.setForeground(LIGHT_TAN);
        output.setCaretColor(ACCENT);
        output.setBorder(new EmptyBorder(12, 14, 12, 14));
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setText("  Welcome! Use the fields above to manage books.");

        JScrollPane scroll = new JScrollPane(output);
        scroll.setBorder(new EmptyBorder(0, 20, 18, 20));
        scroll.setBackground(DARK_BROWN);
        scroll.getViewport().setBackground(new Color(38, 24, 10));

        // ── Compose Root ─────────────────────────────────────────
        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.add(card, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);

        root.add(header, BorderLayout.NORTH);
        root.add(separator, BorderLayout.CENTER);
        root.add(center, BorderLayout.SOUTH);

        // Re-layout: just use BorderLayout properly
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(header, BorderLayout.NORTH);
        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.add(card, BorderLayout.NORTH);
        body.add(scroll, BorderLayout.CENTER);
        root.add(body, BorderLayout.CENTER);

        // ── Action Listeners (unchanged logic) ───────────────────
        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String title  = titleField.getText();
                String author = authorField.getText();
                service.addBook(new Book(id, title, author));
                output.setText("  ✅  Book Added Successfully!");
            } catch (Exception ex) {
                output.setText("  ⚠️  Invalid Input! ID must be a number.");
            }
        });

        showBtn.addActionListener(e -> {
            String books = service.getAllBooks();
            output.setText(books);
        });

        issueBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                service.issueBook(id);
                output.setText("  📤  Book Issued!");
            } catch (Exception ex) {
                output.setText("  ⚠️  Enter a valid Book ID.");
            }
        });

        returnBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                service.returnBook(id);
                output.setText("  📥  Book Returned!");
            } catch (Exception ex) {
                output.setText("  ⚠️  Enter a valid Book ID.");
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ── Helpers ──────────────────────────────────────────────────

    private JLabel styledLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Georgia", Font.BOLD, 13));
        lbl.setForeground(MID_BROWN);
        return lbl;
    }

    private JTextField styledField() {
        JTextField tf = new JTextField(16);
        tf.setFont(new Font("Georgia", Font.PLAIN, 13));
        tf.setForeground(TEXT_DARK);
        tf.setBackground(new Color(245, 235, 215));
        tf.setCaretColor(WARM_BROWN);
        tf.setBorder(new CompoundBorder(
            new LineBorder(LIGHT_TAN, 1, true),
            new EmptyBorder(6, 10, 6, 10)
        ));
        return tf;
    }

    private JButton styledButton(String text, String icon) {
        JButton btn = new JButton("<html><center>" + icon + "<br><span style='font-size:10px'>" + text + "</span></center></html>") {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(DARK_BROWN);
                } else if (getModel().isRollover()) {
                    g2.setColor(WARM_BROWN);
                } else {
                    g2.setColor(MID_BROWN);
                }
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Georgia", Font.BOLD, 12));
        btn.setForeground(TEXT_LIGHT);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(100, 52));
        return btn;
    }
}