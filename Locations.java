public class Locations {
    private int id;
    private String typeOfPlace;
    private String referencePoint;
    private int numberOfRooms;
    private int numberOfBathrooms;
    private String neigbhborhood;
    private String street;
    private int block;
    private int batch;
    private int number;
    private int numberMaxGuests;
    private int numberOfBed;
    private int numberOfRecommendations;

    public Locations(int id, String typeOfPlace, String referencePoint, int numberOfRooms,
                     int numberOfBathrooms, String neigbhborhood, String street, int block,
                     int batch, int number, int numberMaxGuests, int numberOfBed, int numberOfRecommendations) {
        this.id = id;
        this.typeOfPlace = typeOfPlace;
        this.referencePoint = referencePoint;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.neigbhborhood = neigbhborhood;
        this.street = street;
        this.block = block;
        this.batch = batch;
        this.number = number;
        this.numberMaxGuests = numberMaxGuests;
        this.numberOfBed = numberOfBed;
        this.numberOfRecommendations = numberOfRecommendations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfPlace() {
        return typeOfPlace;
    }

    public void setTypeOfPlace(String typeOfPlace) {
        this.typeOfPlace = typeOfPlace;
    }

    public String getReferencePoint() {
        return referencePoint;
    }

    public void setReferencePoint(String referencePoint) {
        this.referencePoint = referencePoint;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public String getNeigbhborhood() {
        return neigbhborhood;
    }

    public void setNeigbhborhood(String neigbhborhood) {
        this.neigbhborhood = neigbhborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberMaxGuests() {
        return numberMaxGuests;
    }

    public void setNumberMaxGuests(int numberMaxGuests) {
        this.numberMaxGuests = numberMaxGuests;
    }

    public int getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(int numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public int getNumberOfRecommendations() {
        return numberOfRecommendations;
    }

    public void setNumberOfRecommendations(int numberOfRecommendations) {
        this.numberOfRecommendations = numberOfRecommendations;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", tipoDoLocal='" + typeOfPlace + '\'' +
                ", pontosDeReferencia='" + referencePoint + '\'' +
                ", quantidadeDeQuartos=" + numberOfRooms +
                ", quantidadeDeBanheiros=" + numberOfBathrooms +
                ", bairro='" + neigbhborhood + '\'' +
                ", rua='" + street + '\'' +
                ", quadra=" + block +
                ", lote=" + batch +
                ", numero=" + number +
                ", quantidadeMaximaDeHospedes=" + numberMaxGuests +
                ", quantidadeDeCamas=" + numberOfBed +
                ", quantidadeDeRecomendacoes=" + numberOfRecommendations +
                '}';
    }
}
