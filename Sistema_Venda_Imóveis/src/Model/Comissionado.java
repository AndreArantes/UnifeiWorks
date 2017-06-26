
package Model;

public class Comissionado extends Corretor {
        private float percentualVenda; // comissão do corretor (varia de 1% a 3%).

    public Comissionado(String nome, String NumCRECI, float numPercentual) {
        super(nome, NumCRECI);
        this.percentualVenda = numPercentual;
    }

    public float getPercentualVenda() {
        return percentualVenda;
    }
  
}
