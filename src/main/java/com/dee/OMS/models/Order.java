package com.dee.OMS.models;

import com.dee.customer.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * Created by akash.v on 17/04/15.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonSnakeCase
@Table(name = "orders")
public class Order extends BaseEntity{


    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @JsonProperty
    long orderId;

    @JsonProperty
    @Column(name="customer_id")
    String mobileNo;

    @JsonProperty
    @Column(name="created_at")
    Date createdAt;

    @JsonProperty
    @Column(name="promise_date")
    Date promiseDate;

    @JsonProperty
    @Column(name="price")
    float price;

    @JsonProperty
    @Transient
    List<OrderItem> orderItems;

    @JsonProperty
    @Column(name="current_payment")
    float currentPayment;

    public void setItemIds() {
        for(OrderItem o : orderItems){
            o.setId(orderId);
            o.setStatus(OrderItemStatus.created);
        }
    }
}
