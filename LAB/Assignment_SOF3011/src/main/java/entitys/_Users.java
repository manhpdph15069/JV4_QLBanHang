package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "asm_java4")
public class _Users{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "fullname")
    private String fullname;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "gender")
    private Byte gender;
    @Basic
    @Column(name = "pass")
    private String pass;
    @CreationTimestamp
    @Column(name = "date_created")
    private Timestamp dateCreated;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "address")
    private String address;

    @Column(name = "isActive")
    private Byte isActive=1;

    @Column(name = "isAdmin")
    private Byte isAdmin =0;

    @OneToMany(mappedBy = "user")
    private List<_Categories> categories;
    @OneToMany(mappedBy = "users")
    private List<_Products> products;

    @OneToMany(mappedBy = "cusId")
    private List<_Orders> orders;
}
