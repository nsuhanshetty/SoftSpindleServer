package com.dee.OMS.models;

import com.dee.customer.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by akash.v on 17/04/15.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonSnakeCase
@Table(name = "order_items")
public class OrderItem extends BaseEntity{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    long id;

    @Column(name="order_id")
    long orderId;

    @JsonProperty
    @Column(name="quantity")
    int orderQuantity;

    @JsonProperty
    @Column(name="product_id")
    String productId;

    @JsonProperty
    @Column(name="price")
    float price;

    @JsonProperty
    @Column(name="status")
    OrderItemStatus status;
}
