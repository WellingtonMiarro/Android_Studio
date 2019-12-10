package Model;

public class Pet {

    private String uid;
    private String especie;
    private String nomePet;
    private String raca;
    private String propetario;

    //Construtor
    public Pet() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPropetario() {
        return propetario;
    }

    public void setPropetario(String propetario) {
        this.propetario = propetario;
    }

    @Override
    public String toString() {
        return nomePet;
    }
}
