package lab5.exceptions;

/**
 * класс {@link BandsFileNotFoundException} - исключение, возникающее при невозможности получения доступа к файлу
 */

public class BandsFileNotFoundException extends RuntimeException {
    public BandsFileNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
