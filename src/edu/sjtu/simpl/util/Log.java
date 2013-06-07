package edu.sjtu.simpl.util;

public class Log {
	public static void debug(String msg)
	{
		System.out.println("[debug]:"+msg);
	}
	
	public static void error(String msg)
	{
		System.out.println("[Error]:"+msg);
	}
	
	public static void info(String msg)
	{
		System.out.println("[Info]:"+msg);
	}
	
	public static void rslt(String msg)
	{
		System.out.println("SimPL>"+msg);
	}
}
