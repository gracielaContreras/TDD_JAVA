package cl.computin.ejemploJunit5.models;

import cl.computin.ejemploJunit5.models.exception.DineroInsuficienteExeption;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentasTest {

    @Test
    void saldoCuentaTest() {

      Cuentas cuenta = new Cuentas("Eduardo", new BigDecimal("1000.12345"));
      assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
      assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
      assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void referenciaTest() {
      Cuentas cuenta = new Cuentas("John Doe", new BigDecimal("8900.9997"));
      Cuentas cuenta2 = new Cuentas("John Doe", new BigDecimal("8900.9997"));

      assertEquals(cuenta,cuenta2);

    }

    @Test
    void debitoCuentaTest() {
        Cuentas cuenta = new Cuentas("Andres", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }
    @Test
    void creditoCuentaTest() {
        Cuentas cuenta = new Cuentas("Andres", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void dineroInsuficienteExceptionTest() {
        Cuentas cuenta = new Cuentas("John Doe", new BigDecimal("1000.12345"));
        Exception exception = assertThrows(DineroInsuficienteExeption.class, ()->{
            cuenta.debito(new BigDecimal(1500));
        });

        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado,actual);
    }
}