package ni.edu.uam.caso_1.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ni.edu.uam.caso_1.model.ReservaEntradas;

public class ReservaController {
    @FXML
    private Label lblDisponibles;
    @FXML
    private Label lblConfirmadas;
    @FXML
    private Label lblRechazadas;
    @FXML
    private Label lblEstado;
    @FXML
    private TextArea txtRegistro;
    @FXML
    private Button btnIniciar;
    @FXML
    private Button btnCancelar;

    private ReservaEntradas reserva;
    private Thread hilo1;
    private Thread hilo2;
    private Thread hilo3;
    private boolean ejecutando = false;
    private volatile boolean cancelado = false;

    @FXML
    private void initialize() {
        reserva = new ReservaEntradas();
        actualizarResultados();
        lblEstado.setText("Listo para empezar");
        btnCancelar.setDisable(true);
    }

    @FXML
    private void iniciarSimulacion() {
        if (ejecutando) {
            return;
        }
        ejecutando = true;
        cancelado = false;

        // Reiniciar los datos de la simulación
        reserva = new ReservaEntradas();
        txtRegistro.clear();
        actualizarResultados();
        lblEstado.setText("Simulación en proceso...");
        btnIniciar.setDisable(true);
        btnCancelar.setDisable(false);

        // Crear las tareas de los tres agentes
        Runnable agente1 = () -> procesarReservas("Agente 1");
        Runnable agente2 = () -> procesarReservas("Agente 2");
        Runnable agente3 = () -> procesarReservas("Agente 3");

        // Crear los hilos
        hilo1 = new Thread(agente1);
        hilo2 = new Thread(agente2);
        hilo3 = new Thread(agente3);

        // Hilo que controla la finalización
        Thread coordinador = new Thread(() -> {
            hilo1.start();
            hilo2.start();
            hilo3.start();
            try {
                hilo1.join();
                hilo2.join();
                hilo3.join();
                Platform.runLater(() -> {
                    actualizarResultados();
                    if (cancelado) {
                        lblEstado.setText("Simulación cancelada");
                        txtRegistro.appendText("\n--- Simulación cancelada ---\n");
                    } else {
                        lblEstado.setText("Simulación finalizada");
                        txtRegistro.appendText("\n--- Simulación finalizada ---\n");
                    }
                    btnIniciar.setDisable(false);
                    btnCancelar.setDisable(true);
                    ejecutando = false;
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        coordinador.start();
    }

    // Cada agente realiza 10 solicitudes
    private void procesarReservas(String agente) {
        for (int i = 1; i <= 10; i++) {
            if (cancelado) {
                return;
            }
            try {
                Thread.sleep(300);
                if (cancelado) {
                    return;
                }
                boolean confirmada = reserva.reservarEntrada();
                String mensaje;
                if (confirmada) {
                    mensaje = agente + " - Solicitud " + i + ": Confirmada\n";
                } else {
                    mensaje = agente + " - Solicitud " + i + ": Rechazada\n";
                }

                Platform.runLater(() -> {
                    txtRegistro.appendText(mensaje);
                    actualizarResultados();
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    // Actualizar los contadores de la interfaz
    private void actualizarResultados() {

        int[] resultados = reserva.obtenerResultados();
        lblDisponibles.setText(String.valueOf(resultados[0]));
        lblConfirmadas.setText(String.valueOf(resultados[1]));
        lblRechazadas.setText(String.valueOf(resultados[2]));
    }

    @FXML
    private void cancelarSimulacion() {
        if (!ejecutando || cancelado) {
            return;
        }
        cancelado = true;
        lblEstado.setText("Cancelando simulación...");
        btnCancelar.setDisable(true);
        hilo1.interrupt();
        hilo2.interrupt();
        hilo3.interrupt();
    }

    @FXML
    private void salirSistema() {
        cancelado = true;
        if (hilo1 != null) {
            hilo1.interrupt();
        }
        if (hilo2 != null) {
            hilo2.interrupt();
        }
        if (hilo3 != null) {
            hilo3.interrupt();
        }
        Platform.exit();
    }
}