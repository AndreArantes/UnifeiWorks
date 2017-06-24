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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class ctrCorretor {

    private ctrPrincipal objCtrPrincipal;
    private Vector listaVendas = objCtrPrincipal.getObjACtrVenda().getListaVendas();
    private Vector corretores = new Vector();

    public ctrCorretor() throws Exception {
        desserializaCorretor();
    }

    public void cadCorretorCT(String nome, String CRECI, float salariofixo, String pData) throws Exception{
        
        SimpleDateFormat formato = new SimpleDateFormat();
        Date data = formato.parse(pData);
        
        if(corretores.add(new Contratado(nome, CRECI, salariofixo, data))){
            serializaCorretor();
        } else {
            throw new Exception("Error ao cadastrar corretor!");
        }
            
        
    }
    
    public void cadCorretorCM(){
            
    }


    public Vector getListaCorretor() {
        return corretores;
    }

    
    private void serializaCorretor() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("Corretor.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(corretores);
        objOS.flush();
        objOS.close();
    }

    private void desserializaCorretor() throws Exception {
        File objFile = new File("Corretor.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("Corretor.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            corretores = (Vector) objIS.readObject();
            objIS.close();
        }
    }    
    
    public float calculaSalario(Corretor objCorretor, String pMes) {
        float salario = 0;

        if (objCorretor instanceof Contratado) {

            for (int idx = 0; idx < listaVendas.size(); idx++) {
                Venda objVenda = (Venda) listaVendas.get(idx);
                
                SimpleDateFormat sdf = new SimpleDateFormat("MM");
                String mes = sdf.format(objVenda.getDataVenda());
                
                if (objCorretor.getNome().equalsIgnoreCase(objVenda.getCorretorResponsavel()) && mes.equalsIgnoreCase(pMes)) {
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

        public float calculaLucro(String pMes) {
        float lucro = 0;

        for (int idx = 0; idx < listaVendas.size(); idx++) {
            Venda objVenda = (Venda) listaVendas.get(idx);

            SimpleDateFormat sdf = new SimpleDateFormat("MM");
            String mes = sdf.format(objVenda.getDataVenda());

            if (mes.equalsIgnoreCase(pMes)) {
                lucro += (objVenda.getValorDaVenda() * 5) / 100;
            }
        }

        for (int idx = 0; idx < corretores.size(); idx++) {
            Corretor objCorretor = (Corretor) corretores.get(idx);

            lucro -= calculaSalario(objCorretor, pMes);

        }

    return lucro;
    }
        
    public Corretor corretorDoMes(String pMes) throws Exception {
        desserializaCorretor();
        
        Corretor objCorretorDoMes = (Corretor) corretores.get(0);
        float comissao = 0;
        float testa_comissao = 0;

        if (objCorretorDoMes instanceof Contratado) {
            comissao = calculaSalario(objCorretorDoMes, pMes) - ((Contratado) objCorretorDoMes).getSalarioFixo();
        } else {
            comissao = calculaSalario(objCorretorDoMes, pMes);
        }

        for (int idx = 1; idx < corretores.size(); idx++) {
            Corretor objCorretor = (Corretor) corretores.get(idx);

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

    public String salarioCorretor(String pMes) throws Exception {
        desserializaCorretor();
        
        String result = " ";

        for (int idx = 0; idx < corretores.size(); idx++) {
            Corretor objCorretor = (Corretor) corretores.get(idx);
            result += "Nome: " + objCorretor.getNome()
                    + " || CRECI: " + objCorretor.getNumCRECI()
                    + " || Salario: R$" + calculaSalario(objCorretor, pMes)
                    + "\n";
        }

        return result;
    }

    public String faturamentoCorretor(String pMes) throws Exception {
        desserializaCorretor();
        
        String result = " ";

        for (int idx = 0; idx < corretores.size(); idx++) {
            Corretor objCorretor = (Corretor) corretores.get(idx);
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
    
   public void finalize() throws Exception {
        serializaCorretor();

    }
}
