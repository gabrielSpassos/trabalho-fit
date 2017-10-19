public class Dieta {

    Usuario user;

    public Dieta(Usuario user){
        this.user = user;
    }

    public String decideDieta(){
        if(user.calculaIMC() > 29){
            return "Gordo!";
        }else {
            return "Frango!";
        }
    }
}
