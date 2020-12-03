import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ChainImpotarPerfil extends AcessarDiscoChain {

    public ChainImpotarPerfil() {
        super(OperacaoDisco.importar_Perfil);
    }


    @Override
    protected boolean escrever(String matricula, String historico, String perfil, int tipoSO) {
        return false;
    }

    @Override
    protected boolean ler(String matricula, int sisOpe) {
        List<String> linha;
        Path caminhoPerfil = bridgeSO.importarPerfil(sisOpe);
        try {
            if (Files.exists(caminhoPerfil)) {
                Stream<Path> stream = Files.list(caminhoPerfil);
                Iterator it = stream.iterator();
                while (it.hasNext()) {
                    Object element = it.next();
                    linha = Files.readAllLines((Path) element, Charset.defaultCharset());
                    String palavra []=linha.get(0).split(";");
                    instanciadorUsuarios.montarPerfis(palavra[0], palavra[1], palavra[2], palavra[3], palavra[4], palavra[5], palavra[6]);
                }
                stream.close();
            }
            return true;
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Não foi possível importar o perfil.",
                    "Atenção!",2);
        }
        return false;
    }
}
