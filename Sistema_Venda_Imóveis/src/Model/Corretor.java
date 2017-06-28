
package Model;

import java.io.Serializable;

public class Corretor implements Serializable{
    private String nome; // nome do corretor.
    private String NumCRECI; // número do orgão CRECI (5 digitos).

    public Corretor(String nome, String NumCRECI) {
        this.nome = nome;
        this.NumCRECI = NumCRECI;
    }

    public String getNome() {
        return nome;
    }

    public String getNumCRECI() {
        return NumCRECI;
    }
    
    public String toString() {
        return getNome();
    }
    
}
