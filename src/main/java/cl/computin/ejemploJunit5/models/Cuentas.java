package cl.computin.ejemploJunit5.models;

import cl.computin.ejemploJunit5.models.exception.DineroInsuficienteExeption;

import java.math.BigDecimal;

public class Cuentas {

    private String persona;
    private BigDecimal saldo;


    public Cuentas(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    public void debito(BigDecimal monto){
     BigDecimal nuevoSaldo = this.saldo.subtract(monto);
     if(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
         throw new DineroInsuficienteExeption("Dinero Insuficiente");
     }
     this.saldo = nuevoSaldo;
    }
    public void credito(BigDecimal monto){
        this.saldo = this.saldo.add(monto);
    }

    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof Cuentas)){
            return false;
        }
        Cuentas cuentas = (Cuentas) obj;
        if (this.persona == null || this.saldo == null){
            return false;
        }
        return this.persona.equals(cuentas.getPersona()) && this.saldo.equals(cuentas.getSaldo());
    }
}
