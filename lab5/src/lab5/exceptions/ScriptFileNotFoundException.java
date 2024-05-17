package lab5.exceptions;

import java.io.IOException;

/**
 * класс {@link ScriptFileNotFoundException} - исключение, возникающее при отсутствии указанного пользователем файла скрипта программы
 */

public class ScriptFileNotFoundException extends Error {

    public ScriptFileNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
