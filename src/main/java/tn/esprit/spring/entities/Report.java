package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private int reportId;

    @NotBlank(message = "Title cannot be blank")
    @Length(max = 50 , message = "Note cannot exceed 200 characters" )
    private String title;

    @NotBlank (message = "Note cannot be blank")
    @Size(max = 200, message = "Note cannot exceed 200 characters")
    private String note;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    private boolean traitee ;

    public Report(String title, String note, Type type, User user) {
        this.title = title;
        this.note = note;
        this.type = type;
        this.user = user;
        this.createdAt = new Date();
        this.traitee = false;
    }


    @Enumerated(EnumType.STRING)
    private Type type;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

}
