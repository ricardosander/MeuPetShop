package br.com.ricardosander.meupetshop.model;

import java.time.LocalDate;

/**
 * Model de um Pet(Animal).
 */
public class Pet {

    /**
     * Código identificador.
     */
    private long id;

    /**
     * Nome.
     */
    private String name;

    /**
     * Espécie.
     */
    private String species;

    /**
     * Raça.
     */
    private String breed;

    /**
     * Pêlo.
     */
    private String fur;

    /**
     * Pelagem.
     */
    private String pelage;

    /**
     * Porte
     */
    private PetSize size;

    /**
     * Peso.
     */
    private double weight;

    /**
     * Nascimento.
     */
    private LocalDate birth;

    /**
     * Cadastro
     */
    private LocalDate register;

    /**
     * Castrado.
     */
    private boolean castrated;

    /**
     * Observações.
     */
    private String comments;

    /**
     * Sexo.
     */
    private Gender gender;

    /**
     * Cliente pacote.
     */
    private boolean clientPacket;

    /**
     * Usuário.
     */
    private User user;

    /**
     * Proprietário.
     */
    private Owner owner;

    public Pet(long id, String name, String species, String breed, String fur, String pelage, PetSize size, double weight, LocalDate birth, LocalDate register, boolean castrated, String comments, Gender gender, boolean clientPacket, User user, Owner owner) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.fur = fur;
        this.pelage = pelage;
        this.size = size;
        this.weight = weight;
        this.birth = birth;
        this.register = register;
        this.castrated = castrated;
        this.comments = comments;
        this.gender = gender;
        this.clientPacket = clientPacket;
        this.user = user;
        this.owner = owner;
    }

    public Pet(String name, String species, String breed, String fur, String pelage, PetSize size, double weight, LocalDate birth, LocalDate register, boolean castrated, String comments, Gender gender, boolean clientPacket, User user, Owner owner) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.fur = fur;
        this.pelage = pelage;
        this.size = size;
        this.weight = weight;
        this.birth = birth;
        this.register = register;
        this.castrated = castrated;
        this.comments = comments;
        this.gender = gender;
        this.clientPacket = clientPacket;
        this.user = user;
        this.owner = owner;
    }

    public Pet(String name, String species, String breed, String fur, String pelage, PetSize size, double weight, LocalDate birth, LocalDate register, boolean castrated, String comments, Gender gender, boolean clientPacket, User user) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.fur = fur;
        this.pelage = pelage;
        this.size = size;
        this.weight = weight;
        this.birth = birth;
        this.register = register;
        this.castrated = castrated;
        this.comments = comments;
        this.gender = gender;
        this.clientPacket = clientPacket;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public String getFur() {
        return fur;
    }

    public String getPelage() {
        return pelage;
    }

    public PetSize getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public LocalDate getRegister() {
        return register;
    }

    public boolean isCastrated() {
        return castrated;
    }

    public String getComments() {
        return comments;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isClientPacket() {
        return clientPacket;
    }

    public User getUser() {
        return user;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}