
package Model;

import java.io.Serializable;

public class Comissionado extends Corretor implements Serializable{
        private float numPercentual; // comissão do corretor (varia de 1% a 3%).

    public Comissionado(float numPercentual, String nome, int NumCRECI) {
        super(nome, NumCRECI);
        this.numPercentual = numPercentual;
    }
        
}
