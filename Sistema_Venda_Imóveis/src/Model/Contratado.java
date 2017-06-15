
package Model;

import java.io.Serializable;

public class Contratado extends Corretor implements Serializable{
    private float salario; // salario fixo ( implementar opção de alteração ).
    private int dataAdmissao; // data que o corretor foi contratado pela empresa.
    private final float percentualVenda = (float) 0.01; // percentual de cada venda fixa de 1%.

    public Contratado(float salario, int dataAdmissao, String nome, int NumCRECI) {
        super(nome, NumCRECI);
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(int dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
    
}
