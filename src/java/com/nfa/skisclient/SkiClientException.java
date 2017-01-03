package com.nfa.skisclient;

/**
 * Created by barclakj on 03/01/2017.
 */
public class SkiClientException extends Exception  {

    public SkiClientException() {
        super();
    }

    public SkiClientException(String msg) {
        super(msg);
    }

    public SkiClientException(Throwable e) {
        super(e);
    }
}
