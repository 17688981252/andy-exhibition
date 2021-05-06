package com.zel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author zel
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ZelExhibitionApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ZelExhibitionApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
            "   ___            _         _     _         _      _        _                    \n" +
                "  | __|   __ __  | |_      (_)   | |__     (_)    | |_     (_)     ___    _ _    \n" +
                "  | _|    \\ \\ /  | ' \\     | |   | '_ \\    | |    |  _|    | |    / _ \\  | ' \\   \n" +
                "  |___|   /_\\_\\  |_||_|   _|_|_  |_.__/   _|_|_   _\\__|   _|_|_   \\___/  |_||_|  \n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' \n"
        );
    }
}