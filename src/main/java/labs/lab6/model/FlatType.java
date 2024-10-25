package labs.lab6.model;

import java.util.UUID;

public record FlatType(UUID id, String title, Integer roomAmount, Double area, String planUrl) {
    public FlatType(String title, Integer roomAmount, Double area, String planUrl){
       this(null, title, roomAmount, area, planUrl);
    }
}


//
//record DateRange(LocalDate from, LocalDate to) {
//    DateRange {
//        Objects.requireNonNull(from, "'from' date is required");
//
//        if (to != null && from.isAfter(to)) {
//            throw new IllegalArgumentException(
//                    "'from' date must be earlier than 'to' date"
//            );
//        }
//    }
//
//    DateRange(LocalDate from) {
//        this(from, null); // will call compact constructor validations too!
//    }
//}
