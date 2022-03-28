package View;

import Controller.UserController;
import Model.Entidade.Exceptions.PersonagemInexistenteException;
import Model.Entidade.Exceptions.UsuarioNaoCadastradoException;
import Model.Entidade.Personagem;
import java.util.Scanner;

public class Tela_Info_Personagem {
    
    public void mostrar(String usuario){
        Scanner scan = new Scanner(System.in);
        UserController controle = new UserController();
        Personagem perso = null;
        
        System.out.println("Digite o id do personagem: ");
        String id = scan.next();
        
        try{
            perso = controle.getPerso(usuario, id);
            }catch(PersonagemInexistenteException | UsuarioNaoCadastradoException e){
                System.err.println("Erro: " + e.getMessage());}

        if(perso != null){System.out.println(perso.toString());}
        
    }
    
}
