package Model;

import java.io.Serializable;
import java.util.Date;

public class Imovel implements Serializable{

    private String tipo; // tipo do imovel ( casa, apartamento, sala comercial, lote, chácara, sítio e fazenda)
    private int codigo; // código do imovel.
    private String descricao; // descrição da casa.
    private String nomeProprietario; // nome do vendedor(proprietário).
    private float valorRequerido; // valor que o proprietário quer no imóvel.
    private Date dataCadastro; // deixar a data livre para digitação.

    public Imovel(String tipo, int codigo, String descricao, String nomeProprietario, float valorRequerido, Date dataCadastro) {
        this.tipo = tipo;
        this.codigo = codigo;
        this.descricao = descricao;
        this.nomeProprietario = nomeProprietario;
        this.valorRequerido = valorRequerido;
        this.dataCadastro = dataCadastro;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public float getValorRequerido() {
        return valorRequerido;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    
}
