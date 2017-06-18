package Controller;

import Model.Imovel;
import Model.Venda;
import View.ImovelView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

public class ctrImovel {

    private /*ImovelView*/ limImovel objALimImovel = new limImovel();//new ImovelView();
    private ctrPrincipal objCtrPrincipal;
    private Imovel objAEntImovel;
    private String[] aDadosForm;
    private String arquivo = "Imovel.dat";
    private Vector Imoveis = new Vector();
    private Vector listaVendas = objCtrPrincipal.getObjACtrVenda().getListaVendas();
    private Vector ImoveisVendidos = new Vector();

    public boolean cadImovel() throws ParseException {
        cadastra();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse(aDadosForm[4]);
        objAEntImovel = new Imovel(aDadosForm[0], Integer.parseInt(aDadosForm[1]), aDadosForm[2], aDadosForm[3], Float.parseFloat(aDadosForm[4]), data);
        addVetor(objAEntImovel);

        return true;
    }

    private void cadastra() {
        aDadosForm = objALimImovel.montaForm();
    }

    private void pesquisa() {
        //aDadosForm = objALimImovel.pesquisaForm();
    }

    public void addVetor(Imovel pImovelp) {
        Imoveis.add(pImovelp);
    }

    public void preecheImoveisVendidos() throws Exception {

        for (int idx = 0; idx < listaVendas.size(); idx++) {

            Venda objVenda = (Venda) listaVendas.get(idx);

            for (int idx2 = 0; idx2 < Imoveis.size(); idx2++) {

                Imovel objImovel = (Imovel) Imoveis.get(idx);

                if (objVenda.getCodImovel() == objImovel.getCodigo()) {

                    ImoveisVendidos.add(objImovel);
                    Imoveis.remove(objImovel);
                }
            }
        }

        serializaImovel(); //testando
        serializaImovelVendido(); //testando
    }

    private void serializaImovel() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("Imovel.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(Imoveis);
        objOS.flush();
        objOS.close();
    }

    private void desserializaImovel() throws Exception {
        File objFile = new File("Imovel.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("Imovel.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            Imoveis = (Vector) objIS.readObject();
            objIS.close();
        }
    }

    private void serializaImovelVendido() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("ImovelVendido.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(ImoveisVendidos);
        objOS.flush();
        objOS.close();
    }

    private void desserializaImovelVendido() throws Exception {
        File objFile = new File("ImovelVendido.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("ImovelVendido.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            ImoveisVendidos = (Vector) objIS.readObject();
            objIS.close();
        }
    }

    public Vector getListagemImovel() {
        return Imoveis;
    }

    private String getImovel(Imovel objPImovel) {
        return "Código: " + objPImovel.getCodigo() + "  Nome Proprietário: " + objPImovel.getNomeProprietario() + "\n";
    }

    public void getListaImovel(String pTipo) throws Exception {
        desserializaImovel();
        String result = "";
        for (int idx = 0; idx < Imoveis.size(); idx++) {
            Imovel objImovel = (Imovel) Imoveis.elementAt(idx);

            if (objImovel.getTipo().equalsIgnoreCase(pTipo)) {
                result += "Tipo: " + objImovel.getTipo()
                        + " || Codigo: " + objImovel.getCodigo()
                        + " || Nome do Proprietário: " + objImovel.getNomeProprietario()
                        + " || Valor: " + objImovel.getValorRequerido()
                        + "\n";
            }
        }

        if (result.equalsIgnoreCase("")) {
            System.out.println("Não existem Imoveis cadastrados.");
        } else {
            //return result;
            objALimImovel.listaImoveis(result);
        }
    }

    public String getListaVendidos() throws Exception {
        desserializaImovelVendido();
        String result = "";
        for (int idx = 0; idx < ImoveisVendidos.size(); idx++) {
            Imovel objImovel = (Imovel) ImoveisVendidos.elementAt(idx);
            result += "Tipo: " + objImovel.getTipo()
                    + " || Codigo: " + objImovel.getCodigo()
                    + " || Nome do Proprietário: " + objImovel.getNomeProprietario()
                    + " || Valor: " + objImovel.getValorRequerido()
                    + "\n";

        }

        if (result.equalsIgnoreCase("")) {
            return "Não existem Imoveis cadastrados.";
        } else {
            return result;
        }
    }

    public void finalize() throws Exception {
        serializaImovel();
        serializaImovelVendido();
    }

}
