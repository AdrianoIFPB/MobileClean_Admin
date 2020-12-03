import javax.swing.*;

class ValidaLogin {
    private ValidaEntrada validaEntrada = new ValidaEntrada();

    String login() {

        String matricula = JOptionPane.showInputDialog(null,
                "Informe sua Matrícula", "LOGIN", 3);
        boolean loginValido = validaEntrada.verificaEntradaDigitada(matricula);
        if (loginValido) return matricula;
        return null;
    }
}