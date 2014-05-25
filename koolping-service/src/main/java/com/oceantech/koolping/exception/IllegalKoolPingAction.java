package com.oceantech.koolping.exception;

/**
 * @author Sanjoy Roy
 */
public class IllegalKoolPingAction extends BusinessException {

    public IllegalKoolPingAction(String message) {
        super(message);
    }

    public IllegalKoolPingAction(String message, Throwable cause) {
        super(message, cause);
    }
}
