package codes;

public class CalcImc {
    public double calcular(double peso, double altura) {
        if (peso <= 0 || altura <= 0) throw new IllegalArgumentException("Valores devem ser positivos");
        return peso / (altura * altura);
    }
    public static String classificacao(double imc) {
        if (imc < 18.5) return "Abaixo do peso";
        if (imc < 25) return "Peso saudável";
        if (imc < 30) return "Sobrepeso";
        if (imc < 35) return "Obesidade grau I";
        return "Obesidade grau II ou maior";
    }
}
