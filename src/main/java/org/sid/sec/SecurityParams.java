package org.sid.sec;

public interface SecurityParams {
	public static final String JWT_HEADER_NAME = "Autorization";
	public static final String SECRET = "madyeradil@gmail.com";
	public static final long EXPIRATION = 10*24*3600*1000;
	public static final String HEADER_PREFIX = "Bearer ";
}
