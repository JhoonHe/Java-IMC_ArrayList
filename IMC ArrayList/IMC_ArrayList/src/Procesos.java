import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Procesos {

	ArrayList<String> listaNombres;
	ArrayList<String> listaTelefonos;
	ArrayList<String> listaImc;

	ArrayList<Integer> posiciones;
	
	public Procesos() {

		listaNombres = new ArrayList<String>();
		listaTelefonos = new ArrayList<String>();
		listaImc = new ArrayList<String>();

		int opcion = 0;
		String menu = obtenerMenu();

		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
			validarOpcion(opcion);
		} while (opcion != 6);
	}

	public String obtenerMenu() {
		String menu = "";

		menu += "****MENU****";
		menu += "\n\nIngrese la opcion";
		menu += "\n\n1- Registrar datos";
		menu += "\n2- Imprimir por nombre";
		menu += "\n3- Imprimir por lista completa";
		menu += "\n4- Actualizar datos";
		menu += "\n5- Eliminar datos";
		menu += "\n6- Salir\n\n";

		return menu;
	}

	public void validarOpcion(int opcion) {

		switch (opcion) {

		case 1:
			registrarDatos();
			break;

		case 2:
			if (listaNombres.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay personas registradas");
				break;
			}
			imprimirNombre();
			break;

		case 3:
			if (listaNombres.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay personas registradas");
				break;
			}
			imprimirLista();
			break;

		case 4:
			if (listaNombres.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay personas registradas");
				break;
			}
			actualizarDatos();
			break;
			
		case 5:
			if (listaNombres.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay personas registradas");
				break;
			}
			eliminarDatos();
			break;
			
		case 6:
			break;

		default:
			JOptionPane.showMessageDialog(null, "Opcion incorrecta");
			break;
		}
	}

	public void registrarDatos() {

		double peso = 0, talla = 0, imc = 0;
		String nombre = "", respuesta = "";
		String telefono = "";
		String opcion = "";

		do {
			nombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona");
			telefono = JOptionPane.showInputDialog("Ingrese el telefono de la persona");

			peso = validarNumero("Ingrese el peso de la persona");
			talla = validarNumero("Ingrese la talla de la persona");

			imc = calcularImc(peso, talla);
			respuesta = obtenerRespuesta(imc);

			listaNombres.add(nombre.toLowerCase());
			listaTelefonos.add(telefono);
			listaImc.add(respuesta);

			opcion = JOptionPane.showInputDialog("Desea ingresar otra persona");

		} while (opcion.equalsIgnoreCase("si"));
	}

	public double calcularImc(double peso, double talla) {

		double imc = 0;

		imc = peso / (talla * talla);

		return imc;
	}

	public String obtenerRespuesta(double imc) {

		String mensaje = "";

		if (imc < 18) {
			mensaje = "Anorexia";
		} else if (imc >= 18 && imc < 20) {
			mensaje = "Delgadez";
		} else if (imc >= 20 && imc < 27) {
			mensaje = "Normalidad";
		} else if (imc >= 27 && imc < 30) {
			mensaje = "Obesidad (grado 1)";
		} else if (imc >= 30 && imc < 35) {
			mensaje = "Obesidad (grado 2)";
		} else if (imc >= 35 && imc < 40) {
			mensaje = "Obesidad (grado 3)";
		} else {
			mensaje = "Obesidad (grado 3)";
		}

		return mensaje;
	}

	public void imprimirNombre() {
		
		String nombre = "";
		String mensaje = "";
		int cantidad = 0;
		
		nombre = JOptionPane.showInputDialog("Ingrese el nombre a consultar").toLowerCase();
		
		mensaje+= "****Imprimir por nombre****\n";

		if (listaNombres.contains(nombre)) {
		
		for (int i = 0 ; i < listaNombres.size() ; i++) {
			
			if (listaNombres.get(i).equalsIgnoreCase(nombre)) {				
				mensaje+= "\nNombre: "+listaNombres.get(i) +"\t\t | Telefono: "+ listaTelefonos.get(i) +"\t\t | Resultado: "+ listaImc.get(i);
				cantidad++;
			}
		}
		
			mensaje+= "\n\nEl nombre " +nombre+ " se encuentra " +cantidad+ " vez/veces";
			
			JOptionPane.showMessageDialog(null, mensaje);		
		
		}else {
			JOptionPane.showMessageDialog(null, "El nombre " +nombre+ " no se encuentra registrado");					
			
		}
	}

	public void imprimirLista() {

		String mensaje = "";

		mensaje += "****Imprimir lista****\n";

		for (int i = 0; i < listaNombres.size(); i++) {
			
			mensaje += "\nNombre: " + listaNombres.get(i) + "\t\t | Telefono: " + listaTelefonos.get(i) + "\t\t | Resultado: " + listaImc.get(i);
		}
		
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	public void actualizarDatos() {
		
		posiciones = new ArrayList<Integer>();
		String nombre = "", nombreNuevo = "", telefonoNuevo = "";
		String mensaje = "";
		int posicion = 0, opcion = 0;
		
		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona a actualizar").toLowerCase();
		
		if (listaNombres.contains(nombre)) {
			
			mensaje+= "Ingrese la posición de la persona a actualizar\n";
			
			for (int i = 0 ; i < listaNombres.size() ; i++) {
				
				if (listaNombres.get(i).equalsIgnoreCase(nombre)) {
					
					mensaje+= "\nPosicion # "+i+" Nombre: "+listaNombres.get(i) +"\t\t | Telefono: "+ listaTelefonos.get(i) +"\t\t | Resultado: "+ listaImc.get(i);
					posiciones.add(i);
				}
			}
						
			do {	
				posicion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
				
				if (posiciones.contains(posicion)) {

					do {						
						opcion = Integer.parseInt(JOptionPane.showInputDialog("Por favor ingrese la opcion del cambio que desea realizar: \n1- Nombre \n2- Telefono \n3- Telefono y nombre"));
					}while(opcion < 1 || opcion > 3);
					
					switch (opcion) {
					
					case 1: 
						nombreNuevo = JOptionPane.showInputDialog("Nombre anterior (" +listaNombres.get(posicion)+ ")\nIngrese el nombre nuevo de la persona").toLowerCase();
						listaNombres.set(posicion, nombreNuevo);				
						break;
						
					case 2: 
						telefonoNuevo = JOptionPane.showInputDialog("Telefono anterior (" +listaTelefonos.get(posicion)+ ")\nIngrese el telefono nuevo de la persona");
						listaTelefonos.set(posicion, telefonoNuevo);
						break;
						
					case 3: 
						nombreNuevo = JOptionPane.showInputDialog("Nombre anterior (" +listaNombres.get(posicion)+ ")\nIngrese el nombre nuevo de la persona").toLowerCase();
						listaNombres.set(posicion, nombreNuevo);				
						telefonoNuevo = JOptionPane.showInputDialog("Telefono anterior (" +listaTelefonos.get(posicion)+ ")\nIngrese el telefono nuevo de la persona");
						listaTelefonos.set(posicion, telefonoNuevo);
						break;
					}
					
					break;
				}
				
			}while(true);
				
		} else {
			JOptionPane.showMessageDialog(null, "El nombre " +nombre+ " no se encuentra registrado");	
		}
	}
	
	public void eliminarDatos() {
		
		posiciones = new ArrayList<Integer>();
		String nombre = "";
		String mensaje = "", opcion = "";
		int posicion = 0;
		
		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona a eliminar").toLowerCase();
		
		if (listaNombres.contains(nombre)) {
			
			mensaje+= "Ingrese la posición de la persona a eliminar\n";
			
			for (int i = 0 ; i < listaNombres.size() ; i++) {
				
				if (listaNombres.get(i).equalsIgnoreCase(nombre)) {
					
					mensaje+= "\nPosicion # "+i+" Nombre: "+listaNombres.get(i) +"\t\t | Telefono: "+ listaTelefonos.get(i) +"\t\t | Resultado: "+ listaImc.get(i);
					posiciones.add(i);
				}
			}
						
			do {	
				posicion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
				
				if (posiciones.contains(posicion)) {
					
					opcion = JOptionPane.showInputDialog("¿Esta seguro de eliminar los datos de la persona (" +listaNombres.get(posicion)+ ") ? \nIngrese Si para continuar de lo contrario ingrese No");
					
					if (opcion.equalsIgnoreCase("si")) {						
						listaNombres.remove(posicion);
						listaTelefonos.remove(posicion);
						listaImc.remove(posicion);
					}
					
					break;
				}
				
			}while(true);
				
		} else {
			JOptionPane.showMessageDialog(null, "El nombre " +nombre+ " no se encuentra registrado");	
		}
	}

	public double validarNumero(String mensaje) {

		double numero = 0;
		do {
			numero = Double.parseDouble(JOptionPane.showInputDialog(mensaje));
		} while (numero <= 0);

		return numero;
	}

}