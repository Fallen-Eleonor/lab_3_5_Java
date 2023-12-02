package ntukhpi.kn221a.kro.webappsyrlab3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String secondName;

    @Column(nullable = false, length = 6, unique = true)
    private String code;

    @Column(nullable = false)
    private LocalDate dateStartWork;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private boolean isVIP;

    public Manager(String firstName, String secondName, String code, LocalDate dateStartWork, Status status, boolean isVIP) {
        this.id = 0L;
        this.firstName = firstName;
        this.secondName = secondName;
        this.code = code;
        this.dateStartWork = dateStartWork;
        this.status = status;
        this.isVIP = isVIP;
    }

    public Manager(String firstName, String secondName, String code) {
        this(firstName, secondName, code, LocalDate.now(), Status.ACTIVE, false);
    }

    public enum Status {
        ACTIVE,
        WEEKEND,
        VACATION
    }

    public boolean isIsVIP() {
        return isVIP;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setIsVIP(boolean isVIP) {
        this.isVIP = isVIP;
    }
}