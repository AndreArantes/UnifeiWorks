package Model;

import java.io.Serializable;
import java.util.Date;

public class Imovel implements Serializable{

    private String tipo; // tipo do imovel ( casa, apartamento, sala comercial, lote, chácara, sítio e fazenda)
    private String codigo; // código do imovel.
    private String descricao; // descrição da casa.
    private String nomeProprietario; // nome do vendedor(proprietário).
    private float valorRequerido; // valor que o proprietário quer no imóvel.
    private Date dataCadastro; // deixar a data livre para digitação.

    public Imovel(String tipo, String codigo, String descricao, String nomeProprietario, float valorRequerido, Date dataCadastro) {
        this.tipo = tipo;
        this.codigo = codigo;
        this.descricao = descricao;
        this.nomeProprietario = nomeProprietario;
        this.valorRequerido = valorRequerido;
        this.dataCadastro = dataCadastro;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public void setValorRequerido(float valorRequerido) {
        this.valorRequerido = valorRequerido;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCodigo() {
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
