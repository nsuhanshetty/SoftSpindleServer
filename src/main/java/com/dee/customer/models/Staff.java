package com.dee.customer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by akash.v on 16/04/15.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonSnakeCase
@Getter
@Setter
@Table(name = "staff")
public class Staff extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private long id;

    @Column(name = "staff_name")
    @JsonProperty
    private String name;

    @Column(name = "mobile_no")
    @JsonProperty
    private String mobileNo;

    @Column(name = "phone_no")
    @JsonProperty
    private String phoneNo;

    @Column(name = "address")
    @JsonProperty
    private String address;

    @Column(name = "email")
    @JsonProperty
    private String email;

    @Column(name = "image")
    @JsonProperty
    private String image;

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonIgnore
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public Staff(Staff  customer) {
        super();
    }
}