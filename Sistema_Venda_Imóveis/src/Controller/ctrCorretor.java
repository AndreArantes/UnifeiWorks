package Controller;

import Model.Corretor;
import java.util.Vector;

public class ctrCorretor {

    private Corretor objACorretor;
    private limCorretor objALimCorretor;
    //private ctrPrincipal objCtrPrincipal;
    private Vector vecCorretor = new Vector();
    private Vector vecADadosForm = new Vector();

    public ctrCorretor() throws Exception {
        //objCtrPrincipal = pCtrPrincipal;
        objALimCorretor = new limCorretor();
        //desserializaCorretor();
    }

    public boolean cadTurma(String pTipo, String pNome, int pNumCRECI) {
        objACorretor = new Corretor(pNome, pNumCRECI);
        cadastra();
        
        vecCorretor.add(objAEntTurma);
        return true;
    }

    private void cadastra() {
        vecADadosForm = objALimTurma.montaForm(objCtrPrincipal.getObjCtrDisciplina().getListaDisciplinas(),
                objCtrPrincipal.getObjCtrEstudante().getListaEstudantes());
    }
}
