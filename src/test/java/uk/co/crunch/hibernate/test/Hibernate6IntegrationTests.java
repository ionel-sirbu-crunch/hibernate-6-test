package uk.co.crunch.hibernate.test;

import org.hibernate.FetchNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import uk.co.crunch.hibernate.test.entity.A;
import uk.co.crunch.hibernate.test.entity.B;
import uk.co.crunch.hibernate.test.entity.C;
import uk.co.crunch.hibernate.test.query.AQuery;
import uk.co.crunch.hibernate.test.query.CQuery;
import uk.co.crunch.hibernate.test.repository.ARepository;
import uk.co.crunch.hibernate.test.repository.CRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class Hibernate6IntegrationTests {

	@Autowired
	private ARepository aRepository;

	@Autowired
	private CRepository cRepository;

	@Autowired
	private AQuery aQuery;

	@Autowired
	private CQuery cQuery;

	@Sql("/preload_db.sql")
	@Transactional
	@Test
	void queryOnPreloadedRecordCausesFetchNotFoundException() {
		// Prerequisite - entity C must be preloaded in the persistence context
		Optional<C> c = cRepository.findById(11L);

		assertThat(c).isPresent();
		assertThat(c.get().getId()).isEqualTo(11);
		assertThat(c.get().getEs()).hasSize(3);

		List<A> as = aRepository.searchByCriteria(2L, 10011L);
		assertThat(as).hasSize(2);
		B b = as.get(0).getB();

		// what we want
//		assertThat(b.getC().getD().getId()).isEqualTo(101);

		// what we get
		assertThatThrownBy(b::getC).isInstanceOf(FetchNotFoundException.class)
				.hasMessage("Entity `uk.co.crunch.hibernate.test.entity.D` with identifier value `101` does not exist");
	}

	@Sql("/preload_db.sql")
	@Transactional
	@Test
	void usingEntityManager() {
		// preload
		C c = cQuery.searchByB(2L);
		assertThat(c.getId()).isEqualTo(11);

		List<A> as = aQuery.searchByCriteria(2L, 10011L);
		assertThat(as).hasSize(2);
		B b = as.get(0).getB();

		// what we want
//		assertThat(b.getC().getD().getId()).isEqualTo(101);

		// what we get
		assertThatThrownBy(b::getC).isInstanceOf(FetchNotFoundException.class)
				.hasMessage("Entity `uk.co.crunch.hibernate.test.entity.D` with identifier value `101` does not exist");
	}

}
