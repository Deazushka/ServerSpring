package by.itsm.server.service;

import by.itsm.server.annotation.Addition;
import by.itsm.server.model.Request;
import by.itsm.server.model.Response;
import org.springframework.stereotype.Component;

@Component
public class SimpleProcessor implements RequestProcessor {

    @Override
    @Addition
    public Response process(Request request) {
        return new Response(request.getName() + ", hello!");
    }

    @Override
    public boolean accept(Request request) {
        return true;
    }
}
