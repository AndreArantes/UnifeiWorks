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

    public float calculaSalario(Corretor objCorretor, String pMes, String pAno) {

        float salario = 0;

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
    }

    public float calculaLucro(String pMes, String pAno) {
        float lucro = 0;

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
    }

    public String corretorDoMes(String pMes) {
        Date dataAtual = new Date();

        SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
        String ano = sdfY.format(dataAtual);

        Corretor objCorretorDoMes = (Corretor) corretores.get(0);
        float comissao = 0;
        float testa_comissao = 0;

        if (objCorretorDoMes instanceof Contratado) {
            comissao = calculaSalario(objCorretorDoMes, pMes, ano) - ((Contratado) objCorretorDoMes).getSalarioFixo();
        } else {
            comissao = calculaSalario(objCorretorDoMes, pMes, ano);
        }

        for (int idx = 1; idx < corretores.size(); idx++) {
            Corretor objCorretor = (Corretor) corretores.get(idx);

            if (objCorretorDoMes instanceof Contratado) {
                testa_comissao = calculaSalario(objCorretor, pMes, ano) - ((Contratado) objCorretor).getSalarioFixo();

                if (testa_comissao > comissao) {

                    objCorretorDoMes = objCorretor;
                    comissao = testa_comissao;

                }
            } else {
                testa_comissao = calculaSalario(objCorretor, pMes, ano);

                if (testa_comissao > comissao) {

                    objCorretorDoMes = objCorretor;
                    comissao = testa_comissao;

                }
            }

        }
        return  "\n"
                + "Nome: " + objCorretorDoMes.getNome()
                + " || CRECI: " + objCorretorDoMes.getNumCRECI()
                + "\n";
    }

    public String faturamentoCorretor(String pMes) throws Exception {
        desserializaCorretor();

        String result = " ";

        for (int idx = 0; idx < corretores.size(); idx++) {
            Corretor objCorretor = (Corretor) corretores.get(idx);

            result += "\n"
                    + "Nome: " + objCorretor.getNome()
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

    public String PagamentoCorretores(String pMes, String pAno) throws Exception {
        desserializaCorretor();

        String result = " ";

        for (int idx = 0; idx < corretores.size(); idx++) {
            Corretor objCorretor = (Corretor) corretores.get(idx);

            result += "\n"
                    + "Nome: " + objCorretor.getNome()
                    + " || CRECI: " + objCorretor.getNumCRECI()
                    + " || Valor Pago: R$" + objCtrPrincipal.getObjACtrCorretor().calculaSalario(objCorretor, pMes, pAno)
                    + "\n";
        }

        if (result.equalsIgnoreCase("")) {

            return "Error! Não existem Corretores cadastrados.";

        } else {

            return result;

        }
    }

    public void finalize() throws Exception {
        serializaCorretor();
    }
}
