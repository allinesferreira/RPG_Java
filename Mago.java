package br.rpg.com;


import java.util.*;


public class Mago  extends Personagem{

    Random ran = new Random();
    Mapa mapa = new Mapa();
    Scanner in = new Scanner(System.in);
    Equipe eq = new Equipe();
    
	public Mago(String name, int posX, int posY){
		super(posX,posY, name);
                calculaAtributo();
                alcanceDeAtaque = 3;
                locomocao = 5;
                tipo="Mago";
                tipoAtaque = "Cura";
                tipoDefesa = "Conflagrar";
        }
        
        public void calculaAtributo(){
            
            do{
                agilidade = 15 + ran.nextInt(50);
                inteligencia = 30 + ran.nextInt(70);
                vigor = 10 + ran.nextInt(50);
                
            }while((inteligencia < vigor )||(inteligencia < agilidade) || ((vigor+agilidade+inteligencia) >200));
            
        }
        
        public int PontoVida(){
            
            return 25 * (1+ vigor/75)+(inteligencia/75);
        }
       
        public double Dano(){
            
            return 15*(inteligencia/25)*(1+(0.1+(0.7 *ran.nextDouble())));
            
        }
        
        public int Cura(){
            
            return PontoVida();
        }
        
        public double Conflagrar(){
            
            return Dano()*1.55;
        }
        
  
        public void imprimirSituacaoAtual( ArrayList<Elemento> equipe){
                System.out.println("Tipo: Mago");
                System.out.println("Nome: " +name);
                System.out.println("Equipe: "+ eq.getName());
                System.out.println("Ponto de Vida: "+PontoVida());
                System.out.print("Magias de ataque: Cura" );
                System.out.print("Magias de defesa: Conflagrar" );
            }
        
        public boolean minhasMagias(int magia){
            
            boolean manobra=false;
            double prob;
            
            switch (magia) {
                case 1:
                    prob = 1 + ran.nextInt(100);
                    if(prob < 6){
                    manobra=true;    
                    System.out.println("Voce curou todos seus pontos de vida!");
                    }
                    break;
                case 2:
                    prob = 1 + ran.nextInt(100);
                    if(prob < 11){
                    manobra=true;    
                    System.out.println("Voce conjurou a habilidade conflagrar com sucesso!");
                    }
                    break;
        
        }
            return manobra;
        }   
        

}