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
@Table(name = "products", schema = "asm_java4")
public class _Products {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name_product")
    private String nameProduct;

    @Column(name = "quantilys")
    private Integer quantilys;

    @Column(name = "price")
    private Double price;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "img")
    private String img;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private _Categories categoryId;

    @Column(name = "status")
    private Integer status=1;

    @CreationTimestamp
    @Column(name = "date_create")
    private Timestamp dateCreate;


    @ManyToOne
    @JoinColumn(name = "user_create")
    private _Users users ;

    @OneToMany(mappedBy = "proid")
    private List<_OrderDetails> orderDetails;
}
