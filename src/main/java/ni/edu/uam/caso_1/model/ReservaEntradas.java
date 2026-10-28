
package ni.edu.uam.caso_1.model;

public class ReservaEntradas {

    private int entradasDisponibles = 20;
    private int reservasConfirmadas = 0;
    private int reservasRechazadas = 0;

    public ReservaEntradas() {
    }

    // Permite que solo un hilo reserve a la vez
    public synchronized boolean reservarEntrada() {

        if (entradasDisponibles > 0) {
            entradasDisponibles--;
            reservasConfirmadas++;
            return true;
        } else {
            reservasRechazadas++;
            return false;
        }
    }

    public synchronized int[] obtenerResultados() {
        return new int[]{
                entradasDisponibles,
                reservasConfirmadas,
                reservasRechazadas
        };
    }
}
