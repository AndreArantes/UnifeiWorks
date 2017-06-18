package Controller;

import Model.Comissionado;
import Model.Contratado;
import Model.Corretor;
import Model.Venda;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class ctrCorretor {

    private Corretor objAEntCorretor;
    private limCorretor objALimCorretor;
    private ctrPrincipal objCtrPrincipal;
    private Vector listaVendas = objCtrPrincipal.getObjACtrVenda().getListaVendas();
    private Vector vecCorretor = new Vector();
    private String[] aDadosForm;

    public ctrCorretor() throws Exception {
        //objCtrPrincipal = pCtrPrincipal;
        objALimCorretor = new limCorretor();
        //desserializaCorretor();
    }

    public boolean cadCorretor(int tipoCorretor) throws ParseException {

        if (tipoCorretor == 1) {
            cadastra1();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date data = formato.parse(aDadosForm[3]);
            objAEntCorretor = new Contratado(aDadosForm[0], Integer.parseInt(aDadosForm[1]), Float.parseFloat(aDadosForm[2]), data);
        } else if (tipoCorretor == 2) {
            cadastra2();
            objAEntCorretor = new Comissionado(aDadosForm[0], Integer.parseInt(aDadosForm[1]), Float.parseFloat(aDadosForm[2]));
        }

        vecCorretor.add(objAEntCorretor);
        return true;
    }

    private void cadastra1() {
        aDadosForm = objALimCorretor.montaFormContratado();
    }

    private void cadastra2() {
        aDadosForm = objALimCorretor.montaFormComissionado();
    }

    public Vector getListaCorretor() {
        return vecCorretor;
    }

    public float calculaSalario(Corretor objCorretor, String pMes) {
        float salario = 0;

        if (objCorretor instanceof Contratado) {

            for (int idx = 0; idx < listaVendas.size(); idx++) {
                Venda objVenda = (Venda) listaVendas.get(idx);

                if (objCorretor.getNome().equalsIgnoreCase(objVenda.getCorretorResponsavel())) {
                    salario += (float) objVenda.getValorDaVenda() * ((Contratado) objCorretor).getPercentualVenda();
                }
            }

            salario += ((Contratado) objCorretor).getSalarioFixo();
        } else if (objCorretor instanceof Comissionado) {

            for (int idx = 0; idx < listaVendas.size(); idx++) {
                Venda objVenda = (Venda) listaVendas.get(idx);

                SimpleDateFormat sdf = new SimpleDateFormat("MM");
                String mes = sdf.format(objVenda.getDataVenda());

                if (objCorretor.getNome().equalsIgnoreCase(objVenda.getCorretorResponsavel()) && mes.equalsIgnoreCase(pMes)) {
                    salario += (float) objVenda.getValorDaVenda() * ((Comissionado) objCorretor).getPercentualVenda();
                }
            }
        }

        //objALimCorretor.imprimeSalario(objCorretor, salario);
        return salario;
    }

    public Corretor corretorDoMes(String pMes) {

        Corretor objCorretorDoMes = (Corretor) vecCorretor.get(0);
        float comissao = 0;
        float testa_comissao = 0;

        if (objCorretorDoMes instanceof Contratado) {
            comissao = calculaSalario(objCorretorDoMes, pMes) - ((Contratado) objCorretorDoMes).getSalarioFixo();
        } else {
            comissao = calculaSalario(objCorretorDoMes, pMes);
        }

        for (int idx = 1; idx < vecCorretor.size(); idx++) {
            Corretor objCorretor = (Corretor) vecCorretor.get(idx);

            if (objCorretorDoMes instanceof Contratado) {
                testa_comissao = calculaSalario(objCorretor, pMes) - ((Contratado) objCorretor).getSalarioFixo();

                if (testa_comissao > comissao) {

                    objCorretorDoMes = objCorretor;
                    comissao = testa_comissao;

                }
            } else {
                testa_comissao = calculaSalario(objCorretor, pMes);

                if (testa_comissao > comissao) {

                    objCorretorDoMes = objCorretor;
                    comissao = testa_comissao;

                }
            }

        }
        return objCorretorDoMes;
    }

    public String salarioCorretor(String pMes) {
        String result = " ";

        for (int idx = 0; idx < vecCorretor.size(); idx++) {
            Corretor objCorretor = (Corretor) vecCorretor.get(idx);
            result += "Nome: " + objCorretor.getNome()
                    + " || CRECI: " + objCorretor.getNumCRECI()
                    + " || Salario: R$" + calculaSalario(objCorretor, pMes)
                    + "\n";
        }

        return result;
    }

    public String faturamentoCorretor(String pMes) {
        String result = " ";

        for (int idx = 0; idx < vecCorretor.size(); idx++) {
            Corretor objCorretor = (Corretor) vecCorretor.get(idx);
            result += "Nome: " + objCorretor.getNome()
                    + " || CRECI: " + objCorretor.getNumCRECI()
                    + " || Faturamento trazido: R$" + objCtrPrincipal.getObjACtrVenda().calculaFaturamentoCorretor(objCorretor, pMes)
                    + "\n";
        }

        if (result.equalsIgnoreCase("")) {
            
            return "Error! Não existem Corretores cadastrados.";
            
        } else {
            
            return result;
            
        }
    }

}
