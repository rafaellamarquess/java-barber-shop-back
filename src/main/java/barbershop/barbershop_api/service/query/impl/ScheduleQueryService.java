package barbershop.barbershop_api.service.query.impl;

import barbershop.barbershop_api.entity.ScheduleEntity;
import barbershop.barbershop_api.exception.ScheduleInUseException;
import barbershop.barbershop_api.repository.IScheduleRepository;
import barbershop.barbershop_api.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private final IScheduleRepository repository;

    @Override
    public ScheduleEntity findById(final long id) {
        return repository.findById(id).orElseThrow(
                () -> new ChangeSetPersister.NotFoundException("Agendamento não encontrado")
        );
    }

    @Override
    public List<ScheduleEntity> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt) {
        if (repository.existsByStartAtAndEndAt(startAt, endAt)){
            var message = "Já existe um cliente agendado no horário solicitado";
            throw new ScheduleInUseException(message);
        }
    }

}
