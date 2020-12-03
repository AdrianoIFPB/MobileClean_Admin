import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ChainImportarHistoricos extends AcessarDiscoChain {

    public ChainImportarHistoricos() {
        super(OperacaoDisco.importar_Historico);
    }


    @Override
    protected boolean escrever(String matricula, String historico, String perfil, int tipoSO) {
        return false;
    }

    @Override
    protected boolean ler(String matricula, int sisOpe) {
        List<String> linhasHistorico;
        Path caminhoHistorico = bridgeSO.importarHistorico(sisOpe);
        try {
            if (Files.exists(caminhoHistorico)){
                Stream<Path> stream = Files.list(caminhoHistorico);
                Iterator it = stream.iterator();
                while (it.hasNext()){
                    Object element = it.next();
                    String mat = element.toString().substring(22, 26);
                    String historicoUsuario ="";
                    linhasHistorico = Files.readAllLines((Path) element, Charset.defaultCharset());
                    for (String linha: linhasHistorico){
                        String linhaHistorico [] = linha.split(";");
                        historicoUsuario += linhaHistorico[0]+"\n";
                    }

                    instanciadorUsuarios.montarHistoricos(mat, historicoUsuario);
                }
                stream.close();
                return true;
            }
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Sem histórico salvo para importar.",
                    "ATENÇÃO!",2);
        }
        return false;
    }
}
