package Collabo.MoITZY.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString
public class Inform {

    @Id @GeneratedValue
    @Column(name = "inform_id")
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private LocalDateTime writeDate;

    public Inform(String title, String content, LocalDateTime writeDate) {
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
    }
}