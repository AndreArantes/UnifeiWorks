
package Controller;

import Model.Corretor;
import javax.swing.JOptionPane;

public class limCorretor {
    
    public String[] montaFormContratado() {
        
        String aDadosForm[] = new String[4];
        aDadosForm[0] = JOptionPane.showInputDialog("Digite o nome do corretor");
        aDadosForm[1] = JOptionPane.showInputDialog("Digite o numero do CRECI");
        aDadosForm[2] = JOptionPane.showInputDialog("Digite o salario do corretor");
        aDadosForm[3] = JOptionPane.showInputDialog("Digite a data de admissao(nesse formato dd/mm/yyyy)");
        
    return aDadosForm;
    }
    
    public String[] montaFormComissionado() {
        
        String aDadosForm[] = new String[3];
        aDadosForm[0] = JOptionPane.showInputDialog("Digite o nome do corretor");
        aDadosForm[1] = JOptionPane.showInputDialog("Digite o numero do CRECI");
        aDadosForm[2] = JOptionPane.showInputDialog("Digite a comissao do corretor");
        
    return aDadosForm;
    }
    
    public void imprimeSalario(Corretor pCorretor, float pSalario){
        JOptionPane.showMessageDialog(null, "O corretor " + pCorretor.getNome() + " recebeu R$" + pSalario +"\n");
    }
}
