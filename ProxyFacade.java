import javax.swing.*;

public class ProxyFacade extends Facade{

    private boolean retorno = false;
    private int cont = 0;

    private boolean temPermissaoDeAcesso(){

        saidaSegura.setSairLogin(false);

        while (!saidaSegura.getSairLogin()){
            int novo = JOptionPane.showConfirmDialog(null, "Você é um novo Administrador?",
                    "PRIMEIRO ACESSO?", 0, 3);
            switch (novo) {
                case 0: {
                    String matriculaDigitada;
                    String nome;
                    String senha;
                    String cargo;
                    adapterGerenciadorAcessarDisco.tipoSO();
                    if (saidaSegura.getFecharPrograma()) return false;

                    matriculaDigitada = JOptionPane.showInputDialog(null, "Informe sua matrícula:\n" +
                            "(4 dígitos e somente números)", " --MATRÍCULA DO ADMIN--   ", 3);
                    if (gerenciadorLogon.matriculaValida(matriculaDigitada)) {
                        nome = JOptionPane.showInputDialog(null, "Informe seu Nome:",
                                " --NOME DO ADMIN--   ", 3);
                        cargo = JOptionPane.showInputDialog(null, "Informe seu cargo:",
                                " --CARGO--   ", 3);
                        senha = gerenciadorLogon.registraSenha();
                        if (senha == null) return false;
                        adminUsers = AdminUsers.getInstancia(senha);
                        adminUsers.setMatricula(matriculaDigitada);
                        adminUsers.setNome(nome);
                        adminUsers.setCargo(cargo);
                        adapterGerenciadorAcessarDisco.salvarPerfilAdmin(adminUsers.getMatricula(), adminUsers.salvarPerfilAdmin());
                        saidaSegura.setSairLogin(true);
                        retorno = true;
                    }
                    break;
                }
                case 1: {
                    adapterGerenciadorAcessarDisco.tipoSO();
                    if (saidaSegura.getFecharPrograma()) return false;
                    String matricula = gerenciadorLogon.loginValido();
                    if (saidaSegura.getSairLogin()) return false;
                    if (matricula != null) {
                        String senhaDigitada = gerenciadorLogon.registraSenha();
                        if (senhaDigitada == null) return false;
                        if (adapterGerenciadorAcessarDisco.importarPerfilAdmin(matricula)){
                            adminUsers = AdminUsers.getInstancia("");
                            String senhaSalva = adminUsers.getSenha();
                            if (!gerenciadorLogon.confereSenha( senhaSalva, senhaDigitada)){
                                return false;
                            }
                        }
                        else return false;
                        JOptionPane.showMessageDialog(null, "Bem vindo, "+adminUsers.getNome()+"!",
                                "ADMINISTRADOR DO SISTEMA", 1);
                        saidaSegura.setSairLogin(true);
                        retorno = true;
                        break;
                    }
                    break;
                }
                default: {
                    saidaSegura.fecharPrograma();
                    return false;
                }
            }
            if (cont==1 && !retorno) JOptionPane.showMessageDialog(null, "Você tem apenas mais 1(uma) tentativa!",
                    "ATENÇÃO", 2);
            cont++;
            if (cont == 3 && !retorno) return false;
        }
        return retorno;
    }

    @Override
    public void iniciar(){
        if (temPermissaoDeAcesso()){
            super.iniciar();
        }
    }

}
