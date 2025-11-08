package model;

import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor

public class Orders {
    private String orderId;
    private LocalDate orderDate;
    private String customerId;
}
