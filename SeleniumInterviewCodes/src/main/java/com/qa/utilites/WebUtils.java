package com.qa.utilites;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import com.qa.base.TestBase;

public class WebUtils extends TestBase {
	
	public void takeEntireScreenShot(WebDriver driver,String fileName){
		Screenshot screenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		try {
			ImageIO.write(screenShot.getImage(), "jpg", new File(userdir+"\\ScreenShots\\" + fileName + currentDate+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void takeElementScreenShot(WebDriver driver,WebElement ele,String fileName){
		Screenshot screenShot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver,ele);
//		Screenshot screenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver,ele);
		try {
			ImageIO.write(screenShot.getImage(), "jpg", new File(userdir+"\\ScreenShots\\" + fileName + currentDate+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
