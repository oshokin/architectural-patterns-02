package cinema.controllers;

import cinema.models.Client;
import cinema.views.ClientView;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClientController {

    private Client model;
    private ClientView view;

    public Integer getClientId() {
        return model.getId();
    }

    public String getClientPhoneNumber() {
        return model.getPhoneNumber();
    }

    public String getClientFirstName() {
        return model.getFirstName();
    }

    public String getClientLastName() {
        return model.getLastName();
    }

    public void setClientId(Integer id) {
        model.setId(id);
    }

    public void setClientPhoneNumber(String phoneNumber) {
        model.setPhoneNumber(phoneNumber);
    }

    public void setClientFirstName(String firstName) {
        model.setFirstName(firstName);
    }

    public void setClientLastName(String lastName) {
        model.setLastName(lastName);
    }

    public void updateView() {
        view.showDetails(model.toString());
    }

}