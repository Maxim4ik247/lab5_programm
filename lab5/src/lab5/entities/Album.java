package lab5.entities;

/**
 * класс {@link #Album} содержит информацию об альбоме группы: название и продолжительность
 */
public class Album {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long length; //Поле не может быть null, Значение поля должно быть больше 0

    public Album(String name, Long length) {
        this.name = name;
        this.length = length;
    }

    public Album() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "\n        <album>" +
                "\n            <name>" + name + "</name>" +
                "\n            <length>" + length + "</length>" +
                "\n        </album>";
    }

    public int compareTo(Album otherAlbum){
        if(otherAlbum == null)
            return -1;
        return Long.compare(this.getLength(), otherAlbum.getLength());
    }
}
