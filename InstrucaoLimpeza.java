import javax.swing.*;

class InstrucaoLimpeza {
    private String instrucoes = "Desconecte todas as fontes de alimentação externas, dispositivos e cabos;\n" +
            "Use álcool isopropílico ou álcool 70%;\n" +
            "O álcool em gel não é indicado;\n" +
            "Embebede um lenço de papel no álcool;\n" +
            "Tire a capinha e faça a limpeza tanto na parte de fora como por dentro dela;\n"+
            "Passe o lenço com álcool em todo o celular;\n" +
            "Use somente panos macios que não soltem fiapos. Evite usar toalhas, lenços abrasivos, toalhas de papel e itens parecidos;\n" +
            "Não deixe entrar umidade nas aberturas;\n" +
            "Não use sprays aerossol, água sanitária ou abrasivos; \n" +
            "Não borrife produtos de limpeza diretamente no dispositivo.";

    void getInstrucoes(){
        JOptionPane.showMessageDialog(null, instrucoes,"INSTRUÇÕES DE LIMPEZA",1);
    }
}
