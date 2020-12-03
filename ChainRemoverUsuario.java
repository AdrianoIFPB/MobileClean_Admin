import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChainRemoverUsuario extends AcessarDiscoChain {


    public ChainRemoverUsuario(){
        super(OperacaoDisco.remover_Usuario);
    }

    @Override
    protected boolean escrever(String matricula, String historico, String perfil, int tipoSO) {
        Path caminhoPerfil = bridgeSO.removerPerfilUsuario(matricula, tipoSO);
        Path caminhoHistorico = bridgeSO.removerHistoricoUsuario(matricula, tipoSO);
        try {

            Files.delete(caminhoPerfil);
            Files.delete(caminhoHistorico);
            return true;
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "ERRO: USUÁRIO NÃO ENCONTRADO NA BASE DE DADOS!",
                    "ATENÇÃO!", 0);
        }
        return false;
    }

    @Override
    protected boolean ler(String matricula, int tipoSo) {
        return false;
    }
}
