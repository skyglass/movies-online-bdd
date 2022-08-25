package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PublisherRepositoryTest {
    @Autowired
    PublisherRepository publisherRepository;

    @Test
    public void findForId_throwsException_whenThereAreNoPublishersWithId() {
        final Long nonExistentId = -1L;

        assertThatThrownBy(() -> publisherRepository.findForId(nonExistentId)).isInstanceOf(DataAccessException.class);
    }

    @Test
    public void findForId_returnsPublisher_whenPublishersWithIdExists() {
        final Long publisherId = 1L;
        final Publisher expectedPublisher = new Publisher(1L, "Quality", "QR", "8565554255", "J. Mallard");

        Publisher publisher = publisherRepository.findForId(publisherId);

        assertPublisher(publisher, expectedPublisher);
    }

    private void assertPublisher(Publisher actual, Publisher expected) {
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getShortName()).isEqualTo(expected.getShortName());
        assertThat(actual.getContactName()).isEqualTo(expected.getContactName());
        assertThat(actual.getContactNumber()).isEqualTo(expected.getContactNumber());
    }

    @Test
    public void findAll_returnsPublishers_whenThereAreThree() {
        List<Publisher> allPublishers = publisherRepository.findAll();

        List<Publisher> expectedPublishers = List.of(
                new Publisher(1L, "Quality", "QR", "8565554255", "J. Mallard"),
                new Publisher(2L, "SpinFast Records", "SFR", "+44 1632 960609", "Lawrence Hendricks II"),
                new Publisher(3L, "Argentina", "A", "2125553695", "Jamie Weston")
        );

        assertThat(allPublishers).hasSize(3);
        assertThat(allPublishers.size()).isEqualTo(expectedPublishers.size());

        IntStream.range(0, allPublishers.size()).forEach(i -> {
            assertPublisher(allPublishers.get(i), expectedPublishers.get(i));
        });
    }

    @Test
    public void update_modifiesPublisherAtId() {
        Publisher expected = new Publisher(1L, "Updated Name", "US", "2125553456", "Updated contact");
        publisherRepository.update(expected);

        Publisher updated = publisherRepository.findForId(expected.getId());

        assertPublisher(updated, expected);
    }

    @Test
    public void update_throwsException_whenIdNotFound() {
        var publisherWithNonExistentId = new Publisher(0L, "Updated Name", "US", "2125553456", "Updated contact");

        assertThatThrownBy(() -> {
            publisherRepository.update(publisherWithNonExistentId);
        }).isInstanceOf(RuntimeException.class);
    }
}
