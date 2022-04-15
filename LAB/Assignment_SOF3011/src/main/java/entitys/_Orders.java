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
@Table(name = "orders", schema = "asm_java4")
public class _Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "cusId")
    private _Users cusId;
    @CreationTimestamp
    @Column(name = "date_order")
    private Timestamp dateOrder;
    @Basic
    @Column(name = "status")
    private Integer status =1;

    @OneToMany(mappedBy = "ordid")
    private List<_OrderDetails> item;

    @Column(name = "total")
    private long total;
}
