package labs.lab2.service;

import labs.lab2.Accounting;
import labs.lab2.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AccountingService {
    private List<Accounting> accountings;

    public List<Accounting> getByPerson(Person person){
        return accountings.stream().filter(ac -> ac.getFlat().getOwner().equals(person))
                .sorted(Comparator.comparing(Accounting::getPayTime).reversed()).toList();
    }

    public List<Accounting> getByPersonFromEarlier(Person person){
        return accountings.stream().filter(ac -> ac.getFlat().getOwner().equals(person))
                .sorted(Comparator.comparing(Accounting::getPayTime)).toList();
    }

}
