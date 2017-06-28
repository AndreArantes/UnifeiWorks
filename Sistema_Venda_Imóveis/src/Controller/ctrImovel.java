package Controller;

import Model.Imovel;
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

    
    public void cadImovel(String tipo, String cod, String descricao, String nomeProprietario, float valor, String pData) throws Exception{
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse(pData);
        
        try{
            Imoveis.add(new Imovel(tipo, cod, descricao, nomeProprietario, valor, data));
            serializaImovel();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }    
        
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

                if(objImovel.getCodigo().equalsIgnoreCase(codImovel)){

                    ImoveisVendidos.add(objImovel);
                    Imoveis.remove(objImovel);
                    serializaImovel();
                    serializaImovelVendido();
                    testa = true;
                    
                } else{
                    
                    testa = false;
                    
                }
                
            }
            
            if(testa == true){
                
            } else if (testa == false){
              throw new Exception("Imovel não encontrado!");
            }          
    }

    public void excluiImovel(String codImovel) throws Exception {
        
        boolean testa = true;
        
            for (int idx = 0; idx < Imoveis.size(); idx++) {

                Imovel objImovel = (Imovel) Imoveis.get(idx);

                if(objImovel.getCodigo().equalsIgnoreCase(codImovel)){

                    Imoveis.remove(objImovel);
                    serializaImovel();
                    testa = true;
                    
                } else{
                    
                    testa = false;
                    
                }
                
            }
            
            if(testa == true){
                
            } else if (testa == false){
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

    public String getListaVendidos() throws Exception {
        
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

    public String getListaEncalhados(String pMes, String pAno) throws Exception {
        
        if(validaMesAno(pMes, pAno)){
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
        } else{
            throw new Exception("Data inserida é invalida");
        }
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInDays = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInDays, TimeUnit.DAYS);
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
        serializaImovel();
        serializaImovelVendido();
    }

}
