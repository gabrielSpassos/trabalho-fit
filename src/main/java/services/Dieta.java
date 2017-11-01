package services;

import model.Usuario;


public class Dieta {

    private double caloriasLimite;
    private double totalCaloriasLimiteDiarias;
    Usuario user = new Usuario();


    public Dieta (Usuario u){
        this.user = u;
    }

    public double decideCaloriasLimites(){
        Calculadora calculadora = new Calculadora();
        Double imc = calculadora.calculaIMC(this.user.getPeso(),this.user.getAltura());
        if(imc<18.4){ //abaixo do peso ideal
            return this.caloriasLimite = 400;
        }else if(imc>=18.5 && imc<25){ // está no peso ideal
            return this.caloriasLimite = 250;
        }else{
            return this.caloriasLimite = 100; //está obeso
        }
    }

    public double decideTotalCaloriasLimiteDiarias(){
        Calculadora calculadora = new Calculadora();
        Double imc = calculadora.calculaIMC(this.user.getPeso(),this.user.getAltura());
        if(imc<18.4){ //abaixo do peso ideal
            return this.totalCaloriasLimiteDiarias = 4000;
        }else if(imc>=18.5 && imc<25){ // está no peso ideal
            return this.totalCaloriasLimiteDiarias = 2000;
        }else{
            return this.totalCaloriasLimiteDiarias = 1250; //está obeso
        }
    }


    public double getTotalCaloriasLimiteDiarias() {
        return totalCaloriasLimiteDiarias;
    }

    public void setTotalCaloriasLimiteDiarias(double totalCaloriasLimiteDiarias) {
        this.totalCaloriasLimiteDiarias = totalCaloriasLimiteDiarias;
    }
}
