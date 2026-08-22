package telas;

import java.awt.*;
import java.awt.geom.Arc2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Shared visual language for the application. */
public final class UI {
    public static final Color INK = new Color(20, 31, 55);
    public static final Color MUTED = new Color(103, 116, 139);
    public static final Color SURFACE = new Color(255, 255, 255);
    public static final Color CANVAS = new Color(244, 247, 251);
    public static final Color CORAL = new Color(238, 105, 88);
    public static final Color MINT = new Color(65, 184, 150);
    public static final Color LILAC = new Color(122, 103, 214);
    private UI() { }

    public static void frame(JFrame frame, String title, int width, int height) {
        frame.setTitle(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(width, height));
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(CANVAS);
    }

    public static JLabel eyebrow(String text) {
        JLabel label = new JLabel(text.toUpperCase());
        label.setFont(new Font("SansSerif", Font.BOLD, 11));
        label.setForeground(CORAL);
        return label;
    }

    public static JLabel title(String text, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, size));
        label.setForeground(INK);
        return label;
    }

    public static JLabel body(String text) {
        JLabel label = new JLabel("<html>" + text + "</html>");
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        label.setForeground(MUTED);
        return label;
    }

    public static JPanel card() {
        JPanel p = new RoundedPanel(18, SURFACE);
        p.setBorder(new EmptyBorder(22, 24, 22, 24));
        return p;
    }

    public static JTextField field(String hint) {
        JTextField f = new JTextField();
        f.setFont(new Font("SansSerif", Font.BOLD, 16));
        f.setForeground(INK);
        f.setCaretColor(CORAL);
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(222, 228, 237), 1),
                new EmptyBorder(9, 12, 9, 12)));
        f.setToolTipText(hint);
        f.setPreferredSize(new Dimension(150, 43));
        return f;
    }

    public static JButton button(String text, Color color) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setForeground(Color.WHITE);
        b.setBackground(color);
        b.setBorder(new EmptyBorder(12, 20, 12, 20));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    public static JButton ghost(String text) {
        JButton b = button(text, CANVAS);
        b.setForeground(MUTED);
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 226, 235)),
                new EmptyBorder(10, 18, 10, 18)));
        return b;
    }

    public static void back(JFrame current) {
        new Menu().setVisible(true);
        current.dispose();
    }

    public static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color color;
        public RoundedPanel(int radius, Color color) { this.radius = radius; this.color = color; setOpaque(false); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color); g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius); g2.dispose();
            super.paintComponent(g);
        }
    }

    public static class Gauge extends JPanel {
        private double value;
        public Gauge() { setOpaque(false); setPreferredSize(new Dimension(210, 155)); }
        public void setValue(double value) { this.value = value; repaint(); }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g); Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int x = 17, y = 14, w = getWidth() - 34, h = getHeight() * 2 - 48;
            g2.setStroke(new BasicStroke(13, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(new Color(230, 235, 242)); g2.draw(new Arc2D.Double(x, y, w, h, 180, -180, Arc2D.OPEN));
            double progress = Math.max(0, Math.min(40, value)) / 40.0;
            g2.setColor(value == 0 ? new Color(210, 218, 230) : value < 18.5 ? LILAC : value < 25 ? MINT : CORAL);
            g2.draw(new Arc2D.Double(x, y, w, h, 180, -180 * progress, Arc2D.OPEN));
            g2.dispose();
        }
    }
}
