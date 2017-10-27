package model;

public class MinhaException extends RuntimeException {
    private String msg;

    public MinhaException(String msg){
        super(msg);
        this.msg = msg;
    }

    public String getMessage(){
        return msg;
    }


}
