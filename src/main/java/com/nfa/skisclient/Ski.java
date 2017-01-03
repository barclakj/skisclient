package com.nfa.skisclient;


/**
 * Created by barclakj on 02/01/2017.
 */
public interface Ski {
    void revokeIdentity(String identity, String token) throws SkiClientException;
    String grantToken(String identity, String token) throws SkiClientException;
    String createToken(String identity) throws SkiClientException;
    byte[] createKey(String keyName, byte[] keyValue, String token) throws SkiClientException;
    byte[] createKey(String keyName, String token) throws SkiClientException;
    byte[] getKey(String keyName, String token) throws SkiClientException;
}
