package gerenciadorfichasrpg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author eduar
 */
public class AtributosSistema {
    private ArrayList<Atributo> atributos = new ArrayList(); //atributos fixos de determinado sistema
    
    //carrega os atributos dentro de um sistema armazenado em um arquivo
    public AtributosSistema(File atribFile) throws FileNotFoundException{
        Scanner scanner = new Scanner(atribFile);
        scanner.useDelimiter("\n");
        String nomeAtributo = scanner.next(); 
        while (!"=-=".equals(nomeAtributo)){ //nao para ate que chege no limitador dos atributos
            boolean opcional = ("true".equals(scanner.next())); //le se e opcional

            int i = 0;
            ArrayList<String> nomes = new ArrayList();
            nomes.add(scanner.next());
            while (!"-".equals(nomes.get(i))){ //adciona os nomes dos campos a serem preenchidos ate q chege no limitador dos conteudos
                i++;
                nomes.add(scanner.next());
            }
            nomes.remove(i);
            
            insereAtributo(nomeAtributo, opcional, nomes); //insere tudo sobre um atributo que foi lido
            nomeAtributo = scanner.next();
        }
    }
    
    private void insereAtributo(String nome, boolean opcional , ArrayList<String> nomeConteudos){ //insere um atributo na lista de atributos do sistema
        Atributo atributoInserir = new Atributo(nome, opcional ,nomeConteudos);
        atributos.add(atributoInserir);
    }

    public ArrayList<Atributo> getAtributos() { //retorna a lista de atributos do sistema
        return atributos;
    }
}
