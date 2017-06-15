package Model;

import java.io.Serializable;

public class Imovel implements Serializable{

    private String tipo; // tipo do imovel ( casa, apartamento, sala comercial, lote, chácara, sítio e fazenda)
    private int codigo; // código do imovel.
    private String descricao; // descrição da casa.
    private String nomeProprietario; // nome do vendedor(proprietário).
    private float valorRequerido; // valor que o proprietário quer na casa.
    private int dataCadastro; // deixar a data livre para digitação.
    
  

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public float getValorRequerido() {
        return valorRequerido;
    }

    public void setValorRequerido(float valorRequerido) {
        this.valorRequerido = valorRequerido;
    }

    public int getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(int dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
