
package Model;

import java.io.Serializable;
import java.util.*;

public class Corretor implements Serializable{
    private String nome; // nome do corretor.
    private int NumCRECI; // número do orgão CRECI (5 digitos).

    public Corretor(String nome, int NumCRECI) {
        this.nome = nome;
        this.NumCRECI = NumCRECI;
    }

    public String getNome() {
        return nome;
    }

    public int getNumCRECI() {
        return NumCRECI;
    }
}
