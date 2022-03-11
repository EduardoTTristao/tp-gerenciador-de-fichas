package Model.Persistence;

import Exceptions.PersonagemInexistenteException;
import Model.Entidade.Personagem;
import java.util.ArrayList;

public class PersonagemBD {
    private final ArrayList<Personagem> personagens;
    
    public PersonagemBD(){
        personagens = new ArrayList<>();
    }

    public void add(Personagem personagem) {
        personagens.add(personagem);
    }

    public ArrayList<String> listId() {
        ArrayList<String> lista = new ArrayList<>();
        for (Personagem personagem : personagens)
            lista.add(personagem.getId());
        return lista;
    }
    
    public Personagem getPersonagem(String id) throws PersonagemInexistenteException{
        for (Personagem personagem : personagens)
            if (personagem.getId().equals(id))
                return personagem;
        throw new PersonagemInexistenteException(id);
    }
}
