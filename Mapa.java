package br.rpg.com;

import java.util.ArrayList;

public class Mapa {
	public static final int size = 15;
	String [][] mapa = new String[size][size]; 
       
	public void imprimirMapa(ArrayList<Elemento> elementosList){				
		limparMapa();
		posicionarElemento(elementosList);
		imprimirCabecalho();

		for(int i = 0; i< size; i++){				
			for(int j = 0; j<size; j++){
				if(j==0){
					System.out.printf("%02d", i);
					System.out.print("\t");
				}
				if(mapa[i][j] == null || mapa[i][j].isEmpty())
					System.out.print("    "+"\t");
				else
					System.out.print(mapa[i][j] + "\t");
			}
			System.out.println("");
		}
	}
	
	public boolean estaVazio(int x, int y){
	    if (mapa[x][y] == null || mapa[x][y].isEmpty())
		return true;
	    else
		return false;
	}

	private void imprimirCabecalho(){
		System.out.print("\t");
		for(int i = 0; i<size; i++){			
			System.out.printf(" %02d", i);
			System.out.print("\t");
		}
		System.out.println();
	}

	private void limparMapa(){
		for(int i = 0; i< size; i++){				
			for(int j = 0; j<size; j++){
				mapa[i][j] = "";
			}			
		}		
	}

	private boolean posicionarElemento(ArrayList<Elemento> elementoList){
            
            boolean encontrouExcecao= false;
            
		for(Elemento e: elementoList){
                    try
                    {
			if(e instanceof Arqueiro){
				if(estaVazio(e.posX, e.posY))						
					mapa[e.posX][e.posY] = "a"+ ((Arqueiro) e).name.substring(0, 3);
				else
					System.out.println("Posicao ["+e.posX+"] ["+ e.posY+"] ja esta ocupada! ");
			}
			else if(e instanceof Guerreiro){
				if(estaVazio(e.posX, e.posY))
					mapa[e.posX][e.posY] = "g"+ ((Guerreiro) e).name.substring(0, 3);
				else
					System.out.println("Posicao ["+e.posX+"] ["+ e.posY+"] ja esta ocupada! ");
			}
			else if(e instanceof Mago){
				if(estaVazio(e.posX, e.posY))
					mapa[e.posX][e.posY] = "m"+ ((Mago) e).name.substring(0, 3);
				else
					System.out.println("Posicao ["+e.posX+"] ["+ e.posY+"] ja esta ocupada! ");
			}
			else
                                mapa[e.posX][e.posY] = "####";
                    }catch(ArrayIndexOutOfBoundsException exception)
                    {
                        System.out.println("As posições informadas não existem no jogo");
                        encontrouExcecao = true;
                    }
		} 
                
                return encontrouExcecao;
	}
}
