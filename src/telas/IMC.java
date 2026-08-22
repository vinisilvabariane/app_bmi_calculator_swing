package telas;

import codes.CalcImc;
import java.awt.*;
import javax.swing.*;

public class IMC extends JFrame {
    private final JTextField peso = UI.field("Ex.: 72");
    private final JTextField altura = UI.field("Ex.: 1,75");
    private final JLabel value = UI.title("—", 48);
    private final JLabel status = UI.title("Preencha seus dados", 18);
    private final UI.Gauge gauge = new UI.Gauge();
    public IMC() {
        UI.frame(this, "Pulso · IMC", 820, 560);
        JPanel root = new JPanel(new BorderLayout(26, 20)); root.setOpaque(false); root.setBorder(BorderFactory.createEmptyBorder(34, 48, 38, 48));
        JPanel header = new JPanel(); header.setOpaque(false); header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(UI.eyebrow("01 / ÍNDICE DE MASSA CORPORAL")); header.add(Box.createVerticalStrut(10)); header.add(UI.title("Seu peso em perspectiva.", 28));
        header.add(Box.createVerticalStrut(6)); header.add(UI.body("Uma leitura rápida para começar a acompanhar sua saúde."));
        root.add(header, BorderLayout.NORTH);
        JPanel form = UI.card(); form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.add(UI.title("Insira seus dados", 18)); form.add(Box.createVerticalStrut(20));
        form.add(label("PESO", "kg")); form.add(peso); form.add(Box.createVerticalStrut(15)); form.add(label("ALTURA", "m")); form.add(altura); form.add(Box.createVerticalStrut(22));
        JButton calc = UI.button("Calcular meu IMC  →", UI.CORAL); calc.setAlignmentX(Component.LEFT_ALIGNMENT); calc.addActionListener(e -> calculate()); form.add(calc); form.add(Box.createVerticalGlue());
        JButton back = UI.ghost("←  Voltar"); back.setAlignmentX(Component.LEFT_ALIGNMENT); back.addActionListener(e -> UI.back(this)); form.add(Box.createVerticalStrut(12)); form.add(back);
        root.add(form, BorderLayout.WEST);
        JPanel result = UI.card(); result.setLayout(new BorderLayout());
        JPanel top = new JPanel(); top.setOpaque(false); top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS)); top.add(UI.eyebrow("LEITURA ATUAL")); top.add(Box.createVerticalStrut(8));
        value.setAlignmentX(Component.CENTER_ALIGNMENT); status.setAlignmentX(Component.CENTER_ALIGNMENT); top.add(value); top.add(status); result.add(top, BorderLayout.NORTH);
        result.add(gauge, BorderLayout.CENTER); JPanel foot = new JPanel(); foot.setOpaque(false); foot.add(UI.body("Faixa saudável: 18,5 a 24,9")); result.add(foot, BorderLayout.SOUTH); root.add(result, BorderLayout.CENTER); add(root);
    }
    private JPanel label(String name, String unit) { JPanel p = new JPanel(new BorderLayout()); p.setOpaque(false); JLabel l = UI.eyebrow(name); p.add(l, BorderLayout.WEST); JLabel u = UI.body(unit); p.add(u, BorderLayout.EAST); return p; }
    private void calculate() {
        try { double p = number(peso.getText()), a = number(altura.getText()); double imc = new CalcImc().calcular(p, a); value.setText(String.format("%.1f", imc).replace('.', ',')); status.setText(CalcImc.classificacao(imc)); status.setForeground(imc >= 18.5 && imc < 25 ? UI.MINT : UI.CORAL); gauge.setValue(imc); }
        catch (Exception ex) { JOptionPane.showMessageDialog(this, "Digite peso e altura válidos e maiores que zero.", "Confira os dados", JOptionPane.WARNING_MESSAGE); }
    }
    private double number(String text) { return Double.parseDouble(text.trim().replace(',', '.')); }
}
