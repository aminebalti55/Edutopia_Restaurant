package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "Report")
public class Report {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "report_id")
        private long reportId;

        @NotBlank(message = "Title cannot be blank")
        @Size(max = 50, message = "Title cannot exceed 50 characters")
        private String title;

        @NotBlank(message = "Note cannot be blank")
        @Size(max = 200, message = "Note cannot exceed 200 characters")
        private String note;

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_at")
        private Date createdAt;

        private boolean traitee;

        @Enumerated(EnumType.STRING)
        private Type type;
        @JsonIgnore
        @ManyToOne(cascade = CascadeType.PERSIST)
        private User user;
}



