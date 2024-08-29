import java.util.ArrayList;
import java.util.List;

public class Clients {
    private int id;
    private String name;
    private List<String> address;
    private int age;
    private String sex;
    private List<String> stayPreferense;

    public Clients(int id, String name, List<String> address, int age, String sex, List<String> stayPreferense) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.sex = sex;
        this.stayPreferense = stayPreferense;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public List<String> getStayPreferense() {
        return stayPreferense;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", endereco=" + address +
                ", idade=" + age +
                ", sexo='" + sex + '\'' +
                ", preferenciaEstadia=" + stayPreferense +
                '}';
    }
}
