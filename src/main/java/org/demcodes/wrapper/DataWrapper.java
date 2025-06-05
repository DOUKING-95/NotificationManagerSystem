package org.demcodes.wrapper;

import org.demcodes.Model.Channel;
import org.demcodes.Model.Employee;
import org.demcodes.Model.Message;

import java.util.ArrayList;
import java.util.List;

public class DataWrapper {
    public static DataWrapper instance;
    private List<Employee> employees  = new ArrayList<>();;
    private List<Message> messages = new ArrayList<>();;
    private List<Channel> channels = new ArrayList<>();;





    public static  DataWrapper getInstance() {
        if (instance == null) {
            instance = new DataWrapper();
        }
        return instance;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}