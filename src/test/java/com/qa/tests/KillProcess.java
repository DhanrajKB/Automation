package com.qa.tests;

import java.util.ArrayList;
import java.util.List;

public class KillProcess {

	public static void main(String[] args) {
		KillProcess.killTaskManagerProcess("excel.exe");
	}
	
	public static void killTaskManagerProcess(String processName01){
		List<String> processToKill = new ArrayList<String>();
//		processToKill.add("excel.exe");
		processToKill.add("NewAccounts.xlsx - Excel");
		processToKill.add("Expense Sheet.xlsx - Excel");
//		processToKill.add("Time Table New.xlsx - Excel");
		for (String processName : processToKill) {
			try {
				Runtime runtime = Runtime.getRuntime();
				System.out.println("Opearting System :- "+ System.getProperty("os.name"));
				if (System.getProperty("os.name").toLowerCase().contains("windows")) {
					System.out.println("Inside if");
//					runtime.exec("taskkill /t /f /im "+processName);
					//runtime.exec("taskkill /T /F /PID " + Long.parseLong(processName)); // Supply the PID value as string
		            runtime.exec("taskkill /F /FI \"WINDOWTITLE eq " + processName + "\" /T"); // Supply the window title bar text.
		            // If you want to kill only a single instance of the 
		            // named process then get its PID value and use:
		            // "taskkill /T /F /PID PID_Value"  OR you can provide
		            // the window title and use:
		            // "taskkill /F /FI \"WINDOWTITLE eq " + processName + "\" /T"
				}
				runtime.freeMemory();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
