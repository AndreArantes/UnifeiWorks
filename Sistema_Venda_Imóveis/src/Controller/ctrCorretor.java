package Controller;

import Model.Comissionado;
import Model.Contratado;
import Model.Corretor;
import Model.Venda;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

public class ctrCorretor {

    private ctrPrincipal objCtrPrincipal;
    private Vector<Venda> listaVendas;
    private Vector corretores = new Vector();

    public ctrCorretor(ctrPrincipal pCtr) throws Exception {

        objCtrPrincipal = pCtr;
        listaVendas = objCtrPrincipal.objACtrVenda.getListaVendas();
        desserializaCorretor();

    }

    public void cadCorretorCT(String nome, String CRECI, float salariofixo, String pData) throws Exception {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse(pData);

        if (corretores.add(new Contratado(nome, CRECI, salariofixo, data))) {
            serializaCorretor();
        } else {
            throw new Exception("Error ao cadastrar corretor contratado!");
        }

    }

    public void cadCorretorCM(String nome, String CRECI, float comissao) throws Exception {

        if (corretores.add(new Comissionado(nome, CRECI, comissao))) {
            serializaCorretor();
        } else {
            throw new Exception("Error ao cadastrar corretor comissionado!");
        }
    }

    public Vector getListaCorretor() {
        return corretores;
    }

    public void serializaCorretor() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("corretores.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(corretores);
        objOS.flush();
        objOS.close();
    }

    public void desserializaCorretor() throws Exception {
        File objFile = new File("corretores.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("corretores.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            corretores = (Vector) objIS.readObject();
            objIS.close();
        }
    }

    public Corretor buscaCorretor(String nomeCorretor) {

        for (int idx = 0; idx < corretores.size(); idx++) {
            Corretor objCorretor = (Corretor) corretores.get(idx);

            if (objCorretor.getNome().equalsIgnoreCase(nomeCorretor)) {
                return objCorretor;
            }
        }

        return null;
    }

    public float calculaSalario(Corretor objCorretor, String pMes, String pAno) throws Exception {

        float salario = 0;
        if (validaMesAno(pMes, pAno)) {
            if (objCorretor instanceof Contratado) {

                for (int idx = 0; idx < listaVendas.size(); idx++) {
                    Venda objVenda = (Venda) listaVendas.get(idx);

                    SimpleDateFormat sdfM = new SimpleDateFormat("MM");
                    String mes = sdfM.format(objVenda.getDataVenda());

                    SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
                    String ano = sdfY.format(objVenda.getDataVenda());

                    if (objCorretor.getNome().equalsIgnoreCase(objVenda.getCorretorResponsavel()) && mes.equalsIgnoreCase(pMes)
                            && ano.equalsIgnoreCase(pAno)) {
                        salario += (float) objVenda.getValorDaVenda() * ((Contratado) objCorretor).getPercentualVenda();
                    }
                }

            } else if (objCorretor instanceof Comissionado) {

                for (int idx = 0; idx < listaVendas.size(); idx++) {
                    Venda objVenda = (Venda) listaVendas.get(idx);

                    SimpleDateFormat sdf = new SimpleDateFormat("MM");
                    String mes = sdf.format(objVenda.getDataVenda());

                    SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
                    String ano = sdfY.format(objVenda.getDataVenda());

                    if (objCorretor.getNome().equalsIgnoreCase(objVenda.getCorretorResponsavel()) && mes.equalsIgnoreCase(pMes)
                            && ano.equalsIgnoreCase(pAno)) {
                        salario += (float) objVenda.getValorDaVenda() * ((Comissionado) objCorretor).getPercentualVenda();
                    }
                }
            }

            return salario;
        } else {
            throw new Exception("Data inserida é invalida");
        }
    }

    public float calculaLucro(String pMes, String pAno) throws Exception {
        float lucro = 0;

        if (validaMesAno(pMes, pAno)) {
            for (int idx = 0; idx < listaVendas.size(); idx++) {
                Venda objVenda = (Venda) listaVendas.get(idx);

                SimpleDateFormat sdf = new SimpleDateFormat("MM");
                String mes = sdf.format(objVenda.getDataVenda());

                SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
                String ano = sdfY.format(objVenda.getDataVenda());

                if (mes.equalsIgnoreCase(pMes) && ano.equalsIgnoreCase(pAno)) {
                    lucro += (objVenda.getValorDaVenda() * 5) / 100;
                }
            }

            for (int idx = 0; idx < corretores.size(); idx++) {
                Corretor objCorretor = (Corretor) corretores.get(idx);

                lucro -= calculaSalario(objCorretor, pMes, pAno);

            }

            return lucro;
        } else {
            throw new Exception("Data inserida é invalida");
        }
    }

    public String corretorDoMes(String pMes, String pAno) throws Exception {

        if (validaMesAno(pMes, pAno)) {
            Corretor objCorretorDoMes = (Corretor) corretores.get(0);
            float comissao = 0;
            float testa_comissao = 0;

            comissao = calculaSalario(objCorretorDoMes, pMes, pAno);

            for (int idx = 1; idx < corretores.size(); idx++) {
                Corretor objCorretor = (Corretor) corretores.get(idx);

                testa_comissao = calculaSalario(objCorretor, pMes, pAno);

                if (testa_comissao > comissao) {

                    objCorretorDoMes = objCorretor;
                    comissao = testa_comissao;

                }
            }

            return "\n"
                    + "Nome: " + objCorretorDoMes.getNome()
                    + " || CRECI: " + objCorretorDoMes.getNumCRECI()
                    + "\n";
        } else {
            throw Exception("Data inserida é invalida");
        }
    }

    public String faturamentoCorretor(String pMes, String pAno) throws Exception {

        if (validaMesAno(pMes, pAno)) {
            String result = " ";

            for (int idx = 0; idx < corretores.size(); idx++) {
                Corretor objCorretor = (Corretor) corretores.get(idx);

                result += "\n"
                        + "Nome: " + objCorretor.getNome()
                        + " || CRECI: " + objCorretor.getNumCRECI()
                        + " || Faturamento trazido: R$" + objCtrPrincipal.getObjACtrVenda().calculaFaturamentoCorretor(objCorretor, pMes, pAno)
                        + "\n";
            }

            if (result.equalsIgnoreCase("")) {

                return "Error! Não existem Corretores cadastrados.";

            } else {

                return result;

            }
        } else {
            throw new Exception("Data inserida é invalida");
        }
    }

    public String PagamentoCorretores(String pMes, String pAno) throws Exception {

        if (validaMesAno(pMes, pAno)) {
            String result = " ";

            for (int idx = 0; idx < corretores.size(); idx++) {
                Corretor objCorretor = (Corretor) corretores.get(idx);

                if (objCorretor instanceof Contratado) {
                    result += "\n"
                            + "Nome: " + objCorretor.getNome()
                            + " || CRECI: " + objCorretor.getNumCRECI()
                            + " || Valor Pago: R$" + (objCtrPrincipal.getObjACtrCorretor().calculaSalario(objCorretor, pMes, pAno) + ((Contratado) objCorretor).getSalarioFixo())
                            + "\n";
                } else if (objCorretor instanceof Comissionado) {
                    result += "\n"
                            + "Nome: " + objCorretor.getNome()
                            + " || CRECI: " + objCorretor.getNumCRECI()
                            + " || Valor Pago: R$" + objCtrPrincipal.getObjACtrCorretor().calculaSalario(objCorretor, pMes, pAno)
                            + "\n";
                }

            }

            if (result.equalsIgnoreCase("")) {

                return "Error! Não existem Corretores cadastrados.";

            } else {

                return result;

            }
        } else {
            throw new Exception("Data inserida é invalida");
        }
    }

    public boolean validaMesAno(String pMes, String pAno) {
        int mes = Integer.parseInt(pMes);
        int ano = Integer.parseInt(pAno);

        GregorianCalendar dtA = new GregorianCalendar();
        dtA.setTime(new Date());

        int month = 1 + dtA.get(GregorianCalendar.MONTH);
        int year = dtA.get(GregorianCalendar.YEAR);

        if (mes > month && ano == year) {
            return false;
        } else if (ano > year) {
            return false;
        } else if (mes > 1 && mes < 12) {
            return true;
        }

        return true;
    }

    public void finalize() throws Exception {
        serializaCorretor();
    }

    private Exception Exception(String data_inserida_é_invalida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
