package kamil.gaborek.tacocloud;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt;
    @NotBlank(message = "You  have to filled tne name an surname!")
    private String name;
    @NotBlank(message = "Type tne street")
    private String street;
    @NotBlank(message = "Type the city")
    private String city;
    @NotBlank(message = "Type the state")
    private String state;
    @NotBlank(message = "Type the zip")
    private String zip;

    @CreditCardNumber(message = "It is not the coorest number of credit card")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$" , message = "The needed format is MM/RR")
    private String ccExpiration;
    @Digits( integer = 3, fraction = 0, message = "CVV is not valid")
    private String ccCVV;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void  addDesign(Taco design){
        tacos.add(design);
    }

    @PrePersist
    void placedAt(){
        this.placedAt = new Date();
    }
}
