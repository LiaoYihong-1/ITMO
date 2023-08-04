package com.example.demo_back.Dao.House;

import com.example.demo_back.Dao.Account.AccountJpa;
import com.example.demo_back.Dao.Address.AddressJpa;
import com.example.demo_back.Dao.Contact.ContactJpa;
import com.example.demo_back.Dao.Enums.Gender;
import com.example.demo_back.Dao.Enums.House_Type;
import com.example.demo_back.Dao.Enums.PostgreSQLEnumType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "House")
@TypeDef(name="pgsql_enum",typeClass = PostgreSQLEnumType.class)
public class HouseJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,name="house_type")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private House_Type house_type;
    @Column(nullable = false,name="address_id")
    private Integer address_id;
    @ManyToMany(cascade = CascadeType.REFRESH,mappedBy = "houses",fetch = FetchType.LAZY)
    private List<AccountJpa> users;
}
