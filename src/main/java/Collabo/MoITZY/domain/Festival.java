package Collabo.MoITZY.domain;

import Collabo.MoITZY.domain.embed.Address;
import Collabo.MoITZY.domain.embed.Period;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "festival")
@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString(of = {
    "id", "name", "img", "host", "organization", "inquiry", "supporter", "description", "place", "period"
})
public class Festival {

    @Id @GeneratedValue
    @Column(name = "festival_id")
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String img;

    @NotBlank
    private String host;

    @NotBlank
    private String organization;

    @NotBlank
    private String inquiry;

    @NotBlank
    private String supporter;

    private String description;

    @Embedded
    private Address place;

    @Embedded
    private Period period;

    @OneToMany(mappedBy = "festival") // Festival은 Member에 의해 여러 ROI로 설정될 수 있다
    private List<ROI> roiList = new ArrayList<>();

    @OneToMany(mappedBy = "festival") // Festival은 여러개의 Review를 가질 수 있다
    private List<Review> reviews = new ArrayList<>();

    public Festival(String name, String img, String host, String organization, String inquiry, String supporter, String description, Address place, Period period) {
        this.name = name;
        this.img = img;
        this.host = host;
        this.organization = organization;
        this.inquiry = inquiry;
        this.supporter = supporter;
        this.description = description;
        this.place = place;
        this.period = period;
    }

    public void addROI(ROI roi) {
        roiList.add(roi);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }
}