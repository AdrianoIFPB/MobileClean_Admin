import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Windows implements Tipo_S_O {

    @Override
    public void criaDiretorioPerfil(){
        try{
            if (!Files.exists(Paths.get("\\Phoneclean\\Perfil\\"))) Files.createDirectory(Paths.get("\\Phoneclean\\Perfil\\"));

        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Não foi possível criar o diretório: " +
                    "\\Phoneclean\\Perfil\\","Atenção!", 2);

        }
    }

    @Override
    public void criaDiretorioHistorico(){
        try{
            if (!Files.exists(Paths.get("\\Phoneclean\\Historico\\"))) Files.createDirectory(Paths.get("\\Phoneclean\\Historico\\"));

        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Não foi possível criar o diretório: " +
                    "\\Phoneclean\\Historico\\","Atenção!", 2);

        }
    }
    @Override
    public void criaDiretorioPerfilAdmin(){
        try{
            if (!Files.exists(Paths.get("\\Phoneclean\\Admin\\"))) Files.createDirectory(Paths.get("\\Phoneclean\\Admin\\"));

        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Não foi possível criar o diretório: " +
                    "\\Phoneclean\\Admin\\","Atenção!", 2);

        }
    }

    @Override
    public String getPathPerfil() {
        return "\\Phoneclean\\Perfil\\";
    }

    @Override
    public String getPathHistorico() {
        return "\\Phoneclean\\Historico\\";
    }

    @Override
    public String getPathPerfilAdmin() {
        return "\\Phoneclean\\Admin\\";
    }

}