import org.openqa.selenium.WebDriver;

public class App {
        public static void main(String args[]) {
        	WebDriver driver=new ChromeDriver();
        	driver.get("https://www.google.com/");
        	String actualURL=driver.getTitle();
        	String expectedURL="Google";
        	if(actualURL.equals(expectedURL)) {
        		System.out.println("True");
        	}
        	else {
        		System.out.println("False");
        	}
        }
}
