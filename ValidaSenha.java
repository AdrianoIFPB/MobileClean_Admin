import javax.swing.*;

public class ValidaSenha {

    private ValidaEntrada validaEntrada = new ValidaEntrada();
    private SaidaSegura saidaSegura = SaidaSegura.getInstance();

    public String registraSenha(){
        int cont=0;
        while (!saidaSegura.getSairSenha()){
            JLabel label = new JLabel("Digite sua senha com 4 dígitos e somente números");
            JPasswordField jpf = new JPasswordField();
            JOptionPane.showConfirmDialog(null, new Object[]{label, jpf}, "SENHA: ", JOptionPane.OK_CANCEL_OPTION);
            String senha = new String(jpf.getPassword());
            if (validaEntrada.verificaEntradaDigitada(senha)){
                saidaSegura.setSairSenha(true);
                return senha;
            }
            else {
                cont++;
            }
            if (cont == 2){
                JOptionPane.showMessageDialog(null, "Você tem apenas mais 1(uma) tentativa!",
                        "ATENÇÃO", 2);
            }
            else if (cont == 3){
                saidaSegura.fecharPrograma();
            }
        }
        return null;
    }

    public boolean verificaSenha(String senhaSalva, String senhaDigitada){

        String senha = senhaDigitada;
        for (int i=0; i<3; i++){
            if (senhaSalva.equals(senha)){
                return true;
            }
            JOptionPane.showMessageDialog(null, "Senha não confere!",
                    "ATENÇÃO", 2);

            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Você tem apenas mais 1(uma) tentativa!",
                        "ATENÇÃO", 2);
            }
            if (i!=2){

                JLabel label = new JLabel("Digite sua senha com 4 dígitos e somente números");
                JPasswordField jpf = new JPasswordField();
                JOptionPane.showConfirmDialog(null, new Object[]{label, jpf}, "SENHA: ",JOptionPane.OK_CANCEL_OPTION);
                senha = new String(jpf.getPassword());
            }
        }
        JOptionPane.showMessageDialog(null, "ACESSO NEGADO!",
                "ATENÇÃO", 2);
        saidaSegura.fecharPrograma();
        return false;
    }
}
