
package Model;

import java.io.Serializable;
import java.util.Date;

public class Contratado extends Corretor implements Serializable{
    private float salarioFixo; // salarioFixo fixo ( implementar opção de alteração ).
    private Date dataAdmissao; // data que o corretor foi contratado pela empresa.
    private final float percentualVenda = (float) 0.01; // percentual de cada venda fixa de 1%.

    public Contratado(String nome, int NumCRECI , float salarioFixo, Date dataAdmissao) {
        super(nome, NumCRECI);
        this.salarioFixo = salarioFixo;
        this.dataAdmissao = dataAdmissao;
    }

    public void setSalarioFixo(float salarioFixo) {
        this.salarioFixo = salarioFixo;
    }

    public float getSalarioFixo() {
        return salarioFixo;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public float getPercentualVenda() {
        return percentualVenda;
    }

}
