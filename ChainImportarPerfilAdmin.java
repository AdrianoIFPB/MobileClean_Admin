import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ChainImportarPerfilAdmin extends AcessarDiscoChain {

    private AdminUsers adminUsers;

    public ChainImportarPerfilAdmin(){
        super(OperacaoDisco.importar_Perfil_Admin);
    }

    @Override
    protected boolean escrever(String matricula, String historico, String perfil, int sisOpe) {
        return false;
    }

    @Override
    protected boolean ler(String matricula, int sisOpe) {

        List<String> linha;
        Path caminhoPerfil = bridgeSO.importarPerfilAdmin(matricula, sisOpe);

        try {
            linha = Files.readAllLines(caminhoPerfil, Charset.defaultCharset());
            String palavra []=linha.get(0).split(";");
            adminUsers = AdminUsers.getInstancia(palavra[3]);
            adminUsers.setNome(palavra[0]);
            adminUsers.setMatricula(palavra[1]);
            adminUsers.setCargo(palavra[2]);
            return true;
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Dados do Administrador não encontrados!",
                    "Atenção!",2);
        }
        return false;
    }
}
