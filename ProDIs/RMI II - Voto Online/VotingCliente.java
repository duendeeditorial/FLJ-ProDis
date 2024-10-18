import java.rmi.Naming;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VotingCliente {
    public static void main(String[] args) {
        Scanner scanner = null; // Declarar scanner fuera del bloque try
        try {
            VotingServicio votingService = (VotingServicio) Naming.lookup("rmi://localhost/VotingServicio"); // Conectar con el servidor de votación
            scanner = new Scanner(System.in);

            while (true) {
                System.out.println("----------------------------------------");
                System.out.println("OPCIONES:");
                System.out.println("----------------------------------------");
                System.out.println("1. Votar por un candidato");
                System.out.println("2. Ver recuento de votos de un candidato");
                System.out.println("3. Ver todos los candidatos");
                System.out.println("4. Ver toda la información de votos");
                System.out.println("5. Eliminar todos los votos");
                System.out.println("6. Eliminar todos los votos y candidatos");
                System.out.println("7. Salir");
                System.out.println("----------------------------------------");

                int opcion = scanner.nextInt(); scanner.nextLine(); 
                switch (opcion) {
                    case 1:
                        System.out.print("- Introduce el nombre del candidato: ");
                        String candidato = scanner.nextLine();
                        int votos = votingService.vote(candidato); // Votar por candidato
                        System.out.println("El candidato " + candidato + " tiene ahora " + votos + " votos.");
                        break;
                    case 2:
                        System.out.print("- Introduce el nombre del candidato: ");
                        String candidateName = scanner.nextLine();
                        int count = votingService.getCount(candidateName); // Obtener número de votos de candidato
                        System.out.println("El candidato " + candidateName + " tiene " + count + " votos.");
                        break;
                    case 3:
                        List<String> candidates = votingService.getAllCandidates(); // Obtener todos los candidatos
                        if (candidates.isEmpty()) {
                            System.out.println("- No hay candidatos disponibles.");
                        } else {
                            System.out.println("- Candidatos: " + candidates);
                        }
                        break;
                    case 4:
                        Map<String, Integer> votingInfo = votingService.getVotingInformation(); // Obtener toda la información de votos
                        if (votingInfo.isEmpty()) {
                            System.out.println("- No hay información de votos disponible.");
                        } else {
                            System.out.println("- Información de votos:");
                            for (Map.Entry<String, Integer> entry : votingInfo.entrySet()) { // Mostrar todos los candidatos, incluso con 0 votos
                                System.out.println("Candidato: " + entry.getKey() + ", Votos: " + entry.getValue()); 
                            }
                        }
                        break;
                    case 5:
                        votingService.removeVotes(); // Eliminar todos los votos
                        System.out.println("- Todos los votos han sido eliminados.");
                        break;
                    case 6:
                        votingService.removeVotesAndCandidates(); // Eliminar todos los votos y candidatos
                        System.out.println("- Todos los votos y candidatos han sido eliminados.");
                        break;
                    case 7:
                        System.out.println("- Saliendo...");
                        return;
                    default:
                        System.out.println("- Opción no válida.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}