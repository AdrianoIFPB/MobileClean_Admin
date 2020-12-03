import javax.swing.*;


public class SistemaOperacional {
    private int sistemaOpe;
    SaidaSegura saidaSegura = SaidaSegura.getInstance();

    private void tipoSistema(){
        saidaSegura.setSairSO(false);
        while (!saidaSegura.getSairSO()){

            String temp = JOptionPane.showInputDialog(null, "\n1- Windows\n" +
                    "2- Linux\nDIGITE O Nº DA OPÇÃO:","      TIPO DE SISTEMA      ", -1);

            try {
                switch (Integer.parseInt(temp)){
                    case 1:{
                        sistemaOpe = 1;
                        saidaSegura.setSairSO(true);
                        break;
                    }

                    case 2:{
                        sistemaOpe = 2;
                        saidaSegura.setSairSO(true);
                        break;
                    }

                    default:{
                        JOptionPane.showMessageDialog(null, "Digite um número entre 1 e 2",
                                "Número inválido!",0);
                    }
                }
            }
            catch (NumberFormatException e){
                if (temp != null)
                    JOptionPane.showMessageDialog(null, "Digite uma entrada válida.",
                            "Se liga!",0);
                else {
                    saidaSegura.fecharPrograma();
                }
            }

        }
    }

    public int getSistemaOpe() {
        tipoSistema();
        return sistemaOpe;
    }
}
