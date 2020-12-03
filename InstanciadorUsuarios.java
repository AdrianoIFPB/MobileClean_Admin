import javax.swing.*;
import java.text.DecimalFormat;
import java.util.HashMap;

public class InstanciadorUsuarios extends GeradorDeAlerta{ //classe que se comunicara com as cadeias importa perfil e importa historico

    private static InstanciadorUsuarios instancia;
    private Usuario prototipoUsuario = new Usuario();
    private HashMap<String, String> mapaPerfil;
    private HashMap<String, String> mapaHistorico;
    private AdapterGerenciadorAcessarDisco adapterGerenciadorAcessarDisco = AdapterGerenciadorAcessarDisco.getInstance();
    public HashMap<String, UsuarioPrototype> usuarios = new HashMap<>();
    private String perfis;
    private String pendentes;
    private double totalUsuarios=0;
    private double totalPendentes;



    private InstanciadorUsuarios(){}

    DecimalFormat df = new DecimalFormat("#.00");

    public static InstanciadorUsuarios getInstancia(){
        if (instancia == null){
            instancia = new InstanciadorUsuarios();
            instancia.mapaPerfil = new HashMap<>();
            instancia.mapaHistorico = new HashMap<>();
            return instancia;
        }
        else return instancia;
    }

    public void montarPerfis(String nome, String matricula, String cargo, String perfilLimpeza, String ultimaLimpeza, String senha, String alerta){
        String perfil = nome+";"+matricula+";"+cargo+";"+perfilLimpeza+";"+ultimaLimpeza+";"+senha+";"+alerta;
        mapaPerfil.put(matricula, perfil);
    }

    public void montarHistoricos(String matricula, String historico){
        mapaHistorico.put(matricula, historico);
    }

    public void instanciaUsuarios(){
        mapaPerfil.forEach((chave,perfil)-> {
            String palavra []=perfil.split(";");
            UsuarioPrototype temporario = prototipoUsuario.clonar();
            temporario.setNome(palavra[0]);//nome
            temporario.setMatricula(palavra[1]);//matricula
            temporario.setCargo(palavra[2]);//cargo
            temporario.setPerfilLimpeza(palavra[3]);//perfil de limpeza
            temporario.setUltimaLimpeza(palavra[4]);//ultima limpeza realizada
            temporario.senha = palavra[5];//senha
            temporario.setAlerta(palavra[6]);//alerta ativo?
            temporario.setHouveAlteracao(false);
            usuarios.put(palavra[1], temporario);

        }) ;
        setHistoricos();
    };

    private void setHistoricos(){
        mapaHistorico.forEach((matricula, historico)->{
            usuarios.get(matricula).setHistoricoLimpeza(historico);
        });
    };



    public boolean removerUsuario(String matricula){

        if (adapterGerenciadorAcessarDisco.removerUsuario(matricula)){
            try {
                String nome = usuarios.get(matricula).getNome();
                usuarios.remove(matricula); mapaPerfil.remove(matricula); mapaHistorico.remove(matricula);
                JOptionPane.showMessageDialog(null, nome+" removido com sucesso!",
                        "--REMOÇÃO DE USUÁRIO--", 3);
                return true;
            }
            catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Usuário ainda não foi instanciado.",
                            "ATENÇÃO", 2);
            }
        }
        return false;
    }

    public boolean salvarUsuarios(){

        try {
            usuarios.forEach((chave, user)->{
                if (user.getHouveAlteracao()){
                    //System.out.println(user.getHouveAlteracao());
                    adapterGerenciadorAcessarDisco.salvarHistorico(user.getMatricula(), user.getHistoricoLimpeza());
                    adapterGerenciadorAcessarDisco.salvarPerfilUsuario(user.getMatricula(), user.salavarPerfil());
                }
            });
            return true;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Alguma alteração pode não ter sido salva!",
                    "ATENÇÃO", 2);
            return false;
        }
    }

    public void salvarUsuario(String matricula){

        try {
            usuarios.forEach((chave, user)->{
                if (user.getMatricula().equals(matricula)){
                    adapterGerenciadorAcessarDisco.salvarHistorico(user.getMatricula(), user.getHistoricoLimpeza());
                    adapterGerenciadorAcessarDisco.salvarPerfilUsuario(user.getMatricula(), user.salavarPerfil());
                }
            });
            JOptionPane.showMessageDialog(null, "Limpeza registrada em disco!",
                    "--REGISTRANDO ALTERAÇÃO--", 3);

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro no registro da limpeza em disco !",
                    "ATENÇÃO", 2);

        }
    }

    public void atualizar(){
        mapaHistorico.clear();
        mapaPerfil.clear();
        usuarios.clear();
        adapterGerenciadorAcessarDisco.importarHistorico();
        adapterGerenciadorAcessarDisco.importarPerfil();
        instanciaUsuarios();
    }

    @Override
    public int limpezaPendente(String matricula) {
        if (usuarios.containsKey(matricula)){
            String ultimaLimpeza = usuarios.get(matricula).getUltimaLimpeza();
            ultimaLimpeza = ultimaLimpeza.substring(21, 32);
            int valorPerfil = valorPerfil(matricula);
            if (valorPerfil == -1){
                return -1;
            }
            return gerenciadorDeDatas.limpezaPendente(valorPerfil , ultimaLimpeza);
        }
        JOptionPane.showMessageDialog(null, "Usuário não encontrado!",
                "ATENÇÃO", 2);
        return -1;
    }

    @Override
    public String estadoLimpeza() {
        pendentes = "";
        totalPendentes=0;
        usuarios.forEach((matricula, usuario)->{
            iterarPendentes(matricula);
        });
        return getPendentes();
    }

    @Override
    protected int valorPerfil(String matricula) {
        if (usuarios.get(matricula).getPerfilLimpeza().equals("Limpeza Diária")) return (3600000*24);
        if (usuarios.get(matricula).getPerfilLimpeza().equals("Limpeza a cada 3 dias")) return (3600000*24*3);
        if (usuarios.get(matricula).getPerfilLimpeza().equals("Limpeza Semanal")) return (3600000*24*7);
        return -1;
    }

    public String verPerfis(){
        perfis="";
        mapaPerfil.forEach((matricula, perfil)->{
            iteraPerfis(matricula);
        });
        return getPerfis();
    }

    private void iteraPerfis(String matricula){
        this.perfis += usuarios.get(matricula).toString()+"\n\n";
    }

    private void iterarPendentes(String matricula){
        int pendente;
        pendente = limpezaPendente(matricula);
        if (pendente > 0){
            this.totalPendentes +=1;
            this.pendentes += "Usuário "+ usuarios.get(matricula).getNome()+
                    " está com a limpeza atrasada em: "+pendente+" dias!\nMatrícula: "+ matricula +
                    "\nPerfil de "+ usuarios.get(matricula).getPerfilLimpeza()+".\n"+
                    usuarios.get(matricula).getUltimaLimpeza()+"\n\n";
        }
    }

    public String getPerfis(){
        return this.perfis;
    }

    public String getPendentes(){
        return this.pendentes;
    }

    public String getRelatorio(){
        totalUsuarios = usuarios.size();
        double resultado = (totalPendentes/totalUsuarios)*100;
        return "Total de Usuários = "+totalUsuarios+"\nTotal de Usuários com Limpeza Pendente = "+ totalPendentes +"\n\n"+
                "Situação Atual: "+df.format(resultado).replace(',','.')+"% estão com higienização fora de prazo!";
    }

    public void alertaTodos(){
        usuarios.forEach((chave, user)->{
            user.setAlerta("sim");
            user.setHouveAlteracao(true);
        });
    }

    public void alertaPendentes(){

        usuarios.forEach((matricula, usuario)->{
            int pendencia = limpezaPendente(matricula);
            if (pendencia > 0) {
                usuario.setAlerta("sim");
                usuario.setHouveAlteracao(true);
            }
        });
    }
}
