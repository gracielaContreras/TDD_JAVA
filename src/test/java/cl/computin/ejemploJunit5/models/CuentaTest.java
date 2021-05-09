package cl.computin.ejemploJunit5.models;

import cl.computin.ejemploJunit5.models.exception.DineroInsuficienteExeption;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    void saldoCuentaTest() {

      Cuenta cuenta = new Cuenta("Eduardo", new BigDecimal("1000.12345"));
      assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
      assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
      assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void referenciaTest() {
      Cuenta cuenta = new Cuenta("John Doe", new BigDecimal("8900.9997"));
      Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal("8900.9997"));

      assertEquals(cuenta,cuenta2);

    }

    @Test
    void debitoCuentaTest() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }
    @Test
    void creditoCuentaTest() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void dineroInsuficienteExceptionTest() {
        Cuenta cuenta = new Cuenta("John Doe", new BigDecimal("1000.12345"));
        Exception exception = assertThrows(DineroInsuficienteExeption.class, ()->{
            cuenta.debito(new BigDecimal(1500));
        });

        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado,actual);
    }

    @Test
    void transferirDineroCuentaTest(){
        Cuenta cuenta1 = new Cuenta("Graciela Contreras", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Eduardo Escobar", new BigDecimal("1500.8989"));

        Banco banco =  new Banco();
        banco.setNombre("Banco del estado");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
        assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
        assertEquals("3000", cuenta1.getSaldo().toPlainString());
    }
    @Test
    void relacionBancoCuentasTest(){
        Cuenta cuenta1 = new Cuenta("Graciela", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Eduardo", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);

        banco.setNombre("Banco del estado");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));

//        assertAll: agrupa los assert
        assertAll(() -> assertEquals("1000.8989", cuenta2.getSaldo().toPlainString()),
                () -> assertEquals("3000", cuenta1.getSaldo().toPlainString()),
                () -> assertEquals("3000", cuenta1.getSaldo().toPlainString()),
                () -> assertEquals(2, banco.getCuentas().size()),
                () -> assertEquals("Graciela", banco.getCuentas().stream()
                        .filter(c -> c.getPersona().equals("Graciela"))
                        .findFirst()
                        .get().getPersona()),
                () -> assertTrue(banco.getCuentas().stream()
                        .anyMatch(c -> c.getPersona().equals("Eduardo"))));


    }
}