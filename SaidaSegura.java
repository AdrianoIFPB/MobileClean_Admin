public class SaidaSegura {
    static SaidaSegura instancia;

    private boolean sairMenuPrincipal = false;
    private boolean sairLogin = false;
    private boolean sairSO = false;
    private boolean fecharPrograma = false;
    private boolean sairSenha = false;


    private SaidaSegura() {
    }

    public static SaidaSegura getInstance() {
        if (instancia == null) return instancia = new SaidaSegura();
        else return instancia;
    }

    public void fecharPrograma() {
        sairMenuPrincipal = true;
        sairLogin = true;
        sairSO = true;
        sairSenha = true;
        fecharPrograma = true;
    }

    public boolean getSairMenuPrincipal() {
        return sairMenuPrincipal;
    }

    public void setSairMenuPrincipal(boolean estado) {
        sairMenuPrincipal = estado;
    }

    public boolean getSairLogin() {
        return sairLogin;
    }

    public void setSairLogin(boolean estado) {
        sairLogin = estado;
    }

    public boolean getSairSO() {
        return sairSO;
    }

    public void setSairSO(boolean estado) {
        sairSO = estado;
    }

    public boolean getFecharPrograma() {        return fecharPrograma;     }

    public boolean getSairSenha() {         return sairSenha;     }

    public void setSairSenha(boolean estado) {         sairSenha = estado;     }

}