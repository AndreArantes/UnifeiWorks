
package Controller;

import View.TelaPrincipal;
import java.util.Date;

public class ctrPrincipal {
    
    private TelaPrincipal objALimPrincipal;
    private ctrImovel objACtrImovel;
    private ctrCorretor objACtrCorretor;
    private ctrVenda objACtrVenda;
    
    
    public ctrPrincipal() throws Exception {    
       try{ 
        objACtrVenda = new ctrVenda();
        objACtrImovel = new ctrImovel();
        objACtrCorretor = new ctrCorretor();
       } catch(Exception ex) { 
           
       }
        objALimPrincipal = new TelaPrincipal(this);
    }

    public ctrImovel getObjCtrImovel() {
        return objACtrImovel;
    }

    public ctrCorretor getObjACtrCorretor() {
        return objACtrCorretor;
    }

    public ctrVenda getObjACtrVenda() {
        return objACtrVenda;
    }

/*
    private boolean cadImovel() {
        return objACtrImovel.cadImovel();
    }

    private boolean cadCorretor() {
        return objACtrEstudante.cadEstudante();
    }
    */
    public void cadVenda(String codImovel, float valorDaVenda, String nomeComprador, String dataVenda, String corretorResponsvel) throws Exception {
        objACtrVenda.cadVenda(codImovel, valorDaVenda, nomeComprador, dataVenda, corretorResponsvel);
    }
    /*
    public void listaEstudantes(){
        objACtrEstudante.imprimeListaEstudantes();
    }
    
    public void listaDisciplinas(){
        objACtrDisciplina.imprimeListaDisciplinas();
    }
    
    public void listaTurmas(){
        objACtrTurma.imprimeListaTurmas();
    }
    */
    public void finalize() {
        try {
            objACtrImovel.finalize();
            objACtrCorretor.finalize();
            objACtrVenda.finalize();
        } catch (Exception e) {
            System.err.println("Erro ao fechar arquivo!");
        }
        System.exit(0);
    }
    
        public static void  main(String [] args) throws Exception{
            ctrPrincipal cp = new ctrPrincipal();
        }
}
