package tp2;

import jakarta.persistence.*;   // jakarta.*

@Entity                         // cette classe devient une table
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrément
    private Long id;
    private String nom;
    private String prenom;
    @Column(unique = true)      // pas deux fois le même email
    private String email;
    private String motDePasse;  // on stocke le HACHAGE, jamais le mot de passe clair

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
}