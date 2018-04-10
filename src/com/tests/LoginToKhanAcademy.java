package com.tests;

import java.util.concurrent.TimeUnit;

import org.jboss.netty.util.internal.SystemPropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.AccountLogIn;
import com.pages.FaceBookLogin;

 
public class LoginToKhanAcademy {
	WebDriver driver;
	AccountLogIn objLogin;
	FaceBookLogin objFBLogin;
	
	@BeforeMethod
	public void setUp(){
		 driver = new FirefoxDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		 driver.get("https://www.khanacademy.org/");		
	}
	
  @AfterMethod
	public void closureActivities(){
		driver.close();
	}
	
	@Test(priority=0,description="Login to the application with Account credentials")
	public void loginWithAcademyCredentials(){
		objLogin= new AccountLogIn(driver);
		objLogin.loginWithCredentials("b12test@gmail.com", "Test@12345");	
		String actValue =objLogin.getUserProfileName();
		String expValue="Name";
		Assert.assertTrue(actValue.contains(expValue));
		//logging out
		String msg=objLogin.logOut();
		Assert.assertTrue(msg.contains("Khan"), "Logged Out Successfully");
	}
	 
	
	//@Test(priority=1,description="Login to the application with facebook credentials")
	public void loginWithFBCredentials(){
		objFBLogin = new FaceBookLogin(driver);
		objFBLogin.clickLoginPage();
		objFBLogin.clickContinuewithFB();
		String s=objFBLogin.FBUserLogin("leela.krishna101@gmail.com", "anil123");
		System.out.println(s);
	}
	
	@Test(description ="A simple negative Scenario with blank login")
	public void blankLogin(){
		//objLogin.loginBlank();
		 driver.get("https://www.khanacademy.org/");	
		 driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
		 driver.findElement(By.xpath("//input[contains(@class,'input') and @type='text']")).sendKeys(Keys.TAB);
		 String error=driver.findElement(By.xpath("//*[contains(@class,'fieldRequirement') and contains(text(),'An email or username')]")).
		 getText();
		 System.out.println("Error: "+error);
	}



}
