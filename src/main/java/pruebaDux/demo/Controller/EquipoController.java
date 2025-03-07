package pruebaDux.demo.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pruebaDux.demo.Model.Equipo;
import pruebaDux.demo.Service.EquipoService;

import java.util.List;
import java.util.Optional;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/equipos")
@Tag(name = "Equipos Controller", description = "API de gestión de equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    @Operation(summary = "Obtener todos los equipos")
    @ApiResponse(responseCode = "200", description = "Lista de equipos encontrada", content = @Content(schema = @Schema(implementation = Equipo.class)))
    public ResponseEntity<List<Equipo>> getAllEquipos(){
        return new ResponseEntity<>(equipoService.getAllEquipos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un equipo por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipo encontrado", content = @Content(schema = @Schema(implementation = Equipo.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
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
    @ApiResponse(responseCode = "200", description = "Lista de equipos encontrada", content = @Content(schema = @Schema(implementation = Equipo.class)))
    public ResponseEntity<List<Equipo>> getEquipoByNombre(@PathVariable String nombre){
        return new ResponseEntity<>(equipoService.getEquipoByNombre(nombre), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear un equipo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Equipo creado exitosamente", content = @Content(schema = @Schema(implementation = Equipo.class))),
            @ApiResponse(responseCode = "400", description = "Datos del equipo inválidos", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo){
        return new ResponseEntity<>(equipoService.createEquipo(equipo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un equipo por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Equipo eliminado", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipo actualizado", content = @Content(schema = @Schema(implementation = Equipo.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
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
