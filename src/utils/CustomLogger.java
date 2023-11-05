package utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * TODO. Custom Logger를 Singleton Pattern 으로 만들기
 * 싱글톤 패턴을 만드는 가장 단순한 방법으로는
 * 클래스 생성자를 private 로 만들어서 new 예약어로 생성하는 것을 막고
 * static 영역에 객체를 딱 한개 생성하고, 그것으로 조회하고 사용하는 방법이 있다.
 * **/
public class CustomLogger {
    // static 영역에 객체를 딱 1개만 생성.
    private static final CustomLogger instance = new CustomLogger();

    private final Logger logger;

    // 생성자를 private으로 선언해서 외부에서 new 예약어를 사용한 객체 생성을 못하게 막는다.
    private CustomLogger() {
        logger = LogManager.getLogger(CustomLogger.class.getName());
    }
    // public으로 열어서 객체 인스터스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용.
    public static CustomLogger getInstance() {
        return instance;
    }

    /*
    * log level
    * off < fatal < error < warn < info < debug < trace < all
    * **/

    public void error(String message) {
        logger.log(Level.ERROR, message);
    }

    public void warn(String message) {
        logger.log(Level.WARN, message);
    }

    public void info(String message) {
        logger.log(Level.INFO, message);
    }

    public void debug(String message) {
        logger.log(Level.DEBUG, message);
    }

}
