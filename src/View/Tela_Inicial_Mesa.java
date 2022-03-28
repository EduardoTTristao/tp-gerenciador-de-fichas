package View;

import Controller.MesasController;
import Controller.UserController;
import Model.Entidade.Personagem;
import Model.Entidade.Usuario;
import java.util.ArrayList;
import java.util.Scanner;

public class Tela_Inicial_Mesa {
    
    Scanner scan = new Scanner(System.in);
    int opcao;    
    
    public void mostrar(MesasController controlem, UserController controleu, Usuario usuario, String nomemesa){

        
        menu();
        opcao=scan.nextInt();
        
        while(opcao!=0){
            switch(opcao){
                case 1:
                    mostraPCS(controlem, usuario, nomemesa);
                    break;
                case 2:
                    mostraNPCS(controlem, usuario, nomemesa);
                    break;
                case 3:
                    Tela_Cadastro_Usuario_Mesa caduser = new Tela_Cadastro_Usuario_Mesa();
                    caduser.mostrar(controlem, nomemesa, usuario);
                    break;
                case 4:
                    Tela_Cadastro_Mestre_Mesa cadmestre = new Tela_Cadastro_Mestre_Mesa();
                    cadmestre.mostrar(controlem, nomemesa, usuario);
                    break;                    
            }
        menu();
        opcao=scan.nextInt();     
        }
    }
    
    public static void menu(){
        System.out.println("\nMenu de Mesa");
        System.out.println("1) Listar PCS");
        System.out.println("2) Listar NPCS");
        System.out.println("3) Adicionar Jogador");
        System.out.println("4) Adicionar Mestre");        
        System.out.println("0) Voltar");}
    
    public void mostraPCS(MesasController controlem, Usuario user, String mesa){
        
        ArrayList<Personagem> personagens = null;
        
        try{
            personagens = controlem.getPCS(mesa, user);
            }catch(Exception e){
                System.err.println("Erro: " + e.getMessage());}
        
        if(personagens != null){
            System.out.println("\nPCS: ");
            for(int i = 0; i < personagens.size(); i++){
                        System.out.printf("[%d] - %s  \n", i, personagens.get(i).getNome());}
        }
    }
    
    public void mostraNPCS(MesasController controlem, Usuario user, String mesa){
        
        ArrayList<Personagem> personagens = null;
        
        try{
            personagens = controlem.getNPCS(mesa, user);
            }catch(Exception e){
                System.err.println("Erro: " + e.getMessage());}
        
        if(personagens != null){
            System.out.println("\nNPCS:");
            for(int i = 0; i < personagens.size(); i++){
                        System.out.printf("[%d] - %s  \n", i, personagens.get(i).getNome());}
        }
    }
    
    
}
