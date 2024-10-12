package labs.lab4;

import lombok.AllArgsConstructor;

import java.time.*;

/**
 * The {@code Accounting} class represents an accounting record for a specific
 * flat, including details such as the month, year, required amount, and actual
 * amount paid. It also stores the time when the payment was made.
 *
 * <p>This class is intended to be used for tracking payments related to a specific
 * flat for a given month and year.</p>
 *
 * <p>The following fields are included:</p>
 * <ul>
 *   <li>{@link Flat} flat - The flat associated with this accounting record.</li>
 *   <li>{@link Month} month - The month for which the accounting is recorded.</li>
 *   <li>{@link Year} year - The year for which the accounting is recorded.</li>
 *   <li>{@link LocalDateTime} payTime - The date and time when the payment was made.</li>
 *   <li>{@link Double} reqAmount - The required amount to be paid.</li>
 *   <li>{@link Double} actualAmount - The actual amount that was paid.</li>
 * </ul>
 *
 * @version 1.0
 * @since 2024-08-22
 */
@AllArgsConstructor
public class Accounting {

    private Flat flat;
    private Month month;
    private Year year;
    private LocalDateTime payTime;
    private Double reqAmount;
    private Double actualAmount;

    @Override
    public String toString() {
        int p = Period.between(LocalDate.MIN, LocalDate.MAX).getDays();
        return "Accounting{" +
                "flat=" + flat.getNumber() +
                ", owner = " + flat.getOwner().getFirstName() + " " + flat.getOwner().getLastName() +
                ", date =" + year + "-" + month +
                ", payTime=" + payTime +
                ", actualAmount=" + actualAmount +
                '}';
    }
}
