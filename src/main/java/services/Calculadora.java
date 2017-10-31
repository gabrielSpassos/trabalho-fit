package services;

import model.MinhaException;

public class Calculadora {

    public double calculaIMC (double peso, double altura){
        return (peso / (altura * altura));
    }

    public double calculaCaloriasbyGramas (double gramasInformadas, double calorias){

        if(gramasInformadas<0){
            throw new MinhaException("Não pode ser informado gramas regativas");
        }else{
            double calculo = (gramasInformadas * calorias)/100;
            return calculo;
        }

    }

    public double calculaCaloriasRestantes(double totalCaloriasLimiteDiarias, double caloriasComidas){
        return totalCaloriasLimiteDiarias = totalCaloriasLimiteDiarias - caloriasComidas;
    }

}
