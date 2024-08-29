import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.Normalizer;
import java.text.Normalizer.Form;


public class Main {
    public static void main(String[] args) {
        List<Clients> clients = readClientsFromCSV("C:\\Users\\joaov\\IdeaProjects\\Prova POO P1 4P\\src\\clients_p.csv");
        List<Locations> locations = readLocationsFromCSV("C:\\Users\\joaov\\IdeaProjects\\Prova POO P1 4P\\src\\locations.csv");
        Scanner sc = new Scanner(System.in);
        int idClient;

        System.out.println("Busque um cliente através do seu ID: ");
        idClient = sc.nextInt();

        Optional<Clients> clientsOptional = findClientById(idClient, clients);

        if (clientsOptional.isPresent()) {
            Clients cliente = clientsOptional.get();
            List<Locations> matchingLocations = findBestLocationsForClient(cliente, locations);

            int maxLength = 0;

            if (!matchingLocations.isEmpty()) {
                System.out.println("Cliente: " + cliente.getName() + ", " + cliente.getAge() + " anos, sexo: " + cliente.getSex() +
                        ", mora em: " + String.join(", ", cliente.getAddress()) +
                        ", tem preferência por: " + cliente.getStayPreferense() +
                        " -> Localizações Ideais: ");

                for (Locations location : matchingLocations) {
                    int length = location.toString().length();
                    if(length > maxLength) {
                        maxLength = length;
                    }
                }

                for (Locations location : matchingLocations){
                    System.out.println("-".repeat(maxLength));
                    System.out.println(location);
                }
            } else {
                System.out.println("Cliente: " + cliente.getName() + ", " + cliente.getAge() + " anos, sexo: " + cliente.getSex() +
                        ", mora em: " + String.join(", ", cliente.getAddress()) +
                        ", tem preferência por: " + cliente.getStayPreferense() +
                        " -> Nenhuma localização adequada encontrada.");
            }
        } else {
            System.out.println("Cliente com ID " + idClient + " não encontrado.");
        }
    }

    private static Optional<Clients> findClientById(int id, List<Clients> clients) {
        return clients.stream()
                .filter(client -> client.getId() == id)
                .findFirst();
    }

    private static List<Locations> findBestLocationsForClient(Clients client, List<Locations> locations) {
        String stayPreference = String.join(",", client.getStayPreferense());
        stayPreference = normalizeString(stayPreference);

        String[] preferences = parsePreferences(stayPreference);

        if (preferences == null) {
            System.out.println("Formato de preferência inválido para o cliente: " + client.getName());
            return Collections.emptyList();
        }

        String typeLocalPreference = normalizeString(preferences[0]);
        int roomsPreference = Integer.parseInt(preferences[1]);
        int bathroomsPreference = Integer.parseInt(preferences[2]);
        int bedsPreference = Integer.parseInt(preferences[3]);

        List<Locations> filteredLocations = locations.stream()
                .filter(location -> normalizeString(location.getTypeOfPlace()).equals(typeLocalPreference)
                        && location.getNumberOfRooms() >= roomsPreference
                        && location.getNumberOfBathrooms() >= bathroomsPreference
                        && location.getNumberOfBed() >= bedsPreference)
                .toList();

        if (filteredLocations.isEmpty()) {
            System.out.println("Nenhuma localização encontrada para o tipo: " + typeLocalPreference +
                    ", com pelo menos " + roomsPreference + " quartos, " + bathroomsPreference +
                    " banheiros, e " + bedsPreference + " camas.");
        } else {
            System.out.println(filteredLocations.size() + " localizações encontradas para o cliente: " + client.getName());
        }

        return filteredLocations;
    }

    private static String[] parsePreferences(String stayPreference) {
        String regex = "(?i)(\\w+)\\s*com\\s*(\\d+)\\s*quartos?\\s*,?\\s*(\\d+)\\s*banheiros?\\s*e\\s*(\\d+)\\s*camas?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stayPreference);

        if (matcher.find()) {
            return new String[]{
                    matcher.group(1).trim(), // tipo de local
                    matcher.group(2).trim(), // quartos
                    matcher.group(3).trim(), // banheiros
                    matcher.group(4).trim()  // camas
            };
        } else {
            System.out.println("Regex falhou para o cliente. Tentando parsing manual. String recebida: \"" + stayPreference + "\"");
            try {
                String tipoLocal = stayPreference.split("com")[0].trim();
                int rooms = 0, bathrooms = 0, beds = 0;

                if (stayPreference.contains("quartos")) {
                    String roomsStr = stayPreference.split("quartos")[0].replaceAll("[^0-9]", "");
                    rooms = !roomsStr.isEmpty() ? Integer.parseInt(roomsStr) : 0;
                }
                if (stayPreference.contains("banheiros")) {
                    String bathroomsStr = stayPreference.split("banheiros")[0].split(",")[1].replaceAll("[^0-9]", "");
                    bathrooms = !bathroomsStr.isEmpty() ? Integer.parseInt(bathroomsStr) : 0;
                }
                if (stayPreference.contains("camas")) {
                    String bedsStr = stayPreference.split("camas")[0].split("e")[1].replaceAll("[^0-9]", "");
                    beds = !bedsStr.isEmpty() ? Integer.parseInt(bedsStr) : 0;
                }

                return new String[]{
                        tipoLocal,
                        String.valueOf(rooms),
                        String.valueOf(bathrooms),
                        String.valueOf(beds)
                };
            } catch (Exception e) {
                System.out.println("Parsing manual falhou. Formato de preferência inválido para o cliente. String recebida: \"" + stayPreference + "\". Erro: " + e.getMessage());
            }
        }
        return null;
    }

    private static String normalizeString(String input) {
        return Normalizer.normalize(input, Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toLowerCase();
    }

    private static List<Clients> readClientsFromCSV(String fileName) {
        List<Clients> clients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 7) {
                    int id = Integer.parseInt(values[0].trim());
                    String name = values[1].trim();
                    List<String> address = new ArrayList<>();
                    address.add(values[2].trim());
                    address.add(values[3].trim());
                    address.add(values[4].trim());

                    int age = Integer.parseInt(values[5].trim());
                    String sex = values[6].trim();

                    List<String> stayPreference = new ArrayList<>();
                    for (int i = 7; i < values.length; i++) {
                        stayPreference.add(values[i].trim());
                    }

                    Clients client = new Clients(id, name, address, age, sex, stayPreference);
                    clients.add(client);
                } else {
                    System.out.println("Dados insuficientes na linha: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter número: " + e.getMessage());
        }
        return clients;
    }

    private static List<Locations> readLocationsFromCSV(String fileName) {
        List<Locations> locations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0].trim());
                int numberOfRooms = Integer.parseInt(values[3].trim());
                int numberOfBathrooms = Integer.parseInt(values[4].trim());
                int quadra = Integer.parseInt(values[7].trim());
                int lote = Integer.parseInt(values[8].trim());
                int numero = Integer.parseInt(values[9].trim());
                int numberMaxGuests = Integer.parseInt(values[10].trim());
                int numberOfBeds = Integer.parseInt(values[11].trim());
                int numberOfRecommendations = Integer.parseInt(values[12].trim());

                Locations location = new Locations(id, values[1], values[2], numberOfRooms,
                        numberOfBathrooms, values[5], values[6], quadra, lote, numero, numberMaxGuests,
                        numberOfBeds, numberOfRecommendations);
                locations.add(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter número: " + e.getMessage());
        }
        return locations;
    }
}