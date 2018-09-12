package by.itsm.server.service;

import by.itsm.server.annotation.Addition;
import by.itsm.server.model.Request;
import by.itsm.server.model.Response;

public interface RequestProcessor {
    Response process(Request request);
    boolean accept(Request request);
}
