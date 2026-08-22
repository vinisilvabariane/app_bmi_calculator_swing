package telas;

import java.awt.*;
import javax.swing.*;

public class Recomendacao extends JFrame {
    private final JTextField calories = UI.field("Ex.: 2200");
    private final JLabel carb = UI.title("—", 25), prot = UI.title("—", 25), fat = UI.title("—", 25);
    public Recomendacao() {
        UI.frame(this, "Pulso · recomendação", 780, 520);
        JPanel root = new JPanel(new BorderLayout(25, 20)); root.setOpaque(false); root.setBorder(BorderFactory.createEmptyBorder(34, 48, 38, 48));
        JPanel h = new JPanel(); h.setOpaque(false); h.setLayout(new BoxLayout(h, BoxLayout.Y_AXIS)); h.add(UI.eyebrow("03 / EQUILÍBRIO NUTRICIONAL")); h.add(Box.createVerticalStrut(10)); h.add(UI.title("Divida sua energia.", 29)); h.add(Box.createVerticalStrut(6)); h.add(UI.body("Uma sugestão simples de macros a partir das suas calorias diárias.")); root.add(h, BorderLayout.NORTH);
        JPanel input = UI.card(); input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS)); input.add(UI.title("Sua meta diária", 18)); input.add(Box.createVerticalStrut(18)); input.add(UI.eyebrow("CALORIAS")); input.add(calories); input.add(Box.createVerticalStrut(20)); JButton calc = UI.button("Distribuir macros  →", UI.CORAL); calc.setAlignmentX(Component.LEFT_ALIGNMENT); calc.addActionListener(e -> calculate()); input.add(calc); input.add(Box.createVerticalGlue()); JButton back = UI.ghost("←  Voltar"); back.setAlignmentX(Component.LEFT_ALIGNMENT); back.addActionListener(e -> UI.back(this)); input.add(back); root.add(input, BorderLayout.WEST);
        JPanel output = UI.card(); output.setLayout(new GridLayout(3, 1, 0, 8)); output.add(macro("CARBOIDRATOS", "50% · energia para o dia", carb, UI.MINT)); output.add(macro("PROTEÍNAS", "25% · construção e reparo", prot, UI.LILAC)); output.add(macro("GORDURAS", "25% · suporte e saciedade", fat, UI.CORAL)); root.add(output, BorderLayout.CENTER); add(root);
    }
    private JPanel macro(String name, String sub, JLabel value, Color accent) { JPanel p = new JPanel(new BorderLayout()); p.setOpaque(false); JPanel text = new JPanel(); text.setOpaque(false); text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS)); JLabel n = UI.eyebrow(name); n.setForeground(accent); text.add(n); text.add(UI.body(sub)); p.add(text, BorderLayout.WEST); p.add(value, BorderLayout.EAST); return p; }
    private void calculate() { try { double c = Double.parseDouble(calories.getText().trim().replace(',', '.')); if (c <= 0) throw new NumberFormatException(); carb.setText(String.format("%.0f g", c * .5 / 4)); prot.setText(String.format("%.0f g", c * .25 / 4)); fat.setText(String.format("%.0f g", c * .25 / 9)); } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Digite uma meta de calorias válida.", "Confira os dados", JOptionPane.WARNING_MESSAGE); } }
}
