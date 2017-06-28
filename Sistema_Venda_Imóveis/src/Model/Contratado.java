package Model;

import java.util.Date;
import java.util.GregorianCalendar;

public class Contratado extends Corretor {

    private float salarioFixo; // salarioFixo fixo ( implementar opção de alteração ).
    private Date dataAdmissao; // data que o corretor foi contratado pela empresa.
    private final float percentualVenda = (float) 0.01; // percentual de cada venda fixa de 1%.

    public Contratado(String nome, String NumCRECI, float salarioFixo, Date dataAdmissao) throws Exception {
        
        super(nome, NumCRECI);
        
        if (validaData(dataAdmissao)) {
            this.salarioFixo = salarioFixo;
            this.dataAdmissao = dataAdmissao;
        } else {
            throw new Exception("Data inserida é invalida!");
        }
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

    public boolean validaData(Date pData) {

        GregorianCalendar dt = new GregorianCalendar();
        dt.setTime(pData);

        int day = dt.get(GregorianCalendar.DAY_OF_MONTH);
        int month = 1 + dt.get(GregorianCalendar.MONTH);
        int year = dt.get(GregorianCalendar.YEAR);

        GregorianCalendar dtA = new GregorianCalendar();
        dtA.setTime(new Date());

        int dia = dtA.get(GregorianCalendar.DAY_OF_MONTH);
        int mes =  1 + dtA.get(GregorianCalendar.MONTH);
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
