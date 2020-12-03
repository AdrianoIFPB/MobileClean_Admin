public abstract class AcessarDiscoChain {

    protected AcessarDiscoChain next;
    protected OperacaoDisco operacaoDisco;
    protected Bridge_S_O bridgeSO = new Bridge_S_O();
    protected InstanciadorUsuarios instanciadorUsuarios = InstanciadorUsuarios.getInstancia();


    public AcessarDiscoChain(OperacaoDisco od){
        next = null;
        operacaoDisco = od;
    }

    public void setNext(AcessarDiscoChain proxOperacao){
        if (next==null){
            next = proxOperacao;
        }
        else {
            next.setNext(proxOperacao);
        }
    }

    public boolean escreverNoDisco(OperacaoDisco od, String matricula, String historico, String perfil, int tipoSO) throws Exception{
        if (podeEfetuarOperacao(od)){

            return escrever(matricula, historico, perfil,tipoSO);
        }
        else {
            if (next == null){
                throw new Exception("Operação não cadastrada!");
            }
            return next.escreverNoDisco(od, matricula, historico, perfil,tipoSO);
        }
        //return false;
    }

    public boolean lerDoDisco(OperacaoDisco od, String matricula, int tipoSO) throws Exception{
        if (podeEfetuarOperacao(od)){
            return ler(matricula, tipoSO);
        }
        else {
            if (next == null){
                throw new Exception("Operação não cadastrada!");
            }
            return next.lerDoDisco(od, matricula, tipoSO);
        }
    }

    private boolean podeEfetuarOperacao(OperacaoDisco od){
        if (operacaoDisco == od){
            return true;
        }
        return false;
    }

    protected abstract boolean escrever( String matricula, String historico, String perfil, int tipoSO);
    protected abstract boolean ler(String matricula, int tipoSo);
}
