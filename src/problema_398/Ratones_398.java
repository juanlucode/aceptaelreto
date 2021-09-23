package problema_398;

import java.util.*;

import static java.lang.Math.pow;

public class Ratones_398 {

	private static class Celda{
		private byte fila;
		private byte columna;
		
		Celda(byte fila, byte columna){
			this.fila = fila;
			this.columna = columna;
		}
		
		private int distancia(Celda destino) {
			return (Math.abs(destino.fila - this.fila) + Math.abs(destino.columna - this.columna));
		}

		@Override
		public boolean equals(Object celda){
			if ( this.fila == ((Celda) celda).fila && this.columna == ((Celda) celda).columna )
				return true;
			else
				return false;
		}
/*
		@Override
		public String toString(){
			return String.format("(%d, %d)", this.fila, this.columna);
		}
*/		
	}
	
	private static class Pulsador extends Celda {
		private int tiempoPulsado = -1;
		
		Pulsador(byte fila, byte columna) {
			super(fila, columna);
		}
				
		public void pulsar(int tiempo) {
			this.tiempoPulsado = tiempo;
		}
		
		public int tiempoPulsado() {
			return this.tiempoPulsado;
		}
	}
	
	private static class Caja{
		private Celda celdaSalida;
		private Vector<Pulsador> objetivos;
		private Raton[] ratones = {new Raton(), new Raton()};
		private int tiempoMinimo = Integer.MAX_VALUE;

		Caja(Celda salida, short numPulsadores){
			this.celdaSalida = salida;
			// los objetivos seran los pulsadores
			this.objetivos = new Vector<Pulsador>(numPulsadores);
		}


		private void situarRatonesEntradaCaja() {
			for (Raton raton : this.ratones) raton.situarEntradaCaja();
		}
		

		private int calcularTiempoSecuencia(String secuencia){
			int idxSecuencia = secuencia.length() - 1;
			int idxObjetivo = 0;
			this.situarRatonesEntradaCaja();
			byte ratonActivo;
			while (idxSecuencia >= 0 && idxObjetivo < this.objetivos.size()) {
				// raton a mover
				ratonActivo = Byte.parseByte(secuencia.substring(idxSecuencia, idxSecuencia + 1));
				this.ratones[ratonActivo].mover(this.objetivos.get(idxObjetivo));
				// Hay que esperar hasta que el anterior pulsador haya sido pulsado
				if ( idxObjetivo > 0 && this.ratones[ratonActivo].getTiempo() < this.objetivos.get(idxObjetivo - 1).tiempoPulsado())
					this.ratones[ratonActivo].esperarHasta(this.objetivos.get(idxObjetivo - 1).tiempoPulsado());

				// si se sobrepasa o iguala el tiempo mínimo, se interrumpe el cálculo
				if (this.ratones[ratonActivo].getTiempo() >= this.tiempoMinimo ) return Integer.MAX_VALUE;

				this.ratones[ratonActivo].pulsar(this.objetivos.get(idxObjetivo));
				idxSecuencia--;
				idxObjetivo++;

			}
			// Una vez pulsados todos los objetivos, mover los ratones a la salida.
			for (Raton raton : this.ratones) {
				raton.mover(this.celdaSalida);
				raton.esperarHasta(raton.getTiempo()+1);
			}

			// devuelve el tiempo del último ratón
			return (this.ratones[0].getTiempo() > this.ratones[1].getTiempo())?this.ratones[0].getTiempo():this.ratones[1].getTiempo();
			
		}


		public void situarPulsador(Pulsador pulsador) {
			objetivos.add(pulsador);
		}

		public int calcularFugaOptima(){
			int tiempoCamino;
			
			if (this.objetivos.size() == 0) {
				this.tiempoMinimo = this.calcularTiempoSecuencia("");
			} else 
				for (int i = 0; i < pow(2, this.objetivos.size()); i += 2){
	
					tiempoCamino = this.calcularTiempoSecuencia(String.format("%" + this.objetivos.size() + "s", Integer.toBinaryString(i)).replace(' ', '0'));
					if ( tiempoCamino < this.tiempoMinimo ) this.tiempoMinimo = tiempoCamino;
	
				}
			return this.tiempoMinimo;
		}
	}

	private static class Raton implements Comparable<Raton> {
		private Celda celdaActual;
		private int tiempo;
		
		Raton(){
			//this.situarSalida();
		}
		
		public void esperarHasta(int tiempo) {
			this.tiempo = tiempo;
			
		}

		public void mover(Celda destino) {
			this.tiempo += this.celdaActual.distancia(destino);
			this.celdaActual = destino;
		}
		
		public void pulsar(Pulsador pulsador) {
			pulsador.pulsar(this.tiempo);
		}

		public int getTiempo(){return this.tiempo;}

		public void situarEntradaCaja(){
			// tomamos como celda actual la 1,1
			this.celdaActual = new Celda((byte) 1, (byte) 1);
			this.tiempo = 0;
		}

		@Override
		public int compareTo(Raton raton) {
			if (this.tiempo == raton.tiempo)
				return 0;
			else
				if (this.tiempo > raton.tiempo)
					return 1;
				else
					return -1;
		}
	}



	// MÉTODO INDEPENDIENTE
	private static Pulsador extraerPulsador(String entrada){
		StringTokenizer st = new StringTokenizer(entrada);
		return new Pulsador(Byte.parseByte(st.nextToken().trim()), Byte.parseByte(st.nextToken().trim()));
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in).useDelimiter("\n");
		sc.useLocale(Locale.UK);

		while (sc.hasNext()){
			// nueva caja
			// pasamos la dimensión (coincide casilla de salida)
			// y número de objetivos
			String dimension = sc.next();
			short numPulsadores = Byte.parseByte(sc.next().trim());
			Caja caja = new Caja(extraerPulsador(dimension), numPulsadores);
			
			// cargar objetivos
			String pulsador;
			for (byte i = 0; i < numPulsadores; i++){
				pulsador = sc.next();
				caja.situarPulsador(extraerPulsador(pulsador));
			}

			// imprimir fuga óptima
			System.out.println(caja.calcularFugaOptima());
		}
		sc.close();
		System.exit(0);

	}

}

