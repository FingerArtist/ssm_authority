package ssm_authority.exception;

/**
 * @Description
 * @Author dzh
 * @date 2020/9/7 10:40
 */
public class CustomException extends Exception {

    private String message;
    public CustomException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

}
