package util;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyProgramLogger {
    private static MyProgramLogger instance = null;

    private MyProgramLogger() {
    }

    public static Logger getMyLogger() {
        if (instance == null) {
            instance = new MyProgramLogger();
            return instance.getLogger();
        }
        return instance.getLogger();
    }

    private Logger getLogger() {
        String path = Const.Logger.LOGER_PATH;
        Logger logger = Logger.getLogger(Const.Logger.LOGGER_NAME);
        FileHandler fh = null;

        try {
            fh = new FileHandler(path, true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            logger.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }


        return logger;
    }
}




















