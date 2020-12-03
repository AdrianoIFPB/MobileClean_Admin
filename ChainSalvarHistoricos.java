import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChainSalvarHistoricos extends AcessarDiscoChain{

    public ChainSalvarHistoricos(){
        super(OperacaoDisco.salvar_Historicos);
    }

    @Override
    protected boolean escrever(String matricula, String historico, String perfil, int tipoSO) {
        bridgeSO.criaDiretorio(tipoSO);
        Path caminhoHistorico = bridgeSO.salvarHistorico(matricula, tipoSO);
        try{
            Files.write(caminhoHistorico, historico.getBytes());
            return true;
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null,"Não foi possível atualizar um histórico!",
                    "ATENÇÃO!",0);

        }
        return false;
    }

    @Override
    protected boolean ler(String matricula, int tipoSo) {
        return false;
    }
}
