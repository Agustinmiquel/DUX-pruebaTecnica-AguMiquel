package pruebaDux.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebaDux.demo.Model.Equipo;
import pruebaDux.demo.Repository.EquipoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> getAllEquipos(){
        return equipoRepository.findAll();
    }

    public Optional<Equipo> getEquipoById(long id){
        return equipoRepository.findById(id);
    }

    //Obtener un equipo por nombre
    public List<Equipo> getEquipoByNombre(String nombre){
        return equipoRepository.findByNombre(nombre);
    }

    //Eliminar un equipo
    public Equipo deleteEquipo(long id){
        Equipo equipo = equipoRepository.findById(id).orElse(null);
        if(equipo != null){
            equipoRepository.delete(equipo);
        }
        return equipo;
    }

    //Crear un equipo
    public Equipo createEquipo(Equipo equipo){
        return equipoRepository.save(equipo);
    }

    //Actualizar un equipo
    public Equipo updateEquipo(long id, Equipo equipo){
        return equipoRepository.save(equipo);
    }
}
