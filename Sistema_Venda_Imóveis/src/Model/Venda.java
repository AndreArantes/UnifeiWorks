package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class Venda implements Serializable {

    private String codImovel;
    private float valorDaVenda; // valor que foi vendido o imovel.
    private String nomeComprador; // nome do comprador.
    private Date dataVenda; // data que foi vendido o imovel.
    private String corretorResponsavel; // nome do corretor que vendeu o imovel.

    public Venda(String codImovel, float valorDaVenda, String nomeComprador, Date dataVenda, String corretorResponsavel) throws Exception {

        if (validaData(dataVenda)) {
            this.codImovel = codImovel;
            this.valorDaVenda = valorDaVenda;
            this.nomeComprador = nomeComprador;
            this.dataVenda = dataVenda;
            this.corretorResponsavel = corretorResponsavel;
        } else {
            throw new Exception("Data inserida é invalida");
        }
    }

    public String getCodImovel() {
        return codImovel;
    }

    public float getValorDaVenda() {
        return valorDaVenda;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public String getCorretorResponsavel() {
        return corretorResponsavel;
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
