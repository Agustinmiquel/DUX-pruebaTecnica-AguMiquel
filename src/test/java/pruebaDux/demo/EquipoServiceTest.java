package pruebaDux.demo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pruebaDux.demo.Model.Equipo;
import pruebaDux.demo.Repository.EquipoRepository;
import pruebaDux.demo.Service.EquipoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private EquipoService equipoService;

    private Equipo equipo;
    private Equipo equipo2;

    @BeforeEach
    void setUp(){
        equipo = new Equipo(1L, "Real Madrid", "La liga", "España");
        equipo2 = new Equipo(2L, "Barcelona", "La liga", "España");
    }

    @Test
    void obtenerTodoslosEquipos(){
        when(equipoRepository.findAll()).thenReturn(Arrays.asList(equipo, equipo2));

        List<Equipo> equipos = equipoService.getAllEquipos();

        assertEquals(2, equipos.size());
        verify(equipoRepository, times(1)).findAll();
    }

    @Test
    void obtenerEquipoPorId(){
        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));

        Equipo equipo = equipoService.getEquipoById(1L).get();

        assertNotNull(equipo);
        assertEquals("Real Madrid", equipo.getNombre());
        verify(equipoRepository, times(1)).findById(1L);
    }

    @Test
    void ObtenerEquipoPorNombre(){
        when(equipoRepository.findByNombre("Real Madrid")).thenReturn(Arrays.asList(equipo));

        List<Equipo> equipos = equipoService.getEquipoByNombre("Real Madrid");

        assertEquals(1, equipos.size());
        assertEquals("Real Madrid", equipos.get(0).getNombre());
        verify(equipoRepository, times(1)).findByNombre("Real Madrid");
    }
}
