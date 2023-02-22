package model.eventlogging;

public interface LogPrinter {
    //EFFECTS: prints the log
    void printLog(EventLog el) throws LogException;
}
