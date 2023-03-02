package circuit_touristique.entities;

public class Circuit {

    private int nc;
    private String vdep;
    private String varr;
    private float prix;
    private int duree;
    private String nom;
    private String description;
    private String imageSrc;

    public Circuit(int nc, String vdep, String varr, float prix, int duree, String nom, String description, String imageSrc) {
        this.nc = nc;
        this.vdep = vdep;
        this.varr = varr;
        this.prix = prix;
        this.duree = duree;
        this.nom = nom;
        this.description = description;
        this.imageSrc = imageSrc;
    }

    public int getNc() {
        return nc;
    }

    public void setNc(int nc) {
        this.nc = nc;
    }

    public String getVdep() {
        return vdep;
    }

    public void setVdep(String vdep) {
        this.vdep = vdep;
    }

    public String getVarr() {
        return varr;
    }

    public void setVarr(String varr) {
        this.varr = varr;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

}
