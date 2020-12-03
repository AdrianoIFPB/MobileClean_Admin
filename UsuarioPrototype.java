public abstract class  UsuarioPrototype {

    protected boolean houveAlteracao = false;
    protected String nome;
    protected String matricula;
    protected String cargo;
    protected String perfilLimpeza;
    protected String historicoLimpeza;
    protected String ultimaLimpeza;
    protected String senha;
    private String alerta;

    protected String getNome() {
        return nome;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }

    protected String getMatricula() {
        return matricula;
    }

    protected void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    protected String getCargo() {
        return cargo;
    }

    protected void setCargo(String cargo) {
        this.cargo = cargo;
    }

    protected String getPerfilLimpeza() {
        return perfilLimpeza;
    }

    protected void setPerfilLimpeza(String perfilLimpeza) {
        this.perfilLimpeza = perfilLimpeza;
    }

    protected String getHistoricoLimpeza() {
        return historicoLimpeza;
    }

    protected void setHistoricoLimpeza(String historicoLimpeza) {
        if (!ultimaLimpeza.equals(historicoLimpeza)){
            this.historicoLimpeza += historicoLimpeza;
        }
    }

    protected String getUltimaLimpeza() {
        return ultimaLimpeza;
    }

    protected void setUltimaLimpeza(String ultimaLimpeza) {
        this.ultimaLimpeza = ultimaLimpeza;
    }

    protected void setHouveAlteracao(boolean houveAlteracao) {
        this.houveAlteracao = houveAlteracao;
    }

    protected boolean getHouveAlteracao(){
        return houveAlteracao;
    }

    protected String salavarPerfil(){
        return getNome()+";"+getMatricula()+";"+getCargo()+";"+getPerfilLimpeza()+";"+getUltimaLimpeza()+";"+senha+";"+getAlerta();
    }

    protected void setAlerta(String alerta){
        this.alerta = alerta;
    }

    protected String getAlerta(){
        return alerta;
    }

    protected abstract UsuarioPrototype clonar();
}
