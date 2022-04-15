package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories", schema = "asm_java4")
public class _Categories {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private _Users user;

    @OneToMany( mappedBy = "categoryId")
    private List<_Products> products;

    @Column(name = "status")
    private Integer status=1;
    @CreationTimestamp
    @Column(name = "date_create")
    private Timestamp dateCreate;
}
