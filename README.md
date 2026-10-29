# Caso 1 - Reserva Concurrente de Entradas

**Asignatura:** Programación de Aplicaciones de Escritorio  
**Carrera:** Ingeniería de Sistemas  
**Universidad:** Universidad Americana (UAM)

## Integrantes
- Jarold José Montealto Cantillano
- Hendrix Devadip Aguirre Zamora
- Fanor Alexander Velásquez Sarabia

## Descripción del proyecto

En este proyecto desarrollamos una aplicación en JavaFX que simula la reserva de entradas para un evento. El sistema cuenta con 20 entradas disponibles y tres agentes que realizan 10 solicitudes cada uno.

El objetivo es que los agentes puedan trabajar al mismo tiempo, evitando que se reserven más entradas de las disponibles.

## Funcionamiento

La aplicación permite:

- Iniciar una simulación con tres agentes trabajando simultáneamente.
- Confirmar reservas mientras existan entradas disponibles.
- Rechazar solicitudes cuando las entradas se agoten.
- Mostrar las reservas confirmadas, rechazadas y las entradas restantes.
- Cancelar una simulación mientras se está ejecutando.
- Iniciar una nueva simulación con los valores restablecidos.

Para controlar las reservas utilizamos `synchronized`, evitando que varios agentes modifiquen las entradas disponibles al mismo tiempo.

## Herramientas utilizadas

- Java 21
- JavaFX
- IntelliJ IDEA
- Scene Builder
- Git y GitHub

## Ejecución del proyecto

1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar que esté configurado el JDK 21.
3. Ejecutar la clase `ReservaApplication`.
4. Presionar el botón **Iniciar** para comenzar la simulación.
5. Observar cómo los agentes realizan las reservas y se actualizan los resultados.

## Resultados esperados

Al finalizar una simulación completa, el sistema debe mostrar:

| Resultado | Cantidad |
|---|---:|
| Reservas confirmadas | 20 |
| Reservas rechazadas | 10 |
| Entradas disponibles | 0 |
| Solicitudes realizadas | 30 |

Como parte de las pruebas, se realizan cinco simulaciones para comprobar que el sistema mantenga los resultados correctos.

## Trabajo en equipo

Este proyecto se desarrolló de manera grupal, aplicando los conocimientos adquiridos en clase sobre programación concurrente e interfaces gráficas. El trabajo nos permitió comprender mejor cómo varios agentes pueden realizar operaciones al mismo tiempo sin afectar los resultados del sistema.

## Repositorio

https://github.com/hendrixaguirre/Caso1.git
