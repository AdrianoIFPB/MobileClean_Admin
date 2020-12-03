public class Usuario extends UsuarioPrototype {

    protected Usuario(Usuario usuario){
        this.nome = usuario.getNome();
        this.matricula = usuario.getMatricula();
        this.cargo =  usuario.getCargo();
        this.ultimaLimpeza = usuario.getUltimaLimpeza();
        this.historicoLimpeza = usuario.getHistoricoLimpeza();
        this.perfilLimpeza = usuario.getPerfilLimpeza();
        this.senha = usuario.senha;
        this.houveAlteracao = usuario.getHouveAlteracao();
    }

    public Usuario(){
        nome = "Novo usuario";
        matricula = "Novo usuario";
        cargo = "Novo usuario";
        ultimaLimpeza = "Novo usuario";
        historicoLimpeza = "";
        perfilLimpeza = "Novo usuario";
        senha = "";
        houveAlteracao = false;
    }

    @Override
    public UsuarioPrototype clonar() {
        return new Usuario(this);
    }

    @Override
    public String toString() {
        return "Nome: "+nome.toUpperCase()+"\n"+"Matrícula: "+matricula+"\n"+"Cargo: "+cargo+"\n"+
                "Perfil: "+perfilLimpeza+"\n"+"Status Atual: "+ultimaLimpeza;
    }

}
