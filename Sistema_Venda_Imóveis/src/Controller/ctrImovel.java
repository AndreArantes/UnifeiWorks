package Controller;

import Model.Imovel;
import View.ImovelView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import javax.swing.JOptionPane;

public class ctrImovel {

    private ImovelView objALimImovel = new ImovelView();
    private Imovel objAEntImovel;
    private String[] DadosForm;
    private String arquivo = "Imovel.dat";
    private Vector listaImovel = new Vector();

    public boolean cadImovel() {
        objAEntImovel = new Imovel();
        cadastra();
        objAEntImovel.setCodigo(1);
        objAEntImovel.setDataCadastro(1);
        objAEntImovel.setDescricao(DadosForm[2]);
        objAEntImovel.setNomeProprietario(DadosForm[3]);
        objAEntImovel.setTipo(DadosForm[4]);
        objAEntImovel.setValorRequerido(1);
        addVetor(objAEntImovel);

        return true;
    }

    private void cadastra() {
        DadosForm = objALimImovel.montaForm();
    }

    private void pesquisa() {
        DadosForm = objALimImovel.pesquisaForm();
    }


    public void addVetor(Imovel pImovelp) {
        listaImovel.add(pImovelp);
    }

    private void serializaImovel() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("Imovel.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(listaImovel);
        objOS.flush();
        objOS.close();
    }

    private void desserializaImovel() throws Exception {
        File objFile = new File("Imovel.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("Imovel.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            listaImovel = (Vector) objIS.readObject();
            objIS.close();
        }
    }

    public Vector getListagemImovel() {
        return listaImovel;
    }

    private String getImovel(Imovel objPImovel) {
        return "Código: " + objPImovel.getCodigo() + "  Nome Proprietário: " + objPImovel.getNomeProprietario() + "\n";
    }

    public String getListaImovel() {
        String result = "";
        Imovel objImovel = null;
        for (int intIdx = 0; intIdx < listaImovel.size(); intIdx++) {
            objImovel = (Imovel) listaImovel.elementAt(intIdx);
            result += getImovel(objImovel);
            
        }
        if (result.equalsIgnoreCase("")) {
            return "Não existem Imoveis cadastrados.";
        } else {
            return result;
        }
    }

    public void finalize() throws Exception {
        serializaImovel();
    }

}
