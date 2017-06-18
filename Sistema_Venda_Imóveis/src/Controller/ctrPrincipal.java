
package Controller;

public class ctrPrincipal {
    
    private int intAOperacao = -1;
    private ctrImovel objACtrImovel;
    private limPrincipal objALimPrincipal;
    private ctrCorretor objACtrCorretor;
    private ctrVenda objACtrVenda;
    
    
    public ctrPrincipal() {
        objALimPrincipal = new limPrincipal();
        try {
            objACtrImovel = new ctrImovel();
            objACtrCorretor = new ctrCorretor();
            objACtrVenda= new ctrVenda();
        } catch (Exception e) {
            System.out.println("Erro na abertura de arquivo");
        }

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


}
