
package Controller;

import javax.swing.JOptionPane;

public class limImovel {
        
    public String[] montaForm() {
        String aDadosForm[] = new String[6];
        
            aDadosForm[0] = JOptionPane.showInputDialog("Digite o tipo do imóvel");
            aDadosForm[1] = JOptionPane.showInputDialog("Digite o codigo do imóvel");
            aDadosForm[2] = JOptionPane.showInputDialog("Dê uma descrição do imóvel");
            aDadosForm[3] = JOptionPane.showInputDialog("Digite nome do propietário");
            aDadosForm[4] = JOptionPane.showInputDialog("Digite o valor do imóvel");
            aDadosForm[5] = JOptionPane.showInputDialog("Digite a data");
            
        
        return aDadosForm;
    }
}
