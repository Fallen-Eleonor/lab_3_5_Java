package ntukhpi.kn221a.kro.webappsyrlab3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private boolean active;

    @Column
    private LocalTime callTimeSum;

    public Phone(Manager manager, String number, boolean active, LocalTime callTimeSum) {
        this.id = 0L;
        this.manager = manager;
        this.number = number;
        this.active = active;
        this.callTimeSum = callTimeSum;
    }
}
