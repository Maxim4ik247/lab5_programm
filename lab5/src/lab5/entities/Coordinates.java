package lab5.entities;

/**
 * класс {@link #Coordinates} содержит информацию о координатах группы: x; y
 */
public class Coordinates {
    private long x;
    private Float y; //Поле не может быть null

    public Coordinates(long x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {

    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "\n        <coordinates>" +
                "\n            <x>" + x + "</x>" +
                "\n            <y>" + y + "</y>" +
                "\n        </coordinates>";
    }

    public int compareTo(Coordinates otherBand){
        if(otherBand == null)
            return -1;
        return Long.compare(this.getX(), otherBand.getX());
    }
}
