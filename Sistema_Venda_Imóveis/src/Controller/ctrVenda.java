package Controller;

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

public class ctrVenda {

    private ctrPrincipal objPrincipal;
    private Vector<Venda> vendas = new Vector<Venda>();

    public ctrVenda(ctrPrincipal pCtr) throws Exception {

        objPrincipal = pCtr;
        desserializaVenda();

    }

    public void cadVenda(String codImovel, float valorDaVenda, String nomeComprador, String dataVenda, String corretorResponsvel) throws Exception {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse(dataVenda);

        if (vendas.add(new Venda(codImovel, valorDaVenda, nomeComprador, data, corretorResponsvel))) {
            objPrincipal.objACtrImovel.vendeImovel(codImovel);
            serializaVenda();
        } else {
            throw new Exception("Error ao cadastrar venda!");
        }
    }

    private void serializaVenda() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("vendas.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(vendas);
        objOS.flush();
        objOS.close();
    }

    private void desserializaVenda() throws Exception {
        File objFile = new File("vendas.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("vendas.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            vendas = (Vector) objIS.readObject();
            objIS.close();
        }
    }

    public Vector getListaVendas() {
        return vendas;
    }

    public String imprimeListaVendas(String pMes, String pAno) {
        String result = "";

        for (int idx = 0; idx < vendas.size(); idx++) {
            Venda objVenda = (Venda) vendas.get(idx);
            SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
            String mes = sdfMes.format(objVenda.getDataVenda());

            SimpleDateFormat sdfAnos = new SimpleDateFormat("yyyy");
            String ano = sdfAnos.format(objVenda.getDataVenda());

            if (mes.equalsIgnoreCase(pMes) && ano.equalsIgnoreCase(pAno)) {
                Venda objDisc = (Venda) vendas.get(idx);
                result += "\n"
                        + "Valor: R$" + objDisc.getValorDaVenda()
                        + "\nComprador: " + objDisc.getNomeComprador()
                        + "\nData: " + objDisc.getDataVenda()
                        + "\nCorretor: " + objDisc.getCorretorResponsavel()
                        + "\n";
            }

        }
        return result;
    }

    public float calculaFaturamento(String pMes, String pAno) {
        float faturamento = 0;

        for (int idx = 0; idx < vendas.size(); idx++) {
            Venda objVenda = (Venda) vendas.get(idx);

            SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
            String mes = sdfMes.format(objVenda.getDataVenda());

            SimpleDateFormat sdfAnos = new SimpleDateFormat("yyyy");
            String ano = sdfAnos.format(objVenda.getDataVenda());

            if (mes.equalsIgnoreCase(pMes) && ano.equalsIgnoreCase(pAno)) {
                faturamento += (objVenda.getValorDaVenda() * 5) / 100;
            }
        }

        return faturamento;
    }

    public float calculaFaturamentoCorretor(Corretor objCorretor, String pMes) {
        float faturamento = 0;
        String auxCorretor = "";
        auxCorretor = String.valueOf(objCorretor);
        for (int idx = 0; idx < vendas.size(); idx++) {
            Venda objVenda = (Venda) vendas.get(idx);

            SimpleDateFormat sdf = new SimpleDateFormat("MM");
            String mes = sdf.format(objVenda.getDataVenda());
            
            if (mes.equalsIgnoreCase(pMes) && objVenda.getCorretorResponsavel().equals(auxCorretor)) {
                faturamento += (objVenda.getValorDaVenda() * 5) / 100;
                System.out.println(objVenda.getValorDaVenda());
            }
        }

        return faturamento;
    }
    
    public float calculaValorPagoCorretor(Corretor objCorretor, String pMes) {
        float faturamento = 0;
        String auxCorretor = "";
        auxCorretor = String.valueOf(objCorretor);
        for (int idx = 0; idx < vendas.size(); idx++) {
            Venda objVenda = (Venda) vendas.get(idx);

            SimpleDateFormat sdf = new SimpleDateFormat("MM");
            String mes = sdf.format(objVenda.getDataVenda());
            
            if (mes.equalsIgnoreCase(pMes) && objVenda.getCorretorResponsavel().equals(auxCorretor)) {
                faturamento += (objVenda.getValorDaVenda() * 5) / 100;
                System.out.println(objVenda.getValorDaVenda());
            }
        }

        return faturamento;
    }

    public void finalize() throws Exception {
        serializaVenda();
    }
}
