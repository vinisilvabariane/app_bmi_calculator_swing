package telas;

import java.awt.*;
import javax.swing.*;

public class Gasto extends JFrame {
    private final JTextField peso = UI.field("kg"), altura = UI.field("cm"), idade = UI.field("anos");
    private final JComboBox<String> atividade = new JComboBox<>(new String[]{"Sedentário", "Leve", "Moderado", "Ativo", "Extremamente ativo"});
    private final JRadioButton homem = new JRadioButton("Homem"), mulher = new JRadioButton("Mulher");
    private final JLabel basal = UI.title("—", 28), total = UI.title("—", 28);
    public Gasto() {
        UI.frame(this, "Pulso · gasto calórico", 820, 575);
        JPanel root = new JPanel(new BorderLayout(26, 20)); root.setOpaque(false); root.setBorder(BorderFactory.createEmptyBorder(34, 48, 38, 48));
        JPanel h = new JPanel(); h.setOpaque(false); h.setLayout(new BoxLayout(h, BoxLayout.Y_AXIS)); h.add(UI.eyebrow("02 / ENERGIA DIÁRIA")); h.add(Box.createVerticalStrut(10)); h.add(UI.title("Quanto seu corpo precisa?", 28)); h.add(Box.createVerticalStrut(6)); h.add(UI.body("Uma estimativa para entender melhor seu ritmo de energia.")); root.add(h, BorderLayout.NORTH);
        JPanel form = UI.card(); form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS)); form.add(UI.title("Perfil", 18)); form.add(Box.createVerticalStrut(18));
        form.add(UI.eyebrow("SEXO BIOLÓGICO")); JPanel radios = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 8)); radios.setOpaque(false); ButtonGroup group = new ButtonGroup(); group.add(homem); group.add(mulher); homem.setSelected(true); styleRadio(homem); styleRadio(mulher); radios.add(homem); radios.add(Box.createHorizontalStrut(15)); radios.add(mulher); form.add(radios);
        form.add(UI.eyebrow("DADOS")); form.add(row("Peso", peso, "kg")); form.add(row("Altura", altura, "cm")); form.add(row("Idade", idade, "anos")); form.add(UI.eyebrow("ROTINA")); atividade.setFont(new Font("SansSerif", Font.PLAIN, 14)); atividade.setPreferredSize(new Dimension(240, 38)); form.add(atividade); form.add(Box.createVerticalStrut(17));
        JButton calc = UI.button("Estimar gasto  →", UI.LILAC); calc.setAlignmentX(Component.LEFT_ALIGNMENT); calc.addActionListener(e -> calculate()); form.add(calc); JButton back = UI.ghost("←  Voltar"); back.setAlignmentX(Component.LEFT_ALIGNMENT); back.addActionListener(e -> UI.back(this)); form.add(Box.createVerticalStrut(10)); form.add(back); root.add(form, BorderLayout.WEST);
        JPanel out = UI.card(); out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS)); out.add(UI.eyebrow("SUA ESTIMATIVA")); out.add(Box.createVerticalStrut(22)); out.add(metric("METABOLISMO BASAL", basal, "kcal em repouso")); out.add(Box.createVerticalStrut(22)); out.add(metric("GASTO TOTAL DIÁRIO", total, "kcal com sua rotina")); out.add(Box.createVerticalGlue()); out.add(UI.body("Estimativa baseada na fórmula de Mifflin-St Jeor.")); root.add(out, BorderLayout.CENTER); add(root);
    }
    private void styleRadio(JRadioButton b) { b.setOpaque(false); b.setFont(new Font("SansSerif", Font.PLAIN, 13)); b.setForeground(UI.INK); }
    private JPanel row(String text, JTextField input, String unit) { JPanel p = new JPanel(new BorderLayout(10, 0)); p.setOpaque(false); p.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0)); p.add(UI.body(text), BorderLayout.WEST); p.add(input, BorderLayout.CENTER); p.add(UI.body(unit), BorderLayout.EAST); return p; }
    private JPanel metric(String name, JLabel number, String caption) { JPanel p = new JPanel(); p.setOpaque(false); p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS)); p.add(UI.eyebrow(name)); p.add(number); p.add(UI.body(caption)); return p; }
    private void calculate() { try { double kg = num(peso.getText()), cm = num(altura.getText()), years = num(idade.getText()); double b = homem.isSelected() ? 66 + 13.7 * kg + 5 * cm - 6.8 * years : 655 + 9.6 * kg + 1.8 * cm - 4.7 * years; double[] factors = {1.2, 1.375, 1.55, 1.725, 1.9}; double t = b * factors[atividade.getSelectedIndex()]; basal.setText(String.format("%.0f", b) + " kcal"); total.setText(String.format("%.0f", t) + " kcal"); } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Preencha peso, altura e idade com números válidos.", "Confira os dados", JOptionPane.WARNING_MESSAGE); } }
    private double num(String s) { return Double.parseDouble(s.trim().replace(',', '.')); }
}
