
package br.rpg.com;


import java.util.*;

public class Equipe{
    
    Scanner in = new Scanner (System.in);
    String desejado, name;
    ArrayList<Personagem> equipe;
    ArrayList<Personagem> escolhidos= new ArrayList<Personagem>();
    
    Equipe(String name){
       
        this.name = name;
        equipe = new ArrayList<Personagem>();
    }
    Equipe(){
              
        equipe = new ArrayList<Personagem>();
    }

    public String getName() {
        return name;
    }
       
    
    public void selecionaPersonagens(ArrayList<Personagem> persona){
        
        System.out.println("Informe o nome do personagem desejado para o jogo.");
        desejado = in.nextLine();
        
        for(Personagem p: persona){
            
            if(p.name.equalsIgnoreCase(desejado)){
                
                equipe.add(p);
                escolhidos.add(p);
            }
        }
        removePersonagemDaLista(persona,escolhidos);
        
    }
    
    public void removePersonagemDaLista(ArrayList <Personagem> listaAremover, ArrayList<Personagem> listaElementosEscolhidos){
        
        for(Personagem p: listaElementosEscolhidos){
            listaAremover.remove(p);
        }
    }

       
  
    
}
    
    