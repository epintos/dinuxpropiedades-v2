package ar.edu.itba.it.paw.domain.users;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;

@Repository
public class HibernateUserRepo extends AbstractHibernateRepo implements
		UserRepo {
	@Autowired
	public HibernateUserRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public User get(int id) {
		return get(User.class, id);
	}

	public List<User> getAll(UserType userType) {

		Criteria crit = getCriteria(User.class);

		addEqualRestriction(crit, "userType", userType);
		return listResults(crit);
	}

	public User get(String username) {
		if (existsUsername(username)) {

			Criteria crit = getCriteria(User.class);
			addEqualRestriction(crit, "username", username);
			return (User) listResults(crit).get(0);

		}
		return null;
	}

	public void add(User user) {
		if (existsUsername(user.getUsername())) {
			throw new DuplicatedUserException(user);
		}
		save(user);
	}

	private boolean existsUsername(String username) {

		Criteria crit = getCriteria(User.class);
		addEqualRestriction(crit, "username", username);

		return !listResults(crit).isEmpty();
	}
}
