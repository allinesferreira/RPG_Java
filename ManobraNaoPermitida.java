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
public class ManobraNaoPermitida extends Exception{
    
     
    public ManobraNaoPermitida() {
        super();
    }

    @Override
    public String toString() {
        return "A manobra não é permitida, a probabilidade gerada não foi suficiente para gerar a defesa.";
    }
    
 
    
}
