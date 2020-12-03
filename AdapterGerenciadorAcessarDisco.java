public class AdapterGerenciadorAcessarDisco {

    private static AdapterGerenciadorAcessarDisco instancia;
    private GerenciadorAcessarDisco gerenciadorAcessarDisco = GerenciadorAcessarDisco.getInstancia();
    private String matricula = null;
    private String historico = "Sem Registro!";
    private String perfil = null;
    private static int SO;
    private SistemaOperacional sistemaOperacional = new SistemaOperacional();

    private AdapterGerenciadorAcessarDisco(){};

    public static AdapterGerenciadorAcessarDisco getInstance(){
        if (instancia == null){
            return instancia = new AdapterGerenciadorAcessarDisco();
        }
        else return instancia;
    }

    public boolean importarPerfil(){
        return gerenciadorAcessarDisco.lerDoDisco(OperacaoDisco.importar_Perfil, matricula, SO);
    }

    public void importarHistorico(){
        gerenciadorAcessarDisco.lerDoDisco(OperacaoDisco.importar_Historico, matricula, SO);
    }

    public boolean importarPerfilAdmin(String mat){
        this.matricula = mat;
        return gerenciadorAcessarDisco.lerDoDisco(OperacaoDisco.importar_Perfil_Admin, matricula, SO);
    }

    public boolean removerUsuario(String mat){
        this.matricula = mat;
        return gerenciadorAcessarDisco.escreverNoDisco(OperacaoDisco.remover_Usuario, matricula, historico, perfil, SO);
    }


    public void salvarPerfilAdmin(String mat, String perf){
        this.matricula = mat;
        this.perfil = perf;
        gerenciadorAcessarDisco.escreverNoDisco(OperacaoDisco.salvar_Perfil_Admin, matricula, historico, perfil, SO);
    }

    public void salvarPerfilUsuario(String mat, String perf){
        this.matricula = mat;
        this.perfil = perf;
        gerenciadorAcessarDisco.escreverNoDisco(OperacaoDisco.salvar_Perfil_Usuario, matricula, historico, perfil, SO);
    }

    public void salvarHistorico(String mat, String hist){
        this.matricula = mat;
        this.historico = hist;
        gerenciadorAcessarDisco.escreverNoDisco(OperacaoDisco.salvar_Historicos, matricula, historico, perfil, SO);
    }
    public void tipoSO(){
        SO = sistemaOperacional.getSistemaOpe();
    }

}
