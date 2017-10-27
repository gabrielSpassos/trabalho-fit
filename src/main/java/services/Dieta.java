package services;

import model.Usuario;


public class Dieta {

    public double caloriasLimite;
    Usuario user = new Usuario();

    public Dieta (Usuario u){
        this.user = u;
    }

    public double decideCaloriasLimites(){
        Calculadora calculadora = new Calculadora();
        Double imc = calculadora.calculaIMC(this.user.getPeso(),this.user.getAltura());
        if(imc<18.4){ //abaixo do peso ideal
            return this.caloriasLimite = 200;
        }else if(imc>=18.5 && imc<25){ // está no peso ideal
            return this.caloriasLimite = 100;
        }else{
            return this.caloriasLimite = 50; //está obeso
        }
    }


}
