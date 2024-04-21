package pl.example.api.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsDTO {


   private List<OrderItemDTO> orderItems;


}
