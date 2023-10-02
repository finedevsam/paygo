package com.paygo.paygo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paygo.paygo.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    UserProfile findUserProfileByUserId(String userId);
}
