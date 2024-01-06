package common.exception;

public class FailLoginDueToBannedException extends AimsException{
    public FailLoginDueToBannedException() {
        super("ERROR: Fail to Login. Due to be banned!");
    }
}
