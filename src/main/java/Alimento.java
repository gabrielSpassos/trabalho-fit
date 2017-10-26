

public class Alimento {

    private String nome;
    private double calorias;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public String calculaCaloriasbyGramas (double gramasInformadas){
        String retorno;

        if(gramasInformadas<0){
            retorno = "Não pode ser informado valor negativo de peso";
        }else{
            double calculo = (gramasInformadas * getCalorias())/100;
            retorno = String.valueOf(calculo);
        }

        return retorno;
    }



}
