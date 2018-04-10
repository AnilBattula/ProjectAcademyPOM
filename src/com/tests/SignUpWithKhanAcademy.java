package com.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.AccountSignUp;
import com.pages.HomePage;

public class SignUpWithKhanAcademy {
	WebDriver driver;
	AccountSignUp objAccountSignUp;
	HomePage objHomePage;
	
	@BeforeMethod
	public void setUp(){
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
		driver.get("https://www.khanacademy.org/");
	}
	@AfterMethod
	public void closureActivities(){
		driver.close();
	}
	
	//signingUp as a Learner
	@Test(priority=0,description="signup to khan academy with acccount/email")
	public void signUpwithEmailLearner(){
		objAccountSignUp = new AccountSignUp(driver);
		objAccountSignUp.signUpHome();
		objAccountSignUp.signUpLearner(11,06,1989);
		objAccountSignUp.signUpWithMailUserDetails("a1battula@gmail.com", "anil", "battula", "anil12345");
		String title=driver.getTitle();
		if (title.contains("Dashboard")) System.out.println("SignUp is successful");	
		objHomePage = new HomePage(driver);
		objHomePage.samplePreferences();
		objHomePage.logOut();
	}
	

	//SigningUp as a Teacher
	//@Test(description="signup to khan academy with Tecaher details")
	public void signUpwithMailTeacher(){
	objAccountSignUp=new AccountSignUp(driver);
	objAccountSignUp.signUpHome();
	objAccountSignUp.signUpTeacher();
	objAccountSignUp.signUpWithMailUserDetails("teahera@gmail.com", "anil", "battula", "anil12345");
	String title=driver.getTitle();
	if (title.contains("Dashboard")) System.out.println("SignUp is successful");
	}
	
	
	
	
	
}
