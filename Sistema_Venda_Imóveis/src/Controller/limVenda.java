
package Controller;

import Model.Imovel;
import java.util.Vector;
import javax.swing.JOptionPane;

public class limVenda {
    
    public String[] montaForm() {
        String aDadosForm[] = new String[5];
        aDadosForm[0] = JOptionPane.showInputDialog("Digite o codigo do imóvel vendido");
        aDadosForm[1] = JOptionPane.showInputDialog("Digite o valor da venda");
        aDadosForm[2] = JOptionPane.showInputDialog("Digite o nome  do comprador");
        aDadosForm[3] = JOptionPane.showInputDialog("Digite a data(nesse formato dd/mm/yyyy)");
        aDadosForm[4] = JOptionPane.showInputDialog("Digite o nome do corretor responsável");
        return aDadosForm;
    }
    
    public void listaVendas(String lista){
        JOptionPane.showMessageDialog(null, lista);
    }
    
    public void imprimeFaturamento(float vFaturamento){
        JOptionPane.showMessageDialog(null,"O valor do faturamneto é de: R$" + vFaturamento + "\n");
    }
    
        public void imprimeLucro(float vLucro){
        JOptionPane.showMessageDialog(null,"O valor do lucro é de: R$" + vLucro + "\n");
    }
}
