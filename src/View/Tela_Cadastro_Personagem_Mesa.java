package View;

import Controller.MesasController;
import Controller.UserController;
import Model.Entidade.Personagem;
import Model.Entidade.Usuario;
import java.util.ArrayList;
import java.util.Scanner;

public class Tela_Cadastro_Personagem_Mesa {
    public void mostrar(MesasController controlem, UserController controleu, Usuario user){
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        
        ArrayList<String> ids = user.getIDSPersos();
        Personagem personagem = null;
        
        System.out.println("\nPersonagens: \n");
        for(int i = 0; i < ids.size(); i++){
            System.out.printf("[%d] - %s  \n", i, ids.get(i));}
        
        System.out.println("Digite o id do personagem:");
        String id = scan.next(); 
        
        System.out.println("Digite o nome da mesa:");
        String mesa = scan.next(); 
        
        try{
            personagem = controleu.getPerso(user.getNome(), id);
            }catch(Exception e){
                System.err.println("Erro: " + e.getMessage());}
        
        
        try{
            controlem.addCharacter(personagem, user, mesa);
            }catch(Exception e){
                System.err.println("Erro: " + e.getMessage());}
        
        
    
    }
    
    
}
