package JSONParser.Calculators;

import JSONParser.Models.Ticket;
import JSONParser.DAO.TicketsDAO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PriceCalculator {

    public void showResult(TicketsDAO ticketsDAO) throws IOException {
        double diff = calculateDifferenceOfPrices(ticketsDAO);
        System.out.println("Разница между средней ценой и медианой для полета между городами Владивосток и Тель-Авив: " + diff);
    }

    private double calculateDifferenceOfPrices(TicketsDAO tickets) throws IOException {
        return findMediumPrice(tickets) - findMedianOfPrices(tickets);
    }

    private double findMediumPrice(TicketsDAO tickets) {
        List<Double> prices = findPricesByRoute(tickets);
        double total = 0d;

        for (Double price : prices) {
            total += price;
        }

        return total/prices.size();
    }

    private double findMedianOfPrices(TicketsDAO tickets) {
        if(findPricesByRoute(tickets).size() % 2 == 0) {
            return ((findPricesByRoute(tickets).get((findPricesByRoute(tickets).size() / 2) - 1)
                    + findPricesByRoute(tickets).get(findPricesByRoute(tickets).size() / 2)) / 2);
        } else {
            return findPricesByRoute(tickets).get(findPricesByRoute(tickets).size()/2);
        }
    }

    private List<Double> findPricesByRoute(TicketsDAO tickets) {
        List<Double> pricesList = tickets.getAllTickets().stream()
                .filter(ticket -> ticket.getOrigin_name().equals("Владивосток"))
                .filter(ticket -> ticket.getDestination_name().equals("Тель-Авив"))
                .map(Ticket::getPrice)
                .collect(Collectors.toList());

        Collections.sort(pricesList);

        return pricesList;
    }
}
