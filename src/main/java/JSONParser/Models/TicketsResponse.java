package JSONParser.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketsResponse {
    private List<Ticket> tickets;
}
