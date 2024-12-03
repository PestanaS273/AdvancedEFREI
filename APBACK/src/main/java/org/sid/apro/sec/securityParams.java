package org.sid.apro.sec;

public class securityParams {
    public static final String JWTheaderName="Authorization";
    public static final String SECRET="apro@oubou.net";
    public static final long EXPIRATION=10*24*3600*1000;
    public static final String HEADER_PREFIX="Bearer ";
}
