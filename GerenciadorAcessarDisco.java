import javax.swing.*;

public class GerenciadorAcessarDisco {

    private static GerenciadorAcessarDisco instancia;
    private AcessarDiscoChain operacoes;

    private GerenciadorAcessarDisco(){}

    static GerenciadorAcessarDisco getInstancia(){
        if (instancia == null){
            instancia = new GerenciadorAcessarDisco();
            instancia.formarCadeia();
            return instancia;
        }
        else return instancia;
    }

    private void formarCadeia(){
        operacoes = new ChainImportarHistoricos();
        operacoes.setNext(new ChainImpotarPerfil());
        operacoes.setNext(new ChainImportarPerfilAdmin());
        operacoes.setNext(new ChainRemoverUsuario());
        operacoes.setNext(new ChainSalvarHistoricos());
        operacoes.setNext(new ChainSalvarPerfilAdmin());
        operacoes.setNext(new ChainSalvarPerfilUsuario());
    }

    public boolean escreverNoDisco(OperacaoDisco od, String matricula, String historico, String perfil, int sisOpe){
        try {
            return operacoes.escreverNoDisco(od, matricula, historico, perfil, sisOpe);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível escrever no disco!",
                    "ATENÇÃO!",2);
        }
        return false;
    }

    public boolean lerDoDisco(OperacaoDisco od, String matricula, int tipoSO){
        try {
            return operacoes.lerDoDisco(od, matricula, tipoSO);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível ler do disco!",
                    "ATENÇÃO!",2);
        }
        return false;
    }
}
