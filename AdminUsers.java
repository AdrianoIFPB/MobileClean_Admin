import javax.swing.*;


public class AdminUsers {

    private static AdminUsers instancia;
    private String nome;
    private String cargo;
    private String matricula;
    private String senha;
    private InstanciadorUsuarios instanciadorUsuarios = InstanciadorUsuarios.getInstancia();


    private AdminUsers(){
    }

    public static AdminUsers getInstancia(String senha){
        if (instancia == null){
             instancia = new AdminUsers();
             instancia.setSenha(senha);
             return instancia;
        }
        else return instancia;
    }

    String getNome() {
        return nome;
    }

    void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    String getCargo() {
        return cargo;
    }

    void setCargo(String cargo) {
        this.cargo = cargo.toUpperCase();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    private void setSenha(String senha){
        this.senha = senha;
    }

    String getSenha(){
        return senha;
    }

    String salvarPerfilAdmin(){
        return getNome()+";"+getMatricula()+";"+getCargo()+";"+senha;
    }


    @Override
    public String toString() {
        return "Nome: "+nome.toUpperCase()+"\n"+"Matrícula: "+matricula+"\n"+"Cargo: "+cargo.toUpperCase();
    }

    void getPerfilAdmin(){
        JOptionPane.showMessageDialog(null, toString(),
                "PERFIL DO ADMINISTRADOR", 1);
    }

    void removeUsuario(String matricula){
        instanciadorUsuarios.removerUsuario(matricula);
    }

    public void verTodosPerfis(){
        JOptionPane.showMessageDialog(null, instanciadorUsuarios.verPerfis(),
                "TODOS USUÁRIO", 1);

    }

    void verUmPerfil(String matricula){
        try {
            JOptionPane.showMessageDialog(null, instanciadorUsuarios.usuarios.get(matricula).toString(),
                    "PERFIL DO USUÁRIO", 1);
        }
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.",
                    "ATENÇÃO", 2);
        }
    }

    void limpezaPendente(String matricula){
        int pendencia = instanciadorUsuarios.limpezaPendente(matricula);
        if (pendencia == 0){
            String estado = "Dispositivo de "+instanciadorUsuarios.usuarios.get(matricula).getNome()+" está higienizado!\n"+
                    "Perfil de "+instanciadorUsuarios.usuarios.get(matricula).getPerfilLimpeza()+".\n"+
                    instanciadorUsuarios.usuarios.get(matricula).getUltimaLimpeza();
            JOptionPane.showMessageDialog(null, estado,
                    "SITUAÇÃO DE HIGIENIZAÇÃO", 1);
        }
        else if (pendencia > 0){
            String estado = "Usuário "+instanciadorUsuarios.usuarios.get(matricula).getNome()+
                    " está com a limpeza atrasada em: "+pendencia+" dias!\n"+
                    "Perfil de "+instanciadorUsuarios.usuarios.get(matricula).getPerfilLimpeza()+"\n"+
                    instanciadorUsuarios.usuarios.get(matricula).getUltimaLimpeza();
            JOptionPane.showMessageDialog(null, estado,
                    "SITUAÇÃO DE HIGIENIZAÇÃO", 2);
        }
    }

    public void verStatusTodos(){
        JOptionPane.showMessageDialog(null, instanciadorUsuarios.estadoLimpeza()+"\n"+instanciadorUsuarios.getRelatorio(),
                "SITUAÇÃO DE HIGIENIZAÇÃO", 1);
        int alertar = JOptionPane.showConfirmDialog(null, "Deseja gerar alerta a estes usuários?",
                "ENVIAR ALERTA?", 0, 3);
        if (alertar==0) instanciadorUsuarios.alertaPendentes();
    }

    void verHistorico(String matricula){
        try {
            JOptionPane.showMessageDialog(null, instanciadorUsuarios.usuarios.get(matricula).getNome()+":\n"+
                            instanciadorUsuarios.usuarios.get(matricula).getHistoricoLimpeza(),
                    "HISTÓRICO DE LIMPEZA", 1);
        }
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.",
                    "ATENÇÃO", 2);
        }
    }

    void registrarLimpeza(String matricula, String limpeza){
        try {

            instanciadorUsuarios.usuarios.get(matricula).setHistoricoLimpeza(limpeza);
            instanciadorUsuarios.usuarios.get(matricula).setUltimaLimpeza(limpeza);
            instanciadorUsuarios.usuarios.get(matricula).setAlerta("nao");
            instanciadorUsuarios.usuarios.get(matricula).setHouveAlteracao(true);
            instanciadorUsuarios.salvarUsuario(matricula);
            instanciadorUsuarios.usuarios.get(matricula).setHouveAlteracao(false);

        }
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.",
                    "ATENÇÃO", 2);
        }

    }

    public void atualizar(){
        instanciadorUsuarios.atualizar();
    }

    void salvarTodosUsuarios() {
        instanciadorUsuarios.salvarUsuarios();
    }

    void criarUsuarios(){
        instanciadorUsuarios.instanciaUsuarios();
    }


    void alertarTodos(){
        instanciadorUsuarios.alertaTodos();
    }



}
