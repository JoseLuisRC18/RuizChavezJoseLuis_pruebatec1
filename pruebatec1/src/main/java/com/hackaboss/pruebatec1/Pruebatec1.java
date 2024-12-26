
package com.hackaboss.pruebatec1;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import logica.Empleado;
import persistencia.ControladoraPersistencia;

public class Pruebatec1 {

    public static void main(String[] args) {

        ControladoraPersistencia controlPersistencia = new ControladoraPersistencia();
        Scanner teclado = new Scanner(System.in);
        
        int op = 0;
        int id = 0;
        
        System.out.println("**Aplicación de Gestión de Empleados**");
        while (true) {
            System.out.println(" 1) Agregar Empleado \n 2) Eliminar Empleado \n 3) Busqueda Empleados \n 4) Modificacion de Empleado \n 5) Lista de Empleados \n 6) Salir");

            System.out.print("Selecciona una opción, ingresa el numero de la opcion: ");
            op = teclado.nextInt();
            teclado.nextLine();

            switch (op) {
                case 1:
                    System.out.println("ingresando nuevo empleado");

                    teclado = new Scanner(System.in);

                    System.out.println("Ingresa el nombre");
                    String nombre = teclado.nextLine();

                    System.out.println("Ingresa el apellido");
                    String apellido = teclado.nextLine();

                    System.out.println("Ingresa el cargo");
                    String cargo = teclado.nextLine();

                    teclado = new Scanner(System.in);
                    System.out.println("Ingresa el salario");
                    Double salario = teclado.nextDouble();

                    teclado = new Scanner(System.in);

                    LocalDate fechaInicio = null;

                    while (fechaInicio == null) {
                        try {
                            System.out.println("Ingresa la fecha de ingreso con este formato AAAA-MM-DD: ");
                            String fechaInicio1 = teclado.nextLine();
                            fechaInicio = LocalDate.parse(fechaInicio1);
                        } catch (DateTimeParseException e) {
                            System.out.println("Error: Formato inválido. Intenta nuevamente usando AAAA-MM-DD.");
                        }
                    }

                    controlPersistencia.crearEmpleado(new Empleado(id, nombre, apellido, cargo, salario, fechaInicio));
                    break;

                case 2:
                    System.out.println("Ingrese el id del empleado que desea eliminar:");
                    int idEliminar = teclado.nextInt();
                    Empleado empleadoCheck = controlPersistencia.buscarEmpleado(idEliminar);
                    if (empleadoCheck != null) {
                        controlPersistencia.borrarEmpleado(idEliminar);
                        System.out.println("Eliminado");

                    } else {
                        System.out.println("El empleado con el ID : " + idEliminar + "  No se encontro  ");
                    }

                    break;

                case 3:
                    System.out.println("Busqueda de empleado por cargo ");
                    System.out.println("Ingresa el cargo");
                    String cargof = teclado.nextLine();
                    List<Empleado> listaEmpleados = controlPersistencia.buscarEmpleados();

                    Empleado empleadoEncontrado = null;
                    for (Empleado empleado : listaEmpleados) {
                        if (empleado.getCargo().equalsIgnoreCase(cargof)) {
                            empleadoEncontrado = empleado;
                            break;
                        }
                    }
                    if (empleadoEncontrado != null) {

                        System.out.println("Empleado encontrado: " + empleadoEncontrado);

                    } else {
                        System.out.println("  No se encontro  " + cargof);
                    }

                    break;

                case 4:
                    System.out.println("actualizar información de un empleado:");
                    System.out.println("Ingresa el Id que tiene registrado el Empleado");
                    int idModificar = teclado.nextInt();
                    Empleado empleadoCheck2 = controlPersistencia.buscarEmpleado(idModificar);
                    if (empleadoCheck2 != null) {
                        System.out.println("Se encontro Empleado");
                        teclado = new Scanner(System.in);

                        System.out.println("Ingresa el nombre");
                        String nombreMod = teclado.nextLine();

                        System.out.println("Ingresa el apellido");
                        String apellidoMod = teclado.nextLine();

                        System.out.println("Ingresa el cargo");
                        String cargoMod = teclado.nextLine();

                        teclado = new Scanner(System.in);
                        System.out.println("Ingresa el salario");
                        Double salarioMod = teclado.nextDouble();

                        teclado = new Scanner(System.in);

                        LocalDate fechaInicioMod = null;

                        while (fechaInicioMod == null) {
                            try {
                                System.out.println("Ingresa la fecha de ingreso con este formato AAAA-MM-DD: ");
                                String fechaInicio2 = teclado.nextLine();
                                fechaInicioMod = LocalDate.parse(fechaInicio2);
                            } catch (DateTimeParseException e) {
                                System.out.println("Error: Formato inválido. Intenta nuevamente usando AAAA-MM-DD.");
                            }
                        }

                        controlPersistencia.modificarEmpleado(new Empleado(idModificar, nombreMod, apellidoMod, cargoMod, salarioMod, fechaInicioMod));
                        System.out.println("El empleado ha sido modificado correctamente ");

                    } else {
                        System.out.println("El empleado con el ID : " + idModificar + "  No se encontro  ");
                    }

                    break;
                case 5:

                    listaEmpleados = controlPersistencia.buscarEmpleados();
                    if (listaEmpleados.isEmpty()) {
                        System.out.println("No hay empleados.");
                    } else {
                        System.out.println("Listado de los empleados registrados:");
                        recorrerList(listaEmpleados);
                    }

                    break;

                case 6:
                    System.out.println("Cerro el Sistema de Gestión de Empleados");
                    return;

                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        }

    }

    public static void recorrerList(List<Empleado> listaEmpleados) {
        for (Empleado emplea : listaEmpleados) {
            System.out.println(emplea.toString());
        }
    }
    
    
}
