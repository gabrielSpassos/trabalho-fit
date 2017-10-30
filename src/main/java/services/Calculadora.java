package services;

public class Calculadora {

    public double calculaIMC (double peso, double altura){
        return (peso / (altura * altura));
    }

    public String calculaCaloriasbyGramas (double gramasInformadas, double calorias){
        String retorno;

        if(gramasInformadas<0){
            retorno = "Não pode ser informado valor negativo de peso";
        }else{
            double calculo = (gramasInformadas * calorias)/100;
            retorno = String.valueOf(calculo);
        }

        return retorno;
    }

    public double calculaCaloriasRestantes(double totalCaloriasLimiteDiarias, double caloriasComidas){
        return totalCaloriasLimiteDiarias = totalCaloriasLimiteDiarias - caloriasComidas;
    }

}
