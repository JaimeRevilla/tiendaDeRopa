package tiendaDeRopa;

import java.io.*;

import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/*
 * Creacion de la clase Tabla bidimensional de cualquier tipo de productos 
 * para poder hacer una busqueda posteriormente
 * 
 */
public class Tabla {
	//Atributos
	private ArrayList<String> cabeceras;
	private ArrayList<ArrayList<String>> datos;// Datos de la cabecdra de las columnas, implemento de todos los Strings
	private ArrayList<Class<?>> tipos;//tipos de cada una de las columnas, requiridas a los datos de los Strings
	//FECHA
	
	/*
	 * Creacion de una tabla vacia para poder poner los datos de los productos
	 */
	public Tabla() {
		cabeceras = new ArrayList<>();
		datos = new ArrayList<>();
	}
	/*
	 * Creacion de una tabla de datos de cabecera
	 */
	public Tabla(ArrayList<String>cabeceras) {
		this.cabeceras = cabeceras;
		datos = new ArrayList<>();
	}
	/*
	 * Cambio de cabeceras 
	 */
	public void setCabeceras(ArrayList<String> cabeceras) {
		this.cabeceras = cabeceras;
	}
	/*
	 * Añade una columna al final de las columnas ya creadas
	 */
	public void addColumna(String cabecera, String defaultValue) {
		cabeceras.add(cabecera);
		for (ArrayList<String> line: datos) {
			line.add(defaultValue);
		}
		if(tipos != null) {
		//	tipos.add( calcDataType(defaultValue));
		}
	}
}
