
package Model;

import java.io.Serializable;
import java.util.Date;

public class Comissionado extends Corretor implements Serializable{
        private float percentualVenda; // comissão do corretor (varia de 1% a 3%).

    public Comissionado(float numPercentual, String nome, int NumCRECI) {
        super(nome, NumCRECI);
        this.percentualVenda = numPercentual;
    }

    public void setPercentualVenda(float percentualVenda) {
        this.percentualVenda = percentualVenda;
    }

    
}
