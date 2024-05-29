package com.flab.mame.profile.domain;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Point;

import com.flab.mame.profile.ProfileUpdateRequest;
import com.flab.mame.profileimage.domain.ProfileImage;
import com.flab.mame.user.domain.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30)
	@NotBlank
	private String nickname;

	@Column(nullable = false)
	@NotNull
	private int age;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private GenderType genderType;

	@Column(nullable = false, length = 500)
	@NotBlank
	private String introduction;

	@OneToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(columnDefinition = "geometry(Point, 4326)")
	private Point location;
	
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProfileImage> profileImages = new ArrayList<>();

	public void updateProfile(final ProfileUpdateRequest request) {
		this.nickname = request.getNickname();
		this.age = request.getAge();
		this.genderType = request.getGenderType();
		this.introduction = request.getIntroduction();
	}

	public void addProfileImage(final ProfileImage newProfileImage) {
		profileImages.add(newProfileImage);
	}

}
