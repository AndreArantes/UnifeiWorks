package Controller;

import Model.Corretor;
import Model.Venda;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JOptionPane;

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
        try {
            vendas.add(new Venda(codImovel, valorDaVenda, nomeComprador, data, corretorResponsvel));
            objPrincipal.objACtrImovel.vendeImovel(codImovel);
            serializaVenda();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
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

    public String imprimeListaVendas(String pMes, String pAno) throws Exception {

        if (validaMesAno(pMes, pAno)) {
            String result = "";

            for (int idx = 0; idx < vendas.size(); idx++) {
                Venda objVenda = (Venda) vendas.get(idx);
                SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
                String mes = sdfMes.format(objVenda.getDataVenda());

                SimpleDateFormat sdfAnos = new SimpleDateFormat("yyyy");
                String ano = sdfAnos.format(objVenda.getDataVenda());

                if (mes.equalsIgnoreCase(pMes) && ano.equalsIgnoreCase(pAno)) {
                    Venda objDisc = (Venda) vendas.get(idx);

                    DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
                    String data = formatDate.format(objDisc.getDataVenda());

                    result += "\n"
                            + "Valor: R$" + objDisc.getValorDaVenda()
                            + "\nComprador: " + objDisc.getNomeComprador()
                            + "\nData: " + data
                            + "\nCorretor: " + objDisc.getCorretorResponsavel()
                            + "\n";
                }

            }
            return result;
        } else {
            throw new Exception("Data inserida é invalida");
        }
    }

    public float calculaFaturamento(String pMes, String pAno) throws Exception {
        float faturamento = 0;
        if (validaMesAno(pMes, pAno)) {
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
        } else {
            throw new Exception("Data inserida é invalida");
        }
    }

    public float calculaFaturamentoCorretor(Corretor objCorretor, String pMes, String pAno) throws Exception {
        if (validaMesAno(pMes, pAno)) {
            float faturamento = 0;
            String auxCorretor = "";
            auxCorretor = String.valueOf(objCorretor);
            for (int idx = 0; idx < vendas.size(); idx++) {
                Venda objVenda = (Venda) vendas.get(idx);

                SimpleDateFormat sdf = new SimpleDateFormat("MM");
                String mes = sdf.format(objVenda.getDataVenda());

                if (mes.equalsIgnoreCase(pMes) && objVenda.getCorretorResponsavel().equals(auxCorretor)) {
                    faturamento += (objVenda.getValorDaVenda() * 5) / 100;

                }
            }

            return faturamento;
        } else {
            throw new Exception("Data inserida é invalida");
        }
    }

    public float calculaValorPagoCorretor(Corretor objCorretor, String pMes, String pAno) throws Exception {
        if (validaMesAno(pMes, pAno)) {
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
        } else {
            throw new Exception("Data inserida é invalida");
        }
    }

    public boolean validaMesAno(String pMes, String pAno) {
        int mes = Integer.parseInt(pMes);
        int ano = Integer.parseInt(pAno);

        System.out.println(mes);
        System.out.println(ano);
        
        GregorianCalendar dtA = new GregorianCalendar();
        dtA.setTime(new Date());

        int month = 1 + dtA.get(GregorianCalendar.MONTH);
        int year = dtA.get(GregorianCalendar.YEAR);
        
        System.out.println(month);
        System.out.println(year);
        
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
        serializaVenda();
    }
}
