
package Model;

import java.io.Serializable;
import java.util.Date;

public class Comissionado extends Corretor implements Serializable{
        private float percentualVenda; // comissão do corretor (varia de 1% a 3%).

    public Comissionado(String nome, int NumCRECI, float numPercentual) {
        super(nome, NumCRECI);
        this.percentualVenda = numPercentual;
    }

    public float getPercentualVenda() {
        return percentualVenda;
    }
  
}
