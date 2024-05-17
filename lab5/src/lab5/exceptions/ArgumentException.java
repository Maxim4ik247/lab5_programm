package lab5.exceptions;

/**
 * класс {@link ArgumentException} - исключение, возникающее при неправильном вводе аргумента пользователем
 */

public class ArgumentException extends Error{
    public ArgumentException(String message) {
        super(message);
    }
}
