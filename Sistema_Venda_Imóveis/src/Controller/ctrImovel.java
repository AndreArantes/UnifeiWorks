package Controller;

import Model.Imovel;
import Model.Venda;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

public class ctrImovel {

    private Vector<Imovel> Imoveis = new Vector<Imovel>();
    private Vector<Imovel> ImoveisVendidos = new Vector<Imovel>();

    public ctrImovel() throws Exception {

        desserializaImovel();
        desserializaImovelVendido();

    }

    public void cadImovel(String tipo, String cod, String descricao, String nomeProprietario, float valor, String pData) throws Exception {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse(pData);
<<<<<<< HEAD
        
        try{
            Imoveis.add(new Imovel(tipo, cod, descricao, nomeProprietario, valor, data));
            serializaImovel();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }    
        
=======

        if (Imoveis.add(new Imovel(tipo, cod, descricao, nomeProprietario, valor, data))) {
            serializaImovel();
        } else {
            throw new Exception("Error ao cadastrar imóvel!");
        }

>>>>>>> master
    }

    public Imovel buscaImovel(String codImovel) throws Exception {
 
        
            for (int idx = 0; idx < Imoveis.size(); idx++) {

                Imovel objImovel = (Imovel) Imoveis.get(idx);

                if(objImovel.getCodigo().equalsIgnoreCase(codImovel)){

                    return objImovel;                
                } 
                
            }

    throw new Exception("Imovel não encontrado!");    
    }
        
    public void vendeImovel(String codImovel) throws Exception {

        boolean testa = true;

        for (int idx = 0; idx < Imoveis.size(); idx++) {

            Imovel objImovel = (Imovel) Imoveis.get(idx);

            if (objImovel.getCodigo().equalsIgnoreCase(codImovel)) {

                ImoveisVendidos.add(objImovel);
                Imoveis.remove(objImovel);
                serializaImovel();
                serializaImovelVendido();
                testa = true;

            } else {

                testa = false;

            }

        }

        if (testa == true) {

        } else if (testa == false) {
            throw new Exception("Imovel não encontrado!");
        }
    }

    public void serializaImovel() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("imoveis.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(Imoveis);
        objOS.flush();
        objOS.close();
    }

    public void desserializaImovel() throws Exception {
        File objFile = new File("imoveis.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("imoveis.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            Imoveis = (Vector) objIS.readObject();
            objIS.close();
        }
    }

    private void serializaImovelVendido() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("imoveisVendidos.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(ImoveisVendidos);
        objOS.flush();
        objOS.close();
    }

    private void desserializaImovelVendido() throws Exception {
        File objFile = new File("imoveisVendidos.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("imoveisVendidos.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            ImoveisVendidos = (Vector) objIS.readObject();
            objIS.close();
        }
    }

    public Vector getListagemImovel() {
        return Imoveis;
    }

    public String getImovel(Imovel objPImovel) {
        return "Código: " + objPImovel.getCodigo() + "  Nome Proprietário: " + objPImovel.getNomeProprietario() + "\n";
    }

    public void getListaImovel(String pTipo) throws Exception {
        desserializaImovel();
        String result = "";
        for (int idx = 0; idx < Imoveis.size(); idx++) {
            Imovel objImovel = (Imovel) Imoveis.elementAt(idx);

            if (objImovel.getTipo().equalsIgnoreCase(pTipo)) {
                result += "\n"
                        + "Tipo: " + objImovel.getTipo()
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
        }
    }

    public String getListaVendidos() throws Exception {
        desserializaImovelVendido();
        String result = "";
        for (int idx = 0; idx < ImoveisVendidos.size(); idx++) {
            Imovel objImovel = (Imovel) ImoveisVendidos.elementAt(idx);
            result += "\n"
                    + "Tipo: " + objImovel.getTipo()
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

    public String getListaEncalhados(String pMes, String pAno) {
        String result = "";
       
        for (int idx = 0; idx < Imoveis.size(); idx++) {
            Imovel objImovel = (Imovel) Imoveis.elementAt(idx);

            if (getDateDiff(objImovel.getDataCadastro(), new Date(), TimeUnit.DAYS) > 180) {

                result += "\n"
                        + "Tipo: " + objImovel.getTipo()
                        + " || Codigo: " + objImovel.getCodigo()
                        + " || Nome do Proprietário: " + objImovel.getNomeProprietario()
                        + " || Valor: " + objImovel.getValorRequerido()
                        + "\n";
            }
        }

        if (result.equalsIgnoreCase("")) {
            return "Não existem Imoveis cadastrados.";
        } else {
            return result;
        }
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInDays = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInDays, TimeUnit.DAYS);
    }

    public void finalize() throws Exception {
        serializaImovel();
        serializaImovelVendido();
    }

}
