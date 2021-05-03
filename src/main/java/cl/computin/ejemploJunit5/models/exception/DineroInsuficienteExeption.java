package cl.computin.ejemploJunit5.models.exception;

public class DineroInsuficienteExeption  extends RuntimeException{
    public DineroInsuficienteExeption(String message) {
        super(message);
    }
}
