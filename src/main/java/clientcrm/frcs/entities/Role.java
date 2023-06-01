package clientcrm.frcs.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id_role")
  private int idRole;
  @Basic
  @Column(name = "roleName")
  private String roleName;

}
