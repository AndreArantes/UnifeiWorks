
package Controller;

import Model.Venda;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class ctrVenda {
    
    private limVenda objALimVenda = new limVenda();
    private Venda objAEntVenda;
    private String[] aDadosForm;
    private Vector vecAVendas = new Vector();
    private final String arquivo = "disc.dat";

    public ctrVenda() throws Exception {
        objALimVenda = new limVenda();
        desserializaVenda();
    }

    public boolean cadDisciplina() throws ParseException {
        cadastra();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse(aDadosForm[2]);
        objAEntVenda = new Venda(Integer.parseInt(aDadosForm[0]),Float.parseFloat(aDadosForm[1]),aDadosForm[2], data ,aDadosForm[4]);
        addVetor(objAEntVenda);
        
    return true;
    }

    private void cadastra() {
        aDadosForm = objALimVenda.montaForm();
    }

    private void salva() {
    }

    public void addVetor(Venda pVenda) {
        vecAVendas.add(pVenda);
    }

    private void serializaVenda() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("vendas.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(vecAVendas);
        objOS.flush();
        objOS.close();
    }

    private void desserializaVenda() throws Exception {
        File objFile = new File("vendas.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("vendas.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            vecAVendas = (Vector) objIS.readObject();
            objIS.close();
        }
    }

    public Vector getListaDisciplinas() {
        return vecAVendas;
    }
    
    public void imprimeListaVendas(){
        String result="Lista de Vendas:\n";
        
        for(int idx = 0; idx<vecAVendas.size(); idx++){
            Venda objDisc = (Venda) vecAVendas.get(idx);
            result +=  "Valor: R$" + objDisc.getValorDaVenda()
                       +" || Comprador: " + objDisc.getNomeComprador()
                       +" || Data: " + objDisc.getDataVenda()
                       +" || Corretor: " + objDisc.getCorretorResponsavel()
                       +"\n";
        }
        
        objALimVenda.listaVendas(result);    
    }
    
    public void finalize() throws Exception {
        serializaVenda();
    }
}
