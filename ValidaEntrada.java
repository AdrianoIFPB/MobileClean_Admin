import javax.swing.*;
import java.util.InputMismatchException;

class ValidaEntrada {
    private SaidaSegura saidaSegura = SaidaSegura.getInstance();
    boolean verificaEntradaDigitada(String entrada){
        if (entrada == null) {
            saidaSegura.fecharPrograma();
            return false;

        }
        if (entrada.length() != 4){
            JOptionPane.showMessageDialog(null, "Verifique a quantidade de dígitos!",
                    "ERRO!", 0);
            return false;
        }
        try {
            int entradaVerificada = Integer.parseInt(entrada);
        }
        catch (InputMismatchException | NumberFormatException | NullPointerException e){
            JOptionPane.showMessageDialog(null, "Caracter inválido. Digite apenas números",
                    "ERRO!",0);
            return false;

        }
        return true;
    }


}