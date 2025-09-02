import config.AppConfig;
import controller.CliController;
import util.InitialData;

public class Main {
    public static void main(String[] args) {
        InitialData.populate();
        
        CliController controller = AppConfig.build();
        
        controller.start();
    }
}