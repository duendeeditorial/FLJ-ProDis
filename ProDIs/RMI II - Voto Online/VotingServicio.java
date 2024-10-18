import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface VotingServicio extends Remote {
    int vote(String candidate) throws RemoteException; // Votar por candidato
    int getCount(String candidate) throws RemoteException; // Obtener número de votos de un candidato
    List<String> getAllCandidates() throws RemoteException; // Obtener todos los candidatos con votos
    void removeVotes() throws RemoteException; // Eliminar todos los votos, manteniendo candidatos
    void removeVotesAndCandidates() throws RemoteException; // Eliminar todos los votos y candidatos
    Map<String, Integer> getVotingInformation() throws RemoteException; // Obtener toda la información de voto

// Compilar:                                            javac *.java
// Ejecutar servidor en una ventana de terminal:        java VotingServidor
// Ejecutar cliente/s en otra/s ventana de terminal:    java VotingCliente

}