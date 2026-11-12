// vim:fileencoding=utf-8:foldmethod=marker


package com.cloudnomads.underbash.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloudnomads.underbash.model.User;


@Repository 
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByUsername(String username);
	public boolean existsByUsername(String username);

	public List<User> findAllByActive(Boolean active);

	//{{{ Query Methods

	@Query ("SELECT u FROM User u WHERE u.total_score = :tot_score")
	public List<User> findAllByTotalScore(Integer tot_score);
	@Query ("SELECT u FROM User u WHERE u.created_at = :created_at")
	public List<User> findAllByCreated_at(LocalDate created_at);

	//}}}
}
