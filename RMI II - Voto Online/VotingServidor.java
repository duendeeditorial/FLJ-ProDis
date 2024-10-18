import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

public class VotingServidor {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // Iniciar registro en puerto 1099
            VotingServicioImpl votingService = new VotingServicioImpl(); // Crear instancia del servicio de votación
            Naming.rebind("rmi://localhost/VotingServicio", votingService); // Registrar servicio en RMIRegistry
            System.out.println("Servidor de votación listo.");
            System.out.println("Presiona ENTER para detener el servidor..."); System.in.read(); // Esperar hasta que se presione Enter
            UnicastRemoteObject.unexportObject(votingService, true); // Desregistrar el objeto remoto y liberar recursos
            System.out.println("Servidor detenido y recursos liberados.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}