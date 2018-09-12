package by.itsm.server.model;

import java.util.Objects;

public class Request {
    private String name;
    private String message;

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public Request() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Request(String name, String message) {
        this.name = name;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(name, request.name) &&
                Objects.equals(message, request.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, message);
    }
}
