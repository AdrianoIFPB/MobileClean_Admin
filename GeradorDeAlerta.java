public abstract class GeradorDeAlerta {

    protected boolean alertaGeral=false;

    protected GerenciadorDeDatas gerenciadorDeDatas = GerenciadorDeDatas.getInstancia();

    protected abstract int limpezaPendente(String matricula);

    protected abstract String estadoLimpeza();

    protected abstract int valorPerfil(String matricula);


}
