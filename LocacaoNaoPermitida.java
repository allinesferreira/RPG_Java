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
public class LocacaoNaoPermitida extends Exception{
    
    private int posicaoX;
    private int posicaoY;

    public LocacaoNaoPermitida(int posicaoX, int posicaoY) {
        super();
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    @Override
    public String toString() {
        return "A posição informada não está vazia.";
    }
    
    
    
}
