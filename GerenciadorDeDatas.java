import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class GerenciadorDeDatas {
    private static GerenciadorDeDatas instancia;
    private LocalDateTime agora = LocalDateTime.now();
    private String data;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");

    private GerenciadorDeDatas(){}

    static GerenciadorDeDatas getInstancia(){
        if (instancia==null){
            return instancia = new GerenciadorDeDatas();
        }
        else return instancia;
    }

    public String getData(){

        DateTimeFormatter hoje = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        return data = hoje.format(agora);
    }

    private long transformaData(String ultimaLimpeza){
        try {
            return dateFormat.parse(ultimaLimpeza).getTime();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível transformar a data informada!",
                    "ATENÇÃO", 2);
        }
        return -1;
    }

    public int limpezaPendente(int perfil, String ultimaLimpeza){

        long diferenca = transformaData(ultimaLimpeza) + perfil - transformaData(getData());

        if (diferenca<0){
            return  ((int)(Math.abs(diferenca)/86400000));
        }
        return 0;
    }
}
