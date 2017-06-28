package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Imovel implements Serializable {

    private String tipo; // tipo do imovel ( casa, apartamento, sala comercial, lote, chácara, sítio e fazenda)
    private String codigo; // código do imovel.
    private String descricao; // descrição da casa.
    private String nomeProprietario; // nome do vendedor(proprietário).
    private float valorRequerido; // valor que o proprietário quer no imóvel.
    private Date dataCadastro; // deixar a data livre para digitação.

    public Imovel(String tipo, String codigo, String descricao, String nomeProprietario, float valorRequerido, Date dataCadastro) throws Exception {

        if (validaData(dataCadastro)) {
            this.tipo = tipo;
            this.codigo = codigo;
            this.descricao = descricao;
            this.nomeProprietario = nomeProprietario;
            this.valorRequerido = valorRequerido;
            this.dataCadastro = dataCadastro;
        } else {
            throw new Exception("Data inserida é invalida!");
        }
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

    public boolean validaData(Date pData) {

        GregorianCalendar dt = new GregorianCalendar();
        dt.setTime(pData);

        int day = dt.get(GregorianCalendar.DAY_OF_MONTH);
        int month = 1 + dt.get(GregorianCalendar.MONTH);
        int year = dt.get(GregorianCalendar.YEAR);

        GregorianCalendar dtA = new GregorianCalendar();
        dtA.setTime(new Date());

        int dia = dtA.get(GregorianCalendar.DAY_OF_MONTH);
        int mes = 1 + dtA.get(GregorianCalendar.MONTH);
        int ano = dtA.get(GregorianCalendar.YEAR);

        if (day > dia && month == mes && year == ano) {
            return false;
        } else if (year > ano) {
            return false;
        } else if (month > mes && year == ano) {
            return false;
        } else if (month >= 1 && month <= 12) {
            if (day >= 1 && day <= 31) {
                return true;
            }
        }

        return true;
    }
}
