import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChainSalvarPerfilUsuario extends AcessarDiscoChain {

    ChainSalvarPerfilUsuario(){
        super(OperacaoDisco.salvar_Perfil_Usuario);
    }

    @Override
    protected boolean escrever(String matricula, String historico, String perfil, int tipoSO) {
        bridgeSO.criaDiretorio(tipoSO);
        try {
            Path caminhoUsuario = bridgeSO.salvarPerfil(matricula, tipoSO);
            Files.write(caminhoUsuario, perfil.getBytes());
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
