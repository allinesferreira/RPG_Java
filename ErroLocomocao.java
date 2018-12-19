/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rpg.com;

/**
 *
 * @author sferreira
 */
public class ErroLocomocao extends Exception{
    
    private int posicaoDesejadaParaX;
    private int posicaoDesejadaParaY;

    public ErroLocomocao(int posicaoDesejadaParaX, int posicaoDesejadaParaY) {
        super();
        this.posicaoDesejadaParaX = posicaoDesejadaParaX;
        this.posicaoDesejadaParaY = posicaoDesejadaParaY;
    }

    @Override
    public String toString() {
        return "ErroLocomocao{" + "posicao " + posicaoDesejadaParaX + ", posicao " + posicaoDesejadaParaY + " não é permitida para esse personagem}\n Informe outra posição.";
    }
}
