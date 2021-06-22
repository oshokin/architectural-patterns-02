package cinema;

import cinema.controllers.ClientController;
import cinema.models.Client;
import cinema.mappers.ClientMapper;
import cinema.utils.IdentityMapper;
import cinema.views.ClientView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class PersonalMVC {

    private Properties properties = new Properties();
    private final IdentityMapper cache = IdentityMapper.getInstance();

    public static void main(String[] args) {
        PersonalMVC tester = new PersonalMVC();
        tester.initializeProperties();
        try (Connection connection = tester.establishConnection()) {
            //сделаем на примере из предыдущего ДЗ, а моделью у нас будет клиент
            tester.showMyPersonalMVCRealization(connection);
        } catch (Exception e) {
            System.out.println("Couldn't make magic work:");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void initializeProperties() {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            System.out.println("Couldn't make magic work:");
            e.printStackTrace();
        }
    }

    private Connection establishConnection() throws Exception {
        Class.forName(properties.getProperty("jdbc.driver.name"));
        return DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.user"),
                properties.getProperty("jdbc.password"));
    }

    private void showMyPersonalMVCRealization(Connection connection) throws Exception {
        ClientMapper mapper = new ClientMapper(connection);

        System.out.println("Let's try to find our model");
        Client model = mapper.findById(100);
        System.out.printf("And our model is: %s%n", model);
        if (model == null) {
            System.out.println("Shiiet! We got no model!");
            return;
        }

        ClientView view = new ClientView();
        ClientController controller = new ClientController(model, view);
        controller.updateView();

        controller.setClientFirstName("Oleg");
        controller.setClientLastName("Shokin");

        controller.updateView();
        System.out.println("Потрачено! That's all folks! Esto es lo todo que pude mostrar");
    }

}