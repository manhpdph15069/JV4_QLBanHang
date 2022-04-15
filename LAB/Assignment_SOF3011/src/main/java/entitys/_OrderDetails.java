package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_details", schema = "asm_java4")
public class _OrderDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "odeid")
    private int odeid;

    @ManyToOne
    @JoinColumn(name = "ordid")
    private _Orders ordid;

    @ManyToOne
    @JoinColumn(name = "proid")
    private _Products proid;
    @Basic
    @Column(name = "quantity")
    private int quantity;
    @Basic
    @Column(name = "price")
    private Double price;
    @Column(name = "status")
    private int status=1;


}
