package clientcrm.frcs.Exceptions;

/**
 * @author slim
 * @Project
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Could not find the user with id "+ id);
    }
}
