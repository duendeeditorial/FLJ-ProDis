import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class VotingServicioImpl extends UnicastRemoteObject implements VotingServicio {
    private Map<String, Integer> votes; // Mapa candidatos/votos
    private List<String> allCandidates; // Lista de candidatos

    protected VotingServicioImpl() throws RemoteException {
        super(); 
        votes = new HashMap<>(); 
        allCandidates = new ArrayList<>(); 
    }

    @Override
    public synchronized int vote(String candidate) throws RemoteException {
        votes.put(candidate, votes.getOrDefault(candidate, 0) + 1); // + votos para el candidato
        if (!allCandidates.contains(candidate)) {
            allCandidates.add(candidate); // Añadir el candidato a la lista si no está
        }
        System.out.println("- Voto registrado en el servidor para: " + candidate + ". Total votos: " + votes.get(candidate));
        return votes.get(candidate); 
    }

    @Override
    public synchronized int getCount(String candidate) throws RemoteException {
        int count = votes.getOrDefault(candidate, 0); // Obtener número de votos de un candidato
        System.out.println("- Consulta de votos en el servidor para: " + candidate + ". Total votos: " + count);
        return count;
    }

    @Override
    public synchronized List<String> getAllCandidates() throws RemoteException {
        System.out.println("- Consulta en el servidor de todos los candidatos.");
        if (allCandidates.isEmpty()) {
            System.out.println("No hay candidatos registrados.");
        } else {
            System.out.println("Candidatos actuales: " + allCandidates);
        }
        return new ArrayList<>(allCandidates);
    }

    @Override
    public synchronized void removeVotes() throws RemoteException {
        for (String candidate : votes.keySet()) {
            votes.put(candidate, 0); // Poner a 0 los votos de todos los candidatos
        }
        System.out.println("- Todos los votos han sido eliminados en el servidor.");
    }

    @Override
    public synchronized void removeVotesAndCandidates() throws RemoteException {
        votes.clear(); // Eliminar todos los votos y candidatos del mapa
        allCandidates.clear(); // Eliminar todos los candidatos de la lista
        System.out.println("- Todos los votos y candidatos han sido eliminados en el servidor.");
    }

    @Override
    public synchronized Map<String, Integer> getVotingInformation() throws RemoteException {
        System.out.println("- Consulta de toda la información de voto en el servidor.");
        Map<String, Integer> allVotingInfo = new HashMap<>();
        if (allCandidates.isEmpty()) {
            System.out.println("No hay candidatos."); 
        } else {
            for (String candidate : allCandidates) {
                allVotingInfo.put(candidate, votes.getOrDefault(candidate, 0)); // Obtener número de votos o 0 si no tiene
            }
            for (Map.Entry<String, Integer> entry : allVotingInfo.entrySet()) {  // Imprimir información de voto
                System.out.println("Candidato: " + entry.getKey() + ", Votos: " + entry.getValue());
            }
        }
        return allVotingInfo; // Devolver toda la información de votos, incluso con 0 votos
    }
}