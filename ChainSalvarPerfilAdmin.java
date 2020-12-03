import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChainSalvarPerfilAdmin extends AcessarDiscoChain {

    public ChainSalvarPerfilAdmin(){ super(OperacaoDisco.salvar_Perfil_Admin); }

    @Override
    protected boolean escrever(String matricula, String historico, String perfil, int sisOpe) {
        bridgeSO.criaDiretorio(sisOpe);
        try {
            Path caminhoAdmin = bridgeSO.salvarPerfilAdmin(matricula, sisOpe);
            Files.write(caminhoAdmin, perfil.getBytes());
            JOptionPane.showMessageDialog(null,"Dados de perfil salvos!",
                    "Gravando seus dados!", 1);
            return true;
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,"Não foi possível salvar o perfil.",
                    "ERRO!",0);
        }

        return false;
    }

    @Override
    protected boolean ler(String matricula, int tipoSo) {
        return false;
    }
}
