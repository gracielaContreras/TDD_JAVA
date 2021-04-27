package cl.computin.ejemploMockito.repositories;

import cl.computin.ejemploMockito.models.Examen;

import java.util.List;

public interface ExamenRepository {

    List<Examen> findAll();
}
