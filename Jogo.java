/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rpg.com;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sferreira
 */
public class Jogo {
    
    private String nome, tipo, interagir, jogadorMovimento, resposta;
    private int sorteio, linha, coluna, indice =1, adversario, vidaEqA, vidaEqB, contaPEqA=0, contaPEqB=0;
    private boolean estaVazio;
    private double vitima;
    Scanner in = new Scanner(System.in);
    Random random = new Random();
    Mapa mapa = new Mapa();
        
    ArrayList<Personagem> tPersonas = new ArrayList<Personagem>();
    ArrayList<Elemento> personagensNoJogo = new ArrayList<Elemento>();
    
    Equipe equipeA = new Equipe("equipeA");
    Equipe equipeB = new Equipe("equipeB");
    
    Jogo(){
        Random random = new Random();		

        tPersonas.add(new Guerreiro("Arthur", random.nextInt(15), random.nextInt(15)));
        tPersonas.add(new Arqueiro("Legolas", random.nextInt(15), random.nextInt(15)));
        tPersonas.add(new Mago("Nimue", random.nextInt(15), random.nextInt(15)));
        tPersonas.add(new Mago("Morgana", random.nextInt(15), random.nextInt(15)));
        
        for(Personagem e: tPersonas){
            
            GerenciaGame.elementosList.add(e);
        }
	
        
	for(int i = 0; i< 10; i++){
		Elemento e = new Elemento(random.nextInt(15), random.nextInt(15));
		personagensNoJogo.add(e);
	}
        
    }
    
    public void inciaJogo(){
        
        
        System.out.println("Inciando o jogo.");
        System.out.println("Os jogadores existentes são:");
        imprimirListaPersonagem(tPersonas);
        
        for(int i=1; i<=2; i++){
            
            System.out.println("Jogador " + i + ", deseja gerar novos jogadores? Sim ou Não");
            interagir = in.nextLine();

            if(interagir.equalsIgnoreCase("Sim")){
                criaPersonagem();
            }
            
            imprimirListaPersonagem(tPersonas);
            
            if( i == 1){
                equipeA.selecionaPersonagens(tPersonas);
                contaPEqA ++;}
            else{
                equipeB.selecionaPersonagens(tPersonas);
                contaPEqB++;}
        }
        
        System.out.println("Imprimindo todos os personagens");
        imprimirListaPersonagem(tPersonas);
        
        System.out.println("Imprimindo personagens da equipeA");
        imprimirListaPersonagem(equipeA.equipe);
        personagensNoJogo.add(equipeA.equipe.get(0));
        
        System.out.println("Imprimindo personagens da equipeB");
        imprimirListaPersonagem(equipeB.equipe);
        personagensNoJogo.add(equipeB.equipe.get(0));
       
        System.out.println("Imprimindo personagens presentes no jogo");
        mapa.imprimirMapa(personagensNoJogo);
        
        while ((contaPEqA<2)&&(contaPEqB<2))
        {
            System.out.println("No jogo só é permitido andar em vertical e horizontal");
            System.out.println("Jogador 1 você está na posição ["+ equipeA.equipe.get(0).posX + ","+ equipeA.equipe.get(0).posY + "]  deseja locomover o personagem? Sim ou Não.");
            resposta = in.nextLine();

            if (resposta.equalsIgnoreCase("Sim")){
                realizandoMovimento(equipeA.equipe.get(0));
            }

            //Calculando alcance de ataque
            vidaEqA = calculaAlcance(equipeA.equipe.get(0), equipeB.equipe.get(0), personagensNoJogo);

            System.out.println("Jogador 2 você está na posição ["+ equipeB.equipe.get(0).posX + ","+ equipeB.equipe.get(0).posY + "]  deseja locomover o personagem? Sim ou Não.");
            resposta = in.nextLine();


            if (resposta.equalsIgnoreCase("Sim")){
                realizandoMovimento(equipeA.equipe.get(0));
            }else{
                //Calculando alcance de ataque
                vidaEqB = calculaAlcance(equipeA.equipe.get(0), equipeB.equipe.get(0), personagensNoJogo);
            }
            if((vidaEqA==0)&&(contaPEqA<2)){
                personagensNoJogo.remove(equipeA.equipe.get(0));
                equipeA.equipe.clear();
                equipeA.selecionaPersonagens(tPersonas);
                personagensNoJogo.add(equipeA.equipe.get(0));
                mapa.imprimirMapa(personagensNoJogo);
                contaPEqA++;
            }

            if((vidaEqB==0)&&(contaPEqB<2)){
                personagensNoJogo.remove(equipeB.equipe.get(0));
                equipeB.equipe.clear();
                equipeB.selecionaPersonagens(tPersonas);
                personagensNoJogo.add(equipeB.equipe.get(0));
                mapa.imprimirMapa(personagensNoJogo);
                contaPEqB++;
            }
        }
        
        if((contaPEqA>2)||(contaPEqB>2)){
            
            System.out.println("Fim do jogo!");
            
            
            if(vidaEqA>0){
                System.out.print("O campeão foi a equipe A");
            }else{
                if(vidaEqB>0){
                    System.out.print("O campeão foi a equipe A");
                }
            
            }
        }
    }
    
   
    
    public void criaPersonagem(){
        
        int contadorCP=2;
        String resposta;
        
        System.out.println("ATENÇÃO: só é permitido criar 2 personagens!!");
        
        while(contadorCP>0){
            System.out.println("Qual personagem deseja: Mago, Guerreiro, Arqueiro?");
            tipo = in.nextLine();

            System.out.println("Qual o nome do personagem?");
            nome = in.nextLine();

            if(tipo.equalsIgnoreCase("Guerreiro"))
                
                tPersonas.add(new Guerreiro(nome, random.nextInt(15), random.nextInt(15)));
            else
            {
                if( tipo.equalsIgnoreCase("Mago"))
                     tPersonas.add(new Mago(nome, random.nextInt(15), random.nextInt(15)));
                    else
                {
                    if(tipo.equalsIgnoreCase("Arqueiro"))
                         tPersonas.add(new Arqueiro(nome, random.nextInt(15), random.nextInt(15)));
                }
            }
            System.out.print("Deseja criar novamente?");
            resposta = in.nextLine();
            if(resposta.equalsIgnoreCase("Não"))
                break;
            contadorCP --;
        }

    }
    public void imprimirListaPersonagem(ArrayList<Personagem> listaDeJogadores){
        
        int indice = 1;
        for(Personagem p: listaDeJogadores){
            
            System.out.println(indice + "-Personagem ");
            p.imprimirPersonagem();
            indice++;
        }
    }
    
    public void realizandoMovimento(Personagem personagemNoJogo){
        
        boolean mover=false;
        
        while (mover == false){
            
            do{
            
            System.out.println("Informe a posição em linha e coluna respectivamente:");
            System.out.print("Linha:");
            linha = in.nextInt();
            System.out.print("\nColuna:");
            coluna = in.nextInt();
            
            try
            {
                if(!(mapa.estaVazio(linha, coluna)))
                    throw new LocacaoNaoPermitida(linha, coluna);
            }catch(LocacaoNaoPermitida ex){
                Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }while(!(mapa.estaVazio(linha, coluna)));

            mover = locomocao(personagemNoJogo, linha, coluna);
            
        }
        
    }
    
    public boolean locomocao(Personagem alocomover,  int linha, int coluna){
        
        int resultadoSumLinha,resultadoSumColuna;
        boolean movimento = true;
        
        if(alocomover.posY> coluna){
            resultadoSumColuna = alocomover.posY - coluna;
        }else{
            resultadoSumColuna = coluna - alocomover.posY;
        }
             
        if(alocomover.posX> linha){
            resultadoSumLinha = alocomover.posX - linha;
        }else{
            resultadoSumLinha = linha - alocomover.posX;
        }
        
        try {
                if(((alocomover instanceof Arqueiro)&&((resultadoSumColuna <= 9)||(resultadoSumLinha <= 9)))||((alocomover instanceof Guerreiro)&&((resultadoSumLinha <= 6)||((resultadoSumColuna <= 6))))||((alocomover instanceof Mago)&&((resultadoSumLinha <= 5)||((resultadoSumColuna <= 5)))))
                {
                    alocomover.posX = linha;
                    alocomover.posY = coluna;
                    mapa.imprimirMapa(personagensNoJogo);
                }else{
                    throw new ErroLocomocao(linha,coluna);
                }
                
        } catch (ErroLocomocao ex) {
                movimento = false;
        }
        return movimento;
    }
    
    public int calculaAlcance(Personagem atacante, Personagem vitima, ArrayList<Elemento> equipe){
        
                
        String resposta;
        boolean manobra;
        int habilidade;
        int vidaDaVitima=0;
            
        if((vitima.posY <= (atacante.posY+atacante.alcanceDeAtaque))||(vitima.posX <= (atacante.posX+atacante.alcanceDeAtaque))||(vitima.posY <= (atacante.posY-atacante.alcanceDeAtaque))||(vitima.posX <= (atacante.posX-atacante.alcanceDeAtaque))){
                 
            System.out.println("Você gostaria de atacar esse adversário que se encontra na posição ["+vitima.posX+","+vitima.posY+"]?");
            resposta = in.nextLine();
                 
            if(resposta.equalsIgnoreCase("Sim")){
                
                if(atacante instanceof Guerreiro){
                    
                    habilidade = ((Guerreiro) atacante).imprimirMenu(equipe);
                        
                    manobra = atacante.minhasMagias(habilidade);

                    if((manobra == true)&&(habilidade == 1)){

                        ((Guerreiro)atacante).Adrenalina();
                        vidaDaVitima = vitima.atualizaVida(atacante, vitima);

                    }else{
                        if((manobra == true)&&(habilidade == 2)){
                            ((Guerreiro)atacante).Heroismo(atacante, vitima);
                            vidaDaVitima = vitima.atualizaVida(atacante, vitima);
                        }else{
                            try {
                                throw new ManobraNaoPermitida();

                               } catch (ManobraNaoPermitida ex) {
                                   ex.getMessage();
                               }
                        }
                    }
                }else{
                    if(atacante instanceof Arqueiro){
                        
                        habilidade = ((Arqueiro) atacante).imprimirMenu(equipe);
                        
                        manobra = atacante.minhasMagias(habilidade);

                        if((manobra == true)&&(habilidade == 1)){

                            ((Arqueiro)atacante).TiroCerteiro();
                            vidaDaVitima = vitima.atualizaVida(atacante, vitima);
                        }else{
                            if((manobra == true)&&(habilidade == 2)){
                                ((Arqueiro)atacante).Camuflagem(atacante, vitima);
                                vidaDaVitima = vitima.atualizaVida(atacante, vitima);
                            }else{
                                try {
                                    throw new ManobraNaoPermitida();

                                   } catch (ManobraNaoPermitida ex) {
                                       ex.getMessage();
                                   }
                            }
                        }
                    }else{
                        if(atacante instanceof Mago){
                            
                            habilidade = ((Mago) atacante).imprimirMenu(equipe);
                        
                            manobra = atacante.minhasMagias(habilidade);

                            if((manobra == true)&&(habilidade == 1)){

                                ((Mago)atacante).Cura();
                                vidaDaVitima = vitima.atualizaVida(atacante, vitima);
                            }else{
                                if((manobra == true)&&(habilidade == 2)){
                                    ((Mago)atacante).Conflagrar();
                                    vidaDaVitima = vitima.atualizaVida(atacante, vitima);
                                }else{
                                    try {
                                        throw new ManobraNaoPermitida();

                                       } catch (ManobraNaoPermitida ex) {
                                           ex.getMessage();
                                       }
                                }
                            }
                            
                        }
                    }
                }
                            
            }
        }else{
            System.out.println("O alcance para ataque não é permitido");
        }
        return vidaDaVitima;
    }
    
   
}
