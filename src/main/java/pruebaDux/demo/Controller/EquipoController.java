package pruebaDux.demo.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pruebaDux.demo.Model.Equipo;
import pruebaDux.demo.Service.EquipoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipos")
@Tag(name = "Equipos Controller", description = "API de gestión de equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    @Operation(summary = "Obtener todos los equipos")
    public ResponseEntity<List<Equipo>> getAllEquipos(){
        return new ResponseEntity<>(equipoService.getAllEquipos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un equipo por su ID")
    public ResponseEntity<?> getEquipoById(@PathVariable long id){
        Optional<Equipo> equipo = equipoService.getEquipoById(id);
        if(equipo.isPresent()){
            return new ResponseEntity<>(equipo.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Equipo no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Obtener un equipo por su nombre")
    public ResponseEntity<List<Equipo>> getEquipoByNombre(@PathVariable String nombre){
        return new ResponseEntity<>(equipoService.getEquipoByNombre(nombre), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear un equipo")
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo){
        return new ResponseEntity<>(equipoService.createEquipo(equipo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un equipo por su ID")
    public ResponseEntity<?> deleteEquipo(@PathVariable long id){
        Optional<Equipo> equipo = equipoService.getEquipoById(id);
        if(equipo.isPresent()){
            equipoService.deleteEquipo(id);
            return new ResponseEntity<>("Equipo eliminado", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Equipo no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un equipo por su ID")
    public ResponseEntity<?> updateEquipo(@PathVariable long id, @RequestBody Equipo equipo){
        Optional<Equipo> equipoSeleccionado = equipoService.getEquipoById(id);
        if(equipoSeleccionado.isPresent()){
            equipo.setId(id);
            return new ResponseEntity<>(equipoService.updateEquipo(id, equipo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Equipo no encontrado", HttpStatus.NOT_FOUND);
        }
    }

}
