import javax.swing.*;
import java.util.InputMismatchException;

public class Facade {

    protected GerenciadorDeDatas gerenciadorDeDatas;
    protected GerenciadorLogon gerenciadorLogon;
    protected AdapterGerenciadorAcessarDisco adapterGerenciadorAcessarDisco;
    protected SaidaSegura saidaSegura;
    protected InstrucaoLimpeza instrucaoLimpeza;
    protected AdminUsers adminUsers;

    Facade (){
        gerenciadorDeDatas = GerenciadorDeDatas.getInstancia();
        gerenciadorLogon = new GerenciadorLogon();
        adapterGerenciadorAcessarDisco = AdapterGerenciadorAcessarDisco.getInstance();
        saidaSegura = SaidaSegura.getInstance();
        instrucaoLimpeza = new InstrucaoLimpeza();
    }

    protected void iniciar(){

        adapterGerenciadorAcessarDisco.importarHistorico();
        adapterGerenciadorAcessarDisco.importarPerfil();
        adminUsers.criarUsuarios();

        saidaSegura.setSairMenuPrincipal(false);
        while (!saidaSegura.getSairMenuPrincipal()) {

            String menuPrincipal = JOptionPane.showInputDialog(null, "\n0- Remover Usuário\n" +
                    "1- Ver Perfil e Histórico de Usuário\n2- Alertar Todos Usuários\n3- Registrar Limpeza\n" +
                    "4- Ver Situação de Usuário\n5- Ver Todos os Perfis\n6- Ver Todos os Pendentes\n" +
                    "7- Instruções de Higienização\n8- Dados do Administrador\n9 - Atualizar informações\n" +
                    "10- Sair\n\nDIGITE O Nº DA OPERAÇÃO DESEJADA:", "      MOBILE CLEAN - Admin     ", -1);


            try {
                switch (Integer.parseInt(menuPrincipal)) {

                    case 0: {
                        //código
                         String matriculaDigitada = JOptionPane.showInputDialog(null, "Qual a Matrícula do Usuário:\n" +
                                "(4 dígitos e somente números)", " --REMOVER USUÁRIO--   ", 3);

                         if (gerenciadorLogon.matriculaValida(matriculaDigitada)) {
                            adminUsers.removeUsuario(matriculaDigitada);
                        }
                        break;
                    }

                    case 1: {

                        String matriculaDigitada = JOptionPane.showInputDialog(null, "Qual a Matrícula do Usuário:\n" +
                                "(4 dígitos e somente números)", " --VER PERFIL DE USUÁRIO--   ", 3);
                        if (gerenciadorLogon.matriculaValida(matriculaDigitada)) {
                            adminUsers.verUmPerfil(matriculaDigitada);
                            adminUsers.verHistorico(matriculaDigitada);
                        }
                        break;
                    }

                    case 2: {
                        //código

                        int alerta = JOptionPane.showConfirmDialog(null, "Deseja disparar alerta para todos?",
                                "--ALERTAR TODOS--", 0, 3);
                        if (alerta == 0) adminUsers.alertarTodos();
                        break;
                    }

                    case 3: {

                        String matriculaDigitada = JOptionPane.showInputDialog(null, "Qual a Matrícula do Usuário:\n" +
                                "(4 dígitos e somente números)", " --REGISTRANDO LIMPEZA--   ", 3);
                        if (gerenciadorLogon.matriculaValida(matriculaDigitada)) {
                            int limp = JOptionPane.showConfirmDialog(null, "Registrar Limpeza de Dispositivo?",
                                    "--REGISTRANDO LIMPEZA!--", 0);
                            if (limp == 0) {
                                String temp = "Limpeza efetuada em: " + gerenciadorDeDatas.getData();
                                adminUsers.registrarLimpeza(matriculaDigitada, temp);
                            }
                        }
                        break;
                    }

                    case 4: {

                        String matriculaDigitada = JOptionPane.showInputDialog(null, "Qual a Matrícula do Usuário:\n" +
                                "(4 dígitos e somente números)", " -- HÁ LIMPEZA PENDENTE? --   ", 3);
                        if (gerenciadorLogon.matriculaValida(matriculaDigitada)) {
                            adminUsers.limpezaPendente(matriculaDigitada);
                        }
                        break;
                    }

                    case 5: {
                        adminUsers.verTodosPerfis();
                        break;
                    }

                    case 6: {
                        adminUsers.verStatusTodos();
                        break;
                    }

                    case 7: {

                        instrucaoLimpeza.getInstrucoes();
                        break;
                    }

                    case 8: {

                        adminUsers.getPerfilAdmin();
                        break;
                    }

                    case 9: {//atualizar
                        adminUsers.atualizar();
                        break;
                    }

                    case 10: {

                        adminUsers.salvarTodosUsuarios();
                        saidaSegura.setSairMenuPrincipal(true);

                        break;
                    }

                    default: {
                        JOptionPane.showMessageDialog(null, "Digite um número entre 0 e 10",
                                "Número inválido!", 0);
                        break;

                    }
                }

            }
            catch (InputMismatchException | NumberFormatException | NullPointerException e) {
                    if (menuPrincipal != null)
                        JOptionPane.showMessageDialog(null, "Digite uma entrada válida.",
                                "ATENÇÃO!",0);
                    else {
                        saidaSegura.fecharPrograma();
                    }
            }


        }

    }
}
