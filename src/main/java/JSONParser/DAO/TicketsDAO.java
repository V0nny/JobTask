package JSONParser.DAO;

import JSONParser.Models.Ticket;
import JSONParser.Models.TicketsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TicketsDAO {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File source = new File("src/main/resources/tickets.json");

    public List<Ticket> getAllTickets(){
        TicketsResponse response = new TicketsResponse();
        try {
            response = objectMapper.readValue(source, TicketsResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getTickets();
    }
}
