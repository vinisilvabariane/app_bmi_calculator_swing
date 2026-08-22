package telas;

import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame {
    public Menu() {
        UI.frame(this, "Pulso · saúde em números", 900, 590);
        JPanel root = new JPanel(new BorderLayout(34, 20));
        root.setOpaque(false); root.setBorder(BorderFactory.createEmptyBorder(48, 58, 44, 58));

        JPanel intro = new JPanel(); intro.setOpaque(false); intro.setLayout(new BoxLayout(intro, BoxLayout.Y_AXIS));
        intro.add(UI.eyebrow("PULSO / CALCULADORA DE SAÚDE")); intro.add(Box.createVerticalStrut(18));
        intro.add(UI.title("Entenda seu corpo\n", 38));
        JLabel headline = UI.title("um número de cada vez.", 38); headline.setForeground(UI.CORAL); intro.add(headline);
        intro.add(Box.createVerticalStrut(16));
        intro.add(UI.body("Ferramentas simples para acompanhar IMC,\nenergia diária e equilíbrio nutricional."));
        intro.add(Box.createVerticalGlue());
        JLabel note = UI.body("Seu ponto de partida para escolhas mais conscientes."); note.setFont(new Font("SansSerif", Font.ITALIC, 12)); intro.add(note);
        root.add(intro, BorderLayout.WEST);

        JPanel options = new JPanel(new GridLayout(3, 1, 0, 13)); options.setOpaque(false); options.setPreferredSize(new Dimension(330, 360));
        options.add(option("01", "Calcular IMC", "Peso e altura em uma leitura clara", UI.MINT, () -> open(new IMC())));
        options.add(option("02", "Gasto calórico", "Estime a energia que seu corpo usa", UI.LILAC, () -> open(new Gasto())));
        options.add(option("03", "Recomendação", "Distribua seus macros do dia", UI.CORAL, () -> open(new Recomendacao())));
        root.add(options, BorderLayout.EAST); add(root);
    }
    private JPanel option(String number, String title, String sub, Color accent, Runnable action) {
        JPanel card = UI.card(); card.setLayout(new BorderLayout(14, 0));
        JLabel n = UI.title(number, 19); n.setForeground(accent); card.add(n, BorderLayout.WEST);
        JPanel text = new JPanel(); text.setOpaque(false); text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.add(UI.title(title, 17)); text.add(Box.createVerticalStrut(5)); text.add(UI.body(sub)); card.add(text, BorderLayout.CENTER);
        JButton go = UI.button("→", accent); go.setPreferredSize(new Dimension(50, 42)); go.addActionListener(e -> action.run()); card.add(go, BorderLayout.EAST);
        return card;
    }
    private void open(JFrame frame) { frame.setVisible(true); dispose(); }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new Menu().setVisible(true)); }
}
