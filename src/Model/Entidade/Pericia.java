package Model.Entidade;

import Model.Entidade.Exceptions.CategoriaPericiaInvalidaException;
import Model.Entidade.Exceptions.ModPericiaInvalidaException;

class Pericia {
    private final String nome;
    private int mod;
    private final String categoria;
            
    public Pericia(String nome, int mod, String categoria) throws CategoriaPericiaInvalidaException, ModPericiaInvalidaException{
        this.nome = nome;
        this.mod = mod;
        if ("facil".equals(categoria.toLowerCase()) ||
                "medio".equals(categoria.toLowerCase()) ||
                "dificil".equals(categoria.toLowerCase()) ||
                "muito dificil".equals(categoria.toLowerCase()))
            this.categoria = categoria;
        else
            throw new CategoriaPericiaInvalidaException(categoria);
        
        switch (categoria.toLowerCase()){
            case "facil" -> {
                if(mod < 0)
                    throw new ModPericiaInvalidaException(mod);
            }
            case "medio" -> {
                if(mod < -1)
                    throw new ModPericiaInvalidaException(mod);
            }
            case "dificil" -> {
                if(mod < -2)
                    throw new ModPericiaInvalidaException(mod);
            }
            case "muito dificil" -> {
                if(mod < -3)
                    throw new ModPericiaInvalidaException(mod);
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public int getMod() {
        return mod;
    }

    public void setMod(int mod) throws ModPericiaInvalidaException {
        switch (categoria.toLowerCase()){
            case "facil" -> {
                if(mod < 0)
                    throw new ModPericiaInvalidaException(mod);
            }
            case "medio" -> {
                if(mod < -1)
                    throw new ModPericiaInvalidaException(mod);
            }
            case "dificil" -> {
                if(mod < -2)
                    throw new ModPericiaInvalidaException(mod);
            }
            case "muito dificil" -> {
                if(mod < -3)
                    throw new ModPericiaInvalidaException(mod);
            }
        }
        
        this.mod = mod;
    }

    public String getCategoria() {
        return categoria;
    }
    
    public int getCusto() throws CategoriaPericiaInvalidaException{
        int base = 0;
        switch (categoria.toLowerCase()){
            case "facil" -> {
                base = 0;
            }
            case "medio" -> {
                base = -1;
            }
            case "dificil" -> {
                base = -2;
            }
            case "muito dificil" -> {
                base = -3;
            }
            default -> throw new CategoriaPericiaInvalidaException(categoria);
        }
        if (mod - base < 2) {
            return 2^(mod-base);
        }
        return (mod-base-1)*4;
    }
}
