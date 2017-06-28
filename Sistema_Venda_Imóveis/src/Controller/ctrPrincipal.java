package Controller;

import Model.Corretor;
import View.TelaPrincipal;


public class ctrPrincipal {

    public TelaPrincipal objALimPrincipal;
    ctrImovel objACtrImovel;
    ctrCorretor objACtrCorretor;
    ctrVenda objACtrVenda;

    public ctrPrincipal() throws Exception {

        objACtrVenda = new ctrVenda(this);
        objACtrImovel = new ctrImovel( );
        objACtrCorretor = new ctrCorretor(this);

        objALimPrincipal = new TelaPrincipal(this);
    }

    public ctrVenda getObjACtrVenda() {
        return objACtrVenda;
    }

    public ctrImovel getObjCtrImovel() {
        return objACtrImovel;
    }

    public ctrCorretor getObjACtrCorretor() {
        return objACtrCorretor;
    }

    public void cadImovel(String tipo, String cod, String descricao, String nomeProprietario, float valor, String pData) throws Exception{
        objACtrImovel.cadImovel(tipo, cod, descricao, nomeProprietario, valor, pData);
    }
    
    public void cadCorretorCT(String nome, String CRECI, float salariofixo, String pData) throws Exception {
        objACtrCorretor.cadCorretorCT(nome, CRECI, salariofixo, pData);

    }

    public void cadCorretorCM(String nome, String CRECI, float comissao) throws Exception {
        objACtrCorretor.cadCorretorCM(nome, CRECI, comissao);
    }

    public void cadVenda(String codImovel, float valorDaVenda, String nomeComprador, String dataVenda, String corretorResponsvel) throws Exception {
        objACtrVenda.cadVenda(codImovel, valorDaVenda, nomeComprador, dataVenda, corretorResponsvel);
    }
    
    public Corretor buscaCorretor(String nome){
        return objACtrCorretor.buscaCorretor(nome);
    }
    
    public float calculaSalario(Corretor objCorretor, String pMes,String pAno) throws Exception{
        return objACtrCorretor.calculaSalario(objCorretor, pMes, pAno);
    }

    public void finalize() {
        try {
            objACtrImovel.finalize();
            objACtrCorretor.finalize();
            objACtrVenda.finalize();
        } catch (Exception e) {

        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        ctrPrincipal cp = new ctrPrincipal();
    }
}
