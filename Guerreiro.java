package br.rpg.com;


import static java.lang.Math.random;
import java.util.*;

public class Guerreiro extends Personagem{

        Random ran = new Random();
        Scanner in = new Scanner(System.in);
        Mapa mapa = new Mapa();
        boolean heroismoA,adrenalinaA;
	
        public Guerreiro(String name, int posX, int posY){
		super(posX,posY, name);
                calculaAtributo();
                alcanceDeAtaque = 1;
                locomocao = 6;
	        tipo="Guerreiro";
                tipoAtaque = "Adrenalina";
                tipoDefesa = "Heroismo";
        }
        
        
        public void calculaAtributo(){
            
            do{
                agilidade = 15 + ran.nextInt(50);
                vigor = 30 + ran.nextInt(70);
                inteligencia = 10 + ran.nextInt(50);
                
            }while((vigor < agilidade)||(vigor < inteligencia) || ((vigor+agilidade+inteligencia) >200));
            
        }
        
        public int PontoVida(){
            
            return 50 * (1+ (vigor/25))+(agilidade/100);
  
      }
        public double Dano(){
            
            return 10*(vigor/100)*(1+(0.1+(0.7 *ran.nextDouble())));
            
        }
        
        public double Adrenalina(){
            
            return Dano()*1.4;
        }
        
        public double Heroismo(Personagem defensor,Personagem atacante){
            
            double valorHeroismo=0;
            
           if(defensor instanceof Guerreiro ){
                if(atacante instanceof Mago){
                    valorHeroismo = (double)defensor.PontoVida()-atacante.Dano()/2;
                }
                else{
                    if(atacante instanceof Arqueiro){
                        valorHeroismo = (double)defensor.PontoVida()-atacante.Dano()/2;
                    }
                    else{
                        if(atacante instanceof Guerreiro){
                            valorHeroismo = (double)defensor.PontoVida()-atacante.Dano()/2;   
                        }
                    }  
                }
           }                
         return valorHeroismo;
        }
        
        public boolean minhasMagias(int magia){
                    
            boolean manobra=false;
            double prob;
            
            switch (magia) {
                case 1:
                    prob = 1 + ran.nextInt(100);
                    if(prob < 9){
                    manobra=true;    
                    System.out.println("Voce esta sob efeito da Adrenalina");
                    }
                    break;
                case 2:
                    prob = 1 + ran.nextInt(100);
                    if(prob < 7){
                    manobra=true;    
                    System.out.println("Voce esta sob efeito do Heroismo");
                    }
                    break;
        
        }
            return manobra;
        }   
       
}


