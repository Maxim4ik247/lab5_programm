package lab5.exceptions;

import java.io.IOException;

/**
 * класс {@link IOBandsFileException} - исключение, возникающее при невозможности чтения или записи файла
 */

public class IOBandsFileException extends RuntimeException {
    public IOBandsFileException(String errorMessage) {
        super(errorMessage);
    }
}
