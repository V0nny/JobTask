package JSONParser;

import JSONParser.Calculators.*;
import JSONParser.DAO.TicketsDAO;

public class Main {
    public static void main(String[] args) throws Exception {
        TicketsDAO ticketsDAO = new TicketsDAO();
        PriceCalculator calculator = new PriceCalculator();
        TravelTimeCalculator travelTimeCalculator = new TravelTimeCalculator();

        calculator.showResult(ticketsDAO);
        travelTimeCalculator.showTravelTimeForCarriers(ticketsDAO);
    }
}