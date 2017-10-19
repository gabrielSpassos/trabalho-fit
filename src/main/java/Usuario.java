public class Usuario {

    private String nome;
    private double altura;
    private double peso;

    public double calculaIMC (){
        return ((this.getPeso()) / (this.getAltura() * this.getAltura()));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
