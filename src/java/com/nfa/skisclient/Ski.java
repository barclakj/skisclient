package com.nfa.skisclient;


/**
 * Created by barclakj on 02/01/2017.
 */
public interface Ski {
    void revokeIdentity(String identity, String token) throws SkiClientException;
    String grantToken(String identity, String token) throws SkiClientException;
    String createToken(String identity) throws SkiClientException;
    String createKey(String keyName, String keyValue, String token) throws SkiClientException;
    String createKey(String keyName, String token) throws SkiClientException;
    String getKey(String keyName, String token) throws SkiClientException;
}
