
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Empleado;
import persistencia.exceptions.NonexistentEntityException;


public class ControladoraPersistencia {
    
    
    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();
    
    
    //creo empleado
    public void crearEmpleado(Empleado empleado){
        empleadoJpa.create(empleado);
    }
    //eliminar
    public void borrarEmpleado(int id){
        try {
            empleadoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //busqueda
    
    public Empleado buscarEmpleado(int id){
       return empleadoJpa.findEmpleado(id);
    }
    
    
    //listar 
    public List<Empleado>buscarEmpleados(){
        return empleadoJpa.findEmpleadoEntities();
    }
    
    
    
    //modificar
    public void modificarEmpleado(Empleado empleado){
        try {
            empleadoJpa.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
