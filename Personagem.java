package br.rpg.com;

import java.util.*;

public abstract class Personagem extends Elemento {
        protected String name,tipo, tipoAtaque, tipoDefesa;
        protected int vigor, inteligencia, agilidade;
        protected int alcanceDeAtaque;
        protected int vida, locomocao;
	Random ran = new Random();
        Scanner in = new Scanner(System.in);
        Equipe eq = new Equipe();
        
	
        public Personagem(int posX, int posY, String name) {
		super(posX, posY);
		this.name = name;
	}
        
        public abstract void calculaAtributo();
                
        public abstract int PontoVida();
                
        public abstract double Dano();
        
        public abstract boolean minhasMagias(int magia);
        
        public int atualizaVida(Personagem defensor,Personagem atacante){
            int atualizaVida=0;
            if(atacante instanceof Arqueiro ){
                if(defensor instanceof Guerreiro){
                    atualizaVida = defensor.PontoVida()-(int)atacante.Dano();
                   }
                    else{
                        if(defensor instanceof Mago){
                            atualizaVida = defensor.PontoVida()-(int)atacante.Dano();
                           }
                        else{
                            if(defensor instanceof Arqueiro){
                                atualizaVida = defensor.PontoVida()-(int)atacante.Dano();
                            }
                            else{
                                if(atacante instanceof Guerreiro ){
                                    if(defensor instanceof Guerreiro){
                                        atualizaVida = defensor.PontoVida()-(int)atacante.Dano();
                                       }
                                    else{
                                        if(defensor instanceof Mago){
                                            atualizaVida = defensor.PontoVida()-(int)atacante.Dano();
                                           }
                                        else{
                                            if(defensor instanceof Arqueiro){
                                                atualizaVida = defensor.PontoVida()-(int)atacante.Dano();
                                            }
                                            else{
                                                if(atacante instanceof Mago ){
                                                    if(defensor instanceof Guerreiro){
                                                        atualizaVida = defensor.PontoVida()-(int)atacante.Dano();
                                                       }
                                                    else{
                                                        if(defensor instanceof Mago){
                                                            atualizaVida = defensor.PontoVida()-(int)atacante.Dano();
                                                           }
                                                        else{
                                                            if(defensor instanceof Arqueiro){
                                                                atualizaVida = defensor.PontoVida()-(int)atacante.Dano();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }    
                }
            }
        return atualizaVida; 
        }
        
        public void imprimirPersonagem(){
            
	    	System.out.println("    Nome: " +name);
	    	System.out.println("    Vigor: " +vigor);
	    	System.out.println("    Agilidade: " +agilidade);
	    	System.out.println("    Inteligencia: " +inteligencia);
	    	
	}
        
        public int imprimirMenu( ArrayList<Elemento> equipe){
            
            int magia;
            System.out.println("Tipo: " + tipo);
            System.out.println("Nome: " +name);
            System.out.println("Equipe: "+eq.getName());
            System.out.println("Ponto de Vida: "+PontoVida());
            System.out.println("Escolha entre as seguintes magias: ");
            System.out.println("1 - " + tipoAtaque );
            System.out.println("2 - " + tipoDefesa);
            Scanner in = new Scanner(System.in);
            magia=in.nextInt();
            
            return magia;
        }
        
}
