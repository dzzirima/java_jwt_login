package com.zirima.enties;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @SequenceGenerator(
            name = "app_user_sequence" ,
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "app_user_sequence"
    )

    private  Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;

}
