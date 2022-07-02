package comp3350.team10.application;


//import comp3350.team10.application.Services;

import comp3350.team10.persistence.SharedDB;

public class Main
{
	public static final String dbName = "db";
	private static String dbPathName = "database/db";

	public static void main(String[] args)
	{
		startUp();

		shutDown();
		System.out.println("All done");
	}

	public static void startUp()
	{
		SharedDB.start();
	}

	public static void shutDown()
	{
		SharedDB.close();
	}


	public static String getDBPathName() {
		if (dbPathName == null)
			return dbName;
		else
			return dbPathName;
	}

	public static void setDBPathName(String pathName) {
		System.out.println("Setting DB path to: " + pathName);
		dbPathName = pathName;
	}
}
