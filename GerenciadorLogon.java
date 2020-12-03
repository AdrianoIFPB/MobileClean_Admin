public class GerenciadorLogon {

    private ValidaLogin validaLogin;
    private ValidaEntrada validaEntrada;
    private ValidaSenha validaSenha;

    public GerenciadorLogon(){
        validaLogin = new ValidaLogin();
        validaEntrada = new ValidaEntrada();
        validaSenha = new ValidaSenha();
    }

    public boolean matriculaValida(String matricula){
        return validaEntrada.verificaEntradaDigitada(matricula);
    }

    public String loginValido(){
        return validaLogin.login();
    }

    public boolean confereSenha(String senhaSalva, String senhaDigitada){return validaSenha.verificaSenha(senhaSalva, senhaDigitada);}

    public String registraSenha(){
        return validaSenha.registraSenha();
    }
}
