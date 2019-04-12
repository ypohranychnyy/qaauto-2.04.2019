1. Download + Install IDEA Community Edition
2. Download + Install Java JDK 1.8
3. Download + unpack/install  Chrome Webdriver (3.8.1)
 •. Input path into IDEA:
         System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe");

4. Add the dependencies into pom.xml file:
 <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-chrome-driver</artifactId>
            <version>33.141.59</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-api</artifactId>
            <version>3.8.1</version>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-chrome-driver</artifactId>
            <version>3.8.1</version>
        </dependency>
    </dependencies>
 5.