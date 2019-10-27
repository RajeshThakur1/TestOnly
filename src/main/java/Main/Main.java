package Main;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Main {

	public static void main(String[] args) throws IOException {
		TestNG myTestNG = new TestNG();
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName("MySuite");
		XmlTest myTest = new XmlTest(mySuite);
		myTest.setName("MyTest");

		java.util.List<XmlClass> myClasses = new ArrayList<XmlClass>();

		XmlClass xc = new XmlClass("Login.Login");
		
		XmlClass xp = new XmlClass("WaferProcess.CreateWafer");
		
		// xc.setExcludedMethods(Arrays.asList("SearchMobile"));
		// xc.setExcludedMethods(Arrays.asList("InputSearchItem"));

		myClasses.add(xc);
		myClasses.add(xp);

		myTest.setXmlClasses(myClasses);

		java.util.List<XmlTest> myTests = new ArrayList<XmlTest>();
		myTests.add(myTest);

		mySuite.setTests(myTests);

		java.util.List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);

		myTestNG.setXmlSuites(mySuites);

		TestListenerAdapter tla = new TestListenerAdapter();
		myTestNG.addListener(tla);

		myTestNG.run();

	}// Main End
}
