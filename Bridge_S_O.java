import java.nio.file.Path;
import java.nio.file.Paths;

public class Bridge_S_O {
    private Tipo_S_O linux = new Linux();
    private Tipo_S_O windows = new Windows();

    //OPERAÇÕES PARA ADMINISTRADOR
    public Path salvarPerfilAdmin(String matricula, int sisOpe){
        if (sisOpe==1) return Paths.get(windows.getPathPerfilAdmin()+matricula+".txt");
        else return Paths.get(linux.getPathPerfilAdmin()+matricula+".txt");
    }

    public Path importarPerfilAdmin(String matricula, int sisOpe){
        if (sisOpe==1) return Paths.get(windows.getPathPerfilAdmin()+matricula+".txt");
        else return Paths.get(linux.getPathPerfilAdmin()+matricula+".txt");
    }

    //OPERAÇÕES PARA USUÁRIOS

    public Path salvarHistorico(String matricula, int sisOpe){
        if (sisOpe==1) return Paths.get(windows.getPathHistorico()+matricula+"-historico.txt");
        else return Paths.get(linux.getPathHistorico()+matricula+"-historico.txt");
    }

    public Path salvarPerfil(String matricula, int sisOpe){
        if (sisOpe==1)return Paths.get(windows.getPathPerfil()+matricula+".txt");
        else return Paths.get(linux.getPathPerfil()+matricula+".txt");
    }

    public Path importarHistorico(int sisOpe){
        if (sisOpe==1)return Paths.get(windows.getPathHistorico());
        else return Paths.get(linux.getPathHistorico());
    }

    public Path importarPerfil(int sisOpe){
        if (sisOpe==1)return Paths.get(windows.getPathPerfil());
        else return Paths.get(linux.getPathPerfil());
    }

    public Path removerPerfilUsuario(String matricula, int sisOpe){
        if (sisOpe==1) return Paths.get(windows.getPathPerfil()+matricula+".txt");
        else return Paths.get(linux.getPathPerfilAdmin()+matricula+".txt");
    }

    public Path removerHistoricoUsuario(String matricula, int sisOpe){
        if (sisOpe==1) return Paths.get(windows.getPathHistorico()+matricula+"-historico.txt");
        else return Paths.get(linux.getPathHistorico()+matricula+"-historico.txt");
    }

    //OPERAÇÕES COMUNS

    public void criaDiretorio(int sisOpe){
        if (sisOpe==1) {
            windows.criaDiretorioPerfil();
            windows.criaDiretorioHistorico();
            windows.criaDiretorioPerfilAdmin();
        }
        else {
            linux.criaDiretorioPerfil();
            linux.criaDiretorioHistorico();
            linux.criaDiretorioPerfilAdmin();
        }
    }
}
