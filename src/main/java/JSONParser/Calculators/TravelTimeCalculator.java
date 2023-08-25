package JSONParser.Calculators;

import JSONParser.DAO.TicketsDAO;
import JSONParser.Models.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TravelTimeCalculator {

    public void showTravelTimeForCarriers(TicketsDAO ticketsDAO) {
        Map<String, Ticket> map = getMapOfCarriersAndTickets(ticketsDAO);
        for (String carrier : map.keySet()) {
            System.out.print("Для перевозчика " + carrier);
            System.out.println(travelTime(getTravelTime(map.get(carrier))));
        }
    }

    private String travelTime(long timeInMillis) {
        long temp = TimeUnit.MILLISECONDS.toMinutes(timeInMillis);
        long hours = temp/60;
        long minutes = temp - hours * 60;
        String time = String.format(" минимальноe время в пути: %d часов, %d минут", hours, minutes);

        return time;
    }

    private long getTravelTime(Ticket ticket){
        String departureDate = ticket.getDeparture_date();
        String departureTime = ticket.getDeparture_time();
        String arrivalDate = ticket.getArrival_date();
        String arrivalTime = ticket.getArrival_time();

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyHH:mm");
        Date departure = null;
        Date arrive = null;
        try {
            departure = formatter.parse(departureDate + departureTime);
            arrive = formatter.parse(arrivalDate + arrivalTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return arrive.getTime() - departure.getTime();
    }

    private Ticket compareTravelTime(Ticket ticket1, Ticket ticket2){
        if (getTravelTime(ticket1) > getTravelTime(ticket2)) {
            return ticket2;
        } else return ticket1;
    }


    private Map<String, Ticket> getMapOfCarriersAndTickets(TicketsDAO tickets) {
        List<Ticket> ticketList = findTicketByRoute(tickets);
        Map<String, Ticket> ticketMap = new HashMap<>();

        for(Ticket ticket : ticketList) {
            if (!ticketMap.containsKey(ticket.getCarrier())) {
                ticketMap.put(ticket.getCarrier(), ticket);
            } else {
                Ticket fasterOne = compareTravelTime(ticketMap.get(ticket.getCarrier()), ticket);
                ticketMap.put(ticket.getCarrier(), fasterOne);
            }
        }
        return ticketMap;
    }

    private List<Ticket> findTicketByRoute(TicketsDAO tickets) {
        List<Ticket> ticketList = tickets.getAllTickets().stream()
                .filter(ticket -> ticket.getOrigin_name().equals("Владивосток"))
                .filter(ticket -> ticket.getDestination_name().equals("Тель-Авив"))
                .collect(Collectors.toList());

        return ticketList;
    }

}
