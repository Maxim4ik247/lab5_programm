package lab5.entities;



import java.time.LocalDate;
import java.util.Objects;

/**
 * класс {@link #MusicBand} содержит информацию о музыкальной группе:
 * id, name, {@link Coordinates}, creationDate, numberOfParticipants, {@link MusicGenre}, {@link Album}
 */

public class MusicBand implements Comparable<MusicBand> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer numberOfParticipants; //Поле не может быть null, Значение поля должно быть больше 0
    private MusicGenre genre; //Поле не может быть null
    private Album bestAlbum; //Поле может быть null


    @Override
    public String toString() {

        return "\n    <band>" +
                "\n        <id>" + id + "</id>" +
                "\n        <name>" + name + "</name>" +
                "\n        <creationDate>" + creationDate + "</creationDate>" +
                "\n        <numberOfParticipants>" + numberOfParticipants + "</numberOfParticipants>" +
                "\n        <genre>" + genre.ordinal() + "</genre>" +
                (bestAlbum==null?"":bestAlbum) +
                (coordinates==null?"":coordinates) +
                "\n    </band>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicBand musicBand = (MusicBand) o;
        return Objects.equals(id, musicBand.id) && Objects.equals(name, musicBand.name) && Objects.equals(coordinates, musicBand.coordinates) && Objects.equals(creationDate, musicBand.creationDate) && Objects.equals(numberOfParticipants, musicBand.numberOfParticipants) && genre == musicBand.genre && Objects.equals(bestAlbum, musicBand.bestAlbum);
    }

    @Override
    public int hashCode() {
        return id;
    }

    public MusicBand() {

    }

    public MusicBand(Integer id, String name, LocalDate creationDate,
                     Integer numberOfParticipants, MusicGenre genre) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.genre = genre;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Album getBestAlbum() {
        return bestAlbum;
    }

    public void setBestAlbum(Album bestAlbum) {
        this.bestAlbum = bestAlbum;
    }


    public int compareTo(MusicBand otherBand){
        return this.id.compareTo(otherBand.getId());
    }
}
